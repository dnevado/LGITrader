package com.ibtrader.indexer;

import java.util.Date;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.ibtrader.admin.constants.IBStrategyPortletKeys;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.service.StrategyLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;


@Component(immediate = true,service = Indexer.class,
property = { "indexer.class.name=com.ibtrader.data.model.Strategy"}
)
	public class StrategyIndexer extends BaseIndexer<Strategy> {

	    public static final String CLASS_NAME = Strategy.class.getName();
		private Log _log = LogFactoryUtil.getLog(Strategy.class);
		public static final String PORTLET_ID = IBStrategyPortletKeys.IBStrategy;


		public StrategyIndexer() {
			_log.info("StrategyIndexer");
			setPermissionAware(true);
			setDefaultSelectedFieldNames(
					Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
					Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.GROUP_ID,
					Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID, Field.TITLE, Field.UID);
				setFilterSearch(true);
				setPermissionAware(true);
		}
		
		@Override
		public String getClassName() {
			// TODO Auto-generated method stub
			return CLASS_NAME;
		}

		@Override
		protected void doDelete(Strategy strategy) throws Exception {
			// TODO Auto-generated method stub
			_log.info("doDelete");
			deleteDocument(strategy.getCompanyId(), strategy.getStrategyID());
		}

		@Override
		protected Document doGetDocument(Strategy strategy) throws Exception {
			// TODO Auto-generated method stub
			_log.info("doGetDocument");
			Document doc = getBaseModelDocument(IBStrategyPortletKeys.IBStrategy, strategy);

			doc.addKeyword(Field.USER_ID, strategy.getUserId());
			doc.addText(Field.TITLE, strategy.getName());
			doc.addText(Field.CONTENT, strategy.getDescription());
			return doc;			
			
		}

		@Override
		protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
				PortletResponse portletResponse) throws Exception {
			// TODO Auto-generated method stub
		//	Document doc = getBaseModelDocument(IBStrategyPortletKeys.IBStrategy, strategy);
			_log.info("doGetSummary");
			 Summary summary = createSummary(document);
			 
			 summary.setMaxContentLength(500);
			 
			 return summary;
		}

		  @Reference(unbind = "-")
		 protected void setStrategyLocalService(StrategyLocalService strategyLocalService) {
		 
			 _strategyLocalService = strategyLocalService;
		 }
		
		  
		 
		@Override
		protected void doReindex(String className, long classPK) throws Exception {
			// TODO Auto-generated method stub
			Strategy strategy = _strategyLocalService.getStrategy(classPK);
			 
			 doReindex(strategy);
		}

		@Override
		protected void doReindex(String[] ids) throws Exception {
			// TODO Auto-generated method stub
			_log.info("doReindex");
			long companyId = GetterUtil.getLong(ids[0]);			
			reindexEntries(companyId);
		}

		protected void reindexEntries(long companyId) throws PortalException {
	     
			
			final IndexableActionableDynamicQuery indexableActionableDynamicQuery =_strategyLocalService.getIndexableActionableDynamicQuery();

			indexableActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {

	                @Override
	                public void addCriteria(DynamicQuery dynamicQuery) {
	                    Property displayDateProperty = PropertyFactoryUtil.forName("createDate");

	                    dynamicQuery.add(displayDateProperty.lt(new Date()));

	                    Property statusProperty = PropertyFactoryUtil.forName("status");

	                    //Integer[] statuses = {WorkflowConstants.STATUS_APPROVED};

	                    dynamicQuery.add(statusProperty.eq(new Long(WorkflowConstants.STATUS_APPROVED)));
	                }

	            });
	        indexableActionableDynamicQuery.setCompanyId(companyId);
	        indexableActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Strategy>() {
	                @Override
	                public void performAction(Strategy strategy) {
	                    try {
	                        Document document = getDocument(strategy);

	                        indexableActionableDynamicQuery.addDocuments(document);
	                    }
	                    catch (PortalException pe) {
	                        if (_log.isWarnEnabled()) {
	                            _log.warn("Unable to index blogs entry " +strategy.getStrategyID(),pe);
	                            }
	                    }
	                }

	            });
	        indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

	        indexableActionableDynamicQuery.performActions();
	    }
		
		
		@Override
		protected void doReindex(Strategy strategy) throws Exception {
			// TODO Auto-generated method stub
			_log.info("doReindex");
			Document doc = getDocument(strategy);
			
			doc.add(new Field(Field.GROUP_ID,Long.toString(strategy.getGroupId())));
			doc.add(new Field(Field.SCOPE_GROUP_ID,Long.toString(strategy.getGroupId())));
			doc.addNumber(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
			doc.addNumber(Field.COMPANY_ID, PortalUtil.getDefaultCompanyId());
			doc.add(new Field(Field.TITLE, strategy.getName()));
			doc.add(new Field(Field.DESCRIPTION, strategy.getDescription()));
			


			//doc.add(new Field(name)); 

			IndexWriterHelperUtil.updateDocument(this.getSearchEngineId(), strategy.getCompanyId(), doc, isCommitImmediately());
		}
		
		@Reference
		 protected   StrategyLocalService _strategyLocalService;

		@Reference
		protected IndexWriterHelper _indexWriterHelper;
		@Override
		protected boolean isVisible(int entryStatus, int queryStatus) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public String getPortletId() {
			// TODO Auto-generated method stub
			return 	PORTLET_ID;
		}
	}