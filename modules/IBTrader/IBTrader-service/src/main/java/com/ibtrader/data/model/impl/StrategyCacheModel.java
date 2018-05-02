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

import com.ibtrader.data.model.Strategy;

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
 * The cache model class for representing Strategy in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Strategy
 * @generated
 */
@ProviderType
public class StrategyCacheModel implements CacheModel<Strategy>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StrategyCacheModel)) {
			return false;
		}

		StrategyCacheModel strategyCacheModel = (StrategyCacheModel)obj;

		if (strategyID == strategyCacheModel.strategyID) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, strategyID);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", strategyID=");
		sb.append(strategyID);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", active=");
		sb.append(active);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", type=");
		sb.append(type);
		sb.append(", can_override_params=");
		sb.append(can_override_params);
		sb.append(", className=");
		sb.append(className);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", visible=");
		sb.append(visible);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Strategy toEntityModel() {
		StrategyImpl strategyImpl = new StrategyImpl();

		if (uuid == null) {
			strategyImpl.setUuid(StringPool.BLANK);
		}
		else {
			strategyImpl.setUuid(uuid);
		}

		strategyImpl.setStrategyID(strategyID);
		strategyImpl.setGroupId(groupId);
		strategyImpl.setCompanyId(companyId);

		if (name == null) {
			strategyImpl.setName(StringPool.BLANK);
		}
		else {
			strategyImpl.setName(name);
		}

		if (description == null) {
			strategyImpl.setDescription(StringPool.BLANK);
		}
		else {
			strategyImpl.setDescription(description);
		}

		if (createDate == Long.MIN_VALUE) {
			strategyImpl.setCreateDate(null);
		}
		else {
			strategyImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			strategyImpl.setModifiedDate(null);
		}
		else {
			strategyImpl.setModifiedDate(new Date(modifiedDate));
		}

		strategyImpl.setActive(active);
		strategyImpl.setStatus(status);
		strategyImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			strategyImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			strategyImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			strategyImpl.setStatusDate(null);
		}
		else {
			strategyImpl.setStatusDate(new Date(statusDate));
		}

		if (type == null) {
			strategyImpl.setType(StringPool.BLANK);
		}
		else {
			strategyImpl.setType(type);
		}

		strategyImpl.setCan_override_params(can_override_params);

		if (className == null) {
			strategyImpl.setClassName(StringPool.BLANK);
		}
		else {
			strategyImpl.setClassName(className);
		}

		strategyImpl.setUserId(userId);
		strategyImpl.setVisible(visible);

		strategyImpl.resetOriginalValues();

		return strategyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		strategyID = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		active = objectInput.readBoolean();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
		type = objectInput.readUTF();

		can_override_params = objectInput.readBoolean();
		className = objectInput.readUTF();

		userId = objectInput.readLong();

		visible = objectInput.readBoolean();
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

		objectOutput.writeLong(strategyID);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeBoolean(active);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeBoolean(can_override_params);

		if (className == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(className);
		}

		objectOutput.writeLong(userId);

		objectOutput.writeBoolean(visible);
	}

	public String uuid;
	public long strategyID;
	public long groupId;
	public long companyId;
	public String name;
	public String description;
	public long createDate;
	public long modifiedDate;
	public boolean active;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String type;
	public boolean can_override_params;
	public String className;
	public long userId;
	public boolean visible;
}