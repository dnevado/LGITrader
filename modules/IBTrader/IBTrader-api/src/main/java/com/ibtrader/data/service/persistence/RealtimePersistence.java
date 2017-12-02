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

import com.ibtrader.data.exception.NoSuchRealtimeException;
import com.ibtrader.data.model.Realtime;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the realtime service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.RealtimePersistenceImpl
 * @see RealtimeUtil
 * @generated
 */
@ProviderType
public interface RealtimePersistence extends BasePersistence<Realtime> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RealtimeUtil} to access the realtime persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the realtimes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching realtimes
	*/
	public java.util.List<Realtime> findByUuid(java.lang.String uuid);

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
	public java.util.List<Realtime> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<Realtime> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

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
	public java.util.List<Realtime> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public Realtime findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Returns the first realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public Realtime fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

	/**
	* Returns the last realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public Realtime findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Returns the last realtime in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public Realtime fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

	/**
	* Returns the realtimes before and after the current realtime in the ordered set where uuid = &#63;.
	*
	* @param realtimeId the primary key of the current realtime
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next realtime
	* @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	*/
	public Realtime[] findByUuid_PrevAndNext(long realtimeId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Removes all the realtimes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of realtimes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching realtimes
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the realtime where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRealtimeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public Realtime findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchRealtimeException;

	/**
	* Returns the realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public Realtime fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the realtime where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public Realtime fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the realtime where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the realtime that was removed
	*/
	public Realtime removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchRealtimeException;

	/**
	* Returns the number of realtimes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching realtimes
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the realtimes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching realtimes
	*/
	public java.util.List<Realtime> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<Realtime> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<Realtime> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

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
	public java.util.List<Realtime> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public Realtime findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Returns the first realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public Realtime fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

	/**
	* Returns the last realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public Realtime findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Returns the last realtime in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public Realtime fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

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
	public Realtime[] findByUuid_C_PrevAndNext(long realtimeId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Removes all the realtimes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of realtimes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching realtimes
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the realtimes where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @return the matching realtimes
	*/
	public java.util.List<Realtime> findByCompanyShare(long companyId,
		long shareId);

	/**
	* Returns a range of all the realtimes where companyId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @return the range of matching realtimes
	*/
	public java.util.List<Realtime> findByCompanyShare(long companyId,
		long shareId, int start, int end);

	/**
	* Returns an ordered range of all the realtimes where companyId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching realtimes
	*/
	public java.util.List<Realtime> findByCompanyShare(long companyId,
		long shareId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

	/**
	* Returns an ordered range of all the realtimes where companyId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching realtimes
	*/
	public java.util.List<Realtime> findByCompanyShare(long companyId,
		long shareId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public Realtime findByCompanyShare_First(long companyId, long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Returns the first realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public Realtime fetchByCompanyShare_First(long companyId, long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

	/**
	* Returns the last realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public Realtime findByCompanyShare_Last(long companyId, long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Returns the last realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public Realtime fetchByCompanyShare_Last(long companyId, long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

	/**
	* Returns the realtimes before and after the current realtime in the ordered set where companyId = &#63; and shareId = &#63;.
	*
	* @param realtimeId the primary key of the current realtime
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next realtime
	* @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	*/
	public Realtime[] findByCompanyShare_PrevAndNext(long realtimeId,
		long companyId, long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Removes all the realtimes where companyId = &#63; and shareId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	*/
	public void removeByCompanyShare(long companyId, long shareId);

	/**
	* Returns the number of realtimes where companyId = &#63; and shareId = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @return the number of matching realtimes
	*/
	public int countByCompanyShare(long companyId, long shareId);

	/**
	* Returns all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @return the matching realtimes
	*/
	public java.util.List<Realtime> findByCompanyShareDate(long companyId,
		long shareId, Date createDate);

	/**
	* Returns a range of all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @return the range of matching realtimes
	*/
	public java.util.List<Realtime> findByCompanyShareDate(long companyId,
		long shareId, Date createDate, int start, int end);

	/**
	* Returns an ordered range of all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching realtimes
	*/
	public java.util.List<Realtime> findByCompanyShareDate(long companyId,
		long shareId, Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

	/**
	* Returns an ordered range of all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RealtimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param start the lower bound of the range of realtimes
	* @param end the upper bound of the range of realtimes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching realtimes
	*/
	public java.util.List<Realtime> findByCompanyShareDate(long companyId,
		long shareId, Date createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public Realtime findByCompanyShareDate_First(long companyId, long shareId,
		Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Returns the first realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public Realtime fetchByCompanyShareDate_First(long companyId, long shareId,
		Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

	/**
	* Returns the last realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime
	* @throws NoSuchRealtimeException if a matching realtime could not be found
	*/
	public Realtime findByCompanyShareDate_Last(long companyId, long shareId,
		Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Returns the last realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching realtime, or <code>null</code> if a matching realtime could not be found
	*/
	public Realtime fetchByCompanyShareDate_Last(long companyId, long shareId,
		Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

	/**
	* Returns the realtimes before and after the current realtime in the ordered set where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param realtimeId the primary key of the current realtime
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next realtime
	* @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	*/
	public Realtime[] findByCompanyShareDate_PrevAndNext(long realtimeId,
		long companyId, long shareId, Date createDate,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator)
		throws NoSuchRealtimeException;

	/**
	* Removes all the realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63; from the database.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	*/
	public void removeByCompanyShareDate(long companyId, long shareId,
		Date createDate);

	/**
	* Returns the number of realtimes where companyId = &#63; and shareId = &#63; and createDate = &#63;.
	*
	* @param companyId the company ID
	* @param shareId the share ID
	* @param createDate the create date
	* @return the number of matching realtimes
	*/
	public int countByCompanyShareDate(long companyId, long shareId,
		Date createDate);

	/**
	* Caches the realtime in the entity cache if it is enabled.
	*
	* @param realtime the realtime
	*/
	public void cacheResult(Realtime realtime);

	/**
	* Caches the realtimes in the entity cache if it is enabled.
	*
	* @param realtimes the realtimes
	*/
	public void cacheResult(java.util.List<Realtime> realtimes);

	/**
	* Creates a new realtime with the primary key. Does not add the realtime to the database.
	*
	* @param realtimeId the primary key for the new realtime
	* @return the new realtime
	*/
	public Realtime create(long realtimeId);

	/**
	* Removes the realtime with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param realtimeId the primary key of the realtime
	* @return the realtime that was removed
	* @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	*/
	public Realtime remove(long realtimeId) throws NoSuchRealtimeException;

	public Realtime updateImpl(Realtime realtime);

	/**
	* Returns the realtime with the primary key or throws a {@link NoSuchRealtimeException} if it could not be found.
	*
	* @param realtimeId the primary key of the realtime
	* @return the realtime
	* @throws NoSuchRealtimeException if a realtime with the primary key could not be found
	*/
	public Realtime findByPrimaryKey(long realtimeId)
		throws NoSuchRealtimeException;

	/**
	* Returns the realtime with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param realtimeId the primary key of the realtime
	* @return the realtime, or <code>null</code> if a realtime with the primary key could not be found
	*/
	public Realtime fetchByPrimaryKey(long realtimeId);

	@Override
	public java.util.Map<java.io.Serializable, Realtime> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the realtimes.
	*
	* @return the realtimes
	*/
	public java.util.List<Realtime> findAll();

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
	public java.util.List<Realtime> findAll(int start, int end);

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
	public java.util.List<Realtime> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator);

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
	public java.util.List<Realtime> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Realtime> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the realtimes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of realtimes.
	*
	* @return the number of realtimes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}