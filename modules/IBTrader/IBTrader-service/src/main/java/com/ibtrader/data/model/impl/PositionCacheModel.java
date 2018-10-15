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
		StringBundler sb = new StringBundler(81);

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
		sb.append(", positionId_tws_in=");
		sb.append(positionId_tws_in);
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
		sb.append(", clientId_in=");
		sb.append(clientId_in);
		sb.append(", clientId_out=");
		sb.append(clientId_out);
		sb.append(", strategy_in=");
		sb.append(strategy_in);
		sb.append(", strategy_out=");
		sb.append(strategy_out);
		sb.append(", percentualstoplost_out=");
		sb.append(percentualstoplost_out);
		sb.append(", pricestoplost_out=");
		sb.append(pricestoplost_out);
		sb.append(", percentualstopprofit_out=");
		sb.append(percentualstopprofit_out);
		sb.append(", pricestopprofit_out=");
		sb.append(pricestopprofit_out);
		sb.append(", percentual_trailling_stop_lost=");
		sb.append(percentual_trailling_stop_lost);
		sb.append(", pricetrailling_stop_lost=");
		sb.append(pricetrailling_stop_lost);
		sb.append(", pendingcancelled=");
		sb.append(pendingcancelled);
		sb.append(", position_mode=");
		sb.append(position_mode);
		sb.append(", totalcommision=");
		sb.append(totalcommision);
		sb.append(", forceclose=");
		sb.append(forceclose);
		sb.append(", backtestingId=");
		sb.append(backtestingId);
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

		positionImpl.setPositionId_tws_in(positionId_tws_in);
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
		positionImpl.setClientId_in(clientId_in);
		positionImpl.setClientId_out(clientId_out);

		if (strategy_in == null) {
			positionImpl.setStrategy_in(StringPool.BLANK);
		}
		else {
			positionImpl.setStrategy_in(strategy_in);
		}

		if (strategy_out == null) {
			positionImpl.setStrategy_out(StringPool.BLANK);
		}
		else {
			positionImpl.setStrategy_out(strategy_out);
		}

		positionImpl.setPercentualstoplost_out(percentualstoplost_out);
		positionImpl.setPricestoplost_out(pricestoplost_out);
		positionImpl.setPercentualstopprofit_out(percentualstopprofit_out);
		positionImpl.setPricestopprofit_out(pricestopprofit_out);
		positionImpl.setPercentual_trailling_stop_lost(percentual_trailling_stop_lost);
		positionImpl.setPricetrailling_stop_lost(pricetrailling_stop_lost);
		positionImpl.setPendingcancelled(pendingcancelled);

		if (position_mode == null) {
			positionImpl.setPosition_mode(StringPool.BLANK);
		}
		else {
			positionImpl.setPosition_mode(position_mode);
		}

		positionImpl.setTotalcommision(totalcommision);
		positionImpl.setForceclose(forceclose);
		positionImpl.setBacktestingId(backtestingId);

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

		positionId_tws_in = objectInput.readLong();

		positionId_tws_out = objectInput.readLong();
		type = objectInput.readUTF();

		price_out = objectInput.readDouble();

		price_real_out = objectInput.readDouble();

		limit_price_out = objectInput.readDouble();
		date_out = objectInput.readLong();
		date_real_out = objectInput.readLong();

		share_number = objectInput.readLong();

		clientId_in = objectInput.readLong();

		clientId_out = objectInput.readLong();
		strategy_in = objectInput.readUTF();
		strategy_out = objectInput.readUTF();

		percentualstoplost_out = objectInput.readDouble();

		pricestoplost_out = objectInput.readDouble();

		percentualstopprofit_out = objectInput.readDouble();

		pricestopprofit_out = objectInput.readDouble();

		percentual_trailling_stop_lost = objectInput.readDouble();

		pricetrailling_stop_lost = objectInput.readDouble();

		pendingcancelled = objectInput.readLong();
		position_mode = objectInput.readUTF();

		totalcommision = objectInput.readDouble();

		forceclose = objectInput.readBoolean();

		backtestingId = objectInput.readLong();
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

		objectOutput.writeLong(positionId_tws_in);

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

		objectOutput.writeLong(clientId_in);

		objectOutput.writeLong(clientId_out);

		if (strategy_in == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(strategy_in);
		}

		if (strategy_out == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(strategy_out);
		}

		objectOutput.writeDouble(percentualstoplost_out);

		objectOutput.writeDouble(pricestoplost_out);

		objectOutput.writeDouble(percentualstopprofit_out);

		objectOutput.writeDouble(pricestopprofit_out);

		objectOutput.writeDouble(percentual_trailling_stop_lost);

		objectOutput.writeDouble(pricetrailling_stop_lost);

		objectOutput.writeLong(pendingcancelled);

		if (position_mode == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(position_mode);
		}

		objectOutput.writeDouble(totalcommision);

		objectOutput.writeBoolean(forceclose);

		objectOutput.writeLong(backtestingId);
	}

	public String uuid;
	public long positionId;
	public long groupId;
	public long companyId;
	public long shareId;
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
	public long positionId_tws_in;
	public long positionId_tws_out;
	public String type;
	public double price_out;
	public double price_real_out;
	public double limit_price_out;
	public long date_out;
	public long date_real_out;
	public long share_number;
	public long clientId_in;
	public long clientId_out;
	public String strategy_in;
	public String strategy_out;
	public double percentualstoplost_out;
	public double pricestoplost_out;
	public double percentualstopprofit_out;
	public double pricestopprofit_out;
	public double percentual_trailling_stop_lost;
	public double pricetrailling_stop_lost;
	public long pendingcancelled;
	public String position_mode;
	public double totalcommision;
	public boolean forceclose;
	public long backtestingId;
}