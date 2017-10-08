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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class MarkeShareSoap implements Serializable {
	public static MarkeShareSoap toSoapModel(MarkeShare model) {
		MarkeShareSoap soapModel = new MarkeShareSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setMarketshareId(model.getMarketshareId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setShareId(model.getShareId());
		soapModel.setMarketId(model.getMarketId());

		return soapModel;
	}

	public static MarkeShareSoap[] toSoapModels(MarkeShare[] models) {
		MarkeShareSoap[] soapModels = new MarkeShareSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MarkeShareSoap[][] toSoapModels(MarkeShare[][] models) {
		MarkeShareSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MarkeShareSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MarkeShareSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MarkeShareSoap[] toSoapModels(List<MarkeShare> models) {
		List<MarkeShareSoap> soapModels = new ArrayList<MarkeShareSoap>(models.size());

		for (MarkeShare model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MarkeShareSoap[soapModels.size()]);
	}

	public MarkeShareSoap() {
	}

	public long getPrimaryKey() {
		return _marketshareId;
	}

	public void setPrimaryKey(long pk) {
		setMarketshareId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getMarketshareId() {
		return _marketshareId;
	}

	public void setMarketshareId(long marketshareId) {
		_marketshareId = marketshareId;
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

	public long getShareId() {
		return _shareId;
	}

	public void setShareId(long shareId) {
		_shareId = shareId;
	}

	public long getMarketId() {
		return _marketId;
	}

	public void setMarketId(long marketId) {
		_marketId = marketId;
	}

	private String _uuid;
	private long _marketshareId;
	private long _groupId;
	private long _companyId;
	private long _shareId;
	private long _marketId;
}