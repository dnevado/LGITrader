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

import com.ibtrader.data.model.Position;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
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
 * Provides the local service interface for Position. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see PositionLocalServiceUtil
 * @see com.ibtrader.data.service.base.PositionLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.PositionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PositionLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PositionLocalServiceUtil} to access the position local service. Add custom service methods to {@link com.ibtrader.data.service.impl.PositionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public boolean ExistsOpenPosition(long groupId, long companyId,
		long shareId, java.lang.String positionMode, long backtestingId);

	public boolean ExistsPositionToExit(long groupId, long companyId,
		long shareId, java.lang.String positionMode, long backtestingId);

	public boolean IsinRangeUserBudget(User user, double newTargetPosition,
		java.lang.String positionMode, long companyId, long groupId);

	public boolean satisfyDayTraderPattern(Date from, Date to, long groupId,
		long companyId, java.lang.String positionMode);

	/**
	* Adds the position to the database. Also notifies the appropriate model listeners.
	*
	* @param position the position
	* @return the position that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Position addPosition(Position position);

	/**
	* Creates a new position with the primary key. Does not add the position to the database.
	*
	* @param positionId the primary key for the new position
	* @return the new position
	*/
	public Position createPosition(long positionId);

	/**
	* Deletes the position from the database. Also notifies the appropriate model listeners.
	*
	* @param position the position
	* @return the position that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Position deletePosition(Position position);

	/**
	* Deletes the position with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param positionId the primary key of the position
	* @return the position that was removed
	* @throws PortalException if a position with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Position deletePosition(long positionId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Position fetchPosition(long positionId);

	/**
	* Returns the position matching the UUID and group.
	*
	* @param uuid the position's UUID
	* @param groupId the primary key of the group
	* @return the matching position, or <code>null</code> if a matching position could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Position fetchPositionByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	public Position findByCompanyGroup(long companyId, long groupId,
		java.lang.String positionMode);

	public Position findByPositionID_In_TWS(long groupId, long companyId,
		long _PositionIDTWS, long clientId_in, java.lang.String positionMode);

	public Position findByPositionID_Out_TWS(long groupId, long companyId,
		long _PositionIDTWS, long clientId_out, java.lang.String positionMode);

	public Position findPositionToExit(long groupId, long companyId,
		long shareId, java.lang.String positionMode, long backtestingId);

	/**
	* Returns the position with the primary key.
	*
	* @param positionId the primary key of the position
	* @return the position
	* @throws PortalException if a position with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Position getPosition(long positionId) throws PortalException;

	/**
	* Returns the position matching the UUID and group.
	*
	* @param uuid the position's UUID
	* @param groupId the primary key of the group
	* @return the matching position
	* @throws PortalException if a matching position could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Position getPositionByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param position the position
	* @return the position that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Position updatePosition(Position position);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	public JSONArray findPositionClosedResults(Date from, Date to,
		long groupId, long companyId, java.lang.String positionMode,
		long backtestingId);

	public JSONArray findPositionOpenResults(Date to, long groupId,
		long companyId, java.lang.String positionMode);

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

	public double findTotalLiquidPositionOpen(long groupId, long companyId,
		java.lang.String positionMode);

	/**
	* Returns the number of positions.
	*
	* @return the number of positions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPositionsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public List<Position> findByCancelShareCompanyGroup(long companyId,
		long groupId, long pendingcancelled, long shareId,
		java.lang.String positionMode);

	public List<Position> findByCloseCompanyGroup(long companyId, long groupId,
		long shareId, boolean forceclose, java.lang.String positionMode);

	public List<Position> findByCompanyGroupDate(long companyId, long groupId,
		Date start_date_in, Date end_date_in, java.lang.String positionMode);

	public List<Position> findByCompanyGroupShare(long companyId, long groupId,
		long share, java.lang.String positionMode);

	public List<Position> findIntradiaByCompanyGroupDate(long companyId,
		long groupId, Date end_date_in, java.lang.String positionMode);

	/**
	* Returns a range of all the positions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.PositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @return the range of positions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Position> getPositions(int start, int end);

	/**
	* Returns all the positions matching the UUID and company.
	*
	* @param uuid the UUID of the positions
	* @param companyId the primary key of the company
	* @return the matching positions, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Position> getPositionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of positions matching the UUID and company.
	*
	* @param uuid the UUID of the positions
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of positions
	* @param end the upper bound of the range of positions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching positions, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Position> getPositionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<Position> orderByComparator);

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
		long clientId, java.lang.String positionMode);
}