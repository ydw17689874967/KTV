package com.tjetc.entity.vo;

public class UserUserInfoVo {
    private String id;
    private String avatar;
    private String username;
    private String password;
    private String realName;
    private int age;
    private int sex;
    private String email;
    private String phoneNumber;

    public UserUserInfoVo() {
    }

    public UserUserInfoVo(String id, String avatar, String username, String password, String realName, int age, int sex, String email, String phoneNumber) {
        this.id = id;
        this.avatar = avatar;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UserUserInfoVo(String realName, int age, int sex, String email, String phoneNumber) {
        this.realName = realName;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
