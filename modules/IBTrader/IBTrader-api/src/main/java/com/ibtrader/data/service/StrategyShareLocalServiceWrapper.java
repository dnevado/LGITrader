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
 * Provides a wrapper for {@link StrategyShareLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see StrategyShareLocalService
 * @generated
 */
@ProviderType
public class StrategyShareLocalServiceWrapper
	implements StrategyShareLocalService,
		ServiceWrapper<StrategyShareLocalService> {
	public StrategyShareLocalServiceWrapper(
		StrategyShareLocalService strategyShareLocalService) {
		_strategyShareLocalService = strategyShareLocalService;
	}

	/**
	* Adds the strategy share to the database. Also notifies the appropriate model listeners.
	*
	* @param strategyShare the strategy share
	* @return the strategy share that was added
	*/
	@Override
	public com.ibtrader.data.model.StrategyShare addStrategyShare(
		com.ibtrader.data.model.StrategyShare strategyShare) {
		return _strategyShareLocalService.addStrategyShare(strategyShare);
	}

	/**
	* Creates a new strategy share with the primary key. Does not add the strategy share to the database.
	*
	* @param strategyshareId the primary key for the new strategy share
	* @return the new strategy share
	*/
	@Override
	public com.ibtrader.data.model.StrategyShare createStrategyShare(
		long strategyshareId) {
		return _strategyShareLocalService.createStrategyShare(strategyshareId);
	}

	/**
	* Deletes the strategy share from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyShare the strategy share
	* @return the strategy share that was removed
	*/
	@Override
	public com.ibtrader.data.model.StrategyShare deleteStrategyShare(
		com.ibtrader.data.model.StrategyShare strategyShare) {
		return _strategyShareLocalService.deleteStrategyShare(strategyShare);
	}

	/**
	* Deletes the strategy share with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share that was removed
	* @throws PortalException if a strategy share with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.StrategyShare deleteStrategyShare(
		long strategyshareId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyShareLocalService.deleteStrategyShare(strategyshareId);
	}

	@Override
	public com.ibtrader.data.model.StrategyShare fetchStrategyShare(
		long strategyshareId) {
		return _strategyShareLocalService.fetchStrategyShare(strategyshareId);
	}

	/**
	* Returns the strategy share matching the UUID and group.
	*
	* @param uuid the strategy share's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy share, or <code>null</code> if a matching strategy share could not be found
	*/
	@Override
	public com.ibtrader.data.model.StrategyShare fetchStrategyShareByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _strategyShareLocalService.fetchStrategyShareByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.ibtrader.data.model.StrategyShare getByCommpanyShareStrategyId(
		long groupid, long companyid, long shareId, long strategyId) {
		return _strategyShareLocalService.getByCommpanyShareStrategyId(groupid,
			companyid, shareId, strategyId);
	}

	/**
	* Returns the strategy share with the primary key.
	*
	* @param strategyshareId the primary key of the strategy share
	* @return the strategy share
	* @throws PortalException if a strategy share with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.StrategyShare getStrategyShare(
		long strategyshareId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyShareLocalService.getStrategyShare(strategyshareId);
	}

	/**
	* Returns the strategy share matching the UUID and group.
	*
	* @param uuid the strategy share's UUID
	* @param groupId the primary key of the group
	* @return the matching strategy share
	* @throws PortalException if a matching strategy share could not be found
	*/
	@Override
	public com.ibtrader.data.model.StrategyShare getStrategyShareByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyShareLocalService.getStrategyShareByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the strategy share in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param strategyShare the strategy share
	* @return the strategy share that was updated
	*/
	@Override
	public com.ibtrader.data.model.StrategyShare updateStrategyShare(
		com.ibtrader.data.model.StrategyShare strategyShare) {
		return _strategyShareLocalService.updateStrategyShare(strategyShare);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _strategyShareLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _strategyShareLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _strategyShareLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _strategyShareLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyShareLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strategyShareLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of strategy shares.
	*
	* @return the number of strategy shares
	*/
	@Override
	public int getStrategySharesCount() {
		return _strategyShareLocalService.getStrategySharesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _strategyShareLocalService.getOSGiServiceIdentifier();
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
		return _strategyShareLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _strategyShareLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _strategyShareLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Strategy> findByActiveStrategies(
		boolean active, long shareId, long companyId, long groupId) {
		return _strategyShareLocalService.findByActiveStrategies(active,
			shareId, companyId, groupId);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.StrategyShare> getByGroupCompanyShareId(
		long groupid, long companyid, long shareId) {
		return _strategyShareLocalService.getByGroupCompanyShareId(groupid,
			companyid, shareId);
	}

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
	@Override
	public java.util.List<com.ibtrader.data.model.StrategyShare> getStrategyShares(
		int start, int end) {
		return _strategyShareLocalService.getStrategyShares(start, end);
	}

	/**
	* Returns all the strategy shares matching the UUID and company.
	*
	* @param uuid the UUID of the strategy shares
	* @param companyId the primary key of the company
	* @return the matching strategy shares, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.StrategyShare> getStrategySharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _strategyShareLocalService.getStrategySharesByUuidAndCompanyId(uuid,
			companyId);
	}

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
	@Override
	public java.util.List<com.ibtrader.data.model.StrategyShare> getStrategySharesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.StrategyShare> orderByComparator) {
		return _strategyShareLocalService.getStrategySharesByUuidAndCompanyId(uuid,
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
		return _strategyShareLocalService.dynamicQueryCount(dynamicQuery);
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
		return _strategyShareLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public StrategyShareLocalService getWrappedService() {
		return _strategyShareLocalService;
	}

	@Override
	public void setWrappedService(
		StrategyShareLocalService strategyShareLocalService) {
		_strategyShareLocalService = strategyShareLocalService;
	}

	private StrategyShareLocalService _strategyShareLocalService;
}