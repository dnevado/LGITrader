package IBTrader.productnavigation.application.list;

import IBTrader.productnavigation.constants.IBTraderProductnavigationPanelCategoryKeys;
import IBTrader.productnavigation.constants.IBTraderProductnavigationPortletKeys;

import com.liferay.application.list.BaseJSPPanelApp;
import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author DAVIDNEVADO
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + IBTraderProductnavigationPanelCategoryKeys.CONTROL_PANEL_CATEGORY
	},
	service = PanelApp.class
)
public class IBTraderProductnavigationPanelApp extends BaseJSPPanelApp {

	@Override
	public String getPortletId() {
		return IBTraderProductnavigationPortletKeys.IBTraderProductnavigation;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + IBTraderProductnavigationPortletKeys.IBTraderProductnavigation + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

	@Override
	public String getJspPath() {
		// TODO Auto-generated method stub
		return "/view.jsp";
	}

}