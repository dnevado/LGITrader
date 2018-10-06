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
 * This class is used by SOAP remote services, specifically {@link com.ibtrader.data.service.http.HistoricalRealtimeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.http.HistoricalRealtimeServiceSoap
 * @generated
 */
@ProviderType
public class HistoricalRealtimeSoap implements Serializable {
	public static HistoricalRealtimeSoap toSoapModel(HistoricalRealtime model) {
		HistoricalRealtimeSoap soapModel = new HistoricalRealtimeSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setRealtimeId(model.getRealtimeId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setShareId(model.getShareId());
		soapModel.setValue(model.getValue());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMax_value(model.getMax_value());
		soapModel.setMin_value(model.getMin_value());
		soapModel.setVolume(model.getVolume());
		soapModel.setAvg_volume(model.getAvg_volume());
		soapModel.setCloseprice(model.getCloseprice());

		return soapModel;
	}

	public static HistoricalRealtimeSoap[] toSoapModels(
		HistoricalRealtime[] models) {
		HistoricalRealtimeSoap[] soapModels = new HistoricalRealtimeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HistoricalRealtimeSoap[][] toSoapModels(
		HistoricalRealtime[][] models) {
		HistoricalRealtimeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HistoricalRealtimeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HistoricalRealtimeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HistoricalRealtimeSoap[] toSoapModels(
		List<HistoricalRealtime> models) {
		List<HistoricalRealtimeSoap> soapModels = new ArrayList<HistoricalRealtimeSoap>(models.size());

		for (HistoricalRealtime model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HistoricalRealtimeSoap[soapModels.size()]);
	}

	public HistoricalRealtimeSoap() {
	}

	public long getPrimaryKey() {
		return _realtimeId;
	}

	public void setPrimaryKey(long pk) {
		setRealtimeId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getRealtimeId() {
		return _realtimeId;
	}

	public void setRealtimeId(long realtimeId) {
		_realtimeId = realtimeId;
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

	public double getValue() {
		return _value;
	}

	public void setValue(double value) {
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

	public double getMax_value() {
		return _max_value;
	}

	public void setMax_value(double max_value) {
		_max_value = max_value;
	}

	public double getMin_value() {
		return _min_value;
	}

	public void setMin_value(double min_value) {
		_min_value = min_value;
	}

	public int getVolume() {
		return _volume;
	}

	public void setVolume(int volume) {
		_volume = volume;
	}

	public int getAvg_volume() {
		return _avg_volume;
	}

	public void setAvg_volume(int avg_volume) {
		_avg_volume = avg_volume;
	}

	public boolean getCloseprice() {
		return _closeprice;
	}

	public boolean isCloseprice() {
		return _closeprice;
	}

	public void setCloseprice(boolean closeprice) {
		_closeprice = closeprice;
	}

	private String _uuid;
	private long _realtimeId;
	private long _groupId;
	private long _companyId;
	private long _shareId;
	private double _value;
	private Date _createDate;
	private Date _modifiedDate;
	private double _max_value;
	private double _min_value;
	private int _volume;
	private int _avg_volume;
	private boolean _closeprice;
}