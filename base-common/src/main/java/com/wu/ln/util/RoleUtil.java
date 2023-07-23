package com.wu.ln.util;

public class RoleUtil {

    /**
     * 判断用户是否有权限
     *
     * @param userPermissions 用户权限
     * @param requiredPermission 需要的权限
     * @return 是否有权限
     */
     public static boolean hasPermission(int userPermissions, int requiredPermission) {
        return (userPermissions & requiredPermission) == requiredPermission;
    }
}
