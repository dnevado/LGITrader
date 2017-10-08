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

import com.ibtrader.data.model.IBOrder;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing IBOrder in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see IBOrder
 * @generated
 */
@ProviderType
public class IBOrderCacheModel implements CacheModel<IBOrder>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IBOrderCacheModel)) {
			return false;
		}

		IBOrderCacheModel ibOrderCacheModel = (IBOrderCacheModel)obj;

		if (ordersId == ibOrderCacheModel.ordersId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, ordersId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", ordersId=");
		sb.append(ordersId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", orderID=");
		sb.append(orderID);
		sb.append(", shareID=");
		sb.append(shareID);
		sb.append(", checked=");
		sb.append(checked);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public IBOrder toEntityModel() {
		IBOrderImpl ibOrderImpl = new IBOrderImpl();

		if (uuid == null) {
			ibOrderImpl.setUuid(StringPool.BLANK);
		}
		else {
			ibOrderImpl.setUuid(uuid);
		}

		ibOrderImpl.setOrdersId(ordersId);
		ibOrderImpl.setGroupId(groupId);
		ibOrderImpl.setCompanyId(companyId);
		ibOrderImpl.setOrderID(orderID);
		ibOrderImpl.setShareID(shareID);
		ibOrderImpl.setChecked(checked);

		ibOrderImpl.resetOriginalValues();

		return ibOrderImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		ordersId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		orderID = objectInput.readLong();

		shareID = objectInput.readLong();

		checked = objectInput.readBoolean();
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

		objectOutput.writeLong(ordersId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(orderID);

		objectOutput.writeLong(shareID);

		objectOutput.writeBoolean(checked);
	}

	public String uuid;
	public long ordersId;
	public long groupId;
	public long companyId;
	public long orderID;
	public long shareID;
	public boolean checked;
}