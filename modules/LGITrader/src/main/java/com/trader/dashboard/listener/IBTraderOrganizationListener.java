package com.trader.dashboard.listener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.ibtrader.data.service.ConfigLocalService;
import com.ibtrader.data.service.MarketLocalService;
import com.ibtrader.data.service.ShareLocalService;
import com.ibtrader.data.service.StrategyLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationModel;
import com.trader.dashboard.portlet.portlet.GITraderPortlet;

/* ADD DEFAULT VALUES FOR CONFIG ONCE ORGANIZATION PARENT IS CREATED */
@Component(
	    immediate = true,
	    service = ModelListener.class
	)
public class IBTraderOrganizationListener extends BaseModelListener<Group> {

	Log _log = LogFactoryUtil.getLog(IBTraderOrganizationListener.class);

	private ConfigLocalService  _configLocalService;
	@Reference(unbind = "-")
	protected void setConfigLocalService(ConfigLocalService configLocalService) {
		_configLocalService = configLocalService;
	}
	
	@Override
	public void onAfterCreate(Group model) throws ModelListenerException {
		// TODO Auto-generated method stub
		// solo de primer nivel 
		if (model.isOrganization() &  model.isRoot())
		{
			// añadimos parametros 
			_configLocalService.addConfigurationValuesCompanyGroup(model.getOrganizationId(), model.getGroupId());
			_log.info("Adding default values for Organization:" + model.getName());
			
		}
		super.onAfterCreate(model);
	}

	@Override
	public void onAfterRemove(Group model) throws ModelListenerException {
		// TODO Auto-generated method stub
		if (model.isOrganization() &  model.isRoot())
		{
			// añadimos parametros 
			_configLocalService.removeConfigurationValuesCompanyGroup(model.getOrganizationId(), model.getGroupId());
			_log.info("onAfterRemove default values for Organization:" + model.getName());
			
		}	
		super.onAfterRemove(model);
	}

	

}
