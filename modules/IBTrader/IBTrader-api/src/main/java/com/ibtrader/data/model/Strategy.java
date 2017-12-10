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
 * The extended model interface for the Strategy service. Represents a row in the &quot;ibtrader_Strategy&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see StrategyModel
 * @see com.ibtrader.data.model.impl.StrategyImpl
 * @see com.ibtrader.data.model.impl.StrategyModelImpl
 * @generated
 */
@ImplementationClassName("com.ibtrader.data.model.impl.StrategyImpl")
@ProviderType
public interface Strategy extends StrategyModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ibtrader.data.model.impl.StrategyImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Strategy, Long> STRATEGY_I_D_ACCESSOR = new Accessor<Strategy, Long>() {
			@Override
			public Long get(Strategy strategy) {
				return strategy.getStrategyID();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Strategy> getTypeClass() {
				return Strategy.class;
			}
		};

	public void execute(Share _share, Market _market);

	public boolean verify(Share _share, Market _market);

	public void init(long companyId);

	public boolean activated();

	public java.util.List<com.liferay.expando.kernel.model.ExpandoColumn> getIBStrategyParams();

	public void setIBStrategyParams(
		java.util.List<com.liferay.expando.kernel.model.ExpandoColumn> _IBStrategyParams);

	public com.liferay.asset.kernel.model.AssetEntry getIBStrategyAssetEntry();

	public void setIBStrategyAssetEntry(
		com.liferay.asset.kernel.model.AssetEntry _IBStrategyAssetEntry);

	public float getValueIn();

	public void setValueIn(float _valueIn);

	public float getValueLimitIn();

	public void setValueLimitIn(float _valueLimitIn);

	public float getValueOut();

	public void setValueOut(float _valueOut);

	public float getValueLimitOut();

	public void setValueLimitOut(float _valueLimitOut);

	public int getCLIENT_ID();

	public void setCLIENT_ID(int _CLIENT_ID);
}