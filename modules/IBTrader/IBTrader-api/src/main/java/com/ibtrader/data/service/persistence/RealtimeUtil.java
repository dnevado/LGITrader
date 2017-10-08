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

import com.ibtrader.data.model.Realtime;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the realtime service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.RealtimePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RealtimePersistence
 * @see com.ibtrader.data.service.persistence.impl.RealtimePersistenceImpl
 * @generated
 */
@ProviderType
public class RealtimeUtil {
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
	public static void clearCache(Realtime realtime) {
		getPersistence().clearCache(realtime);
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
	public static List<Realtime> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Realtime> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Realtime> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Realtime> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Realtime update(Realtime realtime) {
		return getPersistence().update(realtime);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Realtime update(Realtime realtime,
		ServiceContext serviceContext) {
		return getPersistence().update(realtime, serviceContext);
	}

	/**
	* Returns all the realtimes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching realtimes
	*/
	public static List<Realtime> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the realtimes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @return the range of matching realtimes
	*/
	public static List<Realtime> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the realtimes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching realtimes
	*/
	public static List<Realtime> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Realtime> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the realtimes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching realtimes
	*/
	public static List<Realtime> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Realtime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public static Realtime findByUuid_First(java.lang.String uuid,
		OrderByComparator<Realtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public static Realtime fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Realtime> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public static Realtime findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Realtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public static Realtime fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Realtime> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the realtimes before and after the current realtime in the ordered set where uuid = &#63;.
	*
	* @param realtimeId the primary key of the current realtime
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next realtime
	* @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	*/
	public static Realtime[] findByUuid_PrevAndNext(long realtimeId,
		java.lang.String uuid, OrderByComparator<Realtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence()
				   .findByUuid_PrevAndNext(realtimeId, uuid, orderByComparator);
	}

	/**
	* Removes all the realtimes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of realtimes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching realtimes
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the realtime where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRealtimeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public static Realtime findByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public static Realtime fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public static Realtime fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the realtime where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the realtime that was removed
	*/
	public static Realtime removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of realtimes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching realtimes
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the realtimes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching realtimes
	*/
	public static List<Realtime> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the realtimes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @return the range of matching realtimes
	*/
	public static List<Realtime> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the realtimes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching realtimes
	*/
	public static List<Realtime> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Realtime> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the realtimes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching realtimes
	*/
	public static List<Realtime> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Realtime> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public static Realtime findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Realtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public static Realtime fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Realtime> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public static Realtime findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Realtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public static Realtime fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Realtime> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the realtimes before and after the current realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param realtimeId the primary key of the current realtime
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next realtime
	* @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	*/
	public static Realtime[] findByUuid_C_PrevAndNext(long realtimeId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Realtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(realtimeId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the realtimes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of realtimes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching realtimes
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the realtimes where shareId = &#63;.
	*
	* @param shareId the share ID
	* @return the matching realtimes
	*/
	public static List<Realtime> findByshareId(long shareId) {
		return getPersistence().findByshareId(shareId);
	}

	/**
	* Returns a range of all the realtimes where shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @return the range of matching realtimes
	*/
	public static List<Realtime> findByshareId(long shareId, int start, int end) {
		return getPersistence().findByshareId(shareId, start, end);
	}

	/**
	* Returns an ordered range of all the realtimes where shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching realtimes
	*/
	public static List<Realtime> findByshareId(long shareId, int start,
		int end, OrderByComparator<Realtime> orderByComparator) {
		return getPersistence()
				   .findByshareId(shareId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the realtimes where shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching realtimes
	*/
	public static List<Realtime> findByshareId(long shareId, int start,
		int end, OrderByComparator<Realtime> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByshareId(shareId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first realtime in the ordered set where shareId = &#63;.
	*
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public static Realtime findByshareId_First(long shareId,
		OrderByComparator<Realtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence().findByshareId_First(shareId, orderByComparator);
	}

	/**
	* Returns the first realtime in the ordered set where shareId = &#63;.
	*
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public static Realtime fetchByshareId_First(long shareId,
		OrderByComparator<Realtime> orderByComparator) {
		return getPersistence().fetchByshareId_First(shareId, orderByComparator);
	}

	/**
	* Returns the last realtime in the ordered set where shareId = &#63;.
	*
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public static Realtime findByshareId_Last(long shareId,
		OrderByComparator<Realtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence().findByshareId_Last(shareId, orderByComparator);
	}

	/**
	* Returns the last realtime in the ordered set where shareId = &#63;.
	*
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public static Realtime fetchByshareId_Last(long shareId,
		OrderByComparator<Realtime> orderByComparator) {
		return getPersistence().fetchByshareId_Last(shareId, orderByComparator);
	}

	/**
	* Returns the realtimes before and after the current realtime in the ordered set where shareId = &#63;.
	*
	* @param realtimeId the primary key of the current realtime
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next realtime
	* @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	*/
	public static Realtime[] findByshareId_PrevAndNext(long realtimeId,
		long shareId, OrderByComparator<Realtime> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence()
				   .findByshareId_PrevAndNext(realtimeId, shareId,
			orderByComparator);
	}

	/**
	* Removes all the realtimes where shareId = &#63; from the database.
	*
	* @param shareId the share ID
	*/
	public static void removeByshareId(long shareId) {
		getPersistence().removeByshareId(shareId);
	}

	/**
	* Returns the number of realtimes where shareId = &#63;.
	*
	* @param shareId the share ID
	* @return the number of matching realtimes
	*/
	public static int countByshareId(long shareId) {
		return getPersistence().countByshareId(shareId);
	}

	/**
	* Caches the realtime in the entity cache if it is enabled.
	*
	* @param realtime the realtime
	*/
	public static void cacheResult(Realtime realtime) {
		getPersistence().cacheResult(realtime);
	}

	/**
	* Caches the realtimes in the entity cache if it is enabled.
	*
	* @param realtimes the realtimes
	*/
	public static void cacheResult(List<Realtime> realtimes) {
		getPersistence().cacheResult(realtimes);
	}

	/**
	* Creates a new realtime with the primary key. Does not add the realtime to the database.
	*
	* @param realtimeId the primary key for the new realtime
	* @return the new realtime
	*/
	public static Realtime create(long realtimeId) {
		return getPersistence().create(realtimeId);
	}

	/**
	* Removes the realtime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param realtimeId the primary key of the realtime
	* @return the realtime that was removed
	* @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	*/
	public static Realtime remove(long realtimeId)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence().remove(realtimeId);
	}

	public static Realtime updateImpl(Realtime realtime) {
		return getPersistence().updateImpl(realtime);
	}

	/**
	* Returns the realtime with the primary key or throws a {@link NoSuchRealtimeException} if it could not be found.
	*
	* @param realtimeId the primary key of the realtime
	* @return the realtime
	* @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	*/
	public static Realtime findByPrimaryKey(long realtimeId)
		throws com.ibtrader.data.exception.NoSuchRealtimeException {
		return getPersistence().findByPrimaryKey(realtimeId);
	}

	/**
	* Returns the realtime with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param realtimeId the primary key of the realtime
	* @return the realtime, or <code>null</code> if a realtime with the primary key could not be found
	*/
	public static Realtime fetchByPrimaryKey(long realtimeId) {
		return getPersistence().fetchByPrimaryKey(realtimeId);
	}

	public static java.util.Map<java.io.Serializable, Realtime> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the realtimes.
	*
	* @return the realtimes
	*/
	public static List<Realtime> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the realtimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @return the range of realtimes
	*/
	public static List<Realtime> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the realtimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of realtimes
	*/
	public static List<Realtime> findAll(int start, int end,
		OrderByComparator<Realtime> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the realtimes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of realtimes
	*/
	public static List<Realtime> findAll(int start, int end,
		OrderByComparator<Realtime> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the realtimes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of realtimes.
	*
	* @return the number of realtimes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RealtimePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RealtimePersistence, RealtimePersistence> _serviceTracker =
		ServiceTrackerFactory.open(RealtimePersistence.class);
}