package com.ibtrader.admin.portlet;

import com.ibtrader.admin.constants.IBStrategyPortletKeys;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.service.StrategyLocalService;
import com.ibtrader.data.service.StrategyLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/* import DNMAssetService.model.dnmAsset;
import DNMAssetService.service.dnmAssetLocalService;
import DNMAssetService.service.dnmAssetLocalServiceUtil;
*/
import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author DAVIDNEVADO
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=ibtrader",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=IBStrategy Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + IBStrategyPortletKeys.IBStrategy,	
		"javax.portlet.resource-bundle=content.Language",
        "com.liferay.portlet.scopeable=true",
        "javax.portlet.expiration-cache=0",
        "javax.portlet.supports.mime-type=text/html",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class IBStrategyPortlet extends MVCPortlet {

	ThemeDisplay themeDisplay;
	 private static final Log   _log = LogFactoryUtil.getLog(IBStrategyPortlet.class);
	 
	/*  @Reference
     public void set_dnmAssetLocalService(dnmAssetLocalService _dnmAssetLocalService) {
        this._dnmAssetLocalService = _dnmAssetLocalService;
     }
	 
	 private dnmAssetLocalService _dnmAssetLocalService;
	 */

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY); 
		// TODO Auto-generated method stub
		
		_log.info("2");
		/*_log.info(CounterLocalServiceUtil.increment(dnmAsset.class.getName()));
	 	dnmAsset _dnmAsset = dnmAssetLocalServiceUtil.creatednmAsset(CounterLocalServiceUtil.increment(dnmAsset.class.getName()));
		*/
		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();
		 
		
		assetEntryQuery.setClassName(Strategy.class.getName());
		assetEntryQuery.setStart(0);
		assetEntryQuery.setEnd(10);		
		try {
			List<AssetEntry> results = AssetEntryServiceUtil.getEntries(assetEntryQuery);
			
			_log.info("results" + results.size());
			
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		Long StrategyId =  new Long(-1);
		if (renderRequest.getParameter("strategyId")!=null)
		{
			StrategyId = Long.parseLong(renderRequest.getParameter("strategyId"));
			if (StrategyId!=null && StrategyId.longValue()>0) // editando o nuevo
			{
				Strategy Strg = StrategyLocalServiceUtil.fetchStrategy(StrategyId.longValue());
				renderRequest.setAttribute("Strg", Strg);
			}
				
		}
		else
		{
			//List<Strategy> _lStrg = StrategyLocalServiceUtil.findByCompanyId(themeDisplay.getCompanyId()); 
			List<Strategy> _lStrg = _strategyLocalService.findBYGroupIDStatus(themeDisplay.getScopeGroupId(), WorkflowConstants.STATUS_APPROVED);
			
			//List<Strategy> _lStrg = StrategyLocalServiceUtil.find
			renderRequest.setAttribute("_lStrg", _lStrg);
			
			PortletURL iteratorURL = renderResponse.createRenderURL();
	
			SearchContainer<Strategy> searchContainer = null;
			searchContainer  = new SearchContainer<Strategy>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, iteratorURL, null, StringPool.BLANK);
			searchContainer.setEmptyResultsMessage("Estrategias no encontradas");        
			searchContainer.setResults(ListUtil.subList(_lStrg, searchContainer.getStart(), searchContainer.getEnd()));
			searchContainer.setTotal(_lStrg.size());
			renderRequest.setAttribute("searchStrategy" , searchContainer); 
			renderRequest.setAttribute("iteratorURL" , iteratorURL);
		}
		//include(renderRequest.getPortalContext(). viewJSP, renderRequest, renderResponse);
		super.doView(renderRequest, renderResponse);
	}
	
	@ProcessAction(name="editStrategy")
	public void editStrategy(ActionRequest request, ActionResponse response)
	{
		themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
	}
	
	@ProcessAction(name="deleteStrategy")
	public void deleteStrategy(ActionRequest request, ActionResponse response)
	{
		
		themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
	
	}
	
	@ProcessAction(name="addStrategy")
	public void addStrategy(ActionRequest request, ActionResponse response)
	{
		
		themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			
			
			
			ServiceContext serviceContext = ServiceContextFactory.getInstance(Strategy.class.getName(), request);
		
		
		
			String _name = ParamUtil.getString(request, "nombre", "");
			String _description = ParamUtil.getString(request, "descripcion", ""); 
			String _type = ParamUtil.getString(request, "tipo", ""); 
			String _sActive = ParamUtil.getString(request, "active","");
			boolean _active =  (!_sActive.equals("") ? true : false);
			int status = (_active ? WorkflowConstants.STATUS_APPROVED : WorkflowConstants.STATUS_INACTIVE);
			
			
			String _className = ParamUtil.getString(request, "classname","");
			
			 
			Strategy _strategy = _strategyLocalService.createStrategy(CounterLocalServiceUtil.increment(Strategy.class.getName())); 

			
			
			_strategy.setDescription(_description);
			_strategy.setName(_name);
			_strategy.setActive(_active);
			_strategy.setStatus(status);
			_strategy.setType(_type);
			_strategy.setClassName(_className);
			_strategy.setCompanyId(themeDisplay.getCompanyId());
			_strategy.setGroupId(themeDisplay.getScopeGroupId());
			_strategy.setClassName(_className);
			_strategy.setUserId(themeDisplay.getUser().getUserId()); 
			
			_strategyLocalService.addStrategy(_strategy, serviceContext);
			
			} catch (PortalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
			 
		
	}
	public void edit(RenderRequest renderRequest, RenderResponse renderResponse)
	{
		
	}
	
	@Reference(unbind = "-")
    protected void setStrategyService(StrategyLocalService strategyLocalService) {
		_strategyLocalService = strategyLocalService;
    }

    
    private StrategyLocalService _strategyLocalService;

	
}