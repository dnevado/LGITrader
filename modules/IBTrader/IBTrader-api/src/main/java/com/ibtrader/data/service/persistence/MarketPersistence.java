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

import com.ibtrader.data.exception.NoSuchMarketException;
import com.ibtrader.data.model.Market;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the market service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.MarketPersistenceImpl
 * @see MarketUtil
 * @generated
 */
@ProviderType
public interface MarketPersistence extends BasePersistence<Market> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MarketUtil} to access the market persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the markets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching markets
	*/
	public java.util.List<Market> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the markets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @return the range of matching markets
	*/
	public java.util.List<Market> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the markets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching markets
	*/
	public java.util.List<Market> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator);

	/**
	* Returns an ordered range of all the markets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching markets
	*/
	public java.util.List<Market> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first market in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public Market findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException;

	/**
	* Returns the first market in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market, or <code>null</code> if a matching market could not be found
	*/
	public Market fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator);

	/**
	* Returns the last market in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public Market findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException;

	/**
	* Returns the last market in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market, or <code>null</code> if a matching market could not be found
	*/
	public Market fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator);

	/**
	* Returns the markets before and after the current market in the ordered set where uuid = &#63;.
	*
	* @param marketId the primary key of the current market
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next market
	* @throws NoSuchMarketException if a market with the primary key could not be found
	*/
	public Market[] findByUuid_PrevAndNext(long marketId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException;

	/**
	* Removes all the markets where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of markets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching markets
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the market where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchMarketException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public Market findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchMarketException;

	/**
	* Returns the market where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching market, or <code>null</code> if a matching market could not be found
	*/
	public Market fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the market where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching market, or <code>null</code> if a matching market could not be found
	*/
	public Market fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the market where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the market that was removed
	*/
	public Market removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchMarketException;

	/**
	* Returns the number of markets where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching markets
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the markets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching markets
	*/
	public java.util.List<Market> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the markets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @return the range of matching markets
	*/
	public java.util.List<Market> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the markets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching markets
	*/
	public java.util.List<Market> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator);

	/**
	* Returns an ordered range of all the markets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching markets
	*/
	public java.util.List<Market> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first market in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public Market findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException;

	/**
	* Returns the first market in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market, or <code>null</code> if a matching market could not be found
	*/
	public Market fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator);

	/**
	* Returns the last market in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public Market findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException;

	/**
	* Returns the last market in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market, or <code>null</code> if a matching market could not be found
	*/
	public Market fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator);

	/**
	* Returns the markets before and after the current market in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param marketId the primary key of the current market
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next market
	* @throws NoSuchMarketException if a market with the primary key could not be found
	*/
	public Market[] findByUuid_C_PrevAndNext(long marketId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException;

	/**
	* Removes all the markets where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of markets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching markets
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the markets where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @return the matching markets
	*/
	public java.util.List<Market> findByActiveStartEndHour(
		java.lang.String start_hour, java.lang.String end_hour, boolean active);

	/**
	* Returns a range of all the markets where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @return the range of matching markets
	*/
	public java.util.List<Market> findByActiveStartEndHour(
		java.lang.String start_hour, java.lang.String end_hour, boolean active,
		int start, int end);

	/**
	* Returns an ordered range of all the markets where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching markets
	*/
	public java.util.List<Market> findByActiveStartEndHour(
		java.lang.String start_hour, java.lang.String end_hour, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator);

	/**
	* Returns an ordered range of all the markets where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching markets
	*/
	public java.util.List<Market> findByActiveStartEndHour(
		java.lang.String start_hour, java.lang.String end_hour, boolean active,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first market in the ordered set where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public Market findByActiveStartEndHour_First(java.lang.String start_hour,
		java.lang.String end_hour, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException;

	/**
	* Returns the first market in the ordered set where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market, or <code>null</code> if a matching market could not be found
	*/
	public Market fetchByActiveStartEndHour_First(java.lang.String start_hour,
		java.lang.String end_hour, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator);

	/**
	* Returns the last market in the ordered set where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public Market findByActiveStartEndHour_Last(java.lang.String start_hour,
		java.lang.String end_hour, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException;

	/**
	* Returns the last market in the ordered set where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market, or <code>null</code> if a matching market could not be found
	*/
	public Market fetchByActiveStartEndHour_Last(java.lang.String start_hour,
		java.lang.String end_hour, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator);

	/**
	* Returns the markets before and after the current market in the ordered set where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param marketId the primary key of the current market
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next market
	* @throws NoSuchMarketException if a market with the primary key could not be found
	*/
	public Market[] findByActiveStartEndHour_PrevAndNext(long marketId,
		java.lang.String start_hour, java.lang.String end_hour, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator)
		throws NoSuchMarketException;

	/**
	* Removes all the markets where start_hour = &#63; and end_hour = &#63; and active = &#63; from the database.
	*
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	*/
	public void removeByActiveStartEndHour(java.lang.String start_hour,
		java.lang.String end_hour, boolean active);

	/**
	* Returns the number of markets where start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @return the number of matching markets
	*/
	public int countByActiveStartEndHour(java.lang.String start_hour,
		java.lang.String end_hour, boolean active);

	/**
	* Caches the market in the entity cache if it is enabled.
	*
	* @param market the market
	*/
	public void cacheResult(Market market);

	/**
	* Caches the markets in the entity cache if it is enabled.
	*
	* @param markets the markets
	*/
	public void cacheResult(java.util.List<Market> markets);

	/**
	* Creates a new market with the primary key. Does not add the market to the database.
	*
	* @param marketId the primary key for the new market
	* @return the new market
	*/
	public Market create(long marketId);

	/**
	* Removes the market with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param marketId the primary key of the market
	* @return the market that was removed
	* @throws NoSuchMarketException if a market with the primary key could not be found
	*/
	public Market remove(long marketId) throws NoSuchMarketException;

	public Market updateImpl(Market market);

	/**
	* Returns the market with the primary key or throws a {@link NoSuchMarketException} if it could not be found.
	*
	* @param marketId the primary key of the market
	* @return the market
	* @throws NoSuchMarketException if a market with the primary key could not be found
	*/
	public Market findByPrimaryKey(long marketId) throws NoSuchMarketException;

	/**
	* Returns the market with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param marketId the primary key of the market
	* @return the market, or <code>null</code> if a market with the primary key could not be found
	*/
	public Market fetchByPrimaryKey(long marketId);

	@Override
	public java.util.Map<java.io.Serializable, Market> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the markets.
	*
	* @return the markets
	*/
	public java.util.List<Market> findAll();

	/**
	* Returns a range of all the markets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @return the range of markets
	*/
	public java.util.List<Market> findAll(int start, int end);

	/**
	* Returns an ordered range of all the markets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of markets
	*/
	public java.util.List<Market> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator);

	/**
	* Returns an ordered range of all the markets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of markets
	*/
	public java.util.List<Market> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Market> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the markets from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of markets.
	*
	* @return the number of markets
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}