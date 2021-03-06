package com.ibtrader.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.Duration;
import java.time.LocalDateTime ;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ib.client.Contract;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.BackTesting;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.BackTestingLocalServiceUtil;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.data.service.StrategyLocalServiceUtil;
import com.ibtrader.data.service.StrategyShareLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.ibtrader.interactive.TIMApiWrapper;
import com.ibtrader.strategy.IBStrategyCancelPosition;


/* OJOOOOOOOOO 
 * reusing CLIET_ID 
 * https://groups.io/g/twsapi/topic/ib_gateway_connect/5111420?p=Created,,,20,1,0,0
 * 
 * */

public class CronUtil {

	private final  Log _log = LogFactoryUtil.getLog(CronUtil.class);
	
	
	
	public  CronUtil()  {return;} 	

	public  void StartSimulationCron(Message _message)  throws Exception 	
	{

		
		long companyId =  PortalUtil.getDefaultCompanyId();
		
	
		
		BackTesting _freshBackTesting = null;
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
			
			if (_Organization.getOrganizationId()==20165) continue;  // LIFERAY 	 EXCEPTION

			/* backtesting no acabadas por modifieddate */
			List<BackTesting> lBackTesting = BackTestingLocalServiceUtil.findByPendingCompanyGroup(_Organization.getCompanyId(), _Organization.getGroupId());
			
			if (Validator.isNull(lBackTesting) || lBackTesting.size()==0)
			{				
				 continue;
			}
			
			for (BackTesting backtesting : lBackTesting)
			{
				
				
				Share oShare = ShareLocalServiceUtil.fetchShare(backtesting.getShareId()); 
				Market oMarket = MarketLocalServiceUtil.getMarket(oShare.getMarketId());
				
				
				Calendar DateFromSimulation = Calendar.getInstance();
		    	Calendar DateToSimulation =Calendar.getInstance();
				
		    	Date fromDate = backtesting.getFromDate();
		    	if (backtesting.getFromDate().before(backtesting.getLastRunDate()))
		    		fromDate = backtesting.getLastRunDate();
		    	
		    	/* corte entre medias, acabóp  */
		    	if (backtesting.getLastRunDate().after(backtesting.getToDate()) || (Validator.isNull(oShare) || Validator.isNull(oMarket)))
		    	{
		    		
		    		backtesting.setStatus(IBTraderConstants.statusSimulation.Processed.toString());
		    		backtesting.setEndDate(new Date());
					BackTestingLocalServiceUtil.updateBackTesting(backtesting);
					break;
		    		
		    	}
		    	/* arranque del proceso */
		    	if (Validator.isNull(backtesting.getStartDate()))
		    	{		    	
	    			backtesting.setStartDate(new Date());	
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
	    		    
	    		    DateDayFin.set(Calendar.HOUR, 23);
	    		    DateDayFin.set(Calendar.MINUTE, 59);
	    		    DateDayFin.set(Calendar.SECOND, 59);
	    		    
	    		    _log.debug("Backtesting for " + oShare.getSymbol()  + ",date:" + DateDayIni.getTime());
	    		    
	    		    /* 	si no hay realtime ese dia lo saltamos */
	    			HistoricalRealtime existsRealTime = HistoricalRealtimeLocalServiceUtil.findFirstRealTimeBetweenDates(backtesting.getShareId(), backtesting.getCompanyId(), backtesting.getGroupId(), DateDayIni.getTime(), DateDayFin.getTime());
	    			if (!Validator.isNull(existsRealTime)) // hay tiempo real ese dia, tratamos de buscar operaciones 
	    			{
	    					
		    		    /* de 0 a 23.59.59 en barras de 5 minutos */
		    			int _NUM_PERIODS_TO_REQUEST = 24 * 60 /  ConfigKeys.SIMULATION_MINUTES_BAR_SIZE;
		    			for (int i=0;i<_NUM_PERIODS_TO_REQUEST;i++) // recorremos dias, ojo a los limites, por eso dia a dia.
		    			{
		    			
		    				DateDayIni.add(Calendar.MINUTE, ConfigKeys.SIMULATION_MINUTES_BAR_SIZE ); //00:05 el primero 
		    				
		    				
		    				_log.debug("Backtesting for " + oShare.getSymbol()  + ",timeframe :" +  DateDayIni.getTime());
		    				
		    				List<Strategy> _lStrategiesOfShare = StrategyShareLocalServiceUtil.findByActiveStrategies(Boolean.TRUE,
		    						backtesting.getShareId(),backtesting.getCompanyId(), backtesting.getGroupId());
		    				
		    				
		    				/* TENEMOS QUE ORDENARLAS POR SI ES UNA ESTRATEGIA DE SALIDA PRIMERO, 
		    				 * POR SI EN EL MISMO TRAMO SE SALE Y ENTRA A LA VEZ (P.E. MEDIAS MOVILES 
		    				 * 
		    				 *  PRIMERO LAS OUT Y DESPUES LAS IN 
		    				 * */
		    				
		    				Comparator<Strategy> strategyTypeComparator = Comparator.comparing(Strategy::getType);
		    				Comparator<Strategy> strategyTypeComparatorReversed  = strategyTypeComparator.reversed();
		    				_lStrategiesOfShare.sort(strategyTypeComparatorReversed);
		    				
		    				
		    				for (Strategy oStrategyShare :_lStrategiesOfShare)
			    			{
		    					
		    				StrategyImpl _strategyImpl= (StrategyImpl) Utilities.getContextClassLoader().loadClass(oStrategyShare.getClassName()).newInstance();
		    				StrategyShare strategyShare =  StrategyShareLocalServiceUtil.getByCommpanyShareStrategyId(backtesting.getGroupId(), backtesting.getCompanyId(), backtesting.getShareId(), oStrategyShare.getStrategyID());
	    					_strategyImpl.init(backtesting.getCompanyId());   // verify if custom fields are created and filled
	    					_strategyImpl.init_simulation(backtesting);   // verify if custom fields are created and filled
	    				
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
	    			} // if (!Validator.isNull(existsRealTime)) 
	    			
	    			/* ANTES DE ACTUALIZAR, VERIFICAMOS SI SE HA PARADO O ELIMINADO EL BACKTESTING A TRAVES DE LA ADMINISTRACION */
	    			_freshBackTesting = BackTestingLocalServiceUtil.fetchBackTesting(backtesting.getBackTId());
	    			if (Validator.isNull(_freshBackTesting) || !_freshBackTesting.getStatus().equals(backtesting.getStatus()))
	    					break;
	    			_freshBackTesting.setLastRunDate(DateDayIni.getTime());
					BackTestingLocalServiceUtil.updateBackTesting(_freshBackTesting);
	    			 
	    		} // for (int jDAY=0;jDAY<=NUM_DAYS_;jDAY++)
				
				_freshBackTesting = BackTestingLocalServiceUtil.fetchBackTesting(backtesting.getBackTId());
    			if (Validator.isNull(_freshBackTesting) || !_freshBackTesting.getStatus().equals(backtesting.getStatus()))
    					break;
    			
				
    			_freshBackTesting.setEndDate(new Date());
    			_freshBackTesting.setStatus(IBTraderConstants.statusSimulation.Processed.toString());
				
				
				/* llamamos al servicio para actualizar los totales */
    			Calendar _cSimulationTo = Calendar.getInstance();
    			_cSimulationTo.setTime(_freshBackTesting.getEndDate());  // 1 dia mas
    			_cSimulationTo.add(Calendar.DATE, 1);
    			 
				String position_mode = Utilities.getPositionModeType(_freshBackTesting.getFromDate(), _freshBackTesting.getCompanyId(),_freshBackTesting.getGroupId()); 
				JSONArray results = PositionLocalServiceUtil.findPositionClosedResults(_freshBackTesting.getFromDate(), _cSimulationTo.getTime(), _freshBackTesting.getGroupId(), _freshBackTesting.getCompanyId(), position_mode, _freshBackTesting.getBackTId());
				long totalpositions_buy=0;
				long totalpositions_sell=0;
				String  type  = "";
				double totalprofit_buy=0;
				double totalprofit_sell=0;
					
				if (results != null && results.length() > 0) {
					for (int i = 0; i < results.length(); i++) {
						JSONObject result = results.getJSONObject(i);						
						type = result.getString("TIPO").replace("\"","");
						if (type.equals(PositionStates.statusTWSFire.BUY.toString()))
						{
								totalprofit_buy    += result.getDouble("MARGENBENEFICIO");
								totalpositions_buy +=  result.getLong("OPERACIONES");
						}
						if (type.equals(PositionStates.statusTWSFire.SELL.toString()))
						{
							totalprofit_sell    += result.getDouble("MARGENBENEFICIO");
							totalpositions_sell +=  result.getLong("OPERACIONES");
						}

					}
					
					_freshBackTesting.setCountordersBUY(totalpositions_buy);
					_freshBackTesting.setCountordersSELL(totalpositions_sell);
					_freshBackTesting.setProfitordersBUY(totalprofit_buy);
					_freshBackTesting.setProfitordersSELL(totalprofit_sell);
					_freshBackTesting.setStatus(IBTraderConstants.statusSimulation.Processed.toString());
					
				}
				
				BackTestingLocalServiceUtil.updateBackTesting(_freshBackTesting);
				
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
	public  void StartOrdersValidator(Message _message) throws Exception 	{
		
		int 	_CLIENT_ID = 4;	  // el dos para leer, el 3 para escribir			
		String  _HOST = "127.0.0.1";
		int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
		boolean _bCheckParam = Boolean.TRUE;
		long companyId =  PortalUtil.getDefaultCompanyId();	 
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
				 
				if (_Organization.getOrganizationId()==20165) continue;  // LIFERAY 	 EXCEPTION
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
					if (Validator.isNull(wrapper))		
	    			{
    					wrapper = new TIMApiWrapper(_CLIENT_ID);
    					wrapper.connect(_HOST, _PORT,_CLIENT_ID);
	    			}
					else    				
						if (Validator.isNotNull(wrapper) && !wrapper.isConnected())
						{
							wrapper.disconnect();
							wrapper.connect(_HOST, _PORT,_CLIENT_ID);
						}				 	
					 
					if (!wrapper.isConnected()) // si no se conecta, lo intentamos con otro clienid , aunque pudiera ser que no tenga configuracion o tws activa 
					{
						Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_ORDERSCHECKER_CLIENT_INITIAL,_Organization.getCompanyId(), _Organization.getGroupId());
											
						Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId(_Organization.getCompanyId(), _Organization.getGroupId());
						_conf.setValue(String.valueOf(NewClientID));
						ConfigLocalServiceUtil.updateConfig(_conf);
						return;
						
					}
		
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
					_log.trace("Connected, StartOrdersValidator, connecting to TWS,currentOrderId:");
					
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
		
			//if (m_client.isConnected()) m_client.eDisconnect();
		
		}
		
		catch (Exception e)
		{
			if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();
		}
		
		
	}
	
	public  void StartRemovePastRealTime(Message _message) throws Exception 	{
		
		
	long companyId =  PortalUtil.getDefaultCompanyId();

	    List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(companyId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
		
		for (Organization _Organization : lOrganization )
		{
			try
			{
				RealtimeLocalServiceUtil.removeScheduledRealTimes(_Organization.getCompanyId(), _Organization.getGroupId());
			}
			catch (Exception e)
			{			
			}
		}
		
	}
	
	
	/*RELLENAMOS DIA ACTUAL y ANTERIORES SI NO HAY DATOS CON BARRAS NECESARIAS PARA MEDIA MOVIL ESPONENENCIA, 
	 * UNOS 100 PERIODOS   */
	public  void StartFillRequiredRealtime(Message _message) throws Exception 	{
		

			int 	_CLIENT_ID = 13;	  // el dos para leer, el 3 para escribir			
			String  _HOST = "127.0.0.1";
			String  _USERTWS  = "edemo";
			int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
			long companyId =  PortalUtil.getDefaultCompanyId();
			Contract oContrat = null;
			/* VERIFICAMOS MERCADOS ACTIVOS */
		    java.util.List<Share> lShare = null;
		    SimpleDateFormat sdf = new SimpleDateFormat();
		    sdf.applyPattern(Utilities._IBTRADER_FUTURE_SHORT_DATE);
		    
			TIMApiWrapper wrapper = null;
			
			/* CAMBIO 20190712 
			 * hay concurrencia y se cruzan los iborder , ejecutamos una vez cogiendo el ultimo realtime en vez de iterar  y salimos
			 * 
			 * xc
			findTargetShareToFillRealtime share = RealtimeLocalServiceUtil.findLastCompanyShare(companyId, shareId, groupId)
			*/
			
			Share  share = ShareLocalServiceUtil.findTargetShareToFillRealtime(Boolean.TRUE, Boolean.TRUE);
			
			try
			{
	    			
			_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_FILLHISTORICALDATA, share.getCompanyId(), share.getGroupId())).intValue();;	  // el dos para leer, el 3 para escribir	

			/* CUERNTA PAPER */
			boolean bSIMULATED_TRADING = Utilities.getSimulatedTrading(share.getCompanyId(), share.getGroupId());
			String _keyUSER  = IBTraderConstants.keyUSER_TWS;
			String _keyHOST  = IBTraderConstants.keyTWS_HOST;
			String _keyPORT  = IBTraderConstants.keyTWS_PORT;
			if (bSIMULATED_TRADING)
			{
				 _keyHOST  = IBTraderConstants.keyPAPER_TWS_HOST;
				 _keyPORT  = IBTraderConstants.keyPAPER_TWS_PORT;
				 _keyUSER  = IBTraderConstants.keyPAPER_USER_TWS;
			}
			
		    _HOST = Utilities.getConfigurationValue(_keyHOST, share.getCompanyId(), share.getGroupId());
			_PORT = Integer.valueOf(Utilities.getConfigurationValue(_keyPORT, share.getCompanyId(), share.getGroupId()));
			_USERTWS  = Utilities.getConfigurationValue(_keyUSER, share.getCompanyId(), share.getGroupId());
			
			wrapper = new TIMApiWrapper(_CLIENT_ID);
			wrapper.connect(_HOST, _PORT,_CLIENT_ID);
									 							
			if (!wrapper.isConnected()) // si no se conecta, lo intentamos con otro clienid , aunque pudiera ser que no tenga configuracion o tws activa 
			{
					Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_FILLHISTORICALDATA,share.getCompanyId(), share.getGroupId());
										
					Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId(share.getCompanyId(), share.getGroupId());
					_conf.setValue(String.valueOf(NewClientID));
					ConfigLocalServiceUtil.updateConfig(_conf);
					return;
					
			}
		    Group group = GroupLocalServiceUtil.fetchGroup(share.getGroupId());
		    Market market= MarketLocalServiceUtil.fetchMarket(share.getMarketId());
		    Organization  organization = null;
		    if (Validator.isNotNull(group))  
		    	organization =  OrganizationLocalServiceUtil.fetchOrganization(group.getClassPK());
		    
		 	/* VERIFICACION DE LA CONECTIVIDAD, SIEMPRE NECESARIO */
			wrapper.set_ibtarget_share(share);
			wrapper.reqNextId(); 
			wrapper.set_ibtarget_organization(organization);
			wrapper.setCronId(IBTraderConstants.keyCRON_FILLHISTORICALDATA);
			wrapper.setUserTWS(_USERTWS);
			if (wrapper.getCurrentOrderId()==-1)
			{
				wrapper.disconnect();
				return;
			}
			
			long  _INCREMENT_ORDER_ID = wrapper.getNextOrderId();
			
			IBOrderLocalServiceUtil.deleteByOrderCompanyGroup(_INCREMENT_ORDER_ID, share.getCompanyId(), share.getGroupId(),_CLIENT_ID,share.getShareId());
			
			IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);
			_order.setCompanyId(share.getCompanyId());
			_order.setGroupId(share.getGroupId());
			_order.setShareID(share.getShareId());	
			_order.setOrdersId(_INCREMENT_ORDER_ID);
			_order.setIbclientId(_CLIENT_ID);
			_order.setRemovable_on_reboot(Boolean.TRUE);	 /* los requestid  se borran */
			/* pedimos tiempo real */
			IBOrderLocalServiceUtil.updateIBOrder(_order);
			
			/* 	CALCULAMOS LA SERIE DE BARRAS (5 minutos)  FROM Y TO SEGUN APERTURAS DE MERCADOS Y ACCIONES */
			List<String> lPeriods = new ArrayList<String>();

			Calendar _tomorrow = Calendar.getInstance();
			_tomorrow.add(Calendar.DATE,1); // SITE DIAS ATRAS, HOY NO DEBERIA ESTAR CERRADO
			_tomorrow.set(Calendar.HOUR_OF_DAY, 23);
			_tomorrow.set(Calendar.MILLISECOND, 0);
			_tomorrow.set(Calendar.SECOND,59);
			_tomorrow.set(Calendar.MINUTE, 59);
			
			Calendar hoyOpenMarket = Calendar.getInstance();
			
			/* Intentamos con este, si por lo que sea dado error de expiración antigua, o por symbol no existente, pasamos al siguiente, de otra manera, 
			 * se queda enbuclado en el mismo siempre */
			
			/* actualizamos a este momento */
			share.setDate_filled_realtime_gaps(hoyOpenMarket.getTime());
			ShareLocalServiceUtil.updateShare(share);
			
			/* REDONDEMOS A LA BARRA MAS CERCANA */ 		    		
		    int minute = hoyOpenMarket.get(Calendar.MINUTE);
		    minute = minute % 5;
		    if (minute != 0) {
		        int minuteToAdd = 5 - minute;
		        hoyOpenMarket.add(Calendar.MINUTE, minuteToAdd);
		    }
				
		    
		    
		    
			/* MODIFICACION, MEDIAS MOVILES HASTA EL PERIDO N, N-1, N-2 */
			/* SUMAMOS UN PERIOD */	
			
		    /* YA QUE PARA BUSCAR LOS PERIODOS BUSCA LOS MERCADOS ABIERTOS ANTERIORES PARA RELLENAR EL REALTIME QUE SEA NECESARIO, LO COGE 
		     * DEL TRADING HOURS Y AHI NO HAY HORAS DE MERCADOS PASADOS, ASI QUE SUSAMOS EL SUMLATION = TRUE PARA QUE TIRE DEL 
		     * HISTORICAL Y SABER LA HORA Y FIN DE MERCADO DEL SHARE 
		     */
		    
			lPeriods = BaseIndicatorUtil.getPeriodsMinutesMobileAvg(hoyOpenMarket.getTime(), ConfigKeys.INDICATORS_MIN_SERIE_COUNT + 1 ,ConfigKeys.DEFAULT_TIMEBAR_MINUTES, Boolean.TRUE, market, Boolean.TRUE, share.getShareId());
			
			SimpleDateFormat _sdf = new SimpleDateFormat(Utilities.__IBTRADER_SQL_DATE_);
    	    Date toWithOpenMarketsTimes  =null;
    	    toWithOpenMarketsTimes = _sdf.parse(lPeriods.get(0));
    			
    	    /* ALGUNA RAZON SI BUSCAMOS EL DIA SIGUIENTE HASTA LAS 23:29 FUNCIONA MEJOR EL REALTIME */
    	    
			hoyOpenMarket.setTime(toWithOpenMarketsTimes);				    			
			SimpleDateFormat form = new SimpleDateFormat(Utilities.__IBTRADER_HISTORICAL_DATE_FORMAT);
			
			String _to = form.format(_tomorrow.getTime()).concat(" UTC");
			
			Date _Expiration;
			Calendar cExpirationDate = Calendar.getInstance();
			
		
			
			
			if (share.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS))
			{
				if (Validator.isNotNull(share.getExpiry_date()))
				{
					SimpleDateFormat sdfSHORT = new SimpleDateFormat();
					sdfSHORT.applyPattern(Utilities._IBTRADER_FUTURE_LONG_DATE);
			
					// "dd/MM/yyyy";
					_Expiration = sdfSHORT.parse(Utilities.getActiveFutureDateByDate(Arrays.asList(share.getExpiry_expression().split(",")), hoyOpenMarket)); 
					cExpirationDate.setTimeInMillis(_Expiration.getTime());		
					
					/* LA FECHA HASTA FIN DEL FUTURO, CAMBIAMOS A LA FECHA FIN DEL CONTRATO */
					oContrat = new FutContract( share.getSymbol(), sdf.format(cExpirationDate.getTime()));		    			
					oContrat.exchange(share.getExchange());
					oContrat.currency(market.getCurrency());
					oContrat.includeExpired(Boolean.TRUE);
					oContrat.primaryExch(share.getPrimary_exchange()); 
					oContrat.multiplier(String.valueOf(share.getMultiplier()));
				} 
				
			}
			else		    					
				oContrat = new StkContract( share.getSymbol());				    			
			
			/* METEMOS UN STOP ENTRE PETICION Y PETICION */
		   //	Thread.sleep(2000);
			_log.debug("get required realtime  (1  WEEK)   for " + share.getSymbol() + ",to:" + _to + ",iborder:" + _INCREMENT_ORDER_ID);	
			//System.out.println("get required realtime  (1  WEEK)   for " + oShare.getSymbol() + ",to:" + _to + ",iborder:" + _INCREMENT_ORDER_ID);
			wrapper.set_ibtarget_share(share);
			wrapper.setFilledData(Boolean.TRUE);
			wrapper.getHistoricalData(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat,_to);			
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
			if (!wrapper.isHistorialDataEnd()) // acaba, actualizamos la fecha del share y cerramos el thread 				    			
					wrapper.cancelHistoricalData(new Long(_INCREMENT_ORDER_ID).intValue());    			
    								
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			_log.error(e.getMessage());				
			//if (oTWS.GITraderTWSIsConnected())
			 if (wrapper!=null &&  wrapper.isConnected()) wrapper.disconnect();
		}
	
		if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();
	}
	
	
	
	
	/*RELLENAMOS UNA SEMANA HACIA A TRAS LOS CLOSE PRICES  */
	public  void StartFillClosePrices(Message _message) throws Exception 	{
		

			int 	_CLIENT_ID = 14;	  // el dos para leer, el 3 para escribir			
			String  _HOST = "127.0.0.1";
			String  _USERTWS  = "edemo";
			int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
			long companyId =  PortalUtil.getDefaultCompanyId();

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
					if (_Organization.getOrganizationId()==20165) continue;  // LIFERAY 	 EXCEPTION
					
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
							/* el historical data va hacia delante, la creacion de la accion mete 4  meses  para la simulacion
							 * en el campo simulation_end_date 
							 * 
							 * 
							 * 1. Buscamos la simulacion hasta el dia de ayer */
							Calendar _tomorrow = Calendar.getInstance();
							_tomorrow.add(Calendar.DATE,1); // SITE DIAS ATRAS, HOY NO DEBERIA ESTAR CERRADO
							_tomorrow.set(Calendar.HOUR_OF_DAY, 23);
							_tomorrow.set(Calendar.MILLISECOND, 0);
							_tomorrow.set(Calendar.SECOND,59);
							_tomorrow.set(Calendar.MINUTE, 59);
							  
							
							Calendar _closeperiod = Calendar.getInstance();
							_closeperiod.set(Calendar.HOUR_OF_DAY, 0);
							_closeperiod.set(Calendar.MILLISECOND, 0);
							_closeperiod.set(Calendar.SECOND,0);
							_closeperiod.set(Calendar.MINUTE, 0);
							_closeperiod.add(Calendar.DATE,-16); // 15  DIAS ATRAS, HOY NO DEBERIA ESTAR CERRADO  
							
			    			if (!oShare.isValidated_trader_provider() )
								continue;
			    			
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
			    			if (Validator.isNull(wrapper))		
			    			{
		    					wrapper = new TIMApiWrapper(_CLIENT_ID);
		    					wrapper.connect(_HOST, _PORT,_CLIENT_ID);
			    			}
							else    				
								if (Validator.isNotNull(wrapper) && !wrapper.isConnected())
								{
									wrapper.disconnect();
									wrapper.connect(_HOST, _PORT,_CLIENT_ID);
								}				
							
							 if (!wrapper.isConnected()) // si no se conecta, lo intentamos con otro clienid , aunque pudiera ser que no tenga configuracion o tws activa 
							{
									Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_FILLHISTORICALDATA,_Organization.getCompanyId(), _Organization.getGroupId());
														
									Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId(_Organization.getCompanyId(), _Organization.getGroupId());
									_conf.setValue(String.valueOf(NewClientID));
									ConfigLocalServiceUtil.updateConfig(_conf);
									continue;
									
							 }
							 
							 /* VERIFICACION DE LA CONECTIVIDAD, SIEMPRE NECESARIO */
							 wrapper.set_ibtarget_share(oShare);
							 wrapper.reqNextId(); 
							 wrapper.set_ibtarget_organization(_Organization);
							 wrapper.setCronId(IBTraderConstants.keyCRON_FILLHISTORICALDATA);
							 wrapper.setUserTWS(_USERTWS);
							 if (wrapper.getCurrentOrderId()==-1)
							 {
								wrapper.disconnect();
								return;
							 }
			    						    			
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
			    			
			    			
			    			
			    			SimpleDateFormat form = new SimpleDateFormat(Utilities.__IBTRADER_HISTORICAL_DATE_FORMAT);
			    			String _from =  form.format(_closeperiod.getTime());				    			
			    			String _to = form.format(_tomorrow.getTime()).concat(" UTC");
			    			
			    			Date _Expiration;
			    			Calendar cExpirationDate = Calendar.getInstance();
			    		
			    			if (oShare.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS))
							{
			    				if (Validator.isNotNull(oShare.getExpiry_date()))
			    				{
			    					SimpleDateFormat sdfSHORT = new SimpleDateFormat();
			    					sdfSHORT.applyPattern(Utilities._IBTRADER_FUTURE_LONG_DATE);
			    			
			    					// "dd/MM/yyyy";
			    					_Expiration = sdfSHORT.parse(Utilities.getActiveFutureDateByDate(Arrays.asList(oShare.getExpiry_expression().split(",")), _tomorrow)); 
			    					cExpirationDate.setTimeInMillis(_Expiration.getTime());		
			    					
			    					/* LA FECHA HASTA FIN DEL FUTURO, CAMBIAMOS A LA FECHA FIN DEL CONTRATO */
			    					oContrat = new FutContract( oShare.getSymbol(), sdf.format(cExpirationDate.getTime()));		    			
									oContrat.exchange(oShare.getExchange());
									oContrat.currency(oMarket.getCurrency());
									oContrat.includeExpired(Boolean.TRUE);
									oContrat.primaryExch(oShare.getPrimary_exchange()); 
									oContrat.multiplier(String.valueOf(oShare.getMultiplier()));
			    				}
								
							}
							else		    					
								oContrat = new StkContract( oShare.getSymbol());				    			
			    			
			    			try {
			    				/* METEMOS UN STOP ENTRE PETICION Y PETICION */
			    				//Thread.sleep(2000);
			    				_log.debug("get Closing Dates  for " + oShare.getSymbol() + ",from:" + _from + ",to:" + _to + ",iborder:" + _INCREMENT_ORDER_ID);	
			    				wrapper.set_ibtarget_share(oShare);			
			    				
			    				//  siempre hay que poner un dia de mas para que te lo de hasta el momento actual 23.59.59 
			    				
			    				wrapper.getHClosingPricesData(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat,_to);			
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
			    				if (!wrapper.isHistorialDataEnd()) // acaba, actualizamos la fecha del share y cerramos el thread 				    			
			    						wrapper.cancelHistoricalData(new Long(_INCREMENT_ORDER_ID).intValue());
			    			//	if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();				    													
			    				
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
						 				   
				}  // 		for (Organization _Organization : lOrganization )
				//if (m_client.isConnected()) m_client.eDisconnect();
			}
			catch (Exception e)
			{
				if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();
				_log.error(e.getMessage());
			}
			if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();
			
	}
	
	
	/* CONEXIONES POR ORGANIZACION */
	public  void StartFillHistoricalDataCron(Message _message) throws Exception 	{
		

			int 	_CLIENT_ID = 15;	  // el dos para leer, el 3 para escribir			
			String  _HOST = "127.0.0.1";
			String  _USERTWS  = "edemo";
			int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
			long companyId =  PortalUtil.getDefaultCompanyId();

			Contract oContrat = null;
			/* VERIFICAMOS MERCADOS ACTIVOS */
		    java.util.List<Share> lShare = null;
		    SimpleDateFormat sdf = new SimpleDateFormat();
		    sdf.applyPattern(Utilities._IBTRADER_FUTURE_SHORT_DATE);
		    
			TIMApiWrapper wrapper = null;
			
			try
			{
		    		lShare = ShareLocalServiceUtil.findAllSharesSortbySimulated(); // ASC SEGUN LA ULTIMA FECHA QUE TENGAN QUE CALCULAR
			         
					for (Share oShare : lShare)
		        	{
						/* el historical data va hacia delante, la creacion de la accion mete 4  meses  para la simulacion
						 * en el campo simulation_end_date 
						 * 
						 * 
						 * 1. Buscamos la simulacion hasta el dia de ayer */
						Calendar _yesterday = Calendar.getInstance();
						_yesterday.set(Calendar.HOUR_OF_DAY, 23);
						_yesterday.set(Calendar.MILLISECOND, 0);
						_yesterday.set(Calendar.SECOND,59);
						_yesterday.set(Calendar.MINUTE, 59);
						_yesterday.add(Calendar.DATE,-1);
															    		
		    			
		    			Calendar calSimulatedDate = Calendar.getInstance();
		    			if (Validator.isNull(oShare.getSimulation_end_date())) // no hay, por error, cogemos la fecha de creación y generamos un año para atras
		    			{
		    				calSimulatedDate.setTimeInMillis(oShare.getCreateDate().getTime());
		    				calSimulatedDate.add(Calendar.MONTH, -4); // 4 meses  meses para que haya un año de simulacion 
		    			}
		    			else
		    				calSimulatedDate.setTimeInMillis(oShare.getSimulation_end_date().getTime());
		    			
		    			
		    			Calendar _cVerifiedDate = Calendar.getInstance();
		    			_cVerifiedDate.setTime(calSimulatedDate.getTime());
		    			_cVerifiedDate.set(Calendar.HOUR_OF_DAY, 23);
		    			_cVerifiedDate.set(Calendar.MILLISECOND, 0);
		    			_cVerifiedDate.set(Calendar.SECOND,59);
		    			_cVerifiedDate.set(Calendar.MINUTE, 59);
		    			/* solamente hasta el dia de ayer  */
		    			if (!oShare.isValidated_trader_provider() ||  _cVerifiedDate.after(_yesterday))
							continue;
		    			try
						{
						
							_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_FILLHISTORICALDATA, companyId, oShare.getGroupId())).intValue();;	  // el dos para leer, el 3 para escribir	
	
							/* CUERNTA PAPER */
							boolean bSIMULATED_TRADING = Utilities.getSimulatedTrading(companyId, oShare.getGroupId());
							String _keyUSER  = IBTraderConstants.keyUSER_TWS;
							String _keyHOST  = IBTraderConstants.keyTWS_HOST;
							String _keyPORT  = IBTraderConstants.keyTWS_PORT;
							if (bSIMULATED_TRADING)
							{
								 _keyHOST  = IBTraderConstants.keyPAPER_TWS_HOST;
								 _keyPORT  = IBTraderConstants.keyPAPER_TWS_PORT;
								 _keyUSER  = IBTraderConstants.keyPAPER_USER_TWS;
							}
							
						    _HOST = Utilities.getConfigurationValue(_keyHOST, oShare.getCompanyId(), oShare.getGroupId());
							_PORT = Integer.valueOf(Utilities.getConfigurationValue(_keyPORT, oShare.getCompanyId(), oShare.getGroupId()));
							_USERTWS  = Utilities.getConfigurationValue(_keyUSER, oShare.getCompanyId(), oShare.getGroupId());
						}
						catch (Exception e)
					    {
							
							//_log.info("Error conectandose a la organización : " + _Organization.getName() + " por parámetros de configuración inexistentes" + e.getMessage());
							continue;// no dispone de los parameteros necesarios por error 
						}	
		    			if (Validator.isNull(wrapper))		
		    			{
	    					wrapper = new TIMApiWrapper(_CLIENT_ID);
	    					wrapper.connect(_HOST, _PORT,_CLIENT_ID);
		    			}
						else    				
							if (Validator.isNotNull(wrapper) && !wrapper.isConnected())
							{
								wrapper.disconnect();
								wrapper.connect(_HOST, _PORT,_CLIENT_ID);
							}				
						
						 if (!wrapper.isConnected()) // si no se conecta, lo intentamos con otro clienid , aunque pudiera ser que no tenga configuracion o tws activa 
							{
								Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_FILLHISTORICALDATA,oShare.getCompanyId(), oShare.getGroupId());
													
								Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId(oShare.getCompanyId(), oShare.getGroupId());
								_conf.setValue(String.valueOf(NewClientID));
								ConfigLocalServiceUtil.updateConfig(_conf);
								continue;
								
						 }			    

					    Market marketShare = MarketLocalServiceUtil.fetchMarket(oShare.getMarketId());	
					    if (Validator.isNull(marketShare)) continue;
						
						long organizationId = 0;					       
					    organizationId = oShare.getGroupId();
					    Group group = GroupLocalServiceUtil.fetchGroup(oShare.getGroupId());
					    if (Validator.isNull(group)) continue; 
					    Organization  organization =  OrganizationLocalServiceUtil.fetchOrganization(group.getClassPK());
					    if (Validator.isNull(organization)) continue; 
						 /* VERIFICACION DE LA CONECTIVIDAD, SIEMPRE NECESARIO */
						wrapper.set_ibtarget_share(oShare);
						wrapper.reqNextId(); 
						wrapper.set_ibtarget_organization(organization);
						wrapper.setCronId(IBTraderConstants.keyCRON_FILLHISTORICALDATA);
						wrapper.setUserTWS(_USERTWS);
						if (wrapper.getCurrentOrderId()==-1)
						{
							wrapper.disconnect();
							return;
						}
		    			
		    			/* insertamos control de ordenes de peticion */		
	    				long  _INCREMENT_ORDER_ID = wrapper.getNextOrderId();
		    			
						IBOrderLocalServiceUtil.deleteByOrderCompanyGroup(_INCREMENT_ORDER_ID, oShare.getCompanyId(), oShare.getGroupId(),_CLIENT_ID,oShare.getShareId());
		    			
		    			IBOrder _order = IBOrderLocalServiceUtil.createIBOrder(_INCREMENT_ORDER_ID);
		    			_order.setCompanyId(oShare.getCompanyId());
		    			_order.setGroupId(oShare.getGroupId());
		    			_order.setShareID(oShare.getShareId());	
		    			_order.setOrdersId(_INCREMENT_ORDER_ID);
		    			_order.setIbclientId(_CLIENT_ID);
		    			_order.setRemovable_on_reboot(Boolean.TRUE);	 /* los requestid  se borran */
		    			/* pedimos tiempo real */
		    			IBOrderLocalServiceUtil.updateIBOrder(_order);
		    			
		    			
		    			
		    			SimpleDateFormat form = new SimpleDateFormat(Utilities.__IBTRADER_HISTORICAL_DATE_FORMAT);
		    			String _from =  form.format(calSimulatedDate.getTime());
		    			
		    			/* HASTA UNA SEMANA ADELANTE, YA QUE LAS BARRAS  ADELANTE SI NO SOBREPASA DEL DIA ACTUAL */				    			
		    			calSimulatedDate.add(Calendar.DATE, 7);
		    			if (calSimulatedDate.after(_yesterday))
		    				calSimulatedDate.setTime(_yesterday.getTime());
		    								    		
		    				
		    			String _to = form.format(calSimulatedDate.getTime());
		    			
		    			Date _Expiration;
		    			Calendar cExpirationDate = Calendar.getInstance();
		    		
		    			if (oShare.getSecurity_type().equals(ConfigKeys.SECURITY_TYPE_FUTUROS))
						{
		    				if (Validator.isNotNull(oShare.getExpiry_date()))
		    				{
		    					SimpleDateFormat sdfSHORT = new SimpleDateFormat();
		    					sdfSHORT.applyPattern(Utilities._IBTRADER_FUTURE_LONG_DATE);
		    			
		    					// "dd/MM/yyyy";
		    					try 
		    					{
		    						_Expiration = sdfSHORT.parse(Utilities.getActiveFutureDateByDate(Arrays.asList(oShare.getExpiry_expression().split(",")), calSimulatedDate));
		    					}
		    					catch (ParseException parseE)
		    					{
		    						continue;
		    					}
		    					cExpirationDate.setTime(_Expiration);		
		    					
		    					/* LA FECHA HASTA FIN DEL FUTURO, CAMBIAMOS A LA FECHA FIN DEL CONTRATO */
		    					if (calSimulatedDate.after(cExpirationDate))
		    						calSimulatedDate.setTime(cExpirationDate.getTime());
		    					
		    					oContrat = new FutContract( oShare.getSymbol(), sdf.format(cExpirationDate.getTime()));		    			
								oContrat.exchange(oShare.getExchange());
								oContrat.currency(marketShare.getCurrency());
								oContrat.includeExpired(Boolean.TRUE);
								oContrat.primaryExch(oShare.getPrimary_exchange());
								oContrat.multiplier(String.valueOf(oShare.getMultiplier()));
								 
		    				}
							
						}
						else		    					
							oContrat = new StkContract( oShare.getSymbol());				    			
		    			
		    			
		    			calSimulatedDate.set(Calendar.HOUR_OF_DAY, 23);
		    			calSimulatedDate.set(Calendar.MILLISECOND, 0);
		    			calSimulatedDate.set(Calendar.SECOND,59);
		    			calSimulatedDate.set(Calendar.MINUTE, 59);
		    			
		    			try {
		    				/* METEMOS UN STOP ENTRE PETICION Y PETICION */
		    				//Thread.sleep(2000);
		    				_log.debug("getHistoricalData for " + oShare.getSymbol() + ",from:" + _from + ",to:" + _to + ",iborder:" + _INCREMENT_ORDER_ID + ",ExpirationFuture:" + oContrat.lastTradeDateOrContractMonth());	
		    				wrapper.set_ibtarget_share(oShare);
		    				wrapper.getHistoricalData(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat,_to);			
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
		    					_log.debug("updating  for " + oShare.getSymbol() + ",date:" + _to);
		    					
		    					oShare.setSimulation_end_date(calSimulatedDate.getTime());
		    					ShareLocalServiceUtil.updateShare(oShare);				    					
		    				}
		    				else // psaado el tiempo no acabo, lo cancelamos.				    					
		    					wrapper.cancelHistoricalData(new Long(_INCREMENT_ORDER_ID).intValue());
		    				
		    				if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();				    				
		    				return; // salimos 
		    				//if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();				    					
							//return;
		    				
						//	oTWS.GITradergetContractDetails(new Long(_INCREMENT_ORDER_ID).intValue(),oContrat);							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();							
							//if (oTWS.GITraderTWSIsConnected())
							 if (wrapper.isConnected()) wrapper.disconnect();

						}		    		
		    		} // OVER SHARES 											 
			}
			catch (Exception e)
			{
				if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();
				_log.error(e.getMessage());
			}
			if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();
			
	}
	
	//16:47:03,923 DEBUG [liferay/scheduler_dispatch-4][CronUtil:1039] getHistoricalData for ES,from:20181226 23:59:59,to:20190102 23:59:59,iborder:3136099,ExpirationFuture:201901

	
	/* CONEXIONES POR ORGANIZACION */
	public   void StartReadingCron(Message _message) throws Exception 	{
	
	int 	_CLIENT_ID = 1;	  // el dos para leer, el 3 para escribir			
	String  _HOST = "127.0.0.1";
	int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
	

	long companyId =  PortalUtil.getDefaultCompanyId();

	long guestGroupId = 0;
	try {
		guestGroupId = GroupLocalServiceUtil.getGroup(companyId, GroupConstants.GUEST).getGroupId();
	} catch (PortalException e) {
	// TODO Auto-generated catch block
		e.printStackTrace();			
	}
	
	
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
		 
		if (_Organization.getOrganizationId()==20165) continue;  // LIFERAY 	 EXCEPTION
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
			 wrapper.connect(_HOST, _PORT,_CLIENT_ID);
			 			
			 if (wrapper.isConnected()) 				 
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
		
			 	_log.trace("Connected, StartReadingCron for " + _Organization.getName() + ", connecting to TWS for " + _Organization.getName() + ",threadID:" + Thread.currentThread().getId());
			
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
					     /*String _HORACTUAL = Utilities.getActualHourFormatPlusMinutes(Utilities.getGlobalIBDateNowFormat(),10); 
				    	
				    	 OJO, ESTOS SON MERCADOS ACTIVOS 
				    	 * List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveStartEndHour(_HORACTUAL, _HORACTUAL,Boolean.TRUE,_Organization.getCompanyId(), _Organization.getGroupId());
				    	 * */
				    	List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveStartEndMarketHour(Boolean.TRUE,_Organization.getCompanyId(), _Organization.getGroupId());
				    	for (Market oMarket : lActiveMarkets)
				    	{
				    		
				    		//lShare =  ShareLocalServiceUtil.findByActiveMarketGroupCompany(oMarket.getMarketId(), Boolean.TRUE, oMarket.getGroupId(), oMarket.getCompanyId());
				    		
				    		/* 20190319 --> si se cancela o desactiva un activo, debe salir por aqui para quitarlo de la lista de control, sacamos todas, indepe
				    		 * pendiente si estan activas o no  */
				    		lShare =  ShareLocalServiceUtil.findByMarketGroupCompany(oMarket.getMarketId(), oMarket.getGroupId(), oMarket.getCompanyId());
				    		
				    		for (Share oShare : lShare)
					    	{				    		
				    			String _Expiration = "";	
				    			if (oShare.getExpiry_date()!=null) _Expiration = sdf.format(oShare.getExpiry_date());		
				    			String _uniqueShare = oShare.getSymbol() + "|" + _Expiration + "|" + oShare.getGroupId() + "|" + oShare.getCompanyId();

				    			/* METEMOS UN CASO DE MULTIPLES CLIENTES CON UNA TRADESTAATION  */
				    			/* YA NO ESTA ACTIVA, LA QUITAMOS DE LA LISTA DE CONTROL Y CANCELAMOS rktmake */
				    			if ((!oShare.getActive()  || !oShare.IsTradeable()) && lShareRequested!=null && lShareRequested.contains(_uniqueShare))
				    			{
				    				
				    				/* SI HAY QUE QUITARLO Y ESTABA PEDIDO, CANCELAMOS SU rekmktData */
				    				long existingOrderMktData = IBOrderLocalServiceUtil.findMaxOrderClientShareCompanyGroup(oShare.getCompanyId(),oShare.getGroupId(),_CLIENT_ID, oShare.getShareId());
				    				if (existingOrderMktData>0)
				    				{
				    					_log.debug("Share " + oShare.getSymbol() + " not active and previous requested, to cancel data : iborderId: " + existingOrderMktData);				    				
				    					wrapper.cancelMarketData(Long.valueOf(existingOrderMktData).intValue());
				    				}
				    				lShareRequested.remove(_uniqueShare);
				    				continue;
				    			}
				    			boolean bToRequest=true;
				    						    			/* cancelar el mkt si esta pedido */
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
				    				_log.debug("TradingRead de :" + _uniqueShare + ":" +  oShare.getSecurity_type() + ":" + oShare.getExchange() + ":" + oMarket.getCurrency());
				    				//System.out.println("TradingRead de :" + _uniqueShare + ":" +  oShare.getSecurity_type() + ":" + oShare.getExchange() + ":" + oMarket.getCurrency());

					    			/* insertamos control de ordenes de peticion */		
				    				long  _INCREMENT_ORDER_ID = wrapper.getNextOrderId();
				    				_log.debug("Se borran las ordendes y cancela market request para :" +_INCREMENT_ORDER_ID + ",groupId:" + _Organization.getGroupId() + ",clientId:" + _CLIENT_ID);
				    				wrapper.cancelMarketData(Long.valueOf(_INCREMENT_ORDER_ID).intValue());
				    				IBOrderLocalServiceUtil.deleteByOrderCompanyGroup(_INCREMENT_ORDER_ID, _Organization.getCompanyId(), _Organization.getGroupId(), _CLIENT_ID,oShare.getShareId());
				    				_log.debug("Next Valid OrderId for :" +  _INCREMENT_ORDER_ID + "," + _uniqueShare);
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
	public  void StartTradingCron(Message _message) throws Exception {

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
		
		
		
		long companyId =  PortalUtil.getDefaultCompanyId();
		 long guestGroupId = 0;
		try {
			guestGroupId = GroupLocalServiceUtil.getGroup(companyId, GroupConstants.GUEST).getGroupId();
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
			
			if (_Organization.getOrganizationId()==20165) continue;  // LIFERAY 	 EXCEPTION
			
			try
			{
			
			if (_Organization.getOrganizationId()==20165) continue;  // LIFERAY 	 EXCEPTION
				
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
				 
				_log.trace("threadID:" + Thread.currentThread().getId() + ",Wrapper obtained from pool:" + wrapper.getClient().connectedHost() + "," + _PORT + ",group:" + _Organization.getGroupId() + ",_CLIENT_ID:" + _CLIENT_ID);

				 
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
				    	_log.debug("Updating  TradingCron to database connecting to TWS clientID:" +_CLIENT_ID);				    	
				    	 
				    } 				 	 				   	
			   	 	List<Market> lActiveMarkets = MarketLocalServiceUtil.findByActiveStartEndMarketHour(Boolean.TRUE,_Organization.getCompanyId(), _Organization.getGroupId());				   	 	
			   	  
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
				    			 	
				    			 	
				    			 	
				    			 	List<Strategy> _lStrategiesOfShare = StrategyShareLocalServiceUtil.findByActiveStrategies(Boolean.TRUE,
				    			 			oShare.getShareId(),oShare.getCompanyId(), oShare.getGroupId());
				    				
				    				for (Strategy oStrategyShare :_lStrategiesOfShare)
					    			{
				    				
				    				if (!oStrategyShare.isActive()) continue;  // si no esta activa, no se trata 

				    					
				    				StrategyImpl _strategyImpl= (StrategyImpl) Utilities.getContextClassLoader().loadClass(oStrategyShare.getClassName()).newInstance();
				    				StrategyShare strategyShare =  StrategyShareLocalServiceUtil.getByCommpanyShareStrategyId(oShare.getGroupId(), oShare.getCompanyId(), oShare.getShareId(), oStrategyShare.getStrategyID());
			    					_strategyImpl.init(oShare.getCompanyId());   // verify if custom fields are created and filled
			    				
			    					/* SOLO PARA LAS DE SALIDA SI NO ESTA HABILITADO LA OPERCION, QUEREMOS AL MENOS SALIR */
			    					boolean IsTradingEnabled = Utilities.getTradingEnabled(oShare.getCompanyId(), oShare.getGroupId());
			    					IsTradingEnabled = 	(IsTradingEnabled || (!IsTradingEnabled && oStrategyShare.getType().equals(IBTraderConstants.STRATEGY_OUT_TYPE.toString()))); 
			    					
			    					Calendar _calendarFromNow = Calendar.getInstance();
			    					long currentSeconds = _calendarFromNow.get(Calendar.SECOND);

			    					if (currentSeconds<3)						
			    					{
			    						_log.debug("IsTradingEnabled for " + oShare.getSymbol() + ":" + IsTradingEnabled);
			    					}
			    					
			    					
			    					if ( IsTradingEnabled && _strategyImpl.verify(oShare, oMarket,strategyShare,null) && wrapper.isConnected() 
			    							&& !Utilities.IsDayTraderPattern(oShare.getGroupId(), oShare.getCompanyId()))
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
			    								_log.info("Opening order CronUTIL,threadID:" + Thread.currentThread().getId()  +",wrapper:" + wrapper.getClient().connectedHost() + "," + _PORT + ",group:" + _Organization.getGroupId() + ",_CLIENT_ID:" + _CLIENT_ID);
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
			    							
			    							
			    					} // end if (_strategyImpl.verify(oShare, oMarket,strategyShare,null) && wrapper.isConnected())
					    			
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
	public  void StartVerifyContractsCron(Message _message) throws Exception {		
		List<Config> lConfig=null;
		int 	_CLIENT_ID = 3;	  // el dos para leer, el 3 para escribir			
		String  _HOST = "127.0.0.1";
		int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
		long companyId =  PortalUtil.getDefaultCompanyId();

		Contract oContrat = null;
		/* VERIFICAMOS MERCADOS ACTIVOS */
	    java.util.List<Share> lShare = null;
	    SimpleDateFormat sdf = new SimpleDateFormat();
	    sdf.applyPattern(Utilities._IBTRADER_FUTURE_SHORT_DATE);
	    
	    long guestGroupId = 0;
		try {
			guestGroupId = GroupLocalServiceUtil.getGroup(companyId, GroupConstants.GUEST).getGroupId();
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
				 
				if (_Organization.getOrganizationId()==20165) continue;  // LIFERAY 	 EXCEPTION
									 
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
			    			if (Validator.isNull(wrapper))		
			    			{
		    					wrapper = new TIMApiWrapper(_CLIENT_ID);
		    					wrapper.connect(_HOST, _PORT,_CLIENT_ID);
			    			}
							else    				
								if (Validator.isNotNull(wrapper) && !wrapper.isConnected())
								{
									wrapper.disconnect();
									wrapper.connect(_HOST, _PORT,_CLIENT_ID);
								}				
							
							 if (!wrapper.isConnected()) // si no se conecta, lo intentamos con otro clienid , aunque pudiera ser que no tenga configuracion o tws activa 
								{
									Config _conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_CONTRACTCHECKER_CLIENT_INITIAL,_Organization.getCompanyId(), _Organization.getGroupId());
														
									Long  NewClientID = ConfigLocalServiceUtil.findByFreeCronClientId(_Organization.getCompanyId(), _Organization.getGroupId());
									_conf.setValue(String.valueOf(NewClientID));
									ConfigLocalServiceUtil.updateConfig(_conf);
									continue;
									
							 }
							
							 /* VERIFICACION DE LA CONECTIVIDAD, SIEMPRE NECESARIO */
							wrapper.reqNextId(); 
							wrapper.set_ibtarget_organization(_Organization);
							wrapper.setCronId(IBTraderConstants.keyCRON_CONTRACTCHECKER_CLIENT_INITIAL);
							if (wrapper.getCurrentOrderId()==-1)
							{
								wrapper.disconnect();
								return;
							}
			    			wrapper.set_ibtarget_share(oShare);
			    			/* insertamos control de ordenes de peticion */		
		    				long  _INCREMENT_ORDER_ID = wrapper.getNextOrderId();
			    			_log.debug("Verifyng de :" + oShare.getSymbol() + ":" +  oShare.getSecurity_type() + ":" + oShare.getExchange() + ":" + oMarket.getCurrency() + " y orderId:" + _INCREMENT_ORDER_ID);

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
					 			 
			}  // 		for (Organization _Organization : lOrganization )
			//if (m_client.isConnected()) m_client.eDisconnect();
		}
		catch (Exception e)
		{
			if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();		 
		}
		if (wrapper!=null && wrapper.isConnected()) wrapper.disconnect();
		

	}	
	
	/* ESTE PUEDE EJECUTARSE POR VECES 
	 * 
	 *  EXPIRAN HOY, SE LES CAMBIA LA FECHA. 
	 * */
	public  void StartVerifyFuturesDatesCron(Message _message) throws Exception {					
		
		
	    List<Share> lFutures = ShareLocalServiceUtil.findByActiveFuturesDates(Boolean.TRUE);
	    SimpleDateFormat sdfSHORT = new SimpleDateFormat();
    	sdfSHORT.applyPattern(Utilities._IBTRADER_FUTURE_LONG_DATE);
		for (Share oShare : lFutures)
		{			
			/* EXPIRATION TB SE COMPLETA CON HORA DEL CONTRACT DETAILS, UNA VEZ HECHO EL ROLLOVER  
			 * NO PIERDO AL MENOS LA HORA ORIGINAL */	
			try
			{
				Calendar cOldExp = Calendar.getInstance();
				cOldExp.setTimeInMillis(oShare.getExpiry_date().getTime());
				Date _newExpirationDate = sdfSHORT.parse(Utilities.getActiveFutureDate(Arrays.asList(oShare.getExpiry_expression().split(","))));
				Calendar cNewExp = Calendar.getInstance();
				
				
				cNewExp.setTimeInMillis(_newExpirationDate.getTime());
				cNewExp.set(Calendar.HOUR, cOldExp.get(Calendar.HOUR));
				cNewExp.set(Calendar.MINUTE, cOldExp.get(Calendar.MINUTE));
				cNewExp.set(Calendar.SECOND, 0);
				
				oShare.setExpiry_date(cNewExp.getTime());			
				ShareLocalServiceUtil.updateShare(oShare);
			}
			catch (Exception e)
			{
				_log.debug(e.getMessage());
			}
		}
	
	
		
		

	}		
	/* DEJAMOS 7 DIAS PARA LOS REINICIOS PREVENTIVOS */
	/* BORRA ORDENDES MYT ANTIGUAS DE REALTIME, TODAS MENOS DE POSICIONES, 7 DIAS PARA ATRAS   
	public  void StartDeletingOldOrderRequestCron(Message _message) throws Exception {					
		
	    
		
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
			
		
		

	}		*/
	

}