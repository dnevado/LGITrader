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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ibtrader.data.service.http.ConfigServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.http.ConfigServiceSoap
 * @generated
 */
@ProviderType
public class ConfigSoap implements Serializable {
	public static ConfigSoap toSoapModel(Config model) {
		ConfigSoap soapModel = new ConfigSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setConfigId(model.getConfigId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setName(model.getName());
		soapModel.setValue(model.getValue());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setGlobaldefault(model.getGlobaldefault());
		soapModel.setIscron(model.getIscron());
		soapModel.setConfig_key(model.getConfig_key());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static ConfigSoap[] toSoapModels(Config[] models) {
		ConfigSoap[] soapModels = new ConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ConfigSoap[][] toSoapModels(Config[][] models) {
		ConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ConfigSoap[] toSoapModels(List<Config> models) {
		List<ConfigSoap> soapModels = new ArrayList<ConfigSoap>(models.size());

		for (Config model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ConfigSoap[soapModels.size()]);
	}

	public ConfigSoap() {
	}

	public long getPrimaryKey() {
		return _configId;
	}

	public void setPrimaryKey(long pk) {
		setConfigId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getConfigId() {
		return _configId;
	}

	public void setConfigId(long configId) {
		_configId = configId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public boolean getGlobaldefault() {
		return _globaldefault;
	}

	public boolean isGlobaldefault() {
		return _globaldefault;
	}

	public void setGlobaldefault(boolean globaldefault) {
		_globaldefault = globaldefault;
	}

	public boolean getIscron() {
		return _iscron;
	}

	public boolean isIscron() {
		return _iscron;
	}

	public void setIscron(boolean iscron) {
		_iscron = iscron;
	}

	public String getConfig_key() {
		return _config_key;
	}

	public void setConfig_key(String config_key) {
		_config_key = config_key;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private String _uuid;
	private long _configId;
	private long _groupId;
	private long _companyId;
	private String _name;
	private String _value;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _globaldefault;
	private boolean _iscron;
	private String _config_key;
	private String _description;
}