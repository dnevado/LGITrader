/* So the simple fact is that if an order is fully filled while the client application is not connected to TWS, or TWS is not connected to the IB servers, the client will never receive an orderStatus with a status of ‘Filled’.



To deal with this situation, there are three things that need to be done:



1.       The client application must save information about orders it has placed. This must be persisted to a file or database for use when the application is restarted.



2.       When the client application connects to TWS it must call reqExecutions and reqOpenOrders. The combination of the saved order information and the information received via execDetails and openOrder is enough to reconstruct exactly what the current state is. (Note that there is a TWS API configuration option for open orders to be notified automatically when a client connects, but it’s better to call reqOpenOrders as you cannot guarantee that this option will be set.)



3.       When TWS reconnects to the IB servers, the client receives an 1101 or 1102 message code via the error callback. It must call reqExecutions and reqOpenOrders to get up-to-date information about orders and fills.
*/

package com.ibtrader.interactive;

import java.sql.Timestamp;
import java.text.DecimalFormat;
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
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
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
import com.sun.javafx.webkit.UtilitiesImpl;



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
	

	
	//! [socket_declare]
	
	//! [socket_init]
	public TIMApiWrapper(int clientId, boolean debug) {
		readerSignal = new EJavaSignal();
		clientSocket = new EClientSocket(this, readerSignal);
		_clientId = clientId;	
	}
	
	public TIMApiWrapper(int clientId ) {
		readerSignal = new EJavaSignal();
		clientSocket = new EClientSocket(this, readerSignal);
		_clientId = clientId;
		try {
			guestGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), GroupConstants.GUEST).getGroupId();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			guestGroupId = -1;
		}
	}
	
	public void getContractDetails(int requestId, Contract contract){
		clientSocket.reqContractDetails(requestId, contract);
	}
	
	public void getOpenOrders(int requestId) throws InterruptedException{
		clientSocket.reqOpenOrders();		
	}
    public void getExecutionOrders(int requestId) throws InterruptedException{
    	clientSocket.reqExecutions(requestId,  new ExecutionFilter());
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
		        } catch (Exception e) {
		        	_log.info("Connnect Exception: "+e.getMessage());
		        }
		    }
		 }).start();
	}
	
	//! [socket_init]
	public EClientSocket getClient() {
		return clientSocket;
	}
	
	public EReaderSignal getSignal() {
		return readerSignal;
	}
	
	public int getCurrentOrderId() {
		return currentOrderId++;
	}
	
	 //! [tickprice]

	//! [tickprice]
	
	//! [ticksize]
	@Override
	public void tickSize(int tickerId, int field, int size) {
		//System.out.println("Tick Size. Ticker Id:" + tickerId + ", Field: " + field + ", Size: " + size);
	}
	//! [ticksize]
	//! [tickoptioncomputation]
	@Override
	public void tickOptionComputation(int tickerId, int field,
			double impliedVol, double delta, double optPrice,
			double pvDividend, double gamma, double vega, double theta,
			double undPrice) {
		System.out.println("TickOptionComputation. TickerId: "+tickerId+", field: "+field+", ImpliedVolatility: "+impliedVol+", Delta: "+delta
                +", OptionPrice: "+optPrice+", pvDividend: "+pvDividend+", Gamma: "+gamma+", Vega: "+vega+", Theta: "+theta+", UnderlyingPrice: "+undPrice);
	}
	//! [tickoptioncomputation]
	
	//! [tickgeneric]
	@Override
	public void tickGeneric(int tickerId, int tickType, double value) {
		//System.out.println("Tick Generic. Ticker Id:" + tickerId + ", Field: " + ", Value: " + value);
	}
	//! [tickgeneric]
	
	//! [tickstring]
	@Override
	public void tickString(int tickerId, int tickType, String value) {
	//	System.out.println("Tick string. Ticker Id:" + tickerId + ", Type: " + tickType + ", Value: " + value);
	}
	//! [tickstring]
	@Override
	public void tickEFP(int tickerId, int tickType, double basisPoints,
			String formattedBasisPoints, double impliedFuture, int holdDays,
			String futureLastTradeDate, double dividendImpact,
			double dividendsToLastTradeDate) {
		System.out.println("TickEFP. "+tickerId+", Type: "+tickType+", BasisPoints: "+basisPoints+", FormattedBasisPoints: "+
			formattedBasisPoints+", ImpliedFuture: "+impliedFuture+", HoldDays: "+holdDays+", FutureLastTradeDate: "+futureLastTradeDate+
			", DividendImpact: "+dividendImpact+", DividendsToLastTradeDate: "+dividendsToLastTradeDate);
	}
	//! [orderstatus]
	
	/* lOrders
	 * LISTA DE ORDENES QUE ACOMPAÑAN A LA ORDEN PADRE, SOLO SE APLICA A ORDENES DE TIPO TRAIL PARA AJUSTAR BENEFICIO 
	 */
	
	public void openOrder(Contract contract, Order parentOrder, List<Order> childsOrders, OrderState orderState, long positionId) {
	
		
		 /* METEMOS DOBLE VALIDACION POR LOS THREADS EN CASO DE QUE HAYA OPERACION PREVIA YA EN MARCHA 	
		  * 
		  * 
		  */
		int  _INCREMENT_ORDER_ID = getCurrentOrderId();
		int  parentOrderId = _INCREMENT_ORDER_ID;
		/* if (_INCREMENT_ORDER_ID>0) // mecanismo de control, siempre el next valid >=1
		{*/
		
		IBOrderLocalServiceUtil.deleteByOrderCompanyGroup(_INCREMENT_ORDER_ID, _ibtarget_share.getCompanyId(), _ibtarget_share.getGroupId(),_clientId,_ibtarget_share.getShareId());
		IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);			/* insertamos control de ordenes de peticion */
		_order.setCompanyId(_ibtarget_share.getCompanyId());
		_order.setGroupId(_ibtarget_share.getGroupId());
		_order.setShareID(_ibtarget_share.getShareId());
		_order.setOrdersId(_INCREMENT_ORDER_ID);
		_order.setCreateDate(new Date());
		_order.setModifiedDate(new Date());			
		_order.setIbclientId(_clientId);					
		IBOrderLocalServiceUtil.updateIBOrder(_order);
		Position _position = PositionLocalServiceUtil.fetchPosition(positionId);
		/* ACTULIAMOS CON LA POSICION DE ENTRADA  SI  ES UNA ENTRADA */
		if (_position.getDate_real_in()!=null)  // SALIDA DE LA MISMA POSICION 			
			_position.setPositionId_tws_out(_INCREMENT_ORDER_ID);
		else
			_position.setPositionId_tws_in(_INCREMENT_ORDER_ID);
		PositionLocalServiceUtil.updatePosition(_position);
		//	_log.info("1. openOrder...." +  positionId + "," + _INCREMENT_ORDER_ID);
		// Si hay hijas, aseguramos el transmit correcto,
		if (childsOrders!=null && !childsOrders.isEmpty())			
			parentOrder.transmit(false);
		
	/*	 if (!this.isConnected()) 
			 this.connect(this., _PORT,_CLIENT_ID); 	 	
 

		// if (oTWS.GITraderTWSIsConnected() )
		if (wrapper.isConnected())
	    {
			
				wrapper.reqNextId();
		
		*/
		clientSocket.placeOrder(new Long(_INCREMENT_ORDER_ID).intValue(), contract, parentOrder);
		for (Order childOrder : childsOrders)
		{
			_INCREMENT_ORDER_ID++;
			childOrder.parentId(parentOrderId);
			childOrder.transmit(true);
			IBOrderLocalServiceUtil.deleteByOrderCompanyGroup(_INCREMENT_ORDER_ID, _ibtarget_share.getCompanyId(), _ibtarget_share.getGroupId(),_clientId,_ibtarget_share.getShareId());
			 _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);			/* insertamos control de ordenes de peticion */
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
			
		//}
		
	}
	
	//! [openorder]
	@Override
	public void openOrder(int orderId, Contract contract, Order order,OrderState orderState) {
		 _log.info("OpenOrder. ID: "+orderId+", "+contract.symbol()+", "+contract.secType()+" @ "+contract.exchange()+": "+
			order.action()+", "+order.orderType()+" "+order.totalQuantity());				 
		}
			
	//! [openorder]
	
	//! [openorderend]
	@Override
	public void openOrderEnd() {
		System.out.println("OpenOrderEnd");
	}
	//! [openorderend]
	
	
	//! [updateaccountvalue]
	@Override
	public void updateAccountValue(String key, String value, String currency,
			String accountName) {
		System.out.println("UpdateAccountValue. Key: " + key + ", Value: " + value + ", Currency: " + currency + ", AccountName: " + accountName);
	}
	//! [updateaccountvalue]
	
	
	
	//! [updateaccounttime]
	@Override
	public void updateAccountTime(String timeStamp) {
		System.out.println("UpdateAccountTime. Time: " + timeStamp+"\n");
	}
	//! [updateaccounttime]
	
	//! [accountdownloadend]
	@Override
	public void accountDownloadEnd(String accountName) {
		System.out.println("Account download finished: "+accountName+"\n");
	}
	//! [accountdownloadend]
	
	//! [nextvalidid]
	@Override
	public void nextValidId(int orderId) {
		System.out.println("Next Valid Id: ["+orderId+"]");
		currentOrderId = orderId;
	}
	//! [nextvalidid]
	
	//! [contractdetails]
	@Override
	public void contractDetails(int reqId, ContractDetails contractDetails) {
		System.out.println("TIMApiWrapper ContractDetails. ReqId: ["+reqId+"] - ["+contractDetails.contract().symbol()+"], ["+contractDetails.contract().secType()+"], ConId: ["+contractDetails.contract().conid()+"] @ ["+contractDetails.contract().exchange()+"]");
	}
	//! [contractdetails]
	@Override
	public void bondContractDetails(int reqId, ContractDetails contractDetails) {
		System.out.println("bondContractDetails");
	}
	//! [contractdetailsend]
	@Override
	public void contractDetailsEnd(int reqId)
    {
		 _log.info("contractDetailsEnd:" + reqId);
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
			_log.info("Updating contract share:" + share.getSymbol());
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
		
		_log.info("ExecDetails. "+reqId+" - ["+contract.symbol()+"], ["+contract.secType()+"], ["+contract.currency()+"], ["+execution.execId()+"], ["+execution.orderId()+"], ["+execution.shares()+"]");

		
		Position _oPosition = PositionLocalServiceUtil.findByPositionID_In_TWS(_ibtarget_organization.getGroupId(), _ibtarget_organization.getCompanyId(),execution.orderId());
		boolean bChanged = false;
		// SI ES NULL, QUIERE DECIR QUE PUEDE VENIR UNA OPERACION DE VENTA...LA BUSCAMOS.		
		if (_oPosition==null)
		{
			_oPosition = PositionLocalServiceUtil.findByPositionID_Out_TWS(_ibtarget_organization.getGroupId(), _ibtarget_organization.getCompanyId(),execution.orderId());
			if (_oPosition==null) 
			{
				_log.info("execDetails order not found for Order Key:" + reqId + ",contract:" + contract.symbol() + ",Execution:" + execution.avgPrice());
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
					_oPosition.setShare_number_traded(_oPosition.getShare_number());
					
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
				_oPosition.setDate_real_in(_oPosition.getDate_in());
				_oPosition.setPrice_real_in(execution.avgPrice());
				_oPosition.setShare_number_traded(_oPosition.getShare_number());
				
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
		System.out.println("ExecDetailsEnd. "+reqId+"\n");
	}
	//! [execdetailsend]
	
	//! [updatemktdepth]
	@Override
	public void updateMktDepth(int tickerId, int position, int operation,
			int side, double price, int size) {
		System.out.println("UpdateMarketDepth. "+tickerId+" - Position: "+position+", Operation: "+operation+", Side: "+side+", Price: "+price+", Size: "+size+"");
	}
	//! [updatemktdepth]
	@Override
	public void updateMktDepthL2(int tickerId, int position,
			String marketMaker, int operation, int side, double price, int size) {
		System.out.println("updateMktDepthL2");
	}
	//! [updatenewsbulletin]
	@Override
	public void updateNewsBulletin(int msgId, int msgType, String message,
			String origExchange) {
		System.out.println("News Bulletins. "+msgId+" - Type: "+msgType+", Message: "+message+", Exchange of Origin: "+origExchange+"\n");
	}
	//! [updatenewsbulletin]
	
	//! [managedaccounts]
	@Override
	public void managedAccounts(String accountsList) {
		System.out.println("Account list: " +accountsList);
	}
	//! [managedaccounts]

	//! [receivefa]
	@Override
	public void receiveFA(int faDataType, String xml) {
		System.out.println("Receing FA: "+faDataType+" - "+xml);
	}
	//! [receivefa]
	
	//! [historicaldata]

	//! [historicaldata]
	
	//! [scannerparameters]
	@Override
	public void scannerParameters(String xml) {
		System.out.println("ScannerParameters. "+xml+"\n");
	}
	//! [scannerparameters]
	
	//! [scannerdata]
	@Override
	public void scannerData(int reqId, int rank,
			ContractDetails contractDetails, String distance, String benchmark,
			String projection, String legsStr) {
		System.out.println("ScannerData. "+reqId+" - Rank: "+rank+", Symbol: "+contractDetails.contract().symbol()+", SecType: "+contractDetails.contract().secType()+", Currency: "+contractDetails.contract().currency()
                +", Distance: "+distance+", Benchmark: "+benchmark+", Projection: "+projection+", Legs String: "+legsStr);
	}
	//! [scannerdata]
	
	//! [scannerdataend]
	@Override
	public void scannerDataEnd(int reqId) {
		System.out.println("ScannerDataEnd. "+reqId);
	}
	//! [scannerdataend]
	
	//! [realtimebar]
	@Override
	public void realtimeBar(int reqId, long time, double open, double high,
			double low, double close, long volume, double wap, int count) {
		System.out.println("RealTimeBars. " + reqId + " - Time: " + time + ", Open: " + open + ", High: " + high + ", Low: " + low + ", Close: " + close + ", Volume: " + volume + ", Count: " + count + ", WAP: " + wap);
	}
	//! [realtimebar]
	@Override
	public void currentTime(long time) {
		System.out.println("currentTime");
	}
	//! [fundamentaldata]
	@Override
	public void fundamentalData(int reqId, String data) {
		System.out.println("FundamentalData. ReqId: ["+reqId+"] - Data: ["+data+"]");
	}
	//! [fundamentaldata]
	@Override
	public void deltaNeutralValidation(int reqId, DeltaNeutralContract underComp) {
		System.out.println("deltaNeutralValidation");
	}
	//! [ticksnapshotend]
	@Override
	public void tickSnapshotEnd(int reqId) {
		System.out.println("TickSnapshotEnd: "+reqId);
	}
	//! [ticksnapshotend]
	
	//! [marketdatatype]
	@Override
	public void marketDataType(int reqId, int marketDataType) {
		System.out.println("MarketDataType. ["+reqId+"], Type: ["+marketDataType+"]\n");
	}
	//! [marketdatatype]
	
	//! [commissionreport]
	@Override
	public void commissionReport(CommissionReport commissionReport) {
		System.out.println("CommissionReport. ["+commissionReport.m_execId+"] - ["+commissionReport.m_commission+"] ["+commissionReport.m_currency+"] RPNL ["+commissionReport.m_realizedPNL+"]");
	}
	//! [commissionreport]
	
	
	
	//! [positionend]
	@Override
	public void positionEnd() {
		System.out.println("PositionEnd \n");
	}
	//! [positionend]
	
	//! [accountsummary]
	@Override
	public void accountSummary(int reqId, String account, String tag,
			String value, String currency) {
		System.out.println("Acct Summary. ReqId: " + reqId + ", Acct: " + account + ", Tag: " + tag + ", Value: " + value + ", Currency: " + currency);
	}
	//! [accountsummary]
	
	//! [accountsummaryend]
	@Override
	public void accountSummaryEnd(int reqId) {
		System.out.println("AccountSummaryEnd. Req Id: "+reqId+"\n");
	}
	//! [accountsummaryend]
	@Override
	public void verifyMessageAPI(String apiData) {
		System.out.println("verifyMessageAPI");
	}

	@Override
	public void verifyCompleted(boolean isSuccessful, String errorText) {
		System.out.println("verifyCompleted");
	}

	@Override
	public void verifyAndAuthMessageAPI(String apiData, String xyzChallange) {
		System.out.println("verifyAndAuthMessageAPI");
	}

	@Override
	public void verifyAndAuthCompleted(boolean isSuccessful, String errorText) {
		System.out.println("verifyAndAuthCompleted");
	}
	//! [displaygrouplist]
	@Override
	public void displayGroupList(int reqId, String groups) {
		System.out.println("Display Group List. ReqId: "+reqId+", Groups: "+groups+"\n");
	}
	//! [displaygrouplist]
	
	//! [displaygroupupdated]
	@Override
	public void displayGroupUpdated(int reqId, String contractInfo) {
		System.out.println("Display Group Updated. ReqId: "+reqId+", Contract info: "+contractInfo+"\n");
	}
	//! [displaygroupupdated]
	@Override
	public void error(Exception e) {
		_log.info("error Exception: "+e.getMessage());
	}

	@Override
	public void error(String str) {
		System.out.println("Error STR");
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
			 _log.info("Error :" + errorCode + ",reqId" + reqId + ",txt:" + str + "clientId:" + _clientId) ;
			/* OBTENEMOS EL CRON USADO DE LA TABLA  CON EL CLIENTID USADO, COMO TENEMOS 3 CRON, 
			 * CLIENT_ID SERÁ EL CLIENT_ID MAS EL RESTO DEL CONFIGURATIONID DE LA CLAVE PRIMERARIO, AL SER 
			 * SECUENCIALES LOS 3 CRON, NOS ASEGURAMOS DE SER DISTINTOS, HASTA UN MAXIMO DE 1024 INICIALMENTE    */
			Config _conf = ConfigLocalServiceUtil.findByIsCronValue(Boolean.TRUE, String.valueOf(_clientId));			
			if (_conf!=null)
			{
				Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId();
				_conf.setValue(String.valueOf(NewClientID));
				ConfigLocalServiceUtil.updateConfig(_conf);
			}			


		}
		else
		{
			if (errorCode==300)  // An attempt was made to cancel market data for a ticker ID that was not associated with a current subscription. With the DDE API this occurs by clearing the spreadsheet cell.
			{
				 _log.info("Error :" + errorCode + ",reqId" + reqId + ",txt:" + str + ",clientid:" + _clientId) ;			 		
	
			}
			else				
			{
				 if (errorCode==511) // 511,Cancel Market Data Sending Error
					 _log.info("Error :" + errorCode + ",reqId" + reqId + ",txt:" + str + ",clientid:" + _clientId) ;
				 else					 						
				 {	
					if (errorCode>=0 && reqId>=0)  // errores operativa - lectura
					{
									
						SimpleDateFormat sdf2 = new SimpleDateFormat();
						sdf2.applyPattern("dd-MM-yyyy HH:mm:ss");
						 
						Position _ErrorPosition;
						Share oErrorShare = null;
						
						_ErrorPosition = PositionLocalServiceUtil.fetchPosition(reqId);    			
						
						if (_ErrorPosition!=null && _ErrorPosition.IsPendingIn())  // error en una posicion dada abierta
							/* HAY QUE MANDAR CANCEL A TWS */
						{ 
							
							oErrorShare = ShareLocalServiceUtil.fetchShare(_ErrorPosition.getShareId());  
							_log.info("error operativa : [" + reqId + "," + errorCode + "," + str + "]");
							_log.info("error order  : " + oErrorShare.getSymbol());
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
								_log.info("error operativa PositionLocalServiceUtil.deletePosition : [" + e.getMessage() + "]") ;
							}							
								
						}
						else   // supuestamente error lectura de datos en tiempo real.
							   // METEMOS EL 25-12-2013 un  job verificador que pide el contract detail con variable de estado asociada.		
						{			
							 _log.info("error lectura [HISTORICAL_DATA:  : [" + reqId + "," + errorCode+ "," + str + "]");
							
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
									_log.info("error order  : " + oErrorShare.getSymbol());
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
		System.out.println("Connection closed");
	}

	//! [connectack]
	@Override
	public void connectAck() {
		if (clientSocket.isAsyncEConnect()) {
			System.out.println("Acknowledging connection");
			clientSocket.startAPI();
		}
	}
	@Override
	public void tickPrice(int tickerId, int field, double price, TickAttr attrib) {
    	 //  TODO Auto-generated method stub
	    _log.debug("Impl tickPrice : + " + tickerId + ",prices:" + price + ",field" + field);
		// TODO Auto-generated method stub
		IBOrder MyOrder = null;
		
		if (price>0 && (field==ConfigKeys._TICKTYPE_LAST || field==ConfigKeys._TICKTYPE_DELAYED_LAST)) {

			//MyOrder =  IBOrderLocalServiceUtil.fetchIBOrder(tickerId);
			MyOrder =  IBOrderLocalServiceUtil.findByOrderClientGroupCompany(tickerId, _clientId, _ibtarget_organization.getCompanyId(),_ibtarget_organization.getGroupId());
			if (MyOrder == null) {
				
				_log.info("No se encuentra el ID " + tickerId);
				return;
			}
			
			Date cNow = new Date();
			Calendar _calNow = Calendar.getInstance();
			_calNow.setTime(cNow);
		//	_calNow.set(Calendar.MILLISECOND,0);
			
			// verificamos si esta en modo simulation con precios introducidos a mano 
			Share share = ShareLocalServiceUtil.fetchShare(MyOrder.getShareID());
			
			if (share.getSimulation_end_date()==null)
			{
				Realtime  oReal = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				oReal.setGroupId(MyOrder.getGroupId());
				oReal.setCompanyId(MyOrder.getCompanyId());
				oReal.setShareId(MyOrder.getShareID());
				oReal.setValue(price);
				oReal.setCreateDate(_calNow.getTime());
				oReal.setModifiedDate(_calNow.getTime());
				RealtimeLocalServiceUtil.updateRealtime(oReal);
				
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
		
		
		Position _oPosition = PositionLocalServiceUtil.findByPositionID_In_TWS(_ibtarget_share.getGroupId(), _ibtarget_organization.getCompanyId(),orderId);
		// SI ES NULL, QUIERE DECIR QUE PUEDE VENIR UNA OPERACION DE VENTA...LA BUSCAMOS.		
		if (_oPosition==null)
		{
			_oPosition = PositionLocalServiceUtil.findByPositionID_Out_TWS(_ibtarget_share.getGroupId(), _ibtarget_organization.getCompanyId(),orderId);
			if (_oPosition==null) 
			{
				_log.info("Error Execution Details order not found for Order Key:" + orderId);
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
					
	    			/* acumulo las acciones vendidas y a vender en la operativa */
	    			long _RemaingShares = _oPosition.getShare_number_to_trade() +_oPosition.getShare_number_traded();
	    			long _TotalShares = _oPosition.getShare_number();
	    			
	    			_oPosition.setShare_number_traded(_RemaingShares);
	    			_oPosition.setShare_number_to_trade(_oPosition.getShare_number() - _oPosition.getShare_number_traded());
	    			
	    			/* NO ES EL SELL OK y PRECIOS REALES NO SE GUARDAN EN LOS CAMPOS hasta que el numero de acciones vendidas quede cerrado*/
	    			if (_RemaingShares ==_TotalShares)   // ya no hay =
	    			{	
	    				_oPosition.setState_out(status);	 
	    				_oPosition.setDate_out(new Date()) ; // OJO TIMESTAMP.
	    				_oPosition.setDate_real_out(new Date());			    			
		    			_oPosition.setPrice_real_out(avgFillPrice);  // cogemos el avg que nos manda el TWS
	    				_oPosition.setState(PositionStates.status.SELL_OK.toString());
	    			}
	    			else   //  hay acciones pendientes para salir..actualizo el stop de beneficio para no dispararse
	    			{
	    				// SE PUEDEN DAR DOS COSAS,
	    				// 1. QUE EL STOP PROFIT SALTO POR DEBAJO DEL INICIAL--> DEJAMOS EL INICIAL
	    				// 2. QUE EL STOP PROFIT SALTO POR ENCIMA DEL INICIAL --> SIGUE EN TENDENCIA, LOS MULTIPLICAMOS POR UN 50%	    				
	    				_oPosition.setState_out(null);	
	    				
	    				double priceStopProfitAperturaPosicion = 0;
	    				double priceNewStopProfit = 0;
	    				
	    				
	    				if (_oPosition.getType().equals(PositionStates.statusTWSFire.SELL.toString()))  //short
		    			{
	    					// cojo el original
	    					priceStopProfitAperturaPosicion = _oPosition.getPrice_real_in() - (_oPosition.getPrice_real_in() *  sharePosition.getPercentual_stop_profit() / 100);
	    					// si el original es mayor...subio la accion mucho, la tendencia es seguir, le subo un 50% para que no salte el resto de la posicion 
	    					if (priceStopProfitAperturaPosicion > _oPosition.getPricestopprofit_out())
	    					{
	    						priceNewStopProfit = _oPosition.getPricestopprofit_out() - (_oPosition.getPricestopprofit_out()*0.5);
	    						priceNewStopProfit  = priceNewStopProfit /100;
	    						//_oPosition.setSell_percentual_stop_profit(Utilidades.RedondeaPrice(priceNewStopProfit));	    						
	    					}
	    					else  // restauramos el original
	    					{
	    						priceNewStopProfit = priceStopProfitAperturaPosicion;
	    									    						
	    					}
		    			}
		    			else  //long position
		    			{		
		    				// cojo el original
	    					priceStopProfitAperturaPosicion = _oPosition.getPrice_real_in()  + (_oPosition.getPrice_real_in() *  sharePosition.getPercentual_stop_profit() / 100);
	    					// si el original es menor...subio la accion mucho, la tendencia es seguir, le subo un 50% para que no salte el resto 
	    					if (priceStopProfitAperturaPosicion < _oPosition.getPricestopprofit_out())
	    					{
	    						priceNewStopProfit = _oPosition.getPricestopprofit_out() + (_oPosition.getPricestopprofit_out() *0.5);
	    						priceNewStopProfit  = priceNewStopProfit / 100;
	    						//_oPosition.setSell_percentual_stop_profit(Utilidades.RedondeaPrice(priceNewStopProfit));
	    						
	    					}
	    					else  // restauramos el original
	    					{
	    						priceNewStopProfit = priceStopProfitAperturaPosicion;
	    									    						
	    					}
	    				}
	    				
	    				_oPosition.setPricestopprofit_out(Utilities.RoundPrice(priceNewStopProfit));
	    				
	    				//LogTWM.log(Priority.INFO,"New Profit Stop.. " + Utilidades.RedondeaPrice(priceNewStopProfit));
	    				
	    			}
	    			
	    			// ACTUALIZAMOS EL PRECIO DE SALIDA CON EL PORCENTAJE.
	    			/*priceStopLost = avgFillPrice  + (avgFillPrice *  _oPosition.getSell_percentual_stop_lost());
	    			priceStopProfit = avgFillPrice  - (avgFillPrice *  _oPosition.getSell_percentual_stop_profit());*/
	    			/* DOS DECIMALES PRECIOS DE SALIDA LOST Y PROFIT */
	    			/*_oPosition.setSell_price_stop_lost(Utilidades.RedondeaPrice(priceStopLost));
	    			_oPosition.setSell_price_stop_profit(Utilidades.RedondeaPrice(priceStopProfit));*/
	    			
	    			
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
	@Override
	public void historicalData(int reqId, Bar bar) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
	
	

}
