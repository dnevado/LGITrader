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
 * Provides a wrapper for {@link MarkeShareLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MarkeShareLocalService
 * @generated
 */
@ProviderType
public class MarkeShareLocalServiceWrapper implements MarkeShareLocalService,
	ServiceWrapper<MarkeShareLocalService> {
	public MarkeShareLocalServiceWrapper(
		MarkeShareLocalService markeShareLocalService) {
		_markeShareLocalService = markeShareLocalService;
	}

	/**
	* Adds the marke share to the database. Also notifies the appropriate model listeners.
	*
	* @param markeShare the marke share
	* @return the marke share that was added
	*/
	@Override
	public com.ibtrader.data.model.MarkeShare addMarkeShare(
		com.ibtrader.data.model.MarkeShare markeShare) {
		return _markeShareLocalService.addMarkeShare(markeShare);
	}

	/**
	* Creates a new marke share with the primary key. Does not add the marke share to the database.
	*
	* @param marketshareId the primary key for the new marke share
	* @return the new marke share
	*/
	@Override
	public com.ibtrader.data.model.MarkeShare createMarkeShare(
		long marketshareId) {
		return _markeShareLocalService.createMarkeShare(marketshareId);
	}

	/**
	* Deletes the marke share from the database. Also notifies the appropriate model listeners.
	*
	* @param markeShare the marke share
	* @return the marke share that was removed
	*/
	@Override
	public com.ibtrader.data.model.MarkeShare deleteMarkeShare(
		com.ibtrader.data.model.MarkeShare markeShare) {
		return _markeShareLocalService.deleteMarkeShare(markeShare);
	}

	/**
	* Deletes the marke share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param marketshareId the primary key of the marke share
	* @return the marke share that was removed
	* @throws PortalException if a marke share with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.MarkeShare deleteMarkeShare(
		long marketshareId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _markeShareLocalService.deleteMarkeShare(marketshareId);
	}

	@Override
	public com.ibtrader.data.model.MarkeShare fetchMarkeShare(
		long marketshareId) {
		return _markeShareLocalService.fetchMarkeShare(marketshareId);
	}

	/**
	* Returns the marke share matching the UUID and group.
	*
	* @param uuid the marke share's UUID
	* @param groupId the primary key of the group
	* @return the matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	@Override
	public com.ibtrader.data.model.MarkeShare fetchMarkeShareByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _markeShareLocalService.fetchMarkeShareByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the marke share with the primary key.
	*
	* @param marketshareId the primary key of the marke share
	* @return the marke share
	* @throws PortalException if a marke share with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.MarkeShare getMarkeShare(long marketshareId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _markeShareLocalService.getMarkeShare(marketshareId);
	}

	/**
	* Returns the marke share matching the UUID and group.
	*
	* @param uuid the marke share's UUID
	* @param groupId the primary key of the group
	* @return the matching marke share
	* @throws PortalException if a matching marke share could not be found
	*/
	@Override
	public com.ibtrader.data.model.MarkeShare getMarkeShareByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _markeShareLocalService.getMarkeShareByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the marke share in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param markeShare the marke share
	* @return the marke share that was updated
	*/
	@Override
	public com.ibtrader.data.model.MarkeShare updateMarkeShare(
		com.ibtrader.data.model.MarkeShare markeShare) {
		return _markeShareLocalService.updateMarkeShare(markeShare);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _markeShareLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _markeShareLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _markeShareLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _markeShareLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _markeShareLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of marke shares.
	*
	* @return the number of marke shares
	*/
	@Override
	public int getMarkeSharesCount() {
		return _markeShareLocalService.getMarkeSharesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _markeShareLocalService.getOSGiServiceIdentifier();
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
		return _markeShareLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _markeShareLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _markeShareLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the marke shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @return the range of marke shares
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.MarkeShare> getMarkeShares(
		int start, int end) {
		return _markeShareLocalService.getMarkeShares(start, end);
	}

	/**
	* Returns all the marke shares matching the UUID and company.
	*
	* @param uuid the UUID of the marke shares
	* @param companyId the primary key of the company
	* @return the matching marke shares, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.MarkeShare> getMarkeSharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _markeShareLocalService.getMarkeSharesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of marke shares matching the UUID and company.
	*
	* @param uuid the UUID of the marke shares
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching marke shares, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.MarkeShare> getMarkeSharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.MarkeShare> orderByComparator) {
		return _markeShareLocalService.getMarkeSharesByUuidAndCompanyId(uuid,
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
		return _markeShareLocalService.dynamicQueryCount(dynamicQuery);
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
		return _markeShareLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public MarkeShareLocalService getWrappedService() {
		return _markeShareLocalService;
	}

	@Override
	public void setWrappedService(MarkeShareLocalService markeShareLocalService) {
		_markeShareLocalService = markeShareLocalService;
	}

	private MarkeShareLocalService _markeShareLocalService;
}