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

import com.ibtrader.data.model.BackTesting;

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
 * The cache model class for representing BackTesting in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see BackTesting
 * @generated
 */
@ProviderType
public class BackTestingCacheModel implements CacheModel<BackTesting>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BackTestingCacheModel)) {
			return false;
		}

		BackTestingCacheModel backTestingCacheModel = (BackTestingCacheModel)obj;

		if (backTId == backTestingCacheModel.backTId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, backTId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", backTId=");
		sb.append(backTId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", fromDate=");
		sb.append(fromDate);
		sb.append(", toDate=");
		sb.append(toDate);
		sb.append(", shareId=");
		sb.append(shareId);
		sb.append(", countordersBUY=");
		sb.append(countordersBUY);
		sb.append(", countordersSELL=");
		sb.append(countordersSELL);
		sb.append(", profitordersBUY=");
		sb.append(profitordersBUY);
		sb.append(", profitordersSELL=");
		sb.append(profitordersSELL);
		sb.append(", status=");
		sb.append(status);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BackTesting toEntityModel() {
		BackTestingImpl backTestingImpl = new BackTestingImpl();

		if (uuid == null) {
			backTestingImpl.setUuid(StringPool.BLANK);
		}
		else {
			backTestingImpl.setUuid(uuid);
		}

		backTestingImpl.setBackTId(backTId);
		backTestingImpl.setGroupId(groupId);
		backTestingImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			backTestingImpl.setCreateDate(null);
		}
		else {
			backTestingImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			backTestingImpl.setModifiedDate(null);
		}
		else {
			backTestingImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (fromDate == Long.MIN_VALUE) {
			backTestingImpl.setFromDate(null);
		}
		else {
			backTestingImpl.setFromDate(new Date(fromDate));
		}

		if (toDate == Long.MIN_VALUE) {
			backTestingImpl.setToDate(null);
		}
		else {
			backTestingImpl.setToDate(new Date(toDate));
		}

		backTestingImpl.setShareId(shareId);
		backTestingImpl.setCountordersBUY(countordersBUY);
		backTestingImpl.setCountordersSELL(countordersSELL);
		backTestingImpl.setProfitordersBUY(profitordersBUY);
		backTestingImpl.setProfitordersSELL(profitordersSELL);

		if (status == null) {
			backTestingImpl.setStatus(StringPool.BLANK);
		}
		else {
			backTestingImpl.setStatus(status);
		}

		if (description == null) {
			backTestingImpl.setDescription(StringPool.BLANK);
		}
		else {
			backTestingImpl.setDescription(description);
		}

		backTestingImpl.resetOriginalValues();

		return backTestingImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		backTId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		fromDate = objectInput.readLong();
		toDate = objectInput.readLong();

		shareId = objectInput.readLong();

		countordersBUY = objectInput.readLong();

		countordersSELL = objectInput.readLong();

		profitordersBUY = objectInput.readDouble();

		profitordersSELL = objectInput.readDouble();
		status = objectInput.readUTF();
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

		objectOutput.writeLong(backTId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(fromDate);
		objectOutput.writeLong(toDate);

		objectOutput.writeLong(shareId);

		objectOutput.writeLong(countordersBUY);

		objectOutput.writeLong(countordersSELL);

		objectOutput.writeDouble(profitordersBUY);

		objectOutput.writeDouble(profitordersSELL);

		if (status == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(status);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public String uuid;
	public long backTId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long fromDate;
	public long toDate;
	public long shareId;
	public long countordersBUY;
	public long countordersSELL;
	public double profitordersBUY;
	public double profitordersSELL;
	public String status;
	public String description;
}