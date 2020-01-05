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

import com.ibtrader.data.model.BackTesting;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the back testing service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.BackTestingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BackTestingPersistence
 * @see com.ibtrader.data.service.persistence.impl.BackTestingPersistenceImpl
 * @generated
 */
@ProviderType
public class BackTestingUtil {
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
	public static void clearCache(BackTesting backTesting) {
		getPersistence().clearCache(backTesting);
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
	public static List<BackTesting> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BackTesting> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BackTesting> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BackTesting update(BackTesting backTesting) {
		return getPersistence().update(backTesting);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BackTesting update(BackTesting backTesting,
		ServiceContext serviceContext) {
		return getPersistence().update(backTesting, serviceContext);
	}

	/**
	* Returns all the back testings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching back testings
	*/
	public static List<BackTesting> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the back testings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @return the range of matching back testings
	*/
	public static List<BackTesting> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the back testings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching back testings
	*/
	public static List<BackTesting> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the back testings where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching back testings
	*/
	public static List<BackTesting> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first back testing in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public static BackTesting findByUuid_First(java.lang.String uuid,
		OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first back testing in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public static BackTesting fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last back testing in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public static BackTesting findByUuid_Last(java.lang.String uuid,
		OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last back testing in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public static BackTesting fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the back testings before and after the current back testing in the ordered set where uuid = &#63;.
	*
	* @param backTId the primary key of the current back testing
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next back testing
	* @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	*/
	public static BackTesting[] findByUuid_PrevAndNext(long backTId,
		java.lang.String uuid, OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence()
				   .findByUuid_PrevAndNext(backTId, uuid, orderByComparator);
	}

	/**
	* Removes all the back testings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of back testings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching back testings
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the back testing where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBackTestingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public static BackTesting findByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the back testing where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public static BackTesting fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the back testing where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public static BackTesting fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the back testing where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the back testing that was removed
	*/
	public static BackTesting removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of back testings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching back testings
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the back testings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching back testings
	*/
	public static List<BackTesting> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the back testings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @return the range of matching back testings
	*/
	public static List<BackTesting> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the back testings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching back testings
	*/
	public static List<BackTesting> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the back testings where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching back testings
	*/
	public static List<BackTesting> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public static BackTesting findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public static BackTesting fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public static BackTesting findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public static BackTesting fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the back testings before and after the current back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param backTId the primary key of the current back testing
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next back testing
	* @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	*/
	public static BackTesting[] findByUuid_C_PrevAndNext(long backTId,
		java.lang.String uuid, long companyId,
		OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(backTId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the back testings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of back testings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching back testings
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the matching back testings
	*/
	public static List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId) {
		return getPersistence()
				   .findByShareCompanyGroup(shareId, companyId, groupId);
	}

	/**
	* Returns a range of all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @return the range of matching back testings
	*/
	public static List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId, int start, int end) {
		return getPersistence()
				   .findByShareCompanyGroup(shareId, companyId, groupId, start,
			end);
	}

	/**
	* Returns an ordered range of all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching back testings
	*/
	public static List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId, int start, int end,
		OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence()
				   .findByShareCompanyGroup(shareId, companyId, groupId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching back testings
	*/
	public static List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId, int start, int end,
		OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByShareCompanyGroup(shareId, companyId, groupId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public static BackTesting findByShareCompanyGroup_First(long shareId,
		long companyId, long groupId,
		OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence()
				   .findByShareCompanyGroup_First(shareId, companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the first back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public static BackTesting fetchByShareCompanyGroup_First(long shareId,
		long companyId, long groupId,
		OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence()
				   .fetchByShareCompanyGroup_First(shareId, companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public static BackTesting findByShareCompanyGroup_Last(long shareId,
		long companyId, long groupId,
		OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence()
				   .findByShareCompanyGroup_Last(shareId, companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public static BackTesting fetchByShareCompanyGroup_Last(long shareId,
		long companyId, long groupId,
		OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence()
				   .fetchByShareCompanyGroup_Last(shareId, companyId, groupId,
			orderByComparator);
	}

	/**
	* Returns the back testings before and after the current back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param backTId the primary key of the current back testing
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next back testing
	* @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	*/
	public static BackTesting[] findByShareCompanyGroup_PrevAndNext(
		long backTId, long shareId, long companyId, long groupId,
		OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence()
				   .findByShareCompanyGroup_PrevAndNext(backTId, shareId,
			companyId, groupId, orderByComparator);
	}

	/**
	* Removes all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63; from the database.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	*/
	public static void removeByShareCompanyGroup(long shareId, long companyId,
		long groupId) {
		getPersistence().removeByShareCompanyGroup(shareId, companyId, groupId);
	}

	/**
	* Returns the number of back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the number of matching back testings
	*/
	public static int countByShareCompanyGroup(long shareId, long companyId,
		long groupId) {
		return getPersistence()
				   .countByShareCompanyGroup(shareId, companyId, groupId);
	}

	/**
	* Returns all the back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @return the matching back testings
	*/
	public static List<BackTesting> findByStatusShareCompanyGroup(
		java.lang.String status, long companyId, long groupId, long shareId) {
		return getPersistence()
				   .findByStatusShareCompanyGroup(status, companyId, groupId,
			shareId);
	}

	/**
	* Returns a range of all the back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @return the range of matching back testings
	*/
	public static List<BackTesting> findByStatusShareCompanyGroup(
		java.lang.String status, long companyId, long groupId, long shareId,
		int start, int end) {
		return getPersistence()
				   .findByStatusShareCompanyGroup(status, companyId, groupId,
			shareId, start, end);
	}

	/**
	* Returns an ordered range of all the back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching back testings
	*/
	public static List<BackTesting> findByStatusShareCompanyGroup(
		java.lang.String status, long companyId, long groupId, long shareId,
		int start, int end, OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence()
				   .findByStatusShareCompanyGroup(status, companyId, groupId,
			shareId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching back testings
	*/
	public static List<BackTesting> findByStatusShareCompanyGroup(
		java.lang.String status, long companyId, long groupId, long shareId,
		int start, int end, OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByStatusShareCompanyGroup(status, companyId, groupId,
			shareId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public static BackTesting findByStatusShareCompanyGroup_First(
		java.lang.String status, long companyId, long groupId, long shareId,
		OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence()
				   .findByStatusShareCompanyGroup_First(status, companyId,
			groupId, shareId, orderByComparator);
	}

	/**
	* Returns the first back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public static BackTesting fetchByStatusShareCompanyGroup_First(
		java.lang.String status, long companyId, long groupId, long shareId,
		OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence()
				   .fetchByStatusShareCompanyGroup_First(status, companyId,
			groupId, shareId, orderByComparator);
	}

	/**
	* Returns the last back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public static BackTesting findByStatusShareCompanyGroup_Last(
		java.lang.String status, long companyId, long groupId, long shareId,
		OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence()
				   .findByStatusShareCompanyGroup_Last(status, companyId,
			groupId, shareId, orderByComparator);
	}

	/**
	* Returns the last back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public static BackTesting fetchByStatusShareCompanyGroup_Last(
		java.lang.String status, long companyId, long groupId, long shareId,
		OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence()
				   .fetchByStatusShareCompanyGroup_Last(status, companyId,
			groupId, shareId, orderByComparator);
	}

	/**
	* Returns the back testings before and after the current back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param backTId the primary key of the current back testing
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next back testing
	* @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	*/
	public static BackTesting[] findByStatusShareCompanyGroup_PrevAndNext(
		long backTId, java.lang.String status, long companyId, long groupId,
		long shareId, OrderByComparator<BackTesting> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence()
				   .findByStatusShareCompanyGroup_PrevAndNext(backTId, status,
			companyId, groupId, shareId, orderByComparator);
	}

	/**
	* Removes all the back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63; from the database.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	*/
	public static void removeByStatusShareCompanyGroup(
		java.lang.String status, long companyId, long groupId, long shareId) {
		getPersistence()
			.removeByStatusShareCompanyGroup(status, companyId, groupId, shareId);
	}

	/**
	* Returns the number of back testings where status = &#63; and companyId = &#63; and groupId = &#63; and shareId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param shareId the share ID
	* @return the number of matching back testings
	*/
	public static int countByStatusShareCompanyGroup(java.lang.String status,
		long companyId, long groupId, long shareId) {
		return getPersistence()
				   .countByStatusShareCompanyGroup(status, companyId, groupId,
			shareId);
	}

	/**
	* Caches the back testing in the entity cache if it is enabled.
	*
	* @param backTesting the back testing
	*/
	public static void cacheResult(BackTesting backTesting) {
		getPersistence().cacheResult(backTesting);
	}

	/**
	* Caches the back testings in the entity cache if it is enabled.
	*
	* @param backTestings the back testings
	*/
	public static void cacheResult(List<BackTesting> backTestings) {
		getPersistence().cacheResult(backTestings);
	}

	/**
	* Creates a new back testing with the primary key. Does not add the back testing to the database.
	*
	* @param backTId the primary key for the new back testing
	* @return the new back testing
	*/
	public static BackTesting create(long backTId) {
		return getPersistence().create(backTId);
	}

	/**
	* Removes the back testing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param backTId the primary key of the back testing
	* @return the back testing that was removed
	* @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	*/
	public static BackTesting remove(long backTId)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence().remove(backTId);
	}

	public static BackTesting updateImpl(BackTesting backTesting) {
		return getPersistence().updateImpl(backTesting);
	}

	/**
	* Returns the back testing with the primary key or throws a {@link NoSuchBackTestingException} if it could not be found.
	*
	* @param backTId the primary key of the back testing
	* @return the back testing
	* @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	*/
	public static BackTesting findByPrimaryKey(long backTId)
		throws com.ibtrader.data.exception.NoSuchBackTestingException {
		return getPersistence().findByPrimaryKey(backTId);
	}

	/**
	* Returns the back testing with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param backTId the primary key of the back testing
	* @return the back testing, or <code>null</code> if a back testing with the primary key could not be found
	*/
	public static BackTesting fetchByPrimaryKey(long backTId) {
		return getPersistence().fetchByPrimaryKey(backTId);
	}

	public static java.util.Map<java.io.Serializable, BackTesting> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the back testings.
	*
	* @return the back testings
	*/
	public static List<BackTesting> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the back testings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @return the range of back testings
	*/
	public static List<BackTesting> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the back testings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of back testings
	*/
	public static List<BackTesting> findAll(int start, int end,
		OrderByComparator<BackTesting> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the back testings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of back testings
	*/
	public static List<BackTesting> findAll(int start, int end,
		OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the back testings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of back testings.
	*
	* @return the number of back testings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static BackTestingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BackTestingPersistence, BackTestingPersistence> _serviceTracker =
		ServiceTrackerFactory.open(BackTestingPersistence.class);
}