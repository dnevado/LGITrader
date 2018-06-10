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
 * Provides a wrapper for {@link ConfigLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ConfigLocalService
 * @generated
 */
@ProviderType
public class ConfigLocalServiceWrapper implements ConfigLocalService,
	ServiceWrapper<ConfigLocalService> {
	public ConfigLocalServiceWrapper(ConfigLocalService configLocalService) {
		_configLocalService = configLocalService;
	}

	/**
	* Adds the config to the database. Also notifies the appropriate model listeners.
	*
	* @param config the config
	* @return the config that was added
	*/
	@Override
	public com.ibtrader.data.model.Config addConfig(
		com.ibtrader.data.model.Config config) {
		return _configLocalService.addConfig(config);
	}

	/**
	* Creates a new config with the primary key. Does not add the config to the database.
	*
	* @param configId the primary key for the new config
	* @return the new config
	*/
	@Override
	public com.ibtrader.data.model.Config createConfig(long configId) {
		return _configLocalService.createConfig(configId);
	}

	/**
	* Deletes the config from the database. Also notifies the appropriate model listeners.
	*
	* @param config the config
	* @return the config that was removed
	*/
	@Override
	public com.ibtrader.data.model.Config deleteConfig(
		com.ibtrader.data.model.Config config) {
		return _configLocalService.deleteConfig(config);
	}

	/**
	* Deletes the config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param configId the primary key of the config
	* @return the config that was removed
	* @throws PortalException if a config with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.Config deleteConfig(long configId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _configLocalService.deleteConfig(configId);
	}

	@Override
	public com.ibtrader.data.model.Config fetchConfig(long configId) {
		return _configLocalService.fetchConfig(configId);
	}

	/**
	* Returns the config matching the UUID and group.
	*
	* @param uuid the config's UUID
	* @param groupId the primary key of the group
	* @return the matching config, or <code>null</code> if a matching config could not be found
	*/
	@Override
	public com.ibtrader.data.model.Config fetchConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _configLocalService.fetchConfigByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.ibtrader.data.model.Config findByIsCronValue(boolean isCron,
		java.lang.String value) {
		return _configLocalService.findByIsCronValue(isCron, value);
	}

	@Override
	public com.ibtrader.data.model.Config findByKeyCompanyGroup(
		java.lang.String _key, long _company, long _group) {
		return _configLocalService.findByKeyCompanyGroup(_key, _company, _group);
	}

	/**
	* Returns the config with the primary key.
	*
	* @param configId the primary key of the config
	* @return the config
	* @throws PortalException if a config with the primary key could not be found
	*/
	@Override
	public com.ibtrader.data.model.Config getConfig(long configId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _configLocalService.getConfig(configId);
	}

	/**
	* Returns the config matching the UUID and group.
	*
	* @param uuid the config's UUID
	* @param groupId the primary key of the group
	* @return the matching config
	* @throws PortalException if a matching config could not be found
	*/
	@Override
	public com.ibtrader.data.model.Config getConfigByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _configLocalService.getConfigByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param config the config
	* @return the config that was updated
	*/
	@Override
	public com.ibtrader.data.model.Config updateConfig(
		com.ibtrader.data.model.Config config) {
		return _configLocalService.updateConfig(config);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _configLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _configLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _configLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _configLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _configLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _configLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of configs.
	*
	* @return the number of configs
	*/
	@Override
	public int getConfigsCount() {
		return _configLocalService.getConfigsCount();
	}

	@Override
	public java.lang.Long findByFreeCronClientId() {
		return _configLocalService.findByFreeCronClientId();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _configLocalService.getOSGiServiceIdentifier();
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
		return _configLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _configLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _configLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	@Override
	public java.util.List<com.ibtrader.data.model.Config> getConfigs(
		int start, int end) {
		return _configLocalService.getConfigs(start, end);
	}

	/**
	* Returns all the configs matching the UUID and company.
	*
	* @param uuid the UUID of the configs
	* @param companyId the primary key of the company
	* @return the matching configs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.ibtrader.data.model.Config> getConfigsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _configLocalService.getConfigsByUuidAndCompanyId(uuid, companyId);
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
	@Override
	public java.util.List<com.ibtrader.data.model.Config> getConfigsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.ibtrader.data.model.Config> orderByComparator) {
		return _configLocalService.getConfigsByUuidAndCompanyId(uuid,
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
		return _configLocalService.dynamicQueryCount(dynamicQuery);
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
		return _configLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public void addConfigurationValuesCompanyGroup(long _company, long _group) {
		_configLocalService.addConfigurationValuesCompanyGroup(_company, _group);
	}

	@Override
	public void removeConfigurationValuesCompanyGroup(long _company, long _group) {
		_configLocalService.removeConfigurationValuesCompanyGroup(_company,
			_group);
	}

	@Override
	public ConfigLocalService getWrappedService() {
		return _configLocalService;
	}

	@Override
	public void setWrappedService(ConfigLocalService configLocalService) {
		_configLocalService = configLocalService;
	}

	private ConfigLocalService _configLocalService;
}