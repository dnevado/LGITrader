package com.trader.dashboard.portlet.portlet;

import com.trader.dashboard.portlet.constants.GITraderPortletKeys;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.ConfigWrapper;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.runtime.dto.ComponentConfigurationDTO;

/**
 * @author DAVIDNEVADO
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=LGITrader Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GITraderPortletKeys.GITrader,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class GITraderPortlet extends MVCPortlet {

	Log _log = LogFactoryUtil.getLog(GITraderPortlet.class);
	@Override
	public void init() throws PortletException {
		// TODO Auto-generated method stub
		super.init();
		/* fill up with required data */
		
		long guestGroupId=0;
		Config  _conf = null;
		_log.info("Verifying Config Table for all companies" );
		
		List<Company> lCompanies = CompanyLocalServiceUtil.getCompanies();
		if (!lCompanies.isEmpty())
		{
			for (Company _Company : lCompanies )
			{
				
				try {
					guestGroupId = GroupLocalServiceUtil.getGroup(_Company.getCompanyId(), GroupConstants.GUEST).getGroupId();
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int _TotalConfigs = ConfigLocalServiceUtil.getConfigsCount();
				if (_TotalConfigs==0)
				{
					  /* TWS HOST */
					 _log.info("Adding missing Config Table Values .." );
					 
					  //_conf = new ConfigImpl();
					 
					 _conf = ConfigLocalServiceUtil.createConfig(CounterLocalServiceUtil.increment(Config.class.getName()));
					 _conf.setGroupId(guestGroupId);
					 _conf.setCompanyId(_Company.getCompanyId());
					 _conf.setConfig_key(IBTraderConstants.keyTWS_HOST);
					 _conf.setValue(IBTraderConstants.vTWS_HOST);
					 _conf.setName(IBTraderConstants.keyTWS_HOST);
					 _conf.setDescription(IBTraderConstants.keyTWS_HOST);
					 ConfigLocalServiceUtil.updateConfig(_conf);
					  
					 /* TWS ACOOUNT */
					 _conf = ConfigLocalServiceUtil.createConfig(CounterLocalServiceUtil.increment(Config.class.getName()));
					 _conf.setCompanyId(_Company.getCompanyId());
					 _conf.setGroupId(guestGroupId);
					 _conf.setConfig_key(IBTraderConstants.keyACCOUNT_IB_NAME);
					 _conf.setValue(IBTraderConstants.vACCOUNT_IB_NAME);
					 _conf.setName(IBTraderConstants.keyACCOUNT_IB_NAME);
					 _conf.setDescription(IBTraderConstants.keyACCOUNT_IB_NAME);
					 ConfigLocalServiceUtil.updateConfig(_conf);
					 
					 /* TWS PORT  */
					 _conf = ConfigLocalServiceUtil.createConfig(CounterLocalServiceUtil.increment(Config.class.getName()));
					 _conf.setCompanyId(_Company.getCompanyId());
					 _conf.setGroupId(guestGroupId);
					 _conf.setConfig_key(IBTraderConstants.keyTWS_PORT);
					 _conf.setValue(String.valueOf(IBTraderConstants.vTWS_PORT));
					 _conf.setName(IBTraderConstants.keyTWS_PORT);
					 _conf.setDescription(IBTraderConstants.keyTWS_PORT);
					 ConfigLocalServiceUtil.updateConfig(_conf);
					 
					 /* TWS tick future  */
					 _conf = ConfigLocalServiceUtil.createConfig(CounterLocalServiceUtil.increment(Config.class.getName()));
					 _conf.setCompanyId(_Company.getCompanyId());
					 _conf.setGroupId(guestGroupId);
					 _conf.setConfig_key(IBTraderConstants.keyDEFAULT_TICK_FUTURE);
					 _conf.setValue(String.valueOf(IBTraderConstants.vDEFAULT_TICK_FUTURE));
					 _conf.setName(IBTraderConstants.keyDEFAULT_TICK_FUTURE);
					 _conf.setDescription(IBTraderConstants.keyDEFAULT_TICK_FUTURE);
					 ConfigLocalServiceUtil.updateConfig(_conf);
					 
					 /* DIAS PARA MANTENER EL BACKUP   */
					 _conf = ConfigLocalServiceUtil.createConfig(CounterLocalServiceUtil.increment(Config.class.getName()));
					 _conf.setGroupId(guestGroupId);
					 _conf.setCompanyId(_Company.getCompanyId());
					 _conf.setConfig_key(IBTraderConstants.keyNUM_DAYS_PAST_REALTIME);
					 _conf.setValue(String.valueOf(IBTraderConstants.vNUM_DAYS_PAST_REALTIME));
					 _conf.setName(IBTraderConstants.keyNUM_DAYS_PAST_REALTIME);
					 _conf.setDescription(IBTraderConstants.keyNUM_DAYS_PAST_REALTIME);
					 ConfigLocalServiceUtil.updateConfig(_conf);
					
					
				}
				
				
			}
		}
		
		
			
		
		
	}
}