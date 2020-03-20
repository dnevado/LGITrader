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

package com.ibtrader.data.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AuditIndicatorsStrategyPK implements Comparable<AuditIndicatorsStrategyPK>,
	Serializable {
	public long groupId;
	public long companyId;
	public String auditDate;
	public String auditstrategy;
	public long shareId;

	public AuditIndicatorsStrategyPK() {
	}

	public AuditIndicatorsStrategyPK(long groupId, long companyId,
		String auditDate, String auditstrategy, long shareId) {
		this.groupId = groupId;
		this.companyId = companyId;
		this.auditDate = auditDate;
		this.auditstrategy = auditstrategy;
		this.shareId = shareId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditstrategy() {
		return auditstrategy;
	}

	public void setAuditstrategy(String auditstrategy) {
		this.auditstrategy = auditstrategy;
	}

	public long getShareId() {
		return shareId;
	}

	public void setShareId(long shareId) {
		this.shareId = shareId;
	}

	@Override
	public int compareTo(AuditIndicatorsStrategyPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (groupId < pk.groupId) {
			value = -1;
		}
		else if (groupId > pk.groupId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (companyId < pk.companyId) {
			value = -1;
		}
		else if (companyId > pk.companyId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = auditDate.compareTo(pk.auditDate);

		if (value != 0) {
			return value;
		}

		value = auditstrategy.compareTo(pk.auditstrategy);

		if (value != 0) {
			return value;
		}

		if (shareId < pk.shareId) {
			value = -1;
		}
		else if (shareId > pk.shareId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditIndicatorsStrategyPK)) {
			return false;
		}

		AuditIndicatorsStrategyPK pk = (AuditIndicatorsStrategyPK)obj;

		if ((groupId == pk.groupId) && (companyId == pk.companyId) &&
				(auditDate.equals(pk.auditDate)) &&
				(auditstrategy.equals(pk.auditstrategy)) &&
				(shareId == pk.shareId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, groupId);
		hashCode = HashUtil.hash(hashCode, companyId);
		hashCode = HashUtil.hash(hashCode, auditDate);
		hashCode = HashUtil.hash(hashCode, auditstrategy);
		hashCode = HashUtil.hash(hashCode, shareId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("groupId");
		sb.append(StringPool.EQUAL);
		sb.append(groupId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("companyId");
		sb.append(StringPool.EQUAL);
		sb.append(companyId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("auditDate");
		sb.append(StringPool.EQUAL);
		sb.append(auditDate);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("auditstrategy");
		sb.append(StringPool.EQUAL);
		sb.append(auditstrategy);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("shareId");
		sb.append(StringPool.EQUAL);
		sb.append(shareId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}