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

package com.ibtrader.data.service.persistence.impl;

import com.ibtrader.data.model.HistoricalRealtime;
import com.ibtrader.data.service.persistence.HistoricalRealtimePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class HistoricalRealtimeFinderBaseImpl extends BasePersistenceImpl<HistoricalRealtime> {
	@Override
	public Set<String> getBadColumnNames() {
		return getHistoricalRealtimePersistence().getBadColumnNames();
	}

	/**
	 * Returns the historical realtime persistence.
	 *
	 * @return the historical realtime persistence
	 */
	public HistoricalRealtimePersistence getHistoricalRealtimePersistence() {
		return historicalRealtimePersistence;
	}

	/**
	 * Sets the historical realtime persistence.
	 *
	 * @param historicalRealtimePersistence the historical realtime persistence
	 */
	public void setHistoricalRealtimePersistence(
		HistoricalRealtimePersistence historicalRealtimePersistence) {
		this.historicalRealtimePersistence = historicalRealtimePersistence;
	}

	@BeanReference(type = HistoricalRealtimePersistence.class)
	protected HistoricalRealtimePersistence historicalRealtimePersistence;
}