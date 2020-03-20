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

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.ibtrader.data.exception.NoSuchMarketException;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.data.service.base.MarketLocalServiceBaseImpl;
import com.ibtrader.util.Utilities;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

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
	public List<Market> findByActiveStartEndMarketHour(boolean active, long companyId, long groupId, Date backTestingDate)
	{
		return getByActiveStartEndMarketHour(active, companyId, groupId,backTestingDate);
				
							
	}
	public List<Market> findByActiveStartEndMarketHour(boolean active, long companyId, long groupId)
	{
		return getByActiveStartEndMarketHour(active, companyId, groupId, null);
				
							
	}
	private List<Market> getByActiveStartEndMarketHour(boolean active, long companyId, long groupId, Date backTestingDate)
	{
		
		
		/* PARA SACAR EL OWNER, NO VALE CON EL USERID DE LA ORGANIZACION, PODRIA CREARLA EL MISMO TEST O ADMINISTRADOR */
		
		/* PODRIAMOS COGER LOS USUARIOS DE LA ORGANIZACION. PERO PODRIAN SALIR VARIOS, DEPENDIENDO DEL PERFIL */
		
		/* SACAMOS OWNERS O ADMINISTRADORES  */
		
		List<User> administrators = new LinkedList<>();
		
		Role org_admin;
		try 
		{
			
			org_admin = RoleLocalServiceUtil.getRole(companyId, RoleConstants.ORGANIZATION_ADMINISTRATOR);
			Role org_owner = RoleLocalServiceUtil.getRole(companyId, RoleConstants.ORGANIZATION_OWNER);
			Role org_member= RoleLocalServiceUtil.getRole(companyId, RoleConstants.ORGANIZATION_USER);
	
			List<UserGroupRole> allOrganizationAdministrators = UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(groupId, org_admin.getRoleId());
			for (UserGroupRole userGroupRoleTemp : allOrganizationAdministrators) {
				if (!PortalUtil.isOmniadmin(userGroupRoleTemp.getUser()))
				{
					administrators.add(userGroupRoleTemp.getUser());
					break;
				}
			}
			if (allOrganizationAdministrators.size()==0)
			{
				List<UserGroupRole> allOrganizationOwners = UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(groupId, org_owner.getRoleId());
				for (UserGroupRole userGroupRoleTemp2 : allOrganizationOwners) {
					// quitamos a los test o superadministradores para que no confundan 
					if (!PortalUtil.isOmniadmin(userGroupRoleTemp2.getUser()))
					{
						administrators.add(userGroupRoleTemp2.getUser());
				    	break;
					}
				}
				if (allOrganizationOwners.size()==0)
				{
					List<UserGroupRole> allOrganizationMembers         = UserGroupRoleLocalServiceUtil.getUserGroupRolesByGroupAndRole(groupId, org_member.getRoleId());
					for (UserGroupRole userGroupRoleTemp3 : allOrganizationMembers) {
						if (!PortalUtil.isOmniadmin(userGroupRoleTemp3.getUser()))
						{
							administrators.add(userGroupRoleTemp3.getUser());
							break;
						}
					}
					
				}			
			}
			/* NO HAY ADMNISTRAADORES */
			if (administrators.size()==0)
				return null;
			
			/* DEBERIA HABER AL MENOS UN ADMINISTRADAOR */
			User _IBUser = UserLocalServiceUtil.fetchUser(administrators.get(0).getUserId());
			String HoraActual = "";		
			if (Validator.isNull(backTestingDate))
				HoraActual = Utilities.getHourNowFormat(_IBUser);					
			else
			
				HoraActual = Utilities.getHourNowFormat(_IBUser,backTestingDate);		/* HORA ACTUAL DEL USUARIO TOMADA DE LA FECHA DE BACKTESTING */
			
			DynamicQuery _DQ = MarketLocalServiceUtil.dynamicQuery();	
			/* LAS HORAS HAY QUE SACARLAS CON LA HORA LOCAL DEL USUARIO */
			_DQ.add(RestrictionsFactoryUtil.eq("active", active));
			_DQ.add(RestrictionsFactoryUtil.le("start_hour", HoraActual));
			_DQ.add(RestrictionsFactoryUtil.ge("end_hour", HoraActual));
			_DQ.add(RestrictionsFactoryUtil.eq("companyId", companyId));
			_DQ.add(RestrictionsFactoryUtil.eq("groupId", groupId));
	
			return marketLocalService.dynamicQuery(_DQ);
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			return null;
		}		
							
	}
	public List<Market> findByActive(boolean active)
	{
	
		DynamicQuery _DQ = MarketLocalServiceUtil.dynamicQuery();
		_DQ.add(RestrictionsFactoryUtil.eq("active", active));
		return marketLocalService.dynamicQuery(_DQ);
				
	}				
		
			
	public List<Market> findByCompanyGroup(long companyId, long groupId)
	{
	
			return getMarketPersistence().findByCompanyGroup(companyId, groupId);
				
							
	}
	
	public List<Market> findByActiveCompanyGroup(long companyId, long groupId, boolean active)
	{
	
			return getMarketPersistence().findByActiveCompanyGroup(companyId, groupId, active);
				
							
	}
	

	public Market findByNameMarketCompanyGroup(long companyId, long groupId, String name)
	{
			Market market =null;
			try {
				market = getMarketPersistence().findByNameCompanyGroup(companyId, groupId, name);
			} catch (NoSuchMarketException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			return market;
				
							
	}
	public Market findByIdentifierCompanyGroup(long companyId, long groupId, String identifier)
	{
			Market market =null;	
			try {
				market = getMarketPersistence().findByIdentifierCompanyGroup(companyId, groupId, identifier);
			} catch (NoSuchMarketException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			return market;	
							
	}
	
	public Market addMarket(Market market, ServiceContext serviceContext) throws PortalException   {
        
		//shareLocalService.get

		/*  VERIFICACIONES,
		 * QUE NO EXISTA POR NOMBRE
		 * QUE LOS PARAMETROS ESTEN CORRECTOS NUMEROS
		 * QUE TODOS LOS PARAMETROS OBLIGATORIOS EXISTAN
		 */
		
		Market _market = marketLocalService.createMarket(CounterLocalServiceUtil.increment(Market.class.getName()));
		
		_market.setActive(market.getActive());
		_market.setName(market.getName());
		_market.setIdentifier(market.getIdentifier());		
		_market.setDescription(market.getDescription());
		_market.setCompanyId(market.getCompanyId());
		_market.setGroupId(market.getGroupId());		
		_market.setCurrency(market.getCurrency());
		_market.setCreateDate(market.getCreateDate());
		_market.setModifiedDate(market.getModifiedDate());
		_market.setStart_hour(market.getStart_hour());
		_market.setEnd_hour(market.getEnd_hour());
		_market.setModifiedDate(market.getModifiedDate());
		marketLocalService.updateMarket(_market);
		   
	   
	    return _market;
	}
	
	public Market editMarket(Market market, ServiceContext serviceContext) throws PortalException   {
        
		//shareLocalService.get

		/*  VERIFICACIONES,
		 * QUE NO EXISTA POR NOMBRE
		 * QUE LOS PARAMETROS ESTEN CORRECTOS NUMEROS
		 * QUE TODOS LOS PARAMETROS OBLIGATORIOS EXISTAN
		 */
		
		marketPersistence.update(market);
		   
	   
	    return market;
	}
	
	
	
	
	
}