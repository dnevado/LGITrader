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

import com.ibtrader.data.model.Config;

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
 * The cache model class for representing Config in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Config
 * @generated
 */
@ProviderType
public class ConfigCacheModel implements CacheModel<Config>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ConfigCacheModel)) {
			return false;
		}

		ConfigCacheModel configCacheModel = (ConfigCacheModel)obj;

		if (configId == configCacheModel.configId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, configId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", configId=");
		sb.append(configId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", value=");
		sb.append(value);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", globaldefault=");
		sb.append(globaldefault);
		sb.append(", iscron=");
		sb.append(iscron);
		sb.append(", config_key=");
		sb.append(config_key);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Config toEntityModel() {
		ConfigImpl configImpl = new ConfigImpl();

		if (uuid == null) {
			configImpl.setUuid(StringPool.BLANK);
		}
		else {
			configImpl.setUuid(uuid);
		}

		configImpl.setConfigId(configId);
		configImpl.setGroupId(groupId);
		configImpl.setCompanyId(companyId);

		if (name == null) {
			configImpl.setName(StringPool.BLANK);
		}
		else {
			configImpl.setName(name);
		}

		if (value == null) {
			configImpl.setValue(StringPool.BLANK);
		}
		else {
			configImpl.setValue(value);
		}

		if (createDate == Long.MIN_VALUE) {
			configImpl.setCreateDate(null);
		}
		else {
			configImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			configImpl.setModifiedDate(null);
		}
		else {
			configImpl.setModifiedDate(new Date(modifiedDate));
		}

		configImpl.setGlobaldefault(globaldefault);
		configImpl.setIscron(iscron);

		if (config_key == null) {
			configImpl.setConfig_key(StringPool.BLANK);
		}
		else {
			configImpl.setConfig_key(config_key);
		}

		if (description == null) {
			configImpl.setDescription(StringPool.BLANK);
		}
		else {
			configImpl.setDescription(description);
		}

		configImpl.resetOriginalValues();

		return configImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		configId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		name = objectInput.readUTF();
		value = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		globaldefault = objectInput.readBoolean();

		iscron = objectInput.readBoolean();
		config_key = objectInput.readUTF();
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

		objectOutput.writeLong(configId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (value == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(value);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeBoolean(globaldefault);

		objectOutput.writeBoolean(iscron);

		if (config_key == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(config_key);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public String uuid;
	public long configId;
	public long groupId;
	public long companyId;
	public String name;
	public String value;
	public long createDate;
	public long modifiedDate;
	public boolean globaldefault;
	public boolean iscron;
	public String config_key;
	public String description;
}