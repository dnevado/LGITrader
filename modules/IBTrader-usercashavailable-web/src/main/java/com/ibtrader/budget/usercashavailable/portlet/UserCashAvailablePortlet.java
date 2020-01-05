package com.ibtrader.budget.usercashavailable.portlet;

import com.ibtrader.budget.usercashavailable.constants.UserCashAvailablePortletKeys;
import com.ibtrader.data.service.PositionLocalService;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.Utilities;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=IBTrader-userbudget-web",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"com.liferay.portlet.header-portlet-javascript=/js/main.js",
		"javax.portlet.name=" + UserCashAvailablePortletKeys.USERCASHAVAILABLE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UserCashAvailablePortlet extends MVCPortlet {
	private final  Log _log = LogFactoryUtil.getLog(UserCashAvailablePortlet.class);
	
	
	private PositionLocalService _positionLocalService;
	
	@Reference(unbind = "-")
    protected void setStrategyShareService(PositionLocalService positionLocalService) {
		_positionLocalService= positionLocalService;
    }
	 	
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		
		_log.debug("Starting render :" + ConfigKeys._BUDGET_USER_EXPANDO );
		
		ThemeDisplay themedisplay =  (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY); 
		if (!themedisplay.isSignedIn()) return;
		User logged = themedisplay.getUser();
		Long  userBudget  =  new Long(0);
		double _percentage = 0;
		double positionsLiquid = 0;
		try
		{
			userBudget  = (Long) logged.getExpandoBridge().getAttribute(ConfigKeys._BUDGET_USER_EXPANDO);
			if (userBudget.longValue()>0)
			{
				_log.debug("Exists user budget:" + userBudget );
				 String position_mode = Utilities.getPositionModeType(null, themedisplay.getCompanyId(),themedisplay.getScopeGroupId());
				 positionsLiquid = _positionLocalService.findTotalLiquidPositionOpen( themedisplay.getScopeGroupId(), themedisplay.getCompanyId(), position_mode);
				 _percentage = positionsLiquid / userBudget * 100;
				 _percentage = Utilities.RoundPrice(_percentage);
			}
			
		}
		catch (Exception e)
		{
			_log.debug("Expando:" + ConfigKeys._BUDGET_USER_EXPANDO  + " not found");
		}
		
		
		renderRequest.setAttribute("userBudget", userBudget);
		renderRequest.setAttribute("positionsLiquid", positionsLiquid);
		renderRequest.setAttribute("progress", _percentage);
		
		super.render(renderRequest, renderResponse);
	}

	@Override
	public void init() throws PortletException {
		// TODO Auto-generated method stub
		

		_log.debug("Init searching for expandos creation");
		
		ExpandoTable expandoTable = null;
		try {
			
			
			long companyId = PortalUtil.getDefaultCompanyId();
			expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(companyId, User.class.getName());
			ExpandoColumn ExistsExpando = ExpandoColumnLocalServiceUtil.getColumn(expandoTable.getTableId(), ConfigKeys._BUDGET_USER_EXPANDO);
			ExpandoColumn paramColumn = null;
			if (ExistsExpando==null)
			{
				paramColumn = ExpandoColumnLocalServiceUtil.createExpandoColumn(CounterLocalServiceUtil.increment(ExpandoColumn.class.getName()));
				paramColumn.setName(ConfigKeys._BUDGET_USER_EXPANDO);
				paramColumn.setType(ExpandoColumnConstants.LONG);
				paramColumn.setTypeSettings("hidden=0\nindex-type=1\nvisible-with-update-permission=1");
				paramColumn.setCompanyId(companyId);					
				paramColumn.setTableId(expandoTable.getTableId());
				ExpandoColumnLocalServiceUtil.updateExpandoColumn(paramColumn);
			}
			
			_log.debug("Verifying expandos permissions");
			
			Role guestUserRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);
			Role userRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
            String[] actionIds = new String[] { ActionKeys.VIEW, ActionKeys.UPDATE};

			if (guestUserRole != null) {
                // define actions 
                // set the permission
                ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId, 
                  ExpandoColumn.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, 
                  String.valueOf(paramColumn.getColumnId()), guestUserRole.getRoleId(), actionIds);
			} 
			if (userRole != null) {
                // define actions 
                // set the permission
                ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId, 
                  ExpandoColumn.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, 
                  String.valueOf(paramColumn.getColumnId()), userRole.getRoleId(), actionIds);
			} 
			
			
		}
		catch (Exception e)
		{
			_log.debug("Not able to create expando " + ConfigKeys._BUDGET_USER_EXPANDO + ",error:" + e.getMessage());
		}
		
		super.init();
	}
}