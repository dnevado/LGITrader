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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Strategy. This utility wraps
 * {@link com.ibtrader.data.service.impl.StrategyLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see StrategyLocalService
 * @see com.ibtrader.data.service.base.StrategyLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.StrategyLocalServiceImpl
 * @generated
 */
@ProviderType
public class StrategyLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ibtrader.data.service.impl.StrategyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the strategy to the database. Also notifies the appropriate model listeners.
	*
	* @param strategy the strategy
	* @return the strategy that was added
	*/
	public static com.ibtrader.data.model.Strategy addStrategy(
		com.ibtrader.data.model.Strategy strategy) {
		return getService().addStrategy(strategy);
	}

	public static com.ibtrader.data.model.Strategy addStrategy(
		com.ibtrader.data.model.Strategy strategy,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addStrategy(strategy, serviceContext);
	}

	/**
	* Creates a new strategy with the primary key. Does not add the strategy to the database.
	*
	* @param strategyID the primary key for the new strategy
	* @return the new strategy
	*/
	public static com.ibtrader.data.model.Strategy createStrategy(
		long strategyID) {
		return getService().createStrategy(strategyID);
	}

	/**
	* Deletes the strategy from the database. Also notifies the appropriate model listeners.
	*
	* @param strategy the strategy
	* @return the strategy that was removed
	*/
	public static com.ibtrader.data.model.Strategy deleteStrategy(
		com.ibtrader.data.model.Strategy strategy) {
		return getService().deleteStrategy(strategy);
	}

	/**
	* Deletes the strategy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy that was removed
	* @throws PortalException if a strategy with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Strategy deleteStrategy(
		long strategyID)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteStrategy(strategyID);
	}

	public static com.ibtrader.data.model.Strategy deleteStrategy(
		long strategyId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteStrategy(strategyId, serviceContext);
	}

	public static com.ibtrader.data.model.Strategy editStrategy(
		com.ibtrader.data.model.Strategy strategy,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().editStrategy(strategy, serviceContext);
	}

	public static com.ibtrader.data.model.Strategy fetchStrategy(
		long strategyID) {
		return getService().fetchStrategy(strategyID);
	}

	/**
	* Returns the strategy matching the UUID and group.
	*
	* @param uuid the strategy's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static com.ibtrader.data.model.Strategy fetchStrategyByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchStrategyByUuidAndGroupId(uuid, groupId);
	}

	public static com.ibtrader.data.model.Strategy getCompanyClassName(
		long companyId, java.lang.String strategyClassName) {
		return getService().getCompanyClassName(companyId, strategyClassName);
	}

	/**
	* Returns the strategy with the primary key.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy
	* @throws PortalException if a strategy with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Strategy getStrategy(long strategyID)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStrategy(strategyID);
	}

	/**
	* Returns the strategy matching the UUID and group.
	*
	* @param uuid the strategy's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy
	* @throws PortalException if a matching strategy could not be found
	*/
	public static com.ibtrader.data.model.Strategy getStrategyByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStrategyByUuidAndGroupId(uuid, groupId);
	}

	public static com.ibtrader.data.model.Strategy updateStatus(long userId,
		long strategyId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, strategyId, status, serviceContext);
	}

	/**
	* Updates the strategy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param strategy the strategy
	* @return the strategy that was updated
	*/
	public static com.ibtrader.data.model.Strategy updateStrategy(
		com.ibtrader.data.model.Strategy strategy) {
		return getService().updateStrategy(strategy);
	}

	public static com.ibtrader.data.model.Strategy updateStrategy(
		com.ibtrader.data.model.Strategy strategy,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateStrategy(strategy, serviceContext);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of strategies.
	*
	* @return the number of strategies
	*/
	public static int getStrategiesCount() {
		return getService().getStrategiesCount();
	}

	public static int getStrategysCount(long groupId) {
		return getService().getStrategysCount(groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<com.ibtrader.data.model.Strategy> findBYGroupIDStatus(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().findBYGroupIDStatus(groupId, status);
	}

	public static java.util.List<com.ibtrader.data.model.Strategy> findByActiveCompanyId(
		boolean _active, long companyid) {
		return getService().findByActiveCompanyId(_active, companyid);
	}

	public static java.util.List<com.ibtrader.data.model.Strategy> findByCompanyId(
		long companyid) {
		return getService().findByCompanyId(companyid);
	}

	public static java.util.List<com.ibtrader.data.model.Strategy> findStrategies(
		long shareId, long companyId, long groupId) {
		return getService().findStrategies(shareId, companyId, groupId);
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
	public static java.util.List<com.ibtrader.data.model.Strategy> getStrategies(
		int start, int end) {
		return getService().getStrategies(start, end);
	}

	/**
	* Returns all the strategies matching the UUID and company.
	*
	* @param uuid the UUID of the strategies
	* @param companyId the primary key of the company
	* @return the matching strategies, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.Strategy> getStrategiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getStrategiesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<com.ibtrader.data.model.Strategy> getStrategiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Strategy> orderByComparator) {
		return getService()
				   .getStrategiesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	public static java.util.List<com.ibtrader.data.model.Strategy> getStrategys(
		long groupId) {
		return getService().getStrategys(groupId);
	}

	public static java.util.List<com.ibtrader.data.model.Strategy> getStrategys(
		long groupId, int start, int end) {
		return getService().getStrategys(groupId, start, end);
	}

	public static java.util.List<com.ibtrader.data.model.Strategy> getStrategys(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Strategy> obc) {
		return getService().getStrategys(groupId, start, end, obc);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static StrategyLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StrategyLocalService, StrategyLocalService> _serviceTracker =
		ServiceTrackerFactory.open(StrategyLocalService.class);
}