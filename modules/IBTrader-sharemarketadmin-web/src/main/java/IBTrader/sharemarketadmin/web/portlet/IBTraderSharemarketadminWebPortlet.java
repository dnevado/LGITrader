package IBTrader.sharemarketadmin.web.portlet;

import IBTrader.sharemarketadmin.web.constants.IBTraderSharemarketadminWebPortletKeys;

import com.ib.client.Contract;
import com.ib.contracts.FutContract;
import com.ib.contracts.StkContract;
import com.ibtrader.constants.IBTraderConstants;
import com.ibtrader.data.model.Config;
import com.ibtrader.data.model.IBOrder;
import com.ibtrader.data.model.Market;
import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Share;
import com.ibtrader.data.model.ShareModel;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.model.StrategyShare;
import com.ibtrader.data.model.impl.StrategyImpl;
import com.ibtrader.data.service.IBOrderLocalServiceUtil;
import com.ibtrader.data.service.MarketLocalService;
import com.ibtrader.data.service.MarketLocalServiceUtil;
import com.ibtrader.data.service.PositionLocalServiceUtil;
import com.ibtrader.data.service.ShareLocalService;
import com.ibtrader.data.service.ShareLocalServiceUtil;
import com.ibtrader.data.service.StrategyLocalService;
import com.ibtrader.data.service.StrategyShareLocalService;
import com.ibtrader.data.service.StrategyShareLocalServiceUtil;
import com.ibtrader.interactive.TIMApiGITrader;
import com.ibtrader.util.ConfigKeys;
import com.ibtrader.util.Utilities;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
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
		"javax.portlet.display-name=IBTrader-sharemarketadmin-web Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/view.jsp",		
		"javax.portlet.name=" + IBTraderSharemarketadminWebPortletKeys.IBTraderSharemarketadminWeb,
        "com.liferay.portlet.footer-portlet-javascript=/js/main.js",
		"javax.portlet.resource-bundle=content.Language",
		 "mvc.command.name=/html/add_edit_share",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class IBTraderSharemarketadminWebPortlet extends MVCPortlet {
	ThemeDisplay themeDisplay;
	private static final Log   _log = LogFactoryUtil.getLog(IBTraderSharemarketadminWebPortlet.class);
    private ShareLocalService _shareLocalService;
    private StrategyShareLocalService _strategyshareLocalService;
    private StrategyLocalService _strategyLocalService;
    private MarketLocalService _marketLocalService;
    
    private Market market;
    
    Share share=null;
    StrategyShare strategyshare =null;
    
    @Reference(unbind = "-")
    protected void setStrategyService(StrategyLocalService strategyLocalService) {
    	_strategyLocalService = strategyLocalService;
    }
    
	@Reference(unbind = "-")
    protected void setShareService(ShareLocalService shareLocalService) {
		_shareLocalService = shareLocalService;
    }
	@Reference(unbind = "-")
    protected void setStrategyShareService(StrategyShareLocalService strategyshareLocalService) {
		_strategyshareLocalService = strategyshareLocalService;
    }
	@Reference(unbind = "-")
    protected void setMarketService(MarketLocalService marketLocalService) {
		_marketLocalService = marketLocalService;
    }

    

	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
	

     themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY); 		
		
	 List<Share> _lShares  = _shareLocalService.findCompanyGroup(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());
	
	 PortletURL iteratorURL = renderResponse.createRenderURL();

	 SearchContainer<Share> searchContainer = null;
	 searchContainer  = new SearchContainer<Share>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, iteratorURL, null, StringPool.BLANK);
	 searchContainer.setEmptyResultsMessage("Posiciones no encontradas");        
	 searchContainer.setResults(ListUtil.subList(_lShares, searchContainer.getStart(), searchContainer.getEnd()));
	 searchContainer.setTotal(_lShares.size());
	 renderRequest.setAttribute("searchShare" , searchContainer); 
	 renderRequest.setAttribute("iteratorURL" , iteratorURL);
			
	super.doView(renderRequest, renderResponse);
	}


	@Override
	public void init() throws PortletException {
		// TODO Auto-generated method stub
		super.init();
	}

	private boolean ValidateDataShare(ActionRequest actionRequest)
	{
	
		boolean validated = true;	
		
		// add / edit
		String mvcPath = actionRequest.getParameter("javax.portlet.action");
		Share editShare=null; 
		boolean bEditMode=mvcPath.equals("editShare");
		
		SimpleDateFormat sdfSHORT = new SimpleDateFormat();
    	sdfSHORT.applyPattern(Utilities._IBTRADER_FUTURE_LONG_DATE);
		String name = ParamUtil.getString(actionRequest,"name","");
		String symbol = ParamUtil.getString(actionRequest,"symbol","");
		String active = ParamUtil.getString(actionRequest,"active","");			
		long numbertopurchase =  ParamUtil.getLong(actionRequest,ConfigKeys._FIELD_NUMBER_TO_PURCHASE,-1);
		double percentual_limit_buy =  ParamUtil.getDouble(actionRequest,"percentual_limit_buy",0);
		double percentual_stop_lost =  ParamUtil.getDouble(actionRequest,ConfigKeys._FIELD_STOP_LOST,0);
		double percentual_stop_profit =  ParamUtil.getDouble(actionRequest,ConfigKeys._FIELD_STOP_PROFIT,0);
		double percentual_trailling_stop_lost =  ParamUtil.getDouble(actionRequest,"trailling_stop_lost",0);
		
		
		Date expiry_date = ParamUtil.getDate(actionRequest,"expiry_date",null, null);
		double tick_futures=  ParamUtil.getDouble(actionRequest,"tick_futures",0);
		long multiplier =  ParamUtil.getLong(actionRequest,"multiplier",0);
		String security_type = ParamUtil.getString(actionRequest,"security_type","");
		String primary_exchange = ParamUtil.getString(actionRequest,"primaryexchange","");
		String exchange = ParamUtil.getString(actionRequest,"exchange","");
		long marketId =  ParamUtil.getLong(actionRequest,"marketId",-1);
		long shareId =  ParamUtil.getLong(actionRequest,"shareId",-1);
		String[]  _expirationmonth = ParamUtil.getStringValues(actionRequest, "expirationmonth");
		
		String expirationweek =  ParamUtil.getString(actionRequest,"expirationweek","");
		String  expirationdayweek =  ParamUtil.getString(actionRequest,"expirationdayweek","");
		
		
		String  expirationmonth = String.join(",", _expirationmonth);
		boolean bNameOK = Validator.isContent(name) && name.length()<=75;
		boolean bSymbolOK = Validator.isAlphanumericName(symbol)  && symbol.length()<=75;
		boolean bExchangeOK = _shareLocalService.ExistsExchange(exchange);
		boolean bPrimaryExchangeOK = _shareLocalService.ExistsPrimaryExchange(primary_exchange);		
		boolean bSecurityOK = _shareLocalService.ExistsSecurityType(security_type);
		boolean bFutureOK = security_type.equals(ConfigKeys.SECURITY_TYPE_STOCK) || (security_type.equals(ConfigKeys.SECURITY_TYPE_FUTUROS) && !expirationmonth.equals("")
								&& !expirationweek.equals("") && !expirationdayweek.equals(""));
		
		boolean bNumberParamsOK = numbertopurchase>=1 && 
						percentual_limit_buy>=0 && percentual_limit_buy<=100  && 
								percentual_stop_lost>=0 && percentual_stop_lost<=100 && 
										percentual_stop_profit>=0 && percentual_stop_profit<=100 && 
												percentual_trailling_stop_lost>=0 && percentual_trailling_stop_lost<=100 &&												
														tick_futures>=0  && multiplier>=0;
						

		try 
		{
		
			
		if (name.equals("") || symbol.equals("") || numbertopurchase==-1 || security_type.equals("") || primary_exchange.equals("") || marketId==-1)
		{
			
			validated=false;
			SessionErrors.add(actionRequest, "share.error.missingparameters");
		}
		else
		{
			if (!bNameOK || !bSymbolOK || !bExchangeOK || !bPrimaryExchangeOK || !bSecurityOK || !bNumberParamsOK)
			{
				validated=false;			
				SessionErrors.add(actionRequest, "share.error.formatparameters");
			}
			else
				if (!bFutureOK)
				{
					validated=false;			
					SessionErrors.add(actionRequest, "share.error.futuresparameters");
				}
				else
				{
					/* shareid belongs to company? */				
					/* exists by name or by simbol */
					if (bEditMode)
					{
						editShare = _shareLocalService.fetchShare(shareId);
						
					}
					/* let update with no changes */
					Share ShareFoundByName = _shareLocalService.findByNameMarketCompanyGroup(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), name, marketId);
					boolean bShareFoundByName =   ShareFoundByName!=null && (editShare==null || (editShare!=null && !name.equals(editShare.getName())));
					Share ShareFoundBySymbol = _shareLocalService.findBySymbolCompanyGroup(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), symbol);
					boolean bShareFoundBySymbol =   ShareFoundBySymbol!=null &&  (editShare==null || editShare!=null && !symbol.equals(editShare.getSymbol()));
					boolean bShareBelongsToCompany =  (editShare==null || (editShare!=null && editShare.getCompanyId()==themeDisplay.getCompanyId()));
					if (bShareFoundByName  || bShareFoundBySymbol || !bShareBelongsToCompany)
					{
						validated=false;
						SessionErrors.add(actionRequest, "share.error.exists");
					}
					else
					{
						if (!bEditMode)
							share = _shareLocalService.createShare(CounterLocalServiceUtil.increment(Share.class.getName()));
						else
							share = _shareLocalService.fetchShare(shareId);
						share.setActive(active.equals("") ? Boolean.FALSE : Boolean.TRUE);
						share.setName(name);
						share.setSymbol(symbol);
						share.setNumbertopurchase(numbertopurchase);
						share.setPercentual_limit_buy(percentual_limit_buy);
						share.setPercentual_stop_lost(percentual_stop_lost);
						share.setPercentual_stop_profit(percentual_stop_profit);
						share.setPercentual_trailling_stop_lost(percentual_trailling_stop_lost);						
						share.setCompanyId(themeDisplay.getCompanyId());
						share.setGroupId(themeDisplay.getScopeGroupId());
					//	share.setPercentual_stop_profit(percentual_stop_profit);
						
						share.setExpiry_date(expiry_date);
						share.setMultiplier(multiplier);
						share.setTick_futures(tick_futures);
						share.setSecurity_type(security_type);					
						share.setPrimary_exchange(primary_exchange);
						share.setExchange(exchange);
						share.setMarketId(marketId);
						
						JSONObject  jsonFutureShareParams = JSONFactoryUtil.createJSONObject();
						if (security_type.equals(ConfigKeys.SECURITY_TYPE_FUTUROS))						
						{					
							jsonFutureShareParams.put("expirationmonth", expirationmonth.toString());
							jsonFutureShareParams.put("expirationweek", expirationweek);	
							jsonFutureShareParams.put("expirationdayweek", expirationdayweek);
							share.setExpiry_expression(jsonFutureShareParams.toString());
							share.setExpiry_date(sdfSHORT.parse(Utilities.getActiveFutureDate(expirationmonth.toString(), expirationdayweek, expirationweek)));
							
						}
						else
							share.setExpiry_expression(null);
						/* GENERAMOS EL JSON CON LOS DATOS DEL FUTURO   */					
						if (!bEditMode)
							share.setCreateDate(new Date());
						else
						{
							share.setDate_validated_trader_provider(null);  // para verificarlo de nuevo
							share.setValidated_trader_provider(Boolean.FALSE);
						}
						share.setModifiedDate(new Date());
						share.setUserCreatedId(themeDisplay.getUserId());
					}
				}
		}  // end if inicial
		
		} // end try 
		catch (Exception e)
		{
			_log.info("ValidateDataShare:" + e.getMessage());
			validated=false;
		}
		return validated;
		
	}
	
	
	private boolean ValidateDataMarket(ActionRequest actionRequest)
	{
	
		boolean validated = true;	
	  		// add / edit
		String mvcPath = actionRequest.getParameter("javax.portlet.action");
		Market editMarket=null; 
		String name = ParamUtil.getString(actionRequest,"name","");
		String identifier = ParamUtil.getString(actionRequest,"identifier","");
		String description = ParamUtil.getString(actionRequest,"description","");
		String active = ParamUtil.getString(actionRequest,"active","");
		String starthour = ParamUtil.getString(actionRequest,"starthour","");
		String endhour = ParamUtil.getString(actionRequest,"endhour","");			
		String currency = ParamUtil.getString(actionRequest,"currency","");
		
		long marketId =  ParamUtil.getLong(actionRequest,"marketId",0);
		boolean bEditMode=	(marketId !=0);
		boolean bNameOK = Validator.isContent(name)  && name.length()<=75;
		boolean bIdentifierOK = Validator.isContent(identifier)  && identifier.length()<=75;
		boolean bDescriptionOK = Validator.isContent(description)  && description.length()<=150;		
		
		boolean bStartHourOK = Utilities.validateTime24hours(starthour);
		boolean bEndHourOK =  Utilities.validateTime24hours(endhour);
		
		boolean bHourOK = bStartHourOK &&  bEndHourOK && (endhour.compareTo(starthour)>=0) ;
				
		
		try 
		{
			
		if (name.equals("") || identifier.equals("") || description.equals("") || starthour.equals("") || endhour.equals("") )
		{
			
			validated=false;
			SessionErrors.add(actionRequest, "market.error.missingparameters");
		}
		else
		{
			if (!bNameOK || !bIdentifierOK || !bDescriptionOK || !bStartHourOK || !bEndHourOK)
			{
				validated=false;			
				SessionErrors.add(actionRequest, "market.error.formatparameters");
			}
			else
			{
				/* shareid belongs to company? */				
				/* exists by name or by simbol */
				if (bEditMode)
				{
					editMarket = _marketLocalService.fetchMarket(marketId);
					
				}
				/* let update with no changes */
				Market MarketFoundByName = _marketLocalService.findByNameMarketCompanyGroup(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), name);
				boolean bMarketFoundByName =   MarketFoundByName!=null && (editMarket==null || (editMarket!=null && !name.equals(editMarket.getName())));
				Market MarketFoundByIdentifier = _marketLocalService.findByIdentifierCompanyGroup(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), identifier);
				boolean bMarketFoundByIdentifier =   MarketFoundByIdentifier!=null &&  (editMarket==null || editMarket!=null && !identifier.equals(editMarket.getIdentifier()));
				boolean bMarketBelongsToCompany =  (editMarket==null || (editMarket!=null && editMarket.getCompanyId()==themeDisplay.getCompanyId()));
				if (!bEditMode && (bMarketFoundByName  || bMarketFoundByIdentifier || !bMarketBelongsToCompany))
				{
					validated=false;
					SessionErrors.add(actionRequest, "market.error.exists");
				}
				if (!bHourOK)
				{
					validated=false;
					SessionErrors.add(actionRequest, "market.error.endhour_gt_estarthour");
				}
				else
				{
					if (!bEditMode)
						market = _marketLocalService.createMarket(CounterLocalServiceUtil.increment(Market.class.getName()));
					else
						market = _marketLocalService.fetchMarket(marketId);
					market.setActive(active.equals("") ? Boolean.FALSE : Boolean.TRUE);
					market.setName(name);
					market.setDescription(description);
					market.setIdentifier(identifier);				
					market.setCompanyId(themeDisplay.getCompanyId());
					market.setGroupId(themeDisplay.getScopeGroupId());
					market.setStart_hour(starthour.replace(":", ""));
					market.setEnd_hour(endhour.replace(":", ""));
					market.setCurrency(currency);						
					if (!bEditMode)
						market.setCreateDate(new Date());
					market.setModifiedDate(new Date());					
				}
			}
		}  // end if inicial
		
		} // end try 
		catch (Exception e)
		{
			_log.info("ValidateDataMarket:" + e.getMessage());
			validated=false;
		}
		return validated;
		
	}
	
	public void editStrategyShareParams(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		
		themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		/* VALORES DE LOS EXPANDO DE CADA EXTRSATEGIA */
		Map<String, String> paramValues = new HashMap<String,String>(); 
		
		ServiceContext serviceContext = null;
		try {
			serviceContext = ServiceContextFactory.getInstance(StrategyShare.class.getName(), actionRequest);
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean validated = Boolean.TRUE;//= ValidateDataShare(actionRequest);		
		long numbertopurchase =  ParamUtil.getLong(actionRequest,ConfigKeys._FIELD_NUMBER_TO_PURCHASE,-1);
		double percentual_limit_buy=  ParamUtil.getDouble(actionRequest,"percentual_limit_buy",0d);
		double percentual_stop_lost=  ParamUtil.getDouble(actionRequest,ConfigKeys._FIELD_STOP_LOST,0d);
		double percentual_stop_profit=  ParamUtil.getDouble(actionRequest,ConfigKeys._FIELD_STOP_PROFIT,0d);		
		double trailling_stop_lost =  ParamUtil.getDouble(actionRequest,ConfigKeys._FIELD_TRAILLING_STOP_LOST,0d);
		long shareId =  ParamUtil.getLong(actionRequest,"shareId",-1);
		long strategyId =  ParamUtil.getLong(actionRequest,"strategyId",-1);		
		boolean bExists = false;
	    Strategy strategy = _strategyLocalService.fetchStrategy(strategyId);

		/* ESTOS DATOS SI LAS ESTRATEGIAS PUEDEN SOBREESCRIBIR LOS DATOS */
		if (strategy!=null && strategy.getCan_override_params())
		{
			validated = numbertopurchase>=1 && 
					percentual_limit_buy>=0 && percentual_limit_buy<=100  && 
						percentual_stop_lost>=0 && percentual_stop_lost<=100 && 
								percentual_stop_profit>=0 && percentual_stop_profit<=100
										&&  trailling_stop_lost>=0 && trailling_stop_lost<=100;
		}
		
		/*  BUSCAMOS EN LA REQUEST TODOS LOS PARAMETROS QUE EMPIECEN CON EL PREFIJO Utilities.IBTRADER_PREFIX...*/
		Enumeration enumeration = actionRequest.getParameterNames();
	    while (enumeration.hasMoreElements()) {
	        String parameterName = (String) enumeration.nextElement();
	        String parameterValue = actionRequest.getParameter(parameterName);
	        /* EXPANDOS + VALUE */
	        if (parameterName.startsWith(Utilities._IBTRADER_STRATEGY_CUSTOM_FIELDS_))
	        {
	        	paramValues.put(parameterName.replace(Utilities._IBTRADER_STRATEGY_CUSTOM_FIELDS_, ""), parameterValue);
	        }
	        	       
	    }
	    
	    StrategyImpl _strategyImpl = null;
 	    try {
			 _strategyImpl = (StrategyImpl) Utilities.getContextClassLoader().loadClass(strategy.getClassName()).newInstance();
			 _strategyImpl.init(strategy.getCompanyId());   // INICIALIZAMOS
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  /* Verificamos que est�n correctos  con la llamada al metodo */
 	    if (_strategyImpl==null)
 	    	validated = false;
 	    else
 	    	validated = validated && _strategyImpl.validateParams(paramValues);
	    
		
		SessionMessages.clear(actionRequest);
		SessionErrors.clear(actionRequest);
		
		
		StrategyShare StrategyShare = _strategyshareLocalService.getByCommpanyShareStrategyId(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), shareId, strategyId);
		if (StrategyShare!=null)
			bExists = true;
		
		if (!bExists)
			strategyshare = _strategyshareLocalService.createStrategyShare(CounterLocalServiceUtil.increment(StrategyShare.class.getName()));
		else
			strategyshare = StrategyShare;
			
		
		try 
		{			
			if (numbertopurchase==-1  || !validated )
			{
				
				if (!_strategyImpl.getValidateParamsKeysError().equals(""))				
					SessionErrors.add(actionRequest, _strategyImpl.getValidateParamsKeysError());				
				else
					SessionErrors.add(actionRequest, "share.error.missingparameters");
			}
			else
			{
					
					_log.info("Hay que meter un metodo en el interfaz que sea validar los expandos");
				
					if (!bExists)
						strategyshare.setCreateDate(new Date());											
					strategyshare.setModifiedDate(new Date());					
					strategyshare.setCompanyId(themeDisplay.getCompanyId());
					strategyshare.setGroupId(themeDisplay.getScopeGroupId());
					strategyshare.setStrategyId(strategyId);
					strategyshare.setShareId(shareId);								
					
					
					/* GENERAMOS EL JSON CON LOS DATOS DEL ACTIVO  + LOS EXPANDOS  */					
					JSONObject  jsonStrategyShareParams = JSONFactoryUtil.createJSONObject();
					jsonStrategyShareParams.put(ConfigKeys._FIELD_NUMBER_TO_PURCHASE, numbertopurchase);
					jsonStrategyShareParams.put("percentual_limit_buy", percentual_limit_buy);
					jsonStrategyShareParams.put(ConfigKeys._FIELD_STOP_LOST, percentual_stop_lost);
					jsonStrategyShareParams.put(ConfigKeys._FIELD_STOP_PROFIT, percentual_stop_profit);
					jsonStrategyShareParams.put(ConfigKeys._FIELD_TRAILLING_STOP_LOST, trailling_stop_lost);					
					
					/*  BUSCAMOS EN LA REQUEST TODOS LOS PARAMETROS QUE EMPIECEN CON EL PREFIJO Utilities.IBTRADER_PREFIX...*/				
				    //while (enumeration.hasMoreElements()) {
				    for (Map.Entry<String, String> StrategyParam : paramValues.entrySet()) {
				     
				    	String parameterName = StrategyParam.getKey();
				        String parameterValue = StrategyParam.getValue();				     
				        jsonStrategyShareParams.put(parameterName,parameterValue);
				        
				    }		
				    strategyshare.setStrategyparamsoverride(jsonStrategyShareParams.toString());
					_strategyshareLocalService.updateStrategyShare(strategyshare);	
					
										
					
					SessionMessages.add(actionRequest, "share.success");
			}  // end if inicial
		
		} // end try 
		catch (Exception e)
		{
			_log.info("ValidateDataStrategyShare:" + e.getMessage());
			SessionErrors.add(actionRequest, "share.error.missingparameters");
		}
		
		actionResponse.setRenderParameter("mvcRenderCommandName", "/html/add_edit_strategyshare");
		actionResponse.setRenderParameter("shareId", String.valueOf(shareId));
		actionResponse.setRenderParameter("strategyId", String.valueOf(strategyId));
		actionResponse.setRenderParameter("tab", "share.strategy");
		
		
	}
	
	
	
	public void addStrategyShare(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		
		themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		ServiceContext serviceContext = null;
		try {
			serviceContext = ServiceContextFactory.getInstance(Share.class.getName(), actionRequest);
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long shareId = ParamUtil.getLong(actionRequest, "shareId");
	    long strategyId = ParamUtil.getLong(actionRequest, "strategyId");
		boolean active = ParamUtil.getBoolean(actionRequest,"active",false);			

	    
	    StrategyShare _strategyshare = StrategyShareLocalServiceUtil.getByCommpanyShareStrategyId(serviceContext.getScopeGroupId(), serviceContext.getCompanyId(), shareId, strategyId);
	    if (_strategyshare==null) // existe, solo ponemos el active a como venga el checkbox
	    {
	    	
	    	StrategyShare strategyshare = _strategyshareLocalService.createStrategyShare(CounterLocalServiceUtil.increment(StrategyShare.class.getName()));
			strategyshare.setActive(Boolean.FALSE);
			strategyshare.setGroupId(serviceContext.getScopeGroupId());
			strategyshare.setCompanyId(serviceContext.getCompanyId());
			strategyshare.setCreateDate(new Date());
			strategyshare.setShareId(shareId);
			strategyshare.setStrategyId(strategyId);					
	    	_strategyshareLocalService.addStrategyShare(strategyshare);
	    }

		if (_strategyshare!=null) // existe, solo ponemos el active a como venga el checkbox 
		{
			_strategyshare.setActive(active);
			_strategyshareLocalService.updateStrategyShare(_strategyshare);
			
		}
		//boolean validated = ValidateDataShare(actionRequest);
		
		
		
	}
	
	public void addeditMarket(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		ServiceContext serviceContext = null;
		try {
			serviceContext = ServiceContextFactory.getInstance(Market.class.getName(), actionRequest);
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean validated = ValidateDataMarket(actionRequest);
		long marketId = ParamUtil.getLong(actionRequest, "marketId", 0);
		try 
		{
				if (validated)
				{
					if (marketId==0)  // add
						market = _marketLocalService.addMarket(market,serviceContext );
					else
						market = _marketLocalService.editMarket(market,serviceContext );					
					SessionMessages.add(actionRequest, "market.success");
				}
		}				
		catch (Exception e)
		{
			SessionErrors.add(actionRequest, "market.error.missingparameters");
		}
		
		actionResponse.setRenderParameter("mvcRenderCommandName", "/html/add_edit_market");
		actionResponse.setRenderParameter("marketId", String.valueOf(marketId));		
			

		
	

		
		
	}
	
	private void cancelremoveMktData(Share _share)
	{
		List<Config> lConfig=null;
		int 	_CLIENT_ID = 9;	  // el dos para leer, el 3 para escribir			
		String  _HOST = "127.0.0.1";
		int     _PORT = ConfigKeys.TWS_CONNECTION_PORT;	
		TIMApiGITrader  oTWS = null;
		_CLIENT_ID = Long.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyCRON_CONTRACTCHECKER_CLIENT_INITIAL, _share.getCompanyId(), _share.getGroupId())).intValue();	  // el dos para leer, el 3 para escribir

	   _HOST = Utilities.getConfigurationValue(IBTraderConstants.keyTWS_HOST, _share.getCompanyId(), _share.getGroupId());		 
	   _PORT = Integer.valueOf(Utilities.getConfigurationValue(IBTraderConstants.keyTWS_PORT, _share.getCompanyId(), _share.getGroupId()));
	    oTWS = new TIMApiGITrader(_HOST,_PORT, _CLIENT_ID);	
	    /* por si se hubiera colado mas peticiones de realtime que 1, las borramos al arrancar los cron  */
		List<IBOrder> _lrkMktData = IBOrderLocalServiceUtil.findByShareIdCompanyGroup(_share.getShareId(), _share.getCompanyId(), _share.getGroupId());
		if (!_lrkMktData.isEmpty())
		{
			try
			{
				if (oTWS.GITraderTWSIsConnected())  oTWS.GITraderDisconnectFromTWS();
				// _log.info("StartVerifyContractsCron, connecting to TWS");
				oTWS.GITraderConnetToTWS();
				for (IBOrder rkMktData  : _lrkMktData)	{			
						oTWS.GITraderCancelRealTimeContract((new Long(rkMktData.getPrimaryKey()).intValue()));
				}	// end for 				
				if (oTWS.GITraderTWSIsConnected()) oTWS.GITraderDisconnectFromTWS();	
			}
			catch (InterruptedException e)
			{ 
				e.printStackTrace();							
			//	if (oTWS.GITraderTWSIsConnected()) oTWS.GITraderDisconnectFromTWS();
			}
		}
	}
	
	public void RemoveMarket(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		
		themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		ServiceContext serviceContext = null;
		try {
			serviceContext = ServiceContextFactory.getInstance(Market.class.getName(), actionRequest);
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long marketId =  ParamUtil.getLong(actionRequest,"marketId",-1);		
		List<Share> lShare =  _shareLocalService.findByMarketGroupCompany(marketId, serviceContext.getScopeGroupId(), serviceContext.getCompanyId());
		if (!lShare.isEmpty())
			SessionErrors.add(actionRequest, "market.error.shareexists");
		else
		{
			try
			{								
				_marketLocalService.deleteMarket(marketId);								
				SessionMessages.add(actionRequest, "market.delete.success");				
			}				
			catch (Exception e)
			{
				SessionErrors.add(actionRequest, "market.error.shareexists");
			}		
		}
			
		
	}
	
	
	
	
	public void removeShare(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		
		themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		ServiceContext serviceContext = null;
		try {
			serviceContext = ServiceContextFactory.getInstance(Share.class.getName(), actionRequest);
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long shareId =  ParamUtil.getLong(actionRequest,"shareId",-1);
		Share share =  _shareLocalService.fetchShare(shareId);
		
		
		List<Position> lPositions =  PositionLocalServiceUtil.findByCompanyGroupShare(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), shareId);
		if (!lPositions.isEmpty())
			SessionErrors.add(actionRequest, "share.error.positionexists");
		else
		{
			try
			{	
				if (share.IsTradeable())  // si esataba activa para operar????
					cancelremoveMktData(share);				
				ShareLocalServiceUtil.deleteShare(shareId);
				/* CANCELAMOS MKTDATA SI ESTUVIERA */
				SessionMessages.add(actionRequest, "share.delete.success");				
			}				
			catch (Exception e)
			{
				SessionErrors.add(actionRequest, "share.delete.error");
			}		
		}
			
		
	}
	
	public void editShare(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		
		themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		ServiceContext serviceContext = null;
		try {
			serviceContext = ServiceContextFactory.getInstance(Share.class.getName(), actionRequest);
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		long shareId =  ParamUtil.getLong(actionRequest,"shareId",-1);
		boolean  validated = ValidateDataShare(actionRequest);
		boolean _bOpenPosition =  PositionLocalServiceUtil.ExistsOpenPosition(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), shareId);
		try 
		{		
				if (validated)
				{
					if (!_bOpenPosition)
					{
						share = _shareLocalService.editShare(share,serviceContext );
						SessionMessages.add(actionRequest, "share.success");
					}
					else
						SessionErrors.add(actionRequest, "share.error.positionexistsactive");	
				}
		}				
		catch (Exception e)
		{
			SessionErrors.add(actionRequest, "share.error.missingparameters");
		}
		
		actionResponse.setRenderParameter("mvcRenderCommandName", "/html/add_edit_share");
		actionResponse.setRenderParameter("shareId", String.valueOf(shareId));		
		actionResponse.setRenderParameter("tab", "share.details");
		
		
	}
	
	public void addShare(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		
		themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long _retShareId = -1;
		
		ServiceContext serviceContext = null;
		try {
			serviceContext = ServiceContextFactory.getInstance(Share.class.getName(), actionRequest);
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boolean validated = ValidateDataShare(actionRequest);
		 JSONObject  jsonFutureParams =null;
		
		try 
		{
				if (validated)
				{
					share = _shareLocalService.addShare(share,serviceContext );
					SessionMessages.add(actionRequest, "share.success");
					_retShareId  = share.getShareId();
					 if (share.getExpiry_expression()!=null && !share.getExpiry_expression().equals("")) 
		        		 jsonFutureParams = JSONFactoryUtil.createJSONObject(share.getExpiry_expression());
				}
		}				
		catch (Exception e)
		{
			SessionErrors.add(actionRequest, "share.error.missingparameters");
		}
		
		 
		PortalUtil.copyRequestParameters(actionRequest, actionResponse);
		actionResponse.setRenderParameter("mvcRenderCommandName", "/html/add_edit_share");
		actionResponse.setRenderParameter("shareAddedId", String.valueOf(_retShareId));	
		actionResponse.setRenderParameter("tab", "share.details");
		/*
		 actionResponse.setRenderParameter("mvcRenderCommandName", "/html/add_edit_share");
		actionResponse.setRenderParameter("shareId", String.valueOf(_retShareId));
		actionResponse.setRenderParameter("tab", "share.details");
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        String portletId =  themeDisplay.getPortletDisplay().getId();
        String InstaceId =  themeDisplay.getPortletDisplay().getInstanceId();
        PortletResponse portletResponse = (PortletResponse) actionRequest.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);
        LiferayPortletResponse liferayPortletResponse = PortalUtil.getLiferayPortletResponse(portletResponse); 
        LiferayPortletURL renderUrl = liferayPortletResponse.createLiferayPortletURL(themeDisplay.getPlid(), portletId, PortletRequest.RENDER_PHASE);
		
	    if (_retShareId!=-1) // OK, redirect con nuevos parametros 	    	
	    {*/
	    	
	    	
			
			/*
	        try {     
	        	 _log.info("portletId:"  + portletId);
	        	_log.info("InstaceId:"  + InstaceId);
	        	_log.info("mvcRenderCommandName: /html/add_edit_share");        	
	        	_log.info("Redirecting to:"  + renderUrl.toString());
	           // actionResponse.sendRedirect(renderUrl.toString());
	        } catch (Exception e) {
	            // handle error
	        } 
	    }*/
	    Enumeration<String> lParams = actionRequest.getParameterNames();
		Enumeration<String> lAttr = actionRequest.getAttributeNames();	
		while (lParams.hasMoreElements())
		{
			String  p = lParams.nextElement();
			_log.info("Action Portlet AddShare Param:" + p + ":" + actionRequest.getParameter(p));
		}
		while (lAttr.hasMoreElements())
		{
			String  p2 = lAttr.nextElement();
			_log.info("Action Portlet AddShare:" + p2 + ":" + actionRequest.getParameter(p2));
		}
	    
		
	}
	

	
}