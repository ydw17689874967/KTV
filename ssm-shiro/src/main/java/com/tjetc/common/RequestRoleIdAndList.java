package com.tjetc.common;

import com.github.pagehelper.PageInfo;

public class RequestRoleIdAndList {
    private Long roleId;
    private PageInfo list;

    public RequestRoleIdAndList(Long roleId, PageInfo list) {
        this.roleId = roleId;
        this.list = list;
    }

    public RequestRoleIdAndList() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public PageInfo getList() {
        return list;
    }

    public void setList(PageInfo list) {
        this.list = list;
    }
}
