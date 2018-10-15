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
 * This class is a wrapper for {@link BackTesting}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BackTesting
 * @generated
 */
@ProviderType
public class BackTestingWrapper implements BackTesting,
	ModelWrapper<BackTesting> {
	public BackTestingWrapper(BackTesting backTesting) {
		_backTesting = backTesting;
	}

	@Override
	public Class<?> getModelClass() {
		return BackTesting.class;
	}

	@Override
	public String getModelClassName() {
		return BackTesting.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("backTId", getBackTId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("fromDate", getFromDate());
		attributes.put("toDate", getToDate());
		attributes.put("shareId", getShareId());
		attributes.put("countordersBUY", getCountordersBUY());
		attributes.put("countordersSELL", getCountordersSELL());
		attributes.put("profitordersBUY", getProfitordersBUY());
		attributes.put("profitordersSELL", getProfitordersSELL());
		attributes.put("status", getStatus());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long backTId = (Long)attributes.get("backTId");

		if (backTId != null) {
			setBackTId(backTId);
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

		Date fromDate = (Date)attributes.get("fromDate");

		if (fromDate != null) {
			setFromDate(fromDate);
		}

		Date toDate = (Date)attributes.get("toDate");

		if (toDate != null) {
			setToDate(toDate);
		}

		Long shareId = (Long)attributes.get("shareId");

		if (shareId != null) {
			setShareId(shareId);
		}

		Long countordersBUY = (Long)attributes.get("countordersBUY");

		if (countordersBUY != null) {
			setCountordersBUY(countordersBUY);
		}

		Long countordersSELL = (Long)attributes.get("countordersSELL");

		if (countordersSELL != null) {
			setCountordersSELL(countordersSELL);
		}

		Double profitordersBUY = (Double)attributes.get("profitordersBUY");

		if (profitordersBUY != null) {
			setProfitordersBUY(profitordersBUY);
		}

		Double profitordersSELL = (Double)attributes.get("profitordersSELL");

		if (profitordersSELL != null) {
			setProfitordersSELL(profitordersSELL);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	@Override
	public BackTesting toEscapedModel() {
		return new BackTestingWrapper(_backTesting.toEscapedModel());
	}

	@Override
	public BackTesting toUnescapedModel() {
		return new BackTestingWrapper(_backTesting.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _backTesting.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _backTesting.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _backTesting.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _backTesting.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<BackTesting> toCacheModel() {
		return _backTesting.toCacheModel();
	}

	/**
	* Returns the profitorders b u y of this back testing.
	*
	* @return the profitorders b u y of this back testing
	*/
	@Override
	public double getProfitordersBUY() {
		return _backTesting.getProfitordersBUY();
	}

	/**
	* Returns the profitorders s e l l of this back testing.
	*
	* @return the profitorders s e l l of this back testing
	*/
	@Override
	public double getProfitordersSELL() {
		return _backTesting.getProfitordersSELL();
	}

	@Override
	public int compareTo(BackTesting backTesting) {
		return _backTesting.compareTo(backTesting);
	}

	@Override
	public int hashCode() {
		return _backTesting.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _backTesting.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new BackTestingWrapper((BackTesting)_backTesting.clone());
	}

	/**
	* Returns the description of this back testing.
	*
	* @return the description of this back testing
	*/
	@Override
	public java.lang.String getDescription() {
		return _backTesting.getDescription();
	}

	/**
	* Returns the status of this back testing.
	*
	* @return the status of this back testing
	*/
	@Override
	public java.lang.String getStatus() {
		return _backTesting.getStatus();
	}

	/**
	* Returns the uuid of this back testing.
	*
	* @return the uuid of this back testing
	*/
	@Override
	public java.lang.String getUuid() {
		return _backTesting.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _backTesting.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _backTesting.toXmlString();
	}

	/**
	* Returns the create date of this back testing.
	*
	* @return the create date of this back testing
	*/
	@Override
	public Date getCreateDate() {
		return _backTesting.getCreateDate();
	}

	/**
	* Returns the from date of this back testing.
	*
	* @return the from date of this back testing
	*/
	@Override
	public Date getFromDate() {
		return _backTesting.getFromDate();
	}

	/**
	* Returns the modified date of this back testing.
	*
	* @return the modified date of this back testing
	*/
	@Override
	public Date getModifiedDate() {
		return _backTesting.getModifiedDate();
	}

	/**
	* Returns the to date of this back testing.
	*
	* @return the to date of this back testing
	*/
	@Override
	public Date getToDate() {
		return _backTesting.getToDate();
	}

	/**
	* Returns the back t ID of this back testing.
	*
	* @return the back t ID of this back testing
	*/
	@Override
	public long getBackTId() {
		return _backTesting.getBackTId();
	}

	/**
	* Returns the company ID of this back testing.
	*
	* @return the company ID of this back testing
	*/
	@Override
	public long getCompanyId() {
		return _backTesting.getCompanyId();
	}

	/**
	* Returns the countorders b u y of this back testing.
	*
	* @return the countorders b u y of this back testing
	*/
	@Override
	public long getCountordersBUY() {
		return _backTesting.getCountordersBUY();
	}

	/**
	* Returns the countorders s e l l of this back testing.
	*
	* @return the countorders s e l l of this back testing
	*/
	@Override
	public long getCountordersSELL() {
		return _backTesting.getCountordersSELL();
	}

	/**
	* Returns the group ID of this back testing.
	*
	* @return the group ID of this back testing
	*/
	@Override
	public long getGroupId() {
		return _backTesting.getGroupId();
	}

	/**
	* Returns the primary key of this back testing.
	*
	* @return the primary key of this back testing
	*/
	@Override
	public long getPrimaryKey() {
		return _backTesting.getPrimaryKey();
	}

	/**
	* Returns the share ID of this back testing.
	*
	* @return the share ID of this back testing
	*/
	@Override
	public long getShareId() {
		return _backTesting.getShareId();
	}

	@Override
	public void persist() {
		_backTesting.persist();
	}

	/**
	* Sets the back t ID of this back testing.
	*
	* @param backTId the back t ID of this back testing
	*/
	@Override
	public void setBackTId(long backTId) {
		_backTesting.setBackTId(backTId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_backTesting.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this back testing.
	*
	* @param companyId the company ID of this back testing
	*/
	@Override
	public void setCompanyId(long companyId) {
		_backTesting.setCompanyId(companyId);
	}

	/**
	* Sets the countorders b u y of this back testing.
	*
	* @param countordersBUY the countorders b u y of this back testing
	*/
	@Override
	public void setCountordersBUY(long countordersBUY) {
		_backTesting.setCountordersBUY(countordersBUY);
	}

	/**
	* Sets the countorders s e l l of this back testing.
	*
	* @param countordersSELL the countorders s e l l of this back testing
	*/
	@Override
	public void setCountordersSELL(long countordersSELL) {
		_backTesting.setCountordersSELL(countordersSELL);
	}

	/**
	* Sets the create date of this back testing.
	*
	* @param createDate the create date of this back testing
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_backTesting.setCreateDate(createDate);
	}

	/**
	* Sets the description of this back testing.
	*
	* @param description the description of this back testing
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_backTesting.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_backTesting.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_backTesting.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_backTesting.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the from date of this back testing.
	*
	* @param fromDate the from date of this back testing
	*/
	@Override
	public void setFromDate(Date fromDate) {
		_backTesting.setFromDate(fromDate);
	}

	/**
	* Sets the group ID of this back testing.
	*
	* @param groupId the group ID of this back testing
	*/
	@Override
	public void setGroupId(long groupId) {
		_backTesting.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this back testing.
	*
	* @param modifiedDate the modified date of this back testing
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_backTesting.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_backTesting.setNew(n);
	}

	/**
	* Sets the primary key of this back testing.
	*
	* @param primaryKey the primary key of this back testing
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_backTesting.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_backTesting.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the profitorders b u y of this back testing.
	*
	* @param profitordersBUY the profitorders b u y of this back testing
	*/
	@Override
	public void setProfitordersBUY(double profitordersBUY) {
		_backTesting.setProfitordersBUY(profitordersBUY);
	}

	/**
	* Sets the profitorders s e l l of this back testing.
	*
	* @param profitordersSELL the profitorders s e l l of this back testing
	*/
	@Override
	public void setProfitordersSELL(double profitordersSELL) {
		_backTesting.setProfitordersSELL(profitordersSELL);
	}

	/**
	* Sets the share ID of this back testing.
	*
	* @param shareId the share ID of this back testing
	*/
	@Override
	public void setShareId(long shareId) {
		_backTesting.setShareId(shareId);
	}

	/**
	* Sets the status of this back testing.
	*
	* @param status the status of this back testing
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_backTesting.setStatus(status);
	}

	/**
	* Sets the to date of this back testing.
	*
	* @param toDate the to date of this back testing
	*/
	@Override
	public void setToDate(Date toDate) {
		_backTesting.setToDate(toDate);
	}

	/**
	* Sets the uuid of this back testing.
	*
	* @param uuid the uuid of this back testing
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_backTesting.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BackTestingWrapper)) {
			return false;
		}

		BackTestingWrapper backTestingWrapper = (BackTestingWrapper)obj;

		if (Objects.equals(_backTesting, backTestingWrapper._backTesting)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _backTesting.getStagedModelType();
	}

	@Override
	public BackTesting getWrappedModel() {
		return _backTesting;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _backTesting.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _backTesting.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_backTesting.resetOriginalValues();
	}

	private final BackTesting _backTesting;
}