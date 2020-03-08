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
 * This class is a wrapper for {@link AuditIndicatorsStrategy}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditIndicatorsStrategy
 * @generated
 */
@ProviderType
public class AuditIndicatorsStrategyWrapper implements AuditIndicatorsStrategy,
	ModelWrapper<AuditIndicatorsStrategy> {
	public AuditIndicatorsStrategyWrapper(
		AuditIndicatorsStrategy auditIndicatorsStrategy) {
		_auditIndicatorsStrategy = auditIndicatorsStrategy;
	}

	@Override
	public Class<?> getModelClass() {
		return AuditIndicatorsStrategy.class;
	}

	@Override
	public String getModelClassName() {
		return AuditIndicatorsStrategy.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("auditDate", getAuditDate());
		attributes.put("auditstrategy", getAuditstrategy());
		attributes.put("shareId", getShareId());
		attributes.put("auditData", getAuditData());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String auditDate = (String)attributes.get("auditDate");

		if (auditDate != null) {
			setAuditDate(auditDate);
		}

		String auditstrategy = (String)attributes.get("auditstrategy");

		if (auditstrategy != null) {
			setAuditstrategy(auditstrategy);
		}

		Long shareId = (Long)attributes.get("shareId");

		if (shareId != null) {
			setShareId(shareId);
		}

		String auditData = (String)attributes.get("auditData");

		if (auditData != null) {
			setAuditData(auditData);
		}
	}

	@Override
	public AuditIndicatorsStrategy toEscapedModel() {
		return new AuditIndicatorsStrategyWrapper(_auditIndicatorsStrategy.toEscapedModel());
	}

	@Override
	public AuditIndicatorsStrategy toUnescapedModel() {
		return new AuditIndicatorsStrategyWrapper(_auditIndicatorsStrategy.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _auditIndicatorsStrategy.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _auditIndicatorsStrategy.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _auditIndicatorsStrategy.isNew();
	}

	/**
	* Returns the primary key of this audit indicators strategy.
	*
	* @return the primary key of this audit indicators strategy
	*/
	@Override
	public com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK getPrimaryKey() {
		return _auditIndicatorsStrategy.getPrimaryKey();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _auditIndicatorsStrategy.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AuditIndicatorsStrategy> toCacheModel() {
		return _auditIndicatorsStrategy.toCacheModel();
	}

	@Override
	public int compareTo(AuditIndicatorsStrategy auditIndicatorsStrategy) {
		return _auditIndicatorsStrategy.compareTo(auditIndicatorsStrategy);
	}

	@Override
	public int hashCode() {
		return _auditIndicatorsStrategy.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _auditIndicatorsStrategy.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new AuditIndicatorsStrategyWrapper((AuditIndicatorsStrategy)_auditIndicatorsStrategy.clone());
	}

	/**
	* Returns the audit data of this audit indicators strategy.
	*
	* @return the audit data of this audit indicators strategy
	*/
	@Override
	public java.lang.String getAuditData() {
		return _auditIndicatorsStrategy.getAuditData();
	}

	/**
	* Returns the audit date of this audit indicators strategy.
	*
	* @return the audit date of this audit indicators strategy
	*/
	@Override
	public java.lang.String getAuditDate() {
		return _auditIndicatorsStrategy.getAuditDate();
	}

	/**
	* Returns the auditstrategy of this audit indicators strategy.
	*
	* @return the auditstrategy of this audit indicators strategy
	*/
	@Override
	public java.lang.String getAuditstrategy() {
		return _auditIndicatorsStrategy.getAuditstrategy();
	}

	/**
	* Returns the uuid of this audit indicators strategy.
	*
	* @return the uuid of this audit indicators strategy
	*/
	@Override
	public java.lang.String getUuid() {
		return _auditIndicatorsStrategy.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _auditIndicatorsStrategy.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _auditIndicatorsStrategy.toXmlString();
	}

	/**
	* Returns the company ID of this audit indicators strategy.
	*
	* @return the company ID of this audit indicators strategy
	*/
	@Override
	public long getCompanyId() {
		return _auditIndicatorsStrategy.getCompanyId();
	}

	/**
	* Returns the group ID of this audit indicators strategy.
	*
	* @return the group ID of this audit indicators strategy
	*/
	@Override
	public long getGroupId() {
		return _auditIndicatorsStrategy.getGroupId();
	}

	/**
	* Returns the share ID of this audit indicators strategy.
	*
	* @return the share ID of this audit indicators strategy
	*/
	@Override
	public long getShareId() {
		return _auditIndicatorsStrategy.getShareId();
	}

	@Override
	public void persist() {
		_auditIndicatorsStrategy.persist();
	}

	/**
	* Sets the audit data of this audit indicators strategy.
	*
	* @param auditData the audit data of this audit indicators strategy
	*/
	@Override
	public void setAuditData(java.lang.String auditData) {
		_auditIndicatorsStrategy.setAuditData(auditData);
	}

	/**
	* Sets the audit date of this audit indicators strategy.
	*
	* @param auditDate the audit date of this audit indicators strategy
	*/
	@Override
	public void setAuditDate(java.lang.String auditDate) {
		_auditIndicatorsStrategy.setAuditDate(auditDate);
	}

	/**
	* Sets the auditstrategy of this audit indicators strategy.
	*
	* @param auditstrategy the auditstrategy of this audit indicators strategy
	*/
	@Override
	public void setAuditstrategy(java.lang.String auditstrategy) {
		_auditIndicatorsStrategy.setAuditstrategy(auditstrategy);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_auditIndicatorsStrategy.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this audit indicators strategy.
	*
	* @param companyId the company ID of this audit indicators strategy
	*/
	@Override
	public void setCompanyId(long companyId) {
		_auditIndicatorsStrategy.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_auditIndicatorsStrategy.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_auditIndicatorsStrategy.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_auditIndicatorsStrategy.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this audit indicators strategy.
	*
	* @param groupId the group ID of this audit indicators strategy
	*/
	@Override
	public void setGroupId(long groupId) {
		_auditIndicatorsStrategy.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_auditIndicatorsStrategy.setNew(n);
	}

	/**
	* Sets the primary key of this audit indicators strategy.
	*
	* @param primaryKey the primary key of this audit indicators strategy
	*/
	@Override
	public void setPrimaryKey(
		com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK primaryKey) {
		_auditIndicatorsStrategy.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_auditIndicatorsStrategy.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the share ID of this audit indicators strategy.
	*
	* @param shareId the share ID of this audit indicators strategy
	*/
	@Override
	public void setShareId(long shareId) {
		_auditIndicatorsStrategy.setShareId(shareId);
	}

	/**
	* Sets the uuid of this audit indicators strategy.
	*
	* @param uuid the uuid of this audit indicators strategy
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_auditIndicatorsStrategy.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditIndicatorsStrategyWrapper)) {
			return false;
		}

		AuditIndicatorsStrategyWrapper auditIndicatorsStrategyWrapper = (AuditIndicatorsStrategyWrapper)obj;

		if (Objects.equals(_auditIndicatorsStrategy,
					auditIndicatorsStrategyWrapper._auditIndicatorsStrategy)) {
			return true;
		}

		return false;
	}

	@Override
	public AuditIndicatorsStrategy getWrappedModel() {
		return _auditIndicatorsStrategy;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _auditIndicatorsStrategy.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _auditIndicatorsStrategy.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_auditIndicatorsStrategy.resetOriginalValues();
	}

	private final AuditIndicatorsStrategy _auditIndicatorsStrategy;
}