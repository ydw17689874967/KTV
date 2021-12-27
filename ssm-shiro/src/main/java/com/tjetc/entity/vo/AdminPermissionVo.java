package com.tjetc.entity.vo;

import java.util.List;

public class AdminPermissionVo {
    private Long adminId;
    private String adminUsername;
    private Boolean adminRoot;
    private List<String> permissionNames;

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public Boolean getAdminRoot() {
        return adminRoot;
    }

    public void setAdminRoot(Boolean adminRoot) {
        this.adminRoot = adminRoot;
    }

    public List<String> getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(List<String> permissionNames) {
        this.permissionNames = permissionNames;
    }
}
