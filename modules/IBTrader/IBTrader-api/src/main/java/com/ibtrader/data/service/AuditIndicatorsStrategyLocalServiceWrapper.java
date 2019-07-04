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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AuditIndicatorsStrategyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuditIndicatorsStrategyLocalService
 * @generated
 */
@ProviderType
public class AuditIndicatorsStrategyLocalServiceWrapper
	implements AuditIndicatorsStrategyLocalService,
		ServiceWrapper<AuditIndicatorsStrategyLocalService> {
	public AuditIndicatorsStrategyLocalServiceWrapper(
		AuditIndicatorsStrategyLocalService auditIndicatorsStrategyLocalService) {
		_auditIndicatorsStrategyLocalService = auditIndicatorsStrategyLocalService;
	}

	/**
	* Adds the audit indicators strategy to the database. Also notifies the appropriate model listeners.
	*
	* @param auditIndicatorsStrategy the audit indicators strategy
	* @return the audit indicators strategy that was added
	*/
	@Override
	public com.ibtrader.data.model.AuditIndicatorsStrategy addAuditIndicatorsStrategy(
		com.ibtrader.data.model.AuditIndicatorsStrategy auditIndicatorsStrategy) {
		return _auditIndicatorsStrategyLocalService.addAuditIndicatorsStrategy(auditIndicatorsStrategy);
	}

	/**
	* Creates a new audit indicators strategy with the primary key. Does not add the audit indicators strategy to the database.
	*
	* @param auditIndicatorsStrategyPK the primary key for the new audit indicators strategy
	* @return the new audit indicators strategy
	*/
	@Override
	public com.ibtrader.data.model.AuditIndicatorsStrategy createAuditIndicatorsStrategy(
		com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK auditIndicatorsStrategyPK) {
		return _auditIndicatorsStrategyLocalService.createAuditIndicatorsStrategy(auditIndicatorsStrategyPK);
	}

	/**
	* Deletes the audit indicators strategy from the database. Also notifies the appropriate model listeners.
	*
	* @param auditIndicatorsStrategy the audit indicators strategy
	* @return the audit indicators strategy that was removed
	*/
	@Override
	public com.ibtrader.data.model.AuditIndicatorsStrategy deleteAuditIndicatorsStrategy(
		com.ibtrader.data.model.AuditIndicatorsStrategy auditIndicatorsStrategy) {
		return _auditIndicatorsStrategyLocalService.deleteAuditIndicatorsStrategy(auditIndicatorsStrategy);
	}

	/**
	* Deletes the audit indicators strategy with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	* @return the audit indicators strategy that was removed
	* @throws PortalException if a audit indicators strategy with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.AuditIndicatorsStrategy deleteAuditIndicatorsStrategy(
		com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK auditIndicatorsStrategyPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditIndicatorsStrategyLocalService.deleteAuditIndicatorsStrategy(auditIndicatorsStrategyPK);
	}

	@Override
	public com.ibtrader.data.model.AuditIndicatorsStrategy fetchAuditIndicatorsStrategy(
		com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK auditIndicatorsStrategyPK) {
		return _auditIndicatorsStrategyLocalService.fetchAuditIndicatorsStrategy(auditIndicatorsStrategyPK);
	}

	/**
	* Returns the audit indicators strategy matching the UUID and group.
	*
	* @param uuid the audit indicators strategy's UUID
	* @param groupId the primary key of the group
	* @return the matching audit indicators strategy, or <code>null</code> if a matching audit indicators strategy could not be found
	*/
	@Override
	public com.ibtrader.data.model.AuditIndicatorsStrategy fetchAuditIndicatorsStrategyByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _auditIndicatorsStrategyLocalService.fetchAuditIndicatorsStrategyByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the audit indicators strategy with the primary key.
	*
	* @param auditIndicatorsStrategyPK the primary key of the audit indicators strategy
	* @return the audit indicators strategy
	* @throws PortalException if a audit indicators strategy with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.AuditIndicatorsStrategy getAuditIndicatorsStrategy(
		com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK auditIndicatorsStrategyPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditIndicatorsStrategyLocalService.getAuditIndicatorsStrategy(auditIndicatorsStrategyPK);
	}

	/**
	* Returns the audit indicators strategy matching the UUID and group.
	*
	* @param uuid the audit indicators strategy's UUID
	* @param groupId the primary key of the group
	* @return the matching audit indicators strategy
	* @throws PortalException if a matching audit indicators strategy could not be found
	*/
	@Override
	public com.ibtrader.data.model.AuditIndicatorsStrategy getAuditIndicatorsStrategyByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditIndicatorsStrategyLocalService.getAuditIndicatorsStrategyByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the audit indicators strategy in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditIndicatorsStrategy the audit indicators strategy
	* @return the audit indicators strategy that was updated
	*/
	@Override
	public com.ibtrader.data.model.AuditIndicatorsStrategy updateAuditIndicatorsStrategy(
		com.ibtrader.data.model.AuditIndicatorsStrategy auditIndicatorsStrategy) {
		return _auditIndicatorsStrategyLocalService.updateAuditIndicatorsStrategy(auditIndicatorsStrategy);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _auditIndicatorsStrategyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditIndicatorsStrategyLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _auditIndicatorsStrategyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditIndicatorsStrategyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _auditIndicatorsStrategyLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of audit indicators strategies.
	*
	* @return the number of audit indicators strategies
	*/
	@Override
	public int getAuditIndicatorsStrategiesCount() {
		return _auditIndicatorsStrategyLocalService.getAuditIndicatorsStrategiesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _auditIndicatorsStrategyLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _auditIndicatorsStrategyLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _auditIndicatorsStrategyLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _auditIndicatorsStrategyLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
	@Override
	public java.util.List<com.ibtrader.data.model.AuditIndicatorsStrategy> getAuditIndicatorsStrategies(
		int start, int end) {
		return _auditIndicatorsStrategyLocalService.getAuditIndicatorsStrategies(start,
			end);
	}

	/**
	* Returns all the audit indicators strategies matching the UUID and company.
	*
	* @param uuid the UUID of the audit indicators strategies
	* @param companyId the primary key of the company
	* @return the matching audit indicators strategies, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.AuditIndicatorsStrategy> getAuditIndicatorsStrategiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _auditIndicatorsStrategyLocalService.getAuditIndicatorsStrategiesByUuidAndCompanyId(uuid,
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
	@Override
	public java.util.List<com.ibtrader.data.model.AuditIndicatorsStrategy> getAuditIndicatorsStrategiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.AuditIndicatorsStrategy> orderByComparator) {
		return _auditIndicatorsStrategyLocalService.getAuditIndicatorsStrategiesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _auditIndicatorsStrategyLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _auditIndicatorsStrategyLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public AuditIndicatorsStrategyLocalService getWrappedService() {
		return _auditIndicatorsStrategyLocalService;
	}

	@Override
	public void setWrappedService(
		AuditIndicatorsStrategyLocalService auditIndicatorsStrategyLocalService) {
		_auditIndicatorsStrategyLocalService = auditIndicatorsStrategyLocalService;
	}

	private AuditIndicatorsStrategyLocalService _auditIndicatorsStrategyLocalService;
}