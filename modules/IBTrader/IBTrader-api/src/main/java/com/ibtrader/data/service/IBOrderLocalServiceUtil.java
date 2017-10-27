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
 * Provides the local service utility for IBOrder. This utility wraps
 * {@link com.ibtrader.data.service.impl.IBOrderLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see IBOrderLocalService
 * @see com.ibtrader.data.service.base.IBOrderLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.IBOrderLocalServiceImpl
 * @generated
 */
@ProviderType
public class IBOrderLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ibtrader.data.service.impl.IBOrderLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ib order to the database. Also notifies the appropriate model listeners.
	*
	* @param ibOrder the ib order
	* @return the ib order that was added
	*/
	public static com.ibtrader.data.model.IBOrder addIBOrder(
		com.ibtrader.data.model.IBOrder ibOrder) {
		return getService().addIBOrder(ibOrder);
	}

	/**
	* Creates a new ib order with the primary key. Does not add the ib order to the database.
	*
	* @param ordersId the primary key for the new ib order
	* @return the new ib order
	*/
	public static com.ibtrader.data.model.IBOrder createIBOrder(long ordersId) {
		return getService().createIBOrder(ordersId);
	}

	/**
	* Deletes the ib order from the database. Also notifies the appropriate model listeners.
	*
	* @param ibOrder the ib order
	* @return the ib order that was removed
	*/
	public static com.ibtrader.data.model.IBOrder deleteIBOrder(
		com.ibtrader.data.model.IBOrder ibOrder) {
		return getService().deleteIBOrder(ibOrder);
	}

	/**
	* Deletes the ib order with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ordersId the primary key of the ib order
	* @return the ib order that was removed
	* @throws PortalException if a ib order with the primary key could not be found
	*/
	public static com.ibtrader.data.model.IBOrder deleteIBOrder(long ordersId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteIBOrder(ordersId);
	}

	public static com.ibtrader.data.model.IBOrder fetchIBOrder(long ordersId) {
		return getService().fetchIBOrder(ordersId);
	}

	/**
	* Returns the ib order matching the UUID and group.
	*
	* @param uuid the ib order's UUID
	* @param groupId the primary key of the group
	* @return the matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static com.ibtrader.data.model.IBOrder fetchIBOrderByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchIBOrderByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the ib order with the primary key.
	*
	* @param ordersId the primary key of the ib order
	* @return the ib order
	* @throws PortalException if a ib order with the primary key could not be found
	*/
	public static com.ibtrader.data.model.IBOrder getIBOrder(long ordersId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getIBOrder(ordersId);
	}

	/**
	* Returns the ib order matching the UUID and group.
	*
	* @param uuid the ib order's UUID
	* @param groupId the primary key of the group
	* @return the matching ib order
	* @throws PortalException if a matching ib order could not be found
	*/
	public static com.ibtrader.data.model.IBOrder getIBOrderByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getIBOrderByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the ib order in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ibOrder the ib order
	* @return the ib order that was updated
	*/
	public static com.ibtrader.data.model.IBOrder updateIBOrder(
		com.ibtrader.data.model.IBOrder ibOrder) {
		return getService().updateIBOrder(ibOrder);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Returns the number of ib orders.
	*
	* @return the number of ib orders
	*/
	public static int getIBOrdersCount() {
		return getService().getIBOrdersCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns a range of all the ib orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @return the range of ib orders
	*/
	public static java.util.List<com.ibtrader.data.model.IBOrder> getIBOrders(
		int start, int end) {
		return getService().getIBOrders(start, end);
	}

	/**
	* Returns all the ib orders matching the UUID and company.
	*
	* @param uuid the UUID of the ib orders
	* @param companyId the primary key of the company
	* @return the matching ib orders, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.IBOrder> getIBOrdersByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getIBOrdersByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of ib orders matching the UUID and company.
	*
	* @param uuid the UUID of the ib orders
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching ib orders, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.IBOrder> getIBOrdersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.IBOrder> orderByComparator) {
		return getService()
				   .getIBOrdersByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
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

	public static IBOrderLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IBOrderLocalService, IBOrderLocalService> _serviceTracker =
		ServiceTrackerFactory.open(IBOrderLocalService.class);
}