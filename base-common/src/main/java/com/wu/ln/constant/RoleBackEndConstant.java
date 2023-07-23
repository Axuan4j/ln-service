package com.wu.ln.constant;

public enum RoleBackEndConstant {

    OPERATOR(1, "运维人员"),

    CUSTOMER(2, "客服人员"),

    ADMIN(4, "管理员"),

    SUPER_ADMIN(8, "超级管理员");

    private final Integer role;

    private final String name;

    RoleBackEndConstant(Integer role, String name) {
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
        for (RoleBackEndConstant roleConstant : RoleBackEndConstant.values()) {
            if (roleConstant.getRole().equals(role)) {
                return roleConstant.getName();
            }
        }
        return null;
    }
}
