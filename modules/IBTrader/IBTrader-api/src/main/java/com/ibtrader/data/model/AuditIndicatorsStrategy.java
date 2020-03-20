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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AuditIndicatorsStrategy service. Represents a row in the &quot;ibtrader_AuditIndicatorsStrategy&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AuditIndicatorsStrategyModel
 * @see com.ibtrader.data.model.impl.AuditIndicatorsStrategyImpl
 * @see com.ibtrader.data.model.impl.AuditIndicatorsStrategyModelImpl
 * @generated
 */
@ImplementationClassName("com.ibtrader.data.model.impl.AuditIndicatorsStrategyImpl")
@ProviderType
public interface AuditIndicatorsStrategy extends AuditIndicatorsStrategyModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.ibtrader.data.model.impl.AuditIndicatorsStrategyImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AuditIndicatorsStrategy, Long> GROUP_ID_ACCESSOR =
		new Accessor<AuditIndicatorsStrategy, Long>() {
			@Override
			public Long get(AuditIndicatorsStrategy auditIndicatorsStrategy) {
				return auditIndicatorsStrategy.getGroupId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AuditIndicatorsStrategy> getTypeClass() {
				return AuditIndicatorsStrategy.class;
			}
		};

	public static final Accessor<AuditIndicatorsStrategy, Long> COMPANY_ID_ACCESSOR =
		new Accessor<AuditIndicatorsStrategy, Long>() {
			@Override
			public Long get(AuditIndicatorsStrategy auditIndicatorsStrategy) {
				return auditIndicatorsStrategy.getCompanyId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AuditIndicatorsStrategy> getTypeClass() {
				return AuditIndicatorsStrategy.class;
			}
		};

	public static final Accessor<AuditIndicatorsStrategy, String> AUDIT_DATE_ACCESSOR =
		new Accessor<AuditIndicatorsStrategy, String>() {
			@Override
			public String get(AuditIndicatorsStrategy auditIndicatorsStrategy) {
				return auditIndicatorsStrategy.getAuditDate();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<AuditIndicatorsStrategy> getTypeClass() {
				return AuditIndicatorsStrategy.class;
			}
		};

	public static final Accessor<AuditIndicatorsStrategy, String> AUDITSTRATEGY_ACCESSOR =
		new Accessor<AuditIndicatorsStrategy, String>() {
			@Override
			public String get(AuditIndicatorsStrategy auditIndicatorsStrategy) {
				return auditIndicatorsStrategy.getAuditstrategy();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<AuditIndicatorsStrategy> getTypeClass() {
				return AuditIndicatorsStrategy.class;
			}
		};

	public static final Accessor<AuditIndicatorsStrategy, Long> SHARE_ID_ACCESSOR =
		new Accessor<AuditIndicatorsStrategy, Long>() {
			@Override
			public Long get(AuditIndicatorsStrategy auditIndicatorsStrategy) {
				return auditIndicatorsStrategy.getShareId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AuditIndicatorsStrategy> getTypeClass() {
				return AuditIndicatorsStrategy.class;
			}
		};
}