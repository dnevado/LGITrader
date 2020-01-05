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
 * This class is a wrapper for {@link HistoricalRealtime}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HistoricalRealtime
 * @generated
 */
@ProviderType
public class HistoricalRealtimeWrapper implements HistoricalRealtime,
	ModelWrapper<HistoricalRealtime> {
	public HistoricalRealtimeWrapper(HistoricalRealtime historicalRealtime) {
		_historicalRealtime = historicalRealtime;
	}

	@Override
	public Class<?> getModelClass() {
		return HistoricalRealtime.class;
	}

	@Override
	public String getModelClassName() {
		return HistoricalRealtime.class.getName();
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
		attributes.put("closeprice", getCloseprice());

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

		Double min_value = (Double)attributes.get("min_value");

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

		Boolean closeprice = (Boolean)attributes.get("closeprice");

		if (closeprice != null) {
			setCloseprice(closeprice);
		}
	}

	@Override
	public HistoricalRealtime toEscapedModel() {
		return new HistoricalRealtimeWrapper(_historicalRealtime.toEscapedModel());
	}

	@Override
	public HistoricalRealtime toUnescapedModel() {
		return new HistoricalRealtimeWrapper(_historicalRealtime.toUnescapedModel());
	}

	/**
	* Returns the closeprice of this historical realtime.
	*
	* @return the closeprice of this historical realtime
	*/
	@Override
	public boolean getCloseprice() {
		return _historicalRealtime.getCloseprice();
	}

	@Override
	public boolean isCachedModel() {
		return _historicalRealtime.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this historical realtime is closeprice.
	*
	* @return <code>true</code> if this historical realtime is closeprice; <code>false</code> otherwise
	*/
	@Override
	public boolean isCloseprice() {
		return _historicalRealtime.isCloseprice();
	}

	@Override
	public boolean isEscapedModel() {
		return _historicalRealtime.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _historicalRealtime.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _historicalRealtime.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HistoricalRealtime> toCacheModel() {
		return _historicalRealtime.toCacheModel();
	}

	/**
	* Returns the max_value of this historical realtime.
	*
	* @return the max_value of this historical realtime
	*/
	@Override
	public double getMax_value() {
		return _historicalRealtime.getMax_value();
	}

	/**
	* Returns the min_value of this historical realtime.
	*
	* @return the min_value of this historical realtime
	*/
	@Override
	public double getMin_value() {
		return _historicalRealtime.getMin_value();
	}

	/**
	* Returns the value of this historical realtime.
	*
	* @return the value of this historical realtime
	*/
	@Override
	public double getValue() {
		return _historicalRealtime.getValue();
	}

	@Override
	public int compareTo(HistoricalRealtime historicalRealtime) {
		return _historicalRealtime.compareTo(historicalRealtime);
	}

	/**
	* Returns the avg_volume of this historical realtime.
	*
	* @return the avg_volume of this historical realtime
	*/
	@Override
	public int getAvg_volume() {
		return _historicalRealtime.getAvg_volume();
	}

	/**
	* Returns the volume of this historical realtime.
	*
	* @return the volume of this historical realtime
	*/
	@Override
	public int getVolume() {
		return _historicalRealtime.getVolume();
	}

	@Override
	public int hashCode() {
		return _historicalRealtime.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _historicalRealtime.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new HistoricalRealtimeWrapper((HistoricalRealtime)_historicalRealtime.clone());
	}

	/**
	* Returns the uuid of this historical realtime.
	*
	* @return the uuid of this historical realtime
	*/
	@Override
	public java.lang.String getUuid() {
		return _historicalRealtime.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _historicalRealtime.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _historicalRealtime.toXmlString();
	}

	/**
	* Returns the create date of this historical realtime.
	*
	* @return the create date of this historical realtime
	*/
	@Override
	public Date getCreateDate() {
		return _historicalRealtime.getCreateDate();
	}

	/**
	* Returns the modified date of this historical realtime.
	*
	* @return the modified date of this historical realtime
	*/
	@Override
	public Date getModifiedDate() {
		return _historicalRealtime.getModifiedDate();
	}

	/**
	* Returns the company ID of this historical realtime.
	*
	* @return the company ID of this historical realtime
	*/
	@Override
	public long getCompanyId() {
		return _historicalRealtime.getCompanyId();
	}

	/**
	* Returns the group ID of this historical realtime.
	*
	* @return the group ID of this historical realtime
	*/
	@Override
	public long getGroupId() {
		return _historicalRealtime.getGroupId();
	}

	/**
	* Returns the primary key of this historical realtime.
	*
	* @return the primary key of this historical realtime
	*/
	@Override
	public long getPrimaryKey() {
		return _historicalRealtime.getPrimaryKey();
	}

	/**
	* Returns the realtime ID of this historical realtime.
	*
	* @return the realtime ID of this historical realtime
	*/
	@Override
	public long getRealtimeId() {
		return _historicalRealtime.getRealtimeId();
	}

	/**
	* Returns the share ID of this historical realtime.
	*
	* @return the share ID of this historical realtime
	*/
	@Override
	public long getShareId() {
		return _historicalRealtime.getShareId();
	}

	@Override
	public void persist() {
		_historicalRealtime.persist();
	}

	/**
	* Sets the avg_volume of this historical realtime.
	*
	* @param avg_volume the avg_volume of this historical realtime
	*/
	@Override
	public void setAvg_volume(int avg_volume) {
		_historicalRealtime.setAvg_volume(avg_volume);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_historicalRealtime.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this historical realtime is closeprice.
	*
	* @param closeprice the closeprice of this historical realtime
	*/
	@Override
	public void setCloseprice(boolean closeprice) {
		_historicalRealtime.setCloseprice(closeprice);
	}

	/**
	* Sets the company ID of this historical realtime.
	*
	* @param companyId the company ID of this historical realtime
	*/
	@Override
	public void setCompanyId(long companyId) {
		_historicalRealtime.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this historical realtime.
	*
	* @param createDate the create date of this historical realtime
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_historicalRealtime.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_historicalRealtime.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_historicalRealtime.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_historicalRealtime.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this historical realtime.
	*
	* @param groupId the group ID of this historical realtime
	*/
	@Override
	public void setGroupId(long groupId) {
		_historicalRealtime.setGroupId(groupId);
	}

	/**
	* Sets the max_value of this historical realtime.
	*
	* @param max_value the max_value of this historical realtime
	*/
	@Override
	public void setMax_value(double max_value) {
		_historicalRealtime.setMax_value(max_value);
	}

	/**
	* Sets the min_value of this historical realtime.
	*
	* @param min_value the min_value of this historical realtime
	*/
	@Override
	public void setMin_value(double min_value) {
		_historicalRealtime.setMin_value(min_value);
	}

	/**
	* Sets the modified date of this historical realtime.
	*
	* @param modifiedDate the modified date of this historical realtime
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_historicalRealtime.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_historicalRealtime.setNew(n);
	}

	/**
	* Sets the primary key of this historical realtime.
	*
	* @param primaryKey the primary key of this historical realtime
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_historicalRealtime.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_historicalRealtime.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the realtime ID of this historical realtime.
	*
	* @param realtimeId the realtime ID of this historical realtime
	*/
	@Override
	public void setRealtimeId(long realtimeId) {
		_historicalRealtime.setRealtimeId(realtimeId);
	}

	/**
	* Sets the share ID of this historical realtime.
	*
	* @param shareId the share ID of this historical realtime
	*/
	@Override
	public void setShareId(long shareId) {
		_historicalRealtime.setShareId(shareId);
	}

	/**
	* Sets the uuid of this historical realtime.
	*
	* @param uuid the uuid of this historical realtime
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_historicalRealtime.setUuid(uuid);
	}

	/**
	* Sets the value of this historical realtime.
	*
	* @param value the value of this historical realtime
	*/
	@Override
	public void setValue(double value) {
		_historicalRealtime.setValue(value);
	}

	/**
	* Sets the volume of this historical realtime.
	*
	* @param volume the volume of this historical realtime
	*/
	@Override
	public void setVolume(int volume) {
		_historicalRealtime.setVolume(volume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistoricalRealtimeWrapper)) {
			return false;
		}

		HistoricalRealtimeWrapper historicalRealtimeWrapper = (HistoricalRealtimeWrapper)obj;

		if (Objects.equals(_historicalRealtime,
					historicalRealtimeWrapper._historicalRealtime)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _historicalRealtime.getStagedModelType();
	}

	@Override
	public HistoricalRealtime getWrappedModel() {
		return _historicalRealtime;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _historicalRealtime.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _historicalRealtime.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_historicalRealtime.resetOriginalValues();
	}

	private final HistoricalRealtime _historicalRealtime;
}