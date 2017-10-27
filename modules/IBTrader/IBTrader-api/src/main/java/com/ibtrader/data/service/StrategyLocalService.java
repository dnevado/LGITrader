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

package com.ibtrader.data.service;

import aQute.bnd.annotation.ProviderType;

import com.ibtrader.data.model.Strategy;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for Strategy. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see StrategyLocalServiceUtil
 * @see com.ibtrader.data.service.base.StrategyLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.StrategyLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface StrategyLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StrategyLocalServiceUtil} to access the strategy local service. Add custom service methods to {@link com.ibtrader.data.service.impl.StrategyLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the strategy to the database. Also notifies the appropriate model listeners.
	*
	* @param strategy the strategy
	* @return the strategy that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Strategy addStrategy(Strategy strategy);

	@Indexable(type = IndexableType.REINDEX)
	@SystemEvent(type = SystemEventConstants.TYPE_DEFAULT)
	public Strategy addStrategy(Strategy strategy, ServiceContext serviceContext)
		throws PortalException;

	/**
	* Creates a new strategy with the primary key. Does not add the strategy to the database.
	*
	* @param strategyID the primary key for the new strategy
	* @return the new strategy
	*/
	public Strategy createStrategy(long strategyID);

	/**
	* Deletes the strategy from the database. Also notifies the appropriate model listeners.
	*
	* @param strategy the strategy
	* @return the strategy that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Strategy deleteStrategy(Strategy strategy);

	/**
	* Deletes the strategy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy that was removed
	* @throws PortalException if a strategy with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Strategy deleteStrategy(long strategyID) throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public Strategy deleteStrategy(long strategyId,
		ServiceContext serviceContext) throws PortalException, SystemException;

	@Indexable(type = IndexableType.REINDEX)
	@SystemEvent(type = SystemEventConstants.TYPE_DEFAULT)
	public Strategy editStrategy(Strategy strategy,
		ServiceContext serviceContext) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Strategy fetchStrategy(long strategyID);

	/**
	* Returns the strategy matching the UUID and group.
	*
	* @param uuid the strategy's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Strategy fetchStrategyByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the strategy with the primary key.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy
	* @throws PortalException if a strategy with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Strategy getStrategy(long strategyID) throws PortalException;

	/**
	* Returns the strategy matching the UUID and group.
	*
	* @param uuid the strategy's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy
	* @throws PortalException if a matching strategy could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Strategy getStrategyByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	public Strategy updateStatus(long userId, long strategyId, int status,
		ServiceContext serviceContext) throws PortalException, SystemException;

	/**
	* Updates the strategy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param strategy the strategy
	* @return the strategy that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Strategy updateStrategy(Strategy strategy);

	public Strategy updateStrategy(Strategy strategy,
		ServiceContext serviceContext) throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of strategies.
	*
	* @return the number of strategies
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getStrategiesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	public List<Strategy> findBYGroupIDStatus(long groupId, int status)
		throws SystemException;

	public List<Strategy> findByActiveCompanyId(boolean _active, long companyid);

	public List<Strategy> findByCompanyId(long companyid);

	/**
	* Returns a range of all the strategies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @return the range of strategies
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Strategy> getStrategies(int start, int end);

	/**
	* Returns all the strategies matching the UUID and company.
	*
	* @param uuid the UUID of the strategies
	* @param companyId the primary key of the company
	* @return the matching strategies, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Strategy> getStrategiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of strategies matching the UUID and company.
	*
	* @param uuid the UUID of the strategies
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching strategies, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Strategy> getStrategiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);
}