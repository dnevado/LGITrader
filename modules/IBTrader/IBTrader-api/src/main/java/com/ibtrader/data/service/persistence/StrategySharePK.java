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
public class StrategySharePK implements Comparable<StrategySharePK>,
	Serializable {
	public long strategyId;
	public long shareId;

	public StrategySharePK() {
	}

	public StrategySharePK(long strategyId, long shareId) {
		this.strategyId = strategyId;
		this.shareId = shareId;
	}

	public long getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(long strategyId) {
		this.strategyId = strategyId;
	}

	public long getShareId() {
		return shareId;
	}

	public void setShareId(long shareId) {
		this.shareId = shareId;
	}

	@Override
	public int compareTo(StrategySharePK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (strategyId < pk.strategyId) {
			value = -1;
		}
		else if (strategyId > pk.strategyId) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof StrategySharePK)) {
			return false;
		}

		StrategySharePK pk = (StrategySharePK)obj;

		if ((strategyId == pk.strategyId) && (shareId == pk.shareId)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, strategyId);
		hashCode = HashUtil.hash(hashCode, shareId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("strategyId");
		sb.append(StringPool.EQUAL);
		sb.append(strategyId);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("shareId");
		sb.append(StringPool.EQUAL);
		sb.append(shareId);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}