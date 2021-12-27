package com.tjetc.entity.vo;

public class MemberInfoVo {
    private Long id;
    //头像
    private String avatar;
    //真实姓名
    private String realName;
    //age
    private Integer age;
    //sex
    private Integer sex;
    //电话号
    private String phoneNumber;
    //邮箱
    private String email;
    //任务描述
    private String desc;
    //任务进度
    private Integer state;
    //任务id
    private Long tid;
    //项目名称
    private String title;


    public MemberInfoVo(Long tid,String desc, Integer state,  String title) {
        this.desc = desc;
        this.state = state;
        this.tid = tid;
        this.title = title;
    }

    public MemberInfoVo(String desc, Integer state) {
        this.desc = desc;
        this.state = state;
    }

    public MemberInfoVo(String avatar, String realName, Integer age, Integer sex, String phoneNumber, String email, String desc) {
        this.avatar = avatar;
        this.realName = realName;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.desc = desc;
    }

    public MemberInfoVo(Long id, String avatar, String realName, Integer age, Integer sex, String phoneNumber, String email, String desc) {
        this.id = id;
        this.avatar = avatar;
        this.realName = realName;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.desc = desc;
    }

    public MemberInfoVo(Long id, String avatar, String realName, Integer age, Integer sex, String phoneNumber, String email, String desc, Integer state) {
        this.id = id;
        this.avatar = avatar;
        this.realName = realName;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.desc = desc;
        this.state = state;
    }

    public MemberInfoVo(Long id, String avatar, String realName, Integer age, Integer sex, String phoneNumber, String email) {
        this.id = id;
        this.avatar = avatar;
        this.realName = realName;
        this.age = age;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
