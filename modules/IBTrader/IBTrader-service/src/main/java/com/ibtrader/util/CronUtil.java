package com.ibtrader.util;

import java.text.SimpleDateFormat;

import java.time.Duration;
import java.time.LocalDateTime ;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.ib.client.Contract;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.cron.IBTraderTrade;
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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.ibtrader.interactive.TIMApiGITrader_NOVALE;
import com.ibtrader.interactive.TIMApiWrapper;
import com.ibtrader.strategy.IBStrategyCancelPosition;


/* OJOOOOOOOOO 
 * reusing CLIET_ID 
 * https://groups.io/g/twsapi/topic/ib_gateway_connect/5111420?p=Created,,,20,1,0,0
 * 
 * */

public class CronUtil {

	private final static Log _log = LogFactoryUtil.getLog(CronUtil.class);

	/* VALIDA CONFIRMACIONES DE ORDENES COMPLETADAS Y NO ENVIADAS A LA API POR DESCONEXIONES  */
	public static void StartOrdersValidator(Message _message) throws Exception 	{
		
		int 	_CLIENT_ID = 4;	  // el dos para leer, el 3 para escribir			
		String  _HOST = "127.0.0.1";
		int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
		List<Company> lCompanies = CompanyLocalServiceUtil.getCompanies();
		Company _company = lCompanies.get(0); // tiene que existir
		long companyId =  _company.getCompanyId();		
		/* VERIFICAMOS MERCADOS ACTIVOS */
	    long guestGroupId = 0;
		try {
			guestGroupId = GroupLocalServiceUtil.getGroup(_company.getCompanyId(), GroupConstants.GUEST).getGroupId();
		} catch (PortalException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();			
		}
	    
		_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_ORDERSCHECKER_CLIENT_INITIAL, companyId, guestGroupId)).intValue();;	  // el dos para leer, el 3 para escribir
		TIMApiWrapper wrapper = null; 
	    List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
		try
		{
			for (Organization _Organization : lOrganization )
			{
				  
				/* CUERNTA PAPER */
				String simulated = Utilities.getConfigurationValue(IBTraderConstants.keySIMULATION_MODE, companyId, guestGroupId);
				boolean bSIMULATED_TRADING = simulated.equals("1");  
				
				String _keyHOST  = IBTraderConstants.keyTWS_HOST;
				String _keyPORT  = IBTraderConstants.keyTWS_PORT;
				if (bSIMULATED_TRADING)
				{
					_keyHOST  = IBTraderConstants.keyPAPER_TWS_HOST;
					 _keyPORT  = IBTraderConstants.keyPAPER_TWS_PORT;
				}
				
				 _HOST = Utilities.getConfigurationValue(_keyHOST, _Organization.getCompanyId(), _Organization.getGroupId());		 
				 _PORT = Integer.valueOf(Utilities.getConfigurationValue(_keyPORT, _Organization.getCompanyId(), _Organization.getGroupId()));			
			     wrapper = new TIMApiWrapper(_CLIENT_ID);				
				 if (wrapper.isConnected()) wrapper.disconnect();
				 wrapper.connect(_HOST, _PORT,_CLIENT_ID); 	 	
				 
				 if (wrapper.isConnected())
			     {
					
					 /* VERIFICACION DE LA CONECTIVIDAD, SIEMPRE NECESARIO */
					wrapper.reqNextId(); 
					wrapper.set_ibtarget_organization(_Organization);
					
					if (wrapper.getCurrentOrderId()==-1)
					{
						wrapper.disconnect();
						return;
					}
					wrapper.getCurrentTwsTime();
					_log.info("Connected, StartOrdersValidator, connecting to TWS,currentOrderId:");
					
					long  _INCREMENT_ORDER_ID = wrapper.getNextOrderId();
					IBOrderLocalServiceUtil.deleteByOrderCompanyGroup(_INCREMENT_ORDER_ID, _Organization.getCompanyId(), _Organization.getGroupId(),_CLIENT_ID,-1);

	    			 IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);
	    			_order.setCompanyId(_Organization.getCompanyId());
	    			_order.setGroupId(_Organization.getGroupId());
	    			_order.setShareID(-1);	// NO HAY SHARE 
	    			_order.setOrdersId(_INCREMENT_ORDER_ID);
	    			_order.setIbclientId(_CLIENT_ID);
	    			_order.setRemovable_on_reboot(Boolean.TRUE);	 /* los requestid  se borran */
	    			/* pedimos tiempo real */
	    			
	    			IBOrderLocalServiceUtil.updateIBOrder(_order);
	    			try {
	    				wrapper.getExecutionOrders(new Long(_INCREMENT_ORDER_ID).intValue());
	    				/* _INCREMENT_ORDER_ID++;
	    				wrapper.getOpenOrders(new Long(_INCREMENT_ORDER_ID).intValue()); */	
	    				Thread.sleep(1000);
					//	oTWS.GITradergetContractDetails(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat);							
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();							
						//if (oTWS.GITraderTWSIsConnected())
						 if (wrapper.isConnected()) wrapper.disconnect();

					}			    						 
			     }  // 		 if (oTWS.GITraderTWSIsConnected() )
				 if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();
			}  // 		for (Organization _Organization : lOrganization )
			//if (m_client.isConnected()) m_client.eDisconnect();
		
		}
		
		catch (Exception e)
		{
			if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();
		}
		
		
	}
	
	/* CONEXIONES POR ORGANIZACION */
	public static void StartReadingCron(Message _message) throws Exception 	{
	
	int 	_CLIENT_ID = 1;	  // el dos para leer, el 3 para escribir			
	String  _HOST = "127.0.0.1";
	int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
	
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
	
	 
	_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_READING_CLIENT_INITIAL, companyId, guestGroupId)).intValue();;	  // el dos para leer, el 3 para escribir
	_CRON_RUNNING = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_READING_STATUS, companyId, guestGroupId)).intValue();
	
	
		 
	
	SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMM");
	LocalDateTime  _now =  LocalDateTime .now();  
	
    
	/*    long totalJobs = Utilities.getTotalJobsRunnig(IBTraderTrade.class.getName()); 
    if (totalJobs>1) // no permitmos mas de 1
    {
    	_log.info("TradingRead totalJobs" + totalJobs + "," + _CLIENT_ID);	    
    	Thread.currentThread().stop();
    }
    _log.info("TradingRead totalJobs" + totalJobs + "," + _CLIENT_ID);	   
	*/
	if (_CRON_RUNNING<=0)  // no se esta ejecutando --> FALTA CONTROL DE EXCEPTIONES PARA CONTROLAR QUE SE PUEDA VOLVER A EJECUTAR 
	{
	// sacamos organizaciones padre 
	
	List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
	
	for (Organization _Organization : lOrganization )
		{
		 
			/* CUERNTA PAPER */
			String simulated = Utilities.getConfigurationValue(IBTraderConstants.keySIMULATION_MODE, companyId, _Organization.getGroupId());	
			boolean bSIMULATED_TRADING = simulated.equals("1");  
			String _keyHOST  = IBTraderConstants.keyTWS_HOST;
			String _keyPORT  = IBTraderConstants.keyTWS_PORT;
			if (bSIMULATED_TRADING)
			{
				_keyHOST  = IBTraderConstants.keyPAPER_TWS_HOST;
				_keyPORT  = IBTraderConstants.keyPAPER_TWS_PORT;
			}
		
		
		 _HOST = Utilities.getConfigurationValue(_keyHOST, _Organization.getCompanyId(), _Organization.getGroupId());		 
		 _PORT = Integer.valueOf(Utilities.getConfigurationValue(_keyPORT, _Organization.getCompanyId(), _Organization.getGroupId()));
		 /* TIMApiGITrader oTWS = new TIMApiGITrader(_HOST,_PORT, _CLIENT_ID);	
		 if (oTWS.GITraderTWSIsConnected())  oTWS.GITraderDisconnectFromTWS(); */ 
		// _log.info("StartReadingCron, connecting to TWS for Organization:" + _Organization.getName());
		 //oTWS.GITraderConnetToTWS();
	     TIMApiWrapper wrapper = new TIMApiWrapper(_CLIENT_ID);				
		 if (wrapper.isConnected()) wrapper.disconnect();
		 wrapper.connect(_HOST, _PORT,_CLIENT_ID); 	 	
 

		// if (oTWS.GITraderTWSIsConnected() )
		if (wrapper.isConnected())
	    {
			
				/* VERIFICACION DE LA CONECTIVIDAD, SIEMPRE NECESARIO */
				wrapper.reqNextId(); 
				wrapper.set_ibtarget_organization(_Organization);
				
				if (wrapper.getCurrentOrderId()==-1)
				{
					wrapper.disconnect();
					return;
				}
				_log.info("Connected, StartReadingCron, connecting to TWS");
			 
			 	ArrayList<String> lShareRequested = new ArrayList<String>();
			 	while (true)
				{
			 	 /* 20180417 con el uso de la variable estatica en cada listemer, parece que no hace falta este control 
			 	 ALMACENIAMOS TIMESTAMP DE CONTROL 			  
			    LocalDateTime  _nowAuditTime =  LocalDateTime .now();  			    
			    Duration Period = Duration.between(_nowAuditTime, _now);
			    long milsecondsdiff = Math.abs(Period.toMillis());
			  //  _log.info("Updating AuditDate...?" + milsecondsdiff + ", mayor que " + ConfigKeys._AUDIT_TIME_CRON_READ_IN_MILLISECONDS);
			    
			  
			    
			    if (milsecondsdiff > ConfigKeys._AUDIT_TIME_CRON_READ_IN_MILLISECONDS)
			    {			    	
			    	 Config _confRunning = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_READING_STATUS, companyId, guestGroupId);
			    	 _now =  LocalDateTime .now(); 
			    	 _confRunning.setModifiedDate(new Date());  // or localtime ????
			    	 _confRunning.setValue(String.valueOf(1));
			    	 ConfigLocalServiceUtil.updateConfig(_confRunning);
			    	//  _log.info("Updated AuditDate StartReadingCron");
			    }
			    */	
				Contract oContrat = null;
				/* VERIFICAMOS MERCADOS ACTIVOS */			    
			    java.util.List<Share> lShare = null;
			    /* 1. VERIFICAMOS QUE EXISTA UNA PETICION PARA EL HISTORICAL DATA QUE NO HAYA FINALIZADO SIN ERROR */
			    /* LANZAMOS LA OPERACION DE CONTINUO */ 
			   	
				try
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
				    			String _uniqueShare = oShare.getSymbol() + "|" + _Expiration + "|" + oShare.getGroupId() + "|" + oShare.getCompanyId();

				    			if  (!oShare.IsTradeable())
				    			{
				    				
				    				lShareRequested.remove(_uniqueShare);
				    			}
				    			/* PEDIMOS SI SYMBOL + GROUP NO ESTA  */
				    			
				    			if (!oShare.IsTradeable() || (lShareRequested!=null && lShareRequested.contains(_uniqueShare)))
				    			{
				    				bToRequest = false; 
				    				
				    			}
				    			else
				    			{
				    				lShareRequested.add(_uniqueShare);
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
				    				_log.info("TradingRead de :" + oShare.getSymbol() + ":" +  oShare.getSecurity_type() + ":" + oShare.getExchange() + ":" + oMarket.getCurrency());

					    			/* insertamos control de ordenes de peticion */		
				    				long  _INCREMENT_ORDER_ID = wrapper.getNextOrderId();
				    				IBOrderLocalServiceUtil.deleteByOrderCompanyGroup(_INCREMENT_ORDER_ID, _Organization.getCompanyId(), _Organization.getGroupId(), _CLIENT_ID,oShare.getShareId());

					    			 IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);
					    			_order.setCompanyId(oMarket.getCompanyId());
					    			_order.setGroupId(oMarket.getGroupId());
					    			_order.setShareID(oShare.getShareId());
					    			_order.setOrdersId(_INCREMENT_ORDER_ID);
					    			_order.setIbclientId(_CLIENT_ID);
					    			_order.setRemovable_on_reboot(Boolean.TRUE);	 /* los requestid  se borran */
					    		//	_order.setIborderID(_INCREMENT_ORDER_ID);
					    			/* pedimos tiempo real */
					    			IBOrderLocalServiceUtil.updateIBOrder(_order);
					    			wrapper.getRealTime(new Long(_INCREMENT_ORDER_ID).intValue(), oContrat);			    			
									//oTWS.GITraderGetRealTimeContract(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat);
					    		} // btoRequest
					        		
					        	} // bucle de shares
					    		
					    	}		
				    	// fin de mercamos y acciones.
				    	

				 
				} // en try 
				catch (Exception e)
				{
					 if (wrapper.isConnected()) wrapper.disconnect();
				}
					
				/* CASO DE QUE SE CAIGA LA TWS*/
				if (!wrapper.isConnected()) 
				{
					 wrapper.disconnect();
					 break;
				}
			 }   // while 
	     } //  end 		 if (oTWS.GITraderTWSIsConnected() )
		// if (wrapper.isConnected()) wrapper.disconnect();
		 
		} //	for (Organization _Organization : lOrganization )	
	
	} // Cron runnig
	/* else  // verificamos que haya un cambio de modifieddate reciente,  digamos 30 segundos, ya que el cron escribiera cada menos
	{
		// si han pasado mas de 5 segundos sin actualizar y esta el cron running, se pone a 0
	   Config _confRunning =ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_READING_STATUS, companyId, guestGroupId);  
	   Date now = new Date();			
	   long diffInMillies = Math.abs(now.getTime() - _confRunning.getModifiedDate().getTime() + 2000);	   
	   if (diffInMillies>ConfigKeys._AUDIT_TIME_CRON_READ_IN_MILLISECONDS)  // puede haber pasado un error 
	   {
		   	_confRunning.setValue("0"); // no running
		    _confRunning.setModifiedDate(new Date());  // or localtime ????
   	 		_confRunning = ConfigLocalServiceUtil.updateConfig(_confRunning);
	   }
	}*/
	
	}
	@SuppressWarnings("deprecation")
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
		
		int 	_CLIENT_ID = 2; 			
		String  _HOST = "127.0.0.1";
		int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;			
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
		
		
		_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_TRADING_CLIENT_INITIAL, companyId, guestGroupId)).intValue();;	  // el dos para leer, el 3 para escribir
		_CRON_RUNNING = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_TRADING_STATUS, companyId, guestGroupId)).intValue();		
	    LocalDateTime  _now =  LocalDateTime .now();  
	    
	    /* long totalJobs = Utilities.getTotalJobsRunnig(IBTraderTrade.class.getName()); 
	   if (totalJobs>1) // no permitmos mas de 1
	    {
	    	_log.info("TradingCron totalJobs" + totalJobs + "," + _CLIENT_ID);	    
	    	Thread.currentThread().stop();
	    }
	    _log.info("TradingCron totalJobs" + totalJobs + "," + _CLIENT_ID);
	    */	    
		if (_CRON_RUNNING<=0)  // no se esta ejecutando o no exista para client --> FALTA CONTROL DE EXCEPTIONES PARA CONTROLAR QUE SE PUEDA VOLVER A EJECUTAR 
		{
			
			// sacamos organizaciones padre 
			List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
			for (Organization _Organization : lOrganization )
			{
				  
				/* CUERNTA PAPER */
				String simulated = Utilities.getConfigurationValue(IBTraderConstants.keySIMULATION_MODE, companyId, _Organization.getGroupId());
				boolean bSIMULATED_TRADING = simulated.equals("1");  		
				String _keyHOST  = IBTraderConstants.keyTWS_HOST;
				String _keyPORT  = IBTraderConstants.keyTWS_PORT;
				if (bSIMULATED_TRADING)
				{
					 _keyHOST  = IBTraderConstants.keyPAPER_TWS_HOST;
					 _keyPORT  = IBTraderConstants.keyPAPER_TWS_PORT;
				}
				
				 _HOST = Utilities.getConfigurationValue(_keyHOST, _Organization.getCompanyId(), _Organization.getGroupId());		 
				 _PORT = Integer.valueOf(Utilities.getConfigurationValue(_keyPORT, _Organization.getCompanyId(), _Organization.getGroupId()));
				 
				 TIMApiWrapper wrapper = new TIMApiWrapper(_CLIENT_ID);	
				 //_log.info("StartTradingCron, connecting to TWS");
				 if (wrapper.isConnected()) wrapper.disconnect();
				 wrapper.connect(_HOST, _PORT,_CLIENT_ID); 	 	
				 
				if (wrapper.isConnected())
			    {
									
 					wrapper.set_ibtarget_organization(_Organization);
 					if (wrapper.getCurrentOrderId()==-1)
 					{
 						wrapper.disconnect();
 						return;
 					}
					_log.info("Connected, StartTradingCron, connecting to TWS clientID:" +_CLIENT_ID);
					
					while (true)
					{
						/* ALMACENIAMOS TIMESTAMP DE CONTROL */
						/* 20180417 con el uso de la variable estatica en cada listemer, parece que no hace falta este control 
					    LocalDateTime  _nowAuditTime =  LocalDateTime .now();  			    
					    Duration Period = Duration.between(_nowAuditTime, _now);
					    long milsecondsdiff = Math.abs(Period.toMillis());
					    if (milsecondsdiff>ConfigKeys._AUDIT_TIME_CRON_READ_IN_MILLISECONDS)
					    {
					    	
					    	
					    	
					    	Config _confRunning =ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_TRADING_STATUS, companyId, guestGroupId);
					    	 _now =  LocalDateTime.now(); 
					    	 _confRunning.setModifiedDate(new Date());  // or localtime ????
					    	 _confRunning.setValue(String.valueOf(1));
					    	 ConfigLocalServiceUtil.updateConfig(_confRunning);
					    	_log.info("Updating  TradingCron to database connecting to TWS clientID:" +_CLIENT_ID);
					    	 
					    } */
					 	String _HORACTUAL = Utilities.getActualHourFormatPlusMinutes(Utilities.getGlobalIBDateNowFormat(),10); 				   	 
				   	 	List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveStartEndHour(_HORACTUAL, _HORACTUAL,Boolean.TRUE);				   	 	
				   	  
				   	    try
				   	    {
							
					    	/* recorremos mercados */
					    	List<Share> lShare;	    	
					    	for (Market oMarket : lActiveMarkets)
					    	{
					    		lShare =  ShareLocalServiceUtil.findByActiveMarketGroupCompany(oMarket.getMarketId(),  Boolean.TRUE,oMarket.getGroupId(),oMarket.getCompanyId());
					         	
					    		 for (Share oShare :lShare)
					    		 {
						    			/* recorremos mercados y acciones  para verificar las estrategias. Por reflexion, creamos la factoria
										 * de objetos que implementa cada strategia */
					    			 	if (!oShare.IsTradeable()) 
					    			 		continue;
					    			 	
						    			List<Strategy> _lStrategies = StrategyLocalServiceUtil.findByActiveCompanyId(Boolean.TRUE, oShare.getCompanyId());
						    			List<StrategyShare> _lStrategiesOfShare = StrategyShareLocalServiceUtil.getByGroupCompanyShareId(oShare.getGroupId(), 
						    						oShare.getCompanyId(), oShare.getShareId());
						    			
						    			for (Strategy oStrategy :_lStrategies)
						    			{
						    				
						    				for (StrategyShare oStrategyShare :_lStrategiesOfShare)
							    			{
						    					// salimos si no es la misma 
						    					if (oStrategyShare.getStrategyId()!=oStrategy.getStrategyID())
						    						continue;
						    					
						    					
						    					if (!oStrategyShare.isActive()) continue;  // si no esta activa, no se trata 
						    					StrategyImpl _strategyImpl= (StrategyImpl) Utilities.getContextClassLoader().loadClass(oStrategy.getClassName()).newInstance();
						    					_strategyImpl.init(oShare.getCompanyId());   // verify if custom fields are created and filled 	    						    				
						    					if (_strategyImpl.verify(oShare, oMarket,oStrategyShare))
						    					{		
						    							long positionId = _strategyImpl.execute(oShare, oMarket);
						    							// 1. ESTABLECEMOS EL CONTEXTO 
						    							if (positionId!=-1) // no hay error 
						    							{	
						    								wrapper.set_ibtarget_share(oShare);
						    								//	wrapper.setStrategyshare(oStrategyShare);
						    								//  1. ABRIMOS CANCELACION EN SU CASO 
 						    							    //  2. ABRIMOS OPERACION CON EL ORDER ESPECIFICAADO EN EL METHOD execute
						    								if (_strategyImpl.getClass().getName().equals(IBStrategyCancelPosition.class.getName()))
						    									wrapper.cancelOrder(_strategyImpl.getTargetContract(), _strategyImpl.getTargetOrder(),_strategyImpl.getChildsOrder(),null,positionId);
						    								else						    									
						    									wrapper.openOrder(_strategyImpl.getTargetContract(), _strategyImpl.getTargetOrder(),_strategyImpl.getChildsOrder(),null,positionId);
						    								/* LA RAZON ES QUE ES QUE METE DOS O TRES OPERACIONES A LA VEZ A PESAR DE TENER LAS VALIDACIONES 
						    								 * SEGUNDO PARO */
						    							//	Thread.sleep(1000);
						    							}
						    							
						    							
						    					}
						    					
						    				}
						    			}  // for (Strategy oStrategy :_lStrategies)
					    		 }  //  for (Share oShare :lShare)
					    	} // for (Market oMarket : lActiveMarkets)							    							
				   		} // en try 
						catch (Exception e)
						{
							 if (wrapper.isConnected()) wrapper.disconnect();
						}
				   	    /* CASO DE QUE SE CAIGA LA TWS*/
						if (!wrapper.isConnected()) 
						{
							 wrapper.disconnect();
							 break;
						}
					} // end wile true 	
			     }  //  if (oTWS.GITraderTWSIsConnected() )

			//	if (wrapper.isConnected()) wrapper.disconnect();
			} // for (Organization _Organization : lOrganization )
	    
		} // if (_CRON_RUNNING==0)  // no se esta ejecutando --> FALTA CONTROL DE EXCEPTIONES PARA CONTROLAR QUE SE PUEDA VOLVER A EJECUTAR 
	/* 	else		
		{
			// si han pasado mas de 5 segundos sin actualizar y esta el cron running, se pone a 0
			Config _confRunning =ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_TRADING_STATUS, companyId, guestGroupId);  
			Date now = new Date();
		   long diffInMillies = Math.abs(now.getTime() - _confRunning.getModifiedDate().getTime() + 2000); 
		   if (diffInMillies>ConfigKeys._AUDIT_TIME_CRON_READ_IN_MILLISECONDS)  // puede haber pasado un error 
		   {
			 	_confRunning.setValue("0"); // no running
			    _confRunning.setModifiedDate(new Date());  // or localtime ????
	   	 		_confRunning = ConfigLocalServiceUtil.updateConfig(_confRunning);
		   }
		}*/
								
}
		

	/* ESTE PUEDE EJECUTARSE POR VECES */
	public static void StartVerifyContractsCron(Message _message) throws Exception {		
		List<Config> lConfig=null;
		int 	_CLIENT_ID = 3;	  // el dos para leer, el 3 para escribir			
		String  _HOST = "127.0.0.1";
		int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
		List<Company> lCompanies = CompanyLocalServiceUtil.getCompanies();
		Company _company = lCompanies.get(0); // tiene que existir
		long companyId =  _company.getCompanyId();		
		Contract oContrat = null;
		/* VERIFICAMOS MERCADOS ACTIVOS */
	    java.util.List<Share> lShare = null;
	    SimpleDateFormat sdf = new SimpleDateFormat();
	    sdf.applyPattern(Utilities._IBTRADER_FUTURE_SHORT_DATE);
	    
	    long guestGroupId = 0;
		try {
			guestGroupId = GroupLocalServiceUtil.getGroup(_company.getCompanyId(), GroupConstants.GUEST).getGroupId();
		} catch (PortalException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();			
		}
	    
		_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_CONTRACTCHECKER_CLIENT_INITIAL, companyId, guestGroupId)).intValue();;	  // el dos para leer, el 3 para escribir
		
		TIMApiWrapper wrapper = null;
		
	    
	    List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
		try
		{
			for (Organization _Organization : lOrganization )
			{
				  
				/* CUERNTA PAPER */
				String simulated = Utilities.getConfigurationValue(IBTraderConstants.keySIMULATION_MODE, companyId, guestGroupId);
				boolean bSIMULATED_TRADING = simulated.equals("1");  
				
				String _keyHOST  = IBTraderConstants.keyTWS_HOST;
				String _keyPORT  = IBTraderConstants.keyTWS_PORT;
				if (bSIMULATED_TRADING)
				{
					_keyHOST  = IBTraderConstants.keyPAPER_TWS_HOST;
					 _keyPORT  = IBTraderConstants.keyPAPER_TWS_PORT;
				}
				
				 _HOST = Utilities.getConfigurationValue(_keyHOST, _Organization.getCompanyId(), _Organization.getGroupId());		 
				 _PORT = Integer.valueOf(Utilities.getConfigurationValue(_keyPORT, _Organization.getCompanyId(), _Organization.getGroupId()));				
			     wrapper = new TIMApiWrapper(_CLIENT_ID);				
				 if (wrapper.isConnected()) wrapper.disconnect();
				 wrapper.connect(_HOST, _PORT,_CLIENT_ID); 	 	
				
				 
				 if (wrapper.isConnected())
			     {

					 /* VERIFICACION DE LA CONECTIVIDAD, SIEMPRE NECESARIO */
					wrapper.reqNextId(); 
					wrapper.set_ibtarget_organization(_Organization);
					
					if (wrapper.getCurrentOrderId()==-1)
					{
						wrapper.disconnect();
						return;
					}
					
					_log.info("Connected, StartVerifyContractsCron, connecting to TWS");

					 
					List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActive(Boolean.TRUE);
			    	for (Market oMarket : lActiveMarkets)
			    	{
			    	     /* TODAS LAS DESACTIVADAS 
			    		 * 1 . SE VERIFICAN 
			    		 * 2. SE CANCELA EL MARKETDATA SI LO TUVIERA  	
			    		 * */
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
			    			
			    			
			    			/* por si se hubiera colado mas peticiones de realtime que 1, las borramos al arrancar los cron  
			    			List<IBOrder> _lrkMktData = IBOrderLocalServiceUtil.findByShareIdCompanyGroup(oShare.getShareId(), oShare.getCompanyId(), oShare.getGroupId());
			    			for (IBOrder rkMktData  : _lrkMktData)	{	 					
								//oTWS.GITraderCancelRealTimeContract((new Long(rkMktData.getPrimaryKey()).intValue()));
			    				m_client.cancelMktData(new Long(rkMktData.getPrimaryKey()).intValue());
							}												    		*/
			    			_log.info("Verifyng de :" + oShare.getSymbol() + ":" +  oShare.getSecurity_type() + ":" + oShare.getExchange() + ":" + oMarket.getCurrency());
			    			wrapper.set_ibtarget_share(oShare);
			    			/* insertamos control de ordenes de peticion */		
		    				long  _INCREMENT_ORDER_ID = wrapper.getNextOrderId();
			    			
							IBOrderLocalServiceUtil.deleteByOrderCompanyGroup(_INCREMENT_ORDER_ID, _Organization.getCompanyId(), _Organization.getGroupId(),_CLIENT_ID,oShare.getShareId());

			    			 IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);
			    			_order.setCompanyId(oMarket.getCompanyId());
			    			_order.setGroupId(oMarket.getGroupId());
			    			_order.setShareID(oShare.getShareId());	
			    			_order.setOrdersId(_INCREMENT_ORDER_ID);
			    			_order.setIbclientId(_CLIENT_ID);
			    			_order.setRemovable_on_reboot(Boolean.TRUE);	 /* los requestid  se borran */
			    			/* pedimos tiempo real */
			    			IBOrderLocalServiceUtil.updateIBOrder(_order);
			    			try {
			    				wrapper.getContractDetails(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat);			
			    				Thread.sleep(1000);
							//	oTWS.GITradergetContractDetails(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat);							
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();							
								//if (oTWS.GITraderTWSIsConnected())
								 if (wrapper.isConnected()) wrapper.disconnect();

							}
			    		} // OVER SHARES 
				        		
				    } // OVER MARKETS
					if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();
					 
			     }  // 		 if (oTWS.GITraderTWSIsConnected() )
			}  // 		for (Organization _Organization : lOrganization )
			//if (m_client.isConnected()) m_client.eDisconnect();
		}
		catch (Exception e)
		{
			if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();		 
		}
		

	}	
	
	/* ESTE PUEDE EJECUTARSE POR VECES */
	public static void StartVerifyFuturesDatesCron(Message _message) throws Exception {					
		
	    
	    List<Share> lFutures = ShareLocalServiceUtil.findByActiveFuturesDates(Boolean.TRUE);
	    SimpleDateFormat sdfSHORT = new SimpleDateFormat();
    	sdfSHORT.applyPattern(Utilities._IBTRADER_FUTURE_LONG_DATE);
		for (Share oShare : lFutures)
		{
			JSONObject  jsonFutureShareParams = JSONFactoryUtil.createJSONObject(oShare.getExpiry_expression());
			String  expirationmonth = jsonFutureShareParams.get("expirationmonth").toString();
			String expirationweek = jsonFutureShareParams.get("expirationweek").toString();;	
			String expirationdayweek = jsonFutureShareParams.get("expirationdayweek").toString();;
			oShare.setExpiry_date(sdfSHORT.parse(Utilities.getActiveFutureDate(expirationmonth, expirationdayweek, expirationweek)));
			ShareLocalServiceUtil.updateShare(oShare);
		}
			
		
		

	}		
	
	/* BORRA ORDENDES MYT ANTIGUAS DE REALTIME, TODAS MENOS DE POSICIONES, 7 DIAS PARA ATRAS   */
	public static void StartDeletingOldOrderRequestCron(Message _message) throws Exception {					
		
	    
		/* DEJAMOS 7 DIAS PARA LOS REINICIOS PREVENTIVOS */
		Calendar cNow = Calendar.getInstance();
		cNow.add(Calendar.DATE, -7);
		cNow.set(Calendar.HOUR_OF_DAY,23);
		cNow.set(Calendar.MINUTE,59);
		cNow.set(Calendar.SECOND,59);
		
		List<IBOrder> _ordersToRemove =  IBOrderLocalServiceUtil.findByDate(cNow.getTime());
		
		for (IBOrder order : _ordersToRemove)
		{
			IBOrderLocalServiceUtil.deleteIBOrder(order);
		}
			
		
		

	}		
	

}