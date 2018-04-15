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
 * Provides the local service utility for Realtime. This utility wraps
 * {@link com.ibtrader.data.service.impl.RealtimeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RealtimeLocalService
 * @see com.ibtrader.data.service.base.RealtimeLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.RealtimeLocalServiceImpl
 * @generated
 */
@ProviderType
public class RealtimeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ibtrader.data.service.impl.RealtimeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the realtime to the database. Also notifies the appropriate model listeners.
	*
	* @param realtime the realtime
	* @return the realtime that was added
	*/
	public static com.ibtrader.data.model.Realtime addRealtime(
		com.ibtrader.data.model.Realtime realtime) {
		return getService().addRealtime(realtime);
	}

	/**
	* Creates a new realtime with the primary key. Does not add the realtime to the database.
	*
	* @param realtimeId the primary key for the new realtime
	* @return the new realtime
	*/
	public static com.ibtrader.data.model.Realtime createRealtime(
		long realtimeId) {
		return getService().createRealtime(realtimeId);
	}

	/**
	* Deletes the realtime from the database. Also notifies the appropriate model listeners.
	*
	* @param realtime the realtime
	* @return the realtime that was removed
	*/
	public static com.ibtrader.data.model.Realtime deleteRealtime(
		com.ibtrader.data.model.Realtime realtime) {
		return getService().deleteRealtime(realtime);
	}

	/**
	* Deletes the realtime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param realtimeId the primary key of the realtime
	* @return the realtime that was removed
	* @throws PortalException if a realtime with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Realtime deleteRealtime(
		long realtimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRealtime(realtimeId);
	}

	public static com.ibtrader.data.model.Realtime fetchRealtime(
		long realtimeId) {
		return getService().fetchRealtime(realtimeId);
	}

	/**
	* Returns the realtime matching the UUID and group.
	*
	* @param uuid the realtime's UUID
	* @param groupId the primary key of the group
	* @return the matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public static com.ibtrader.data.model.Realtime fetchRealtimeByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchRealtimeByUuidAndGroupId(uuid, groupId);
	}

	public static com.ibtrader.data.model.Realtime findLastCompanyShare(
		long companyId, long shareId, long groupId) {
		return getService().findLastCompanyShare(companyId, shareId, groupId);
	}

	public static com.ibtrader.data.model.Realtime findLastRealTime(
		long shareId, long companyId, long groupId) {
		return getService().findLastRealTime(shareId, companyId, groupId);
	}

	public static com.ibtrader.data.model.Realtime findLastRealTimeLessThanDate(
		long shareId, long companyId, long groupId, java.util.Date _to) {
		return getService()
				   .findLastRealTimeLessThanDate(shareId, companyId, groupId,
			_to);
	}

	public static com.ibtrader.data.model.Realtime findMinMaxRealTime(
		java.util.Date from, java.util.Date to, long shareId, long companyId,
		long groupId) {
		return getService()
				   .findMinMaxRealTime(from, to, shareId, companyId, groupId);
	}

	public static com.ibtrader.data.model.Realtime findSimpleMobileAvgGroupByPeriods(
		long shareId, long companyId, long groupId, java.util.Date from,
		java.util.Date to, java.util.List<java.lang.String> mobileAvgDates) {
		return getService()
				   .findSimpleMobileAvgGroupByPeriods(shareId, companyId,
			groupId, from, to, mobileAvgDates);
	}

	/**
	* Returns the realtime with the primary key.
	*
	* @param realtimeId the primary key of the realtime
	* @return the realtime
	* @throws PortalException if a realtime with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Realtime getRealtime(long realtimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRealtime(realtimeId);
	}

	/**
	* Returns the realtime matching the UUID and group.
	*
	* @param uuid the realtime's UUID
	* @param groupId the primary key of the group
	* @return the matching realtime
	* @throws PortalException if a matching realtime could not be found
	*/
	public static com.ibtrader.data.model.Realtime getRealtimeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRealtimeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the realtime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param realtime the realtime
	* @return the realtime that was updated
	*/
	public static com.ibtrader.data.model.Realtime updateRealtime(
		com.ibtrader.data.model.Realtime realtime) {
		return getService().updateRealtime(realtime);
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
	* Returns the number of realtimes.
	*
	* @return the number of realtimes
	*/
	public static int getRealtimesCount() {
		return getService().getRealtimesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the realtimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @return the range of realtimes
	*/
	public static java.util.List<com.ibtrader.data.model.Realtime> getRealtimes(
		int start, int end) {
		return getService().getRealtimes(start, end);
	}

	/**
	* Returns all the realtimes matching the UUID and company.
	*
	* @param uuid the UUID of the realtimes
	* @param companyId the primary key of the company
	* @return the matching realtimes, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.Realtime> getRealtimesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getRealtimesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of realtimes matching the UUID and company.
	*
	* @param uuid the UUID of the realtimes
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching realtimes, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.Realtime> getRealtimesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Realtime> orderByComparator) {
		return getService()
				   .getRealtimesByUuidAndCompanyId(uuid, companyId, start, end,
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

	public static void removeRealtimeFromToDate(java.util.Date from,
		java.util.Date to, long shareId, long companyId, long groupId) {
		getService()
			.removeRealtimeFromToDate(from, to, shareId, companyId, groupId);
	}

	public static RealtimeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RealtimeLocalService, RealtimeLocalService> _serviceTracker =
		ServiceTrackerFactory.open(RealtimeLocalService.class);
}