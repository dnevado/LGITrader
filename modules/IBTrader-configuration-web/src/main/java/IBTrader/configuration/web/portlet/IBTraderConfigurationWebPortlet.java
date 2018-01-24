package IBTrader.configuration.web.portlet;

import IBTrader.configuration.web.constants.IBTraderConfigurationWebPortletKeys;

import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.MarketLocalService;
import com.ibtrader.util.Utilities;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author DAVIDNEVADO
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=ibtrader",
		"com.liferay.portlet.instanceable=false", 
		"javax.portlet.display-name=IBTrader-configuration-web Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/view.jsp",
		"javax.portlet.name=" + IBTraderConfigurationWebPortletKeys.IBTraderConfigurationWeb,
		"javax.portlet.resource-bundle=content.Language",
		 "mvc.command.name=/html/view",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class IBTraderConfigurationWebPortlet extends MVCPortlet {
	
	ThemeDisplay themeDisplay;
	private static final Log   _log = LogFactoryUtil.getLog(IBTraderConfigurationWebCommand.class);

    private MarketLocalService _marketLocalService; 

    private static String  _JSP_COMMAND_LIST_ = "/html/view.jsp";
    
    
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException 	{
	// TODO Auto-generated method stub


	// TODO Auto-generated method stub
			themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String redirect = ParamUtil.getString(renderRequest, "redirect");
			HttpServletRequest _hR = PortalUtil.getHttpServletRequest(renderRequest);
			String _mvcCommand = "";
		    _log.info("render..configuration");
		    try {
		        ServiceContext serviceContext = ServiceContextFactory.getInstance(Share.class.getName(), renderRequest);
		      
		      
		    	_mvcCommand = (String) serviceContext.getAttribute("mvcRenderCommandName");
		        String tab_selected= ParamUtil.getString(renderRequest, "tab", "share.details");
		       
		    	String  _HOST = Utilities.getConfigurationValue(IBTraderConstants.keyTWS_HOST, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());	 
		    	String  _PORT = Utilities.getConfigurationValue(IBTraderConstants.keyTWS_PORT, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	
		    	String  _USER_TWS = Utilities.getConfigurationValue(IBTraderConstants.keyUSER_TWS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	String  _PWD_TWS = Utilities.getConfigurationValue(IBTraderConstants.keyUSER_PWD, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	String  _DESTOP_NOTIFICATION= Utilities.getConfigurationValue(IBTraderConstants.keyTWS_PORT, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	String  _MAIL_NOTIFICATION = Utilities.getConfigurationValue(IBTraderConstants.keyTWS_PORT, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());

		        renderRequest.setAttribute("_USER_TWS", _USER_TWS);
		        renderRequest.setAttribute("_PWD_TWS", _PWD_TWS);
		        renderRequest.setAttribute("_DESTOP_NOTIFICATION", _DESTOP_NOTIFICATION);
		        renderRequest.setAttribute("_MAIL_NOTIFICATION", _MAIL_NOTIFICATION);
		        renderRequest.setAttribute("_HOST", _HOST);
		        renderRequest.setAttribute("_PORT", _PORT);	        
		        	       	        

		    } catch (Exception e) {
		    	e.printStackTrace();
		        //throw new PortletException(e);
		    }
	    		   	  
			super.doView(renderRequest, renderResponse);
		}
}


