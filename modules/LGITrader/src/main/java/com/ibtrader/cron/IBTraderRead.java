package com.ibtrader.cron;

import com.ib.client.Contract;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Order;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.OrderLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.data.service.persistence.ConfigUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;


import com.liferay.portal.kernel.messaging.BaseSchedulerEntryMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.tim.service.TIMApiGITrader;
import com.ibtrader.util.Utilidades;
import com.trader.dashboard.portlet.portlet.GITraderPortlet;





@Component(immediate = true, service = IBTraderRead.class)
public class IBTraderRead  extends BaseSchedulerEntryMessageListener {

	Log _log = LogFactoryUtil.getLog(IBTraderRead.class);
	@Override
	protected void doReceive(Message message) throws Exception {
		
		int 	_CLIENT_ID = 6;	  // el dos para leer, el 3 para escribir			
		String  _HOST = "127.0.0.1";
		int     _PORT = 7497;
		
		 /* UNA GLOBAL POR DEFECCTO */
		List<Config> lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyTWS_HOST, true);
		if (!lConfig.isEmpty())  _HOST = lConfig.get(0).getValue();
		
		 /* UNA GLOBAL POR DEFECCTO */
		 lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyTWS_PORT, true);
		if (!lConfig.isEmpty())  _PORT = Integer.valueOf(lConfig.get(0).getValue());
		
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMM");
			


		TIMApiGITrader oTWS = new TIMApiGITrader(_HOST,_PORT, _CLIENT_ID);

		
		/* activamos el scheduler de MYSQL por si no lo está.No me tira desde el my.ini 
		TradingMarketDAO.StartTradingSchedulerMYSQL();*/
		
		
		_log.info("Starting Trading_Read process..");
		
		// verificamos si hay mercado abierto.
		/* if (lActiveMarkets.size()>0)
		{
			*/
		if (oTWS.GITraderTWSIsConnected())
		{
			oTWS.GITraderDisconnectFromTWS();
		}
		
		oTWS.GITraderConnetToTWS();
		if (oTWS.GITraderTWSIsConnected())
		{
		
		
		
		Contract oContrat = null;
		
		/* VERIFICAMOS MERCADOS ACTIVOS */
	    java.util.List<Market> lMarket = null;
	    java.util.List<Share> lShare = null;
	    
	    
	    int UniqueORDERID=1;
	    int UniquePOSITIONID=1;
	    
	    
	   // MyLog.log(Priority.INFO, "UniqueORDERID" + UniqueORDERID);
	    
	    
	    /* 1. VERIFICAMOS QUE EXISTA UNA PETICION PARA EL HISTORICAL DATA QUE NO HAYA FINALIZADO SIN ERROR */
	    
	    
	    
	    
	    boolean bAllRequested = false;
	    
	    ArrayList<String> lShareRequested = new ArrayList();
	    
	    /* LANZAMOS LA OPERACION DE CONTINUO */ 
	    
	    while (true)
	    	
	    {	
	    	
	    
	    
	    if (!bAllRequested)
	    {
	   	
	    	 // empezamos a contar desde 5 o 10 minutos antes de la apertura para contar precios
	    	  
	    	 String _HORACTUAL = Utilidades.getActualHourFormatPlusMinutes(Utilidades.getActualHourFormat(),10); 
	    	 
	    	List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveStartEndHour(_HORACTUAL, _HORACTUAL,true);
	    	//List<Market> lActiveMarkets = MarketDAO.getListActiveMarketBtHours(Utilidades.getActualHourFormat(), 1,null);
	    	
		    
			
		    if (!lActiveMarkets.isEmpty())
		    {
		    	
		    	/* recorremos mercados */
		    	
		    	for (int j=0;j<lActiveMarkets.size();j++)
		    	{
		    		
		    		Market oMarket = lActiveMarkets.get(j);
		    		
	    			
		    		 lShare =  ShareLocalServiceUtil.findByActiveMarket(oMarket.getMarketId(), true);
		         	
		    	    //	lShare = ShareDAO.getListActiveShareByMarket(oMarket.getMarketID());
		    			    		
		    		
		    		if (!lShare.isEmpty())
		    		{
		    			
		    			//MyLog.log(Priority.INFO, "Recorremos acciones lShare!=null" + String.valueOf(lShare!=null) + "|Size:" + lShare.size());
		    		
			    		for (int z=0;z<lShare.size();z++)
			        	{

			    			boolean bToRequest=true;
			    			
			    			
			    			Share oShare = (Share)lShare.get(z);
			    			
			    			String _Expiration = "";
			      		    if (oShare.getExpiry_date()!=null)
			      		    {	
			    				_Expiration = sdf.format(oShare.getExpiry_date());
			    			//	LogTWM.log(Priority.INFO, "_Expiration:" + _Expiration);
			      		    }
			    		
			    			
			    			if  (oShare.getLast_error_data_read()!=null && !oShare.getLast_error_data_read().trim().equals(""))
			    			{
			    				
			    				lShareRequested.remove(oShare.getSymbol());
			    			}
			    			
			    			if (lShareRequested!=null && lShareRequested.contains(oShare.getSymbol() + "|" + _Expiration))
			    			{
			    				bToRequest = false; 
			    				
			    			}
			    			else
			    			{
			    				lShareRequested.add(oShare.getSymbol() + "|" + _Expiration);
			    			}
			    			if (bToRequest)
			    			{
			    				
				    			
			    				if (oShare.getSecurity_type().equals("FUT"))
			    				{
			    					oContrat = new FutContract( oShare.getSymbol(), _Expiration);
			    					//oContrat.multiplier(String.valueOf(oShare.getMultiplier()));		    					
			    					oContrat.exchange(oShare.getExchange());
			    					oContrat.currency(oMarket.getCurrency());
			    				}
			    				else		    					
			    					oContrat = new StkContract( oShare.getSymbol());
			    				
				    			
				    			//oContrat = (Contract) oTWS.createContract(oShare.getSymbol(), oMarket.getSecurity_type(),oMarket.getExchange(),oMarket.getCurrency());//, _Expiration, "", 0);
				    			
				    			_log.info("TradingRead de :" + oShare.getSymbol() + ":" +  oShare.getSecurity_type() + ":" + oShare.getExchange() + ":" + oMarket.getCurrency());
				    			
				    			
				    			// USAMOS EL CLIENT_ID UNICO PARA GENERAR INTERVALOS DE ORDERSID PARA 
				    			// EVITAR CONCURRENCIA Y GENERAR CODIGOS IGUALES
				    			
				    			long  _INCREMENT_ORDER_ID = CounterLocalServiceUtil.increment(Order.class.getName()) +  _CLIENT_ID;
				    			
				    			/* insertamos control de ordenes de peticion */
				    			 IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);
				    			_order.setCompanyId(oMarket.getMarketId());
				    			_order.setGroupId(oMarket.getGroupId());
				    			_order.setShareID(oShare.getShareId());
				    			/* pedimos tiempo real */

				    			try {
									oTWS.GITraderGetRealTimeContract(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
									oTWS.GITraderConnetToTWS();
									//if (oTWS.GITraderTWSIsConnected())
									if (oTWS.GITraderTWSIsConnected())
									{
										oTWS.GITraderDisconnectFromTWS();
										
									}
								}
				        		
				    			//UniqueORDERID+=1;
			    			
			    			}
			        		
			        	}
		
		    		}
		    //		bAllRequested = true;
		    		
		    	}
		    	// fin de mercamos y acciones.
		    	
		    	
		    	
		    		
		    	
		    	/* fin recorremos mercados */
		    	
		    }
	    /* } */
	 		// una vez que estamos dentro..verificamos que haya conexion y este el mercado abierto.
			List<Market> lActiveMarketsCheck =  MarketLocalServiceUtil.findByActiveStartEndHour(_HORACTUAL, _HORACTUAL,true);
			boolean IsConnected = oTWS.GITraderTWSIsConnected();		   // en debug está siempre a false		
			if (lActiveMarketsCheck==null ||  !IsConnected)
			{
				if (IsConnected)
				{
					oTWS.disconnectFromTWS();
					
				}
				_log.info("Ending Trading_Read Process..There is no open market or console closed");
				System.exit(-1);
			}
		    	
	    	// fin while
	    }   // fin is connected
	     
	     
		  
		else
		{
			
			_log.info("Ending Trading_Read process..No conectado a la TWS");
		}	
		
		/*} // fin de mercados abiertos.
		 else
		{
			LogTWM.log(Priority.INFO, "Ending Trading_Read process..No open market");
		}	
		*/
	    }
		}
			
} // END RECEIVER
}// END CLASS
