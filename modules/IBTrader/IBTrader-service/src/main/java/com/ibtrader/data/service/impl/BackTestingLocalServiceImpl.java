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

import aQute.bnd.annotation.ProviderType;

import java.util.List;

import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.BackTesting;
import com.ibtrader.data.service.base.BackTestingLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;


/**
 * The implementation of the back testing local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.service.BackTestingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BackTestingLocalServiceBaseImpl
 * @see com.ibtrader.data.service.BackTestingLocalServiceUtil
 */
@ProviderType
public class BackTestingLocalServiceImpl extends BackTestingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ibtrader.data.service.BackTestingLocalServiceUtil} to access the back testing local service.
	 */
	public List<BackTesting> findByShareCompanyGroup(long shareId, long companyId, long groupId)
	{
		return getBackTestingPersistence().findByShareCompanyGroup(shareId,companyId, groupId);
	}
	/* status 
	 * DEBERIA IR TODO CON CERO SEGUNDOS ....
	 * */
	public List<BackTesting> findByPendingCompanyGroup(long companyId, long groupId)
	{
		

		DynamicQuery _DQ = backTestingLocalService.dynamicQuery();
		
		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));		
		_DQ.add(RestrictionsFactoryUtil.eq("status",IBTraderConstants.statusSimulation.Pending.toString()));		
		return backTestingLocalService.dynamicQuery(_DQ);
		
	}
}