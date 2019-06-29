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

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the ib order service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.IBOrderPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
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
	* Returns all the ib orders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching ib orders
	*/
	public static List<IBOrder> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the ib orders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @return the range of matching ib orders
	*/
	public static List<IBOrder> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the ib orders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ib orders where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ib order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByUuid_First(java.lang.String uuid,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first ib order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByUuid_Last(java.lang.String uuid,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the ib orders before and after the current ib order in the ordered set where uuid = &#63;.
	*
	* @param orderIdPk the primary key of the current ib order
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ib order
	* @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	*/
	public static IBOrder[] findByUuid_PrevAndNext(long orderIdPk,
		java.lang.String uuid, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByUuid_PrevAndNext(orderIdPk, uuid, orderByComparator);
	}

	/**
	* Removes all the ib orders where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of ib orders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching ib orders
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the ib order where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchIBOrderException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the ib order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the ib order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the ib order where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the ib order that was removed
	*/
	public static IBOrder removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of ib orders where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching ib orders
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the ib orders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching ib orders
	*/
	public static List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the ib orders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @return the range of matching ib orders
	*/
	public static List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the ib orders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ib orders where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ib order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first ib order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the ib orders before and after the current ib order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param orderIdPk the primary key of the current ib order
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ib order
	* @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	*/
	public static IBOrder[] findByUuid_C_PrevAndNext(long orderIdPk,
		java.lang.String uuid, long companyId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(orderIdPk, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the ib orders where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of ib orders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching ib orders
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the matching ib orders
	*/
	public static List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId) {
		return getPersistence()
				   .findByShareIdCompanyGroup(shareID, companyId, groupId);
	}

	/**
	* Returns a range of all the ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @return the range of matching ib orders
	*/
	public static List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId, int start, int end) {
		return getPersistence()
				   .findByShareIdCompanyGroup(shareID, companyId, groupId,
			start, end);
	}

	/**
	* Returns an ordered range of all the ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .findByShareIdCompanyGroup(shareID, companyId, groupId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByShareIdCompanyGroup(shareID, companyId, groupId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ib order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByShareIdCompanyGroup_First(long shareID,
		long companyId, long groupId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByShareIdCompanyGroup_First(shareID, companyId,
			groupId, orderByComparator);
	}

	/**
	* Returns the first ib order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByShareIdCompanyGroup_First(long shareID,
		long companyId, long groupId,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByShareIdCompanyGroup_First(shareID, companyId,
			groupId, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByShareIdCompanyGroup_Last(long shareID,
		long companyId, long groupId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByShareIdCompanyGroup_Last(shareID, companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByShareIdCompanyGroup_Last(long shareID,
		long companyId, long groupId,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByShareIdCompanyGroup_Last(shareID, companyId,
			groupId, orderByComparator);
	}

	/**
	* Returns the ib orders before and after the current ib order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param orderIdPk the primary key of the current ib order
	* @param shareID the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ib order
	* @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	*/
	public static IBOrder[] findByShareIdCompanyGroup_PrevAndNext(
		long orderIdPk, long shareID, long companyId, long groupId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByShareIdCompanyGroup_PrevAndNext(orderIdPk, shareID,
			companyId, groupId, orderByComparator);
	}

	/**
	* Removes all the ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63; from the database.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	*/
	public static void removeByShareIdCompanyGroup(long shareID,
		long companyId, long groupId) {
		getPersistence().removeByShareIdCompanyGroup(shareID, companyId, groupId);
	}

	/**
	* Returns the number of ib orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the number of matching ib orders
	*/
	public static int countByShareIdCompanyGroup(long shareID, long companyId,
		long groupId) {
		return getPersistence()
				   .countByShareIdCompanyGroup(shareID, companyId, groupId);
	}

	/**
	* Returns all the ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @return the matching ib orders
	*/
	public static List<IBOrder> findByOrderGroupCompany(long shareID,
		long companyId, long ordersId) {
		return getPersistence()
				   .findByOrderGroupCompany(shareID, companyId, ordersId);
	}

	/**
	* Returns a range of all the ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @return the range of matching ib orders
	*/
	public static List<IBOrder> findByOrderGroupCompany(long shareID,
		long companyId, long ordersId, int start, int end) {
		return getPersistence()
				   .findByOrderGroupCompany(shareID, companyId, ordersId,
			start, end);
	}

	/**
	* Returns an ordered range of all the ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByOrderGroupCompany(long shareID,
		long companyId, long ordersId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .findByOrderGroupCompany(shareID, companyId, ordersId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByOrderGroupCompany(long shareID,
		long companyId, long ordersId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByOrderGroupCompany(shareID, companyId, ordersId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ib order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByOrderGroupCompany_First(long shareID,
		long companyId, long ordersId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderGroupCompany_First(shareID, companyId, ordersId,
			orderByComparator);
	}

	/**
	* Returns the first ib order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByOrderGroupCompany_First(long shareID,
		long companyId, long ordersId,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByOrderGroupCompany_First(shareID, companyId,
			ordersId, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByOrderGroupCompany_Last(long shareID,
		long companyId, long ordersId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderGroupCompany_Last(shareID, companyId, ordersId,
			orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByOrderGroupCompany_Last(long shareID,
		long companyId, long ordersId,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByOrderGroupCompany_Last(shareID, companyId, ordersId,
			orderByComparator);
	}

	/**
	* Returns the ib orders before and after the current ib order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param orderIdPk the primary key of the current ib order
	* @param shareID the share ID
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ib order
	* @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	*/
	public static IBOrder[] findByOrderGroupCompany_PrevAndNext(
		long orderIdPk, long shareID, long companyId, long ordersId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderGroupCompany_PrevAndNext(orderIdPk, shareID,
			companyId, ordersId, orderByComparator);
	}

	/**
	* Removes all the ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63; from the database.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param ordersId the orders ID
	*/
	public static void removeByOrderGroupCompany(long shareID, long companyId,
		long ordersId) {
		getPersistence().removeByOrderGroupCompany(shareID, companyId, ordersId);
	}

	/**
	* Returns the number of ib orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share ID
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @return the number of matching ib orders
	*/
	public static int countByOrderGroupCompany(long shareID, long companyId,
		long ordersId) {
		return getPersistence()
				   .countByOrderGroupCompany(shareID, companyId, ordersId);
	}

	/**
	* Returns all the ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @return the matching ib orders
	*/
	public static List<IBOrder> findByOrderClientGroupCompany(long ordersId,
		long companyId, long groupId, long ibclientId) {
		return getPersistence()
				   .findByOrderClientGroupCompany(ordersId, companyId, groupId,
			ibclientId);
	}

	/**
	* Returns a range of all the ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @return the range of matching ib orders
	*/
	public static List<IBOrder> findByOrderClientGroupCompany(long ordersId,
		long companyId, long groupId, long ibclientId, int start, int end) {
		return getPersistence()
				   .findByOrderClientGroupCompany(ordersId, companyId, groupId,
			ibclientId, start, end);
	}

	/**
	* Returns an ordered range of all the ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByOrderClientGroupCompany(long ordersId,
		long companyId, long groupId, long ibclientId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .findByOrderClientGroupCompany(ordersId, companyId, groupId,
			ibclientId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByOrderClientGroupCompany(long ordersId,
		long companyId, long groupId, long ibclientId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByOrderClientGroupCompany(ordersId, companyId, groupId,
			ibclientId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ib order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByOrderClientGroupCompany_First(long ordersId,
		long companyId, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderClientGroupCompany_First(ordersId, companyId,
			groupId, ibclientId, orderByComparator);
	}

	/**
	* Returns the first ib order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByOrderClientGroupCompany_First(long ordersId,
		long companyId, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByOrderClientGroupCompany_First(ordersId, companyId,
			groupId, ibclientId, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByOrderClientGroupCompany_Last(long ordersId,
		long companyId, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderClientGroupCompany_Last(ordersId, companyId,
			groupId, ibclientId, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByOrderClientGroupCompany_Last(long ordersId,
		long companyId, long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByOrderClientGroupCompany_Last(ordersId, companyId,
			groupId, ibclientId, orderByComparator);
	}

	/**
	* Returns the ib orders before and after the current ib order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param orderIdPk the primary key of the current ib order
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ib order
	* @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	*/
	public static IBOrder[] findByOrderClientGroupCompany_PrevAndNext(
		long orderIdPk, long ordersId, long companyId, long groupId,
		long ibclientId, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderClientGroupCompany_PrevAndNext(orderIdPk,
			ordersId, companyId, groupId, ibclientId, orderByComparator);
	}

	/**
	* Removes all the ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63; from the database.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	*/
	public static void removeByOrderClientGroupCompany(long ordersId,
		long companyId, long groupId, long ibclientId) {
		getPersistence()
			.removeByOrderClientGroupCompany(ordersId, companyId, groupId,
			ibclientId);
	}

	/**
	* Returns the number of ib orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @return the number of matching ib orders
	*/
	public static int countByOrderClientGroupCompany(long ordersId,
		long companyId, long groupId, long ibclientId) {
		return getPersistence()
				   .countByOrderClientGroupCompany(ordersId, companyId,
			groupId, ibclientId);
	}

	/**
	* Returns all the ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param shareID the share ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @return the matching ib orders
	*/
	public static List<IBOrder> findByOrderShareClientGroupCompany(
		long ordersId, long companyId, long shareID, long groupId,
		long ibclientId) {
		return getPersistence()
				   .findByOrderShareClientGroupCompany(ordersId, companyId,
			shareID, groupId, ibclientId);
	}

	/**
	* Returns a range of all the ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param shareID the share ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @return the range of matching ib orders
	*/
	public static List<IBOrder> findByOrderShareClientGroupCompany(
		long ordersId, long companyId, long shareID, long groupId,
		long ibclientId, int start, int end) {
		return getPersistence()
				   .findByOrderShareClientGroupCompany(ordersId, companyId,
			shareID, groupId, ibclientId, start, end);
	}

	/**
	* Returns an ordered range of all the ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param shareID the share ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByOrderShareClientGroupCompany(
		long ordersId, long companyId, long shareID, long groupId,
		long ibclientId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .findByOrderShareClientGroupCompany(ordersId, companyId,
			shareID, groupId, ibclientId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param shareID the share ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByOrderShareClientGroupCompany(
		long ordersId, long companyId, long shareID, long groupId,
		long ibclientId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByOrderShareClientGroupCompany(ordersId, companyId,
			shareID, groupId, ibclientId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ib order in the ordered set where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param shareID the share ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByOrderShareClientGroupCompany_First(
		long ordersId, long companyId, long shareID, long groupId,
		long ibclientId, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderShareClientGroupCompany_First(ordersId,
			companyId, shareID, groupId, ibclientId, orderByComparator);
	}

	/**
	* Returns the first ib order in the ordered set where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param shareID the share ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByOrderShareClientGroupCompany_First(
		long ordersId, long companyId, long shareID, long groupId,
		long ibclientId, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByOrderShareClientGroupCompany_First(ordersId,
			companyId, shareID, groupId, ibclientId, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param shareID the share ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByOrderShareClientGroupCompany_Last(
		long ordersId, long companyId, long shareID, long groupId,
		long ibclientId, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderShareClientGroupCompany_Last(ordersId,
			companyId, shareID, groupId, ibclientId, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param shareID the share ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByOrderShareClientGroupCompany_Last(
		long ordersId, long companyId, long shareID, long groupId,
		long ibclientId, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByOrderShareClientGroupCompany_Last(ordersId,
			companyId, shareID, groupId, ibclientId, orderByComparator);
	}

	/**
	* Returns the ib orders before and after the current ib order in the ordered set where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param orderIdPk the primary key of the current ib order
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param shareID the share ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ib order
	* @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	*/
	public static IBOrder[] findByOrderShareClientGroupCompany_PrevAndNext(
		long orderIdPk, long ordersId, long companyId, long shareID,
		long groupId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderShareClientGroupCompany_PrevAndNext(orderIdPk,
			ordersId, companyId, shareID, groupId, ibclientId, orderByComparator);
	}

	/**
	* Removes all the ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63; from the database.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param shareID the share ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	*/
	public static void removeByOrderShareClientGroupCompany(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId) {
		getPersistence()
			.removeByOrderShareClientGroupCompany(ordersId, companyId, shareID,
			groupId, ibclientId);
	}

	/**
	* Returns the number of ib orders where ordersId = &#63; and companyId = &#63; and shareID = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param shareID the share ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @return the number of matching ib orders
	*/
	public static int countByOrderShareClientGroupCompany(long ordersId,
		long companyId, long shareID, long groupId, long ibclientId) {
		return getPersistence()
				   .countByOrderShareClientGroupCompany(ordersId, companyId,
			shareID, groupId, ibclientId);
	}

	/**
	* Returns all the ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	*
	* @param removable_on_reboot the removable_on_reboot
	* @param companyId the company ID
	* @param groupId the group ID
	* @param createDate the create date
	* @return the matching ib orders
	*/
	public static List<IBOrder> findByRemovableDateGroupCompany(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate) {
		return getPersistence()
				   .findByRemovableDateGroupCompany(removable_on_reboot,
			companyId, groupId, createDate);
	}

	/**
	* Returns a range of all the ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param removable_on_reboot the removable_on_reboot
	* @param companyId the company ID
	* @param groupId the group ID
	* @param createDate the create date
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @return the range of matching ib orders
	*/
	public static List<IBOrder> findByRemovableDateGroupCompany(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, int start, int end) {
		return getPersistence()
				   .findByRemovableDateGroupCompany(removable_on_reboot,
			companyId, groupId, createDate, start, end);
	}

	/**
	* Returns an ordered range of all the ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param removable_on_reboot the removable_on_reboot
	* @param companyId the company ID
	* @param groupId the group ID
	* @param createDate the create date
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByRemovableDateGroupCompany(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .findByRemovableDateGroupCompany(removable_on_reboot,
			companyId, groupId, createDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param removable_on_reboot the removable_on_reboot
	* @param companyId the company ID
	* @param groupId the group ID
	* @param createDate the create date
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByRemovableDateGroupCompany(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByRemovableDateGroupCompany(removable_on_reboot,
			companyId, groupId, createDate, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ib order in the ordered set where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	*
	* @param removable_on_reboot the removable_on_reboot
	* @param companyId the company ID
	* @param groupId the group ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByRemovableDateGroupCompany_First(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByRemovableDateGroupCompany_First(removable_on_reboot,
			companyId, groupId, createDate, orderByComparator);
	}

	/**
	* Returns the first ib order in the ordered set where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	*
	* @param removable_on_reboot the removable_on_reboot
	* @param companyId the company ID
	* @param groupId the group ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByRemovableDateGroupCompany_First(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByRemovableDateGroupCompany_First(removable_on_reboot,
			companyId, groupId, createDate, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	*
	* @param removable_on_reboot the removable_on_reboot
	* @param companyId the company ID
	* @param groupId the group ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByRemovableDateGroupCompany_Last(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByRemovableDateGroupCompany_Last(removable_on_reboot,
			companyId, groupId, createDate, orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	*
	* @param removable_on_reboot the removable_on_reboot
	* @param companyId the company ID
	* @param groupId the group ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByRemovableDateGroupCompany_Last(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByRemovableDateGroupCompany_Last(removable_on_reboot,
			companyId, groupId, createDate, orderByComparator);
	}

	/**
	* Returns the ib orders before and after the current ib order in the ordered set where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	*
	* @param orderIdPk the primary key of the current ib order
	* @param removable_on_reboot the removable_on_reboot
	* @param companyId the company ID
	* @param groupId the group ID
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ib order
	* @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	*/
	public static IBOrder[] findByRemovableDateGroupCompany_PrevAndNext(
		long orderIdPk, boolean removable_on_reboot, long companyId,
		long groupId, Date createDate,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByRemovableDateGroupCompany_PrevAndNext(orderIdPk,
			removable_on_reboot, companyId, groupId, createDate,
			orderByComparator);
	}

	/**
	* Removes all the ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63; from the database.
	*
	* @param removable_on_reboot the removable_on_reboot
	* @param companyId the company ID
	* @param groupId the group ID
	* @param createDate the create date
	*/
	public static void removeByRemovableDateGroupCompany(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate) {
		getPersistence()
			.removeByRemovableDateGroupCompany(removable_on_reboot, companyId,
			groupId, createDate);
	}

	/**
	* Returns the number of ib orders where removable_on_reboot = &#63; and companyId = &#63; and groupId = &#63; and createDate = &#63;.
	*
	* @param removable_on_reboot the removable_on_reboot
	* @param companyId the company ID
	* @param groupId the group ID
	* @param createDate the create date
	* @return the number of matching ib orders
	*/
	public static int countByRemovableDateGroupCompany(
		boolean removable_on_reboot, long companyId, long groupId,
		Date createDate) {
		return getPersistence()
				   .countByRemovableDateGroupCompany(removable_on_reboot,
			companyId, groupId, createDate);
	}

	/**
	* Returns all the ib orders where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @return the matching ib orders
	*/
	public static List<IBOrder> findByOrderClient(long ordersId, long ibclientId) {
		return getPersistence().findByOrderClient(ordersId, ibclientId);
	}

	/**
	* Returns a range of all the ib orders where ordersId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @return the range of matching ib orders
	*/
	public static List<IBOrder> findByOrderClient(long ordersId,
		long ibclientId, int start, int end) {
		return getPersistence()
				   .findByOrderClient(ordersId, ibclientId, start, end);
	}

	/**
	* Returns an ordered range of all the ib orders where ordersId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByOrderClient(long ordersId,
		long ibclientId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .findByOrderClient(ordersId, ibclientId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ib orders where ordersId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ib orders
	*/
	public static List<IBOrder> findByOrderClient(long ordersId,
		long ibclientId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByOrderClient(ordersId, ibclientId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ib order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByOrderClient_First(long ordersId,
		long ibclientId, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderClient_First(ordersId, ibclientId,
			orderByComparator);
	}

	/**
	* Returns the first ib order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByOrderClient_First(long ordersId,
		long ibclientId, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByOrderClient_First(ordersId, ibclientId,
			orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order
	* @throws NoSuchIBOrderException if a matching ib order could not be found
	*/
	public static IBOrder findByOrderClient_Last(long ordersId,
		long ibclientId, OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderClient_Last(ordersId, ibclientId,
			orderByComparator);
	}

	/**
	* Returns the last ib order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	public static IBOrder fetchByOrderClient_Last(long ordersId,
		long ibclientId, OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence()
				   .fetchByOrderClient_Last(ordersId, ibclientId,
			orderByComparator);
	}

	/**
	* Returns the ib orders before and after the current ib order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param orderIdPk the primary key of the current ib order
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ib order
	* @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	*/
	public static IBOrder[] findByOrderClient_PrevAndNext(long orderIdPk,
		long ordersId, long ibclientId,
		OrderByComparator<IBOrder> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence()
				   .findByOrderClient_PrevAndNext(orderIdPk, ordersId,
			ibclientId, orderByComparator);
	}

	/**
	* Removes all the ib orders where ordersId = &#63; and ibclientId = &#63; from the database.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	*/
	public static void removeByOrderClient(long ordersId, long ibclientId) {
		getPersistence().removeByOrderClient(ordersId, ibclientId);
	}

	/**
	* Returns the number of ib orders where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @return the number of matching ib orders
	*/
	public static int countByOrderClient(long ordersId, long ibclientId) {
		return getPersistence().countByOrderClient(ordersId, ibclientId);
	}

	/**
	* Caches the ib order in the entity cache if it is enabled.
	*
	* @param ibOrder the ib order
	*/
	public static void cacheResult(IBOrder ibOrder) {
		getPersistence().cacheResult(ibOrder);
	}

	/**
	* Caches the ib orders in the entity cache if it is enabled.
	*
	* @param ibOrders the ib orders
	*/
	public static void cacheResult(List<IBOrder> ibOrders) {
		getPersistence().cacheResult(ibOrders);
	}

	/**
	* Creates a new ib order with the primary key. Does not add the ib order to the database.
	*
	* @param orderIdPk the primary key for the new ib order
	* @return the new ib order
	*/
	public static IBOrder create(long orderIdPk) {
		return getPersistence().create(orderIdPk);
	}

	/**
	* Removes the ib order with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orderIdPk the primary key of the ib order
	* @return the ib order that was removed
	* @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	*/
	public static IBOrder remove(long orderIdPk)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().remove(orderIdPk);
	}

	public static IBOrder updateImpl(IBOrder ibOrder) {
		return getPersistence().updateImpl(ibOrder);
	}

	/**
	* Returns the ib order with the primary key or throws a {@link NoSuchIBOrderException} if it could not be found.
	*
	* @param orderIdPk the primary key of the ib order
	* @return the ib order
	* @throws NoSuchIBOrderException if a ib order with the primary key could not be found
	*/
	public static IBOrder findByPrimaryKey(long orderIdPk)
		throws com.ibtrader.data.exception.NoSuchIBOrderException {
		return getPersistence().findByPrimaryKey(orderIdPk);
	}

	/**
	* Returns the ib order with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param orderIdPk the primary key of the ib order
	* @return the ib order, or <code>null</code> if a ib order with the primary key could not be found
	*/
	public static IBOrder fetchByPrimaryKey(long orderIdPk) {
		return getPersistence().fetchByPrimaryKey(orderIdPk);
	}

	public static java.util.Map<java.io.Serializable, IBOrder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ib orders.
	*
	* @return the ib orders
	*/
	public static List<IBOrder> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ib orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @return the range of ib orders
	*/
	public static List<IBOrder> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ib orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ib orders
	*/
	public static List<IBOrder> findAll(int start, int end,
		OrderByComparator<IBOrder> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ib orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ib orders
	*/
	public static List<IBOrder> findAll(int start, int end,
		OrderByComparator<IBOrder> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ib orders from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ib orders.
	*
	* @return the number of ib orders
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