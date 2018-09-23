package com.ibtrader.interactive;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.ATRIndicator;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.adx.ADXIndicator;
import org.ta4j.core.indicators.adx.MinusDIIndicator;
import org.ta4j.core.indicators.adx.PlusDIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
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
import com.ibtrader.util.DirectionalMovementADXRUtil;
import com.ibtrader.util.MobileAvgUtil;
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
import com.ib.client.ExecutionFilter;


public class TIMApiGITrader_NOVALE extends TIMApiWrapper {

	
	Log _log = LogFactoryUtil.getLog(TIMApiGITrader_NOVALE.class);
	long guestGroupId=0;
	SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMM");	
	private String _ConnectionHOST = "127.0.0.1";
	private int    _ConnectionPORT = 7497;
	private int    _ConnectionCLIENTID = 0;
	
	private boolean _sendDisconnectEvent =false;
	
	static TimeSeries	series = new BaseTimeSeries("my_live_series");
    static ZonedDateTime endTime = ZonedDateTime.now();
	
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
	public TIMApiGITrader_NOVALE(String _host, int _port, int clientid, boolean debug)  {
		 super(clientid);					
		_ConnectionHOST = _host;
		_ConnectionPORT = _port;
		_ConnectionCLIENTID = clientid;						
	}	
	public TIMApiGITrader_NOVALE(String _host, int _port, int clientid)  {
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
	
	private static void AddExampleBars1()
	{
		     series.addBar(new BaseBar(endTime.plusMinutes(5),165.36,165.43,164.84,164.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(10),164.9,165.24,164.84,164.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(15),164.9,165.28,164.9,165.2,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(20),165.19,165.28,164.9,165.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(25),165.26,165.43,164.9,165.2,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(30),165.21,165.58,163.65,165.26,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(35),165.26,165.45,164.97,165.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(40),165.07,165.27,164.23,164.41,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(45),164.42,164.56,163.94,163.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(50),163.99,164.16,163.77,163.81,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(55),163.85,164.16,163.7,164.09,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(60),164.08,164.55,164.03,164.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(65),164.38,164.59,163.7,164.44,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(70),164.45,164.47,164.35,164.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(75),164.4,164.4,164.21,164.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(80),164.33,164.39,164.29,164.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(85),164.34,164.48,164.22,164.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(90),164.36,164.51,164.27,164.29,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(95),164.27,164.47,164.27,164.44,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(100),164.47,164.58,164.34,164.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(105),164.46,164.5,164.13,164.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(110),164.3,164.96,164.14,164.85,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(115),164.84,165.38,164.84,165.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(120),165.33,165.92,165.3,165.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(125),165.9,165.94,165.78,165.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(130),165.83,166.03,165.72,165.77,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(135),165.78,165.94,165.68,165.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(140),165.88,166.0,165.54,165.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(145),165.76,166.03,165.73,166.02,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(150),166.02,166.09,166.01,166.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(155),166.06,166.06,166.0,166.01,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(160),166.02,166.08,166.01,166.04,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(165),166.13,166.13,165.71,165.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(170),165.85,165.93,165.65,165.77,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(175),165.75,166.02,165.72,165.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(180),165.92,166.02,165.79,165.84,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(185),165.95,166.17,165.85,165.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(190),165.98,166.4,165.73,166.31,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(195),166.31,166.38,165.86,165.89,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(200),165.89,166.19,165.87,166.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(205),166.14,166.17,165.96,166.03,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(210),166.02,166.22,165.93,165.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(215),165.98,166.26,165.94,166.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(220),166.17,166.19,165.87,165.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(225),165.92,166.04,165.83,165.85,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(230),165.85,165.97,165.76,165.84,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(235),165.83,166.08,165.82,165.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(240),165.94,165.98,165.93,165.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(245),165.88,165.96,165.88,165.96,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(250),165.94,165.96,165.86,165.86,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(255),165.83,165.92,165.81,165.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(260),165.91,166.23,165.91,166.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(265),166.21,166.94,165.82,166.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(270),166.83,166.85,164.84,165.18,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(275),165.17,166.33,164.48,166.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(280),166.27,166.64,166.26,166.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(285),166.53,166.63,166.2,166.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(290),166.38,166.92,166.38,166.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(295),166.81,166.98,166.58,166.66,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(300),166.66,166.73,165.88,165.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(305),165.98,166.54,165.17,166.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(310),166.0,166.12,165.81,165.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(315),165.9,165.96,165.81,165.84,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(320),165.84,165.94,165.83,165.84,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(325),166.15,166.52,166.15,166.51,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(330),166.46,166.46,166.27,166.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(335),166.26,166.5,166.26,166.26,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(340),166.24,166.44,166.2,166.29,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(345),166.32,166.42,166.27,166.42,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(350),166.42,167.05,166.37,166.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(355),166.99,167.11,166.63,166.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(360),166.79,167.07,166.7,166.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(365),166.98,167.28,166.98,167.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(370),167.19,167.44,167.12,167.43,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(375),167.42,167.73,167.42,167.56,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(380),167.56,167.72,167.46,167.64,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(385),167.64,167.71,167.42,167.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(390),167.62,167.69,167.61,167.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(395),167.67,167.79,167.65,167.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(400),167.71,167.8,167.69,167.79,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(405),168.06,168.32,168.06,168.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(410),168.26,168.33,168.18,168.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(415),168.33,168.41,168.3,168.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(420),168.3,168.7,168.3,168.6,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(425),168.57,168.64,168.38,168.5,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(430),168.49,168.69,168.26,168.54,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(435),168.55,168.88,168.48,168.73,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(440),168.74,168.8,168.27,168.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(445),168.45,168.69,168.43,168.66,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(450),168.66,168.78,168.49,168.7,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(455),168.7,168.77,168.51,168.73,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(460),168.72,168.9,168.62,168.89,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(465),168.89,168.9,168.55,168.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(470),168.83,168.85,167.64,168.77,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(475),168.78,168.84,168.75,168.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(480),168.75,168.75,168.68,168.7,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(485),168.64,168.88,168.64,168.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(490),168.85,168.85,168.64,168.64,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(495),168.68,168.84,168.67,168.78,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(500),168.76,168.84,168.57,168.59,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(505),168.59,168.8,168.57,168.68,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(510),168.68,168.84,168.43,168.49,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(515),168.5,168.71,168.35,168.57,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(520),168.57,169.13,168.57,169.03,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(525),169.03,169.18,168.93,169.16,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(530),169.15,169.31,169.05,169.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(535),169.24,169.32,169.08,169.12,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(540),169.12,169.4,168.99,169.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(545),169.4,169.55,168.52,169.54,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(550),169.55,169.61,168.88,169.56,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(555),169.55,169.55,169.46,169.47,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(560),169.5,169.54,169.42,169.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(565),169.35,169.4,169.23,169.26,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(570),169.24,169.25,169.11,169.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(575),169.17,169.35,169.13,169.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(580),169.35,169.51,169.25,169.51,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(585),169.48,169.74,169.23,169.44,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(590),169.43,169.45,169.07,169.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(595),169.34,169.56,169.27,169.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(600),169.34,169.45,168.98,169.1,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(605),169.11,169.11,168.74,169.02,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(610),169.02,169.31,169.0,169.13,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(615),169.14,169.25,168.95,169.23,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(620),169.23,169.23,168.72,168.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(625),168.95,169.45,168.85,169.11,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(630),169.11,169.13,169.05,169.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(635),169.08,169.14,169.05,169.12,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(640),169.12,169.14,169.08,169.14,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(645),169.03,169.03,168.77,168.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(650),168.86,168.94,168.86,168.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(655),168.89,168.92,168.85,168.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(660),168.94,169.13,168.88,169.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(665),169.06,169.41,168.92,169.2,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(670),169.19,169.46,168.58,169.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(675),169.26,169.27,168.74,169.13,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(680),169.13,169.37,169.08,169.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(685),169.29,169.4,169.16,169.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(690),169.18,169.34,169.18,169.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(695),169.34,169.43,169.26,169.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(700),169.27,169.38,169.11,169.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(705),169.35,169.59,168.88,169.52,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(710),169.52,169.59,169.23,169.59,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(715),169.52,169.57,169.48,169.5,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(720),169.49,169.53,169.47,169.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(725),171.0,171.4,170.66,171.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(730),171.26,171.31,171.13,171.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(735),171.21,171.34,171.2,171.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(740),171.22,171.22,171.09,171.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(745),171.17,171.61,170.97,171.01,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(750),171.0,171.24,170.57,170.68,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(755),170.68,170.88,170.4,170.65,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(760),170.65,170.95,170.58,170.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(765),170.91,171.06,170.72,170.73,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(770),170.73,170.82,170.22,170.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(775),170.52,170.56,170.15,170.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(780),170.22,170.48,170.04,170.31,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(785),170.31,170.89,169.34,170.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(790),170.34,170.55,170.17,170.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(795),170.18,170.31,170.18,170.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(800),170.29,170.31,170.15,170.18,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(805),170.1,170.24,170.06,170.21,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(810),170.24,170.24,170.05,170.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(815),170.18,170.21,170.07,170.2,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(820),170.17,170.36,170.1,170.36,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(825),170.34,170.51,170.2,170.43,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(830),170.43,170.89,170.42,170.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(835),170.75,171.0,170.72,170.86,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(840),170.86,170.96,170.8,170.86,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(845),170.86,171.06,170.81,171.03,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(850),171.03,171.08,170.93,171.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(855),171.05,171.11,170.95,171.03,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(860),171.03,171.07,170.89,171.04,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(865),171.05,171.19,170.3,171.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(870),171.15,171.22,170.94,171.18,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(875),171.18,171.18,171.08,171.12,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(880),171.12,171.13,171.11,171.13,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(885),171.11,171.31,171.11,171.31,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(890),171.28,171.37,171.25,171.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(895),171.23,171.32,171.18,171.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(900),171.28,171.28,171.16,171.18,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(905),171.16,171.25,171.07,171.1,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(910),171.1,171.13,170.92,171.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(915),171.0,171.16,170.71,170.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(920),170.83,170.89,170.66,170.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(925),170.67,170.79,170.58,170.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(930),170.75,170.98,170.73,170.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(935),170.86,173.47,170.82,173.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(940),173.45,173.52,172.85,173.1,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(945),173.1,173.14,171.01,173.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(950),173.06,173.1,172.35,173.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(955),173.07,173.15,173.06,173.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(960),173.15,173.26,173.12,173.16,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(965),173.46,173.54,173.45,173.51,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(970),173.57,173.86,173.56,173.78,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(975),173.76,173.85,173.7,173.7,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(980),173.72,173.79,173.4,173.51,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(985),173.5,173.75,173.34,173.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(990),173.39,173.6,173.06,173.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(995),173.4,173.49,173.01,173.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1000),173.19,173.2,172.79,172.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1005),172.92,173.11,172.84,172.85,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1010),172.84,172.93,172.6,172.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1015),172.62,172.88,172.59,172.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1020),172.8,172.99,172.71,172.81,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1025),172.81,173.11,172.75,172.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1030),172.9,173.09,172.82,172.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1035),172.94,172.94,172.83,172.89,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1040),172.9,172.9,172.82,172.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1045),172.12,172.5,172.08,172.08,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1050),172.05,172.2,171.99,172.13,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1055),172.14,172.2,172.05,172.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1060),172.18,172.27,172.03,172.03,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1065),172.05,172.24,171.79,172.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1070),172.01,172.37,171.75,171.86,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1075),171.86,172.0,171.6,171.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1080),171.87,171.94,171.22,171.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1085),171.25,171.42,171.1,171.38,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1090),171.38,171.42,170.96,170.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1095),170.98,171.16,170.84,171.02,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1100),171.03,171.13,170.58,170.71,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1105),170.7,172.15,170.58,170.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1110),170.86,170.92,170.72,170.73,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1115),170.72,170.73,170.57,170.61,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1120),170.65,171.22,170.61,170.64,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1125),171.04,171.17,171.02,171.02,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1130),170.99,171.0,170.87,170.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1135),170.92,170.96,170.9,170.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1140),170.87,170.87,170.28,170.32,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1145),170.29,171.05,170.22,170.41,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1150),170.42,170.65,170.06,170.09,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1155),170.09,170.2,169.52,169.6,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1160),169.61,169.82,169.39,169.63,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1165),169.64,170.01,169.62,169.99,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1170),169.99,170.18,169.76,170.18,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1175),170.17,170.23,169.89,170.14,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1180),170.14,170.15,169.78,169.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1185),169.94,169.99,169.67,169.74,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1190),169.75,170.03,169.71,169.77,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1195),169.74,169.82,169.74,169.81,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1200),169.78,169.78,169.67,169.7,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1205),169.65,169.7,169.5,169.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1210),169.7,169.7,169.46,169.56,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1215),169.54,169.67,169.49,169.64,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1220),169.63,169.75,169.42,169.46,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1225),169.55,169.8,169.46,169.77,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1230),169.79,169.94,169.57,169.65,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1235),169.64,170.14,169.21,170.04,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1240),170.04,170.27,170.04,170.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1245),170.19,170.43,170.07,170.29,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1250),170.3,170.53,170.25,170.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1255),170.35,170.4,170.08,170.09,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1260),170.09,170.17,169.37,169.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1265),169.53,170.2,169.49,169.64,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1270),169.64,169.9,169.58,169.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1275),169.64,169.66,169.55,169.55,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1280),169.55,169.6,169.52,169.55,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1285),169.33,169.55,169.13,169.51,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1290),169.55,169.75,169.55,169.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1295),169.61,169.61,169.33,169.38,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1300),169.37,169.49,169.28,169.49,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1305),169.35,169.77,169.35,169.68,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1310),169.69,169.79,169.03,169.31,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1315),169.31,169.59,168.89,169.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1320),169.39,169.93,169.25,169.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1325),169.83,169.98,169.35,169.61,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1330),169.62,169.75,168.9,169.23,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1335),169.23,169.38,169.04,169.18,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1340),169.18,169.53,169.03,169.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1345),169.08,169.32,168.98,169.1,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1350),169.06,169.3,169.06,169.09,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1355),169.1,169.23,169.1,169.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1360),169.19,169.23,169.15,169.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1365),169.33,169.47,169.26,169.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1370),169.32,169.36,169.26,169.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1375),169.32,169.32,169.12,169.21,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1380),169.21,169.44,169.21,169.36,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1385),169.34,169.8,169.15,169.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1390),169.44,169.93,169.28,169.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1395),169.91,170.17,169.3,169.43,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1400),169.43,169.52,169.1,169.44,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1405),169.44,169.68,169.29,169.55,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1410),169.55,169.75,169.34,169.48,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1415),169.48,169.48,169.05,169.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1420),169.18,169.69,169.06,169.65,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1425),169.66,169.87,169.06,169.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1430),169.81,169.92,169.5,169.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1435),169.88,169.98,169.87,169.97,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1440),169.95,169.97,169.91,169.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1445),169.65,169.65,169.26,169.26,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1450),169.25,169.3,169.02,169.06,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1455),169.08,169.24,168.92,168.96,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1460),168.96,169.08,168.9,169.04,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1465),169.04,169.09,168.85,169.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1470),169.07,169.11,168.59,168.72,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1475),168.72,169.04,168.47,168.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1480),168.91,169.14,168.64,168.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1485),168.96,169.13,168.85,169.03,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1490),169.03,169.13,168.88,168.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1495),168.92,168.96,168.68,168.78,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1500),168.78,169.11,168.64,168.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1505),168.94,169.66,168.87,168.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1510),168.98,169.03,168.91,168.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1515),168.92,168.97,168.86,168.97,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1520),168.95,169.06,168.95,169.01,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1525),167.98,168.0,167.74,167.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1530),167.93,168.01,167.8,167.81,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1535),167.84,167.87,167.55,167.65,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1540),167.64,167.82,167.51,167.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1545),167.55,167.74,167.43,167.57,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1550),167.57,167.84,167.15,167.6,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1555),167.61,168.29,167.6,168.16,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1560),168.17,168.29,167.89,168.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1565),168.27,168.29,167.92,168.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1570),168.07,168.54,167.92,168.1,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1575),168.09,168.38,168.01,168.14,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1580),168.14,168.14,167.59,168.01,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1585),168.01,168.97,167.5,168.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1590),168.24,168.35,167.9,168.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1595),168.3,168.41,168.23,168.23,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1600),168.23,168.31,168.21,168.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1605),168.65,168.84,168.65,168.72,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1610),168.74,168.76,168.44,168.6,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1615),168.6,168.72,168.6,168.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1620),168.61,168.62,168.26,168.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1625),168.39,168.58,168.09,168.36,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1630),168.37,168.7,168.0,168.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1635),168.28,169.28,168.23,169.16,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1640),169.17,169.4,169.04,169.29,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1645),169.28,169.5,169.18,169.23,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1650),169.22,169.35,169.08,169.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1655),169.18,169.18,168.77,168.96,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1660),168.96,169.38,168.7,169.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1665),169.32,169.69,167.97,169.56,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1670),169.57,169.68,167.97,169.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1675),169.6,169.63,169.53,169.57,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1680),169.55,169.55,169.38,169.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1685),168.65,168.65,168.04,168.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1690),168.17,168.48,168.1,168.21,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1695),168.23,168.31,168.12,168.27,0));
		
	}
	private static void AddExampleBars2()
	{
		 series.addBar(new BaseBar(endTime.plusMinutes(1700),168.26,168.66,168.21,168.56,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1705),168.62,168.95,168.03,168.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1710),168.16,168.58,168.11,168.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1715),168.33,168.39,167.83,168.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1720),168.3,168.75,168.25,168.65,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1725),168.65,169.05,168.65,168.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1730),168.89,169.21,168.85,169.03,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1735),169.03,169.03,168.68,168.79,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1740),168.79,169.21,168.7,169.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1745),169.16,169.34,168.7,168.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1750),168.83,168.87,168.56,168.81,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1755),168.81,168.81,168.6,168.61,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1760),168.61,168.61,168.01,168.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1765),168.47,168.61,168.34,168.46,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1770),168.46,168.54,168.22,168.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1775),168.24,168.39,168.24,168.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1780),168.36,168.57,168.31,168.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1785),168.5,168.87,168.31,168.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1790),168.81,168.94,168.25,168.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1795),168.35,168.35,167.53,167.77,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1800),167.76,167.93,166.89,166.99,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1805),166.99,167.64,166.84,167.59,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1810),167.6,168.24,167.47,168.18,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1815),168.18,168.18,167.35,167.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1820),167.88,168.06,167.56,167.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1825),167.67,169.23,167.27,167.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1830),167.28,167.73,167.28,167.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1835),167.35,167.55,167.33,167.44,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1840),167.48,167.5,167.43,167.46,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1845),167.65,167.92,167.56,167.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1850),167.86,167.93,167.84,167.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1855),167.88,167.94,167.85,167.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1860),167.91,168.0,167.78,167.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1865),167.84,168.03,167.67,167.89,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1870),167.87,168.27,167.53,168.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1875),168.19,168.65,168.08,168.26,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1880),168.27,168.47,167.97,168.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1885),168.4,168.89,168.35,168.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1890),168.75,168.83,168.62,168.64,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1895),168.64,169.05,168.64,168.97,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1900),168.96,169.06,168.65,168.86,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1905),168.86,168.98,168.61,168.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1910),168.76,168.76,168.55,168.66,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1915),168.7,168.76,168.7,168.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1920),168.75,168.91,168.7,168.84,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1925),167.41,167.41,167.16,167.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1930),167.32,167.45,167.2,167.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1935),167.45,167.45,167.32,167.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1940),167.42,167.62,167.34,167.57,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1945),167.56,168.08,167.35,167.57,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1950),167.57,167.88,167.25,167.71,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1955),167.72,168.38,167.61,168.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1960),168.19,168.38,167.93,168.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1965),168.21,168.24,167.8,167.84,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1970),167.83,168.31,167.83,168.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1975),168.3,168.45,168.05,168.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1980),168.24,168.29,167.41,167.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1985),167.44,168.9,167.22,167.29,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1990),167.31,167.8,167.18,167.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(1995),167.24,167.35,167.01,167.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2000),167.28,167.3,167.24,167.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2005),167.42,167.54,167.26,167.32,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2010),167.29,167.29,167.13,167.23,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2015),167.24,167.39,167.19,167.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2020),167.29,167.39,167.17,167.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2025),167.38,167.64,167.24,167.52,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2030),167.52,167.62,167.05,167.54,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2035),167.54,167.58,166.67,166.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2040),166.76,166.83,166.06,166.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2045),166.35,166.37,165.84,166.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2050),166.27,166.4,165.87,165.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2055),165.93,166.12,165.6,165.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2060),165.92,167.51,165.36,165.48,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2065),165.48,167.51,165.16,165.2,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2070),165.2,165.45,165.16,165.41,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2075),165.41,165.96,165.4,165.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2080),165.92,166.22,165.91,165.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2085),165.98,166.12,165.91,166.08,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2090),166.09,166.29,166.07,166.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2095),166.05,166.18,166.05,166.12,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2100),166.15,166.26,165.9,165.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2105),166.25,166.25,165.7,165.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2110),165.83,166.0,165.18,165.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2115),165.62,165.79,164.97,165.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2120),165.01,165.26,164.53,165.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2125),165.18,165.41,164.99,165.06,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2130),165.06,166.0,165.06,165.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2135),165.87,166.2,165.65,166.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2140),166.16,166.2,165.37,165.58,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2145),165.58,166.03,165.29,165.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2150),165.39,165.4,165.28,165.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2155),165.36,165.83,165.32,165.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2160),165.83,165.86,165.68,165.72,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2165),166.43,166.8,166.43,166.66,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2170),166.65,166.86,166.65,166.79,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2175),166.77,167.08,166.77,167.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2180),167.0,167.12,166.83,166.84,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2185),166.84,167.08,165.57,167.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2190),167.07,167.9,167.07,167.81,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2195),167.81,168.18,167.62,167.89,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2200),167.88,168.52,167.59,168.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2205),168.38,168.51,168.3,168.41,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2210),168.39,168.46,168.19,168.43,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2215),168.43,168.43,168.12,168.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2220),168.37,169.26,168.37,169.16,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2225),169.16,169.24,165.59,168.78,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2230),168.78,168.85,168.67,168.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2235),168.8,168.86,167.86,168.6,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2240),168.61,168.9,168.54,168.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2245),168.95,169.25,168.95,169.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2250),169.25,169.29,169.13,169.2,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2255),169.21,169.21,168.99,169.13,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2260),169.11,169.25,169.03,169.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2265),169.05,169.17,168.8,168.89,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2270),168.9,169.53,168.7,169.52,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2275),169.52,169.65,169.06,169.32,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2280),169.32,169.95,169.24,169.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2285),169.92,170.27,169.9,170.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2290),170.16,170.32,169.93,170.09,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2295),170.09,170.21,170.0,170.06,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2300),170.06,170.27,169.2,170.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2305),170.25,170.47,169.2,170.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2310),170.45,170.5,170.31,170.5,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2315),170.46,170.57,170.42,170.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2320),170.45,170.49,170.38,170.41,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2325),169.54,169.54,169.15,169.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2330),169.29,169.29,169.17,169.18,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2335),169.2,169.36,169.16,169.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2340),169.32,169.39,169.15,169.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2345),169.17,169.36,168.96,169.12,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2350),169.11,169.67,169.08,169.5,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2355),169.5,169.6,169.28,169.42,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2360),169.42,170.11,169.34,170.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2365),170.07,170.18,169.88,170.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2370),170.15,170.64,170.07,170.52,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2375),170.51,170.94,170.47,170.68,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2380),170.69,171.08,170.57,170.97,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2385),170.96,171.13,169.49,171.06,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2390),171.07,171.13,171.0,171.09,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2395),171.09,171.12,170.97,170.97,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2400),170.98,171.42,170.96,171.31,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2405),170.89,171.04,170.88,171.02,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2410),171.04,171.3,171.02,171.18,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2415),171.16,171.2,171.06,171.16,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2420),171.18,171.21,170.65,170.68,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2425),170.69,171.17,170.54,170.79,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2430),170.79,170.92,170.01,170.54,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2435),170.54,170.92,170.36,170.44,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2440),170.44,171.15,170.22,170.72,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2445),170.72,170.75,170.41,170.6,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2450),170.59,170.68,170.32,170.54,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2455),170.54,170.67,169.47,170.01,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2460),170.02,170.44,169.69,169.71,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2465),169.7,170.7,169.67,169.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2470),169.93,170.08,169.84,169.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2475),169.93,170.36,169.67,169.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2480),169.69,170.9,169.69,170.86,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2485),170.4,170.49,170.3,170.43,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2490),170.41,170.51,170.34,170.42,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2495),170.37,170.39,170.2,170.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2500),170.31,170.49,170.27,170.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2505),170.3,170.87,169.72,170.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2510),170.81,171.84,170.64,171.66,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2515),171.66,171.98,171.31,171.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2520),171.95,172.16,171.73,171.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2525),171.93,172.15,171.65,171.77,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2530),171.76,172.04,171.63,171.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2535),171.79,171.84,171.47,171.61,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2540),171.61,172.14,171.46,172.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2545),172.07,172.14,169.76,171.7,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2550),171.69,172.05,171.68,171.7,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2555),171.68,171.73,171.6,171.63,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2560),171.65,171.97,171.65,171.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2565),171.77,171.77,171.14,171.41,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2570),171.43,171.47,171.32,171.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2575),171.41,171.69,171.4,171.65,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2580),171.67,171.73,171.55,171.68,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2585),171.67,171.68,171.29,171.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2590),171.3,171.82,171.15,171.65,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2595),171.65,172.16,171.62,172.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2600),172.06,172.68,172.04,172.57,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2605),172.56,172.95,172.5,172.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2610),172.95,172.95,172.6,172.61,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2615),172.61,172.78,172.55,172.77,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2620),172.76,173.32,172.66,173.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2625),173.2,173.39,171.4,173.26,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2630),173.26,173.34,172.12,173.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2635),173.23,173.45,173.2,173.41,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2640),173.4,173.82,173.38,173.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2645),173.47,173.6,173.46,173.47,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2650),173.49,173.53,173.37,173.49,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2655),173.49,173.55,173.39,173.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2660),173.41,173.69,173.39,173.69,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2665),173.44,173.85,173.44,173.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2670),173.75,174.06,173.52,174.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2675),174.0,174.12,173.51,173.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2680),173.75,174.04,173.67,173.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2685),173.81,174.1,173.74,173.99,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2690),174.0,174.44,173.99,174.42,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2695),174.43,174.51,174.23,174.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2700),174.34,174.4,174.14,174.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2705),174.37,174.41,173.28,174.26,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2710),174.38,174.38,174.17,174.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2715),174.21,174.22,174.1,174.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2720),174.21,174.33,174.21,174.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2725),174.27,174.3,174.25,174.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2730),174.26,174.26,174.19,174.23,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2735),174.21,174.21,174.08,174.11,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2740),174.09,174.3,174.06,174.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2745),174.25,174.38,174.15,174.36,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2750),174.36,174.68,174.24,174.6,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2755),174.61,174.75,174.11,174.2,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2760),174.21,174.42,174.17,174.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2765),174.37,174.49,174.32,174.47,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2770),174.47,174.51,174.35,174.42,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2775),174.42,174.42,174.01,174.1,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2780),174.09,174.41,174.08,174.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2785),174.39,174.54,174.26,174.29,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2790),174.31,174.41,174.26,174.32,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2795),174.28,174.32,174.25,174.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2800),174.25,174.35,174.24,174.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2805),174.27,174.31,174.2,174.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2810),174.26,174.26,174.21,174.21,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2815),174.24,174.26,174.18,174.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2820),174.23,174.4,174.21,174.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2825),174.31,175.0,174.3,174.78,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2830),174.78,175.46,174.78,175.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2835),175.39,175.93,174.66,175.06,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2840),175.05,175.36,174.81,175.36,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2845),175.36,175.39,174.94,175.11,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2850),175.1,175.44,175.04,175.38,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2855),175.38,175.64,175.15,175.48,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2860),175.48,175.81,175.34,175.43,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2865),175.42,175.5,175.31,175.46,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2870),175.46,175.5,175.42,175.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2875),175.42,175.42,175.24,175.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2880),175.3,175.34,175.29,175.32,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2885),174.55,174.84,174.23,174.52,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2890),174.54,174.61,174.43,174.43,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2895),174.42,174.62,174.32,174.56,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2900),174.54,174.62,174.38,174.57,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2905),174.56,174.81,174.42,174.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2910),174.77,174.89,174.19,174.23,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2915),174.23,174.71,173.96,174.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2920),174.27,174.67,174.21,174.51,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2925),174.52,174.57,174.18,174.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2930),174.54,174.65,174.36,174.48,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2935),174.48,174.84,174.42,174.71,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2940),174.71,174.81,174.4,174.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2945),174.61,174.82,174.51,174.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2950),174.75,174.83,174.74,174.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2955),174.76,174.8,174.58,174.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2960),174.69,174.78,174.64,174.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2965),175.15,175.38,174.93,175.23,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2970),175.21,175.24,175.11,175.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2975),175.26,175.55,175.25,175.43,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2980),175.43,175.47,175.19,175.23,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2985),175.25,175.41,174.98,174.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2990),174.98,175.12,174.51,174.78,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(2995),174.78,175.02,174.56,174.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3000),174.91,175.16,174.85,175.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3005),175.06,175.23,174.83,175.21,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3010),175.21,175.22,174.95,175.06,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3015),175.06,175.36,175.01,175.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3020),175.35,175.37,175.03,175.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3025),175.14,175.5,174.77,175.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3030),175.4,175.43,175.35,175.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3035),175.35,175.43,175.32,175.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3040),175.37,175.4,175.36,175.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3045),174.84,175.32,174.75,175.11,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3050),175.08,175.26,175.02,175.26,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3055),175.25,175.25,175.13,175.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3060),175.26,175.43,175.24,175.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3065),175.41,175.42,175.17,175.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3070),175.4,175.68,175.32,175.63,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3075),175.63,175.84,175.24,175.44,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3080),175.43,175.65,175.28,175.38,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3085),175.38,175.52,175.17,175.5,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3090),175.5,175.6,175.39,175.51,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3095),175.5,175.7,175.47,175.66,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3100),175.65,175.96,175.49,175.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3105),175.95,176.05,175.31,175.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3110),175.89,175.9,175.7,175.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3115),175.74,175.89,175.74,175.81,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3120),175.83,175.83,175.81,175.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3125),175.89,176.35,175.79,176.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3130),176.26,176.3,176.24,176.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3135),176.28,176.32,176.09,176.09,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3140),176.08,176.14,175.97,176.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3145),175.93,176.06,175.67,175.81,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3150),175.81,176.06,175.72,175.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3155),175.93,176.27,175.7,175.77,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3160),175.78,176.12,175.71,176.1,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3165),176.09,176.38,176.06,176.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3170),176.34,176.38,176.21,176.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3175),176.27,176.47,176.21,176.42,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3180),176.43,176.43,175.92,176.21,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3185),176.22,176.48,175.94,176.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3190),176.25,176.43,176.21,176.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3195),176.37,176.37,176.11,176.18,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3200),176.16,176.19,176.1,176.16,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3205),176.28,176.56,176.25,176.5,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3210),176.51,176.52,176.29,176.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3215),176.31,176.44,176.3,176.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3220),176.32,176.6,176.32,176.51,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3225),176.5,176.63,176.43,176.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3230),176.52,176.87,176.5,176.59,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3235),176.59,176.81,176.38,176.46,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3240),176.46,176.77,176.44,176.68,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3245),176.69,177.03,176.66,176.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3250),176.94,177.08,176.83,176.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3255),176.93,177.07,176.9,177.01,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3260),177.01,177.23,176.79,177.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3265),177.23,177.27,176.18,177.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3270),177.24,177.33,177.22,177.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3275),177.29,177.29,177.17,177.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3280),177.18,177.2,177.13,177.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3285),177.22,177.63,177.22,177.61,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3290),177.62,177.76,177.61,177.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3295),177.7,177.7,177.64,177.66,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3300),177.7,177.7,177.56,177.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3305),177.61,177.75,177.39,177.44,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3310),177.44,177.51,177.13,177.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3315),177.27,177.34,177.02,177.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3320),177.25,177.31,176.65,176.73,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3325),176.73,176.99,176.69,176.96,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3330),176.96,176.99,176.52,176.63,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3335),176.64,177.07,175.66,176.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3340),176.26,176.59,176.14,176.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3345),176.34,177.1,176.23,176.44,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3350),176.44,176.45,176.27,176.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3355),176.27,176.29,175.84,175.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3360),175.87,175.99,175.82,175.96,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3365),175.88,176.11,175.88,175.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3370),175.96,176.13,175.9,176.13,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3375),176.08,176.16,176.06,176.12,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3380),176.07,176.36,176.01,176.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3385),176.26,176.45,176.21,176.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3390),176.25,176.68,175.84,175.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3395),175.94,176.07,175.53,175.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3400),175.94,176.47,175.88,176.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3405),176.22,176.58,176.14,176.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3410),176.35,176.55,176.32,176.46,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3415),176.47,176.89,176.37,176.49,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3420),176.49,176.49,175.69,175.72,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3425),175.72,176.33,175.65,176.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3430),176.05,176.06,175.96,176.02,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3435),176.02,176.02,175.79,175.96,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3440),175.96,175.96,175.81,175.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3445),175.9,175.9,175.73,175.78,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3450),175.83,175.83,175.74,175.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3455),175.8,175.89,175.68,175.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3460),175.83,175.98,175.81,175.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3465),175.9,176.09,175.73,175.97,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3470),175.99,176.38,175.89,176.26,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3475),176.26,176.61,175.76,176.1,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3480),176.11,176.13,175.27,175.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3485),175.3,175.69,175.22,175.57,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3490),175.56,175.95,175.38,175.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3495),175.91,176.0,175.75,175.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3500),175.83,176.42,175.8,176.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3505),176.15,176.26,175.65,175.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3510),175.96,176.02,175.93,175.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3515),175.97,176.01,175.97,176.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3520),175.98,176.0,175.91,176.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3525),176.21,176.35,176.21,176.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3530),176.18,176.25,176.15,176.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3535),176.25,176.42,176.25,176.42,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3540),176.4,176.62,176.36,176.59,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3545),176.56,176.71,176.0,176.69,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3550),176.67,176.8,176.32,176.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3555),176.45,176.62,176.22,176.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3560),176.23,176.46,176.15,176.38,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3565),176.39,176.5,176.34,176.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3570),176.34,176.55,176.33,176.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3575),176.54,176.71,176.51,176.6,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3580),176.61,176.9,176.46,176.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3585),176.83,176.91,176.16,176.81,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3590),176.81,176.82,176.73,176.79,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3595),176.79,176.81,176.66,176.81,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3600),176.82,176.84,176.78,176.81,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3605),176.66,176.66,176.26,176.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3610),176.33,176.41,176.29,176.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3615),176.35,176.45,176.26,176.29,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3620),176.33,176.38,176.21,176.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3625),176.33,176.4,176.07,176.11,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3630),176.11,176.23,175.67,175.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3635),175.8,176.43,175.57,176.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3640),176.34,176.5,176.21,176.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3645),176.46,176.75,176.4,176.56,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3650),176.56,176.7,176.36,176.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3655),176.37,176.72,176.35,176.59,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3660),176.58,176.72,176.25,176.32,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3665),176.31,176.42,176.14,176.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3670),176.23,176.29,176.18,176.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3675),176.25,176.26,176.12,176.2,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3680),176.18,176.44,176.18,176.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3685),176.75,177.01,176.75,176.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3690),176.98,177.17,176.93,177.12,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3695),177.16,177.2,177.0,177.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3700),177.03,177.12,176.93,177.01,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3705),177.0,177.07,176.9,176.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3710),176.94,177.5,176.88,177.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3715),177.34,177.48,176.7,176.84,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3720),176.83,176.99,176.54,176.79,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3725),176.79,177.11,176.77,176.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3730),176.93,177.17,176.88,177.03,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3735),177.03,177.1,176.88,177.04,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3740),177.05,177.24,176.88,177.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3745),177.15,177.35,176.41,177.02,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3750),177.03,177.03,176.88,176.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3755),176.92,176.92,176.7,176.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3760),176.76,176.82,176.71,176.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3765),176.93,176.93,176.77,176.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3770),176.85,176.93,176.79,176.79,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3775),176.79,176.83,176.75,176.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3780),176.81,177.93,176.8,177.72,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3785),177.7,177.95,176.98,177.64,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3790),177.64,177.68,177.11,177.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3795),177.16,177.25,176.15,176.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3800),176.29,176.46,175.72,176.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3805),176.39,176.66,176.21,176.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3810),176.23,176.3,175.6,175.69,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3815),175.69,175.7,175.05,175.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3820),175.53,175.59,174.76,174.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3825),174.95,177.2,174.86,175.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3830),175.05,175.08,174.9,174.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3835),174.99,175.08,174.86,175.06,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3840),175.08,175.34,175.06,175.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3845),175.45,175.56,175.45,175.49,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3850),175.47,175.5,175.4,175.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3855),175.45,175.46,175.27,175.32,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3860),175.32,175.45,175.09,175.13,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3865),175.14,175.5,174.15,174.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3870),174.74,175.53,174.66,175.01,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3875),175.01,176.27,174.98,176.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3880),176.2,176.37,175.97,176.23,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3885),176.24,176.53,176.23,176.48,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3890),176.47,176.78,176.31,176.57,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3895),176.57,176.75,176.53,176.69,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3900),176.68,177.25,176.4,177.23,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3905),177.24,177.31,176.4,177.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3910),177.05,177.24,177.05,177.11,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3915),177.16,177.18,177.1,177.14,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3920),177.17,177.25,177.15,177.19,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3925),176.94,177.11,176.91,177.11,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3930),177.09,177.35,177.09,177.29,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3935),177.26,177.26,177.14,177.18,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3940),177.13,177.38,177.12,177.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3945),177.32,177.36,177.13,177.2,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3950),177.19,177.53,176.91,177.42,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3955),177.42,177.5,177.12,177.41,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3960),177.41,177.43,177.12,177.31,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3965),177.31,177.31,177.16,177.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3970),177.27,177.38,177.22,177.29,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3975),177.3,177.34,177.23,177.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3980),177.3,177.36,177.24,177.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3985),177.28,177.34,177.21,177.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3990),177.28,177.3,177.28,177.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(3995),177.28,177.31,177.24,177.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4000),177.27,177.38,177.26,177.36,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4005),177.12,177.12,176.81,176.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4010),176.89,176.97,176.89,176.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4015),176.94,177.02,176.85,176.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4020),176.94,177.09,176.92,176.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4025),176.99,177.29,176.75,176.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4030),176.83,177.22,176.74,177.14,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4035),177.14,177.36,176.86,177.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4040),177.01,177.21,176.68,176.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4045),176.83,176.9,176.52,176.73,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4050),176.72,176.85,176.37,176.52,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4055),176.52,176.99,176.47,176.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4060),176.95,177.09,176.74,176.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4065),176.99,177.07,176.62,177.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4070),177.06,177.06,176.97,177.02,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4075),177.0,177.04,176.96,177.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4080),177.02,177.11,176.99,177.1,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4085),176.8,176.85,176.65,176.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4090),176.73,176.91,176.72,176.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4095),176.72,176.78,176.5,176.52,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4100),176.54,176.6,176.3,176.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4105),176.4,176.56,175.96,176.06,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4110),176.07,176.71,176.03,176.68,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4115),176.69,176.99,176.44,176.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4120),176.88,176.92,176.69,176.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4125),176.9,177.51,176.79,177.46,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4130),177.46,177.89,177.34,177.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4135),177.75,177.82,177.55,177.59,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4140),177.59,178.41,177.59,178.36,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4145),178.36,178.73,176.99,178.55,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4150),178.55,178.63,178.3,178.46,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4155),178.46,178.82,178.45,178.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4160),178.75,178.81,178.71,178.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4165),178.78,179.0,178.78,178.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4170),178.93,178.93,178.83,178.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4175),178.85,178.85,178.61,178.65,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4180),178.67,178.73,178.56,178.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4185),178.67,178.9,178.59,178.61,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4190),178.6,178.75,178.3,178.56,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4195),178.57,179.08,178.25,178.89,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4200),178.89,179.36,178.8,178.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4205),178.99,179.17,178.93,178.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4210),178.98,179.19,178.96,179.13,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4215),179.14,179.3,179.04,179.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4220),179.16,179.42,179.12,179.31,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4225),179.31,179.36,179.08,179.31,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4230),179.32,179.32,179.23,179.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4235),179.29,179.29,179.18,179.26,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4240),179.22,179.27,179.21,179.25,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4245),179.4,179.5,179.15,179.48,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4250),179.49,179.69,179.49,179.64,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4255),179.62,179.78,179.61,179.69,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4260),179.7,179.78,179.61,179.71,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4265),179.69,179.73,179.58,179.64,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4270),179.65,179.7,179.33,179.54,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4275),179.54,179.67,179.38,179.6,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4280),179.59,179.72,179.33,179.41,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4285),179.41,179.54,179.36,179.38,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4290),179.39,179.69,179.38,179.6,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4295),179.59,179.79,179.59,179.74,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4300),179.74,180.07,179.7,180.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4305),180.06,180.12,179.31,179.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4310),179.9,179.94,179.89,179.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4315),179.92,179.92,179.82,179.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4320),179.87,179.9,179.87,179.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4325),179.75,179.86,179.59,179.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4330),179.84,180.07,179.84,180.02,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4335),180.03,180.22,180.01,180.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4340),180.23,180.3,180.11,180.13,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4345),180.14,180.24,179.7,180.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4350),180.24,180.5,179.91,179.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4355),179.91,180.27,179.79,180.26,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4360),180.26,180.41,180.07,180.12,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4365),180.12,180.27,180.03,180.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4370),180.07,180.25,180.03,180.2,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4375),180.19,180.34,179.8,179.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4380),179.81,179.81,179.02,179.47,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4385),179.47,180.39,179.36,179.5,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4390),179.5,179.51,179.42,179.43,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4395),179.45,179.48,179.31,179.42,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4400),179.41,179.57,179.4,179.49,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4405),179.22,179.32,179.09,179.17,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4410),179.17,179.21,178.99,179.16,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4415),179.15,179.26,179.1,179.13,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4420),179.13,179.16,178.94,179.08,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4425),179.07,179.33,178.96,179.29,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4430),179.28,179.49,178.88,179.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4435),179.39,179.87,179.38,179.69,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4440),179.69,179.71,179.14,179.38,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4445),179.38,179.7,179.31,179.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4450),179.33,179.36,178.75,178.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4455),178.82,179.33,178.72,179.07,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4460),179.07,179.45,178.92,179.04,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4465),179.05,179.44,178.97,179.06,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4470),179.04,179.06,179.0,179.03,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4475),179.05,179.52,179.03,179.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4480),179.32,179.33,178.95,178.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4485),179.1,179.13,179.0,179.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4490),179.04,179.05,178.75,178.78,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4495),178.76,178.94,178.71,178.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4500),178.92,179.04,178.91,178.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4505),178.91,179.42,178.88,179.42,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4510),179.42,179.54,179.09,179.34,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4515),179.35,179.93,179.1,179.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4520),179.53,179.6,179.31,179.45,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4525),179.45,179.6,179.32,179.32,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4530),179.31,179.48,179.24,179.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4535),179.38,179.39,178.38,178.57,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4540),178.58,178.58,177.98,178.44,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4545),178.45,179.51,178.1,178.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4550),178.53,178.57,178.46,178.48,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4555),178.49,178.55,178.4,178.48,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4560),178.49,178.54,178.38,178.51,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4565),178.47,178.8,178.34,178.71,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4570),178.69,178.89,178.66,178.89,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4575),178.84,178.89,178.68,178.75,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4580),178.76,178.96,178.7,178.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4585),178.88,179.11,178.81,178.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4590),178.82,179.36,178.81,179.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4595),179.26,179.68,179.07,179.57,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4600),179.56,179.71,179.43,179.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4605),179.62,179.64,179.36,179.38,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4610),179.38,179.89,179.38,179.84,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4615),179.84,179.97,179.71,179.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4620),179.87,180.05,179.76,179.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4625),179.88,179.97,178.45,179.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4630),179.91,179.94,178.45,179.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4635),179.86,180.02,179.83,179.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4640),179.97,180.01,179.93,180.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4645),180.01,180.02,179.82,179.85,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4650),179.81,179.98,179.76,179.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4655),179.81,179.89,179.75,179.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4660),179.79,179.93,179.76,179.89,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4665),179.88,180.16,179.79,179.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4670),179.94,180.08,179.77,180.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4675),180.0,180.24,179.82,180.16,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4680),180.16,180.55,180.09,180.49,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4685),180.49,180.56,180.41,180.5,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4690),180.5,180.56,180.33,180.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4695),180.37,180.68,180.35,180.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4700),180.67,180.83,180.65,180.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4705),180.75,180.82,179.92,180.63,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4710),180.63,180.66,180.58,180.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4715),180.66,180.66,180.57,180.59,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4720),180.59,180.59,180.56,180.58,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4725),180.97,181.26,180.97,181.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4730),181.11,181.21,181.11,181.21,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4735),181.17,181.28,181.17,181.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4740),181.24,181.26,181.15,181.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4745),181.21,181.29,180.81,181.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4750),181.18,181.25,180.84,180.86,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4755),180.85,181.03,180.71,180.86,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4760),180.86,181.01,180.83,180.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4765),180.92,181.0,180.81,180.89,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4770),180.89,181.04,180.81,181.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4775),181.0,181.09,180.9,180.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4780),180.91,181.05,180.37,180.66,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4785),180.66,181.7,180.43,180.72,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4790),180.73,180.74,180.65,180.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4795),180.65,180.73,180.63,180.73,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4800),180.73,180.85,180.69,180.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4805),180.87,180.87,180.66,180.66,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4810),180.69,180.7,180.63,180.65,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4815),180.69,180.71,180.62,180.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4820),180.71,180.9,180.67,180.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4825),180.91,180.91,180.7,180.7,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4830),180.7,180.94,180.65,180.72,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4835),180.73,180.86,180.41,180.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4840),180.77,180.9,180.68,180.68,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4845),180.68,181.06,180.67,180.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4850),180.95,181.15,180.9,181.12,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4855),181.12,181.19,180.94,181.09,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4860),181.08,181.22,180.63,180.69,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4865),180.68,181.11,180.6,180.77,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4870),180.77,180.77,180.64,180.71,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4875),180.7,180.71,180.64,180.67,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4880),180.67,180.77,180.66,180.74,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4885),180.72,180.92,180.72,180.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4890),180.93,180.93,180.85,180.85,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4895),180.85,180.93,180.8,180.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4900),180.9,180.94,180.83,180.84,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4905),180.87,180.95,180.74,180.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4910),180.92,181.24,180.75,181.11,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4915),181.12,181.22,181.0,181.06,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4920),181.07,181.23,181.03,181.09,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4925),181.09,181.09,180.68,180.69,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4930),180.69,180.92,180.65,180.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4935),180.86,181.16,180.84,181.11,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4940),181.11,181.21,181.04,181.14,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4945),181.13,181.14,180.67,180.96,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4950),180.96,180.98,180.9,180.98,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4955),180.98,181.23,180.98,181.12,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4960),181.12,181.14,181.07,181.08,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4965),181.25,181.33,180.92,181.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4970),181.28,181.3,181.28,181.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4975),181.28,181.35,181.22,181.35,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4980),181.33,181.42,181.3,181.38,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4985),181.37,181.38,181.12,181.3,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4990),181.29,181.59,181.21,181.56,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(4995),181.56,181.75,181.5,181.63,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5000),181.63,181.71,181.51,181.6,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5005),181.59,181.66,180.8,180.91,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5010),180.91,181.43,180.83,180.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5015),180.95,180.97,180.92,180.92,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5020),180.95,180.95,180.93,180.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5025),180.95,181.0,180.94,181.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5030),180.89,181.0,180.87,180.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5035),180.89,180.89,180.7,180.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5040),180.85,180.89,180.8,180.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5045),180.8,181.0,180.7,181.0,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5050),181.0,181.05,180.82,180.95,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5055),180.95,181.22,180.67,180.79,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5060),180.8,181.06,180.63,181.03,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5065),181.04,181.29,180.95,181.22,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5070),181.23,181.43,181.19,181.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5075),181.36,181.38,181.05,181.14,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5080),181.14,181.18,180.76,180.97,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5085),180.97,181.0,180.25,180.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5090),180.53,180.99,180.44,180.53,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5095),180.54,180.54,180.48,180.52,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5100),180.53,180.65,180.48,180.59,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5105),180.6,180.66,180.53,180.65,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5110),180.66,180.66,180.23,180.24,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5115),180.24,180.24,180.05,180.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5120),180.07,180.07,179.89,179.93,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5125),179.94,180.05,179.89,180.05,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5130),180.05,180.1,179.89,179.9,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5135),179.9,180.29,179.76,180.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5140),180.27,180.39,180.11,180.28,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5145),180.29,180.32,179.47,179.69,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5150),179.69,179.87,179.61,179.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5155),179.76,179.79,179.31,179.41,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5160),179.41,179.74,179.17,179.73,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5165),179.73,179.97,179.4,179.88,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5170),179.89,180.51,179.63,179.7,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5175),179.7,179.84,179.66,179.73,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5180),179.73,179.8,179.7,179.73,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5185),179.76,179.84,179.73,179.82,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5190),179.99,180.08,179.92,179.97,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5195),179.97,179.97,179.75,179.79,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5200),179.81,179.86,179.69,179.72,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5205),179.69,179.76,179.34,179.4,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5210),179.41,179.95,179.08,179.33,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5215),179.33,179.45,179.03,179.32,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5220),179.32,180.48,179.3,180.42,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5225),180.41,180.41,179.88,179.99,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5230),179.98,179.99,179.29,179.52,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5235),179.52,179.69,178.35,178.79,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5240),178.78,179.45,178.73,179.39,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5245),179.38,180.13,179.22,179.7,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5250),179.7,179.98,179.57,179.83,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5255),179.8,179.8,179.75,179.78,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5260),179.79,179.79,179.74,179.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5265),179.77,179.8,179.73,179.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5270),179.8,179.8,179.67,179.8,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5275),179.72,179.73,179.65,179.66,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5280),179.69,179.77,179.61,179.63,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5285),179.66,179.84,179.66,179.78,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5290),179.75,179.98,179.46,179.48,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5295),179.48,179.62,179.07,179.37,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5300),179.36,179.74,178.98,179.09,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5305),179.09,179.32,178.91,179.27,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5310),179.27,179.56,179.26,179.41,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5315),179.41,179.46,179.04,179.14,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5320),179.14,179.34,178.79,178.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5325),178.87,179.32,178.77,178.99,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5330),179.0,179.38,178.86,178.94,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5335),178.91,179.02,178.89,179.01,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5340),179.03,179.14,178.98,179.01,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5345),179.03,179.17,179.03,179.15,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5350),179.47,179.84,179.4,179.76,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5355),179.78,179.8,179.62,179.62,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5360),179.6,179.9,179.6,179.87,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5365),179.87,179.89,179.6,179.7,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5370),179.7,180.93,179.14,180.65,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5375),180.66,180.9,180.36,180.55,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5380),180.55,180.72,180.15,180.36,0));
	        series.addBar(new BaseBar(endTime.plusMinutes(5385),180.35,180.77,180.27,180.74,0));
	}
	private static void AddExampleBars3()
	{
		
		series.addBar(new BaseBar(endTime.plusMinutes(5390),180.75,180.97,180.57,180.94,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5395),180.93,181.11,180.81,181.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5400),181.0,181.11,180.87,180.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5405),180.95,181.02,180.6,180.93,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5410),180.93,181.06,178.99,181.05,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5415),181.05,181.1,180.94,181.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5420),181.1,181.1,181.01,181.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5425),181.02,181.23,181.02,181.17,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5430),181.24,181.26,181.14,181.19,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5435),181.17,181.17,181.05,181.11,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5440),181.14,181.17,181.02,181.05,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5445),181.07,181.24,181.07,181.22,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5450),181.22,181.39,181.12,181.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5455),181.29,181.65,181.23,181.63,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5460),181.62,181.67,181.16,181.4,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5465),181.4,181.67,181.37,181.49,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5470),181.48,181.57,181.35,181.45,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5475),181.46,181.65,181.39,181.43,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5480),181.43,181.49,181.25,181.34,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5485),181.35,181.45,181.18,181.38,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5490),181.38,181.45,181.0,181.42,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5495),180.95,181.46,180.95,181.43,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5500),181.45,181.55,181.45,181.5,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5505),181.47,181.58,181.46,181.55,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5510),181.56,181.63,181.5,181.61,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5515),181.61,181.62,181.53,181.55,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5520),181.57,181.63,181.57,181.62,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5525),181.62,181.62,181.27,181.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5530),181.3,181.4,180.8,180.88,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5535),180.87,181.2,180.82,181.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5540),181.11,181.36,181.05,181.18,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5545),181.18,181.2,180.79,180.87,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5550),180.87,181.07,180.68,181.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5555),181.0,181.09,180.64,180.74,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5560),180.74,180.96,180.71,180.93,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5565),180.93,181.11,180.73,180.77,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5570),180.76,180.96,180.74,180.89,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5575),181.4,181.4,180.75,180.9,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5580),180.9,181.17,180.75,181.07,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5585),181.07,181.35,181.07,181.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5590),180.87,181.37,180.48,180.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5595),180.63,180.81,180.63,180.74,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5600),180.72,180.85,180.64,180.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5605),180.7,180.96,180.66,180.92,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5610),180.92,181.0,180.66,180.9,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5615),180.9,180.93,180.02,180.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5620),180.29,180.3,179.67,179.92,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5625),179.92,179.98,179.44,179.65,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5630),179.65,179.65,179.15,179.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5635),179.36,179.7,179.28,179.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5640),179.7,179.7,179.15,179.22,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5645),179.22,179.28,178.5,178.68,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5650),178.68,180.77,178.59,178.64,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5655),180.75,180.75,178.5,178.64,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5660),178.64,178.8,178.64,178.7,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5665),178.7,178.8,178.7,178.74,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5670),178.8,178.9,178.77,178.84,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5675),178.84,178.85,178.4,178.54,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5680),178.49,178.52,178.31,178.37,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5685),178.37,178.57,178.36,178.55,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5690),178.55,178.95,178.36,178.92,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5695),178.93,178.93,178.29,178.45,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5700),178.45,178.6,178.08,178.46,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5705),178.46,178.73,178.22,178.26,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5710),178.26,178.35,177.76,178.03,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5715),178.03,178.45,178.0,178.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5720),178.33,178.84,178.32,178.72,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5725),178.73,178.75,178.03,178.18,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5730),178.18,178.78,177.93,177.94,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5735),178.72,178.78,177.88,177.94,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5740),177.95,178.14,177.93,178.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5745),178.01,178.11,177.96,178.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5750),178.66,178.78,178.4,178.77,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5755),178.75,178.75,178.51,178.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5760),178.68,178.85,178.68,178.77,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5765),178.77,178.83,178.7,178.7,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5770),178.7,178.76,178.28,178.43,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5775),178.44,178.66,178.0,178.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5780),178.05,178.26,177.77,178.09,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5785),178.08,178.21,177.88,178.15,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5790),178.14,178.42,178.02,178.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5795),178.29,178.53,178.27,178.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5800),178.29,178.3,177.79,178.22,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5805),178.22,178.52,178.02,178.11,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5810),178.1,178.28,177.92,178.09,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5815),178.13,178.13,177.92,177.94,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5820),177.94,178.0,177.92,177.98,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5825),177.96,178.05,177.95,177.97,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5830),177.83,178.36,177.83,178.3,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5835),178.27,178.86,178.23,178.81,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5840),178.8,179.04,178.73,178.83,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5845),178.82,179.16,178.8,179.05,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5850),179.07,179.13,177.04,179.02,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5855),179.01,179.77,178.9,179.7,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5860),179.7,179.81,179.25,179.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5865),179.35,179.45,179.16,179.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5870),179.4,179.41,179.13,179.21,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5875),179.21,179.42,178.97,179.37,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5880),179.37,179.55,179.31,179.45,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5885),179.44,179.48,179.2,179.2,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5890),179.21,179.26,178.1,179.12,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5895),178.11,179.22,178.1,179.05,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5900),179.1,179.12,179.07,179.09,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5905),179.08,179.11,179.01,179.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5910),179.11,179.11,178.97,179.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5915),179.1,179.17,179.02,179.15,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5920),179.17,179.17,179.08,179.13,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5925),179.15,179.38,179.15,179.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5930),179.32,179.46,179.22,179.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5935),179.32,179.41,178.62,178.98,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5940),178.98,179.09,178.37,178.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5945),178.47,178.54,178.25,178.5,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5950),178.5,178.77,178.4,178.72,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5955),178.72,178.81,178.56,178.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5960),178.69,179.12,178.66,178.81,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5965),178.81,179.01,178.62,178.63,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5970),178.62,178.78,178.37,178.42,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5975),179.22,179.22,178.39,178.41,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5980),178.45,178.59,178.45,178.58,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5985),178.58,178.83,178.58,178.74,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5990),178.71,179.19,178.71,179.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(5995),179.05,179.2,178.98,179.17,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6000),179.15,179.15,178.85,178.96,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6005),178.93,178.93,178.75,178.82,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6010),178.85,178.91,178.72,178.86,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6015),178.86,179.09,178.74,178.9,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6020),178.9,179.07,178.64,178.81,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6025),178.81,178.88,178.42,178.49,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6030),178.49,178.61,178.32,178.36,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6035),178.35,178.75,177.87,178.44,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6040),178.44,180.8,177.32,180.52,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6045),180.52,181.73,180.37,181.7,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6050),181.7,181.84,178.4,181.59,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6055),181.59,181.66,181.56,181.62,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6060),181.62,181.82,181.59,181.68,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6065),181.67,181.67,181.51,181.52,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6070),181.54,181.54,181.41,181.47,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6075),181.48,181.49,181.33,181.46,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6080),181.44,181.65,181.4,181.5,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6085),181.52,181.68,181.42,181.43,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6090),181.45,181.61,180.75,180.77,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6095),180.77,181.42,180.72,181.16,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6100),181.16,181.34,180.71,181.22,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6105),181.22,181.44,181.06,181.37,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6110),181.38,181.51,181.22,181.43,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6115),181.42,181.63,181.31,181.54,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6120),181.54,181.6,181.3,181.5,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6125),181.5,181.7,181.36,181.53,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6130),181.52,181.6,181.36,181.37,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6135),181.7,181.77,181.38,181.62,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6140),181.59,181.67,181.55,181.58,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6145),181.56,181.66,181.54,181.56,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6150),180.68,181.02,180.68,180.77,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6155),180.82,180.85,180.7,180.73,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6160),180.79,180.82,180.63,180.67,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6165),180.68,181.6,180.51,180.58,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6170),180.59,180.86,180.53,180.79,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6175),180.79,181.44,180.66,181.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6180),181.32,181.55,181.11,181.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6185),181.35,181.79,181.32,181.75,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6190),181.75,181.91,181.64,181.8,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6195),181.79,181.97,181.72,181.94,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6200),181.94,181.99,181.84,181.89,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6205),181.88,181.9,181.46,181.55,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6210),181.56,181.96,180.55,181.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6215),180.52,181.96,180.52,181.82,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6220),181.81,182.0,181.64,181.75,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6225),181.74,181.97,181.7,181.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6230),182.27,182.44,182.2,182.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6235),182.39,182.56,182.37,182.49,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6240),182.49,182.64,182.44,182.47,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6245),182.47,182.53,182.15,182.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6250),182.3,182.44,182.23,182.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6255),182.34,182.52,182.3,182.46,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6260),182.46,182.58,181.47,182.55,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6265),182.54,182.56,181.58,182.5,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6270),182.5,182.54,182.47,182.49,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6275),182.48,182.48,182.29,182.31,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6280),182.53,182.62,182.53,182.61,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6285),182.58,182.63,182.56,182.59,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6290),182.58,182.64,182.51,182.51,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6295),182.5,182.5,182.38,182.46,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6300),182.44,182.59,182.43,182.57,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6305),182.56,182.71,182.49,182.64,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6310),182.65,182.89,182.62,182.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6315),182.86,182.99,182.81,182.94,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6320),182.96,183.01,182.88,182.96,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6325),182.96,183.45,182.45,183.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6330),182.54,183.43,182.54,182.93,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6335),183.35,183.35,183.3,183.32,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6340),183.31,183.35,183.3,183.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6345),183.3,183.36,183.24,183.36,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6350),183.33,183.33,183.33,183.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6355),183.33,183.49,183.28,183.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6360),183.38,183.44,183.28,183.36,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6365),183.37,183.51,183.31,183.49,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6370),183.48,183.53,183.32,183.36,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6375),183.36,183.63,183.36,183.46,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6380),183.46,183.64,183.39,183.56,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6385),183.56,183.73,183.48,183.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6390),183.69,183.87,183.67,183.87,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6395),183.87,183.9,183.74,183.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6400),183.84,183.96,183.79,183.87,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6405),183.86,183.89,183.67,183.83,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6410),183.85,183.88,183.81,183.82,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6415),183.82,183.83,183.75,183.76,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6420),183.75,183.78,183.66,183.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6425),183.76,183.76,183.72,183.72,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6430),183.7,183.7,183.53,183.61,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6435),183.66,183.78,183.66,183.73,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6440),183.73,183.97,183.7,183.97,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6445),183.64,183.97,183.59,183.94,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6450),183.94,184.18,183.93,184.03,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6455),184.03,184.13,183.66,183.67,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6460),183.67,183.98,183.66,183.89,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6465),183.89,183.92,183.71,183.74,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6470),183.74,183.82,183.69,183.79,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6475),183.78,183.94,183.76,183.77,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6480),183.76,183.91,183.76,183.83,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6485),183.84,184.07,183.81,183.89,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6490),183.84,183.9,183.68,183.7,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6495),183.69,183.8,183.69,183.79,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6500),183.8,183.85,183.77,183.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6505),183.83,183.83,183.78,183.83,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6510),183.83,184.02,183.83,184.02,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6515),183.98,184.08,183.95,183.98,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6520),183.96,184.0,183.89,183.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6525),183.95,184.01,183.84,183.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6530),183.85,184.02,183.67,183.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6535),183.69,183.8,183.58,183.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6540),183.68,183.88,183.64,183.72,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6545),183.72,183.75,183.58,183.63,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6550),183.62,183.72,183.58,183.67,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6555),183.68,183.87,183.67,183.82,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6560),183.83,183.84,183.65,183.81,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6565),183.81,183.85,183.6,183.77,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6570),183.84,183.84,183.75,183.75,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6575),183.78,183.78,183.73,183.74,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6580),183.74,183.77,183.74,183.75,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6585),183.81,183.82,183.81,183.82,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6590),183.82,183.82,183.76,183.76,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6595),183.84,183.84,183.78,183.78,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6600),183.81,183.97,183.76,183.97,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6605),183.95,184.13,183.94,184.07,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6610),184.07,184.18,183.97,184.13,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6615),184.13,184.34,184.13,184.34,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6620),184.34,184.6,184.33,184.42,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6625),184.42,184.47,184.27,184.38,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6630),184.38,184.41,184.25,184.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6635),184.36,184.38,183.95,184.08,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6640),184.08,184.68,183.93,184.56,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6645),184.55,184.89,184.51,184.84,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6650),183.82,184.97,183.82,184.89,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6655),184.9,184.94,184.89,184.9,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6660),184.9,184.98,184.9,184.98,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6665),184.84,184.84,183.87,184.15,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6670),184.0,184.25,183.92,184.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6675),184.11,184.31,183.75,184.12,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6680),184.11,184.15,183.82,183.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6685),183.85,184.17,183.82,183.93,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6690),183.89,184.67,183.29,183.56,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6695),183.55,183.69,183.12,183.47,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6700),183.48,183.62,182.86,183.02,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6705),183.02,183.14,182.74,183.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6710),183.01,183.09,182.73,182.97,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6715),182.96,183.09,182.48,182.76,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6720),182.76,183.2,182.74,182.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6725),182.95,184.57,182.92,182.98,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6730),184.67,184.67,182.93,183.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6735),183.01,183.07,182.99,183.07,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6740),183.08,183.1,183.02,183.09,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6745),182.88,183.03,182.78,183.03,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6750),183.13,183.13,183.07,183.07,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6755),183.08,183.13,183.02,183.02,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6760),183.03,183.33,183.03,183.28,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6765),183.24,183.48,183.24,183.28,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6770),183.3,183.54,183.1,183.46,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6775),183.45,183.6,183.23,183.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6780),183.36,183.44,182.68,182.74,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6785),182.74,183.0,182.63,182.87,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6790),182.87,182.96,182.69,182.83,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6795),182.83,183.32,182.8,183.2,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6800),183.2,183.5,182.78,182.8,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6805),182.8,183.18,182.67,182.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6810),182.92,183.13,182.62,182.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6815),182.67,182.7,182.62,182.67,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6820),182.69,182.75,182.62,182.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6825),182.64,182.91,182.64,182.91,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6830),182.95,183.04,182.9,182.94,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6835),182.99,183.13,182.99,183.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6840),183.04,183.32,183.03,183.17,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6845),183.17,183.28,183.15,183.26,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6850),183.26,183.56,182.72,182.98,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6855),182.97,183.1,182.54,182.56,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6860),182.56,182.68,182.08,182.37,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6865),182.37,182.44,182.15,182.34,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6870),182.35,182.79,182.18,182.68,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6875),182.68,182.93,182.53,182.86,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6880),182.85,182.87,182.34,182.4,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6885),182.39,183.47,182.27,182.31,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6890),182.88,182.88,182.29,182.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6895),182.33,182.5,182.33,182.38,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6900),182.4,182.42,182.36,182.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6905),182.8,182.91,182.8,182.9,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6910),182.9,183.14,182.9,183.12,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6915),183.14,183.14,182.95,182.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6920),182.96,183.18,182.92,183.03,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6925),183.02,183.13,182.94,183.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6930),183.08,183.51,182.95,183.49,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6935),183.49,183.79,183.29,183.63,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6940),183.63,183.66,183.36,183.4,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6945),183.4,183.6,183.32,183.59,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6950),183.59,183.65,183.33,183.36,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6955),183.35,183.56,183.14,183.49,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6960),183.48,183.73,183.44,183.44,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6965),183.45,183.52,182.42,183.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6970),182.36,183.42,182.36,183.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6975),183.39,183.42,183.37,183.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6980),183.38,183.38,183.28,183.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6985),183.51,183.51,183.31,183.31,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6990),183.28,183.31,183.09,183.14,0));
        series.addBar(new BaseBar(endTime.plusMinutes(6995),183.22,183.27,183.11,183.13,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7000),183.13,183.31,183.12,183.26,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7005),183.21,183.78,183.05,183.3,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7010),183.29,183.58,182.89,183.24,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7015),183.24,183.83,183.11,183.59,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7020),183.59,183.77,183.37,183.7,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7025),183.71,183.77,183.42,183.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7030),183.48,183.65,183.34,183.59,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7035),183.58,183.73,183.23,183.38,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7040),183.38,183.6,182.97,183.53,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7045),183.54,183.68,183.41,183.57,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7050),183.56,183.65,183.55,183.55,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7055),183.55,183.55,183.5,183.5,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7060),183.5,183.54,183.49,183.52,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7065),183.38,184.19,183.38,184.13,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7070),184.13,184.22,184.09,184.14,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7075),184.16,184.17,184.06,184.08,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7080),184.03,184.15,184.03,184.06,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7085),184.06,184.12,183.88,183.92,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7090),183.91,184.14,183.77,184.03,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7095),184.03,184.1,182.93,183.12,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7100),183.12,183.22,182.79,183.18,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7105),183.18,183.59,183.13,183.59,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7110),183.58,183.61,183.23,183.27,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7115),183.27,184.01,182.98,183.42,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7120),183.41,184.03,183.37,183.63,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7125),183.63,184.03,183.55,184.03,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7130),183.52,184.04,183.52,183.71,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7135),183.71,183.71,183.63,183.67,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7140),183.67,183.74,183.67,183.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7145),183.8,184.14,183.8,184.09,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7150),184.09,184.25,184.09,184.25,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7155),184.23,184.32,184.21,184.22,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7160),184.25,184.45,184.22,184.41,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7165),184.42,184.57,183.26,184.17,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7170),184.14,184.22,183.67,183.68,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7175),183.68,183.68,183.12,183.49,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7180),183.5,183.56,183.01,183.17,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7185),183.17,183.74,183.07,183.44,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7190),183.43,183.72,183.38,183.43,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7195),183.44,183.69,183.42,183.53,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7200),183.54,184.08,183.54,184.07,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7205),184.08,184.21,183.36,184.21,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7210),183.64,184.29,183.64,184.11,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7215),184.12,184.2,184.11,184.19,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7220),184.18,184.25,184.15,184.18,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7225),183.8,183.84,183.61,183.84,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7230),183.81,183.81,183.61,183.71,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7235),183.68,183.69,183.42,183.54,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7240),183.54,183.76,183.5,183.71,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7245),183.74,183.77,183.49,183.61,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7250),183.62,184.0,183.53,183.78,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7255),183.78,184.18,183.78,184.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7260),184.01,184.02,183.79,183.92,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7265),183.92,183.94,182.96,182.96,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7270),182.96,183.03,182.54,182.79,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7275),182.78,182.82,181.96,182.09,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7280),182.09,182.09,181.34,181.73,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7285),181.73,184.05,181.63,181.84,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7290),181.85,181.93,181.79,181.88,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7295),181.88,181.97,181.88,181.94,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7300),181.96,181.99,181.91,181.93,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7305),182.08,182.15,182.0,182.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7310),181.99,182.05,181.97,182.05,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7315),182.05,182.2,182.04,182.12,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7320),182.14,182.28,181.99,182.24,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7325),182.05,182.37,182.04,182.34,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7330),182.35,182.56,181.95,182.17,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7335),182.17,182.93,182.16,182.87,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7340),182.87,183.27,182.8,183.13,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7345),183.13,183.57,183.07,183.32,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7350),183.32,183.6,183.26,183.37,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7355),183.36,183.63,183.21,183.46,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7360),183.47,183.78,183.42,183.68,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7365),183.68,183.72,181.74,183.62,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7370),181.66,183.71,181.66,183.71,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7375),183.68,183.76,183.58,183.7,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7380),183.68,183.7,183.63,183.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7385),183.95,183.99,183.89,183.9,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7390),183.9,183.96,183.86,183.91,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7395),183.94,184.13,183.94,184.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7400),184.04,184.1,183.99,184.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7405),184.08,184.08,183.8,184.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7410),184.01,184.32,183.98,184.25,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7415),184.24,184.69,184.23,184.52,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7420),184.53,184.94,184.44,184.6,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7425),184.61,184.83,184.52,184.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7430),184.66,184.76,184.4,184.45,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7435),184.45,184.82,184.36,184.8,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7440),184.8,184.86,184.48,184.67,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7445),184.67,184.69,183.71,184.54,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7450),183.72,184.57,183.72,184.51,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7455),184.55,184.6,184.48,184.6,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7460),184.62,184.72,184.62,184.7,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7465),184.71,184.71,184.25,184.38,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7470),184.31,184.41,184.25,184.41,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7475),184.39,184.41,184.26,184.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7480),184.32,184.44,184.13,184.22,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7485),184.24,184.39,184.08,184.19,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7490),184.18,184.48,184.03,184.32,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7495),184.31,184.46,183.89,183.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7500),183.95,184.34,183.83,184.28,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7505),184.27,184.31,184.03,184.28,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7510),184.28,184.5,184.15,184.27,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7515),184.27,184.39,184.16,184.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7520),184.33,184.43,184.07,184.4,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7525),184.42,184.43,183.96,184.08,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7530),184.69,184.69,184.11,184.2,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7535),184.19,184.2,183.99,184.02,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7540),184.05,184.12,184.02,184.12,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7545),184.12,184.28,184.12,184.28,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7550),184.3,184.63,184.3,184.54,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7555),184.53,184.63,184.53,184.61,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7560),184.61,184.63,184.36,184.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7565),184.58,184.58,184.01,184.19,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7570),184.19,184.3,183.78,184.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7575),183.99,184.23,183.83,184.21,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7580),184.21,184.36,184.17,184.28,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7585),184.28,184.45,184.22,184.23,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7590),184.23,184.29,183.89,184.08,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7595),184.09,184.17,183.87,183.92,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7600),183.92,184.02,183.32,183.61,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7605),183.6,184.42,183.54,183.79,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7610),184.39,184.39,183.64,183.64,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7615),183.64,183.65,183.57,183.64,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7620),183.61,183.7,183.6,183.6,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7625),184.15,184.3,184.13,184.16,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7630),184.12,184.35,184.12,184.26,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7635),184.27,184.41,184.24,184.3,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7640),184.34,184.47,184.24,184.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7645),184.39,184.72,183.63,184.58,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7650),184.54,184.77,184.5,184.59,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7655),184.58,184.62,183.83,184.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7660),184.01,184.07,183.09,183.36,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7665),183.36,183.74,183.05,183.74,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7670),183.73,184.2,183.73,184.05,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7675),184.05,184.24,183.91,184.06,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7680),184.07,184.23,183.89,184.19,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7685),184.2,184.29,183.7,183.7,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7690),183.65,184.15,183.65,184.12,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7695),184.13,184.13,184.02,184.05,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7700),184.05,184.14,184.05,184.14,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7705),184.3,184.33,184.16,184.16,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7710),184.21,184.21,183.97,184.02,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7715),183.92,184.1,183.9,184.06,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7720),184.14,184.2,184.01,184.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7725),184.06,184.36,183.99,184.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7730),184.3,184.6,184.11,184.23,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7735),184.23,184.45,183.93,183.99,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7740),183.98,184.21,183.91,184.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7745),184.1,184.44,184.02,184.37,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7750),184.37,184.52,184.12,184.24,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7755),184.24,184.43,184.2,184.34,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7760),184.34,184.57,184.31,184.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7765),184.35,184.46,183.98,184.31,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7770),184.18,184.34,184.17,184.26,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7775),184.26,184.74,184.26,184.71,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7780),184.64,184.67,184.54,184.55,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7785),184.02,184.03,183.65,183.76,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7790),183.85,183.88,183.77,183.83,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7795),183.78,183.78,183.44,183.51,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7800),183.51,183.75,183.44,183.73,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7805),183.68,184.73,183.2,183.25,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7810),183.25,183.42,182.73,182.82,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7815),182.82,183.05,182.4,182.5,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7820),182.5,182.74,182.27,182.72,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7825),182.72,182.76,182.24,182.47,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7830),182.47,182.48,181.82,181.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7835),181.86,182.45,181.84,182.41,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7840),182.41,182.78,182.26,182.78,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7845),182.78,184.34,182.5,182.91,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7850),184.3,184.3,182.78,182.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7855),182.85,182.89,182.77,182.8,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7860),182.8,182.97,182.8,182.84,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7865),182.82,182.82,182.1,182.22,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7870),182.14,182.17,181.68,181.68,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7875),181.62,181.78,181.25,181.78,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7880),181.79,181.82,181.44,181.63,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7885),181.74,182.02,181.5,181.84,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7890),181.81,181.82,180.98,181.34,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7895),181.34,181.56,180.36,180.64,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7900),180.65,180.82,179.88,180.58,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7905),180.57,180.64,180.09,180.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7910),180.35,180.48,180.08,180.14,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7915),180.14,180.14,179.5,179.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7920),179.68,179.76,178.87,178.88,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7925),178.89,182.7,178.43,178.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7930),178.48,178.82,178.43,178.79,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7935),178.79,178.82,178.52,178.59,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7940),178.62,178.69,178.56,178.59,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7945),179.05,179.28,178.71,179.13,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7950),179.12,179.16,178.67,179.14,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7955),179.11,179.49,178.88,179.43,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7960),179.43,179.63,179.27,179.31,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7965),179.28,179.5,178.73,179.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7970),179.34,179.52,178.88,179.3,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7975),179.3,179.4,178.07,178.36,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7980),178.35,178.61,177.75,177.83,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7985),177.84,177.85,177.12,177.58,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7990),177.58,178.74,177.47,178.43,0));
        series.addBar(new BaseBar(endTime.plusMinutes(7995),178.44,179.27,178.44,179.2,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8000),179.19,179.25,177.98,178.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8005),178.01,179.07,177.56,177.74,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8010),178.83,178.83,177.43,177.56,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8015),177.56,178.08,177.54,177.96,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8020),177.97,178.06,177.88,178.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8025),178.62,179.07,178.6,178.97,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8030),178.97,178.97,178.84,178.9,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8035),178.93,178.93,178.65,178.86,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8040),178.84,178.86,178.47,178.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8045),178.96,178.96,177.99,178.58,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8050),178.58,178.95,178.02,178.87,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8055),178.89,179.26,178.74,179.06,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8060),179.06,179.3,178.28,178.59,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8065),178.59,179.01,178.48,178.81,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8070),178.83,179.08,178.72,178.86,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8075),178.86,179.19,178.78,179.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8080),179.11,179.27,178.92,179.07,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8085),179.07,179.33,178.03,179.3,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8090),178.01,180.11,178.01,179.89,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8095),179.88,180.34,179.76,179.93,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8100),179.93,180.19,179.93,180.17,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8105),180.07,180.07,179.41,179.72,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8110),179.7,179.7,179.26,179.4,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8115),179.35,179.35,178.82,179.11,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8120),179.19,179.47,178.36,178.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8125),178.7,179.39,177.67,177.96,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8130),177.98,178.21,177.27,177.97,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8135),177.96,178.38,177.57,178.09,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8140),178.09,178.55,178.09,178.49,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8145),178.49,178.54,177.79,178.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8150),178.04,178.07,177.66,178.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8155),178.01,178.43,176.88,177.27,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8160),177.27,177.71,176.93,177.32,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8165),177.32,179.14,177.27,177.44,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8170),177.43,177.63,177.4,177.54,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8175),177.51,178.05,177.49,177.99,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8180),177.95,177.96,177.69,177.89,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8185),177.81,178.03,177.53,177.86,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8190),177.8,178.07,177.8,177.98,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8195),177.97,178.34,177.88,178.16,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8200),178.12,178.19,177.96,178.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8205),178.1,178.54,177.79,178.53,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8210),178.53,178.99,178.46,178.55,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8215),178.54,178.93,178.26,178.78,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8220),178.78,179.66,178.64,179.53,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8225),179.53,179.81,179.39,179.7,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8230),179.7,179.7,179.34,179.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8235),179.39,179.51,179.09,179.38,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8240),179.38,179.69,179.21,179.32,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8245),179.32,179.36,177.32,178.89,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8250),178.89,179.36,178.69,178.76,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8255),178.78,179.23,178.66,178.82,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8260),178.82,178.88,178.68,178.87,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8265),178.36,178.36,177.72,177.82,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8270),177.84,177.84,177.29,177.6,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8275),177.61,177.79,176.87,177.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8280),176.98,177.66,176.93,177.22,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8285),177.54,177.62,176.57,176.88,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8290),176.88,177.71,176.79,177.42,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8295),177.4,178.13,177.25,177.89,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8300),177.88,178.68,177.79,178.58,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8305),178.59,178.86,178.22,178.54,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8310),178.54,179.05,178.22,179.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8315),179.04,179.29,178.85,178.92,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8320),178.92,178.92,177.93,178.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8325),178.1,179.31,177.52,178.11,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8330),179.23,179.23,178.08,178.09,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8335),178.13,178.16,178.07,178.12,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8340),178.12,178.18,178.1,178.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8345),178.18,178.35,177.81,177.88,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8350),177.93,178.11,177.62,177.72,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8355),177.73,178.2,177.68,178.06,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8360),178.09,178.55,178.01,178.55,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8365),178.11,178.66,178.08,178.13,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8370),178.14,178.37,177.4,177.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8375),177.68,177.68,176.21,176.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8380),176.47,176.48,175.32,175.41,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8385),175.4,175.61,174.98,175.3,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8390),175.3,175.4,174.28,174.51,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8395),174.5,174.86,174.05,174.05,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8400),174.05,174.47,173.83,174.17,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8405),174.17,178.18,173.72,174.15,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8410),178.18,178.18,174.0,174.21,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8415),174.21,174.41,174.14,174.3,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8420),174.3,174.5,174.17,174.5,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8425),174.6,174.8,174.49,174.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8430),174.69,174.74,174.27,174.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8435),174.33,174.51,174.23,174.36,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8440),174.38,174.5,174.24,174.43,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8445),174.44,175.06,174.24,175.02,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8450),174.99,175.3,174.34,175.15,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8455),175.15,175.26,174.64,174.77,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8460),174.78,175.59,174.68,175.57,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8465),175.58,175.76,175.39,175.63,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8470),175.64,175.84,175.01,175.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8475),175.1,175.61,174.96,175.53,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8480),175.52,175.65,174.84,175.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8485),175.39,175.44,174.11,174.94,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8490),174.15,175.36,174.15,174.92,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8495),174.94,175.08,174.94,175.07,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8500),175.05,175.05,174.78,174.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8505),174.76,174.92,174.69,174.79,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8510),174.75,174.88,174.61,174.62,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8515),174.66,174.76,174.42,174.52,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8520),174.55,174.71,174.3,174.37,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8525),174.37,174.88,173.94,174.76,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8530),174.75,175.19,174.37,174.52,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8535),174.53,175.4,173.71,174.31,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8540),174.32,175.26,173.92,175.14,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8545),175.14,175.36,174.91,175.18,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8550),175.18,175.56,175.16,175.41,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8555),175.41,175.56,174.93,175.02,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8560),175.02,175.54,174.92,175.14,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8565),175.14,175.52,174.8,175.07,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8570),175.36,175.36,174.95,174.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8575),174.95,175.1,174.94,175.1,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8580),175.02,175.39,175.02,175.24,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8585),175.26,175.97,175.25,175.84,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8590),175.79,176.07,175.79,175.81,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8595),175.78,175.9,175.71,175.75,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8600),175.73,175.8,174.97,175.4,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8605),175.4,176.01,174.95,175.36,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8610),175.36,176.47,175.22,176.42,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8615),176.42,177.14,176.0,176.92,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8620),176.91,177.18,175.22,176.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8625),176.86,177.16,176.86,176.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8630),176.95,177.05,176.67,176.84,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8635),176.84,177.4,176.84,177.28,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8640),177.29,177.48,177.16,177.43,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8645),177.42,177.48,175.22,177.28,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8650),175.17,177.34,175.17,177.25,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8655),177.25,177.48,177.2,177.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8660),177.32,177.32,177.23,177.24,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8665),177.29,177.81,177.29,177.76,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8670),177.74,177.76,177.62,177.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8675),177.66,177.76,177.54,177.76,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8680),177.65,177.91,177.65,177.89,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8685),177.89,178.62,176.35,178.43,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8690),178.43,178.87,178.04,178.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8695),178.48,178.7,177.73,178.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8700),178.3,178.93,178.15,178.93,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8705),178.93,178.94,178.68,178.88,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8710),178.87,179.44,178.86,179.37,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8715),179.37,179.52,179.27,179.41,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8720),179.42,179.87,179.42,179.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8725),179.69,179.88,177.39,179.79,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8730),177.48,179.88,177.48,179.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8735),179.85,179.89,179.75,179.8,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8740),179.8,179.89,179.8,179.86,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8745),179.73,179.73,179.29,179.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8750),179.46,179.52,179.3,179.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8755),179.32,179.41,179.15,179.26,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8760),179.26,179.51,179.24,179.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8765),179.31,179.63,179.1,179.53,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8770),179.51,179.84,179.34,179.73,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8775),179.72,179.79,179.22,179.38,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8780),179.38,179.77,179.21,179.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8785),179.66,179.72,179.53,179.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8790),179.67,179.78,179.52,179.71,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8795),179.72,180.07,179.69,179.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8800),179.95,180.02,179.75,180.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8805),180.01,180.05,179.66,180.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8810),180.02,180.09,180.0,180.08,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8815),180.09,180.15,179.97,180.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8820),180.03,180.28,180.0,180.14,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8825),180.62,180.83,180.6,180.81,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8830),180.78,180.94,180.75,180.76,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8835),180.73,180.79,180.5,180.5,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8840),180.52,180.67,180.48,180.6,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8845),180.6,180.81,180.16,180.2,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8850),180.18,180.73,180.03,180.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8855),180.48,180.91,180.38,180.76,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8860),180.76,181.51,180.74,181.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8865),181.48,181.6,181.3,181.58,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8870),181.58,182.01,181.58,181.81,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8875),181.8,182.21,181.73,182.17,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8880),182.18,182.44,181.88,181.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8885),181.95,182.03,180.04,181.77,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8890),180.01,181.97,180.01,181.9,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8895),181.87,181.9,181.68,181.82,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8900),181.81,181.84,181.71,181.71,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8905),182.0,182.33,182.0,182.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8910),182.29,182.36,182.16,182.23,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8915),182.14,182.15,181.8,181.82,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8920),181.82,181.98,181.74,181.84,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8925),182.25,182.25,181.7,181.96,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8930),181.95,182.81,181.95,182.77,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8935),182.78,182.83,181.92,182.06,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8940),182.06,182.32,181.71,181.91,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8945),181.91,182.24,181.82,182.15,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8950),182.15,182.4,182.05,182.24,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8955),182.25,182.34,181.76,181.88,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8960),181.88,182.14,181.78,182.08,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8965),182.07,182.35,182.06,182.17,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8970),181.98,182.22,181.98,182.15,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8975),182.15,182.16,182.0,182.03,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8980),182.05,182.13,181.94,182.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8985),181.9,181.9,181.25,181.4,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8990),181.44,181.61,181.35,181.42,0));
        series.addBar(new BaseBar(endTime.plusMinutes(8995),181.35,181.39,181.15,181.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9000),181.29,181.49,181.25,181.34,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9005),181.34,181.44,180.75,181.13,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9010),181.11,181.61,180.83,181.55,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9015),181.55,182.44,181.46,182.34,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9020),182.34,182.65,182.21,182.44,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9025),182.45,182.66,182.31,182.6,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9030),182.6,183.08,182.55,183.08,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9035),183.07,183.2,182.87,183.13,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9040),183.13,183.18,182.95,183.05,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9045),183.06,183.14,182.08,183.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9050),182.16,183.14,182.16,182.96,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9055),182.94,182.95,182.72,182.87,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9060),182.85,183.04,182.84,182.97,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9065),182.9,183.05,182.58,182.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9070),182.95,182.95,182.85,182.87,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9075),182.87,183.14,182.87,183.11,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9080),183.12,183.19,183.0,183.08,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9085),183.07,183.11,182.83,182.95,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9090),182.96,183.23,182.58,183.13,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9095),183.13,183.78,182.89,183.5,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9100),183.49,183.74,183.41,183.61,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9105),183.61,183.73,183.53,183.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9110),183.66,184.07,183.61,184.03,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9115),184.03,184.36,184.0,184.29,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9120),184.28,184.31,183.92,184.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9125),184.01,184.06,183.15,183.99,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9130),183.99,184.01,183.69,183.97,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9135),183.97,184.01,183.95,184.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9140),184.0,184.01,183.95,184.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9145),184.2,184.2,183.77,183.81,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9150),183.85,183.97,183.79,183.89,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9155),183.84,183.93,183.73,183.88,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9160),183.89,184.32,183.87,184.27,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9165),184.0,184.32,183.85,184.03,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9170),184.03,184.46,183.9,184.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9175),184.04,184.28,183.65,184.2,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9180),184.2,184.49,184.12,184.22,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9185),184.22,184.45,184.18,184.35,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9190),184.35,184.4,184.11,184.24,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9195),184.25,184.48,184.24,184.34,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9200),184.35,184.47,184.2,184.24,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9205),184.26,184.4,183.98,184.27,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9210),184.01,184.32,184.01,184.25,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9215),184.24,184.25,183.98,183.98,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9220),183.97,184.08,183.94,184.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9225),184.0,184.1,184.0,184.07,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9230),184.04,184.07,183.82,183.82,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9235),183.89,183.89,183.54,183.63,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9240),183.58,183.68,183.54,183.66,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9245),183.66,183.91,183.56,183.78,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9250),183.78,184.46,183.64,184.46,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9255),184.46,184.95,184.24,184.67,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9260),184.68,184.75,184.11,184.11,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9265),184.11,184.19,183.57,183.88,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9270),183.88,184.27,183.85,184.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9275),184.01,184.41,183.66,183.86,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9280),183.86,183.95,182.87,183.02,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9285),183.02,184.28,182.99,183.15,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9290),184.26,184.26,183.1,183.16,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9295),183.16,183.38,183.13,183.24,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9300),183.24,183.35,183.24,183.3,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9305),182.8,182.9,182.7,182.71,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9310),182.73,183.07,182.73,182.91,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9315),182.93,182.93,182.75,182.9,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9320),182.9,183.0,182.45,182.59,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9325),182.57,183.46,182.46,183.26,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9330),183.25,183.64,182.85,182.96,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9335),182.94,183.87,182.6,183.7,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9340),183.71,183.94,183.54,183.75,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9345),183.75,184.28,183.7,184.14,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9350),184.15,184.27,184.11,184.21,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9355),184.21,184.37,183.98,184.34,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9360),184.33,184.52,184.16,184.16,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9365),184.16,184.3,183.11,184.06,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9370),183.05,184.11,183.05,184.09,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9375),184.09,184.15,184.06,184.14,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9380),184.15,184.43,184.15,184.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9385),184.47,184.56,184.35,184.56,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9390),184.52,184.52,184.4,184.41,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9395),184.42,184.53,184.35,184.51,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9400),184.51,184.59,184.42,184.54,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9405),184.53,184.56,184.29,184.34,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9410),184.34,184.72,184.16,184.6,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9415),184.59,184.82,184.24,184.73,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9420),184.73,184.89,184.53,184.68,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9425),184.68,184.77,184.36,184.54,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9430),184.53,184.72,184.37,184.46,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9435),184.46,184.5,183.95,184.15,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9440),184.15,184.44,183.8,183.91,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9445),183.91,184.85,183.85,183.92,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9450),183.92,184.73,183.7,183.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9455),183.82,183.88,183.8,183.83,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9460),183.85,183.88,183.8,183.87,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9465),184.0,184.43,184.0,184.19,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9470),184.15,184.15,183.95,183.98,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9475),183.98,184.32,183.98,184.32,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9480),184.29,184.47,184.29,184.37,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9485),184.38,184.59,184.32,184.36,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9490),184.36,185.45,184.19,185.36,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9495),185.37,185.69,185.16,185.55,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9500),185.56,186.11,185.46,186.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9505),186.05,186.15,185.91,185.93,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9510),185.93,186.0,185.73,185.75,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9515),185.75,185.97,185.71,185.71,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9520),185.72,185.72,184.98,184.98,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9525),184.98,186.12,183.89,184.97,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9530),184.99,184.99,184.88,184.92,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9535),184.92,184.99,184.83,184.99,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9540),185.0,185.09,184.99,185.07,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9545),184.77,184.87,184.72,184.72,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9550),184.73,184.84,184.73,184.79,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9555),184.78,184.78,184.61,184.73,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9560),184.71,184.81,184.6,184.68,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9565),184.67,185.04,184.61,184.93,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9570),184.93,185.26,184.28,184.46,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9575),184.44,185.1,184.23,185.0,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9580),185.0,185.59,184.82,185.03,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9585),185.02,185.2,184.9,185.13,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9590),185.13,185.39,185.02,185.09,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9595),185.09,185.2,184.74,184.9,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9600),184.91,184.91,184.48,184.8,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9605),184.8,185.23,184.77,185.23,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9610),184.91,185.35,184.91,185.26,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9615),185.26,185.29,185.2,185.2,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9620),185.21,185.38,185.21,185.31,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9625),185.54,185.57,185.37,185.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9630),185.43,185.56,185.43,185.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9635),185.44,185.56,185.42,185.45,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9640),185.47,185.51,185.28,185.41,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9645),185.41,185.64,185.27,185.32,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9650),185.32,185.36,184.54,184.64,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9655),184.62,185.6,184.62,185.25,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9660),185.25,185.58,185.11,185.26,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9665),185.25,185.53,185.24,185.38,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9670),185.38,185.5,185.09,185.16,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9675),185.15,185.26,184.33,184.73,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9680),184.73,184.92,184.35,184.83,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9685),184.82,184.99,184.65,184.81,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9690),184.81,185.01,184.81,184.98,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9695),184.96,184.96,184.79,184.88,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9700),184.89,184.9,184.75,184.86,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9705),184.91,185.1,184.21,184.21,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9710),184.17,184.38,183.83,183.93,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9715),183.93,184.2,183.87,184.15,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9720),184.12,184.4,184.06,184.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9725),184.29,184.76,183.99,184.58,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9730),184.59,185.05,184.37,184.69,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9735),184.69,185.09,184.42,184.97,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9740),184.97,185.45,184.87,185.23,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9745),185.23,185.36,185.01,185.26,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9750),185.25,185.81,185.21,185.65,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9755),185.65,185.75,185.28,185.48,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9760),185.48,185.84,185.32,185.84,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9765),185.85,186.04,184.72,186.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9770),186.03,186.07,184.9,185.96,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9775),185.95,185.95,184.9,185.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9780),185.82,185.92,185.8,185.85,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9785),185.83,185.83,185.45,185.75,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9790),185.68,185.78,185.57,185.77,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9795),185.72,185.72,185.56,185.64,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9800),185.61,185.79,185.49,185.64,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9805),185.77,185.87,185.56,185.71,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9810),185.7,186.46,185.66,186.39,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9815),186.39,186.96,186.38,186.81,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9820),186.8,187.15,186.56,187.04,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9825),187.04,187.12,186.93,187.01,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9830),187.0,187.13,186.82,186.86,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9835),186.86,186.87,185.95,186.02,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9840),186.03,186.41,185.05,186.33,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9845),186.31,187.0,185.84,186.52,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9850),186.5,186.55,186.25,186.32,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9855),186.31,186.37,186.28,186.3,0));
        series.addBar(new BaseBar(endTime.plusMinutes(9860),186.3,186.33,186.26,186.29,0));

	}
	public static void main(String[] args) throws InterruptedException {
			
	
		/*AddExampleBars1();
		AddExampleBars2();
		AddExampleBars3();
		
		
		ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
		MACDIndicator macd = new MACDIndicator(closePrice, 12, 26);
		EMAIndicator macd_emea = new EMAIndicator(closePrice, (int) 14);
		SMAIndicator sma = new SMAIndicator(closePrice, (int) 20);
		ATRIndicator atr = new ATRIndicator(series, (int) 14);
		MinusDIIndicator MinusDIIndicator    = new MinusDIIndicator(series, (int) 14);
		PlusDIIndicator PlusDIIndicator      = new PlusDIIndicator(series, (int) 14);
		ADXIndicator ADXIndicator            = new ADXIndicator(series, (int) 14);
		
		
			System.out.println("MACDIndicator:"+ ":" +  macd.getValue(1971));
				
			for (int j=0;j<25;j++)
			{ 
			 System.out.println("EMAIndicator:" + j + ":" +  macd_emea.getValue(j));	
			}
			
			System.out.println("EMAIndicator:" +  macd_emea.getValue(1971));	
				
			System.out.println("SMAIndicator:" +":" +  sma.getValue(1971));
		
			System.out.println("ATRIndicator:" +":" +  atr.getValue(1971));
		
			System.out.println("MinusDIIndicator:" +":" +  MinusDIIndicator.getValue(1971));
		
			System.out.println("PlusDIIndicator:" +":" +  PlusDIIndicator.getValue(1971));
				
		
		
			System.out.println("ADXIndicator::" +  ADXIndicator.getValue(1971));
				*/
		
		

		
		TIMApiWrapper wrapper = new TIMApiWrapper(6,false);
		final EClientSocket m_client = wrapper.getClient();
		final EReaderSignal m_signal = wrapper.getSignal(); 
		//! [connect]
		m_client.eConnect("127.0.0.1", 7499, 13); 
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

		Contract contract = new Contract();
		contract = new FutContract( "ESTX50", "201809");		    			
		contract.exchange("DTB");
		contract.currency("USD");
		
		
	/* 	contract.symbol("DAX");
		contract.secType("FUT");
		contract.currency("EUR");
		contract.exchange("DTB");
		contract.lastTradeDateOrContractMonth("2");
		contract.multiplier("5");
		*/
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Calendar d = Calendar.getInstance();
		d.add(Calendar.MONTH, -6);
		String queryTime = sd.format(d.getTime());		
		List<TagValue> list = new ArrayList<TagValue>();
		m_client.reqMarketDataType(ConfigKeys.MARKET_DATA_TYPE_DELAYED_LIVE);
		m_client.reqMktData(4003, contract,  "", false, false, null); // false
		 
		
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
