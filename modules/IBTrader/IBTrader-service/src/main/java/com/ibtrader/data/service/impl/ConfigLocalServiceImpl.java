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

import java.util.List;

import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.exception.NoSuchConfigException;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.service.base.ConfigLocalServiceBaseImpl;
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
	
	public List<Config> findByKeyGlobalDefault(String _key, boolean _global)
	{
		return getConfigPersistence().findByKeyGlobalDefault(_key, _global);
	}
	public Config findByKeyCompanyGroup(String _key, long  _company, long _group)
	{
		Config _config = null;
		try {
			_config =  getConfigPersistence().findByKeyCompanyGroup(_company, _group, _key);
		} catch (NoSuchConfigException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		return _config;
	}
	
	/* AÑADIMOS LOS DATOS DE COMPANY Y GROUP DE UNA NUEVA ORGANIZACOPM */
	
	public void addConfigurationValuesCompanyGroup(long  _company, long _group)
	{
		  /* TWS HOST */
		 _log.info("Adding missing Config Table Values .." );		
		 Config _conf = configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyTWS_HOST, _company, _group);
		 int _TotalConfigs = (_conf!=null ? 1 : 0 );
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
			 configLocalService.updateConfig(_conf);
			 
			 /* TWS PORT  */
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setCompanyId(_company);
			 _conf.setGroupId(_group);
			 _conf.setConfig_key(IBTraderConstants.keyTWS_PORT);
			 _conf.setValue(String.valueOf(IBTraderConstants.vTWS_PORT));
			 _conf.setName(IBTraderConstants.keyTWS_PORT);
			 _conf.setDescription(IBTraderConstants.keyTWS_PORT);
			 _conf.setGlobaldefault(false);
			 configLocalService.updateConfig(_conf);
			 /* SIMULATION MODE */   
			 _conf = configLocalService.createConfig(counterLocalService.increment(Config.class.getName()));
			 _conf.setGroupId(_group);
			 _conf.setCompanyId(_company);
			 _conf.setConfig_key(IBTraderConstants.keySIMULATION_MODE);
			 _conf.setValue(String.valueOf(IBTraderConstants.vSIMULATION_MODE));
			 _conf.setName(IBTraderConstants.keySIMULATION_MODE);
			 _conf.setDescription(IBTraderConstants.keySIMULATION_MODE);
			 _conf.setGlobaldefault(false);
			 configLocalService.updateConfig(_conf);   	
		 }
	}
	public void removeConfigurationValuesCompanyGroup(long  _company, long _group)
	{
		  /* TWS HOST */
		 _log.info("Removing  Config Table Values .." );		
		 Config _conf = configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyTWS_HOST, _company, _group);
		 int _TotalConfigs = (_conf!=null ? 1 : 0 );
		 if (_TotalConfigs==1)  configLocalService.deleteConfig(_conf);
		 
		 _conf = configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyACCOUNT_IB_NAME, _company, _group);
		 _TotalConfigs = (_conf!=null ? 1 : 0 );
		 if (_TotalConfigs==1)  configLocalService.deleteConfig(_conf);
		 
		 _conf = configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyTWS_PORT, _company, _group);
		 _TotalConfigs = (_conf!=null ? 1 : 0 );
		 if (_TotalConfigs==1)  configLocalService.deleteConfig(_conf);
		 
		 _conf = configLocalService.findByKeyCompanyGroup(IBTraderConstants.keySIMULATION_MODE, _company, _group);
		 _TotalConfigs = (_conf!=null ? 1 : 0 );
		 if (_TotalConfigs==1)  configLocalService.deleteConfig(_conf);		 
	}
	
}