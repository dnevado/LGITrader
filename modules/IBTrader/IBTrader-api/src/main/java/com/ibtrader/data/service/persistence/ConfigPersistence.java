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

import com.ibtrader.data.exception.NoSuchConfigException;
import com.ibtrader.data.model.Config;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.ConfigPersistenceImpl
 * @see ConfigUtil
 * @generated
 */
@ProviderType
public interface ConfigPersistence extends BasePersistence<Config> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ConfigUtil} to access the config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching configs
	*/
	public java.util.List<Config> findByUuid(java.lang.String uuid);

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
	public java.util.List<Config> findByUuid(java.lang.String uuid, int start,
		int end);

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
	public java.util.List<Config> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

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
	public java.util.List<Config> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public Config findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Returns the first config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

	/**
	* Returns the last config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public Config findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Returns the last config in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

	/**
	* Returns the configs before and after the current config in the ordered set where uuid = &#63;.
	*
	* @param configId the primary key of the current config
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config
	* @throws NoSuchConfigException if a config with the primary key could not be found
	*/
	public Config[] findByUuid_PrevAndNext(long configId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Removes all the configs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of configs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching configs
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the config where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchConfigException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public Config findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchConfigException;

	/**
	* Returns the config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the config where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the config where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the config that was removed
	*/
	public Config removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchConfigException;

	/**
	* Returns the number of configs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching configs
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching configs
	*/
	public java.util.List<Config> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<Config> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<Config> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

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
	public java.util.List<Config> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public Config findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Returns the first config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

	/**
	* Returns the last config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public Config findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Returns the last config in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

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
	public Config[] findByUuid_C_PrevAndNext(long configId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Removes all the configs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of configs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching configs
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the config where companyId = &#63; and groupId = &#63; and config_key = &#63; or throws a {@link NoSuchConfigException} if it could not be found.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param config_key the config_key
	* @return the matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public Config findByKeyCompanyGroup(long companyId, long groupId,
		java.lang.String config_key) throws NoSuchConfigException;

	/**
	* Returns the config where companyId = &#63; and groupId = &#63; and config_key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param config_key the config_key
	* @return the matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByKeyCompanyGroup(long companyId, long groupId,
		java.lang.String config_key);

	/**
	* Returns the config where companyId = &#63; and groupId = &#63; and config_key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param config_key the config_key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByKeyCompanyGroup(long companyId, long groupId,
		java.lang.String config_key, boolean retrieveFromCache);

	/**
	* Removes the config where companyId = &#63; and groupId = &#63; and config_key = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param config_key the config_key
	* @return the config that was removed
	*/
	public Config removeByKeyCompanyGroup(long companyId, long groupId,
		java.lang.String config_key) throws NoSuchConfigException;

	/**
	* Returns the number of configs where companyId = &#63; and groupId = &#63; and config_key = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param config_key the config_key
	* @return the number of matching configs
	*/
	public int countByKeyCompanyGroup(long companyId, long groupId,
		java.lang.String config_key);

	/**
	* Returns all the configs where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the matching configs
	*/
	public java.util.List<Config> findByCompanyGroup(long companyId,
		long groupId);

	/**
	* Returns a range of all the configs where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @return the range of matching configs
	*/
	public java.util.List<Config> findByCompanyGroup(long companyId,
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the configs where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching configs
	*/
	public java.util.List<Config> findByCompanyGroup(long companyId,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

	/**
	* Returns an ordered range of all the configs where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching configs
	*/
	public java.util.List<Config> findByCompanyGroup(long companyId,
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first config in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public Config findByCompanyGroup_First(long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Returns the first config in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByCompanyGroup_First(long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

	/**
	* Returns the last config in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public Config findByCompanyGroup_Last(long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Returns the last config in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByCompanyGroup_Last(long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

	/**
	* Returns the configs before and after the current config in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* @param configId the primary key of the current config
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next config
	* @throws NoSuchConfigException if a config with the primary key could not be found
	*/
	public Config[] findByCompanyGroup_PrevAndNext(long configId,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Removes all the configs where companyId = &#63; and groupId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	*/
	public void removeByCompanyGroup(long companyId, long groupId);

	/**
	* Returns the number of configs where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the number of matching configs
	*/
	public int countByCompanyGroup(long companyId, long groupId);

	/**
	* Returns all the configs where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @return the matching configs
	*/
	public java.util.List<Config> findByKeyGlobalDefault(
		java.lang.String config_key, boolean globaldefault);

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
	public java.util.List<Config> findByKeyGlobalDefault(
		java.lang.String config_key, boolean globaldefault, int start, int end);

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
	public java.util.List<Config> findByKeyGlobalDefault(
		java.lang.String config_key, boolean globaldefault, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

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
	public java.util.List<Config> findByKeyGlobalDefault(
		java.lang.String config_key, boolean globaldefault, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public Config findByKeyGlobalDefault_First(java.lang.String config_key,
		boolean globaldefault,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Returns the first config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByKeyGlobalDefault_First(java.lang.String config_key,
		boolean globaldefault,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

	/**
	* Returns the last config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public Config findByKeyGlobalDefault_Last(java.lang.String config_key,
		boolean globaldefault,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Returns the last config in the ordered set where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByKeyGlobalDefault_Last(java.lang.String config_key,
		boolean globaldefault,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

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
	public Config[] findByKeyGlobalDefault_PrevAndNext(long configId,
		java.lang.String config_key, boolean globaldefault,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator)
		throws NoSuchConfigException;

	/**
	* Removes all the configs where config_key = &#63; and globaldefault = &#63; from the database.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	*/
	public void removeByKeyGlobalDefault(java.lang.String config_key,
		boolean globaldefault);

	/**
	* Returns the number of configs where config_key = &#63; and globaldefault = &#63;.
	*
	* @param config_key the config_key
	* @param globaldefault the globaldefault
	* @return the number of matching configs
	*/
	public int countByKeyGlobalDefault(java.lang.String config_key,
		boolean globaldefault);

	/**
	* Returns the config where iscron = &#63; and value = &#63; or throws a {@link NoSuchConfigException} if it could not be found.
	*
	* @param iscron the iscron
	* @param value the value
	* @return the matching config
	* @throws NoSuchConfigException if a matching config could not be found
	*/
	public Config findByIsCronValue(boolean iscron, java.lang.String value)
		throws NoSuchConfigException;

	/**
	* Returns the config where iscron = &#63; and value = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param iscron the iscron
	* @param value the value
	* @return the matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByIsCronValue(boolean iscron, java.lang.String value);

	/**
	* Returns the config where iscron = &#63; and value = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param iscron the iscron
	* @param value the value
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching config, or <code>null</code> if a matching config could not be found
	*/
	public Config fetchByIsCronValue(boolean iscron, java.lang.String value,
		boolean retrieveFromCache);

	/**
	* Removes the config where iscron = &#63; and value = &#63; from the database.
	*
	* @param iscron the iscron
	* @param value the value
	* @return the config that was removed
	*/
	public Config removeByIsCronValue(boolean iscron, java.lang.String value)
		throws NoSuchConfigException;

	/**
	* Returns the number of configs where iscron = &#63; and value = &#63;.
	*
	* @param iscron the iscron
	* @param value the value
	* @return the number of matching configs
	*/
	public int countByIsCronValue(boolean iscron, java.lang.String value);

	/**
	* Caches the config in the entity cache if it is enabled.
	*
	* @param config the config
	*/
	public void cacheResult(Config config);

	/**
	* Caches the configs in the entity cache if it is enabled.
	*
	* @param configs the configs
	*/
	public void cacheResult(java.util.List<Config> configs);

	/**
	* Creates a new config with the primary key. Does not add the config to the database.
	*
	* @param configId the primary key for the new config
	* @return the new config
	*/
	public Config create(long configId);

	/**
	* Removes the config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param configId the primary key of the config
	* @return the config that was removed
	* @throws NoSuchConfigException if a config with the primary key could not be found
	*/
	public Config remove(long configId) throws NoSuchConfigException;

	public Config updateImpl(Config config);

	/**
	* Returns the config with the primary key or throws a {@link NoSuchConfigException} if it could not be found.
	*
	* @param configId the primary key of the config
	* @return the config
	* @throws NoSuchConfigException if a config with the primary key could not be found
	*/
	public Config findByPrimaryKey(long configId) throws NoSuchConfigException;

	/**
	* Returns the config with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param configId the primary key of the config
	* @return the config, or <code>null</code> if a config with the primary key could not be found
	*/
	public Config fetchByPrimaryKey(long configId);

	@Override
	public java.util.Map<java.io.Serializable, Config> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the configs.
	*
	* @return the configs
	*/
	public java.util.List<Config> findAll();

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
	public java.util.List<Config> findAll(int start, int end);

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
	public java.util.List<Config> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator);

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
	public java.util.List<Config> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Config> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the configs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of configs.
	*
	* @return the number of configs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}