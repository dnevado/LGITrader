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

import com.ibtrader.data.exception.NoSuchBackTestingException;
import com.ibtrader.data.model.BackTesting;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the back testing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.persistence.impl.BackTestingPersistenceImpl
 * @see BackTestingUtil
 * @generated
 */
@ProviderType
public interface BackTestingPersistence extends BasePersistence<BackTesting> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BackTestingUtil} to access the back testing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the back testings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching back testings
	*/
	public java.util.List<BackTesting> findByUuid(java.lang.String uuid);

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
	public java.util.List<BackTesting> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<BackTesting> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

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
	public java.util.List<BackTesting> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first back testing in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public BackTesting findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Returns the first back testing in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public BackTesting fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

	/**
	* Returns the last back testing in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public BackTesting findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Returns the last back testing in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public BackTesting fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

	/**
	* Returns the back testings before and after the current back testing in the ordered set where uuid = &#63;.
	*
	* @param backTId the primary key of the current back testing
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next back testing
	* @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	*/
	public BackTesting[] findByUuid_PrevAndNext(long backTId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Removes all the back testings where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of back testings where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching back testings
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the back testing where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBackTestingException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public BackTesting findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchBackTestingException;

	/**
	* Returns the back testing where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public BackTesting fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the back testing where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public BackTesting fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the back testing where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the back testing that was removed
	*/
	public BackTesting removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchBackTestingException;

	/**
	* Returns the number of back testings where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching back testings
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the back testings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching back testings
	*/
	public java.util.List<BackTesting> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<BackTesting> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<BackTesting> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

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
	public java.util.List<BackTesting> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public BackTesting findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Returns the first back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public BackTesting fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

	/**
	* Returns the last back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public BackTesting findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Returns the last back testing in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public BackTesting fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

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
	public BackTesting[] findByUuid_C_PrevAndNext(long backTId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Removes all the back testings where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of back testings where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching back testings
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the matching back testings
	*/
	public java.util.List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId);

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
	public java.util.List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId, int start, int end);

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
	public java.util.List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

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
	public java.util.List<BackTesting> findByShareCompanyGroup(long shareId,
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache);

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
	public BackTesting findByShareCompanyGroup_First(long shareId,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Returns the first back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public BackTesting fetchByShareCompanyGroup_First(long shareId,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

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
	public BackTesting findByShareCompanyGroup_Last(long shareId,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Returns the last back testing in the ordered set where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public BackTesting fetchByShareCompanyGroup_Last(long shareId,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

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
	public BackTesting[] findByShareCompanyGroup_PrevAndNext(long backTId,
		long shareId, long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Removes all the back testings where shareId = &#63; and companyId = &#63; and groupId = &#63; from the database.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	*/
	public void removeByShareCompanyGroup(long shareId, long companyId,
		long groupId);

	/**
	* Returns the number of back testings where shareId = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param shareId the share ID
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the number of matching back testings
	*/
	public int countByShareCompanyGroup(long shareId, long companyId,
		long groupId);

	/**
	* Returns all the back testings where status = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the matching back testings
	*/
	public java.util.List<BackTesting> findByStatusCompanyGroup(
		java.lang.String status, long companyId, long groupId);

	/**
	* Returns a range of all the back testings where status = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @return the range of matching back testings
	*/
	public java.util.List<BackTesting> findByStatusCompanyGroup(
		java.lang.String status, long companyId, long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the back testings where status = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching back testings
	*/
	public java.util.List<BackTesting> findByStatusCompanyGroup(
		java.lang.String status, long companyId, long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

	/**
	* Returns an ordered range of all the back testings where status = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of back testings
	* @param end the upper bound of the range of back testings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching back testings
	*/
	public java.util.List<BackTesting> findByStatusCompanyGroup(
		java.lang.String status, long companyId, long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public BackTesting findByStatusCompanyGroup_First(java.lang.String status,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Returns the first back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public BackTesting fetchByStatusCompanyGroup_First(
		java.lang.String status, long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

	/**
	* Returns the last back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing
	* @throws NoSuchBackTestingException if a matching back testing could not be found
	*/
	public BackTesting findByStatusCompanyGroup_Last(java.lang.String status,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Returns the last back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching back testing, or <code>null</code> if a matching back testing could not be found
	*/
	public BackTesting fetchByStatusCompanyGroup_Last(java.lang.String status,
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

	/**
	* Returns the back testings before and after the current back testing in the ordered set where status = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param backTId the primary key of the current back testing
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next back testing
	* @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	*/
	public BackTesting[] findByStatusCompanyGroup_PrevAndNext(long backTId,
		java.lang.String status, long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator)
		throws NoSuchBackTestingException;

	/**
	* Removes all the back testings where status = &#63; and companyId = &#63; and groupId = &#63; from the database.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	*/
	public void removeByStatusCompanyGroup(java.lang.String status,
		long companyId, long groupId);

	/**
	* Returns the number of back testings where status = &#63; and companyId = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the number of matching back testings
	*/
	public int countByStatusCompanyGroup(java.lang.String status,
		long companyId, long groupId);

	/**
	* Caches the back testing in the entity cache if it is enabled.
	*
	* @param backTesting the back testing
	*/
	public void cacheResult(BackTesting backTesting);

	/**
	* Caches the back testings in the entity cache if it is enabled.
	*
	* @param backTestings the back testings
	*/
	public void cacheResult(java.util.List<BackTesting> backTestings);

	/**
	* Creates a new back testing with the primary key. Does not add the back testing to the database.
	*
	* @param backTId the primary key for the new back testing
	* @return the new back testing
	*/
	public BackTesting create(long backTId);

	/**
	* Removes the back testing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param backTId the primary key of the back testing
	* @return the back testing that was removed
	* @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	*/
	public BackTesting remove(long backTId) throws NoSuchBackTestingException;

	public BackTesting updateImpl(BackTesting backTesting);

	/**
	* Returns the back testing with the primary key or throws a {@link NoSuchBackTestingException} if it could not be found.
	*
	* @param backTId the primary key of the back testing
	* @return the back testing
	* @throws NoSuchBackTestingException if a back testing with the primary key could not be found
	*/
	public BackTesting findByPrimaryKey(long backTId)
		throws NoSuchBackTestingException;

	/**
	* Returns the back testing with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param backTId the primary key of the back testing
	* @return the back testing, or <code>null</code> if a back testing with the primary key could not be found
	*/
	public BackTesting fetchByPrimaryKey(long backTId);

	@Override
	public java.util.Map<java.io.Serializable, BackTesting> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the back testings.
	*
	* @return the back testings
	*/
	public java.util.List<BackTesting> findAll();

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
	public java.util.List<BackTesting> findAll(int start, int end);

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
	public java.util.List<BackTesting> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator);

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
	public java.util.List<BackTesting> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BackTesting> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the back testings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of back testings.
	*
	* @return the number of back testings
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}