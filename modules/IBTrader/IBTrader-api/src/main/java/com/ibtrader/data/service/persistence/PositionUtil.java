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

import com.ibtrader.data.model.Position;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the position service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.PositionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PositionPersistence
 * @see com.ibtrader.data.service.persistence.impl.PositionPersistenceImpl
 * @generated
 */
@ProviderType
public class PositionUtil {
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
	public static void clearCache(Position position) {
		getPersistence().clearCache(position);
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
	public static List<Position> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Position> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Position> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Position update(Position position) {
		return getPersistence().update(position);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Position update(Position position,
		ServiceContext serviceContext) {
		return getPersistence().update(position, serviceContext);
	}

	/**
	* Returns all the positions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching positions
	*/
	public static List<Position> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<Position> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<Position> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Position> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<Position> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first position in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public static Position findByUuid_First(java.lang.String uuid,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first position in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public static Position fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last position in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public static Position findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last position in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public static Position fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the positions before and after the current position in the ordered set where uuid = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public static Position[] findByUuid_PrevAndNext(long positionId,
		java.lang.String uuid, OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(positionId, uuid, orderByComparator);
	}

	/**
	* Removes all the positions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of positions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching positions
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the position where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPositionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public static Position findByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the position where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching position, or <code>null</code> if a matching position could not be found
	*/
	public static Position fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the position where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching position, or <code>null</code> if a matching position could not be found
	*/
	public static Position fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the position where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the position that was removed
	*/
	public static Position removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of positions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching positions
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the positions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching positions
	*/
	public static List<Position> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<Position> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<Position> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<Position> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first position in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public static Position findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first position in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public static Position fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last position in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public static Position findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last position in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public static Position fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static Position[] findByUuid_C_PrevAndNext(long positionId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(positionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the positions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of positions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching positions
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

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
	public static List<Position> findByPositionShareDatesInOut(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out) {
		return getPersistence()
				   .findByPositionShareDatesInOut(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out);
	}

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
	public static List<Position> findByPositionShareDatesInOut(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out, int start, int end) {
		return getPersistence()
				   .findByPositionShareDatesInOut(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, start, end);
	}

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
	public static List<Position> findByPositionShareDatesInOut(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .findByPositionShareDatesInOut(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, start, end,
			orderByComparator);
	}

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
	public static List<Position> findByPositionShareDatesInOut(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByPositionShareDatesInOut(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static Position findByPositionShareDatesInOut_First(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionShareDatesInOut_First(groupId, companyId,
			shareId, date_real_in, date_real_out, date_in, date_out,
			orderByComparator);
	}

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
	public static Position fetchByPositionShareDatesInOut_First(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByPositionShareDatesInOut_First(groupId, companyId,
			shareId, date_real_in, date_real_out, date_in, date_out,
			orderByComparator);
	}

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
	public static Position findByPositionShareDatesInOut_Last(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionShareDatesInOut_Last(groupId, companyId,
			shareId, date_real_in, date_real_out, date_in, date_out,
			orderByComparator);
	}

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
	public static Position fetchByPositionShareDatesInOut_Last(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByPositionShareDatesInOut_Last(groupId, companyId,
			shareId, date_real_in, date_real_out, date_in, date_out,
			orderByComparator);
	}

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
	public static Position[] findByPositionShareDatesInOut_PrevAndNext(
		long positionId, long groupId, long companyId, long shareId,
		Date date_real_in, Date date_real_out, Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionShareDatesInOut_PrevAndNext(positionId,
			groupId, companyId, shareId, date_real_in, date_real_out, date_in,
			date_out, orderByComparator);
	}

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
	public static void removeByPositionShareDatesInOut(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out) {
		getPersistence()
			.removeByPositionShareDatesInOut(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out);
	}

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
	public static int countByPositionShareDatesInOut(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out) {
		return getPersistence()
				   .countByPositionShareDatesInOut(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out);
	}

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
	public static List<Position> findByPositionShareStateDateOut(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out) {
		return getPersistence()
				   .findByPositionShareStateDateOut(groupId, companyId,
			shareId, state, date_out);
	}

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
	public static List<Position> findByPositionShareStateDateOut(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out,
		int start, int end) {
		return getPersistence()
				   .findByPositionShareStateDateOut(groupId, companyId,
			shareId, state, date_out, start, end);
	}

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
	public static List<Position> findByPositionShareStateDateOut(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out,
		int start, int end, OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .findByPositionShareStateDateOut(groupId, companyId,
			shareId, state, date_out, start, end, orderByComparator);
	}

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
	public static List<Position> findByPositionShareStateDateOut(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out,
		int start, int end, OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPositionShareStateDateOut(groupId, companyId,
			shareId, state, date_out, start, end, orderByComparator,
			retrieveFromCache);
	}

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
	public static Position findByPositionShareStateDateOut_First(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionShareStateDateOut_First(groupId, companyId,
			shareId, state, date_out, orderByComparator);
	}

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
	public static Position fetchByPositionShareStateDateOut_First(
		long groupId, long companyId, long shareId, java.lang.String state,
		Date date_out, OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByPositionShareStateDateOut_First(groupId, companyId,
			shareId, state, date_out, orderByComparator);
	}

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
	public static Position findByPositionShareStateDateOut_Last(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionShareStateDateOut_Last(groupId, companyId,
			shareId, state, date_out, orderByComparator);
	}

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
	public static Position fetchByPositionShareStateDateOut_Last(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByPositionShareStateDateOut_Last(groupId, companyId,
			shareId, state, date_out, orderByComparator);
	}

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
	public static Position[] findByPositionShareStateDateOut_PrevAndNext(
		long positionId, long groupId, long companyId, long shareId,
		java.lang.String state, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionShareStateDateOut_PrevAndNext(positionId,
			groupId, companyId, shareId, state, date_out, orderByComparator);
	}

	/**
	* Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and state = &#63; and date_out = &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param state the state
	* @param date_out the date_out
	*/
	public static void removeByPositionShareStateDateOut(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out) {
		getPersistence()
			.removeByPositionShareStateDateOut(groupId, companyId, shareId,
			state, date_out);
	}

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
	public static int countByPositionShareStateDateOut(long groupId,
		long companyId, long shareId, java.lang.String state, Date date_out) {
		return getPersistence()
				   .countByPositionShareStateDateOut(groupId, companyId,
			shareId, state, date_out);
	}

	/**
	* Returns all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @return the matching positions
	*/
	public static List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in) {
		return getPersistence()
				   .findByPositionShareDateIn(groupId, companyId, shareId,
			date_in);
	}

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
	public static List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in, int start, int end) {
		return getPersistence()
				   .findByPositionShareDateIn(groupId, companyId, shareId,
			date_in, start, end);
	}

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
	public static List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .findByPositionShareDateIn(groupId, companyId, shareId,
			date_in, start, end, orderByComparator);
	}

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
	public static List<Position> findByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByPositionShareDateIn(groupId, companyId, shareId,
			date_in, start, end, orderByComparator, retrieveFromCache);
	}

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
	public static Position findByPositionShareDateIn_First(long groupId,
		long companyId, long shareId, Date date_in,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionShareDateIn_First(groupId, companyId,
			shareId, date_in, orderByComparator);
	}

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
	public static Position fetchByPositionShareDateIn_First(long groupId,
		long companyId, long shareId, Date date_in,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByPositionShareDateIn_First(groupId, companyId,
			shareId, date_in, orderByComparator);
	}

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
	public static Position findByPositionShareDateIn_Last(long groupId,
		long companyId, long shareId, Date date_in,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionShareDateIn_Last(groupId, companyId, shareId,
			date_in, orderByComparator);
	}

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
	public static Position fetchByPositionShareDateIn_Last(long groupId,
		long companyId, long shareId, Date date_in,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByPositionShareDateIn_Last(groupId, companyId,
			shareId, date_in, orderByComparator);
	}

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
	public static Position[] findByPositionShareDateIn_PrevAndNext(
		long positionId, long groupId, long companyId, long shareId,
		Date date_in, OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionShareDateIn_PrevAndNext(positionId, groupId,
			companyId, shareId, date_in, orderByComparator);
	}

	/**
	* Removes all the positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	*/
	public static void removeByPositionShareDateIn(long groupId,
		long companyId, long shareId, Date date_in) {
		getPersistence()
			.removeByPositionShareDateIn(groupId, companyId, shareId, date_in);
	}

	/**
	* Returns the number of positions where groupId = &#63; and companyId = &#63; and shareId = &#63; and date_in = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param shareId the share ID
	* @param date_in the date_in
	* @return the number of matching positions
	*/
	public static int countByPositionShareDateIn(long groupId, long companyId,
		long shareId, Date date_in) {
		return getPersistence()
				   .countByPositionShareDateIn(groupId, companyId, shareId,
			date_in);
	}

	/**
	* Returns all the positions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching positions
	*/
	public static List<Position> findByCompany(long companyId) {
		return getPersistence().findByCompany(companyId);
	}

	/**
	* Returns a range of all the positions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public static List<Position> findByCompany(long companyId, int start,
		int end) {
		return getPersistence().findByCompany(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the positions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public static List<Position> findByCompany(long companyId, int start,
		int end, OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .findByCompany(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the positions where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public static List<Position> findByCompany(long companyId, int start,
		int end, OrderByComparator<Position> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompany(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first position in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public static Position findByCompany_First(long companyId,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence().findByCompany_First(companyId, orderByComparator);
	}

	/**
	* Returns the first position in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public static Position fetchByCompany_First(long companyId,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByCompany_First(companyId, orderByComparator);
	}

	/**
	* Returns the last position in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public static Position findByCompany_Last(long companyId,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence().findByCompany_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last position in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public static Position fetchByCompany_Last(long companyId,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence().fetchByCompany_Last(companyId, orderByComparator);
	}

	/**
	* Returns the positions before and after the current position in the ordered set where companyId = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public static Position[] findByCompany_PrevAndNext(long positionId,
		long companyId, OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByCompany_PrevAndNext(positionId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the positions where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompany(long companyId) {
		getPersistence().removeByCompany(companyId);
	}

	/**
	* Returns the number of positions where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching positions
	*/
	public static int countByCompany(long companyId) {
		return getPersistence().countByCompany(companyId);
	}

	/**
	* Returns all the positions where positionId_tws_out = &#63;.
	*
	* @param positionId_tws_out the position id_tws_out
	* @return the matching positions
	*/
	public static List<Position> findByPositionID_Out_TWS(
		long positionId_tws_out) {
		return getPersistence().findByPositionID_Out_TWS(positionId_tws_out);
	}

	/**
	* Returns a range of all the positions where positionId_tws_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param positionId_tws_out the position id_tws_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of matching positions
	*/
	public static List<Position> findByPositionID_Out_TWS(
		long positionId_tws_out, int start, int end) {
		return getPersistence()
				   .findByPositionID_Out_TWS(positionId_tws_out, start, end);
	}

	/**
	* Returns an ordered range of all the positions where positionId_tws_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param positionId_tws_out the position id_tws_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching positions
	*/
	public static List<Position> findByPositionID_Out_TWS(
		long positionId_tws_out, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .findByPositionID_Out_TWS(positionId_tws_out, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the positions where positionId_tws_out = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param positionId_tws_out the position id_tws_out
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching positions
	*/
	public static List<Position> findByPositionID_Out_TWS(
		long positionId_tws_out, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByPositionID_Out_TWS(positionId_tws_out, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first position in the ordered set where positionId_tws_out = &#63;.
	*
	* @param positionId_tws_out the position id_tws_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public static Position findByPositionID_Out_TWS_First(
		long positionId_tws_out, OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionID_Out_TWS_First(positionId_tws_out,
			orderByComparator);
	}

	/**
	* Returns the first position in the ordered set where positionId_tws_out = &#63;.
	*
	* @param positionId_tws_out the position id_tws_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching position, or <code>null</code> if a matching position could not be found
	*/
	public static Position fetchByPositionID_Out_TWS_First(
		long positionId_tws_out, OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByPositionID_Out_TWS_First(positionId_tws_out,
			orderByComparator);
	}

	/**
	* Returns the last position in the ordered set where positionId_tws_out = &#63;.
	*
	* @param positionId_tws_out the position id_tws_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position
	* @throws NoSuchPositionException if a matching position could not be found
	*/
	public static Position findByPositionID_Out_TWS_Last(
		long positionId_tws_out, OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionID_Out_TWS_Last(positionId_tws_out,
			orderByComparator);
	}

	/**
	* Returns the last position in the ordered set where positionId_tws_out = &#63;.
	*
	* @param positionId_tws_out the position id_tws_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching position, or <code>null</code> if a matching position could not be found
	*/
	public static Position fetchByPositionID_Out_TWS_Last(
		long positionId_tws_out, OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByPositionID_Out_TWS_Last(positionId_tws_out,
			orderByComparator);
	}

	/**
	* Returns the positions before and after the current position in the ordered set where positionId_tws_out = &#63;.
	*
	* @param positionId the primary key of the current position
	* @param positionId_tws_out the position id_tws_out
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public static Position[] findByPositionID_Out_TWS_PrevAndNext(
		long positionId, long positionId_tws_out,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionID_Out_TWS_PrevAndNext(positionId,
			positionId_tws_out, orderByComparator);
	}

	/**
	* Removes all the positions where positionId_tws_out = &#63; from the database.
	*
	* @param positionId_tws_out the position id_tws_out
	*/
	public static void removeByPositionID_Out_TWS(long positionId_tws_out) {
		getPersistence().removeByPositionID_Out_TWS(positionId_tws_out);
	}

	/**
	* Returns the number of positions where positionId_tws_out = &#63;.
	*
	* @param positionId_tws_out the position id_tws_out
	* @return the number of matching positions
	*/
	public static int countByPositionID_Out_TWS(long positionId_tws_out) {
		return getPersistence().countByPositionID_Out_TWS(positionId_tws_out);
	}

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
	public static List<Position> findByPositionbyID(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out) {
		return getPersistence()
				   .findByPositionbyID(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out);
	}

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
	public static List<Position> findByPositionbyID(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out, int start, int end) {
		return getPersistence()
				   .findByPositionbyID(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, start, end);
	}

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
	public static List<Position> findByPositionbyID(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out, int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .findByPositionbyID(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, start, end,
			orderByComparator);
	}

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
	public static List<Position> findByPositionbyID(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out, int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByPositionbyID(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static Position findByPositionbyID_First(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionbyID_First(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, orderByComparator);
	}

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
	public static Position fetchByPositionbyID_First(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByPositionbyID_First(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, orderByComparator);
	}

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
	public static Position findByPositionbyID_Last(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionbyID_Last(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, orderByComparator);
	}

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
	public static Position fetchByPositionbyID_Last(long groupId,
		long companyId, long shareId, Date date_real_in, Date date_real_out,
		Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence()
				   .fetchByPositionbyID_Last(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out, orderByComparator);
	}

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
	public static Position[] findByPositionbyID_PrevAndNext(long positionId,
		long groupId, long companyId, long shareId, Date date_real_in,
		Date date_real_out, Date date_in, Date date_out,
		OrderByComparator<Position> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence()
				   .findByPositionbyID_PrevAndNext(positionId, groupId,
			companyId, shareId, date_real_in, date_real_out, date_in, date_out,
			orderByComparator);
	}

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
	public static void removeByPositionbyID(long groupId, long companyId,
		long shareId, Date date_real_in, Date date_real_out, Date date_in,
		Date date_out) {
		getPersistence()
			.removeByPositionbyID(groupId, companyId, shareId, date_real_in,
			date_real_out, date_in, date_out);
	}

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
	public static int countByPositionbyID(long groupId, long companyId,
		long shareId, Date date_real_in, Date date_real_out, Date date_in,
		Date date_out) {
		return getPersistence()
				   .countByPositionbyID(groupId, companyId, shareId,
			date_real_in, date_real_out, date_in, date_out);
	}

	/**
	* Caches the position in the entity cache if it is enabled.
	*
	* @param position the position
	*/
	public static void cacheResult(Position position) {
		getPersistence().cacheResult(position);
	}

	/**
	* Caches the positions in the entity cache if it is enabled.
	*
	* @param positions the positions
	*/
	public static void cacheResult(List<Position> positions) {
		getPersistence().cacheResult(positions);
	}

	/**
	* Creates a new position with the primary key. Does not add the position to the database.
	*
	* @param positionId the primary key for the new position
	* @return the new position
	*/
	public static Position create(long positionId) {
		return getPersistence().create(positionId);
	}

	/**
	* Removes the position with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param positionId the primary key of the position
	* @return the position that was removed
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public static Position remove(long positionId)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence().remove(positionId);
	}

	public static Position updateImpl(Position position) {
		return getPersistence().updateImpl(position);
	}

	/**
	* Returns the position with the primary key or throws a {@link NoSuchPositionException} if it could not be found.
	*
	* @param positionId the primary key of the position
	* @return the position
	* @throws NoSuchPositionException if a position with the primary key could not be found
	*/
	public static Position findByPrimaryKey(long positionId)
		throws com.ibtrader.data.exception.NoSuchPositionException {
		return getPersistence().findByPrimaryKey(positionId);
	}

	/**
	* Returns the position with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param positionId the primary key of the position
	* @return the position, or <code>null</code> if a position with the primary key could not be found
	*/
	public static Position fetchByPrimaryKey(long positionId) {
		return getPersistence().fetchByPrimaryKey(positionId);
	}

	public static java.util.Map<java.io.Serializable, Position> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the positions.
	*
	* @return the positions
	*/
	public static List<Position> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Position> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Position> findAll(int start, int end,
		OrderByComparator<Position> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Position> findAll(int start, int end,
		OrderByComparator<Position> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the positions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of positions.
	*
	* @return the number of positions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PositionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PositionPersistence, PositionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(PositionPersistence.class);
}