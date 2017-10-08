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
 * This class is a wrapper for {@link Share}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Share
 * @generated
 */
@ProviderType
public class ShareWrapper implements Share, ModelWrapper<Share> {
	public ShareWrapper(Share share) {
		_share = share;
	}

	@Override
	public Class<?> getModelClass() {
		return Share.class;
	}

	@Override
	public String getModelClassName() {
		return Share.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("shareId", getShareId());
		attributes.put("name", getName());
		attributes.put("symbol", getSymbol());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("active", getActive());
		attributes.put("numbertopurchase", getNumbertopurchase());
		attributes.put("expiry_date", getExpiry_date());
		attributes.put("expiry_expression", getExpiry_expression());
		attributes.put("tick_futures", getTick_futures());
		attributes.put("multiplier", getMultiplier());
		attributes.put("last_error_data_read", getLast_error_data_read());
		attributes.put("last_error_data_trade", getLast_error_data_trade());
		attributes.put("security_type", getSecurity_type());
		attributes.put("exchange", getExchange());
		attributes.put("primary_exchange", getPrimary_exchange());
		attributes.put("date_contract_verified", getDate_contract_verified());
		attributes.put("marketId", getMarketId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long shareId = (Long)attributes.get("shareId");

		if (shareId != null) {
			setShareId(shareId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String symbol = (String)attributes.get("symbol");

		if (symbol != null) {
			setSymbol(symbol);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		Long numbertopurchase = (Long)attributes.get("numbertopurchase");

		if (numbertopurchase != null) {
			setNumbertopurchase(numbertopurchase);
		}

		Date expiry_date = (Date)attributes.get("expiry_date");

		if (expiry_date != null) {
			setExpiry_date(expiry_date);
		}

		String expiry_expression = (String)attributes.get("expiry_expression");

		if (expiry_expression != null) {
			setExpiry_expression(expiry_expression);
		}

		Double tick_futures = (Double)attributes.get("tick_futures");

		if (tick_futures != null) {
			setTick_futures(tick_futures);
		}

		Long multiplier = (Long)attributes.get("multiplier");

		if (multiplier != null) {
			setMultiplier(multiplier);
		}

		String last_error_data_read = (String)attributes.get(
				"last_error_data_read");

		if (last_error_data_read != null) {
			setLast_error_data_read(last_error_data_read);
		}

		String last_error_data_trade = (String)attributes.get(
				"last_error_data_trade");

		if (last_error_data_trade != null) {
			setLast_error_data_trade(last_error_data_trade);
		}

		String security_type = (String)attributes.get("security_type");

		if (security_type != null) {
			setSecurity_type(security_type);
		}

		String exchange = (String)attributes.get("exchange");

		if (exchange != null) {
			setExchange(exchange);
		}

		String primary_exchange = (String)attributes.get("primary_exchange");

		if (primary_exchange != null) {
			setPrimary_exchange(primary_exchange);
		}

		Date date_contract_verified = (Date)attributes.get(
				"date_contract_verified");

		if (date_contract_verified != null) {
			setDate_contract_verified(date_contract_verified);
		}

		Long marketId = (Long)attributes.get("marketId");

		if (marketId != null) {
			setMarketId(marketId);
		}
	}

	@Override
	public Share toEscapedModel() {
		return new ShareWrapper(_share.toEscapedModel());
	}

	@Override
	public Share toUnescapedModel() {
		return new ShareWrapper(_share.toUnescapedModel());
	}

	/**
	* Returns the active of this share.
	*
	* @return the active of this share
	*/
	@Override
	public boolean getActive() {
		return _share.getActive();
	}

	/**
	* Returns <code>true</code> if this share is active.
	*
	* @return <code>true</code> if this share is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _share.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _share.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _share.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _share.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _share.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Share> toCacheModel() {
		return _share.toCacheModel();
	}

	/**
	* Returns the tick_futures of this share.
	*
	* @return the tick_futures of this share
	*/
	@Override
	public double getTick_futures() {
		return _share.getTick_futures();
	}

	@Override
	public int compareTo(Share share) {
		return _share.compareTo(share);
	}

	@Override
	public int hashCode() {
		return _share.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _share.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ShareWrapper((Share)_share.clone());
	}

	/**
	* Returns the exchange of this share.
	*
	* @return the exchange of this share
	*/
	@Override
	public java.lang.String getExchange() {
		return _share.getExchange();
	}

	/**
	* Returns the expiry_expression of this share.
	*
	* @return the expiry_expression of this share
	*/
	@Override
	public java.lang.String getExpiry_expression() {
		return _share.getExpiry_expression();
	}

	/**
	* Returns the last_error_data_read of this share.
	*
	* @return the last_error_data_read of this share
	*/
	@Override
	public java.lang.String getLast_error_data_read() {
		return _share.getLast_error_data_read();
	}

	/**
	* Returns the last_error_data_trade of this share.
	*
	* @return the last_error_data_trade of this share
	*/
	@Override
	public java.lang.String getLast_error_data_trade() {
		return _share.getLast_error_data_trade();
	}

	/**
	* Returns the name of this share.
	*
	* @return the name of this share
	*/
	@Override
	public java.lang.String getName() {
		return _share.getName();
	}

	/**
	* Returns the primary_exchange of this share.
	*
	* @return the primary_exchange of this share
	*/
	@Override
	public java.lang.String getPrimary_exchange() {
		return _share.getPrimary_exchange();
	}

	/**
	* Returns the security_type of this share.
	*
	* @return the security_type of this share
	*/
	@Override
	public java.lang.String getSecurity_type() {
		return _share.getSecurity_type();
	}

	/**
	* Returns the symbol of this share.
	*
	* @return the symbol of this share
	*/
	@Override
	public java.lang.String getSymbol() {
		return _share.getSymbol();
	}

	/**
	* Returns the uuid of this share.
	*
	* @return the uuid of this share
	*/
	@Override
	public java.lang.String getUuid() {
		return _share.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _share.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _share.toXmlString();
	}

	/**
	* Returns the create date of this share.
	*
	* @return the create date of this share
	*/
	@Override
	public Date getCreateDate() {
		return _share.getCreateDate();
	}

	/**
	* Returns the date_contract_verified of this share.
	*
	* @return the date_contract_verified of this share
	*/
	@Override
	public Date getDate_contract_verified() {
		return _share.getDate_contract_verified();
	}

	/**
	* Returns the expiry_date of this share.
	*
	* @return the expiry_date of this share
	*/
	@Override
	public Date getExpiry_date() {
		return _share.getExpiry_date();
	}

	/**
	* Returns the modified date of this share.
	*
	* @return the modified date of this share
	*/
	@Override
	public Date getModifiedDate() {
		return _share.getModifiedDate();
	}

	/**
	* Returns the company ID of this share.
	*
	* @return the company ID of this share
	*/
	@Override
	public long getCompanyId() {
		return _share.getCompanyId();
	}

	/**
	* Returns the group ID of this share.
	*
	* @return the group ID of this share
	*/
	@Override
	public long getGroupId() {
		return _share.getGroupId();
	}

	/**
	* Returns the market ID of this share.
	*
	* @return the market ID of this share
	*/
	@Override
	public long getMarketId() {
		return _share.getMarketId();
	}

	/**
	* Returns the multiplier of this share.
	*
	* @return the multiplier of this share
	*/
	@Override
	public long getMultiplier() {
		return _share.getMultiplier();
	}

	/**
	* Returns the numbertopurchase of this share.
	*
	* @return the numbertopurchase of this share
	*/
	@Override
	public long getNumbertopurchase() {
		return _share.getNumbertopurchase();
	}

	/**
	* Returns the primary key of this share.
	*
	* @return the primary key of this share
	*/
	@Override
	public long getPrimaryKey() {
		return _share.getPrimaryKey();
	}

	/**
	* Returns the share ID of this share.
	*
	* @return the share ID of this share
	*/
	@Override
	public long getShareId() {
		return _share.getShareId();
	}

	@Override
	public void persist() {
		_share.persist();
	}

	/**
	* Sets whether this share is active.
	*
	* @param active the active of this share
	*/
	@Override
	public void setActive(boolean active) {
		_share.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_share.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this share.
	*
	* @param companyId the company ID of this share
	*/
	@Override
	public void setCompanyId(long companyId) {
		_share.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this share.
	*
	* @param createDate the create date of this share
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_share.setCreateDate(createDate);
	}

	/**
	* Sets the date_contract_verified of this share.
	*
	* @param date_contract_verified the date_contract_verified of this share
	*/
	@Override
	public void setDate_contract_verified(Date date_contract_verified) {
		_share.setDate_contract_verified(date_contract_verified);
	}

	/**
	* Sets the exchange of this share.
	*
	* @param exchange the exchange of this share
	*/
	@Override
	public void setExchange(java.lang.String exchange) {
		_share.setExchange(exchange);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_share.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_share.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_share.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expiry_date of this share.
	*
	* @param expiry_date the expiry_date of this share
	*/
	@Override
	public void setExpiry_date(Date expiry_date) {
		_share.setExpiry_date(expiry_date);
	}

	/**
	* Sets the expiry_expression of this share.
	*
	* @param expiry_expression the expiry_expression of this share
	*/
	@Override
	public void setExpiry_expression(java.lang.String expiry_expression) {
		_share.setExpiry_expression(expiry_expression);
	}

	/**
	* Sets the group ID of this share.
	*
	* @param groupId the group ID of this share
	*/
	@Override
	public void setGroupId(long groupId) {
		_share.setGroupId(groupId);
	}

	/**
	* Sets the last_error_data_read of this share.
	*
	* @param last_error_data_read the last_error_data_read of this share
	*/
	@Override
	public void setLast_error_data_read(java.lang.String last_error_data_read) {
		_share.setLast_error_data_read(last_error_data_read);
	}

	/**
	* Sets the last_error_data_trade of this share.
	*
	* @param last_error_data_trade the last_error_data_trade of this share
	*/
	@Override
	public void setLast_error_data_trade(java.lang.String last_error_data_trade) {
		_share.setLast_error_data_trade(last_error_data_trade);
	}

	/**
	* Sets the market ID of this share.
	*
	* @param marketId the market ID of this share
	*/
	@Override
	public void setMarketId(long marketId) {
		_share.setMarketId(marketId);
	}

	/**
	* Sets the modified date of this share.
	*
	* @param modifiedDate the modified date of this share
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_share.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the multiplier of this share.
	*
	* @param multiplier the multiplier of this share
	*/
	@Override
	public void setMultiplier(long multiplier) {
		_share.setMultiplier(multiplier);
	}

	/**
	* Sets the name of this share.
	*
	* @param name the name of this share
	*/
	@Override
	public void setName(java.lang.String name) {
		_share.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_share.setNew(n);
	}

	/**
	* Sets the numbertopurchase of this share.
	*
	* @param numbertopurchase the numbertopurchase of this share
	*/
	@Override
	public void setNumbertopurchase(long numbertopurchase) {
		_share.setNumbertopurchase(numbertopurchase);
	}

	/**
	* Sets the primary key of this share.
	*
	* @param primaryKey the primary key of this share
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_share.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_share.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the primary_exchange of this share.
	*
	* @param primary_exchange the primary_exchange of this share
	*/
	@Override
	public void setPrimary_exchange(java.lang.String primary_exchange) {
		_share.setPrimary_exchange(primary_exchange);
	}

	/**
	* Sets the security_type of this share.
	*
	* @param security_type the security_type of this share
	*/
	@Override
	public void setSecurity_type(java.lang.String security_type) {
		_share.setSecurity_type(security_type);
	}

	/**
	* Sets the share ID of this share.
	*
	* @param shareId the share ID of this share
	*/
	@Override
	public void setShareId(long shareId) {
		_share.setShareId(shareId);
	}

	/**
	* Sets the symbol of this share.
	*
	* @param symbol the symbol of this share
	*/
	@Override
	public void setSymbol(java.lang.String symbol) {
		_share.setSymbol(symbol);
	}

	/**
	* Sets the tick_futures of this share.
	*
	* @param tick_futures the tick_futures of this share
	*/
	@Override
	public void setTick_futures(double tick_futures) {
		_share.setTick_futures(tick_futures);
	}

	/**
	* Sets the uuid of this share.
	*
	* @param uuid the uuid of this share
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_share.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ShareWrapper)) {
			return false;
		}

		ShareWrapper shareWrapper = (ShareWrapper)obj;

		if (Objects.equals(_share, shareWrapper._share)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _share.getStagedModelType();
	}

	@Override
	public Share getWrappedModel() {
		return _share;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _share.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _share.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_share.resetOriginalValues();
	}

	private final Share _share;
}