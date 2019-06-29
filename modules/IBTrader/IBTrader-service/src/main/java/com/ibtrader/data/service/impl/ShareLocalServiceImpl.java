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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ibtrader.data.exception.NoSuchShareException;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.base.ShareLocalServiceBaseImpl;
import com.ibtrader.util.ConfigKeys;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

/**
 * The implementation of the share local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.service.ShareLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShareLocalServiceBaseImpl
 * @see com.ibtrader.data.service.ShareLocalServiceUtil
 */
public class ShareLocalServiceImpl extends ShareLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ibtrader.data.service.ShareLocalServiceUtil} to access the share local service.
	 */
	public List<Share> findByMarketGroupCompany(long _marketId, long groupId, long companyId)
	{
		return getSharePersistence().findByMarketGroupCompany(groupId, companyId, _marketId);
	}
	
	
	/* para el historical data  validadas */
	public List<Share> findAllSharesSortbySimulated()
	{

		DynamicQuery _DQ = shareLocalService.dynamicQuery();
		Order defaultOrder = OrderFactoryUtil.asc("simulation_end_date");
	
		
		_DQ.addOrder(defaultOrder);

		
		return shareLocalService.dynamicQuery(_DQ);

		
	}
	
	
	
	/* PARA EL MODO FAKE DE UNA TWS PARA TODOS, LOS REALTIME PARECEN QUE NO FUNCIONAN DE MISMO SIMBOL */
	public List<Share> findBySymbolExcludingId(String symbol, long exludingShareId)
	{

		DynamicQuery _DQ = shareLocalService.dynamicQuery();
	
		_DQ.add(RestrictionsFactoryUtil.eq("symbol", symbol));
		_DQ.add(RestrictionsFactoryUtil.eq("active", Boolean.TRUE));
		_DQ.add(RestrictionsFactoryUtil.ne("shareId", exludingShareId));
		
		return shareLocalService.dynamicQuery(_DQ);

		
	}
	
	
	
	
	public List<Share> findByActiveMarketGroupCompany(long _marketId, boolean _active, long groupId, long companyId)
	{
		return getSharePersistence().findByActiveMarketGroupCompany(groupId, companyId,_active, _marketId);
	}
	
	public List<Share> findByActiveFuturesDates(boolean _active)
	{
		DynamicQuery _DQ = shareLocalService.dynamicQuery();
		
		Date _today = new Date();
		_today.setHours(0);
		_today.setMinutes(0);
		_today.setSeconds(0);
		
		_DQ.add(RestrictionsFactoryUtil.eq("active", _active));
		_DQ.add(RestrictionsFactoryUtil.eq("security_type", ConfigKeys.SECURITY_TYPE_FUTUROS));
		_DQ.add(RestrictionsFactoryUtil.le("expiry_date", _today));
		
		/*
		 * al editarlo, 
		 * share.setDate_contract_verified(null);  // para verificarlo de nuevo
		    share.setValidated_trader_provider(Boolean.FALSE);
		*/
		
		return shareLocalService.dynamicQuery(_DQ);
	}
	
	
	/* CUANDO SE EDITE UNA ACCION, 
	 * 1. PONEMOS EL DATE_VALIDATED A date()
	 * 2. Intentamos eliminar que si un contrato ha sido verificado hoy, no lo saque.   
	 * 
	 */
	
	public List<Share> findByValidatedTraderProviderMarketGroupCompany(long marketId, long groupId, long companyId)
	{
		DynamicQuery _DQ = shareLocalService.dynamicQuery();
		_DQ.add(RestrictionsFactoryUtil.eq("marketId", marketId));
	//	_DQ.add(RestrictionsFactoryUtil.eq("active", active));
		_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
		_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		//_DQ.add(RestrictionsFactoryUtil.isNull("date_validated_trader_provider"));
		
		// AND
		
		LocalDate now = LocalDate.now();	
		ZoneId UTCZone = ZoneId.systemDefault(); // UTC
   		ZonedDateTime startOfDay = now.atStartOfDay(UTCZone); //00:00:00:00		   		   	
   		
		Criterion criteria = null; 
		criteria = RestrictionsFactoryUtil.eq("validated_trader_provider",Boolean.FALSE);	
		// OR
		criteria = RestrictionsFactoryUtil.or(criteria, RestrictionsFactoryUtil.le("date_validated_trader_provider",Date.from(startOfDay.toInstant())));
		_DQ.add(criteria);
	
		
		/*
		 * al editarlo, 
		 * share.setDate_contract_verified(null);  // para verificarlo de nuevo
		    share.setValidated_trader_provider(Boolean.FALSE);
		*/
		
		return shareLocalService.dynamicQuery(_DQ);
	}
	

	public List<Share> findCompanyGroup(long companyId, long groupId) {
		// TODO Auto-generated method stub
		return getSharePersistence().findByCompanyGroup(companyId, groupId);
	}
	
	public Share findBySymbolCompanyGroup(long companyId, long groupId, String name) {
		// TODO Auto-generated method stub
		Share share=null;
		try {
			share= getSharePersistence().findBySymbolCompanyGroup(companyId, groupId, name);
		} catch (NoSuchShareException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return share;
	}
	
	
	
	/* SHARES BY MARKET  Y NAME */
	public Share findByNameMarketCompanyGroup(long companyId, long groupId, String name, long marketId) {
		// TODO Auto-generated method stub
		Share share=null;
		try {
			share= getSharePersistence().findByNameMarketCompanyGroup(companyId, groupId, name, marketId);
		} catch (NoSuchShareException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return share;
	}
	
	public boolean ExistsSecurityType(String type)
	{
		return (type.equals(ConfigKeys.SECURITY_TYPE_FUTUROS) || type.equals(ConfigKeys.SECURITY_TYPE_STOCK)); 
	}
	public boolean ExistsExchange(String exchange)
	{
		boolean _bFound =false;
		for (String pExchange : ConfigKeys._MARKET_EXCHANGES)
		{
			if (pExchange.equals(exchange))
			{
				_bFound = true;
				break;
			}
				
		}
		return _bFound;
	}
	public boolean ExistsPrimaryExchange(String primaryexchange)
	{
		
		boolean _bFound =false;
		for (String pExchange : ConfigKeys._PRIMARY_MARKET_EXCHANGES)
		{
			if (pExchange.equals(primaryexchange))
			{
				_bFound = true;
				break;
			}
				
		}
		return _bFound;
		
	}
	
	public Share addShare(Share share, ServiceContext serviceContext) throws PortalException   {
            
		//shareLocalService.get

		/*  VERIFICACIONES,
		 * QUE NO EXISTA POR NOMBRE
		 * QUE LOS PARAMETROS ESTEN CORRECTOS NUMEROS
		 * QUE TODOS LOS PARAMETROS OBLIGATORIOS EXISTAN
		 */
		
		Share _share = shareLocalService.createShare(CounterLocalServiceUtil.increment(Share.class.getName()));
		
		_share.setActive(share.getActive());
		_share.setName(share.getName());
		_share.setSymbol(share.getSymbol());
		_share.setNumbertopurchase(share.getNumbertopurchase());
		_share.setPercentual_limit_buy(share.getPercentual_limit_buy());
		_share.setPercentual_stop_lost(share.getPercentual_stop_lost());
		_share.setPercentual_stop_profit(share.getPercentual_stop_profit());
		_share.setCompanyId(share.getCompanyId());
		_share.setGroupId(share.getGroupId());
		_share.setExpiry_expression(share.getExpiry_expression());
		_share.setExpiry_date(share.getExpiry_date());
		_share.setMultiplier(share.getMultiplier());
		_share.setTick_futures(share.getTick_futures());
		_share.setSecurity_type(share.getSecurity_type());					
		_share.setPrimary_exchange(share.getPrimary_exchange());
		_share.setExchange(share.getExchange());
		_share.setMarketId(share.getMarketId());
		_share.setCreateDate(share.getCreateDate());
		_share.setModifiedDate(share.getModifiedDate());
		_share.setUserCreatedId(share.getUserCreatedId());
		
			
		LocalDate now = LocalDate.now();
		now = now.minus(2, ChronoUnit.DAYS); // para que lo valide el sistema, coge aquellos no validados hoy.
		
		ZoneId UTCZone = ZoneId.of("UTC");	
   		ZonedDateTime zonedDateTime = now.atStartOfDay(UTCZone);   		
		_share.setDate_validated_trader_provider(Date.from(zonedDateTime.toInstant()));
		
		
		sharePersistence.update(_share);
		   
	   
	    return _share;
	}
	
	public Share editShare(Share share, ServiceContext serviceContext) throws PortalException   {
        
		//shareLocalService.get

		/*  VERIFICACIONES,
		 * QUE NO EXISTA POR NOMBRE
		 * QUE LOS PARAMETROS ESTEN CORRECTOS NUMEROS
		 * QUE TODOS LOS PARAMETROS OBLIGATORIOS EXISTAN
		 */
		
		sharePersistence.update(share);
		   
	   
	    return share;
	}
	
	
	
}