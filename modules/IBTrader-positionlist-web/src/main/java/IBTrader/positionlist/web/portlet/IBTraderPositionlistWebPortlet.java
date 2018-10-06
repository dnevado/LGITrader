package IBTrader.positionlist.web.portlet;

import IBTrader.positionlist.web.constants.IBTraderPositionlistWebPortletKeys;

import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.service.PositionLocalService;
import com.ibtrader.data.service.RealtimeLocalService;

import com.ibtrader.data.service.ShareLocalService;
import com.ibtrader.data.service.StrategyLocalService;
import com.ibtrader.data.service.StrategyShareLocalService;
import com.ibtrader.strategy.IBStrategyCancelPosition;
import com.ibtrader.strategy.IBStrategyClosePosition;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;



import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author DAVIDNEVADO
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=ibtrader",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=IBTrader-positionlist-web Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + IBTraderPositionlistWebPortletKeys.IBTraderPositionlistWeb,
		"javax.portlet.resource-bundle=content.Language",
		"com.liferay.portlet.header-portlet-css=/js/datatables.min.css",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class IBTraderPositionlistWebPortlet extends MVCPortlet {

	ThemeDisplay themeDisplay;
	 private static final Log   _log = LogFactoryUtil.getLog(IBTraderPositionlistWebPortlet.class);
	 
	 @SuppressWarnings("deprecation")
	 @Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
			// TODO Auto-generated method stub
			
		
			super.doView(renderRequest, renderResponse);

			
		}
	 @SuppressWarnings("deprecation")
	 @Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
			// TODO Auto-generated method stub
			
			themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY); 
			
			
			
			long positionId  = ParamUtil.getLong(renderRequest, "positionId",-1); 
			Position position = _positionLocalService.fetchPosition(positionId);
			
			Realtime realtime = null;
			Share share = null;
			if (position!=null)
			{
				realtime = _realtimeLocalService.findLastRealTime(position.getShareId(), position.getCompanyId(), position.getGroupId());
				share = _shareLocalService.fetchShare(position.getShareId());
				_log.info("isOpen:" + position.IsOpen());
			}//List<Strategy> _lStrg = StrategyLocalServiceUtil.findByCompanyId(themeDisplay.getCompanyId()); 
						
			/* STOPPROFIT, STOPLOST, STOPTRAILING ENABLE?  */
	    	Strategy _IBSTRATEGY_CLOSEPOSITION = _strategyLocalService.getCompanyGroupClassName(themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId(), IBStrategyClosePosition.class.getName());
	    	StrategyShare strategyshare_closeposition = null;
	    	
	    	/* STOPPROFIT, STOPLOST, STOPTRAILING ENABLE?  */
	    	Strategy _IBSTRATEGY_CANCELPOSITION = _strategyLocalService.getCompanyGroupClassName(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),IBStrategyCancelPosition.class.getName());
	    	StrategyShare strategyshare_cancelposition = null;
	    	
	    		
		    if (_IBSTRATEGY_CLOSEPOSITION!=null && share!=null)
		    	strategyshare_closeposition = _strategyshareLocalService.getByCommpanyShareStrategyId(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), share.getShareId(), _IBSTRATEGY_CLOSEPOSITION.getStrategyID());
		    if (_IBSTRATEGY_CANCELPOSITION!=null && share!=null)
		    	strategyshare_cancelposition = _strategyshareLocalService.getByCommpanyShareStrategyId(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), share.getShareId(), _IBSTRATEGY_CANCELPOSITION.getStrategyID());
	    			
	    		
			
		    /* se habilitan los parametros si existen las estrategias  y estan activas */
	        renderRequest.setAttribute("EnabledStrategyClosePosition", (strategyshare_closeposition==null || (strategyshare_closeposition!=null && !strategyshare_closeposition.isActive()) ? false  : true));
	        renderRequest.setAttribute("EnabledStrategyCancelPosition", (strategyshare_cancelposition==null || (strategyshare_cancelposition!=null && !strategyshare_cancelposition.isActive()) ? false  : true));

			//List<Strategy> _lStrg = StrategyLocalServiceUtil.find
			renderRequest.setAttribute("position", position);
			renderRequest.setAttribute("realtime", realtime);
			renderRequest.setAttribute("share", share);
			
			
			
			
			super.render(renderRequest, renderResponse);  // make sure this is the last  line in your method


			
		}

	 public void editPosition(ActionRequest actionRequest, ActionResponse actionResponse)
		{
			UploadPortletRequest req = PortalUtil.getUploadPortletRequest(actionRequest);
			
			themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			ServiceContext serviceContext = null;
			try {
				serviceContext = ServiceContextFactory.getInstance(Position.class.getName(), actionRequest);
			} catch (PortalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		    String editor = ParamUtil.getString(actionRequest, "editor","");
		    long positionId = ParamUtil.getLong(actionRequest, "positionId",-1);
		    String redirect = ParamUtil.getString(actionRequest,"redirect","");
		    double percentual_stop_lost = ParamUtil.getLong(actionRequest, "percentual_stop_lost",-1);
		    double percentual_stop_profit = ParamUtil.getLong(actionRequest, "percentual_stop_profit",-1);
		    
		    

		    Position position = _positionLocalService.fetchPosition(positionId);	    
		    if (position!=null)
		    {
		    	if (percentual_stop_lost!=-1)		    	
		    			position.setPercentualstoplost_out(percentual_stop_lost);
		    	if (percentual_stop_profit!=-1)		    	
	    			position.setPercentualstopprofit_out(percentual_stop_profit);
		    	position.setDescription(editor);
		    	_positionLocalService.updatePosition(position);
		    	SessionMessages.add(actionRequest, "share.success");
		    }
		    else
		    	SessionErrors.add(actionRequest, "share.error.exist");
		    
			actionResponse.setRenderParameter("mvcPath", "/position_detail.jsp");
			actionResponse.setRenderParameter("positionId", String.valueOf(positionId));
			actionResponse.setRenderParameter("redirect", String.valueOf(redirect));		
		    
		}
	 
	 
	 /* CANCELA UNA OPERACION ENTRADA O SALIDA PENDIENTE DE EJECUTARSE  */
	 public void cancelPosition(ActionRequest actionRequest, ActionResponse actionResponse)
		{
			UploadPortletRequest req = PortalUtil.getUploadPortletRequest(actionRequest);
			
			themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			ServiceContext serviceContext = null;
			try {
				serviceContext = ServiceContextFactory.getInstance(Position.class.getName(), actionRequest);
			} catch (PortalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		    long positionId = ParamUtil.getLong(actionRequest, "positionId",-1);
		    String redirect = ParamUtil.getString(actionRequest,"redirect","");
		    Position position = _positionLocalService.fetchPosition(positionId);	    
		    if (position!=null)
		    {
		    	
		    	if (position.IsCancelable())
		    	{
		    		position.setPendingcancelled(1);
		    		_positionLocalService.updatePosition(position);
			    	SessionMessages.add(actionRequest, "share.success");
		    	}
		    	
		    }
		    else
		    	SessionErrors.add(actionRequest, "share.error.exist");
		    
			actionResponse.setRenderParameter("mvcPath", "/position_detail.jsp");
			actionResponse.setRenderParameter("positionId", String.valueOf(positionId));
			actionResponse.setRenderParameter("redirect", String.valueOf(redirect));		
		    
		}
	 
	 
	 
	 /* CIERRA UNA POSICION ABIERTA */
	 public void closePosition(ActionRequest actionRequest, ActionResponse actionResponse)
		{
			UploadPortletRequest req = PortalUtil.getUploadPortletRequest(actionRequest);
			
			themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			ServiceContext serviceContext = null;
			try {
				serviceContext = ServiceContextFactory.getInstance(Position.class.getName(), actionRequest);
			} catch (PortalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		    long positionId = ParamUtil.getLong(actionRequest, "positionId",-1);
		    String redirect = ParamUtil.getString(actionRequest,"redirect","");
		    		    Position position = _positionLocalService.fetchPosition(positionId);	    
		    if (position!=null)
		    {
		    	
		    	if (position.IsOpen() && position.IsCloseable())
		    	{
		    		position.setForceclose(Boolean.TRUE);
		    		_positionLocalService.updatePosition(position);
			    	SessionMessages.add(actionRequest, "share.success");
		    	}
		    	
		    }
		    else
		    	SessionErrors.add(actionRequest, "share.error.exist");
		    
			actionResponse.setRenderParameter("mvcPath", "/position_detail.jsp");
			actionResponse.setRenderParameter("positionId", String.valueOf(positionId));
			actionResponse.setRenderParameter("redirect", String.valueOf(redirect));		
		    
		}
	 
	 
	 private void getPositionList(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws IOException, PortletException 
	 {
		 
		 themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);  
		 String redirect = ParamUtil.getString(resourceRequest, "redirect");

		 Date DateINI = Utilities.getDate(themeDisplay.getUser());
		 
	 
		 double price_out=0; // precio de salida o ultimo precio 
		 
		 
		 DateINI.setHours(0);
		 DateINI.setMinutes(0);
		 DateINI.setSeconds(0);
		 /* OPERACIONES DEL DIA */
		 /* DateEND.setHours(23);
		 DateEND.setMinutes(59);
		 DateEND.setSeconds(59);
		*/
		 
		String position_mode = Utilities.getPositionModeType(null, themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId()); 

		 
		 List<Position> _lPosition  = _positionLocalService.findIntradiaByCompanyGroupDate(themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId(), DateINI, position_mode );
	     JSONArray posListJson = JSONFactoryUtil.createJSONArray();     
	     for (Position ibposition : _lPosition)
	     { 
	    	 
	    	 price_out=0; 
	    	 
	        JSONObject ibposJSON = JSONFactoryUtil.createJSONObject();
	        ibposJSON.put("date_in", Utilities.getWebFormattedTime(ibposition.getDate_in(),themeDisplay.getUser()));
	        ibposJSON.put("date_out", (ibposition.getDate_out()!=null ? Utilities.getWebFormattedTime(ibposition.getDate_out(),themeDisplay.getUser()) : ""));        
	        ibposJSON.put("type", ibposition.getType());
	        ibposJSON.put("sharenumber", ibposition.getShare_number());
	        ibposJSON.put("price_in", ibposition.getPrice_real_in());        
	        ibposJSON.put("stop_profit", ibposition.getPercentualstopprofit_out());
	        ibposJSON.put("stop_lost", ibposition.getPercentualstoplost_out());
	        ibposJSON.put("positionid", ibposition.getPositionId());
	        ibposJSON.put("state_in", ibposition.getState_in());
	        ibposJSON.put("state", ibposition.getState());
	        ibposJSON.put("state_out", (ibposition.getState_out()!=null ? ibposition.getState_out() : ""));    
	        ibposJSON.put("share_id", ibposition.getShareId());
	        ibposJSON.put("type", ibposition.getType());
	        ibposJSON.put("number", ibposition.getShare_number());
	        
	        
	        /* ENLACE A MODIFICAR POSICION */
	        PortletURL modify = resourceResponse.createRenderURL();
	      //  modify.setWindowState(LiferayWindowState.POP_UP);
	        modify.setParameter("mvcPath", "/position_detail.jsp");
	        modify.setParameter("positionId", String.valueOf(ibposition.getPositionId()));
	        modify.setParameter("redirect", String.valueOf(redirect));
	        
	        ibposJSON.put("modify_link", "<button  class=\"btn btn-lg btn-primary\"  onclick=\"callProcessAction('" + modify + "')\">" + LanguageUtil.get(themeDisplay.getLocale(),"Editar") + "</button>");
	        
	        
	        
	      /*   if (ibposition.IsOpen())
	        {
	        	 ENLACE A CANCELAR POSITION ABIERTA  
	            PortletURL cancel = resourceResponse.createActionURL();                  
	            cancel.setParameter("positionId", String.valueOf(ibposition.getPositionId()));
	            cancel.setParameter("javax.portlet.action", "cancelOpenPosition");
	        }
	        
	        ibposJSON.put("modify_link", "<button  class=\"btn btn-lg btn-primary btn-default\"  href=\"" + modify + "\">" + LanguageUtil.get(themeDisplay.getLocale(),"position.modify") + "</button>");
	        */
//	        ibposJSON.put("exitposition_link", ibposition.getShare_number());
	        
	        /* DATOS DEL SHARE */ 
	        Share _sharePosition = _shareLocalService.fetchShare(ibposition.getShareId());
	        if (_sharePosition!=null)
	        {
	        	ibposJSON.put("multiplier", _sharePosition.getMultiplier());
	            ibposJSON.put("tick_futures", _sharePosition.getTick_futures());
	            ibposJSON.put("symbol", _sharePosition.getSymbol());
	            ibposJSON.put("market_id", _sharePosition.getMarketId());
	            ibposJSON.put("share_name", _sharePosition.getName());
	        }
	        /* DATOS DEL REALTIME */ 
	      //  Share _shareRealTime = RealtimeLocalServiceUtil.get
	        if (ibposition.getPrice_real_out()>0)  // hay salida ya, sacamos el precio de salida, si no, el ultimo en tiempo real
	        {
	        	ibposJSON.put("price_out", ibposition.getPrice_real_out());    
	        	price_out = ibposition.getPrice_real_out();
	        }
	        else
	        {
	        	Realtime _Realtime = _realtimeLocalService.findLastRealTime(_sharePosition.getShareId(), _sharePosition.getCompanyId(), _sharePosition.getGroupId()); 
	        		//	_realtimeLocalService.findLastCompanyShare(_sharePosition.getCompanyId(), _sharePosition.getShareId(),_sharePosition.getGroupId());
	        	if (_Realtime!=null)
	        	{
	        		ibposJSON.put("price_out", _Realtime.getValue());
	        		price_out = _Realtime.getValue();
	        	}
	        }
	        /* PROFIT O PERDIDA */
	        double profit =   price_out - ibposition.getPrice_real_in();
	        double percentual_profit=0;
	        
	        if (ibposition.getType().equals(PositionStates.statusTWSFire.SELL.toString())) // operacion de venta  normal..??
	        	profit =   ibposition.getPrice_real_in() - price_out;
	        
	        profit  =  Utilities.RoundPrice(profit);
	        
	        percentual_profit = (profit /  ibposition.getPrice_real_in() * 100);
	        percentual_profit = Utilities.RoundPrice(percentual_profit);
	        if (profit>0)     
	        	ibposJSON.put("roi","<span class=\"badge badge-pill badge-success\">+" + percentual_profit + "</span>");
	        else
	        	ibposJSON.put("roi","<span class=\"badge badge-pill badge-danger\">" + percentual_profit + "</span>");        	
	     
	        posListJson.put(ibposJSON); 
	    }
	    
	     JSONObject tableData = JSONFactoryUtil.createJSONObject();
	     tableData.put("data", posListJson);
	    // _log.info("tableData:"+tableData.toString());	
	     
	     resourceResponse.getWriter().print(tableData.toString()); 
	 }
	 
	 
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws IOException, PortletException {
		// TODO Auto-generated method stub
	
	
	 UploadPortletRequest req = PortalUtil.getUploadPortletRequest(resourceRequest);
	
	 
	 themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);  
		
	 String cmd = ParamUtil.getString(resourceRequest,"cmd");
	 
	 /* se llaman a lista de resultados y a resultados agregados */
	 String PositionResults = ParamUtil.getString(resourceRequest,"positionResult", StringPool.BLANK);
	 String PositionList = ParamUtil.getString(resourceRequest,"positionList", StringPool.BLANK);
	 
	 
	 if (!PositionList.equals(""))
		 getPositionList(resourceRequest, resourceResponse);

	 if (!PositionResults.equals(""))
		 getPositionResults(resourceRequest, resourceResponse);
	 
	 

	}
	
	private void getPositionResults(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws IOException, PortletException {
		
		
		 themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);  
		 String redirect = ParamUtil.getString(resourceRequest, "redirect");

		 Date DateINI = Utilities.getDate(themeDisplay.getUser());
		 
		 
		 DateINI.setHours(0);
		 DateINI.setMinutes(0);
		 DateINI.setSeconds(0);
		
		 String position_mode = Utilities.getPositionModeType(null, themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId());
		 
		 JSONArray results = _positionLocalService.findPositionOpenResults(DateINI, themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), position_mode);
		 JSONObject tableData = JSONFactoryUtil.createJSONObject();
	     JSONArray posListJson = JSONFactoryUtil.createJSONArray();     
	     if (results!=null)
	    	 tableData.put("dataResults", results);
	    // _log.info("tableData:"+tableData.toString());	
	     
	     resourceResponse.getWriter().print(tableData.toString()); 
	}
	
	

	
	@Reference(unbind = "-")
    protected void setStrategyService(StrategyLocalService strategyLocalService) {
		_strategyLocalService= strategyLocalService;
    }
	
	@Reference(unbind = "-")
    protected void setShareService(ShareLocalService shareLocalService) {
		_shareLocalService = shareLocalService;
    }

	private StrategyLocalService _strategyLocalService;

	private StrategyShareLocalService _strategyshareLocalService;
    private ShareLocalService _shareLocalService;
    private PositionLocalService _positionLocalService;
    private RealtimeLocalService _realtimeLocalService;
    

	@Reference(unbind = "-")
    protected void setStrategyShareService(StrategyShareLocalService strategyshareLocalService) {
		_strategyshareLocalService= strategyshareLocalService;
    }

	@Reference(unbind = "-")
    protected void setPositionService(PositionLocalService positionLocalService) {
		_positionLocalService = positionLocalService;
    }

	@Reference(unbind = "-")
    protected void setRealtimeService(RealtimeLocalService realtimeLocalService) {
		_realtimeLocalService = realtimeLocalService;
    }

    
    
    

	
}