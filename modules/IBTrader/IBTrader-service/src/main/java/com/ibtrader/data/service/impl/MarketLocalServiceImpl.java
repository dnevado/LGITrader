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

import com.ibtrader.data.model.Market;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.base.MarketLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

/**
 * The implementation of the market local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.service.MarketLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MarketLocalServiceBaseImpl
 * @see com.ibtrader.data.service.MarketLocalServiceUtil
 */
public class MarketLocalServiceImpl extends MarketLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ibtrader.data.service.MarketLocalServiceUtil} to access the market local service.
	 */ 
	public List<Market> findByActiveStartEndHour(String _Start,String _End, boolean _Active)
	{
		DynamicQuery _DQ = MarketLocalServiceUtil.dynamicQuery();

		_DQ.add(RestrictionsFactoryUtil.like("active", _Active));
		_DQ.add(RestrictionsFactoryUtil.le("start_hour", _Start));
		_DQ.add(RestrictionsFactoryUtil.ge("end_hour", _End));
		
		//List<Market>  = MarketLocalServiceUtil.dynamicQuery(_DQ);
		
		return MarketLocalServiceUtil.dynamicQuery(_DQ);
				
							
	}
	public List<Market> findByActiveCompanyGroup(long companyId, long groupId, boolean active)
	{
	
			return getMarketPersistence().findByActiveCompanyGroup(companyId, groupId, active);
				
							
	}
	
	
	
}