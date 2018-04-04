package IBTrader.positionlist.web.portlet;

import IBTrader.positionlist.web.constants.IBTraderPositionlistWebPortletKeys;

import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.PositionLocalService;
import com.ibtrader.data.service.RealtimeLocalService;
import com.ibtrader.data.service.ShareLocalService;
import com.ibtrader.util.Utilities;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
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
		"com.liferay.portlet.display-category=category.sample",
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
	 
	
	

	 
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws IOException, PortletException {
		// TODO Auto-generated method stub
	
		 
	themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY); 
		
	 String cmd = ParamUtil.getString(resourceRequest,"cmd");
	 
	 Date DateINI = Utilities.getDate(themeDisplay.getUser());
	 Date DateEND = Utilities.getDate(themeDisplay.getUser());;
	 
	 /* OPERACIONES DEL DIA */
	 DateINI.setHours(0);
	 DateINI.setMinutes(0);
	 DateINI.setSeconds(0);
	 
	 DateEND.setHours(23);
	 DateEND.setMinutes(59);
	 DateEND.setSeconds(59);
	 
	 
	 List<Position> _lPosition  = _positionLocalService.findByCompanyGroupDate(themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId(), DateINI,DateEND );
     JSONArray posListJson = JSONFactoryUtil.createJSONArray();     
     for (Position ibposition : _lPosition)
     { 
        JSONObject ibposJSON = JSONFactoryUtil.createJSONObject();
        ibposJSON.put("date_in", Utilities.getWebFormattedDate(ibposition.getDate_in()));
        ibposJSON.put("date_out", (ibposition.getDate_out()!=null ? Utilities.getWebFormattedDate(ibposition.getDate_out()) : ""));        
        ibposJSON.put("type", ibposition.getType());
        ibposJSON.put("sharenumber", ibposition.getShare_number());
        ibposJSON.put("price_in", ibposition.getPrice_in());        
        ibposJSON.put("stop_profit", ibposition.getPercentualstopprofit_out());
        ibposJSON.put("stop_lost", ibposition.getPercentualstoplost_out());
        ibposJSON.put("positionid", ibposition.getPositionId());
        ibposJSON.put("state_in", ibposition.getState_in());
        ibposJSON.put("state", ibposition.getState());
        ibposJSON.put("state_out", (ibposition.getState_out()!=null ? ibposition.getState_out() : ""));    
        ibposJSON.put("share_id", ibposition.getShareId());
        
        
        
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
        if (ibposition.getPrice_out()>0)  // hay salida ya, sacamos el precio de salida, si no, el ultimo en tiempo real
        {
        	ibposJSON.put("price_out", ibposition.getPrice_out());            
        }
        else
        {
        	Realtime _Realtime = _realtimeLocalService.findLastCompanyShare(_sharePosition.getCompanyId(), _sharePosition.getShareId());
        	if (_Realtime!=null)
        		ibposJSON.put("price_out", _Realtime.getValue());      
        }
     
        posListJson.put(ibposJSON); 
    }
    
     JSONObject tableData = JSONFactoryUtil.createJSONObject();
     tableData.put("data", posListJson);
    // _log.info("tableData:"+tableData.toString());	
     
     resourceResponse.getWriter().print(tableData.toString()); 
	 

	}
	
	@Reference(unbind = "-")
    protected void setShareService(ShareLocalService shareLocalService) {
		_shareLocalService = shareLocalService;
    }

    
    private ShareLocalService _shareLocalService;
    private PositionLocalService _positionLocalService;
    private RealtimeLocalService _realtimeLocalService;
    
    

	@Reference(unbind = "-")
    protected void setPositionService(PositionLocalService positionLocalService) {
		_positionLocalService = positionLocalService;
    }

	@Reference(unbind = "-")
    protected void setRealtimeService(RealtimeLocalService realtimeLocalService) {
		_realtimeLocalService = realtimeLocalService;
    }

    
    
    

	
}