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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.service.base.HistoricalRealtimeLocalServiceBaseImpl;
import com.ibtrader.util.Utilities;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactory;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * The implementation of the historical realtime local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.service.HistoricalRealtimeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HistoricalRealtimeLocalServiceBaseImpl
 * @see com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil
 */
@ProviderType
public class HistoricalRealtimeLocalServiceImpl	extends HistoricalRealtimeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ibtrader.data.service.HistoricalRealtimeLocalServiceUtil} to access the historical realtime local service.
	 */
	private static final Log _log = LogFactoryUtil.getLog(HistoricalRealtimeLocalServiceImpl.class);
	
	public void removeRealtimeFromToDate(Date from, Date to, long shareId, long companyId, long groupId)
	{
		
		DynamicQuery _DQ = historicalRealtimeLocalService.dynamicQuery();
				
		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("shareId", shareId));
		_DQ.add(RestrictionsFactoryUtil.ge("createDate", from));
		_DQ.add(RestrictionsFactoryUtil.le("createDate", to));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		
		List<HistoricalRealtime> dRealTime = historicalRealtimeLocalService.dynamicQuery(_DQ);
		for (HistoricalRealtime rt : dRealTime)
		{
			try {
				historicalRealtimeLocalService.deleteHistoricalRealtime(rt.getPrimaryKey());
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
	}
	/* OBTIENE EL MIN Y MAX DE UN  ACTIVO */	
	public HistoricalRealtime findMinMaxRealTime(Date from, Date to, long shareId, long companyId, long groupId)
	{
		List lMinMaxRealtime = null;
		lMinMaxRealtime = historicalRealtimeFinder.findMinMaxRealTime(from, to, shareId, companyId,  groupId); 
		String serilizeString=null;
		JSONArray minmaxRealtimeJsonArray=null;
		HistoricalRealtime MinMaxRealtime = null;
		for (Object oRealtime : lMinMaxRealtime)
		{
			serilizeString=JSONFactoryUtil.serialize(oRealtime);
			try {
				minmaxRealtimeJsonArray=JSONFactoryUtil.createJSONArray(serilizeString);
				MinMaxRealtime = historicalRealtimeLocalService.createHistoricalRealtime(-1); // no persistimos 				
				MinMaxRealtime.setMin_value(Double.isNaN(minmaxRealtimeJsonArray.getDouble(0)) ? 0d : minmaxRealtimeJsonArray.getDouble(0));
				MinMaxRealtime.setMax_value(Double.isNaN(minmaxRealtimeJsonArray.getDouble(1)) ? 0d : minmaxRealtimeJsonArray.getDouble(1));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			
		}
		return 	MinMaxRealtime;
	}
	public List<HistoricalRealtime> findMinMaxRealTimesGroupedByBars(Date from, Date to, long shareId, long companyId, long groupId, long timebars, Market market)
	{
		
		SimpleDateFormat sf = new SimpleDateFormat(Utilities.__IBTRADER_SHORT_HOUR_FORMAT);

		Calendar _openMarket = Utilities.getNewCalendarWithHour(to,market.getStart_hour());
		Calendar _closeMarket = Utilities.getNewCalendarWithHour(to,market.getEnd_hour());
		
		/* lo pasamos a UTC porque tenemos los realtime en UTC y los datos de mercado en ZONA HORARIA DEL USUARIO */
		Date _dOpenMarket = _openMarket.getTime();
		Date _dCloseMarket = _closeMarket.getTime();
		
		List<HistoricalRealtime> lMinMaxRealtime = null;
		lMinMaxRealtime = historicalRealtimeFinder.findMinMaxRealTimesGroupedByBars(from, to, shareId,companyId,groupId,timebars,sf.format(_dOpenMarket),sf.format(_dCloseMarket));
		String serilizeString=null;
		JSONArray minmaxRealtimeJsonArray=null;
		HistoricalRealtime MinMaxRealtime = null;
		List<HistoricalRealtime> lReturnRealtime = new ArrayList<HistoricalRealtime>();

		Calendar cBarDate = Calendar.getInstance();
		for (Object oHistoricalRealtime : lMinMaxRealtime)
		{
			serilizeString=JSONFactoryUtil.serialize(oHistoricalRealtime);
			try {
				minmaxRealtimeJsonArray=JSONFactoryUtil.createJSONArray(serilizeString);
				MinMaxRealtime = historicalRealtimeLocalService.createHistoricalRealtime(-1); // no persistimos 				
				MinMaxRealtime.setMin_value(Double.isNaN(minmaxRealtimeJsonArray.getDouble(0)) ? 0d : minmaxRealtimeJsonArray.getDouble(0));
				MinMaxRealtime.setMax_value(Double.isNaN(minmaxRealtimeJsonArray.getDouble(1)) ? 0d : minmaxRealtimeJsonArray.getDouble(1));
				
				JSONObject barDate =  JSONFactoryUtil.createJSONObject(minmaxRealtimeJsonArray.getString(2));
				cBarDate.setTimeInMillis(barDate.getLong("time"));
				MinMaxRealtime.setCreateDate(cBarDate.getTime());
				lReturnRealtime.add(MinMaxRealtime);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				_log.debug(e.getMessage());
				break;
			}
			
			
		}
		return 	lReturnRealtime;
		
		
		

	}
	
	public HistoricalRealtime findCloseRealTime(long shareId, long companyId, long groupId, Date closeDate)
	{
		return historicalRealtimeFinder.findCloseRealTimeDate(shareId, companyId, groupId,closeDate);
	}
	public List<HistoricalRealtime> findCloseRealTimes( long shareId, long companyId, long groupId,Date from, Date to, List<String> closingDates)
	{
		return historicalRealtimeFinder.findCloseRealTimes(shareId, companyId, groupId,from,to, closingDates);
	}
	
	public HistoricalRealtime findLastRealTime(long shareId, long companyId, long groupId)
	{
		return historicalRealtimeFinder.findLastRealTime(shareId, companyId,  groupId);	
	}
	public HistoricalRealtime findLastRealTimeLessThanDate(long shareId, long companyId, long groupId, Date _to)
	{
		return historicalRealtimeFinder.findLastRealTimeLessThanDate(shareId, companyId, groupId, _to);
	}
	public HistoricalRealtime findSimpleMobileAvgGroupByPeriods( long shareId, long companyId, long groupId,Date from, Date to, List<String> mobileAvgDates)
	{
		//return realtimeFinder.findSimpleMobileAvgGroupByPeriods( shareId, companyId,  groupId,from, to, mobileAvgDates);
		List lSimpleMobileAvgGroupByPeriods = null;
		lSimpleMobileAvgGroupByPeriods = historicalRealtimeFinder.findSimpleMobileAvgGroupByPeriods( shareId, companyId,  groupId,from, to, mobileAvgDates); 
		String serilizeString=null;
		JSONArray SimpleMobileAvgGroupByPeriodsJsonArray=null;
		HistoricalRealtime SimpleMobileAvgGroupByPeriodsRealtime = null;
		for (Object oRealtime : lSimpleMobileAvgGroupByPeriods)
		{
			serilizeString=JSONFactoryUtil.serialize(oRealtime);
			try {
				SimpleMobileAvgGroupByPeriodsJsonArray=JSONFactoryUtil.createJSONArray(serilizeString);
				SimpleMobileAvgGroupByPeriodsRealtime = historicalRealtimeLocalService.createHistoricalRealtime(-1); // no persistimos 
				SimpleMobileAvgGroupByPeriodsRealtime.setValue(SimpleMobileAvgGroupByPeriodsJsonArray.getDouble(0));
				SimpleMobileAvgGroupByPeriodsRealtime.setVolume(SimpleMobileAvgGroupByPeriodsJsonArray.getInt(1));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			
		}
		return 	SimpleMobileAvgGroupByPeriodsRealtime;
	}
	
	/* public List<Realtime> findExponentialMobileAvgGroupByPeriods( long shareId, long companyId, long groupId,Date from, Date to, List<String> mobileAvgDates)
	{
		return realtimeFinder.findExponentialMobileGroupByPeriods(shareId, companyId, groupId, from, to, mobileAvgDates);

	}
	*/
	
	 public HistoricalRealtime findLastCompanyShare(long companyId, long shareId, long groupId)
	{
		HistoricalRealtime _returnRT=null;
		DynamicQuery _DQ = historicalRealtimeLocalService.dynamicQuery();

		Projection projection_max = PropertyFactoryUtil.forName("realtimeId").max();

		
		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		_DQ.add(RestrictionsFactoryUtil.le("shareId", shareId));
		_DQ.setProjection(projection_max);
		
		
		
		List<Long> LastRealTime = historicalRealtimeLocalService.dynamicQuery(_DQ);
	
		/* hay tiempo real*/ 
		if (!LastRealTime.isEmpty())
		{
			_returnRT = historicalRealtimeLocalService.fetchHistoricalRealtime(LastRealTime.get(0));
		}
				
		
		return _returnRT;
		
				
	}
	
}