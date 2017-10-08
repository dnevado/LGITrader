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
 * This class is a wrapper for {@link MarkeShare}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MarkeShare
 * @generated
 */
@ProviderType
public class MarkeShareWrapper implements MarkeShare, ModelWrapper<MarkeShare> {
	public MarkeShareWrapper(MarkeShare markeShare) {
		_markeShare = markeShare;
	}

	@Override
	public Class<?> getModelClass() {
		return MarkeShare.class;
	}

	@Override
	public String getModelClassName() {
		return MarkeShare.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("marketshareId", getMarketshareId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("shareId", getShareId());
		attributes.put("marketId", getMarketId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long marketshareId = (Long)attributes.get("marketshareId");

		if (marketshareId != null) {
			setMarketshareId(marketshareId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long shareId = (Long)attributes.get("shareId");

		if (shareId != null) {
			setShareId(shareId);
		}

		Long marketId = (Long)attributes.get("marketId");

		if (marketId != null) {
			setMarketId(marketId);
		}
	}

	@Override
	public MarkeShare toEscapedModel() {
		return new MarkeShareWrapper(_markeShare.toEscapedModel());
	}

	@Override
	public MarkeShare toUnescapedModel() {
		return new MarkeShareWrapper(_markeShare.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _markeShare.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _markeShare.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _markeShare.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _markeShare.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MarkeShare> toCacheModel() {
		return _markeShare.toCacheModel();
	}

	@Override
	public int compareTo(MarkeShare markeShare) {
		return _markeShare.compareTo(markeShare);
	}

	@Override
	public int hashCode() {
		return _markeShare.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _markeShare.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new MarkeShareWrapper((MarkeShare)_markeShare.clone());
	}

	/**
	* Returns the uuid of this marke share.
	*
	* @return the uuid of this marke share
	*/
	@Override
	public java.lang.String getUuid() {
		return _markeShare.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _markeShare.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _markeShare.toXmlString();
	}

	/**
	* Returns the company ID of this marke share.
	*
	* @return the company ID of this marke share
	*/
	@Override
	public long getCompanyId() {
		return _markeShare.getCompanyId();
	}

	/**
	* Returns the group ID of this marke share.
	*
	* @return the group ID of this marke share
	*/
	@Override
	public long getGroupId() {
		return _markeShare.getGroupId();
	}

	/**
	* Returns the market ID of this marke share.
	*
	* @return the market ID of this marke share
	*/
	@Override
	public long getMarketId() {
		return _markeShare.getMarketId();
	}

	/**
	* Returns the marketshare ID of this marke share.
	*
	* @return the marketshare ID of this marke share
	*/
	@Override
	public long getMarketshareId() {
		return _markeShare.getMarketshareId();
	}

	/**
	* Returns the primary key of this marke share.
	*
	* @return the primary key of this marke share
	*/
	@Override
	public long getPrimaryKey() {
		return _markeShare.getPrimaryKey();
	}

	/**
	* Returns the share ID of this marke share.
	*
	* @return the share ID of this marke share
	*/
	@Override
	public long getShareId() {
		return _markeShare.getShareId();
	}

	@Override
	public void persist() {
		_markeShare.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_markeShare.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this marke share.
	*
	* @param companyId the company ID of this marke share
	*/
	@Override
	public void setCompanyId(long companyId) {
		_markeShare.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_markeShare.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_markeShare.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_markeShare.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this marke share.
	*
	* @param groupId the group ID of this marke share
	*/
	@Override
	public void setGroupId(long groupId) {
		_markeShare.setGroupId(groupId);
	}

	/**
	* Sets the market ID of this marke share.
	*
	* @param marketId the market ID of this marke share
	*/
	@Override
	public void setMarketId(long marketId) {
		_markeShare.setMarketId(marketId);
	}

	/**
	* Sets the marketshare ID of this marke share.
	*
	* @param marketshareId the marketshare ID of this marke share
	*/
	@Override
	public void setMarketshareId(long marketshareId) {
		_markeShare.setMarketshareId(marketshareId);
	}

	@Override
	public void setNew(boolean n) {
		_markeShare.setNew(n);
	}

	/**
	* Sets the primary key of this marke share.
	*
	* @param primaryKey the primary key of this marke share
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_markeShare.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_markeShare.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the share ID of this marke share.
	*
	* @param shareId the share ID of this marke share
	*/
	@Override
	public void setShareId(long shareId) {
		_markeShare.setShareId(shareId);
	}

	/**
	* Sets the uuid of this marke share.
	*
	* @param uuid the uuid of this marke share
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_markeShare.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MarkeShareWrapper)) {
			return false;
		}

		MarkeShareWrapper markeShareWrapper = (MarkeShareWrapper)obj;

		if (Objects.equals(_markeShare, markeShareWrapper._markeShare)) {
			return true;
		}

		return false;
	}

	@Override
	public MarkeShare getWrappedModel() {
		return _markeShare;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _markeShare.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _markeShare.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_markeShare.resetOriginalValues();
	}

	private final MarkeShare _markeShare;
}