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
		soapModel.setExpiry_date(model.getExpiry_date());
		soapModel.setExpiry_expression(model.getExpiry_expression());
		soapModel.setTick_futures(model.getTick_futures());
		soapModel.setMultiplier(model.getMultiplier());
		soapModel.setLast_error_data_read(model.getLast_error_data_read());
		soapModel.setLast_error_data_trade(model.getLast_error_data_trade());
		soapModel.setSecurity_type(model.getSecurity_type());
		soapModel.setExchange(model.getExchange());
		soapModel.setPrimary_exchange(model.getPrimary_exchange());
		soapModel.setDate_contract_verified(model.getDate_contract_verified());
		soapModel.setUserCreatedId(model.getUserCreatedId());
		soapModel.setMarketId(model.getMarketId());

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

	public String getLast_error_data_read() {
		return _last_error_data_read;
	}

	public void setLast_error_data_read(String last_error_data_read) {
		_last_error_data_read = last_error_data_read;
	}

	public String getLast_error_data_trade() {
		return _last_error_data_trade;
	}

	public void setLast_error_data_trade(String last_error_data_trade) {
		_last_error_data_trade = last_error_data_trade;
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

	public Date getDate_contract_verified() {
		return _date_contract_verified;
	}

	public void setDate_contract_verified(Date date_contract_verified) {
		_date_contract_verified = date_contract_verified;
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
	private Date _expiry_date;
	private String _expiry_expression;
	private double _tick_futures;
	private long _multiplier;
	private String _last_error_data_read;
	private String _last_error_data_trade;
	private String _security_type;
	private String _exchange;
	private String _primary_exchange;
	private Date _date_contract_verified;
	private long _userCreatedId;
	private long _marketId;
}