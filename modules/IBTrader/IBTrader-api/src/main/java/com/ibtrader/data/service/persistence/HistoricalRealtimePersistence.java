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

import com.ibtrader.data.exception.NoSuchHistoricalRealtimeException;
import com.ibtrader.data.model.HistoricalRealtime;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the historical realtime service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.HistoricalRealtimePersistenceImpl
 * @see HistoricalRealtimeUtil
 * @generated
 */
@ProviderType
public interface HistoricalRealtimePersistence extends BasePersistence<HistoricalRealtime> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistoricalRealtimeUtil} to access the historical realtime persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the historical realtimes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching historical realtimes
	*/
	public java.util.List<HistoricalRealtime> findByUuid(java.lang.String uuid);

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
	public java.util.List<HistoricalRealtime> findByUuid(
		java.lang.String uuid, int start, int end);

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
	public java.util.List<HistoricalRealtime> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public java.util.List<HistoricalRealtime> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first historical realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public HistoricalRealtime findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the first historical realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

	/**
	* Returns the last historical realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public HistoricalRealtime findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the last historical realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

	/**
	* Returns the historical realtimes before and after the current historical realtime in the ordered set where uuid = &#63;.
	*
	* @param realtimeId the primary key of the current historical realtime
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next historical realtime
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public HistoricalRealtime[] findByUuid_PrevAndNext(long realtimeId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Removes all the historical realtimes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of historical realtimes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching historical realtimes
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the historical realtime where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchHistoricalRealtimeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public HistoricalRealtime findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the historical realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the historical realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the historical realtime where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the historical realtime that was removed
	*/
	public HistoricalRealtime removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the number of historical realtimes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching historical realtimes
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the historical realtimes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching historical realtimes
	*/
	public java.util.List<HistoricalRealtime> findByUuid_C(
		java.lang.String uuid, long companyId);

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
	public java.util.List<HistoricalRealtime> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

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
	public java.util.List<HistoricalRealtime> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public java.util.List<HistoricalRealtime> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public HistoricalRealtime findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the first historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

	/**
	* Returns the last historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public HistoricalRealtime findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the last historical realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public HistoricalRealtime[] findByUuid_C_PrevAndNext(long realtimeId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Removes all the historical realtimes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of historical realtimes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching historical realtimes
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the historical realtimes where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the matching historical realtimes
	*/
	public java.util.List<HistoricalRealtime> findByDate(Date createDate);

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
	public java.util.List<HistoricalRealtime> findByDate(Date createDate,
		int start, int end);

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
	public java.util.List<HistoricalRealtime> findByDate(Date createDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public java.util.List<HistoricalRealtime> findByDate(Date createDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first historical realtime in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public HistoricalRealtime findByDate_First(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the first historical realtime in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByDate_First(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

	/**
	* Returns the last historical realtime in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public HistoricalRealtime findByDate_Last(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the last historical realtime in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByDate_Last(Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

	/**
	* Returns the historical realtimes before and after the current historical realtime in the ordered set where createDate = &#63;.
	*
	* @param realtimeId the primary key of the current historical realtime
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next historical realtime
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public HistoricalRealtime[] findByDate_PrevAndNext(long realtimeId,
		Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Removes all the historical realtimes where createDate = &#63; from the database.
	*
	* @param createDate the create date
	*/
	public void removeByDate(Date createDate);

	/**
	* Returns the number of historical realtimes where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the number of matching historical realtimes
	*/
	public int countByDate(Date createDate);

	/**
	* Returns all the historical realtimes where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @return the matching historical realtimes
	*/
	public java.util.List<HistoricalRealtime> findByCompanyShare(
		long companyId, long shareId);

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
	public java.util.List<HistoricalRealtime> findByCompanyShare(
		long companyId, long shareId, int start, int end);

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
	public java.util.List<HistoricalRealtime> findByCompanyShare(
		long companyId, long shareId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public java.util.List<HistoricalRealtime> findByCompanyShare(
		long companyId, long shareId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public HistoricalRealtime findByCompanyShare_First(long companyId,
		long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByCompanyShare_First(long companyId,
		long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

	/**
	* Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime
	* @throws NoSuchHistoricalRealtimeException if a matching historical realtime could not be found
	*/
	public HistoricalRealtime findByCompanyShare_Last(long companyId,
		long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByCompanyShare_Last(long companyId,
		long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public HistoricalRealtime[] findByCompanyShare_PrevAndNext(
		long realtimeId, long companyId, long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Removes all the historical realtimes where companyId = &#63; and shareId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	*/
	public void removeByCompanyShare(long companyId, long shareId);

	/**
	* Returns the number of historical realtimes where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @return the number of matching historical realtimes
	*/
	public int countByCompanyShare(long companyId, long shareId);

	/**
	* Returns all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @return the matching historical realtimes
	*/
	public java.util.List<HistoricalRealtime> findByCompanyShareDate(
		long companyId, long shareId, Date createDate);

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
	public java.util.List<HistoricalRealtime> findByCompanyShareDate(
		long companyId, long shareId, Date createDate, int start, int end);

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
	public java.util.List<HistoricalRealtime> findByCompanyShareDate(
		long companyId, long shareId, Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public java.util.List<HistoricalRealtime> findByCompanyShareDate(
		long companyId, long shareId, Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache);

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
	public HistoricalRealtime findByCompanyShareDate_First(long companyId,
		long shareId, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the first historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByCompanyShareDate_First(long companyId,
		long shareId, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public HistoricalRealtime findByCompanyShareDate_Last(long companyId,
		long shareId, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the last historical realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching historical realtime, or <code>null</code> if a matching historical realtime could not be found
	*/
	public HistoricalRealtime fetchByCompanyShareDate_Last(long companyId,
		long shareId, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public HistoricalRealtime[] findByCompanyShareDate_PrevAndNext(
		long realtimeId, long companyId, long shareId, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Removes all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; from the database.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	*/
	public void removeByCompanyShareDate(long companyId, long shareId,
		Date createDate);

	/**
	* Returns the number of historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @return the number of matching historical realtimes
	*/
	public int countByCompanyShareDate(long companyId, long shareId,
		Date createDate);

	/**
	* Returns all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @return the matching historical realtimes
	*/
	public java.util.List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId);

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
	public java.util.List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId, int start,
		int end);

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
	public java.util.List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public java.util.List<HistoricalRealtime> findByCompanySharegGroupDate(
		long companyId, long shareId, Date createDate, long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache);

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
	public HistoricalRealtime findByCompanySharegGroupDate_First(
		long companyId, long shareId, Date createDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

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
	public HistoricalRealtime fetchByCompanySharegGroupDate_First(
		long companyId, long shareId, Date createDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public HistoricalRealtime findByCompanySharegGroupDate_Last(
		long companyId, long shareId, Date createDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

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
	public HistoricalRealtime fetchByCompanySharegGroupDate_Last(
		long companyId, long shareId, Date createDate, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public HistoricalRealtime[] findByCompanySharegGroupDate_PrevAndNext(
		long realtimeId, long companyId, long shareId, Date createDate,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Removes all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	*/
	public void removeByCompanySharegGroupDate(long companyId, long shareId,
		Date createDate, long groupId);

	/**
	* Returns the number of historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @return the number of matching historical realtimes
	*/
	public int countByCompanySharegGroupDate(long companyId, long shareId,
		Date createDate, long groupId);

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
	public java.util.List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice);

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
	public java.util.List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice, int start, int end);

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
	public java.util.List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public java.util.List<HistoricalRealtime> findByCompanySharegGroupClose(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache);

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
	public HistoricalRealtime findByCompanySharegGroupClose_First(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

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
	public HistoricalRealtime fetchByCompanySharegGroupClose_First(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public HistoricalRealtime findByCompanySharegGroupClose_Last(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

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
	public HistoricalRealtime fetchByCompanySharegGroupClose_Last(
		long companyId, long shareId, Date createDate, long groupId,
		boolean closeprice,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public HistoricalRealtime[] findByCompanySharegGroupClose_PrevAndNext(
		long realtimeId, long companyId, long shareId, Date createDate,
		long groupId, boolean closeprice,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Removes all the historical realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; and groupId = &#63; and closeprice = &#63; from the database.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param groupId the group ID
	* @param closeprice the closeprice
	*/
	public void removeByCompanySharegGroupClose(long companyId, long shareId,
		Date createDate, long groupId, boolean closeprice);

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
	public int countByCompanySharegGroupClose(long companyId, long shareId,
		Date createDate, long groupId, boolean closeprice);

	/**
	* Caches the historical realtime in the entity cache if it is enabled.
	*
	* @param historicalRealtime the historical realtime
	*/
	public void cacheResult(HistoricalRealtime historicalRealtime);

	/**
	* Caches the historical realtimes in the entity cache if it is enabled.
	*
	* @param historicalRealtimes the historical realtimes
	*/
	public void cacheResult(
		java.util.List<HistoricalRealtime> historicalRealtimes);

	/**
	* Creates a new historical realtime with the primary key. Does not add the historical realtime to the database.
	*
	* @param realtimeId the primary key for the new historical realtime
	* @return the new historical realtime
	*/
	public HistoricalRealtime create(long realtimeId);

	/**
	* Removes the historical realtime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime that was removed
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public HistoricalRealtime remove(long realtimeId)
		throws NoSuchHistoricalRealtimeException;

	public HistoricalRealtime updateImpl(HistoricalRealtime historicalRealtime);

	/**
	* Returns the historical realtime with the primary key or throws a {@link NoSuchHistoricalRealtimeException} if it could not be found.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime
	* @throws NoSuchHistoricalRealtimeException if a historical realtime with the primary key could not be found
	*/
	public HistoricalRealtime findByPrimaryKey(long realtimeId)
		throws NoSuchHistoricalRealtimeException;

	/**
	* Returns the historical realtime with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param realtimeId the primary key of the historical realtime
	* @return the historical realtime, or <code>null</code> if a historical realtime with the primary key could not be found
	*/
	public HistoricalRealtime fetchByPrimaryKey(long realtimeId);

	@Override
	public java.util.Map<java.io.Serializable, HistoricalRealtime> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the historical realtimes.
	*
	* @return the historical realtimes
	*/
	public java.util.List<HistoricalRealtime> findAll();

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
	public java.util.List<HistoricalRealtime> findAll(int start, int end);

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
	public java.util.List<HistoricalRealtime> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator);

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
	public java.util.List<HistoricalRealtime> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<HistoricalRealtime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the historical realtimes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of historical realtimes.
	*
	* @return the number of historical realtimes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}