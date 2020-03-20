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

import com.ibtrader.data.model.AuditIndicatorsStrategy;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the audit indicators strategy service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.AuditIndicatorsStrategyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditIndicatorsStrategyPersistence
 * @see com.ibtrader.data.service.persistence.impl.AuditIndicatorsStrategyPersistenceImpl
 * @generated
 */
@ProviderType
public class AuditIndicatorsStrategyUtil {
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
	public static void clearCache(
		AuditIndicatorsStrategy auditIndicatorsStrategy) {
		getPersistence().clearCache(auditIndicatorsStrategy);
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
	public static List<AuditIndicatorsStrategy> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditIndicatorsStrategy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditIndicatorsStrategy> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AuditIndicatorsStrategy update(
		AuditIndicatorsStrategy auditIndicatorsStrategy) {
		return getPersistence().update(auditIndicatorsStrategy);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AuditIndicatorsStrategy update(
		AuditIndicatorsStrategy auditIndicatorsStrategy,
		ServiceContext serviceContext) {
		return getPersistence().update(auditIndicatorsStrategy, serviceContext);
	}

	/**
	* Returns all the audit indicators strategies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findByUuid(
		java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the audit indicators strategies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @return the range of matching audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findByUuid(
		java.lang.String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the audit indicators strategies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findByUuid(
		java.lang.String uuid, int start, int end,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit indicators strategies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findByUuid(
		java.lang.String uuid, int start, int end,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first audit indicators strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	*/
	public static AuditIndicatorsStrategy findByUuid_First(
		java.lang.String uuid,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first audit indicators strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public static AuditIndicatorsStrategy fetchByUuid_First(
		java.lang.String uuid,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last audit indicators strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	*/
	public static AuditIndicatorsStrategy findByUuid_Last(
		java.lang.String uuid,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last audit indicators strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public static AuditIndicatorsStrategy fetchByUuid_Last(
		java.lang.String uuid,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the audit indicators strategies before and after the current audit indicators strategy in the ordered set where uuid = &#63;.
	*
	* @param auditIndicatorsStrategyPK the primary key of the current audit indicators strategy
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	*/
	public static AuditIndicatorsStrategy[] findByUuid_PrevAndNext(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK,
		java.lang.String uuid,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException {
		return getPersistence()
				   .findByUuid_PrevAndNext(auditIndicatorsStrategyPK, uuid,
			orderByComparator);
	}

	/**
	* Removes all the audit indicators strategies where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of audit indicators strategies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching audit indicators strategies
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the audit indicators strategy where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAuditIndicatorsStrategyException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	*/
	public static AuditIndicatorsStrategy findByUUID_G(java.lang.String uuid,
		long groupId)
		throws com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the audit indicators strategy where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public static AuditIndicatorsStrategy fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the audit indicators strategy where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public static AuditIndicatorsStrategy fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the audit indicators strategy where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the audit indicators strategy that was removed
	*/
	public static AuditIndicatorsStrategy removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of audit indicators strategies where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching audit indicators strategies
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findByUuid_C(
		java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @return the range of matching audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit indicators strategies where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	*/
	public static AuditIndicatorsStrategy findByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public static AuditIndicatorsStrategy fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a matching audit indicators strategy could not be found
	*/
	public static AuditIndicatorsStrategy findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public static AuditIndicatorsStrategy fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the audit indicators strategies before and after the current audit indicators strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param auditIndicatorsStrategyPK the primary key of the current audit indicators strategy
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	*/
	public static AuditIndicatorsStrategy[] findByUuid_C_PrevAndNext(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK,
		java.lang.String uuid, long companyId,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(auditIndicatorsStrategyPK, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the audit indicators strategies where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of audit indicators strategies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching audit indicators strategies
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the audit indicators strategy in the entity cache if it is enabled.
	*
	* @param auditIndicatorsStrategy the audit indicators strategy
	*/
	public static void cacheResult(
		AuditIndicatorsStrategy auditIndicatorsStrategy) {
		getPersistence().cacheResult(auditIndicatorsStrategy);
	}

	/**
	* Caches the audit indicators strategies in the entity cache if it is enabled.
	*
	* @param auditIndicatorsStrategies the audit indicators strategies
	*/
	public static void cacheResult(
		List<AuditIndicatorsStrategy> auditIndicatorsStrategies) {
		getPersistence().cacheResult(auditIndicatorsStrategies);
	}

	/**
	* Creates a new audit indicators strategy with the primary key. Does not add the audit indicators strategy to the database.
	*
	* @param auditIndicatorsStrategyPK the primary key for the new audit indicators strategy
	* @return the new audit indicators strategy
	*/
	public static AuditIndicatorsStrategy create(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK) {
		return getPersistence().create(auditIndicatorsStrategyPK);
	}

	/**
	* Removes the audit indicators strategy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	* @return the audit indicators strategy that was removed
	* @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	*/
	public static AuditIndicatorsStrategy remove(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK)
		throws com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException {
		return getPersistence().remove(auditIndicatorsStrategyPK);
	}

	public static AuditIndicatorsStrategy updateImpl(
		AuditIndicatorsStrategy auditIndicatorsStrategy) {
		return getPersistence().updateImpl(auditIndicatorsStrategy);
	}

	/**
	* Returns the audit indicators strategy with the primary key or throws a {@link NoSuchAuditIndicatorsStrategyException} if it could not be found.
	*
	* @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	* @return the audit indicators strategy
	* @throws NoSuchAuditIndicatorsStrategyException if a audit indicators strategy with the primary key could not be found
	*/
	public static AuditIndicatorsStrategy findByPrimaryKey(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK)
		throws com.ibtrader.data.exception.NoSuchAuditIndicatorsStrategyException {
		return getPersistence().findByPrimaryKey(auditIndicatorsStrategyPK);
	}

	/**
	* Returns the audit indicators strategy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	* @return the audit indicators strategy, or <code>null</code> if a audit indicators strategy with the primary key could not be found
	*/
	public static AuditIndicatorsStrategy fetchByPrimaryKey(
		AuditIndicatorsStrategyPK auditIndicatorsStrategyPK) {
		return getPersistence().fetchByPrimaryKey(auditIndicatorsStrategyPK);
	}

	public static java.util.Map<java.io.Serializable, AuditIndicatorsStrategy> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the audit indicators strategies.
	*
	* @return the audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the audit indicators strategies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @return the range of audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the audit indicators strategies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findAll(int start, int end,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the audit indicators strategies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of audit indicators strategies
	*/
	public static List<AuditIndicatorsStrategy> findAll(int start, int end,
		OrderByComparator<AuditIndicatorsStrategy> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the audit indicators strategies from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of audit indicators strategies.
	*
	* @return the number of audit indicators strategies
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AuditIndicatorsStrategyPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AuditIndicatorsStrategyPersistence, AuditIndicatorsStrategyPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AuditIndicatorsStrategyPersistence.class);
}