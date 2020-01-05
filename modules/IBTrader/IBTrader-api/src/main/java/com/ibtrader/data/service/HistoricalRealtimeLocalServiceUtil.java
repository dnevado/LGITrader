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
 * Provides the local service utility for HistoricalRealtime. This utility wraps
 * {@link com.ibtrader.data.service.impl.HistoricalRealtimeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see HistoricalRealtimeLocalService
 * @see com.ibtrader.data.service.base.HistoricalRealtimeLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.HistoricalRealtimeLocalServiceImpl
 * @generated
 */
@ProviderType
public class HistoricalRealtimeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ibtrader.data.service.impl.HistoricalRealtimeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the historical realtime to the database. Also notifies the appropriate model listeners.
	*
	* @param historicalRealtime the historical realtime
	* @return the historical realtime that was added
	*/
	public static com.ibtrader.data.model.HistoricalRealtime addHistoricalRealtime(
		com.ibtrader.data.model.HistoricalRealtime historicalRealtime) {
		return getService().addHistoricalRealtime(historicalRealtime);
	}

	/**
	* Creates a new historical realtime with the primary key. Does not add the historical realtime to the database.
	*
	* @param realtimeId the primary key for the new historical realtime
	* @return the new historical realtime
	*/
	public static com.ibtrader.data.model.HistoricalRealtime createHistoricalRealtime(
		long realtimeId) {
		return getService().createHistoricalRealtime(realtimeId);
	}

	/**
	* Deletes the historical realtime from the database. Also notifies the appropriate model listeners.
	*
	* @param historicalRealtime the historical realtime
	* @return the historical realtime that was removed
	*/
	public static com.ibtrader.data.model.HistoricalRealtime deleteHistoricalRealtime(
		com.ibtrader.data.model.HistoricalRealtime historicalRealtime) {
		return getService().deleteHistoricalRealtime(historicalRealtime);
	}

	/**
	* Deletes the historical realtime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime that was removed
	* @throws PortalException if a historical realtime with the primary key could not be found
	*/
	public static com.ibtrader.data.model.HistoricalRealtime deleteHistoricalRealtime(
		long realtimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteHistoricalRealtime(realtimeId);
	}

	public static com.ibtrader.data.model.HistoricalRealtime fetchHistoricalRealtime(
		long realtimeId) {
		return getService().fetchHistoricalRealtime(realtimeId);
	}

	/**
	* Returns the historical realtime matching the UUID and group.
	*
	* @param uuid the historical realtime's UUID
	* @param groupId the primary key of the group
	* @return the matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static com.ibtrader.data.model.HistoricalRealtime fetchHistoricalRealtimeByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService()
				   .fetchHistoricalRealtimeByUuidAndGroupId(uuid, groupId);
	}

	public static com.ibtrader.data.model.HistoricalRealtime findCloseRealTime(
		long shareId, long companyId, long groupId, java.util.Date closeDate) {
		return getService()
				   .findCloseRealTime(shareId, companyId, groupId, closeDate);
	}

	public static com.ibtrader.data.model.HistoricalRealtime findFirstRealTime(
		long shareId, long companyId, long groupId) {
		return getService().findFirstRealTime(shareId, companyId, groupId);
	}

	public static com.ibtrader.data.model.HistoricalRealtime findFirstRealTimeBetweenDates(
		long shareId, long companyId, long groupId, java.util.Date _from,
		java.util.Date _to) {
		return getService()
				   .findFirstRealTimeBetweenDates(shareId, companyId, groupId,
			_from, _to);
	}

	public static com.ibtrader.data.model.HistoricalRealtime findLastCompanyShare(
		long companyId, long shareId, long groupId) {
		return getService().findLastCompanyShare(companyId, shareId, groupId);
	}

	public static com.ibtrader.data.model.HistoricalRealtime findLastRealTime(
		long shareId, long companyId, long groupId) {
		return getService().findLastRealTime(shareId, companyId, groupId);
	}

	public static com.ibtrader.data.model.HistoricalRealtime findLastRealTimeLessThanDate(
		long shareId, long companyId, long groupId, java.util.Date _to) {
		return getService()
				   .findLastRealTimeLessThanDate(shareId, companyId, groupId,
			_to);
	}

	public static com.ibtrader.data.model.HistoricalRealtime findMinMaxRealTime(
		java.util.Date from, java.util.Date to, long shareId, long companyId,
		long groupId) {
		return getService()
				   .findMinMaxRealTime(from, to, shareId, companyId, groupId);
	}

	public static com.ibtrader.data.model.HistoricalRealtime findRealTime(
		long shareId, long companyId, long groupId, java.util.Date closeDate) {
		return getService().findRealTime(shareId, companyId, groupId, closeDate);
	}

	public static com.ibtrader.data.model.HistoricalRealtime findSimpleMobileAvgGroupByPeriods(
		long shareId, long companyId, long groupId, java.util.Date from,
		java.util.Date to, java.util.List<java.lang.String> mobileAvgDates) {
		return getService()
				   .findSimpleMobileAvgGroupByPeriods(shareId, companyId,
			groupId, from, to, mobileAvgDates);
	}

	/**
	* Returns the historical realtime with the primary key.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime
	* @throws PortalException if a historical realtime with the primary key could not be found
	*/
	public static com.ibtrader.data.model.HistoricalRealtime getHistoricalRealtime(
		long realtimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getHistoricalRealtime(realtimeId);
	}

	/**
	* Returns the historical realtime matching the UUID and group.
	*
	* @param uuid the historical realtime's UUID
	* @param groupId the primary key of the group
	* @return the matching historical realtime
	* @throws PortalException if a matching historical realtime could not be found
	*/
	public static com.ibtrader.data.model.HistoricalRealtime getHistoricalRealtimeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getHistoricalRealtimeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the historical realtime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param historicalRealtime the historical realtime
	* @return the historical realtime that was updated
	*/
	public static com.ibtrader.data.model.HistoricalRealtime updateHistoricalRealtime(
		com.ibtrader.data.model.HistoricalRealtime historicalRealtime) {
		return getService().updateHistoricalRealtime(historicalRealtime);
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
	* Returns the number of historical realtimes.
	*
	* @return the number of historical realtimes
	*/
	public static int getHistoricalRealtimesCount() {
		return getService().getHistoricalRealtimesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.ibtrader.data.model.HistoricalRealtime> findCloseRealTimes(
		long shareId, long companyId, long groupId, java.util.Date from,
		java.util.Date to, java.util.List<java.lang.String> closingDates) {
		return getService()
				   .findCloseRealTimes(shareId, companyId, groupId, from, to,
			closingDates);
	}

	public static java.util.List<com.ibtrader.data.model.HistoricalRealtime> findMinMaxRealTimesGroupedByBars(
		java.util.Date from, java.util.Date to, long shareId, long companyId,
		long groupId, long timebars, com.ibtrader.data.model.Market market) {
		return getService()
				   .findMinMaxRealTimesGroupedByBars(from, to, shareId,
			companyId, groupId, timebars, market);
	}

	/**
	* Returns a range of all the historical realtimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @return the range of historical realtimes
	*/
	public static java.util.List<com.ibtrader.data.model.HistoricalRealtime> getHistoricalRealtimes(
		int start, int end) {
		return getService().getHistoricalRealtimes(start, end);
	}

	/**
	* Returns all the historical realtimes matching the UUID and company.
	*
	* @param uuid the UUID of the historical realtimes
	* @param companyId the primary key of the company
	* @return the matching historical realtimes, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.HistoricalRealtime> getHistoricalRealtimesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getHistoricalRealtimesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of historical realtimes matching the UUID and company.
	*
	* @param uuid the UUID of the historical realtimes
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching historical realtimes, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.HistoricalRealtime> getHistoricalRealtimesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.HistoricalRealtime> orderByComparator) {
		return getService()
				   .getHistoricalRealtimesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
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

	public static HistoricalRealtimeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistoricalRealtimeLocalService, HistoricalRealtimeLocalService> _serviceTracker =
		ServiceTrackerFactory.open(HistoricalRealtimeLocalService.class);
}