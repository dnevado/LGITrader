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

package com.ibtrader.data.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Realtime service. Represents a row in the &quot;ibtrader_Realtime&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see RealtimeModel
 * @see com.ibtrader.data.model.impl.RealtimeImpl
 * @see com.ibtrader.data.model.impl.RealtimeModelImpl
 * @generated
 */
@ImplementationClassName("com.ibtrader.data.model.impl.RealtimeImpl")
@ProviderType
public interface Realtime extends RealtimeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ibtrader.data.model.impl.RealtimeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Realtime, Long> REALTIME_ID_ACCESSOR = new Accessor<Realtime, Long>() {
			@Override
			public Long get(Realtime realtime) {
				return realtime.getRealtimeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Realtime> getTypeClass() {
				return Realtime.class;
			}
		};
}