package IBTrader.configuration.web.portlet;

import java.util.Enumeration;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.MarketLocalService;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalService;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.data.service.StrategyLocalService;
import com.ibtrader.data.service.StrategyShareLocalServiceUtil;
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
import com.liferay.portal.kernel.util.WebKeys;

import IBTrader.configuration.web.constants.*;

@Component(
	    immediate = true,
	    property = {
	       "javax.portlet.name=" + IBTraderConfigurationWebPortletKeys.IBTraderConfigurationWeb,	       	      
	       "mvc.command.name=/html/view",	       	       	       
	    },
	    service = MVCRenderCommand.class
	)
public class IBTraderConfigurationWebCommand implements MVCRenderCommand {

	ThemeDisplay themeDisplay;
	private static final Log   _log = LogFactoryUtil.getLog(IBTraderConfigurationWebCommand.class);

    private MarketLocalService _marketLocalService; 

    private static String  _JSP_COMMAND_LIST_ = "/html/view.jsp";
    
   
    @Reference(unbind = "-")
    protected void setMarketService(MarketLocalService marketLocalService) {
		_marketLocalService = marketLocalService;
    }
    public String getMVCPath(String mvcCommand)
	{
    		
    	return _JSP_COMMAND_LIST_; 
	}
    
  

    
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		return null;
	}


}
