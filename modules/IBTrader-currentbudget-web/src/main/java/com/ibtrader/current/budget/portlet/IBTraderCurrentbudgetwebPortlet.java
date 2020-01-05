package com.ibtrader.current.budget.portlet;



import com.ibtrader.current.budget.constants.*;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
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
		"javax.portlet.display-name=IBTrader-usercurrentbudget-web",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" +IBTraderCurretBudgetWebPortletKeys.IBTraderCurrentBudgetWeb,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
	

	
)
public class IBTraderCurrentbudgetwebPortlet extends MVCPortlet {
	
	private final  Log _log = LogFactoryUtil.getLog(IBTraderCurrentbudgetwebPortlet.class);
	private String _BUDGET_USER_EXPANDO = "maxuserbudget";
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		_log.info("Starting doView :" + _BUDGET_USER_EXPANDO );
		_log.info("doView:" + _BUDGET_USER_EXPANDO );
		
		ThemeDisplay themedisplay =  (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY); 
		if (!themedisplay.isSignedIn()) return;
		User logged = themedisplay.getUser();
		Long  userBudget  =  new Long(0);
		try
		{
			userBudget  = (Long) logged.getExpandoBridge().getAttribute(_BUDGET_USER_EXPANDO);	
		}
		catch (Exception e)
		{
			_log.debug("Expando:" + _BUDGET_USER_EXPANDO  + " not found");
		}
		renderRequest.setAttribute("userBudget", userBudget);
		
		super.doView(renderRequest, renderResponse);
	}

	
	@Override
	public void init() throws PortletException {
		// TODO Auto-generated method stub
		
		_log.info("Init..:");
		
		ExpandoTable expandoTable;
		try {
			long companyId = PortalUtil.getDefaultCompanyId();
			expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(companyId , User.class.getName());
			
			ExpandoColumn ExistsExpando = ExpandoColumnLocalServiceUtil.getColumn(expandoTable.getTableId(), _BUDGET_USER_EXPANDO);
				
			if (ExistsExpando==null)
			{
				ExpandoColumn paramColumn = ExpandoColumnLocalServiceUtil.createExpandoColumn(CounterLocalServiceUtil.increment(ExpandoColumn.class.getName()));
				paramColumn.setName(_BUDGET_USER_EXPANDO);
				paramColumn.setType(ExpandoColumnConstants.LONG);
				paramColumn.setTypeSettings("hidden=0\nindex-type=0\nvisible-with-update-permission=0");
				paramColumn.setCompanyId(companyId);					
				paramColumn.setTableId(expandoTable.getTableId());
				ExpandoColumnLocalServiceUtil.updateExpandoColumn(paramColumn);
			}						
		}
		catch (Exception e)
		{
			_log.error("Not able to create expando " + _BUDGET_USER_EXPANDO + ",error:" + e.getMessage());
		}
		
	}
	
}