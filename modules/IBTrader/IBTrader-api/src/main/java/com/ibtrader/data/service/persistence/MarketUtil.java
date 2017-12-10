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

import com.ibtrader.data.model.Market;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the market service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.MarketPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MarketPersistence
 * @see com.ibtrader.data.service.persistence.impl.MarketPersistenceImpl
 * @generated
 */
@ProviderType
public class MarketUtil {
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
	public static void clearCache(Market market) {
		getPersistence().clearCache(market);
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
	public static List<Market> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Market> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Market> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Market> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Market update(Market market) {
		return getPersistence().update(market);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Market update(Market market, ServiceContext serviceContext) {
		return getPersistence().update(market, serviceContext);
	}

	/**
	* Returns all the markets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching markets
	*/
	public static List<Market> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<Market> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<Market> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Market> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<Market> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Market> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first market in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public static Market findByUuid_First(java.lang.String uuid,
		OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first market in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market, or <code>null</code> if a matching market could not be found
	*/
	public static Market fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Market> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last market in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public static Market findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last market in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market, or <code>null</code> if a matching market could not be found
	*/
	public static Market fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Market> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the markets before and after the current market in the ordered set where uuid = &#63;.
	*
	* @param marketId the primary key of the current market
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next market
	* @throws NoSuchMarketException if a market with the primary key could not be found
	*/
	public static Market[] findByUuid_PrevAndNext(long marketId,
		java.lang.String uuid, OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence()
				   .findByUuid_PrevAndNext(marketId, uuid, orderByComparator);
	}

	/**
	* Removes all the markets where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of markets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching markets
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the market where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchMarketException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public static Market findByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the market where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching market, or <code>null</code> if a matching market could not be found
	*/
	public static Market fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the market where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching market, or <code>null</code> if a matching market could not be found
	*/
	public static Market fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the market where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the market that was removed
	*/
	public static Market removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of markets where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching markets
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the markets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching markets
	*/
	public static List<Market> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<Market> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<Market> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Market> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<Market> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Market> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first market in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public static Market findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first market in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market, or <code>null</code> if a matching market could not be found
	*/
	public static Market fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Market> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last market in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public static Market findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last market in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market, or <code>null</code> if a matching market could not be found
	*/
	public static Market fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Market> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static Market[] findByUuid_C_PrevAndNext(long marketId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(marketId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the markets where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of markets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching markets
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the markets where groupId = &#63; and companyId = &#63; and start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @return the matching markets
	*/
	public static List<Market> findByActiveStartEndHour(long groupId,
		long companyId, java.lang.String start_hour, java.lang.String end_hour,
		boolean active) {
		return getPersistence()
				   .findByActiveStartEndHour(groupId, companyId, start_hour,
			end_hour, active);
	}

	/**
	* Returns a range of all the markets where groupId = &#63; and companyId = &#63; and start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @return the range of matching markets
	*/
	public static List<Market> findByActiveStartEndHour(long groupId,
		long companyId, java.lang.String start_hour, java.lang.String end_hour,
		boolean active, int start, int end) {
		return getPersistence()
				   .findByActiveStartEndHour(groupId, companyId, start_hour,
			end_hour, active, start, end);
	}

	/**
	* Returns an ordered range of all the markets where groupId = &#63; and companyId = &#63; and start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching markets
	*/
	public static List<Market> findByActiveStartEndHour(long groupId,
		long companyId, java.lang.String start_hour, java.lang.String end_hour,
		boolean active, int start, int end,
		OrderByComparator<Market> orderByComparator) {
		return getPersistence()
				   .findByActiveStartEndHour(groupId, companyId, start_hour,
			end_hour, active, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the markets where groupId = &#63; and companyId = &#63; and start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching markets
	*/
	public static List<Market> findByActiveStartEndHour(long groupId,
		long companyId, java.lang.String start_hour, java.lang.String end_hour,
		boolean active, int start, int end,
		OrderByComparator<Market> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByActiveStartEndHour(groupId, companyId, start_hour,
			end_hour, active, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first market in the ordered set where groupId = &#63; and companyId = &#63; and start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public static Market findByActiveStartEndHour_First(long groupId,
		long companyId, java.lang.String start_hour, java.lang.String end_hour,
		boolean active, OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence()
				   .findByActiveStartEndHour_First(groupId, companyId,
			start_hour, end_hour, active, orderByComparator);
	}

	/**
	* Returns the first market in the ordered set where groupId = &#63; and companyId = &#63; and start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market, or <code>null</code> if a matching market could not be found
	*/
	public static Market fetchByActiveStartEndHour_First(long groupId,
		long companyId, java.lang.String start_hour, java.lang.String end_hour,
		boolean active, OrderByComparator<Market> orderByComparator) {
		return getPersistence()
				   .fetchByActiveStartEndHour_First(groupId, companyId,
			start_hour, end_hour, active, orderByComparator);
	}

	/**
	* Returns the last market in the ordered set where groupId = &#63; and companyId = &#63; and start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public static Market findByActiveStartEndHour_Last(long groupId,
		long companyId, java.lang.String start_hour, java.lang.String end_hour,
		boolean active, OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence()
				   .findByActiveStartEndHour_Last(groupId, companyId,
			start_hour, end_hour, active, orderByComparator);
	}

	/**
	* Returns the last market in the ordered set where groupId = &#63; and companyId = &#63; and start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market, or <code>null</code> if a matching market could not be found
	*/
	public static Market fetchByActiveStartEndHour_Last(long groupId,
		long companyId, java.lang.String start_hour, java.lang.String end_hour,
		boolean active, OrderByComparator<Market> orderByComparator) {
		return getPersistence()
				   .fetchByActiveStartEndHour_Last(groupId, companyId,
			start_hour, end_hour, active, orderByComparator);
	}

	/**
	* Returns the markets before and after the current market in the ordered set where groupId = &#63; and companyId = &#63; and start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param marketId the primary key of the current market
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next market
	* @throws NoSuchMarketException if a market with the primary key could not be found
	*/
	public static Market[] findByActiveStartEndHour_PrevAndNext(long marketId,
		long groupId, long companyId, java.lang.String start_hour,
		java.lang.String end_hour, boolean active,
		OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence()
				   .findByActiveStartEndHour_PrevAndNext(marketId, groupId,
			companyId, start_hour, end_hour, active, orderByComparator);
	}

	/**
	* Removes all the markets where groupId = &#63; and companyId = &#63; and start_hour = &#63; and end_hour = &#63; and active = &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	*/
	public static void removeByActiveStartEndHour(long groupId, long companyId,
		java.lang.String start_hour, java.lang.String end_hour, boolean active) {
		getPersistence()
			.removeByActiveStartEndHour(groupId, companyId, start_hour,
			end_hour, active);
	}

	/**
	* Returns the number of markets where groupId = &#63; and companyId = &#63; and start_hour = &#63; and end_hour = &#63; and active = &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start_hour the start_hour
	* @param end_hour the end_hour
	* @param active the active
	* @return the number of matching markets
	*/
	public static int countByActiveStartEndHour(long groupId, long companyId,
		java.lang.String start_hour, java.lang.String end_hour, boolean active) {
		return getPersistence()
				   .countByActiveStartEndHour(groupId, companyId, start_hour,
			end_hour, active);
	}

	/**
	* Returns all the markets where companyId = &#63; and groupId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param active the active
	* @return the matching markets
	*/
	public static List<Market> findByActiveCompanyGroup(long companyId,
		long groupId, boolean active) {
		return getPersistence()
				   .findByActiveCompanyGroup(companyId, groupId, active);
	}

	/**
	* Returns a range of all the markets where companyId = &#63; and groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @return the range of matching markets
	*/
	public static List<Market> findByActiveCompanyGroup(long companyId,
		long groupId, boolean active, int start, int end) {
		return getPersistence()
				   .findByActiveCompanyGroup(companyId, groupId, active, start,
			end);
	}

	/**
	* Returns an ordered range of all the markets where companyId = &#63; and groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching markets
	*/
	public static List<Market> findByActiveCompanyGroup(long companyId,
		long groupId, boolean active, int start, int end,
		OrderByComparator<Market> orderByComparator) {
		return getPersistence()
				   .findByActiveCompanyGroup(companyId, groupId, active, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the markets where companyId = &#63; and groupId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param active the active
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching markets
	*/
	public static List<Market> findByActiveCompanyGroup(long companyId,
		long groupId, boolean active, int start, int end,
		OrderByComparator<Market> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByActiveCompanyGroup(companyId, groupId, active, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first market in the ordered set where companyId = &#63; and groupId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public static Market findByActiveCompanyGroup_First(long companyId,
		long groupId, boolean active,
		OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence()
				   .findByActiveCompanyGroup_First(companyId, groupId, active,
			orderByComparator);
	}

	/**
	* Returns the first market in the ordered set where companyId = &#63; and groupId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching market, or <code>null</code> if a matching market could not be found
	*/
	public static Market fetchByActiveCompanyGroup_First(long companyId,
		long groupId, boolean active,
		OrderByComparator<Market> orderByComparator) {
		return getPersistence()
				   .fetchByActiveCompanyGroup_First(companyId, groupId, active,
			orderByComparator);
	}

	/**
	* Returns the last market in the ordered set where companyId = &#63; and groupId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market
	* @throws NoSuchMarketException if a matching market could not be found
	*/
	public static Market findByActiveCompanyGroup_Last(long companyId,
		long groupId, boolean active,
		OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence()
				   .findByActiveCompanyGroup_Last(companyId, groupId, active,
			orderByComparator);
	}

	/**
	* Returns the last market in the ordered set where companyId = &#63; and groupId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching market, or <code>null</code> if a matching market could not be found
	*/
	public static Market fetchByActiveCompanyGroup_Last(long companyId,
		long groupId, boolean active,
		OrderByComparator<Market> orderByComparator) {
		return getPersistence()
				   .fetchByActiveCompanyGroup_Last(companyId, groupId, active,
			orderByComparator);
	}

	/**
	* Returns the markets before and after the current market in the ordered set where companyId = &#63; and groupId = &#63; and active = &#63;.
	*
	* @param marketId the primary key of the current market
	* @param companyId the company ID
	* @param groupId the group ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next market
	* @throws NoSuchMarketException if a market with the primary key could not be found
	*/
	public static Market[] findByActiveCompanyGroup_PrevAndNext(long marketId,
		long companyId, long groupId, boolean active,
		OrderByComparator<Market> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence()
				   .findByActiveCompanyGroup_PrevAndNext(marketId, companyId,
			groupId, active, orderByComparator);
	}

	/**
	* Removes all the markets where companyId = &#63; and groupId = &#63; and active = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param active the active
	*/
	public static void removeByActiveCompanyGroup(long companyId, long groupId,
		boolean active) {
		getPersistence().removeByActiveCompanyGroup(companyId, groupId, active);
	}

	/**
	* Returns the number of markets where companyId = &#63; and groupId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param active the active
	* @return the number of matching markets
	*/
	public static int countByActiveCompanyGroup(long companyId, long groupId,
		boolean active) {
		return getPersistence()
				   .countByActiveCompanyGroup(companyId, groupId, active);
	}

	/**
	* Caches the market in the entity cache if it is enabled.
	*
	* @param market the market
	*/
	public static void cacheResult(Market market) {
		getPersistence().cacheResult(market);
	}

	/**
	* Caches the markets in the entity cache if it is enabled.
	*
	* @param markets the markets
	*/
	public static void cacheResult(List<Market> markets) {
		getPersistence().cacheResult(markets);
	}

	/**
	* Creates a new market with the primary key. Does not add the market to the database.
	*
	* @param marketId the primary key for the new market
	* @return the new market
	*/
	public static Market create(long marketId) {
		return getPersistence().create(marketId);
	}

	/**
	* Removes the market with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param marketId the primary key of the market
	* @return the market that was removed
	* @throws NoSuchMarketException if a market with the primary key could not be found
	*/
	public static Market remove(long marketId)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence().remove(marketId);
	}

	public static Market updateImpl(Market market) {
		return getPersistence().updateImpl(market);
	}

	/**
	* Returns the market with the primary key or throws a {@link NoSuchMarketException} if it could not be found.
	*
	* @param marketId the primary key of the market
	* @return the market
	* @throws NoSuchMarketException if a market with the primary key could not be found
	*/
	public static Market findByPrimaryKey(long marketId)
		throws com.ibtrader.data.exception.NoSuchMarketException {
		return getPersistence().findByPrimaryKey(marketId);
	}

	/**
	* Returns the market with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param marketId the primary key of the market
	* @return the market, or <code>null</code> if a market with the primary key could not be found
	*/
	public static Market fetchByPrimaryKey(long marketId) {
		return getPersistence().fetchByPrimaryKey(marketId);
	}

	public static java.util.Map<java.io.Serializable, Market> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the markets.
	*
	* @return the markets
	*/
	public static List<Market> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Market> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Market> findAll(int start, int end,
		OrderByComparator<Market> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Market> findAll(int start, int end,
		OrderByComparator<Market> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the markets from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of markets.
	*
	* @return the number of markets
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MarketPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MarketPersistence, MarketPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MarketPersistence.class);
}