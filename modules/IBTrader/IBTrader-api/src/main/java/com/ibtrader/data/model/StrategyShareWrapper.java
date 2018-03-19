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
 * This class is a wrapper for {@link StrategyShare}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrategyShare
 * @generated
 */
@ProviderType
public class StrategyShareWrapper implements StrategyShare,
	ModelWrapper<StrategyShare> {
	public StrategyShareWrapper(StrategyShare strategyShare) {
		_strategyShare = strategyShare;
	}

	@Override
	public Class<?> getModelClass() {
		return StrategyShare.class;
	}

	@Override
	public String getModelClassName() {
		return StrategyShare.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("strategyshareId", getStrategyshareId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("strategyId", getStrategyId());
		attributes.put("shareId", getShareId());
		attributes.put("active", getActive());
		attributes.put("strategyparamsoverride", getStrategyparamsoverride());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long strategyshareId = (Long)attributes.get("strategyshareId");

		if (strategyshareId != null) {
			setStrategyshareId(strategyshareId);
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

		Long strategyId = (Long)attributes.get("strategyId");

		if (strategyId != null) {
			setStrategyId(strategyId);
		}

		Long shareId = (Long)attributes.get("shareId");

		if (shareId != null) {
			setShareId(shareId);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		String strategyparamsoverride = (String)attributes.get(
				"strategyparamsoverride");

		if (strategyparamsoverride != null) {
			setStrategyparamsoverride(strategyparamsoverride);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	@Override
	public StrategyShare toEscapedModel() {
		return new StrategyShareWrapper(_strategyShare.toEscapedModel());
	}

	@Override
	public StrategyShare toUnescapedModel() {
		return new StrategyShareWrapper(_strategyShare.toUnescapedModel());
	}

	/**
	* Returns the active of this strategy share.
	*
	* @return the active of this strategy share
	*/
	@Override
	public boolean getActive() {
		return _strategyShare.getActive();
	}

	/**
	* Returns <code>true</code> if this strategy share is active.
	*
	* @return <code>true</code> if this strategy share is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _strategyShare.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _strategyShare.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _strategyShare.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _strategyShare.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _strategyShare.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StrategyShare> toCacheModel() {
		return _strategyShare.toCacheModel();
	}

	@Override
	public int compareTo(StrategyShare strategyShare) {
		return _strategyShare.compareTo(strategyShare);
	}

	@Override
	public int hashCode() {
		return _strategyShare.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _strategyShare.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new StrategyShareWrapper((StrategyShare)_strategyShare.clone());
	}

	/**
	* Returns the description of this strategy share.
	*
	* @return the description of this strategy share
	*/
	@Override
	public java.lang.String getDescription() {
		return _strategyShare.getDescription();
	}

	/**
	* Returns the strategyparamsoverride of this strategy share.
	*
	* @return the strategyparamsoverride of this strategy share
	*/
	@Override
	public java.lang.String getStrategyparamsoverride() {
		return _strategyShare.getStrategyparamsoverride();
	}

	/**
	* Returns the uuid of this strategy share.
	*
	* @return the uuid of this strategy share
	*/
	@Override
	public java.lang.String getUuid() {
		return _strategyShare.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _strategyShare.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _strategyShare.toXmlString();
	}

	/**
	* Returns the create date of this strategy share.
	*
	* @return the create date of this strategy share
	*/
	@Override
	public Date getCreateDate() {
		return _strategyShare.getCreateDate();
	}

	/**
	* Returns the modified date of this strategy share.
	*
	* @return the modified date of this strategy share
	*/
	@Override
	public Date getModifiedDate() {
		return _strategyShare.getModifiedDate();
	}

	/**
	* Returns the company ID of this strategy share.
	*
	* @return the company ID of this strategy share
	*/
	@Override
	public long getCompanyId() {
		return _strategyShare.getCompanyId();
	}

	/**
	* Returns the group ID of this strategy share.
	*
	* @return the group ID of this strategy share
	*/
	@Override
	public long getGroupId() {
		return _strategyShare.getGroupId();
	}

	/**
	* Returns the primary key of this strategy share.
	*
	* @return the primary key of this strategy share
	*/
	@Override
	public long getPrimaryKey() {
		return _strategyShare.getPrimaryKey();
	}

	/**
	* Returns the share ID of this strategy share.
	*
	* @return the share ID of this strategy share
	*/
	@Override
	public long getShareId() {
		return _strategyShare.getShareId();
	}

	/**
	* Returns the strategy ID of this strategy share.
	*
	* @return the strategy ID of this strategy share
	*/
	@Override
	public long getStrategyId() {
		return _strategyShare.getStrategyId();
	}

	/**
	* Returns the strategyshare ID of this strategy share.
	*
	* @return the strategyshare ID of this strategy share
	*/
	@Override
	public long getStrategyshareId() {
		return _strategyShare.getStrategyshareId();
	}

	@Override
	public void persist() {
		_strategyShare.persist();
	}

	/**
	* Sets whether this strategy share is active.
	*
	* @param active the active of this strategy share
	*/
	@Override
	public void setActive(boolean active) {
		_strategyShare.setActive(active);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_strategyShare.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this strategy share.
	*
	* @param companyId the company ID of this strategy share
	*/
	@Override
	public void setCompanyId(long companyId) {
		_strategyShare.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this strategy share.
	*
	* @param createDate the create date of this strategy share
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_strategyShare.setCreateDate(createDate);
	}

	/**
	* Sets the description of this strategy share.
	*
	* @param description the description of this strategy share
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_strategyShare.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_strategyShare.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_strategyShare.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_strategyShare.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this strategy share.
	*
	* @param groupId the group ID of this strategy share
	*/
	@Override
	public void setGroupId(long groupId) {
		_strategyShare.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this strategy share.
	*
	* @param modifiedDate the modified date of this strategy share
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_strategyShare.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_strategyShare.setNew(n);
	}

	/**
	* Sets the primary key of this strategy share.
	*
	* @param primaryKey the primary key of this strategy share
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_strategyShare.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_strategyShare.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the share ID of this strategy share.
	*
	* @param shareId the share ID of this strategy share
	*/
	@Override
	public void setShareId(long shareId) {
		_strategyShare.setShareId(shareId);
	}

	/**
	* Sets the strategy ID of this strategy share.
	*
	* @param strategyId the strategy ID of this strategy share
	*/
	@Override
	public void setStrategyId(long strategyId) {
		_strategyShare.setStrategyId(strategyId);
	}

	/**
	* Sets the strategyparamsoverride of this strategy share.
	*
	* @param strategyparamsoverride the strategyparamsoverride of this strategy share
	*/
	@Override
	public void setStrategyparamsoverride(
		java.lang.String strategyparamsoverride) {
		_strategyShare.setStrategyparamsoverride(strategyparamsoverride);
	}

	/**
	* Sets the strategyshare ID of this strategy share.
	*
	* @param strategyshareId the strategyshare ID of this strategy share
	*/
	@Override
	public void setStrategyshareId(long strategyshareId) {
		_strategyShare.setStrategyshareId(strategyshareId);
	}

	/**
	* Sets the uuid of this strategy share.
	*
	* @param uuid the uuid of this strategy share
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_strategyShare.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StrategyShareWrapper)) {
			return false;
		}

		StrategyShareWrapper strategyShareWrapper = (StrategyShareWrapper)obj;

		if (Objects.equals(_strategyShare, strategyShareWrapper._strategyShare)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _strategyShare.getStagedModelType();
	}

	@Override
	public StrategyShare getWrappedModel() {
		return _strategyShare;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _strategyShare.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _strategyShare.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_strategyShare.resetOriginalValues();
	}

	private final StrategyShare _strategyShare;
}