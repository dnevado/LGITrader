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

	public boolean validateParams(
		java.util.Map<java.lang.String, java.lang.String> paramValues);

	public boolean verify(Share _share, Market _market,
		StrategyShare _strategyImpl, java.util.Date backtestingdDate);

	public boolean verify(Share _share, Market _market,
		StrategyShare _strategyImpl);

	public boolean verifyBackTesting(Share _share, Market _market,
		StrategyShare _strategyImpl, java.util.Date backtestingDate);

	public long execute(Share _share, Market _market,
		java.util.Date backtestingdDate);

	public long execute(Share _share, Market _market);

	public long executeBackTesting(Share _share, Market _market,
		java.util.Date backtestingDate);

	public void init(long companyId);

	public boolean activated();

	public java.util.List<com.liferay.expando.kernel.model.ExpandoColumn> getIBStrategyParams();

	public void setIBStrategyParams(
		java.util.List<com.liferay.expando.kernel.model.ExpandoColumn> _IBStrategyParams);

	public com.liferay.asset.kernel.model.AssetEntry getIBStrategyAssetEntry();

	public void setIBStrategyAssetEntry(
		com.liferay.asset.kernel.model.AssetEntry _IBStrategyAssetEntry);

	public int getCLIENT_ID();

	public void setCLIENT_ID(int _CLIENT_ID);

	public java.lang.String getValidateParamsKeysError();

	public void setValidateParamsKeysError(
		java.lang.String validateParamsKeysError);

	public double getValueIn();

	public void setValueIn(double _valueIn);

	public double getValueLimitIn();

	public void setValueLimitIn(double _valueLimitIn);

	public double getValueOut();

	public void setValueOut(double _valueOut);

	public double getValueLimitOut();

	public void setValueLimitOut(double _valueLimitOut);

	public boolean isVerified();

	public void setVerified(boolean verified);

	public com.liferay.portal.kernel.json.JSONObject getJsonStrategyShareParams();

	public void setJsonStrategyShareParams(
		com.liferay.portal.kernel.json.JSONObject _jsonStrategyShareParams);

	public com.ib.client.Order getTargetOrder();

	public void setTargetOrder(com.ib.client.Order targetOrder);

	public com.ib.client.Contract getTargetContract();

	public void setTargetContract(com.ib.client.Contract _targetContract);

	public java.util.List<com.ib.client.Order> getChildsOrder();

	public void setChildsOrder(java.util.List<com.ib.client.Order> _childsOrder);
}