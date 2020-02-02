package IBTrader.simulationmode.web.portlet;

import IBTrader.simulationmode.web.constants.IBTraderSimulationmodeWebPortletKeys;

import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.BackTesting;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.service.ConfigLocalService;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalService;
import com.ibtrader.util.CronUtil;
import com.ibtrader.util.Utilities;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
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
		"javax.portlet.display-name=IBTrader-simulationmode-web Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + IBTraderSimulationmodeWebPortletKeys.IBTraderSimulationmodeWeb,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.add-process-action-success-action=false"

	},
	service = Portlet.class
)
public class IBTraderSimulationmodeWebPortlet extends MVCPortlet {

	private final  Log _log = LogFactoryUtil.getLog(IBTraderSimulationmodeWebPortlet.class);
	private ThemeDisplay themeDisplay = null;
	
	@SuppressWarnings("unused")
	private ConfigLocalService _configLocalService;
	

	 @Reference(unbind = "-")
	    protected void setRealtimeLocalService(ConfigLocalService configLocalService) {
		 _configLocalService = configLocalService;
	    }
	
	public void change_trading(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		ServiceContext serviceContext = null;
		try {
			serviceContext = ServiceContextFactory.getInstance(BackTesting.class.getName(), actionRequest);
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		long change_trading =  ParamUtil.getLong(actionRequest,"change_trading",1);	
	
		try 
		{
				Config trading_config = _configLocalService.findByKeyCompanyGroup(IBTraderConstants.keyENABLED_GLOBAL_TRADING, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
				if (Validator.isNotNull(trading_config))
				{
					trading_config.setValue(String.valueOf(change_trading==0 ? 1 : 0));
					_configLocalService.updateConfig(trading_config);
				}								
				else
					_log.debug("No se encuentra el valor de la clave " + IBTraderConstants.keyENABLED_GLOBAL_TRADING  + " para el grupo "  + themeDisplay.getScopeGroupId());
						
						
		}				
		catch (Exception e)
		{
			SessionErrors.add(actionRequest, "change_trading.error.missingparameters");
			_log.error(e.getMessage());
		}
				
		//actionResponse.setRenderParameter("redirect", redirect);
		SessionMessages.add(actionRequest, PortalUtil.getPortletId(actionRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);		

		
	}
	
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		 themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		boolean bSIMULATED_TRADING = Utilities.getSimulatedTrading(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		boolean IsDayTraderPattern = Utilities.IsDayTraderPattern( themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId());		
		boolean IsTradingEnabled = Utilities.getTradingEnabled(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		renderRequest.setAttribute("simulated", bSIMULATED_TRADING);
		renderRequest.setAttribute("IsDayTraderPattern", IsDayTraderPattern);
		renderRequest.setAttribute("IsTradingEnabled", IsTradingEnabled);
		super.render(renderRequest, renderResponse);
	}
	
	


}