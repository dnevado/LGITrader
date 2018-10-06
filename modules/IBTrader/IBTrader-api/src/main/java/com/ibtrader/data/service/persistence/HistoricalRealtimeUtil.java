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

package com.ibtrader.data.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.ibtrader.data.model.HistoricalRealtime;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the historical realtime service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.HistoricalRealtimePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HistoricalRealtimePersistence
 * @see com.ibtrader.data.service.persistence.impl.HistoricalRealtimePersistenceImpl
 * @generated
 */
@ProviderType
public class HistoricalRealtimeUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(HistoricalRealtime historicalRealtime) {
		getPersistence().clearCache(historicalRealtime);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HistoricalRealtime> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HistoricalRealtime> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HistoricalRealtime> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HistoricalRealtime update(
		HistoricalRealtime historicalRealtime) {
		return getPersistence().update(historicalRealtime);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HistoricalRealtime update(
		HistoricalRealtime historicalRealtime, ServiceContext serviceContext) {
		return getPersistence().update(historicalRealtime, serviceContext);
	}

	/**
	* Returns all the historical realtimes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the historical realtimes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @return the range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the historical realtimes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the historical realtimes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first historical realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByUuid_First(java.lang.String uuid,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first historical realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByUuid_Last(java.lang.String uuid,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the historical realtimes before and after the current historical realtime in the ordered set where uuid = &#63;.
	*
	* @param realtimeId the primary key of the current historical realtime
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next historical realtime
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public static HistoricalRealtime[] findByUuid_PrevAndNext(long realtimeId,
		java.lang.String uuid,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByUuid_PrevAndNext(realtimeId, uuid, orderByComparator);
	}

	/**
	* Removes all the historical realtimes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of historical realtimes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching historical realtimes
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the historical realtime where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchHistoricalRealtimeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the historical realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the historical realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the historical realtime where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the historical realtime that was removed
	*/
	public static HistoricalRealtime removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of historical realtimes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching historical realtimes
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the historical realtimes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the historical realtimes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @return the range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the historical realtimes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the historical realtimes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the historical realtimes before and after the current historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param realtimeId the primary key of the current historical realtime
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next historical realtime
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public static HistoricalRealtime[] findByUuid_C_PrevAndNext(
		long realtimeId, java.lang.String uuid, long companyId,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(realtimeId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the historical realtimes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of historical realtimes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching historical realtimes
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the historical realtimes where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByDate(Date createDate) {
		return getPersistence().findByDate(createDate);
	}

	/**
	* Returns a range of all the historical realtimes where createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @return the range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByDate(Date createDate,
		int start, int end) {
		return getPersistence().findByDate(createDate, start, end);
	}

	/**
	* Returns an ordered range of all the historical realtimes where createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByDate(Date createDate,
		int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .findByDate(createDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the historical realtimes where createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByDate(Date createDate,
		int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDate(createDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first historical realtime in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByDate_First(Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence().findByDate_First(createDate, orderByComparator);
	}

	/**
	* Returns the first historical realtime in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByDate_First(Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence().fetchByDate_First(createDate, orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByDate_Last(Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence().findByDate_Last(createDate, orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByDate_Last(Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence().fetchByDate_Last(createDate, orderByComparator);
	}

	/**
	* Returns the historical realtimes before and after the current historical realtime in the ordered set where createDate = &#63;.
	*
	* @param realtimeId the primary key of the current historical realtime
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next historical realtime
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public static HistoricalRealtime[] findByDate_PrevAndNext(long realtimeId,
		Date createDate, OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByDate_PrevAndNext(realtimeId, createDate,
			orderByComparator);
	}

	/**
	* Removes all the historical realtimes where createDate = &#63; from the database.
	*
	* @param createDate the create date
	*/
	public static void removeByDate(Date createDate) {
		getPersistence().removeByDate(createDate);
	}

	/**
	* Returns the number of historical realtimes where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the number of matching historical realtimes
	*/
	public static int countByDate(Date createDate) {
		return getPersistence().countByDate(createDate);
	}

	/**
	* Returns all the historical realtimes where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @return the matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanyShare(long companyId,
		long shareId) {
		return getPersistence().findByCompanyShare(companyId, shareId);
	}

	/**
	* Returns a range of all the historical realtimes where companyId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @return the range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanyShare(long companyId,
		long shareId, int start, int end) {
		return getPersistence()
				   .findByCompanyShare(companyId, shareId, start, end);
	}

	/**
	* Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanyShare(long companyId,
		long shareId, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .findByCompanyShare(companyId, shareId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanyShare(long companyId,
		long shareId, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyShare(companyId, shareId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByCompanyShare_First(long companyId,
		long shareId, OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanyShare_First(companyId, shareId,
			orderByComparator);
	}

	/**
	* Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByCompanyShare_First(long companyId,
		long shareId, OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyShare_First(companyId, shareId,
			orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByCompanyShare_Last(long companyId,
		long shareId, OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanyShare_Last(companyId, shareId,
			orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByCompanyShare_Last(long companyId,
		long shareId, OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyShare_Last(companyId, shareId,
			orderByComparator);
	}

	/**
	* Returns the historical realtimes before and after the current historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param realtimeId the primary key of the current historical realtime
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next historical realtime
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public static HistoricalRealtime[] findByCompanyShare_PrevAndNext(
		long realtimeId, long companyId, long shareId,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanyShare_PrevAndNext(realtimeId, companyId,
			shareId, orderByComparator);
	}

	/**
	* Removes all the historical realtimes where companyId = &#63; and shareId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	*/
	public static void removeByCompanyShare(long companyId, long shareId) {
		getPersistence().removeByCompanyShare(companyId, shareId);
	}

	/**
	* Returns the number of historical realtimes where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @return the number of matching historical realtimes
	*/
	public static int countByCompanyShare(long companyId, long shareId) {
		return getPersistence().countByCompanyShare(companyId, shareId);
	}

	/**
	* Returns all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @return the matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanyShareDate(
		long companyId, long shareId, Date createDate) {
		return getPersistence()
				   .findByCompanyShareDate(companyId, shareId, createDate);
	}

	/**
	* Returns a range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @return the range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanyShareDate(
		long companyId, long shareId, Date createDate, int start, int end) {
		return getPersistence()
				   .findByCompanyShareDate(companyId, shareId, createDate,
			start, end);
	}

	/**
	* Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanyShareDate(
		long companyId, long shareId, Date createDate, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .findByCompanyShareDate(companyId, shareId, createDate,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanyShareDate(
		long companyId, long shareId, Date createDate, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyShareDate(companyId, shareId, createDate,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByCompanyShareDate_First(
		long companyId, long shareId, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanyShareDate_First(companyId, shareId,
			createDate, orderByComparator);
	}

	/**
	* Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByCompanyShareDate_First(
		long companyId, long shareId, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyShareDate_First(companyId, shareId,
			createDate, orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByCompanyShareDate_Last(
		long companyId, long shareId, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanyShareDate_Last(companyId, shareId, createDate,
			orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByCompanyShareDate_Last(
		long companyId, long shareId, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyShareDate_Last(companyId, shareId,
			createDate, orderByComparator);
	}

	/**
	* Returns the historical realtimes before and after the current historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param realtimeId the primary key of the current historical realtime
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next historical realtime
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public static HistoricalRealtime[] findByCompanyShareDate_PrevAndNext(
		long realtimeId, long companyId, long shareId, Date createDate,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanyShareDate_PrevAndNext(realtimeId, companyId,
			shareId, createDate, orderByComparator);
	}

	/**
	* Removes all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; from the database.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	*/
	public static void removeByCompanyShareDate(long companyId, long shareId,
		Date createDate) {
		getPersistence().removeByCompanyShareDate(companyId, shareId, createDate);
	}

	/**
	* Returns the number of historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @return the number of matching historical realtimes
	*/
	public static int countByCompanyShareDate(long companyId, long shareId,
		Date createDate) {
		return getPersistence()
				   .countByCompanyShareDate(companyId, shareId, createDate);
	}

	/**
	* Returns all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @return the matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId) {
		return getPersistence()
				   .findByCompanySharegGroupDate(companyId, shareId,
			createDate, groupId);
	}

	/**
	* Returns a range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @return the range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId, int start,
		int end) {
		return getPersistence()
				   .findByCompanySharegGroupDate(companyId, shareId,
			createDate, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId, int start,
		int end, OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .findByCompanySharegGroupDate(companyId, shareId,
			createDate, groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId, int start,
		int end, OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanySharegGroupDate(companyId, shareId,
			createDate, groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByCompanySharegGroupDate_First(
		long companyId, long shareId, Date createDate, long groupId,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanySharegGroupDate_First(companyId, shareId,
			createDate, groupId, orderByComparator);
	}

	/**
	* Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByCompanySharegGroupDate_First(
		long companyId, long shareId, Date createDate, long groupId,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .fetchByCompanySharegGroupDate_First(companyId, shareId,
			createDate, groupId, orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByCompanySharegGroupDate_Last(
		long companyId, long shareId, Date createDate, long groupId,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanySharegGroupDate_Last(companyId, shareId,
			createDate, groupId, orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByCompanySharegGroupDate_Last(
		long companyId, long shareId, Date createDate, long groupId,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .fetchByCompanySharegGroupDate_Last(companyId, shareId,
			createDate, groupId, orderByComparator);
	}

	/**
	* Returns the historical realtimes before and after the current historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* @param realtimeId the primary key of the current historical realtime
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next historical realtime
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public static HistoricalRealtime[] findByCompanySharegGroupDate_PrevAndNext(
		long realtimeId, long companyId, long shareId, Date createDate,
		long groupId, OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanySharegGroupDate_PrevAndNext(realtimeId,
			companyId, shareId, createDate, groupId, orderByComparator);
	}

	/**
	* Removes all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	*/
	public static void removeByCompanySharegGroupDate(long companyId,
		long shareId, Date createDate, long groupId) {
		getPersistence()
			.removeByCompanySharegGroupDate(companyId, shareId, createDate,
			groupId);
	}

	/**
	* Returns the number of historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @return the number of matching historical realtimes
	*/
	public static int countByCompanySharegGroupDate(long companyId,
		long shareId, Date createDate, long groupId) {
		return getPersistence()
				   .countByCompanySharegGroupDate(companyId, shareId,
			createDate, groupId);
	}

	/**
	* Returns all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	* @return the matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice) {
		return getPersistence()
				   .findByCompanySharegGroupClose(companyId, shareId,
			createDate, groupId, closeprice);
	}

	/**
	* Returns a range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @return the range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice, int start, int end) {
		return getPersistence()
				   .findByCompanySharegGroupClose(companyId, shareId,
			createDate, groupId, closeprice, start, end);
	}

	/**
	* Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .findByCompanySharegGroupClose(companyId, shareId,
			createDate, groupId, closeprice, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching historical realtimes
	*/
	public static List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice, int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanySharegGroupClose(companyId, shareId,
			createDate, groupId, closeprice, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByCompanySharegGroupClose_First(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanySharegGroupClose_First(companyId, shareId,
			createDate, groupId, closeprice, orderByComparator);
	}

	/**
	* Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByCompanySharegGroupClose_First(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .fetchByCompanySharegGroupClose_First(companyId, shareId,
			createDate, groupId, closeprice, orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime findByCompanySharegGroupClose_Last(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanySharegGroupClose_Last(companyId, shareId,
			createDate, groupId, closeprice, orderByComparator);
	}

	/**
	* Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public static HistoricalRealtime fetchByCompanySharegGroupClose_Last(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence()
				   .fetchByCompanySharegGroupClose_Last(companyId, shareId,
			createDate, groupId, closeprice, orderByComparator);
	}

	/**
	* Returns the historical realtimes before and after the current historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	*
	* @param realtimeId the primary key of the current historical realtime
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next historical realtime
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public static HistoricalRealtime[] findByCompanySharegGroupClose_PrevAndNext(
		long realtimeId, long companyId, long shareId, Date createDate,
		long groupId, boolean closeprice,
		OrderByComparator<HistoricalRealtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence()
				   .findByCompanySharegGroupClose_PrevAndNext(realtimeId,
			companyId, shareId, createDate, groupId, closeprice,
			orderByComparator);
	}

	/**
	* Removes all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63; from the database.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	*/
	public static void removeByCompanySharegGroupClose(long companyId,
		long shareId, Date createDate, long groupId, boolean closeprice) {
		getPersistence()
			.removeByCompanySharegGroupClose(companyId, shareId, createDate,
			groupId, closeprice);
	}

	/**
	* Returns the number of historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	* @return the number of matching historical realtimes
	*/
	public static int countByCompanySharegGroupClose(long companyId,
		long shareId, Date createDate, long groupId, boolean closeprice) {
		return getPersistence()
				   .countByCompanySharegGroupClose(companyId, shareId,
			createDate, groupId, closeprice);
	}

	/**
	* Caches the historical realtime in the entity cache if it is enabled.
	*
	* @param historicalRealtime the historical realtime
	*/
	public static void cacheResult(HistoricalRealtime historicalRealtime) {
		getPersistence().cacheResult(historicalRealtime);
	}

	/**
	* Caches the historical realtimes in the entity cache if it is enabled.
	*
	* @param historicalRealtimes the historical realtimes
	*/
	public static void cacheResult(List<HistoricalRealtime> historicalRealtimes) {
		getPersistence().cacheResult(historicalRealtimes);
	}

	/**
	* Creates a new historical realtime with the primary key. Does not add the historical realtime to the database.
	*
	* @param realtimeId the primary key for the new historical realtime
	* @return the new historical realtime
	*/
	public static HistoricalRealtime create(long realtimeId) {
		return getPersistence().create(realtimeId);
	}

	/**
	* Removes the historical realtime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime that was removed
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public static HistoricalRealtime remove(long realtimeId)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence().remove(realtimeId);
	}

	public static HistoricalRealtime updateImpl(
		HistoricalRealtime historicalRealtime) {
		return getPersistence().updateImpl(historicalRealtime);
	}

	/**
	* Returns the historical realtime with the primary key or throws a {@link NoSuchHistoricalRealtimeException} if it could not be found.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public static HistoricalRealtime findByPrimaryKey(long realtimeId)
		throws com.ibtrader.data.exception.NoSuchHistoricalRealtimeException {
		return getPersistence().findByPrimaryKey(realtimeId);
	}

	/**
	* Returns the historical realtime with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime, or <code>null</code> if a historical realtime with the primary key could not be found
	*/
	public static HistoricalRealtime fetchByPrimaryKey(long realtimeId) {
		return getPersistence().fetchByPrimaryKey(realtimeId);
	}

	public static java.util.Map<java.io.Serializable, HistoricalRealtime> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the historical realtimes.
	*
	* @return the historical realtimes
	*/
	public static List<HistoricalRealtime> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the historical realtimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @return the range of historical realtimes
	*/
	public static List<HistoricalRealtime> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the historical realtimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of historical realtimes
	*/
	public static List<HistoricalRealtime> findAll(int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the historical realtimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistoricalRealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of historical realtimes
	* @param end the upper bound of the range of historical realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of historical realtimes
	*/
	public static List<HistoricalRealtime> findAll(int start, int end,
		OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the historical realtimes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of historical realtimes.
	*
	* @return the number of historical realtimes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static HistoricalRealtimePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistoricalRealtimePersistence, HistoricalRealtimePersistence> _serviceTracker =
		ServiceTrackerFactory.open(HistoricalRealtimePersistence.class);
}