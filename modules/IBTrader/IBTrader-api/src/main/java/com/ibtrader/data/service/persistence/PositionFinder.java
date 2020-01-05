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

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface PositionFinder {
	public java.util.List<com.ibtrader.data.model.Position> getIntradiaPositions(
		java.util.Date to, long groupId, long companyId,
		java.lang.String positionMode);

	public java.util.List getPositionClosedResults(java.util.Date from,
		java.util.Date to, long groupId, long companyId,
		java.lang.String positionMode, long backtestingId);

	public java.util.List getPositionOpenResults(java.util.Date to,
		long groupId, long companyId, java.lang.String positionMode);

	public java.util.List getCurrentLiquidTraded(long groupId, long companyId,
		java.lang.String positionMode);

	public java.util.List getDayTradingPatternPositions(java.util.Date from,
		java.util.Date to, long groupId, long companyId,
		java.lang.String positionMode, java.lang.String positionState);
}