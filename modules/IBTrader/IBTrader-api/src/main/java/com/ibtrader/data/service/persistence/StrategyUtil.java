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

import com.ibtrader.data.model.Strategy;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the strategy service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.StrategyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrategyPersistence
 * @see com.ibtrader.data.service.persistence.impl.StrategyPersistenceImpl
 * @generated
 */
@ProviderType
public class StrategyUtil {
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
	public static void clearCache(Strategy strategy) {
		getPersistence().clearCache(strategy);
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
	public static List<Strategy> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Strategy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Strategy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Strategy update(Strategy strategy) {
		return getPersistence().update(strategy);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Strategy update(Strategy strategy,
		ServiceContext serviceContext) {
		return getPersistence().update(strategy, serviceContext);
	}

	/**
	* Returns all the strategies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching strategies
	*/
	public static List<Strategy> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the strategies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @return the range of matching strategies
	*/
	public static List<Strategy> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the strategies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Strategy> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByUuid_First(java.lang.String uuid,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the strategies before and after the current strategy in the ordered set where uuid = &#63;.
	*
	* @param strategyID the primary key of the current strategy
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public static Strategy[] findByUuid_PrevAndNext(long strategyID,
		java.lang.String uuid, OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByUuid_PrevAndNext(strategyID, uuid, orderByComparator);
	}

	/**
	* Removes all the strategies where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of strategies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching strategies
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the strategy where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchStrategyException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the strategy where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the strategy where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the strategy where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the strategy that was removed
	*/
	public static Strategy removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of strategies where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching strategies
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the strategies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching strategies
	*/
	public static List<Strategy> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the strategies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @return the range of matching strategies
	*/
	public static List<Strategy> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the strategies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Strategy> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the strategies before and after the current strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param strategyID the primary key of the current strategy
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public static Strategy[] findByUuid_C_PrevAndNext(long strategyID,
		java.lang.String uuid, long companyId,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(strategyID, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the strategies where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of strategies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching strategies
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the strategies where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching strategies
	*/
	public static List<Strategy> findByCompanyId(long companyId) {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the strategies where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @return the range of matching strategies
	*/
	public static List<Strategy> findByCompanyId(long companyId, int start,
		int end) {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the strategies where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategies where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByCompanyId(long companyId, int start,
		int end, OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first strategy in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByCompanyId_First(long companyId,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first strategy in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByCompanyId_First(long companyId,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByCompanyId_Last(long companyId,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByCompanyId_Last(long companyId,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the strategies before and after the current strategy in the ordered set where companyId = &#63;.
	*
	* @param strategyID the primary key of the current strategy
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public static Strategy[] findByCompanyId_PrevAndNext(long strategyID,
		long companyId, OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(strategyID, companyId,
			orderByComparator);
	}

	/**
	* Removes all the strategies where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public static void removeByCompanyId(long companyId) {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of strategies where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching strategies
	*/
	public static int countByCompanyId(long companyId) {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns all the strategies where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching strategies
	*/
	public static List<Strategy> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the strategies where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @return the range of matching strategies
	*/
	public static List<Strategy> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the strategies where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategies where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first strategy in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByGroupId_First(long groupId,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first strategy in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByGroupId_First(long groupId,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByGroupId_Last(long groupId,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByGroupId_Last(long groupId,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the strategies before and after the current strategy in the ordered set where groupId = &#63;.
	*
	* @param strategyID the primary key of the current strategy
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public static Strategy[] findByGroupId_PrevAndNext(long strategyID,
		long groupId, OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(strategyID, groupId,
			orderByComparator);
	}

	/**
	* Removes all the strategies where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of strategies where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching strategies
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the strategies where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the matching strategies
	*/
	public static List<Strategy> findByActiveCompanyId(long companyId,
		boolean active) {
		return getPersistence().findByActiveCompanyId(companyId, active);
	}

	/**
	* Returns a range of all the strategies where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @return the range of matching strategies
	*/
	public static List<Strategy> findByActiveCompanyId(long companyId,
		boolean active, int start, int end) {
		return getPersistence()
				   .findByActiveCompanyId(companyId, active, start, end);
	}

	/**
	* Returns an ordered range of all the strategies where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByActiveCompanyId(long companyId,
		boolean active, int start, int end,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .findByActiveCompanyId(companyId, active, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategies where companyId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param active the active
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByActiveCompanyId(long companyId,
		boolean active, int start, int end,
		OrderByComparator<Strategy> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByActiveCompanyId(companyId, active, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first strategy in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByActiveCompanyId_First(long companyId,
		boolean active, OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByActiveCompanyId_First(companyId, active,
			orderByComparator);
	}

	/**
	* Returns the first strategy in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByActiveCompanyId_First(long companyId,
		boolean active, OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .fetchByActiveCompanyId_First(companyId, active,
			orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByActiveCompanyId_Last(long companyId,
		boolean active, OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByActiveCompanyId_Last(companyId, active,
			orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByActiveCompanyId_Last(long companyId,
		boolean active, OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .fetchByActiveCompanyId_Last(companyId, active,
			orderByComparator);
	}

	/**
	* Returns the strategies before and after the current strategy in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param strategyID the primary key of the current strategy
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public static Strategy[] findByActiveCompanyId_PrevAndNext(
		long strategyID, long companyId, boolean active,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByActiveCompanyId_PrevAndNext(strategyID, companyId,
			active, orderByComparator);
	}

	/**
	* Removes all the strategies where companyId = &#63; and active = &#63; from the database.
	*
	* @param companyId the company ID
	* @param active the active
	*/
	public static void removeByActiveCompanyId(long companyId, boolean active) {
		getPersistence().removeByActiveCompanyId(companyId, active);
	}

	/**
	* Returns the number of strategies where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the number of matching strategies
	*/
	public static int countByActiveCompanyId(long companyId, boolean active) {
		return getPersistence().countByActiveCompanyId(companyId, active);
	}

	/**
	* Returns all the strategies where status = &#63;.
	*
	* @param status the status
	* @return the matching strategies
	*/
	public static List<Strategy> findByStatus(int status) {
		return getPersistence().findByStatus(status);
	}

	/**
	* Returns a range of all the strategies where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @return the range of matching strategies
	*/
	public static List<Strategy> findByStatus(int status, int start, int end) {
		return getPersistence().findByStatus(status, start, end);
	}

	/**
	* Returns an ordered range of all the strategies where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByStatus(int status, int start, int end,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategies where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByStatus(int status, int start, int end,
		OrderByComparator<Strategy> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first strategy in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByStatus_First(int status,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the first strategy in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByStatus_First(int status,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByStatus_Last(int status,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByStatus_Last(int status,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the strategies before and after the current strategy in the ordered set where status = &#63;.
	*
	* @param strategyID the primary key of the current strategy
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public static Strategy[] findByStatus_PrevAndNext(long strategyID,
		int status, OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByStatus_PrevAndNext(strategyID, status,
			orderByComparator);
	}

	/**
	* Removes all the strategies where status = &#63; from the database.
	*
	* @param status the status
	*/
	public static void removeByStatus(int status) {
		getPersistence().removeByStatus(status);
	}

	/**
	* Returns the number of strategies where status = &#63;.
	*
	* @param status the status
	* @return the number of matching strategies
	*/
	public static int countByStatus(int status) {
		return getPersistence().countByStatus(status);
	}

	/**
	* Returns all the strategies where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching strategies
	*/
	public static List<Strategy> findByG_S(long groupId, int status) {
		return getPersistence().findByG_S(groupId, status);
	}

	/**
	* Returns a range of all the strategies where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @return the range of matching strategies
	*/
	public static List<Strategy> findByG_S(long groupId, int status, int start,
		int end) {
		return getPersistence().findByG_S(groupId, status, start, end);
	}

	/**
	* Returns an ordered range of all the strategies where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByG_S(long groupId, int status, int start,
		int end, OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .findByG_S(groupId, status, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategies where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByG_S(long groupId, int status, int start,
		int end, OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_S(groupId, status, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first strategy in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByG_S_First(long groupId, int status,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByG_S_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the first strategy in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByG_S_First(long groupId, int status,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .fetchByG_S_First(groupId, status, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByG_S_Last(long groupId, int status,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByG_S_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByG_S_Last(long groupId, int status,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .fetchByG_S_Last(groupId, status, orderByComparator);
	}

	/**
	* Returns the strategies before and after the current strategy in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param strategyID the primary key of the current strategy
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public static Strategy[] findByG_S_PrevAndNext(long strategyID,
		long groupId, int status, OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByG_S_PrevAndNext(strategyID, groupId, status,
			orderByComparator);
	}

	/**
	* Removes all the strategies where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public static void removeByG_S(long groupId, int status) {
		getPersistence().removeByG_S(groupId, status);
	}

	/**
	* Returns the number of strategies where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching strategies
	*/
	public static int countByG_S(long groupId, int status) {
		return getPersistence().countByG_S(groupId, status);
	}

	/**
	* Returns all the strategies where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @return the matching strategies
	*/
	public static List<Strategy> findByG_G(long groupId, long strategyID) {
		return getPersistence().findByG_G(groupId, strategyID);
	}

	/**
	* Returns a range of all the strategies where groupId = &#63; and strategyID = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @return the range of matching strategies
	*/
	public static List<Strategy> findByG_G(long groupId, long strategyID,
		int start, int end) {
		return getPersistence().findByG_G(groupId, strategyID, start, end);
	}

	/**
	* Returns an ordered range of all the strategies where groupId = &#63; and strategyID = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByG_G(long groupId, long strategyID,
		int start, int end, OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .findByG_G(groupId, strategyID, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategies where groupId = &#63; and strategyID = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategies
	*/
	public static List<Strategy> findByG_G(long groupId, long strategyID,
		int start, int end, OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_G(groupId, strategyID, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first strategy in the ordered set where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByG_G_First(long groupId, long strategyID,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByG_G_First(groupId, strategyID, orderByComparator);
	}

	/**
	* Returns the first strategy in the ordered set where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByG_G_First(long groupId, long strategyID,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .fetchByG_G_First(groupId, strategyID, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByG_G_Last(long groupId, long strategyID,
		OrderByComparator<Strategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByG_G_Last(groupId, strategyID, orderByComparator);
	}

	/**
	* Returns the last strategy in the ordered set where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByG_G_Last(long groupId, long strategyID,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence()
				   .fetchByG_G_Last(groupId, strategyID, orderByComparator);
	}

	/**
	* Removes all the strategies where groupId = &#63; and strategyID = &#63; from the database.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	*/
	public static void removeByG_G(long groupId, long strategyID) {
		getPersistence().removeByG_G(groupId, strategyID);
	}

	/**
	* Returns the number of strategies where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @return the number of matching strategies
	*/
	public static int countByG_G(long groupId, long strategyID) {
		return getPersistence().countByG_G(groupId, strategyID);
	}

	/**
	* Returns the strategy where companyId = &#63; and className = &#63; and groupId = &#63; or throws a {@link NoSuchStrategyException} if it could not be found.
	*
	* @param companyId the company ID
	* @param className the class name
	* @param groupId the group ID
	* @return the matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public static Strategy findByCompanyClassName(long companyId,
		java.lang.String className, long groupId)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .findByCompanyClassName(companyId, className, groupId);
	}

	/**
	* Returns the strategy where companyId = &#63; and className = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param className the class name
	* @param groupId the group ID
	* @return the matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByCompanyClassName(long companyId,
		java.lang.String className, long groupId) {
		return getPersistence()
				   .fetchByCompanyClassName(companyId, className, groupId);
	}

	/**
	* Returns the strategy where companyId = &#63; and className = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param className the class name
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public static Strategy fetchByCompanyClassName(long companyId,
		java.lang.String className, long groupId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCompanyClassName(companyId, className, groupId,
			retrieveFromCache);
	}

	/**
	* Removes the strategy where companyId = &#63; and className = &#63; and groupId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param className the class name
	* @param groupId the group ID
	* @return the strategy that was removed
	*/
	public static Strategy removeByCompanyClassName(long companyId,
		java.lang.String className, long groupId)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence()
				   .removeByCompanyClassName(companyId, className, groupId);
	}

	/**
	* Returns the number of strategies where companyId = &#63; and className = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param className the class name
	* @param groupId the group ID
	* @return the number of matching strategies
	*/
	public static int countByCompanyClassName(long companyId,
		java.lang.String className, long groupId) {
		return getPersistence()
				   .countByCompanyClassName(companyId, className, groupId);
	}

	/**
	* Caches the strategy in the entity cache if it is enabled.
	*
	* @param strategy the strategy
	*/
	public static void cacheResult(Strategy strategy) {
		getPersistence().cacheResult(strategy);
	}

	/**
	* Caches the strategies in the entity cache if it is enabled.
	*
	* @param strategies the strategies
	*/
	public static void cacheResult(List<Strategy> strategies) {
		getPersistence().cacheResult(strategies);
	}

	/**
	* Creates a new strategy with the primary key. Does not add the strategy to the database.
	*
	* @param strategyID the primary key for the new strategy
	* @return the new strategy
	*/
	public static Strategy create(long strategyID) {
		return getPersistence().create(strategyID);
	}

	/**
	* Removes the strategy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy that was removed
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public static Strategy remove(long strategyID)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence().remove(strategyID);
	}

	public static Strategy updateImpl(Strategy strategy) {
		return getPersistence().updateImpl(strategy);
	}

	/**
	* Returns the strategy with the primary key or throws a {@link NoSuchStrategyException} if it could not be found.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public static Strategy findByPrimaryKey(long strategyID)
		throws com.ibtrader.data.exception.NoSuchStrategyException {
		return getPersistence().findByPrimaryKey(strategyID);
	}

	/**
	* Returns the strategy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy, or <code>null</code> if a strategy with the primary key could not be found
	*/
	public static Strategy fetchByPrimaryKey(long strategyID) {
		return getPersistence().fetchByPrimaryKey(strategyID);
	}

	public static java.util.Map<java.io.Serializable, Strategy> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the strategies.
	*
	* @return the strategies
	*/
	public static List<Strategy> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the strategies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @return the range of strategies
	*/
	public static List<Strategy> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the strategies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of strategies
	*/
	public static List<Strategy> findAll(int start, int end,
		OrderByComparator<Strategy> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strategies
	* @param end the upper bound of the range of strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of strategies
	*/
	public static List<Strategy> findAll(int start, int end,
		OrderByComparator<Strategy> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the strategies from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of strategies.
	*
	* @return the number of strategies
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StrategyPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StrategyPersistence, StrategyPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StrategyPersistence.class);
}