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

import com.ibtrader.cron.IBTraderOrderRequestMaintance;
import com.ibtrader.data.exception.NoSuchIBOrderException;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.base.IBOrderLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;


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
	Log _log = LogFactoryUtil.getLog(IBOrderLocalServiceImpl.class);

	
	/* AQUI BORRAMOS TODAS , ANTIGUAS */
	public 	List<IBOrder> findByDate(Date untilDate)
	{
		DynamicQuery _DQ = IBOrderLocalServiceUtil.dynamicQuery();
				
		_DQ.add(RestrictionsFactoryUtil.le("createDate", untilDate));
		
		return ibOrderLocalService.dynamicQuery(_DQ);
	}
	
	
	/* SACAMOS TODAS LAS IBORDER A BORRAR PARA QUE NO ENTREN EN CONFLICTO AL REINICIAR, SOLO LAS REMOVABLE, LAS DE POSICIONES LAS DEJAMOS */
	public 	List<IBOrder> findByRemovableDate(Date untilDate,   boolean removable_on_reboot)
	{
		DynamicQuery _DQ = IBOrderLocalServiceUtil.dynamicQuery();
		
		_DQ.add(RestrictionsFactoryUtil.eq("removable_on_reboot", removable_on_reboot));
		_DQ.add(RestrictionsFactoryUtil.le("createDate", untilDate));
		
		return ibOrderLocalService.dynamicQuery(_DQ);
	}
	
	
	/* DEBERIAMOS COGER EL ULTIMO */
	public 	List<IBOrder> findByShareIdCompanyGroup(long shareId, long companyId, long groupId)
	{
		List<IBOrder> _order = null;
		_order = getIBOrderPersistence().findByShareIdCompanyGroup(shareId, companyId, groupId);
		return _order;
	}
	
	
	public 	IBOrder findByOrderShareClientGroupCompany(long iborderId, long shareId, long clientId, long companyId, long groupId)
	{
		List<IBOrder> _orders = null;
		IBOrder order = null; 	
		try 
		{

			_orders = getIBOrderPersistence().findByOrderShareClientGroupCompany(iborderId,companyId,shareId, groupId,clientId);
			if (_orders!=null && !_orders.isEmpty())
					order = _orders.get(0); 
		}
		catch (Exception e)
		{
			_log.info("findByOrderClientGroupCompany:" + e.getMessage());
		}
		return order;
	}
	
	public 	IBOrder findByOrderClientGroupCompany(long iborderId, long clientId, long companyId, long groupId)
	{
		List<IBOrder> _orders = null;
		IBOrder order = null; 	
		try 
		{
			_orders = getIBOrderPersistence().findByOrderClientGroupCompany(iborderId,companyId,groupId,clientId);
			if (_orders!=null && !_orders.isEmpty())
					order = _orders.get(0); 
		}
		catch (Exception e)
		{
			_log.info("findByOrderClientGroupCompany:" + e.getMessage());
		}
		return order;
	}
	/* al usar el ID VALIDO DE LA TWS, BORRAMOS PARA EVITAR DUPLUICADOS AL INTERNATR CONECTARNOS 
	 *  
	 */
	public 	void deleteByOrderCompanyGroup(long iborderId, long companyId, long groupId, long ibclientId, long shareId)
	{		
		DynamicQuery _DQ = ibOrderLocalService.dynamicQuery();

		_DQ.add(RestrictionsFactoryUtil.eq("ordersId", iborderId));
		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		_DQ.add(RestrictionsFactoryUtil.eq("ibclientId", groupId));
		_DQ.add(RestrictionsFactoryUtil.eq("shareID", shareId));
					
		List<IBOrder> orderList = ibOrderLocalService.dynamicQuery(_DQ);
		
		for (IBOrder order : orderList)
		{
			ibOrderLocalService.deleteIBOrder(order);
		}
		
		
	}
	/* sacamos el maximo de las ordenes metidas en las posiciones para saber si usar estas o el currentOrderId de la TWS */
	public long findMaxOrderClientCompanyGroup(long companyId, long groupId, long clientId)
	{
		 
		
		DynamicQuery _DQ = ibOrderLocalService.dynamicQuery();
		
		long maxOrderId = 0;
		
		Projection projection_max = PropertyFactoryUtil.forName("ordersId").max();
		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		_DQ.add(RestrictionsFactoryUtil.eq("ibclientId", clientId));
		
		ProjectionList ListMaxValues  = ProjectionFactoryUtil.projectionList();
		ListMaxValues.add(projection_max);
		
		_DQ.setProjection(ListMaxValues);
	
		List<Long> ordersMaxId = positionLocalService.dynamicQuery(_DQ);
		if (ordersMaxId!=null && !ordersMaxId.isEmpty())
		{
			for (Long MaxValues : ordersMaxId) {
				if (MaxValues!=null && MaxValues.longValue()>0)
					maxOrderId = MaxValues.longValue();
				
			}
		}		
		return (maxOrderId);
		
	}
	
	
	/* 
	 *  LO USAAMOS PARA DETECTAR ELIMINAR EL MKTDATA DEL SHARE EN CASO DE QUE SE DEACTIVE 
	 *  
	 *  */
	public long findMaxOrderClientShareCompanyGroup(long companyId, long groupId, long clientId, long shareId)
	{
		 
		
		DynamicQuery _DQ = ibOrderLocalService.dynamicQuery();
		
		long maxOrderId = 0;
		
		Projection projection_max = PropertyFactoryUtil.forName("ordersId").max();
		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		_DQ.add(RestrictionsFactoryUtil.eq("ibclientId", clientId));
		_DQ.add(RestrictionsFactoryUtil.eq("shareID", shareId));

		
		ProjectionList ListMaxValues  = ProjectionFactoryUtil.projectionList();
		ListMaxValues.add(projection_max);
		
		_DQ.setProjection(ListMaxValues);
	
		List<Long> ordersMaxId = positionLocalService.dynamicQuery(_DQ);
		if (ordersMaxId!=null && !ordersMaxId.isEmpty())
		{
			for (Long MaxValues : ordersMaxId) {
				if (MaxValues!=null && MaxValues.longValue()>0)
					maxOrderId = MaxValues.longValue();
				
			}
		}		
		return (maxOrderId);
		
	}
	
	
	
}