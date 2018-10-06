/* So the simple fact is that if an order is fully filled while the client application is not connected to TWS, or TWS is not connected to the IB servers, the client will never receive an orderStatus with a status of ‘Filled’.



To deal with this situation, there are three things that need to be done:



1.       The client application must save information about orders it has placed. This must be persisted to a file or database for use when the application is restarted.



2.       When the client application connects to TWS it must call reqExecutions and reqOpenOrders. The combination of the saved order information and the information received via execDetails and openOrder is enough to reconstruct exactly what the current state is. (Note that there is a TWS API configuration option for open orders to be notified automatically when a client connects, but it’s better to call reqOpenOrders as you cannot guarantee that this option will be set.)



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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


import java.util.Set;

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
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
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
	
	
	private Share _ibtarget_share= null;
	private Organization _ibtarget_organization= null;
	
	private boolean standalone_mode = false; // si solo hay una TWS para todos   
	
	private String  cronId = "";  // nos sirve para cambiar el value del clientId en su caso en la BBDD
	
	private String userTWS = "";  
	private String historicalDataRequest = ""; // me vale solo cuando soy un usuario edemo  y la TWS solo me da el historical del dia de hoy, entonces, lo uso para simular

	private boolean historialDataEnd = Boolean.FALSE; 
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
	
	
	public void getHistoricalData(int requestId, Contract contract, String EndTime){
		
		historicalDataRequest = EndTime;
		
		// CONTROL
		if (this.getUserTWS().equalsIgnoreCase(Utilities._DEFAULT_USER_DEMO_))
			clientSocket.reqHistoricalData(requestId, contract, "", "1 D", "5 mins", "TRADES", 1, 1, false, null);
		else
			clientSocket.reqHistoricalData(requestId, contract, EndTime, "1 D", "5 mins", "TRADES", 1, 1, false, null);
		
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
		clientSocket.reqMarketDataType(ConfigKeys.MARKET_DATA_TYPE_DELAYED_LIVE);
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
		    _log.debug("_sendDisconnectEvent: "+_sendDisconnectEvent);
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
		
		String position_mode = Utilities.getPositionModeType(null, _ibtarget_organization.getCompanyId(),_ibtarget_share.getGroupId()); 

		
		long  _currentMaxPositionsId= PositionLocalServiceUtil.findMaxOrderClientCompanyGroup(_ibtarget_organization.getCompanyId(), _ibtarget_organization.getGroupId(), this._clientId, position_mode);
		// ordenes 
		long   _currentMaxOrderId = IBOrderLocalServiceUtil.findMaxOrderClientCompanyGroup(_ibtarget_organization.getCompanyId(), _ibtarget_organization.getGroupId(), this._clientId);
		/* maximo de los tres valores */
		long  _INCREMENT_ORDER_ID   = Math.max(currentOrderId, Math.max(_currentMaxPositionsId, _currentMaxOrderId));
		
		/* Entre lo que nos da la tws (se solapan entre clientIds y lo que nos la ultima posicion (hay que guardarlos) , eleigimos el mayor */
		currentOrderId = (int) _INCREMENT_ORDER_ID;
		return currentOrderId++;
	}
	
	public int getCurrentOrderId() {
		return currentOrderId++;
	}
	
	 //! [tickprice]

	//! [tickprice]
	
	//! [ticksize]
	
	
	
	@Override
	public void tickSize(int tickerId, int field, int size) {
		//_log.debug("Tick Size. Ticker Id:" + tickerId + ", Field: " + field + ", Size: " + size);
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
	 * LISTA DE ORDENES QUE ACOMPAÑAN A LA ORDEN PADRE, SOLO SE APLICA A ORDENES DE TIPO TRAIL PARA AJUSTAR BENEFICIO 
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
		
		_log.info("placeOrder _INCREMENT_ORDER_ID: " + _INCREMENT_ORDER_ID +",symbol"+  contract.symbol()+",groupid::" + _ibtarget_share.getGroupId() + " IP:" + this._host + ",port:" + this._port + ",client:" + this._clientId + ",Isconnected:" + this.isConnected());

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
		_log.info("Next Valid Id: ["+orderId+"]");
		currentOrderId = orderId;
	}
	//! [nextvalidid]
	
	//! [contractdetails]
	@Override
	public void contractDetails(int reqId, ContractDetails contractDetails) {
		_log.debug("TIMApiWrapper ContractDetails. ReqId: ["+reqId+"] - ["+contractDetails.contract().symbol()+"], ["+contractDetails.contract().secType()+"], ConId: ["+contractDetails.contract().conid()+"] @ ["+contractDetails.contract().exchange()+"]");
		
		 IBOrder _ibOrder;		 	
		 //_ibOrder = IBOrderLocalServiceUtil.fetchIBOrder(reqId);
		 _ibOrder = IBOrderLocalServiceUtil.findByOrderClientGroupCompany(reqId, _clientId, _ibtarget_organization.getCompanyId(),_ibtarget_share.getGroupId());
	 	 if (_ibOrder!=null)  // error en una posicion dada abierta		
		 { 
	 		 
			Share share = ShareLocalServiceUtil.fetchShare(_ibOrder.getShareID());  								
			share.setDate_validated_trader_provider(new Date());
			/* actualizamos datos error de operativa */
			_log.debug("Updating contractDetails share:" + share.getSymbol());
			ShareLocalServiceUtil.updateShare(share);	
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
		 _log.debug("contractDetailsEnd:" + reqId);
		 IBOrder _ibOrder;		 	
		 //_ibOrder = IBOrderLocalServiceUtil.fetchIBOrder(reqId);
		 _ibOrder = IBOrderLocalServiceUtil.findByOrderClientGroupCompany(reqId, _clientId, _ibtarget_organization.getCompanyId(),_ibtarget_share.getGroupId());
	 	 if (_ibOrder!=null)  // error en una posicion dada abierta		
		 { 
	 		 
			Share share = ShareLocalServiceUtil.fetchShare(_ibOrder.getShareID());  					
			share.setValidated_trader_provider(Boolean.TRUE);
			share.setDate_validated_trader_provider(new Date());
			share.setLast_error_trader_provider(null);							
			/* actualizamos datos error de operativa */
			_log.debug("Updating contract share:" + share.getSymbol());
			ShareLocalServiceUtil.updateShare(share);		
			
			
		}
		 
    }
	//! [contractdetailsend]
	
	//! [execdetails]
	
	/* VERIFICAMOS ORDENES COMPLETEADAS Y NO VERIFICADAS DEBIDO A DESCONEXIONES, 
	 * 
	 *  reqId orderId, aqui no aplica 
	 *  execution.orderId() es el order de position de la tabla 
	 *  */
	@Override
	public void execDetails(int reqId, Contract contract, Execution execution) {
		
		//_log.info("ExecDetails. "+reqId+" - ["+contract.symbol()+"], ["+contract.secType()+"], ["+contract.currency()+"], ["+execution.execId()+"], ["+execution.orderId()+"], ["+execution.shares()+"]");
		String position_mode = Utilities.getPositionModeType(null, _ibtarget_organization.getCompanyId(),_ibtarget_share.getGroupId()); 

		
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
		_log.debug("error Exception: "+e.getMessage());
	}

	@Override
	public void error(String str) {
		_log.debug("Error STR");
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
		
		if (errorCode==507)  // client_id invalidao, buscamos otros 
		{
			 _log.debug("Error :" + errorCode + ",reqId" + reqId + ",txt:" + str + "clientId:" + _clientId) ;
			/* OBTENEMOS EL CRON USADO DE LA TABLA  CON EL CLIENTID USADO, COMO TENEMOS 3 CRON, 
			 * CLIENT_ID SERÁ EL CLIENT_ID MAS EL RESTO DEL CONFIGURATIONID DE LA CLAVE PRIMERARIO, AL SER 
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


		}
		else
		{
			if (errorCode==300)  // An attempt was made to cancel market data for a ticker ID that was not associated with a current subscription. With the DDE API this occurs by clearing the spreadsheet cell.
			{
				 _log.debug("Error :" + errorCode + ",reqId" + reqId + ",txt:" + str + ",clientid:" + _clientId) ;			 		
	
			}
			else				
			{
				 if (errorCode==511) // 511,Cancel Market Data Sending Error
					 _log.debug("Error :" + errorCode + ",reqId" + reqId + ",txt:" + str + ",clientid:" + _clientId) ;
				 else					 						
				 {	
					if (errorCode>=0 && reqId>=0)  // errores operativa - lectura
					{
									
						Position _ErrorPosition;
						Share oErrorShare = null;
						
						_ErrorPosition = PositionLocalServiceUtil.fetchPosition(reqId);    			
						
						if (_ErrorPosition!=null && _ErrorPosition.IsPendingIn())  // error en una posicion dada abierta
							/* HAY QUE MANDAR CANCEL A TWS */
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
								PositionLocalServiceUtil.deletePosition(_ErrorPosition.getPositionId());
							} catch (PortalException e) {
								// TODO Auto-generated catch block
								_log.debug("error operativa PositionLocalServiceUtil.deletePosition : [" + e.getMessage() + "]") ;
							}							
								
						}
						else   // supuestamente error lectura de datos en tiempo real.
							   // METEMOS EL 25-12-2013 un  job verificador que pide el contract detail con variable de estado asociada.		
						{			
							 _log.debug("error lectura [HISTORICAL_DATA:  : [" + reqId + "," + errorCode+ "," + str + "]");
							
							/* SIEMPRE VA A VER _ErrorOrder con errores de tiempo real.
							 * TENEMOS UN PROBLEMA DE CONECTIVIDAD EN EL TICK --> PASA POR AQUI.
							 * EL JOB DE VERIFICACION COMPRUEBA PARA STOCKS SI EL ACTIVO ES VALIDO, PERO
							 * PARA UN FUTURO VALIDO PERO CON EL EXPIRATION DATE ERRONEO O PASADO, NO LO VERIFICA,
							 * LO IMPLEMENTAMOS AQUI.
							 * AQUI CONTROLAMOS DOS COSAS,
							 * 1. JOB DE VERIFICACION PARA ANULAR LOS STOCKS INVALIDOS.
							 * 2. DESCARGA DE TIEMPO REAL SOLO PARA FUTUROS POR LA RAZON ANTERIOR,. 
							 * Filtramos los del CONTRACTDETAILS EXCLUSIVAMENTE. ES DECIR, ESTADO A ARRANCADO.
							 * [Stopped,Requested, Ended, Received, Executing] y SI ES UN FUTURO.z
							 * _HISTORICAL_DATA_REQUEST "Executing" para controlar los errores  
							 * 162	Historical market data Service error message.				
							 * 165	Historical market Data Service query message.
							 * 
							 *   */															
							 
							//IBOrder _ErrorOrder = IBOrderLocalServiceUtil.fetchIBOrder(reqId);
							 IBOrder _ErrorOrder  = IBOrderLocalServiceUtil.findByOrderClientGroupCompany(reqId, _clientId, _ibtarget_organization.getCompanyId(),_ibtarget_organization.getGroupId());
							if (_ErrorOrder!=null && reqId!=-1)
							{						
								oErrorShare = ShareLocalServiceUtil.fetchShare(_ErrorOrder.getShareID());  					
								if (oErrorShare!=null) 						
								{						
									_log.debug("error order  : " + oErrorShare.getSymbol());
									oErrorShare.setActive(Boolean.FALSE);// desactivamos lectura.
									oErrorShare.setValidated_trader_provider(Boolean.FALSE);
									oErrorShare.setDate_validated_trader_provider(new Date());
									oErrorShare.setLast_error_trader_provider(_stbB.toString());							
									/* actualizamos datos error de operativa */
									ShareLocalServiceUtil.updateShare(oErrorShare);											
								}
							} // fin one <> -1										
						}
					}
				 }
			}
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
	@Override
	public void tickPrice(int tickerId, int field, double price, TickAttr attrib) {
    	 //  TODO Auto-generated method stub
	    _log.debug("Impl tickPrice : + " + tickerId + ",prices:" + price + ",field" + field);
		// TODO Auto-generated method stub
		IBOrder MyOrder = null;
		
		if (price>0 && (field==ConfigKeys._TICKTYPE_LAST || field==ConfigKeys. _TICKTYPE_DELAYED_LAST || field==ConfigKeys. _TICKTYPE_CLOSE)) {

			//MyOrder =  IBOrderLocalServiceUtil.fetchIBOrder(tickerId);
			MyOrder =  IBOrderLocalServiceUtil.findByOrderClientGroupCompany(tickerId, _clientId, _ibtarget_organization.getCompanyId(),_ibtarget_organization.getGroupId());
			if (MyOrder == null) {
				
				_log.debug("No se encuentra el ID " + tickerId);
				return;
			}
			
			Date cNow = new Date();
			Calendar _calNow = Calendar.getInstance();
			_calNow.setTime(cNow);
		//	_calNow.set(Calendar.MILLISECOND,0);
			
			// verificamos si esta en modo simulation con precios introducidos a mano 
			Share share = ShareLocalServiceUtil.fetchShare(MyOrder.getShareID());
			if (share!=null) 
			{
					if (share.getSimulation_end_date()==null)
					{
						
						_log.debug("share : " + share.getSymbol() + "," + share.getShareId() + "Impl tickPrice : + " + tickerId + ",prices:" + price + ",field" + field + ",_clientId" + _clientId);
						
						Realtime  oReal = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
						oReal.setGroupId(MyOrder.getGroupId());
						oReal.setCompanyId(MyOrder.getCompanyId());
						oReal.setShareId(MyOrder.getShareID());
						oReal.setValue(price);
						oReal.setCreateDate(_calNow.getTime());
						oReal.setModifiedDate(_calNow.getTime());
						oReal.setCloseprice(field==ConfigKeys. _TICKTYPE_CLOSE ? Boolean.TRUE : Boolean.FALSE);
						
						
						RealtimeLocalServiceUtil.updateRealtime(oReal);
						
						/* MODO FAKE, UN SOLO TWS, REPLICAMOS REALTIME PARA CADA SYMBOL IGUAL  */
						if (standalone_mode)
						{
							List<Share> fakesharelist = ShareLocalServiceUtil.findBySymbolExcludingId(share.getSymbol(), share.getShareId());
							for (Share fakeshare : fakesharelist)
							{
								oReal = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
								oReal.setGroupId(fakeshare.getGroupId());
								oReal.setCompanyId(fakeshare.getCompanyId());
								oReal.setShareId(fakeshare.getShareId());
								oReal.setCloseprice(field==ConfigKeys. _TICKTYPE_CLOSE ? Boolean.TRUE : Boolean.FALSE);
								oReal.setValue(price);
								oReal.setCreateDate(_calNow.getTime());
								oReal.setModifiedDate(_calNow.getTime());
								RealtimeLocalServiceUtil.updateRealtime(oReal);
							}
							
							
							
						}
						
						
						/* MyOrder.setChecked(true);
						IBOrderLocalServiceUtil.updateIBOrder(MyOrder);*/
					}
					else
					{
						if (cNow.after(share.getSimulation_end_date())) 				 // pasada la simulación?
						{
							share.setSimulation_end_date(null);
							ShareLocalServiceUtil.updateShare(share);
						}
						
					}
			}
			else
			{
				_log.debug("No se encuentra share for OrderId + " + MyOrder.getShareID());

			}
		
		}
			
	}
	@Override
	public void orderStatus(int orderId, String status, double filled, double remaining, double avgFillPrice,
			int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {

			
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
		
		_log.debug("orderStatus for " + orderId + ",status:" +  status);
		
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
		if (changed)
		{	    			
			// controlamos todas las canceladas			
			if (!bIsSellOperation)   // ENTRADA OPERATION ... CANCELLED? --> LA BORRAMOS PARA QUE NO CONSTE
			{
				_oPosition.setState_in(status);
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
				
				/* OJO, PUEDEN SER VENTAS/COMPRAS  PARCIALES..ENTRADA...SOLO OPERACIONES TOTALES */
				if (PositionStates.statusTWSCallBack.Filled.toString().equals(status))
	    		{
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
				if (PositionStates.statusTWSCallBack.Filled.toString().equals(status) && _oPosition.getState_out()!=null)
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
			}
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

	/* PARA DATOS HISTORICOS DE VARIOS DIAS, PUEDE SER QUE ME DE DATOS DEL DIA ANTERIOR EN CASO DE QUE SEA LA PRIMERA HORA O UN SABADO
* 	PROVOCANDO DUPLICIDADES, CONTROLO QUE LO SOLICITADO, ES DE LA MISMA FECHA PEDIDA */
	
	@Override
	public void historicalData(int reqId, Bar bar) {
	
	//_log.debug("historicalData , reqId: + " + reqId);	
	
	SimpleDateFormat fDateHistorical = new SimpleDateFormat(Utilities.__IBTRADER_HISTORICAL_DATE_FORMAT);
	Calendar _barDate = Calendar.getInstance();
	 
	IBOrder MyOrder = null;
	
	//MyOrder =  IBOrderLocalServiceUtil.fetchIBOrder(tickerId);
	MyOrder =  IBOrderLocalServiceUtil.findByOrderClientGroupCompany(reqId, _clientId, _ibtarget_organization.getCompanyId(),_ibtarget_organization.getGroupId());
	if (MyOrder == null) {
		
		_log.debug("No se encuentra el ID " + reqId);
		return;
	}
	
	/* CIERRE */
	/* MAXIMO  */
	/* MINIMO  */
	
	try {
		
		Date parsedDate = fDateHistorical.parse(bar.time());
		Date simulatedDate = fDateHistorical.parse(historicalDataRequest);		
		// CONTROL de usuarios edemo, simulo fecha actual de historical data, ya que me da solo la actual 
		
		Calendar _cSim = Calendar.getInstance();
		Calendar _cParsed = Calendar.getInstance();
		
		_cSim.setTimeInMillis(simulatedDate.getTime());
		_cParsed.setTimeInMillis(parsedDate.getTime());
		
		if (this.getUserTWS().equalsIgnoreCase(Utilities._DEFAULT_USER_DEMO_))
		{
			_cParsed.set(Calendar.DAY_OF_MONTH,_cSim.get(Calendar.DAY_OF_MONTH));
			_cParsed.set(Calendar.MONTH,_cSim.get(Calendar.MONTH));
			_cParsed.set(Calendar.YEAR,_cSim.get(Calendar.YEAR));
			
		}		
		_barDate.setTimeInMillis(_cParsed.getTimeInMillis());
		_barDate.add(Calendar.SECOND, -1);
		_barDate.set(Calendar.MILLISECOND, 0);
		
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		_log.debug(e.getMessage());
		//e.printStackTrace();
	}
	if (Validator.isNull(_barDate))
		return;
	
	/* CIERRE */
	HistoricalRealtime historicalrealtime = HistoricalRealtimeLocalServiceUtil.createHistoricalRealtime(CounterLocalServiceUtil.increment(HistoricalRealtime.class.getName()));
	
	historicalrealtime.setCreateDate(_barDate.getTime());
	historicalrealtime.setGroupId(MyOrder.getGroupId());
	historicalrealtime.setCompanyId(MyOrder.getCompanyId());
	historicalrealtime.setShareId(MyOrder.getShareID());
	historicalrealtime.setValue(bar.close());
	
	HistoricalRealtimeLocalServiceUtil.updateHistoricalRealtime(historicalrealtime);
	
	
	/* MINIMO  */
	_barDate.add(Calendar.SECOND, -1);
	historicalrealtime = HistoricalRealtimeLocalServiceUtil.createHistoricalRealtime(CounterLocalServiceUtil.increment(HistoricalRealtime.class.getName()));
	historicalrealtime.setCreateDate(_barDate.getTime());
	historicalrealtime.setGroupId(MyOrder.getGroupId());
	historicalrealtime.setCompanyId(MyOrder.getCompanyId());
	historicalrealtime.setShareId(MyOrder.getShareID());
	historicalrealtime.setValue(bar.low());
	
	HistoricalRealtimeLocalServiceUtil.updateHistoricalRealtime(historicalrealtime);
	/* MAXIMO  */
	
	historicalrealtime = HistoricalRealtimeLocalServiceUtil.createHistoricalRealtime(CounterLocalServiceUtil.increment(HistoricalRealtime.class.getName()));	
	historicalrealtime.setCreateDate(_barDate.getTime());
	historicalrealtime.setGroupId(MyOrder.getGroupId());
	historicalrealtime.setCompanyId(MyOrder.getCompanyId());
	historicalrealtime.setShareId(MyOrder.getShareID());
	historicalrealtime.setValue(bar.high());

	HistoricalRealtimeLocalServiceUtil.updateHistoricalRealtime(historicalrealtime);

		
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
		historialDataEnd = Boolean.TRUE;

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
		m_client.eConnect("127.0.0.1", 7499, 7);
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

		//tickDataOperations(wrapper.getClient());
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
		cal.add(Calendar.DAY_OF_MONTH, -4);
		SimpleDateFormat form = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String formatted = form.format(cal.getTime());
		m_client.reqHistoricalData(4003, _contractAPI3, formatted, "3 D", "5 mins", "TRADES", 0, 1, false, null);
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
