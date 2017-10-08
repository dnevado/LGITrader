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

import com.ibtrader.data.exception.NoSuchMarkeShareException;
import com.ibtrader.data.model.MarkeShare;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the marke share service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.MarkeSharePersistenceImpl
 * @see MarkeShareUtil
 * @generated
 */
@ProviderType
public interface MarkeSharePersistence extends BasePersistence<MarkeShare> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MarkeShareUtil} to access the marke share persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the marke shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching marke shares
	*/
	public java.util.List<MarkeShare> findByUuid(java.lang.String uuid);

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
	public java.util.List<MarkeShare> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<MarkeShare> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator);

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
	public java.util.List<MarkeShare> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first marke share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marke share
	* @throws NoSuchMarkeShareException if a matching marke share could not be found
	*/
	public MarkeShare findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException;

	/**
	* Returns the first marke share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public MarkeShare fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator);

	/**
	* Returns the last marke share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marke share
	* @throws NoSuchMarkeShareException if a matching marke share could not be found
	*/
	public MarkeShare findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException;

	/**
	* Returns the last marke share in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public MarkeShare fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator);

	/**
	* Returns the marke shares before and after the current marke share in the ordered set where uuid = &#63;.
	*
	* @param marketshareId the primary key of the current marke share
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next marke share
	* @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	*/
	public MarkeShare[] findByUuid_PrevAndNext(long marketshareId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException;

	/**
	* Removes all the marke shares where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of marke shares where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching marke shares
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the marke share where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchMarkeShareException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching marke share
	* @throws NoSuchMarkeShareException if a matching marke share could not be found
	*/
	public MarkeShare findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchMarkeShareException;

	/**
	* Returns the marke share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public MarkeShare fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the marke share where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public MarkeShare fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the marke share where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the marke share that was removed
	*/
	public MarkeShare removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchMarkeShareException;

	/**
	* Returns the number of marke shares where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching marke shares
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the marke shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching marke shares
	*/
	public java.util.List<MarkeShare> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<MarkeShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<MarkeShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator);

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
	public java.util.List<MarkeShare> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marke share
	* @throws NoSuchMarkeShareException if a matching marke share could not be found
	*/
	public MarkeShare findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException;

	/**
	* Returns the first marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public MarkeShare fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator);

	/**
	* Returns the last marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marke share
	* @throws NoSuchMarkeShareException if a matching marke share could not be found
	*/
	public MarkeShare findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException;

	/**
	* Returns the last marke share in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	public MarkeShare fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator);

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
	public MarkeShare[] findByUuid_C_PrevAndNext(long marketshareId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator)
		throws NoSuchMarkeShareException;

	/**
	* Removes all the marke shares where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of marke shares where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching marke shares
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the marke share in the entity cache if it is enabled.
	*
	* @param markeShare the marke share
	*/
	public void cacheResult(MarkeShare markeShare);

	/**
	* Caches the marke shares in the entity cache if it is enabled.
	*
	* @param markeShares the marke shares
	*/
	public void cacheResult(java.util.List<MarkeShare> markeShares);

	/**
	* Creates a new marke share with the primary key. Does not add the marke share to the database.
	*
	* @param marketshareId the primary key for the new marke share
	* @return the new marke share
	*/
	public MarkeShare create(long marketshareId);

	/**
	* Removes the marke share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param marketshareId the primary key of the marke share
	* @return the marke share that was removed
	* @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	*/
	public MarkeShare remove(long marketshareId)
		throws NoSuchMarkeShareException;

	public MarkeShare updateImpl(MarkeShare markeShare);

	/**
	* Returns the marke share with the primary key or throws a {@link NoSuchMarkeShareException} if it could not be found.
	*
	* @param marketshareId the primary key of the marke share
	* @return the marke share
	* @throws NoSuchMarkeShareException if a marke share with the primary key could not be found
	*/
	public MarkeShare findByPrimaryKey(long marketshareId)
		throws NoSuchMarkeShareException;

	/**
	* Returns the marke share with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param marketshareId the primary key of the marke share
	* @return the marke share, or <code>null</code> if a marke share with the primary key could not be found
	*/
	public MarkeShare fetchByPrimaryKey(long marketshareId);

	@Override
	public java.util.Map<java.io.Serializable, MarkeShare> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the marke shares.
	*
	* @return the marke shares
	*/
	public java.util.List<MarkeShare> findAll();

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
	public java.util.List<MarkeShare> findAll(int start, int end);

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
	public java.util.List<MarkeShare> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator);

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
	public java.util.List<MarkeShare> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MarkeShare> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the marke shares from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of marke shares.
	*
	* @return the number of marke shares
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}