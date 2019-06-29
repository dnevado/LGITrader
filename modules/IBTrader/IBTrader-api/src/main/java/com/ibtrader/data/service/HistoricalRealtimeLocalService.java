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

import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.model.Market;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for HistoricalRealtime. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see HistoricalRealtimeLocalServiceUtil
 * @see com.ibtrader.data.service.base.HistoricalRealtimeLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.HistoricalRealtimeLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface HistoricalRealtimeLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistoricalRealtimeLocalServiceUtil} to access the historical realtime local service. Add custom service methods to {@link com.ibtrader.data.service.impl.HistoricalRealtimeLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the historical realtime to the database. Also notifies the appropriate model listeners.
	*
	* @param historicalRealtime the historical realtime
	* @return the historical realtime that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public HistoricalRealtime addHistoricalRealtime(
		HistoricalRealtime historicalRealtime);

	/**
	* Creates a new historical realtime with the primary key. Does not add the historical realtime to the database.
	*
	* @param realtimeId the primary key for the new historical realtime
	* @return the new historical realtime
	*/
	public HistoricalRealtime createHistoricalRealtime(long realtimeId);

	/**
	* Deletes the historical realtime from the database. Also notifies the appropriate model listeners.
	*
	* @param historicalRealtime the historical realtime
	* @return the historical realtime that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public HistoricalRealtime deleteHistoricalRealtime(
		HistoricalRealtime historicalRealtime);

	/**
	* Deletes the historical realtime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime that was removed
	* @throws PortalException if a historical realtime with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public HistoricalRealtime deleteHistoricalRealtime(long realtimeId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public HistoricalRealtime fetchHistoricalRealtime(long realtimeId);

	/**
	* Returns the historical realtime matching the UUID and group.
	*
	* @param uuid the historical realtime's UUID
	* @param groupId the primary key of the group
	* @return the matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public HistoricalRealtime fetchHistoricalRealtimeByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	public HistoricalRealtime findCloseRealTime(long shareId, long companyId,
		long groupId, Date closeDate);

	public HistoricalRealtime findFirstRealTime(long shareId, long companyId,
		long groupId);

	public HistoricalRealtime findLastCompanyShare(long companyId,
		long shareId, long groupId);

	public HistoricalRealtime findLastRealTime(long shareId, long companyId,
		long groupId);

	public HistoricalRealtime findLastRealTimeLessThanDate(long shareId,
		long companyId, long groupId, Date _to);

	public HistoricalRealtime findMinMaxRealTime(Date from, Date to,
		long shareId, long companyId, long groupId);

	public HistoricalRealtime findSimpleMobileAvgGroupByPeriods(long shareId,
		long companyId, long groupId, Date from, Date to,
		List<java.lang.String> mobileAvgDates);

	/**
	* Returns the historical realtime with the primary key.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime
	* @throws PortalException if a historical realtime with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public HistoricalRealtime getHistoricalRealtime(long realtimeId)
		throws PortalException;

	/**
	* Returns the historical realtime matching the UUID and group.
	*
	* @param uuid the historical realtime's UUID
	* @param groupId the primary key of the group
	* @return the matching historical realtime
	* @throws PortalException if a matching historical realtime could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public HistoricalRealtime getHistoricalRealtimeByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Updates the historical realtime in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param historicalRealtime the historical realtime
	* @return the historical realtime that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public HistoricalRealtime updateHistoricalRealtime(
		HistoricalRealtime historicalRealtime);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of historical realtimes.
	*
	* @return the number of historical realtimes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getHistoricalRealtimesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	public List<HistoricalRealtime> findCloseRealTimes(long shareId,
		long companyId, long groupId, Date from, Date to,
		List<java.lang.String> closingDates);

	public List<HistoricalRealtime> findMinMaxRealTimesGroupedByBars(
		Date from, Date to, long shareId, long companyId, long groupId,
		long timebars, Market market);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<HistoricalRealtime> getHistoricalRealtimes(int start, int end);

	/**
	* Returns all the historical realtimes matching the UUID and company.
	*
	* @param uuid the UUID of the historical realtimes
	* @param companyId the primary key of the company
	* @return the matching historical realtimes, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<HistoricalRealtime> getHistoricalRealtimesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<HistoricalRealtime> getHistoricalRealtimesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void removeRealtimeFromToDate(Date from, Date to, long shareId,
		long companyId, long groupId);
}