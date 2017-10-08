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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Order}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Order
 * @generated
 */
@ProviderType
public class OrderWrapper implements Order, ModelWrapper<Order> {
	public OrderWrapper(Order order) {
		_order = order;
	}

	@Override
	public Class<?> getModelClass() {
		return Order.class;
	}

	@Override
	public String getModelClassName() {
		return Order.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("ordersId", getOrdersId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("orderID", getOrderID());
		attributes.put("shareID", getShareID());
		attributes.put("checked", getChecked());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long ordersId = (Long)attributes.get("ordersId");

		if (ordersId != null) {
			setOrdersId(ordersId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long orderID = (Long)attributes.get("orderID");

		if (orderID != null) {
			setOrderID(orderID);
		}

		Long shareID = (Long)attributes.get("shareID");

		if (shareID != null) {
			setShareID(shareID);
		}

		Boolean checked = (Boolean)attributes.get("checked");

		if (checked != null) {
			setChecked(checked);
		}
	}

	@Override
	public Order toEscapedModel() {
		return new OrderWrapper(_order.toEscapedModel());
	}

	@Override
	public Order toUnescapedModel() {
		return new OrderWrapper(_order.toUnescapedModel());
	}

	/**
	* Returns the checked of this order.
	*
	* @return the checked of this order
	*/
	@Override
	public boolean getChecked() {
		return _order.getChecked();
	}

	@Override
	public boolean isCachedModel() {
		return _order.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this order is checked.
	*
	* @return <code>true</code> if this order is checked; <code>false</code> otherwise
	*/
	@Override
	public boolean isChecked() {
		return _order.isChecked();
	}

	@Override
	public boolean isEscapedModel() {
		return _order.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _order.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _order.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Order> toCacheModel() {
		return _order.toCacheModel();
	}

	@Override
	public int compareTo(Order order) {
		return _order.compareTo(order);
	}

	@Override
	public int hashCode() {
		return _order.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _order.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new OrderWrapper((Order)_order.clone());
	}

	/**
	* Returns the uuid of this order.
	*
	* @return the uuid of this order
	*/
	@Override
	public java.lang.String getUuid() {
		return _order.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _order.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _order.toXmlString();
	}

	/**
	* Returns the company ID of this order.
	*
	* @return the company ID of this order
	*/
	@Override
	public long getCompanyId() {
		return _order.getCompanyId();
	}

	/**
	* Returns the group ID of this order.
	*
	* @return the group ID of this order
	*/
	@Override
	public long getGroupId() {
		return _order.getGroupId();
	}

	/**
	* Returns the order i d of this order.
	*
	* @return the order i d of this order
	*/
	@Override
	public long getOrderID() {
		return _order.getOrderID();
	}

	/**
	* Returns the orders ID of this order.
	*
	* @return the orders ID of this order
	*/
	@Override
	public long getOrdersId() {
		return _order.getOrdersId();
	}

	/**
	* Returns the primary key of this order.
	*
	* @return the primary key of this order
	*/
	@Override
	public long getPrimaryKey() {
		return _order.getPrimaryKey();
	}

	/**
	* Returns the share i d of this order.
	*
	* @return the share i d of this order
	*/
	@Override
	public long getShareID() {
		return _order.getShareID();
	}

	@Override
	public void persist() {
		_order.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_order.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this order is checked.
	*
	* @param checked the checked of this order
	*/
	@Override
	public void setChecked(boolean checked) {
		_order.setChecked(checked);
	}

	/**
	* Sets the company ID of this order.
	*
	* @param companyId the company ID of this order
	*/
	@Override
	public void setCompanyId(long companyId) {
		_order.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_order.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_order.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_order.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this order.
	*
	* @param groupId the group ID of this order
	*/
	@Override
	public void setGroupId(long groupId) {
		_order.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_order.setNew(n);
	}

	/**
	* Sets the order i d of this order.
	*
	* @param orderID the order i d of this order
	*/
	@Override
	public void setOrderID(long orderID) {
		_order.setOrderID(orderID);
	}

	/**
	* Sets the orders ID of this order.
	*
	* @param ordersId the orders ID of this order
	*/
	@Override
	public void setOrdersId(long ordersId) {
		_order.setOrdersId(ordersId);
	}

	/**
	* Sets the primary key of this order.
	*
	* @param primaryKey the primary key of this order
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_order.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_order.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the share i d of this order.
	*
	* @param shareID the share i d of this order
	*/
	@Override
	public void setShareID(long shareID) {
		_order.setShareID(shareID);
	}

	/**
	* Sets the uuid of this order.
	*
	* @param uuid the uuid of this order
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_order.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OrderWrapper)) {
			return false;
		}

		OrderWrapper orderWrapper = (OrderWrapper)obj;

		if (Objects.equals(_order, orderWrapper._order)) {
			return true;
		}

		return false;
	}

	@Override
	public Order getWrappedModel() {
		return _order;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _order.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _order.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_order.resetOriginalValues();
	}

	private final Order _order;
}