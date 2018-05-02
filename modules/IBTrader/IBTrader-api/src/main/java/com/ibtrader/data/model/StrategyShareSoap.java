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
 * This class is used by SOAP remote services, specifically {@link com.ibtrader.data.service.http.StrategyShareServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.http.StrategyShareServiceSoap
 * @generated
 */
@ProviderType
public class StrategyShareSoap implements Serializable {
	public static StrategyShareSoap toSoapModel(StrategyShare model) {
		StrategyShareSoap soapModel = new StrategyShareSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setStrategyshareId(model.getStrategyshareId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStrategyId(model.getStrategyId());
		soapModel.setShareId(model.getShareId());
		soapModel.setActive(model.getActive());
		soapModel.setVisible(model.getVisible());
		soapModel.setStrategyparamsoverride(model.getStrategyparamsoverride());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static StrategyShareSoap[] toSoapModels(StrategyShare[] models) {
		StrategyShareSoap[] soapModels = new StrategyShareSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StrategyShareSoap[][] toSoapModels(StrategyShare[][] models) {
		StrategyShareSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StrategyShareSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StrategyShareSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StrategyShareSoap[] toSoapModels(List<StrategyShare> models) {
		List<StrategyShareSoap> soapModels = new ArrayList<StrategyShareSoap>(models.size());

		for (StrategyShare model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StrategyShareSoap[soapModels.size()]);
	}

	public StrategyShareSoap() {
	}

	public long getPrimaryKey() {
		return _strategyshareId;
	}

	public void setPrimaryKey(long pk) {
		setStrategyshareId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getStrategyshareId() {
		return _strategyshareId;
	}

	public void setStrategyshareId(long strategyshareId) {
		_strategyshareId = strategyshareId;
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

	public long getStrategyId() {
		return _strategyId;
	}

	public void setStrategyId(long strategyId) {
		_strategyId = strategyId;
	}

	public long getShareId() {
		return _shareId;
	}

	public void setShareId(long shareId) {
		_shareId = shareId;
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

	public boolean getVisible() {
		return _visible;
	}

	public boolean isVisible() {
		return _visible;
	}

	public void setVisible(boolean visible) {
		_visible = visible;
	}

	public String getStrategyparamsoverride() {
		return _strategyparamsoverride;
	}

	public void setStrategyparamsoverride(String strategyparamsoverride) {
		_strategyparamsoverride = strategyparamsoverride;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private String _uuid;
	private long _strategyshareId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _strategyId;
	private long _shareId;
	private boolean _active;
	private boolean _visible;
	private String _strategyparamsoverride;
	private String _description;
}