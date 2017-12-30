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

import com.ibtrader.data.exception.NoSuchStrategyShareException;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.service.base.StrategyShareLocalServiceBaseImpl;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;

/**
 * The implementation of the strategy share local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.service.StrategyShareLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrategyShareLocalServiceBaseImpl
 * @see com.ibtrader.data.service.StrategyShareLocalServiceUtil
 */
public class StrategyShareLocalServiceImpl 	extends StrategyShareLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ibtrader.data.service.StrategyShareLocalServiceUtil} to access the strategy share local service.
	 */
	
	public StrategyShare  addStrategyShare(StrategyShare _strategyshare)
	{
			StrategyShare strategyshare = strategyShareLocalService.createStrategyShare(CounterLocalServiceUtil.increment(StrategyShare.class.getName()));
			strategyshare.setActive(_strategyshare.getActive());
			strategyshare.setGroupId(_strategyshare.getGroupId());
			strategyshare.setCompanyId(_strategyshare.getCompanyId());
			strategyshare.setCreateDate(new Date());
			strategyshare.setShareId(_strategyshare.getShareId());
			strategyshare.setStrategyId(_strategyshare.getStrategyId());
			
		    strategyShareLocalService.updateStrategyShare(strategyshare);
			
		    return strategyshare;
			
	}
	
	
	public List<StrategyShare>getByGroupCompanyShareId(long groupid, long companyid, long shareId)
	{
			return getStrategySharePersistence().findByCommpanyShareId(shareId, groupid, companyid);
	}
	public StrategyShare getByCommpanyShareStrategyId(long groupid, long companyid, long shareId, long strategyId)
	{	
		
			StrategyShare strategyshare=null;
			try {
				strategyshare= getStrategySharePersistence().findByCommpanyShareStrategyId(shareId, strategyId, groupid, companyid);
			} catch (NoSuchStrategyShareException e) {
				// TODO Auto-generated catch block
			//e.printStackTrace();
			}
			return strategyshare;
	}
	
}