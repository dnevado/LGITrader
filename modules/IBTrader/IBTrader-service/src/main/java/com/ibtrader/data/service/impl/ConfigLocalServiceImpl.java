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

import com.ibtrader.data.model.Config;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.base.ConfigLocalServiceBaseImpl;

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
 * @see com.ibtrader.data.service.ConfigLocalServiceUtil
 */
public class ConfigLocalServiceImpl extends ConfigLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ibtrader.data.service.ConfigLocalServiceUtil} to access the config local service.
	 */
	public List<Config> findByKeyGlobalDefault(String _key, boolean _global)
	{
		return getConfigPersistence().findByKeyGlobalDefault(_key, _global);
	}
	public List<Config> findByKeyCompany(String _key, long  _company)
	{
		return getConfigPersistence().findByKeyCompany(_company, _key);
	}
	
}