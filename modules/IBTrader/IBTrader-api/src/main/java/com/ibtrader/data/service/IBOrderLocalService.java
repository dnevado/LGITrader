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

import com.ibtrader.data.model.IBOrder;

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

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for IBOrder. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see IBOrderLocalServiceUtil
 * @see com.ibtrader.data.service.base.IBOrderLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.IBOrderLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface IBOrderLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link IBOrderLocalServiceUtil} to access the ib order local service. Add custom service methods to {@link com.ibtrader.data.service.impl.IBOrderLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the ib order to the database. Also notifies the appropriate model listeners.
	*
	* @param ibOrder the ib order
	* @return the ib order that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public IBOrder addIBOrder(IBOrder ibOrder);

	/**
	* Creates a new ib order with the primary key. Does not add the ib order to the database.
	*
	* @param orderIdPk the primary key for the new ib order
	* @return the new ib order
	*/
	public IBOrder createIBOrder(long orderIdPk);

	/**
	* Deletes the ib order from the database. Also notifies the appropriate model listeners.
	*
	* @param ibOrder the ib order
	* @return the ib order that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public IBOrder deleteIBOrder(IBOrder ibOrder);

	/**
	* Deletes the ib order with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orderIdPk the primary key of the ib order
	* @return the ib order that was removed
	* @throws PortalException if a ib order with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public IBOrder deleteIBOrder(long orderIdPk) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IBOrder fetchIBOrder(long orderIdPk);

	/**
	* Returns the ib order matching the UUID and group.
	*
	* @param uuid the ib order's UUID
	* @param groupId the primary key of the group
	* @return the matching ib order, or <code>null</code> if a matching ib order could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IBOrder fetchIBOrderByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	public IBOrder findByOrderClientGroupCompany(long iborderId, long clientId,
		long companyId, long groupId);

	/**
	* Returns the ib order with the primary key.
	*
	* @param orderIdPk the primary key of the ib order
	* @return the ib order
	* @throws PortalException if a ib order with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IBOrder getIBOrder(long orderIdPk) throws PortalException;

	/**
	* Returns the ib order matching the UUID and group.
	*
	* @param uuid the ib order's UUID
	* @param groupId the primary key of the group
	* @return the matching ib order
	* @throws PortalException if a matching ib order could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IBOrder getIBOrderByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the ib order in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ibOrder the ib order
	* @return the ib order that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public IBOrder updateIBOrder(IBOrder ibOrder);

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
	* Returns the number of ib orders.
	*
	* @return the number of ib orders
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getIBOrdersCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public List<IBOrder> findByDate(Date untilDate);

	public List<IBOrder> findByRemovableDate(Date untilDate,
		boolean removable_on_reboot);

	public List<IBOrder> findByShareIdCompanyGroup(long shareId,
		long companyId, long groupId);

	/**
	* Returns a range of all the ib orders.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.IBOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @return the range of ib orders
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<IBOrder> getIBOrders(int start, int end);

	/**
	* Returns all the ib orders matching the UUID and company.
	*
	* @param uuid the UUID of the ib orders
	* @param companyId the primary key of the company
	* @return the matching ib orders, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<IBOrder> getIBOrdersByUuidAndCompanyId(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of ib orders matching the UUID and company.
	*
	* @param uuid the UUID of the ib orders
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of ib orders
	* @param end the upper bound of the range of ib orders (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching ib orders, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<IBOrder> getIBOrdersByUuidAndCompanyId(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<IBOrder> orderByComparator);

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

	public long findMaxOrderClientCompanyGroup(long companyId, long groupId,
		long clientId);

	public void deleteByOrderCompanyGroup(long iborderId, long companyId,
		long groupId, long ibclientId, long shareId);
}