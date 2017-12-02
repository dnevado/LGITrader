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

package com.ibtrader.data.model.impl;

import java.util.List;

import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.interactive.TIMApiGITrader;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.expando.kernel.model.ExpandoColumn;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the Strategy service. Represents a row in the &quot;ibtrader_Strategy&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ibtrader.data.model.Strategy} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class StrategyImpl extends StrategyBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a strategy model instance should use the {@link com.ibtrader.data.model.Strategy} interface instead.
	 */
	
	/* DATOS GENERALES DE LA ESTRATEGIA DEL SERVICIO */
	private Strategy _IBStrategy;
	/* LISTA DE PARAMETROS EXPANDOS  */
	private List<ExpandoColumn> _IBStrategyParams;
	private AssetEntry _IBStrategyAssetEntry;	
	//private TIMApiGITrader  _timApiGITrader;
	private int _CLIENT_ID = 7;	  //  TIMApiWrapper data 		
	/* PRECIOS DE ENTRADA SALIDA  */
	private float _valueIn; 
	private float _valueLimitIn;
	private float _valueOut; 
	private float _valueLimitOut;
	/* PRECIOS DE ENTRADA SALIDA  */
	

	public void execute(Share _share, Market _market) {}
	
	public boolean verify(Share _share, Market _market) {return Boolean.TRUE;}
	
	public boolean activated() {return Boolean.TRUE;}
	
	public StrategyImpl() {
	}


	public List<ExpandoColumn> get_IBStrategyParams() {
		return _IBStrategyParams;
	}

	public void setIBStrategyParams(List<ExpandoColumn> _IBStrategyParams) {
		this._IBStrategyParams = _IBStrategyParams;
	}

	public AssetEntry getIBStrategyAssetEntry() {
		return _IBStrategyAssetEntry;
	}

	public void setIBStrategyAssetEntry(AssetEntry _IBStrategyAssetEntry) {
		this._IBStrategyAssetEntry = _IBStrategyAssetEntry;
	}

		
	public float getValueIn() {
		return _valueIn;
	}

	public void setValueIn(float _valueIn) {
		this._valueIn = _valueIn;
	}

	public float getValueLimitIn() {
		return _valueLimitIn;
	}

	public void setValueLimitIn(float _valueLimitIn) {
		this._valueLimitIn = _valueLimitIn;
	}

	public float getValueOut() {
		return _valueOut;
	}

	public void setValueOut(float _valueOut) {
		this._valueOut = _valueOut;
	}

	public float getValueLimitOut() {
		return _valueLimitOut;
	}

	public void setValueLimitOut(float _valueLimitOut) {
		this._valueLimitOut = _valueLimitOut;
	}

	/* public TIMApiGITrader getTimAPIW() {
		return _timApiGITrader;
	}

	public void setTimAPIW(TIMApiGITrader _timAPIW) {
		this._timApiGITrader = _timAPIW;
	}*/

	public int getCLIENT_ID() {
		return _CLIENT_ID;
	}

	public void setCLIENT_ID(int _CLIENT_ID) {
		this._CLIENT_ID = _CLIENT_ID;
	}
}