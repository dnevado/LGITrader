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
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.base.PositionLocalServiceBaseImpl;
import com.ibtrader.data.service.persistence.PositionPersistence;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;


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
	
	
	public List<Position> findByCloseCompanyGroup(long companyId, long groupId, boolean forceclose, String positionMode)
	{	
		
		DynamicQuery _DQ = PositionLocalServiceUtil.dynamicQuery();

		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		_DQ.add(RestrictionsFactoryUtil.eq("forceclose", forceclose));		
		_DQ.add(RestrictionsFactoryUtil.eq("position_mode", positionMode));		
		_DQ.add(RestrictionsFactoryUtil.ne("state", PositionStates.status.SELL_OK.toString()));		
		return PositionLocalServiceUtil.dynamicQuery(_DQ);
	}
	
	
	public List<Position> findByCancelShareCompanyGroup(long companyId, long groupId, long pendingcancelled, long shareId, String positionMode)
	{	
		
		return  getPositionPersistence().findByCancelShareCompanyGroup(companyId, groupId, pendingcancelled,shareId,positionMode);
	}
	
	public List<Position> findByCompanyGroupDate(long companyId, long groupId, Date start_date_in , Date end_date_in, String positionMode)
	{	
		 DynamicQuery _DQ = PositionLocalServiceUtil.dynamicQuery();

		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		_DQ.add(RestrictionsFactoryUtil.eq("position_mode", positionMode));		
		_DQ.add(RestrictionsFactoryUtil.le("date_in", end_date_in));
		_DQ.add(RestrictionsFactoryUtil.ge("date_in", start_date_in));
		_DQ.addOrder(OrderFactoryUtil.desc("date_in"));
		
		
		
		return PositionLocalServiceUtil.dynamicQuery(_DQ);
	}
	
	
	public List<Position> findIntradiaByCompanyGroupDate(long companyId, long groupId, Date end_date_in, String positionMode)
	{	
		/* DynamicQuery _DQ = PositionLocalServiceUtil.dynamicQuery();

		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		 _DQ.add(RestrictionsFactoryUtil.le("date_in", end_date_in));
		_DQ.add(RestrictionsFactoryUtil.ge("date_in", start_date_in));
		_DQ.addOrder(OrderFactoryUtil.desc("date_in"));
		*/
		
		return positionFinder.getIntradiaPositions(end_date_in, groupId, companyId,positionMode);
		
		//List<Market>  = MarketLocalServiceUtil.dynamicQuery(_DQ);
		
		
		//return  getPositionPersistence().findByCompanyDate(companyId,start_date_in,end_date_in);
	}
	
	
	/* sacamos el maximo de las ordenes metidas en las posiciones para saber si usar estas o el currentOrderId de la TWS */
	public long findMaxOrderClientCompanyGroup(long companyId, long groupId, long clientId, String positionMode)
	{
		 
		
		DynamicQuery _DQ = positionLocalService.dynamicQuery();
		
		long maxOrderIdPositionsIn = 0;
		long maxOrderIdPositionsOut = 0;
		
		
		Projection projection_max_in = PropertyFactoryUtil.forName("positionId_tws_in").max();
		Projection projection_max_out = PropertyFactoryUtil.forName("positionId_tws_out").max();
				
		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.le("groupId", groupId));
		_DQ.add(RestrictionsFactoryUtil.le("clientId_in", groupId));
		_DQ.add(RestrictionsFactoryUtil.le("position_mode", positionMode));
		
		
		ProjectionList ListMaxValues  = ProjectionFactoryUtil.projectionList();
		ListMaxValues.add(projection_max_in);
		ListMaxValues.add(projection_max_out);
		_DQ.setProjection(ListMaxValues);
	
		List<Object[]> ordersMaxId = positionLocalService.dynamicQuery(_DQ);
		if (ordersMaxId!=null && !ordersMaxId.isEmpty())
		{
			
			for (Object MaxValues : ordersMaxId)
			{
				String serilizeString=JSONFactoryUtil.serialize(MaxValues);
				try {
					JSONArray MaxValuesJsonArray=JSONFactoryUtil.createJSONArray(serilizeString);
					if (MaxValuesJsonArray!=null && MaxValuesJsonArray.getLong(0)>0)
						maxOrderIdPositionsIn  = MaxValuesJsonArray.getLong(0);
					if (MaxValuesJsonArray!=null && MaxValuesJsonArray.getLong(1)>0)
						maxOrderIdPositionsOut = MaxValuesJsonArray.getLong(1);					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				
				
			}
		
		}
		/* devolvemos el más grande */
		return (maxOrderIdPositionsIn > maxOrderIdPositionsOut ? maxOrderIdPositionsIn : maxOrderIdPositionsOut);
		
	}
	
	public List<Position> findByCompanyGroupShare(long companyId, long groupId, long share, String positionMode)
	{
		 
		List<Position> _lPosition = getPositionPersistence().findByCompanyGroupShare(companyId,groupId,share,positionMode);	
		return _lPosition;
		
	}
	
	public Position findByCompanyGroup(long companyId, long groupId, String positionMode)
	{
		Position _rPosition = null; 
		List<Position> _lPosition = getPositionPersistence().findByCompanyGroup(companyId,groupId,positionMode);
		if (!_lPosition.isEmpty() && _lPosition.size()>0)
		{
			_rPosition = _lPosition.get(0);
		}
		return _rPosition;
		
	}
	
	public Position findByPositionID_Out_TWS(long groupId, long companyId, long _PositionIDTWS, long clientId_out,String positionMode)
	{
		 
		Position _rPosition = null;
		try {
			_rPosition = getPositionPersistence().findByPositionOutGroupCompany(groupId,companyId, _PositionIDTWS,clientId_out,positionMode);
		} catch (NoSuchPositionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}		
		return _rPosition;
		
	}
	public Position findByPositionID_In_TWS(long groupId, long companyId, long _PositionIDTWS, long clientId_in, String positionMode)
	{
		Position _rPosition = null;
		try {
			_rPosition =  getPositionPersistence().findByPositionInGroupCompany(groupId,companyId, _PositionIDTWS,clientId_in,positionMode);
		} 
		catch (NoSuchPositionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}		
		return _rPosition;
		
	}
	
	/* FECHA  SALIDA   Y REAL DE SALIDA A  NULL , SI PODEMOS LA  REAL  ENTRADA, PUEDE VOLVOER A ENTRAR HASTA QUE NO ESTE FILLED */
	public boolean  ExistsOpenPosition(long groupId, long companyId, long shareId, String positionMode)
	{		
		
		
		List<Position> _lPosition = null;

		
		DynamicQuery _DQ = PositionLocalServiceUtil.dynamicQuery();

		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		_DQ.add(RestrictionsFactoryUtil.eq("shareId", shareId));
		_DQ.add(RestrictionsFactoryUtil.eq("position_mode", positionMode));		
		_DQ.add(RestrictionsFactoryUtil.ne("state", com.ibtrader.util.PositionStates.status.SELL_OK.toString()));
		
		
		_lPosition =  PositionLocalServiceUtil.dynamicQuery(_DQ);
		
		/* en el momento que no haya un SELL_OK, consuderans una abierta, no contemplamos fechas */ 
		
		return (!_lPosition.isEmpty() && _lPosition.size()>0);
				
	}
	/* BUY_OK  y no fecha de salida real */
	public boolean  ExistsPositionToExit(long groupId, long companyId, long shareId,String positionMode)
	{		
		int total = getPositionPersistence().countByPositionShareStateDatesRealOut(groupId, companyId, shareId, com.ibtrader.util.PositionStates.status.BUY_OK.toString(),null, null, positionMode);
		return (total>0);
	}
	/* BUY_OK  y no fecha de salida */
	public Position  findPositionToExit(long groupId, long companyId, long shareId, String positionMode)
	{	
		Position _rPosition = null; 
		List<Position> _lPosition = getPositionPersistence().findByPositionShareStateDatesRealOut(groupId, companyId, shareId, com.ibtrader.util.PositionStates.status.BUY_OK.toString(),null,null, positionMode);		
		if (!_lPosition.isEmpty() && _lPosition.size()>0)
		{
			_rPosition = _lPosition.get(0);
		}
		return _rPosition;
	}
	
	/* YO GENERARIA UN JSON DE DATOS DE VUELTA */
	/*
	 *		    OPERACIONES --> OPERACIONES POR TIPO 
		        MARGENBENEFICIO --> MARGEN 
		        BENEFICIO --> TOTAL BENEFIOCIO 
			    INVERTIDO  --> TOTAL INVERTIDO 
			    TIPO --> OPERACION(non-Javadoc)
	 * 
	 */
	public JSONArray findPositionClosedResults(Date from, Date to,long groupId, long companyId, String positionMode, long backtestingId)
	{
		//return realtimeFinder.findSimpleMobileAvgGroupByPeriods( shareId, companyId,  groupId,from, to, mobileAvgDates);
		List lResults = null;
		lResults = positionFinder.getPositionClosedResults(from, to,groupId,companyId,positionMode,backtestingId); 
		String serilizeString=null;				
		JSONArray positionResults = JSONFactoryUtil.createJSONArray();
	
		if (lResults!=null && !lResults.isEmpty())
		{
			for (Object oPosition : lResults)
			{
				//[1,3,23,545,23]
				serilizeString=JSONFactoryUtil.serialize(oPosition);
				serilizeString = serilizeString.replace("[","").replace("]","");
				String[] arrayData = serilizeString.split(",");
				
				JSONObject positionResult = JSONFactoryUtil.createJSONObject();
				positionResult.put("OPERACIONES", arrayData[0]);
				positionResult.put("MARGENBENEFICIO", Utilities.RoundPrice(Double.parseDouble(arrayData[1])));
				positionResult.put("BENEFICIO", Utilities.RoundPrice(Double.parseDouble(arrayData[2])));
				positionResult.put("INVERTIDO", Utilities.RoundPrice(Double.parseDouble(arrayData[3])));
				positionResult.put("TIPO", arrayData[4]);
				
				positionResults.put(positionResult);
				
				
			}
		}
		return 	positionResults;
	}
	public JSONArray findPositionOpenResults(Date to,long groupId, long companyId, String positionMode)
	{
		//return realtimeFinder.findSimpleMobileAvgGroupByPeriods( shareId, companyId,  groupId,from, to, mobileAvgDates);
		List lResults = null;
		lResults = positionFinder.getPositionOpenResults(to,groupId,companyId,positionMode); 
		String serilizeString=null;				
		JSONArray positionResults = JSONFactoryUtil.createJSONArray();
	
		if (lResults!=null && !lResults.isEmpty())
		{
			for (Object oPosition : lResults)
			{
				//[1,3,23,545,23]
				serilizeString=JSONFactoryUtil.serialize(oPosition);
				serilizeString = serilizeString.replace("[","").replace("]","");
				String[] arrayData = serilizeString.split(",");
				
				JSONObject positionResult = JSONFactoryUtil.createJSONObject();
				
				positionResult.put("MARGENBENEFICIO", Utilities.RoundPrice(Double.parseDouble(arrayData[0])));
				positionResult.put("BENEFICIO", Utilities.RoundPrice(Double.parseDouble(arrayData[1])));								
				
				positionResults.put(positionResult);
				
				/* solo cogemos el array de cobjectos la primera posicion dodne viene serializado todo */
				break;
				
				
			}
		}
		return 	positionResults;
	}
	
	


}