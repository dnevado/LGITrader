package IBTrader.configuration.web.portlet;

import IBTrader.configuration.web.constants.IBTraderConfigurationWebPortletKeys;

import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.ConfigLocalService;
import com.ibtrader.util.PositionStates;
import com.ibtrader.util.Utilities;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

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
		"com.liferay.portlet.footer-portlet-javascript=/js/main.js",
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
    
    @Reference
    private MessageBus _messageBus;
    
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
		   // _log.info("render..configuration");
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
		    	String  _PATH_TO_EXECUTABLE_FILE = Utilities.getConfigurationValue(IBTraderConstants.keyPATH_TO_EXECUTABLE_FILE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	/* CUENTA SIMULADA O PAPER */
		    	String  _PAPER_USER_TWS = Utilities.getConfigurationValue(IBTraderConstants.keyPAPER_USER_TWS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	String  _PAPER_USER_PWD = Utilities.getConfigurationValue(IBTraderConstants.keyPAPER_USER_PWD, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId()); 
		    	String  _PAPER_TWS_HOST = Utilities.getConfigurationValue(IBTraderConstants.keyPAPER_TWS_HOST, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	String  _PAPER_TWS_PORT = Utilities.getConfigurationValue(IBTraderConstants.keyPAPER_TWS_PORT, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	String  _PAPER_PATH_TO_CONFIGURATION_FILE = Utilities.getConfigurationValue(IBTraderConstants.keyPAPER_PATH_TO_CONFIGURATION_FILE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	String  _PAPER_PATH_TO_EXECUTABLE_FILE = Utilities.getConfigurationValue(IBTraderConstants.keyPAPER_PATH_TO_EXECUTABLE_FILE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		    	/* CUENTA SIMULADA O PAPER */
		        renderRequest.setAttribute("isOmniadmin", themeDisplay.getPermissionChecker().isOmniadmin());
		        renderRequest.setAttribute("_USER_TWS", _USER_TWS);
		        renderRequest.setAttribute("_PWD_TWS", _PWD_TWS);
		        renderRequest.setAttribute("_DESTOP_NOTIFICATION", _DESTOP_NOTIFICATION);
		        renderRequest.setAttribute("_MAIL_NOTIFICATION", _MAIL_NOTIFICATION);
		        renderRequest.setAttribute("_HOST", _HOST);
		        renderRequest.setAttribute("_PORT", _PORT);	  
		        renderRequest.setAttribute("_SIMULATION_MODE", _SIMULATION_MODE);
		        renderRequest.setAttribute("_PATH_TO_CONFIGURATION_FILE", _PATH_TO_CONFIGURATION_FILE);
		        renderRequest.setAttribute("_PATH_TO_EXECUTABLE_FILE", _PATH_TO_EXECUTABLE_FILE);
		        
		        renderRequest.setAttribute("_PAPER_USER_TWS", _PAPER_USER_TWS);
		        renderRequest.setAttribute("_PAPER_USER_PWD", _PAPER_USER_PWD);
		        renderRequest.setAttribute("_PAPER_TWS_HOST", _PAPER_TWS_HOST);
		        renderRequest.setAttribute("_PAPER_TWS_PORT", _PAPER_TWS_PORT);
		        renderRequest.setAttribute("_PAPER_PATH_TO_CONFIGURATION_FILE", _PAPER_PATH_TO_CONFIGURATION_FILE);
		        renderRequest.setAttribute("_PAPER_PATH_TO_EXECUTABLE_FILE", _PAPER_PATH_TO_EXECUTABLE_FILE);
		        
		        renderRequest.setAttribute("REAL_VALUE", PositionStates.position_mode_type.REAL.toString());
		        renderRequest.setAttribute("SIMULATION_VALUE", PositionStates.position_mode_type.SIMULATED.toString());

		        	       	        

		    } catch (Exception e) {
		    	e.printStackTrace();
		        //throw new PortletException(e);
		    }
	    		   	  
			super.doView(renderRequest, renderResponse);
		}


	/* private void sendRebootMessage(ActionRequest actionRequest, ActionResponse actionResponse) {
			
        if (_log.isInfoEnabled()) {
            _log.info("Sending message to DE Echo service");
        }

        Message message = new Message();

        message.setDestinationName("RebootTWS");
        message.setPayload("Hello World!");
        message.setResponseDestinationName("MyEchoResponse");

        _messageBus.sendMessage(message.getDestinationName(), message);
    }
			*/	
			
			   
	
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
		
		themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		boolean isOmniAdmin = themeDisplay.getPermissionChecker().isOmniadmin();
		
		String ip = ParamUtil.get(actionRequest, "ip", "");
		long  port = ParamUtil.get(actionRequest, "port", -1);
		String ibcontrollerpath = ParamUtil.get(actionRequest, "ibcontrollerpath", "");
		String ibcontrollerexe = ParamUtil.get(actionRequest, "ibcontrollerexe", "");
		String user = ParamUtil.get(actionRequest, "user", "");
		String password = ParamUtil.get(actionRequest, "password", "");
		boolean  desktop_notifications = ParamUtil.get(actionRequest, "desktop_notifications", Boolean.FALSE);
		boolean email_notifications = ParamUtil.get(actionRequest, "email_notifications", Boolean.FALSE);
		boolean simulation_mode = ParamUtil.get(actionRequest, "simulation_mode", Boolean.FALSE);
		
		/* paper or simulation data 
		 * 
		 * 
		 * */
		
		String paper_ip = ParamUtil.get(actionRequest, "paper_ip", "");
		long   paper_port = ParamUtil.get(actionRequest, "paper_port", -1);
		String paper_ibcontrollerpath = ParamUtil.get(actionRequest, "paper_ibcontrollerpath", "");
		String paper_ibcontrollerexe = ParamUtil.get(actionRequest, "paper_ibcontrollerexe", "");
		String paper_user = ParamUtil.get(actionRequest, "paper_user", "");
		String paper_password = ParamUtil.get(actionRequest, "paper_password", "");
		
		
		/* PARA SABER SI TENEMOS QUE ACTUALIZAR EL FICHERO DE CONFIGURACION */
		boolean bLiveUserChanged = false;
		boolean bPaperUserChanged = false;
		
		
		boolean bOK = Boolean.TRUE;
		// separamos omniadmin de user administrador de su organizacion 
		if (isOmniAdmin &&  (ip.equals("") || port==-1 || ibcontrollerpath.equals("") || ibcontrollerexe.equals("") ||
				paper_ip.equals("") || paper_port==-1 || paper_ibcontrollerpath.equals("") || paper_ibcontrollerexe.equals("")))
		{
			SessionErrors.add(actionRequest, "configuration.error.formatparameters");
			bOK = false;
		}
		if (!isOmniAdmin &&  (user.equals("") || password.equals("") || user.equals("") || password.equals("")))
		{
			SessionErrors.add(actionRequest, "configuration.error.formatparameters");
			bOK = false;
		}		
		if (isOmniAdmin && (!Validator.isIPAddress(ip) || !Validator.isIPAddress(paper_ip)))
		{
			SessionErrors.add(actionRequest, "configuration.user.ipinvalid");
			bOK = false;
		}
		if (isOmniAdmin && ( port>65536 || port<1 || paper_port>65536 || paper_port<1))
		{
			SessionErrors.add(actionRequest, "configuration.user.portinvalid");
			bOK = false;
		}
		if (!Validator.isContent(user) || !Validator.isContent(paper_user))
		{
			SessionErrors.add(actionRequest, "configuration.user.userinvalid");
			bOK = false;
		}
		if (!Validator.isPassword(password) || !Validator.isPassword(paper_password))
		{
			SessionErrors.add(actionRequest, "configuration.user.passwordinvalid");
			bOK = false;
		}
		/* existe el fichero y tiene permisos 
		Path p = Paths.get(ibcontrollerpath);
		boolean exists = Files.exists(p);		
		*/
		File f = null;
		File fExe = null;
		File fpaper = null;
		File fPaperExe = null;
		
		if (!isOmniAdmin)
		{
			String  _PATH_TO_CONFIGURATION_FILE = Utilities.getConfigurationValue(IBTraderConstants.keyPATH_TO_CONFIGURATION_FILE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());	    	
	    	String  _PAPER_PATH_TO_CONFIGURATION_FILE = Utilities.getConfigurationValue(IBTraderConstants.keyPAPER_PATH_TO_CONFIGURATION_FILE, themeDisplay.getCompanyId(),  themeDisplay.getScopeGroupId());
			ibcontrollerpath = _PATH_TO_CONFIGURATION_FILE;
			paper_ibcontrollerpath = _PAPER_PATH_TO_CONFIGURATION_FILE;
		}
		f = new File(ibcontrollerpath);
		if(!f.exists() ||  f.isDirectory() || !f.canWrite()) { 
			SessionErrors.add(actionRequest, "configuration.user.invalidfilename");
			bOK = false;
		}	
		/* existe el fichero y tiene permisos */
		fpaper = new File(paper_ibcontrollerpath);
		if(!fpaper.exists() ||  fpaper.isDirectory() || !fpaper.canWrite()) { 
			SessionErrors.add(actionRequest, "configuration.user.invalidfilename");
			bOK = false;
		}				
		if (isOmniAdmin)
		{			
			/* existe el fichero y tiene permisos */
			fExe = new File(ibcontrollerexe);
			if(!fExe.exists() ||  fExe.isDirectory() || !fExe.canWrite()) { 
				SessionErrors.add(actionRequest, "configuration.user.invalidexefilename");
				bOK = false;
			}		
			
			/* existe el fichero y tiene permisos */
			fPaperExe = new File(paper_ibcontrollerexe);
			if(!fPaperExe.exists() ||  fPaperExe.isDirectory() || !fPaperExe.canWrite()) { 
				SessionErrors.add(actionRequest, "configuration.user.invalidexefilename");
				bOK = false;
			}	
		}
		if (bOK)
		{
			Config _config =  null;
			/* IP */
			if (isOmniAdmin)
			{
				_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyTWS_HOST, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
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
				/* ibcontrollerpath */ 
				_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyPATH_TO_CONFIGURATION_FILE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
				if (_config!=null)
				{
					_config.setValue(String.valueOf(ibcontrollerpath));
					_configLocalService.updateConfig(_config);
					
				}
				/* ibcontrollerexe */ 
				_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyPATH_TO_EXECUTABLE_FILE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
				if (_config!=null)
				{
					
					_config.setValue(String.valueOf(ibcontrollerexe));
					_configLocalService.updateConfig(_config);
					
				}
				/* PAPER DATA */
				/* IP */
				_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyPAPER_TWS_HOST, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
				if (_config!=null)
				{
					_config.setValue(paper_ip);
					_configLocalService.updateConfig(_config);
					
				}
				/* HOST */
				_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyPAPER_TWS_PORT, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
				if (_config!=null)
				{
					_config.setValue(String.valueOf(paper_port));
					_configLocalService.updateConfig(_config);
					
				}
				/* ibcontrollerpath */ 
				_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyPAPER_PATH_TO_CONFIGURATION_FILE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
				if (_config!=null)
				{
					_config.setValue(String.valueOf(paper_ibcontrollerpath));
					_configLocalService.updateConfig(_config);
					
				}
				/* ibcontrollerexe */ 
				_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyPAPER_PATH_TO_EXECUTABLE_FILE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
				if (_config!=null)
				{
					_config.setValue(String.valueOf(paper_ibcontrollerexe));
					_configLocalService.updateConfig(_config);
					
				}
			}
				
			/* USER */
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyUSER_TWS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				if (!_config.getValue().equals(user)) // cambio??? 
						bLiveUserChanged = true;
				_config.setValue(String.valueOf(user));
				_configLocalService.updateConfig(_config);
				
			}
			/* PASSWORD */ 
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyUSER_PWD, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				if (!_config.getValue().equals(password)) // cambio??? 
					bLiveUserChanged = true;
				_config.setValue(String.valueOf(password));
				_configLocalService.updateConfig(_config);
				
			}
			
			/* keyENABLE_DESKTOP_NOTIFICATIONS */
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyENABLE_MAIL_NOTIFICATIONS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				String _val = String.valueOf(email_notifications ? "1" : "0");
				_config.setValue(_val);
				_configLocalService.updateConfig(_config);
				
			}
			/* keyENABLE_DESKTOP_NOTIFICATIONS */
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyENABLE_DESKTOP_NOTIFICATIONS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				String _val = String.valueOf(desktop_notifications ? "1" : "0");
				_config.setValue(_val);
				_configLocalService.updateConfig(_config);
				
			}
			/* keySIMULATION_MODE */			
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keySIMULATION_MODE, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				String _val = String.valueOf(simulation_mode ? PositionStates.position_mode_type.SIMULATED.toString() : PositionStates.position_mode_type.REAL.toString());
				_config.setValue(_val);
				_configLocalService.updateConfig(_config);
				
			}
			
			/* MANTENEMOS DOS TWS ABIERTAS */
			//sendRebootMessage(actionRequest,actionResponse);
						
			/* USER */
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyPAPER_USER_TWS, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				if (!_config.getValue().equals(paper_user)) // cambio??? 
					bPaperUserChanged = true;
				_config.setValue(String.valueOf(paper_user));
				_configLocalService.updateConfig(_config);
				
			}
			/* PASSWORD */ 
			_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyPAPER_USER_PWD, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
			if (_config!=null)
			{
				if (!_config.getValue().equals(paper_password)) // cambio??? 
					bPaperUserChanged = true;
				_config.setValue(String.valueOf(paper_password));
				_configLocalService.updateConfig(_config);
				
			}
			
			
			/* ALMACENAMOS EL USUARIO Y PASSWORD, SI HAN CAMBIADO */
			Properties p;
			FileInputStream fIs;
			Properties prop;
			if (bPaperUserChanged)
			{
				fIs = new FileInputStream(fpaper);
				prop = new Properties();
				prop.load(fIs);
				prop.setProperty(IBTraderConstants.IBCONTROLLER_PASSWORD, paper_password);
				prop.setProperty(IBTraderConstants.IBCONTROLLER_USER, paper_user);
				prop.store(new FileOutputStream(fpaper), null);

			}
			/* ALMACENAMOS EL USUARIO Y PASSWORD, SI HAN CAMBIADO DE LA CUENTA LIVE */
			if (bLiveUserChanged)
			{
				fIs = new FileInputStream(f);
				prop = new Properties();
				prop.load(fIs);											
				prop.setProperty(IBTraderConstants.IBCONTROLLER_PASSWORD, password);
				prop.setProperty(IBTraderConstants.IBCONTROLLER_USER, user);
				prop.store(new FileOutputStream(f), null);

			}
			//f.
			
		//	IbLoginId
			
			SessionMessages.add(actionRequest, "configuration.success");
		} // end ok
		
		
	}
}


