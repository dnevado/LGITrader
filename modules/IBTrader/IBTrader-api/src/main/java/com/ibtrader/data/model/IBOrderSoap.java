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
 * This class is used by SOAP remote services, specifically {@link com.ibtrader.data.service.http.IBOrderServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.http.IBOrderServiceSoap
 * @generated
 */
@ProviderType
public class IBOrderSoap implements Serializable {
	public static IBOrderSoap toSoapModel(IBOrder model) {
		IBOrderSoap soapModel = new IBOrderSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setOrderIdPk(model.getOrderIdPk());
		soapModel.setOrdersId(model.getOrdersId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setShareID(model.getShareID());
		soapModel.setChecked(model.getChecked());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setIbclientId(model.getIbclientId());
		soapModel.setRemovable_on_reboot(model.getRemovable_on_reboot());

		return soapModel;
	}

	public static IBOrderSoap[] toSoapModels(IBOrder[] models) {
		IBOrderSoap[] soapModels = new IBOrderSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static IBOrderSoap[][] toSoapModels(IBOrder[][] models) {
		IBOrderSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new IBOrderSoap[models.length][models[0].length];
		}
		else {
			soapModels = new IBOrderSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static IBOrderSoap[] toSoapModels(List<IBOrder> models) {
		List<IBOrderSoap> soapModels = new ArrayList<IBOrderSoap>(models.size());

		for (IBOrder model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IBOrderSoap[soapModels.size()]);
	}

	public IBOrderSoap() {
	}

	public long getPrimaryKey() {
		return _orderIdPk;
	}

	public void setPrimaryKey(long pk) {
		setOrderIdPk(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getOrderIdPk() {
		return _orderIdPk;
	}

	public void setOrderIdPk(long orderIdPk) {
		_orderIdPk = orderIdPk;
	}

	public long getOrdersId() {
		return _ordersId;
	}

	public void setOrdersId(long ordersId) {
		_ordersId = ordersId;
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

	public long getShareID() {
		return _shareID;
	}

	public void setShareID(long shareID) {
		_shareID = shareID;
	}

	public boolean getChecked() {
		return _checked;
	}

	public boolean isChecked() {
		return _checked;
	}

	public void setChecked(boolean checked) {
		_checked = checked;
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

	public long getIbclientId() {
		return _ibclientId;
	}

	public void setIbclientId(long ibclientId) {
		_ibclientId = ibclientId;
	}

	public boolean getRemovable_on_reboot() {
		return _removable_on_reboot;
	}

	public boolean isRemovable_on_reboot() {
		return _removable_on_reboot;
	}

	public void setRemovable_on_reboot(boolean removable_on_reboot) {
		_removable_on_reboot = removable_on_reboot;
	}

	private String _uuid;
	private long _orderIdPk;
	private long _ordersId;
	private long _groupId;
	private long _companyId;
	private long _shareID;
	private boolean _checked;
	private Date _createDate;
	private Date _modifiedDate;
	private long _ibclientId;
	private boolean _removable_on_reboot;
}