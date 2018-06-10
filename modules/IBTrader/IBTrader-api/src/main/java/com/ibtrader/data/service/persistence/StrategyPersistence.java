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

import com.ibtrader.data.exception.NoSuchStrategyException;
import com.ibtrader.data.model.Strategy;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the strategy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.StrategyPersistenceImpl
 * @see StrategyUtil
 * @generated
 */
@ProviderType
public interface StrategyPersistence extends BasePersistence<Strategy> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StrategyUtil} to access the strategy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the strategies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching strategies
	*/
	public java.util.List<Strategy> findByUuid(java.lang.String uuid);

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
	public java.util.List<Strategy> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<Strategy> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public java.util.List<Strategy> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the first strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the last strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the last strategy in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the strategies before and after the current strategy in the ordered set where uuid = &#63;.
	*
	* @param strategyID the primary key of the current strategy
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public Strategy[] findByUuid_PrevAndNext(long strategyID,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Removes all the strategies where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of strategies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching strategies
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the strategy where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchStrategyException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchStrategyException;

	/**
	* Returns the strategy where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the strategy where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the strategy where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the strategy that was removed
	*/
	public Strategy removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchStrategyException;

	/**
	* Returns the number of strategies where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching strategies
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the strategies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching strategies
	*/
	public java.util.List<Strategy> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<Strategy> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<Strategy> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public java.util.List<Strategy> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the first strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the last strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the last strategy in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public Strategy[] findByUuid_C_PrevAndNext(long strategyID,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Removes all the strategies where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of strategies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching strategies
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the strategies where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching strategies
	*/
	public java.util.List<Strategy> findByCompanyId(long companyId);

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
	public java.util.List<Strategy> findByCompanyId(long companyId, int start,
		int end);

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
	public java.util.List<Strategy> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public java.util.List<Strategy> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first strategy in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the first strategy in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the last strategy in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the last strategy in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the strategies before and after the current strategy in the ordered set where companyId = &#63;.
	*
	* @param strategyID the primary key of the current strategy
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public Strategy[] findByCompanyId_PrevAndNext(long strategyID,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Removes all the strategies where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of strategies where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching strategies
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns all the strategies where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching strategies
	*/
	public java.util.List<Strategy> findByGroupId(long groupId);

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
	public java.util.List<Strategy> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<Strategy> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public java.util.List<Strategy> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first strategy in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the first strategy in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the last strategy in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the last strategy in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the strategies before and after the current strategy in the ordered set where groupId = &#63;.
	*
	* @param strategyID the primary key of the current strategy
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public Strategy[] findByGroupId_PrevAndNext(long strategyID, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Removes all the strategies where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of strategies where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching strategies
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the strategies where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the matching strategies
	*/
	public java.util.List<Strategy> findByActiveCompanyId(long companyId,
		boolean active);

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
	public java.util.List<Strategy> findByActiveCompanyId(long companyId,
		boolean active, int start, int end);

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
	public java.util.List<Strategy> findByActiveCompanyId(long companyId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public java.util.List<Strategy> findByActiveCompanyId(long companyId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first strategy in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByActiveCompanyId_First(long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the first strategy in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByActiveCompanyId_First(long companyId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the last strategy in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByActiveCompanyId_Last(long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the last strategy in the ordered set where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByActiveCompanyId_Last(long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public Strategy[] findByActiveCompanyId_PrevAndNext(long strategyID,
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Removes all the strategies where companyId = &#63; and active = &#63; from the database.
	*
	* @param companyId the company ID
	* @param active the active
	*/
	public void removeByActiveCompanyId(long companyId, boolean active);

	/**
	* Returns the number of strategies where companyId = &#63; and active = &#63;.
	*
	* @param companyId the company ID
	* @param active the active
	* @return the number of matching strategies
	*/
	public int countByActiveCompanyId(long companyId, boolean active);

	/**
	* Returns all the strategies where status = &#63;.
	*
	* @param status the status
	* @return the matching strategies
	*/
	public java.util.List<Strategy> findByStatus(int status);

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
	public java.util.List<Strategy> findByStatus(int status, int start, int end);

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
	public java.util.List<Strategy> findByStatus(int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public java.util.List<Strategy> findByStatus(int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first strategy in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the first strategy in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the last strategy in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the last strategy in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the strategies before and after the current strategy in the ordered set where status = &#63;.
	*
	* @param strategyID the primary key of the current strategy
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public Strategy[] findByStatus_PrevAndNext(long strategyID, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Removes all the strategies where status = &#63; from the database.
	*
	* @param status the status
	*/
	public void removeByStatus(int status);

	/**
	* Returns the number of strategies where status = &#63;.
	*
	* @param status the status
	* @return the number of matching strategies
	*/
	public int countByStatus(int status);

	/**
	* Returns all the strategies where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching strategies
	*/
	public java.util.List<Strategy> findByG_S(long groupId, int status);

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
	public java.util.List<Strategy> findByG_S(long groupId, int status,
		int start, int end);

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
	public java.util.List<Strategy> findByG_S(long groupId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public java.util.List<Strategy> findByG_S(long groupId, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first strategy in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByG_S_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the first strategy in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByG_S_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the last strategy in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByG_S_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the last strategy in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByG_S_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public Strategy[] findByG_S_PrevAndNext(long strategyID, long groupId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Removes all the strategies where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public void removeByG_S(long groupId, int status);

	/**
	* Returns the number of strategies where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching strategies
	*/
	public int countByG_S(long groupId, int status);

	/**
	* Returns all the strategies where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @return the matching strategies
	*/
	public java.util.List<Strategy> findByG_G(long groupId, long strategyID);

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
	public java.util.List<Strategy> findByG_G(long groupId, long strategyID,
		int start, int end);

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
	public java.util.List<Strategy> findByG_G(long groupId, long strategyID,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public java.util.List<Strategy> findByG_G(long groupId, long strategyID,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first strategy in the ordered set where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByG_G_First(long groupId, long strategyID,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the first strategy in the ordered set where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByG_G_First(long groupId, long strategyID,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Returns the last strategy in the ordered set where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByG_G_Last(long groupId, long strategyID,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator)
		throws NoSuchStrategyException;

	/**
	* Returns the last strategy in the ordered set where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByG_G_Last(long groupId, long strategyID,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

	/**
	* Removes all the strategies where groupId = &#63; and strategyID = &#63; from the database.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	*/
	public void removeByG_G(long groupId, long strategyID);

	/**
	* Returns the number of strategies where groupId = &#63; and strategyID = &#63;.
	*
	* @param groupId the group ID
	* @param strategyID the strategy i d
	* @return the number of matching strategies
	*/
	public int countByG_G(long groupId, long strategyID);

	/**
	* Returns the strategy where companyId = &#63; and className = &#63; and groupId = &#63; or throws a {@link NoSuchStrategyException} if it could not be found.
	*
	* @param companyId the company ID
	* @param className the class name
	* @param groupId the group ID
	* @return the matching strategy
	* @throws NoSuchStrategyException if a matching strategy could not be found
	*/
	public Strategy findByCompanyClassName(long companyId,
		java.lang.String className, long groupId)
		throws NoSuchStrategyException;

	/**
	* Returns the strategy where companyId = &#63; and className = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param className the class name
	* @param groupId the group ID
	* @return the matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByCompanyClassName(long companyId,
		java.lang.String className, long groupId);

	/**
	* Returns the strategy where companyId = &#63; and className = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param className the class name
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching strategy, or <code>null</code> if a matching strategy could not be found
	*/
	public Strategy fetchByCompanyClassName(long companyId,
		java.lang.String className, long groupId, boolean retrieveFromCache);

	/**
	* Removes the strategy where companyId = &#63; and className = &#63; and groupId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param className the class name
	* @param groupId the group ID
	* @return the strategy that was removed
	*/
	public Strategy removeByCompanyClassName(long companyId,
		java.lang.String className, long groupId)
		throws NoSuchStrategyException;

	/**
	* Returns the number of strategies where companyId = &#63; and className = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param className the class name
	* @param groupId the group ID
	* @return the number of matching strategies
	*/
	public int countByCompanyClassName(long companyId,
		java.lang.String className, long groupId);

	/**
	* Caches the strategy in the entity cache if it is enabled.
	*
	* @param strategy the strategy
	*/
	public void cacheResult(Strategy strategy);

	/**
	* Caches the strategies in the entity cache if it is enabled.
	*
	* @param strategies the strategies
	*/
	public void cacheResult(java.util.List<Strategy> strategies);

	/**
	* Creates a new strategy with the primary key. Does not add the strategy to the database.
	*
	* @param strategyID the primary key for the new strategy
	* @return the new strategy
	*/
	public Strategy create(long strategyID);

	/**
	* Removes the strategy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy that was removed
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public Strategy remove(long strategyID) throws NoSuchStrategyException;

	public Strategy updateImpl(Strategy strategy);

	/**
	* Returns the strategy with the primary key or throws a {@link NoSuchStrategyException} if it could not be found.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy
	* @throws NoSuchStrategyException if a strategy with the primary key could not be found
	*/
	public Strategy findByPrimaryKey(long strategyID)
		throws NoSuchStrategyException;

	/**
	* Returns the strategy with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param strategyID the primary key of the strategy
	* @return the strategy, or <code>null</code> if a strategy with the primary key could not be found
	*/
	public Strategy fetchByPrimaryKey(long strategyID);

	@Override
	public java.util.Map<java.io.Serializable, Strategy> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the strategies.
	*
	* @return the strategies
	*/
	public java.util.List<Strategy> findAll();

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
	public java.util.List<Strategy> findAll(int start, int end);

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
	public java.util.List<Strategy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator);

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
	public java.util.List<Strategy> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Strategy> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the strategies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of strategies.
	*
	* @return the number of strategies
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}