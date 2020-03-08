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
 * Provides the local service utility for AuditIndicatorsStrategy. This utility wraps
 * {@link com.ibtrader.data.service.impl.AuditIndicatorsStrategyLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AuditIndicatorsStrategyLocalService
 * @see com.ibtrader.data.service.base.AuditIndicatorsStrategyLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.AuditIndicatorsStrategyLocalServiceImpl
 * @generated
 */
@ProviderType
public class AuditIndicatorsStrategyLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ibtrader.data.service.impl.AuditIndicatorsStrategyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the audit indicators strategy to the database. Also notifies the appropriate model listeners.
	*
	* @param auditIndicatorsStrategy the audit indicators strategy
	* @return the audit indicators strategy that was added
	*/
	public static com.ibtrader.data.model.AuditIndicatorsStrategy addAuditIndicatorsStrategy(
		com.ibtrader.data.model.AuditIndicatorsStrategy auditIndicatorsStrategy) {
		return getService().addAuditIndicatorsStrategy(auditIndicatorsStrategy);
	}

	/**
	* Creates a new audit indicators strategy with the primary key. Does not add the audit indicators strategy to the database.
	*
	* @param auditIndicatorsStrategyPK the primary key for the new audit indicators strategy
	* @return the new audit indicators strategy
	*/
	public static com.ibtrader.data.model.AuditIndicatorsStrategy createAuditIndicatorsStrategy(
		com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK auditIndicatorsStrategyPK) {
		return getService()
				   .createAuditIndicatorsStrategy(auditIndicatorsStrategyPK);
	}

	/**
	* Deletes the audit indicators strategy from the database. Also notifies the appropriate model listeners.
	*
	* @param auditIndicatorsStrategy the audit indicators strategy
	* @return the audit indicators strategy that was removed
	*/
	public static com.ibtrader.data.model.AuditIndicatorsStrategy deleteAuditIndicatorsStrategy(
		com.ibtrader.data.model.AuditIndicatorsStrategy auditIndicatorsStrategy) {
		return getService()
				   .deleteAuditIndicatorsStrategy(auditIndicatorsStrategy);
	}

	/**
	* Deletes the audit indicators strategy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	* @return the audit indicators strategy that was removed
	* @throws PortalException if a audit indicators strategy with the primary key could not be found
	*/
	public static com.ibtrader.data.model.AuditIndicatorsStrategy deleteAuditIndicatorsStrategy(
		com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK auditIndicatorsStrategyPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteAuditIndicatorsStrategy(auditIndicatorsStrategyPK);
	}

	public static com.ibtrader.data.model.AuditIndicatorsStrategy fetchAuditIndicatorsStrategy(
		com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK auditIndicatorsStrategyPK) {
		return getService()
				   .fetchAuditIndicatorsStrategy(auditIndicatorsStrategyPK);
	}

	/**
	* Returns the audit indicators strategy matching the UUID and group.
	*
	* @param uuid the audit indicators strategy's UUID
	* @param groupId the primary key of the group
	* @return the matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	public static com.ibtrader.data.model.AuditIndicatorsStrategy fetchAuditIndicatorsStrategyByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService()
				   .fetchAuditIndicatorsStrategyByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the audit indicators strategy with the primary key.
	*
	* @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	* @return the audit indicators strategy
	* @throws PortalException if a audit indicators strategy with the primary key could not be found
	*/
	public static com.ibtrader.data.model.AuditIndicatorsStrategy getAuditIndicatorsStrategy(
		com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK auditIndicatorsStrategyPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAuditIndicatorsStrategy(auditIndicatorsStrategyPK);
	}

	/**
	* Returns the audit indicators strategy matching the UUID and group.
	*
	* @param uuid the audit indicators strategy's UUID
	* @param groupId the primary key of the group
	* @return the matching audit indicators strategy
	* @throws PortalException if a matching audit indicators strategy could not be found
	*/
	public static com.ibtrader.data.model.AuditIndicatorsStrategy getAuditIndicatorsStrategyByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getAuditIndicatorsStrategyByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the audit indicators strategy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditIndicatorsStrategy the audit indicators strategy
	* @return the audit indicators strategy that was updated
	*/
	public static com.ibtrader.data.model.AuditIndicatorsStrategy updateAuditIndicatorsStrategy(
		com.ibtrader.data.model.AuditIndicatorsStrategy auditIndicatorsStrategy) {
		return getService()
				   .updateAuditIndicatorsStrategy(auditIndicatorsStrategy);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Returns the number of audit indicators strategies.
	*
	* @return the number of audit indicators strategies
	*/
	public static int getAuditIndicatorsStrategiesCount() {
		return getService().getAuditIndicatorsStrategiesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns a range of all the audit indicators strategies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.AuditIndicatorsStrategyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @return the range of audit indicators strategies
	*/
	public static java.util.List<com.ibtrader.data.model.AuditIndicatorsStrategy> getAuditIndicatorsStrategies(
		int start, int end) {
		return getService().getAuditIndicatorsStrategies(start, end);
	}

	/**
	* Returns all the audit indicators strategies matching the UUID and company.
	*
	* @param uuid the UUID of the audit indicators strategies
	* @param companyId the primary key of the company
	* @return the matching audit indicators strategies, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.AuditIndicatorsStrategy> getAuditIndicatorsStrategiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getAuditIndicatorsStrategiesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of audit indicators strategies matching the UUID and company.
	*
	* @param uuid the UUID of the audit indicators strategies
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of audit indicators strategies
	* @param end the upper bound of the range of audit indicators strategies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching audit indicators strategies, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.AuditIndicatorsStrategy> getAuditIndicatorsStrategiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.AuditIndicatorsStrategy> orderByComparator) {
		return getService()
				   .getAuditIndicatorsStrategiesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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

	public static AuditIndicatorsStrategyLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AuditIndicatorsStrategyLocalService, AuditIndicatorsStrategyLocalService> _serviceTracker =
		ServiceTrackerFactory.open(AuditIndicatorsStrategyLocalService.class);
}