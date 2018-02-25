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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Position. This utility wraps
 * {@link com.ibtrader.data.service.impl.PositionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PositionLocalService
 * @see com.ibtrader.data.service.base.PositionLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.PositionLocalServiceImpl
 * @generated
 */
@ProviderType
public class PositionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ibtrader.data.service.impl.PositionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean ExistsOpenPosition(long groupId, long companyId,
		long shareId) {
		return getService().ExistsOpenPosition(groupId, companyId, shareId);
	}

	public static boolean ExistsPositionToExit(long groupId, long companyId,
		long shareId) {
		return getService().ExistsPositionToExit(groupId, companyId, shareId);
	}

	/**
	* Adds the position to the database. Also notifies the appropriate model listeners.
	*
	* @param position the position
	* @return the position that was added
	*/
	public static com.ibtrader.data.model.Position addPosition(
		com.ibtrader.data.model.Position position) {
		return getService().addPosition(position);
	}

	/**
	* Creates a new position with the primary key. Does not add the position to the database.
	*
	* @param positionId the primary key for the new position
	* @return the new position
	*/
	public static com.ibtrader.data.model.Position createPosition(
		long positionId) {
		return getService().createPosition(positionId);
	}

	/**
	* Deletes the position from the database. Also notifies the appropriate model listeners.
	*
	* @param position the position
	* @return the position that was removed
	*/
	public static com.ibtrader.data.model.Position deletePosition(
		com.ibtrader.data.model.Position position) {
		return getService().deletePosition(position);
	}

	/**
	* Deletes the position with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param positionId the primary key of the position
	* @return the position that was removed
	* @throws PortalException if a position with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Position deletePosition(
		long positionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePosition(positionId);
	}

	public static com.ibtrader.data.model.Position fetchPosition(
		long positionId) {
		return getService().fetchPosition(positionId);
	}

	/**
	* Returns the position matching the UUID and group.
	*
	* @param uuid the position's UUID
	* @param groupId the primary key of the group
	* @return the matching position, or <code>null</code> if a matching position could not be found
	*/
	public static com.ibtrader.data.model.Position fetchPositionByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchPositionByUuidAndGroupId(uuid, groupId);
	}

	public static com.ibtrader.data.model.Position findByCompanyGroup(
		long companyId, long groupId) {
		return getService().findByCompanyGroup(companyId, groupId);
	}

	public static com.ibtrader.data.model.Position findByPositionID_In_TWS(
		long groupId, long companyId, long _PositionIDTWS) {
		return getService()
				   .findByPositionID_In_TWS(groupId, companyId, _PositionIDTWS);
	}

	public static com.ibtrader.data.model.Position findByPositionID_Out_TWS(
		long groupId, long companyId, long _PositionIDTWS) {
		return getService()
				   .findByPositionID_Out_TWS(groupId, companyId, _PositionIDTWS);
	}

	public static com.ibtrader.data.model.Position findPositionToExit(
		long groupId, long companyId, long shareId) {
		return getService().findPositionToExit(groupId, companyId, shareId);
	}

	/**
	* Returns the position with the primary key.
	*
	* @param positionId the primary key of the position
	* @return the position
	* @throws PortalException if a position with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Position getPosition(long positionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPosition(positionId);
	}

	/**
	* Returns the position matching the UUID and group.
	*
	* @param uuid the position's UUID
	* @param groupId the primary key of the group
	* @return the matching position
	* @throws PortalException if a matching position could not be found
	*/
	public static com.ibtrader.data.model.Position getPositionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPositionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param position the position
	* @return the position that was updated
	*/
	public static com.ibtrader.data.model.Position updatePosition(
		com.ibtrader.data.model.Position position) {
		return getService().updatePosition(position);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of positions.
	*
	* @return the number of positions
	*/
	public static int getPositionsCount() {
		return getService().getPositionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<com.ibtrader.data.model.Position> findByCompanyGroupDate(
		long companyId, long groupId, java.util.Date start_date_in,
		java.util.Date end_date_in) {
		return getService()
				   .findByCompanyGroupDate(companyId, groupId, start_date_in,
			end_date_in);
	}

	public static java.util.List<com.ibtrader.data.model.Position> findByCompanyGroupShare(
		long companyId, long groupId, long share) {
		return getService().findByCompanyGroupShare(companyId, groupId, share);
	}

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
	public static java.util.List<com.ibtrader.data.model.Position> getPositions(
		int start, int end) {
		return getService().getPositions(start, end);
	}

	/**
	* Returns all the positions matching the UUID and company.
	*
	* @param uuid the UUID of the positions
	* @param companyId the primary key of the company
	* @return the matching positions, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.Position> getPositionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getPositionsByUuidAndCompanyId(uuid, companyId);
	}

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
	public static java.util.List<com.ibtrader.data.model.Position> getPositionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Position> orderByComparator) {
		return getService()
				   .getPositionsByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static PositionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PositionLocalService, PositionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(PositionLocalService.class);
}