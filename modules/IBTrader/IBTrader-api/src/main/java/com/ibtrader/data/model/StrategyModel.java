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
import com.liferay.portal.kernel.model.WorkflowedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Strategy service. Represents a row in the &quot;ibtrader_Strategy&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ibtrader.data.model.impl.StrategyModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ibtrader.data.model.impl.StrategyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Strategy
 * @see com.ibtrader.data.model.impl.StrategyImpl
 * @see com.ibtrader.data.model.impl.StrategyModelImpl
 * @generated
 */
@ProviderType
public interface StrategyModel extends BaseModel<Strategy>, ShardedModel,
	StagedModel, WorkflowedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a strategy model instance should use the {@link Strategy} interface instead.
	 */

	/**
	 * Returns the primary key of this strategy.
	 *
	 * @return the primary key of this strategy
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this strategy.
	 *
	 * @param primaryKey the primary key of this strategy
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this strategy.
	 *
	 * @return the uuid of this strategy
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this strategy.
	 *
	 * @param uuid the uuid of this strategy
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the strategy i d of this strategy.
	 *
	 * @return the strategy i d of this strategy
	 */
	public long getStrategyID();

	/**
	 * Sets the strategy i d of this strategy.
	 *
	 * @param strategyID the strategy i d of this strategy
	 */
	public void setStrategyID(long strategyID);

	/**
	 * Returns the group ID of this strategy.
	 *
	 * @return the group ID of this strategy
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this strategy.
	 *
	 * @param groupId the group ID of this strategy
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this strategy.
	 *
	 * @return the company ID of this strategy
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this strategy.
	 *
	 * @param companyId the company ID of this strategy
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the name of this strategy.
	 *
	 * @return the name of this strategy
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this strategy.
	 *
	 * @param name the name of this strategy
	 */
	public void setName(String name);

	/**
	 * Returns the description of this strategy.
	 *
	 * @return the description of this strategy
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this strategy.
	 *
	 * @param description the description of this strategy
	 */
	public void setDescription(String description);

	/**
	 * Returns the create date of this strategy.
	 *
	 * @return the create date of this strategy
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this strategy.
	 *
	 * @param createDate the create date of this strategy
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this strategy.
	 *
	 * @return the modified date of this strategy
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this strategy.
	 *
	 * @param modifiedDate the modified date of this strategy
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the active of this strategy.
	 *
	 * @return the active of this strategy
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this strategy is active.
	 *
	 * @return <code>true</code> if this strategy is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this strategy is active.
	 *
	 * @param active the active of this strategy
	 */
	public void setActive(boolean active);

	/**
	 * Returns the status of this strategy.
	 *
	 * @return the status of this strategy
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this strategy.
	 *
	 * @param status the status of this strategy
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this strategy.
	 *
	 * @return the status by user ID of this strategy
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this strategy.
	 *
	 * @param statusByUserId the status by user ID of this strategy
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this strategy.
	 *
	 * @return the status by user uuid of this strategy
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this strategy.
	 *
	 * @param statusByUserUuid the status by user uuid of this strategy
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this strategy.
	 *
	 * @return the status by user name of this strategy
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this strategy.
	 *
	 * @param statusByUserName the status by user name of this strategy
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this strategy.
	 *
	 * @return the status date of this strategy
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this strategy.
	 *
	 * @param statusDate the status date of this strategy
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the type of this strategy.
	 *
	 * @return the type of this strategy
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this strategy.
	 *
	 * @param type the type of this strategy
	 */
	public void setType(String type);

	/**
	 * Returns the class name of this strategy.
	 *
	 * @return the class name of this strategy
	 */
	@AutoEscape
	public String getClassName();

	/**
	 * Sets the class name of this strategy.
	 *
	 * @param className the class name of this strategy
	 */
	public void setClassName(String className);

	/**
	 * Returns the user ID of this strategy.
	 *
	 * @return the user ID of this strategy
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this strategy.
	 *
	 * @param userId the user ID of this strategy
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this strategy.
	 *
	 * @return the user uuid of this strategy
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this strategy.
	 *
	 * @param userUuid the user uuid of this strategy
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns <code>true</code> if this strategy is approved.
	 *
	 * @return <code>true</code> if this strategy is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this strategy is denied.
	 *
	 * @return <code>true</code> if this strategy is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this strategy is a draft.
	 *
	 * @return <code>true</code> if this strategy is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this strategy is expired.
	 *
	 * @return <code>true</code> if this strategy is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this strategy is inactive.
	 *
	 * @return <code>true</code> if this strategy is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this strategy is incomplete.
	 *
	 * @return <code>true</code> if this strategy is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this strategy is pending.
	 *
	 * @return <code>true</code> if this strategy is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this strategy is scheduled.
	 *
	 * @return <code>true</code> if this strategy is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

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
	public int compareTo(Strategy strategy);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Strategy> toCacheModel();

	@Override
	public Strategy toEscapedModel();

	@Override
	public Strategy toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}