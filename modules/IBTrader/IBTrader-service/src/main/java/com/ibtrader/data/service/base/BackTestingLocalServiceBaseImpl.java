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

package com.ibtrader.data.service.base;

import aQute.bnd.annotation.ProviderType;

import com.ibtrader.data.model.BackTesting;
import com.ibtrader.data.service.BackTestingLocalService;
import com.ibtrader.data.service.persistence.AuditIndicatorsStrategyPersistence;
import com.ibtrader.data.service.persistence.BackTestingPersistence;
import com.ibtrader.data.service.persistence.ConfigPersistence;
import com.ibtrader.data.service.persistence.HistoricalRealtimeFinder;
import com.ibtrader.data.service.persistence.HistoricalRealtimePersistence;
import com.ibtrader.data.service.persistence.IBOrderFinder;
import com.ibtrader.data.service.persistence.IBOrderPersistence;
import com.ibtrader.data.service.persistence.MarketPersistence;
import com.ibtrader.data.service.persistence.PositionFinder;
import com.ibtrader.data.service.persistence.PositionPersistence;
import com.ibtrader.data.service.persistence.RealtimeFinder;
import com.ibtrader.data.service.persistence.RealtimePersistence;
import com.ibtrader.data.service.persistence.SharePersistence;
import com.ibtrader.data.service.persistence.StrategyPersistence;
import com.ibtrader.data.service.persistence.StrategySharePersistence;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the back testing local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ibtrader.data.service.impl.BackTestingLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ibtrader.data.service.impl.BackTestingLocalServiceImpl
 * @see com.ibtrader.data.service.BackTestingLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class BackTestingLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements BackTestingLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.ibtrader.data.service.BackTestingLocalServiceUtil} to access the back testing local service.
	 */

	/**
	 * Adds the back testing to the database. Also notifies the appropriate model listeners.
	 *
	 * @param backTesting the back testing
	 * @return the back testing that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public BackTesting addBackTesting(BackTesting backTesting) {
		backTesting.setNew(true);

		return backTestingPersistence.update(backTesting);
	}

	/**
	 * Creates a new back testing with the primary key. Does not add the back testing to the database.
	 *
	 * @param backTId the primary key for the new back testing
	 * @return the new back testing
	 */
	@Override
	public BackTesting createBackTesting(long backTId) {
		return backTestingPersistence.create(backTId);
	}

	/**
	 * Deletes the back testing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param backTId the primary key of the back testing
	 * @return the back testing that was removed
	 * @throws PortalException if a back testing with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public BackTesting deleteBackTesting(long backTId)
		throws PortalException {
		return backTestingPersistence.remove(backTId);
	}

	/**
	 * Deletes the back testing from the database. Also notifies the appropriate model listeners.
	 *
	 * @param backTesting the back testing
	 * @return the back testing that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public BackTesting deleteBackTesting(BackTesting backTesting) {
		return backTestingPersistence.remove(backTesting);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(BackTesting.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return backTestingPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return backTestingPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return backTestingPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return backTestingPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return backTestingPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public BackTesting fetchBackTesting(long backTId) {
		return backTestingPersistence.fetchByPrimaryKey(backTId);
	}

	/**
	 * Returns the back testing matching the UUID and group.
	 *
	 * @param uuid the back testing's UUID
	 * @param groupId the primary key of the group
	 * @return the matching back testing, or <code>null</code> if a matching back testing could not be found
	 */
	@Override
	public BackTesting fetchBackTestingByUuidAndGroupId(String uuid,
		long groupId) {
		return backTestingPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the back testing with the primary key.
	 *
	 * @param backTId the primary key of the back testing
	 * @return the back testing
	 * @throws PortalException if a back testing with the primary key could not be found
	 */
	@Override
	public BackTesting getBackTesting(long backTId) throws PortalException {
		return backTestingPersistence.findByPrimaryKey(backTId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(backTestingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(BackTesting.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("backTId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(backTestingLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(BackTesting.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("backTId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(backTestingLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(BackTesting.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("backTId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {
		final ExportActionableDynamicQuery exportActionableDynamicQuery = new ExportActionableDynamicQuery() {
				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(stagedModelType,
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType,
						modelDeletionCount);

					return modelAdditionCount;
				}
			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(dynamicQuery,
						"modifiedDate");
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<BackTesting>() {
				@Override
				public void performAction(BackTesting backTesting)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						backTesting);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(BackTesting.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return backTestingLocalService.deleteBackTesting((BackTesting)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return backTestingPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the back testings matching the UUID and company.
	 *
	 * @param uuid the UUID of the back testings
	 * @param companyId the primary key of the company
	 * @return the matching back testings, or an empty list if no matches were found
	 */
	@Override
	public List<BackTesting> getBackTestingsByUuidAndCompanyId(String uuid,
		long companyId) {
		return backTestingPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of back testings matching the UUID and company.
	 *
	 * @param uuid the UUID of the back testings
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching back testings, or an empty list if no matches were found
	 */
	@Override
	public List<BackTesting> getBackTestingsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<BackTesting> orderByComparator) {
		return backTestingPersistence.findByUuid_C(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	 * Returns the back testing matching the UUID and group.
	 *
	 * @param uuid the back testing's UUID
	 * @param groupId the primary key of the group
	 * @return the matching back testing
	 * @throws PortalException if a matching back testing could not be found
	 */
	@Override
	public BackTesting getBackTestingByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {
		return backTestingPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the back testings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ibtrader.data.model.impl.BackTestingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of back testings
	 * @param end the upper bound of the range of back testings (not inclusive)
	 * @return the range of back testings
	 */
	@Override
	public List<BackTesting> getBackTestings(int start, int end) {
		return backTestingPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of back testings.
	 *
	 * @return the number of back testings
	 */
	@Override
	public int getBackTestingsCount() {
		return backTestingPersistence.countAll();
	}

	/**
	 * Updates the back testing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param backTesting the back testing
	 * @return the back testing that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public BackTesting updateBackTesting(BackTesting backTesting) {
		return backTestingPersistence.update(backTesting);
	}

	/**
	 * Returns the audit indicators strategy local service.
	 *
	 * @return the audit indicators strategy local service
	 */
	public com.ibtrader.data.service.AuditIndicatorsStrategyLocalService getAuditIndicatorsStrategyLocalService() {
		return auditIndicatorsStrategyLocalService;
	}

	/**
	 * Sets the audit indicators strategy local service.
	 *
	 * @param auditIndicatorsStrategyLocalService the audit indicators strategy local service
	 */
	public void setAuditIndicatorsStrategyLocalService(
		com.ibtrader.data.service.AuditIndicatorsStrategyLocalService auditIndicatorsStrategyLocalService) {
		this.auditIndicatorsStrategyLocalService = auditIndicatorsStrategyLocalService;
	}

	/**
	 * Returns the audit indicators strategy persistence.
	 *
	 * @return the audit indicators strategy persistence
	 */
	public AuditIndicatorsStrategyPersistence getAuditIndicatorsStrategyPersistence() {
		return auditIndicatorsStrategyPersistence;
	}

	/**
	 * Sets the audit indicators strategy persistence.
	 *
	 * @param auditIndicatorsStrategyPersistence the audit indicators strategy persistence
	 */
	public void setAuditIndicatorsStrategyPersistence(
		AuditIndicatorsStrategyPersistence auditIndicatorsStrategyPersistence) {
		this.auditIndicatorsStrategyPersistence = auditIndicatorsStrategyPersistence;
	}

	/**
	 * Returns the back testing local service.
	 *
	 * @return the back testing local service
	 */
	public BackTestingLocalService getBackTestingLocalService() {
		return backTestingLocalService;
	}

	/**
	 * Sets the back testing local service.
	 *
	 * @param backTestingLocalService the back testing local service
	 */
	public void setBackTestingLocalService(
		BackTestingLocalService backTestingLocalService) {
		this.backTestingLocalService = backTestingLocalService;
	}

	/**
	 * Returns the back testing persistence.
	 *
	 * @return the back testing persistence
	 */
	public BackTestingPersistence getBackTestingPersistence() {
		return backTestingPersistence;
	}

	/**
	 * Sets the back testing persistence.
	 *
	 * @param backTestingPersistence the back testing persistence
	 */
	public void setBackTestingPersistence(
		BackTestingPersistence backTestingPersistence) {
		this.backTestingPersistence = backTestingPersistence;
	}

	/**
	 * Returns the config local service.
	 *
	 * @return the config local service
	 */
	public com.ibtrader.data.service.ConfigLocalService getConfigLocalService() {
		return configLocalService;
	}

	/**
	 * Sets the config local service.
	 *
	 * @param configLocalService the config local service
	 */
	public void setConfigLocalService(
		com.ibtrader.data.service.ConfigLocalService configLocalService) {
		this.configLocalService = configLocalService;
	}

	/**
	 * Returns the config persistence.
	 *
	 * @return the config persistence
	 */
	public ConfigPersistence getConfigPersistence() {
		return configPersistence;
	}

	/**
	 * Sets the config persistence.
	 *
	 * @param configPersistence the config persistence
	 */
	public void setConfigPersistence(ConfigPersistence configPersistence) {
		this.configPersistence = configPersistence;
	}

	/**
	 * Returns the historical realtime local service.
	 *
	 * @return the historical realtime local service
	 */
	public com.ibtrader.data.service.HistoricalRealtimeLocalService getHistoricalRealtimeLocalService() {
		return historicalRealtimeLocalService;
	}

	/**
	 * Sets the historical realtime local service.
	 *
	 * @param historicalRealtimeLocalService the historical realtime local service
	 */
	public void setHistoricalRealtimeLocalService(
		com.ibtrader.data.service.HistoricalRealtimeLocalService historicalRealtimeLocalService) {
		this.historicalRealtimeLocalService = historicalRealtimeLocalService;
	}

	/**
	 * Returns the historical realtime persistence.
	 *
	 * @return the historical realtime persistence
	 */
	public HistoricalRealtimePersistence getHistoricalRealtimePersistence() {
		return historicalRealtimePersistence;
	}

	/**
	 * Sets the historical realtime persistence.
	 *
	 * @param historicalRealtimePersistence the historical realtime persistence
	 */
	public void setHistoricalRealtimePersistence(
		HistoricalRealtimePersistence historicalRealtimePersistence) {
		this.historicalRealtimePersistence = historicalRealtimePersistence;
	}

	/**
	 * Returns the historical realtime finder.
	 *
	 * @return the historical realtime finder
	 */
	public HistoricalRealtimeFinder getHistoricalRealtimeFinder() {
		return historicalRealtimeFinder;
	}

	/**
	 * Sets the historical realtime finder.
	 *
	 * @param historicalRealtimeFinder the historical realtime finder
	 */
	public void setHistoricalRealtimeFinder(
		HistoricalRealtimeFinder historicalRealtimeFinder) {
		this.historicalRealtimeFinder = historicalRealtimeFinder;
	}

	/**
	 * Returns the ib order local service.
	 *
	 * @return the ib order local service
	 */
	public com.ibtrader.data.service.IBOrderLocalService getIBOrderLocalService() {
		return ibOrderLocalService;
	}

	/**
	 * Sets the ib order local service.
	 *
	 * @param ibOrderLocalService the ib order local service
	 */
	public void setIBOrderLocalService(
		com.ibtrader.data.service.IBOrderLocalService ibOrderLocalService) {
		this.ibOrderLocalService = ibOrderLocalService;
	}

	/**
	 * Returns the ib order persistence.
	 *
	 * @return the ib order persistence
	 */
	public IBOrderPersistence getIBOrderPersistence() {
		return ibOrderPersistence;
	}

	/**
	 * Sets the ib order persistence.
	 *
	 * @param ibOrderPersistence the ib order persistence
	 */
	public void setIBOrderPersistence(IBOrderPersistence ibOrderPersistence) {
		this.ibOrderPersistence = ibOrderPersistence;
	}

	/**
	 * Returns the ib order finder.
	 *
	 * @return the ib order finder
	 */
	public IBOrderFinder getIBOrderFinder() {
		return ibOrderFinder;
	}

	/**
	 * Sets the ib order finder.
	 *
	 * @param ibOrderFinder the ib order finder
	 */
	public void setIBOrderFinder(IBOrderFinder ibOrderFinder) {
		this.ibOrderFinder = ibOrderFinder;
	}

	/**
	 * Returns the market local service.
	 *
	 * @return the market local service
	 */
	public com.ibtrader.data.service.MarketLocalService getMarketLocalService() {
		return marketLocalService;
	}

	/**
	 * Sets the market local service.
	 *
	 * @param marketLocalService the market local service
	 */
	public void setMarketLocalService(
		com.ibtrader.data.service.MarketLocalService marketLocalService) {
		this.marketLocalService = marketLocalService;
	}

	/**
	 * Returns the market persistence.
	 *
	 * @return the market persistence
	 */
	public MarketPersistence getMarketPersistence() {
		return marketPersistence;
	}

	/**
	 * Sets the market persistence.
	 *
	 * @param marketPersistence the market persistence
	 */
	public void setMarketPersistence(MarketPersistence marketPersistence) {
		this.marketPersistence = marketPersistence;
	}

	/**
	 * Returns the position local service.
	 *
	 * @return the position local service
	 */
	public com.ibtrader.data.service.PositionLocalService getPositionLocalService() {
		return positionLocalService;
	}

	/**
	 * Sets the position local service.
	 *
	 * @param positionLocalService the position local service
	 */
	public void setPositionLocalService(
		com.ibtrader.data.service.PositionLocalService positionLocalService) {
		this.positionLocalService = positionLocalService;
	}

	/**
	 * Returns the position persistence.
	 *
	 * @return the position persistence
	 */
	public PositionPersistence getPositionPersistence() {
		return positionPersistence;
	}

	/**
	 * Sets the position persistence.
	 *
	 * @param positionPersistence the position persistence
	 */
	public void setPositionPersistence(PositionPersistence positionPersistence) {
		this.positionPersistence = positionPersistence;
	}

	/**
	 * Returns the position finder.
	 *
	 * @return the position finder
	 */
	public PositionFinder getPositionFinder() {
		return positionFinder;
	}

	/**
	 * Sets the position finder.
	 *
	 * @param positionFinder the position finder
	 */
	public void setPositionFinder(PositionFinder positionFinder) {
		this.positionFinder = positionFinder;
	}

	/**
	 * Returns the realtime local service.
	 *
	 * @return the realtime local service
	 */
	public com.ibtrader.data.service.RealtimeLocalService getRealtimeLocalService() {
		return realtimeLocalService;
	}

	/**
	 * Sets the realtime local service.
	 *
	 * @param realtimeLocalService the realtime local service
	 */
	public void setRealtimeLocalService(
		com.ibtrader.data.service.RealtimeLocalService realtimeLocalService) {
		this.realtimeLocalService = realtimeLocalService;
	}

	/**
	 * Returns the realtime persistence.
	 *
	 * @return the realtime persistence
	 */
	public RealtimePersistence getRealtimePersistence() {
		return realtimePersistence;
	}

	/**
	 * Sets the realtime persistence.
	 *
	 * @param realtimePersistence the realtime persistence
	 */
	public void setRealtimePersistence(RealtimePersistence realtimePersistence) {
		this.realtimePersistence = realtimePersistence;
	}

	/**
	 * Returns the realtime finder.
	 *
	 * @return the realtime finder
	 */
	public RealtimeFinder getRealtimeFinder() {
		return realtimeFinder;
	}

	/**
	 * Sets the realtime finder.
	 *
	 * @param realtimeFinder the realtime finder
	 */
	public void setRealtimeFinder(RealtimeFinder realtimeFinder) {
		this.realtimeFinder = realtimeFinder;
	}

	/**
	 * Returns the share local service.
	 *
	 * @return the share local service
	 */
	public com.ibtrader.data.service.ShareLocalService getShareLocalService() {
		return shareLocalService;
	}

	/**
	 * Sets the share local service.
	 *
	 * @param shareLocalService the share local service
	 */
	public void setShareLocalService(
		com.ibtrader.data.service.ShareLocalService shareLocalService) {
		this.shareLocalService = shareLocalService;
	}

	/**
	 * Returns the share persistence.
	 *
	 * @return the share persistence
	 */
	public SharePersistence getSharePersistence() {
		return sharePersistence;
	}

	/**
	 * Sets the share persistence.
	 *
	 * @param sharePersistence the share persistence
	 */
	public void setSharePersistence(SharePersistence sharePersistence) {
		this.sharePersistence = sharePersistence;
	}

	/**
	 * Returns the strategy local service.
	 *
	 * @return the strategy local service
	 */
	public com.ibtrader.data.service.StrategyLocalService getStrategyLocalService() {
		return strategyLocalService;
	}

	/**
	 * Sets the strategy local service.
	 *
	 * @param strategyLocalService the strategy local service
	 */
	public void setStrategyLocalService(
		com.ibtrader.data.service.StrategyLocalService strategyLocalService) {
		this.strategyLocalService = strategyLocalService;
	}

	/**
	 * Returns the strategy persistence.
	 *
	 * @return the strategy persistence
	 */
	public StrategyPersistence getStrategyPersistence() {
		return strategyPersistence;
	}

	/**
	 * Sets the strategy persistence.
	 *
	 * @param strategyPersistence the strategy persistence
	 */
	public void setStrategyPersistence(StrategyPersistence strategyPersistence) {
		this.strategyPersistence = strategyPersistence;
	}

	/**
	 * Returns the strategy share local service.
	 *
	 * @return the strategy share local service
	 */
	public com.ibtrader.data.service.StrategyShareLocalService getStrategyShareLocalService() {
		return strategyShareLocalService;
	}

	/**
	 * Sets the strategy share local service.
	 *
	 * @param strategyShareLocalService the strategy share local service
	 */
	public void setStrategyShareLocalService(
		com.ibtrader.data.service.StrategyShareLocalService strategyShareLocalService) {
		this.strategyShareLocalService = strategyShareLocalService;
	}

	/**
	 * Returns the strategy share persistence.
	 *
	 * @return the strategy share persistence
	 */
	public StrategySharePersistence getStrategySharePersistence() {
		return strategySharePersistence;
	}

	/**
	 * Sets the strategy share persistence.
	 *
	 * @param strategySharePersistence the strategy share persistence
	 */
	public void setStrategySharePersistence(
		StrategySharePersistence strategySharePersistence) {
		this.strategySharePersistence = strategySharePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.ibtrader.data.model.BackTesting",
			backTestingLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.ibtrader.data.model.BackTesting");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return BackTestingLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return BackTesting.class;
	}

	protected String getModelClassName() {
		return BackTesting.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = backTestingPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.ibtrader.data.service.AuditIndicatorsStrategyLocalService.class)
	protected com.ibtrader.data.service.AuditIndicatorsStrategyLocalService auditIndicatorsStrategyLocalService;
	@BeanReference(type = AuditIndicatorsStrategyPersistence.class)
	protected AuditIndicatorsStrategyPersistence auditIndicatorsStrategyPersistence;
	@BeanReference(type = BackTestingLocalService.class)
	protected BackTestingLocalService backTestingLocalService;
	@BeanReference(type = BackTestingPersistence.class)
	protected BackTestingPersistence backTestingPersistence;
	@BeanReference(type = com.ibtrader.data.service.ConfigLocalService.class)
	protected com.ibtrader.data.service.ConfigLocalService configLocalService;
	@BeanReference(type = ConfigPersistence.class)
	protected ConfigPersistence configPersistence;
	@BeanReference(type = com.ibtrader.data.service.HistoricalRealtimeLocalService.class)
	protected com.ibtrader.data.service.HistoricalRealtimeLocalService historicalRealtimeLocalService;
	@BeanReference(type = HistoricalRealtimePersistence.class)
	protected HistoricalRealtimePersistence historicalRealtimePersistence;
	@BeanReference(type = HistoricalRealtimeFinder.class)
	protected HistoricalRealtimeFinder historicalRealtimeFinder;
	@BeanReference(type = com.ibtrader.data.service.IBOrderLocalService.class)
	protected com.ibtrader.data.service.IBOrderLocalService ibOrderLocalService;
	@BeanReference(type = IBOrderPersistence.class)
	protected IBOrderPersistence ibOrderPersistence;
	@BeanReference(type = IBOrderFinder.class)
	protected IBOrderFinder ibOrderFinder;
	@BeanReference(type = com.ibtrader.data.service.MarketLocalService.class)
	protected com.ibtrader.data.service.MarketLocalService marketLocalService;
	@BeanReference(type = MarketPersistence.class)
	protected MarketPersistence marketPersistence;
	@BeanReference(type = com.ibtrader.data.service.PositionLocalService.class)
	protected com.ibtrader.data.service.PositionLocalService positionLocalService;
	@BeanReference(type = PositionPersistence.class)
	protected PositionPersistence positionPersistence;
	@BeanReference(type = PositionFinder.class)
	protected PositionFinder positionFinder;
	@BeanReference(type = com.ibtrader.data.service.RealtimeLocalService.class)
	protected com.ibtrader.data.service.RealtimeLocalService realtimeLocalService;
	@BeanReference(type = RealtimePersistence.class)
	protected RealtimePersistence realtimePersistence;
	@BeanReference(type = RealtimeFinder.class)
	protected RealtimeFinder realtimeFinder;
	@BeanReference(type = com.ibtrader.data.service.ShareLocalService.class)
	protected com.ibtrader.data.service.ShareLocalService shareLocalService;
	@BeanReference(type = SharePersistence.class)
	protected SharePersistence sharePersistence;
	@BeanReference(type = com.ibtrader.data.service.StrategyLocalService.class)
	protected com.ibtrader.data.service.StrategyLocalService strategyLocalService;
	@BeanReference(type = StrategyPersistence.class)
	protected StrategyPersistence strategyPersistence;
	@BeanReference(type = com.ibtrader.data.service.StrategyShareLocalService.class)
	protected com.ibtrader.data.service.StrategyShareLocalService strategyShareLocalService;
	@BeanReference(type = StrategySharePersistence.class)
	protected StrategySharePersistence strategySharePersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}