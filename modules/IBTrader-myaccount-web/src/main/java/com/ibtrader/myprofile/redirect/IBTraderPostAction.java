


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

package com.ibtrader.myprofile.redirect;


import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.Portal;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {
	    "key=login.events.post"
	}, service = LifecycleAction.class)
public class IBTraderPostAction extends Action {

	private static final Log  log = LogFactoryUtil.getLog(IBTraderPostAction.class);
	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) {
		
		//System.out.println("run  post login action ");
		long userId = _portal.getUserId(request);
		
		// ADMINISTRATOR, NO HACEMOS NADA 
		if (!_portal.isOmniadmin(userId))
		{
			User user = UserLocalServiceUtil.fetchUser(userId);
			try {
				List<Organization> organizations = user.getOrganizations();
				if (!organizations.isEmpty() && organizations.size()==1)
				{
					Organization user_organization = organizations.get(0);
					Group user_group_org = user_organization.getGroup();
					if (user_group_org.getActive() && user_group_org.hasPublicLayouts() 
							&& user_group_org.isOrganization() && user_group_org.getSite())
					{
						String urlFiendly = user_group_org.getFriendlyURL();
						response.sendRedirect("/web" +  urlFiendly);
						log.info("Redirect to .../web" +  urlFiendly);
						
					}
				}
					
			} catch (PortalException | IOException e) {
				// TODO Auto-generated catch block
				log.debug(e.getMessage());
			}
		}

	}

    @Reference(unbind = "-")
    protected void setPortal(Portal portal) {

        _portal = portal;
    }

    private Portal _portal;

}