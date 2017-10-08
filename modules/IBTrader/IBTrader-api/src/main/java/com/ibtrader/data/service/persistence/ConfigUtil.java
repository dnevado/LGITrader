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

import com.ibtrader.data.model.Config;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the config service. This utility wraps {@link com.ibtrader.data.service.persistence.impl.ConfigPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConfigPersistence
 * @see com.ibtrader.data.service.persistence.impl.ConfigPersistenceImpl
 * @generated
 */
@ProviderType
public class ConfigUtil {
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
	public static void clearCache(Config config) {
		getPersistence().clearCache(config);
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
	public static List<Config> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Config> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Config> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Config> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Config update(Config config) {
		return getPersistence().update(config);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Config update(Config config, ServiceContext serviceContext) {
		return getPersistence().update(config, serviceContext);
	}

	/**
	* Returns all the configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching configs
	*/
	public static List<Config> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @return the range of matching configs
	*/
	public static List<Config> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching configs
	*/
	public static List<Config> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Config> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the configs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching configs
	*/
	public static List<Config> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Config> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public static Config findByUuid_First(java.lang.String uuid,
		OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config, or <code>null</code> if a matching config could not be found
	*/
	public static Config fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Config> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public static Config findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config, or <code>null</code> if a matching config could not be found
	*/
	public static Config fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Config> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the configs before and after the current config in the ordered set where uuid = &#63;.
	*
	* @param configId the primary key of the current config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config
	* @throws NoSuchConfigException if a config with the primary key could not be found
	*/
	public static Config[] findByUuid_PrevAndNext(long configId,
		java.lang.String uuid, OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence()
				   .findByUuid_PrevAndNext(configId, uuid, orderByComparator);
	}

	/**
	* Removes all the configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching configs
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public static Config findByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching config, or <code>null</code> if a matching config could not be found
	*/
	public static Config fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching config, or <code>null</code> if a matching config could not be found
	*/
	public static Config fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the config that was removed
	*/
	public static Config removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching configs
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching configs
	*/
	public static List<Config> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @return the range of matching configs
	*/
	public static List<Config> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching configs
	*/
	public static List<Config> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Config> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the configs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching configs
	*/
	public static List<Config> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Config> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public static Config findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config, or <code>null</code> if a matching config could not be found
	*/
	public static Config fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Config> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public static Config findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config, or <code>null</code> if a matching config could not be found
	*/
	public static Config fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Config> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the configs before and after the current config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param configId the primary key of the current config
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config
	* @throws NoSuchConfigException if a config with the primary key could not be found
	*/
	public static Config[] findByUuid_C_PrevAndNext(long configId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(configId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching configs
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the configs where companyId = &#63; and config_key = &#63;.
	*
	* @param companyId the company ID
	* @param config_key the config_key
	* @return the matching configs
	*/
	public static List<Config> findByKeyCompany(long companyId,
		java.lang.String config_key) {
		return getPersistence().findByKeyCompany(companyId, config_key);
	}

	/**
	* Returns a range of all the configs where companyId = &#63; and config_key = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param config_key the config_key
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @return the range of matching configs
	*/
	public static List<Config> findByKeyCompany(long companyId,
		java.lang.String config_key, int start, int end) {
		return getPersistence()
				   .findByKeyCompany(companyId, config_key, start, end);
	}

	/**
	* Returns an ordered range of all the configs where companyId = &#63; and config_key = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param config_key the config_key
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching configs
	*/
	public static List<Config> findByKeyCompany(long companyId,
		java.lang.String config_key, int start, int end,
		OrderByComparator<Config> orderByComparator) {
		return getPersistence()
				   .findByKeyCompany(companyId, config_key, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the configs where companyId = &#63; and config_key = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param config_key the config_key
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching configs
	*/
	public static List<Config> findByKeyCompany(long companyId,
		java.lang.String config_key, int start, int end,
		OrderByComparator<Config> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByKeyCompany(companyId, config_key, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first config in the ordered set where companyId = &#63; and config_key = &#63;.
	*
	* @param companyId the company ID
	* @param config_key the config_key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public static Config findByKeyCompany_First(long companyId,
		java.lang.String config_key, OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence()
				   .findByKeyCompany_First(companyId, config_key,
			orderByComparator);
	}

	/**
	* Returns the first config in the ordered set where companyId = &#63; and config_key = &#63;.
	*
	* @param companyId the company ID
	* @param config_key the config_key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config, or <code>null</code> if a matching config could not be found
	*/
	public static Config fetchByKeyCompany_First(long companyId,
		java.lang.String config_key, OrderByComparator<Config> orderByComparator) {
		return getPersistence()
				   .fetchByKeyCompany_First(companyId, config_key,
			orderByComparator);
	}

	/**
	* Returns the last config in the ordered set where companyId = &#63; and config_key = &#63;.
	*
	* @param companyId the company ID
	* @param config_key the config_key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public static Config findByKeyCompany_Last(long companyId,
		java.lang.String config_key, OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence()
				   .findByKeyCompany_Last(companyId, config_key,
			orderByComparator);
	}

	/**
	* Returns the last config in the ordered set where companyId = &#63; and config_key = &#63;.
	*
	* @param companyId the company ID
	* @param config_key the config_key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config, or <code>null</code> if a matching config could not be found
	*/
	public static Config fetchByKeyCompany_Last(long companyId,
		java.lang.String config_key, OrderByComparator<Config> orderByComparator) {
		return getPersistence()
				   .fetchByKeyCompany_Last(companyId, config_key,
			orderByComparator);
	}

	/**
	* Returns the configs before and after the current config in the ordered set where companyId = &#63; and config_key = &#63;.
	*
	* @param configId the primary key of the current config
	* @param companyId the company ID
	* @param config_key the config_key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config
	* @throws NoSuchConfigException if a config with the primary key could not be found
	*/
	public static Config[] findByKeyCompany_PrevAndNext(long configId,
		long companyId, java.lang.String config_key,
		OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence()
				   .findByKeyCompany_PrevAndNext(configId, companyId,
			config_key, orderByComparator);
	}

	/**
	* Removes all the configs where companyId = &#63; and config_key = &#63; from the database.
	*
	* @param companyId the company ID
	* @param config_key the config_key
	*/
	public static void removeByKeyCompany(long companyId,
		java.lang.String config_key) {
		getPersistence().removeByKeyCompany(companyId, config_key);
	}

	/**
	* Returns the number of configs where companyId = &#63; and config_key = &#63;.
	*
	* @param companyId the company ID
	* @param config_key the config_key
	* @return the number of matching configs
	*/
	public static int countByKeyCompany(long companyId,
		java.lang.String config_key) {
		return getPersistence().countByKeyCompany(companyId, config_key);
	}

	/**
	* Returns all the configs where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @return the matching configs
	*/
	public static List<Config> findByKeyGlobalDefault(
		java.lang.String config_key, boolean globaldefault) {
		return getPersistence().findByKeyGlobalDefault(config_key, globaldefault);
	}

	/**
	* Returns a range of all the configs where config_key = &#63; and globaldefault = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @return the range of matching configs
	*/
	public static List<Config> findByKeyGlobalDefault(
		java.lang.String config_key, boolean globaldefault, int start, int end) {
		return getPersistence()
				   .findByKeyGlobalDefault(config_key, globaldefault, start, end);
	}

	/**
	* Returns an ordered range of all the configs where config_key = &#63; and globaldefault = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching configs
	*/
	public static List<Config> findByKeyGlobalDefault(
		java.lang.String config_key, boolean globaldefault, int start, int end,
		OrderByComparator<Config> orderByComparator) {
		return getPersistence()
				   .findByKeyGlobalDefault(config_key, globaldefault, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the configs where config_key = &#63; and globaldefault = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching configs
	*/
	public static List<Config> findByKeyGlobalDefault(
		java.lang.String config_key, boolean globaldefault, int start, int end,
		OrderByComparator<Config> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByKeyGlobalDefault(config_key, globaldefault, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public static Config findByKeyGlobalDefault_First(
		java.lang.String config_key, boolean globaldefault,
		OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence()
				   .findByKeyGlobalDefault_First(config_key, globaldefault,
			orderByComparator);
	}

	/**
	* Returns the first config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config, or <code>null</code> if a matching config could not be found
	*/
	public static Config fetchByKeyGlobalDefault_First(
		java.lang.String config_key, boolean globaldefault,
		OrderByComparator<Config> orderByComparator) {
		return getPersistence()
				   .fetchByKeyGlobalDefault_First(config_key, globaldefault,
			orderByComparator);
	}

	/**
	* Returns the last config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public static Config findByKeyGlobalDefault_Last(
		java.lang.String config_key, boolean globaldefault,
		OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence()
				   .findByKeyGlobalDefault_Last(config_key, globaldefault,
			orderByComparator);
	}

	/**
	* Returns the last config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config, or <code>null</code> if a matching config could not be found
	*/
	public static Config fetchByKeyGlobalDefault_Last(
		java.lang.String config_key, boolean globaldefault,
		OrderByComparator<Config> orderByComparator) {
		return getPersistence()
				   .fetchByKeyGlobalDefault_Last(config_key, globaldefault,
			orderByComparator);
	}

	/**
	* Returns the configs before and after the current config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	*
	* @param configId the primary key of the current config
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config
	* @throws NoSuchConfigException if a config with the primary key could not be found
	*/
	public static Config[] findByKeyGlobalDefault_PrevAndNext(long configId,
		java.lang.String config_key, boolean globaldefault,
		OrderByComparator<Config> orderByComparator)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence()
				   .findByKeyGlobalDefault_PrevAndNext(configId, config_key,
			globaldefault, orderByComparator);
	}

	/**
	* Removes all the configs where config_key = &#63; and globaldefault = &#63; from the database.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	*/
	public static void removeByKeyGlobalDefault(java.lang.String config_key,
		boolean globaldefault) {
		getPersistence().removeByKeyGlobalDefault(config_key, globaldefault);
	}

	/**
	* Returns the number of configs where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @return the number of matching configs
	*/
	public static int countByKeyGlobalDefault(java.lang.String config_key,
		boolean globaldefault) {
		return getPersistence()
				   .countByKeyGlobalDefault(config_key, globaldefault);
	}

	/**
	* Caches the config in the entity cache if it is enabled.
	*
	* @param config the config
	*/
	public static void cacheResult(Config config) {
		getPersistence().cacheResult(config);
	}

	/**
	* Caches the configs in the entity cache if it is enabled.
	*
	* @param configs the configs
	*/
	public static void cacheResult(List<Config> configs) {
		getPersistence().cacheResult(configs);
	}

	/**
	* Creates a new config with the primary key. Does not add the config to the database.
	*
	* @param configId the primary key for the new config
	* @return the new config
	*/
	public static Config create(long configId) {
		return getPersistence().create(configId);
	}

	/**
	* Removes the config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param configId the primary key of the config
	* @return the config that was removed
	* @throws NoSuchConfigException if a config with the primary key could not be found
	*/
	public static Config remove(long configId)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence().remove(configId);
	}

	public static Config updateImpl(Config config) {
		return getPersistence().updateImpl(config);
	}

	/**
	* Returns the config with the primary key or throws a {@link NoSuchConfigException} if it could not be found.
	*
	* @param configId the primary key of the config
	* @return the config
	* @throws NoSuchConfigException if a config with the primary key could not be found
	*/
	public static Config findByPrimaryKey(long configId)
		throws com.ibtrader.data.exception.NoSuchConfigException {
		return getPersistence().findByPrimaryKey(configId);
	}

	/**
	* Returns the config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param configId the primary key of the config
	* @return the config, or <code>null</code> if a config with the primary key could not be found
	*/
	public static Config fetchByPrimaryKey(long configId) {
		return getPersistence().fetchByPrimaryKey(configId);
	}

	public static java.util.Map<java.io.Serializable, Config> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the configs.
	*
	* @return the configs
	*/
	public static List<Config> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @return the range of configs
	*/
	public static List<Config> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of configs
	*/
	public static List<Config> findAll(int start, int end,
		OrderByComparator<Config> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of configs
	*/
	public static List<Config> findAll(int start, int end,
		OrderByComparator<Config> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the configs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of configs.
	*
	* @return the number of configs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ConfigPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ConfigPersistence, ConfigPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ConfigPersistence.class);
}