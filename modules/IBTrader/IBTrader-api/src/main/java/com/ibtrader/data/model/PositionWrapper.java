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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Position}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Position
 * @generated
 */
@ProviderType
public class PositionWrapper implements Position, ModelWrapper<Position> {
	public PositionWrapper(Position position) {
		_position = position;
	}

	@Override
	public Class<?> getModelClass() {
		return Position.class;
	}

	@Override
	public String getModelClassName() {
		return Position.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("positionId", getPositionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("shareId", getShareId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("state", getState());
		attributes.put("state_in", getState_in());
		attributes.put("state_out", getState_out());
		attributes.put("description", getDescription());
		attributes.put("price_in", getPrice_in());
		attributes.put("price_real_in", getPrice_real_in());
		attributes.put("limit_price_in", getLimit_price_in());
		attributes.put("date_in", getDate_in());
		attributes.put("date_real_in", getDate_real_in());
		attributes.put("positionId_tws_in", getPositionId_tws_in());
		attributes.put("positionId_tws_out", getPositionId_tws_out());
		attributes.put("type", getType());
		attributes.put("price_out", getPrice_out());
		attributes.put("price_real_out", getPrice_real_out());
		attributes.put("limit_price_out", getLimit_price_out());
		attributes.put("date_out", getDate_out());
		attributes.put("date_real_out", getDate_real_out());
		attributes.put("share_number", getShare_number());
		attributes.put("share_number_to_trade", getShare_number_to_trade());
		attributes.put("share_number_traded", getShare_number_traded());
		attributes.put("clientId_in", getClientId_in());
		attributes.put("clientId_out", getClientId_out());
		attributes.put("strategy_in", getStrategy_in());
		attributes.put("strategy_out", getStrategy_out());
		attributes.put("percentualstoplost_out", getPercentualstoplost_out());
		attributes.put("pricestoplost_out", getPricestoplost_out());
		attributes.put("percentualstopprofit_out", getPercentualstopprofit_out());
		attributes.put("pricestopprofit_out", getPricestopprofit_out());
		attributes.put("pendingcancelled", getPendingcancelled());
		attributes.put("trading_data_operations", getTrading_data_operations());
		attributes.put("simulation_mode", getSimulation_mode());
		attributes.put("totalcommision", getTotalcommision());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long positionId = (Long)attributes.get("positionId");

		if (positionId != null) {
			setPositionId(positionId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long shareId = (Long)attributes.get("shareId");

		if (shareId != null) {
			setShareId(shareId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String state = (String)attributes.get("state");

		if (state != null) {
			setState(state);
		}

		String state_in = (String)attributes.get("state_in");

		if (state_in != null) {
			setState_in(state_in);
		}

		String state_out = (String)attributes.get("state_out");

		if (state_out != null) {
			setState_out(state_out);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Double price_in = (Double)attributes.get("price_in");

		if (price_in != null) {
			setPrice_in(price_in);
		}

		Double price_real_in = (Double)attributes.get("price_real_in");

		if (price_real_in != null) {
			setPrice_real_in(price_real_in);
		}

		Double limit_price_in = (Double)attributes.get("limit_price_in");

		if (limit_price_in != null) {
			setLimit_price_in(limit_price_in);
		}

		Date date_in = (Date)attributes.get("date_in");

		if (date_in != null) {
			setDate_in(date_in);
		}

		Date date_real_in = (Date)attributes.get("date_real_in");

		if (date_real_in != null) {
			setDate_real_in(date_real_in);
		}

		Long positionId_tws_in = (Long)attributes.get("positionId_tws_in");

		if (positionId_tws_in != null) {
			setPositionId_tws_in(positionId_tws_in);
		}

		Long positionId_tws_out = (Long)attributes.get("positionId_tws_out");

		if (positionId_tws_out != null) {
			setPositionId_tws_out(positionId_tws_out);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Double price_out = (Double)attributes.get("price_out");

		if (price_out != null) {
			setPrice_out(price_out);
		}

		Double price_real_out = (Double)attributes.get("price_real_out");

		if (price_real_out != null) {
			setPrice_real_out(price_real_out);
		}

		Double limit_price_out = (Double)attributes.get("limit_price_out");

		if (limit_price_out != null) {
			setLimit_price_out(limit_price_out);
		}

		Date date_out = (Date)attributes.get("date_out");

		if (date_out != null) {
			setDate_out(date_out);
		}

		Date date_real_out = (Date)attributes.get("date_real_out");

		if (date_real_out != null) {
			setDate_real_out(date_real_out);
		}

		Long share_number = (Long)attributes.get("share_number");

		if (share_number != null) {
			setShare_number(share_number);
		}

		Long share_number_to_trade = (Long)attributes.get(
				"share_number_to_trade");

		if (share_number_to_trade != null) {
			setShare_number_to_trade(share_number_to_trade);
		}

		Long share_number_traded = (Long)attributes.get("share_number_traded");

		if (share_number_traded != null) {
			setShare_number_traded(share_number_traded);
		}

		Long clientId_in = (Long)attributes.get("clientId_in");

		if (clientId_in != null) {
			setClientId_in(clientId_in);
		}

		Long clientId_out = (Long)attributes.get("clientId_out");

		if (clientId_out != null) {
			setClientId_out(clientId_out);
		}

		String strategy_in = (String)attributes.get("strategy_in");

		if (strategy_in != null) {
			setStrategy_in(strategy_in);
		}

		String strategy_out = (String)attributes.get("strategy_out");

		if (strategy_out != null) {
			setStrategy_out(strategy_out);
		}

		Double percentualstoplost_out = (Double)attributes.get(
				"percentualstoplost_out");

		if (percentualstoplost_out != null) {
			setPercentualstoplost_out(percentualstoplost_out);
		}

		Double pricestoplost_out = (Double)attributes.get("pricestoplost_out");

		if (pricestoplost_out != null) {
			setPricestoplost_out(pricestoplost_out);
		}

		Double percentualstopprofit_out = (Double)attributes.get(
				"percentualstopprofit_out");

		if (percentualstopprofit_out != null) {
			setPercentualstopprofit_out(percentualstopprofit_out);
		}

		Double pricestopprofit_out = (Double)attributes.get(
				"pricestopprofit_out");

		if (pricestopprofit_out != null) {
			setPricestopprofit_out(pricestopprofit_out);
		}

		Long pendingcancelled = (Long)attributes.get("pendingcancelled");

		if (pendingcancelled != null) {
			setPendingcancelled(pendingcancelled);
		}

		String trading_data_operations = (String)attributes.get(
				"trading_data_operations");

		if (trading_data_operations != null) {
			setTrading_data_operations(trading_data_operations);
		}

		Boolean simulation_mode = (Boolean)attributes.get("simulation_mode");

		if (simulation_mode != null) {
			setSimulation_mode(simulation_mode);
		}

		Double totalcommision = (Double)attributes.get("totalcommision");

		if (totalcommision != null) {
			setTotalcommision(totalcommision);
		}
	}

	@Override
	public Position toEscapedModel() {
		return new PositionWrapper(_position.toEscapedModel());
	}

	@Override
	public Position toUnescapedModel() {
		return new PositionWrapper(_position.toUnescapedModel());
	}

	@Override
	public boolean IsClosed() {
		return _position.IsClosed();
	}

	@Override
	public boolean IsOpen() {
		return _position.IsOpen();
	}

	@Override
	public boolean IsPendingIn() {
		return _position.IsPendingIn();
	}

	@Override
	public boolean IsPendingOut() {
		return _position.IsPendingOut();
	}

	/**
	* Returns the simulation_mode of this position.
	*
	* @return the simulation_mode of this position
	*/
	@Override
	public boolean getSimulation_mode() {
		return _position.getSimulation_mode();
	}

	@Override
	public boolean isCachedModel() {
		return _position.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _position.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _position.isNew();
	}

	/**
	* Returns <code>true</code> if this position is simulation_mode.
	*
	* @return <code>true</code> if this position is simulation_mode; <code>false</code> otherwise
	*/
	@Override
	public boolean isSimulation_mode() {
		return _position.isSimulation_mode();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _position.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Position> toCacheModel() {
		return _position.toCacheModel();
	}

	/**
	* Returns the limit_price_in of this position.
	*
	* @return the limit_price_in of this position
	*/
	@Override
	public double getLimit_price_in() {
		return _position.getLimit_price_in();
	}

	/**
	* Returns the limit_price_out of this position.
	*
	* @return the limit_price_out of this position
	*/
	@Override
	public double getLimit_price_out() {
		return _position.getLimit_price_out();
	}

	/**
	* Returns the percentualstoplost_out of this position.
	*
	* @return the percentualstoplost_out of this position
	*/
	@Override
	public double getPercentualstoplost_out() {
		return _position.getPercentualstoplost_out();
	}

	/**
	* Returns the percentualstopprofit_out of this position.
	*
	* @return the percentualstopprofit_out of this position
	*/
	@Override
	public double getPercentualstopprofit_out() {
		return _position.getPercentualstopprofit_out();
	}

	/**
	* Returns the price_in of this position.
	*
	* @return the price_in of this position
	*/
	@Override
	public double getPrice_in() {
		return _position.getPrice_in();
	}

	/**
	* Returns the price_out of this position.
	*
	* @return the price_out of this position
	*/
	@Override
	public double getPrice_out() {
		return _position.getPrice_out();
	}

	/**
	* Returns the price_real_in of this position.
	*
	* @return the price_real_in of this position
	*/
	@Override
	public double getPrice_real_in() {
		return _position.getPrice_real_in();
	}

	/**
	* Returns the price_real_out of this position.
	*
	* @return the price_real_out of this position
	*/
	@Override
	public double getPrice_real_out() {
		return _position.getPrice_real_out();
	}

	/**
	* Returns the pricestoplost_out of this position.
	*
	* @return the pricestoplost_out of this position
	*/
	@Override
	public double getPricestoplost_out() {
		return _position.getPricestoplost_out();
	}

	/**
	* Returns the pricestopprofit_out of this position.
	*
	* @return the pricestopprofit_out of this position
	*/
	@Override
	public double getPricestopprofit_out() {
		return _position.getPricestopprofit_out();
	}

	/**
	* Returns the totalcommision of this position.
	*
	* @return the totalcommision of this position
	*/
	@Override
	public double getTotalcommision() {
		return _position.getTotalcommision();
	}

	@Override
	public int compareTo(Position position) {
		return _position.compareTo(position);
	}

	@Override
	public int hashCode() {
		return _position.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _position.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PositionWrapper((Position)_position.clone());
	}

	/**
	* Returns the description of this position.
	*
	* @return the description of this position
	*/
	@Override
	public java.lang.String getDescription() {
		return _position.getDescription();
	}

	/**
	* Returns the state of this position.
	*
	* @return the state of this position
	*/
	@Override
	public java.lang.String getState() {
		return _position.getState();
	}

	/**
	* Returns the state_in of this position.
	*
	* @return the state_in of this position
	*/
	@Override
	public java.lang.String getState_in() {
		return _position.getState_in();
	}

	/**
	* Returns the state_out of this position.
	*
	* @return the state_out of this position
	*/
	@Override
	public java.lang.String getState_out() {
		return _position.getState_out();
	}

	/**
	* Returns the strategy_in of this position.
	*
	* @return the strategy_in of this position
	*/
	@Override
	public java.lang.String getStrategy_in() {
		return _position.getStrategy_in();
	}

	/**
	* Returns the strategy_out of this position.
	*
	* @return the strategy_out of this position
	*/
	@Override
	public java.lang.String getStrategy_out() {
		return _position.getStrategy_out();
	}

	/**
	* Returns the trading_data_operations of this position.
	*
	* @return the trading_data_operations of this position
	*/
	@Override
	public java.lang.String getTrading_data_operations() {
		return _position.getTrading_data_operations();
	}

	/**
	* Returns the type of this position.
	*
	* @return the type of this position
	*/
	@Override
	public java.lang.String getType() {
		return _position.getType();
	}

	/**
	* Returns the uuid of this position.
	*
	* @return the uuid of this position
	*/
	@Override
	public java.lang.String getUuid() {
		return _position.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _position.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _position.toXmlString();
	}

	/**
	* Returns the create date of this position.
	*
	* @return the create date of this position
	*/
	@Override
	public Date getCreateDate() {
		return _position.getCreateDate();
	}

	/**
	* Returns the date_in of this position.
	*
	* @return the date_in of this position
	*/
	@Override
	public Date getDate_in() {
		return _position.getDate_in();
	}

	/**
	* Returns the date_out of this position.
	*
	* @return the date_out of this position
	*/
	@Override
	public Date getDate_out() {
		return _position.getDate_out();
	}

	/**
	* Returns the date_real_in of this position.
	*
	* @return the date_real_in of this position
	*/
	@Override
	public Date getDate_real_in() {
		return _position.getDate_real_in();
	}

	/**
	* Returns the date_real_out of this position.
	*
	* @return the date_real_out of this position
	*/
	@Override
	public Date getDate_real_out() {
		return _position.getDate_real_out();
	}

	/**
	* Returns the modified date of this position.
	*
	* @return the modified date of this position
	*/
	@Override
	public Date getModifiedDate() {
		return _position.getModifiedDate();
	}

	/**
	* Returns the client id_in of this position.
	*
	* @return the client id_in of this position
	*/
	@Override
	public long getClientId_in() {
		return _position.getClientId_in();
	}

	/**
	* Returns the client id_out of this position.
	*
	* @return the client id_out of this position
	*/
	@Override
	public long getClientId_out() {
		return _position.getClientId_out();
	}

	/**
	* Returns the company ID of this position.
	*
	* @return the company ID of this position
	*/
	@Override
	public long getCompanyId() {
		return _position.getCompanyId();
	}

	/**
	* Returns the group ID of this position.
	*
	* @return the group ID of this position
	*/
	@Override
	public long getGroupId() {
		return _position.getGroupId();
	}

	/**
	* Returns the pendingcancelled of this position.
	*
	* @return the pendingcancelled of this position
	*/
	@Override
	public long getPendingcancelled() {
		return _position.getPendingcancelled();
	}

	/**
	* Returns the position ID of this position.
	*
	* @return the position ID of this position
	*/
	@Override
	public long getPositionId() {
		return _position.getPositionId();
	}

	/**
	* Returns the position id_tws_in of this position.
	*
	* @return the position id_tws_in of this position
	*/
	@Override
	public long getPositionId_tws_in() {
		return _position.getPositionId_tws_in();
	}

	/**
	* Returns the position id_tws_out of this position.
	*
	* @return the position id_tws_out of this position
	*/
	@Override
	public long getPositionId_tws_out() {
		return _position.getPositionId_tws_out();
	}

	/**
	* Returns the primary key of this position.
	*
	* @return the primary key of this position
	*/
	@Override
	public long getPrimaryKey() {
		return _position.getPrimaryKey();
	}

	/**
	* Returns the share ID of this position.
	*
	* @return the share ID of this position
	*/
	@Override
	public long getShareId() {
		return _position.getShareId();
	}

	/**
	* Returns the share_number of this position.
	*
	* @return the share_number of this position
	*/
	@Override
	public long getShare_number() {
		return _position.getShare_number();
	}

	/**
	* Returns the share_number_to_trade of this position.
	*
	* @return the share_number_to_trade of this position
	*/
	@Override
	public long getShare_number_to_trade() {
		return _position.getShare_number_to_trade();
	}

	/**
	* Returns the share_number_traded of this position.
	*
	* @return the share_number_traded of this position
	*/
	@Override
	public long getShare_number_traded() {
		return _position.getShare_number_traded();
	}

	@Override
	public void persist() {
		_position.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_position.setCachedModel(cachedModel);
	}

	/**
	* Sets the client id_in of this position.
	*
	* @param clientId_in the client id_in of this position
	*/
	@Override
	public void setClientId_in(long clientId_in) {
		_position.setClientId_in(clientId_in);
	}

	/**
	* Sets the client id_out of this position.
	*
	* @param clientId_out the client id_out of this position
	*/
	@Override
	public void setClientId_out(long clientId_out) {
		_position.setClientId_out(clientId_out);
	}

	/**
	* Sets the company ID of this position.
	*
	* @param companyId the company ID of this position
	*/
	@Override
	public void setCompanyId(long companyId) {
		_position.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this position.
	*
	* @param createDate the create date of this position
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_position.setCreateDate(createDate);
	}

	/**
	* Sets the date_in of this position.
	*
	* @param date_in the date_in of this position
	*/
	@Override
	public void setDate_in(Date date_in) {
		_position.setDate_in(date_in);
	}

	/**
	* Sets the date_out of this position.
	*
	* @param date_out the date_out of this position
	*/
	@Override
	public void setDate_out(Date date_out) {
		_position.setDate_out(date_out);
	}

	/**
	* Sets the date_real_in of this position.
	*
	* @param date_real_in the date_real_in of this position
	*/
	@Override
	public void setDate_real_in(Date date_real_in) {
		_position.setDate_real_in(date_real_in);
	}

	/**
	* Sets the date_real_out of this position.
	*
	* @param date_real_out the date_real_out of this position
	*/
	@Override
	public void setDate_real_out(Date date_real_out) {
		_position.setDate_real_out(date_real_out);
	}

	/**
	* Sets the description of this position.
	*
	* @param description the description of this position
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_position.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_position.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_position.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_position.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this position.
	*
	* @param groupId the group ID of this position
	*/
	@Override
	public void setGroupId(long groupId) {
		_position.setGroupId(groupId);
	}

	/**
	* Sets the limit_price_in of this position.
	*
	* @param limit_price_in the limit_price_in of this position
	*/
	@Override
	public void setLimit_price_in(double limit_price_in) {
		_position.setLimit_price_in(limit_price_in);
	}

	/**
	* Sets the limit_price_out of this position.
	*
	* @param limit_price_out the limit_price_out of this position
	*/
	@Override
	public void setLimit_price_out(double limit_price_out) {
		_position.setLimit_price_out(limit_price_out);
	}

	/**
	* Sets the modified date of this position.
	*
	* @param modifiedDate the modified date of this position
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_position.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_position.setNew(n);
	}

	/**
	* Sets the pendingcancelled of this position.
	*
	* @param pendingcancelled the pendingcancelled of this position
	*/
	@Override
	public void setPendingcancelled(long pendingcancelled) {
		_position.setPendingcancelled(pendingcancelled);
	}

	/**
	* Sets the percentualstoplost_out of this position.
	*
	* @param percentualstoplost_out the percentualstoplost_out of this position
	*/
	@Override
	public void setPercentualstoplost_out(double percentualstoplost_out) {
		_position.setPercentualstoplost_out(percentualstoplost_out);
	}

	/**
	* Sets the percentualstopprofit_out of this position.
	*
	* @param percentualstopprofit_out the percentualstopprofit_out of this position
	*/
	@Override
	public void setPercentualstopprofit_out(double percentualstopprofit_out) {
		_position.setPercentualstopprofit_out(percentualstopprofit_out);
	}

	/**
	* Sets the position ID of this position.
	*
	* @param positionId the position ID of this position
	*/
	@Override
	public void setPositionId(long positionId) {
		_position.setPositionId(positionId);
	}

	/**
	* Sets the position id_tws_in of this position.
	*
	* @param positionId_tws_in the position id_tws_in of this position
	*/
	@Override
	public void setPositionId_tws_in(long positionId_tws_in) {
		_position.setPositionId_tws_in(positionId_tws_in);
	}

	/**
	* Sets the position id_tws_out of this position.
	*
	* @param positionId_tws_out the position id_tws_out of this position
	*/
	@Override
	public void setPositionId_tws_out(long positionId_tws_out) {
		_position.setPositionId_tws_out(positionId_tws_out);
	}

	/**
	* Sets the price_in of this position.
	*
	* @param price_in the price_in of this position
	*/
	@Override
	public void setPrice_in(double price_in) {
		_position.setPrice_in(price_in);
	}

	/**
	* Sets the price_out of this position.
	*
	* @param price_out the price_out of this position
	*/
	@Override
	public void setPrice_out(double price_out) {
		_position.setPrice_out(price_out);
	}

	/**
	* Sets the price_real_in of this position.
	*
	* @param price_real_in the price_real_in of this position
	*/
	@Override
	public void setPrice_real_in(double price_real_in) {
		_position.setPrice_real_in(price_real_in);
	}

	/**
	* Sets the price_real_out of this position.
	*
	* @param price_real_out the price_real_out of this position
	*/
	@Override
	public void setPrice_real_out(double price_real_out) {
		_position.setPrice_real_out(price_real_out);
	}

	/**
	* Sets the pricestoplost_out of this position.
	*
	* @param pricestoplost_out the pricestoplost_out of this position
	*/
	@Override
	public void setPricestoplost_out(double pricestoplost_out) {
		_position.setPricestoplost_out(pricestoplost_out);
	}

	/**
	* Sets the pricestopprofit_out of this position.
	*
	* @param pricestopprofit_out the pricestopprofit_out of this position
	*/
	@Override
	public void setPricestopprofit_out(double pricestopprofit_out) {
		_position.setPricestopprofit_out(pricestopprofit_out);
	}

	/**
	* Sets the primary key of this position.
	*
	* @param primaryKey the primary key of this position
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_position.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_position.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the share ID of this position.
	*
	* @param shareId the share ID of this position
	*/
	@Override
	public void setShareId(long shareId) {
		_position.setShareId(shareId);
	}

	/**
	* Sets the share_number of this position.
	*
	* @param share_number the share_number of this position
	*/
	@Override
	public void setShare_number(long share_number) {
		_position.setShare_number(share_number);
	}

	/**
	* Sets the share_number_to_trade of this position.
	*
	* @param share_number_to_trade the share_number_to_trade of this position
	*/
	@Override
	public void setShare_number_to_trade(long share_number_to_trade) {
		_position.setShare_number_to_trade(share_number_to_trade);
	}

	/**
	* Sets the share_number_traded of this position.
	*
	* @param share_number_traded the share_number_traded of this position
	*/
	@Override
	public void setShare_number_traded(long share_number_traded) {
		_position.setShare_number_traded(share_number_traded);
	}

	/**
	* Sets whether this position is simulation_mode.
	*
	* @param simulation_mode the simulation_mode of this position
	*/
	@Override
	public void setSimulation_mode(boolean simulation_mode) {
		_position.setSimulation_mode(simulation_mode);
	}

	/**
	* Sets the state of this position.
	*
	* @param state the state of this position
	*/
	@Override
	public void setState(java.lang.String state) {
		_position.setState(state);
	}

	/**
	* Sets the state_in of this position.
	*
	* @param state_in the state_in of this position
	*/
	@Override
	public void setState_in(java.lang.String state_in) {
		_position.setState_in(state_in);
	}

	/**
	* Sets the state_out of this position.
	*
	* @param state_out the state_out of this position
	*/
	@Override
	public void setState_out(java.lang.String state_out) {
		_position.setState_out(state_out);
	}

	/**
	* Sets the strategy_in of this position.
	*
	* @param strategy_in the strategy_in of this position
	*/
	@Override
	public void setStrategy_in(java.lang.String strategy_in) {
		_position.setStrategy_in(strategy_in);
	}

	/**
	* Sets the strategy_out of this position.
	*
	* @param strategy_out the strategy_out of this position
	*/
	@Override
	public void setStrategy_out(java.lang.String strategy_out) {
		_position.setStrategy_out(strategy_out);
	}

	/**
	* Sets the totalcommision of this position.
	*
	* @param totalcommision the totalcommision of this position
	*/
	@Override
	public void setTotalcommision(double totalcommision) {
		_position.setTotalcommision(totalcommision);
	}

	/**
	* Sets the trading_data_operations of this position.
	*
	* @param trading_data_operations the trading_data_operations of this position
	*/
	@Override
	public void setTrading_data_operations(
		java.lang.String trading_data_operations) {
		_position.setTrading_data_operations(trading_data_operations);
	}

	/**
	* Sets the type of this position.
	*
	* @param type the type of this position
	*/
	@Override
	public void setType(java.lang.String type) {
		_position.setType(type);
	}

	/**
	* Sets the uuid of this position.
	*
	* @param uuid the uuid of this position
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_position.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PositionWrapper)) {
			return false;
		}

		PositionWrapper positionWrapper = (PositionWrapper)obj;

		if (Objects.equals(_position, positionWrapper._position)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _position.getStagedModelType();
	}

	@Override
	public Position getWrappedModel() {
		return _position;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _position.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _position.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_position.resetOriginalValues();
	}

	private final Position _position;
}