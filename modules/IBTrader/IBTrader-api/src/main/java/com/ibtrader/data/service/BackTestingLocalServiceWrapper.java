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
 * Provides a wrapper for {@link BackTestingLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see BackTestingLocalService
 * @generated
 */
@ProviderType
public class BackTestingLocalServiceWrapper implements BackTestingLocalService,
	ServiceWrapper<BackTestingLocalService> {
	public BackTestingLocalServiceWrapper(
		BackTestingLocalService backTestingLocalService) {
		_backTestingLocalService = backTestingLocalService;
	}

	@Override
	public boolean removeBackTestingId(long companyId, long groupId,
		long backtestingId) {
		return _backTestingLocalService.removeBackTestingId(companyId, groupId,
			backtestingId);
	}

	/**
	* Adds the back testing to the database. Also notifies the appropriate model listeners.
	*
	* @param backTesting the back testing
	* @return the back testing that was added
	*/
	@Override
	public com.ibtrader.data.model.BackTesting addBackTesting(
		com.ibtrader.data.model.BackTesting backTesting) {
		return _backTestingLocalService.addBackTesting(backTesting);
	}

	/**
	* Creates a new back testing with the primary key. Does not add the back testing to the database.
	*
	* @param backTId the primary key for the new back testing
	* @return the new back testing
	*/
	@Override
	public com.ibtrader.data.model.BackTesting createBackTesting(long backTId) {
		return _backTestingLocalService.createBackTesting(backTId);
	}

	/**
	* Deletes the back testing from the database. Also notifies the appropriate model listeners.
	*
	* @param backTesting the back testing
	* @return the back testing that was removed
	*/
	@Override
	public com.ibtrader.data.model.BackTesting deleteBackTesting(
		com.ibtrader.data.model.BackTesting backTesting) {
		return _backTestingLocalService.deleteBackTesting(backTesting);
	}

	/**
	* Deletes the back testing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param backTId the primary key of the back testing
	* @return the back testing that was removed
	* @throws PortalException if a back testing with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.BackTesting deleteBackTesting(long backTId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _backTestingLocalService.deleteBackTesting(backTId);
	}

	@Override
	public com.ibtrader.data.model.BackTesting fetchBackTesting(long backTId) {
		return _backTestingLocalService.fetchBackTesting(backTId);
	}

	/**
	* Returns the back testing matching the UUID and group.
	*
	* @param uuid the back testing's UUID
	* @param groupId the primary key of the group
	* @return the matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	@Override
	public com.ibtrader.data.model.BackTesting fetchBackTestingByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _backTestingLocalService.fetchBackTestingByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the back testing with the primary key.
	*
	* @param backTId the primary key of the back testing
	* @return the back testing
	* @throws PortalException if a back testing with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.BackTesting getBackTesting(long backTId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _backTestingLocalService.getBackTesting(backTId);
	}

	/**
	* Returns the back testing matching the UUID and group.
	*
	* @param uuid the back testing's UUID
	* @param groupId the primary key of the group
	* @return the matching back testing
	* @throws PortalException if a matching back testing could not be found
	*/
	@Override
	public com.ibtrader.data.model.BackTesting getBackTestingByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _backTestingLocalService.getBackTestingByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the back testing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param backTesting the back testing
	* @return the back testing that was updated
	*/
	@Override
	public com.ibtrader.data.model.BackTesting updateBackTesting(
		com.ibtrader.data.model.BackTesting backTesting) {
		return _backTestingLocalService.updateBackTesting(backTesting);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _backTestingLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _backTestingLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _backTestingLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _backTestingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _backTestingLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _backTestingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of back testings.
	*
	* @return the number of back testings
	*/
	@Override
	public int getBackTestingsCount() {
		return _backTestingLocalService.getBackTestingsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _backTestingLocalService.getOSGiServiceIdentifier();
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
		return _backTestingLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _backTestingLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _backTestingLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.BackTesting> findByPendingCompanyGroup(
		long companyId, long groupId) {
		return _backTestingLocalService.findByPendingCompanyGroup(companyId,
			groupId);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.BackTesting> findByShareCompanyGroup(
		long shareId, long companyId, long groupId) {
		return _backTestingLocalService.findByShareCompanyGroup(shareId,
			companyId, groupId);
	}

	/**
	* Returns a range of all the back testings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @return the range of back testings
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.BackTesting> getBackTestings(
		int start, int end) {
		return _backTestingLocalService.getBackTestings(start, end);
	}

	/**
	* Returns all the back testings matching the UUID and company.
	*
	* @param uuid the UUID of the back testings
	* @param companyId the primary key of the company
	* @return the matching back testings, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.BackTesting> getBackTestingsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _backTestingLocalService.getBackTestingsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of back testings matching the UUID and company.
	*
	* @param uuid the UUID of the back testings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching back testings, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.BackTesting> getBackTestingsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.BackTesting> orderByComparator) {
		return _backTestingLocalService.getBackTestingsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	@Override
	public long countBackTestingShareStatus(long shareId, long companyId,
		long groupId, java.lang.String status) {
		return _backTestingLocalService.countBackTestingShareStatus(shareId,
			companyId, groupId, status);
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
		return _backTestingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _backTestingLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public BackTestingLocalService getWrappedService() {
		return _backTestingLocalService;
	}

	@Override
	public void setWrappedService(
		BackTestingLocalService backTestingLocalService) {
		_backTestingLocalService = backTestingLocalService;
	}

	private BackTestingLocalService _backTestingLocalService;
}