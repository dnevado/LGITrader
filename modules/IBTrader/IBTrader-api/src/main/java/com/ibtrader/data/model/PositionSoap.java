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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ibtrader.data.service.http.PositionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.http.PositionServiceSoap
 * @generated
 */
@ProviderType
public class PositionSoap implements Serializable {
	public static PositionSoap toSoapModel(Position model) {
		PositionSoap soapModel = new PositionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPositionId(model.getPositionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setShareId(model.getShareId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setState(model.getState());
		soapModel.setState_in(model.getState_in());
		soapModel.setState_out(model.getState_out());
		soapModel.setDescription(model.getDescription());
		soapModel.setPrice_in(model.getPrice_in());
		soapModel.setPrice_real_in(model.getPrice_real_in());
		soapModel.setLimit_price_in(model.getLimit_price_in());
		soapModel.setDate_in(model.getDate_in());
		soapModel.setDate_real_in(model.getDate_real_in());
		soapModel.setPositionId_tws_in(model.getPositionId_tws_in());
		soapModel.setPositionId_tws_out(model.getPositionId_tws_out());
		soapModel.setType(model.getType());
		soapModel.setPrice_out(model.getPrice_out());
		soapModel.setPrice_real_out(model.getPrice_real_out());
		soapModel.setLimit_price_out(model.getLimit_price_out());
		soapModel.setDate_out(model.getDate_out());
		soapModel.setDate_real_out(model.getDate_real_out());
		soapModel.setShare_number(model.getShare_number());
		soapModel.setShare_number_to_trade(model.getShare_number_to_trade());
		soapModel.setShare_number_traded(model.getShare_number_traded());
		soapModel.setRealtimeId_in(model.getRealtimeId_in());
		soapModel.setRealtimeId_out(model.getRealtimeId_out());
		soapModel.setStrategy_in(model.getStrategy_in());
		soapModel.setStrategy_out(model.getStrategy_out());
		soapModel.setPercentualstoplost_out(model.getPercentualstoplost_out());
		soapModel.setPricestoplost_out(model.getPricestoplost_out());
		soapModel.setPercentualstopprofit_out(model.getPercentualstopprofit_out());
		soapModel.setPricestopprofit_out(model.getPricestopprofit_out());
		soapModel.setPendingcancelled(model.getPendingcancelled());
		soapModel.setTrading_data_operations(model.getTrading_data_operations());
		soapModel.setSimulation_mode(model.getSimulation_mode());
		soapModel.setTotalcommision(model.getTotalcommision());

		return soapModel;
	}

	public static PositionSoap[] toSoapModels(Position[] models) {
		PositionSoap[] soapModels = new PositionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PositionSoap[][] toSoapModels(Position[][] models) {
		PositionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PositionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PositionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PositionSoap[] toSoapModels(List<Position> models) {
		List<PositionSoap> soapModels = new ArrayList<PositionSoap>(models.size());

		for (Position model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PositionSoap[soapModels.size()]);
	}

	public PositionSoap() {
	}

	public long getPrimaryKey() {
		return _positionId;
	}

	public void setPrimaryKey(long pk) {
		setPositionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPositionId() {
		return _positionId;
	}

	public void setPositionId(long positionId) {
		_positionId = positionId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getShareId() {
		return _shareId;
	}

	public void setShareId(long shareId) {
		_shareId = shareId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getState() {
		return _state;
	}

	public void setState(String state) {
		_state = state;
	}

	public String getState_in() {
		return _state_in;
	}

	public void setState_in(String state_in) {
		_state_in = state_in;
	}

	public String getState_out() {
		return _state_out;
	}

	public void setState_out(String state_out) {
		_state_out = state_out;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public double getPrice_in() {
		return _price_in;
	}

	public void setPrice_in(double price_in) {
		_price_in = price_in;
	}

	public double getPrice_real_in() {
		return _price_real_in;
	}

	public void setPrice_real_in(double price_real_in) {
		_price_real_in = price_real_in;
	}

	public double getLimit_price_in() {
		return _limit_price_in;
	}

	public void setLimit_price_in(double limit_price_in) {
		_limit_price_in = limit_price_in;
	}

	public Date getDate_in() {
		return _date_in;
	}

	public void setDate_in(Date date_in) {
		_date_in = date_in;
	}

	public Date getDate_real_in() {
		return _date_real_in;
	}

	public void setDate_real_in(Date date_real_in) {
		_date_real_in = date_real_in;
	}

	public long getPositionId_tws_in() {
		return _positionId_tws_in;
	}

	public void setPositionId_tws_in(long positionId_tws_in) {
		_positionId_tws_in = positionId_tws_in;
	}

	public long getPositionId_tws_out() {
		return _positionId_tws_out;
	}

	public void setPositionId_tws_out(long positionId_tws_out) {
		_positionId_tws_out = positionId_tws_out;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public double getPrice_out() {
		return _price_out;
	}

	public void setPrice_out(double price_out) {
		_price_out = price_out;
	}

	public double getPrice_real_out() {
		return _price_real_out;
	}

	public void setPrice_real_out(double price_real_out) {
		_price_real_out = price_real_out;
	}

	public double getLimit_price_out() {
		return _limit_price_out;
	}

	public void setLimit_price_out(double limit_price_out) {
		_limit_price_out = limit_price_out;
	}

	public Date getDate_out() {
		return _date_out;
	}

	public void setDate_out(Date date_out) {
		_date_out = date_out;
	}

	public Date getDate_real_out() {
		return _date_real_out;
	}

	public void setDate_real_out(Date date_real_out) {
		_date_real_out = date_real_out;
	}

	public long getShare_number() {
		return _share_number;
	}

	public void setShare_number(long share_number) {
		_share_number = share_number;
	}

	public long getShare_number_to_trade() {
		return _share_number_to_trade;
	}

	public void setShare_number_to_trade(long share_number_to_trade) {
		_share_number_to_trade = share_number_to_trade;
	}

	public long getShare_number_traded() {
		return _share_number_traded;
	}

	public void setShare_number_traded(long share_number_traded) {
		_share_number_traded = share_number_traded;
	}

	public long getRealtimeId_in() {
		return _realtimeId_in;
	}

	public void setRealtimeId_in(long realtimeId_in) {
		_realtimeId_in = realtimeId_in;
	}

	public long getRealtimeId_out() {
		return _realtimeId_out;
	}

	public void setRealtimeId_out(long realtimeId_out) {
		_realtimeId_out = realtimeId_out;
	}

	public String getStrategy_in() {
		return _strategy_in;
	}

	public void setStrategy_in(String strategy_in) {
		_strategy_in = strategy_in;
	}

	public String getStrategy_out() {
		return _strategy_out;
	}

	public void setStrategy_out(String strategy_out) {
		_strategy_out = strategy_out;
	}

	public double getPercentualstoplost_out() {
		return _percentualstoplost_out;
	}

	public void setPercentualstoplost_out(double percentualstoplost_out) {
		_percentualstoplost_out = percentualstoplost_out;
	}

	public double getPricestoplost_out() {
		return _pricestoplost_out;
	}

	public void setPricestoplost_out(double pricestoplost_out) {
		_pricestoplost_out = pricestoplost_out;
	}

	public double getPercentualstopprofit_out() {
		return _percentualstopprofit_out;
	}

	public void setPercentualstopprofit_out(double percentualstopprofit_out) {
		_percentualstopprofit_out = percentualstopprofit_out;
	}

	public double getPricestopprofit_out() {
		return _pricestopprofit_out;
	}

	public void setPricestopprofit_out(double pricestopprofit_out) {
		_pricestopprofit_out = pricestopprofit_out;
	}

	public long getPendingcancelled() {
		return _pendingcancelled;
	}

	public void setPendingcancelled(long pendingcancelled) {
		_pendingcancelled = pendingcancelled;
	}

	public String getTrading_data_operations() {
		return _trading_data_operations;
	}

	public void setTrading_data_operations(String trading_data_operations) {
		_trading_data_operations = trading_data_operations;
	}

	public boolean getSimulation_mode() {
		return _simulation_mode;
	}

	public boolean isSimulation_mode() {
		return _simulation_mode;
	}

	public void setSimulation_mode(boolean simulation_mode) {
		_simulation_mode = simulation_mode;
	}

	public double getTotalcommision() {
		return _totalcommision;
	}

	public void setTotalcommision(double totalcommision) {
		_totalcommision = totalcommision;
	}

	private String _uuid;
	private long _positionId;
	private long _groupId;
	private long _companyId;
	private long _shareId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _state;
	private String _state_in;
	private String _state_out;
	private String _description;
	private double _price_in;
	private double _price_real_in;
	private double _limit_price_in;
	private Date _date_in;
	private Date _date_real_in;
	private long _positionId_tws_in;
	private long _positionId_tws_out;
	private String _type;
	private double _price_out;
	private double _price_real_out;
	private double _limit_price_out;
	private Date _date_out;
	private Date _date_real_out;
	private long _share_number;
	private long _share_number_to_trade;
	private long _share_number_traded;
	private long _realtimeId_in;
	private long _realtimeId_out;
	private String _strategy_in;
	private String _strategy_out;
	private double _percentualstoplost_out;
	private double _pricestoplost_out;
	private double _percentualstopprofit_out;
	private double _pricestopprofit_out;
	private long _pendingcancelled;
	private String _trading_data_operations;
	private boolean _simulation_mode;
	private double _totalcommision;
}