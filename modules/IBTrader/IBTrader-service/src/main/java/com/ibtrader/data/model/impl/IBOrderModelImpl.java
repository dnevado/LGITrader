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

import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.IBOrderModel;
import com.ibtrader.data.model.IBOrderSoap;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the IBOrder service. Represents a row in the &quot;ibtrader_IBOrder&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link IBOrderModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link IBOrderImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see IBOrderImpl
 * @see IBOrder
 * @see IBOrderModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class IBOrderModelImpl extends BaseModelImpl<IBOrder>
	implements IBOrderModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a i b order model instance should use the {@link IBOrder} interface instead.
	 */
	public static final String TABLE_NAME = "ibtrader_IBOrder";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "ordersId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "orderID", Types.BIGINT },
			{ "shareID", Types.BIGINT },
			{ "checked", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ordersId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("orderID", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("shareID", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("checked", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table ibtrader_IBOrder (uuid_ VARCHAR(75) null,ordersId LONG not null primary key,groupId LONG,companyId LONG,orderID LONG,shareID LONG,checked BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table ibtrader_IBOrder";
	public static final String ORDER_BY_JPQL = " ORDER BY ibOrder.ordersId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ibtrader_IBOrder.ordersId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.ibtrader.data.model.IBOrder"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.ibtrader.data.model.IBOrder"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.ibtrader.data.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.ibtrader.data.model.IBOrder"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long UUID_COLUMN_BITMASK = 4L;
	public static final long ORDERSID_COLUMN_BITMASK = 8L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static IBOrder toModel(IBOrderSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		IBOrder model = new IBOrderImpl();

		model.setUuid(soapModel.getUuid());
		model.setOrdersId(soapModel.getOrdersId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setOrderID(soapModel.getOrderID());
		model.setShareID(soapModel.getShareID());
		model.setChecked(soapModel.getChecked());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<IBOrder> toModels(IBOrderSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<IBOrder> models = new ArrayList<IBOrder>(soapModels.length);

		for (IBOrderSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.ibtrader.data.service.util.ServiceProps.get(
				"lock.expiration.time.com.ibtrader.data.model.IBOrder"));

	public IBOrderModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _ordersId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOrdersId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ordersId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	@JSON
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

	@JSON
	@Override
	public long getOrdersId() {
		return _ordersId;
	}

	@Override
	public void setOrdersId(long ordersId) {
		_ordersId = ordersId;
	}

	@JSON
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

	@JSON
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

	@JSON
	@Override
	public long getOrderID() {
		return _orderID;
	}

	@Override
	public void setOrderID(long orderID) {
		_orderID = orderID;
	}

	@JSON
	@Override
	public long getShareID() {
		return _shareID;
	}

	@Override
	public void setShareID(long shareID) {
		_shareID = shareID;
	}

	@JSON
	@Override
	public boolean getChecked() {
		return _checked;
	}

	@JSON
	@Override
	public boolean isChecked() {
		return _checked;
	}

	@Override
	public void setChecked(boolean checked) {
		_checked = checked;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			IBOrder.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public IBOrder toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (IBOrder)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		IBOrderImpl ibOrderImpl = new IBOrderImpl();

		ibOrderImpl.setUuid(getUuid());
		ibOrderImpl.setOrdersId(getOrdersId());
		ibOrderImpl.setGroupId(getGroupId());
		ibOrderImpl.setCompanyId(getCompanyId());
		ibOrderImpl.setOrderID(getOrderID());
		ibOrderImpl.setShareID(getShareID());
		ibOrderImpl.setChecked(getChecked());

		ibOrderImpl.resetOriginalValues();

		return ibOrderImpl;
	}

	@Override
	public int compareTo(IBOrder ibOrder) {
		long primaryKey = ibOrder.getPrimaryKey();

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

		if (!(obj instanceof IBOrder)) {
			return false;
		}

		IBOrder ibOrder = (IBOrder)obj;

		long primaryKey = ibOrder.getPrimaryKey();

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
		IBOrderModelImpl ibOrderModelImpl = this;

		ibOrderModelImpl._originalUuid = ibOrderModelImpl._uuid;

		ibOrderModelImpl._originalGroupId = ibOrderModelImpl._groupId;

		ibOrderModelImpl._setOriginalGroupId = false;

		ibOrderModelImpl._originalCompanyId = ibOrderModelImpl._companyId;

		ibOrderModelImpl._setOriginalCompanyId = false;

		ibOrderModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<IBOrder> toCacheModel() {
		IBOrderCacheModel ibOrderCacheModel = new IBOrderCacheModel();

		ibOrderCacheModel.uuid = getUuid();

		String uuid = ibOrderCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			ibOrderCacheModel.uuid = null;
		}

		ibOrderCacheModel.ordersId = getOrdersId();

		ibOrderCacheModel.groupId = getGroupId();

		ibOrderCacheModel.companyId = getCompanyId();

		ibOrderCacheModel.orderID = getOrderID();

		ibOrderCacheModel.shareID = getShareID();

		ibOrderCacheModel.checked = getChecked();

		return ibOrderCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", ordersId=");
		sb.append(getOrdersId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", orderID=");
		sb.append(getOrderID());
		sb.append(", shareID=");
		sb.append(getShareID());
		sb.append(", checked=");
		sb.append(getChecked());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.ibtrader.data.model.IBOrder");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ordersId</column-name><column-value><![CDATA[");
		sb.append(getOrdersId());
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
			"<column><column-name>orderID</column-name><column-value><![CDATA[");
		sb.append(getOrderID());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>shareID</column-name><column-value><![CDATA[");
		sb.append(getShareID());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>checked</column-name><column-value><![CDATA[");
		sb.append(getChecked());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = IBOrder.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			IBOrder.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _ordersId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _orderID;
	private long _shareID;
	private boolean _checked;
	private long _columnBitmask;
	private IBOrder _escapedModel;
}