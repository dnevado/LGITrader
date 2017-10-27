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

import com.ibtrader.data.exception.NoSuchStrategyShareException;
import com.ibtrader.data.model.StrategyShare;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the strategy share service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.StrategySharePersistenceImpl
 * @see StrategyShareUtil
 * @generated
 */
@ProviderType
public interface StrategySharePersistence extends BasePersistence<StrategyShare> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StrategyShareUtil} to access the strategy share persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the strategy shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching strategy shares
	*/
	public java.util.List<StrategyShare> findByUuid(java.lang.String uuid);

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
	public java.util.List<StrategyShare> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<StrategyShare> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator);

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
	public java.util.List<StrategyShare> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first strategy share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public StrategyShare findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException;

	/**
	* Returns the first strategy share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public StrategyShare fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator);

	/**
	* Returns the last strategy share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public StrategyShare findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException;

	/**
	* Returns the last strategy share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public StrategyShare fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator);

	/**
	* Returns the strategy shares before and after the current strategy share in the ordered set where uuid = &#63;.
	*
	* @param strategyshareId the primary key of the current strategy share
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy share
	* @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	*/
	public StrategyShare[] findByUuid_PrevAndNext(long strategyshareId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException;

	/**
	* Removes all the strategy shares where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of strategy shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching strategy shares
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the strategy share where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchStrategyShareException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public StrategyShare findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchStrategyShareException;

	/**
	* Returns the strategy share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public StrategyShare fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the strategy share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public StrategyShare fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the strategy share where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the strategy share that was removed
	*/
	public StrategyShare removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchStrategyShareException;

	/**
	* Returns the number of strategy shares where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching strategy shares
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the strategy shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching strategy shares
	*/
	public java.util.List<StrategyShare> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<StrategyShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<StrategyShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator);

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
	public java.util.List<StrategyShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public StrategyShare findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException;

	/**
	* Returns the first strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public StrategyShare fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator);

	/**
	* Returns the last strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share
	* @throws NoSuchStrategyShareException if a matching strategy share could not be found
	*/
	public StrategyShare findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException;

	/**
	* Returns the last strategy share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	public StrategyShare fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator);

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
	public StrategyShare[] findByUuid_C_PrevAndNext(long strategyshareId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator)
		throws NoSuchStrategyShareException;

	/**
	* Removes all the strategy shares where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of strategy shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching strategy shares
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the strategy share in the entity cache if it is enabled.
	*
	* @param strategyShare the strategy share
	*/
	public void cacheResult(StrategyShare strategyShare);

	/**
	* Caches the strategy shares in the entity cache if it is enabled.
	*
	* @param strategyShares the strategy shares
	*/
	public void cacheResult(java.util.List<StrategyShare> strategyShares);

	/**
	* Creates a new strategy share with the primary key. Does not add the strategy share to the database.
	*
	* @param strategyshareId the primary key for the new strategy share
	* @return the new strategy share
	*/
	public StrategyShare create(long strategyshareId);

	/**
	* Removes the strategy share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share that was removed
	* @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	*/
	public StrategyShare remove(long strategyshareId)
		throws NoSuchStrategyShareException;

	public StrategyShare updateImpl(StrategyShare strategyShare);

	/**
	* Returns the strategy share with the primary key or throws a {@link NoSuchStrategyShareException} if it could not be found.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share
	* @throws NoSuchStrategyShareException if a strategy share with the primary key could not be found
	*/
	public StrategyShare findByPrimaryKey(long strategyshareId)
		throws NoSuchStrategyShareException;

	/**
	* Returns the strategy share with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share, or <code>null</code> if a strategy share with the primary key could not be found
	*/
	public StrategyShare fetchByPrimaryKey(long strategyshareId);

	@Override
	public java.util.Map<java.io.Serializable, StrategyShare> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the strategy shares.
	*
	* @return the strategy shares
	*/
	public java.util.List<StrategyShare> findAll();

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
	public java.util.List<StrategyShare> findAll(int start, int end);

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
	public java.util.List<StrategyShare> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator);

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
	public java.util.List<StrategyShare> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StrategyShare> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the strategy shares from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of strategy shares.
	*
	* @return the number of strategy shares
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}