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
 * The extended model interface for the StrategyShare service. Represents a row in the &quot;ibtrader_StrategyShare&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see StrategyShareModel
 * @see com.ibtrader.data.model.impl.StrategyShareImpl
 * @see com.ibtrader.data.model.impl.StrategyShareModelImpl
 * @generated
 */
@ImplementationClassName("com.ibtrader.data.model.impl.StrategyShareImpl")
@ProviderType
public interface StrategyShare extends StrategyShareModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ibtrader.data.model.impl.StrategyShareImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StrategyShare, Long> STRATEGYSHARE_ID_ACCESSOR = new Accessor<StrategyShare, Long>() {
			@Override
			public Long get(StrategyShare strategyShare) {
				return strategyShare.getStrategyshareId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<StrategyShare> getTypeClass() {
				return StrategyShare.class;
			}
		};
}