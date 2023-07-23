package com.wu.ln.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("ln_admin_menu")
public class LnAdminMenu {

    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO, value = "id")
    private Long id;

    @TableField(value = "parent_id")
    private Long parentId;

    @TableField(value = "menu_name")
    private String menuName;

    @TableField(value = "menu_url")
    private String menuUrl;

    @TableField(value = "menu_icon")
    private String menuIcon;

    @TableField(value = "menu_sort")
    private Integer menuSort;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "create_time")
    private String createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }


    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }


    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }


    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
