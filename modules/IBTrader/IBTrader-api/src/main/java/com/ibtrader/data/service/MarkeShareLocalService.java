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

package com.ibtrader.data.service;

import aQute.bnd.annotation.ProviderType;

import com.ibtrader.data.model.MarkeShare;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for MarkeShare. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see MarkeShareLocalServiceUtil
 * @see com.ibtrader.data.service.base.MarkeShareLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.MarkeShareLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface MarkeShareLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MarkeShareLocalServiceUtil} to access the marke share local service. Add custom service methods to {@link com.ibtrader.data.service.impl.MarkeShareLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the marke share to the database. Also notifies the appropriate model listeners.
	*
	* @param markeShare the marke share
	* @return the marke share that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MarkeShare addMarkeShare(MarkeShare markeShare);

	/**
	* Creates a new marke share with the primary key. Does not add the marke share to the database.
	*
	* @param marketshareId the primary key for the new marke share
	* @return the new marke share
	*/
	public MarkeShare createMarkeShare(long marketshareId);

	/**
	* Deletes the marke share from the database. Also notifies the appropriate model listeners.
	*
	* @param markeShare the marke share
	* @return the marke share that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public MarkeShare deleteMarkeShare(MarkeShare markeShare);

	/**
	* Deletes the marke share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param marketshareId the primary key of the marke share
	* @return the marke share that was removed
	* @throws PortalException if a marke share with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public MarkeShare deleteMarkeShare(long marketshareId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MarkeShare fetchMarkeShare(long marketshareId);

	/**
	* Returns the marke share matching the UUID and group.
	*
	* @param uuid the marke share's UUID
	* @param groupId the primary key of the group
	* @return the matching marke share, or <code>null</code> if a matching marke share could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MarkeShare fetchMarkeShareByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the marke share with the primary key.
	*
	* @param marketshareId the primary key of the marke share
	* @return the marke share
	* @throws PortalException if a marke share with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MarkeShare getMarkeShare(long marketshareId)
		throws PortalException;

	/**
	* Returns the marke share matching the UUID and group.
	*
	* @param uuid the marke share's UUID
	* @param groupId the primary key of the group
	* @return the matching marke share
	* @throws PortalException if a matching marke share could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public MarkeShare getMarkeShareByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the marke share in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param markeShare the marke share
	* @return the marke share that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public MarkeShare updateMarkeShare(MarkeShare markeShare);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of marke shares.
	*
	* @return the number of marke shares
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMarkeSharesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the marke shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.MarkeShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @return the range of marke shares
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MarkeShare> getMarkeShares(int start, int end);

	/**
	* Returns all the marke shares matching the UUID and company.
	*
	* @param uuid the UUID of the marke shares
	* @param companyId the primary key of the company
	* @return the matching marke shares, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MarkeShare> getMarkeSharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of marke shares matching the UUID and company.
	*
	* @param uuid the UUID of the marke shares
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of marke shares
	* @param end the upper bound of the range of marke shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching marke shares, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<MarkeShare> getMarkeSharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<MarkeShare> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);
}