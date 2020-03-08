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

import com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ibtrader.data.service.http.AuditIndicatorsStrategyServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.http.AuditIndicatorsStrategyServiceSoap
 * @generated
 */
@ProviderType
public class AuditIndicatorsStrategySoap implements Serializable {
	public static AuditIndicatorsStrategySoap toSoapModel(
		AuditIndicatorsStrategy model) {
		AuditIndicatorsStrategySoap soapModel = new AuditIndicatorsStrategySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setAuditDate(model.getAuditDate());
		soapModel.setAuditstrategy(model.getAuditstrategy());
		soapModel.setShareId(model.getShareId());
		soapModel.setAuditData(model.getAuditData());

		return soapModel;
	}

	public static AuditIndicatorsStrategySoap[] toSoapModels(
		AuditIndicatorsStrategy[] models) {
		AuditIndicatorsStrategySoap[] soapModels = new AuditIndicatorsStrategySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AuditIndicatorsStrategySoap[][] toSoapModels(
		AuditIndicatorsStrategy[][] models) {
		AuditIndicatorsStrategySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AuditIndicatorsStrategySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AuditIndicatorsStrategySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AuditIndicatorsStrategySoap[] toSoapModels(
		List<AuditIndicatorsStrategy> models) {
		List<AuditIndicatorsStrategySoap> soapModels = new ArrayList<AuditIndicatorsStrategySoap>(models.size());

		for (AuditIndicatorsStrategy model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AuditIndicatorsStrategySoap[soapModels.size()]);
	}

	public AuditIndicatorsStrategySoap() {
	}

	public AuditIndicatorsStrategyPK getPrimaryKey() {
		return new AuditIndicatorsStrategyPK(_groupId, _companyId, _auditDate,
			_auditstrategy, _shareId);
	}

	public void setPrimaryKey(AuditIndicatorsStrategyPK pk) {
		setGroupId(pk.groupId);
		setCompanyId(pk.companyId);
		setAuditDate(pk.auditDate);
		setAuditstrategy(pk.auditstrategy);
		setShareId(pk.shareId);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
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

	public String getAuditDate() {
		return _auditDate;
	}

	public void setAuditDate(String auditDate) {
		_auditDate = auditDate;
	}

	public String getAuditstrategy() {
		return _auditstrategy;
	}

	public void setAuditstrategy(String auditstrategy) {
		_auditstrategy = auditstrategy;
	}

	public long getShareId() {
		return _shareId;
	}

	public void setShareId(long shareId) {
		_shareId = shareId;
	}

	public String getAuditData() {
		return _auditData;
	}

	public void setAuditData(String auditData) {
		_auditData = auditData;
	}

	private String _uuid;
	private long _groupId;
	private long _companyId;
	private String _auditDate;
	private String _auditstrategy;
	private long _shareId;
	private String _auditData;
}