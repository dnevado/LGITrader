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

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Market}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Market
 * @generated
 */
@ProviderType
public class MarketWrapper implements Market, ModelWrapper<Market> {
	public MarketWrapper(Market market) {
		_market = market;
	}

	@Override
	public Class<?> getModelClass() {
		return Market.class;
	}

	@Override
	public String getModelClassName() {
		return Market.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("marketId", getMarketId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("active", getActive());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("start_hour", getStart_hour());
		attributes.put("end_hour", getEnd_hour());
		attributes.put("identifier", getIdentifier());
		attributes.put("currency", getCurrency());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long marketId = (Long)attributes.get("marketId");

		if (marketId != null) {
			setMarketId(marketId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String start_hour = (String)attributes.get("start_hour");

		if (start_hour != null) {
			setStart_hour(start_hour);
		}

		String end_hour = (String)attributes.get("end_hour");

		if (end_hour != null) {
			setEnd_hour(end_hour);
		}

		String identifier = (String)attributes.get("identifier");

		if (identifier != null) {
			setIdentifier(identifier);
		}

		String currency = (String)attributes.get("currency");

		if (currency != null) {
			setCurrency(currency);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	@Override
	public Market toEscapedModel() {
		return new MarketWrapper(_market.toEscapedModel());
	}

	@Override
	public Market toUnescapedModel() {
		return new MarketWrapper(_market.toUnescapedModel());
	}

	/**
	* Returns the active of this market.
	*
	* @return the active of this market
	*/
	@Override
	public boolean getActive() {
		return _market.getActive();
	}

	/**
	* Returns <code>true</code> if this market is active.
	*
	* @return <code>true</code> if this market is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _market.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _market.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _market.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _market.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _market.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Market> toCacheModel() {
		return _market.toCacheModel();
	}

	@Override
	public int compareTo(Market market) {
		return _market.compareTo(market);
	}

	@Override
	public int hashCode() {
		return _market.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _market.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new MarketWrapper((Market)_market.clone());
	}

	/**
	* Returns the currency of this market.
	*
	* @return the currency of this market
	*/
	@Override
	public java.lang.String getCurrency() {
		return _market.getCurrency();
	}

	/**
	* Returns the description of this market.
	*
	* @return the description of this market
	*/
	@Override
	public java.lang.String getDescription() {
		return _market.getDescription();
	}

	/**
	* Returns the end_hour of this market.
	*
	* @return the end_hour of this market
	*/
	@Override
	public java.lang.String getEnd_hour() {
		return _market.getEnd_hour();
	}

	/**
	* Returns the identifier of this market.
	*
	* @return the identifier of this market
	*/
	@Override
	public java.lang.String getIdentifier() {
		return _market.getIdentifier();
	}

	/**
	* Returns the name of this market.
	*
	* @return the name of this market
	*/
	@Override
	public java.lang.String getName() {
		return _market.getName();
	}

	/**
	* Returns the start_hour of this market.
	*
	* @return the start_hour of this market
	*/
	@Override
	public java.lang.String getStart_hour() {
		return _market.getStart_hour();
	}

	/**
	* Returns the uuid of this market.
	*
	* @return the uuid of this market
	*/
	@Override
	public java.lang.String getUuid() {
		return _market.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _market.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _market.toXmlString();
	}

	/**
	* Returns the create date of this market.
	*
	* @return the create date of this market
	*/
	@Override
	public Date getCreateDate() {
		return _market.getCreateDate();
	}

	/**
	* Returns the modified date of this market.
	*
	* @return the modified date of this market
	*/
	@Override
	public Date getModifiedDate() {
		return _market.getModifiedDate();
	}

	/**
	* Returns the company ID of this market.
	*
	* @return the company ID of this market
	*/
	@Override
	public long getCompanyId() {
		return _market.getCompanyId();
	}

	/**
	* Returns the group ID of this market.
	*
	* @return the group ID of this market
	*/
	@Override
	public long getGroupId() {
		return _market.getGroupId();
	}

	/**
	* Returns the market ID of this market.
	*
	* @return the market ID of this market
	*/
	@Override
	public long getMarketId() {
		return _market.getMarketId();
	}

	/**
	* Returns the primary key of this market.
	*
	* @return the primary key of this market
	*/
	@Override
	public long getPrimaryKey() {
		return _market.getPrimaryKey();
	}

	@Override
	public void persist() {
		_market.persist();
	}

	/**
	* Sets whether this market is active.
	*
	* @param active the active of this market
	*/
	@Override
	public void setActive(boolean active) {
		_market.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_market.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this market.
	*
	* @param companyId the company ID of this market
	*/
	@Override
	public void setCompanyId(long companyId) {
		_market.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this market.
	*
	* @param createDate the create date of this market
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_market.setCreateDate(createDate);
	}

	/**
	* Sets the currency of this market.
	*
	* @param currency the currency of this market
	*/
	@Override
	public void setCurrency(java.lang.String currency) {
		_market.setCurrency(currency);
	}

	/**
	* Sets the description of this market.
	*
	* @param description the description of this market
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_market.setDescription(description);
	}

	/**
	* Sets the end_hour of this market.
	*
	* @param end_hour the end_hour of this market
	*/
	@Override
	public void setEnd_hour(java.lang.String end_hour) {
		_market.setEnd_hour(end_hour);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_market.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_market.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_market.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this market.
	*
	* @param groupId the group ID of this market
	*/
	@Override
	public void setGroupId(long groupId) {
		_market.setGroupId(groupId);
	}

	/**
	* Sets the identifier of this market.
	*
	* @param identifier the identifier of this market
	*/
	@Override
	public void setIdentifier(java.lang.String identifier) {
		_market.setIdentifier(identifier);
	}

	/**
	* Sets the market ID of this market.
	*
	* @param marketId the market ID of this market
	*/
	@Override
	public void setMarketId(long marketId) {
		_market.setMarketId(marketId);
	}

	/**
	* Sets the modified date of this market.
	*
	* @param modifiedDate the modified date of this market
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_market.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this market.
	*
	* @param name the name of this market
	*/
	@Override
	public void setName(java.lang.String name) {
		_market.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_market.setNew(n);
	}

	/**
	* Sets the primary key of this market.
	*
	* @param primaryKey the primary key of this market
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_market.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_market.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start_hour of this market.
	*
	* @param start_hour the start_hour of this market
	*/
	@Override
	public void setStart_hour(java.lang.String start_hour) {
		_market.setStart_hour(start_hour);
	}

	/**
	* Sets the uuid of this market.
	*
	* @param uuid the uuid of this market
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_market.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MarketWrapper)) {
			return false;
		}

		MarketWrapper marketWrapper = (MarketWrapper)obj;

		if (Objects.equals(_market, marketWrapper._market)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _market.getStagedModelType();
	}

	@Override
	public Market getWrappedModel() {
		return _market;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _market.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _market.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_market.resetOriginalValues();
	}

	private final Market _market;
}