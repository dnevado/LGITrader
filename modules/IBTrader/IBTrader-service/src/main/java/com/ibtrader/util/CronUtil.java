package com.ibtrader.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ib.client.Contract;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.cron.IBTraderRead;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.data.service.StrategyLocalServiceUtil;
import com.ibtrader.data.service.StrategyShareLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.ibtrader.util.Utilities;
import com.ibtrader.util.*;
import com.ibtrader.interactive.TIMApiGITrader;


public class CronUtil {

	private final static Log _log = LogFactoryUtil.getLog(CronUtil.class);

	/* CONEXIONES POR ORGANIZACION */
	public static void StartReadingCron(Message _message) throws Exception 	{
	
	Config _conf = null;
	int 	_CLIENT_ID = 6;	  // el dos para leer, el 3 para escribir			
	String  _HOST = "127.0.0.1";
	int     _PORT = 7497;	
	Config _Conf_CronRunning = null;
	int _CRON_RUNNING = -1;	  // el dos
	List<Company> lCompanies = CompanyLocalServiceUtil.getCompanies();
	Company _company = lCompanies.get(0); // tiene que existir
	long companyId =  _company.getCompanyId();
	long guestGroupId = 0;
	try {
		guestGroupId = GroupLocalServiceUtil.getGroup(_company.getCompanyId(), GroupConstants.GUEST).getGroupId();
	} catch (PortalException e) {
	// TODO Auto-generated catch block
		e.printStackTrace();			
	}
	
	
	
	_CRON_RUNNING = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_READING_STATUS, companyId, guestGroupId)).intValue();
	SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMM");

	
	
	if (_CRON_RUNNING==0)  // no se esta ejecutando --> FALTA CONTROL DE EXCEPTIONES PARA CONTROLAR QUE SE PUEDA VOLVER A EJECUTAR 
	{
	// sacamos organizaciones padre 
	List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
	for (Organization _Organization : lOrganization )
	{
		  
		 _HOST = Utilities.getConfigurationValue(IBTraderConstants.keyTWS_HOST, _Organization.getCompanyId(), _Organization.getGroupId());		 
		 _PORT = Integer.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyTWS_PORT, _Organization.getCompanyId(), _Organization.getGroupId()));
		 TIMApiGITrader oTWS = new TIMApiGITrader(_HOST,_PORT, _CLIENT_ID);	
		 oTWS.GITraderConnetToTWS();
		 if (oTWS.GITraderTWSIsConnected() )
	     {
				Contract oContrat = null;
				/* VERIFICAMOS MERCADOS ACTIVOS */			    
			    java.util.List<Share> lShare = null;
			    /* 1. VERIFICAMOS QUE EXISTA UNA PETICION PARA EL HISTORICAL DATA QUE NO HAYA FINALIZADO SIN ERROR */
			    ArrayList<String> lShareRequested = new ArrayList();
			    /* LANZAMOS LA OPERACION DE CONTINUO */ 
			    
			    _Conf_CronRunning = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_READING_STATUS, companyId, guestGroupId);
			    _Conf_CronRunning.setValue(String.valueOf(1));
				ConfigLocalServiceUtil.updateConfig(_Conf_CronRunning);
				
				try
				{		
					while (true)
					{		   				    	 
				    	 // empezamos a contar desde 5 o 10 minutos antes de la apertura para contar precios
				    	String _HORACTUAL = Utilities.getActualHourFormatPlusMinutes(Utilities.getGlobalIBDateNowFormat(),10); 
				    	
				    	List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveStartEndHour(_HORACTUAL, _HORACTUAL,true);
					    
				    	for (Market oMarket : lActiveMarkets)
				    	{
				    						    		
				    		lShare =  ShareLocalServiceUtil.findByActiveMarketGroupCompany(oMarket.getMarketId(), true, oMarket.getGroupId(), oMarket.getCompanyId());
				    		for (Share oShare : lShare)
					    	{				    		
			
				    			boolean bToRequest=true;
				    			String _Expiration = "";
				      		    if (oShare.getExpiry_date()!=null) _Expiration = sdf.format(oShare.getExpiry_date());			    					    		
				    			if  (!oShare.IsTradeable())
				    			{
				    				
				    				lShareRequested.remove(oShare.getSymbol());
				    			}
				    			
				    			if (!oShare.IsTradeable() || (lShareRequested!=null && lShareRequested.contains(oShare.getSymbol() + "|" + _Expiration)))
				    			{
				    				bToRequest = false; 
				    				
				    			}
				    			else
				    			{
				    				lShareRequested.add(oShare.getSymbol() + "|" + _Expiration);
				    			}
				    			if (bToRequest)
				    			{
				    				
					    			
				    				if (oShare.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS))
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
					    			
					    			long  _INCREMENT_ORDER_ID = CounterLocalServiceUtil.increment(IBOrder.class.getName()) +  _CLIENT_ID;
					    			
					    			/* insertamos control de ordenes de peticion */
					    			 IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);
					    			_order.setCompanyId(oMarket.getMarketId());
					    			_order.setGroupId(oMarket.getGroupId());
					    			_order.setShareID(oShare.getShareId());
					    			/* pedimos tiempo real */
					    			IBOrderLocalServiceUtil.updateIBOrder(_order);
		
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
					    		} // btoRequest
					        		
					        	} // bucle de shares
					    		
					    	}
					    	// fin de mercamos y acciones.
				    }   // fin is connected
				} // en try 
				catch (Exception e)
				{
					if (oTWS.GITraderTWSIsConnected())
					{
						oTWS.GITraderDisconnectFromTWS(); 
						
					}
				}
					  
				
	     } //  end 		 if (oTWS.GITraderTWSIsConnected() )
		 
	} //	for (Organization _Organization : lOrganization )
	} // Cron runnig 
	
	}
	public static void StartTradingCron(Message _message) throws Exception {

		/* TENEMOS TAREAS CON EL TIEMPO REAL DE CADA ACCION 
		 * 
		 * 1. ACCEDER A LAS ACCIONES DEL MODELO DE ACCION
		 * 1.5. VERIFICAR QUE NO HAY OPERACION DE ESE MODELO PENDIENTE
		 * 2. TESTEAR SI SE CUMPLE ALGUNA. VERIFICAR MAXIMOS Y MINIMOS  
		 * 3. SI SE CUMPLE...LANZAR OPERACION ... GUARDAR OPERACION DE COMPRA VENTA Y CONTROLAMOS.
		 * 4. SEGUIMOS MIENTRAS HAYA ACCIONES CON POSIBILIDADES.
		 * 5. falta controlar cuando entra el modelo (OffSet con respecto a la hora inicil de mercado?)
		 * PENSAR EN LOS BUCLES HACERLOS DENTRO...
		 * */
		Config _Conf_CronRunning = null;
		int _CRON_RUNNING = -1;	  // el dos
		List<Config> lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyCRON_TRADING_STATUS, true);
		if (!lConfig.isEmpty())
		{
			_Conf_CronRunning = lConfig.get(0);
			_CRON_RUNNING = Long.valueOf(_Conf_CronRunning.getValue()).intValue();
		}
		
		
		
		int 	_CLIENT_ID = 2;	  // el dos para leer, el 3 para escribir			
		String  _HOST = "127.0.0.1";
		int     _PORT = 7497;
		 /* UNA GLOBAL POR DEFECCTO */
		lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyTWS_HOST, true);
		if (!lConfig.isEmpty())  _HOST = lConfig.get(0).getValue();
		 /* UNA GLOBAL POR DEFECCTO */
		 lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyTWS_PORT, true);
		if (!lConfig.isEmpty())  _PORT = Integer.valueOf(lConfig.get(0).getValue());

		
		TIMApiGITrader oTWS = new TIMApiGITrader(_HOST,_PORT, _CLIENT_ID);	
		 
		String _HORACTUAL = Utilities.getActualHourFormatPlusMinutes(Utilities.getGlobalIBDateNowFormat(),10); 
   	 
   	 	List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveStartEndHour(_HORACTUAL, _HORACTUAL,true);
		
		
		
		_log.info("Starting Trading Cron  Process..");
		
		if (oTWS.GITraderTWSIsConnected())
		{
			oTWS.GITraderDisconnectFromTWS();
		}
		
		if (_CRON_RUNNING==0)  // no se esta ejecutando --> FALTA CONTROL DE EXCEPTIONES PARA CONTROLAR QUE SE PUEDA VOLVER A EJECUTAR 
		{
			
		
		oTWS.GITraderConnetToTWS();
		if (oTWS.GITraderTWSIsConnected() && !lActiveMarkets.isEmpty())
		{
			while (true)
			{	

			//oTWS = new TIMApiGITrader(_HOST,_PORT, _CLIENT_ID);
			
			//if (oTWS.GITraderTWSIsConnected())  oTWS.GITraderDisconnectFromTWS(); 
			
			
			if (!oTWS.GITraderTWSIsConnected()) oTWS.GITraderConnetToTWS();    	
	
	
	    	/* recorremos mercados */
	    	List<Share> lShare;	    	
	    	for (Market oMarket : lActiveMarkets)
	    	{
	    		
    			
	    		 lShare =  ShareLocalServiceUtil.findByActiveMarketGroupCompany(oMarket.getMarketId(), true,oMarket.getGroupId(),oMarket.getCompanyId());
	         	
	    		 for (Share oShare :lShare)
	    		 {
		    			/* recorremos mercados y acciones  para verificar las estrategias. Por reflexion, creamos la factoria
						 * de objetos que implementa cada strategia */
	    			 	if (!oShare.IsTradeable()) 
	    			 		continue;
	    			 	
		    			List<Strategy> _lStrategies = StrategyLocalServiceUtil.findByActiveCompanyId(true, oShare.getCompanyId());
		    			List<StrategyShare> _lStrategiesOfShare = StrategyShareLocalServiceUtil.getByGroupCompanyShareId(oShare.getGroupId(), 
		    						oShare.getCompanyId(), oShare.getShareId());
		    			
		    			for (Strategy oStrategy :_lStrategies)
		    			{
		    				
		    				if (Utilities.fn_IsStrategyInShareStrategies(oStrategy.getStrategyID(),_lStrategiesOfShare))
		    				{		    					
		    					
		    					
		    							    					
		    					StrategyImpl _strategyImpl= (StrategyImpl) Utilities.getContextClassLoader().loadClass(oStrategy.getClassName()).newInstance();
		    					_strategyImpl.init(oShare.getCompanyId());   // verify if custom fields are created and filled 	    						    				
		    					if (_strategyImpl.verify(oShare, oMarket))
		    							_strategyImpl.execute(oShare, oMarket);	
		    					
		    				}
		    			}
	    		 }
	    	} // END FOR ACTIVE MARKETS
				    		
			} // end wile true 	
		}
		else
		{
		  _log.info("Ending Trading process..No conectado a la TWS");
		}					
	}  //if (_CRON_RUNNING==0) 
}
		

	/* ESTE PUEDE EJECUTARSE POR VECES */
	public static void StartVerifyContractsCron(Message _message) throws Exception {		
		List<Config> lConfig=null;
		
		int 	_CLIENT_ID = 12;	  // el dos para leer, el 3 para escribir			
		String  _HOST = "127.0.0.1";
		int     _PORT = 7497;
		
		 /* UNA GLOBAL POR DEFECCTO */
		lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyTWS_HOST, true);
		if (!lConfig.isEmpty())  _HOST = lConfig.get(0).getValue();
		
		 /* UNA GLOBAL POR DEFECCTO */
		 lConfig = ConfigLocalServiceUtil.findByKeyGlobalDefault(IBTraderConstants.keyTWS_PORT, true);
		if (!lConfig.isEmpty())  _PORT = Integer.valueOf(lConfig.get(0).getValue());
		
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMM");
		TIMApiGITrader oTWS = new TIMApiGITrader(_HOST,_PORT, _CLIENT_ID);

		_log.info("Starting Verifying contract details process"); 
		
		if (oTWS.GITraderTWSIsConnected())		oTWS.GITraderDisconnectFromTWS(); 
		
		oTWS.GITraderConnetToTWS();
		if (oTWS.GITraderTWSIsConnected() )
		{
		
		
		Contract oContrat = null;
		/* VERIFICAMOS MERCADOS ACTIVOS */
	    java.util.List<Share> lShare = null;
	    
	    	
    	List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActive(Boolean.TRUE);
    	for (Market oMarket : lActiveMarkets)
    	{
    		
    		lShare =  ShareLocalServiceUtil.findByValidatedTraderProviderMarketGroupCompany(oMarket.getMarketId(), oMarket.getGroupId(), oMarket.getCompanyId());			         
			for (Share oShare : lShare)
        	{
    			String _Expiration = "";
    			
    		    if (oShare.getExpiry_date()!=null) _Expiration = sdf.format(oShare.getExpiry_date());			    					    		
    			if (oShare.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS))
				{
					oContrat = new FutContract( oShare.getSymbol(), _Expiration);		    			
					oContrat.exchange(oShare.getExchange());
					oContrat.currency(oMarket.getCurrency());
				}
				else		    					
					oContrat = new StkContract( oShare.getSymbol());
    				
	    			
    			_log.info("Verifyng de :" + oShare.getSymbol() + ":" +  oShare.getSecurity_type() + ":" + oShare.getExchange() + ":" + oMarket.getCurrency());    						    		
    			long  _INCREMENT_ORDER_ID = CounterLocalServiceUtil.increment(IBOrder.class.getName()) +  _CLIENT_ID;
    			
    			/* insertamos control de ordenes de peticion */
    			 IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);
    			_order.setCompanyId(oMarket.getMarketId());
    			_order.setGroupId(oMarket.getGroupId());
    			_order.setShareID(oShare.getShareId());
    			/* pedimos tiempo real */
    			IBOrderLocalServiceUtil.updateIBOrder(_order);

    			try {
					oTWS.GITradergetContractDetails(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat);
					Thread.sleep(1000);
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
    		} // OVER SHARES 
	        		
	        } // OVER MARKETS
	    		
		  
	    	/* } */
	 		// una vez que estamos dentro..verificamos que haya conexion y este el mercado abierto.
			boolean IsConnected = oTWS.GITraderTWSIsConnected();		   // en debug está siempre a false			
			if (IsConnected)
			{
				oTWS.disconnectFromTWS();
				
			}
			_log.info("Ending Trading_Veriying Process..There is no open market or console closed");
	    	// fin while
	    }   // fin is connected
	     
		else			
			_log.info("Ending Trading_Veriying process..No conectado a la TWS");
	}				

}