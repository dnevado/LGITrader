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

import com.ibtrader.data.model.AuditIndicatorsStrategy;
import com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing AuditIndicatorsStrategy in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AuditIndicatorsStrategy
 * @generated
 */
@ProviderType
public class AuditIndicatorsStrategyCacheModel implements CacheModel<AuditIndicatorsStrategy>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditIndicatorsStrategyCacheModel)) {
			return false;
		}

		AuditIndicatorsStrategyCacheModel auditIndicatorsStrategyCacheModel = (AuditIndicatorsStrategyCacheModel)obj;

		if (auditIndicatorsStrategyPK.equals(
					auditIndicatorsStrategyCacheModel.auditIndicatorsStrategyPK)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, auditIndicatorsStrategyPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", auditDate=");
		sb.append(auditDate);
		sb.append(", auditstrategy=");
		sb.append(auditstrategy);
		sb.append(", shareId=");
		sb.append(shareId);
		sb.append(", auditData=");
		sb.append(auditData);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AuditIndicatorsStrategy toEntityModel() {
		AuditIndicatorsStrategyImpl auditIndicatorsStrategyImpl = new AuditIndicatorsStrategyImpl();

		if (uuid == null) {
			auditIndicatorsStrategyImpl.setUuid(StringPool.BLANK);
		}
		else {
			auditIndicatorsStrategyImpl.setUuid(uuid);
		}

		auditIndicatorsStrategyImpl.setGroupId(groupId);
		auditIndicatorsStrategyImpl.setCompanyId(companyId);

		if (auditDate == null) {
			auditIndicatorsStrategyImpl.setAuditDate(StringPool.BLANK);
		}
		else {
			auditIndicatorsStrategyImpl.setAuditDate(auditDate);
		}

		if (auditstrategy == null) {
			auditIndicatorsStrategyImpl.setAuditstrategy(StringPool.BLANK);
		}
		else {
			auditIndicatorsStrategyImpl.setAuditstrategy(auditstrategy);
		}

		auditIndicatorsStrategyImpl.setShareId(shareId);

		if (auditData == null) {
			auditIndicatorsStrategyImpl.setAuditData(StringPool.BLANK);
		}
		else {
			auditIndicatorsStrategyImpl.setAuditData(auditData);
		}

		auditIndicatorsStrategyImpl.resetOriginalValues();

		return auditIndicatorsStrategyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		auditDate = objectInput.readUTF();
		auditstrategy = objectInput.readUTF();

		shareId = objectInput.readLong();
		auditData = objectInput.readUTF();

		auditIndicatorsStrategyPK = new AuditIndicatorsStrategyPK(groupId,
				companyId, auditDate, auditstrategy, shareId);
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

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		if (auditDate == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(auditDate);
		}

		if (auditstrategy == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(auditstrategy);
		}

		objectOutput.writeLong(shareId);

		if (auditData == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(auditData);
		}
	}

	public String uuid;
	public long groupId;
	public long companyId;
	public String auditDate;
	public String auditstrategy;
	public long shareId;
	public String auditData;
	public transient AuditIndicatorsStrategyPK auditIndicatorsStrategyPK;
}