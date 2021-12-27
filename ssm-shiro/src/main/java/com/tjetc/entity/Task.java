package com.tjetc.entity;

public class Task {
    private Long id;

    private String desc;

    private Long projectId;

    private Integer state;

    public Task() {
    }

    public Task(String desc, Long projectId, Integer state) {
        this.id = id;
        this.desc = desc;
        this.projectId = projectId;
        this.state = state;
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
        this.desc = desc == null ? null : desc.trim();
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}