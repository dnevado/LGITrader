package com.ibtrader.service.data.permission;





import com.ibtrader.data.model.Strategy;
import com.ibtrader.data.service.StrategyLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/* DA PROBLEMAS*/
@Component(immediate = true,property = {"model.class.name=com.ibtrader.data.model.Strategy"})
public class StrategyPermission implements BaseModelPermissionChecker {

	
	 private final static Log _log = LogFactoryUtil.getLog(StrategyPermission.class); 
	
    public static void check(
        PermissionChecker permissionChecker, long strategyId, String actionId)
        throws PortalException, SystemException {

    	
    	_log.info("check");
        if (!contains(permissionChecker, strategyId, actionId)) {
            throw new PrincipalException();
        }
    }

    public static void check(
        PermissionChecker permissionChecker, long groupId, long guestbookId,
        String actionId)
        throws PortalException {
    	_log.info("check");
        if (!contains(permissionChecker, groupId, actionId)) {
            throw new PrincipalException.MustHavePermission(
                permissionChecker, Strategy.class.getName(), guestbookId,
                actionId);
        }
    }

    public static boolean contains(
        PermissionChecker permissionChecker, long groupId, long strategyId, String actionId) 
            throws PortalException {

    	//Strategy  strategy = _strategyLocalService.getStrategy(strategyId);
    	_log.info("contains");
        return StrategyModelPermission.contains(permissionChecker, groupId, actionId);
    }

    public static boolean contains(
    		
        PermissionChecker permissionChecker, long strategyId, String actionId)
        throws PortalException, SystemException {

    	_log.info("contains");
    	Strategy strategy  = _strategyLocalService.getStrategy(strategyId);
    	return contains(permissionChecker, strategy, actionId);
    			
    }

    public static boolean contains(
        PermissionChecker permissionChecker, Strategy strategy, String actionId) 
            throws PortalException, SystemException {
    	_log.info("contains");
        return permissionChecker.hasPermission(strategy.getGroupId(), Strategy.class.getName(), strategy.getStrategyID(), actionId);

    }

    @Reference(unbind = "-")
    protected void setStrategyLocalService(StrategyLocalService strategyLocalService) {
    	_strategyLocalService = strategyLocalService;
    }

    private static StrategyLocalService _strategyLocalService;

    @Override
    public void checkBaseModel(
        PermissionChecker permissionChecker, long groupId, long strategyId, String actionId) throws PortalException {
    	_log.info("checkBaseModel");
            check(permissionChecker, strategyId, actionId);
    }
}