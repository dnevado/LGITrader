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