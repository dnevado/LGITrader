package IBTrader.navigation.contextcontributor.context.contributor;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.LayoutPermission;
import com.liferay.portal.kernel.service.permission.LayoutPermissionUtil;
import com.liferay.portal.kernel.template.TemplateContextContributor;
import com.liferay.portal.kernel.theme.NavItem;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author DAVIDNEVADO
 */
@Component(
	immediate = true,
	property = {"type=" + TemplateContextContributor.TYPE_THEME},
	service = TemplateContextContributor.class
)
public class IBTraderNavigationContextcontributorTemplateContextContributor
	implements TemplateContextContributor {


	@Override
	public void prepare(Map<String, Object> contextObjects, HttpServletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		Group guestGroup = GroupLocalServiceUtil.fetchGroup(themeDisplay.getCompanyId(), "Guest");
		List<Layout> publicLayouts = LayoutLocalServiceUtil.getLayouts(themeDisplay.getScopeGroupId(), false);

		LayoutPermission layoutPermission = LayoutPermissionUtil.getLayoutPermission();


		List<Layout> publicLayoutsFiltered = new ArrayList<>();
		try {
			for (Layout layout : publicLayouts) {
				if (!layout.isHidden()
						&& layoutPermission.contains(themeDisplay.getPermissionChecker(), layout, ActionKeys.VIEW)
						&& layout.getParentPlid() == 0) {
					publicLayoutsFiltered.add(layout);
				}
			}
		} catch (PortalException e) {
			log.error(e.getMessage(), e);
		}

		
		List<NavItem> publictNavItems = NavItem.fromLayouts(request, publicLayoutsFiltered, contextObjects);
		if (log.isDebugEnabled()) {
			for (NavItem navItem : publictNavItems) {
				log.info(navItem.getLayoutId() + " " + navItem.getLayout().getPlid());
			}
		}
		contextObjects.put("publicNavItems", publictNavItems);

	}

	private static final Log log = LogFactoryUtil.getLog(IBTraderNavigationContextcontributorTemplateContextContributor.class);


}