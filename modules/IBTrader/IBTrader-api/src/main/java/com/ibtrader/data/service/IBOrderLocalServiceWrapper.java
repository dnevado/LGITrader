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
 * Provides a wrapper for {@link IBOrderLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see IBOrderLocalService
 * @generated
 */
@ProviderType
public class IBOrderLocalServiceWrapper implements IBOrderLocalService,
	ServiceWrapper<IBOrderLocalService> {
	public IBOrderLocalServiceWrapper(IBOrderLocalService ibOrderLocalService) {
		_ibOrderLocalService = ibOrderLocalService;
	}

	/**
	* Adds the i b order to the database. Also notifies the appropriate model listeners.
	*
	* @param ibOrder the i b order
	* @return the i b order that was added
	*/
	@Override
	public com.ibtrader.data.model.IBOrder addIBOrder(
		com.ibtrader.data.model.IBOrder ibOrder) {
		return _ibOrderLocalService.addIBOrder(ibOrder);
	}

	/**
	* Creates a new i b order with the primary key. Does not add the i b order to the database.
	*
	* @param ordersId the primary key for the new i b order
	* @return the new i b order
	*/
	@Override
	public com.ibtrader.data.model.IBOrder createIBOrder(long ordersId) {
		return _ibOrderLocalService.createIBOrder(ordersId);
	}

	/**
	* Deletes the i b order from the database. Also notifies the appropriate model listeners.
	*
	* @param ibOrder the i b order
	* @return the i b order that was removed
	*/
	@Override
	public com.ibtrader.data.model.IBOrder deleteIBOrder(
		com.ibtrader.data.model.IBOrder ibOrder) {
		return _ibOrderLocalService.deleteIBOrder(ibOrder);
	}

	/**
	* Deletes the i b order with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ordersId the primary key of the i b order
	* @return the i b order that was removed
	* @throws PortalException if a i b order with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.IBOrder deleteIBOrder(long ordersId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ibOrderLocalService.deleteIBOrder(ordersId);
	}

	@Override
	public com.ibtrader.data.model.IBOrder fetchIBOrder(long ordersId) {
		return _ibOrderLocalService.fetchIBOrder(ordersId);
	}

	/**
	* Returns the i b order matching the UUID and group.
	*
	* @param uuid the i b order's UUID
	* @param groupId the primary key of the group
	* @return the matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	@Override
	public com.ibtrader.data.model.IBOrder fetchIBOrderByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _ibOrderLocalService.fetchIBOrderByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.ibtrader.data.model.IBOrder findByShareIdCompanyGroup(
		long shareId, long companyId, long groupId) {
		return _ibOrderLocalService.findByShareIdCompanyGroup(shareId,
			companyId, groupId);
	}

	/**
	* Returns the i b order with the primary key.
	*
	* @param ordersId the primary key of the i b order
	* @return the i b order
	* @throws PortalException if a i b order with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.IBOrder getIBOrder(long ordersId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ibOrderLocalService.getIBOrder(ordersId);
	}

	/**
	* Returns the i b order matching the UUID and group.
	*
	* @param uuid the i b order's UUID
	* @param groupId the primary key of the group
	* @return the matching i b order
	* @throws PortalException if a matching i b order could not be found
	*/
	@Override
	public com.ibtrader.data.model.IBOrder getIBOrderByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ibOrderLocalService.getIBOrderByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the i b order in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ibOrder the i b order
	* @return the i b order that was updated
	*/
	@Override
	public com.ibtrader.data.model.IBOrder updateIBOrder(
		com.ibtrader.data.model.IBOrder ibOrder) {
		return _ibOrderLocalService.updateIBOrder(ibOrder);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ibOrderLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ibOrderLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ibOrderLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ibOrderLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ibOrderLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of i b orders.
	*
	* @return the number of i b orders
	*/
	@Override
	public int getIBOrdersCount() {
		return _ibOrderLocalService.getIBOrdersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ibOrderLocalService.getOSGiServiceIdentifier();
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
		return _ibOrderLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _ibOrderLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _ibOrderLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the i b orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @return the range of i b orders
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.IBOrder> getIBOrders(
		int start, int end) {
		return _ibOrderLocalService.getIBOrders(start, end);
	}

	/**
	* Returns all the i b orders matching the UUID and company.
	*
	* @param uuid the UUID of the i b orders
	* @param companyId the primary key of the company
	* @return the matching i b orders, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.IBOrder> getIBOrdersByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _ibOrderLocalService.getIBOrdersByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of i b orders matching the UUID and company.
	*
	* @param uuid the UUID of the i b orders
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching i b orders, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.IBOrder> getIBOrdersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.IBOrder> orderByComparator) {
		return _ibOrderLocalService.getIBOrdersByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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
		return _ibOrderLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ibOrderLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public IBOrderLocalService getWrappedService() {
		return _ibOrderLocalService;
	}

	@Override
	public void setWrappedService(IBOrderLocalService ibOrderLocalService) {
		_ibOrderLocalService = ibOrderLocalService;
	}

	private IBOrderLocalService _ibOrderLocalService;
}