package com.ibtrader.util;

import java.text.SimpleDateFormat;

import java.time.Duration;
import java.time.LocalDateTime ;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ib.client.Contract;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.cron.IBTraderTrade;
import com.ibtrader.data.model.BackTesting;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.BackTestingLocalServiceUtil;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
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
import com.liferay.portal.kernel.util.Validator;
import com.ibtrader.interactive.TIMApiWrapper;
import com.ibtrader.strategy.IBStrategyCancelPosition;


/* OJOOOOOOOOO 
 * reusing CLIET_ID 
 * https://groups.io/g/twsapi/topic/ib_gateway_connect/5111420?p=Created,,,20,1,0,0
 * 
 * */

public class CronUtil {

	private final static Log _log = LogFactoryUtil.getLog(CronUtil.class);
	
	public static void StartSimulationCron(Message _message)  throws Exception 	
	{

		
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
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMM");
		LocalDateTime  _now =  LocalDateTime .now();  
		
	/* 	while (true)
		{
		*/
		List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
		if (lOrganization.isEmpty())
			return;	
			
		for (Organization _Organization : lOrganization )
		{
			try
			{			
			
			/* backtesting no acabadas por modifieddate */
			List<BackTesting> lBackTesting = BackTestingLocalServiceUtil.findByPendingCompanyGroup(_Organization.getCompanyId(), _Organization.getGroupId());
			
			if (Validator.isNull(lBackTesting) || lBackTesting.size()==0)
			{				
				 continue;
			}
			
			for (BackTesting backtesting : lBackTesting)
			{
				
				
				Share oShare = ShareLocalServiceUtil.getShare(backtesting.getShareId()); 
				Market oMarket = MarketLocalServiceUtil.getMarket(oShare.getMarketId());
				
				Calendar DateFromSimulation = Calendar.getInstance();
		    	Calendar DateToSimulation =Calendar.getInstance();
				
		    	Date fromDate = backtesting.getFromDate();
		    	if (backtesting.getFromDate().before(backtesting.getModifiedDate()))
		    		fromDate = backtesting.getModifiedDate();
		    	
		    	/* corte entre medias, acabóp  */
		    	if (backtesting.getModifiedDate().after(backtesting.getToDate()))
		    	{
		    		
		    		backtesting.setStatus(IBTraderConstants.statusSimulation.Processed.toString());
					BackTestingLocalServiceUtil.updateBackTesting(backtesting);
		    		
		    	}
		    		
		    	
		    	
		    	DateToSimulation.setTimeInMillis(backtesting.getToDate().getTime());
		    	/* si ya ha avanzado la simulacion, el modified date es el from */
		    	DateFromSimulation.setTimeInMillis(fromDate.getTime());
		    	DateFromSimulation.set(Calendar.SECOND,0);
		    	DateFromSimulation.set(Calendar.HOUR_OF_DAY,0);
		    	DateFromSimulation.set(Calendar.MILLISECOND,0);
		    	DateFromSimulation.set(Calendar.MINUTE,0);
		    	
		    	
				int NUM_DAYS_ = Utilities.daysDiff(DateFromSimulation.getTime(), DateToSimulation.getTime());	
				
				for (int jDAY=0;jDAY<=NUM_DAYS_;jDAY++) // recorremos dias, ojo a los limites, por eso dia a dia.
	    		{
					
					Calendar DateDayIni = Calendar.getInstance();
					Calendar DateDayFin = Calendar.getInstance();
					
					DateDayIni.setTimeInMillis(DateFromSimulation.getTimeInMillis());
	    			DateDayFin.setTimeInMillis(DateFromSimulation.getTimeInMillis());
	    			
	    			DateDayIni.add(Calendar.DATE, jDAY);
	    		    DateDayFin.add(Calendar.DATE, jDAY);
	    		    
	    		    /* de 0 a 23.59.59 en barras de 5 minutos */
	    			int _NUM_PERIODS_TO_REQUEST = 24 * 60 /  ConfigKeys.SIMULATION_MINUTES_BAR_SIZE;
	    			for (int i=0;i<_NUM_PERIODS_TO_REQUEST;i++) // recorremos dias, ojo a los limites, por eso dia a dia.
	    			{
	    			
	    				DateDayIni.add(Calendar.MINUTE, ConfigKeys.SIMULATION_MINUTES_BAR_SIZE ); //00:05 el primero 
	    				
	    				List<Strategy> _lStrategiesOfShare = StrategyShareLocalServiceUtil.findByActiveStrategies(Boolean.TRUE,
	    						backtesting.getShareId(),backtesting.getCompanyId(), backtesting.getGroupId());
	    				
	    				for (Strategy oStrategyShare :_lStrategiesOfShare)
		    			{
	    					
	    				StrategyImpl _strategyImpl= (StrategyImpl) Utilities.getContextClassLoader().loadClass(oStrategyShare.getClassName()).newInstance();
	    				StrategyShare strategyShare =  StrategyShareLocalServiceUtil.getByCommpanyShareStrategyId(backtesting.getGroupId(), backtesting.getCompanyId(), backtesting.getShareId(), oStrategyShare.getStrategyID());
    					_strategyImpl.init(backtesting.getCompanyId());   // verify if custom fields are created and filled
    					_strategyImpl.init_simulation();   // verify if custom fields are created and filled
    				
    					if (_strategyImpl.verify(oShare, oMarket,strategyShare,DateDayIni.getTime()))
    					{		
    											    							
							long positionId = _strategyImpl.execute(oShare, oMarket,DateDayIni.getTime());
							Position _position = PositionLocalServiceUtil.fetchPosition(positionId);
							if (Validator.isNotNull(_position))
							{
								_position.setBacktestingId(backtesting.getBackTId());
								PositionLocalServiceUtil.updatePosition(_position);
								
							}
    					}
		    			
		    			}  // for (Strategy oStrategy :_lStrategies)
	    				
	    			}
	    			
	    			backtesting.setModifiedDate(DateDayIni.getTime());
					BackTestingLocalServiceUtil.updateBackTesting(backtesting);
	    			 
	    		} // for (int jDAY=0;jDAY<=NUM_DAYS_;jDAY++)
				
				
				backtesting.setStatus(IBTraderConstants.statusSimulation.Processed.toString());
				BackTestingLocalServiceUtil.updateBackTesting(backtesting);
				
			} //BackTesting backtesting : lBackTesting
			
			}
			catch (Exception e)
			{
				_log.error(e.getMessage());
			//	e.printStackTrace();
			}
		 }   // for (Organization _Organization : lOrganization )
	//	} // while
	}

	/* VALIDA CONFIRMACIONES DE ORDENES COMPLETADAS Y NO ENVIADAS A LA API POR DESCONEXIONES  */
	public static void StartOrdersValidator(Message _message) throws Exception 	{
		
		int 	_CLIENT_ID = 4;	  // el dos para leer, el 3 para escribir			
		String  _HOST = "127.0.0.1";
		int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
		boolean _bCheckParam = Boolean.TRUE;
		List<Company> lCompanies = CompanyLocalServiceUtil.getCompanies();
		Company _company = lCompanies.get(0); // tiene que existir
		long companyId =  _company.getCompanyId();		 
		/*  VERIFICAMOS MERCADOS ACTIVOS  
	    long guestGroupId = 0;
		try {
			guestGroupId = GroupLocalServiceUtil.getGroup(_company.getCompanyId(), GroupConstants.GUEST).getGroupId();
		} catch (PortalException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();			
		}
	     */
		
		TIMApiWrapper wrapper = null; 
	    List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
		try
		{
			for (Organization _Organization : lOrganization )
			{
				  
				try
				{
				
				_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_ORDERSCHECKER_CLIENT_INITIAL, companyId, _Organization.getGroupId())).intValue();;	  // el dos para leer, el 3 para escribir	

				/* CUERNTA PAPER */
				boolean bSIMULATED_TRADING = Utilities.getSimulatedTrading(companyId, _Organization.getGroupId());
				
				String _keyHOST  = IBTraderConstants.keyTWS_HOST;
				String _keyPORT  = IBTraderConstants.keyTWS_PORT;
				if (bSIMULATED_TRADING)
				{
					 _keyHOST  = IBTraderConstants.keyPAPER_TWS_HOST;
					 _keyPORT  = IBTraderConstants.keyPAPER_TWS_PORT;
				}
				
			    _HOST = Utilities.getConfigurationValue(_keyHOST, _Organization.getCompanyId(), _Organization.getGroupId());
				
				_PORT = Integer.valueOf(Utilities.getConfigurationValue(_keyPORT, _Organization.getCompanyId(), _Organization.getGroupId()));
				}
				catch (Exception e)
			    {
					
					//					_log.info("Error conectandose a la organización : " + _Organization.getName() + " por parámetros de configuración inexistentes" + e.getMessage());
					continue;// no dispone de los parameteros necesarios por error 
				}
				
			     wrapper = new TIMApiWrapper(_CLIENT_ID);				
				 if (wrapper.isConnected()) wrapper.disconnect();
				 wrapper.connect(_HOST, _PORT,_CLIENT_ID); 	 	
				 
				if (!wrapper.isConnected()) // si no se conecta, lo intentamos con otro clienid , aunque pudiera ser que no tenga configuracion o tws activa 
				{
					Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_ORDERSCHECKER_CLIENT_INITIAL,_Organization.getCompanyId(), _Organization.getGroupId());
										
					Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId(_Organization.getCompanyId(), _Organization.getGroupId());
					_conf.setValue(String.valueOf(NewClientID));
					ConfigLocalServiceUtil.updateConfig(_conf);
					
				}
				 
				 if (wrapper.isConnected())
			     {
					
					 /* VERIFICACION DE LA CONECTIVIDAD, SIEMPRE NECESARIO */
					wrapper.reqNextId(); 
					wrapper.set_ibtarget_organization(_Organization);
					wrapper.setCronId(IBTraderConstants.keyCRON_ORDERSCHECKER_CLIENT_INITIAL);
					
					if (wrapper.getCurrentOrderId()==-1)
					{
						wrapper.disconnect();
						return;
					}
					wrapper.getCurrentTwsTime();
					_log.debug("Connected, StartOrdersValidator, connecting to TWS,currentOrderId:");
					
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
	public static void StartFillHistoricalDataCron(Message _message) throws Exception 	{
		

			int 	_CLIENT_ID = 13;	  // el dos para leer, el 3 para escribir			
			String  _HOST = "127.0.0.1";
			String  _USERTWS  = "edemo";
			int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
			List<Company> lCompanies = CompanyLocalServiceUtil.getCompanies();
			Company _company = lCompanies.get(0); // tiene que existir
			long companyId =  _company.getCompanyId();		
			Contract oContrat = null;
			/* VERIFICAMOS MERCADOS ACTIVOS */
		    java.util.List<Share> lShare = null;
		    SimpleDateFormat sdf = new SimpleDateFormat();
		    sdf.applyPattern(Utilities._IBTRADER_FUTURE_SHORT_DATE);
		    
			TIMApiWrapper wrapper = null;
			
		    List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
			try
			{
				for (Organization _Organization : lOrganization )
				{
					 
					try
					{
					
					_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_FILLHISTORICALDATA, companyId, _Organization.getGroupId())).intValue();;	  // el dos para leer, el 3 para escribir	

					/* CUERNTA PAPER */
					boolean bSIMULATED_TRADING = Utilities.getSimulatedTrading(companyId, _Organization.getGroupId());
					String _keyUSER  = IBTraderConstants.keyUSER_TWS;
					String _keyHOST  = IBTraderConstants.keyTWS_HOST;
					String _keyPORT  = IBTraderConstants.keyTWS_PORT;
					if (bSIMULATED_TRADING)
					{
						 _keyHOST  = IBTraderConstants.keyPAPER_TWS_HOST;
						 _keyPORT  = IBTraderConstants.keyPAPER_TWS_PORT;
						 _keyUSER  = IBTraderConstants.keyPAPER_USER_TWS;
					}
					
				    _HOST = Utilities.getConfigurationValue(_keyHOST, _Organization.getCompanyId(), _Organization.getGroupId());
					_PORT = Integer.valueOf(Utilities.getConfigurationValue(_keyPORT, _Organization.getCompanyId(), _Organization.getGroupId()));
					_USERTWS  = Utilities.getConfigurationValue(_keyUSER, _Organization.getCompanyId(), _Organization.getGroupId());
					}
					catch (Exception e)
				    {
						
						//_log.info("Error conectandose a la organización : " + _Organization.getName() + " por parámetros de configuración inexistentes" + e.getMessage());
						continue;// no dispone de los parameteros necesarios por error 
					}	
				     wrapper = new TIMApiWrapper(_CLIENT_ID);				
					 if (wrapper.isConnected()) wrapper.disconnect();
					 wrapper.connect(_HOST, _PORT,_CLIENT_ID); 	 	
					
					 if (!wrapper.isConnected()) // si no se conecta, lo intentamos con otro clienid , aunque pudiera ser que no tenga configuracion o tws activa 
						{
							Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_FILLHISTORICALDATA,_Organization.getCompanyId(), _Organization.getGroupId());
												
							Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId(_Organization.getCompanyId(), _Organization.getGroupId());
							_conf.setValue(String.valueOf(NewClientID));
							ConfigLocalServiceUtil.updateConfig(_conf);
							
					 }
					 if (wrapper.isConnected())
				     {

						 /* VERIFICACION DE LA CONECTIVIDAD, SIEMPRE NECESARIO */
						wrapper.reqNextId(); 
						wrapper.set_ibtarget_organization(_Organization);
						wrapper.setCronId(IBTraderConstants.keyCRON_FILLHISTORICALDATA);
						wrapper.setUserTWS(_USERTWS);
						if (wrapper.getCurrentOrderId()==-1)
						{
							wrapper.disconnect();
							return;
						}
						
					//	_log.info("Connected, StartVerifyContractsCron, connecting to TWS");

						 
						List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveCompanyGroup(_Organization.getCompanyId(), _Organization.getGroupId(), Boolean.TRUE);
				    	for (Market oMarket : lActiveMarkets)
				    	{
				    	     /* TODAS LAS DESACTIVADAS 
				    		 * 1 . SE VERIFICAN 
				    		 * 2. SE CANCELA EL MARKETDATA SI LO TUVIERA  	
				    		 * */
				    		lShare =  ShareLocalServiceUtil.findByMarketGroupCompany(oMarket.getMarketId(), oMarket.getGroupId(), oMarket.getCompanyId());			         
							for (Share oShare : lShare)
				        	{
								/* el historical data va hacia delante, la creacion de la accion mete un año para la simulacion
								 * en el campo simulation_end_date 
								 * 
								 * 
								 * 1. Buscamos la simulacion hasta el dia de ayer */
								Calendar _yesterday = Calendar.getInstance();
								_yesterday.set(Calendar.HOUR_OF_DAY, 0);
								_yesterday.set(Calendar.MILLISECOND, 0);
								_yesterday.set(Calendar.SECOND,0);
								_yesterday.set(Calendar.MINUTE, 0);
								_yesterday.add(Calendar.DATE,-1);
								
								
									
								
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
				    			
				    												    		
				    			_log.debug("FillHistoricalDataCron de :" + sdf.format(oShare.getDate_validated_trader_provider()));
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
				    			
				    			Calendar calSimulatedDate = Calendar.getInstance();
				    			if (Validator.isNull(oShare.getSimulation_end_date())) // no hay, por error, cogemos la fecha de creación y generamos un año para atras
				    			{
				    				calSimulatedDate.setTimeInMillis(oShare.getCreateDate().getTime());
				    				calSimulatedDate.add(Calendar.MONTH, -12); // trece meses para que haya un año de simulacion 
				    			}
				    			else
				    				calSimulatedDate.setTimeInMillis(oShare.getSimulation_end_date().getTime());
				    			
				    			/* solamente hasta el dia de ayer  */
				    			if (!oShare.isValidated_trader_provider() ||  calSimulatedDate.after(_yesterday))
									continue;
				    			
				    			calSimulatedDate.set(Calendar.HOUR_OF_DAY, 23);
				    			calSimulatedDate.set(Calendar.MILLISECOND, 0);
				    			calSimulatedDate.set(Calendar.SECOND,59);
				    			calSimulatedDate.set(Calendar.MINUTE, 59);
				    			
				    			
				    			SimpleDateFormat form = new SimpleDateFormat(Utilities.__IBTRADER_HISTORICAL_DATE_FORMAT);
				    			/* HASTA UN DIA ADELANTE */
				    			calSimulatedDate.add(Calendar.DATE, 1);
				    			String formattedDate = form.format(calSimulatedDate.getTime());
				    			
				    			try {
				    				_log.debug("getHistoricalData for " + oShare.getSymbol() + ",date:" + formattedDate);	
				    				
				    				wrapper.getHistoricalData(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat,formattedDate);			
				    				Thread.sleep(1000);
				    				
				    				/* HYA QUE PONER UN TIMEOUT DE SEGUNDOS MAXIMO */
				    				int MAX_SECONDS_WAIT = 20;
				    				int execution_time=0;
				    				
				    				Calendar DateIni = Calendar.getInstance();				    				
				    				while (!wrapper.isHistorialDataEnd() &&  execution_time < MAX_SECONDS_WAIT )				   
				    				{
				    					 	Calendar DateEnd = Calendar.getInstance();
				    					 	execution_time = Utilities.secondsDiff(DateIni.getTime(),DateEnd.getTime());
//				    						 	LogTWM.log(Priority.INFO, "oContrat.m_symbol:" + ":" + oContrat.m_symbol + "," + "_HISTORICAL_DATA_REQUEST:" + oTWS._HISTORICAL_DATA_REQUEST + ",Execution Time:"  + execution_time);
				    				
				    				}
				    				if (wrapper.isHistorialDataEnd()) // acaba, actualizamos la fecha del share y cerramos el thread 
				    				{
				    					_log.debug("updating  for " + oShare.getSymbol() + ",date:" + formattedDate);
				    					
				    					oShare.setSimulation_end_date(calSimulatedDate.getTime());
				    					ShareLocalServiceUtil.updateShare(oShare);				    					
				    				}
				    				wrapper.cancelHistoricalData(new Long(_INCREMENT_ORDER_ID).intValue());
				    				if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();				    					
									return;
				    				
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
	
	 
	_CRON_RUNNING = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_READING_STATUS, companyId, guestGroupId)).intValue();
	
	
	
	SimpleDateFormat sdf = new SimpleDateFormat ("yyyyMM");
	LocalDateTime  _now =  LocalDateTime .now();  
	
	/* manteniemiento de los N clients */
	Map<Long,TIMApiWrapper> clientspool = new HashMap<Long,TIMApiWrapper>();
	
	

	// 	iteramos de corrido 
	
	ArrayList<String> lShareRequested = new ArrayList<String>();
	
	while (true)
	{
	List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
	if (lOrganization.isEmpty())
		return;	
		
	for (Organization _Organization : lOrganization )
		{
		 
		  
		try
		{
		
		_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_READING_CLIENT_INITIAL, companyId, _Organization.getGroupId())).intValue();;	  // el dos para leer, el 3 para escribir	

		/* CUERNTA PAPER */
		boolean bSIMULATED_TRADING = Utilities.getSimulatedTrading(companyId, _Organization.getGroupId());
		String _keyHOST  = IBTraderConstants.keyTWS_HOST;
		String _keyPORT  = IBTraderConstants.keyTWS_PORT;
		if (bSIMULATED_TRADING)
		{
			 _keyHOST  = IBTraderConstants.keyPAPER_TWS_HOST;
			 _keyPORT  = IBTraderConstants.keyPAPER_TWS_PORT;
		}
		
	    _HOST = Utilities.getConfigurationValue(_keyHOST, _Organization.getCompanyId(), _Organization.getGroupId());
		
		_PORT = Integer.valueOf(Utilities.getConfigurationValue(_keyPORT, _Organization.getCompanyId(), _Organization.getGroupId()));
		}
		catch (Exception e)
	    {			
			//_log.info("Error conectandose a la organización : " + _Organization.getName() + " por parámetros de configuración inexistentes" + e.getMessage());
			continue;// no dispone de los parameteros necesarios por error 
		}
		 
		 /* VERIF9ICAMOS SI EXISTE UN WRAPPER */
		 TIMApiWrapper wrapper;
		 if (!clientspool.containsKey(_Organization.getOrganizationId()))
		 {
			 wrapper = new TIMApiWrapper(_CLIENT_ID);	
			 //wrapper = new TIMApiWrapper(_CLIENT_ID);				
			/*  if (wrapper.isConnected()) wrapper.disconnect();
			 
			 wrapper.connect(_HOST, _PORT,_CLIENT_ID);
			 if (wrapper.isConnected()) wrapper.disconnect();*/
			 wrapper.connect(_HOST, _PORT,_CLIENT_ID);
			 
			 _log.info("Connected, StartReadingCron for " + _Organization.getName() + ", connecting to TWS for " + _Organization.getName() + ",threadID:" + Thread.currentThread().getId());
			 
			 clientspool.put(_Organization.getOrganizationId(),wrapper);
		 }
		 else
		 {
			 // lo obtenemos 
			 wrapper = clientspool.get(_Organization.getOrganizationId());
			if (!wrapper.isConnected()) 
					 wrapper.connect(_HOST, _PORT,_CLIENT_ID);
			
			if (!wrapper.isConnected()) // si no se conecta, lo intentamos con otro clienid , aunque pudiera ser que no tenga configuracion o tws activa 
			{
				Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_READING_CLIENT_INITIAL,_Organization.getCompanyId(), _Organization.getGroupId());
									
				Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId(_Organization.getCompanyId(), _Organization.getGroupId());
				_conf.setValue(String.valueOf(NewClientID));
				ConfigLocalServiceUtil.updateConfig(_conf);
				
			}
			
		 }
				 
		// if (oTWS.GITraderTWSIsConnected() )
		if (wrapper.isConnected())
	    {
			
				/* VERIFICACION DE LA CONECTIVIDAD, SIEMPRE NECESARIO */
				wrapper.reqNextId(); 
				wrapper.set_ibtarget_organization(_Organization);
				wrapper.setCronId(IBTraderConstants.keyCRON_READING_CLIENT_INITIAL);
				if (wrapper.getCurrentOrderId()==-1)
				{
					wrapper.disconnect();
					clientspool.remove(_Organization.getOrganizationId());
					continue;

				}
				
			 
			 	 /* 20180417 con el uso de la variable estatica en cada listemer, parece que no hace falta este control 
			 	 ALMACENIAMOS TIMESTAMP DE CONTROL 		*/	  
			    LocalDateTime  _nowAuditTime =  LocalDateTime .now();  			    
			    Duration Period = Duration.between(_nowAuditTime, _now);
			    long milsecondsdiff = Math.abs(Period.toMillis());
			  //  _log.info("Updating AuditDate...?" + milsecondsdiff + ", mayor que " + ConfigKeys._AUDIT_TIME_CRON_READ_IN_MILLISECONDS);
			    
			  
			    
			    if (milsecondsdiff > ConfigKeys._AUDIT_TIME_CRON_READ_IN_MILLISECONDS)
			    {			    	
			    	 Config _confRunning = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_READING_STATUS, companyId, guestGroupId);
			    	 _now =  LocalDateTime .now(); 
			    	 _confRunning.setModifiedDate(new Date());  // or localtime ????
			    	 ConfigLocalServiceUtil.updateConfig(_confRunning);
			    	//  _log.info("Updated AuditDate StartReadingCron");
			    }
			    
				Contract oContrat = null;
				/* VERIFICAMOS MERCADOS ACTIVOS */			    
			    java.util.List<Share> lShare = null;
			    /* 1. VERIFICAMOS QUE EXISTA UNA PETICION PARA EL HISTORICAL DATA QUE NO HAYA FINALIZADO SIN ERROR */
			    /* LANZAMOS LA OPERACION DE CONTINUO */ 
			   	
				try
				{			   				    	 
				    	 // empezamos a contar desde 5 o 10 minutos antes de la apertura para contar precios
				    	String _HORACTUAL = Utilities.getActualHourFormatPlusMinutes(Utilities.getGlobalIBDateNowFormat(),10); 
				    	
				    	List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveStartEndHour(_HORACTUAL, _HORACTUAL,Boolean.TRUE,_Organization.getCompanyId(), _Organization.getGroupId());
					    
				    	for (Market oMarket : lActiveMarkets)
				    	{
				    						    		
				    		lShare =  ShareLocalServiceUtil.findByActiveMarketGroupCompany(oMarket.getMarketId(), Boolean.TRUE, oMarket.getGroupId(), oMarket.getCompanyId());
				    		for (Share oShare : lShare)
					    	{				    		
				    				
				    			
				    			/* METEMOS UN CASO DE MULTIPLES CLIENTES CON UNA TRADESTAATION  */
				    			
				    			boolean bToRequest=true;
				    			String _Expiration = "";
				      		    if (oShare.getExpiry_date()!=null) _Expiration = sdf.format(oShare.getExpiry_date());		
				    			String _uniqueShare = oShare.getSymbol() + "|" + _Expiration + "|" + oShare.getGroupId() + "|" + oShare.getCompanyId();

				    			/* cancelar el mkt si esta pedido */
				    			
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
				    				_log.info("TradingRead de :" + _uniqueShare + ":" +  oShare.getSecurity_type() + ":" + oShare.getExchange() + ":" + oMarket.getCurrency());

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
					 if (wrapper.isConnected())
					 {
						 wrapper.disconnect();
						 lShareRequested.clear();
		    		     _log.debug("CronTradingRead:" + e.getMessage());

						 
					 }
				}
					
				/* CASO DE QUE SE CAIGA LA TWS*/
				if (!wrapper.isConnected()) 
				{
					 wrapper.disconnect();
					 lShareRequested.clear();
					 break;
				}
			 }   // while 
	     } //  end 		 if (oTWS.GITraderTWSIsConnected() )
		// if (wrapper.isConnected()) wrapper.disconnect();
		 
		} //	for (Organization _Organization : lOrganization )	
	
	//}  Cron runnig
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
		
		
	    LocalDateTime  _now =  LocalDateTime .now();  
	 		// sacamos organizaciones padre 
		List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
		/* manteniemiento de los N clients */
		Map<Long,TIMApiWrapper> clientspool = new HashMap<Long,TIMApiWrapper>();
		
		if (lOrganization.isEmpty())
				return;
		// iteramos de corrido 
		while (true)
		{
		
		for (Organization _Organization : lOrganization )
		{
			try
			{
			
			_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_TRADING_CLIENT_INITIAL, companyId, _Organization.getGroupId())).intValue();;	  // el dos para leer, el 3 para escribir	

			/* CUERNTA PAPER */
			boolean bSIMULATED_TRADING = Utilities.getSimulatedTrading(companyId, _Organization.getGroupId());
			String _keyHOST  = IBTraderConstants.keyTWS_HOST;
			String _keyPORT  = IBTraderConstants.keyTWS_PORT;
			if (bSIMULATED_TRADING)
			{
				 _keyHOST  = IBTraderConstants.keyPAPER_TWS_HOST;
				 _keyPORT  = IBTraderConstants.keyPAPER_TWS_PORT;
			}
			
		    _HOST = Utilities.getConfigurationValue(_keyHOST, _Organization.getCompanyId(), _Organization.getGroupId());
			_PORT = Integer.valueOf(Utilities.getConfigurationValue(_keyPORT, _Organization.getCompanyId(), _Organization.getGroupId()));
			}
			catch (Exception e)
		    {
				
				//_log.info("Error conectandose a la organización : " + _Organization.getName() + " por parámetros de configuración inexistentes" + e.getMessage());
				continue;// no dispone de los parameteros necesarios por error 
			}
			
			 
			 /* VERIF9ICAMOS SI EXISTE UN WRAPPER */
			 TIMApiWrapper wrapper;
			  
			 if (!clientspool.containsKey(_Organization.getOrganizationId()))
			 {
				 wrapper = new TIMApiWrapper(_CLIENT_ID);	
				 //wrapper = new TIMApiWrapper(_CLIENT_ID);				
				 wrapper.connect(_HOST, _PORT,_CLIENT_ID);
				 
				 if (wrapper.isConnected()) 				 
					 clientspool.put(_Organization.getOrganizationId(),wrapper);
				
			 }
			 else
			 {
				 // lo obtenemos 
				 wrapper = clientspool.get(_Organization.getOrganizationId());
				 
				_log.debug("threadID:" + Thread.currentThread().getId() + ",Wrapper obtained from pool:" + wrapper.getClient().connectedHost() + "," + _PORT + ",group:" + _Organization.getGroupId() + ",_CLIENT_ID:" + _CLIENT_ID);
				 
				 if (!wrapper.isConnected()) 
				   wrapper.connect(_HOST, _PORT,_CLIENT_ID);
				//_log.info("Connection to," + _Organization.getName() + "clientid:" + _CLIENT_ID + ", getting wrapper pool, conectado:" + wrapper.isConnected());
			 }
			 if (!wrapper.isConnected()) // si no se conecta, lo intentamos con otro clienid , aunque pudiera ser que no tenga configuracion o tws activa 
			 {
				Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_TRADING_CLIENT_INITIAL,_Organization.getCompanyId(), _Organization.getGroupId());
									
				Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId(_Organization.getCompanyId(), _Organization.getGroupId());
				_conf.setValue(String.valueOf(NewClientID));
				ConfigLocalServiceUtil.updateConfig(_conf);
				
			 }
			 
			 if (wrapper.isConnected())
		     {
								
				wrapper.set_ibtarget_organization(_Organization);
				wrapper.setCronId(IBTraderConstants.keyCRON_TRADING_CLIENT_INITIAL);
				
				if (wrapper.getCurrentOrderId()==-1)
				{
					wrapper.disconnect();
					clientspool.remove(_Organization.getOrganizationId());
					continue;
				}
		
					/* ALMACENIAMOS TIMESTAMP DE CONTROL */
					/* 20180417 con el uso de la variable estatica en cada listemer, parece que no hace falta este control */
				    LocalDateTime  _nowAuditTime =  LocalDateTime .now();  			    
				    Duration Period = Duration.between(_nowAuditTime, _now);
				    long milsecondsdiff = Math.abs(Period.toMillis());
				    if (milsecondsdiff>ConfigKeys._AUDIT_TIME_CRON_READ_IN_MILLISECONDS)
				    {
				    	
				    	Config _confRunning =ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_TRADING_STATUS, companyId, guestGroupId);
				    	 _now =  LocalDateTime.now(); 
				    	 _confRunning.setModifiedDate(new Date());  // or localtime ????
				    	// _confRunning.setValue(String.valueOf(1));
				    	 ConfigLocalServiceUtil.updateConfig(_confRunning);
				    	_log.info("Updating  TradingCron to database connecting to TWS clientID:" +_CLIENT_ID);
				    	 
				    } 
				 	String _HORACTUAL = Utilities.getActualHourFormatPlusMinutes(Utilities.getGlobalIBDateNowFormat(),10); 				   	 
			   	 	List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveStartEndHour(_HORACTUAL, _HORACTUAL,Boolean.TRUE,_Organization.getCompanyId(), _Organization.getGroupId());				   	 	
			   	  
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
					    					//if (_strategyImpl.verify(oShare, oMarket,oStrategyShare) && wrapper.isConnected())

					    					if (_strategyImpl.verify(oShare, oMarket,oStrategyShare,null) && wrapper.isConnected())
					    					{		
					    											    							
					    							long positionId = _strategyImpl.execute(oShare, oMarket,null);
					    							// 1. ESTABLECEMOS EL CONTEXTO 
					    							if (positionId!=-1) // no hay error 
					    							{	
					    								if (!wrapper.isConnected()) // si se produce un error, salimos, eliminando order.
					    								{
					    									Position _position = PositionLocalServiceUtil.fetchPosition(positionId);
					    									/* borramos ordenes y posicion */
					    									if (_position!=null)
					    									{
					    										if (_position.getPositionId_tws_in()>0)	
						    										IBOrderLocalServiceUtil.deleteByOrderCompanyGroup(_position.getPositionId_tws_in(), _Organization.getCompanyId(), _Organization.getGroupId(),_position.getClientId_in(),-1);
						    									if (_position.getPositionId_tws_out()>0)	
						    										IBOrderLocalServiceUtil.deleteByOrderCompanyGroup(_position.getPositionId_tws_out(), _Organization.getCompanyId(), _Organization.getGroupId(),_position.getClientId_out(),-1);
					    										PositionLocalServiceUtil.deletePosition(positionId);
					    									}
						    								continue;
					    								} // fin de wrapper.isConnected()
					    								_log.debug("Opening order CronUTIL,threadID:" + Thread.currentThread().getId()  +",wrapper:" + wrapper.getClient().connectedHost() + "," + _PORT + ",group:" + _Organization.getGroupId() + ",_CLIENT_ID:" + _CLIENT_ID);
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
				}// for (Organization _Organization : lOrganization )
		     }  //  if (oTWS.GITraderTWSIsConnected() )

		//	if (wrapper.isConnected()) wrapper.disconnect();
		}  // end wile true 	
	    
			//}  if (_CRON_RUNNING==0)  // no se esta ejecutando --> FALTA CONTROL DE EXCEPTIONES PARA CONTROLAR QUE SE PUEDA VOLVER A EJECUTAR 
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
	    
		
		TIMApiWrapper wrapper = null;
		
	    
	    List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
		try
		{
			for (Organization _Organization : lOrganization )
			{
				 

				try
				{
				
				_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_CONTRACTCHECKER_CLIENT_INITIAL, companyId, _Organization.getGroupId())).intValue();;	  // el dos para leer, el 3 para escribir	

				/* CUERNTA PAPER */
				boolean bSIMULATED_TRADING = Utilities.getSimulatedTrading(companyId, _Organization.getGroupId());
				String _keyHOST  = IBTraderConstants.keyTWS_HOST;
				String _keyPORT  = IBTraderConstants.keyTWS_PORT;
				if (bSIMULATED_TRADING)
				{
					 _keyHOST  = IBTraderConstants.keyPAPER_TWS_HOST;
					 _keyPORT  = IBTraderConstants.keyPAPER_TWS_PORT;
				}
				
			    _HOST = Utilities.getConfigurationValue(_keyHOST, _Organization.getCompanyId(), _Organization.getGroupId());
				
				_PORT = Integer.valueOf(Utilities.getConfigurationValue(_keyPORT, _Organization.getCompanyId(), _Organization.getGroupId()));
				}
				catch (Exception e)
			    {
					
					//_log.info("Error conectandose a la organización : " + _Organization.getName() + " por parámetros de configuración inexistentes" + e.getMessage());
					continue;// no dispone de los parameteros necesarios por error 
				}	
			     wrapper = new TIMApiWrapper(_CLIENT_ID);				
				 if (wrapper.isConnected()) wrapper.disconnect();
				 wrapper.connect(_HOST, _PORT,_CLIENT_ID); 	 	
				
				 if (!wrapper.isConnected()) // si no se conecta, lo intentamos con otro clienid , aunque pudiera ser que no tenga configuracion o tws activa 
					{
						Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_CONTRACTCHECKER_CLIENT_INITIAL,_Organization.getCompanyId(), _Organization.getGroupId());
											
						Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId(_Organization.getCompanyId(), _Organization.getGroupId());
						_conf.setValue(String.valueOf(NewClientID));
						ConfigLocalServiceUtil.updateConfig(_conf);
						
				 }
				 if (wrapper.isConnected())
			     {

					 /* VERIFICACION DE LA CONECTIVIDAD, SIEMPRE NECESARIO */
					wrapper.reqNextId(); 
					wrapper.set_ibtarget_organization(_Organization);
					wrapper.setCronId(IBTraderConstants.keyCRON_CONTRACTCHECKER_CLIENT_INITIAL);
					if (wrapper.getCurrentOrderId()==-1)
					{
						wrapper.disconnect();
						return;
					}
					
				//	_log.info("Connected, StartVerifyContractsCron, connecting to TWS");

					 
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
	
	/* ESTE PUEDE EJECUTARSE POR VECES 
	 * 
	 *  EXPIRAN HOY, SE LES CAMBIA LA FECHA. 
	 * */
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