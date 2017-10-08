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
 * Provides a wrapper for {@link ShareLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ShareLocalService
 * @generated
 */
@ProviderType
public class ShareLocalServiceWrapper implements ShareLocalService,
	ServiceWrapper<ShareLocalService> {
	public ShareLocalServiceWrapper(ShareLocalService shareLocalService) {
		_shareLocalService = shareLocalService;
	}

	/**
	* Adds the share to the database. Also notifies the appropriate model listeners.
	*
	* @param share the share
	* @return the share that was added
	*/
	@Override
	public com.ibtrader.data.model.Share addShare(
		com.ibtrader.data.model.Share share) {
		return _shareLocalService.addShare(share);
	}

	/**
	* Creates a new share with the primary key. Does not add the share to the database.
	*
	* @param shareId the primary key for the new share
	* @return the new share
	*/
	@Override
	public com.ibtrader.data.model.Share createShare(long shareId) {
		return _shareLocalService.createShare(shareId);
	}

	/**
	* Deletes the share from the database. Also notifies the appropriate model listeners.
	*
	* @param share the share
	* @return the share that was removed
	*/
	@Override
	public com.ibtrader.data.model.Share deleteShare(
		com.ibtrader.data.model.Share share) {
		return _shareLocalService.deleteShare(share);
	}

	/**
	* Deletes the share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param shareId the primary key of the share
	* @return the share that was removed
	* @throws PortalException if a share with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.Share deleteShare(long shareId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shareLocalService.deleteShare(shareId);
	}

	@Override
	public com.ibtrader.data.model.Share fetchShare(long shareId) {
		return _shareLocalService.fetchShare(shareId);
	}

	/**
	* Returns the share matching the UUID and group.
	*
	* @param uuid the share's UUID
	* @param groupId the primary key of the group
	* @return the matching share, or <code>null</code> if a matching share could not be found
	*/
	@Override
	public com.ibtrader.data.model.Share fetchShareByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _shareLocalService.fetchShareByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the share with the primary key.
	*
	* @param shareId the primary key of the share
	* @return the share
	* @throws PortalException if a share with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.Share getShare(long shareId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shareLocalService.getShare(shareId);
	}

	/**
	* Returns the share matching the UUID and group.
	*
	* @param uuid the share's UUID
	* @param groupId the primary key of the group
	* @return the matching share
	* @throws PortalException if a matching share could not be found
	*/
	@Override
	public com.ibtrader.data.model.Share getShareByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shareLocalService.getShareByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the share in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param share the share
	* @return the share that was updated
	*/
	@Override
	public com.ibtrader.data.model.Share updateShare(
		com.ibtrader.data.model.Share share) {
		return _shareLocalService.updateShare(share);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _shareLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _shareLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _shareLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _shareLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shareLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _shareLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of shares.
	*
	* @return the number of shares
	*/
	@Override
	public int getSharesCount() {
		return _shareLocalService.getSharesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _shareLocalService.getOSGiServiceIdentifier();
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
		return _shareLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _shareLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _shareLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Share> findByActiveMarket(
		long _MarketId, boolean _Active) {
		return _shareLocalService.findByActiveMarket(_MarketId, _Active);
	}

	/**
	* Returns a range of all the shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of shares
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.Share> getShares(int start,
		int end) {
		return _shareLocalService.getShares(start, end);
	}

	/**
	* Returns all the shares matching the UUID and company.
	*
	* @param uuid the UUID of the shares
	* @param companyId the primary key of the company
	* @return the matching shares, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.Share> getSharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _shareLocalService.getSharesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of shares matching the UUID and company.
	*
	* @param uuid the UUID of the shares
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching shares, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.Share> getSharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Share> orderByComparator) {
		return _shareLocalService.getSharesByUuidAndCompanyId(uuid, companyId,
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
		return _shareLocalService.dynamicQueryCount(dynamicQuery);
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
		return _shareLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public ShareLocalService getWrappedService() {
		return _shareLocalService;
	}

	@Override
	public void setWrappedService(ShareLocalService shareLocalService) {
		_shareLocalService = shareLocalService;
	}

	private ShareLocalService _shareLocalService;
}