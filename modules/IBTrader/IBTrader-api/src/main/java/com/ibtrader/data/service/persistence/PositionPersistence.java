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

import com.ibtrader.data.exception.NoSuchPositionException;
import com.ibtrader.data.model.Position;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the position service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.PositionPersistenceImpl
 * @see PositionUtil
 * @generated
 */
@ProviderType
public interface PositionPersistence extends BasePersistence<Position> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PositionUtil} to access the position persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the positions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching positions
	*/
	public java.util.List<Position> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the positions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the positions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where uuid = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByUuid_PrevAndNext(long positionId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of positions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching positions
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the position where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPositionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPositionException;

	/**
	* Returns the position where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the position where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the position where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the position that was removed
	*/
	public Position removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPositionException;

	/**
	* Returns the number of positions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching positions
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the positions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching positions
	*/
	public java.util.List<Position> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the positions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the positions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByUuid_C_PrevAndNext(long positionId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of positions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching positions
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_real_in the date_real_in
	* @param date_real_out the date_real_out
	* @param date_in the date_in
	* @param date_out the date_out
	* @return the matching positions
	*/
	public java.util.List<Position> findByPositionShareDatesInOut(
		long groupId, long companyId, long shareId, Date date_real_in,
		Date date_real_out, Date date_in, Date date_out);

	/**
	* Returns a range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_real_in the date_real_in
	* @param date_real_out the date_real_out
	* @param date_in the date_in
	* @param date_out the date_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByPositionShareDatesInOut(
		long groupId, long companyId, long shareId, Date date_real_in,
		Date date_real_out, Date date_in, Date date_out, int start, int end);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_real_in the date_real_in
	* @param date_real_out the date_real_out
	* @param date_in the date_in
	* @param date_out the date_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByPositionShareDatesInOut(
		long groupId, long companyId, long shareId, Date date_real_in,
		Date date_real_out, Date date_in, Date date_out, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_real_in the date_real_in
	* @param date_real_out the date_real_out
	* @param date_in the date_in
	* @param date_out the date_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByPositionShareDatesInOut(
		long groupId, long companyId, long shareId, Date date_real_in,
		Date date_real_out, Date date_in, Date date_out, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_real_in the date_real_in
	* @param date_real_out the date_real_out
	* @param date_in the date_in
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByPositionShareDatesInOut_First(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_real_in the date_real_in
	* @param date_real_out the date_real_out
	* @param date_in the date_in
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByPositionShareDatesInOut_First(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_real_in the date_real_in
	* @param date_real_out the date_real_out
	* @param date_in the date_in
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByPositionShareDatesInOut_Last(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_real_in the date_real_in
	* @param date_real_out the date_real_out
	* @param date_in the date_in
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByPositionShareDatesInOut_Last(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_real_in the date_real_in
	* @param date_real_out the date_real_out
	* @param date_in the date_in
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByPositionShareDatesInOut_PrevAndNext(
		long positionId, long groupId, long companyId, long shareId,
		Date date_real_in, Date date_real_out, Date date_in, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_real_in the date_real_in
	* @param date_real_out the date_real_out
	* @param date_in the date_in
	* @param date_out the date_out
	*/
	public void removeByPositionShareDatesInOut(long groupId, long companyId,
		long shareId, Date date_real_in, Date date_real_out, Date date_in,
		Date date_out);

	/**
	* Returns the number of positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_real_in = &#63; and date_real_out = &#63; and date_in = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_real_in the date_real_in
	* @param date_real_out the date_real_out
	* @param date_in the date_in
	* @param date_out the date_out
	* @return the number of matching positions
	*/
	public int countByPositionShareDatesInOut(long groupId, long companyId,
		long shareId, Date date_real_in, Date date_real_out, Date date_in,
		Date date_out);

	/**
	* Returns all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	* @return the matching positions
	*/
	public java.util.List<Position> findByPositionShareStateDateOut(
		long groupId, long companyId, long shareId, java.lang.String state,
		Date date_out);

	/**
	* Returns a range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByPositionShareStateDateOut(
		long groupId, long companyId, long shareId, java.lang.String state,
		Date date_out, int start, int end);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByPositionShareStateDateOut(
		long groupId, long companyId, long shareId, java.lang.String state,
		Date date_out, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByPositionShareStateDateOut(
		long groupId, long companyId, long shareId, java.lang.String state,
		Date date_out, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByPositionShareStateDateOut_First(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByPositionShareStateDateOut_First(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByPositionShareStateDateOut_Last(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByPositionShareStateDateOut_Last(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByPositionShareStateDateOut_PrevAndNext(
		long positionId, long groupId, long companyId, long shareId,
		java.lang.String state, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	*/
	public void removeByPositionShareStateDateOut(long groupId, long companyId,
		long shareId, java.lang.String state, Date date_out);

	/**
	* Returns the number of positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	* @return the number of matching positions
	*/
	public int countByPositionShareStateDateOut(long groupId, long companyId,
		long shareId, java.lang.String state, Date date_out);

	/**
	* Returns all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @return the matching positions
	*/
	public java.util.List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in);

	/**
	* Returns a range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in, int start, int end);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByPositionShareDateIn_First(long groupId,
		long companyId, long shareId, Date date_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByPositionShareDateIn_First(long groupId,
		long companyId, long shareId, Date date_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByPositionShareDateIn_Last(long groupId,
		long companyId, long shareId, Date date_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByPositionShareDateIn_Last(long groupId,
		long companyId, long shareId, Date date_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByPositionShareDateIn_PrevAndNext(long positionId,
		long groupId, long companyId, long shareId, Date date_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	*/
	public void removeByPositionShareDateIn(long groupId, long companyId,
		long shareId, Date date_in);

	/**
	* Returns the number of positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @return the number of matching positions
	*/
	public int countByPositionShareDateIn(long groupId, long companyId,
		long shareId, Date date_in);

	/**
	* Returns all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_out the date_out
	* @return the matching positions
	*/
	public java.util.List<Position> findByPositionShareDateOut(long groupId,
		long companyId, long shareId, Date date_out);

	/**
	* Returns a range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_out the date_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByPositionShareDateOut(long groupId,
		long companyId, long shareId, Date date_out, int start, int end);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_out the date_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByPositionShareDateOut(long groupId,
		long companyId, long shareId, Date date_out, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_out the date_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByPositionShareDateOut(long groupId,
		long companyId, long shareId, Date date_out, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByPositionShareDateOut_First(long groupId,
		long companyId, long shareId, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByPositionShareDateOut_First(long groupId,
		long companyId, long shareId, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByPositionShareDateOut_Last(long groupId,
		long companyId, long shareId, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByPositionShareDateOut_Last(long groupId,
		long companyId, long shareId, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_out the date_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByPositionShareDateOut_PrevAndNext(long positionId,
		long groupId, long companyId, long shareId, Date date_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_out the date_out
	*/
	public void removeByPositionShareDateOut(long groupId, long companyId,
		long shareId, Date date_out);

	/**
	* Returns the number of positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_out the date_out
	* @return the number of matching positions
	*/
	public int countByPositionShareDateOut(long groupId, long companyId,
		long shareId, Date date_out);

	/**
	* Returns all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @return the matching positions
	*/
	public java.util.List<Position> findByCompanyGroupShare(long groupId,
		long companyId, long shareId);

	/**
	* Returns a range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupShare(long groupId,
		long companyId, long shareId, int start, int end);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupShare(long groupId,
		long companyId, long shareId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupShare(long groupId,
		long companyId, long shareId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroupShare_First(long groupId, long companyId,
		long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroupShare_First(long groupId,
		long companyId, long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroupShare_Last(long groupId, long companyId,
		long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroupShare_Last(long groupId, long companyId,
		long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByCompanyGroupShare_PrevAndNext(long positionId,
		long groupId, long companyId, long shareId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	*/
	public void removeByCompanyGroupShare(long groupId, long companyId,
		long shareId);

	/**
	* Returns the number of positions where groupId = &#63; and companyId = &#63; and shareId = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @return the number of matching positions
	*/
	public int countByCompanyGroupShare(long groupId, long companyId,
		long shareId);

	/**
	* Returns all the positions where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the matching positions
	*/
	public java.util.List<Position> findByCompanyGroup(long companyId,
		long groupId);

	/**
	* Returns a range of all the positions where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroup(long companyId,
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroup(long companyId,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroup(long companyId,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroup_First(long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroup_First(long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroup_Last(long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroup_Last(long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByCompanyGroup_PrevAndNext(long positionId,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where companyId = &#63; and groupId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	*/
	public void removeByCompanyGroup(long companyId, long groupId);

	/**
	* Returns the number of positions where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the number of matching positions
	*/
	public int countByCompanyGroup(long companyId, long groupId);

	/**
	* Returns all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @return the matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDate(long companyId,
		long groupId, Date date_in);

	/**
	* Returns a range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDate(long companyId,
		long groupId, Date date_in, int start, int end);

	/**
	* Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDate(long companyId,
		long groupId, Date date_in, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDate(long companyId,
		long groupId, Date date_in, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroupDate_First(long companyId, long groupId,
		Date date_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroupDate_First(long companyId, long groupId,
		Date date_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroupDate_Last(long companyId, long groupId,
		Date date_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroupDate_Last(long companyId, long groupId,
		Date date_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByCompanyGroupDate_PrevAndNext(long positionId,
		long companyId, long groupId, Date date_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	*/
	public void removeByCompanyGroupDate(long companyId, long groupId,
		Date date_in);

	/**
	* Returns the number of positions where companyId = &#63; and groupId = &#63; and date_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @return the number of matching positions
	*/
	public int countByCompanyGroupDate(long companyId, long groupId,
		Date date_in);

	/**
	* Returns all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state the state
	* @return the matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatus(
		long companyId, long groupId, Date date_in, java.lang.String state);

	/**
	* Returns a range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state the state
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatus(
		long companyId, long groupId, Date date_in, java.lang.String state,
		int start, int end);

	/**
	* Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state the state
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatus(
		long companyId, long groupId, Date date_in, java.lang.String state,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state the state
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatus(
		long companyId, long groupId, Date date_in, java.lang.String state,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroupDateStatus_First(long companyId,
		long groupId, Date date_in, java.lang.String state,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroupDateStatus_First(long companyId,
		long groupId, Date date_in, java.lang.String state,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroupDateStatus_Last(long companyId,
		long groupId, Date date_in, java.lang.String state,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroupDateStatus_Last(long companyId,
		long groupId, Date date_in, java.lang.String state,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state the state
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByCompanyGroupDateStatus_PrevAndNext(
		long positionId, long companyId, long groupId, Date date_in,
		java.lang.String state,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state the state
	*/
	public void removeByCompanyGroupDateStatus(long companyId, long groupId,
		Date date_in, java.lang.String state);

	/**
	* Returns the number of positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state the state
	* @return the number of matching positions
	*/
	public int countByCompanyGroupDateStatus(long companyId, long groupId,
		Date date_in, java.lang.String state);

	/**
	* Returns all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state_in the state_in
	* @return the matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatusIn(
		long companyId, long groupId, Date date_in, java.lang.String state_in);

	/**
	* Returns a range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state_in the state_in
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatusIn(
		long companyId, long groupId, Date date_in, java.lang.String state_in,
		int start, int end);

	/**
	* Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state_in the state_in
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatusIn(
		long companyId, long groupId, Date date_in, java.lang.String state_in,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state_in the state_in
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatusIn(
		long companyId, long groupId, Date date_in, java.lang.String state_in,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state_in the state_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroupDateStatusIn_First(long companyId,
		long groupId, Date date_in, java.lang.String state_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state_in the state_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroupDateStatusIn_First(long companyId,
		long groupId, Date date_in, java.lang.String state_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state_in the state_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroupDateStatusIn_Last(long companyId,
		long groupId, Date date_in, java.lang.String state_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state_in the state_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroupDateStatusIn_Last(long companyId,
		long groupId, Date date_in, java.lang.String state_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state_in the state_in
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByCompanyGroupDateStatusIn_PrevAndNext(
		long positionId, long companyId, long groupId, Date date_in,
		java.lang.String state_in,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state_in the state_in
	*/
	public void removeByCompanyGroupDateStatusIn(long companyId, long groupId,
		Date date_in, java.lang.String state_in);

	/**
	* Returns the number of positions where companyId = &#63; and groupId = &#63; and date_in = &#63; and state_in = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_in the date_in
	* @param state_in the state_in
	* @return the number of matching positions
	*/
	public int countByCompanyGroupDateStatusIn(long companyId, long groupId,
		Date date_in, java.lang.String state_in);

	/**
	* Returns all the positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_out the date_out
	* @param state_out the state_out
	* @return the matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatusOut(
		long companyId, long groupId, Date date_out, java.lang.String state_out);

	/**
	* Returns a range of all the positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_out the date_out
	* @param state_out the state_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatusOut(
		long companyId, long groupId, Date date_out,
		java.lang.String state_out, int start, int end);

	/**
	* Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_out the date_out
	* @param state_out the state_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatusOut(
		long companyId, long groupId, Date date_out,
		java.lang.String state_out, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_out the date_out
	* @param state_out the state_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByCompanyGroupDateStatusOut(
		long companyId, long groupId, Date date_out,
		java.lang.String state_out, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_out the date_out
	* @param state_out the state_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroupDateStatusOut_First(long companyId,
		long groupId, Date date_out, java.lang.String state_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_out the date_out
	* @param state_out the state_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroupDateStatusOut_First(long companyId,
		long groupId, Date date_out, java.lang.String state_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_out the date_out
	* @param state_out the state_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByCompanyGroupDateStatusOut_Last(long companyId,
		long groupId, Date date_out, java.lang.String state_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_out the date_out
	* @param state_out the state_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByCompanyGroupDateStatusOut_Last(long companyId,
		long groupId, Date date_out, java.lang.String state_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_out the date_out
	* @param state_out the state_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByCompanyGroupDateStatusOut_PrevAndNext(
		long positionId, long companyId, long groupId, Date date_out,
		java.lang.String state_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_out the date_out
	* @param state_out the state_out
	*/
	public void removeByCompanyGroupDateStatusOut(long companyId, long groupId,
		Date date_out, java.lang.String state_out);

	/**
	* Returns the number of positions where companyId = &#63; and groupId = &#63; and date_out = &#63; and state_out = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param date_out the date_out
	* @param state_out the state_out
	* @return the number of matching positions
	*/
	public int countByCompanyGroupDateStatusOut(long companyId, long groupId,
		Date date_out, java.lang.String state_out);

	/**
	* Returns all the positions where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param positionId_tws_out the position id_tws_out
	* @return the matching positions
	*/
	public java.util.List<Position> findByPositionID_Out_TWS(long groupId,
		long companyId, long positionId_tws_out);

	/**
	* Returns a range of all the positions where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param positionId_tws_out the position id_tws_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public java.util.List<Position> findByPositionID_Out_TWS(long groupId,
		long companyId, long positionId_tws_out, int start, int end);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param positionId_tws_out the position id_tws_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByPositionID_Out_TWS(long groupId,
		long companyId, long positionId_tws_out, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param positionId_tws_out the position id_tws_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public java.util.List<Position> findByPositionID_Out_TWS(long groupId,
		long companyId, long positionId_tws_out, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param positionId_tws_out the position id_tws_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByPositionID_Out_TWS_First(long groupId,
		long companyId, long positionId_tws_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the first position in the ordered set where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param positionId_tws_out the position id_tws_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByPositionID_Out_TWS_First(long groupId,
		long companyId, long positionId_tws_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param positionId_tws_out the position id_tws_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public Position findByPositionID_Out_TWS_Last(long groupId, long companyId,
		long positionId_tws_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Returns the last position in the ordered set where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param positionId_tws_out the position id_tws_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public Position fetchByPositionID_Out_TWS_Last(long groupId,
		long companyId, long positionId_tws_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns the positions before and after the current position in the ordered set where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param groupId the group ID
	* @param companyId the company ID
	* @param positionId_tws_out the position id_tws_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position[] findByPositionID_Out_TWS_PrevAndNext(long positionId,
		long groupId, long companyId, long positionId_tws_out,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator)
		throws NoSuchPositionException;

	/**
	* Removes all the positions where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param positionId_tws_out the position id_tws_out
	*/
	public void removeByPositionID_Out_TWS(long groupId, long companyId,
		long positionId_tws_out);

	/**
	* Returns the number of positions where groupId = &#63; and companyId = &#63; and positionId_tws_out = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param positionId_tws_out the position id_tws_out
	* @return the number of matching positions
	*/
	public int countByPositionID_Out_TWS(long groupId, long companyId,
		long positionId_tws_out);

	/**
	* Caches the position in the entity cache if it is enabled.
	*
	* @param position the position
	*/
	public void cacheResult(Position position);

	/**
	* Caches the positions in the entity cache if it is enabled.
	*
	* @param positions the positions
	*/
	public void cacheResult(java.util.List<Position> positions);

	/**
	* Creates a new position with the primary key. Does not add the position to the database.
	*
	* @param positionId the primary key for the new position
	* @return the new position
	*/
	public Position create(long positionId);

	/**
	* Removes the position with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param positionId the primary key of the position
	* @return the position that was removed
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position remove(long positionId) throws NoSuchPositionException;

	public Position updateImpl(Position position);

	/**
	* Returns the position with the primary key or throws a {@link NoSuchPositionException} if it could not be found.
	*
	* @param positionId the primary key of the position
	* @return the position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public Position findByPrimaryKey(long positionId)
		throws NoSuchPositionException;

	/**
	* Returns the position with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param positionId the primary key of the position
	* @return the position, or <code>null</code> if a position with the primary key could not be found
	*/
	public Position fetchByPrimaryKey(long positionId);

	@Override
	public java.util.Map<java.io.Serializable, Position> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the positions.
	*
	* @return the positions
	*/
	public java.util.List<Position> findAll();

	/**
	* Returns a range of all the positions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of positions
	*/
	public java.util.List<Position> findAll(int start, int end);

	/**
	* Returns an ordered range of all the positions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of positions
	*/
	public java.util.List<Position> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator);

	/**
	* Returns an ordered range of all the positions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of positions
	*/
	public java.util.List<Position> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the positions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of positions.
	*
	* @return the number of positions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}