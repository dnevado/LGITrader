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
 * This class is used by SOAP remote services, specifically {@link com.ibtrader.data.service.http.BackTestingServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.http.BackTestingServiceSoap
 * @generated
 */
@ProviderType
public class BackTestingSoap implements Serializable {
	public static BackTestingSoap toSoapModel(BackTesting model) {
		BackTestingSoap soapModel = new BackTestingSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setBackTId(model.getBackTId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFromDate(model.getFromDate());
		soapModel.setToDate(model.getToDate());
		soapModel.setShareId(model.getShareId());
		soapModel.setCountordersBUY(model.getCountordersBUY());
		soapModel.setCountordersSELL(model.getCountordersSELL());
		soapModel.setProfitordersBUY(model.getProfitordersBUY());
		soapModel.setProfitordersSELL(model.getProfitordersSELL());
		soapModel.setStatus(model.getStatus());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static BackTestingSoap[] toSoapModels(BackTesting[] models) {
		BackTestingSoap[] soapModels = new BackTestingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BackTestingSoap[][] toSoapModels(BackTesting[][] models) {
		BackTestingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BackTestingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BackTestingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BackTestingSoap[] toSoapModels(List<BackTesting> models) {
		List<BackTestingSoap> soapModels = new ArrayList<BackTestingSoap>(models.size());

		for (BackTesting model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BackTestingSoap[soapModels.size()]);
	}

	public BackTestingSoap() {
	}

	public long getPrimaryKey() {
		return _backTId;
	}

	public void setPrimaryKey(long pk) {
		setBackTId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getBackTId() {
		return _backTId;
	}

	public void setBackTId(long backTId) {
		_backTId = backTId;
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

	public Date getFromDate() {
		return _fromDate;
	}

	public void setFromDate(Date fromDate) {
		_fromDate = fromDate;
	}

	public Date getToDate() {
		return _toDate;
	}

	public void setToDate(Date toDate) {
		_toDate = toDate;
	}

	public long getShareId() {
		return _shareId;
	}

	public void setShareId(long shareId) {
		_shareId = shareId;
	}

	public long getCountordersBUY() {
		return _countordersBUY;
	}

	public void setCountordersBUY(long countordersBUY) {
		_countordersBUY = countordersBUY;
	}

	public long getCountordersSELL() {
		return _countordersSELL;
	}

	public void setCountordersSELL(long countordersSELL) {
		_countordersSELL = countordersSELL;
	}

	public double getProfitordersBUY() {
		return _profitordersBUY;
	}

	public void setProfitordersBUY(double profitordersBUY) {
		_profitordersBUY = profitordersBUY;
	}

	public double getProfitordersSELL() {
		return _profitordersSELL;
	}

	public void setProfitordersSELL(double profitordersSELL) {
		_profitordersSELL = profitordersSELL;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private String _uuid;
	private long _backTId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private Date _fromDate;
	private Date _toDate;
	private long _shareId;
	private long _countordersBUY;
	private long _countordersSELL;
	private double _profitordersBUY;
	private double _profitordersSELL;
	private String _status;
	private String _description;
}