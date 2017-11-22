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

import com.ibtrader.data.model.Market;

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
 * The cache model class for representing Market in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Market
 * @generated
 */
@ProviderType
public class MarketCacheModel implements CacheModel<Market>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MarketCacheModel)) {
			return false;
		}

		MarketCacheModel marketCacheModel = (MarketCacheModel)obj;

		if (marketId == marketCacheModel.marketId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, marketId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", marketId=");
		sb.append(marketId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", active=");
		sb.append(active);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", start_hour=");
		sb.append(start_hour);
		sb.append(", end_hour=");
		sb.append(end_hour);
		sb.append(", identifier=");
		sb.append(identifier);
		sb.append(", currency=");
		sb.append(currency);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Market toEntityModel() {
		MarketImpl marketImpl = new MarketImpl();

		if (uuid == null) {
			marketImpl.setUuid(StringPool.BLANK);
		}
		else {
			marketImpl.setUuid(uuid);
		}

		marketImpl.setMarketId(marketId);
		marketImpl.setGroupId(groupId);
		marketImpl.setCompanyId(companyId);
		marketImpl.setActive(active);

		if (createDate == Long.MIN_VALUE) {
			marketImpl.setCreateDate(null);
		}
		else {
			marketImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			marketImpl.setModifiedDate(null);
		}
		else {
			marketImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (start_hour == null) {
			marketImpl.setStart_hour(StringPool.BLANK);
		}
		else {
			marketImpl.setStart_hour(start_hour);
		}

		if (end_hour == null) {
			marketImpl.setEnd_hour(StringPool.BLANK);
		}
		else {
			marketImpl.setEnd_hour(end_hour);
		}

		if (identifier == null) {
			marketImpl.setIdentifier(StringPool.BLANK);
		}
		else {
			marketImpl.setIdentifier(identifier);
		}

		if (currency == null) {
			marketImpl.setCurrency(StringPool.BLANK);
		}
		else {
			marketImpl.setCurrency(currency);
		}

		if (name == null) {
			marketImpl.setName(StringPool.BLANK);
		}
		else {
			marketImpl.setName(name);
		}

		if (description == null) {
			marketImpl.setDescription(StringPool.BLANK);
		}
		else {
			marketImpl.setDescription(description);
		}

		marketImpl.resetOriginalValues();

		return marketImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		marketId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		active = objectInput.readBoolean();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		start_hour = objectInput.readUTF();
		end_hour = objectInput.readUTF();
		identifier = objectInput.readUTF();
		currency = objectInput.readUTF();
		name = objectInput.readUTF();
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

		objectOutput.writeLong(marketId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeBoolean(active);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (start_hour == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(start_hour);
		}

		if (end_hour == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(end_hour);
		}

		if (identifier == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(identifier);
		}

		if (currency == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(currency);
		}

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
	}

	public String uuid;
	public long marketId;
	public long groupId;
	public long companyId;
	public boolean active;
	public long createDate;
	public long modifiedDate;
	public String start_hour;
	public String end_hour;
	public String identifier;
	public String currency;
	public String name;
	public String description;
}