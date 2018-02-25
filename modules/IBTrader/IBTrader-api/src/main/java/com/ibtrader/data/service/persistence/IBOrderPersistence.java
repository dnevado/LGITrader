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

import com.ibtrader.data.exception.NoSuchIBOrderException;
import com.ibtrader.data.model.IBOrder;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the i b order service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.IBOrderPersistenceImpl
 * @see IBOrderUtil
 * @generated
 */
@ProviderType
public interface IBOrderPersistence extends BasePersistence<IBOrder> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IBOrderUtil} to access the i b order persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the i b orders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching i b orders
	*/
	public java.util.List<IBOrder> findByUuid(java.lang.String uuid);

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
	public java.util.List<IBOrder> findByUuid(java.lang.String uuid, int start,
		int end);

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
	public java.util.List<IBOrder> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

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
	public java.util.List<IBOrder> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first i b order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the first i b order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the last i b order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the last i b order in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the i b orders before and after the current i b order in the ordered set where uuid = &#63;.
	*
	* @param orderIdPk the primary key of the current i b order
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next i b order
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public IBOrder[] findByUuid_PrevAndNext(long orderIdPk,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Removes all the i b orders where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of i b orders where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching i b orders
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the i b order where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchIBOrderException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchIBOrderException;

	/**
	* Returns the i b order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the i b order where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the i b order where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the i b order that was removed
	*/
	public IBOrder removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchIBOrderException;

	/**
	* Returns the number of i b orders where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching i b orders
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the i b orders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching i b orders
	*/
	public java.util.List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

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
	public java.util.List<IBOrder> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first i b order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the first i b order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the last i b order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the last i b order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the i b orders before and after the current i b order in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param orderIdPk the primary key of the current i b order
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next i b order
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public IBOrder[] findByUuid_C_PrevAndNext(long orderIdPk,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Removes all the i b orders where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of i b orders where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching i b orders
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the i b orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the matching i b orders
	*/
	public java.util.List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId);

	/**
	* Returns a range of all the i b orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @return the range of matching i b orders
	*/
	public java.util.List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId, int start, int end);

	/**
	* Returns an ordered range of all the i b orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching i b orders
	*/
	public java.util.List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns an ordered range of all the i b orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching i b orders
	*/
	public java.util.List<IBOrder> findByShareIdCompanyGroup(long shareID,
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first i b order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByShareIdCompanyGroup_First(long shareID,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the first i b order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByShareIdCompanyGroup_First(long shareID,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the last i b order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByShareIdCompanyGroup_Last(long shareID, long companyId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the last i b order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByShareIdCompanyGroup_Last(long shareID,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the i b orders before and after the current i b order in the ordered set where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param orderIdPk the primary key of the current i b order
	* @param shareID the share i d
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next i b order
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public IBOrder[] findByShareIdCompanyGroup_PrevAndNext(long orderIdPk,
		long shareID, long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Removes all the i b orders where shareID = &#63; and companyId = &#63; and groupId = &#63; from the database.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param groupId the group ID
	*/
	public void removeByShareIdCompanyGroup(long shareID, long companyId,
		long groupId);

	/**
	* Returns the number of i b orders where shareID = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the number of matching i b orders
	*/
	public int countByShareIdCompanyGroup(long shareID, long companyId,
		long groupId);

	/**
	* Returns all the i b orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @return the matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderGroupCompany(long shareID,
		long companyId, long ordersId);

	/**
	* Returns a range of all the i b orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @return the range of matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderGroupCompany(long shareID,
		long companyId, long ordersId, int start, int end);

	/**
	* Returns an ordered range of all the i b orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderGroupCompany(long shareID,
		long companyId, long ordersId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns an ordered range of all the i b orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderGroupCompany(long shareID,
		long companyId, long ordersId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first i b order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByOrderGroupCompany_First(long shareID, long companyId,
		long ordersId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the first i b order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByOrderGroupCompany_First(long shareID, long companyId,
		long ordersId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the last i b order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByOrderGroupCompany_Last(long shareID, long companyId,
		long ordersId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the last i b order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByOrderGroupCompany_Last(long shareID, long companyId,
		long ordersId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the i b orders before and after the current i b order in the ordered set where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param orderIdPk the primary key of the current i b order
	* @param shareID the share i d
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next i b order
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public IBOrder[] findByOrderGroupCompany_PrevAndNext(long orderIdPk,
		long shareID, long companyId, long ordersId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Removes all the i b orders where shareID = &#63; and companyId = &#63; and ordersId = &#63; from the database.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param ordersId the orders ID
	*/
	public void removeByOrderGroupCompany(long shareID, long companyId,
		long ordersId);

	/**
	* Returns the number of i b orders where shareID = &#63; and companyId = &#63; and ordersId = &#63;.
	*
	* @param shareID the share i d
	* @param companyId the company ID
	* @param ordersId the orders ID
	* @return the number of matching i b orders
	*/
	public int countByOrderGroupCompany(long shareID, long companyId,
		long ordersId);

	/**
	* Returns all the i b orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @return the matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderClientGroupCompany(
		long ordersId, long companyId, long groupId, long ibclientId);

	/**
	* Returns a range of all the i b orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @return the range of matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderClientGroupCompany(
		long ordersId, long companyId, long groupId, long ibclientId,
		int start, int end);

	/**
	* Returns an ordered range of all the i b orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderClientGroupCompany(
		long ordersId, long companyId, long groupId, long ibclientId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns an ordered range of all the i b orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderClientGroupCompany(
		long ordersId, long companyId, long groupId, long ibclientId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first i b order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByOrderClientGroupCompany_First(long ordersId,
		long companyId, long groupId, long ibclientId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the first i b order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByOrderClientGroupCompany_First(long ordersId,
		long companyId, long groupId, long ibclientId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the last i b order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByOrderClientGroupCompany_Last(long ordersId,
		long companyId, long groupId, long ibclientId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the last i b order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByOrderClientGroupCompany_Last(long ordersId,
		long companyId, long groupId, long ibclientId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the i b orders before and after the current i b order in the ordered set where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param orderIdPk the primary key of the current i b order
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next i b order
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public IBOrder[] findByOrderClientGroupCompany_PrevAndNext(long orderIdPk,
		long ordersId, long companyId, long groupId, long ibclientId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Removes all the i b orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63; from the database.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	*/
	public void removeByOrderClientGroupCompany(long ordersId, long companyId,
		long groupId, long ibclientId);

	/**
	* Returns the number of i b orders where ordersId = &#63; and companyId = &#63; and groupId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param ibclientId the ibclient ID
	* @return the number of matching i b orders
	*/
	public int countByOrderClientGroupCompany(long ordersId, long companyId,
		long groupId, long ibclientId);

	/**
	* Returns all the i b orders where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @return the matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderClient(long ordersId,
		long ibclientId);

	/**
	* Returns a range of all the i b orders where ordersId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @return the range of matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderClient(long ordersId,
		long ibclientId, int start, int end);

	/**
	* Returns an ordered range of all the i b orders where ordersId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderClient(long ordersId,
		long ibclientId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns an ordered range of all the i b orders where ordersId = &#63; and ibclientId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param start the lower bound of the range of i b orders
	* @param end the upper bound of the range of i b orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching i b orders
	*/
	public java.util.List<IBOrder> findByOrderClient(long ordersId,
		long ibclientId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first i b order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByOrderClient_First(long ordersId, long ibclientId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the first i b order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByOrderClient_First(long ordersId, long ibclientId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the last i b order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order
	* @throws NoSuchIBOrderException if a matching i b order could not be found
	*/
	public IBOrder findByOrderClient_Last(long ordersId, long ibclientId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Returns the last i b order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching i b order, or <code>null</code> if a matching i b order could not be found
	*/
	public IBOrder fetchByOrderClient_Last(long ordersId, long ibclientId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

	/**
	* Returns the i b orders before and after the current i b order in the ordered set where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param orderIdPk the primary key of the current i b order
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next i b order
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public IBOrder[] findByOrderClient_PrevAndNext(long orderIdPk,
		long ordersId, long ibclientId,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator)
		throws NoSuchIBOrderException;

	/**
	* Removes all the i b orders where ordersId = &#63; and ibclientId = &#63; from the database.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	*/
	public void removeByOrderClient(long ordersId, long ibclientId);

	/**
	* Returns the number of i b orders where ordersId = &#63; and ibclientId = &#63;.
	*
	* @param ordersId the orders ID
	* @param ibclientId the ibclient ID
	* @return the number of matching i b orders
	*/
	public int countByOrderClient(long ordersId, long ibclientId);

	/**
	* Caches the i b order in the entity cache if it is enabled.
	*
	* @param ibOrder the i b order
	*/
	public void cacheResult(IBOrder ibOrder);

	/**
	* Caches the i b orders in the entity cache if it is enabled.
	*
	* @param ibOrders the i b orders
	*/
	public void cacheResult(java.util.List<IBOrder> ibOrders);

	/**
	* Creates a new i b order with the primary key. Does not add the i b order to the database.
	*
	* @param orderIdPk the primary key for the new i b order
	* @return the new i b order
	*/
	public IBOrder create(long orderIdPk);

	/**
	* Removes the i b order with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orderIdPk the primary key of the i b order
	* @return the i b order that was removed
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public IBOrder remove(long orderIdPk) throws NoSuchIBOrderException;

	public IBOrder updateImpl(IBOrder ibOrder);

	/**
	* Returns the i b order with the primary key or throws a {@link NoSuchIBOrderException} if it could not be found.
	*
	* @param orderIdPk the primary key of the i b order
	* @return the i b order
	* @throws NoSuchIBOrderException if a i b order with the primary key could not be found
	*/
	public IBOrder findByPrimaryKey(long orderIdPk)
		throws NoSuchIBOrderException;

	/**
	* Returns the i b order with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param orderIdPk the primary key of the i b order
	* @return the i b order, or <code>null</code> if a i b order with the primary key could not be found
	*/
	public IBOrder fetchByPrimaryKey(long orderIdPk);

	@Override
	public java.util.Map<java.io.Serializable, IBOrder> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the i b orders.
	*
	* @return the i b orders
	*/
	public java.util.List<IBOrder> findAll();

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
	public java.util.List<IBOrder> findAll(int start, int end);

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
	public java.util.List<IBOrder> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator);

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
	public java.util.List<IBOrder> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<IBOrder> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the i b orders from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of i b orders.
	*
	* @return the number of i b orders
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}