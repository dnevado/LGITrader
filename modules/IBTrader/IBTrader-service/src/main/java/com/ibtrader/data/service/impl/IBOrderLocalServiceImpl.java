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

import com.ibtrader.data.exception.NoSuchIBOrderException;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.service.base.IBOrderLocalServiceBaseImpl;


/**
 * The implementation of the i b order local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.service.IBOrderLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see IBOrderLocalServiceBaseImpl
 * @see com.ibtrader.data.service.IBOrderLocalServiceUtil
 */
@ProviderType
public class IBOrderLocalServiceImpl extends IBOrderLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ibtrader.data.service.IBOrderLocalServiceUtil} to access the i b order local service.
	 */
	
	/* DEBERIAMOS COGER EL ULTIMO */
	public 	List<IBOrder> findByShareIdCompanyGroup(long shareId, long companyId, long groupId)
	{
		List<IBOrder> _order = null;
		_order = getIBOrderPersistence().findByShareIdCompanyGroup(shareId, companyId, groupId);
		return _order;
	}
}