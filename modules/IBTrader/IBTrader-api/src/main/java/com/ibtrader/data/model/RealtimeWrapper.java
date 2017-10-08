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
 * This class is a wrapper for {@link Realtime}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Realtime
 * @generated
 */
@ProviderType
public class RealtimeWrapper implements Realtime, ModelWrapper<Realtime> {
	public RealtimeWrapper(Realtime realtime) {
		_realtime = realtime;
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
	public Realtime toEscapedModel() {
		return new RealtimeWrapper(_realtime.toEscapedModel());
	}

	@Override
	public Realtime toUnescapedModel() {
		return new RealtimeWrapper(_realtime.toUnescapedModel());
	}

	/**
	* Returns the min_value of this realtime.
	*
	* @return the min_value of this realtime
	*/
	@Override
	public boolean getMin_value() {
		return _realtime.getMin_value();
	}

	@Override
	public boolean isCachedModel() {
		return _realtime.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _realtime.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this realtime is min_value.
	*
	* @return <code>true</code> if this realtime is min_value; <code>false</code> otherwise
	*/
	@Override
	public boolean isMin_value() {
		return _realtime.isMin_value();
	}

	@Override
	public boolean isNew() {
		return _realtime.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _realtime.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Realtime> toCacheModel() {
		return _realtime.toCacheModel();
	}

	/**
	* Returns the max_value of this realtime.
	*
	* @return the max_value of this realtime
	*/
	@Override
	public double getMax_value() {
		return _realtime.getMax_value();
	}

	/**
	* Returns the value of this realtime.
	*
	* @return the value of this realtime
	*/
	@Override
	public double getValue() {
		return _realtime.getValue();
	}

	@Override
	public int compareTo(Realtime realtime) {
		return _realtime.compareTo(realtime);
	}

	/**
	* Returns the avg_volume of this realtime.
	*
	* @return the avg_volume of this realtime
	*/
	@Override
	public int getAvg_volume() {
		return _realtime.getAvg_volume();
	}

	/**
	* Returns the volume of this realtime.
	*
	* @return the volume of this realtime
	*/
	@Override
	public int getVolume() {
		return _realtime.getVolume();
	}

	@Override
	public int hashCode() {
		return _realtime.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _realtime.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new RealtimeWrapper((Realtime)_realtime.clone());
	}

	/**
	* Returns the uuid of this realtime.
	*
	* @return the uuid of this realtime
	*/
	@Override
	public java.lang.String getUuid() {
		return _realtime.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _realtime.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _realtime.toXmlString();
	}

	/**
	* Returns the create date of this realtime.
	*
	* @return the create date of this realtime
	*/
	@Override
	public Date getCreateDate() {
		return _realtime.getCreateDate();
	}

	/**
	* Returns the modified date of this realtime.
	*
	* @return the modified date of this realtime
	*/
	@Override
	public Date getModifiedDate() {
		return _realtime.getModifiedDate();
	}

	/**
	* Returns the company ID of this realtime.
	*
	* @return the company ID of this realtime
	*/
	@Override
	public long getCompanyId() {
		return _realtime.getCompanyId();
	}

	/**
	* Returns the group ID of this realtime.
	*
	* @return the group ID of this realtime
	*/
	@Override
	public long getGroupId() {
		return _realtime.getGroupId();
	}

	/**
	* Returns the primary key of this realtime.
	*
	* @return the primary key of this realtime
	*/
	@Override
	public long getPrimaryKey() {
		return _realtime.getPrimaryKey();
	}

	/**
	* Returns the realtime ID of this realtime.
	*
	* @return the realtime ID of this realtime
	*/
	@Override
	public long getRealtimeId() {
		return _realtime.getRealtimeId();
	}

	/**
	* Returns the share ID of this realtime.
	*
	* @return the share ID of this realtime
	*/
	@Override
	public long getShareId() {
		return _realtime.getShareId();
	}

	@Override
	public void persist() {
		_realtime.persist();
	}

	/**
	* Sets the avg_volume of this realtime.
	*
	* @param avg_volume the avg_volume of this realtime
	*/
	@Override
	public void setAvg_volume(int avg_volume) {
		_realtime.setAvg_volume(avg_volume);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_realtime.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this realtime.
	*
	* @param companyId the company ID of this realtime
	*/
	@Override
	public void setCompanyId(long companyId) {
		_realtime.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this realtime.
	*
	* @param createDate the create date of this realtime
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_realtime.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_realtime.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_realtime.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_realtime.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this realtime.
	*
	* @param groupId the group ID of this realtime
	*/
	@Override
	public void setGroupId(long groupId) {
		_realtime.setGroupId(groupId);
	}

	/**
	* Sets the max_value of this realtime.
	*
	* @param max_value the max_value of this realtime
	*/
	@Override
	public void setMax_value(double max_value) {
		_realtime.setMax_value(max_value);
	}

	/**
	* Sets whether this realtime is min_value.
	*
	* @param min_value the min_value of this realtime
	*/
	@Override
	public void setMin_value(boolean min_value) {
		_realtime.setMin_value(min_value);
	}

	/**
	* Sets the modified date of this realtime.
	*
	* @param modifiedDate the modified date of this realtime
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_realtime.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_realtime.setNew(n);
	}

	/**
	* Sets the primary key of this realtime.
	*
	* @param primaryKey the primary key of this realtime
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_realtime.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_realtime.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the realtime ID of this realtime.
	*
	* @param realtimeId the realtime ID of this realtime
	*/
	@Override
	public void setRealtimeId(long realtimeId) {
		_realtime.setRealtimeId(realtimeId);
	}

	/**
	* Sets the share ID of this realtime.
	*
	* @param shareId the share ID of this realtime
	*/
	@Override
	public void setShareId(long shareId) {
		_realtime.setShareId(shareId);
	}

	/**
	* Sets the uuid of this realtime.
	*
	* @param uuid the uuid of this realtime
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_realtime.setUuid(uuid);
	}

	/**
	* Sets the value of this realtime.
	*
	* @param value the value of this realtime
	*/
	@Override
	public void setValue(double value) {
		_realtime.setValue(value);
	}

	/**
	* Sets the volume of this realtime.
	*
	* @param volume the volume of this realtime
	*/
	@Override
	public void setVolume(int volume) {
		_realtime.setVolume(volume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RealtimeWrapper)) {
			return false;
		}

		RealtimeWrapper realtimeWrapper = (RealtimeWrapper)obj;

		if (Objects.equals(_realtime, realtimeWrapper._realtime)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _realtime.getStagedModelType();
	}

	@Override
	public Realtime getWrappedModel() {
		return _realtime;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _realtime.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _realtime.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_realtime.resetOriginalValues();
	}

	private final Realtime _realtime;
}