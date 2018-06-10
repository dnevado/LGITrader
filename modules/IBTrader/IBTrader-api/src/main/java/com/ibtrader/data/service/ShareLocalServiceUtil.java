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
 * Provides the local service utility for Share. This utility wraps
 * {@link com.ibtrader.data.service.impl.ShareLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ShareLocalService
 * @see com.ibtrader.data.service.base.ShareLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.ShareLocalServiceImpl
 * @generated
 */
@ProviderType
public class ShareLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ibtrader.data.service.impl.ShareLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean ExistsExchange(java.lang.String exchange) {
		return getService().ExistsExchange(exchange);
	}

	public static boolean ExistsPrimaryExchange(
		java.lang.String primaryexchange) {
		return getService().ExistsPrimaryExchange(primaryexchange);
	}

	public static boolean ExistsSecurityType(java.lang.String type) {
		return getService().ExistsSecurityType(type);
	}

	/**
	* Adds the share to the database. Also notifies the appropriate model listeners.
	*
	* @param share the share
	* @return the share that was added
	*/
	public static com.ibtrader.data.model.Share addShare(
		com.ibtrader.data.model.Share share) {
		return getService().addShare(share);
	}

	public static com.ibtrader.data.model.Share addShare(
		com.ibtrader.data.model.Share share,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addShare(share, serviceContext);
	}

	/**
	* Creates a new share with the primary key. Does not add the share to the database.
	*
	* @param shareId the primary key for the new share
	* @return the new share
	*/
	public static com.ibtrader.data.model.Share createShare(long shareId) {
		return getService().createShare(shareId);
	}

	/**
	* Deletes the share from the database. Also notifies the appropriate model listeners.
	*
	* @param share the share
	* @return the share that was removed
	*/
	public static com.ibtrader.data.model.Share deleteShare(
		com.ibtrader.data.model.Share share) {
		return getService().deleteShare(share);
	}

	/**
	* Deletes the share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param shareId the primary key of the share
	* @return the share that was removed
	* @throws PortalException if a share with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Share deleteShare(long shareId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteShare(shareId);
	}

	public static com.ibtrader.data.model.Share editShare(
		com.ibtrader.data.model.Share share,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().editShare(share, serviceContext);
	}

	public static com.ibtrader.data.model.Share fetchShare(long shareId) {
		return getService().fetchShare(shareId);
	}

	/**
	* Returns the share matching the UUID and group.
	*
	* @param uuid the share's UUID
	* @param groupId the primary key of the group
	* @return the matching share, or <code>null</code> if a matching share could not be found
	*/
	public static com.ibtrader.data.model.Share fetchShareByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchShareByUuidAndGroupId(uuid, groupId);
	}

	public static com.ibtrader.data.model.Share findByNameMarketCompanyGroup(
		long companyId, long groupId, java.lang.String name, long marketId) {
		return getService()
				   .findByNameMarketCompanyGroup(companyId, groupId, name,
			marketId);
	}

	public static com.ibtrader.data.model.Share findBySymbolCompanyGroup(
		long companyId, long groupId, java.lang.String name) {
		return getService().findBySymbolCompanyGroup(companyId, groupId, name);
	}

	/**
	* Returns the share with the primary key.
	*
	* @param shareId the primary key of the share
	* @return the share
	* @throws PortalException if a share with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Share getShare(long shareId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getShare(shareId);
	}

	/**
	* Returns the share matching the UUID and group.
	*
	* @param uuid the share's UUID
	* @param groupId the primary key of the group
	* @return the matching share
	* @throws PortalException if a matching share could not be found
	*/
	public static com.ibtrader.data.model.Share getShareByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getShareByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the share in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param share the share
	* @return the share that was updated
	*/
	public static com.ibtrader.data.model.Share updateShare(
		com.ibtrader.data.model.Share share) {
		return getService().updateShare(share);
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
	* Returns the number of shares.
	*
	* @return the number of shares
	*/
	public static int getSharesCount() {
		return getService().getSharesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.ibtrader.data.model.Share> findByActiveFuturesDates(
		boolean _active) {
		return getService().findByActiveFuturesDates(_active);
	}

	public static java.util.List<com.ibtrader.data.model.Share> findByActiveMarketGroupCompany(
		long _marketId, boolean _active, long groupId, long companyId) {
		return getService()
				   .findByActiveMarketGroupCompany(_marketId, _active, groupId,
			companyId);
	}

	public static java.util.List<com.ibtrader.data.model.Share> findByMarketGroupCompany(
		long _marketId, long groupId, long companyId) {
		return getService()
				   .findByMarketGroupCompany(_marketId, groupId, companyId);
	}

	public static java.util.List<com.ibtrader.data.model.Share> findBySymbolExcludingId(
		java.lang.String symbol, long exludingShareId) {
		return getService().findBySymbolExcludingId(symbol, exludingShareId);
	}

	public static java.util.List<com.ibtrader.data.model.Share> findByValidatedTraderProviderMarketGroupCompany(
		long marketId, long groupId, long companyId) {
		return getService()
				   .findByValidatedTraderProviderMarketGroupCompany(marketId,
			groupId, companyId);
	}

	public static java.util.List<com.ibtrader.data.model.Share> findCompanyGroup(
		long companyId, long groupId) {
		return getService().findCompanyGroup(companyId, groupId);
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
	public static java.util.List<com.ibtrader.data.model.Share> getShares(
		int start, int end) {
		return getService().getShares(start, end);
	}

	/**
	* Returns all the shares matching the UUID and company.
	*
	* @param uuid the UUID of the shares
	* @param companyId the primary key of the company
	* @return the matching shares, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.Share> getSharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getSharesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<com.ibtrader.data.model.Share> getSharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Share> orderByComparator) {
		return getService()
				   .getSharesByUuidAndCompanyId(uuid, companyId, start, end,
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

	public static ShareLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ShareLocalService, ShareLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ShareLocalService.class);
}