package IBTrader.simulationmode.web.portlet;

import IBTrader.simulationmode.web.constants.IBTraderSimulationmodeWebPortletKeys;

import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.util.Utilities;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

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
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class IBTraderSimulationmodeWebPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
 

		boolean bSIMULATED_TRADING = Utilities.getSimulatedTrading(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		renderRequest.setAttribute("simulated", bSIMULATED_TRADING);
		super.render(renderRequest, renderResponse);
	}
}