package IBTrader.configuration.web.portlet;

import IBTrader.configuration.web.constants.IBTraderConfigurationWebPortletKeys;

import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.ConfigLocalService;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalService;
import com.ibtrader.data.service.StrategyLocalService;
import com.ibtrader.util.Utilities;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

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

    private ConfigLocalService _configLocalService; 

    private static String  _JSP_COMMAND_LIST_ = "/html/view.jsp";
    
	@Reference(unbind = "-")
    protected void setConfigService(ConfigLocalService configLocalService) {
		_configLocalService = configLocalService;
    }
    
    
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
		       
		    	String  _HOST = Utilities.getConfigurationValue(IBTraderConstants.keyTWS_HOST, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());	 
		    	String  _PORT = Utilities.getConfigurationValue(IBTraderConstants.keyTWS_PORT, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	
		    	String  _USER_TWS = Utilities.getConfigurationValue(IBTraderConstants.keyUSER_TWS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	String  _PWD_TWS = Utilities.getConfigurationValue(IBTraderConstants.keyUSER_PWD, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	String  _DESTOP_NOTIFICATION= Utilities.getConfigurationValue(IBTraderConstants.keyENABLE_DESKTOP_NOTIFICATIONS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	String  _MAIL_NOTIFICATION = Utilities.getConfigurationValue(IBTraderConstants.keyENABLE_MAIL_NOTIFICATIONS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	
		    	String  _SIMULATION_MODE = Utilities.getConfigurationValue(IBTraderConstants.keySIMULATION_MODE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	String  _PATH_TO_CONFIGURATION_FILE = Utilities.getConfigurationValue(IBTraderConstants.keyPATH_TO_CONFIGURATION_FILE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());

		        renderRequest.setAttribute("_USER_TWS", _USER_TWS);
		        renderRequest.setAttribute("_PWD_TWS", _PWD_TWS);
		        renderRequest.setAttribute("_DESTOP_NOTIFICATION", _DESTOP_NOTIFICATION);
		        renderRequest.setAttribute("_MAIL_NOTIFICATION", _MAIL_NOTIFICATION);
		        renderRequest.setAttribute("_HOST", _HOST);
		        renderRequest.setAttribute("_PORT", _PORT);	  
		        renderRequest.setAttribute("_SIMULATION_MODE", _SIMULATION_MODE);
		        renderRequest.setAttribute("_PATH_TO_CONFIGURATION_FILE", _PATH_TO_CONFIGURATION_FILE);

		        	       	        

		    } catch (Exception e) {
		    	e.printStackTrace();
		        //throw new PortletException(e);
		    }
	    		   	  
			super.doView(renderRequest, renderResponse);
		}


	/* HAY QUE AVISAR AL USUARIO QUE CAMBIANDO LOS DATOS, HAY QUE REINICIAR LA TWS, INFORMANDO DE SI DISPONE DE OPERACIONES ABIERTAS */
	/* 1. SI ESTA ARRANCADA, HAY QUE PARARLA EN CASO DE CAMBIOS DE USUARIO */	
	public void addeditConfiguration(ActionRequest actionRequest, ActionResponse actionResponse)	throws IOException, PortletException {
		// TODO Auto-generated method stub
		//super.processAction(actionRequest, actionResponse);
		/* 
		  * ip
			port
			ibcontrollerpath
			user
			password
			desktop_notifications
			email_notifications
			simulation_mode
		 */
		String ip = ParamUtil.get(actionRequest, "ip", "");
		long  port = ParamUtil.get(actionRequest, "port", -1);
		String ibcontrollerpath = ParamUtil.get(actionRequest, "ibcontrollerpath", "");
		String user = ParamUtil.get(actionRequest, "user", "");
		String password = ParamUtil.get(actionRequest, "password", "");
		boolean  desktop_notifications = ParamUtil.get(actionRequest, "desktop_notifications", Boolean.FALSE);
		boolean email_notifications = ParamUtil.get(actionRequest, "email_notifications", Boolean.FALSE);
		boolean simulation_mode = ParamUtil.get(actionRequest, "simulation_mode", Boolean.FALSE);
		
		boolean bOK = Boolean.TRUE;
		if (ip.equals("") || port==-1 || ibcontrollerpath.equals("") || user.equals("") || password.equals(""))
		{
			SessionErrors.add(actionRequest, "configuration.error.formatparameters");
			bOK = false;
		}
		if (!Validator.isIPAddress(ip))
		{
			SessionErrors.add(actionRequest, "configuration.user.ipinvalid");
			bOK = false;
		}
		if (port>65536 || port<1)
		{
			SessionErrors.add(actionRequest, "configuration.user.portinvalid");
			bOK = false;
		}
		if (!Validator.isContent(user))
		{
			SessionErrors.add(actionRequest, "configuration.user.userinvalid");
			bOK = false;
		}
		if (!Validator.isPassword(password))
		{
			SessionErrors.add(actionRequest, "configuration.user.passwordinvalid");
			bOK = false;
		}
		/* existe el fichero y tiene permisos */
		
		/* CONNECTED ???  */
		if (!Validator.isPassword(password))
		{
			SessionErrors.add(actionRequest, "configuration.user.passwordinvalid");
			bOK = false;
		}
		if (bOK)
		{
			/* IP */
			Config _config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyTWS_HOST, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				_config.setValue(ip);
				_configLocalService.updateConfig(_config);
				
			}
			/* HOST */
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyTWS_PORT, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				_config.setValue(String.valueOf(port));
				_configLocalService.updateConfig(_config);
				
			}
			/* USER */
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyUSER_TWS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				_config.setValue(String.valueOf(user));
				_configLocalService.updateConfig(_config);
				
			}
			/* PASSWORD */ 
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyUSER_PWD, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				_config.setValue(String.valueOf(password));
				_configLocalService.updateConfig(_config);
				
			}
			/* ibcontrollerpath */ 
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyPATH_TO_CONFIGURATION_FILE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				_config.setValue(String.valueOf(ibcontrollerpath));
				_configLocalService.updateConfig(_config);
				
			}
			/* keyENABLE_DESKTOP_NOTIFICATIONS */
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyENABLE_MAIL_NOTIFICATIONS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				_config.setValue(String.valueOf(email_notifications));
				_configLocalService.updateConfig(_config);
				
			}
			/* keyENABLE_DESKTOP_NOTIFICATIONS */
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyENABLE_DESKTOP_NOTIFICATIONS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				_config.setValue(String.valueOf(desktop_notifications));
				_configLocalService.updateConfig(_config);
				
			}
			/* keySIMULATION_MODE */
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keySIMULATION_MODE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				_config.setValue(String.valueOf(simulation_mode));
				_configLocalService.updateConfig(_config);
				
			}
			SessionMessages.add(actionRequest, "configuration.success");
		} // end ok
		
		
	}
}


