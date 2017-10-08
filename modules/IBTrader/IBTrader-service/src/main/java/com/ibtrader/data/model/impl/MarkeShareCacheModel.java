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

import com.ibtrader.data.model.MarkeShare;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing MarkeShare in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MarkeShare
 * @generated
 */
@ProviderType
public class MarkeShareCacheModel implements CacheModel<MarkeShare>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MarkeShareCacheModel)) {
			return false;
		}

		MarkeShareCacheModel markeShareCacheModel = (MarkeShareCacheModel)obj;

		if (marketshareId == markeShareCacheModel.marketshareId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, marketshareId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", marketshareId=");
		sb.append(marketshareId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", shareId=");
		sb.append(shareId);
		sb.append(", marketId=");
		sb.append(marketId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MarkeShare toEntityModel() {
		MarkeShareImpl markeShareImpl = new MarkeShareImpl();

		if (uuid == null) {
			markeShareImpl.setUuid(StringPool.BLANK);
		}
		else {
			markeShareImpl.setUuid(uuid);
		}

		markeShareImpl.setMarketshareId(marketshareId);
		markeShareImpl.setGroupId(groupId);
		markeShareImpl.setCompanyId(companyId);
		markeShareImpl.setShareId(shareId);
		markeShareImpl.setMarketId(marketId);

		markeShareImpl.resetOriginalValues();

		return markeShareImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		marketshareId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		shareId = objectInput.readLong();

		marketId = objectInput.readLong();
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

		objectOutput.writeLong(marketshareId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(shareId);

		objectOutput.writeLong(marketId);
	}

	public String uuid;
	public long marketshareId;
	public long groupId;
	public long companyId;
	public long shareId;
	public long marketId;
}