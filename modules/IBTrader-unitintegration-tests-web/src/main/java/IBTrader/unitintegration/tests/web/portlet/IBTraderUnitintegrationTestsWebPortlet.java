package IBTrader.unitintegration.tests.web.portlet;

import IBTrader.unitintegration.tests.web.constants.IBTraderUnitintegrationTestsWebPortletKeys;

import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;


import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Realtime;
import com.ibtrader.data.service.ConfigLocalService;
import com.ibtrader.data.service.MarketLocalService;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.RealtimeLocalServiceUtil;
import com.ibtrader.util.CronUtil;
import com.ibtrader.util.Utilities;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

/**
 * @author DAVIDNEVADO
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=IBTrader-unitintegration-tests-web Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + IBTraderUnitintegrationTestsWebPortletKeys.IBTraderUnitintegrationTestsWeb,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class IBTraderUnitintegrationTestsWebPortlet extends MVCPortlet {
	
	
	public  void fillDataStrategyMinMax(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortletException {
		
		// TODO Auto-generated method stub
				String starthour = ParamUtil.getString(actionRequest,"starthour","");
				long OffSet1FromOpenMarket = ParamUtil.getLong(actionRequest,"OffSet1FromOpenMarket",1);
				long OffSet2FromOpenMarket = ParamUtil.getLong(actionRequest,"OffSet2FromOpenMarket",30);
				long shareId = ParamUtil.getLong(actionRequest,"share",1902);
				double percentual_limit_buy = ParamUtil.getDouble(actionRequest,"percentual_limit_buy",2);
				percentual_limit_buy = percentual_limit_buy / 100;
				double percentual_gap = ParamUtil.getDouble(actionRequest,"percentual_gap",2);
				percentual_gap = percentual_gap / 100;
				double min = ParamUtil.getDouble(actionRequest,"min",176);
				double max = ParamUtil.getDouble(actionRequest,"max",176);
				double percentual_stop_lost = ParamUtil.getDouble(actionRequest,"percentual_stop_lost",2);
				percentual_stop_lost = percentual_stop_lost / 100;
				double percentual_stop_profit = ParamUtil.getDouble(actionRequest,"percentual_stop_profit",2);
				percentual_stop_profit = percentual_stop_profit / 100;
				double percentual_trailling_stop_lost = ParamUtil.getDouble(actionRequest,"percentual_trailling_stop_lost",2);
				percentual_trailling_stop_lost = percentual_trailling_stop_lost / 100;
				/* MEDIA MOBIL DE O PERIODOS DE 176+177.... ES 190 */
				
				ServiceContext serviceContext = null;
				try {
					serviceContext = ServiceContextFactory.getInstance(IBTraderUnitintegrationTestsWebPortlet.class.getName(), actionRequest);
				} catch (PortalException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				 
				Share share = null;
				Market oMarket = null;
				try {
					share = ShareLocalServiceUtil.getShare(shareId);
					oMarket = MarketLocalServiceUtil.getMarket(share.getMarketId());

				} catch (PortalException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				DateFormat formatter = new SimpleDateFormat("HH:mm");
				DateFormat formatter_market = new SimpleDateFormat("HHmm");

				Date time = null;
				Date opentime = null;

				try {
					time = formatter.parse(starthour);
					opentime = formatter_market.parse(oMarket.getStart_hour());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ThemeDisplay themeDisplay =      (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY); 		

				
				User _IBUser = null;
				try {
					_IBUser = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Calendar _calendario = Utilities.getNewCalendarWithHour(starthour.replace(":", ""));
			    
				/* BARRA INMEDIATAMENTE ANTERIOR */
				_calendario.set(Calendar.MINUTE, time.getMinutes());
				_calendario.set(Calendar.HOUR_OF_DAY, time.getHours());
				_calendario.set(Calendar.SECOND,0);
			
				
				
				/* BORRAMOS CON MARGENES LOS NUEVOS DATOS SIMULADOS PARA QUE NO HAYA COLISIONES 
				 * 
				 * BORRAMOS DESDE EL INICIO DE MERCADO CON EL TRAMO ELEGIDO DE MAXIMOS 
				*/
						
				Calendar cFROMMarket = Calendar.getInstance();
				Calendar cTOMarket = Calendar.getInstance();
				cFROMMarket.set(Calendar.MINUTE, opentime.getMinutes());
				cFROMMarket.set(Calendar.HOUR_OF_DAY, opentime.getHours());
				cFROMMarket.set(Calendar.SECOND,0);
				cFROMMarket.set(Calendar.MILLISECOND,0);
				cTOMarket.setTime(cFROMMarket.getTime());
				cFROMMarket.add(Calendar.MINUTE, (int) OffSet1FromOpenMarket);
				cTOMarket.add(Calendar.MINUTE, (int) OffSet2FromOpenMarket);
				cTOMarket.add(Calendar.SECOND, -1);

				
				/* COJO EL MAXIMO Y MINIMO ACTUAL PARA AJUSTARLO */
				Realtime MaxMin  = RealtimeLocalServiceUtil.findMinMaxRealTime(cFROMMarket.getTime(), _calendario.getTime(), shareId, serviceContext.getCompanyId(), serviceContext.getScopeGroupId());				
				
				RealtimeLocalServiceUtil.removeRealtimeFromToDate(cFROMMarket.getTime(), cTOMarket.getTime(), shareId, serviceContext.getCompanyId(), serviceContext.getScopeGroupId());

				if (MaxMin!=null && MaxMin.getMax_value()>0)
				{					
					/* SUMAMOS UN 3 % */
					min = MaxMin.getMin_value()* (1 - 0.03) ;
					max = MaxMin.getMax_value()* (1 + 0.03) ;;
				}
				
				
				/* MAIXMO */
				Realtime _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(cFROMMarket.getTime());
				_realtime.setModifiedDate(cFROMMarket.getTime());
				_realtime.setValue(max); // MIN 
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(shareId);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				/* MINIMO  */
				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(cTOMarket.getTime());
				_realtime.setModifiedDate(cTOMarket.getTime());
				_realtime.setValue(min); // MIN 
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(shareId);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				

				_calendario.add(Calendar.SECOND, 30);
				
				double percent = 0.02;
				double maxpluspercent_gap = max * (1 + percentual_gap);
				double maxpluspercent_limit = maxpluspercent_gap * (1 + percentual_limit_buy);
				
				double minpluspercent_gap = min * (1 + percentual_gap);
				double minpluspercent_limit = minpluspercent_gap * (1 + percentual_limit_buy);
				
				/* bRachedMax = ((oShareLastRTime.getValue() > MaxValueWithGap) &&  (oShareLastRTime.getValue() < MaxValueWithGapAndLimit)); */
				// sacamos un valor entre medias
				double maxentryValue = ThreadLocalRandom.current().nextDouble(maxpluspercent_gap, maxpluspercent_limit);
				double minentryValue = ThreadLocalRandom.current().nextDouble(minpluspercent_gap, minpluspercent_limit);

				/* PRIMERA SEÑAL LARGO +30 SECONDS   */

				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime());
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(maxentryValue); //   
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(shareId);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				/* SALIDA PROFIT +30 SECONDS   */
				_calendario.add(Calendar.SECOND, 30);

				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime());
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(maxentryValue  * (1 + percentual_stop_profit)); //  max * (1 + 0.02) + stop profit 
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(shareId);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				/* ENTRADA CORTO  +30 SECONDS   */
				_calendario.add(Calendar.SECOND, 30);
				double minpluspercent = max * (1 + percent);
				
				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime());
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(minentryValue); //  
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(shareId);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				/* SALIDA STOP LOST  +30 SECONDS   */
				_calendario.add(Calendar.SECOND, 30);

				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime());
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(minentryValue  * (1 + percentual_stop_lost)); //  max * (1 + 0.02) + stop profit 
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(shareId);
				
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				/* SLA SEÑAL ENTRADA A LARGO   +30 SECONDS   */
				_calendario.add(Calendar.SECOND, 30);

				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime());
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(maxentryValue); //   		
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(shareId);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				/* SUBE 5%  SEÑAL ENTRADA A LARGO   + 60  SECONDS, SALE POR TRAILING STOP NO SE INTRODUCE  */
				_calendario.add(Calendar.SECOND, 60);

				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime()); 
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(maxentryValue * (1+0.05)); //  
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(shareId);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
			
				/* 1. ACTIVAMOS EL  MODO SIMULACION EN LA ACCION PARA NO CARGAR REALTIME HASTA LA ULTIMA SEÑAL MAS 5 MNUTOS */
				_calendario.add(Calendar.MINUTE, 60);
				share.setSimulation_end_date(_calendario.getTime());
				ShareLocalServiceUtil.updateShare(share);
		
	}
	
	
	
	
	public  void fillDataStrategyMobileAverage(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortletException {
		
		// TODO Auto-generated method stub
				String starthour = ParamUtil.getString(actionRequest,"starthour","");
				long periods = ParamUtil.getLong(actionRequest,"periods",8);
				long timebars = ParamUtil.getLong(actionRequest,"timebars",5);
				long share = ParamUtil.getLong(actionRequest,"share",1902);
				double entrygap = ParamUtil.getDouble(actionRequest,"entrygap",75);
				entrygap = entrygap / 100;
				double aproxvalue = ParamUtil.getDouble(actionRequest,"aproxvalue",176);
				
				/* MEDIA MOBIL DE O PERIODOS DE 176+177.... ES 190 */
				
				ServiceContext serviceContext = null;
				try {
					serviceContext = ServiceContextFactory.getInstance(IBTraderUnitintegrationTestsWebPortlet.class.getName(), actionRequest);
				} catch (PortalException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DateFormat formatter = new SimpleDateFormat("HH:mm");
				Date time = null;
				try {
					time = formatter.parse(starthour);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ThemeDisplay themeDisplay =      (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY); 		

				
				User _IBUser = null;
				try {
					_IBUser = UserLocalServiceUtil.getUser(themeDisplay.getUserId());
				} catch (PortalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				Calendar _calendario = Utilities.getNewCalendarWithHour(starthour.replace(":", ""));
			    
				int _ModMinuteToEntry = (int) (_calendario.get(Calendar.MINUTE) % periods);
				
			//	_calendario.add(Calendar.DATE, (int) (_ModMinuteToEntry + timebars));
				
				
				/* PARA BORRAR */
				Date StartTime = new Date();
				Date EndTime = new Date();
				
				/* BARRA INMEDIATAMENTE ANTERIOR */
				_calendario.set(Calendar.MINUTE, time.getMinutes());
				_calendario.set(Calendar.HOUR_OF_DAY, time.getHours());
				_calendario.set(Calendar.SECOND,0);
				
				Calendar _cDesde = Calendar.getInstance();
				_cDesde.setTime(_calendario.getTime());
				
				Calendar _cHasta = Calendar.getInstance();
				_cHasta.setTime(_calendario.getTime());
				
				
				/* BORRAMOS CON MARGENES LOS NUEVOS DATOS SIMULADOS PARA QUE NO HAYA SOLISIONES */
				_cHasta.add(Calendar.MINUTE, (int) (timebars * 5));
				_cDesde.add(Calendar.MINUTE, -(int) (timebars * (periods + 2)));
				RealtimeLocalServiceUtil.removeRealtimeFromToDate(_cDesde.getTime(), _cHasta.getTime(), share, serviceContext.getCompanyId(), serviceContext.getScopeGroupId());

				
				/* RESTAMOS 5 MINUTOS 22:30 A 22:34:59*/ 
				/* CIERRE DE BARRA  */
				_calendario.add(Calendar.SECOND, -1);	
				_calendario.set(Calendar.MILLISECOND, 999);
				
				Realtime _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime());
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(600); // MIN 
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(share);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				
				/* CIERRE DE BARRA  DE SALIDA N+1  */
				_calendario.add(Calendar.MINUTE, (int) timebars);				
				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime());
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(100); // MIN 
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(share);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				
				/* MINIMO  	barra n+1  */
				_calendario.add(Calendar.SECOND, -1);
				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime());
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(90); // MIN 
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(share);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				_calendario.add(Calendar.MINUTE, -(int) timebars);

				/* MINIMO   */
				//_calendario.add(Calendar.SECOND, -1);
				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime());
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(150); // MIN 
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(share);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				
				/* RESTAMOS 5 MINUTOS 22:30 A 22:34:59*/ 
				/* PRINCIPIO DE BARRA  Y MAXIMO */
				_calendario.add(Calendar.SECOND, +3);
				_calendario.set(Calendar.MILLISECOND, 999);
				_calendario.add(Calendar.MINUTE, (int) -timebars);
				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime());
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(220); // MAX BARRA ANTERIOR 
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(share);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				/* PRINCIPIO DE BARRA  Y MAXIMO PERIODO N+1 SALIDA */
				_calendario.add(Calendar.MINUTE, (int) timebars);
				 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
				_realtime.setCreateDate(_calendario.getTime());
				_realtime.setModifiedDate(_calendario.getTime());
				_realtime.setValue(250); // MAX BARRA ANTERIOR 
				_realtime.setGroupId(serviceContext.getScopeGroupId());
				_realtime.setCompanyId(serviceContext.getCompanyId());
				_realtime.setShareId(share);
				RealtimeLocalServiceUtil.updateRealtime(_realtime);
				
				
				
				/* TANTAS BARRAS AL SEGUNDO :59:59:999*/
				_calendario.add(Calendar.MINUTE, -(int) timebars);
				_calendario.add(Calendar.SECOND, -2);
				_calendario.set(Calendar.MILLISECOND, 999);
				/* METEMOS LOS OCHOS PERIODOS MAS LA BARRA SIGUIENTE CON LA SEÑAL DE SALIDA */ 
				for (int j=0;j<periods;j++)
				{
					
					/* X BARRAS */	
					 _realtime = RealtimeLocalServiceUtil.createRealtime(CounterLocalServiceUtil.increment(Realtime.class.getName()));
					 _realtime.setCreateDate(_calendario.getTime());
					_realtime.setModifiedDate(_calendario.getTime());
					_realtime.setValue(166);
					_realtime.setGroupId(serviceContext.getScopeGroupId());
					_realtime.setCompanyId(serviceContext.getCompanyId());
					_realtime.setShareId(share);
					
					RealtimeLocalServiceUtil.updateRealtime(_realtime);
					_calendario.add(Calendar.MINUTE, (int) -timebars);
				}
				
				/* 1. ACTIVAMOS EL  MODO SIMULACION EN LA ACCION PARA NO CARGAR REALTIME HASTA LA ULTIMA SEÑAL MAS 5 MNUTOS */
				Share oShare = null;
				try {
					 oShare = ShareLocalServiceUtil.getShare(share);				

				} catch (PortalException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				_calendario.add(Calendar.MINUTE, 5);
				oShare.setSimulation_end_date(_calendario.getTime());
				ShareLocalServiceUtil.updateShare(oShare);
		
		
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		super.doView(renderRequest, renderResponse);
	
	}

	@Override
	public void init() throws PortletException {
		// TODO Auto-generated method stub
		super.init();
	}




	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		ThemeDisplay themeDisplay =      (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY); 		

		List<Share> lShares= ShareLocalServiceUtil.findCompanyGroup(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
		renderRequest.setAttribute("lShares", lShares);
		super.render(renderRequest, renderResponse);
	}
}