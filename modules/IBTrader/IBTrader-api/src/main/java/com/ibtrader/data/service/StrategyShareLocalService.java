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

import com.ibtrader.data.model.StrategyShare;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
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
 * Provides the local service interface for StrategyShare. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see StrategyShareLocalServiceUtil
 * @see com.ibtrader.data.service.base.StrategyShareLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.StrategyShareLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface StrategyShareLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StrategyShareLocalServiceUtil} to access the strategy share local service. Add custom service methods to {@link com.ibtrader.data.service.impl.StrategyShareLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the strategy share to the database. Also notifies the appropriate model listeners.
	*
	* @param strategyShare the strategy share
	* @return the strategy share that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public StrategyShare addStrategyShare(StrategyShare strategyShare);

	/**
	* Creates a new strategy share with the primary key. Does not add the strategy share to the database.
	*
	* @param strategyshareId the primary key for the new strategy share
	* @return the new strategy share
	*/
	public StrategyShare createStrategyShare(long strategyshareId);

	/**
	* Deletes the strategy share from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyShare the strategy share
	* @return the strategy share that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public StrategyShare deleteStrategyShare(StrategyShare strategyShare);

	/**
	* Deletes the strategy share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share that was removed
	* @throws PortalException if a strategy share with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public StrategyShare deleteStrategyShare(long strategyshareId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StrategyShare fetchStrategyShare(long strategyshareId);

	/**
	* Returns the strategy share matching the UUID and group.
	*
	* @param uuid the strategy share's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StrategyShare fetchStrategyShareByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the strategy share with the primary key.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share
	* @throws PortalException if a strategy share with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StrategyShare getStrategyShare(long strategyshareId)
		throws PortalException;

	/**
	* Returns the strategy share matching the UUID and group.
	*
	* @param uuid the strategy share's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy share
	* @throws PortalException if a matching strategy share could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public StrategyShare getStrategyShareByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Updates the strategy share in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param strategyShare the strategy share
	* @return the strategy share that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public StrategyShare updateStrategyShare(StrategyShare strategyShare);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

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
	* Returns the number of strategy shares.
	*
	* @return the number of strategy shares
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getStrategySharesCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StrategyShare> getByGroupCompanyShareId(long groupid,
		long companyid, long shareId);

	/**
	* Returns a range of all the strategy shares.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.StrategyShareModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @return the range of strategy shares
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StrategyShare> getStrategyShares(int start, int end);

	/**
	* Returns all the strategy shares matching the UUID and company.
	*
	* @param uuid the UUID of the strategy shares
	* @param companyId the primary key of the company
	* @return the matching strategy shares, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StrategyShare> getStrategySharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of strategy shares matching the UUID and company.
	*
	* @param uuid the UUID of the strategy shares
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of strategy shares
	* @param end the upper bound of the range of strategy shares (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching strategy shares, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<StrategyShare> getStrategySharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<StrategyShare> orderByComparator);

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