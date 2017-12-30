package com.trader.dashboard.portlet.portlet;

import com.trader.dashboard.portlet.constants.GITraderPortletKeys;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.ConfigWrapper;
import com.ibtrader.data.service.ConfigLocalServiceUtil;
import com.ibtrader.util.CronUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
		Config  _conf = null;
		_log.info("Verifying Configuration Table for All Organizations/Company" );
		List<Company> lCompanies = CompanyLocalServiceUtil.getCompanies();
		long guestGroupId =0;
		/* VALORES GENERALES DE LA COMPAÑIA */
		for (Company _Company : lCompanies )
		{
			try {
				guestGroupId = GroupLocalServiceUtil.getGroup(_Company.getCompanyId(), GroupConstants.GUEST).getGroupId();
			} catch (PortalException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();			
			}
			_conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_TRADING_STATUS, _Company.getCompanyId(), guestGroupId);
			int _TotalConfigs = (_conf!=null ? 1 : 0 );
			if (_TotalConfigs==0)
			{
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
				 _conf.setCompanyId(_Company.getCompanyId());
				 _conf.setGroupId(guestGroupId);
				 _conf.setConfig_key(IBTraderConstants.keyNUM_DAYS_PAST_REALTIME);
				 _conf.setValue(String.valueOf(IBTraderConstants.vNUM_DAYS_PAST_REALTIME));
				 _conf.setName(IBTraderConstants.keyNUM_DAYS_PAST_REALTIME);
				 _conf.setDescription(IBTraderConstants.keyNUM_DAYS_PAST_REALTIME);
				 _conf.setGlobaldefault(false);
				 ConfigLocalServiceUtil.updateConfig(_conf);
				 
				 
				 /* PREVENT CRON TRADE  CONCURRENCY */   
				 _conf = ConfigLocalServiceUtil.createConfig(CounterLocalServiceUtil.increment(Config.class.getName()));
				 _conf.setCompanyId(_Company.getCompanyId());
				 _conf.setGroupId(guestGroupId);
				 _conf.setConfig_key(IBTraderConstants.keyCRON_TRADING_STATUS);
				 _conf.setValue(String.valueOf(IBTraderConstants.vCRON_TRADING_STATUS));
				 _conf.setName(IBTraderConstants.keyCRON_TRADING_STATUS);
				 _conf.setDescription(IBTraderConstants.keyCRON_TRADING_STATUS);
				 _conf.setGlobaldefault(false);
				 ConfigLocalServiceUtil.updateConfig(_conf); 
				 
				 /* PREVENT CRON READ CONCURRENCY */   
				 _conf = ConfigLocalServiceUtil.createConfig(CounterLocalServiceUtil.increment(Config.class.getName()));
				 _conf.setCompanyId(_Company.getCompanyId());
				 _conf.setGroupId(guestGroupId);
				 _conf.setConfig_key(IBTraderConstants.keyCRON_READING_STATUS);
				 _conf.setValue(String.valueOf(IBTraderConstants.vCRON_READING_STATUS));
				 _conf.setName(IBTraderConstants.keyCRON_READING_STATUS);
				 _conf.setDescription(IBTraderConstants.keyCRON_READING_STATUS);
				 _conf.setGlobaldefault(false);
				 ConfigLocalServiceUtil.updateConfig(_conf);   
				
			}
			List<Organization> lOrganization = OrganizationLocalServiceUtil.getOrganizations(_Company.getCompanyId(), OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, 0, OrganizationLocalServiceUtil.getOrganizationsCount()+1);
			for (Organization _Organization : lOrganization )
			{
				/* BUSCAMOS UNA CLAVE PARA VER SI EXISTE PARA LA ORGANIZACION */
				ConfigLocalServiceUtil.addConfigurationValuesCompanyGroup(_Organization.getCompanyId(), _Organization.getGroupId());
				//addConfigurationValuesCompanyGroup
			}			
				
			_conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_READING_STATUS, _Company.getCompanyId(), guestGroupId);
			_conf.setValue(String.valueOf(IBTraderConstants.vCRON_READING_STATUS));
			ConfigLocalServiceUtil.updateConfig(_conf);
			_conf = ConfigLocalServiceUtil.findByKeyCompanyGroup(IBTraderConstants.keyCRON_TRADING_STATUS , _Company.getCompanyId(), guestGroupId);
			_conf.setValue(String.valueOf(IBTraderConstants.keyCRON_TRADING_STATUS));
			ConfigLocalServiceUtil.updateConfig(_conf);					 
		}//  end companies list 
		/* VALORES PARA CADA EMPRESA */
	}
	
	public  void  CronTrade(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortletException {
		// TODO Auto-generated method stub
		try {
			//CronUtil.StartTradingCron(new Message());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void  CronRead(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortletException {
		// TODO Auto-generated method stub
		try {
		//	CronUtil.StartReadingCron(new Message());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void  CronVerifyContract(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortletException {
		// TODO Auto-generated method stub
		try {
			CronUtil.StartVerifyContractsCron(new Message());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}