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
 * Provides a wrapper for {@link HistoricalRealtimeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HistoricalRealtimeLocalService
 * @generated
 */
@ProviderType
public class HistoricalRealtimeLocalServiceWrapper
	implements HistoricalRealtimeLocalService,
		ServiceWrapper<HistoricalRealtimeLocalService> {
	public HistoricalRealtimeLocalServiceWrapper(
		HistoricalRealtimeLocalService historicalRealtimeLocalService) {
		_historicalRealtimeLocalService = historicalRealtimeLocalService;
	}

	/**
	* Adds the historical realtime to the database. Also notifies the appropriate model listeners.
	*
	* @param historicalRealtime the historical realtime
	* @return the historical realtime that was added
	*/
	@Override
	public com.ibtrader.data.model.HistoricalRealtime addHistoricalRealtime(
		com.ibtrader.data.model.HistoricalRealtime historicalRealtime) {
		return _historicalRealtimeLocalService.addHistoricalRealtime(historicalRealtime);
	}

	/**
	* Creates a new historical realtime with the primary key. Does not add the historical realtime to the database.
	*
	* @param realtimeId the primary key for the new historical realtime
	* @return the new historical realtime
	*/
	@Override
	public com.ibtrader.data.model.HistoricalRealtime createHistoricalRealtime(
		long realtimeId) {
		return _historicalRealtimeLocalService.createHistoricalRealtime(realtimeId);
	}

	/**
	* Deletes the historical realtime from the database. Also notifies the appropriate model listeners.
	*
	* @param historicalRealtime the historical realtime
	* @return the historical realtime that was removed
	*/
	@Override
	public com.ibtrader.data.model.HistoricalRealtime deleteHistoricalRealtime(
		com.ibtrader.data.model.HistoricalRealtime historicalRealtime) {
		return _historicalRealtimeLocalService.deleteHistoricalRealtime(historicalRealtime);
	}

	/**
	* Deletes the historical realtime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime that was removed
	* @throws PortalException if a historical realtime with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.HistoricalRealtime deleteHistoricalRealtime(
		long realtimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historicalRealtimeLocalService.deleteHistoricalRealtime(realtimeId);
	}

	@Override
	public com.ibtrader.data.model.HistoricalRealtime fetchHistoricalRealtime(
		long realtimeId) {
		return _historicalRealtimeLocalService.fetchHistoricalRealtime(realtimeId);
	}

	/**
	* Returns the historical realtime matching the UUID and group.
	*
	* @param uuid the historical realtime's UUID
	* @param groupId the primary key of the group
	* @return the matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	@Override
	public com.ibtrader.data.model.HistoricalRealtime fetchHistoricalRealtimeByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _historicalRealtimeLocalService.fetchHistoricalRealtimeByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the historical realtime with the primary key.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime
	* @throws PortalException if a historical realtime with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.HistoricalRealtime getHistoricalRealtime(
		long realtimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historicalRealtimeLocalService.getHistoricalRealtime(realtimeId);
	}

	/**
	* Returns the historical realtime matching the UUID and group.
	*
	* @param uuid the historical realtime's UUID
	* @param groupId the primary key of the group
	* @return the matching historical realtime
	* @throws PortalException if a matching historical realtime could not be found
	*/
	@Override
	public com.ibtrader.data.model.HistoricalRealtime getHistoricalRealtimeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historicalRealtimeLocalService.getHistoricalRealtimeByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the historical realtime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param historicalRealtime the historical realtime
	* @return the historical realtime that was updated
	*/
	@Override
	public com.ibtrader.data.model.HistoricalRealtime updateHistoricalRealtime(
		com.ibtrader.data.model.HistoricalRealtime historicalRealtime) {
		return _historicalRealtimeLocalService.updateHistoricalRealtime(historicalRealtime);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _historicalRealtimeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _historicalRealtimeLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _historicalRealtimeLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _historicalRealtimeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historicalRealtimeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _historicalRealtimeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of historical realtimes.
	*
	* @return the number of historical realtimes
	*/
	@Override
	public int getHistoricalRealtimesCount() {
		return _historicalRealtimeLocalService.getHistoricalRealtimesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _historicalRealtimeLocalService.getOSGiServiceIdentifier();
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
		return _historicalRealtimeLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _historicalRealtimeLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _historicalRealtimeLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
	@Override
	public java.util.List<com.ibtrader.data.model.HistoricalRealtime> getHistoricalRealtimes(
		int start, int end) {
		return _historicalRealtimeLocalService.getHistoricalRealtimes(start, end);
	}

	/**
	* Returns all the historical realtimes matching the UUID and company.
	*
	* @param uuid the UUID of the historical realtimes
	* @param companyId the primary key of the company
	* @return the matching historical realtimes, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.HistoricalRealtime> getHistoricalRealtimesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _historicalRealtimeLocalService.getHistoricalRealtimesByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<com.ibtrader.data.model.HistoricalRealtime> getHistoricalRealtimesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.HistoricalRealtime> orderByComparator) {
		return _historicalRealtimeLocalService.getHistoricalRealtimesByUuidAndCompanyId(uuid,
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
		return _historicalRealtimeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _historicalRealtimeLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public HistoricalRealtimeLocalService getWrappedService() {
		return _historicalRealtimeLocalService;
	}

	@Override
	public void setWrappedService(
		HistoricalRealtimeLocalService historicalRealtimeLocalService) {
		_historicalRealtimeLocalService = historicalRealtimeLocalService;
	}

	private HistoricalRealtimeLocalService _historicalRealtimeLocalService;
}