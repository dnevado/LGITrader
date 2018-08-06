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

import com.ibtrader.data.model.Realtime;

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
 * The cache model class for representing Realtime in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Realtime
 * @generated
 */
@ProviderType
public class RealtimeCacheModel implements CacheModel<Realtime>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RealtimeCacheModel)) {
			return false;
		}

		RealtimeCacheModel realtimeCacheModel = (RealtimeCacheModel)obj;

		if (realtimeId == realtimeCacheModel.realtimeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, realtimeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", realtimeId=");
		sb.append(realtimeId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", shareId=");
		sb.append(shareId);
		sb.append(", value=");
		sb.append(value);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", max_value=");
		sb.append(max_value);
		sb.append(", min_value=");
		sb.append(min_value);
		sb.append(", volume=");
		sb.append(volume);
		sb.append(", avg_volume=");
		sb.append(avg_volume);
		sb.append(", closeprice=");
		sb.append(closeprice);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Realtime toEntityModel() {
		RealtimeImpl realtimeImpl = new RealtimeImpl();

		if (uuid == null) {
			realtimeImpl.setUuid(StringPool.BLANK);
		}
		else {
			realtimeImpl.setUuid(uuid);
		}

		realtimeImpl.setRealtimeId(realtimeId);
		realtimeImpl.setGroupId(groupId);
		realtimeImpl.setCompanyId(companyId);
		realtimeImpl.setShareId(shareId);
		realtimeImpl.setValue(value);

		if (createDate == Long.MIN_VALUE) {
			realtimeImpl.setCreateDate(null);
		}
		else {
			realtimeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			realtimeImpl.setModifiedDate(null);
		}
		else {
			realtimeImpl.setModifiedDate(new Date(modifiedDate));
		}

		realtimeImpl.setMax_value(max_value);
		realtimeImpl.setMin_value(min_value);
		realtimeImpl.setVolume(volume);
		realtimeImpl.setAvg_volume(avg_volume);
		realtimeImpl.setCloseprice(closeprice);

		realtimeImpl.resetOriginalValues();

		return realtimeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		realtimeId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		shareId = objectInput.readLong();

		value = objectInput.readDouble();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		max_value = objectInput.readDouble();

		min_value = objectInput.readDouble();

		volume = objectInput.readInt();

		avg_volume = objectInput.readInt();

		closeprice = objectInput.readBoolean();
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

		objectOutput.writeLong(realtimeId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(shareId);

		objectOutput.writeDouble(value);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeDouble(max_value);

		objectOutput.writeDouble(min_value);

		objectOutput.writeInt(volume);

		objectOutput.writeInt(avg_volume);

		objectOutput.writeBoolean(closeprice);
	}

	public String uuid;
	public long realtimeId;
	public long groupId;
	public long companyId;
	public long shareId;
	public double value;
	public long createDate;
	public long modifiedDate;
	public double max_value;
	public double min_value;
	public int volume;
	public int avg_volume;
	public boolean closeprice;
}