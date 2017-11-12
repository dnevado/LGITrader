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

import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.service.StrategyLocalServiceUtil;
import com.ibtrader.data.service.base.StrategyLocalServiceBaseImpl;
import com.ibtrader.data.service.persistence.StrategyPersistence;
import com.ibtrader.data.service.persistence.impl.StrategyPersistenceImpl;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ResourceLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

/**
 * The implementation of the strategy local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.service.StrategyLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrategyLocalServiceBaseImpl
 * @see com.ibtrader.data.service.StrategyLocalServiceUtil
 */
public class StrategyLocalServiceImpl extends StrategyLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.ibtrader.data.service.StrategyLocalServiceUtil} to access the strategy local service.
	 */
	
	private static final Log _log = LogFactoryUtil.getLog(StrategyLocalServiceImpl.class);
	
	
	protected void validate(String name) throws PortalException {
	    if (Validator.isNull(name)) {
	        throw new PortalException();
	    }
	}
	
    public List<Strategy> getStrategys(long groupId) {

	    return strategyPersistence.findByGroupId(groupId);
	}

	public List<Strategy> getStrategys(long groupId, int start, int end,OrderByComparator<Strategy> obc) {

	    return strategyPersistence.findByGroupId(groupId, start, end, obc);
	}

	public List<Strategy> getStrategys(long groupId, int start, int end) {

	    return strategyPersistence.findByGroupId(groupId, start, end);
	}

	public int getStrategysCount(long groupId) {

	    return strategyPersistence.countByGroupId(groupId);
	}
	
	
	public List<Strategy> findByActiveCompanyId(boolean _active, long companyid)
	{
			return getStrategyPersistence().findByActiveCompanyId(companyid, _active);
	}
	public List<Strategy> findByCompanyId(long companyid)
	{
			return getStrategyPersistence().findByCompanyId(companyid);
	}
	 @Indexable (type = IndexableType.DELETE)
	 @SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public Strategy deleteStrategy(long strategyId, ServiceContext serviceContext) throws PortalException, SystemException {

			Strategy strategy = getStrategy(strategyId);

		    resourceLocalService.deleteResource(serviceContext.getCompanyId(), Strategy.class.getName(),ResourceConstants.SCOPE_INDIVIDUAL, strategyId);

		    AssetEntry assetEntry = assetEntryLocalService.fetchEntry(Strategy.class.getName(), strategyId);

		    assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

		    assetEntryLocalService.deleteEntry(assetEntry);
		    
		    Indexer<Strategy> indexer = IndexerRegistryUtil.getIndexer(Strategy.class.getName());
			   
		    indexer.delete(strategy);
		    
		    strategy = deleteStrategy(strategyId);
		    
		  
		     return strategy;
		}

	
	 @Indexable (type = IndexableType.REINDEX)
	 @SystemEvent(type = SystemEventConstants.TYPE_DEFAULT)
	public Strategy editStrategy(Strategy strategy, ServiceContext serviceContext) throws PortalException   {
            
	
		 super.updateStrategy(strategy);
		//Strategy _strategy =  StrategyLocalServiceUtil.fetchStrategy(strategy.getStrategyID());
		 Strategy _strategy = getStrategy(strategy.getStrategyID());

		_strategy.setActive(strategy.getActive());
		_strategy.setDescription(strategy.getDescription());
		_strategy.setName(strategy.getName());
		_strategy.setType(strategy.getType());
		_strategy.setClassName(strategy.getClassName());
		_strategy.setGroupId(strategy.getGroupId());
		_strategy.setCompanyId(strategy.getCompanyId());		
		_strategy.setUserId(strategy.getUserId());
		
//        User user = userLocalService.getUser(userId);

		StrategyLocalServiceUtil.updateStrategy(_strategy);
		
    
		try {
			
			resourceLocalService.updateResources(serviceContext.getCompanyId(),
	                serviceContext.getScopeGroupId(), 
	                Strategy.class.getName(), strategy.getStrategyID(),
	                serviceContext.getGroupPermissions(),
	                serviceContext.getGuestPermissions());
		} 
		catch (PortalException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	   
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(strategy.getUserId(),
			   strategy.getGroupId(), strategy.getCreateDate(),
			   strategy.getModifiedDate(), Strategy.class.getName(),
			   _strategy.getPrimaryKey(), _strategy.getUuid(), 0,
               serviceContext.getAssetCategoryIds(),
               serviceContext.getAssetTagNames(), true, true, null, null, null, null,
               ContentTypes.TEXT_HTML, strategy.getName(), strategy.getDescription(), null, null,
               null, 0, 0, null);

	   assetLinkLocalService.updateLinks(strategy.getUserId(), assetEntry.getEntryId(),
               serviceContext.getAssetLinkEntryIds(),
               AssetLinkConstants.TYPE_RELATED);
	   
	   Indexer<Strategy> indexer = IndexerRegistryUtil.getIndexer(Strategy.class.getName());
	   
	   indexer.reindex(_strategy);
	 
	   return strategyPersistence.update(_strategy);
	}
	 
	
	 @Indexable (type = IndexableType.REINDEX)
	 @SystemEvent(type = SystemEventConstants.TYPE_DEFAULT)	 
	public Strategy addStrategy(Strategy strategy, ServiceContext serviceContext) throws PortalException   {
            
		 Date now = new Date();
		
		Strategy _strategy =  strategyPersistence.create(CounterLocalServiceUtil.increment(Strategy.class.getName())); 

		User _user = UserLocalServiceUtil.getUser(strategy.getUserId());
		
		
		_strategy.setUuid(serviceContext.getUuid());
		_strategy.setActive(strategy.getActive());
		_strategy.setDescription(strategy.getDescription());
		_strategy.setName(strategy.getName());
		_strategy.setType(strategy.getType());
		_strategy.setClassName(strategy.getClassName());
		_strategy.setCreateDate(serviceContext.getCreateDate(now));
		_strategy.setModifiedDate(serviceContext.getModifiedDate(now));
		_strategy.setGroupId(strategy.getGroupId());
		_strategy.setCompanyId(strategy.getCompanyId());		
		_strategy.setUserId(strategy.getUserId());
		_strategy.setStatus(strategy.getStatus());
		_strategy.setStatusByUserId(strategy.getUserId());
		_strategy.setStatusByUserName(_user.getFullName());
		_strategy.setStatusDate(serviceContext.getModifiedDate(null));
		
		_strategy.setExpandoBridgeAttributes(serviceContext);

		strategyPersistence.update(_strategy);
		
//        User user = userLocalService.getUser(userId);
    
		try {
		
			resourceLocalService.addResources(_strategy.getCompanyId(), _strategy.getGroupId(), _strategy.getUserId(),
					Strategy.class.getName(), _strategy.getStrategyID(), false, true, true);
		} 
		catch (PortalException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	
   
	   Indexer<Strategy> indexer = IndexerRegistryUtil.getIndexer(Strategy.class.getName());
	   
	   indexer.reindex(_strategy);
		   
	    /* assetEntry =  assetEntryLocalService.updateEntry(serviceContext.getUserId(),
	        		_strategy.getGroupId(), Strategy.class.getName(),
	        		_strategy.getStrategyID(), null, null);

		*/
	    AssetEntry assetEntry = assetEntryLocalService.updateEntry(_strategy.getUserId(),
				_strategy.getGroupId(), _strategy.getCreateDate(),
				_strategy.getModifiedDate(), Strategy.class.getName(),
			   _strategy.getPrimaryKey(), _strategy.getUuid(), 0,
               serviceContext.getAssetCategoryIds(),
               serviceContext.getAssetTagNames(), true, true, _strategy.getCreateDate(), null, _strategy.getCreateDate(), null, ContentTypes.TEXT_HTML,
               _strategy.getName(), _strategy.getDescription(),  _strategy.getDescription(), null, null, 0, 0, null);

	   assetLinkLocalService.updateLinks(_strategy.getUserId(), assetEntry.getEntryId(),
               serviceContext.getAssetLinkEntryIds(),
               AssetLinkConstants.TYPE_RELATED);
	   
	   
	   
	   WorkflowHandlerRegistryUtil.startWorkflowInstance(_strategy.getCompanyId(), 
			   _strategy.getGroupId(), _strategy.getUserId(), Strategy.class.getName(), 
			   _strategy.getStrategyID(), _strategy, serviceContext);
	   
	   return _strategy;
	   
	   
}
	@Override
	public Strategy updateStrategy(Strategy strategy, ServiceContext serviceContext)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return editStrategy(strategy, serviceContext);
	}
	
	
	public Strategy updateStatus(long userId, long strategyId, int status,ServiceContext serviceContext) throws PortalException,SystemException {

		    User user = userLocalService.getUser(userId);
		    _log.info("strategyId:" + strategyId + ",status:" + status);
		    Strategy _strategy = getStrategy(strategyId);
		    		    

		    _strategy.setStatus(status);
		    _strategy.setStatusByUserId(userId);
		    _strategy.setStatusByUserName(user.getFullName());
		    _strategy.setStatusDate(new Date());

		    if (status == WorkflowConstants.STATUS_APPROVED) {

		        assetEntryLocalService.updateVisible(Strategy.class.getName(),strategyId, true);

		     } else {

		        assetEntryLocalService.updateVisible(Strategy.class.getName(),strategyId, false);
		     }
		     
		    
		   return  strategyPersistence.update(_strategy);
	
	}
	@Override
	public List<Strategy> findBYGroupIDStatus(long groupId, int status) throws SystemException {
		// TODO Auto-generated method stub
		return getStrategyPersistence().findByG_S(groupId, status);
	}
	
}