package IBTrader.sharemarketadmin.web.portlet;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.ibtrader.data.model.BackTesting;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.BackTestingLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalService;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalService;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalService;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.data.service.StrategyLocalService;
import com.ibtrader.data.service.StrategyShareLocalService;
import com.ibtrader.data.service.StrategyShareLocalServiceUtil;
import com.ibtrader.strategy.IBStrategyStopLost;
import com.ibtrader.strategy.IBStrategyStopProfit;
import com.ibtrader.strategy.IBStrategyTrailingStopLost;
import com.ibtrader.util.Utilities;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import IBTrader.sharemarketadmin.web.constants.IBTraderSharemarketadminWebPortletKeys;

@Component(
	    immediate = true,
	    property = {
	       "javax.portlet.name=" + IBTraderSharemarketadminWebPortletKeys.IBTraderSharemarketadminWeb,
	       "mvc.command.name=/html/add_edit_share",
	       "mvc.command.name=/html/add_edit_strategyshare",
	       "mvc.command.name=/html/add_edit_market",	       
	       "mvc.command.name=/html/view_market",	       
	       "mvc.command.name=/html/view_strategyshare",
	       "mvc.command.name=/html/backtesting_view",	       
	    },
	    service = MVCRenderCommand.class
	)
public class IBTradersharemarketcommand implements MVCRenderCommand {

	ThemeDisplay themeDisplay;
	private static final Log   _log = LogFactoryUtil.getLog(IBTraderSharemarketadminWebPortlet.class);
    private ShareLocalService _shareLocalService;
    private MarketLocalService _marketLocalService; 
    private StrategyLocalService _strategyLocalService;
    private StrategyShareLocalService _strategyshareLocalService;
    private RealtimeLocalService _realtimLocalService;


    private static String _JSP_COMMAND_EDIT_SHARE_DETAIL = "/html/add_edit_share.jsp";
    private static String  _JSP_COMMAND_EDIT_SHARE_STRATEGIES = "/html/add_edit_strategyshare.jsp";
    private static String  _JSP_COMMAND_LIST_SHARE_STRATEGIES = "/html/view_strategyshare.jsp";
    private static String  _JSP_COMMAND_EDIT_MARKET = "/html/add_edit_market.jsp";
    private static String  _JSP_COMMAND_LIST_MARKETS = "/html/view_market.jsp";
    private static String  _JSP_COMMAND_LIST_BACKTESTING = "/html/view_backtesting.jsp";
    
    
    @Reference(unbind = "-")
    protected void setRealtimeLocalService(RealtimeLocalService realtimLocalService) {
    	_realtimLocalService = realtimLocalService;
    }
    
    
    @Reference(unbind = "-")
    protected void setStrategyShareService(StrategyShareLocalService strategyshareLocalService) {
    	_strategyshareLocalService = strategyshareLocalService;
    }
    
    @Reference(unbind = "-")
    protected void setStrategyService(StrategyLocalService strategyLocalService) {
    	_strategyLocalService = strategyLocalService;
    }
    
    @Reference(unbind = "-")
    protected void setShareService(ShareLocalService shareLocalService) {
		_shareLocalService = shareLocalService;
    }
   
    @Reference(unbind = "-")
    protected void setMarketService(MarketLocalService marketLocalService) {
		_marketLocalService = marketLocalService;
    }
    public String getMVCPath(String mvcCommand)
	{
    	String jspPage = _JSP_COMMAND_EDIT_SHARE_DETAIL;    	
    	if (mvcCommand.equals("/html/add_edit_strategyshare"))
    			jspPage=_JSP_COMMAND_EDIT_SHARE_STRATEGIES;
    	if (mvcCommand.equals("/html/view_strategyshare"))
			jspPage=_JSP_COMMAND_LIST_SHARE_STRATEGIES;
    	if (mvcCommand.equals("/html/add_edit_market"))
			jspPage=_JSP_COMMAND_EDIT_MARKET;
    	if (mvcCommand.equals("/html/view_market"))
			jspPage=_JSP_COMMAND_LIST_MARKETS;
    	if (mvcCommand.equals("/html/backtesting_view"))
			jspPage=_JSP_COMMAND_LIST_BACKTESTING;
    	return jspPage; 
	}

    
	@SuppressWarnings("unchecked")
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		// TODO Auto-generated method stub
		themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String redirect = ParamUtil.getString(renderRequest, "redirect");
		HttpServletRequest _hR = PortalUtil.getHttpServletRequest(renderRequest);
	    String _mvcCommand = "";
	    List<Market> _lMarket = null;
	    Market Market = null;	 

	   
	   
        List<Strategy> _lStrategies = null;
        List<BackTesting> lBackTesting = null;
        Strategy Strategy = null;
        StrategyImpl _strategyImpl = null;
        Share share = null;
        JSONObject  jsonStrategyShareParams = null;
        JSONObject  jsonFutureParams = null;
        
        /* Lastrealtime */
        Realtime realtime  = null; 
        
		
	    try {
	        ServiceContext serviceContext = ServiceContextFactory.getInstance(Share.class.getName(), renderRequest);
	        long shareId = ParamUtil.getLong(renderRequest, "shareId");
	        /* VIENE DEL ACTION ADDSHARE */
	        long shareAddedId = ParamUtil.getLong(renderRequest, "shareAddedId",-1);
	        long strategyId = ParamUtil.getLong(renderRequest, "strategyId");
	        long marketId = ParamUtil.getLong(renderRequest, "marketId");
	        
			String strategyselected = ParamUtil.getString(renderRequest, "strategyselected", "SELECTED");
			
			
			
			
	        if (shareAddedId!=-1)
	        	shareId = shareAddedId;
	        
	    	share = _shareLocalService.fetchShare(shareId);
	    	
	    	/* STOPPROFIT, STOPLOST, STOPTRAILING ENABLE?  */
	    	Strategy _IBSTRATEGY_STOPLOST = _strategyLocalService.getCompanyGroupClassName(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),IBStrategyStopLost.class.getName());
	    	Strategy _IBSTRATEGY_TRAILINGSTOPLOST = _strategyLocalService.getCompanyGroupClassName(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),IBStrategyTrailingStopLost.class.getName());
	    	Strategy _IBSTRATEGY_STOPROFIT = _strategyLocalService.getCompanyGroupClassName(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),IBStrategyStopProfit.class.getName());
	    	
	    	StrategyShare strategyshare_stoplost = null;
	    	StrategyShare strategyshare_stopprofit = null;
	    	StrategyShare strategyshare_traillingstoplost = null;
	    	
	    	if (_IBSTRATEGY_STOPLOST!=null && share!=null)
	    		 strategyshare_stoplost = _strategyshareLocalService.getByCommpanyShareStrategyId(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), share.getShareId(), _IBSTRATEGY_STOPLOST.getStrategyID());
	    	if (_IBSTRATEGY_STOPROFIT!=null && share!=null)
	    		strategyshare_stopprofit = _strategyshareLocalService.getByCommpanyShareStrategyId(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), share.getShareId(), _IBSTRATEGY_STOPROFIT.getStrategyID());
	    	if (_IBSTRATEGY_TRAILINGSTOPLOST!=null && share!=null)
				strategyshare_traillingstoplost  = _strategyshareLocalService.getByCommpanyShareStrategyId(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), share.getShareId(), _IBSTRATEGY_TRAILINGSTOPLOST.getStrategyID());
	    	
	    	
	    	_mvcCommand = (String) serviceContext.getAttribute("mvcRenderCommandName");
	        String tab_selected= ParamUtil.getString(renderRequest, "tab", "share.details");
	        
	        if (_mvcCommand.equals("/html/add_edit_market"))	        		        
	        {
	        		 Market = _marketLocalService.fetchMarket(marketId);
	        		// share = _shareLocalService.fetchShare(shareId);
		        	 renderResponse.setTitle((Market != null) ? Market.getName() :  LanguageUtil.get(_hR, "market.addmarket"));
	        }
	        
	        if (_mvcCommand.equals("/html/add_edit_share"))	        		        
	        {
	        		 _lMarket = _marketLocalService.findByActiveCompanyGroup(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), Boolean.TRUE);
	        		// share = _shareLocalService.fetchShare(shareId);
		        	 renderResponse.setTitle((share != null) ? share.getName() :  LanguageUtil.get(_hR, "share.addshare"));
		        	 if (share!=null &&  share.getExpiry_expression()!=null && !share.getExpiry_expression().equals("")) 
		        	 {
		        		 jsonFutureParams = JSONFactoryUtil.createJSONObject(share.getExpiry_expression());
		        		 realtime = _realtimLocalService.findLastRealTime(share.getShareId(), share.getCompanyId(), share.getGroupId());
		        	 }
		        	 
	        }
			 /* ojo, podrian rellenarse en el action via request, lo detectamos 
			  *  llamando al mvcrendercommand no funciona desde el action 
			  * 
			  * */	 
	        if (_mvcCommand.equals("/html/view_strategyshare"))	  
	        {	
	        	
	        		 if (strategyselected.equals("SELECTED"))	    		
	        			 _lStrategies = _strategyshareLocalService.findByActiveStrategies(Boolean.TRUE, shareId, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
	        		 else
		        		 _lStrategies = _strategyLocalService.findStrategies(shareId, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
	        		 
	        		 PortletURL iteratorURL = renderResponse.createRenderURL();
	        		 SearchContainer<Strategy> searchContainer = null;
	        		 searchContainer  = new SearchContainer<Strategy>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, iteratorURL, null, StringPool.BLANK);
	        		 searchContainer.setEmptyResultsMessage("Estrategias  no encontradas");        
	        		 searchContainer.setResults(ListUtil.subList(_lStrategies, searchContainer.getStart(), searchContainer.getEnd()));
	        		 searchContainer.setTotal(_lStrategies.size());
	        		 renderRequest.setAttribute("searchStrategy" , searchContainer); 
	        		 renderRequest.setAttribute("iteratorURL" , iteratorURL);	        
	        }
	        /* EDITAMOS LOS DATOS DE LA ESTRATEGIA (EXPANDOS + DATOS DEL SHARE QUE TENGAMOS FIJOS PARA GENERAR UN JSON/XML) */
	        if (_mvcCommand.equals("/html/add_edit_strategyshare"))	  
	        {	        		
	        
	        	Strategy = _strategyLocalService.fetchStrategy(strategyId);
	        	 _strategyImpl= (StrategyImpl) Utilities.getContextClassLoader().loadClass(Strategy.getClassName()).newInstance();
				_strategyImpl.init(share.getCompanyId());   // INICIALIZAMOS
				/* GENERAMOS EL JSON CON LOS DATOS DEL ACTIVO  + LOS EXPANDOS  */					
				if (strategyId>0 && shareId>0)
				{
					StrategyShare _strategyshare = StrategyShareLocalServiceUtil.getByCommpanyShareStrategyId(serviceContext.getScopeGroupId(), serviceContext.getCompanyId(), shareId, strategyId);
					jsonStrategyShareParams = JSONFactoryUtil.createJSONObject(_strategyshare.getStrategyparamsoverride());													
					/* EXPANDOS PARA PINTAR */
				}
			//	ExpandoColumns = _strategyImpl.get
				
	        	
	        	
	        	
	        }
	        
	        if (_mvcCommand.equals("/html/backtesting_view"))	  
	        {	        		
	        
	        	 lBackTesting = BackTestingLocalServiceUtil.findByShareCompanyGroup(shareId, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
	        	 
	        	 
	        	 PortletURL iteratorURL = renderResponse.createRenderURL();
	        		
	       		 SearchContainer<BackTesting> searchContainer = null;
	       		 searchContainer  = new SearchContainer<BackTesting>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, iteratorURL, null, StringPool.BLANK);
	       		 searchContainer.setEmptyResultsMessage("Simulaciones  no encontradas");        
	       		 searchContainer.setResults(ListUtil.subList(lBackTesting, searchContainer.getStart(), searchContainer.getEnd()));
	       		 searchContainer.setTotal(lBackTesting.size());
	       		 renderRequest.setAttribute("searchBackTesting" , searchContainer); 
	       		 renderRequest.setAttribute("iteratorURL" , iteratorURL);	 
	        	
	        }
	        
	        /* EDITAMOS LOS DATOS DE LA ESTRATEGIA (EXPANDOS + DATOS DEL SHARE QUE TENGAMOS FIJOS PARA GENERAR UN JSON/XML) */
	        if (_mvcCommand.equals("/html/view_market"))	  
	        {	        		
	        

	       		 _lMarket = _marketLocalService.findByCompanyGroup(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
	       		 PortletURL iteratorURL = renderResponse.createRenderURL();
	
	       		 SearchContainer<Market> searchContainer = null;
	       		 searchContainer  = new SearchContainer<Market>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, iteratorURL, null, StringPool.BLANK);
	       		 searchContainer.setEmptyResultsMessage("Estrategias  no encontradas");        
	       		 searchContainer.setResults(ListUtil.subList(_lMarket, searchContainer.getStart(), searchContainer.getEnd()));
	       		 searchContainer.setTotal(_lMarket.size());
	       		 renderRequest.setAttribute("searchMarket" , searchContainer); 
	       		 renderRequest.setAttribute("iteratorURL" , iteratorURL);	 
			//	ExpandoColumns = _strategyImpl.get
				
	        	
	        	
	        	
	        }
	        
	        
	        String starthour  = ""; 
			String endhour =  ""; 
			
	        if (Validator.isNotNull(Market))
	        {
	    		starthour = Utilities.getIBFormattedUserLocalTime(themeDisplay.getUser(),Market.getStart_hour()); 
				endhour   = Utilities.getIBFormattedUserLocalTime(themeDisplay.getUser(),Market.getEnd_hour()); 
			
	        }
	        
	    	
	    	
	        /* se habilitan los parametros si existen las estrategias  y estan activas */
	        renderRequest.setAttribute("readonlyStopLost", (strategyshare_stoplost==null || (strategyshare_stoplost!=null && !strategyshare_stoplost.isActive()) ? "readonly"  : ""));
	        renderRequest.setAttribute("readonlyStopProfit",(strategyshare_stopprofit==null || (strategyshare_stopprofit!=null && !strategyshare_stopprofit.isActive()) ? "readonly"  : "")); 
	        renderRequest.setAttribute("readonlyTrailingStopLost",(strategyshare_traillingstoplost==null || (strategyshare_traillingstoplost!=null && !strategyshare_traillingstoplost.isActive()) ? "readonly"  : ""));
	        
	        renderRequest.setAttribute("share", share);
	        renderRequest.setAttribute("strategy", Strategy);
	        renderRequest.setAttribute("_lMarket", _lMarket);
	        renderRequest.setAttribute("market", Market);
	        
	        /* HORAS ADAPTADAS DE UTC A LOCAL DE USUARIO */
	        renderRequest.setAttribute("marketStart", starthour);
	        renderRequest.setAttribute("marketEnd", endhour);
	       
	        renderRequest.setAttribute("implemented_strategy", _strategyImpl);
	        renderRequest.setAttribute("tab_selected", tab_selected);	        
	        renderRequest.setAttribute("jsonStrategyShareParams", jsonStrategyShareParams);
	        renderRequest.setAttribute("jsonFutureParams", jsonFutureParams);	        
	        renderRequest.setAttribute("marketId", marketId);
	        renderRequest.setAttribute("realtime", realtime);
	        
	        
	        
	        
	        
	        

	    } catch (Exception e) {
	    	e.printStackTrace();
	        throw new PortletException(e);
	    }
	   	  
	    return getMVCPath(_mvcCommand);
	}
	

}
