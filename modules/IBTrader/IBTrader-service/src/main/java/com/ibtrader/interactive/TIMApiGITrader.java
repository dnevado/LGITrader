package com.ibtrader.interactive;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;


import org.xml.sax.SAXException;

import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.ib.client.Order;
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

import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
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
	private int  _ConnectionPORT = 7497;
	private int _ConnectionCLIENTID = 7;
	
	
	private static double _HISTORICAL_DATA_LOW=0;
	private  static double _HISTORICAL_DATA_HIGH=0;
	private  static String  _HISTORICAL_DATA_REQUEST="";   // OK acabada.	
	public static String get_HISTORICAL_DATA_REQUEST() {
		return _HISTORICAL_DATA_REQUEST;
	}


	public static void set_HISTORICAL_DATA_REQUEST(String _HISTORICAL_DATA_REQUEST) {
		TIMApiGITrader._HISTORICAL_DATA_REQUEST = _HISTORICAL_DATA_REQUEST;
	}


	public static void set_DATE_HISTORICAL_REQUEST(String _DATE_HISTORICAL_REQUEST) {
		TIMApiGITrader._DATE_HISTORICAL_REQUEST = _DATE_HISTORICAL_REQUEST;
	}


	private  static String  _HISTORICAL_MODE_REQUEST="REAL";   // REAL, SIMULATION	
	private  static String  _CONTRACT_DATA_REQUEST="Stopped";   // [Stopped,Requested, Ended, Received,Executing]
	private static String _DATE_HISTORICAL_REQUEST = "";  //yyyymmdd  
	private static String _HISTORICAL_DATA_ERRORS_CODES = "";
	
	/* USAMOS PARA ALMACENAR LAS TRAZAS DE RESULTADOS POR BLOQUES Y BUSCAR EL MAXIMO Y MIN DESPUES */
	private  ArrayList<Double> aLowValuesHistorical =  new ArrayList<Double>();
	private ArrayList<Double> aHighValuesHistorical =  new ArrayList<Double>();
	
	/* FECHA ESTIMATIVA, NO SIRVE  COMO INFORMATIVA */
	private  static String _HISTORICAL_DATA_DATE  = "";	
	private static String _HISTORICAL_DATA_DATE_BEGINNING  = "";
	
	private String error = "";

	public String GITraderError() {
		return error;
	}

	
	
	/* ESTE DATO SIRVE PARA ALMACENAR DATOS HISTORICOS DE LA FECHA EN CUESTION 
	 * LA TWS DEVUELVE DATOS ANTERIORES A UNA FECHA DADA, NO ENTRE FECHAS, CON LO QUE SI PIDES UN DOMINGO TE DARA 
	 * DEL VIERNES */
	
	private List<Long> _lTest = null;
	
	public static String get_HISTORICAL_DATA_ERRORS_CODES() {
		return _HISTORICAL_DATA_ERRORS_CODES;
	}


	public static String get_DATE_HISTORICAL_REQUEST() {
		return _DATE_HISTORICAL_REQUEST;
	}


	
	
	/* public  void GITraderHistoricalData(int tickerId, Contract contract,
			String endDateTime, String durationStr, String barSizeSetting,
			String whatToShow, int useRTH, int formatDate) {
		
		_HISTORICAL_DATA_REQUEST = "Executing";
		
		clientSocket.reqHistoricalData(tickerId, contract, endDateTime,
				durationStr, barSizeSetting, whatToShow, useRTH, formatDate, false, null);

	}
	*/

	
	/* METODOS PERSONALIZADOS QUE NO ESTAN EN EL INTERFACE */
	public boolean GITradercancelOrder(int RequestID) throws InterruptedException {
		clientSocket.cancelOrder(RequestID);
		return true;
	}
	
	/* METODOS PERSONALIZADOS QUE NO ESTAN EN EL INTERFACE */
	public boolean GITradergetContractDetails(int RequestID, Contract contract)
			throws InterruptedException {
		
		_CONTRACT_DATA_REQUEST = "Executing";
		
		clientSocket.reqContractDetails(RequestID, contract);
		return true;
	}
	
	public Contract GITraderCreateSTKContract(String symbol) {
					
				return  new StkContract(symbol);
				
	}
	
	public void GITraderOpenOrder(int orderId, Contract contract, Order order) {
		
		 
		 _log.info("Open Order:" + orderId + " Contrato =" + contract.symbol() + " order=" + order.totalQuantity() + " price=" + order.lmtPrice());
		 

		 clientSocket.placeOrder(orderId, contract, order);

	}
	
	public TIMApiGITrader(String _host, int _port, int clientid)  {
		//super();			
		_ConnectionHOST = _host;
		_ConnectionPORT = _port;
		_ConnectionCLIENTID = clientid;		
		
		try {
			guestGroupId = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), GroupConstants.GUEST).getGroupId();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void GITrade() {
		clientSocket.eConnect(_ConnectionHOST, _ConnectionPORT, _ConnectionCLIENTID);
	}

	public void disconnectFromTWS() {
		if (clientSocket.isConnected()) {
			clientSocket.eDisconnect();
		}
	}
	
	
	/* @Override
	public void tickPrice(int tickerId, int field, double price, TickAttr attribs) {
		// TODO Auto-generated method stub
		
		IBOrder MyOrder = null;

		if (field==ConfigKeys._TICKTYPE_CLOSE)
		{
			
			if (MyOrder == null) {
				
				_log.info( "No se encuentra el ID " + tickerId);
				return;
			}

		}
		if (field==ConfigKeys._TICKTYPE_LAST) {

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
	}*/
	public void GITraderGetRealTimeContract(int RequestID, Contract contract)
			throws InterruptedException {
		
		///clientSocket .reqMktData(RequestID, contract,  "", false, false, null);
		
		clientSocket.reqMktData(RequestID, contract,  "", false,  null);
		
	}
	
	public void error(int one, int two, String str) {

		
		/* LogTWM.getLog(TIMApiWrapper.class);
		_log.info(error lectura : [" + one + "," + two
				+ "," + str + "]");
		*/
		
		/* 1. LECTURA DE DATOS --> INFORMACION ERROR, INTRODUCIMOS OBSERVACIONES 
		 * 2. ORDENES , OPEN ORDERS...CLOSE ORDERS */
		
		
		if (two>=0 && one>=0)  // errores operativa - lectura
		{
			
			java.sql.Timestamp FechaError = new Timestamp(Calendar.getInstance().getTimeInMillis());	    			
			
			SimpleDateFormat sdf2 = new SimpleDateFormat();
			sdf2.applyPattern("dd-MM-yyyy HH:mm:ss");
			 
			Position _ErrorPosition;
			_ErrorPosition = PositionLocalServiceUtil.fetchPosition(one);  
			
			Share oErrorShare = null; 
			
			if (_ErrorPosition!=null)  // error en una posicion dada
			{ 
				oErrorShare = ShareLocalServiceUtil.fetchShare(_ErrorPosition.getShareId());  
				
				if (_ErrorPosition.getPrice_out()>0)
				{
				//if (_ErrorPosition.getPrice_out()!=null && _ErrorPosition.getPrice_out().compareTo(new Double(0))>0) 			
				}	
			
				else {  // entrada???.. En ppio se quedan los errores gestionados en el orderstatus.
				
				
					_log.info("error operativa : [" + one + "," + two + "," + str + "]");
					_log.info("error order  : " + oErrorShare.getSymbol());
					oErrorShare.setActive(false); 
					 //setActive_trading(new Long(0));  // desactivamos trading.
					//Actualizamos campos de errores.
					oErrorShare.setLast_error_data_trade(FechaError + "|" +  str + "[N. Error " + two + "]");  // desactivamos trading.
					_ErrorPosition.setPendingcancelled(1);   // supuestamente se cancela SOLA.
					
					try {
						PositionLocalServiceUtil.deletePosition(_ErrorPosition.getPositionId());
					} catch (PortalException e) {
						// TODO Auto-generated catch block
						_log.info("error operativa PositionLocalServiceUtil.deletePosition : [" + e.getMessage() + "]") ;
					}
					
					//PositionDAO.deletePositionByPositionID(_ErrorPosition);
	
					/* actualizamos datos error de operativa */
					ShareLocalServiceUtil.updateShare(oErrorShare);
					//ShareDAO.updateShare(oErrorShare);
				/* fin actualizamos datos error de operativa */
				
				// Actualizamos estado de la posición con Cancelled o similar
				// Sería interesante que apareciera en la consola pero puede dar problemas (Gris???)			
					}
			}
			else   // supuestamente error lectura de datos en tiempo real.
				   // METEMOS EL 25-12-2013 un  job verificador que pide el contract detail con variable de estado asociada.		
			{			
				 _log.info("error lectura [HISTORICAL_DATA: " + _HISTORICAL_DATA_REQUEST + "] : [" + one + "," + two+ "," + str + "]");
				

				/* SIEMPRE VA A VER _ErrorOrder con errores de tiempo real.
				 * TENEMOS UN PROBLEMA DE CONECTIVIDAD EN EL TICK --> PASA POR AQUI.
				 * EL JOB DE VERIFICACION COMPRUEBA PARA STOCKS SI EL ACTIVO ES VALIDO, PERO
				 * PARA UN FUTURO VALIDO PERO CON EL EXPIRATION DATE ERRONEO O PASADO, NO LO VERIFICA,
				 * LO IMPLEMENTAMOS AQUI.
				 * AQUI CONTROLAMOS DOS COSAS,
				 * 1. JOB DE VERIFICACION PARA ANULAR LOS STOCKS INVALIDOS.
				 * 2. DESCARGA DE TIEMPO REAL SOLO PARA FUTUROS POR LA RAZON ANTERIOR,. 
				 * Filtramos los del CONTRACTDETAILS EXCLUSIVAMENTE. ES DECIR, ESTADO A ARRANCADO.
				 * [Stopped,Requested, Ended, Received, Executing] y SI ES UN FUTURO.
				 * _HISTORICAL_DATA_REQUEST "Executing" para controlar los errores  
				 * 162	Historical market data Service error message.				
				 * 165	Historical market Data Service query message.
				 * 
				 *   */
				
				if (one!=-1)
				{	
				
					
					StringBuilder _stbB = new StringBuilder();
					_stbB.append(one);
					_stbB.append("|");
					_stbB.append(two);
					_stbB.append("|");
					_stbB.append(str);
					
					
					
					_HISTORICAL_DATA_ERRORS_CODES =_stbB.toString(); 
					
					
					IBOrder _ErrorOrder = IBOrderLocalServiceUtil.fetchIBOrder(one);
					if (_ErrorOrder!=null)
					{
						
						
						oErrorShare = ShareLocalServiceUtil.fetchShare(_ErrorOrder.getShareID());  
						
						//oErrorShare = ShareDAO.getShare(_ErrorOrder.getShareID());
						
						
						if (oErrorShare!=null 
								&& (oErrorShare.getSecurity_type().equalsIgnoreCase(ConfigKeys.SECURITY_TYPE_FUTUROS)
								   || _HISTORICAL_DATA_REQUEST.equals("Executing")  
								   		|| _CONTRACT_DATA_REQUEST.equals("Requested") 
								   			|| _CONTRACT_DATA_REQUEST.equals("Executing")))
						{
						
						
							_log.info("error order  : " + oErrorShare.getSymbol());
							
							
							// DESACTIVAMOS SIEMPRE QUE NO SEA POR PACING VIOLATIONS
							//162	Historical market data Service error message.		
							if (two!=162)
							{
								
								oErrorShare.setActive(false);// desactivamos lectura.
								//.setActive(new Long(0));  
								oErrorShare.setLast_error_data_read("[" + oErrorShare.getSymbol() + "]" + FechaError + "|" + str + "[N. Error " + two + "]");  // desactivamos
								oErrorShare.setDate_contract_verified(FechaError);
								/* actualizamos datos error de operativa */
								ShareLocalServiceUtil.updateShare(oErrorShare);
							
							}
								
							
							/* fin actualizamos datos error de operativa */
						}
						
						}
					} // fin one <> -1										
		
			}
		}		
		else // fin errores operativa - lectura. Conectividad 
		{
			error =   str + "[N. Error " + two + "]";
			
		}
		
	}
	public void error(String str) {
		
		_log.info("error consoleTWS1: [" + str + "]");
	}
	
	public void GITraderConnetToTWS() throws InterruptedException {
		
		//! [connect]
		//! [ereader]
		
		clientSocket.eConnect(_ConnectionHOST, _ConnectionPORT, _ConnectionCLIENTID);
		
		EReader reader2 = new EReader(clientSocket, readerSignal);   
		
		reader2.start();
		
		
		//An additional thread is created in this program design to empty the messaging queue
		new Thread(() -> {
		    while (clientSocket.isConnected()) {
		    	readerSignal.waitForSignal();
		        try {
		            reader2.processMsgs();
		        } catch (Exception e) {
		        	_log.info("Exception connection to TWS: "+e.getMessage());
		        }
		    }
		}).start();
		
		//TIMApiclientSocket.eConnect(_ConnectionHOST, _ConnectionPORT, _ConnectionCLIENTID);

		
	}
	
	
	
	public void GITraderDisconnectFromTWS() throws InterruptedException {
		clientSocket.eDisconnect();

	}
	public boolean GITraderTWSIsConnected() throws InterruptedException {
		return clientSocket.isConnected();
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		//! [connect]
		//! [ereader]
		//final EReader reader = new EReader(this.getClient(), m_signal);
	
		TIMApiWrapper wrapper = new TIMApiWrapper();
		
		
		final EClientSocket m_client = wrapper.getClient();
		final EReaderSignal m_signal = wrapper.getSignal();
		//! [connect]
		m_client.eConnect("127.0.0.1", 7497, 3);
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

		Contract  _contractAPI3 =  new StkContract("AAPL");
		_contractAPI3.symbol("AAPL");
		_contractAPI3.secType("STK");
		_contractAPI3.exchange("ISLAND");
		_contractAPI3.currency("USD");
		
		
		
	/* 	Contract contract = new Contract();
		contract.symbol("DAX");
		contract.secType("FUT");
		contract.currency("EUR");
		contract.exchange("DTB");
		contract.lastTradeDateOrContractMonth("201709");
		contract.multiplier("5");
		*/
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
		String formatted = form.format(cal.getTime());
		/* m_client.reqHistoricalData(4001, _contractAPI3, formatted, "1 M", "1 day", "MIDPOINT", 1, 1, false, null);
		m_client.reqHistoricalData(4002, _contractAPI3, formatted, "10 D", "1 min", "TRADES", 1, 1, false, null); */
		Thread.sleep(2000);
		/*** Canceling historical data requests ***/
		m_client.cancelHistoricalData(4001);
		m_client.cancelHistoricalData(4002);
		//! [reqhistoricaldata]
			
		
		

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

		Thread.sleep(100000);
		m_client.eDisconnect();

		
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
	public static String get_CONTRACT_DATA_REQUEST() {
		return _CONTRACT_DATA_REQUEST;
	}


	public static void set_CONTRACT_DATA_REQUEST(String _CONTRACT_DATA_REQUEST) {
		TIMApiGITrader._CONTRACT_DATA_REQUEST = _CONTRACT_DATA_REQUEST;
	}


	@Override
	public void tickPrice(int tickerId, int field, double price, int canAutoExecute) {
		IBOrder MyOrder = null;
	
		if (field==ConfigKeys._TICKTYPE_LAST) {

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
	public void orderStatus(int orderId, String status, int filled, int remaining, double avgFillPrice, int permId,
			int parentId, double lastFillPrice, int clientId, String whyHeld) {
		// TODO Auto-generated method stub
		
		
	}	
	 
		
	
	


}
