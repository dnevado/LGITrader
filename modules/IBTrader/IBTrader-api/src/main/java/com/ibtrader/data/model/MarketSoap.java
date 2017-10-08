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
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class MarketSoap implements Serializable {
	public static MarketSoap toSoapModel(Market model) {
		MarketSoap soapModel = new MarketSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setMarketId(model.getMarketId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setActive(model.getActive());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStart_hour(model.getStart_hour());
		soapModel.setEnd_hour(model.getEnd_hour());
		soapModel.setIdentifier(model.getIdentifier());
		soapModel.setCurrency(model.getCurrency());

		return soapModel;
	}

	public static MarketSoap[] toSoapModels(Market[] models) {
		MarketSoap[] soapModels = new MarketSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MarketSoap[][] toSoapModels(Market[][] models) {
		MarketSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MarketSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MarketSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MarketSoap[] toSoapModels(List<Market> models) {
		List<MarketSoap> soapModels = new ArrayList<MarketSoap>(models.size());

		for (Market model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MarketSoap[soapModels.size()]);
	}

	public MarketSoap() {
	}

	public long getPrimaryKey() {
		return _marketId;
	}

	public void setPrimaryKey(long pk) {
		setMarketId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getMarketId() {
		return _marketId;
	}

	public void setMarketId(long marketId) {
		_marketId = marketId;
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

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
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

	public String getStart_hour() {
		return _start_hour;
	}

	public void setStart_hour(String start_hour) {
		_start_hour = start_hour;
	}

	public String getEnd_hour() {
		return _end_hour;
	}

	public void setEnd_hour(String end_hour) {
		_end_hour = end_hour;
	}

	public String getIdentifier() {
		return _identifier;
	}

	public void setIdentifier(String identifier) {
		_identifier = identifier;
	}

	public String getCurrency() {
		return _currency;
	}

	public void setCurrency(String currency) {
		_currency = currency;
	}

	private String _uuid;
	private long _marketId;
	private long _groupId;
	private long _companyId;
	private boolean _active;
	private Date _createDate;
	private Date _modifiedDate;
	private String _start_hour;
	private String _end_hour;
	private String _identifier;
	private String _currency;
}