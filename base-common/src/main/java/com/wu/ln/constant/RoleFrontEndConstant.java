package com.wu.ln.constant;

public enum RoleFrontEndConstant {

    ROLE_USER(1, "普通用户"),

    ROlE_VIP_USER(2, "VIP用户"),

    ROLE_GROUP(4, "板块主持人"),

    ROLE_GROUP_ADMIN(8, "总板块管理员");

    private final Integer role;

    private final String name;

    RoleFrontEndConstant(Integer role, String name) {
        this.role = role;
        this.name = name;
    }

    public Integer getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    /**
     * 根据角色ID获取角色名称
     * @param role 角色ID
     * @return 角色名称
     */
    public static String getNameByRole(Integer role) {
        for (RoleFrontEndConstant roleConstant : RoleFrontEndConstant.values()) {
            if (roleConstant.getRole().equals(role)) {
                return roleConstant.getName();
            }
        }
        return null;
    }
}
