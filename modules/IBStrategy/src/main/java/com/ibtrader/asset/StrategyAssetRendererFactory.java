package com.ibtrader.asset;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.PermissionChecker;


import com.liferay.portal.kernel.theme.ThemeDisplay;

import com.ibtrader.data.service.StrategyLocalService;
import com.ibtrader.service.data.permission.StrategyPermission;
import com.ibtrader.admin.constants.IBStrategyPortletKeys;
import com.ibtrader.data.model.Strategy;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = {"javax.portlet.name=" + IBStrategyPortletKeys.IBStrategy},service = AssetRendererFactory.class)
public class StrategyAssetRendererFactory extends BaseAssetRendererFactory<Strategy> {
	
	private StrategyLocalService _strategyLocalService;
    private static final boolean _LINKABLE  = true;
    public static final String CLASS_NAME = Strategy.class.getName();
    public static final String TYPE = "strategy";
    private Log _log = LogFactoryUtil.getLog(StrategyAssetRendererFactory.class); 

    public StrategyAssetRendererFactory() 
	{
    	    
    	
    	_log.info("StrategyAssetRendererFactory");
	    setClassName(CLASS_NAME);
	    setLinkable(Boolean.TRUE);
	    setPortletId(IBStrategyPortletKeys.IBStrategy);
	    setCategorizable(Boolean.TRUE);
	    setSearchable(Boolean.TRUE);
	    setSelectable(Boolean.TRUE);
	   
	  }
	
    @Override
	public AssetRenderer<Strategy> getAssetRenderer(long classPK) throws PortalException {
		// TODO Auto-generated method stub
    	_log.info("StrategyAssetRendererFactory");
    	return getAssetRenderer(classPK, 0);
	}
    
	@Override
	public AssetRenderer<Strategy> getAssetRenderer(long classPK, int type) throws PortalException 
	{

		_log.info("getAssetRenderer");
	  Strategy strategy = _strategyLocalService.getStrategy(classPK);

	  StrategyAssetRenderer strategyAssetRenderer = new StrategyAssetRenderer(strategy);

	  strategyAssetRenderer.setAssetRendererType(type);
	  strategyAssetRenderer.setServletContext(_servletContext);

	  return strategyAssetRenderer;
	}
	@Override
	  public String getClassName() {
		_log.info("getAssetRenderer");
	    return CLASS_NAME;
	  }

	@Override
	public boolean hasPermission(PermissionChecker permissionChecker, long classPK, String actionId) throws Exception 
	{
		_log.info("getAssetRenderer");
		Strategy strategy = _strategyLocalService.getStrategy(classPK);
	  return StrategyPermission.contains(permissionChecker, strategy,actionId);
	}
	
	  @Override
	  public String getType() {
		  _log.info("getAssetRenderer");
	    return TYPE;
	  }
	  
	  
	  @Override
      public PortletURL getURLAdd(LiferayPortletRequest liferayPortletRequest,LiferayPortletResponse liferayPortletResponse, long classTypeId) 
	  {
		  _log.info("getAssetRenderer");
        PortletURL portletURL = null;

        try {
          ThemeDisplay themeDisplay = (ThemeDisplay) 
          liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);

          portletURL = liferayPortletResponse.createLiferayPortletURL(getControlPanelPlid(themeDisplay),IBStrategyPortletKeys.IBStrategy, PortletRequest.RENDER_PHASE);
          portletURL.setParameter("mvcRenderCommandName", "/edit_strategy");
          portletURL.setParameter("showback", Boolean.FALSE.toString());
        } catch (PortalException e) {
        }

        return portletURL;
      }

      @Override
      public boolean isLinkable() {
    	  _log.info("getAssetRenderer");
        return _LINKABLE;
      }

      @Override
      public String getIconCssClass() {
    	  _log.info("getAssetRenderer");
          return "bookmarks";
      }
      @Reference(target = "(bundle.symbolic.name=com.ibtrader.admin)", unbind = "-")
     public void setResourceBundleLoader(ResourceBundleLoader resourceBundleLoader) {
    }

      private ServletContext _servletContext;

    @Reference(unbind = "-")
        protected void setStrategyLocalService(StrategyLocalService strategyLocalService) {
    	_log.info("setStrategyLocalService");
    	_strategyLocalService = strategyLocalService; 
    }

	@Override
	public AssetRenderer getAssetRenderer(long groupId, String urlTitle) throws PortalException {
		// TODO Auto-generated method stub
		_log.info("getAssetRenderer");
		return super.getAssetRenderer(groupId, urlTitle);
	}

	@Override
	public AssetEntry getAssetEntry(long assetEntryId) throws PortalException {
		// TODO Auto-generated method stub
		_log.info("getAssetEntry");
		return super.getAssetEntry(assetEntryId);
	}

	@Override
	public AssetEntry getAssetEntry(String className, long classPK) throws PortalException {
		// TODO Auto-generated method stub
		_log.info("getAssetEntry");
		return super.getAssetEntry(className, classPK);
	}

	
	
}


