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
 * Provides the local service utility for Config. This utility wraps
 * {@link com.ibtrader.data.service.impl.ConfigLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ConfigLocalService
 * @see com.ibtrader.data.service.base.ConfigLocalServiceBaseImpl
 * @see com.ibtrader.data.service.impl.ConfigLocalServiceImpl
 * @generated
 */
@ProviderType
public class ConfigLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.ibtrader.data.service.impl.ConfigLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the config to the database. Also notifies the appropriate model listeners.
	*
	* @param config the config
	* @return the config that was added
	*/
	public static com.ibtrader.data.model.Config addConfig(
		com.ibtrader.data.model.Config config) {
		return getService().addConfig(config);
	}

	/**
	* Creates a new config with the primary key. Does not add the config to the database.
	*
	* @param configId the primary key for the new config
	* @return the new config
	*/
	public static com.ibtrader.data.model.Config createConfig(long configId) {
		return getService().createConfig(configId);
	}

	/**
	* Deletes the config from the database. Also notifies the appropriate model listeners.
	*
	* @param config the config
	* @return the config that was removed
	*/
	public static com.ibtrader.data.model.Config deleteConfig(
		com.ibtrader.data.model.Config config) {
		return getService().deleteConfig(config);
	}

	/**
	* Deletes the config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param configId the primary key of the config
	* @return the config that was removed
	* @throws PortalException if a config with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Config deleteConfig(long configId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteConfig(configId);
	}

	public static com.ibtrader.data.model.Config fetchConfig(long configId) {
		return getService().fetchConfig(configId);
	}

	/**
	* Returns the config matching the UUID and group.
	*
	* @param uuid the config's UUID
	* @param groupId the primary key of the group
	* @return the matching config, or <code>null</code> if a matching config could not be found
	*/
	public static com.ibtrader.data.model.Config fetchConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchConfigByUuidAndGroupId(uuid, groupId);
	}

	public static com.ibtrader.data.model.Config findByKeyCompanyGroup(
		java.lang.String _key, long _company, long _group) {
		return getService().findByKeyCompanyGroup(_key, _company, _group);
	}

	/**
	* Returns the config with the primary key.
	*
	* @param configId the primary key of the config
	* @return the config
	* @throws PortalException if a config with the primary key could not be found
	*/
	public static com.ibtrader.data.model.Config getConfig(long configId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getConfig(configId);
	}

	/**
	* Returns the config matching the UUID and group.
	*
	* @param uuid the config's UUID
	* @param groupId the primary key of the group
	* @return the matching config
	* @throws PortalException if a matching config could not be found
	*/
	public static com.ibtrader.data.model.Config getConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getConfigByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param config the config
	* @return the config that was updated
	*/
	public static com.ibtrader.data.model.Config updateConfig(
		com.ibtrader.data.model.Config config) {
		return getService().updateConfig(config);
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
	* Returns the number of configs.
	*
	* @return the number of configs
	*/
	public static int getConfigsCount() {
		return getService().getConfigsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.ibtrader.data.model.Config> findByKeyGlobalDefault(
		java.lang.String _key, boolean _global) {
		return getService().findByKeyGlobalDefault(_key, _global);
	}

	/**
	* Returns a range of all the configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.ConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @return the range of configs
	*/
	public static java.util.List<com.ibtrader.data.model.Config> getConfigs(
		int start, int end) {
		return getService().getConfigs(start, end);
	}

	/**
	* Returns all the configs matching the UUID and company.
	*
	* @param uuid the UUID of the configs
	* @param companyId the primary key of the company
	* @return the matching configs, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.Config> getConfigsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getConfigsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of configs matching the UUID and company.
	*
	* @param uuid the UUID of the configs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of configs
	* @param end the upper bound of the range of configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching configs, or an empty list if no matches were found
	*/
	public static java.util.List<com.ibtrader.data.model.Config> getConfigsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Config> orderByComparator) {
		return getService()
				   .getConfigsByUuidAndCompanyId(uuid, companyId, start, end,
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

	public static void addConfigurationValuesCompanyGroup(long _company,
		long _group) {
		getService().addConfigurationValuesCompanyGroup(_company, _group);
	}

	public static void removeConfigurationValuesCompanyGroup(long _company,
		long _group) {
		getService().removeConfigurationValuesCompanyGroup(_company, _group);
	}

	public static ConfigLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ConfigLocalService, ConfigLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ConfigLocalService.class);
}