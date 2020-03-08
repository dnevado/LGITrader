/* So the simple fact is that if an order is fully filled while the client application is not connected to TWS, or TWS is not connected to the IB servers, the client will never receive an orderStatus with a status of ï¿½Filledï¿½.



To deal with this situation, there are three things that need to be done:



1.       The client application must save information about orders it has placed. This must be persisted to a file or database for use when the application is restarted.



2.       When the client application connects to TWS it must call reqExecutions and reqOpenOrders. The combination of the saved order information and the information received via execDetails and openOrder is enough to reconstruct exactly what the current state is. (Note that there is a TWS API configuration option for open orders to be notified automatically when a client connects, but itï¿½s better to call reqOpenOrders as you cannot guarantee that this option will be set.)



3.       When TWS reconnects to the IB servers, the client receives an 1101 or 1102 message code via the error callback. It must call reqExecutions and reqOpenOrders to get up-to-date information about orders and fills.

*
*
*
*https://groups.io/g/twsapi/message/2127?p=,,,20,0,0,0::Created,,nextvalidId+clients,20,2,0,4039537
*https://groups.io/g/twsapi/topic/4039546#2193
*
*/

package com.ibtrader.interactive;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.ta4j.core.TimeSeries;

import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

import com.ib.client.Bar;
import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDescription;
import com.ib.client.ContractDetails;
import com.ib.client.DeltaNeutralContract;
import com.ib.client.DepthMktDataDescription;
import com.ib.client.EClientSocket;
import com.ib.client.EJavaSignal;
import com.ib.client.EReader;
import com.ib.client.EReaderSignal;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.ExecutionFilter;
import com.ib.client.FamilyCode;
import com.ib.client.HistogramEntry;
import com.ib.client.HistoricalTick;
import com.ib.client.HistoricalTickBidAsk;
import com.ib.client.HistoricalTickLast;
import com.ib.client.NewsProvider;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.PriceIncrement;
import com.ib.client.SoftDollarTier;
import com.ib.client.TickAttr;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.ShareModel;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil;
import com.ibtrader.data.service.HistoricalRealtimeServiceUtil;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.PriceTimeSeriesCacheList;
import com.ibtrader.util.RealTimeCallBacksCacheList;
import com.ibtrader.util.Utilities;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;


//! [ewrapperimpl]
public class TIMApiWrapper implements EWrapper {
	//! [ewrapperimpl]
	
	//! [socket_declare]
	Log _log = LogFactoryUtil.getLog(TIMApiWrapper.class);

	protected EReaderSignal readerSignal;
	protected EClientSocket clientSocket;
	protected int currentOrderId = -1;
	
	protected int _clientId = 0;
	protected String _host = "127.0.0.1";
	protected int _port = 1;
	
	protected long  guestGroupId=0;
	
	private boolean _sendDisconnectEvent = false; 
	
	/* ATRIBUTOS */
	private Share _ibtarget_share= null;
	private Organization _ibtarget_organization= null;

	
	/* Utlima barra cargada del historical data, lo usamos para guardar la ultina barra valida que consideraremos el cierre */
	private Date  _lastHistoricalBarDate = null;
		
	
	private boolean isFilledData = Boolean.FALSE; // DISTINGUIMOS CUANDO PEDIMOS EL HISTORICAL PARA RELLENAR LAS BARRAS DE TIEMPO REAL NECESARIAS 
	
	
	public boolean isFilledData() {
		return isFilledData;
	}

	public void setFilledData(boolean isFilledData) {
		this.isFilledData = isFilledData;
	}

	private boolean standalone_mode = false; // si solo hay una TWS para todos   
	
	private String  cronId = "";  // nos sirve para cambiar el value del clientId en su caso en la BBDD
	
	private String userTWS = "";  
	private String historicalDataRequest = ""; // me vale solo cuando soy un usuario edemo  y la TWS solo me da el historical del dia de hoy, entonces, lo uso para simular

	private boolean historialDataEnd = Boolean.FALSE;
	
	
	/* Modelo de cache de,
	 * 
	 *  Price : Para poder asociar el price del tick a la siguiente callback que es tickSize (despues de una invoación a tickPrice viene un 
	 *  tickSize para el volumen)
	 *  
	 */
	private static int CACHE_MAX_ELEMENTS = 50; 

	private static LinkedHashMap<String, String> stockRealTimePricesCacheLRUMap = new RealTimeCallBacksCacheList<>(CACHE_MAX_ELEMENTS, .75f, true);	// new LinkedHashMap<String, String>();
	
	
	
	//! [socket_declare]
	
	//! [socket_init]
	public TIMApiWrapper(int clientId, boolean debug) {
		readerSignal = new EJavaSignal();
		clientSocket = new EClientSocket(this, readerSignal);
		_clientId = clientId;	
	}
	
	public boolean isHistorialDataEnd() {
		return historialDataEnd;
	}

	

	public String getUserTWS() {
		return userTWS;
	}

	public void setUserTWS(String userTWS) {
		this.userTWS = userTWS;
	}

	public TIMApiWrapper(int clientId ) {
		readerSignal = new EJavaSignal();
		clientSocket = new EClientSocket(this, readerSignal);
		_clientId = clientId;
		try {
			guestGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), GroupConstants.GUEST).getGroupId();
			standalone_mode = (Utilities.getConfigurationValue(IBTraderConstants.keyFAKE_MODE, PortalUtil.getDefaultCompanyId(), guestGroupId).equals("1") ? Boolean.TRUE : Boolean.FALSE);	  // el dos para leer, el 3 para escribir
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			guestGroupId = -1;
		}
		

		 
	}
	
	/* USUARIO DEMO, SOLO PERMITE UN DIA, JUSTO EL DIA DE HOY */
	/* IMPORTANTO QUE SEA DIA A DIA PARA SABER CON USUARIOS DEMO QUE FECHA ES LA TRATADA , YA QUE SOLO VIENE EL ULTIMO DIA
	 * 
	 */
	public void cancelHistoricalData(int requestId)
	{
		clientSocket.cancelHistoricalData(requestId);
	}
	
	public void cancelMarketData(int requestId)
	{
		clientSocket.cancelMktData(requestId);
	}
	
	/* TICKER ID , SHAREID , COMPANYID, GROUPUID */
	private String getUniqueWrapperConnectionId(int tickerId)
	{
		
		StringBundler  keyBuilder= new StringBundler(String.valueOf(tickerId));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(_clientId));		
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(_ibtarget_organization.getCompanyId()));
		keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(_ibtarget_organization.getGroupId()));
		/* keyBuilder.append(StringPool.PIPE);
		keyBuilder.append(String.valueOf(field == ConfigKeys. _TICKTYPE_CLOSE ? Boolean.TRUE : Boolean.FALSE)); */
	
		
		return  keyBuilder.toString();
		
	}
	
	public void getHistoricalData(int requestId, Contract contract, String EndTime){
		
		historicalDataRequest = EndTime;
		
		// CONTROL
		if (this.getUserTWS().equalsIgnoreCase(Utilities._DEFAULT_USER_DEMO_))
			clientSocket.reqHistoricalData(requestId, contract, "", ConfigKeys.SIMULATION_INTERVAL_PERIOD, ConfigKeys.SIMULATION_MINUTES_BAR_SIZE + " mins", "TRADES", 1, 1, false, null);
		else
			clientSocket.reqHistoricalData(requestId, contract, EndTime, ConfigKeys.SIMULATION_INTERVAL_PERIOD, ConfigKeys.SIMULATION_MINUTES_BAR_SIZE + " mins", "TRADES", 1, 1, false, null);
		
	}
	
	// formatted  = "20190511 23:59:59";
	//client.reqHistoricalData(4002, contract, formatted, "7 D", "5 mins", "TRADES", 0, 1, false, null);
	public void getHClosingPricesData(int requestId, Contract contract, String EndTime){
				
		//clientSocket.reqHistoricalData(requestId, contract, EndTime,  ConfigKeys.SIMULATION_INTERVAL_PERIOD, ConfigKeys.CLOSE_BAR_SIZE, "TRADES", 0, 1, false, null);
		clientSocket.reqHistoricalData(requestId, contract, EndTime,  "6 M", ConfigKeys.CLOSE_BAR_SIZE, "TRADES", 1, 1, false, null);

	}
	
	
	
	
	
	public void getContractDetails(int requestId, Contract contract){
		clientSocket.reqContractDetails(requestId, contract);
	}
	
	/* para saber la hora local del servidor y obtener las operaciones previas */
	public void getCurrentTwsTime() throws InterruptedException{
		clientSocket.reqCurrentTime();		
	}
	public void getOpenOrders(int requestId) throws InterruptedException{
		clientSocket.reqOpenOrders();		
	}
    public void getExecutionOrders(int requestId) throws InterruptedException{

    	/* DESDE CUANDO */
    	
	//	int  _orders_from_hours = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyLAST_POSITIONS_TO_CHECK_IN_HOURS, _ibtarget_organization.getCompanyId(), guestGroupId)).intValue();;	  // el dos para leer, el 3 para escribir

		//yyyymmdd hh:mm:ss
		// ojo entre la hora de la TWS ya la hora UTC 
	//	Calendar now = Calendar.getInstance();
		/*now.setTime(currentTWSDate);		
		now.add(Calendar.HOUR_OF_DAY, -_orders_from_hours);
		SimpleDateFormat sdf = new SimpleDateFormat(Utilities.__IBTRADER_ORDERS_EXECUTED__DATE_FORMAT);		
		ExecutionFilter filter = new ExecutionFilter(this._clientId,"",sdf.format(now.getTime()),"","","","");*/
		ExecutionFilter filter = new ExecutionFilter();
		/* NO ME FUNCIONA EL FILTRO POR FECHAS */
		clientSocket.reqExecutions(requestId,  filter);
	}
	
	public void getRealTime(int requestId, Contract contract) throws InterruptedException{
		Thread.sleep(1000);	
		clientSocket.reqMarketDataType(ConfigKeys.MARKET_DATA_TYPE_LIVE);
		clientSocket.reqMktData(requestId, contract,  "", false, false, null); // false
	}
	public boolean isConnected() {
		 //! [connect]
		return clientSocket.isConnected(); 
	}
	public void  reqNextId() throws InterruptedException {
		 //! [connect]
		
		clientSocket.reqIds(-1);
		Thread.sleep(1000);	
 
	}
	
	public void disconnect() throws InterruptedException {
		 //! [connect]
		_sendDisconnectEvent = true;
		Thread.sleep(1000);	
		clientSocket.eDisconnect();
	}
	
	public void connect(String _HOST, int  _PORT, int _CLIENT_ID) throws InterruptedException {
		 //! [connect]
		_clientId = _CLIENT_ID;
		_host = "127.0.0.1";
		_port =_PORT;
		
		clientSocket.eConnect(_HOST, _PORT,_CLIENT_ID); 
		 //! [connect]
		 //! [ereader]
		_sendDisconnectEvent = false;
		Thread.sleep(1000);
		 final EReader reader = new EReader(clientSocket, readerSignal);   		
		 reader.start();
		//An additional thread is created in this program design to empty the messaging queue
		 //
		new Thread(() -> {
		    while (clientSocket.isConnected() && !_sendDisconnectEvent) {
		    	readerSignal.waitForSignal();
		    	try {
					reader.processMsgs();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        /*try {
		           
		        } catch (Exception e) {
		        	_log.info("Connnect Exception: "+e.getMessage());
		        }*/
		    }
		   // _log.debug("_sendDisconnectEvent: "+_sendDisconnectEvent);
		 }).start();
		Thread.sleep(1000);
		
	}
	
	//! [socket_init]
	public EClientSocket getClient() {
		return clientSocket;
	}
	
	public EReaderSignal getSignal() {
		return readerSignal;
	}
	
	
	/* SERA EL MAXIMO DE 
	 * 1. TWS 
	 * 2. MAX ORDERID DE LA TABLA ORDENES
	 * 3. MAX DE LA ORDENES DE POSICIONES REALIZAZAD  
	 */	
	public int getNextOrderId() {
		/* POSICIOES */
		
		String position_mode = Utilities.getPositionModeType(null, _ibtarget_organization.getCompanyId(),_ibtarget_organization.getGroupId()); 

		
		long  _currentMaxPositionsId= PositionLocalServiceUtil.findMaxOrderClientCompanyGroup(_ibtarget_organization.getCompanyId(), _ibtarget_organization.getGroupId(), this._clientId, position_mode);
		// ordenes 
		long   _currentMaxOrderId = IBOrderLocalServiceUtil.findMaxOrderClientCompanyGroup(_ibtarget_organization.getCompanyId(), _ibtarget_organization.getGroupId(), this._clientId);
		/* maximo de los tres valores */
		long  _INCREMENT_ORDER_ID   = Math.max(currentOrderId, Math.max(_currentMaxPositionsId, _currentMaxOrderId));
		
		/* Entre lo que nos da la tws (se solapan entre clientIds y lo que nos la ultima posicion (hay que guardarlos) , eleigimos el mayor */
		currentOrderId = (int) _INCREMENT_ORDER_ID +1;
		return currentOrderId;
	}
	
	public int getCurrentOrderId() {
		return currentOrderId++;
	}
	
	 //! [tickprice]

	//! [tickprice]
	
	//! [ticksize]
	
	
	
	@Override
	public void tickSize(int tickerId, int field, int size) {
	try
	{
		_log.trace("tickSize called");
		
		// TODO Auto-generated method stub
	    //_log.debug("Impl tickPrice : + " + tickerId + ",prices:" + price + ",field" + field);		
		// TODO Auto-generated method stub
		if (size>0 && (field==ConfigKeys._TICKTYPE_VOLUME)) 
		{
			/* si no lo contiene, deberia estar relleno por el TickPrice  */
			if (!stockRealTimePricesCacheLRUMap.containsKey(getUniqueWrapperConnectionId(tickerId)))
			{
				return;
			}
			/* lo contiene el price
			 * 
			 */
			/* PRICE|(CIERRE(S/N)|SHAREID|PERSISTED(S/N)
			 * 
			 * Persisted: Lo ponemos a 0 la primera vez ya que se llama varias veces a TickSize antes de tener el cambio de precio */
			String priceBuilder = stockRealTimePricesCacheLRUMap.get(getUniqueWrapperConnectionId(tickerId));		
			String[] priceArray =  priceBuilder.split(Pattern.quote(StringPool.PIPE));  		
			
			double tickPrice = Double.valueOf(priceArray[0]);  // ES EL ULTIMO, PUESTO QUE DESPUES SE LLAMA AQUI. 
			boolean bClosePrice =  Boolean.valueOf(priceArray[1]);  
			long shareId = Long.valueOf(priceArray[2]);
			int  previousVolume = Integer.valueOf(priceArray[3]);
			int  currentVolume = (previousVolume>0 ?  size - previousVolume : size);
			boolean bPersisted =  Boolean.valueOf(priceArray[4]);
			
			if (!bPersisted) /// ya ha sido persistido para ese precio , en otro cambio se pone a 0
			{				
				
				/* SOLO ALMACENAMOS DATOS DE LAS HORAS DE OPERATIVA PARA NO SALVAR REALTIME DE HORAS FUERA DE MERCADO.*/
				 Share _share =  ShareLocalServiceUtil.fetchShare(shareId);
				if (Utilities.IsTradingEnabledFromHours(_share.getTrading_hours()))
				{
				 				
					_log.trace("tickSize called and persisted RealTime ");
					
					Date cNow = new Date();
					Calendar _calNow = Calendar.getInstance();
					_calNow.setTime(cNow);
														
					Realtime  oReal = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
					oReal.setGroupId(this._ibtarget_organization.getGroupId());
					oReal.setCompanyId(_ibtarget_organization.getCompanyId());
					oReal.setShareId(shareId);
					oReal.setValue(tickPrice);					
					oReal.setCloseprice(bClosePrice ? Boolean.TRUE : Boolean.FALSE);
					
					if (oReal.getCloseprice())
					{
						_calNow.add(-1, Calendar.DATE);
						_calNow.set(Calendar.HOUR, 23);
						_calNow.set(Calendar.MINUTE, 59);
						_calNow.set(Calendar.SECOND, 59);
					}
					
					oReal.setCreateDate(_calNow.getTime());
					oReal.setModifiedDate(_calNow.getTime());
					oReal.setVolume(currentVolume);
					
					RealtimeLocalServiceUtil.addRealtime(oReal);
				 } 
			}
			
			
			/* ACTUALIZAMOS EL VOLUMEN PARA LA PROXIMA BARRA */
			/* PRICE|(CIERRE(S/N)|SHAREID|PERSISTED(S/N)
			 * 
			 * Persisted: Lo ponemos a 0 la primera vez ya que se llama varias veces a TickSize antes de tener el cambio de precio */
			StringBuilder sb = new StringBuilder();
			sb.append(tickPrice);
			sb.append(StringPool.PIPE);
			sb.append(bClosePrice);
			sb.append(StringPool.PIPE);
			sb.append(String.valueOf(shareId));
			sb.append(StringPool.PIPE);
			sb.append(String.valueOf(size));
			sb.append(StringPool.PIPE);
			sb.append(String.valueOf(Boolean.TRUE));  // se guardo, evitamos que se persista mas 
			
			
			stockRealTimePricesCacheLRUMap.put(getUniqueWrapperConnectionId(tickerId), sb.toString());	
			
		}
			
	}
	catch (Exception e)
	{
		_log.debug(e.getMessage());
	}
		
	}
	//! [ticksize]
	//! [tickoptioncomputation]
	@Override
	public void tickOptionComputation(int tickerId, int field,
			double impliedVol, double delta, double optPrice,
			double pvDividend, double gamma, double vega, double theta,
			double undPrice) {
		_log.debug("TickOptionComputation. TickerId: "+tickerId+", field: "+field+", ImpliedVolatility: "+impliedVol+", Delta: "+delta
                +", OptionPrice: "+optPrice+", pvDividend: "+pvDividend+", Gamma: "+gamma+", Vega: "+vega+", Theta: "+theta+", UnderlyingPrice: "+undPrice);
	}
	//! [tickoptioncomputation]
	
	//! [tickgeneric]
	@Override
	public void tickGeneric(int tickerId, int tickType, double value) {
		//_log.debug("Tick Generic. Ticker Id:" + tickerId + ", Field: " + ", Value: " + value);
	}
	//! [tickgeneric]
	
	//! [tickstring]
	@Override
	public void tickString(int tickerId, int tickType, String value) {
	//	_log.debug("Tick string. Ticker Id:" + tickerId + ", Type: " + tickType + ", Value: " + value);
	}
	//! [tickstring]
	@Override
	public void tickEFP(int tickerId, int tickType, double basisPoints,
			String formattedBasisPoints, double impliedFuture, int holdDays,
			String futureLastTradeDate, double dividendImpact,
			double dividendsToLastTradeDate) {
		_log.debug("TickEFP. "+tickerId+", Type: "+tickType+", BasisPoints: "+basisPoints+", FormattedBasisPoints: "+
			formattedBasisPoints+", ImpliedFuture: "+impliedFuture+", HoldDays: "+holdDays+", FutureLastTradeDate: "+futureLastTradeDate+
			", DividendImpact: "+dividendImpact+", DividendsToLastTradeDate: "+dividendsToLastTradeDate);
	}
	//! [orderstatus]
	
	/* lOrders
	 * LISTA DE ORDENES QUE ACOMPAï¿½AN A LA ORDEN PADRE, SOLO SE APLICA A ORDENES DE TIPO TRAIL PARA AJUSTAR BENEFICIO 
	 */
	
	/* thread safe */
	
	public  synchronized  void cancelOrder(Contract contract, Order parentOrder, List<Order> childsOrders, OrderState orderState, long  positionTWSId) {
		
		clientSocket.cancelOrder( (int) positionTWSId);
		
	}
	
	public  synchronized  void openOrder(Contract contract, Order parentOrder, List<Order> childsOrders, OrderState orderState, long positionId) {
	
		
		 /* METEMOS DOBLE VALIDACION POR LOS THREADS EN CASO DE QUE HAYA OPERACION PREVIA YA EN MARCHA 	
		  * 
		  * 
		  */
		/******************************************************************************************************************************
		 * OJO, LAS ORDENES INCLUIDAS EN LAS POSICIONES NO SE PUEDEN REUTILIZAR PUESTO QUE SE USAN PARA TRATARLAS EN CASO DE DESCONEXION 
		 *  
		 */
		
		
		/* METEMOS LA LISTA DE ORDENES HIJAS */
		
		Position _position = PositionLocalServiceUtil.fetchPosition(positionId);
		/* PUEDE VENIR EL MODO FAKE O STANDALONE */
		if (standalone_mode) // ya esta todo configurado para que no se mande a la TWS 
			return;
		
		
		int  _INCREMENT_ORDER_ID = getNextOrderId();
		IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);			/* insertamos control de ordenes de peticion */
		_order.setCompanyId(_ibtarget_share.getCompanyId());
		_order.setGroupId(_ibtarget_share.getGroupId());
		_order.setShareID(_ibtarget_share.getShareId());
		_order.setOrdersId(_INCREMENT_ORDER_ID);
		_order.setCreateDate(new Date());
		_order.setModifiedDate(new Date());			
		_order.setIbclientId(_clientId);				
		_order.setRemovable_on_reboot(Boolean.FALSE);	 /* los requestid de las posiciones no se borran */		
		IBOrderLocalServiceUtil.updateIBOrder(_order);
		/* ACTULIAMOS CON LA POSICION DE ENTRADA  SI  ES UNA ENTRADA */
		if (_position.getDate_real_in()!=null)  // SALIDA DE LA MISMA POSICION
		{
			_position.setPositionId_tws_out(_INCREMENT_ORDER_ID);
			_position.setClientId_out(this._clientId);
		}
		else
		{
			_position.setPositionId_tws_in(_INCREMENT_ORDER_ID);
			_position.setClientId_in(this._clientId);
		}
		PositionLocalServiceUtil.updatePosition(_position);
		clientSocket.placeOrder(new Long(_INCREMENT_ORDER_ID).intValue(), contract, parentOrder);
		
		_log.info("placeOrder _INCREMENT_ORDER_ID: " + _INCREMENT_ORDER_ID +",number:" + _position.getShare_number()  + ",symbol:"+  contract.symbol()+",groupid::" + _ibtarget_share.getGroupId() + " IP:" + this._host + ",port:" + this._port + ",client:" + this._clientId + ",Isconnected:" + this.isConnected());

		//	_log.info("1. openOrder...." +  positionId + "," + _INCREMENT_ORDER_ID);
		// Si hay hijas, aseguramos el transmit correcto,
		
		/* CAMBIO, NO HAY HIJAS PORQUE LAS TRAILING VAN CONTROLADAS INTERNAMENTE 
		if (childsOrders!=null && !childsOrders.isEmpty())			
			parentOrder.transmit(false);
	
		
		for (Order childOrder : childsOrders)
		{
			_INCREMENT_ORDER_ID++;
			childOrder.parentId(parentOrderId);
			childOrder.transmit(true);
			IBOrderLocalServiceUtil.deleteByOrderCompanyGroup(_INCREMENT_ORDER_ID, _ibtarget_share.getCompanyId(), _ibtarget_share.getGroupId(),_clientId,_ibtarget_share.getShareId());
			 _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);			
			_order.setCompanyId(_ibtarget_share.getCompanyId());
			_order.setGroupId(_ibtarget_share.getGroupId());
			_order.setShareID(_ibtarget_share.getShareId());
			_order.setOrdersId(_INCREMENT_ORDER_ID);
			_order.setCreateDate(new Date());
			_order.setModifiedDate(new Date());			
			_order.setIbclientId(_clientId);					
			IBOrderLocalServiceUtil.updateIBOrder(_order);
			clientSocket.placeOrder(new Long(_INCREMENT_ORDER_ID).intValue(), contract, childOrder);
		}	
	    */	
			
		//}
		
	}
	
	//! [openorder]
	@Override
	public void openOrder(int orderId, Contract contract, Order order,OrderState orderState) {
		/*  _log.info("OpenOrder. ID: "+orderId+", "+contract.symbol()+", "+contract.secType()+" @ "+contract.exchange()+": "+
			order.action()+", "+order.orderType()+" "+order.totalQuantity());
		*/				 
		}
			
	//! [openorder]
	
	//! [openorderend]
	@Override
	public void openOrderEnd() {
		_log.debug("OpenOrderEnd");
	}
	//! [openorderend]
	
	
	//! [updateaccountvalue]
	@Override
	public void updateAccountValue(String key, String value, String currency,
			String accountName) {
		_log.debug("UpdateAccountValue. Key: " + key + ", Value: " + value + ", Currency: " + currency + ", AccountName: " + accountName);
	}
	//! [updateaccountvalue]
	
	
	
	//! [updateaccounttime]
	@Override
	public void updateAccountTime(String timeStamp) {
		_log.debug("UpdateAccountTime. Time: " + timeStamp+"\n");
	}
	//! [updateaccounttime]
	
	//! [accountdownloadend]
	@Override
	public void accountDownloadEnd(String accountName) {
		_log.debug("Account download finished: "+accountName+"\n");
	}
	//! [accountdownloadend]
	
	//! [nextvalidid]
	@Override
	public void nextValidId(int orderId) {
		//_log.trace("Next Valid Id: ["+orderId+"]");
		currentOrderId = orderId;
	}
	//! [nextvalidid]
	
	//! [contractdetails]
	@Override
	public void contractDetails(int reqId, ContractDetails contractDetails) {
	
		/* Next Valid Id: [1]
		TimeZoneTrading: [EST5EDT] - 
		TradingHours: [20190323:CLOSED;20190324:CLOSED;20190325:0400-20190325:2000;20190326:0400-20190326:2000;20190327:0400-20190327:2000;
		20190328:0400-20190328:2000;20190329:0400-20190329:2000;20190330:CLOSED;
		20190331:CLOSED;20190401:0400-20190401:2000;20190402:0400-20190402:2000;20190403:0400-20190403:
		2000;20190404:0400-20190404:2000;20190405:0400-20190405:2000;20190406:CLOSED;20190407:CLOSED;
		20190408:0400-20190408:2000;20190409:0400-20190409:2000;20190410:0400-20190410:2000;20190411:0400-20190411:2000;
		20190412:0400-20190412:2000;20190413:CLOSED;20190414:CLOSED;20190415:0400-20190415:2000;20190416:0400-20190416:2000;
		20190417:0400-20190417:2000;20190418:0400-20190418:2000;20190419:0400-20190419:2000;20190420:CLOSED;
		20190421:CLOSED;20190422:0400-20190422:2000;20190423:0400-20190423:2000;20190424:0400-20190424:2000;
		20190425:0400-20190425:2000;20190426:0400-20190426:2000]   [AAPL], [STK], ConId: [265598] @ [SMART]
		
		FUTURO 
		20190326:1700-20190327:1515;20190327:1530-20190327:1600;20190327:1700-20190328:1515;20190328:1530-20190328:1600;20190328:1700-20190329:1515;20190329:1530-20190329:1600;20190330:CLOSED;20190331:1700-20190401:1515;20190401:1530-20190401:1600;20190401:1700-20190402:1515;20190402:1530-20190402:1600;20190402:1700-20190403:1515;20190403:1530-20190403:1600;20190403:1700-20190404:1515;20190404:1530-20190404:1600;20190404:1700-20190405:1515;20190405:1530-20190405:1600;20190406:CLOSED;20190407:1700-20190408:1515;20190408:1530-20190408:1600;20190408:1700-20190409:1515;20190409:1530-20190409:1600;20190409:1700-20190410:1515;20190410:1530-20190410:1600;20190410:1700-20190411:1515;20190411:1530-20190411:1600;20190411:1700-20190412:1515;20190412:1530-20190412:1600;20190413:CLOSED;20190414:1700-20190415:1515;20190415:1530-20190415:1600;20190415:1700-20190416:1515;20190416:1530-20190416:1600;20190416:1700-20190417:1515;20190417:1530-20190417:1600;20190417:1700-20190418:1515;20190418:1530-20190418:1600;20190418:1700-20190419:1515;20190419:1530-20190419:1600;20190420:CLOSED;20190421:1700-20190422:1515;20190422:1530-20190422:1600;20190422:1700-20190423:1515;20190423:1530-20190423:1600;20190423:1700-20190424:1515;20190424:1530-20190424:1600;20190424:1700-20190425:1515;20190425:1530-20190425:1600;20190425:1700-20190426:1515;20190426:1530-20190426:1600]
		
		EST5EDT --> TELEFONICA 
		EST5EDT --> netflix
		MET --> EUROSTOXX50 ESTX50 				
		
		
		31032019 : INCLUSO VAMOS A ACTUALIZAR EL INICIO FIN DE MERCADO CON LOS DATOS DEL SHARE.
		 
		*/
		
		
	try
	{
		
		/* GENERAMOS EL JSON CON LOS DATOS DEL HORAS DE TRADING SEGUN UTC */					
		JSONArray    jsonTradingHours = JSONFactoryUtil.createJSONArray();		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Utilities.__IBTRADER_TRADINHOURS_DATE_FORMAT);
		DateTimeFormatter expirationformatter = DateTimeFormatter.ofPattern(Utilities.__IBTRADER_CONTRACTEXPIRATION_DATE_FORMAT);		
		
		SimpleDateFormat returnedExpiration =  new SimpleDateFormat(Utilities.__IBTRADER_CONTRACTEXPIRATION_DATE_FORMAT);

		
		
		IBOrder _ibOrder;		 	
		 //_ibOrder = IBOrderLocalServiceUtil.fetchIBOrder(reqId);
		_ibOrder = IBOrderLocalServiceUtil.findByOrderShareClientGroupCompany(reqId, _ibtarget_share.getShareId(),_clientId, _ibtarget_organization.getCompanyId(),_ibtarget_share.getGroupId());
	 	if (_ibOrder==null)  // error en una posicion dada abierta		
	 		 return;
		
	 	Share oShare = ShareLocalServiceUtil.fetchShare(_ibOrder.getShareID());  						 	 
	 	
		// PARA FUTUROS m_lastTradedateOrContractMonth	"20190828 13:30 EST" (id=567)	
	 	// lo utilizamos para  saber fecha expiracion exacta 
	 	if (oShare.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS))
		{
		 	String[] lastTrade = Arrays.stream(contractDetails.contract().lastTradeDateOrContractMonth().split(StringPool.SPACE)).map(String::trim).toArray(String[]::new); 	 
		 	oShare.setExpiry_date(returnedExpiration.parse(Utilities.getConvertedUTCStringDate(lastTrade[0].concat(" ").concat(lastTrade[1]), expirationformatter, contractDetails.timeZoneId())));		 	
		 	
		}
	 	
	 	
	 	/* EXTRAï¿½O, PARA APPLE, LAS TRADINHOURS PARECEN INCORRETAS PERO NO PARA LOS FUTURES, QUE SON MAS APROPIADAS Y CERTEREZAS LAS TRADING */
		/* PARECE UNA ï¿½APA 
		 * https://www.cmegroup.com/trading/equity-index/us-index/sandp-500_contract_specifications.html
		 * */			 
		String _traingHours = contractDetails.liquidHours();

		/* if (oShare.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_STOCK))
		{
			_traingHours = contractDetails.liquidHours();
		}*/
	
		if (!_traingHours.isEmpty()) // 					
		{
						
			String[] tradingHours = Arrays.stream(_traingHours.split(StringPool.SEMICOLON)).map(String::trim).toArray(String[]::new);
			if (tradingHours.length>0)
			{
				for (String hour: tradingHours)
				{
					if (!hour.contains("CLOSED") && !hour.equals("")) //NO HAY MERCADO, LO IGNORAMOS 
					{
						JSONObject  jsonTradingHour = JSONFactoryUtil.createJSONObject();
						
						//20190326:1700-20190327:1515
						String[] tradingHour = Arrays.stream(hour.split(StringPool.DASH)).map(String::trim).toArray(String[]::new);
						
						/* ZoneId.getAvailableZoneIds().stream()
				        .filter(z -> z.length() == 3)
				        .forEach(System.out::println);
						*/
						
						_log.trace("Trading hour:" + hour);
						
						String[] from = Arrays.stream(tradingHour[0].split(StringPool.COLON)).map(String::trim).toArray(String[]::new);
						String[] to =   Arrays.stream(tradingHour[1].split(StringPool.COLON)).map(String::trim).toArray(String[]::new);
						
						jsonTradingHour.put("fromDate", Utilities.getConvertedUTCStringDate(from[0].concat(" ").concat(from[1]), formatter, contractDetails.timeZoneId()));
						jsonTradingHour.put("toDate", Utilities.getConvertedUTCStringDate(to[0].concat(" ").concat(to[1]), formatter, contractDetails.timeZoneId()));
						jsonTradingHours.put(jsonTradingHour);
						
					}
				}
			}
								
			_log.trace("LiquidHours:" + contractDetails.liquidHours());
			_log.trace("TIMApiWrapper ContractDetails. ReqId: ["+reqId+"] - TimeZoneTrading: ["+contractDetails.timeZoneId()+ "] - ["+contractDetails.contract().symbol()+"], ["+contractDetails.contract().secType()+"], ConId: ["+contractDetails.contract().conid()+"] @ ["+contractDetails.contract().exchange()+"]");		
		 		 

			LocalDate now = LocalDate.now();
			ZoneId UTCZone = ZoneId.systemDefault(); // UTC 	
	   		ZonedDateTime zonedDateTime = now.atStartOfDay(UTCZone);   	
	   		zonedDateTime = zonedDateTime.minus(-1, ChronoUnit.SECONDS);
	   		
	   		oShare.setDate_validated_trader_provider(Date.from(zonedDateTime.toInstant()));			
	   		oShare.setValidated_trader_provider(Boolean.TRUE);
	   		oShare.setLast_error_trader_provider(null);			
	   		oShare.setTrading_hours(jsonTradingHours.toString());
			/* actualizamos datos error de operativa */
			_log.trace("Updating contractDetails share:" + oShare.getSymbol());			
			ShareLocalServiceUtil.updateShare(oShare);			
			Market updateMarket = Utilities.fillOpenEndHoursMarket(jsonTradingHours.toString());
			if (Validator.isNotNull(updateMarket))
			{
				Market market = MarketLocalServiceUtil.fetchMarket(oShare.getMarketId());
				market.setStart_hour(updateMarket.getStart_hour());
				market.setEnd_hour(updateMarket.getEnd_hour());
				MarketLocalServiceUtil.updateMarket(market);
				
				_log.trace("Updating market " + updateMarket.getName() + "," + updateMarket.getStart_hour() + "," + updateMarket.getEnd_hour());
			}
		
		}
	}
	catch (Exception e)
	{
		_log.info("Error getting contract details :" + e.getMessage());
	}
		
	}
	
	
	
	
	
	//! [contractdetails]
	@Override
	public void bondContractDetails(int reqId, ContractDetails contractDetails) {
		_log.debug("bondContractDetails");
	}
	//! [contractdetailsend]
	@Override
	public void contractDetailsEnd(int reqId)
    {	
	
	}

	//! [contractdetailsend]
	
	//! [execdetails]
	
	/* VERIFICAMOS ORDENES COMPLETEADAS Y NO VERIFICADAS DEBIDO A DESCONEXIONES, 
	 * 
	 *  reqId orderId, aqui no aplica ï¿½
	 *  
	 *  execution.orderId() es el order de position de la tabla 
	 *  */
	@Override
	public void execDetails(int reqId, Contract contract, Execution execution) {
	try
	{
		//_log.info("ExecDetails. "+reqId+" - ["+contract.symbol()+"], ["+contract.secType()+"], ["+contract.currency()+"], ["+execution.execId()+"], ["+execution.orderId()+"], ["+execution.shares()+"]");
		String position_mode = Utilities.getPositionModeType(null, _ibtarget_organization.getCompanyId(),_ibtarget_organization.getGroupId()); 

		
		Position _oPosition = PositionLocalServiceUtil.findByPositionID_In_TWS(_ibtarget_organization.getGroupId(), _ibtarget_organization.getCompanyId(),execution.orderId(),execution.clientId(), position_mode);
		boolean bChanged = false;
		// SI ES NULL, QUIERE DECIR QUE PUEDE VENIR UNA OPERACION DE VENTA...LA BUSCAMOS.		
		if (_oPosition==null)
		{
			_oPosition = PositionLocalServiceUtil.findByPositionID_Out_TWS(_ibtarget_organization.getGroupId(), _ibtarget_organization.getCompanyId(),execution.orderId(),execution.clientId(), position_mode);
			if (_oPosition==null) 
			{
				//_log.info("execDetails order not found for Order Key:" + reqId + ",contract:" + contract.symbol() + ",Execution:" + execution.avgPrice());
				return;
			}
			else
			{
				/*  SIENDO UNA VENTA, VERIFICAMOS QUE NO ESTE FILLED, CERRRAMOS LA VENTA COMO OK */
				if (!_oPosition.getState_out().equals(PositionStates.statusTWSCallBack.Filled.toString()))
				{
					_oPosition.setState_out(PositionStates.statusTWSCallBack.Filled.toString());
					_oPosition.setState(PositionStates.status.SELL_OK.toString());
					_oPosition.setDate_real_out(_oPosition.getDate_out());
					_oPosition.setPrice_real_out(execution.avgPrice());
					
					
					bChanged = true;
					
				}

			}
		}
		else
		{
			/*  SIENDO UNA COMPRA , VERIFICAMOS QUE NO ESTE FILLED, CERRRAMOS LA COMPRA  COMO OK */
			if (!_oPosition.getState_in().equals(PositionStates.statusTWSCallBack.Filled.toString()))
			{				
				_oPosition.setState(PositionStates.status.BUY_OK.toString());
				_oPosition.setState_in(PositionStates.statusTWSCallBack.Filled.toString());
				_oPosition.setDate_real_in(_oPosition.getDate_in());
				_oPosition.setPrice_real_in(execution.avgPrice());
			
				
				bChanged = true;
				
			}
		}
		/* si hay cambios, actualizamos */
		if (bChanged)
				PositionLocalServiceUtil.updatePosition(_oPosition);
	}
	catch (Exception e)
	{
		_log.debug(e.getMessage());
	}	
	}
	//! [execdetails]
	
	//! [execdetailsend]
	@Override
	public void execDetailsEnd(int reqId) {
		//_log.debug("ExecDetailsEnd. "+reqId+"\n");
	}
	//! [execdetailsend]
	
	//! [updatemktdepth]
	@Override
	public void updateMktDepth(int tickerId, int position, int operation,
			int side, double price, int size) {
		_log.debug("UpdateMarketDepth. "+tickerId+" - Position: "+position+", Operation: "+operation+", Side: "+side+", Price: "+price+", Size: "+size+"");
	}
	//! [updatemktdepth]
	@Override
	public void updateMktDepthL2(int tickerId, int position,
			String marketMaker, int operation, int side, double price, int size) {
		_log.debug("updateMktDepthL2");
	}
	//! [updatenewsbulletin]
	@Override
	public void updateNewsBulletin(int msgId, int msgType, String message,
			String origExchange) {
		_log.debug("News Bulletins. "+msgId+" - Type: "+msgType+", Message: "+message+", Exchange of Origin: "+origExchange+"\n");
	}
	//! [updatenewsbulletin]
	
	//! [managedaccounts]
	@Override
	public void managedAccounts(String accountsList) {
		_log.debug("Account list: " +accountsList);
	}
	//! [managedaccounts]

	//! [receivefa]
	@Override
	public void receiveFA(int faDataType, String xml) {
		_log.debug("Receing FA: "+faDataType+" - "+xml);
	}
	//! [receivefa]
	
	//! [historicaldata]

	//! [historicaldata]
	
	//! [scannerparameters]
	@Override
	public void scannerParameters(String xml) {
		_log.debug("ScannerParameters. "+xml+"\n");
	}
	//! [scannerparameters]
	
	//! [scannerdata]
	@Override
	public void scannerData(int reqId, int rank,
			ContractDetails contractDetails, String distance, String benchmark,
			String projection, String legsStr) {
		_log.debug("ScannerData. "+reqId+" - Rank: "+rank+", Symbol: "+contractDetails.contract().symbol()+", SecType: "+contractDetails.contract().secType()+", Currency: "+contractDetails.contract().currency()
                +", Distance: "+distance+", Benchmark: "+benchmark+", Projection: "+projection+", Legs String: "+legsStr);
	}
	//! [scannerdata]
	
	//! [scannerdataend]
	@Override
	public void scannerDataEnd(int reqId) {
		_log.debug("ScannerDataEnd. "+reqId);
	}
	//! [scannerdataend]
	
	//! [realtimebar]
	@Override
	public void realtimeBar(int reqId, long time, double open, double high,
			double low, double close, long volume, double wap, int count) {
		_log.debug("RealTimeBars. " + reqId + " - Time: " + time + ", Open: " + open + ", High: " + high + ", Low: " + low + ", Close: " + close + ", Volume: " + volume + ", Count: " + count + ", WAP: " + wap);
	}
	//! [realtimebar]
	@Override
	public void currentTime(long time) {
		new Date(time * 1000L);
	}
	//! [fundamentaldata]
	@Override
	public void fundamentalData(int reqId, String data) {
		_log.debug("FundamentalData. ReqId: ["+reqId+"] - Data: ["+data+"]");
	}
	//! [fundamentaldata]
	@Override
	public void deltaNeutralValidation(int reqId, DeltaNeutralContract underComp) {
		_log.debug("deltaNeutralValidation");
	}
	//! [ticksnapshotend]
	@Override
	public void tickSnapshotEnd(int reqId) {
		_log.debug("TickSnapshotEnd: "+reqId);
	}
	//! [ticksnapshotend]
	
	//! [marketdatatype]
	@Override
	public void marketDataType(int reqId, int marketDataType) {
		_log.debug("MarketDataType. ["+reqId+"], Type: ["+marketDataType+"]\n");
	}
	//! [marketdatatype]
	
	//! [commissionreport]
	@Override
	public void commissionReport(CommissionReport commissionReport) {
		_log.debug("CommissionReport. ["+commissionReport.m_execId+"] - ["+commissionReport.m_commission+"] ["+commissionReport.m_currency+"] RPNL ["+commissionReport.m_realizedPNL+"]");
	}
	//! [commissionreport]
	
	
	
	//! [positionend]
	@Override
	public void positionEnd() {
		_log.debug("PositionEnd \n");
	}
	//! [positionend]
	
	//! [accountsummary]
	@Override
	public void accountSummary(int reqId, String account, String tag,
			String value, String currency) {
		_log.debug("Acct Summary. ReqId: " + reqId + ", Acct: " + account + ", Tag: " + tag + ", Value: " + value + ", Currency: " + currency);
	}
	//! [accountsummary]
	
	//! [accountsummaryend]
	@Override
	public void accountSummaryEnd(int reqId) {
		_log.debug("AccountSummaryEnd. Req Id: "+reqId+"\n");
	}
	//! [accountsummaryend]
	@Override
	public void verifyMessageAPI(String apiData) {
		_log.debug("verifyMessageAPI");
	}

	@Override
	public void verifyCompleted(boolean isSuccessful, String errorText) {
		_log.debug("verifyCompleted");
	}

	@Override
	public void verifyAndAuthMessageAPI(String apiData, String xyzChallange) {
		_log.debug("verifyAndAuthMessageAPI");
	}

	@Override
	public void verifyAndAuthCompleted(boolean isSuccessful, String errorText) {
		_log.debug("verifyAndAuthCompleted");
	}
	//! [displaygrouplist]
	@Override
	public void displayGroupList(int reqId, String groups) {
		_log.debug("Display Group List. ReqId: "+reqId+", Groups: "+groups+"\n");
	}
	//! [displaygrouplist]
	
	//! [displaygroupupdated]
	@Override
	public void displayGroupUpdated(int reqId, String contractInfo) {
		_log.debug("Display Group Updated. ReqId: "+reqId+", Contract info: "+contractInfo+"\n");
	}
	//! [displaygroupupdated]
	@Override
	public void error(Exception e) {
		if (Validator.isNotNull(e) && Validator.isNotNull(e.getMessage()))
			_log.debug("error Exception: "+e.getMessage());
	}

	@Override
	public void error(String str) {
		_log.error("Error STR");
	}
	//! [error]
	@Override
	public void error(int reqId, int errorCode, String str) {
	
		StringBuilder _stbB = new StringBuilder();
		_stbB.append(reqId);
		_stbB.append("|");
		_stbB.append(errorCode);
		_stbB.append("|");
		_stbB.append(str);				
		
		
		try
		
		{
			
		
		Position _ErrorPosition = null;
		Share oErrorShare = null;
		
		switch (errorCode)
		{
		case 587:
			 _log.error("Error :" + errorCode + ",reqId" + reqId + ",txt:" + str + "clientId:" + _clientId) ;
			/* OBTENEMOS EL CRON USADO DE LA TABLA  CON EL CLIENTID USADO, COMO TENEMOS 3 CRON, 
			 * CLIENT_ID SERï¿½ EL CLIENT_ID MAS EL RESTO DEL CONFIGURATIONID DE LA CLAVE PRIMERARIO, AL SER 
			 * SECUENCIALES LOS 3 CRON, NOS ASEGURAMOS DE SER DISTINTOS, HASTA UN MAXIMO DE 1024 INICIALMENTE    */
						
			if (_ibtarget_organization!=null)
			{
			
				Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(this.cronId,_ibtarget_organization.getCompanyId(),_ibtarget_organization.getGroupId());
				if (_conf!=null)
				{
					Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId(_ibtarget_organization.getCompanyId(),_ibtarget_organization.getGroupId());
					_conf.setValue(String.valueOf(NewClientID));
					ConfigLocalServiceUtil.updateConfig(_conf);
				}			
			}
			break;
		case 300: //  // An attempt was made to cancel market data for a ticker ID that was not associated with a current subscription. With the DDE API this occurs by clearing the spreadsheet cell.
			 _log.debug("Error :" + errorCode + ",reqId" + reqId + ",txt:" + str + ",clientid:" + _clientId) ;
			 break;
		case 511: // // 511,Cancel Market Data Sending Error
			 _log.debug("Error :" + errorCode + ",reqId" + reqId + ",txt:" + str + ",clientid:" + _clientId) ;
			 break; 
		case 366:  //366	No historical data query found for ticker id:	Historical market data request with this ticker id has either been cancelled or is not found.
				 // historical data, ponemos la variable a true para que pase al siguiente dia
			historialDataEnd = Boolean.TRUE;
			_log.debug("Finalizado el historical data  a TRUE de " + _ibtarget_share.getSymbol() + " : [reqId:" + reqId + "," + errorCode+ "," + str + "]");
			 break; 
		case 162:
			historialDataEnd = Boolean.TRUE;
			_log.debug("Finalizado el historical data  a TRUE de " + _ibtarget_share.getSymbol()  + " : [reqId:" + reqId + "," + errorCode+ "," + str + "]");
			 break;
		case 200:
			historialDataEnd = Boolean.TRUE;
			_log.debug("Finalizado el historical data  a TRUE de " + _ibtarget_share.getSymbol()  + " : [reqId:" + reqId + "," + errorCode+ "," + str + "]");
			 break;
		default:		
			if (errorCode>=0 && reqId>=0)  // errores operativa - lectura
			{
				String position_mode = Utilities.getPositionModeType(null, _ibtarget_organization.getCompanyId(),_ibtarget_share.getGroupId()); 			
				_ErrorPosition = PositionLocalServiceUtil.findByPositionID_In_TWS(_ibtarget_share.getGroupId(), _ibtarget_organization.getCompanyId(),reqId,this._clientId ,position_mode);				
				if (_ErrorPosition!=null)  // las ponemos inactivas en el cambio de status 
				{ 
					
					oErrorShare = ShareLocalServiceUtil.fetchShare(_ErrorPosition.getShareId());  
					_log.debug("error operativa : [" + reqId + "," + errorCode + "," + str + "]");
					_log.debug("error order  : " + oErrorShare.getSymbol());
					oErrorShare.setActive(Boolean.FALSE);// desactivamos lectura.
					oErrorShare.setValidated_trader_provider(Boolean.FALSE);
					oErrorShare.setDate_validated_trader_provider(new Date());
					oErrorShare.setLast_error_trader_provider(_stbB.toString());							
					/* actualizamos datos error de operativa */
					ShareLocalServiceUtil.updateShare(oErrorShare);					
					try {
						_ErrorPosition.setDescription(reqId + "," + errorCode + "," + str);
						PositionLocalServiceUtil.updatePosition(_ErrorPosition);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						_log.debug("error operativa PositionLocalServiceUtil.updatePosition : [" + e.getMessage() + "]") ;
					}							
						
				}
				else
				{
					IBOrder _ErrorOrder = null;
					if (Validator.isNull(_ibtarget_share))
					{
						_ErrorOrder  = IBOrderLocalServiceUtil.findByOrderClientGroupCompany(reqId, _clientId, _ibtarget_organization.getCompanyId(),_ibtarget_organization.getGroupId());
						 oErrorShare = Validator.isNotNull(_ErrorOrder)  ?   ShareLocalServiceUtil.fetchShare(_ErrorOrder.getShareID())  : null;
					}
					else						
						oErrorShare = (Share) _ibtarget_share.clone();
					if (oErrorShare!=null)
					{																	
							_log.trace("Error lectura de " + oErrorShare.getSymbol() + " : [reqId:" + reqId + "," + errorCode+ "," + str + "]");
							_log.trace("Cancelamos el marketData");
							this.cancelMarketData(reqId);
							oErrorShare.setActive(Boolean.FALSE);// desactivamos lectura.
							oErrorShare.setValidated_trader_provider(Boolean.FALSE);
							oErrorShare.setDate_validated_trader_provider(new Date());
							oErrorShare.setLast_error_trader_provider(_stbB.toString());							
							/* actualizamos datos error de operativa */
							ShareLocalServiceUtil.updateShare(oErrorShare);						
					} // fin one <> -1		
				}
			
			}  // end default
			
			} // end switch 	
		} // end try 
		catch (Exception e)
		{
			_log.error("error:" + e.getMessage());
		}
	}			 
	//! [error]
	@Override
	public void connectionClosed() {
		_log.debug("Connection closed");
	}

	//! [connectack]
	@Override
	public void connectAck() {
		if (clientSocket.isAsyncEConnect()) {
			_log.debug("Acknowledging connection");
			clientSocket.startAPI();
		}
	}
	/* introducimos el modo_fake para usar una misma TWS */
	
	/* 
	 * (non-Javadoc)
	 * @see com.ib.client.EWrapper#tickPrice(int, int, double, com.ib.client.TickAttr)
	 * 
	 * Close Price	9	The last available closing price for the previous day. For US Equities, 
	 * we use corporate action processing to get the closing price, 
	 * so the close price is adjusted to reflect forward and reverse splits and cash and stock dividends.
	 * 
	 */
	
	@Override
	public void tickPrice(int tickerId, int field, double price, TickAttr attrib) {
	try
	{
		
		_log.trace("TickPrice called");
		
    	 //  TODO Auto-generated method stub
	    //_log.debug("Impl tickPrice : + " + tickerId + ",prices:" + price + ",field" + field);		
		// TODO Auto-generated method stub
		IBOrder MyOrder = null;

		boolean closePrice = Boolean.FALSE;
		long shareId = 0;
		long volumen = 0;
		
		
		if (price>0 && (field==ConfigKeys._TICKTYPE_LAST || field==ConfigKeys. _TICKTYPE_DELAYED_LAST)) { //|| field==ConfigKeys. _TICKTYPE_CLOSE)
			
			/* si no lo contiene  */
			if (!stockRealTimePricesCacheLRUMap.containsKey(getUniqueWrapperConnectionId(tickerId)))
			{				
				MyOrder =  IBOrderLocalServiceUtil.findByOrderClientGroupCompany(tickerId, _clientId, _ibtarget_organization.getCompanyId(),_ibtarget_organization.getGroupId());
				if (MyOrder == null) {
					
					_log.debug("No se encuentra el ID " + tickerId);
					return;
				}
				// verificamos si esta en modo simulation con precios introducidos a mano 
				Share share = ShareLocalServiceUtil.fetchShare(MyOrder.getShareID());
				if (share==null) 
				{
					_log.debug("No se encuentra share for OrderId + " + MyOrder.getShareID());
					return;
				}
				
				closePrice = (field == ConfigKeys. _TICKTYPE_CLOSE ? Boolean.TRUE : Boolean.FALSE);
				shareId = share.getShareId();
			}
			// encuentra la clave, actualizamos el realtime  
			else
			{
				/* PRICE|(CIERRE(S/N)|SHAREID|VOLUMENPREVIO*/
				String priceBuilder = stockRealTimePricesCacheLRUMap.get(getUniqueWrapperConnectionId(tickerId));		
				String[] priceArray =  priceBuilder.split(Pattern.quote(StringPool.PIPE));   		
				
				closePrice =  Boolean.valueOf(priceArray[1]);  
				shareId = Long.valueOf(priceArray[2]);
				volumen =  Long.valueOf(priceArray[3]);
			}
			
			/* PRICE|(CIERRE(S/N)|SHAREID|PERSISTED(S/N)
			 * 
			 * Persisted: Lo ponemos a 0 la primera vez ya que se llama varias veces a TickSize antes de tener el cambio de precio */
			
			_log.trace("TickPrice called and filled");

			
			StringBuilder priceBuilder = new StringBuilder();
			priceBuilder.append(price);
			priceBuilder.append(StringPool.PIPE);
			priceBuilder.append(closePrice);
			priceBuilder.append(StringPool.PIPE);
			priceBuilder.append(String.valueOf(shareId));
			priceBuilder.append(StringPool.PIPE);
			priceBuilder.append(String.valueOf(volumen));
			priceBuilder.append(StringPool.PIPE);
			priceBuilder.append(String.valueOf(Boolean.FALSE));
			
			
			stockRealTimePricesCacheLRUMap.put(getUniqueWrapperConnectionId(tickerId), priceBuilder.toString());	

		
		}
	
	}
	catch (Exception e)
	{
		_log.debug(e.getMessage());
	}	
		
	}
	
	/* Inactive - indicates an order is not working, possible reasons include:
		it is invalid or triggered an error. A corresponding error code is expected to the error() function.
		the order is to short shares but the order is being held while shares are being located.
		an order is placed manually in TWS while the exchange is closed.
		an order is blocked by TWS due to a precautionary setting and appears there in an untransmitted state
	*/
	@Override
	public void orderStatus(int orderId, String status, double filled, double remaining, double avgFillPrice,
			int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {

	try
	{
			
		double priceStopLost = 0;
		double priceStopProfit = 0;
		double priceTraillingStopLost = 0;
		boolean bIsSellOperation = false;  //ENTRADA O SALIDA, TERMINO ERRONEO
		boolean isDelete = false;   // cuando sea compra cancelada, nos la cargamos.
		
		if (parentId>0)  // no hacemos nada con las hijas 
			return;
		
		
		String position_mode = Utilities.getPositionModeType(null, _ibtarget_organization.getCompanyId(),_ibtarget_share.getGroupId()); 

		
		Position _oPosition = PositionLocalServiceUtil.findByPositionID_In_TWS(_ibtarget_share.getGroupId(), _ibtarget_organization.getCompanyId(),orderId,clientId,position_mode);
		// SI ES NULL, QUIERE DECIR QUE PUEDE VENIR UNA OPERACION DE VENTA...LA BUSCAMOS.		
		if (_oPosition==null)
		{
			_oPosition = PositionLocalServiceUtil.findByPositionID_Out_TWS(_ibtarget_share.getGroupId(), _ibtarget_organization.getCompanyId(),orderId,clientId,position_mode);
			if (_oPosition==null) 
			{
				_log.debug("Error Execution Details order not found for Order Key:" + orderId);
				return;
			}
			else
				bIsSellOperation = true;
			
		}
		
		
		/* SUPOEMOS UNA POSICION */
		Share sharePosition = ShareLocalServiceUtil.fetchShare(_oPosition.getShareId());
		/* DETECTAMOS SI ES UNA ORDER DE COMPRA O DE VENTA. VERIFICAMOS SI HA CAMBIADO . VERIFICAR SI HAY UNA ORDEN DE COMPRA PREVIA. */
		boolean changed = false;
		if (!bIsSellOperation)   //ENTRADA OPERATION
		{	
			if (_oPosition.getState_in() ==null || (_oPosition.getState_in()!=null && !_oPosition.getState_in().toLowerCase().equals(status.toLowerCase())))			
					changed = true;
		}
		else  // SALIDA OPERATION
		{
			if (_oPosition.getState_out()==null || (_oPosition.getState_out()!=null && !_oPosition.getState_out().toLowerCase().equals(status.toLowerCase())))
    			changed = true;
		}
		_log.debug("orderStatus for " + orderId + ",status:" +  status +   ",remaining:" + remaining + " share:" + sharePosition.getSymbol());

		if (changed )
		{	    			
			// controlamos todas las canceladas			
			if (!bIsSellOperation)   // ENTRADA OPERATION ... CANCELLED? --> LA BORRAMOS PARA QUE NO CONSTE
			{
				/* cancelada compra  */
				/* 21.09.2013 QUITO EL OR DE INACTIVE PARA CONTROLARLO EN LA RUTINA DE ERRORES*/
				if (PositionStates.statusTWSCallBack.Cancelled.toString().equals(status))
	    		{			    						    		
					// procedemos a borrarla y desactivar
					/* FUTURO VENCIDO D-1 DEL VENCIMIENTO */
					//Actualizamos campos de errores.
					sharePosition.setActive(Boolean.FALSE);					
				//	sharePosition.setLast_error_trader_provider(sdf2.format(FechaError));  // desactivamos trading.
					ShareLocalServiceUtil.updateShare(sharePosition);
					PositionLocalServiceUtil.deletePosition(_oPosition);
					isDelete = true;
	    			
	    		}
				/* la pone desactivada la share en los errores. Aqui la damos como SELL_OK pero status Inactive para que no conste y  pueda operarse  */
				if (PositionStates.statusTWSCallBack.Inactive.toString().equals(status))
	    		{			    						    		
					// procedemos a borrarla y desactivar
					/* FUTURO VENCIDO D-1 DEL VENCIMIENTO */
					//Actualizamos campos de errores.
					_oPosition.setState_in(status);
				    _oPosition.setState(PositionStates.status.SELL_OK.toString());
				    _oPosition.setState_out(PositionStates.statusTWSCallBack.Inactive.toString());
				    _oPosition.setPrice_real_in(_oPosition.getPrice_in());
				    _oPosition.setPrice_out(_oPosition.getPrice_in());
				    _oPosition.setPrice_real_out(_oPosition.getPrice_in());
				    _oPosition.setDate_real_in(_oPosition.getDate_in());
				    _oPosition.setDate_real_out(_oPosition.getDate_in());
				    _oPosition.setDate_out(_oPosition.getDate_in());
				//	sharePosition.setLast_error_trader_provider(sdf2.format(FechaError));  // desactivamos trading.
					PositionLocalServiceUtil.updatePosition(_oPosition);
					isDelete = false;
	    			
	    		}
				
				/* OJO, PUEDEN SER VENTAS/COMPRAS  PARCIALES..ENTRADA...SOLO OPERACIONES TOTALES */
				if (PositionStates.statusTWSCallBack.Filled.toString().equals(status) )
	    		{
					_oPosition.setState_in(status);
	    			_oPosition.setDate_real_in(new Date());			    			
	    			_oPosition.setPrice_real_in(avgFillPrice);  // cogemos el avg que nos manda el TWS	    			
	    			// ACTUALIZAMOS EL PRECIO DE SALIDA CON EL PORCENTAJE PORQUE ES EL VALOR QUE TRATAMOS
	    			// vemos el tipo de operacion
	    			
	    			if (_oPosition.getType().equals(PositionStates.statusTWSFire.SELL.toString()))  	{ //short
	    				if (_oPosition.getPercentualstoplost_out()>0)
	    					priceStopLost = avgFillPrice +  (avgFillPrice *  _oPosition.getPercentualstoplost_out() / 100);
	    				if (_oPosition.getPercentualstopprofit_out()>0)
		    			priceStopProfit = avgFillPrice  - (avgFillPrice *  _oPosition.getPercentualstopprofit_out() / 100); }
	    			else {  //long
	    				if (_oPosition.getPercentualstoplost_out()>0)
	    					priceStopLost    = avgFillPrice  - (avgFillPrice *  _oPosition.getPercentualstoplost_out() / 100);
	    				if (_oPosition.getPercentualstopprofit_out()>0)
	    					priceStopProfit = avgFillPrice  + (avgFillPrice *  _oPosition.getPercentualstopprofit_out() / 100); }	    				 	    		
	    			_oPosition.setPricestoplost_out(Utilities.RoundPrice(priceStopLost));
	    			_oPosition.setPricestopprofit_out(Utilities.RoundPrice(priceStopProfit));
	    			_oPosition.setState(PositionStates.status.BUY_OK.toString());
	    		}	    				
			}
			else  // SALIDA OPERATION..  OJO, PUEDEN SER VENTAS/COMPRAS  PARCIALES.. */
			{
				/* PUEDE RETORNAR FILLED MAS DE UNA VEZ*/
				/* EN LAS OPERACIONES PARCIALES, DESPUES DE FILLED, PONGO EL STATESELL A NULL
				 * PARA PODER SEGUIR VENDIENDO. 
				 * PASARIA POR AQUI Y ACTUALIZARIA DOS VECES ERROREO EL CAMPO OPERATIONS.
				 */
				if (PositionStates.statusTWSCallBack.Filled.toString().equals(status) && _oPosition.getState_out()!=null && remaining==0)
	    		{
				_oPosition.setState_out(status);	 
				_oPosition.setDate_out(new Date()) ; // OJO TIMESTAMP.
				_oPosition.setDate_real_out(new Date());			    			
    			_oPosition.setPrice_real_out(avgFillPrice);  // cogemos el avg que nos manda el TWS
				_oPosition.setState(PositionStates.status.SELL_OK.toString());
	    		}	    
				
			}
			if (!isDelete)  // compras canceladas se borran.
			{	
				PositionLocalServiceUtil.updatePosition(_oPosition);
				/* ENVIAMOS NOTIFICACION, SI TIENE LA PREFERENCIA ACTIVADA */
				Message message = new Message();
				message.put("position", _oPosition);
				MessageBusUtil.sendMessage("position/update", message);
				/* ENVIAMOS NOTIFICACION, SI TIENE LA PREFERENCIA ACTIVADA */
				
			}
		}	
	
	}
	catch (Exception e)
	{
		_log.debug(e.getMessage());
	}	
		
	}
	@Override
	public void updatePortfolio(Contract contract, double position, double marketPrice, double marketValue,
			double averageCost, double unrealizedPNL, double realizedPNL, String accountName) {
		// TODO Auto-generated method stub
		
	}
	
	/* id=1 date = 20131023  19:15:00 open=5.04 high=5.05 low=4.88 close=4.94 volume=33 count=31 WAP=4.973 hasGaps=false
	id=1 date = 20131023  19:20:00 open=4.99 high=5.26 low=4.99 close=5.19 volume=44 count=40 WAP=5.163 hasGaps=false
	id=1 date = 20131023  19:25:00 open=5.2 high=5.21 low=5.07 close=5.14 volume=27 count=24 WAP=5.148 hasGaps=false
	id=1 date = 20131023  19:30:00 open=5.14 high=5.14 low=5.14 close=5.14 volume=0 count=0 WAP=5.14 hasGaps=false
	id=1 date = finished-20131023  19:15:01-20131023  19:30:01 o
	 */
	
	/* ************** CLOSING PRICES****************************************************************************** 
	/* 	id=1 date = 20190306 open=171.66 high=173.57 low=171.27 close=171.99 volume=182468 count=90072 WAP=172.358
		id=1 date = 20190307 open=172.01 high=172.64 low=167.61 close=168.3 volume=156127 count=73540 WAP=169.478
		id=1 date = 20190308 open=167.61 high=169.92 low=165.79 close=169.76 volume=109017 count=54566 WAP=168.268
		id=1 date = 20190311 open=171.0 high=174.3 low=171.0 close=172.48 volume=153642 count=70039 WAP=172.866
		id=1 date = 20190312 open=172.95 high=173.8 low=171.21 close=172.0 volume=100118 count=47496 WAP=172.153
	*/
	/* ************** REALTIME ****************************************************************************** 
	/* 	id=1 date = 20131023  19:15:00 open=5.04 high=5.05 low=4.88 close=4.94 volume=33 count=31 WAP=4.973 hasGaps=false
	id=1 date = 20131023  19:20:00 open=4.99 high=5.26 low=4.99 close=5.19 volume=44 count=40 WAP=5.163 hasGaps=false
	id=1 date = 20131023  19:25:00 open=5.2 high=5.21 low=5.07 close=5.14 volume=27 count=24 WAP=5.148 hasGaps=false
	id=1 date = 20131023  19:30:00 open=5.14 high=5.14 low=5.14 close=5.14 volume=0 count=0 WAP=5.14 hasGaps=false
	id=1 date = finished-20131023  19:15:01-20131023  19:30:01 o


	/* PARA DATOS HISTORICOS DE VARIOS DIAS, PUEDE SER QUE ME DE DATOS DEL DIA ANTERIOR EN CASO DE QUE SEA LA PRIMERA HORA O UN SABADO
* 	PROVOCANDO DUPLICIDADES, CONTROLO QUE LO SOLICITADO, ES DE LA MISMA FECHA PEDIDA */
	
	@Override
	public void historicalData(int reqId, Bar bar) {
	
	_log.debug("historicalData , volumen:" + bar.volume() + ", reqId: + " + reqId + " for " + this.get_ibtarget_share().getSymbol() + ",close:" + bar.close() + ",time:" + bar.time());	
	
	SimpleDateFormat fDateHistorical = new SimpleDateFormat(Utilities.__IBTRADER_HISTORICAL_DATE_FORMAT);
	SimpleDateFormat fDateClosePrice = new SimpleDateFormat(Utilities.__IBTRADER_LONG_DATE_FORMAT);

	Calendar _barDate = Calendar.getInstance();
	/* CIERRE */
	/* MAXIMO  */
	/* MINIMO  */
	Date parsedDate = null;
	boolean IsFinishedBar = Boolean.FALSE;
	try
	{
		
		User _user = UserLocalServiceUtil.fetchUser(this.get_ibtarget_share().getUserCreatedId());
		fDateHistorical.setTimeZone(TimeZone.getTimeZone(_user.getTimeZoneId()));
		
		parsedDate = fDateHistorical.parse(bar.time());	
		
		if (Validator.isNotNull(_lastHistoricalBarDate)) // ultima barra, miramos si el dia cambia para meter el cierre  		
			IsFinishedBar =  !fDateClosePrice.format(parsedDate).equals(fDateClosePrice.format(_lastHistoricalBarDate));			
	}
	catch (Exception e){}	
	try {
		
		Calendar _cSim = Calendar.getInstance();
		Calendar _cParsed = Calendar.getInstance();
		_cParsed.setTimeInMillis(parsedDate.getTime());
		// CONTROL de usuarios edemo, simulo fecha actual de historical data, ya que me da solo la actual 
		if (this.getUserTWS().equalsIgnoreCase(Utilities._DEFAULT_USER_DEMO_))
		{
			Date simulatedDate = fDateHistorical.parse(historicalDataRequest);		
			_cSim.setTimeInMillis(simulatedDate.getTime());
			_cParsed.set(Calendar.DAY_OF_MONTH,_cSim.get(Calendar.DAY_OF_MONTH));
			_cParsed.set(Calendar.MONTH,_cSim.get(Calendar.MONTH));
			_cParsed.set(Calendar.YEAR,_cSim.get(Calendar.YEAR));
			
		}
		/* LA barra 00:05:00 se corresponde a todo el rango 00:04:59 */
		_barDate.setTimeInMillis(_cParsed.getTimeInMillis());
		_barDate.set(Calendar.MILLISECOND, 0);			
		_barDate.add(Calendar.SECOND, 59);
		_barDate.add(Calendar.MINUTE, ConfigKeys.DEFAULT_TIMEBAR_MINUTES-1); 
		
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		_log.debug(e.getMessage());
		//e.printStackTrace();
	}
	if (Validator.isNull(_barDate))
		return;
	
	/* CONTROLAMOS LOS CIERRE QUE VAN AL REALTIME O LOS REALTIME PARA RELLLENAR HUECOS ANTERIORES */
	if (!this.isFilledData())
	{
		Calendar barTime = Calendar.getInstance();
		barTime.setTime(_barDate.getTime());
		HistoricalRealtime existsHistoricalRealtime  = null;
		if (IsFinishedBar) // hay cambio de dia?
		{
			 existsHistoricalRealtime = HistoricalRealtimeLocalServiceUtil.findRealTime(this.get_ibtarget_share().getShareId(), this.get_ibtarget_share().getCompanyId(), this.get_ibtarget_share().getGroupId(), _lastHistoricalBarDate);
			if (Validator.isNotNull(existsHistoricalRealtime) && !existsHistoricalRealtime.getCloseprice()) // no duplicamos 
			{
					existsHistoricalRealtime.setCloseprice(Boolean.TRUE);
				    HistoricalRealtimeLocalServiceUtil.updateHistoricalRealtime(existsHistoricalRealtime);

			}
		}
		existsHistoricalRealtime = HistoricalRealtimeLocalServiceUtil.findRealTime(this.get_ibtarget_share().getShareId(), this.get_ibtarget_share().getCompanyId(), this.get_ibtarget_share().getGroupId(), barTime.getTime());
		
		_log.trace("Exists HistoricalRealtime for  " +  this.get_ibtarget_share().getSymbol() + " " + barTime.getTime() + "?:" + Validator.isNotNull(existsHistoricalRealtime));

		
		if (Validator.isNull(existsHistoricalRealtime)) // no duplicamos 
		{
			
			HistoricalRealtime historicalrealtime = HistoricalRealtimeLocalServiceUtil.createHistoricalRealtime(CounterLocalServiceUtil.increment(HistoricalRealtime.class.getName()));
			_lastHistoricalBarDate = barTime.getTime(); 
			_log.trace("Adding historicalData for " +  this.get_ibtarget_share().getSymbol() + " " + barTime.getTime());
	
			try
			{
				historicalrealtime.setCreateDate(barTime.getTime());
				historicalrealtime.setGroupId(this.get_ibtarget_share().getGroupId());
				historicalrealtime.setCompanyId(this.get_ibtarget_share().getCompanyId());
				historicalrealtime.setShareId(this.get_ibtarget_share().getShareId());
				historicalrealtime.setValue(bar.close());
				historicalrealtime.setVolume(new Long(bar.volume()).intValue());
				HistoricalRealtimeLocalServiceUtil.updateHistoricalRealtime(historicalrealtime);
			}
			catch (Exception e) {}
			
			try
			{
				/* MINIMO  */
				barTime.add(Calendar.SECOND, -1);
				historicalrealtime = HistoricalRealtimeLocalServiceUtil.createHistoricalRealtime(CounterLocalServiceUtil.increment(HistoricalRealtime.class.getName()));
				historicalrealtime.setCreateDate(barTime.getTime());
				historicalrealtime.setGroupId(this.get_ibtarget_share().getGroupId());
				historicalrealtime.setCompanyId(this.get_ibtarget_share().getCompanyId());
				historicalrealtime.setShareId(this.get_ibtarget_share().getShareId());
				historicalrealtime.setValue(bar.low());
				historicalrealtime.setVolume(new Long(bar.volume()).intValue());
				
				HistoricalRealtimeLocalServiceUtil.updateHistoricalRealtime(historicalrealtime);
			}
			catch (Exception e) {}
			/* MAXIMO  */
			try
				{
				historicalrealtime = HistoricalRealtimeLocalServiceUtil.createHistoricalRealtime(CounterLocalServiceUtil.increment(HistoricalRealtime.class.getName()));	
				historicalrealtime.setCreateDate(barTime.getTime());
				historicalrealtime.setGroupId(this.get_ibtarget_share().getGroupId());
				historicalrealtime.setCompanyId(this.get_ibtarget_share().getCompanyId());
				historicalrealtime.setShareId(this.get_ibtarget_share().getShareId());
				historicalrealtime.setValue(bar.high());
				historicalrealtime.setVolume(new Long(bar.volume()).intValue());
	
				HistoricalRealtimeLocalServiceUtil.updateHistoricalRealtime(historicalrealtime);
				/* guardamos la ultinma barra para saber la cultima cargada cuando venga el finished */
				}
			catch (Exception e) {}
			
		}
	}
	else // los cierres van en  historicalrealtime
	{
				
		Calendar _calNow = Calendar.getInstance();
		/* A VECES LA ULTIMA BARRA ESTA POR ENCIMA DEL MOMENTO ACTUAL, ESTA LA IGNORAMOS */
		Calendar now = Calendar.getInstance();
		/* PRECIO CIERRE ES EL DIA ANTERIOR SEGUN DOCUM DE IB */
		
		User _user = UserLocalServiceUtil.fetchUser(this.get_ibtarget_share().getUserCreatedId());
		fDateHistorical.setTimeZone(TimeZone.getTimeZone(_user.getTimeZoneId()));
		
		Date utcDate = null;
		try {
			utcDate = fDateHistorical.parse(bar.time());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
		_calNow.setTimeInMillis(utcDate.getTime());
		_calNow.set(Calendar.MILLISECOND, 0);			
		_calNow.add(Calendar.SECOND, 59);
		_calNow.add(Calendar.MINUTE, ConfigKeys.DEFAULT_TIMEBAR_MINUTES-1); 
			
		/* BUSCANDO EL REALTIME CON HISTORICAL DATA EN UTC ME LO DEVUELVE EN EL HUSO DEL USUARIO */
		
		/* IGNORAMOS EL DIA DE HOY , PUEDE SER QUE ESTEN ABIERTOS LOS MERCADOS
		 * AUN ASI, TE ENVIA COMO CIERRE , ¿BUG? 
		 */
		// }
		if (_calNow.after(now))
			return;
		
		//Realtime existsClosePrice = RealtimeLocalServiceUtil.findCloseRealTime(this.get_ibtarget_share().getShareId(), this.get_ibtarget_share().getCompanyId(), this.get_ibtarget_share().getGroupId(), _calNow.getTime(), IsClosePrice);
		Realtime existsClosePrice = RealtimeLocalServiceUtil.findRealTime(this.get_ibtarget_share().getShareId(), this.get_ibtarget_share().getCompanyId(), this.get_ibtarget_share().getGroupId(), _calNow.getTime());

		_log.trace("Exists Realtime for  " +  this.get_ibtarget_share().getSymbol() + " " + _calNow.getTime() + "?:" + Validator.isNotNull(existsClosePrice));

		
		if (Validator.isNull(existsClosePrice)) // no duplicamos 
		{
			
			_log.trace("Adding Required Realtime for  " +  this.get_ibtarget_share().getSymbol() + " " + parsedDate + ",value:" + bar.close());
			

			try // avoid duplicate entry due to milliseconds 
			{
				Realtime  oReal = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				oReal.setGroupId(this.get_ibtarget_share().getGroupId());
				oReal.setCompanyId(this.get_ibtarget_share().getCompanyId());
				oReal.setShareId(this.get_ibtarget_share().getShareId());
				oReal.setValue(bar.close());					
				oReal.setCloseprice(Boolean.FALSE);
				oReal.setCreateDate(_calNow.getTime());
				oReal.setModifiedDate(new Date());		
				oReal.setVolume(new Long(bar.volume()).intValue());
				RealtimeLocalServiceUtil.updateRealtime(oReal);
				
				/* MINIMO  */

				_calNow.add(Calendar.SECOND, -1);				
				  oReal = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				oReal.setGroupId(this.get_ibtarget_share().getGroupId());
				oReal.setCompanyId(this.get_ibtarget_share().getCompanyId());
				oReal.setShareId(this.get_ibtarget_share().getShareId());
				oReal.setValue(bar.high());					
				oReal.setCloseprice(Boolean.FALSE);
				oReal.setCreateDate(_calNow.getTime());
				oReal.setModifiedDate(new Date());		
				oReal.setVolume(new Long(bar.volume()).intValue());

				RealtimeLocalServiceUtil.updateRealtime(oReal);
				
				/* MAXIMO  */
				_calNow.add(Calendar.SECOND, -1);
				oReal = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				oReal.setGroupId(this.get_ibtarget_share().getGroupId());
				oReal.setCompanyId(this.get_ibtarget_share().getCompanyId());
				oReal.setShareId(this.get_ibtarget_share().getShareId());
				oReal.setValue(bar.low());					
				oReal.setCloseprice(Boolean.FALSE);
				oReal.setCreateDate(_calNow.getTime());
				oReal.setModifiedDate(new Date());		
				oReal.setVolume(new Long(bar.volume()).intValue());

				RealtimeLocalServiceUtil.updateRealtime(oReal);
				
				
				
				
				/* si es relleno de huecos, actualizo el campo para saber que cual es el ultimo share actualizado y no buscarle en la siguiente iteracion 
				if (this.isFilledData())
				{*/
				Share updatedShare =ShareLocalServiceUtil.fetchShare(this.get_ibtarget_share().getShareId()); 
				updatedShare.setDate_filled_realtime_gaps(now.getTime());
				ShareLocalServiceUtil.updateShare(updatedShare);
				//}
			}
			catch  (Exception e) {}
		}
	}

		
	}
	@Override
	public void position(String account, Contract contract, double pos, double avgCost) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void positionMulti(int reqId, String account, String modelCode, Contract contract, double pos,
			double avgCost) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void positionMultiEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void accountUpdateMulti(int reqId, String account, String modelCode, String key, String value,
			String currency) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void accountUpdateMultiEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void securityDefinitionOptionalParameter(int reqId, String exchange, int underlyingConId,
			String tradingClass, String multiplier, Set<String> expirations, Set<Double> strikes) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void securityDefinitionOptionalParameterEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void softDollarTiers(int reqId, SoftDollarTier[] tiers) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void familyCodes(FamilyCode[] familyCodes) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void symbolSamples(int reqId, ContractDescription[] contractDescriptions) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalDataEnd(int reqId, String startDateStr, String endDateStr) {
		// TODO Auto-generated method stub
		_log.debug("historicalDataEnd for " + reqId + ",startDateStr:" +  startDateStr);
		
		/* METEMOS EL CIERRE DE LA ULTIMA BARRA METIDA */
		HistoricalRealtime existsHistoricalRealtime  = HistoricalRealtimeLocalServiceUtil.findLastRealTime(this.get_ibtarget_share().getShareId(), this.get_ibtarget_share().getCompanyId(), this.get_ibtarget_share().getGroupId());
		_log.trace("Filling closePrice for final bar for  " +  this.get_ibtarget_share().getSymbol() + " " + Validator.isNotNull(existsHistoricalRealtime));
		historialDataEnd = Boolean.TRUE;

		if (Validator.isNotNull(existsHistoricalRealtime)) // no duplicamos 
		{
			existsHistoricalRealtime.setCloseprice(Boolean.TRUE);
			HistoricalRealtimeLocalServiceUtil.updateHistoricalRealtime(existsHistoricalRealtime);
		}
		
		

	}
	@Override
	public void mktDepthExchanges(DepthMktDataDescription[] depthMktDataDescriptions) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tickNews(int tickerId, long timeStamp, String providerCode, String articleId, String headline,
			String extraData) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void smartComponents(int reqId, Map<Integer, Entry<String, Character>> theMap) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void tickReqParams(int tickerId, double minTick, String bboExchange, int snapshotPermissions) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void newsProviders(NewsProvider[] newsProviders) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void newsArticle(int requestId, int articleType, String articleText) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalNews(int requestId, String time, String providerCode, String articleId, String headline) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalNewsEnd(int requestId, boolean hasMore) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void headTimestamp(int reqId, String headTimestamp) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void histogramData(int reqId, List<HistogramEntry> items) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalDataUpdate(int reqId, Bar bar) {
		_log.debug("historicalDataUpdate for " + reqId + ",bar:" +  bar);

		
	}
	@Override
	public void rerouteMktDataReq(int reqId, int conId, String exchange) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rerouteMktDepthReq(int reqId, int conId, String exchange) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void marketRule(int marketRuleId, PriceIncrement[] priceIncrements) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pnl(int reqId, double dailyPnL, double unrealizedPnL) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pnlSingle(int reqId, int pos, double dailyPnL, double unrealizedPnL, double value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalTicks(int reqId, List<HistoricalTick> ticks, boolean done) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalTicksBidAsk(int reqId, List<HistoricalTickBidAsk> ticks, boolean done) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void historicalTicksLast(int reqId, List<HistoricalTickLast> ticks, boolean done) {
		// TODO Auto-generated method stub
		
	}



	public Share get_ibtarget_share() {
		return _ibtarget_share;
	}

	public void set_ibtarget_share(Share _ibtarget_share) {
		this._ibtarget_share = _ibtarget_share;
	}

	public Organization get_ibtarget_organization() {
		return _ibtarget_organization;
	}

	public void set_ibtarget_organization(Organization _ibtarget_organization) {
		this._ibtarget_organization = _ibtarget_organization;
	}

	

	public String getCronId() {
		return cronId;
	}

	public void setCronId(String cronId) {
		this.cronId = cronId;
	}
	
	public static void main(String[] args) throws InterruptedException {

		TIMApiWrapper wrapper = new TIMApiWrapper(7, true);		
		final EClientSocket m_client = wrapper.getClient();
		final EReaderSignal m_signal = wrapper.getSignal();
		//! [connect]
		m_client.eConnect("127.0.0.1", 7497, 1023);
		//! [connect]
		//! [ereader]
		final EReader reader = new EReader(m_client, m_signal);   
		
		reader.start();
		//An additional thread is created in this program design to empty the messaging queue
		new Thread(() -> {
		    while (m_client.isConnected()) {
		        m_signal.waitForSignal();
		        try {
		            reader.processMsgs();
		        } catch (Exception e) {
		            System.out.println("Exception: "+e.getMessage());
		        }
		    }
		}).start();
		//! [ereader]
		// A pause to give the application time to establish the connection
		// In a production application, it would be best to wait for callbacks to confirm the connection is complete
		Thread.sleep(1000);

	//	tickDataOperations(wrapper.getClient());
		//orderOperations(wrapper.getClient(), wrapper.getCurrentOrderId());
		//contractOperations(wrapper.getClient());
		//hedgeSample(wrapper.getClient(), wrapper.getCurrentOrderId());
		//testAlgoSamples(wrapper.getClient(), wrapper.getCurrentOrderId());
		//bracketSample(wrapper.getClient(), wrapper.getCurrentOrderId());
		//bulletins(wrapper.getClient());
		//reutersFundamentals(wrapper.getClient());
		//marketDataType(wrapper.getClient());
		Contract  _contractAPI3 =  new StkContract("AAPL");
		_contractAPI3.symbol("AAPL");
		_contractAPI3.secType("STK");
		_contractAPI3.exchange("ISLAND");
		_contractAPI3.currency("USD");
				
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat form = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String formatted = form.format(cal.getTime()).concat(" GMT");
		
		formatted  = "20190509 23:59:59";
		m_client.reqHistoricalData(4003, _contractAPI3, formatted, "2 D", "5 mins", "TRADES", 0, 0, false, null);
		Thread.sleep(2000);
		//accountOperations(wrapper.getClient());
		//newsOperations(wrapper.getClient());
		//marketDepthOperations(wrapper.getClient());
		//rerouteCFDOperations(wrapper.getClient());
		//marketRuleOperations(wrapper.getClient());
		//tickDataOperations(wrapper.getClient());
		//pnlSingle(wrapper.getClient());
		//continuousFuturesOperations(wrapper.getClient());

		Thread.sleep(100000);
		m_client.eDisconnect();
	}	

}
