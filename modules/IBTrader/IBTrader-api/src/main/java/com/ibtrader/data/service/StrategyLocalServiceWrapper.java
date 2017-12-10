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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StrategyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see StrategyLocalService
 * @generated
 */
@ProviderType
public class StrategyLocalServiceWrapper implements StrategyLocalService,
	ServiceWrapper<StrategyLocalService> {
	public StrategyLocalServiceWrapper(
		StrategyLocalService strategyLocalService) {
		_strategyLocalService = strategyLocalService;
	}

	/**
	* Adds the strategy to the database. Also notifies the appropriate model listeners.
	*
	* @param strategy the strategy
	* @return the strategy that was added
	*/
	@Override
	public com.ibtrader.data.model.Strategy addStrategy(
		com.ibtrader.data.model.Strategy strategy) {
		return _strategyLocalService.addStrategy(strategy);
	}

	@Override
	public com.ibtrader.data.model.Strategy addStrategy(
		com.ibtrader.data.model.Strategy strategy,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyLocalService.addStrategy(strategy, serviceContext);
	}

	/**
	* Creates a new strategy with the primary key. Does not add the strategy to the database.
	*
	* @param strategyID the primary key for the new strategy
	* @return the new strategy
	*/
	@Override
	public com.ibtrader.data.model.Strategy createStrategy(long strategyID) {
		return _strategyLocalService.createStrategy(strategyID);
	}

	/**
	* Deletes the strategy from the database. Also notifies the appropriate model listeners.
	*
	* @param strategy the strategy
	* @return the strategy that was removed
	*/
	@Override
	public com.ibtrader.data.model.Strategy deleteStrategy(
		com.ibtrader.data.model.Strategy strategy) {
		return _strategyLocalService.deleteStrategy(strategy);
	}

	/**
	* Deletes the strategy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy that was removed
	* @throws PortalException if a strategy with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.Strategy deleteStrategy(long strategyID)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyLocalService.deleteStrategy(strategyID);
	}

	@Override
	public com.ibtrader.data.model.Strategy deleteStrategy(long strategyId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _strategyLocalService.deleteStrategy(strategyId, serviceContext);
	}

	@Override
	public com.ibtrader.data.model.Strategy editStrategy(
		com.ibtrader.data.model.Strategy strategy,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyLocalService.editStrategy(strategy, serviceContext);
	}

	@Override
	public com.ibtrader.data.model.Strategy fetchStrategy(long strategyID) {
		return _strategyLocalService.fetchStrategy(strategyID);
	}

	/**
	* Returns the strategy matching the UUID and group.
	*
	* @param uuid the strategy's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	@Override
	public com.ibtrader.data.model.Strategy fetchStrategyByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _strategyLocalService.fetchStrategyByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the strategy with the primary key.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy
	* @throws PortalException if a strategy with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.Strategy getStrategy(long strategyID)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyLocalService.getStrategy(strategyID);
	}

	/**
	* Returns the strategy matching the UUID and group.
	*
	* @param uuid the strategy's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy
	* @throws PortalException if a matching strategy could not be found
	*/
	@Override
	public com.ibtrader.data.model.Strategy getStrategyByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyLocalService.getStrategyByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.ibtrader.data.model.Strategy updateStatus(long userId,
		long strategyId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _strategyLocalService.updateStatus(userId, strategyId, status,
			serviceContext);
	}

	/**
	* Updates the strategy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param strategy the strategy
	* @return the strategy that was updated
	*/
	@Override
	public com.ibtrader.data.model.Strategy updateStrategy(
		com.ibtrader.data.model.Strategy strategy) {
		return _strategyLocalService.updateStrategy(strategy);
	}

	@Override
	public com.ibtrader.data.model.Strategy updateStrategy(
		com.ibtrader.data.model.Strategy strategy,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _strategyLocalService.updateStrategy(strategy, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _strategyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _strategyLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _strategyLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _strategyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of strategies.
	*
	* @return the number of strategies
	*/
	@Override
	public int getStrategiesCount() {
		return _strategyLocalService.getStrategiesCount();
	}

	@Override
	public int getStrategysCount(long groupId) {
		return _strategyLocalService.getStrategysCount(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _strategyLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _strategyLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _strategyLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _strategyLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Strategy> findBYGroupIDStatus(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _strategyLocalService.findBYGroupIDStatus(groupId, status);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Strategy> findByActiveCompanyId(
		boolean _active, long companyid) {
		return _strategyLocalService.findByActiveCompanyId(_active, companyid);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Strategy> findByCompanyId(
		long companyid) {
		return _strategyLocalService.findByCompanyId(companyid);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Strategy> findStrategies(
		long shareId, long companyId, long groupId) {
		return _strategyLocalService.findStrategies(shareId, companyId, groupId);
	}

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
	@Override
	public java.util.List<com.ibtrader.data.model.Strategy> getStrategies(
		int start, int end) {
		return _strategyLocalService.getStrategies(start, end);
	}

	/**
	* Returns all the strategies matching the UUID and company.
	*
	* @param uuid the UUID of the strategies
	* @param companyId the primary key of the company
	* @return the matching strategies, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.Strategy> getStrategiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _strategyLocalService.getStrategiesByUuidAndCompanyId(uuid,
			companyId);
	}

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
	@Override
	public java.util.List<com.ibtrader.data.model.Strategy> getStrategiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Strategy> orderByComparator) {
		return _strategyLocalService.getStrategiesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Strategy> getStrategys(
		long groupId) {
		return _strategyLocalService.getStrategys(groupId);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Strategy> getStrategys(
		long groupId, int start, int end) {
		return _strategyLocalService.getStrategys(groupId, start, end);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Strategy> getStrategys(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Strategy> obc) {
		return _strategyLocalService.getStrategys(groupId, start, end, obc);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _strategyLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _strategyLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public StrategyLocalService getWrappedService() {
		return _strategyLocalService;
	}

	@Override
	public void setWrappedService(StrategyLocalService strategyLocalService) {
		_strategyLocalService = strategyLocalService;
	}

	private StrategyLocalService _strategyLocalService;
}