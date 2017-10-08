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
 * Provides a wrapper for {@link OrderLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OrderLocalService
 * @generated
 */
@ProviderType
public class OrderLocalServiceWrapper implements OrderLocalService,
	ServiceWrapper<OrderLocalService> {
	public OrderLocalServiceWrapper(OrderLocalService orderLocalService) {
		_orderLocalService = orderLocalService;
	}

	/**
	* Adds the order to the database. Also notifies the appropriate model listeners.
	*
	* @param order the order
	* @return the order that was added
	*/
	@Override
	public com.ibtrader.data.model.Order addOrder(
		com.ibtrader.data.model.Order order) {
		return _orderLocalService.addOrder(order);
	}

	/**
	* Creates a new order with the primary key. Does not add the order to the database.
	*
	* @param ordersId the primary key for the new order
	* @return the new order
	*/
	@Override
	public com.ibtrader.data.model.Order createOrder(long ordersId) {
		return _orderLocalService.createOrder(ordersId);
	}

	/**
	* Deletes the order from the database. Also notifies the appropriate model listeners.
	*
	* @param order the order
	* @return the order that was removed
	*/
	@Override
	public com.ibtrader.data.model.Order deleteOrder(
		com.ibtrader.data.model.Order order) {
		return _orderLocalService.deleteOrder(order);
	}

	/**
	* Deletes the order with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ordersId the primary key of the order
	* @return the order that was removed
	* @throws PortalException if a order with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.Order deleteOrder(long ordersId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderLocalService.deleteOrder(ordersId);
	}

	@Override
	public com.ibtrader.data.model.Order fetchOrder(long ordersId) {
		return _orderLocalService.fetchOrder(ordersId);
	}

	/**
	* Returns the order matching the UUID and group.
	*
	* @param uuid the order's UUID
	* @param groupId the primary key of the group
	* @return the matching order, or <code>null</code> if a matching order could not be found
	*/
	@Override
	public com.ibtrader.data.model.Order fetchOrderByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _orderLocalService.fetchOrderByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the order with the primary key.
	*
	* @param ordersId the primary key of the order
	* @return the order
	* @throws PortalException if a order with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.Order getOrder(long ordersId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderLocalService.getOrder(ordersId);
	}

	/**
	* Returns the order matching the UUID and group.
	*
	* @param uuid the order's UUID
	* @param groupId the primary key of the group
	* @return the matching order
	* @throws PortalException if a matching order could not be found
	*/
	@Override
	public com.ibtrader.data.model.Order getOrderByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderLocalService.getOrderByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the order in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param order the order
	* @return the order that was updated
	*/
	@Override
	public com.ibtrader.data.model.Order updateOrder(
		com.ibtrader.data.model.Order order) {
		return _orderLocalService.updateOrder(order);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _orderLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _orderLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _orderLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of orders.
	*
	* @return the number of orders
	*/
	@Override
	public int getOrdersCount() {
		return _orderLocalService.getOrdersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _orderLocalService.getOSGiServiceIdentifier();
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
		return _orderLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.OrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _orderLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.OrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _orderLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.OrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of orders
	* @param end the upper bound of the range of orders (not inclusive)
	* @return the range of orders
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.Order> getOrders(int start,
		int end) {
		return _orderLocalService.getOrders(start, end);
	}

	/**
	* Returns all the orders matching the UUID and company.
	*
	* @param uuid the UUID of the orders
	* @param companyId the primary key of the company
	* @return the matching orders, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.Order> getOrdersByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _orderLocalService.getOrdersByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of orders matching the UUID and company.
	*
	* @param uuid the UUID of the orders
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of orders
	* @param end the upper bound of the range of orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching orders, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.Order> getOrdersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Order> orderByComparator) {
		return _orderLocalService.getOrdersByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
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
		return _orderLocalService.dynamicQueryCount(dynamicQuery);
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
		return _orderLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public OrderLocalService getWrappedService() {
		return _orderLocalService;
	}

	@Override
	public void setWrappedService(OrderLocalService orderLocalService) {
		_orderLocalService = orderLocalService;
	}

	private OrderLocalService _orderLocalService;
}