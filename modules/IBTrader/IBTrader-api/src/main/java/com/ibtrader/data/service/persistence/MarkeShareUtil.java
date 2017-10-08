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

import com.ibtrader.data.model.MarkeShare;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the marke share service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.MarkeSharePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MarkeSharePersistence
 * @see com.ibtrader.data.service.persistence.impl.MarkeSharePersistenceImpl
 * @generated
 */
@ProviderType
public class MarkeShareUtil {
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
	public static void clearCache(MarkeShare markeShare) {
		getPersistence().clearCache(markeShare);
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
	public static List<MarkeShare> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MarkeShare> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MarkeShare> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MarkeShare> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MarkeShare update(MarkeShare markeShare) {
		return getPersistence().update(markeShare);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MarkeShare update(MarkeShare markeShare,
		ServiceContext serviceContext) {
		return getPersistence().update(markeShare, serviceContext);
	}

	/**
	* Returns all the marke shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching marke shares
	*/
	public static List<MarkeShare> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the marke shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @return the range of matching marke shares
	*/
	public static List<MarkeShare> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the marke shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching marke shares
	*/
	public static List<MarkeShare> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<MarkeShare> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the marke shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching marke shares
	*/
	public static List<MarkeShare> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<MarkeShare> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first marke share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marke share
	* @throws NoSuchMarkeShareException if a matching marke share could not be found
	*/
	public static MarkeShare findByUuid_First(java.lang.String uuid,
		OrderByComparator<MarkeShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarkeShareException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first marke share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public static MarkeShare fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<MarkeShare> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last marke share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marke share
	* @throws NoSuchMarkeShareException if a matching marke share could not be found
	*/
	public static MarkeShare findByUuid_Last(java.lang.String uuid,
		OrderByComparator<MarkeShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarkeShareException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last marke share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public static MarkeShare fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<MarkeShare> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the marke shares before and after the current marke share in the ordered set where uuid = &#63;.
	*
	* @param marketshareId the primary key of the current marke share
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next marke share
	* @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	*/
	public static MarkeShare[] findByUuid_PrevAndNext(long marketshareId,
		java.lang.String uuid, OrderByComparator<MarkeShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarkeShareException {
		return getPersistence()
				   .findByUuid_PrevAndNext(marketshareId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the marke shares where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of marke shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching marke shares
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the marke share where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchMarkeShareException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching marke share
	* @throws NoSuchMarkeShareException if a matching marke share could not be found
	*/
	public static MarkeShare findByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchMarkeShareException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the marke share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public static MarkeShare fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the marke share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public static MarkeShare fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the marke share where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the marke share that was removed
	*/
	public static MarkeShare removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchMarkeShareException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of marke shares where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching marke shares
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the marke shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching marke shares
	*/
	public static List<MarkeShare> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the marke shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @return the range of matching marke shares
	*/
	public static List<MarkeShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the marke shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching marke shares
	*/
	public static List<MarkeShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<MarkeShare> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the marke shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching marke shares
	*/
	public static List<MarkeShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<MarkeShare> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marke share
	* @throws NoSuchMarkeShareException if a matching marke share could not be found
	*/
	public static MarkeShare findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<MarkeShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarkeShareException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public static MarkeShare fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<MarkeShare> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marke share
	* @throws NoSuchMarkeShareException if a matching marke share could not be found
	*/
	public static MarkeShare findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<MarkeShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarkeShareException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public static MarkeShare fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<MarkeShare> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the marke shares before and after the current marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param marketshareId the primary key of the current marke share
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next marke share
	* @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	*/
	public static MarkeShare[] findByUuid_C_PrevAndNext(long marketshareId,
		java.lang.String uuid, long companyId,
		OrderByComparator<MarkeShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchMarkeShareException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(marketshareId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the marke shares where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of marke shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching marke shares
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the marke share in the entity cache if it is enabled.
	*
	* @param markeShare the marke share
	*/
	public static void cacheResult(MarkeShare markeShare) {
		getPersistence().cacheResult(markeShare);
	}

	/**
	* Caches the marke shares in the entity cache if it is enabled.
	*
	* @param markeShares the marke shares
	*/
	public static void cacheResult(List<MarkeShare> markeShares) {
		getPersistence().cacheResult(markeShares);
	}

	/**
	* Creates a new marke share with the primary key. Does not add the marke share to the database.
	*
	* @param marketshareId the primary key for the new marke share
	* @return the new marke share
	*/
	public static MarkeShare create(long marketshareId) {
		return getPersistence().create(marketshareId);
	}

	/**
	* Removes the marke share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param marketshareId the primary key of the marke share
	* @return the marke share that was removed
	* @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	*/
	public static MarkeShare remove(long marketshareId)
		throws com.ibtrader.data.exception.NoSuchMarkeShareException {
		return getPersistence().remove(marketshareId);
	}

	public static MarkeShare updateImpl(MarkeShare markeShare) {
		return getPersistence().updateImpl(markeShare);
	}

	/**
	* Returns the marke share with the primary key or throws a {@link NoSuchMarkeShareException} if it could not be found.
	*
	* @param marketshareId the primary key of the marke share
	* @return the marke share
	* @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	*/
	public static MarkeShare findByPrimaryKey(long marketshareId)
		throws com.ibtrader.data.exception.NoSuchMarkeShareException {
		return getPersistence().findByPrimaryKey(marketshareId);
	}

	/**
	* Returns the marke share with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param marketshareId the primary key of the marke share
	* @return the marke share, or <code>null</code> if a marke share with the primary key could not be found
	*/
	public static MarkeShare fetchByPrimaryKey(long marketshareId) {
		return getPersistence().fetchByPrimaryKey(marketshareId);
	}

	public static java.util.Map<java.io.Serializable, MarkeShare> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the marke shares.
	*
	* @return the marke shares
	*/
	public static List<MarkeShare> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the marke shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @return the range of marke shares
	*/
	public static List<MarkeShare> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the marke shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of marke shares
	*/
	public static List<MarkeShare> findAll(int start, int end,
		OrderByComparator<MarkeShare> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the marke shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of marke shares
	*/
	public static List<MarkeShare> findAll(int start, int end,
		OrderByComparator<MarkeShare> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the marke shares from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of marke shares.
	*
	* @return the number of marke shares
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MarkeSharePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MarkeSharePersistence, MarkeSharePersistence> _serviceTracker =
		ServiceTrackerFactory.open(MarkeSharePersistence.class);
}