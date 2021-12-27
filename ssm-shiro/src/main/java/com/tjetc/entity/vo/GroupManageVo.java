package com.tjetc.entity.vo;

public class GroupManageVo {
    //小组id
    private Long id;
    //头像
    private String avatar;
    //小组名称
    private String name;
    //组长姓名
    private String realName;
    //组长电话
    private String phoneNumber;

    public GroupManageVo(Long id, String avatar, String name, String realName, String phoneNumber) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.realName = realName;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "GroupManageVo{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
