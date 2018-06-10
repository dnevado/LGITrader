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

import com.ibtrader.data.model.StrategyShare;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the strategy share service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.StrategySharePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrategySharePersistence
 * @see com.ibtrader.data.service.persistence.impl.StrategySharePersistenceImpl
 * @generated
 */
@ProviderType
public class StrategyShareUtil {
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
	public static void clearCache(StrategyShare strategyShare) {
		getPersistence().clearCache(strategyShare);
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
	public static List<StrategyShare> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StrategyShare> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StrategyShare> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StrategyShare update(StrategyShare strategyShare) {
		return getPersistence().update(strategyShare);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StrategyShare update(StrategyShare strategyShare,
		ServiceContext serviceContext) {
		return getPersistence().update(strategyShare, serviceContext);
	}

	/**
	* Returns all the strategy shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching strategy shares
	*/
	public static List<StrategyShare> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the strategy shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @return the range of matching strategy shares
	*/
	public static List<StrategyShare> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the strategy shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategy shares
	*/
	public static List<StrategyShare> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategy shares where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategy shares
	*/
	public static List<StrategyShare> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<StrategyShare> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first strategy share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public static StrategyShare findByUuid_First(java.lang.String uuid,
		OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first strategy share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last strategy share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public static StrategyShare findByUuid_Last(java.lang.String uuid,
		OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last strategy share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the strategy shares before and after the current strategy share in the ordered set where uuid = &#63;.
	*
	* @param strategyshareId the primary key of the current strategy share
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy share
	* @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	*/
	public static StrategyShare[] findByUuid_PrevAndNext(long strategyshareId,
		java.lang.String uuid,
		OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .findByUuid_PrevAndNext(strategyshareId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the strategy shares where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of strategy shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching strategy shares
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the strategy share where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchStrategyShareException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public static StrategyShare findByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the strategy share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the strategy share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the strategy share where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the strategy share that was removed
	*/
	public static StrategyShare removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of strategy shares where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching strategy shares
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the strategy shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching strategy shares
	*/
	public static List<StrategyShare> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the strategy shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @return the range of matching strategy shares
	*/
	public static List<StrategyShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the strategy shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategy shares
	*/
	public static List<StrategyShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategy shares where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategy shares
	*/
	public static List<StrategyShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<StrategyShare> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public static StrategyShare findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public static StrategyShare findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the strategy shares before and after the current strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param strategyshareId the primary key of the current strategy share
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy share
	* @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	*/
	public static StrategyShare[] findByUuid_C_PrevAndNext(
		long strategyshareId, java.lang.String uuid, long companyId,
		OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(strategyshareId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the strategy shares where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of strategy shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching strategy shares
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @return the matching strategy shares
	*/
	public static List<StrategyShare> findByCommpanyShareId(long shareId,
		long groupId, long companyId) {
		return getPersistence()
				   .findByCommpanyShareId(shareId, groupId, companyId);
	}

	/**
	* Returns a range of all the strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @return the range of matching strategy shares
	*/
	public static List<StrategyShare> findByCommpanyShareId(long shareId,
		long groupId, long companyId, int start, int end) {
		return getPersistence()
				   .findByCommpanyShareId(shareId, groupId, companyId, start,
			end);
	}

	/**
	* Returns an ordered range of all the strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategy shares
	*/
	public static List<StrategyShare> findByCommpanyShareId(long shareId,
		long groupId, long companyId, int start, int end,
		OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence()
				   .findByCommpanyShareId(shareId, groupId, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategy shares
	*/
	public static List<StrategyShare> findByCommpanyShareId(long shareId,
		long groupId, long companyId, int start, int end,
		OrderByComparator<StrategyShare> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommpanyShareId(shareId, groupId, companyId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first strategy share in the ordered set where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public static StrategyShare findByCommpanyShareId_First(long shareId,
		long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .findByCommpanyShareId_First(shareId, groupId, companyId,
			orderByComparator);
	}

	/**
	* Returns the first strategy share in the ordered set where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByCommpanyShareId_First(long shareId,
		long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence()
				   .fetchByCommpanyShareId_First(shareId, groupId, companyId,
			orderByComparator);
	}

	/**
	* Returns the last strategy share in the ordered set where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public static StrategyShare findByCommpanyShareId_Last(long shareId,
		long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .findByCommpanyShareId_Last(shareId, groupId, companyId,
			orderByComparator);
	}

	/**
	* Returns the last strategy share in the ordered set where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByCommpanyShareId_Last(long shareId,
		long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence()
				   .fetchByCommpanyShareId_Last(shareId, groupId, companyId,
			orderByComparator);
	}

	/**
	* Returns the strategy shares before and after the current strategy share in the ordered set where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param strategyshareId the primary key of the current strategy share
	* @param shareId the share ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy share
	* @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	*/
	public static StrategyShare[] findByCommpanyShareId_PrevAndNext(
		long strategyshareId, long shareId, long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .findByCommpanyShareId_PrevAndNext(strategyshareId, shareId,
			groupId, companyId, orderByComparator);
	}

	/**
	* Removes all the strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63; from the database.
	*
	* @param shareId the share ID
	* @param groupId the group ID
	* @param companyId the company ID
	*/
	public static void removeByCommpanyShareId(long shareId, long groupId,
		long companyId) {
		getPersistence().removeByCommpanyShareId(shareId, groupId, companyId);
	}

	/**
	* Returns the number of strategy shares where shareId = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @return the number of matching strategy shares
	*/
	public static int countByCommpanyShareId(long shareId, long groupId,
		long companyId) {
		return getPersistence()
				   .countByCommpanyShareId(shareId, groupId, companyId);
	}

	/**
	* Returns the strategy share where shareId = &#63; and strategyId = &#63; and groupId = &#63; and companyId = &#63; or throws a {@link NoSuchStrategyShareException} if it could not be found.
	*
	* @param shareId the share ID
	* @param strategyId the strategy ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @return the matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public static StrategyShare findByCommpanyShareStrategyId(long shareId,
		long strategyId, long groupId, long companyId)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .findByCommpanyShareStrategyId(shareId, strategyId, groupId,
			companyId);
	}

	/**
	* Returns the strategy share where shareId = &#63; and strategyId = &#63; and groupId = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param shareId the share ID
	* @param strategyId the strategy ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @return the matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByCommpanyShareStrategyId(long shareId,
		long strategyId, long groupId, long companyId) {
		return getPersistence()
				   .fetchByCommpanyShareStrategyId(shareId, strategyId,
			groupId, companyId);
	}

	/**
	* Returns the strategy share where shareId = &#63; and strategyId = &#63; and groupId = &#63; and companyId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param shareId the share ID
	* @param strategyId the strategy ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByCommpanyShareStrategyId(long shareId,
		long strategyId, long groupId, long companyId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCommpanyShareStrategyId(shareId, strategyId,
			groupId, companyId, retrieveFromCache);
	}

	/**
	* Removes the strategy share where shareId = &#63; and strategyId = &#63; and groupId = &#63; and companyId = &#63; from the database.
	*
	* @param shareId the share ID
	* @param strategyId the strategy ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @return the strategy share that was removed
	*/
	public static StrategyShare removeByCommpanyShareStrategyId(long shareId,
		long strategyId, long groupId, long companyId)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .removeByCommpanyShareStrategyId(shareId, strategyId,
			groupId, companyId);
	}

	/**
	* Returns the number of strategy shares where shareId = &#63; and strategyId = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param strategyId the strategy ID
	* @param groupId the group ID
	* @param companyId the company ID
	* @return the number of matching strategy shares
	*/
	public static int countByCommpanyShareStrategyId(long shareId,
		long strategyId, long groupId, long companyId) {
		return getPersistence()
				   .countByCommpanyShareStrategyId(shareId, strategyId,
			groupId, companyId);
	}

	/**
	* Returns all the strategy shares where shareId = &#63; and active = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param active the active
	* @param groupId the group ID
	* @param companyId the company ID
	* @return the matching strategy shares
	*/
	public static List<StrategyShare> findByActiveCommpanyGroupShare(
		long shareId, boolean active, long groupId, long companyId) {
		return getPersistence()
				   .findByActiveCommpanyGroupShare(shareId, active, groupId,
			companyId);
	}

	/**
	* Returns a range of all the strategy shares where shareId = &#63; and active = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param active the active
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @return the range of matching strategy shares
	*/
	public static List<StrategyShare> findByActiveCommpanyGroupShare(
		long shareId, boolean active, long groupId, long companyId, int start,
		int end) {
		return getPersistence()
				   .findByActiveCommpanyGroupShare(shareId, active, groupId,
			companyId, start, end);
	}

	/**
	* Returns an ordered range of all the strategy shares where shareId = &#63; and active = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param active the active
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strategy shares
	*/
	public static List<StrategyShare> findByActiveCommpanyGroupShare(
		long shareId, boolean active, long groupId, long companyId, int start,
		int end, OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence()
				   .findByActiveCommpanyGroupShare(shareId, active, groupId,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategy shares where shareId = &#63; and active = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareId the share ID
	* @param active the active
	* @param groupId the group ID
	* @param companyId the company ID
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strategy shares
	*/
	public static List<StrategyShare> findByActiveCommpanyGroupShare(
		long shareId, boolean active, long groupId, long companyId, int start,
		int end, OrderByComparator<StrategyShare> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByActiveCommpanyGroupShare(shareId, active, groupId,
			companyId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first strategy share in the ordered set where shareId = &#63; and active = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param active the active
	* @param groupId the group ID
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public static StrategyShare findByActiveCommpanyGroupShare_First(
		long shareId, boolean active, long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .findByActiveCommpanyGroupShare_First(shareId, active,
			groupId, companyId, orderByComparator);
	}

	/**
	* Returns the first strategy share in the ordered set where shareId = &#63; and active = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param active the active
	* @param groupId the group ID
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByActiveCommpanyGroupShare_First(
		long shareId, boolean active, long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence()
				   .fetchByActiveCommpanyGroupShare_First(shareId, active,
			groupId, companyId, orderByComparator);
	}

	/**
	* Returns the last strategy share in the ordered set where shareId = &#63; and active = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param active the active
	* @param groupId the group ID
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public static StrategyShare findByActiveCommpanyGroupShare_Last(
		long shareId, boolean active, long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .findByActiveCommpanyGroupShare_Last(shareId, active,
			groupId, companyId, orderByComparator);
	}

	/**
	* Returns the last strategy share in the ordered set where shareId = &#63; and active = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param active the active
	* @param groupId the group ID
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public static StrategyShare fetchByActiveCommpanyGroupShare_Last(
		long shareId, boolean active, long groupId, long companyId,
		OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence()
				   .fetchByActiveCommpanyGroupShare_Last(shareId, active,
			groupId, companyId, orderByComparator);
	}

	/**
	* Returns the strategy shares before and after the current strategy share in the ordered set where shareId = &#63; and active = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param strategyshareId the primary key of the current strategy share
	* @param shareId the share ID
	* @param active the active
	* @param groupId the group ID
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy share
	* @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	*/
	public static StrategyShare[] findByActiveCommpanyGroupShare_PrevAndNext(
		long strategyshareId, long shareId, boolean active, long groupId,
		long companyId, OrderByComparator<StrategyShare> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence()
				   .findByActiveCommpanyGroupShare_PrevAndNext(strategyshareId,
			shareId, active, groupId, companyId, orderByComparator);
	}

	/**
	* Removes all the strategy shares where shareId = &#63; and active = &#63; and groupId = &#63; and companyId = &#63; from the database.
	*
	* @param shareId the share ID
	* @param active the active
	* @param groupId the group ID
	* @param companyId the company ID
	*/
	public static void removeByActiveCommpanyGroupShare(long shareId,
		boolean active, long groupId, long companyId) {
		getPersistence()
			.removeByActiveCommpanyGroupShare(shareId, active, groupId,
			companyId);
	}

	/**
	* Returns the number of strategy shares where shareId = &#63; and active = &#63; and groupId = &#63; and companyId = &#63;.
	*
	* @param shareId the share ID
	* @param active the active
	* @param groupId the group ID
	* @param companyId the company ID
	* @return the number of matching strategy shares
	*/
	public static int countByActiveCommpanyGroupShare(long shareId,
		boolean active, long groupId, long companyId) {
		return getPersistence()
				   .countByActiveCommpanyGroupShare(shareId, active, groupId,
			companyId);
	}

	/**
	* Caches the strategy share in the entity cache if it is enabled.
	*
	* @param strategyShare the strategy share
	*/
	public static void cacheResult(StrategyShare strategyShare) {
		getPersistence().cacheResult(strategyShare);
	}

	/**
	* Caches the strategy shares in the entity cache if it is enabled.
	*
	* @param strategyShares the strategy shares
	*/
	public static void cacheResult(List<StrategyShare> strategyShares) {
		getPersistence().cacheResult(strategyShares);
	}

	/**
	* Creates a new strategy share with the primary key. Does not add the strategy share to the database.
	*
	* @param strategyshareId the primary key for the new strategy share
	* @return the new strategy share
	*/
	public static StrategyShare create(long strategyshareId) {
		return getPersistence().create(strategyshareId);
	}

	/**
	* Removes the strategy share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share that was removed
	* @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	*/
	public static StrategyShare remove(long strategyshareId)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence().remove(strategyshareId);
	}

	public static StrategyShare updateImpl(StrategyShare strategyShare) {
		return getPersistence().updateImpl(strategyShare);
	}

	/**
	* Returns the strategy share with the primary key or throws a {@link NoSuchStrategyShareException} if it could not be found.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share
	* @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	*/
	public static StrategyShare findByPrimaryKey(long strategyshareId)
		throws com.ibtrader.data.exception.NoSuchStrategyShareException {
		return getPersistence().findByPrimaryKey(strategyshareId);
	}

	/**
	* Returns the strategy share with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share, or <code>null</code> if a strategy share with the primary key could not be found
	*/
	public static StrategyShare fetchByPrimaryKey(long strategyshareId) {
		return getPersistence().fetchByPrimaryKey(strategyshareId);
	}

	public static java.util.Map<java.io.Serializable, StrategyShare> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the strategy shares.
	*
	* @return the strategy shares
	*/
	public static List<StrategyShare> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the strategy shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @return the range of strategy shares
	*/
	public static List<StrategyShare> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the strategy shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of strategy shares
	*/
	public static List<StrategyShare> findAll(int start, int end,
		OrderByComparator<StrategyShare> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strategy shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of strategy shares
	*/
	public static List<StrategyShare> findAll(int start, int end,
		OrderByComparator<StrategyShare> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the strategy shares from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of strategy shares.
	*
	* @return the number of strategy shares
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StrategySharePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StrategySharePersistence, StrategySharePersistence> _serviceTracker =
		ServiceTrackerFactory.open(StrategySharePersistence.class);
}