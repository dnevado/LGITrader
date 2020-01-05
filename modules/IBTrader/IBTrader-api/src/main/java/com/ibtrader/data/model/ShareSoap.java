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
 * This class is used by SOAP remote services, specifically {@link com.ibtrader.data.service.http.ShareServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.http.ShareServiceSoap
 * @generated
 */
@ProviderType
public class ShareSoap implements Serializable {
	public static ShareSoap toSoapModel(Share model) {
		ShareSoap soapModel = new ShareSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setShareId(model.getShareId());
		soapModel.setName(model.getName());
		soapModel.setSymbol(model.getSymbol());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setActive(model.getActive());
		soapModel.setNumbertopurchase(model.getNumbertopurchase());
		soapModel.setPercentual_limit_buy(model.getPercentual_limit_buy());
		soapModel.setPercentual_stop_lost(model.getPercentual_stop_lost());
		soapModel.setPercentual_stop_profit(model.getPercentual_stop_profit());
		soapModel.setPercentual_stop_profit_position(model.getPercentual_stop_profit_position());
		soapModel.setPercentual_trailling_stop_lost(model.getPercentual_trailling_stop_lost());
		soapModel.setExpiry_date(model.getExpiry_date());
		soapModel.setExpiry_expression(model.getExpiry_expression());
		soapModel.setTick_futures(model.getTick_futures());
		soapModel.setMultiplier(model.getMultiplier());
		soapModel.setSecurity_type(model.getSecurity_type());
		soapModel.setExchange(model.getExchange());
		soapModel.setPrimary_exchange(model.getPrimary_exchange());
		soapModel.setUserCreatedId(model.getUserCreatedId());
		soapModel.setMarketId(model.getMarketId());
		soapModel.setValidated_trader_provider(model.getValidated_trader_provider());
		soapModel.setDate_validated_trader_provider(model.getDate_validated_trader_provider());
		soapModel.setLast_error_trader_provider(model.getLast_error_trader_provider());
		soapModel.setSimulation_end_date(model.getSimulation_end_date());
		soapModel.setTrading_hours(model.getTrading_hours());
		soapModel.setDate_filled_realtime_gaps(model.getDate_filled_realtime_gaps());

		return soapModel;
	}

	public static ShareSoap[] toSoapModels(Share[] models) {
		ShareSoap[] soapModels = new ShareSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ShareSoap[][] toSoapModels(Share[][] models) {
		ShareSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ShareSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ShareSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ShareSoap[] toSoapModels(List<Share> models) {
		List<ShareSoap> soapModels = new ArrayList<ShareSoap>(models.size());

		for (Share model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ShareSoap[soapModels.size()]);
	}

	public ShareSoap() {
	}

	public long getPrimaryKey() {
		return _shareId;
	}

	public void setPrimaryKey(long pk) {
		setShareId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getShareId() {
		return _shareId;
	}

	public void setShareId(long shareId) {
		_shareId = shareId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getSymbol() {
		return _symbol;
	}

	public void setSymbol(String symbol) {
		_symbol = symbol;
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

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public long getNumbertopurchase() {
		return _numbertopurchase;
	}

	public void setNumbertopurchase(long numbertopurchase) {
		_numbertopurchase = numbertopurchase;
	}

	public double getPercentual_limit_buy() {
		return _percentual_limit_buy;
	}

	public void setPercentual_limit_buy(double percentual_limit_buy) {
		_percentual_limit_buy = percentual_limit_buy;
	}

	public double getPercentual_stop_lost() {
		return _percentual_stop_lost;
	}

	public void setPercentual_stop_lost(double percentual_stop_lost) {
		_percentual_stop_lost = percentual_stop_lost;
	}

	public double getPercentual_stop_profit() {
		return _percentual_stop_profit;
	}

	public void setPercentual_stop_profit(double percentual_stop_profit) {
		_percentual_stop_profit = percentual_stop_profit;
	}

	public double getPercentual_stop_profit_position() {
		return _percentual_stop_profit_position;
	}

	public void setPercentual_stop_profit_position(
		double percentual_stop_profit_position) {
		_percentual_stop_profit_position = percentual_stop_profit_position;
	}

	public double getPercentual_trailling_stop_lost() {
		return _percentual_trailling_stop_lost;
	}

	public void setPercentual_trailling_stop_lost(
		double percentual_trailling_stop_lost) {
		_percentual_trailling_stop_lost = percentual_trailling_stop_lost;
	}

	public Date getExpiry_date() {
		return _expiry_date;
	}

	public void setExpiry_date(Date expiry_date) {
		_expiry_date = expiry_date;
	}

	public String getExpiry_expression() {
		return _expiry_expression;
	}

	public void setExpiry_expression(String expiry_expression) {
		_expiry_expression = expiry_expression;
	}

	public double getTick_futures() {
		return _tick_futures;
	}

	public void setTick_futures(double tick_futures) {
		_tick_futures = tick_futures;
	}

	public long getMultiplier() {
		return _multiplier;
	}

	public void setMultiplier(long multiplier) {
		_multiplier = multiplier;
	}

	public String getSecurity_type() {
		return _security_type;
	}

	public void setSecurity_type(String security_type) {
		_security_type = security_type;
	}

	public String getExchange() {
		return _exchange;
	}

	public void setExchange(String exchange) {
		_exchange = exchange;
	}

	public String getPrimary_exchange() {
		return _primary_exchange;
	}

	public void setPrimary_exchange(String primary_exchange) {
		_primary_exchange = primary_exchange;
	}

	public long getUserCreatedId() {
		return _userCreatedId;
	}

	public void setUserCreatedId(long userCreatedId) {
		_userCreatedId = userCreatedId;
	}

	public long getMarketId() {
		return _marketId;
	}

	public void setMarketId(long marketId) {
		_marketId = marketId;
	}

	public boolean getValidated_trader_provider() {
		return _validated_trader_provider;
	}

	public boolean isValidated_trader_provider() {
		return _validated_trader_provider;
	}

	public void setValidated_trader_provider(boolean validated_trader_provider) {
		_validated_trader_provider = validated_trader_provider;
	}

	public Date getDate_validated_trader_provider() {
		return _date_validated_trader_provider;
	}

	public void setDate_validated_trader_provider(
		Date date_validated_trader_provider) {
		_date_validated_trader_provider = date_validated_trader_provider;
	}

	public String getLast_error_trader_provider() {
		return _last_error_trader_provider;
	}

	public void setLast_error_trader_provider(String last_error_trader_provider) {
		_last_error_trader_provider = last_error_trader_provider;
	}

	public Date getSimulation_end_date() {
		return _simulation_end_date;
	}

	public void setSimulation_end_date(Date simulation_end_date) {
		_simulation_end_date = simulation_end_date;
	}

	public String getTrading_hours() {
		return _trading_hours;
	}

	public void setTrading_hours(String trading_hours) {
		_trading_hours = trading_hours;
	}

	public Date getDate_filled_realtime_gaps() {
		return _date_filled_realtime_gaps;
	}

	public void setDate_filled_realtime_gaps(Date date_filled_realtime_gaps) {
		_date_filled_realtime_gaps = date_filled_realtime_gaps;
	}

	private String _uuid;
	private long _shareId;
	private String _name;
	private String _symbol;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _active;
	private long _numbertopurchase;
	private double _percentual_limit_buy;
	private double _percentual_stop_lost;
	private double _percentual_stop_profit;
	private double _percentual_stop_profit_position;
	private double _percentual_trailling_stop_lost;
	private Date _expiry_date;
	private String _expiry_expression;
	private double _tick_futures;
	private long _multiplier;
	private String _security_type;
	private String _exchange;
	private String _primary_exchange;
	private long _userCreatedId;
	private long _marketId;
	private boolean _validated_trader_provider;
	private Date _date_validated_trader_provider;
	private String _last_error_trader_provider;
	private Date _simulation_end_date;
	private String _trading_hours;
	private Date _date_filled_realtime_gaps;
}