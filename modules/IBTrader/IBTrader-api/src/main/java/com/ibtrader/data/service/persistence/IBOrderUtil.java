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

import com.ibtrader.data.model.IBOrder;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the i b order service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.IBOrderPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see IBOrderPersistence
 * @see com.ibtrader.data.service.persistence.impl.IBOrderPersistenceImpl
 * @generated
 */
@ProviderType
public class IBOrderUtil {
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
	public static void clearCache(IBOrder ibOrder) {
		getPersistence().clearCache(ibOrder);
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
	public static List<IBOrder> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<IBOrder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<IBOrder> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static IBOrder update(IBOrder ibOrder) {
		return getPersistence().update(ibOrder);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static IBOrder update(IBOrder ibOrder, ServiceContext serviceContext) {
		return getPersistence().update(ibOrder, serviceContext);
	}

	/**
	* Returns all the i b orders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching i b orders
	*/
	public static List<IBOrder> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the i b orders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @return the range of matching i b orders
	*/
	public static List<IBOrder> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the i b orders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching i b orders
	*/
	public static List<IBOrder> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the i b orders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching i b orders
	*/
	public static List<IBOrder> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first i b order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public static IBOrder findByUuid_First(java.lang.String uuid,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first i b order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public static IBOrder fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last i b order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public static IBOrder findByUuid_Last(java.lang.String uuid,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last i b order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public static IBOrder fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the i b orders before and after the current i b order in the ordered set where uuid = &#63;.
	*
	* @param ordersId the primary key of the current i b order
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next i b order
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public static IBOrder[] findByUuid_PrevAndNext(long ordersId,
		java.lang.String uuid, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByUuid_PrevAndNext(ordersId, uuid, orderByComparator);
	}

	/**
	* Removes all the i b orders where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of i b orders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching i b orders
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the i b order where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchIBOrderException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public static IBOrder findByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the i b order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public static IBOrder fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the i b order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public static IBOrder fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the i b order where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the i b order that was removed
	*/
	public static IBOrder removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of i b orders where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching i b orders
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the i b orders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching i b orders
	*/
	public static List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the i b orders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @return the range of matching i b orders
	*/
	public static List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the i b orders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching i b orders
	*/
	public static List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the i b orders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching i b orders
	*/
	public static List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first i b order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public static IBOrder findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first i b order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public static IBOrder fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last i b order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public static IBOrder findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last i b order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public static IBOrder fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the i b orders before and after the current i b order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param ordersId the primary key of the current i b order
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next i b order
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public static IBOrder[] findByUuid_C_PrevAndNext(long ordersId,
		java.lang.String uuid, long companyId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(ordersId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the i b orders where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of i b orders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching i b orders
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the i b order in the entity cache if it is enabled.
	*
	* @param ibOrder the i b order
	*/
	public static void cacheResult(IBOrder ibOrder) {
		getPersistence().cacheResult(ibOrder);
	}

	/**
	* Caches the i b orders in the entity cache if it is enabled.
	*
	* @param ibOrders the i b orders
	*/
	public static void cacheResult(List<IBOrder> ibOrders) {
		getPersistence().cacheResult(ibOrders);
	}

	/**
	* Creates a new i b order with the primary key. Does not add the i b order to the database.
	*
	* @param ordersId the primary key for the new i b order
	* @return the new i b order
	*/
	public static IBOrder create(long ordersId) {
		return getPersistence().create(ordersId);
	}

	/**
	* Removes the i b order with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ordersId the primary key of the i b order
	* @return the i b order that was removed
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public static IBOrder remove(long ordersId)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().remove(ordersId);
	}

	public static IBOrder updateImpl(IBOrder ibOrder) {
		return getPersistence().updateImpl(ibOrder);
	}

	/**
	* Returns the i b order with the primary key or throws a {@link NoSuchIBOrderException} if it could not be found.
	*
	* @param ordersId the primary key of the i b order
	* @return the i b order
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public static IBOrder findByPrimaryKey(long ordersId)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().findByPrimaryKey(ordersId);
	}

	/**
	* Returns the i b order with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ordersId the primary key of the i b order
	* @return the i b order, or <code>null</code> if a i b order with the primary key could not be found
	*/
	public static IBOrder fetchByPrimaryKey(long ordersId) {
		return getPersistence().fetchByPrimaryKey(ordersId);
	}

	public static java.util.Map<java.io.Serializable, IBOrder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the i b orders.
	*
	* @return the i b orders
	*/
	public static List<IBOrder> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the i b orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @return the range of i b orders
	*/
	public static List<IBOrder> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the i b orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of i b orders
	*/
	public static List<IBOrder> findAll(int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the i b orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of i b orders
	*/
	public static List<IBOrder> findAll(int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the i b orders from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of i b orders.
	*
	* @return the number of i b orders
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static IBOrderPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<IBOrderPersistence, IBOrderPersistence> _serviceTracker =
		ServiceTrackerFactory.open(IBOrderPersistence.class);
}