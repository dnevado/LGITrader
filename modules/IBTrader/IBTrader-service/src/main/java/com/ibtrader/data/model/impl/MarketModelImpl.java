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

package com.ibtrader.data.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.MarketModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Market service. Represents a row in the &quot;ibtrader_Market&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link MarketModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MarketImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MarketImpl
 * @see Market
 * @see MarketModel
 * @generated
 */
@ProviderType
public class MarketModelImpl extends BaseModelImpl<Market>
	implements MarketModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a market model instance should use the {@link Market} interface instead.
	 */
	public static final String TABLE_NAME = "ibtrader_Market";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "marketId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "active_", Types.BOOLEAN },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "start_hour", Types.VARCHAR },
			{ "end_hour", Types.VARCHAR },
			{ "identifier", Types.VARCHAR },
			{ "currency_", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("marketId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("start_hour", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("end_hour", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("identifier", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("currency_", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table ibtrader_Market (uuid_ VARCHAR(75) null,marketId LONG not null primary key,groupId LONG,companyId LONG,active_ BOOLEAN,createDate DATE null,modifiedDate DATE null,start_hour VARCHAR(75) null,end_hour VARCHAR(75) null,identifier VARCHAR(75) null,currency_ VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table ibtrader_Market";
	public static final String ORDER_BY_JPQL = " ORDER BY market.marketId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ibtrader_Market.marketId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.ibtrader.data.model.Market"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.ibtrader.data.model.Market"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.ibtrader.data.model.Market"),
			true);
	public static final long ACTIVE_COLUMN_BITMASK = 1L;
	public static final long COMPANYID_COLUMN_BITMASK = 2L;
	public static final long END_HOUR_COLUMN_BITMASK = 4L;
	public static final long GROUPID_COLUMN_BITMASK = 8L;
	public static final long START_HOUR_COLUMN_BITMASK = 16L;
	public static final long UUID_COLUMN_BITMASK = 32L;
	public static final long MARKETID_COLUMN_BITMASK = 64L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.ibtrader.data.service.util.ServiceProps.get(
				"lock.expiration.time.com.ibtrader.data.model.Market"));

	public MarketModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _marketId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMarketId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _marketId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getMarketId() {
		return _marketId;
	}

	@Override
	public void setMarketId(long marketId) {
		_marketId = marketId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public boolean getActive() {
		return _active;
	}

	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_columnBitmask |= ACTIVE_COLUMN_BITMASK;

		if (!_setOriginalActive) {
			_setOriginalActive = true;

			_originalActive = _active;
		}

		_active = active;
	}

	public boolean getOriginalActive() {
		return _originalActive;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public String getStart_hour() {
		if (_start_hour == null) {
			return StringPool.BLANK;
		}
		else {
			return _start_hour;
		}
	}

	@Override
	public void setStart_hour(String start_hour) {
		_columnBitmask |= START_HOUR_COLUMN_BITMASK;

		if (_originalStart_hour == null) {
			_originalStart_hour = _start_hour;
		}

		_start_hour = start_hour;
	}

	public String getOriginalStart_hour() {
		return GetterUtil.getString(_originalStart_hour);
	}

	@Override
	public String getEnd_hour() {
		if (_end_hour == null) {
			return StringPool.BLANK;
		}
		else {
			return _end_hour;
		}
	}

	@Override
	public void setEnd_hour(String end_hour) {
		_columnBitmask |= END_HOUR_COLUMN_BITMASK;

		if (_originalEnd_hour == null) {
			_originalEnd_hour = _end_hour;
		}

		_end_hour = end_hour;
	}

	public String getOriginalEnd_hour() {
		return GetterUtil.getString(_originalEnd_hour);
	}

	@Override
	public String getIdentifier() {
		if (_identifier == null) {
			return StringPool.BLANK;
		}
		else {
			return _identifier;
		}
	}

	@Override
	public void setIdentifier(String identifier) {
		_identifier = identifier;
	}

	@Override
	public String getCurrency() {
		if (_currency == null) {
			return StringPool.BLANK;
		}
		else {
			return _currency;
		}
	}

	@Override
	public void setCurrency(String currency) {
		_currency = currency;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Market.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Market.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Market toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Market)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		MarketImpl marketImpl = new MarketImpl();

		marketImpl.setUuid(getUuid());
		marketImpl.setMarketId(getMarketId());
		marketImpl.setGroupId(getGroupId());
		marketImpl.setCompanyId(getCompanyId());
		marketImpl.setActive(getActive());
		marketImpl.setCreateDate(getCreateDate());
		marketImpl.setModifiedDate(getModifiedDate());
		marketImpl.setStart_hour(getStart_hour());
		marketImpl.setEnd_hour(getEnd_hour());
		marketImpl.setIdentifier(getIdentifier());
		marketImpl.setCurrency(getCurrency());

		marketImpl.resetOriginalValues();

		return marketImpl;
	}

	@Override
	public int compareTo(Market market) {
		long primaryKey = market.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Market)) {
			return false;
		}

		Market market = (Market)obj;

		long primaryKey = market.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		MarketModelImpl marketModelImpl = this;

		marketModelImpl._originalUuid = marketModelImpl._uuid;

		marketModelImpl._originalGroupId = marketModelImpl._groupId;

		marketModelImpl._setOriginalGroupId = false;

		marketModelImpl._originalCompanyId = marketModelImpl._companyId;

		marketModelImpl._setOriginalCompanyId = false;

		marketModelImpl._originalActive = marketModelImpl._active;

		marketModelImpl._setOriginalActive = false;

		marketModelImpl._setModifiedDate = false;

		marketModelImpl._originalStart_hour = marketModelImpl._start_hour;

		marketModelImpl._originalEnd_hour = marketModelImpl._end_hour;

		marketModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Market> toCacheModel() {
		MarketCacheModel marketCacheModel = new MarketCacheModel();

		marketCacheModel.uuid = getUuid();

		String uuid = marketCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			marketCacheModel.uuid = null;
		}

		marketCacheModel.marketId = getMarketId();

		marketCacheModel.groupId = getGroupId();

		marketCacheModel.companyId = getCompanyId();

		marketCacheModel.active = getActive();

		Date createDate = getCreateDate();

		if (createDate != null) {
			marketCacheModel.createDate = createDate.getTime();
		}
		else {
			marketCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			marketCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			marketCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		marketCacheModel.start_hour = getStart_hour();

		String start_hour = marketCacheModel.start_hour;

		if ((start_hour != null) && (start_hour.length() == 0)) {
			marketCacheModel.start_hour = null;
		}

		marketCacheModel.end_hour = getEnd_hour();

		String end_hour = marketCacheModel.end_hour;

		if ((end_hour != null) && (end_hour.length() == 0)) {
			marketCacheModel.end_hour = null;
		}

		marketCacheModel.identifier = getIdentifier();

		String identifier = marketCacheModel.identifier;

		if ((identifier != null) && (identifier.length() == 0)) {
			marketCacheModel.identifier = null;
		}

		marketCacheModel.currency = getCurrency();

		String currency = marketCacheModel.currency;

		if ((currency != null) && (currency.length() == 0)) {
			marketCacheModel.currency = null;
		}

		return marketCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", marketId=");
		sb.append(getMarketId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", active=");
		sb.append(getActive());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", start_hour=");
		sb.append(getStart_hour());
		sb.append(", end_hour=");
		sb.append(getEnd_hour());
		sb.append(", identifier=");
		sb.append(getIdentifier());
		sb.append(", currency=");
		sb.append(getCurrency());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.ibtrader.data.model.Market");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>marketId</column-name><column-value><![CDATA[");
		sb.append(getMarketId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>active</column-name><column-value><![CDATA[");
		sb.append(getActive());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>start_hour</column-name><column-value><![CDATA[");
		sb.append(getStart_hour());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>end_hour</column-name><column-value><![CDATA[");
		sb.append(getEnd_hour());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>identifier</column-name><column-value><![CDATA[");
		sb.append(getIdentifier());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currency</column-name><column-value><![CDATA[");
		sb.append(getCurrency());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Market.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Market.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _marketId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private boolean _active;
	private boolean _originalActive;
	private boolean _setOriginalActive;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _start_hour;
	private String _originalStart_hour;
	private String _end_hour;
	private String _originalEnd_hour;
	private String _identifier;
	private String _currency;
	private long _columnBitmask;
	private Market _escapedModel;
}