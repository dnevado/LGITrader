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

import com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the AuditIndicatorsStrategy service. Represents a row in the &quot;ibtrader_AuditIndicatorsStrategy&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ibtrader.data.model.impl.AuditIndicatorsStrategyModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ibtrader.data.model.impl.AuditIndicatorsStrategyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditIndicatorsStrategy
 * @see com.ibtrader.data.model.impl.AuditIndicatorsStrategyImpl
 * @see com.ibtrader.data.model.impl.AuditIndicatorsStrategyModelImpl
 * @generated
 */
@ProviderType
public interface AuditIndicatorsStrategyModel extends BaseModel<AuditIndicatorsStrategy>,
	ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a audit indicators strategy model instance should use the {@link AuditIndicatorsStrategy} interface instead.
	 */

	/**
	 * Returns the primary key of this audit indicators strategy.
	 *
	 * @return the primary key of this audit indicators strategy
	 */
	public AuditIndicatorsStrategyPK getPrimaryKey();

	/**
	 * Sets the primary key of this audit indicators strategy.
	 *
	 * @param primaryKey the primary key of this audit indicators strategy
	 */
	public void setPrimaryKey(AuditIndicatorsStrategyPK primaryKey);

	/**
	 * Returns the uuid of this audit indicators strategy.
	 *
	 * @return the uuid of this audit indicators strategy
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this audit indicators strategy.
	 *
	 * @param uuid the uuid of this audit indicators strategy
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the group ID of this audit indicators strategy.
	 *
	 * @return the group ID of this audit indicators strategy
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this audit indicators strategy.
	 *
	 * @param groupId the group ID of this audit indicators strategy
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this audit indicators strategy.
	 *
	 * @return the company ID of this audit indicators strategy
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this audit indicators strategy.
	 *
	 * @param companyId the company ID of this audit indicators strategy
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the audit date of this audit indicators strategy.
	 *
	 * @return the audit date of this audit indicators strategy
	 */
	@AutoEscape
	public String getAuditDate();

	/**
	 * Sets the audit date of this audit indicators strategy.
	 *
	 * @param auditDate the audit date of this audit indicators strategy
	 */
	public void setAuditDate(String auditDate);

	/**
	 * Returns the auditstrategy of this audit indicators strategy.
	 *
	 * @return the auditstrategy of this audit indicators strategy
	 */
	@AutoEscape
	public String getAuditstrategy();

	/**
	 * Sets the auditstrategy of this audit indicators strategy.
	 *
	 * @param auditstrategy the auditstrategy of this audit indicators strategy
	 */
	public void setAuditstrategy(String auditstrategy);

	/**
	 * Returns the share ID of this audit indicators strategy.
	 *
	 * @return the share ID of this audit indicators strategy
	 */
	public long getShareId();

	/**
	 * Sets the share ID of this audit indicators strategy.
	 *
	 * @param shareId the share ID of this audit indicators strategy
	 */
	public void setShareId(long shareId);

	/**
	 * Returns the audit data of this audit indicators strategy.
	 *
	 * @return the audit data of this audit indicators strategy
	 */
	@AutoEscape
	public String getAuditData();

	/**
	 * Sets the audit data of this audit indicators strategy.
	 *
	 * @param auditData the audit data of this audit indicators strategy
	 */
	public void setAuditData(String auditData);

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
	public int compareTo(AuditIndicatorsStrategy auditIndicatorsStrategy);

	@Override
	public int hashCode();

	@Override
	public CacheModel<AuditIndicatorsStrategy> toCacheModel();

	@Override
	public AuditIndicatorsStrategy toEscapedModel();

	@Override
	public AuditIndicatorsStrategy toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}