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

import com.ibtrader.data.model.Share;

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
 * The cache model class for representing Share in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Share
 * @generated
 */
@ProviderType
public class ShareCacheModel implements CacheModel<Share>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ShareCacheModel)) {
			return false;
		}

		ShareCacheModel shareCacheModel = (ShareCacheModel)obj;

		if (shareId == shareCacheModel.shareId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, shareId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(61);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", shareId=");
		sb.append(shareId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", symbol=");
		sb.append(symbol);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", active=");
		sb.append(active);
		sb.append(", numbertopurchase=");
		sb.append(numbertopurchase);
		sb.append(", percentual_limit_buy=");
		sb.append(percentual_limit_buy);
		sb.append(", percentual_stop_lost=");
		sb.append(percentual_stop_lost);
		sb.append(", percentual_stop_profit=");
		sb.append(percentual_stop_profit);
		sb.append(", percentual_stop_profit_position=");
		sb.append(percentual_stop_profit_position);
		sb.append(", percentual_trailling_stop_lost=");
		sb.append(percentual_trailling_stop_lost);
		sb.append(", expiry_date=");
		sb.append(expiry_date);
		sb.append(", expiry_expression=");
		sb.append(expiry_expression);
		sb.append(", tick_futures=");
		sb.append(tick_futures);
		sb.append(", multiplier=");
		sb.append(multiplier);
		sb.append(", security_type=");
		sb.append(security_type);
		sb.append(", exchange=");
		sb.append(exchange);
		sb.append(", primary_exchange=");
		sb.append(primary_exchange);
		sb.append(", userCreatedId=");
		sb.append(userCreatedId);
		sb.append(", marketId=");
		sb.append(marketId);
		sb.append(", validated_trader_provider=");
		sb.append(validated_trader_provider);
		sb.append(", date_validated_trader_provider=");
		sb.append(date_validated_trader_provider);
		sb.append(", last_error_trader_provider=");
		sb.append(last_error_trader_provider);
		sb.append(", simulation_end_date=");
		sb.append(simulation_end_date);
		sb.append(", trading_hours=");
		sb.append(trading_hours);
		sb.append(", date_filled_realtime_gaps=");
		sb.append(date_filled_realtime_gaps);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Share toEntityModel() {
		ShareImpl shareImpl = new ShareImpl();

		if (uuid == null) {
			shareImpl.setUuid(StringPool.BLANK);
		}
		else {
			shareImpl.setUuid(uuid);
		}

		shareImpl.setShareId(shareId);

		if (name == null) {
			shareImpl.setName(StringPool.BLANK);
		}
		else {
			shareImpl.setName(name);
		}

		if (symbol == null) {
			shareImpl.setSymbol(StringPool.BLANK);
		}
		else {
			shareImpl.setSymbol(symbol);
		}

		shareImpl.setGroupId(groupId);
		shareImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			shareImpl.setCreateDate(null);
		}
		else {
			shareImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			shareImpl.setModifiedDate(null);
		}
		else {
			shareImpl.setModifiedDate(new Date(modifiedDate));
		}

		shareImpl.setActive(active);
		shareImpl.setNumbertopurchase(numbertopurchase);
		shareImpl.setPercentual_limit_buy(percentual_limit_buy);
		shareImpl.setPercentual_stop_lost(percentual_stop_lost);
		shareImpl.setPercentual_stop_profit(percentual_stop_profit);
		shareImpl.setPercentual_stop_profit_position(percentual_stop_profit_position);
		shareImpl.setPercentual_trailling_stop_lost(percentual_trailling_stop_lost);

		if (expiry_date == Long.MIN_VALUE) {
			shareImpl.setExpiry_date(null);
		}
		else {
			shareImpl.setExpiry_date(new Date(expiry_date));
		}

		if (expiry_expression == null) {
			shareImpl.setExpiry_expression(StringPool.BLANK);
		}
		else {
			shareImpl.setExpiry_expression(expiry_expression);
		}

		shareImpl.setTick_futures(tick_futures);
		shareImpl.setMultiplier(multiplier);

		if (security_type == null) {
			shareImpl.setSecurity_type(StringPool.BLANK);
		}
		else {
			shareImpl.setSecurity_type(security_type);
		}

		if (exchange == null) {
			shareImpl.setExchange(StringPool.BLANK);
		}
		else {
			shareImpl.setExchange(exchange);
		}

		if (primary_exchange == null) {
			shareImpl.setPrimary_exchange(StringPool.BLANK);
		}
		else {
			shareImpl.setPrimary_exchange(primary_exchange);
		}

		shareImpl.setUserCreatedId(userCreatedId);
		shareImpl.setMarketId(marketId);
		shareImpl.setValidated_trader_provider(validated_trader_provider);

		if (date_validated_trader_provider == Long.MIN_VALUE) {
			shareImpl.setDate_validated_trader_provider(null);
		}
		else {
			shareImpl.setDate_validated_trader_provider(new Date(
					date_validated_trader_provider));
		}

		if (last_error_trader_provider == null) {
			shareImpl.setLast_error_trader_provider(StringPool.BLANK);
		}
		else {
			shareImpl.setLast_error_trader_provider(last_error_trader_provider);
		}

		if (simulation_end_date == Long.MIN_VALUE) {
			shareImpl.setSimulation_end_date(null);
		}
		else {
			shareImpl.setSimulation_end_date(new Date(simulation_end_date));
		}

		if (trading_hours == null) {
			shareImpl.setTrading_hours(StringPool.BLANK);
		}
		else {
			shareImpl.setTrading_hours(trading_hours);
		}

		if (date_filled_realtime_gaps == Long.MIN_VALUE) {
			shareImpl.setDate_filled_realtime_gaps(null);
		}
		else {
			shareImpl.setDate_filled_realtime_gaps(new Date(
					date_filled_realtime_gaps));
		}

		shareImpl.resetOriginalValues();

		return shareImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		shareId = objectInput.readLong();
		name = objectInput.readUTF();
		symbol = objectInput.readUTF();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		active = objectInput.readBoolean();

		numbertopurchase = objectInput.readLong();

		percentual_limit_buy = objectInput.readDouble();

		percentual_stop_lost = objectInput.readDouble();

		percentual_stop_profit = objectInput.readDouble();

		percentual_stop_profit_position = objectInput.readDouble();

		percentual_trailling_stop_lost = objectInput.readDouble();
		expiry_date = objectInput.readLong();
		expiry_expression = objectInput.readUTF();

		tick_futures = objectInput.readDouble();

		multiplier = objectInput.readDouble();
		security_type = objectInput.readUTF();
		exchange = objectInput.readUTF();
		primary_exchange = objectInput.readUTF();

		userCreatedId = objectInput.readLong();

		marketId = objectInput.readLong();

		validated_trader_provider = objectInput.readBoolean();
		date_validated_trader_provider = objectInput.readLong();
		last_error_trader_provider = objectInput.readUTF();
		simulation_end_date = objectInput.readLong();
		trading_hours = objectInput.readUTF();
		date_filled_realtime_gaps = objectInput.readLong();
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

		objectOutput.writeLong(shareId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (symbol == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(symbol);
		}

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeBoolean(active);

		objectOutput.writeLong(numbertopurchase);

		objectOutput.writeDouble(percentual_limit_buy);

		objectOutput.writeDouble(percentual_stop_lost);

		objectOutput.writeDouble(percentual_stop_profit);

		objectOutput.writeDouble(percentual_stop_profit_position);

		objectOutput.writeDouble(percentual_trailling_stop_lost);
		objectOutput.writeLong(expiry_date);

		if (expiry_expression == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(expiry_expression);
		}

		objectOutput.writeDouble(tick_futures);

		objectOutput.writeDouble(multiplier);

		if (security_type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(security_type);
		}

		if (exchange == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(exchange);
		}

		if (primary_exchange == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(primary_exchange);
		}

		objectOutput.writeLong(userCreatedId);

		objectOutput.writeLong(marketId);

		objectOutput.writeBoolean(validated_trader_provider);
		objectOutput.writeLong(date_validated_trader_provider);

		if (last_error_trader_provider == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(last_error_trader_provider);
		}

		objectOutput.writeLong(simulation_end_date);

		if (trading_hours == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(trading_hours);
		}

		objectOutput.writeLong(date_filled_realtime_gaps);
	}

	public String uuid;
	public long shareId;
	public String name;
	public String symbol;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public boolean active;
	public long numbertopurchase;
	public double percentual_limit_buy;
	public double percentual_stop_lost;
	public double percentual_stop_profit;
	public double percentual_stop_profit_position;
	public double percentual_trailling_stop_lost;
	public long expiry_date;
	public String expiry_expression;
	public double tick_futures;
	public double multiplier;
	public String security_type;
	public String exchange;
	public String primary_exchange;
	public long userCreatedId;
	public long marketId;
	public boolean validated_trader_provider;
	public long date_validated_trader_provider;
	public String last_error_trader_provider;
	public long simulation_end_date;
	public String trading_hours;
	public long date_filled_realtime_gaps;
}