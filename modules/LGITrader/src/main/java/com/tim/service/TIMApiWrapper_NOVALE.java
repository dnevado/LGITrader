package com.tim.service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Priority;

import com.ib.client.Bar;
import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDescription;
import com.ib.client.ContractDetails;
import com.ib.client.DeltaNeutralContract;
import com.ib.client.DepthMktDataDescription;
import com.ib.client.EClientSocket;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
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
import com.ib.client.TickType;
import com.mchange.v2.log.MLevel;
import com.mchange.v2.log.log4j.Log4jMLog;
import com.tim.dao.MarketDAO;
import com.tim.dao.MarketShareDAO;
import com.tim.dao.OrderDAO;
import com.tim.dao.RealTimeDAO;
import com.tim.dao.PositionDAO;
import com.tim.dao.ShareDAO;
import com.tim.model.Market;
import com.tim.model.Position;
import com.tim.model.RealTime;
import com.tim.model.Share;
import com.tim.util.ConfigKeys;
import com.tim.util.LogTWM;
import com.tim.util.PositionStates;
import com.tim.util.TWSMail;
import com.tim.util.Utilidades;

/**
 * Base class providing default implementation of all EWrapper methods.
 * 
 * $Id$
 */
public class TIMApiWrapper_NOVALE extends Thread implements EWrapper {

	protected EClientSocket eClientSocket = new EClientSocket(this);

	protected String TWS_HOST = "localhost";
	protected int TWS_PORT = 7496;
	protected int TWS_CLIENT_ID = 2; // creo que 2 es la lectura, 3 operativa y
										// 4 cancelar posicion
	protected final static int MAX_WAIT_COUNT = 15; // 15 secs
	protected final static int WAIT_TIME = 1000; // 1 sec
	private double lastPrice = 0.0;
	public int LastPositionID = 0;
	public int lastOrderID = 0;
	
	
	/* VALORES INTERMEDIOS POR QUE NO SE LA FECHA HASTA LA SEGUNDA LLAMADA AL HISTORICALDATA*/
	/* 1. PRIMERO ENVIA LOS VALORES */
	/* 1. SEGUNDO ENVIA LOS TRAMOS */
	
	public static double _HISTORICAL_DATA_LOW=0;
	public  static double _HISTORICAL_DATA_HIGH=0;

	public  static String  _HISTORICAL_DATA_REQUEST="";   // OK acabada.
	
	public  static String  _HISTORICAL_MODE_REQUEST="REAL";   // REAL, SIMULATION
	
	public  static String  _CONTRACT_DATA_REQUEST="Stopped";   // [Stopped,Requested, Ended, Received,Executing]
	
	/* USAMOS PARA ALMACENAR LAS TRAZAS DE RESULTADOS POR BLOQUES Y BUSCAR EL MAXIMO Y MIN DESPUES */
	public  ArrayList<Double>aLowValuesHistorical =  new ArrayList();
	public ArrayList<Double>aHighValuesHistorical =  new ArrayList();
	
	
	/* FECHA ESTIMATIVA, NO SIRVE  COMO INFORMATIVA */
	public static String _HISTORICAL_DATA_DATE  = "";
	
	public static String _HISTORICAL_DATA_DATE_BEGINNING  = "";
	
	
	/* ESTE DATO SIRVE PARA ALMACENAR DATOS HISTORICOS DE LA FECHA EN CUESTION 
	 * LA TWS DEVUELVE DATOS ANTERIORES A UNA FECHA DADA, NO ENTRE FECHAS, CON LO QUE SI PIDES UN DOMINGO TE DARA 
	 * DEL VIERNES */
	private static String _DATE_HISTORICAL_REQUEST = "";  //yyyymmdd  
	
	
	private static String _HISTORICAL_DATA_ERRORS_CODES = "";
	

	public static String get_HISTORICAL_DATA_ERRORS_CODES() {
		return _HISTORICAL_DATA_ERRORS_CODES;
	}

	public static void set_HISTORICAL_DATA_ERRORS_CODES(
			String _HISTORICAL_DATA_ERRORS_CODES) {
		TIMApiGITrader._HISTORICAL_DATA_ERRORS_CODES = _HISTORICAL_DATA_ERRORS_CODES;
	}

	public static String get_DATE_HISTORICAL_REQUEST() {
		return _DATE_HISTORICAL_REQUEST;
	}

	public static void set_DATE_HISTORICAL_REQUEST(String _DATE_HISTORICAL_REQUEST) {
		TIMApiGITrader._DATE_HISTORICAL_REQUEST = _DATE_HISTORICAL_REQUEST;
	}

	DecimalFormat Decimales = null;

	private String error = "";

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	private static Contract oContrat = null;// guardamos el ultimo contract que
											// pedimos de tiempo real para pa

	// pasarlo al open order

	/*
	 * protected void TIMApiWrapper() {
	 * 
	 * 
	 * }
	 */
	public TIMApiWrapper_NOVALE() {
	}

	public TIMApiWrapper_NOVALE(int ClientID) {
		TWS_CLIENT_ID = ClientID;
	}

	public TIMApiWrapper_NOVALE(String _TWS_HOST, int _TWS_PORT, int _TWS_CLIENT_ID) {
		TWS_HOST = _TWS_HOST;
		TWS_PORT = _TWS_PORT;
		TWS_CLIENT_ID = _TWS_CLIENT_ID;

	}

	public  void reqHistoricalData(int tickerId, Contract contract,
			String endDateTime, String durationStr, String barSizeSetting,
			String whatToShow, int useRTH, int formatDate) {
		
		_HISTORICAL_DATA_REQUEST = "Executing";
		
		this.eClientSocket.reqHistoricalData(tickerId, contract, endDateTime,
				durationStr, barSizeSetting, whatToShow, useRTH, formatDate);

	}

	public void connectToTWS() {
		eClientSocket.eConnect(TWS_HOST, TWS_PORT, TWS_CLIENT_ID);
	}

	public void disconnectFromTWS() {
		if (eClientSocket.isConnected()) {
			eClientSocket.eDisconnect();
		}
	}

	protected Order createOrder(String action, int quantity, String orderType) {
		Order order = new Order();

		order.m_action = action;
		order.m_totalQuantity = quantity;
		order.m_orderType = orderType;
		// order.m_transmit = true;

		return order;
	}

	public Contract createContract(String symbol, String securityType,
			String exchange, String currency) {
		return createContract(symbol, securityType, exchange, currency, "",
				"", 0.0);

	}

	public Contract createContract(String symbol, String securityType,
			String exchange, String currency, String expiry, String right,
			double strike) {
		Contract contract = new Contract();

		contract.m_symbol = symbol;
		contract.m_secType = securityType;
		contract.m_exchange = exchange;
		contract.m_currency = currency;
		
		

		if (expiry != null) {
			contract.m_expiry = expiry;
		}

		if (strike != 0.0) {
			contract.m_strike = strike;
		}

		if (right != null) {
			contract.m_right = right;
		}

		
/* 		LogTWM.getLog(TIMApiWrapper.class);
		LogTWM.log(Priority.ERROR, "Contract:" +contract.m_symbol + "|" + contract.m_secType  
				+ "|" + contract.m_exchange
				+ "|" + contract.m_currency
				+ "|" + contract.m_expiry
				+ "|" + contract.m_expiry);*/
		
		return contract;
	}

	public void tickPrice(int tickerId, int field, double price,
			int canAutoExecute) {

		
		com.tim.model.Order MyOrder = null;

		switch (field) {
		case TickType.CLOSE: {
			
			if (MyOrder == null) {
				LogTWM.getLog(TIMApiGITrader.class);
				LogTWM.log(Priority.ERROR, "No se encuentra el ID " + tickerId);
				return;
			}
			saveDataLastRealTime(MyOrder.getShareID().intValue(), "close_value", price); 
			break;
		}
		case TickType.ASK: {
			// MyLog.log(Priority.INFO, "type:" + field + " Last Price for :" +
			// tickerId + ":" + field + ": ASK");
			break;
		}
		case TickType.BID: {
			// MyLog.log(Priority.INFO, "type:" + field + " Last Price for :" +
			// tickerId + ":" + field + ": BID");
			break;
		}
		/*
		 * case TickType.HIGH: { MyOrder = OrderDAO.getOrder(tickerId); if
		 * (MyOrder==null) { MyLog.log(Priority.INFO, "No se encuentra el ID "
		 * +tickerId ); return; }
		 * saveDataLastRealTime(MyOrder.getShareID().intValue(), "max_value",
		 * price); break; }
		 */

		/*
		 * supuestamente el low y el high no los da la primera vez..prefiero
		 * calcularlo yo
		 */

		/*
		 * case TickType.LOW: { MyOrder = OrderDAO.getOrder(tickerId); if
		 * (MyOrder==null) { MyLog.log(Priority.INFO, "No se encuentra el ID "
		 * +tickerId ); return; }
		 * saveDataLastRealTime(MyOrder.getShareID().intValue(), "min_value",
		 * price); break; }
		 */
		/*
		 * case TickType.VOLUME: { //MyLog.log(Priority.INFO, "type:" + field +
		 * " Last Price for :" + tickerId + ":" + field + ": Closed"); break; }
		 */
		case TickType.AVG_VOLUME: {
			// MyLog.log(Priority.INFO, "type:" + field + " Last Price for :" +
			// tickerId + ":" + field + ": Closed");
			break;
		}
		case TickType.LAST: {

			/*
			 * HAY CAMBIO DE TICK ??? VERIFICAMOS SI HAY ACCION DEL MODELO.
			 */

			

			MyOrder = OrderDAO.getOrder(tickerId);
			if (MyOrder == null) {
				LogTWM.getLog(TIMApiGITrader.class);
				LogTWM.log(Priority.ERROR, "No se encuentra el ID " + tickerId);
				return;
			}

			//
			/*if (MyOrder.getShareID().intValue()==3 && price<400) // AP PL
			{
					LogTWM.getLog(TIMApiWrapper.class);
					LogTWM.log(Priority.INFO, "ShareId:" + MyOrder.getShareID().intValue() + "," + "tickerId:" + tickerId + ":" + field + ":" + price);	
			}*/
			
			RealTimeDAO oRealDAO = new RealTimeDAO();
			oRealDAO.addRealTime(MyOrder.getShareID().intValue(), price);
			//Share Mishare = ShareDAO.getShare(MyOrder.getShareID());
			OrderDAO.updateStatusOrder(tickerId, 1);

		}

		}
	}

	public void tickSize(int tickerId, int field, int size) {

		com.tim.model.Order MyOrder = null;

		switch (field) {
		case TickType.VOLUME: {
			/* MyOrder = OrderDAO.getOrder(tickerId);
			if (MyOrder == null) {
				LogTWM.getLog(TIMApiWrapper.class);
				LogTWM.log(Priority.ERROR, "No se encuentra el ID " + tickerId);

				return;
			}
			saveDataLastRealTime(MyOrder.getShareID().intValue(), "volume",
					size);*/
			break;
		}
		case TickType.AVG_VOLUME: {
			/* MyOrder = OrderDAO.getOrder(tickerId);
			if (MyOrder == null) {
				LogTWM.getLog(TIMApiWrapper.class);
				LogTWM.log(Priority.ERROR, "No se encuentra el ID " + tickerId);
				return;
			}
			saveDataLastRealTime(MyOrder.getShareID().intValue(), "avg_volume",
					size);*/
			break;
		}
		}

	}

	public void tickString(int tickerId, int field, String value) {
		// System.out.println(" [API.msg1] tickString" + tickerId + "|" + field
		// + "|" + value + "|");
	}

	public void tickGeneric(int tickerId, int field, double generic) {
		// System.out.println(" [API.msg1] tickGeneric" + tickerId + "|" + field
		// + "|" + generic + "|");
	}

	public void tickEFP(int tickerId, int field, double basisPoints,
			String formattedBasisPoints, double totalDividends, int holdDays,
			String futureExpiry, double dividendImpact, double dividendsToExpiry) {
		// System.out.println(" [API.msg1] tickEFP ");
	}

	public void tickOptionComputation(int tickerId, int field,
			double impliedVol, double delta, double modelPrice,
			double pvDividend) {
		// System.out.println(" [API.msg1] tickOptionComputation ");
	}

	public void error(String str) {
		LogTWM.getLog(TIMApiGITrader.class);
		LogTWM.log(Priority.FATAL, "error consoleTWS1: [" + str + "]");
	}

	public void error(int one, int two, String str) {

		
		/* LogTWM.getLog(TIMApiWrapper.class);
		LogTWM.log(Priority.FATAL, "error lectura : [" + one + "," + two
				+ "," + str + "]");
		*/
		
		/* 1. LECTURA DE DATOS --> INFORMACION ERROR, INTRODUCIMOS OBSERVACIONES 
		 * 2. ORDENES , OPEN ORDERS...CLOSE ORDERS */
		
		
		if (two>=0 && one>=0)  // errores operativa - lectura
		{
			
		java.sql.Timestamp FechaError = new Timestamp(Calendar.getInstance().getTimeInMillis());	    			
		
		SimpleDateFormat sdf2 = new SimpleDateFormat();
		sdf2.applyPattern("dd-MM-yyyy HH:mm:ss");
		 
		Position _ErrorPosition = null;
		_ErrorPosition = PositionDAO.getPosition(one);
		
		Share oErrorShare = null;
		
		if (_ErrorPosition!=null)  // error en una posicion dada
		{
			oErrorShare = ShareDAO.getShare(_ErrorPosition.getShareID());
			
			if (_ErrorPosition.getPrice_sell()!=null && _ErrorPosition.getPrice_sell().compareTo(new Double(0))>0) 
			{
				// INTENTO DE salir --> NO SABEMOS LO QUE HACER
			}
			else   // entrada???.. En ppio se quedan los errores gestionados en el orderstatus.
			{
				
			  	LogTWM.getLog(TIMApiGITrader.class);
				LogTWM.log(Priority.FATAL, "error operativa : [" + one + "," + two
						+ "," + str + "]");
				LogTWM.log(Priority.FATAL, "error order  : " + oErrorShare.getSymbol());
				oErrorShare.setActive_trading(new Long(0));  // desactivamos trading.
				//Actualizamos campos de errores.
				oErrorShare.setLast_error_data_trade(FechaError + "|" +  str + "[N. Error " + two + "]");  // desactivamos trading.
				
				
				_ErrorPosition.setPending_cancelled(1);  // supuestamente se cancela SOLA.
				PositionDAO.deletePositionByPositionID(_ErrorPosition);

				/* actualizamos datos error de operativa */
				ShareDAO.updateShare(oErrorShare);
				/* fin actualizamos datos error de operativa */
				
				// Actualizamos estado de la posición con Cancelled o similar
				// Sería interesante que apareciera en la consola pero puede dar problemas (Gris???)
			
			}
			
				
		}
		else   // supuestamente error lectura de datos en tiempo real.
			   // METEMOS EL 25-12-2013 un  job verificador que pide el contract detail con variable de estado asociada.
			
		{
			
			LogTWM.getLog(TIMApiGITrader.class);
			LogTWM.log(Priority.FATAL, "error lectura [HISTORICAL_DATA: " + _HISTORICAL_DATA_REQUEST + "] : [" + one + "," + two
					+ "," + str + "]");
			

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
				
				
				com.tim.model.Order _ErrorOrder = OrderDAO.getOrder(one);
				if (_ErrorOrder!=null)
				{
					
					
					
					oErrorShare = ShareDAO.getShare(_ErrorOrder.getShareID());
					
					
					if (oErrorShare!=null 
							&& (oErrorShare.getSecurity_type().equalsIgnoreCase(ConfigKeys.SECURITY_TYPE_FUTUROS)
							   || _HISTORICAL_DATA_REQUEST.equals("Executing")  
							   		|| _CONTRACT_DATA_REQUEST.equals("Requested") 
							   			|| _CONTRACT_DATA_REQUEST.equals("Executing")))
					{
					
					
						LogTWM.log(Priority.FATAL, "error order  : " + oErrorShare.getSymbol());
						
						
						// DESACTIVAMOS SIEMPRE QUE NO SEA POR PACING VIOLATIONS
						//162	Historical market data Service error message.		
						if (two!=162)
						{
							
							oErrorShare.setActive(new Long(0));  // desactivamos lectura.
							oErrorShare.setLast_error_data_read("[" + oErrorShare.getSymbol() + "]" + FechaError + "|" + str + "[N. Error " + two + "]");  // desactivamos
							oErrorShare.setDate_contract_verified(FechaError);
							/* actualizamos datos error de operativa */
							ShareDAO.updateShare(oErrorShare);
						
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

	public void error(Exception e) {
		LogTWM.getLog(TIMApiGITrader.class);
		LogTWM.log(Priority.FATAL, "error consoleTWS3: [" + e.getMessage() + "]");
	}

	public void connectionClosed() {
		LogTWM.getLog(TIMApiGITrader.class);
		LogTWM.log(Priority.FATAL, "error consoleTWS4: [connectionClosed]");
	}

	public void execDetails(int orderId, Contract contract, Execution execution) {
		
		
		
	}
	
	/* id=1 date = 20131023  19:15:00 open=5.04 high=5.05 low=4.88 close=4.94 volume=33 count=31 WAP=4.973 hasGaps=false
			id=1 date = 20131023  19:20:00 open=4.99 high=5.26 low=4.99 close=5.19 volume=44 count=40 WAP=5.163 hasGaps=false
			id=1 date = 20131023  19:25:00 open=5.2 high=5.21 low=5.07 close=5.14 volume=27 count=24 WAP=5.148 hasGaps=false
			id=1 date = 20131023  19:30:00 open=5.14 high=5.14 low=5.14 close=5.14 volume=0 count=0 WAP=5.14 hasGaps=false
			id=1 date = finished-20131023  19:15:01-20131023  19:30:01 o
   */
	
	/* PARA DATOS HISTORICOS DE VARIOS DIAS, PUEDE SER QUE ME DE DATOS DEL DIA ANTERIOR EN CASO DE QUE SEA LA PRIMERA HORA O UN SABADO
	 * PROVOCANDO DUPLICIDADES, CONTROLO QUE LO SOLICITADO, ES DE LA MISMA FECHA PEDIDA */
	
	public void historicalData(int reqId, String date, double open,
			double high, double low, double close, int volume, int count,
			double WAP, boolean hasGaps) {
		
		 //LogTWM.getLog(TIMApiWrapper.class); 
		 //LogTWM.log(Priority.INFO, "if( IsEndOfHistoricalData(date))  USAR HstoricalData:" + reqId + " daTE =" + date + " HIGH=" + high + " LOW=" + low);

		// LogTWM.log(Priority.INFO, "date, " + date + ",historicalData:low" + low + ",high:" + high + ",date:"  + date);
		 /* AQUI NO ME DA EL RANGO DEFINIDO DE HORAS POR LAS  QUE PEDI EL HISTORICAL DATA */
		 
		 com.tim.model.Order MyOrder = OrderDAO.getOrder(reqId);
		 RealTimeDAO oRealDAO = new RealTimeDAO();
		 
		 SimpleDateFormat sdfD = new SimpleDateFormat();
		 sdfD.applyPattern("yyyyMMdd");
		 
		 String  FeedDate = "";
		 
		 try {
			 FeedDate= sdfD.format(sdfD.parse(date));					 
			} 
		 catch (ParseException e) {}
		 
		 /* SI LA FECHA NO COINCIDE, NO SE CARGA */
		 
		if (!_DATE_HISTORICAL_REQUEST.equals("")  && !FeedDate.equals("") &&  !FeedDate.equals(_DATE_HISTORICAL_REQUEST))
				 return;
				 
		 
		 
		 if (MyOrder==null)
		 {
			 LogTWM.log(Priority.INFO, "historicalData:" + reqId + " OrderId not found ");
		 }
		 else   // DISTINGUIMOS MODO REAL (TIEMPO REAL EN UN TRAMO DADO)  / SIMULADO (CARGAMOS TODOS)
		 {
			 
			 
			   if (_HISTORICAL_MODE_REQUEST.equals("SIMULATED"))  // modo simulado, obtencion del historico para simulacion del modelo
			   {
					   if (!date.toLowerCase().contains("finished"))
					   {
				   
						  // RealTimeDAO oRealDAO = new RealTimeDAO();
						   
						   

						   
						   String[] _aDate2 = date.split(" ");
						   String _Date2Date = _aDate2[0].trim();
						   String _Date2Time = _aDate2[2].trim();
						   Calendar DateRealTime =  Calendar.getInstance();
						   
						   
						//   LogTWM.log(Priority.INFO, date + "/" + _Date2Date + "-" +_Date2Time  + ".." + _Date2Date.substring(0, 4));
						   
						  // LogTWM.log(Priority.INFO, _Date2Date.substring(0, 4) + "-" + _Date2Date.substring(4, 6) + "-" + _Date2Date.substring(6, 8));
						   
						   DateRealTime.set(Calendar.YEAR, Integer.valueOf(_Date2Date.substring(0, 4)).intValue());
						   DateRealTime.set(Calendar.MONTH, Integer.valueOf(_Date2Date.substring(4, 6)).intValue()-1);
						   DateRealTime.set(Calendar.DAY_OF_MONTH, Integer.valueOf(_Date2Date.substring(6, 8)).intValue());
						   
						   DateRealTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(_Date2Time.substring(0, 2)).intValue());
						   DateRealTime.set(Calendar.MINUTE,  Integer.valueOf(_Date2Time.substring(3, 5)).intValue());
						   DateRealTime.set(Calendar.SECOND, Integer.valueOf(_Date2Time.substring(6, 8)).intValue());
						   
						   /* COGEMOS CLOSE */


						   oRealDAO.addSimulationRealTime(MyOrder.getShareID().intValue(), close, new Timestamp(DateRealTime.getTimeInMillis()));
				   				  
					   }
					   else // finished simulated mode
					   {
						   
						   _HISTORICAL_DATA_REQUEST = "OK";
						   
					   }
			   }
			   else  // modo real, tiempo real actual.
			   {
				   //
				   /* COMO TENGO MAXIMO Y MINIMO, INTRODUZCO DOS COMO VALUE */
				   /* SI VIENE finished en la fecha, no los meto, pk es como la marca del final  */
				   if (!date.toLowerCase().contains("finished"))
				    {	
				    	
		//		    	LogTWM.log(Priority.INFO, "date, " + date + ",historicalData:low" + low + ",high:" + high + ",date:"  + date);
				    	
				    	aLowValuesHistorical.add(low);
				    	aHighValuesHistorical.add(high);
				    	
				    	/* _HISTORICAL_DATA_LOW = low;
				    	_HISTORICAL_DATA_HIGH = high; */
				    	
				    	/* COGEMOS LA FECHA INICIAL */
				    	if (_HISTORICAL_DATA_DATE == null || _HISTORICAL_DATA_DATE.equals(""))
				    		_HISTORICAL_DATA_DATE_BEGINNING = date;
				    	
				    	
				    	_HISTORICAL_DATA_DATE = date;
				    	
						
				    }
				   else // sabemos la fecha final			    	
				    {
				    	/* finished-yyyymmdd   19:10:00-yyyymmdd   19:10:00 */
				    	//RealTimeDAO oRealDAO = new RealTimeDAO();
						/* HIGH
						String[] _DataDates = date.split("-"); 
						
						if (_DataDates.length>2)
						{	
							 */
							/* 20130926  19:10:00*/	
				    	
				    	   LogTWM.log(Priority.INFO, "date, " + date + ",historicalData:low" + low + ",high:" + high + ",date:"  + date);
				    	
							String[] _aDate2 = _HISTORICAL_DATA_DATE.split(" ");
							
							String _Date2Date = _aDate2[0].trim();
							String _Date2Time = _aDate2[2].trim();
							
							Calendar DateFromRealTime =  Calendar.getInstance();
							
							
							/* COGEMOS EL PRINCIPIO DEL RANGO TAMBIEN */
							String[] _aDate1 = date.split(" ");
							
							String _Date1Date = _aDate1[0].trim();
							String _Date1Time = _aDate1[2].trim();
							
							Calendar DateToRealTime =  Calendar.getInstance();
							
							LogTWM.log(Priority.INFO, "_HISTORICAL_DATA_DATE_BEGINNING" + _HISTORICAL_DATA_DATE_BEGINNING + ",_HISTORICAL_DATA_DATE:" + _HISTORICAL_DATA_DATE);
							//LogTWM.log(Priority.INFO, "add);
							
							/* TENEMOS UN PROBLEMA CON EL AÑO DEL CALENDAR..ME METE 2020. COMO METEMOS SIEMPRE EL ACTUAL
							 * PUES LO DEJAMOS SIN COGER LO QUE VENGA DEL HISTORICAL DATA 
								
							DateFromRealTime.set(Calendar.MONTH, Integer.valueOf(_Date1Date.substring(5, 7)));
							DateFromRealTime.set(Calendar.DAY_OF_MONTH, Integer.valueOf(_Date1Date.substring(6, 7)));*/
							DateFromRealTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(_Date2Time.substring(0, 2)));
							DateFromRealTime.set(Calendar.MINUTE, Integer.valueOf(_Date2Time.substring(3, 5)));
							DateFromRealTime.set(Calendar.SECOND, Integer.valueOf(_Date2Time.substring(6, 7)));
																		
							 
							DateToRealTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(_Date1Time.substring(0, 2)));
							DateToRealTime.set(Calendar.MINUTE, Integer.valueOf(_Date1Time.substring(3, 5)));
							DateToRealTime.set(Calendar.SECOND, Integer.valueOf(_Date1Time.substring(6, 7))); 
							 
							
							
							
	/* 						Date DateFromRealTime = new Date(_Date1Date.substring(0, 4), _Date1Date.substring(5, 7), 
									_Date1Date.substring(6, 7), */
									
							
							LogTWM.log(Priority.INFO, "Min Value :" + Utilidades.GetTickMaxMinValueFromArray(aLowValuesHistorical, ConfigKeys.IDENTITY_VALUE_TYPE.MIN));
							oRealDAO.addRealTime(MyOrder.getShareID().intValue(),Utilidades.GetTickMaxMinValueFromArray(aLowValuesHistorical, ConfigKeys.IDENTITY_VALUE_TYPE.MIN), new Timestamp(DateToRealTime.getTimeInMillis()));
							
							LogTWM.log(Priority.INFO, "Max Value :" + Utilidades.GetTickMaxMinValueFromArray(aHighValuesHistorical, ConfigKeys.IDENTITY_VALUE_TYPE.MAX));
							/* LOW */
							oRealDAO.addRealTime(MyOrder.getShareID().intValue(), Utilidades.GetTickMaxMinValueFromArray(aHighValuesHistorical, ConfigKeys.IDENTITY_VALUE_TYPE.MAX), new Timestamp(DateFromRealTime.getTimeInMillis()));
							//Share Mishare = ShareDAO.getShare(MyOrder.getShareID());
							OrderDAO.updateStatusOrder(reqId, 1);
							
						  	//LogTWM.log(Priority.INFO, "historicalData:" + Utilidades.GetTickMaxMinValueFromArray(aLowValuesHistorical, ConfigKeys.IDENTITY_VALUE_TYPE.MIN) + "," + DateFromRealTime + "|" + Utilidades.GetTickMaxMinValueFromArray(aHighValuesHistorical, ConfigKeys.IDENTITY_VALUE_TYPE.MAX) + "," + DateToRealTime);
							
							
							_HISTORICAL_DATA_REQUEST = "OK";
					/* 	} */
						
						
						
				    	
				    }  // fin sabemos la fecha final, insertamos el tramo.
				
			   }  // modo real, tiempo real actual.

			 
		 }

			 
		 
		 

	}

	public void managedAccounts(String accountsList) {
	}

	public void nextValidId(int orderId) {
		// MyLog.log(Priority.INFO,"NextValid ID:" + orderId);
		LastPositionID = orderId;
	}

	public boolean isConnected() {
		return (eClientSocket.isConnected());
	}

	public void openOrder(int orderId, Contract contract, Order order) {
		
		 LogTWM.getLog(TIMApiGITrader.class); 
		 LogTWM.log(Priority.INFO, "Open Order:" + orderId + " Contrato =" + contract.m_symbol + " order=" + order.m_totalQuantity + " price=" + order.m_lmtPrice + " auxprice=" + order.m_auxPrice);
		 

		this.eClientSocket.placeOrder(orderId, contract, order);

	}

	/*
	 * PUEDEN LLEGAR VARIOS CALL BACK...TRATAR CADA UNO.
	 * 
	 * DE MOMENTO CONTROLAMOS LAS COMPRAS. LA VENTA SERÁ OTRA ESTRATEGIA.
	 * 
	 * orderid se corresponde al campo positionid. que es donde tenemos las
	 * posiciones
	 */

	public void receiveFA(int faDataType, String xml) {
	}

	public void scannerData(int reqId, int rank,
			ContractDetails contractDetails, String distance, String benchmark,
			String projection, String legsStr) {
	}

	public void scannerParameters(String xml) {
	}

	public void updateAccountTime(String timeStamp) {
	}

	public void updateAccountValue(String key, String value, String currency) {
	}

	public void updateNewsBulletin(int msgId, int msgType, String message,
			String origExchange) {
	}

	public void updateAccountValue(String key, String value, String currency,
			String accountName) {
	}

	public void updateMktDepth(int tickerId, int position, int operation,
			int side, double price, int size) {
	}

	public void updateMktDepthL2(int tickerId, int position,
			String marketMaker, int operation, int side, double price, int size) {
	}

	public void updatePortfolio(Contract contract, int position,
			double marketPrice, double marketValue, double averageCost,
			double unrealizedPNL, double realizedPNL, String accountName) {
	}

	public void updatePortfolio(Contract contract, int position,
			double marketPrice, double marketValue, double averageCost,
			double unrealizedPNL, double realizedPNL) {
	}

	/*
	 * public void orderStatus(int orderId, String status, int filled, int
	 * remaining, double avgFillPrice, int permId, int parentId, double
	 * lastFillPrice, int clientId, String whyHeld) {
	 * 
	 * MyLog.log(Priority.INFO,"order status: orderId=" + orderId + " clientId="
	 * + clientId + " permId=" + permId + " status=" + status + " filled=" +
	 * filled + " remaining=" + remaining + " avgFillPrice=" + avgFillPrice +
	 * " lastFillPrice=" + lastFillPrice + " parent Id=" + parentId +
	 * " whyHeld=" + whyHeld);
	 * 
	 * }
	 */

	public void openOrder(int orderId, Contract contract, Order order,
			OrderState orderState) {

	}

	public void contractDetails(int reqId, ContractDetails contractDetails) {

		
		_CONTRACT_DATA_REQUEST = "Received";
		
		LogTWM.getLog(TIMApiGITrader.class);
		LogTWM.log(Priority.INFO,"contractDetails: " + contractDetails.m_tradingClass);
		
		//this.oContrat = contractDetails;
		
	}

	public void bondContractDetails(int reqId, ContractDetails contractDetails) {
	}

	
	/* OJO, LOS FUTUROS, NO COMTEMPLAN LA VERIFICACION DE LA FECHA DE EXPIRACION YA
	 * QUE AMBOS CONTRATOS (INDEPENDIENTEMENTE DE LA FECHA, SON VALIDOS 
	 */
	
	public void contractDetailsEnd(int reqId) {
		
		
		_CONTRACT_DATA_REQUEST = "Ended";
		
		 LogTWM.getLog(TIMApiGITrader.class);
		LogTWM.log(Priority.INFO,"contractDetailsEnd: " + reqId);
		
		com.tim.model.Order _Order = OrderDAO.getOrder(reqId);
		Share ContractShare = ShareDAO.getShare(_Order.getShareID());
		ContractShare.setDate_contract_verified(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		/* ACTUALIZAMOS CON LA FECHA DE COMPROBACION OK */		
		ShareDAO.updateShare(ContractShare);
		
		

	}

	public void scannerDataEnd(int reqId) {
	}

	public void realtimeBar(int reqId, long time, double open, double high,
			double low, double close, long volume, double wap, int count) {
	}

	public void currentTime(long time) {
	}

	public void fundamentalData(int reqId, String data) {
	}

	/* METODOS PERSONALIZADOS QUE NO ESTAN EN EL INTERFACE */
	public boolean getContractDetails(int RequestID, Contract contract)
			throws InterruptedException {
		
		_CONTRACT_DATA_REQUEST = "Executing";
		
		eClientSocket.reqContractDetails(RequestID, contract);
		return true;
	}

	/* METODOS PERSONALIZADOS QUE NO ESTAN EN EL INTERFACE */
	public boolean cancelOrder(int RequestID) throws InterruptedException {
		eClientSocket.cancelOrder(RequestID);
		return true;
	}

	public void getRealTimeContract(int RequestID, Contract contract)
			throws InterruptedException {

		boolean isSuccess = false;
		int waitCount = 0;
		// System.out.println("Consultado (" +RequestID + ")" +
		// contract.m_exchange + "|" + contract.m_symbol);

		eClientSocket.reqMktData(RequestID, contract, "", false);

		this.oContrat = contract;

	}

	protected static int getUniqueOrderID() {

		String IDTime = "" + Calendar.getInstance().get(Calendar.HOUR);
		IDTime += "" + Calendar.getInstance().get(Calendar.MINUTE);
		IDTime += "" + Calendar.getInstance().get(Calendar.SECOND);
		IDTime += "" + Calendar.getInstance().get(Calendar.MILLISECOND);
		/*
		 * Calendar.getInstance().get(Calendar.MINUTE) +
		 * Calendar.getInstance().get(Calendar.SECOND) +
		 * Calendar.getInstance().get(Calendar.MILLISECOND)
		 */
		int _IDTime = Integer.parseInt(IDTime);

		return _IDTime;

	}

	/*
	 * ESTO PARA LOS CASOS DE VOLUMEN, HIGH, LOW, AVG VOLUMNE
	 * 
	 * CREO QUE LOS HIGH Y LOW ES LA PRIMERA VEZ
	 */
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

	@Override
		public synchronized void orderStatus(int orderId, String status, int filled,
				int remaining, double avgFillPrice, int permId, int parentId,
				double lastFillPrice, int clientId, String whyHeld) {
			


		/* LogTWM.getLog(TIMApiWrapper.class);
		LogTWM.log(Priority.INFO,"order status: orderId=" + orderId + " clientId=" + clientId + " permId=" + permId +
	    	        " status=" + status + " filled=" + filled + " remaining=" + remaining +
	    	        " avgFillPrice=" + avgFillPrice + " lastFillPrice=" + lastFillPrice +
	    	        " parent Id=" + parentId +  "|" + whyHeld );
	    	*/
	    //	MyLog.log(Priority.INFO,"Hola1");
			
			java.sql.Timestamp FechaError = new Timestamp(Calendar.getInstance().getTimeInMillis());	    			
		
			SimpleDateFormat sdf2 = new SimpleDateFormat();
			sdf2.applyPattern("dd-MM-yyyy HH:mm:ss");
		 
        	
			double priceStopLost = 0;
			double priceStopProfit = 0;
			
			DecimalFormat sNumero = new DecimalFormat();
			sNumero.applyPattern("######,##");
			
			boolean bIsSellOperation = false;  //ENTRADA O SALIDA, TERMINO ERRONEO
			boolean isDelete = false;   // cuando sea compra cancelada, nos la cargamos.
	    	
	    	try {
	    		
	    		Position MiPosicion= null;
	    		Share MySharePosition= null;
	    		
	    		MiPosicion=  (Position) PositionDAO.getPosition(orderId);
	    		
	    		// SI ES NULL, QUIERE DECIR QUE PUEDE VENIR UNA OPERACION DE VENTA...LA BUSCAMOS.
	    		if (MiPosicion==null)
	    		{    			
	    			
	    			MiPosicion=  (Position) PositionDAO.getPositionByIdSell(orderId);
	    			if (MiPosicion==null)
		    		{    				    		
	    				LogTWM.log(Priority.INFO,"Error Execution Details order not found for Order Key:" + orderId);
	    				return;
		    		}	
	    			else
	    				bIsSellOperation = true;
	    		}
	    		 
	    		MySharePosition= ShareDAO.getShare(MiPosicion.getShareID());
	    		
	    		/* DETECTAMOS SI ES UNA ORDER DE COMPRA O DE VENTA. VERIFICAMOS SI HA CAMBIADO . VERIFICAR SI HAY UNA ORDEN DE COMPRA PREVIA. */
	    		boolean changed = false;
	    		if (!bIsSellOperation)   //ENTRADA OPERATION
	    		{	
	    			if (MiPosicion.getState_buy()==null || (MiPosicion.getState_buy()!=null && !MiPosicion.getState_buy().toLowerCase().equals(status.toLowerCase())))
	    			changed = true;
	    		}
	    		else  // SALIDA OPERATION
	    		{
	    			if (MiPosicion.getState_sell()==null || (MiPosicion.getState_sell()!=null && !MiPosicion.getState_sell().toLowerCase().equals(status.toLowerCase())))
		    			changed = true;
	    		}
	    		
	    		
	    		if (changed)
	    		{	    			
	    			// controlamos todas las canceladas
	    			// LogTWM.log(Priority.INFO,"Change: " + changed + ",StateBuy, StateSell,status:" + MiPosicion.getState_buy() + "," +  MiPosicion.getState_sell()  +  "," + status);
	    			
	    			
	    			if (!bIsSellOperation)   // ENTRADA OPERATION ... CANCELLED? --> LA BORRAMOS PARA QUE NO CONSTE
	    			{
	    				

	    				
	    				MiPosicion.setState_buy(status);
	    				/* cancelada compra  */
	    				/* 21.09.2013 QUITO EL OR DE INACTIVE PARA CONTROLARLO EN LA RUTINA DE ERRORES 
	    				 * || 
	    						PositionStates.statusTWSCallBack.Inactive.toString().equals(status)
	    				 */
	    				if (PositionStates.statusTWSCallBack.Cancelled.toString().equals(status))
			    		{			    						    		
	    					
	    					// procedemos a borrarla y desactivar
	    					/* FUTURO VENCIDO D-1 DEL VENCIMIENTO */
	    				
	    					//Actualizamos campos de errores.
	    					MySharePosition.setActive(new Long(0));
	    					MySharePosition.setActive_trading(new Long(0));
	    					MySharePosition.setLast_error_data_trade(sdf2.format(FechaError));  // desactivamos trading.
	    					
	    					ShareDAO.updateShare(MySharePosition);
	    					
	    					
	    					PositionDAO.deletePositionByPositionID(MiPosicion);
	    					isDelete = true;
			    			//MiPosicion.setState(PositionStates.status.CANCEL_BUY.toString());
			    			
			    		}
	    				
	    				/* OJO, PUEDEN SER VENTAS/COMPRAS  PARCIALES..ENTRADA...SOLO OPERACIONES TOTALES */
	    				if (PositionStates.statusTWSCallBack.Filled.toString().equals(status))
			    		{
			    			
			    			java.sql.Timestamp FechaCompra = new Timestamp(Calendar.getInstance().getTimeInMillis());	    			
			    			MiPosicion.setDate_real_buy(FechaCompra) ;    // OJO TIMESTAMP.			    			
			    			MiPosicion.setPrice_real_buy(avgFillPrice);  // cogemos el avg que nos manda el TWS
			    			
			    			// ACTUALIZAMOS EL PRECIO DE SALIDA CON EL PORCENTAJE PORQUE ES EL VALOR QUE TRATAMOS
			    			// vemos el tipo de operacion
			    			if (MiPosicion.getType().equals(PositionStates.statusTWSFire.SELL.toString()))  //short
			    			{
			    				priceStopLost = avgFillPrice +  (avgFillPrice *  MiPosicion.getSell_percentual_stop_lost());
				    			priceStopProfit = avgFillPrice  - (avgFillPrice *  MiPosicion.getSell_percentual_stop_profit());
			    				
			    			}
			    			else  //long
			    			{
			    				priceStopLost = avgFillPrice  - (avgFillPrice *  MiPosicion.getSell_percentual_stop_lost());
				    			priceStopProfit = avgFillPrice  + (avgFillPrice *  MiPosicion.getSell_percentual_stop_profit());
			    			}
			    				 
			    			
			    			MiPosicion.setSell_price_stop_lost(Utilidades.RedondeaPrice(priceStopLost));
			    			MiPosicion.setSell_price_stop_profit(Utilidades.RedondeaPrice(priceStopProfit));
			    			
			    			
			    			MiPosicion.setState(PositionStates.status.BUY_OK.toString());
			    			
			    		}	    				
	    						
	    			}
	    			else  // SALIDA OPERATION..  OJO, PUEDEN SER VENTAS/COMPRAS  PARCIALES.. */
	    			{
	    				
	    				
	    				if (PositionStates.statusTWSCallBack.Cancelled.toString().equals(status))
			    		{			    						    		
	    					MiPosicion.setState_sell(null);
	    					MiPosicion.setDate_sell(null);
	    					MiPosicion.setDate_real_sell(null);
	    					MiPosicion.setLimit_price_sell(null);
	    					MiPosicion.setPositionID_tws_sell(null);
	    					MiPosicion.setPrice_real_sell(null);
	    					MiPosicion.setPrice_sell(null);
	    					MiPosicion.setRealtimeID_sell_alert(null);
	    					MiPosicion.setStrategyID_sell(null);
	    					MiPosicion.setState_sell(status);	 
	    					/*MiPosicion.setSell_price_stop_lost(null);
	    					MiPosicion.setSell_price_stop_lost(sell_price_stop_lost);
	    					*/
	    					
	    					// es una venta cancelada, restauramos los valores para seguir vendiendo.	    					
			    			//MiPosicion.setState(PositionStates.status.CANCEL_BUY.toString());
			    			
			    		}
	    				
	    				
	    				/* PUEDE RETORNAR FILLED MAS DE UNA VEZ*/
	    				/* EN LAS OPERACIONES PARCIALES, DESPUES DE FILLED, PONGO EL STATESELL A NULL
	    				 * PARA PODER SEGUIR VENDIENDO. 
	    				 * PASARIA POR AQUI Y ACTUALIZARIA DOS VECES ERROREO EL CAMPO OPERATIONS.
	    				 */
	    				if (PositionStates.statusTWSCallBack.Filled.toString().equals(status)
	    						&& MiPosicion.getState_sell()!=null)
			    		{
	    					
			    			java.sql.Timestamp FechaVenta = new Timestamp(Calendar.getInstance().getTimeInMillis());	    			
			    			
			    			//SimpleDateFormat sdf2 = new SimpleDateFormat();
			    			sdf2.applyPattern("HH:mm:ss");
			    			
			    			StringBuilder StrOperaciones = new StringBuilder();
			    			
			    			StrOperaciones.append("(" + avgFillPrice + "|" + sdf2.format(FechaVenta) + "|" +  MiPosicion.getShare_number_to_trade() + "|" + MiPosicion.getStrategyID_sell() + ")");
			    			
			    			// hay operacion previa
			    			String _Operation = "";
			    			
			    			if (MiPosicion.getTrading_data_operations()!=null &&  MiPosicion.getTrading_data_operations().equals("")==false)
			    			{
			    				_Operation = MiPosicion.getTrading_data_operations() + ";";
			    			}
			    			_Operation += StrOperaciones.toString();
			    			
			    			MiPosicion.setTrading_data_operations(_Operation);
			    			
			    			//LogTWM.log(Priority.INFO,"Updating OutPut Operation.. " + MySharePosition.getSymbol());
			    			
			    			/* acumulo las acciones vendidas y a vender en la operativa */
			    			int _RemaingShares = MiPosicion.getShare_number_to_trade().intValue() +MiPosicion.getShare_number_traded().intValue();
			    			int _TotalShares = MiPosicion.getShare_number().intValue();
			    			
			    			MiPosicion.setShare_number_traded(new Long(_RemaingShares));
			    			MiPosicion.setShare_number_to_trade(MiPosicion.getShare_number() - MiPosicion.getShare_number_traded());
			    			
			    			/* NO ES EL SELL OK y PRECIOS REALES NO SE GUARDAN EN LOS CAMPOS hasta que el numero de acciones vendidas quede cerrado*/
			    			if (_RemaingShares==_TotalShares)   // ya no hay =
			    			{	
			    				MiPosicion.setState_sell(status);	 
			    				MiPosicion.setDate_sell(FechaVenta) ; // OJO TIMESTAMP.
			    				MiPosicion.setDate_real_sell(FechaVenta) ; // OJO TIMESTAMP.			    			
				    			MiPosicion.setPrice_real_sell(avgFillPrice);  // cogemos el avg que nos manda el TWS
			    				MiPosicion.setState(PositionStates.status.SELL_OK.toString());
			    			}
			    			else   //  hay acciones pendientes para salir..actualizo el stop de beneficio para no dispararse
			    			{
			    				// SE PUEDEN DAR DOS COSAS,
			    				// 1. QUE EL STOP PROFIT SALTO POR DEBAJO DEL INICIAL--> DEJAMOS EL INICIAL
			    				// 2. QUE EL STOP PROFIT SALTO POR ENCIMA DEL INICIAL --> SIGUE EN TENDENCIA, LOS MULTIPLICAMOS POR UN 50%
			    				
			    				MiPosicion.setState_sell(null);	
			    				
			    				double priceStopProfitAperturaPosicion = 0;
			    				double priceNewStopProfit = 0;
			    				
			    				
			    				if (MiPosicion.getType().equals(PositionStates.statusTWSFire.SELL.toString()))  //short
				    			{
			    					// cojo el original
			    					priceStopProfitAperturaPosicion = MiPosicion.getPrice_real_buy()  - (MiPosicion.getPrice_real_buy() *  MySharePosition.getSell_percentual_stop_profit());
			    					// si el original es mayor...subio la accion mucho, la tendencia es seguir, le subo un 50% para que no salte el resto de la posicion 
			    					if (priceStopProfitAperturaPosicion > MiPosicion.getSell_price_stop_profit().doubleValue())
			    					{
			    						priceNewStopProfit = MiPosicion.getSell_price_stop_profit().doubleValue() - (MiPosicion.getSell_price_stop_profit().doubleValue() *0.5);
			    						priceNewStopProfit  = priceNewStopProfit /100;
			    						//MiPosicion.setSell_percentual_stop_profit(Utilidades.RedondeaPrice(priceNewStopProfit));
			    						
			    					}
			    					else  // restauramos el original
			    					{
			    						priceNewStopProfit = priceStopProfitAperturaPosicion;
			    									    						
			    					}
				    			}
				    			else  //long position
				    			{		
				    				// cojo el original
			    					priceStopProfitAperturaPosicion = MiPosicion.getPrice_real_buy()  + (MiPosicion.getPrice_real_buy() *  MySharePosition.getSell_percentual_stop_profit());
			    					// si el original es menor...subio la accion mucho, la tendencia es seguir, le subo un 50% para que no salte el resto 
			    					if (priceStopProfitAperturaPosicion < MiPosicion.getSell_price_stop_profit().doubleValue())
			    					{
			    						priceNewStopProfit = MiPosicion.getSell_price_stop_profit().doubleValue() + (MiPosicion.getSell_price_stop_profit().doubleValue() *0.5);
			    						priceNewStopProfit  = priceNewStopProfit / 100;
			    						//MiPosicion.setSell_percentual_stop_profit(Utilidades.RedondeaPrice(priceNewStopProfit));
			    						
			    					}
			    					else  // restauramos el original
			    					{
			    						priceNewStopProfit = priceStopProfitAperturaPosicion;
			    									    						
			    					}
			    				}
			    				
			    				MiPosicion.setSell_price_stop_profit(Utilidades.RedondeaPrice(priceNewStopProfit));
			    				
			    				//LogTWM.log(Priority.INFO,"New Profit Stop.. " + Utilidades.RedondeaPrice(priceNewStopProfit));
			    				
			    				
			    				
			    			}
			    			
			    			// ACTUALIZAMOS EL PRECIO DE SALIDA CON EL PORCENTAJE.
			    			/*priceStopLost = avgFillPrice  + (avgFillPrice *  MiPosicion.getSell_percentual_stop_lost());
			    			priceStopProfit = avgFillPrice  - (avgFillPrice *  MiPosicion.getSell_percentual_stop_profit());*/
			    			/* DOS DECIMALES PRECIOS DE SALIDA LOST Y PROFIT */
			    			/*MiPosicion.setSell_price_stop_lost(Utilidades.RedondeaPrice(priceStopLost));
			    			MiPosicion.setSell_price_stop_profit(Utilidades.RedondeaPrice(priceStopProfit));*/
			    			
			    			
			    		}	    
	    				
	    			}
	    			if (!isDelete)  // compras canceladas se borran.
	    			{	
	    				PositionDAO.updatePositionByPositionID(MiPosicion);
	    			}
	    		}	
		    	
			} catch (Exception ex) {
				LogTWM.log(Priority.ERROR,"Error Order Status:" + ex.getMessage() + "" + ex.getStackTrace().toString());
			}
			//	
			
		}
	/*
	 * public static void main(String[] args) throws InterruptedException { //
	 * TODO Auto-generated method stub
	 * 
	 * 
	 * TIMApiWrapper oTWS = new TIMApiWrapper();
	 * 
	 * 
	 * // oTWS.connectToTWS();
	 * 
	 * if (oTWS.isConnected()) { Order BuyPosition = new Order();
	 * BuyPosition.m_action = PositionStates.status.BUY.toString();
	 * BuyPosition.m_totalQuantity = 100; BuyPosition.m_orderType =
	 * PositionStates.ordertypes.LMT.toString(); BuyPosition.m_allOrNone = true;
	 * BuyPosition.m_lmtPrice =62;
	 * 
	 * oContrat = oTWS.createContract("GOOG", "STK","SMART","USD");
	 * 
	 * oTWS.openOrder(getUniqueOrderID(), oContrat, BuyPosition); }
	 * 
	 * 
	 * }
	 */

	@Override
	public void tickOptionComputation(int tickerId, int field,
			double impliedVol, double delta, double optPrice,
			double pvDividend, double gamma, double vega, double theta,
			double undPrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openOrderEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accountDownloadEnd(String accountName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execDetailsEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deltaNeutralValidation(int reqId, UnderComp underComp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickSnapshotEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void marketDataType(int reqId, int marketDataType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickPrice(int tickerId, int field, double price, TickAttr attrib) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orderStatus(int orderId, String status, double filled, double remaining, double avgFillPrice,
			int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {
		// TODO Auto-generated method stub
		
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
	public void deltaNeutralValidation(int reqId, DeltaNeutralContract underComp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commissionReport(CommissionReport commissionReport) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void position(String account, Contract contract, double pos, double avgCost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void positionEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accountSummary(int reqId, String account, String tag, String value, String currency) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accountSummaryEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyMessageAPI(String apiData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyCompleted(boolean isSuccessful, String errorText) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyAndAuthMessageAPI(String apiData, String xyzChallenge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verifyAndAuthCompleted(boolean isSuccessful, String errorText) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayGroupList(int reqId, String groups) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayGroupUpdated(int reqId, String contractInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void connectAck() {
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

}
