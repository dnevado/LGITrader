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

import java.util.Date;
import java.util.List;

import com.ibtrader.data.exception.NoSuchPositionException;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.base.PositionLocalServiceBaseImpl;
import com.ibtrader.data.service.persistence.PositionPersistence;
import com.ibtrader.util.ConfigKeys;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;


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
	
	
	public List<Position> findByCompanyGroupDate(long companyId, long groupId, Date start_date_in,Date end_date_in)
	{	
		DynamicQuery _DQ = PositionLocalServiceUtil.dynamicQuery();

		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		 _DQ.add(RestrictionsFactoryUtil.le("date_in", end_date_in));
		_DQ.add(RestrictionsFactoryUtil.ge("date_in", start_date_in));
		_DQ.addOrder(OrderFactoryUtil.desc("date_in"));
		
		//List<Market>  = MarketLocalServiceUtil.dynamicQuery(_DQ);
		
		return PositionLocalServiceUtil.dynamicQuery(_DQ);
		
		//return  getPositionPersistence().findByCompanyDate(companyId,start_date_in,end_date_in);
	}
	
	
	public List<Position> findByCompanyGroupShare(long companyId, long groupId, long share)
	{
		 
		List<Position> _lPosition = getPositionPersistence().findByCompanyGroupShare(companyId,groupId,share);	
		return _lPosition;
		
	}
	
	public Position findByCompanyGroup(long companyId, long groupId)
	{
		Position _rPosition = null; 
		List<Position> _lPosition = getPositionPersistence().findByCompanyGroup(companyId,groupId);
		if (!_lPosition.isEmpty() && _lPosition.size()>0)
		{
			_rPosition = _lPosition.get(0);
		}
		return _rPosition;
		
	}
	
	public Position findByPositionID_Out_TWS(long groupId, long companyId, long _PositionIDTWS)
	{
		 
		Position _rPosition = null;
		try {
			_rPosition = getPositionPersistence().findByPositionOutGroupCompany(groupId,companyId, _PositionIDTWS);
		} catch (NoSuchPositionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}		
		return _rPosition;
		
	}
	public Position findByPositionID_In_TWS(long groupId, long companyId, long _PositionIDTWS)
	{
		Position _rPosition = null;
		try {
			_rPosition =  getPositionPersistence().findByPositionInGroupCompany(groupId,companyId, _PositionIDTWS);
		} 
		catch (NoSuchPositionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}		
		return _rPosition;
		
	}
	
	/* FECHA DE ENTRADA A NULL */
	public boolean  ExistsOpenPosition(long groupId, long companyId, long shareId)
	{		
		List<Position> _lPosition = getPositionPersistence().findByPositionShareDateOut(groupId, companyId, shareId, null);
		return (!_lPosition.isEmpty() && _lPosition.size()>0);
	}
	/* BUY_OK  y no fecha de salida */
	public boolean  ExistsPositionToExit(long groupId, long companyId, long shareId)
	{		
		int total = getPositionPersistence().countByPositionShareStateDateOut(groupId, companyId, shareId, com.ibtrader.util.PositionStates.status.BUY_OK.toString(),null);
		return (total>0);
	}
	/* BUY_OK  y no fecha de salida */
	public Position  findPositionToExit(long groupId, long companyId, long shareId)
	{	
		Position _rPosition = null; 
		List<Position> _lPosition =getPositionPersistence().findByPositionShareStateDateOut(groupId, companyId, shareId, com.ibtrader.util.PositionStates.status.BUY_OK.toString(),null);		
		if (!_lPosition.isEmpty() && _lPosition.size()>0)
		{
			_rPosition = _lPosition.get(0);
		}
		return _rPosition;
	}
	
	
	
	


}