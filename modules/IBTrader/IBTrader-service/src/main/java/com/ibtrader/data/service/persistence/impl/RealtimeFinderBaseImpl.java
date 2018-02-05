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

import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.service.persistence.RealtimePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RealtimeFinderBaseImpl extends BasePersistenceImpl<Realtime> {
	@Override
	public Set<String> getBadColumnNames() {
		return getRealtimePersistence().getBadColumnNames();
	}

	/**
	 * Returns the realtime persistence.
	 *
	 * @return the realtime persistence
	 */
	public RealtimePersistence getRealtimePersistence() {
		return realtimePersistence;
	}

	/**
	 * Sets the realtime persistence.
	 *
	 * @param realtimePersistence the realtime persistence
	 */
	public void setRealtimePersistence(RealtimePersistence realtimePersistence) {
		this.realtimePersistence = realtimePersistence;
	}

	@BeanReference(type = RealtimePersistence.class)
	protected RealtimePersistence realtimePersistence;
}