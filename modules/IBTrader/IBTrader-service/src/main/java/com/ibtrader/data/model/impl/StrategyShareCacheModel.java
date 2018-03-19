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

import com.ibtrader.data.model.StrategyShare;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing StrategyShare in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see StrategyShare
 * @generated
 */
@ProviderType
public class StrategyShareCacheModel implements CacheModel<StrategyShare>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StrategyShareCacheModel)) {
			return false;
		}

		StrategyShareCacheModel strategyShareCacheModel = (StrategyShareCacheModel)obj;

		if (strategyshareId == strategyShareCacheModel.strategyshareId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, strategyshareId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", strategyshareId=");
		sb.append(strategyshareId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", strategyId=");
		sb.append(strategyId);
		sb.append(", shareId=");
		sb.append(shareId);
		sb.append(", active=");
		sb.append(active);
		sb.append(", strategyparamsoverride=");
		sb.append(strategyparamsoverride);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StrategyShare toEntityModel() {
		StrategyShareImpl strategyShareImpl = new StrategyShareImpl();

		if (uuid == null) {
			strategyShareImpl.setUuid(StringPool.BLANK);
		}
		else {
			strategyShareImpl.setUuid(uuid);
		}

		strategyShareImpl.setStrategyshareId(strategyshareId);
		strategyShareImpl.setGroupId(groupId);
		strategyShareImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			strategyShareImpl.setCreateDate(null);
		}
		else {
			strategyShareImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			strategyShareImpl.setModifiedDate(null);
		}
		else {
			strategyShareImpl.setModifiedDate(new Date(modifiedDate));
		}

		strategyShareImpl.setStrategyId(strategyId);
		strategyShareImpl.setShareId(shareId);
		strategyShareImpl.setActive(active);

		if (strategyparamsoverride == null) {
			strategyShareImpl.setStrategyparamsoverride(StringPool.BLANK);
		}
		else {
			strategyShareImpl.setStrategyparamsoverride(strategyparamsoverride);
		}

		if (description == null) {
			strategyShareImpl.setDescription(StringPool.BLANK);
		}
		else {
			strategyShareImpl.setDescription(description);
		}

		strategyShareImpl.resetOriginalValues();

		return strategyShareImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		strategyshareId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		strategyId = objectInput.readLong();

		shareId = objectInput.readLong();

		active = objectInput.readBoolean();
		strategyparamsoverride = objectInput.readUTF();
		description = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(strategyshareId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(strategyId);

		objectOutput.writeLong(shareId);

		objectOutput.writeBoolean(active);

		if (strategyparamsoverride == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(strategyparamsoverride);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public String uuid;
	public long strategyshareId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long strategyId;
	public long shareId;
	public boolean active;
	public String strategyparamsoverride;
	public String description;
}