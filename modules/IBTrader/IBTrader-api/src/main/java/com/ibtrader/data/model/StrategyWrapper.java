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
 * This class is a wrapper for {@link Strategy}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Strategy
 * @generated
 */
@ProviderType
public class StrategyWrapper implements Strategy, ModelWrapper<Strategy> {
	public StrategyWrapper(Strategy strategy) {
		_strategy = strategy;
	}

	@Override
	public Class<?> getModelClass() {
		return Strategy.class;
	}

	@Override
	public String getModelClassName() {
		return Strategy.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("strategyID", getStrategyID());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("active", getActive());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());
		attributes.put("type", getType());
		attributes.put("className", getClassName());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long strategyID = (Long)attributes.get("strategyID");

		if (strategyID != null) {
			setStrategyID(strategyID);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
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

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	@Override
	public Strategy toEscapedModel() {
		return new StrategyWrapper(_strategy.toEscapedModel());
	}

	@Override
	public Strategy toUnescapedModel() {
		return new StrategyWrapper(_strategy.toUnescapedModel());
	}

	@Override
	public boolean activated() {
		return _strategy.activated();
	}

	/**
	* Returns the active of this strategy.
	*
	* @return the active of this strategy
	*/
	@Override
	public boolean getActive() {
		return _strategy.getActive();
	}

	/**
	* Returns <code>true</code> if this strategy is active.
	*
	* @return <code>true</code> if this strategy is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _strategy.isActive();
	}

	/**
	* Returns <code>true</code> if this strategy is approved.
	*
	* @return <code>true</code> if this strategy is approved; <code>false</code> otherwise
	*/
	@Override
	public boolean isApproved() {
		return _strategy.isApproved();
	}

	@Override
	public boolean isCachedModel() {
		return _strategy.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this strategy is denied.
	*
	* @return <code>true</code> if this strategy is denied; <code>false</code> otherwise
	*/
	@Override
	public boolean isDenied() {
		return _strategy.isDenied();
	}

	/**
	* Returns <code>true</code> if this strategy is a draft.
	*
	* @return <code>true</code> if this strategy is a draft; <code>false</code> otherwise
	*/
	@Override
	public boolean isDraft() {
		return _strategy.isDraft();
	}

	@Override
	public boolean isEscapedModel() {
		return _strategy.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this strategy is expired.
	*
	* @return <code>true</code> if this strategy is expired; <code>false</code> otherwise
	*/
	@Override
	public boolean isExpired() {
		return _strategy.isExpired();
	}

	/**
	* Returns <code>true</code> if this strategy is inactive.
	*
	* @return <code>true</code> if this strategy is inactive; <code>false</code> otherwise
	*/
	@Override
	public boolean isInactive() {
		return _strategy.isInactive();
	}

	/**
	* Returns <code>true</code> if this strategy is incomplete.
	*
	* @return <code>true</code> if this strategy is incomplete; <code>false</code> otherwise
	*/
	@Override
	public boolean isIncomplete() {
		return _strategy.isIncomplete();
	}

	@Override
	public boolean isNew() {
		return _strategy.isNew();
	}

	/**
	* Returns <code>true</code> if this strategy is pending.
	*
	* @return <code>true</code> if this strategy is pending; <code>false</code> otherwise
	*/
	@Override
	public boolean isPending() {
		return _strategy.isPending();
	}

	/**
	* Returns <code>true</code> if this strategy is scheduled.
	*
	* @return <code>true</code> if this strategy is scheduled; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduled() {
		return _strategy.isScheduled();
	}

	@Override
	public boolean isVerified() {
		return _strategy.isVerified();
	}

	@Override
	public boolean validateParams(
		Map<java.lang.String, java.lang.String> paramValues) {
		return _strategy.validateParams(paramValues);
	}

	@Override
	public boolean verify(Share _share, Market _market,
		StrategyShare _strategyImpl) {
		return _strategy.verify(_share, _market, _strategyImpl);
	}

	@Override
	public com.ib.client.Contract getTargetContract() {
		return _strategy.getTargetContract();
	}

	@Override
	public com.ib.client.Order getTargetOrder() {
		return _strategy.getTargetOrder();
	}

	@Override
	public com.liferay.asset.kernel.model.AssetEntry getIBStrategyAssetEntry() {
		return _strategy.getIBStrategyAssetEntry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _strategy.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getJsonStrategyShareParams() {
		return _strategy.getJsonStrategyShareParams();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Strategy> toCacheModel() {
		return _strategy.toCacheModel();
	}

	@Override
	public double getValueIn() {
		return _strategy.getValueIn();
	}

	@Override
	public double getValueLimitIn() {
		return _strategy.getValueLimitIn();
	}

	@Override
	public double getValueLimitOut() {
		return _strategy.getValueLimitOut();
	}

	@Override
	public double getValueOut() {
		return _strategy.getValueOut();
	}

	@Override
	public int compareTo(Strategy strategy) {
		return _strategy.compareTo(strategy);
	}

	@Override
	public int getCLIENT_ID() {
		return _strategy.getCLIENT_ID();
	}

	/**
	* Returns the status of this strategy.
	*
	* @return the status of this strategy
	*/
	@Override
	public int getStatus() {
		return _strategy.getStatus();
	}

	@Override
	public int hashCode() {
		return _strategy.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _strategy.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new StrategyWrapper((Strategy)_strategy.clone());
	}

	/**
	* Returns the class name of this strategy.
	*
	* @return the class name of this strategy
	*/
	@Override
	public java.lang.String getClassName() {
		return _strategy.getClassName();
	}

	/**
	* Returns the description of this strategy.
	*
	* @return the description of this strategy
	*/
	@Override
	public java.lang.String getDescription() {
		return _strategy.getDescription();
	}

	/**
	* Returns the name of this strategy.
	*
	* @return the name of this strategy
	*/
	@Override
	public java.lang.String getName() {
		return _strategy.getName();
	}

	/**
	* Returns the status by user name of this strategy.
	*
	* @return the status by user name of this strategy
	*/
	@Override
	public java.lang.String getStatusByUserName() {
		return _strategy.getStatusByUserName();
	}

	/**
	* Returns the status by user uuid of this strategy.
	*
	* @return the status by user uuid of this strategy
	*/
	@Override
	public java.lang.String getStatusByUserUuid() {
		return _strategy.getStatusByUserUuid();
	}

	/**
	* Returns the type of this strategy.
	*
	* @return the type of this strategy
	*/
	@Override
	public java.lang.String getType() {
		return _strategy.getType();
	}

	/**
	* Returns the user uuid of this strategy.
	*
	* @return the user uuid of this strategy
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _strategy.getUserUuid();
	}

	/**
	* Returns the uuid of this strategy.
	*
	* @return the uuid of this strategy
	*/
	@Override
	public java.lang.String getUuid() {
		return _strategy.getUuid();
	}

	@Override
	public java.lang.String getValidateParamsKeysError() {
		return _strategy.getValidateParamsKeysError();
	}

	@Override
	public java.lang.String toString() {
		return _strategy.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _strategy.toXmlString();
	}

	/**
	* Returns the create date of this strategy.
	*
	* @return the create date of this strategy
	*/
	@Override
	public Date getCreateDate() {
		return _strategy.getCreateDate();
	}

	/**
	* Returns the modified date of this strategy.
	*
	* @return the modified date of this strategy
	*/
	@Override
	public Date getModifiedDate() {
		return _strategy.getModifiedDate();
	}

	/**
	* Returns the status date of this strategy.
	*
	* @return the status date of this strategy
	*/
	@Override
	public Date getStatusDate() {
		return _strategy.getStatusDate();
	}

	@Override
	public java.util.List<com.liferay.expando.kernel.model.ExpandoColumn> getIBStrategyParams() {
		return _strategy.getIBStrategyParams();
	}

	@Override
	public long execute(Share _share, Market _market) {
		return _strategy.execute(_share, _market);
	}

	/**
	* Returns the company ID of this strategy.
	*
	* @return the company ID of this strategy
	*/
	@Override
	public long getCompanyId() {
		return _strategy.getCompanyId();
	}

	/**
	* Returns the group ID of this strategy.
	*
	* @return the group ID of this strategy
	*/
	@Override
	public long getGroupId() {
		return _strategy.getGroupId();
	}

	/**
	* Returns the primary key of this strategy.
	*
	* @return the primary key of this strategy
	*/
	@Override
	public long getPrimaryKey() {
		return _strategy.getPrimaryKey();
	}

	/**
	* Returns the status by user ID of this strategy.
	*
	* @return the status by user ID of this strategy
	*/
	@Override
	public long getStatusByUserId() {
		return _strategy.getStatusByUserId();
	}

	/**
	* Returns the strategy i d of this strategy.
	*
	* @return the strategy i d of this strategy
	*/
	@Override
	public long getStrategyID() {
		return _strategy.getStrategyID();
	}

	/**
	* Returns the user ID of this strategy.
	*
	* @return the user ID of this strategy
	*/
	@Override
	public long getUserId() {
		return _strategy.getUserId();
	}

	@Override
	public void init(long companyId) {
		_strategy.init(companyId);
	}

	@Override
	public void persist() {
		_strategy.persist();
	}

	/**
	* Sets whether this strategy is active.
	*
	* @param active the active of this strategy
	*/
	@Override
	public void setActive(boolean active) {
		_strategy.setActive(active);
	}

	@Override
	public void setCLIENT_ID(int _CLIENT_ID) {
		_strategy.setCLIENT_ID(_CLIENT_ID);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_strategy.setCachedModel(cachedModel);
	}

	/**
	* Sets the class name of this strategy.
	*
	* @param className the class name of this strategy
	*/
	@Override
	public void setClassName(java.lang.String className) {
		_strategy.setClassName(className);
	}

	/**
	* Sets the company ID of this strategy.
	*
	* @param companyId the company ID of this strategy
	*/
	@Override
	public void setCompanyId(long companyId) {
		_strategy.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this strategy.
	*
	* @param createDate the create date of this strategy
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_strategy.setCreateDate(createDate);
	}

	/**
	* Sets the description of this strategy.
	*
	* @param description the description of this strategy
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_strategy.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_strategy.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_strategy.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_strategy.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this strategy.
	*
	* @param groupId the group ID of this strategy
	*/
	@Override
	public void setGroupId(long groupId) {
		_strategy.setGroupId(groupId);
	}

	@Override
	public void setIBStrategyAssetEntry(
		com.liferay.asset.kernel.model.AssetEntry _IBStrategyAssetEntry) {
		_strategy.setIBStrategyAssetEntry(_IBStrategyAssetEntry);
	}

	@Override
	public void setIBStrategyParams(
		java.util.List<com.liferay.expando.kernel.model.ExpandoColumn> _IBStrategyParams) {
		_strategy.setIBStrategyParams(_IBStrategyParams);
	}

	@Override
	public void setJsonStrategyShareParams(
		com.liferay.portal.kernel.json.JSONObject _jsonStrategyShareParams) {
		_strategy.setJsonStrategyShareParams(_jsonStrategyShareParams);
	}

	/**
	* Sets the modified date of this strategy.
	*
	* @param modifiedDate the modified date of this strategy
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_strategy.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this strategy.
	*
	* @param name the name of this strategy
	*/
	@Override
	public void setName(java.lang.String name) {
		_strategy.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_strategy.setNew(n);
	}

	/**
	* Sets the primary key of this strategy.
	*
	* @param primaryKey the primary key of this strategy
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_strategy.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_strategy.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this strategy.
	*
	* @param status the status of this strategy
	*/
	@Override
	public void setStatus(int status) {
		_strategy.setStatus(status);
	}

	/**
	* Sets the status by user ID of this strategy.
	*
	* @param statusByUserId the status by user ID of this strategy
	*/
	@Override
	public void setStatusByUserId(long statusByUserId) {
		_strategy.setStatusByUserId(statusByUserId);
	}

	/**
	* Sets the status by user name of this strategy.
	*
	* @param statusByUserName the status by user name of this strategy
	*/
	@Override
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_strategy.setStatusByUserName(statusByUserName);
	}

	/**
	* Sets the status by user uuid of this strategy.
	*
	* @param statusByUserUuid the status by user uuid of this strategy
	*/
	@Override
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_strategy.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Sets the status date of this strategy.
	*
	* @param statusDate the status date of this strategy
	*/
	@Override
	public void setStatusDate(Date statusDate) {
		_strategy.setStatusDate(statusDate);
	}

	/**
	* Sets the strategy i d of this strategy.
	*
	* @param strategyID the strategy i d of this strategy
	*/
	@Override
	public void setStrategyID(long strategyID) {
		_strategy.setStrategyID(strategyID);
	}

	@Override
	public void setTargetContract(com.ib.client.Contract _targetContract) {
		_strategy.setTargetContract(_targetContract);
	}

	@Override
	public void setTargetOrder(com.ib.client.Order targetOrder) {
		_strategy.setTargetOrder(targetOrder);
	}

	/**
	* Sets the type of this strategy.
	*
	* @param type the type of this strategy
	*/
	@Override
	public void setType(java.lang.String type) {
		_strategy.setType(type);
	}

	/**
	* Sets the user ID of this strategy.
	*
	* @param userId the user ID of this strategy
	*/
	@Override
	public void setUserId(long userId) {
		_strategy.setUserId(userId);
	}

	/**
	* Sets the user uuid of this strategy.
	*
	* @param userUuid the user uuid of this strategy
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_strategy.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this strategy.
	*
	* @param uuid the uuid of this strategy
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_strategy.setUuid(uuid);
	}

	@Override
	public void setValidateParamsKeysError(
		java.lang.String validateParamsKeysError) {
		_strategy.setValidateParamsKeysError(validateParamsKeysError);
	}

	@Override
	public void setValueIn(double _valueIn) {
		_strategy.setValueIn(_valueIn);
	}

	@Override
	public void setValueLimitIn(double _valueLimitIn) {
		_strategy.setValueLimitIn(_valueLimitIn);
	}

	@Override
	public void setValueLimitOut(double _valueLimitOut) {
		_strategy.setValueLimitOut(_valueLimitOut);
	}

	@Override
	public void setValueOut(double _valueOut) {
		_strategy.setValueOut(_valueOut);
	}

	@Override
	public void setVerified(boolean verified) {
		_strategy.setVerified(verified);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StrategyWrapper)) {
			return false;
		}

		StrategyWrapper strategyWrapper = (StrategyWrapper)obj;

		if (Objects.equals(_strategy, strategyWrapper._strategy)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _strategy.getStagedModelType();
	}

	@Override
	public Strategy getWrappedModel() {
		return _strategy;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _strategy.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _strategy.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_strategy.resetOriginalValues();
	}

	private final Strategy _strategy;
}