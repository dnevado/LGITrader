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

import com.ibtrader.data.exception.NoSuchShareException;
import com.ibtrader.data.model.Share;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the share service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.SharePersistenceImpl
 * @see ShareUtil
 * @generated
 */
@ProviderType
public interface SharePersistence extends BasePersistence<Share> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ShareUtil} to access the share persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching shares
	*/
	public java.util.List<Share> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of matching shares
	*/
	public java.util.List<Share> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching shares
	*/
	public java.util.List<Share> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator);

	/**
	* Returns an ordered range of all the shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching shares
	*/
	public java.util.List<Share> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public Share findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException;

	/**
	* Returns the first share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share, or <code>null</code> if a matching share could not be found
	*/
	public Share fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator);

	/**
	* Returns the last share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public Share findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException;

	/**
	* Returns the last share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share, or <code>null</code> if a matching share could not be found
	*/
	public Share fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator);

	/**
	* Returns the shares before and after the current share in the ordered set where uuid = &#63;.
	*
	* @param shareId the primary key of the current share
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next share
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public Share[] findByUuid_PrevAndNext(long shareId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException;

	/**
	* Removes all the shares where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching shares
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the share where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchShareException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public Share findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchShareException;

	/**
	* Returns the share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching share, or <code>null</code> if a matching share could not be found
	*/
	public Share fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching share, or <code>null</code> if a matching share could not be found
	*/
	public Share fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the share where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the share that was removed
	*/
	public Share removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchShareException;

	/**
	* Returns the number of shares where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching shares
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching shares
	*/
	public java.util.List<Share> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of matching shares
	*/
	public java.util.List<Share> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching shares
	*/
	public java.util.List<Share> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator);

	/**
	* Returns an ordered range of all the shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching shares
	*/
	public java.util.List<Share> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public Share findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException;

	/**
	* Returns the first share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share, or <code>null</code> if a matching share could not be found
	*/
	public Share fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator);

	/**
	* Returns the last share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public Share findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException;

	/**
	* Returns the last share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share, or <code>null</code> if a matching share could not be found
	*/
	public Share fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator);

	/**
	* Returns the shares before and after the current share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param shareId the primary key of the current share
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next share
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public Share[] findByUuid_C_PrevAndNext(long shareId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException;

	/**
	* Removes all the shares where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching shares
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the shares where active = &#63; and marketId = &#63;.
	*
	* @param active the active
	* @param marketId the market ID
	* @return the matching shares
	*/
	public java.util.List<Share> findByActiveMarket(boolean active,
		long marketId);

	/**
	* Returns a range of all the shares where active = &#63; and marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param marketId the market ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of matching shares
	*/
	public java.util.List<Share> findByActiveMarket(boolean active,
		long marketId, int start, int end);

	/**
	* Returns an ordered range of all the shares where active = &#63; and marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param marketId the market ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching shares
	*/
	public java.util.List<Share> findByActiveMarket(boolean active,
		long marketId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator);

	/**
	* Returns an ordered range of all the shares where active = &#63; and marketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param active the active
	* @param marketId the market ID
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching shares
	*/
	public java.util.List<Share> findByActiveMarket(boolean active,
		long marketId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first share in the ordered set where active = &#63; and marketId = &#63;.
	*
	* @param active the active
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public Share findByActiveMarket_First(boolean active, long marketId,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException;

	/**
	* Returns the first share in the ordered set where active = &#63; and marketId = &#63;.
	*
	* @param active the active
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching share, or <code>null</code> if a matching share could not be found
	*/
	public Share fetchByActiveMarket_First(boolean active, long marketId,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator);

	/**
	* Returns the last share in the ordered set where active = &#63; and marketId = &#63;.
	*
	* @param active the active
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share
	* @throws NoSuchShareException if a matching share could not be found
	*/
	public Share findByActiveMarket_Last(boolean active, long marketId,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException;

	/**
	* Returns the last share in the ordered set where active = &#63; and marketId = &#63;.
	*
	* @param active the active
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching share, or <code>null</code> if a matching share could not be found
	*/
	public Share fetchByActiveMarket_Last(boolean active, long marketId,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator);

	/**
	* Returns the shares before and after the current share in the ordered set where active = &#63; and marketId = &#63;.
	*
	* @param shareId the primary key of the current share
	* @param active the active
	* @param marketId the market ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next share
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public Share[] findByActiveMarket_PrevAndNext(long shareId, boolean active,
		long marketId,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator)
		throws NoSuchShareException;

	/**
	* Removes all the shares where active = &#63; and marketId = &#63; from the database.
	*
	* @param active the active
	* @param marketId the market ID
	*/
	public void removeByActiveMarket(boolean active, long marketId);

	/**
	* Returns the number of shares where active = &#63; and marketId = &#63;.
	*
	* @param active the active
	* @param marketId the market ID
	* @return the number of matching shares
	*/
	public int countByActiveMarket(boolean active, long marketId);

	/**
	* Caches the share in the entity cache if it is enabled.
	*
	* @param share the share
	*/
	public void cacheResult(Share share);

	/**
	* Caches the shares in the entity cache if it is enabled.
	*
	* @param shares the shares
	*/
	public void cacheResult(java.util.List<Share> shares);

	/**
	* Creates a new share with the primary key. Does not add the share to the database.
	*
	* @param shareId the primary key for the new share
	* @return the new share
	*/
	public Share create(long shareId);

	/**
	* Removes the share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param shareId the primary key of the share
	* @return the share that was removed
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public Share remove(long shareId) throws NoSuchShareException;

	public Share updateImpl(Share share);

	/**
	* Returns the share with the primary key or throws a {@link NoSuchShareException} if it could not be found.
	*
	* @param shareId the primary key of the share
	* @return the share
	* @throws NoSuchShareException if a share with the primary key could not be found
	*/
	public Share findByPrimaryKey(long shareId) throws NoSuchShareException;

	/**
	* Returns the share with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param shareId the primary key of the share
	* @return the share, or <code>null</code> if a share with the primary key could not be found
	*/
	public Share fetchByPrimaryKey(long shareId);

	@Override
	public java.util.Map<java.io.Serializable, Share> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the shares.
	*
	* @return the shares
	*/
	public java.util.List<Share> findAll();

	/**
	* Returns a range of all the shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @return the range of shares
	*/
	public java.util.List<Share> findAll(int start, int end);

	/**
	* Returns an ordered range of all the shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of shares
	*/
	public java.util.List<Share> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator);

	/**
	* Returns an ordered range of all the shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of shares
	* @param end the upper bound of the range of shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of shares
	*/
	public java.util.List<Share> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Share> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the shares from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of shares.
	*
	* @return the number of shares
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}