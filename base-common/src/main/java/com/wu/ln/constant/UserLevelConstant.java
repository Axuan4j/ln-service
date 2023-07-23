package com.wu.ln.constant;

public enum UserLevelConstant {

    LEVEL_1(1, "黑铁"),
    LEVEL_2(2, "青铜"),
    LEVEL_3(3, "白银"),
    LEVEL_4(4, "黄金"),
    LEVEL_5(5, "铂金"),
    LEVEL_6(6, "钻石"),
    LEVEL_7(7, "大师"),
    LEVEL_8(8, "宗师"),
    LEVEL_9(9, "王者");

    private final Integer level;

    private final String name;

    UserLevelConstant(Integer level, String name) {
        this.level = level;
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public static String getNameByLevel(Integer level) {
        for (UserLevelConstant userLevelConstant : UserLevelConstant.values()) {
            if (userLevelConstant.getLevel().equals(level)) {
                return userLevelConstant.getName();
            }
        }
        return null;
    }

}
