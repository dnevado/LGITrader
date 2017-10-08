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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Market service. Represents a row in the &quot;ibtrader_Market&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ibtrader.data.model.impl.MarketModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ibtrader.data.model.impl.MarketImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Market
 * @see com.ibtrader.data.model.impl.MarketImpl
 * @see com.ibtrader.data.model.impl.MarketModelImpl
 * @generated
 */
@ProviderType
public interface MarketModel extends BaseModel<Market>, ShardedModel, StagedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a market model instance should use the {@link Market} interface instead.
	 */

	/**
	 * Returns the primary key of this market.
	 *
	 * @return the primary key of this market
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this market.
	 *
	 * @param primaryKey the primary key of this market
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this market.
	 *
	 * @return the uuid of this market
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this market.
	 *
	 * @param uuid the uuid of this market
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the market ID of this market.
	 *
	 * @return the market ID of this market
	 */
	public long getMarketId();

	/**
	 * Sets the market ID of this market.
	 *
	 * @param marketId the market ID of this market
	 */
	public void setMarketId(long marketId);

	/**
	 * Returns the group ID of this market.
	 *
	 * @return the group ID of this market
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this market.
	 *
	 * @param groupId the group ID of this market
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this market.
	 *
	 * @return the company ID of this market
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this market.
	 *
	 * @param companyId the company ID of this market
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the active of this market.
	 *
	 * @return the active of this market
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this market is active.
	 *
	 * @return <code>true</code> if this market is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this market is active.
	 *
	 * @param active the active of this market
	 */
	public void setActive(boolean active);

	/**
	 * Returns the create date of this market.
	 *
	 * @return the create date of this market
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this market.
	 *
	 * @param createDate the create date of this market
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this market.
	 *
	 * @return the modified date of this market
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this market.
	 *
	 * @param modifiedDate the modified date of this market
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the start_hour of this market.
	 *
	 * @return the start_hour of this market
	 */
	@AutoEscape
	public String getStart_hour();

	/**
	 * Sets the start_hour of this market.
	 *
	 * @param start_hour the start_hour of this market
	 */
	public void setStart_hour(String start_hour);

	/**
	 * Returns the end_hour of this market.
	 *
	 * @return the end_hour of this market
	 */
	@AutoEscape
	public String getEnd_hour();

	/**
	 * Sets the end_hour of this market.
	 *
	 * @param end_hour the end_hour of this market
	 */
	public void setEnd_hour(String end_hour);

	/**
	 * Returns the identifier of this market.
	 *
	 * @return the identifier of this market
	 */
	@AutoEscape
	public String getIdentifier();

	/**
	 * Sets the identifier of this market.
	 *
	 * @param identifier the identifier of this market
	 */
	public void setIdentifier(String identifier);

	/**
	 * Returns the currency of this market.
	 *
	 * @return the currency of this market
	 */
	@AutoEscape
	public String getCurrency();

	/**
	 * Sets the currency of this market.
	 *
	 * @param currency the currency of this market
	 */
	public void setCurrency(String currency);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(Market market);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Market> toCacheModel();

	@Override
	public Market toEscapedModel();

	@Override
	public Market toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}