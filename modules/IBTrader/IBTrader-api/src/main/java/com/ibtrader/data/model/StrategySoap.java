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
 * This class is used by SOAP remote services, specifically {@link com.ibtrader.data.service.http.StrategyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.http.StrategyServiceSoap
 * @generated
 */
@ProviderType
public class StrategySoap implements Serializable {
	public static StrategySoap toSoapModel(Strategy model) {
		StrategySoap soapModel = new StrategySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setStrategyID(model.getStrategyID());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setActive(model.getActive());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());
		soapModel.setType(model.getType());
		soapModel.setCan_override_params(model.getCan_override_params());
		soapModel.setClassName(model.getClassName());
		soapModel.setUserId(model.getUserId());

		return soapModel;
	}

	public static StrategySoap[] toSoapModels(Strategy[] models) {
		StrategySoap[] soapModels = new StrategySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StrategySoap[][] toSoapModels(Strategy[][] models) {
		StrategySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StrategySoap[models.length][models[0].length];
		}
		else {
			soapModels = new StrategySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StrategySoap[] toSoapModels(List<Strategy> models) {
		List<StrategySoap> soapModels = new ArrayList<StrategySoap>(models.size());

		for (Strategy model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StrategySoap[soapModels.size()]);
	}

	public StrategySoap() {
	}

	public long getPrimaryKey() {
		return _strategyID;
	}

	public void setPrimaryKey(long pk) {
		setStrategyID(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getStrategyID() {
		return _strategyID;
	}

	public void setStrategyID(long strategyID) {
		_strategyID = strategyID;
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

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
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

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public boolean getCan_override_params() {
		return _can_override_params;
	}

	public boolean isCan_override_params() {
		return _can_override_params;
	}

	public void setCan_override_params(boolean can_override_params) {
		_can_override_params = can_override_params;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private String _uuid;
	private long _strategyID;
	private long _groupId;
	private long _companyId;
	private String _name;
	private String _description;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _active;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private String _type;
	private boolean _can_override_params;
	private String _className;
	private long _userId;
}