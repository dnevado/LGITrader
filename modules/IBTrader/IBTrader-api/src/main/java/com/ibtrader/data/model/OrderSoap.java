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
public class OrderSoap implements Serializable {
	public static OrderSoap toSoapModel(Order model) {
		OrderSoap soapModel = new OrderSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setOrdersId(model.getOrdersId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setOrderID(model.getOrderID());
		soapModel.setShareID(model.getShareID());
		soapModel.setChecked(model.getChecked());

		return soapModel;
	}

	public static OrderSoap[] toSoapModels(Order[] models) {
		OrderSoap[] soapModels = new OrderSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OrderSoap[][] toSoapModels(Order[][] models) {
		OrderSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OrderSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OrderSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OrderSoap[] toSoapModels(List<Order> models) {
		List<OrderSoap> soapModels = new ArrayList<OrderSoap>(models.size());

		for (Order model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OrderSoap[soapModels.size()]);
	}

	public OrderSoap() {
	}

	public long getPrimaryKey() {
		return _ordersId;
	}

	public void setPrimaryKey(long pk) {
		setOrdersId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
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

	public long getOrderID() {
		return _orderID;
	}

	public void setOrderID(long orderID) {
		_orderID = orderID;
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

	private String _uuid;
	private long _ordersId;
	private long _groupId;
	private long _companyId;
	private long _orderID;
	private long _shareID;
	private boolean _checked;
}