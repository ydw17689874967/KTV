package com.tjetc.entity.vo;

public class MyTaskVo {
    private Long id;
    private String desc;
    private Integer state;
    private String title;

    public MyTaskVo(Long id, String desc, Integer state) {
        this.id = id;
        this.desc = desc;
        this.state = state;
    }

    public MyTaskVo(Long id, String desc, Integer state, String title) {
        this.id = id;
        this.desc = desc;
        this.state = state;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MyTaskVo(Long id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "MyTaskVo{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                '}';
    }
}
