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

import java.util.Date;
import java.util.List;

import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.service.base.RealtimeLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;

/**
 * The implementation of the realtime local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.service.RealtimeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RealtimeLocalServiceBaseImpl
 * @see com.ibtrader.data.service.RealtimeLocalServiceUtil
 */
@ProviderType
public class RealtimeLocalServiceImpl extends RealtimeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ibtrader.data.service.RealtimeLocalServiceUtil} to access the realtime local service.
	 */
	
	
	
	public void removeRealtimeFromToDate(Date from, Date to, long shareId, long companyId, long groupId)
	{
		
		DynamicQuery _DQ = realtimeLocalService.dynamicQuery();
				
		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("shareId", shareId));
		_DQ.add(RestrictionsFactoryUtil.ge("createDate", from));
		_DQ.add(RestrictionsFactoryUtil.le("createDate", to));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		
		List<Realtime> dRealTime = realtimeLocalService.dynamicQuery(_DQ);
		for (Realtime rt : dRealTime)
		{
			try {
				realtimeLocalService.deleteRealtime(rt.getPrimaryKey());
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
	}
	/* OBTIENE EL MIN Y MAX DE UN  ACTIVO */	
	public Realtime findMinMaxRealTime(Date from, Date to, long shareId, long companyId, long groupId)
	{
		List lMinMaxRealtime = null;
		lMinMaxRealtime = realtimeFinder.findMinMaxRealTime(from, to, shareId, companyId,  groupId); 
		String serilizeString=null;
		JSONArray minmaxRealtimeJsonArray=null;
		Realtime MinMaxRealtime = null;
		for (Object oRealtime : lMinMaxRealtime)
		{
			serilizeString=JSONFactoryUtil.serialize(oRealtime);
			try {
				minmaxRealtimeJsonArray=JSONFactoryUtil.createJSONArray(serilizeString);
				MinMaxRealtime = realtimeLocalService.createRealtime(-1); // no persistimos 
				MinMaxRealtime.setMin_value(minmaxRealtimeJsonArray.getDouble(0));
				MinMaxRealtime.setMax_value(minmaxRealtimeJsonArray.getDouble(1));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			
		}
		return 	MinMaxRealtime;
	}
	public Realtime findLastRealTime(long shareId, long companyId, long groupId)
	{
		return realtimeFinder.findLastRealTime(shareId, companyId,  groupId);	
	}
	public Realtime findLastRealTimeLessThanDate(long shareId, long companyId, long groupId, Date _to)
	{
		return realtimeFinder.findLastRealTimeLessThanDate(shareId, companyId, groupId, _to);
	}
	public Realtime findSimpleMobileAvgGroupByPeriods( long shareId, long companyId, long groupId,Date from, Date to, List<String> mobileAvgDates)
	{
		//return realtimeFinder.findSimpleMobileAvgGroupByPeriods( shareId, companyId,  groupId,from, to, mobileAvgDates);
		List lSimpleMobileAvgGroupByPeriods = null;
		lSimpleMobileAvgGroupByPeriods = realtimeFinder.findSimpleMobileAvgGroupByPeriods( shareId, companyId,  groupId,from, to, mobileAvgDates); 
		String serilizeString=null;
		JSONArray SimpleMobileAvgGroupByPeriodsJsonArray=null;
		Realtime SimpleMobileAvgGroupByPeriodsRealtime = null;
		for (Object oRealtime : lSimpleMobileAvgGroupByPeriods)
		{
			serilizeString=JSONFactoryUtil.serialize(oRealtime);
			try {
				SimpleMobileAvgGroupByPeriodsJsonArray=JSONFactoryUtil.createJSONArray(serilizeString);
				SimpleMobileAvgGroupByPeriodsRealtime = realtimeLocalService.createRealtime(-1); // no persistimos 
				SimpleMobileAvgGroupByPeriodsRealtime.setValue(SimpleMobileAvgGroupByPeriodsJsonArray.getDouble(0));
				SimpleMobileAvgGroupByPeriodsRealtime.setVolume(SimpleMobileAvgGroupByPeriodsJsonArray.getInt(1));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			
		}
		return 	SimpleMobileAvgGroupByPeriodsRealtime;
	}
	
	 public Realtime findLastCompanyShare(long companyId, long shareId, long groupId)
	{
		Realtime _returnRT=null;
		DynamicQuery _DQ = realtimeLocalService.dynamicQuery();

		Projection projection_max = PropertyFactoryUtil.forName("realtimeId").max();

		
		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		_DQ.add(RestrictionsFactoryUtil.le("shareId", shareId));
		_DQ.setProjection(projection_max);
		
		
		
		List<Long> LastRealTime = realtimeLocalService.dynamicQuery(_DQ);
	
		/* hay tiempo real*/ 
		if (!LastRealTime.isEmpty())
		{
			_returnRT = realtimeLocalService.fetchRealtime(LastRealTime.get(0));
		}
				
		
		return _returnRT;
		
				
	}
}