package com.tjetc.model.view;

import java.util.List;

public class UserVoView {
    private String username;
    private List<String> permissionNames;
    private String avatar;

    public UserVoView() {
    }

    public UserVoView(String username, List<String> permissionNames) {
        this.username = username;
        this.permissionNames = permissionNames;
    }

    public UserVoView(String username, List<String> permissionNames, String avatar) {
        this.username = username;
        this.permissionNames = permissionNames;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(List<String> permissionNames) {
        this.permissionNames = permissionNames;
    }
}
