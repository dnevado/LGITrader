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

import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.RealtimeModel;

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
 * The base model implementation for the Realtime service. Represents a row in the &quot;ibtrader_Realtime&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link RealtimeModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RealtimeImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RealtimeImpl
 * @see Realtime
 * @see RealtimeModel
 * @generated
 */
@ProviderType
public class RealtimeModelImpl extends BaseModelImpl<Realtime>
	implements RealtimeModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a realtime model instance should use the {@link Realtime} interface instead.
	 */
	public static final String TABLE_NAME = "ibtrader_Realtime";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "realtimeId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "shareId", Types.BIGINT },
			{ "value", Types.DOUBLE },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "max_value", Types.DOUBLE },
			{ "min_value", Types.BOOLEAN },
			{ "volume", Types.INTEGER },
			{ "avg_volume", Types.INTEGER }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("realtimeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("shareId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("value", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("max_value", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("min_value", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("volume", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("avg_volume", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE = "create table ibtrader_Realtime (uuid_ VARCHAR(75) null,realtimeId LONG not null primary key,groupId LONG,companyId LONG,shareId LONG,value DOUBLE,createDate DATE null,modifiedDate DATE null,max_value DOUBLE,min_value BOOLEAN,volume INTEGER,avg_volume INTEGER)";
	public static final String TABLE_SQL_DROP = "drop table ibtrader_Realtime";
	public static final String ORDER_BY_JPQL = " ORDER BY realtime.shareId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ibtrader_Realtime.shareId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.ibtrader.data.model.Realtime"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.ibtrader.data.model.Realtime"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.ibtrader.data.model.Realtime"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long SHAREID_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.ibtrader.data.service.util.ServiceProps.get(
				"lock.expiration.time.com.ibtrader.data.model.Realtime"));

	public RealtimeModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _realtimeId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRealtimeId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _realtimeId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Realtime.class;
	}

	@Override
	public String getModelClassName() {
		return Realtime.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("realtimeId", getRealtimeId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("shareId", getShareId());
		attributes.put("value", getValue());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("max_value", getMax_value());
		attributes.put("min_value", getMin_value());
		attributes.put("volume", getVolume());
		attributes.put("avg_volume", getAvg_volume());

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

		Long realtimeId = (Long)attributes.get("realtimeId");

		if (realtimeId != null) {
			setRealtimeId(realtimeId);
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

		Double value = (Double)attributes.get("value");

		if (value != null) {
			setValue(value);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Double max_value = (Double)attributes.get("max_value");

		if (max_value != null) {
			setMax_value(max_value);
		}

		Boolean min_value = (Boolean)attributes.get("min_value");

		if (min_value != null) {
			setMin_value(min_value);
		}

		Integer volume = (Integer)attributes.get("volume");

		if (volume != null) {
			setVolume(volume);
		}

		Integer avg_volume = (Integer)attributes.get("avg_volume");

		if (avg_volume != null) {
			setAvg_volume(avg_volume);
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
	public long getRealtimeId() {
		return _realtimeId;
	}

	@Override
	public void setRealtimeId(long realtimeId) {
		_realtimeId = realtimeId;
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
	public long getShareId() {
		return _shareId;
	}

	@Override
	public void setShareId(long shareId) {
		_columnBitmask = -1L;

		if (!_setOriginalShareId) {
			_setOriginalShareId = true;

			_originalShareId = _shareId;
		}

		_shareId = shareId;
	}

	public long getOriginalShareId() {
		return _originalShareId;
	}

	@Override
	public double getValue() {
		return _value;
	}

	@Override
	public void setValue(double value) {
		_value = value;
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
	public double getMax_value() {
		return _max_value;
	}

	@Override
	public void setMax_value(double max_value) {
		_max_value = max_value;
	}

	@Override
	public boolean getMin_value() {
		return _min_value;
	}

	@Override
	public boolean isMin_value() {
		return _min_value;
	}

	@Override
	public void setMin_value(boolean min_value) {
		_min_value = min_value;
	}

	@Override
	public int getVolume() {
		return _volume;
	}

	@Override
	public void setVolume(int volume) {
		_volume = volume;
	}

	@Override
	public int getAvg_volume() {
		return _avg_volume;
	}

	@Override
	public void setAvg_volume(int avg_volume) {
		_avg_volume = avg_volume;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Realtime.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Realtime.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Realtime toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Realtime)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		RealtimeImpl realtimeImpl = new RealtimeImpl();

		realtimeImpl.setUuid(getUuid());
		realtimeImpl.setRealtimeId(getRealtimeId());
		realtimeImpl.setGroupId(getGroupId());
		realtimeImpl.setCompanyId(getCompanyId());
		realtimeImpl.setShareId(getShareId());
		realtimeImpl.setValue(getValue());
		realtimeImpl.setCreateDate(getCreateDate());
		realtimeImpl.setModifiedDate(getModifiedDate());
		realtimeImpl.setMax_value(getMax_value());
		realtimeImpl.setMin_value(getMin_value());
		realtimeImpl.setVolume(getVolume());
		realtimeImpl.setAvg_volume(getAvg_volume());

		realtimeImpl.resetOriginalValues();

		return realtimeImpl;
	}

	@Override
	public int compareTo(Realtime realtime) {
		int value = 0;

		if (getShareId() < realtime.getShareId()) {
			value = -1;
		}
		else if (getShareId() > realtime.getShareId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Realtime)) {
			return false;
		}

		Realtime realtime = (Realtime)obj;

		long primaryKey = realtime.getPrimaryKey();

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
		RealtimeModelImpl realtimeModelImpl = this;

		realtimeModelImpl._originalUuid = realtimeModelImpl._uuid;

		realtimeModelImpl._originalGroupId = realtimeModelImpl._groupId;

		realtimeModelImpl._setOriginalGroupId = false;

		realtimeModelImpl._originalCompanyId = realtimeModelImpl._companyId;

		realtimeModelImpl._setOriginalCompanyId = false;

		realtimeModelImpl._originalShareId = realtimeModelImpl._shareId;

		realtimeModelImpl._setOriginalShareId = false;

		realtimeModelImpl._setModifiedDate = false;

		realtimeModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Realtime> toCacheModel() {
		RealtimeCacheModel realtimeCacheModel = new RealtimeCacheModel();

		realtimeCacheModel.uuid = getUuid();

		String uuid = realtimeCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			realtimeCacheModel.uuid = null;
		}

		realtimeCacheModel.realtimeId = getRealtimeId();

		realtimeCacheModel.groupId = getGroupId();

		realtimeCacheModel.companyId = getCompanyId();

		realtimeCacheModel.shareId = getShareId();

		realtimeCacheModel.value = getValue();

		Date createDate = getCreateDate();

		if (createDate != null) {
			realtimeCacheModel.createDate = createDate.getTime();
		}
		else {
			realtimeCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			realtimeCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			realtimeCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		realtimeCacheModel.max_value = getMax_value();

		realtimeCacheModel.min_value = getMin_value();

		realtimeCacheModel.volume = getVolume();

		realtimeCacheModel.avg_volume = getAvg_volume();

		return realtimeCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", realtimeId=");
		sb.append(getRealtimeId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", shareId=");
		sb.append(getShareId());
		sb.append(", value=");
		sb.append(getValue());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", max_value=");
		sb.append(getMax_value());
		sb.append(", min_value=");
		sb.append(getMin_value());
		sb.append(", volume=");
		sb.append(getVolume());
		sb.append(", avg_volume=");
		sb.append(getAvg_volume());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.ibtrader.data.model.Realtime");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>realtimeId</column-name><column-value><![CDATA[");
		sb.append(getRealtimeId());
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
			"<column><column-name>shareId</column-name><column-value><![CDATA[");
		sb.append(getShareId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>value</column-name><column-value><![CDATA[");
		sb.append(getValue());
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
			"<column><column-name>max_value</column-name><column-value><![CDATA[");
		sb.append(getMax_value());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>min_value</column-name><column-value><![CDATA[");
		sb.append(getMin_value());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>volume</column-name><column-value><![CDATA[");
		sb.append(getVolume());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>avg_volume</column-name><column-value><![CDATA[");
		sb.append(getAvg_volume());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Realtime.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Realtime.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _realtimeId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _shareId;
	private long _originalShareId;
	private boolean _setOriginalShareId;
	private double _value;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private double _max_value;
	private boolean _min_value;
	private int _volume;
	private int _avg_volume;
	private long _columnBitmask;
	private Realtime _escapedModel;
}