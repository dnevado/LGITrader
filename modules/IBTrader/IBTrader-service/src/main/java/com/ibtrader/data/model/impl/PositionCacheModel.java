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

import aQute.bnd.annotation.ProviderType;

import com.ibtrader.data.model.Position;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Position in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Position
 * @generated
 */
@ProviderType
public class PositionCacheModel implements CacheModel<Position>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PositionCacheModel)) {
			return false;
		}

		PositionCacheModel positionCacheModel = (PositionCacheModel)obj;

		if (positionId == positionCacheModel.positionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, positionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(77);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", positionId=");
		sb.append(positionId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", shareId=");
		sb.append(shareId);
		sb.append(", value=");
		sb.append(value);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", state=");
		sb.append(state);
		sb.append(", state_in=");
		sb.append(state_in);
		sb.append(", state_out=");
		sb.append(state_out);
		sb.append(", description=");
		sb.append(description);
		sb.append(", price_in=");
		sb.append(price_in);
		sb.append(", price_real_in=");
		sb.append(price_real_in);
		sb.append(", limit_price_in=");
		sb.append(limit_price_in);
		sb.append(", date_in=");
		sb.append(date_in);
		sb.append(", date_real_in=");
		sb.append(date_real_in);
		sb.append(", positionId_tws_out=");
		sb.append(positionId_tws_out);
		sb.append(", type=");
		sb.append(type);
		sb.append(", price_out=");
		sb.append(price_out);
		sb.append(", price_real_out=");
		sb.append(price_real_out);
		sb.append(", limit_price_out=");
		sb.append(limit_price_out);
		sb.append(", date_out=");
		sb.append(date_out);
		sb.append(", date_real_out=");
		sb.append(date_real_out);
		sb.append(", share_number=");
		sb.append(share_number);
		sb.append(", share_number_to_trade=");
		sb.append(share_number_to_trade);
		sb.append(", share_number_traded=");
		sb.append(share_number_traded);
		sb.append(", realtimeId_in=");
		sb.append(realtimeId_in);
		sb.append(", realtimeId_out=");
		sb.append(realtimeId_out);
		sb.append(", strategyId_in=");
		sb.append(strategyId_in);
		sb.append(", strategyId_out=");
		sb.append(strategyId_out);
		sb.append(", percentualstoplost_out=");
		sb.append(percentualstoplost_out);
		sb.append(", pricestoplost_out=");
		sb.append(pricestoplost_out);
		sb.append(", percentualstopprofit_out=");
		sb.append(percentualstopprofit_out);
		sb.append(", pricestopprofit_out=");
		sb.append(pricestopprofit_out);
		sb.append(", pendingcancelled=");
		sb.append(pendingcancelled);
		sb.append(", trading_data_operations=");
		sb.append(trading_data_operations);
		sb.append(", simulation_mode=");
		sb.append(simulation_mode);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Position toEntityModel() {
		PositionImpl positionImpl = new PositionImpl();

		if (uuid == null) {
			positionImpl.setUuid(StringPool.BLANK);
		}
		else {
			positionImpl.setUuid(uuid);
		}

		positionImpl.setPositionId(positionId);
		positionImpl.setGroupId(groupId);
		positionImpl.setCompanyId(companyId);
		positionImpl.setShareId(shareId);
		positionImpl.setValue(value);

		if (createDate == Long.MIN_VALUE) {
			positionImpl.setCreateDate(null);
		}
		else {
			positionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			positionImpl.setModifiedDate(null);
		}
		else {
			positionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (state == null) {
			positionImpl.setState(StringPool.BLANK);
		}
		else {
			positionImpl.setState(state);
		}

		if (state_in == null) {
			positionImpl.setState_in(StringPool.BLANK);
		}
		else {
			positionImpl.setState_in(state_in);
		}

		if (state_out == null) {
			positionImpl.setState_out(StringPool.BLANK);
		}
		else {
			positionImpl.setState_out(state_out);
		}

		if (description == null) {
			positionImpl.setDescription(StringPool.BLANK);
		}
		else {
			positionImpl.setDescription(description);
		}

		positionImpl.setPrice_in(price_in);
		positionImpl.setPrice_real_in(price_real_in);
		positionImpl.setLimit_price_in(limit_price_in);

		if (date_in == Long.MIN_VALUE) {
			positionImpl.setDate_in(null);
		}
		else {
			positionImpl.setDate_in(new Date(date_in));
		}

		if (date_real_in == Long.MIN_VALUE) {
			positionImpl.setDate_real_in(null);
		}
		else {
			positionImpl.setDate_real_in(new Date(date_real_in));
		}

		positionImpl.setPositionId_tws_out(positionId_tws_out);

		if (type == null) {
			positionImpl.setType(StringPool.BLANK);
		}
		else {
			positionImpl.setType(type);
		}

		positionImpl.setPrice_out(price_out);
		positionImpl.setPrice_real_out(price_real_out);
		positionImpl.setLimit_price_out(limit_price_out);

		if (date_out == Long.MIN_VALUE) {
			positionImpl.setDate_out(null);
		}
		else {
			positionImpl.setDate_out(new Date(date_out));
		}

		if (date_real_out == Long.MIN_VALUE) {
			positionImpl.setDate_real_out(null);
		}
		else {
			positionImpl.setDate_real_out(new Date(date_real_out));
		}

		positionImpl.setShare_number(share_number);
		positionImpl.setShare_number_to_trade(share_number_to_trade);
		positionImpl.setShare_number_traded(share_number_traded);
		positionImpl.setRealtimeId_in(realtimeId_in);
		positionImpl.setRealtimeId_out(realtimeId_out);
		positionImpl.setStrategyId_in(strategyId_in);
		positionImpl.setStrategyId_out(strategyId_out);
		positionImpl.setPercentualstoplost_out(percentualstoplost_out);
		positionImpl.setPricestoplost_out(pricestoplost_out);
		positionImpl.setPercentualstopprofit_out(percentualstopprofit_out);
		positionImpl.setPricestopprofit_out(pricestopprofit_out);
		positionImpl.setPendingcancelled(pendingcancelled);

		if (trading_data_operations == null) {
			positionImpl.setTrading_data_operations(StringPool.BLANK);
		}
		else {
			positionImpl.setTrading_data_operations(trading_data_operations);
		}

		positionImpl.setSimulation_mode(simulation_mode);

		positionImpl.resetOriginalValues();

		return positionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		positionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		shareId = objectInput.readLong();

		value = objectInput.readDouble();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		state = objectInput.readUTF();
		state_in = objectInput.readUTF();
		state_out = objectInput.readUTF();
		description = objectInput.readUTF();

		price_in = objectInput.readDouble();

		price_real_in = objectInput.readDouble();

		limit_price_in = objectInput.readDouble();
		date_in = objectInput.readLong();
		date_real_in = objectInput.readLong();

		positionId_tws_out = objectInput.readLong();
		type = objectInput.readUTF();

		price_out = objectInput.readDouble();

		price_real_out = objectInput.readDouble();

		limit_price_out = objectInput.readDouble();
		date_out = objectInput.readLong();
		date_real_out = objectInput.readLong();

		share_number = objectInput.readLong();

		share_number_to_trade = objectInput.readLong();

		share_number_traded = objectInput.readLong();

		realtimeId_in = objectInput.readLong();

		realtimeId_out = objectInput.readLong();

		strategyId_in = objectInput.readLong();

		strategyId_out = objectInput.readLong();

		percentualstoplost_out = objectInput.readDouble();

		pricestoplost_out = objectInput.readDouble();

		percentualstopprofit_out = objectInput.readDouble();

		pricestopprofit_out = objectInput.readDouble();

		pendingcancelled = objectInput.readLong();
		trading_data_operations = objectInput.readUTF();

		simulation_mode = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(positionId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(shareId);

		objectOutput.writeDouble(value);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (state == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(state);
		}

		if (state_in == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(state_in);
		}

		if (state_out == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(state_out);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeDouble(price_in);

		objectOutput.writeDouble(price_real_in);

		objectOutput.writeDouble(limit_price_in);
		objectOutput.writeLong(date_in);
		objectOutput.writeLong(date_real_in);

		objectOutput.writeLong(positionId_tws_out);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeDouble(price_out);

		objectOutput.writeDouble(price_real_out);

		objectOutput.writeDouble(limit_price_out);
		objectOutput.writeLong(date_out);
		objectOutput.writeLong(date_real_out);

		objectOutput.writeLong(share_number);

		objectOutput.writeLong(share_number_to_trade);

		objectOutput.writeLong(share_number_traded);

		objectOutput.writeLong(realtimeId_in);

		objectOutput.writeLong(realtimeId_out);

		objectOutput.writeLong(strategyId_in);

		objectOutput.writeLong(strategyId_out);

		objectOutput.writeDouble(percentualstoplost_out);

		objectOutput.writeDouble(pricestoplost_out);

		objectOutput.writeDouble(percentualstopprofit_out);

		objectOutput.writeDouble(pricestopprofit_out);

		objectOutput.writeLong(pendingcancelled);

		if (trading_data_operations == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(trading_data_operations);
		}

		objectOutput.writeBoolean(simulation_mode);
	}

	public String uuid;
	public long positionId;
	public long groupId;
	public long companyId;
	public long shareId;
	public double value;
	public long createDate;
	public long modifiedDate;
	public String state;
	public String state_in;
	public String state_out;
	public String description;
	public double price_in;
	public double price_real_in;
	public double limit_price_in;
	public long date_in;
	public long date_real_in;
	public long positionId_tws_out;
	public String type;
	public double price_out;
	public double price_real_out;
	public double limit_price_out;
	public long date_out;
	public long date_real_out;
	public long share_number;
	public long share_number_to_trade;
	public long share_number_traded;
	public long realtimeId_in;
	public long realtimeId_out;
	public long strategyId_in;
	public long strategyId_out;
	public double percentualstoplost_out;
	public double pricestoplost_out;
	public double percentualstopprofit_out;
	public double pricestopprofit_out;
	public long pendingcancelled;
	public String trading_data_operations;
	public boolean simulation_mode;
}