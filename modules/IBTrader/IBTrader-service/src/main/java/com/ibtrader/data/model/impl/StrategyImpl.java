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

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ib.client.Contract;
import com.ib.client.Order;
import com.ibtrader.data.model.BackTesting;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.interactive.TIMApiGITrader_NOVALE;
import com.ibtrader.interactive.TIMApiWrapper;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.portal.kernel.json.JSONObject;

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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* DATOS GENERALES DE LA ESTRATEGIA DEL SERVICIO */
	/* LISTA DE PARAMETROS EXPANDOS  */
	private List<ExpandoColumn> _IBStrategyParams;
	private AssetEntry _IBStrategyAssetEntry;	
	//private TIMApiGITrader  _timApiGITrader;
	private int _CLIENT_ID = 7;	  //  TIMApiWrapper data 		
	/* PRECIOS DE ENTRADA SALIDA  */
	private double valueIn; 
	private double valueLimitIn;
	private double valueOut; 
	private double valueLimitOut;	
	
	private Order _targetOrder; // esta es la orden que se rellena en el metodo execute para que el cron la trate y la ordene, la parte transaccional
								// en BBDD se hace en el metodo execute	
	private List<Order> _childsOrder; // ordenes hijas que acompañan a la padre (TRAIL)
 	
	private Contract _targetContract;
	
	private JSONObject jsonStrategyShareParams;	
	private boolean verified = false;	
	/* PRECIOS DE ENTRADA SALIDA  */	
	/* nos sirve para devolver los session errors de la funciona validatPara	ms*/
	private String validateParamsKeysError = "";
	
	private boolean simulation_mode = Boolean.FALSE;
	private BackTesting current_backtesting = null;


	/* COMPORTAMIENTO DE LOS PARAMETROS DE ENTRADA EN CUANTO A TIPOS */
	public boolean validateParams(Map<String, String> paramValues) {return Boolean.TRUE;}
	//public void execute(Share _share, Market _market) {}
	public boolean verify(Share _share, Market _market,StrategyShare _strategyImpl, Date backtestingdDate) {
		return Boolean.TRUE;
	}
	public boolean verify(Share _share, Market _market,StrategyShare _strategyImpl) {
		return verify(_share, _market,_strategyImpl,null);
	}  // share general, market y datos de la estrategoa con los valores parametrizados

	public boolean verifyBackTesting(Share _share, Market _market, StrategyShare _strategyImpl, Date backtestingDate) {
		// TODO Auto-generated method stub
		return verify(_share, _market,_strategyImpl,backtestingDate);
	}
	
	public  long execute(Share _share, Market _market,  Date backtestingdDate)
	{
		return 0;
	}
	
	public long execute(Share _share, Market _market) {
		// TODO Auto-generated method stub
		return execute(_share,_market,null);		
	}
	public long executeBackTesting(Share _share, Market _market, Date backtestingDate){
		// TODO Auto-generated method stub
		return execute(_share,_market,backtestingDate);
		
	}
	
	/* CREATE THE REQUERIMENT EXPANDOS PARAMETERS */
	public void init(long companyId) {}
	public void init_simulation(BackTesting _current_backtesting) {
		simulation_mode = Boolean.TRUE;
		current_backtesting = _current_backtesting;
	}
	
	public boolean activated() {return Boolean.TRUE;}
	
	/* GET LIST EXPANDOS FIELDS */
	public StrategyImpl() {
	}


	public List<ExpandoColumn> getIBStrategyParams() {
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
	
	public int getCLIENT_ID() {
		return _CLIENT_ID;
	}

	public void setCLIENT_ID(int _CLIENT_ID) {
		this._CLIENT_ID = _CLIENT_ID;
	}

	public String getValidateParamsKeysError() {
		return validateParamsKeysError;
	}

	public void setValidateParamsKeysError(String validateParamsKeysError) {
		this.validateParamsKeysError = validateParamsKeysError;
	}

	
	@Override
	public double getValueIn() {
		// TODO Auto-generated method stub
		return valueIn;
	}

	@Override
	public void setValueIn(double _valueIn) {
		// TODO Auto-generated method stub
		valueIn = _valueIn;
	}

	@Override
	public double getValueLimitIn() {
		// TODO Auto-generated method stub
		return valueLimitIn;
	}

	@Override
	public void setValueLimitIn(double _valueLimitIn) {
		// TODO Auto-generated method stub
		valueLimitIn = _valueLimitIn;
	}

	@Override
	public double getValueOut() {
		// TODO Auto-generated method stub
		return valueOut;
	}

	@Override
	public void setValueOut(double _valueOut) {
		// TODO Auto-generated method stub
		valueOut = _valueOut;
	}

	@Override
	public double getValueLimitOut() {
		// TODO Auto-generated method stub
		return valueLimitOut;
	}

	@Override
	public void setValueLimitOut(double _valueLimitOut) {
		// TODO Auto-generated method stub
		valueLimitOut = _valueLimitOut;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public JSONObject getJsonStrategyShareParams() {
		return jsonStrategyShareParams;
	}
	public void setJsonStrategyShareParams(JSONObject _jsonStrategyShareParams) {
		this.jsonStrategyShareParams = _jsonStrategyShareParams;
	}
	
	/* ESTO NO ES UNA IMPLEMENTACION YA QUE EN DENERIA METER EL TIMApiWrapper EN LA API Y ES COSTOSO */
	/*public void execute(Share _share, Market _market, com.ibtrader.interactive.TIMApiWrapper wrapper) {
		// TODO Auto-generated method stub
		
	}*/
	public Order getTargetOrder() {
		return _targetOrder;
	}
	public void setTargetOrder(Order targetOrder) {
		this._targetOrder = targetOrder;
	}
	public Contract getTargetContract() {
		return _targetContract;
	}
	public void setTargetContract(Contract _targetContract) {
		this._targetContract = _targetContract;
	}
	public List<Order> getChildsOrder() {
		return _childsOrder;
	}
	public void setChildsOrder(List<Order> _childsOrder) {
		this._childsOrder = _childsOrder;
	}
	/**
	 * @return the simulation_mode
	 */
	public boolean isSimulation_mode() {
		return simulation_mode;
	}
	public BackTesting getCurrentBackTesting() {
		return current_backtesting;
	}

		
}