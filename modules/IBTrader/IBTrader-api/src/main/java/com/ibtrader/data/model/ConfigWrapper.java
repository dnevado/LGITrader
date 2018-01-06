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

package com.ibtrader.data.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Config}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Config
 * @generated
 */
@ProviderType
public class ConfigWrapper implements Config, ModelWrapper<Config> {
	public ConfigWrapper(Config config) {
		_config = config;
	}

	@Override
	public Class<?> getModelClass() {
		return Config.class;
	}

	@Override
	public String getModelClassName() {
		return Config.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("configId", getConfigId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("name", getName());
		attributes.put("value", getValue());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("globaldefault", getGlobaldefault());
		attributes.put("iscron", getIscron());
		attributes.put("config_key", getConfig_key());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long configId = (Long)attributes.get("configId");

		if (configId != null) {
			setConfigId(configId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String value = (String)attributes.get("value");

		if (value != null) {
			setValue(value);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean globaldefault = (Boolean)attributes.get("globaldefault");

		if (globaldefault != null) {
			setGlobaldefault(globaldefault);
		}

		Boolean iscron = (Boolean)attributes.get("iscron");

		if (iscron != null) {
			setIscron(iscron);
		}

		String config_key = (String)attributes.get("config_key");

		if (config_key != null) {
			setConfig_key(config_key);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	@Override
	public Config toEscapedModel() {
		return new ConfigWrapper(_config.toEscapedModel());
	}

	@Override
	public Config toUnescapedModel() {
		return new ConfigWrapper(_config.toUnescapedModel());
	}

	/**
	* Returns the globaldefault of this config.
	*
	* @return the globaldefault of this config
	*/
	@Override
	public boolean getGlobaldefault() {
		return _config.getGlobaldefault();
	}

	/**
	* Returns the iscron of this config.
	*
	* @return the iscron of this config
	*/
	@Override
	public boolean getIscron() {
		return _config.getIscron();
	}

	@Override
	public boolean isCachedModel() {
		return _config.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _config.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this config is globaldefault.
	*
	* @return <code>true</code> if this config is globaldefault; <code>false</code> otherwise
	*/
	@Override
	public boolean isGlobaldefault() {
		return _config.isGlobaldefault();
	}

	/**
	* Returns <code>true</code> if this config is iscron.
	*
	* @return <code>true</code> if this config is iscron; <code>false</code> otherwise
	*/
	@Override
	public boolean isIscron() {
		return _config.isIscron();
	}

	@Override
	public boolean isNew() {
		return _config.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _config.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Config> toCacheModel() {
		return _config.toCacheModel();
	}

	@Override
	public int compareTo(Config config) {
		return _config.compareTo(config);
	}

	@Override
	public int hashCode() {
		return _config.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _config.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ConfigWrapper((Config)_config.clone());
	}

	/**
	* Returns the config_key of this config.
	*
	* @return the config_key of this config
	*/
	@Override
	public java.lang.String getConfig_key() {
		return _config.getConfig_key();
	}

	/**
	* Returns the description of this config.
	*
	* @return the description of this config
	*/
	@Override
	public java.lang.String getDescription() {
		return _config.getDescription();
	}

	/**
	* Returns the name of this config.
	*
	* @return the name of this config
	*/
	@Override
	public java.lang.String getName() {
		return _config.getName();
	}

	/**
	* Returns the uuid of this config.
	*
	* @return the uuid of this config
	*/
	@Override
	public java.lang.String getUuid() {
		return _config.getUuid();
	}

	/**
	* Returns the value of this config.
	*
	* @return the value of this config
	*/
	@Override
	public java.lang.String getValue() {
		return _config.getValue();
	}

	@Override
	public java.lang.String toString() {
		return _config.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _config.toXmlString();
	}

	/**
	* Returns the create date of this config.
	*
	* @return the create date of this config
	*/
	@Override
	public Date getCreateDate() {
		return _config.getCreateDate();
	}

	/**
	* Returns the modified date of this config.
	*
	* @return the modified date of this config
	*/
	@Override
	public Date getModifiedDate() {
		return _config.getModifiedDate();
	}

	/**
	* Returns the company ID of this config.
	*
	* @return the company ID of this config
	*/
	@Override
	public long getCompanyId() {
		return _config.getCompanyId();
	}

	/**
	* Returns the config ID of this config.
	*
	* @return the config ID of this config
	*/
	@Override
	public long getConfigId() {
		return _config.getConfigId();
	}

	/**
	* Returns the group ID of this config.
	*
	* @return the group ID of this config
	*/
	@Override
	public long getGroupId() {
		return _config.getGroupId();
	}

	/**
	* Returns the primary key of this config.
	*
	* @return the primary key of this config
	*/
	@Override
	public long getPrimaryKey() {
		return _config.getPrimaryKey();
	}

	@Override
	public void persist() {
		_config.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_config.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this config.
	*
	* @param companyId the company ID of this config
	*/
	@Override
	public void setCompanyId(long companyId) {
		_config.setCompanyId(companyId);
	}

	/**
	* Sets the config ID of this config.
	*
	* @param configId the config ID of this config
	*/
	@Override
	public void setConfigId(long configId) {
		_config.setConfigId(configId);
	}

	/**
	* Sets the config_key of this config.
	*
	* @param config_key the config_key of this config
	*/
	@Override
	public void setConfig_key(java.lang.String config_key) {
		_config.setConfig_key(config_key);
	}

	/**
	* Sets the create date of this config.
	*
	* @param createDate the create date of this config
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_config.setCreateDate(createDate);
	}

	/**
	* Sets the description of this config.
	*
	* @param description the description of this config
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_config.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_config.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_config.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_config.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this config is globaldefault.
	*
	* @param globaldefault the globaldefault of this config
	*/
	@Override
	public void setGlobaldefault(boolean globaldefault) {
		_config.setGlobaldefault(globaldefault);
	}

	/**
	* Sets the group ID of this config.
	*
	* @param groupId the group ID of this config
	*/
	@Override
	public void setGroupId(long groupId) {
		_config.setGroupId(groupId);
	}

	/**
	* Sets whether this config is iscron.
	*
	* @param iscron the iscron of this config
	*/
	@Override
	public void setIscron(boolean iscron) {
		_config.setIscron(iscron);
	}

	/**
	* Sets the modified date of this config.
	*
	* @param modifiedDate the modified date of this config
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_config.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this config.
	*
	* @param name the name of this config
	*/
	@Override
	public void setName(java.lang.String name) {
		_config.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_config.setNew(n);
	}

	/**
	* Sets the primary key of this config.
	*
	* @param primaryKey the primary key of this config
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_config.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_config.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the uuid of this config.
	*
	* @param uuid the uuid of this config
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_config.setUuid(uuid);
	}

	/**
	* Sets the value of this config.
	*
	* @param value the value of this config
	*/
	@Override
	public void setValue(java.lang.String value) {
		_config.setValue(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ConfigWrapper)) {
			return false;
		}

		ConfigWrapper configWrapper = (ConfigWrapper)obj;

		if (Objects.equals(_config, configWrapper._config)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _config.getStagedModelType();
	}

	@Override
	public Config getWrappedModel() {
		return _config;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _config.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _config.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_config.resetOriginalValues();
	}

	private final Config _config;
}