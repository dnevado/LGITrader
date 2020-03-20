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
 * Provides a wrapper for {@link MarketLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MarketLocalService
 * @generated
 */
@ProviderType
public class MarketLocalServiceWrapper implements MarketLocalService,
	ServiceWrapper<MarketLocalService> {
	public MarketLocalServiceWrapper(MarketLocalService marketLocalService) {
		_marketLocalService = marketLocalService;
	}

	/**
	* Adds the market to the database. Also notifies the appropriate model listeners.
	*
	* @param market the market
	* @return the market that was added
	*/
	@Override
	public com.ibtrader.data.model.Market addMarket(
		com.ibtrader.data.model.Market market) {
		return _marketLocalService.addMarket(market);
	}

	@Override
	public com.ibtrader.data.model.Market addMarket(
		com.ibtrader.data.model.Market market,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _marketLocalService.addMarket(market, serviceContext);
	}

	/**
	* Creates a new market with the primary key. Does not add the market to the database.
	*
	* @param marketId the primary key for the new market
	* @return the new market
	*/
	@Override
	public com.ibtrader.data.model.Market createMarket(long marketId) {
		return _marketLocalService.createMarket(marketId);
	}

	/**
	* Deletes the market from the database. Also notifies the appropriate model listeners.
	*
	* @param market the market
	* @return the market that was removed
	*/
	@Override
	public com.ibtrader.data.model.Market deleteMarket(
		com.ibtrader.data.model.Market market) {
		return _marketLocalService.deleteMarket(market);
	}

	/**
	* Deletes the market with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param marketId the primary key of the market
	* @return the market that was removed
	* @throws PortalException if a market with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.Market deleteMarket(long marketId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _marketLocalService.deleteMarket(marketId);
	}

	@Override
	public com.ibtrader.data.model.Market editMarket(
		com.ibtrader.data.model.Market market,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _marketLocalService.editMarket(market, serviceContext);
	}

	@Override
	public com.ibtrader.data.model.Market fetchMarket(long marketId) {
		return _marketLocalService.fetchMarket(marketId);
	}

	/**
	* Returns the market matching the UUID and group.
	*
	* @param uuid the market's UUID
	* @param groupId the primary key of the group
	* @return the matching market, or <code>null</code> if a matching market could not be found
	*/
	@Override
	public com.ibtrader.data.model.Market fetchMarketByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _marketLocalService.fetchMarketByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.ibtrader.data.model.Market findByIdentifierCompanyGroup(
		long companyId, long groupId, java.lang.String identifier) {
		return _marketLocalService.findByIdentifierCompanyGroup(companyId,
			groupId, identifier);
	}

	@Override
	public com.ibtrader.data.model.Market findByNameMarketCompanyGroup(
		long companyId, long groupId, java.lang.String name) {
		return _marketLocalService.findByNameMarketCompanyGroup(companyId,
			groupId, name);
	}

	/**
	* Returns the market with the primary key.
	*
	* @param marketId the primary key of the market
	* @return the market
	* @throws PortalException if a market with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.Market getMarket(long marketId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _marketLocalService.getMarket(marketId);
	}

	/**
	* Returns the market matching the UUID and group.
	*
	* @param uuid the market's UUID
	* @param groupId the primary key of the group
	* @return the matching market
	* @throws PortalException if a matching market could not be found
	*/
	@Override
	public com.ibtrader.data.model.Market getMarketByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _marketLocalService.getMarketByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the market in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param market the market
	* @return the market that was updated
	*/
	@Override
	public com.ibtrader.data.model.Market updateMarket(
		com.ibtrader.data.model.Market market) {
		return _marketLocalService.updateMarket(market);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _marketLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _marketLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _marketLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _marketLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _marketLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _marketLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of markets.
	*
	* @return the number of markets
	*/
	@Override
	public int getMarketsCount() {
		return _marketLocalService.getMarketsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _marketLocalService.getOSGiServiceIdentifier();
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
		return _marketLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _marketLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _marketLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Market> findByActive(
		boolean active) {
		return _marketLocalService.findByActive(active);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Market> findByActiveCompanyGroup(
		long companyId, long groupId, boolean active) {
		return _marketLocalService.findByActiveCompanyGroup(companyId, groupId,
			active);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Market> findByActiveStartEndMarketHour(
		boolean active, long companyId, long groupId) {
		return _marketLocalService.findByActiveStartEndMarketHour(active,
			companyId, groupId);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Market> findByActiveStartEndMarketHour(
		boolean active, long companyId, long groupId,
		java.util.Date backTestingDate) {
		return _marketLocalService.findByActiveStartEndMarketHour(active,
			companyId, groupId, backTestingDate);
	}

	@Override
	public java.util.List<com.ibtrader.data.model.Market> findByCompanyGroup(
		long companyId, long groupId) {
		return _marketLocalService.findByCompanyGroup(companyId, groupId);
	}

	/**
	* Returns a range of all the markets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @return the range of markets
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.Market> getMarkets(
		int start, int end) {
		return _marketLocalService.getMarkets(start, end);
	}

	/**
	* Returns all the markets matching the UUID and company.
	*
	* @param uuid the UUID of the markets
	* @param companyId the primary key of the company
	* @return the matching markets, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.Market> getMarketsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _marketLocalService.getMarketsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of markets matching the UUID and company.
	*
	* @param uuid the UUID of the markets
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of markets
	* @param end the upper bound of the range of markets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching markets, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.Market> getMarketsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Market> orderByComparator) {
		return _marketLocalService.getMarketsByUuidAndCompanyId(uuid,
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
		return _marketLocalService.dynamicQueryCount(dynamicQuery);
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
		return _marketLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public MarketLocalService getWrappedService() {
		return _marketLocalService;
	}

	@Override
	public void setWrappedService(MarketLocalService marketLocalService) {
		_marketLocalService = marketLocalService;
	}

	private MarketLocalService _marketLocalService;
}