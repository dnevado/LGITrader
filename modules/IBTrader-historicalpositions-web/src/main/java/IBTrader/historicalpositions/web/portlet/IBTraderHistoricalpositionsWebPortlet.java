package IBTrader.historicalpositions.web.portlet;

import IBTrader.historicalpositions.web.constants.IBTraderHistoricalpositionsWebPortletKeys;


import com.ibtrader.data.model.Position;
import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.service.PositionLocalService;
import com.ibtrader.data.service.ShareLocalService;
import com.ibtrader.util.Utilities;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
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
		"javax.portlet.display-name=IBTrader-historicalpositions-web Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + IBTraderHistoricalpositionsWebPortletKeys.IBTraderHistoricalpositionsWeb,
		"javax.portlet.resource-bundle=content.Language",
        "com.liferay.portlet.scopeable=true",
        "javax.portlet.expiration-cache=0",
        "javax.portlet.init-param.add-process-action-success-action=false",
        "javax.portlet.supports.mime-type=text/html",
		"javax.portlet.security-role-ref=power-user,user"
		
	},
	service = Portlet.class
)
public class IBTraderHistoricalpositionsWebPortlet extends MVCPortlet {

	ThemeDisplay themeDisplay;
	private static final Log   _log = LogFactoryUtil.getLog(IBTraderHistoricalpositionsWebPortlet.class);
	/* SACAMOS TODAS LAS POSICIONES HISTORICAS, DIA n -1 */
	
	private Date _DateINI = null;
	private Date _DateEND = null;
	
	/* lista resultados */
	 List<Position> _lPosition  = null;
	/* totales en json */
	 JSONArray results = null;

	
	
	private void getInitialDateFilter(RenderRequest renderRequest, RenderResponse renderResponse)
	{
		themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY); 
		 
		 
		 _DateINI = Utilities.getDate(themeDisplay.getUser());
		 _DateEND = Utilities.getDate(themeDisplay.getUser());
		 
		 /* OPERACIONES DEL DIA ANTERIOR, SUPUESTAMENTE FINALIZADAS,
		  * PONEMOS UN A�O DE 2017*/
		 Calendar _cINI = Calendar.getInstance();
		
		 _cINI.add(Calendar.DATE, -1);  // DOS DIAS ATRAS 		 
		 _DateINI.setTime(_cINI.getTimeInMillis());
		 _DateINI.setHours(0);
		 _DateINI.setMinutes(0);
		 _DateINI.setSeconds(0);
		
		 
		 _DateEND.setHours(23);
		 _DateEND.setMinutes(59);
		 _DateEND.setSeconds(59);
	}
	
	
	private void getSearchResults()
	{
		
		 /* lista resultados */
		String position_mode = Utilities.getPositionModeType(null, themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId()); 

		
		_lPosition  = _positionLocalService.findByCompanyGroupDate(themeDisplay.getCompanyId(),themeDisplay.getScopeGroupId(), _DateINI,_DateEND, position_mode);
		/* totales en json */
		results = _positionLocalService.findPositionClosedResults(_DateINI, _DateEND, themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId(), position_mode);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		// TODO Auto-generated method stub
		
		/* cuando envia datos de fechas en el action */
		if (renderRequest.getAttribute("_DateINI")!=null)
			_DateINI = (Date) renderRequest.getAttribute("_DateINI"); 
		if (renderRequest.getAttribute("_DateEND")!=null)
			_DateEND = (Date) renderRequest.getAttribute("_DateEND");
		 
		if (Validator.isNull(_DateINI))
			getInitialDateFilter(renderRequest, renderResponse);
		
		getSearchResults();
		
		renderRequest.setAttribute("_lPosition", _lPosition);
		renderRequest.setAttribute("_jsonResults", results);
		
		PortletURL iteratorURL = renderResponse.createRenderURL();

		SearchContainer<Position> searchContainer = null;
		searchContainer  = new SearchContainer<Position>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, iteratorURL, null, StringPool.BLANK);
		searchContainer.setEmptyResultsMessage("Posiciones no encontradas");        
		searchContainer.setResults(ListUtil.subList(_lPosition, searchContainer.getStart(), searchContainer.getEnd()));
		searchContainer.setTotal(_lPosition.size());
		renderRequest.setAttribute("searchPosition" , searchContainer); 
		
		renderRequest.setAttribute("iteratorURL" , iteratorURL);		
		renderRequest.setAttribute("_DateINI" , _DateINI);
		renderRequest.setAttribute("_DateEND" , _DateEND);
		
		
		
		super.doView(renderRequest, renderResponse);

		
	}

	    
    private PositionLocalService _positionLocalService;
  
    
    

	@Reference(unbind = "-")
    protected void setPositionService(PositionLocalService positionLocalService) {
		_positionLocalService = positionLocalService;
    }

	public void cancelOpenPosition(ActionRequest actionRequest, ActionResponse actionResponse)
	{
	
	}
	
	
	public void editPosition(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		UploadPortletRequest req = PortalUtil.getUploadPortletRequest(actionRequest);
		
		themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ServiceContext serviceContext = null;
		try {
			serviceContext = ServiceContextFactory.getInstance(Position.class.getName(), actionRequest);
		} catch (PortalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	    String editor = ParamUtil.getString(actionRequest, "editor","");
	    long positionId = ParamUtil.getLong(actionRequest, "positionId",-1);
	    String redirect = ParamUtil.getString(actionRequest,"redirect","");

	    Position position = _positionLocalService.fetchPosition(positionId);	    
	    if (position!=null)
	    {
	    	position.setDescription(editor);
	    	_positionLocalService.updatePosition(position);
	    	SessionMessages.add(actionRequest, "share.success");
	    }
	    else
	    	SessionErrors.add(actionRequest, "share.error.exist");
	    
		actionResponse.setRenderParameter("mvcPath", "/position_detail.jsp");
		actionResponse.setRenderParameter("positionId", String.valueOf(positionId));
		actionResponse.setRenderParameter("redirect", String.valueOf(redirect));		
	    
	}
	
	public void searchPosition(ActionRequest actionRequest, ActionResponse actionResponse)
	{
		themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		  // Getting the Date Range
		int fromDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int fromMonth = ParamUtil.getInteger(actionRequest, "startDateMonth");
		int fromYear = ParamUtil.getInteger(actionRequest, "startDateYear");
		int  toDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int  toMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int  toYear = ParamUtil.getInteger(actionRequest, "endDateYear");
		
		try {
		     _DateINI = PortalUtil.getDate(fromMonth, fromDay, fromYear);
			 _DateEND = PortalUtil.getDate(toMonth, toDay, toYear);
			
			 _DateINI.setHours(0);
			 _DateINI.setMinutes(0);
			 _DateINI.setSeconds(0);
			 
			 _DateEND.setHours(0);
			 _DateEND.setMinutes(0);
			 _DateEND.setSeconds(0);
			
			 actionRequest.setAttribute("_DateINI", _DateINI); 
			 actionRequest.setAttribute("_DateEND", _DateEND); 

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
	    }
		
		
	    
	}
	

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY); 
		long positionId = ParamUtil.get(renderRequest, "positionId", -1);
		Position position = null;
		if (positionId!=-1)
		{
			position = _positionLocalService.fetchPosition(positionId);
		}
		renderRequest.setAttribute("position", position);
		super.render(renderRequest, renderResponse);
	}

	
}