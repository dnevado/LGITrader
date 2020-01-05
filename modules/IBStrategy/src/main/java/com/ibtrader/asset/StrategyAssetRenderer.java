package com.ibtrader.asset;

import java.util.Locale;


import com.ibtrader.service.data.permission.StrategyPermission;

import com.ibtrader.data.model.Strategy;
import com.liferay.asset.kernel.model.BaseAssetRenderer;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.ibtrader.admin.constants.IBStrategyPortletKeys;


import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// BaseJSPAssetRenderer<Strategy>
public class StrategyAssetRenderer extends BaseAssetRenderer {
	
	private Strategy _strategy;
	private ServletContext _servletContext;
	//private final ResourceBundleLoader _resourceBundleLoader;
    private Log _log = LogFactoryUtil.getLog(StrategyAssetRenderer.class); 
	
	public StrategyAssetRenderer(Strategy strategy) {
		
		_log.info("hasEditPermission");	
		_strategy = strategy;
		//_resourceBundleLoader = resourceBundleLoader;
	}
	
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}
	
	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) 	throws PortalException {

		_log.info("hasEditPermission");	
	  long strategyId = _strategy.getStrategyID();
	  return StrategyPermission.contains(permissionChecker, strategyId, ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) 
	throws PortalException {
		_log.info("hasViewPermission");
	  long strategyId = _strategy.getStrategyID();
	  return StrategyPermission.contains(permissionChecker, strategyId,	  ActionKeys.VIEW);
	}

	@Override
	public Strategy getAssetObject() {
		// TODO Auto-generated method stub
		_log.info("getAssetObject");
		return _strategy;
	}

	@Override
	public long getGroupId() {
		// TODO Auto-generated method stub
		_log.info("getGroupId");
		return _strategy.getGroupId() ;
	}

	@Override
	public long getUserId() {
		// TODO Auto-generated method stub
		_log.info("getUserId");
		return _strategy.getUserId();
	}

	@Override
	public String getUserName() {
		_log.info("getUserId");
		// TODO Auto-generated method stub
		return "Strategy User Name";
	}
	
	

	@Override
	public String getUuid() {
		_log.info("getUserId");
		// TODO Auto-generated method stub
		return _strategy.getUuid();
	}

	@Override
	public String getClassName() {
		_log.info("getUserId");
		// TODO Auto-generated method stub
	    return Strategy.class.getName();

	}
	@Override
	public long getClassPK() {
		// TODO Auto-generated method stub
		_log.info("getUserId");
		return _strategy.getStrategyID();
	}

	@Override
	public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
		// TODO Auto-generated method stub
		_log.info("getUserId");
		return _strategy.getDescription();
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		_log.info("getUserId");
		return _strategy.getName();
	}
	@Override
	public boolean include(HttpServletRequest request, HttpServletResponse response, String template) throws Exception {
		_log.info("getUserId");
	    request.setAttribute("STRATEGY", _strategy);
	    request.setAttribute("HtmlUtil", HtmlUtil.getHtml());
	    request.setAttribute("StringUtil", new StringUtil());
	   // return super.include(request, response, template);
		return true;
	}
	

	/*  @Override
	 public String getJspPath(HttpServletRequest request, String template) {
			_log.info("getUserId");
		 if (template.equals(TEMPLATE_FULL_CONTENT)) {
		      request.setAttribute("gb_strategy", _strategy);

		      return "/asset/strategy/" + template + ".jsp";
		    } else {
		      return null;
		    }

	  }
	*/
	 @Override
	  public PortletURL getURLEdit(LiferayPortletRequest liferayPortletRequest,LiferayPortletResponse liferayPortletResponse) throws Exception 
	  
	 {
			_log.info("getUserId");
	    PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
	        getControlPanelPlid(liferayPortletRequest), IBStrategyPortletKeys.IBStrategy,
	        PortletRequest.RENDER_PHASE);
	    portletURL.setParameter("mvcRenderCommandName", "/edit_strategy");
	    portletURL.setParameter("strategy", String.valueOf(_strategy.getStrategyID()));
	    portletURL.setParameter("showback", Boolean.FALSE.toString());

	    return portletURL;
	  }
	 
	 @Override
	  public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest,LiferayPortletResponse liferayPortletResponse, String noSuchEntryRedirect) throws Exception {
			_log.info("getUserId");
	    try {
	      long plid = PortalUtil.getPlidFromPortletId(_strategy.getGroupId(),IBStrategyPortletKeys.IBStrategy);

	      PortletURL portletURL;
	      if (plid == LayoutConstants.DEFAULT_PLID) {
	        portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(liferayPortletRequest),IBStrategyPortletKeys.IBStrategy, PortletRequest.RENDER_PHASE);
	      } else {
	        portletURL = PortletURLFactoryUtil.create(liferayPortletRequest,IBStrategyPortletKeys.IBStrategy, plid, PortletRequest.RENDER_PHASE);
	      }

	      portletURL.setParameter("mvcRenderCommandName", "/edit_strategy");
	      portletURL.setParameter("guestbookId", String.valueOf(_strategy.getStrategyID()));

	      String currentUrl = PortalUtil.getCurrentURL(liferayPortletRequest);

	      portletURL.setParameter("redirect", currentUrl);

	      return portletURL.toString();

	    } catch (PortalException e) {

	    } catch (SystemException e) {
	    }

	    return noSuchEntryRedirect;
	  }

	 @Override
	  public String getURLView(LiferayPortletResponse liferayPortletResponse,   WindowState windowState) throws Exception {

			_log.info("getUserId");
	    return super.getURLView(liferayPortletResponse, windowState);
	  }

	@Override
	public boolean isPrintable() {
		// TODO Auto-generated method stub
		_log.info("isPrintable");
		return true;
	}

	@Override
	public String renderActions(RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		// TODO Auto-generated method stub
		_log.info("renderActions");
		return super.renderActions(renderRequest, renderResponse);
	}
}
