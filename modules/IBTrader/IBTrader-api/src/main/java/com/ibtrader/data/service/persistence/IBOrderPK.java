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
public class IBOrderPK implements Comparable<IBOrderPK>, Serializable {
	public long ordersId;
	public long groupId;
	public long companyId;

	public IBOrderPK() {
	}

	public IBOrderPK(long ordersId, long groupId, long companyId) {
		this.ordersId = ordersId;
		this.groupId = groupId;
		this.companyId = companyId;
	}

	public long getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(long ordersId) {
		this.ordersId = ordersId;
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

	@Override
	public int compareTo(IBOrderPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (ordersId < pk.ordersId) {
			value = -1;
		}
		else if (ordersId > pk.ordersId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

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

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IBOrderPK)) {
			return false;
		}

		IBOrderPK pk = (IBOrderPK)obj;

		if ((ordersId == pk.ordersId) && (groupId == pk.groupId) &&
				(companyId == pk.companyId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, ordersId);
		hashCode = HashUtil.hash(hashCode, groupId);
		hashCode = HashUtil.hash(hashCode, companyId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("ordersId");
		sb.append(StringPool.EQUAL);
		sb.append(ordersId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("groupId");
		sb.append(StringPool.EQUAL);
		sb.append(groupId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("companyId");
		sb.append(StringPool.EQUAL);
		sb.append(companyId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}