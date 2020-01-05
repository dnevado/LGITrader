package com.ibtrader.service.data.permission;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

@Component(immediate = true, property = {"resource.name=" + StrategyModelPermission.RESOURCE_NAME}, service = ResourcePermissionChecker.class)
public class StrategyModelPermission extends BaseResourcePermissionChecker   {

    public static final String RESOURCE_NAME = "com.ibtrader.service.data";
    private final static Log _log = LogFactoryUtil.getLog(StrategyPermission.class); 
    public static void check(PermissionChecker permissionChecker, long groupId, String actionId) throws PortalException {

    	
    	_log.info("check");
        if (!contains(permissionChecker, groupId, actionId)) {
            throw new PrincipalException.MustHavePermission(
                permissionChecker, RESOURCE_NAME, groupId, actionId);
        }
    }

    public static boolean contains(
        PermissionChecker permissionChecker, long groupId, String actionId) {
    	_log.info("contains");
        return permissionChecker.hasPermission(
            groupId, RESOURCE_NAME, groupId, actionId);
    }

    @Override
    public Boolean checkResource(
        PermissionChecker permissionChecker, long classPK, String actionId) {

    	_log.info("checkResource");
        return contains(permissionChecker, classPK, actionId);
    }
}