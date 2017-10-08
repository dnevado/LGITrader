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
 * Provides the local service utility for Market. This utility wraps
 * {@link com.ibtrader.data.service.impl.MarketLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MarketLocalService
 * @see com.ibtrader.data.service.base.MarketLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.MarketLocalServiceImpl
 * @generated
 */
@ProviderType
public class MarketLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ibtrader.data.service.impl.MarketLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the market to the database. Also notifies the appropriate model listeners.
	*
	* @param market the market
	* @return the market that was added
	*/
	public static com.ibtrader.data.model.Market addMarket(
		com.ibtrader.data.model.Market market) {
		return getService().addMarket(market);
	}

	/**
	* Creates a new market with the primary key. Does not add the market to the database.
	*
	* @param marketId the primary key for the new market
	* @return the new market
	*/
	public static com.ibtrader.data.model.Market createMarket(long marketId) {
		return getService().createMarket(marketId);
	}

	/**
	* Deletes the market from the database. Also notifies the appropriate model listeners.
	*
	* @param market the market
	* @return the market that was removed
	*/
	public static com.ibtrader.data.model.Market deleteMarket(
		com.ibtrader.data.model.Market market) {
		return getService().deleteMarket(market);
	}

	/**
	* Deletes the market with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param marketId the primary key of the market
	* @return the market that was removed
	* @throws PortalException if a market with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Market deleteMarket(long marketId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMarket(marketId);
	}

	public static com.ibtrader.data.model.Market fetchMarket(long marketId) {
		return getService().fetchMarket(marketId);
	}

	/**
	* Returns the market matching the UUID and group.
	*
	* @param uuid the market's UUID
	* @param groupId the primary key of the group
	* @return the matching market, or <code>null</code> if a matching market could not be found
	*/
	public static com.ibtrader.data.model.Market fetchMarketByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchMarketByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the market with the primary key.
	*
	* @param marketId the primary key of the market
	* @return the market
	* @throws PortalException if a market with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Market getMarket(long marketId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMarket(marketId);
	}

	/**
	* Returns the market matching the UUID and group.
	*
	* @param uuid the market's UUID
	* @param groupId the primary key of the group
	* @return the matching market
	* @throws PortalException if a matching market could not be found
	*/
	public static com.ibtrader.data.model.Market getMarketByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMarketByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the market in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param market the market
	* @return the market that was updated
	*/
	public static com.ibtrader.data.model.Market updateMarket(
		com.ibtrader.data.model.Market market) {
		return getService().updateMarket(market);
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
	* Returns the number of markets.
	*
	* @return the number of markets
	*/
	public static int getMarketsCount() {
		return getService().getMarketsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.MarketModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.ibtrader.data.model.Market> findByActiveStartEndHour(
		java.lang.String _Start, java.lang.String _End, boolean _Active) {
		return getService().findByActiveStartEndHour(_Start, _End, _Active);
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
	public static java.util.List<com.ibtrader.data.model.Market> getMarkets(
		int start, int end) {
		return getService().getMarkets(start, end);
	}

	/**
	* Returns all the markets matching the UUID and company.
	*
	* @param uuid the UUID of the markets
	* @param companyId the primary key of the company
	* @return the matching markets, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.Market> getMarketsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getMarketsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<com.ibtrader.data.model.Market> getMarketsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Market> orderByComparator) {
		return getService()
				   .getMarketsByUuidAndCompanyId(uuid, companyId, start, end,
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

	public static MarketLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MarketLocalService, MarketLocalService> _serviceTracker =
		ServiceTrackerFactory.open(MarketLocalService.class);
}