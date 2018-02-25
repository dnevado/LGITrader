package com.ibtrader.interactive;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;


import org.xml.sax.SAXException;

import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.EClientSocket;
import com.ib.client.Order;
import com.ib.client.TagValue;
import com.ib.client.TickAttr;
/* import com.ib.client.TickAttr; */
import com.ib.client.TickType;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;
import com.ibtrader.constants.IBTraderConstants;
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
import com.ib.client.EJavaSignal;
import com.ib.client.EReader;
import com.ib.client.EReaderSignal;


public class TIMApiGITrader extends TIMApiWrapper {

	
	Log _log = LogFactoryUtil.getLog(TIMApiGITrader.class);
	long guestGroupId=0;
	SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMM");	
	private String _ConnectionHOST = "127.0.0.1";
	private int    _ConnectionPORT = 7497;
	private int    _ConnectionCLIENTID = 0;
	
	private boolean _sendDisconnectEvent =false; 
	
	/* USAMOS PARA ALMACENAR LAS TRAZAS DE RESULTADOS POR BLOQUES Y BUSCAR EL MAXIMO Y MIN DESPUES */	
	
	/* ESTE DATO SIRVE PARA ALMACENAR DATOS HISTORICOS DE LA FECHA EN CUESTION 
	 * LA TWS DEVUELVE DATOS ANTERIORES A UNA FECHA DADA, NO ENTRE FECHAS, CON LO QUE SI PIDES UN DOMINGO TE DARA 
	 * DEL VIERNES */
	/* METODOS PERSONALIZADOS QUE NO ESTAN EN EL INTERFACE */
	public boolean GITradercancelOrder(int RequestID) throws InterruptedException {
		clientSocket.cancelOrder(RequestID);
		return true;
	}
	
	/* METODOS PERSONALIZADOS QUE NO ESTAN EN EL INTERFACE */
	public void GITradergetContractDetails(int RequestID, Contract contract) throws InterruptedException {
		 _log.info("GITradergetContractDetails RequestID:" + RequestID);		 	
		clientSocket.reqContractDetails(RequestID, contract);
		Thread.sleep(1000);
		_log.info("Fin GITradergetContractDetails");
	/* 	try {
			IBOrderLocalServiceUtil.deleteIBOrder(RequestID);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
}	
	public Contract GITraderCreateSTKContract(String symbol) {
				return  new StkContract(symbol);
	}
	public void GITraderOpenOrder(int orderId, Contract contract, Order order) {
		 _log.info("Open Order:" + orderId + " Contrato =" + contract.symbol() + " order=" + order.totalQuantity() + " price=" + order.lmtPrice());
		 clientSocket.placeOrder(orderId, contract, order);
	}
	public TIMApiGITrader(String _host, int _port, int clientid, boolean debug)  {
		 super(clientid);					
		_ConnectionHOST = _host;
		_ConnectionPORT = _port;
		_ConnectionCLIENTID = clientid;						
	}	
	public TIMApiGITrader(String _host, int _port, int clientid)  {
		super(clientid);			
		_ConnectionHOST = _host;
		_ConnectionPORT = _port;
		_ConnectionCLIENTID = clientid;		
		
		try {
			guestGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), GroupConstants.GUEST).getGroupId();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			guestGroupId = -1;
		}
	}	
	public void GITraderGetRealTimeContract(int RequestID, Contract contract) throws InterruptedException {
		// http://interactivebrokers.github.io/tws-api/tick_types.html#gsc.tab=0
		clientSocket.reqMarketDataType(ConfigKeys.MARKET_DATA_TYPE_DELAYED_LIVE);
		clientSocket.reqMktData(RequestID, contract,  "", false, false, null); // false
	}
	public void GITraderCancelRealTimeContract(int RequestID) throws InterruptedException {
		// http://interactivebrokers.github.io/tws-api/tick_types.html#gsc.tab=0
		clientSocket.cancelMktData(RequestID);		
	}
	@Override
	public void contractDetailsEnd(int reqId)
    {
		 _log.info("contractDetailsEnd:" + reqId);
		 IBOrder _ibOrder;		 	
		 _ibOrder = IBOrderLocalServiceUtil.fetchIBOrder(reqId); 
	 	 if (_ibOrder!=null)  // error en una posicion dada abierta		
		 { 
			
			Share share = ShareLocalServiceUtil.fetchShare(_ibOrder.getShareID());  					
			share.setValidated_trader_provider(Boolean.TRUE);
			share.setDate_validated_trader_provider(new Date());
			share.setLast_error_trader_provider(null);							
			/* actualizamos datos error de operativa */
			ShareLocalServiceUtil.updateShare(share);		
			
			
		}
		 
    }
	@Override
	public void contractDetails(int reqId, ContractDetails contractDetails) {
		System.out.println("ContractDetails. ReqId: ["+reqId+"] - ["+contractDetails.contract().symbol()+"], ["+contractDetails.contract().secType()+"], ConId: ["+contractDetails.contract().conid()+"] @ ["+contractDetails.contract().exchange()+"]");
		 _log.info("contractDetails:" + reqId);
	}
	/* 1. LECTURA DE DATOS --> INFORMACION ERROR, INTRODUCIMOS OBSERVACIONES 
	 * 2. ORDENES , OPEN ORDERS...CLOSE ORDERS 
	200 -->No security definition has been found for the request.	The specified contract does not match any in IB's database, usually because of an incorrect or missing parameter.
	 */
	/* 507 --> CIENT_ID IS INVALIDO */
	@Override
	public void error(int reqId, int errorCode, String str) {
	
		StringBuilder _stbB = new StringBuilder();
		_stbB.append(reqId);
		_stbB.append("|");
		_stbB.append(errorCode);
		_stbB.append("|");
		_stbB.append(str);				
		
		if (errorCode==507)  // client_id invalidao, buscamos otro 
		{
			 _log.info("Error :" + errorCode + ",reqId" + reqId + ",txt:" + str + ",clientid:" + _ConnectionCLIENTID) ;
			/* OBTENEMOS EL CRON USADO DE LA TABLA  CON EL CLIENTID USADO, COMO TENEMOS 3 CRON, 
			 * CLIENT_ID SERÁ EL CLIENT_ID MAS EL RESTO DEL CONFIGURATIONID DE LA CLAVE PRIMERARIO, AL SER 
			 * SECUENCIALES LOS 3 CRON, NOS ASEGURAMOS DE SER DISTINTOS, HASTA UN MAXIMO DE 1024 INICIALMENTE    */
			Config _conf = ConfigLocalServiceUtil.findByIsCronValue(Boolean.TRUE, String.valueOf(_ConnectionCLIENTID));			
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
				 _log.info("Error :" + errorCode + ",reqId" + reqId + ",txt:" + str + ",clientid:" + _ConnectionCLIENTID) ;			 		
	
			}		
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
						IBOrder _ErrorOrder = IBOrderLocalServiceUtil.fetchIBOrder(reqId);
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
			
	public void error(String str) {
		
		_log.info("error consoleTWS1: [" + str + "]");
	}
	
	public void GITraderConnetToTWS() throws InterruptedException {
		
		//! [connect]
		//! [ereader]
		
		_sendDisconnectEvent = true;
		
		_log.info("Connecting to TWS with " + _ConnectionCLIENTID  + " clientID");
		clientSocket.eConnect(_ConnectionHOST, _ConnectionPORT, _ConnectionCLIENTID);
		Thread.sleep(1000);
		EReader reader2 = new EReader(clientSocket, readerSignal);   
		
		reader2.start();
		
		
		//An additional thread is created in this program design to empty the messaging queue
		new Thread(() -> {
		    while (clientSocket.isConnected()  && !_sendDisconnectEvent) {
		    	readerSignal.waitForSignal();
		        try {
		        //	_log.info("_sendDisconnectEvent : " + _sendDisconnectEvent);
		            reader2.processMsgs();
		            
		        } catch (Exception e) {
		        	_log.info("Exception connection to TWS: "+e.getMessage());
		        }
		    }
		}).start();
		
		Thread.sleep(1000);

		
	}





	
	 
	public void GITraderDisconnectFromTWS() throws InterruptedException {
		
		/* lo hacemos para parar el thread de lectura de la cola de mensajes */
		_sendDisconnectEvent = true;
		Thread.sleep(1000);	
		clientSocket.eDisconnect();

	}
	public boolean GITraderTWSIsConnected() throws InterruptedException {
		return clientSocket.isConnected();
	}
	
	public static void main(String[] args) throws InterruptedException {
			
	
		/*  TIMApiGITrader oTWS = new TIMApiGITrader();
		oTWS.GITraderConnetToTWS();
		Thread.sleep(1000);
*/
		Contract  _contractAPI3 =  new StkContract("AAPL");
		_contractAPI3.symbol("AAPL");
		_contractAPI3.secType("STK");
		_contractAPI3.exchange("ISLAND");
		_contractAPI3.currency("USD");
		
		
		 TIMApiWrapper wrapper = new TIMApiWrapper(5665,false);
		 TIMApiWrapper wrapper2 = new TIMApiWrapper(5666,false);
		
		 wrapper.connect("127.0.0.1", 7498, 5665); 
		 wrapper.reqNextId(); 
		 wrapper2.connect("127.0.0.1", 7498, 5666);
		 wrapper2.reqNextId();
		 System.out.println(wrapper2.getCurrentOrderId());
		 

		 
		/* final EClientSocket m_client = wrapper.getClient();
		 final EReaderSignal m_signal = wrapper.getSignal(); 
 		 //! [connect]
		 m_client.eConnect("127.0.0.1", 7497, 5665); 
		 m_client.isConnected();
		 
		 
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
		m_client.reqContractDetails(58722, _contractAPI3);
*/
		
		
		
		
		 /*    TIMApiWrapper wrapper = new TIMApiWrapper(6,false);
			final EClientSocket m_client = wrapper.getClient();
			final EReaderSignal m_signal = wrapper.getSignal(); 
			//! [connect]
			m_client.eConnect("127.0.0.1", 7497, 12); 
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
			
			*/
	//	m_client.reqContractDetails(4545, _contractAPI3);
		//	m_client.reqMktData(98989, _contractAPI3,"", false, false,null); //false
		
		
	/* 	Contract  _contractAPI4=  new StkContract("ESTX50");
		_contractAPI3.symbol("ESTX50");
		_contractAPI3.secType("FUT");
		_contractAPI3.exchange("ISLAND");
		_contractAPI3.currency("USD");
		*/
	//	oTWS.GITraderGetRealTimeContract(7000, _contractAPI3);
		
		
	/* 	Thread.sleep(1000);	
		TIMApiGITrader oTWS = new TIMApiGITrader("127.0.0.1", 7497, 2015,true); 

		
		 if (oTWS.GITraderTWSIsConnected())  oTWS.GITraderDisconnectFromTWS();
		 oTWS.GITraderConnetToTWS();		
		Thread.sleep(1000);
		oTWS.GITradergetContractDetails(600, _contractAPI3);
		//oTWS.getClient().reqContractDetails(4545, _contractAPI3);
		Thread.sleep(1000);
		oTWS.GITraderGetRealTimeContract(255557, _contractAPI3); 
		oTWS.GITraderDisconnectFromTWS();
		
		*/		//! [ereader]
		// A pause to give the application time to establish the connection
		// In a production application, it would be best to wait for callbacks to confirm the connection is complete

		/* m_client.reqMarketDataType(3);
		m_client.reqMktData(5004, _contractAPI3,"", false, false,null); //false
		*/
		
	/* 	Contract contract = new Contract();
		contract.symbol("DAX");
		contract.secType("FUT");
		contract.currency("EUR");
		contract.exchange("DTB");
		contract.lastTradeDateOrContractMonth("201709");
		contract.multiplier("5");
		
		FutContract  _contractAPI4 =  new FutContract("ES","201709");
		_contractAPI4.exchange("GLOBEX");
		_contractAPI4.currency("USD");
		_contractAPI4.lastTradeDateOrContractMonth("201709");
		
		FutContract  _contractAPI5 =  new FutContract("ESTX50","201709");
		_contractAPI5.exchange("DTB");
		_contractAPI5.currency("EUR");
		_contractAPI5.lastTradeDateOrContractMonth("201709");
		
		
		//! [reqHeadTimeStamp]

		//! [cancelHeadTimestamp]
		
		//! [cancelHeadTimestamp]
		
		//! [reqhistoricaldata]
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		SimpleDateFormat form = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String formatted = form.format(cal.getTime());*/
		/* m_client.reqHistoricalData(4001, _contractAPI3, formatted, "1 M", "1 day", "MIDPOINT", 1, 1, false, null);
		m_client.reqHistoricalData(4002, _contractAPI3, formatted, "10 D", "1 min", "TRADES", 1, 1, false, null); */
		/*** Canceling historical data requests 
		Thread.sleep(2000);
		
		m_client.cancelHistoricalData(4001);
		m_client.cancelHistoricalData(4002);***/
		//! [reqhistoricaldata]
			
		
		/*	TIMApiGITrader _APIGTrader = new TIMApiGITrader("127.0.0.1", 7497, 4);
		_APIGTrader.GITraderConnetToTWS();
		_APIGTrader.GITraderGetRealTimeContract(7001, _contractAPI3);
	 	_APIGTrader.GITraderGetRealTimeContract(1002, _contractAPI2);
		_APIGTrader.GITraderGetRealTimeContract(1003, _contractAPI);*/
		
		
		//_APIGTrader.
		
		//m_client.reqMktData(18006, _contractAPI5, "", false, false, null);
		//tickDataOperations(wrapper.getClient());
		//orderOperations(wrapper.getClient(), wrapper.getCurrentOrderId());
		//contractOperations(wrapper.getClient()); 
		//hedgeSample(wrapper.getClient(), wrapper.getCurrentOrderId());
		//testAlgoSamples(wrapper.getClient(), wrapper.getCurrentOrderId());
		//bracketSample(wrapper.getClient(), wrapper.getCurrentOrderId());
		//bulletins(wrapper.getClient());
		//reutersFundamentals(wrapper.getClient());
		//marketDataType(wrapper.getClient());
		//historicalDataRequests(wrapper.getClient());
		//accountOperations(wrapper.getClient());
		//newsOperations(wrapper.getClient());
		//marketDepthOperations(wrapper.getClient());
		//rerouteCFDOperations(wrapper.getClient());
		//marketRuleOperations(wrapper.getClient());
		//tickDataOperations(wrapper.getClient());
		//pnlSingle(wrapper.getClient());
		//continuousFuturesOperations(wrapper.getClient());

	//	Thread.sleep(100000);
		//m_client.eDisconnect();

		
/* 
		TIMApiGITrader _APIGTrader = new TIMApiGITrader();
		_APIGTrader.GITraderConnetToTWS();
		_APIGTrader.GITraderGetRealTimeContract(1001, _contractAPI3);
		_APIGTrader.GITraderGetRealTimeContract(1002, _contractAPI2);
		_APIGTrader.GITraderGetRealTimeContract(1003, _contractAPI);
		

		/*** Canceling historical data request for continuous futures ***/
		//client.cancelHistoricalData(18002);

		//tickDataOperations(wrapper.getClient());
		//orderOperations(wrapper.getClient(), wrapper.getCurrentOrderId());
		//contractOperations(wrapper.getClient());
		//hedgeSample(wrapper.getClient(), wrapper.getCurrentOrderId());
		//testAlgoSamples(wrapper.getClient(), wrapper.getCurrentOrderId());
		//bracketSample(wrapper.getClient(), wrapper.getCurrentOrderId());
		//bulletins(wrapper.getClient());
		//reutersFundamentals(wrapper.getClient());
		//marketDataType(wrapper.getClient());
		//historicalDataRequests(wrapper.getClient());
		//accountOperations(wrapper.getClient());
		//newsOperations(wrapper.getClient());
		//marketDepthOperations(wrapper.getClient());
		//rerouteCFDOperations(wrapper.getClient());
		//marketRuleOperations(wrapper.getClient());
		//tickDataOperations(wrapper.getClient());
		//pnlSingle(wrapper.getClient());
		//continuousFuturesOperations(wrapper.getClient());

		
	//	_APIGTrader.disconnectFromTWS();
	}


	


	@Override
	public void tickPrice(int tickerId, int field, double price, TickAttr attrib) {
    	 //  TODO Auto-generated method stub
	    _log.debug("Impl tickPrice : + " + tickerId + ",prices:" + price + ",field" + field);
		// TODO Auto-generated method stub
		IBOrder MyOrder = null;
		
		if (field==ConfigKeys._TICKTYPE_LAST || field==ConfigKeys._TICKTYPE_DELAYED_LAST ) {

			MyOrder =  IBOrderLocalServiceUtil.fetchIBOrder(tickerId);
			if (MyOrder == null) {
				
				_log.info("No se encuentra el ID " + tickerId);
				return;
			}
			
			Realtime  oReal = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
			oReal.setGroupId(guestGroupId);
			oReal.setCompanyId(PortalUtil.getDefaultCompanyId());
			oReal.setShareId(MyOrder.getShareID());
			oReal.setValue(price);
			RealtimeLocalServiceUtil.updateRealtime(oReal);
			
			MyOrder.setChecked(true);
			IBOrderLocalServiceUtil.updateIBOrder(MyOrder);
		
		}
			
	}

	@Override
	public void nextValidId(int orderId) {
		// TODO Auto-generated method stub
		 _log.debug("GITrader, nextValidId" + orderId);
		super.nextValidId(orderId);
	}
	
	/* private  void connect(int clientId) {
		String host = System.getProperty("jts.host");
		host = host != null ? host : "";
		this.getClient().eConnect(host, 7497, clientId);
		
        final EReader reader = new EReader(this.getClient(), m_signal);
        
        reader.start();
       
		new Thread(() -> {
            while (this.getClient().isConnected()) {
                m_signal.waitForSignal();
                try {
                    SwingUtilities.invokeAndWait(() -> {
                                try {
                                    reader.processMsgs();
                                } catch (IOException e) {
                                    error(e);
                                }
                            });
                } catch (Exception e) {
                    error(e);
                }
            }
        }).start();
	}

	public void disconnect() {
		this.getClient().eDisconnect();
	}
	
	protected static void saveDataLastRealTime(int shareID, String field,
			double value) {

		RealTime oLastRealTime = RealTimeDAO.getLastRealTime(shareID);
		if (oLastRealTime != null) {
			// MyLog.log(Priority.INFO, "uPDATE rEALtIME " + shareID + "|" +
			// field + "|" + value);
			RealTimeDAO.updateRealTimeByField(oLastRealTime.getRealtimeID()
					.intValue(), field, value);
		}

	}

*/
	
		
	
	


}
