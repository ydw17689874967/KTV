package com.tjetc.entity;

import java.time.LocalDateTime;

public class Project {
    private Long id;

    private String title;

    private String desc;

    private String type;

    private Long groupId;

    private Double itemCost;

    private Double itemProfit;

    private LocalDateTime createTime;

    private LocalDateTime lastUpdateTime;

    private Integer state;

    public Project() {
    }

    public Project(String title, String desc, String type, Double itemCost, Double itemProfit) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.itemCost = itemCost;
        this.itemProfit = itemProfit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Double getItemCost() {
        return itemCost;
    }

    public void setItemCost(Double itemCost) {
        this.itemCost = itemCost;
    }

    public Double getItemProfit() {
        return itemProfit;
    }

    public void setItemProfit(Double itemProfit) {
        this.itemProfit = itemProfit;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}