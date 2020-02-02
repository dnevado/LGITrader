/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.ibtrader.data.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.osgi.service.component.annotations.Reference;

import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.exception.NoSuchConfigException;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.service.ConfigLocalService;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalService;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalService;
import com.ibtrader.data.service.ShareLocalService;
import com.ibtrader.data.service.StrategyLocalService;
import com.ibtrader.data.service.StrategyShareLocalService;
import com.ibtrader.data.service.base.ConfigLocalServiceBaseImpl;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;
import com.ibtrader.util.Utilities.OSType;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;



/**
 * The implementation of the config local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.service.ConfigLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConfigLocalServiceBaseImpl
 * @see com.ibtrader.data.service.configLocalService
 */
public class ConfigLocalServiceImpl extends ConfigLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ibtrader.data.service.configLocalService} to access the config local service.
	 */	
	private static final Log _log = LogFactoryUtil.getLog(ConfigLocalServiceImpl.class);

	
  /*   private StrategyLocalService _strategyLocalService;
    private StrategyShareLocalService _strategyshareLocalService;
	    
	    
	    
  
	
	
	public List<Config> findByKeyGlobalDefault(String _key, boolean _global)
	{
		return getConfigPersistence().findByKeyGlobalDefault(_key, _global);
	}
	
	@Reference(unbind = "-")
	public void setStrategyLocalService(StrategyLocalService strategyLocalService) {
    	_strategyLocalService = strategyLocalService;
    }
    
    
    @Reference(unbind = "-")
	public void setStrategyShareLocalService(StrategyShareLocalService strategyshareLocalService) {
    	_strategyshareLocalService = strategyshareLocalService;
    }
		    
	*/	
	
	public Config findByIsCronValue(boolean isCron, String value)
	{

		Config _config = null;
		try {
			_config =  getConfigPersistence().findByIsCronValue(isCron, value);
		} catch (NoSuchConfigException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		return _config;
	}
	
	/* SABER LOS CLIENID  REPETIDOS Y RECONECTAR, NO DEBERIA REPERTIRSE  */
	public Long findByFreeCronClientId(long  _company, long _group)
	{
		 Long _ClientID = null;
		/* NO PUEDO APLICAR MAX PORQUE SON VALUES COMO STRING */
		DynamicQuery _DQ = ConfigLocalServiceUtil.dynamicQuery();
		_DQ.add(RestrictionsFactoryUtil.eq("iscron", Boolean.TRUE));
		List<Config> lCronConfigClientId = configLocalService.dynamicQuery(_DQ);
		List<Long> lBusyClientsId = new ArrayList<Long>();
		List<Long> lFreeClientsId = new ArrayList<Long>();
		
		for (Config _conf : lCronConfigClientId)
		{
			lBusyClientsId.add(Long.valueOf(_conf.getValue()));
		}
		/* COGEMOS EL PRIMER LIBRE DE 0 A 1024 ALEATORIO QUE NO EXISTA 
		new Random().ints(1024, 0, 1023).forEach(clienid->{
			if (!lBusyClientsId.contains(Long.valueOf(clienid))) {
				 Long _lClientID = new Long(clienid);
				return ;
			}
		});*/	
		for (int i = 0; i < 1023; i++) {
			lFreeClientsId.add(new Long(i));
		}
		Collections.shuffle(lFreeClientsId);
		for (Long ClientId : lFreeClientsId)
		{
			if (!lBusyClientsId.contains(ClientId)) {
				_ClientID = ClientId;
				 break;
			}
			
		}
		
		return _ClientID;
	}
	
	public Config findByKeyCompanyGroup(String _key, long  _company, long _group)
	{
		Config _config = null;
		try {
			_config =  getConfigPersistence().findByKeyCompanyGroup(_company, _group, _key);
		} catch (NoSuchConfigException e) {
			// TODO Auto-generated catch block
			 _log.debug("findByKeyCompanyGroup " + e.getMessage() + ":"  + _company + "," + _group + "," + _key);
		}
		return _config;
	}
	
	/* AÑADIMOS LOS DATOS DE COMPANY Y GROUP DE UNA NUEVA ORGANIZACOPM */
	
	public void addConfigurationValuesCompanyGroup(long  _company, long _group)
	{
		  /* TWS HOST */
		 _log.info("Adding missing Config Table Values .." );		
		 Config _conf = configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyTWS_HOST, _company, _group);
		 
		 /* VERIFICAMOS ESTRATEGIAS */
		 long strategies = strategyLocalService.getStrategysCount(_group);
		 
		 int _TotalConfigs = (_conf!=null ? 1 : 0 );
		 
		 boolean bLinux = false;
		 
		 OSType ostype= Utilities.getOperatingSystemType();
		 
		 switch (ostype) {
		     case Windows: break;
		     case MacOS: break;
		     case Linux:
		    	 bLinux = true;
		    	 break;
		     case Other: break;
		 }
		 
		 String _PrefixPath = IBTraderConstants.IBCONTROLLER_WINDOWS_PATH;
		 String _SuffixPath = ".bat";
		 
		 if (bLinux)
		 {
			 _PrefixPath = IBTraderConstants.IBCONTROLLER_UNIX_PATH;
			 _SuffixPath = ".sh";
		 }
		 
		 if (_TotalConfigs==0)
		 {
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyTWS_HOST);
			 _conf.setValue(IBTraderConstants.vTWS_HOST);
			 _conf.setName(IBTraderConstants.keyTWS_HOST);
			 _conf.setDescription(IBTraderConstants.keyTWS_HOST);
			 _conf.setGlobaldefault(true);		
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);
			 							 
			 
			 /* TWS ACOOUNT */
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setCompanyId(_company);
			 _conf.setGroupId(_group);
			 _conf.setConfig_key(IBTraderConstants.keyACCOUNT_IB_NAME);
			 _conf.setValue(IBTraderConstants.vACCOUNT_IB_NAME);
			 _conf.setName(IBTraderConstants.keyACCOUNT_IB_NAME);
			 _conf.setDescription(IBTraderConstants.keyACCOUNT_IB_NAME);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);
			 
			 /* TWS PORT  */
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setCompanyId(_company);
			 _conf.setGroupId(_group);
			 _conf.setConfig_key(IBTraderConstants.keyTWS_PORT);
			 _conf.setValue(String.valueOf(_group)); // METEMOS EL GROUP ID PARA QUE NO HAYA DUPLICADOS 
			// _conf.setValue(String.valueOf(IBTraderConstants.vTWS_PORT));
			 _conf.setName(IBTraderConstants.keyTWS_PORT);
			 _conf.setDescription(IBTraderConstants.keyTWS_PORT);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);
			 /* SIMULATION MODE */   
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keySIMULATION_MODE);
			 _conf.setValue(PositionStates.position_mode_type.SIMULATED.toString());  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones);
			 _conf.setName(IBTraderConstants.keySIMULATION_MODE);
			 _conf.setDescription(IBTraderConstants.keySIMULATION_MODE);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			 /* SIMULATION MODE 
			  * 
			  * public static long   vENABLE_MAIL_NOTIFICATIONS = 10;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
			public static long   vSIMULATION_MODE = 0;  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
			public static String   vPATH_TO_CONFIGURATION_FILE = "C:\\ibcontroller\\IBController.ini";  // para saber si esta ejecutando o no , ojo a las caidas y exceptiones
			  * */   
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyENABLE_MAIL_NOTIFICATIONS);
			 _conf.setValue(String.valueOf(IBTraderConstants.vENABLE_MAIL_NOTIFICATIONS));
			 _conf.setName(IBTraderConstants.keyENABLE_MAIL_NOTIFICATIONS);
			 _conf.setDescription(IBTraderConstants.keyENABLE_MAIL_NOTIFICATIONS);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);
			 
			     
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyENABLE_DESKTOP_NOTIFICATIONS);
			 _conf.setValue(String.valueOf(IBTraderConstants.vENABLE_DESKTOP_NOTIFICATIONS));
			 _conf.setName(IBTraderConstants.keyENABLE_DESKTOP_NOTIFICATIONS);
			 _conf.setDescription(IBTraderConstants.keyENABLE_DESKTOP_NOTIFICATIONS);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			
			 
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyDAY_TRADER_PATTER);
			 _conf.setValue(String.valueOf(IBTraderConstants.vDAY_TRADER_PATTER));
			 _conf.setName(IBTraderConstants.keyDAY_TRADER_PATTER);
			 _conf.setDescription(IBTraderConstants.keyDAY_TRADER_PATTER);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyENABLED_GLOBAL_TRADING);
			 _conf.setValue(String.valueOf(IBTraderConstants.VENABLED_GLOBAL_TRADING));
			 _conf.setName(IBTraderConstants.keyENABLED_GLOBAL_TRADING);
			 _conf.setDescription(IBTraderConstants.keyENABLED_GLOBAL_TRADING);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
				   
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyPATH_TO_CONFIGURATION_FILE);
			 _conf.setValue(String.valueOf(IBTraderConstants.vPATH_TO_CONFIGURATION_FILE.replace("{PLATFORM_PATH}",_PrefixPath)));
			 _conf.setName(IBTraderConstants.keyPATH_TO_CONFIGURATION_FILE);
			 _conf.setDescription(IBTraderConstants.keyPATH_TO_CONFIGURATION_FILE);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);
			 
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyUSER_TWS);
			 _conf.setValue(String.valueOf(IBTraderConstants.vUSER_TWS));
			 _conf.setName(IBTraderConstants.keyUSER_TWS);
			 _conf.setDescription(IBTraderConstants.keyUSER_TWS);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			 
			 
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyUSER_PWD);
			 _conf.setValue(String.valueOf(IBTraderConstants.vUSER_PWD));
			 _conf.setName(IBTraderConstants.keyUSER_PWD);
			 _conf.setDescription(IBTraderConstants.keyUSER_PWD);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			 
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyPATH_TO_EXECUTABLE_FILE);
			 _conf.setValue(String.valueOf(IBTraderConstants.vPATH_TO_EXECUTABLE_FILE.replace("{PLATFORM_PATH}",_PrefixPath).concat(_SuffixPath)));
			 _conf.setName(IBTraderConstants.keyPATH_TO_EXECUTABLE_FILE);
			 _conf.setDescription(IBTraderConstants.keyPATH_TO_EXECUTABLE_FILE);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			 
			 /* DUPLICAMOS LOS DATOS PARA LOS CASOS QUE TENEMOS DE SIMULACIONES, CON DOS TWS CORRIENDO A LA VEZ  */
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyPAPER_USER_TWS);
			 _conf.setValue(String.valueOf(IBTraderConstants.vPAPER_USER_TWS));
			 _conf.setName(IBTraderConstants.keyPAPER_USER_TWS);
			 _conf.setDescription(IBTraderConstants.keyPAPER_USER_TWS);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			 
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyPAPER_USER_PWD);
			 _conf.setValue(String.valueOf(IBTraderConstants.vPAPER_USER_PWD));
			 _conf.setName(IBTraderConstants.keyPAPER_USER_PWD);
			 _conf.setDescription(IBTraderConstants.keyPAPER_USER_PWD);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			 
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyPAPER_TWS_HOST);
			 _conf.setValue(String.valueOf(IBTraderConstants.vPAPER_TWS_HOST));
			 _conf.setName(IBTraderConstants.keyPAPER_TWS_HOST);
			 _conf.setDescription(IBTraderConstants.keyPAPER_TWS_HOST);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			 
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyPAPER_TWS_PORT);
			 //_conf.setValue(String.valueOf(IBTraderConstants.vPAPER_TWS_PORT));			 
			 _conf.setValue(String.valueOf(_group)); // METEMOS EL GROUP ID PARA QUE NO HAYA DUPLICADOS 
			 _conf.setName(IBTraderConstants.keyPAPER_TWS_PORT);
			 _conf.setDescription(IBTraderConstants.keyPAPER_TWS_PORT);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			 
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyPAPER_PATH_TO_CONFIGURATION_FILE);
			 _conf.setValue(String.valueOf(IBTraderConstants.vPAPER_PATH_TO_CONFIGURATION_FILE.replace("{PLATFORM_PATH}",_PrefixPath)));
			 _conf.setName(IBTraderConstants.keyPAPER_PATH_TO_CONFIGURATION_FILE);
			 _conf.setDescription(IBTraderConstants.keyPAPER_PATH_TO_CONFIGURATION_FILE);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			 
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keyPAPER_PATH_TO_EXECUTABLE_FILE);
			 _conf.setValue(String.valueOf(IBTraderConstants.vPAPER_PATH_TO_EXECUTABLE_FILE.replace("{PLATFORM_PATH}",_PrefixPath).concat(_SuffixPath)));
			 _conf.setName(IBTraderConstants.keyPAPER_PATH_TO_EXECUTABLE_FILE);
			 _conf.setDescription(IBTraderConstants.keyPAPER_PATH_TO_EXECUTABLE_FILE);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.FALSE);
			 configLocalService.updateConfig(_conf);   	
			 
			 
			 /* CLIENTS_IDS, INICITAL CONFIGURACION  */
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setCompanyId(_company);
			 _conf.setGroupId(_group);
			 _conf.setConfig_key(IBTraderConstants.keyCRON_CONTRACTCHECKER_CLIENT_INITIAL);
			 _conf.setValue(String.valueOf(IBTraderConstants.vCRON_CONTRACTCHECKER_CLIENT_INITIAL));
			 _conf.setName(IBTraderConstants.keyCRON_CONTRACTCHECKER_CLIENT_INITIAL);
			 _conf.setDescription(IBTraderConstants.keyCRON_CONTRACTCHECKER_CLIENT_INITIAL);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.TRUE);
			 configLocalService.updateConfig(_conf);   
			 
			 /* CLIENTS_IDS, INICITAL CONFIGURACION  */
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setCompanyId(_company);
			 _conf.setGroupId(_group);
			 _conf.setConfig_key(IBTraderConstants.keyCRON_FILLHISTORICALDATA);
			 _conf.setValue(String.valueOf(IBTraderConstants.vCRON_FILLHISTORICALDATA));
			 _conf.setName(IBTraderConstants.keyCRON_FILLHISTORICALDATA);
			 _conf.setDescription(IBTraderConstants.keyCRON_FILLHISTORICALDATA);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.TRUE);
			 configLocalService.updateConfig(_conf); 
			 
			  
			 /* CLIENTS_IDS, INICITAL CONFIGURACION  */
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setCompanyId(_company);
			 _conf.setGroupId(_group);
			 _conf.setConfig_key(IBTraderConstants.keyCRON_ORDERSCHECKER_CLIENT_INITIAL);
			 _conf.setValue(String.valueOf(IBTraderConstants.vCRON_ORDERSCHECKER_CLIENT_INITIAL));
			 _conf.setName(IBTraderConstants.keyCRON_ORDERSCHECKER_CLIENT_INITIAL);
			 _conf.setDescription(IBTraderConstants.keyCRON_ORDERSCHECKER_CLIENT_INITIAL);
			 _conf.setGlobaldefault(false);
			 _conf.setIscron(Boolean.TRUE);
			 configLocalService.updateConfig(_conf);   
			 
			 
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setCompanyId(_company);
			 _conf.setGroupId(_group);
			 _conf.setConfig_key(IBTraderConstants.keyCRON_READING_CLIENT_INITIAL);
			 _conf.setValue(String.valueOf(IBTraderConstants.vCRON_READING_CLIENT_INITIAL));
			 _conf.setName(IBTraderConstants.keyCRON_READING_CLIENT_INITIAL);
			 _conf.setDescription(IBTraderConstants.keyCRON_READING_CLIENT_INITIAL);
			 _conf.setIscron(Boolean.TRUE);
			 configLocalService.updateConfig(_conf);   
			 
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setCompanyId(_company);
			 _conf.setGroupId(_group);
			 _conf.setConfig_key(IBTraderConstants.keyCRON_TRADING_CLIENT_INITIAL);
			 _conf.setValue(String.valueOf(IBTraderConstants.vCRON_TRADING_CLIENT_INITIAL));
			 _conf.setName(IBTraderConstants.keyCRON_TRADING_CLIENT_INITIAL);
			 _conf.setDescription(IBTraderConstants.keyCRON_TRADING_CLIENT_INITIAL);
			 _conf.setIscron(Boolean.TRUE);
			 configLocalService.updateConfig(_conf);   
			
			  
		 }
		 if (strategies==0)
		 {
			 /* COGEMOS EL DE OTRO GROUP */
			 List<Strategy> strategiesCompany =  strategyLocalService.findByActiveCompanyId(Boolean.TRUE, _company);
			 long existingGroupId = 0 ;
			 if (strategiesCompany!=null && !strategiesCompany.isEmpty())
			 {
				 Strategy strategy = strategiesCompany.get(0);
				 existingGroupId = strategy.getGroupId();				 
			 }
			 /* replicamos la de una existente 
			  * 
			  */
			 List<Strategy> strategiesGroup =  strategyLocalService.findBYGroupIDStatus(existingGroupId,1);
			 for (Strategy strategygroup : strategiesGroup)
			 {
				 Strategy StrategyNew =  strategyLocalService.createStrategy(counterLocalService.increment(Strategy.class.getName()));
				 StrategyNew.setActive(strategygroup.getActive());
				 StrategyNew.setCan_override_params(strategygroup.getCan_override_params());
				 StrategyNew.setClassName(strategygroup.getClassName());
				 StrategyNew.setName(strategygroup.getName());
				 StrategyNew.setDescription(strategygroup.getDescription());
				 StrategyNew.setCreateDate(new Date());
				 StrategyNew.setModifiedDate(new Date());
				 StrategyNew.setStatus(strategygroup.getStatus());
				 StrategyNew.setType(strategygroup.getType());
				 StrategyNew.setVisible(strategygroup.getVisible());
				 StrategyNew.setGroupId(_group);
				 StrategyNew.setCompanyId(strategygroup.getCompanyId());
				 
				 strategyLocalService.updateStrategy(StrategyNew);
				 
			 }
			 
		 }
		 
	}
	public void removeConfigurationValuesCompanyGroup(long  _company, long _group)
	{
		  /* TWS HOST */
		 _log.info("Removing  Config Table Values for .." + _group);
		 
		 List<Config> _configurations  = configPersistence.findByCompanyGroup(_company, _group);
		 for (Config removeConfig : _configurations )
		 {
			 configLocalService.deleteConfig(removeConfig);
		 }
		 

	}
	
}