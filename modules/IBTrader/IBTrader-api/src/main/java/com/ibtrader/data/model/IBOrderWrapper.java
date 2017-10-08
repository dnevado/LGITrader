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
 * This class is a wrapper for {@link IBOrder}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see IBOrder
 * @generated
 */
@ProviderType
public class IBOrderWrapper implements IBOrder, ModelWrapper<IBOrder> {
	public IBOrderWrapper(IBOrder ibOrder) {
		_ibOrder = ibOrder;
	}

	@Override
	public Class<?> getModelClass() {
		return IBOrder.class;
	}

	@Override
	public String getModelClassName() {
		return IBOrder.class.getName();
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
	public IBOrder toEscapedModel() {
		return new IBOrderWrapper(_ibOrder.toEscapedModel());
	}

	@Override
	public IBOrder toUnescapedModel() {
		return new IBOrderWrapper(_ibOrder.toUnescapedModel());
	}

	/**
	* Returns the checked of this i b order.
	*
	* @return the checked of this i b order
	*/
	@Override
	public boolean getChecked() {
		return _ibOrder.getChecked();
	}

	@Override
	public boolean isCachedModel() {
		return _ibOrder.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this i b order is checked.
	*
	* @return <code>true</code> if this i b order is checked; <code>false</code> otherwise
	*/
	@Override
	public boolean isChecked() {
		return _ibOrder.isChecked();
	}

	@Override
	public boolean isEscapedModel() {
		return _ibOrder.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ibOrder.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ibOrder.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IBOrder> toCacheModel() {
		return _ibOrder.toCacheModel();
	}

	@Override
	public int compareTo(IBOrder ibOrder) {
		return _ibOrder.compareTo(ibOrder);
	}

	@Override
	public int hashCode() {
		return _ibOrder.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ibOrder.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new IBOrderWrapper((IBOrder)_ibOrder.clone());
	}

	/**
	* Returns the uuid of this i b order.
	*
	* @return the uuid of this i b order
	*/
	@Override
	public java.lang.String getUuid() {
		return _ibOrder.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _ibOrder.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _ibOrder.toXmlString();
	}

	/**
	* Returns the company ID of this i b order.
	*
	* @return the company ID of this i b order
	*/
	@Override
	public long getCompanyId() {
		return _ibOrder.getCompanyId();
	}

	/**
	* Returns the group ID of this i b order.
	*
	* @return the group ID of this i b order
	*/
	@Override
	public long getGroupId() {
		return _ibOrder.getGroupId();
	}

	/**
	* Returns the order i d of this i b order.
	*
	* @return the order i d of this i b order
	*/
	@Override
	public long getOrderID() {
		return _ibOrder.getOrderID();
	}

	/**
	* Returns the orders ID of this i b order.
	*
	* @return the orders ID of this i b order
	*/
	@Override
	public long getOrdersId() {
		return _ibOrder.getOrdersId();
	}

	/**
	* Returns the primary key of this i b order.
	*
	* @return the primary key of this i b order
	*/
	@Override
	public long getPrimaryKey() {
		return _ibOrder.getPrimaryKey();
	}

	/**
	* Returns the share i d of this i b order.
	*
	* @return the share i d of this i b order
	*/
	@Override
	public long getShareID() {
		return _ibOrder.getShareID();
	}

	@Override
	public void persist() {
		_ibOrder.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ibOrder.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this i b order is checked.
	*
	* @param checked the checked of this i b order
	*/
	@Override
	public void setChecked(boolean checked) {
		_ibOrder.setChecked(checked);
	}

	/**
	* Sets the company ID of this i b order.
	*
	* @param companyId the company ID of this i b order
	*/
	@Override
	public void setCompanyId(long companyId) {
		_ibOrder.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ibOrder.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ibOrder.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ibOrder.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this i b order.
	*
	* @param groupId the group ID of this i b order
	*/
	@Override
	public void setGroupId(long groupId) {
		_ibOrder.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_ibOrder.setNew(n);
	}

	/**
	* Sets the order i d of this i b order.
	*
	* @param orderID the order i d of this i b order
	*/
	@Override
	public void setOrderID(long orderID) {
		_ibOrder.setOrderID(orderID);
	}

	/**
	* Sets the orders ID of this i b order.
	*
	* @param ordersId the orders ID of this i b order
	*/
	@Override
	public void setOrdersId(long ordersId) {
		_ibOrder.setOrdersId(ordersId);
	}

	/**
	* Sets the primary key of this i b order.
	*
	* @param primaryKey the primary key of this i b order
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_ibOrder.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ibOrder.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the share i d of this i b order.
	*
	* @param shareID the share i d of this i b order
	*/
	@Override
	public void setShareID(long shareID) {
		_ibOrder.setShareID(shareID);
	}

	/**
	* Sets the uuid of this i b order.
	*
	* @param uuid the uuid of this i b order
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_ibOrder.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IBOrderWrapper)) {
			return false;
		}

		IBOrderWrapper ibOrderWrapper = (IBOrderWrapper)obj;

		if (Objects.equals(_ibOrder, ibOrderWrapper._ibOrder)) {
			return true;
		}

		return false;
	}

	@Override
	public IBOrder getWrappedModel() {
		return _ibOrder;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ibOrder.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ibOrder.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ibOrder.resetOriginalValues();
	}

	private final IBOrder _ibOrder;
}