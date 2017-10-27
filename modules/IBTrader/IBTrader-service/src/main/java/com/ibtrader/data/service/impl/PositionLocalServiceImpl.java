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

import com.ibtrader.data.model.Position;
import com.ibtrader.data.service.base.PositionLocalServiceBaseImpl;
import com.ibtrader.data.service.persistence.PositionPersistence;

/**
 * The implementation of the position local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.service.PositionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PositionLocalServiceBaseImpl
 * @see com.ibtrader.data.service.PositionLocalServiceUtil
 */
public class PositionLocalServiceImpl extends PositionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ibtrader.data.service.PositionLocalServiceUtil} to access the position local service.
	 */
	public Position findByPositionID_Out_TWS(long _PositionIDTWS)
	{
		Position _rPosition = null; 
		List<Position> _lPosition = getPositionPersistence().findByPositionID_Out_TWS(_PositionIDTWS);
		if (!_lPosition.isEmpty())
		{
			_rPosition = _lPosition.get(0);
		}
		return _rPosition;
		
	}
}