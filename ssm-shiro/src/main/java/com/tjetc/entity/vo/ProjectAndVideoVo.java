package com.tjetc.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ProjectAndVideoVo {
    private Long id;
    private String title;
    private String desc;
    private String type;
    private String groupName;
    private Double itemCost;
    private Double itemProfit;
    //接收参数日期格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //返回日期的json数据格式
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createTime;
    //接收参数日期格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //返回日期的json数据格式
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime lastUpdateTime;
    private String status;
    private String video;

    /**
     * select 和 update 方法用
     * @param id
     * @param title
     * @param desc
     * @param type
     * @param groupName
     * @param itemCost
     * @param itemProfit
     * @param createTime
     * @param lastUpdateTime
     * @param status
     * @param video
     */
    public ProjectAndVideoVo(Long id, String title, String desc, String type, String groupName, Double itemCost, Double itemProfit, LocalDateTime createTime, LocalDateTime lastUpdateTime, String status, String video) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.groupName = groupName;
        this.itemCost = itemCost;
        this.itemProfit = itemProfit;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
        this.status = status;
        this.video = video;
    }

    public ProjectAndVideoVo(Long id, String title, String desc, String type, Double itemCost, Double itemProfit, LocalDateTime createTime, LocalDateTime lastUpdateTime, String status, String video) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.itemCost = itemCost;
        this.itemProfit = itemProfit;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
        this.status = status;
        this.video = video;
    }

    public ProjectAndVideoVo(String title, String desc, String type, Double itemCost, Double itemProfit, String video) {
        this.title = title;
        this.desc = desc;
        this.type = type;
        this.itemCost = itemCost;
        this.itemProfit = itemProfit;
        this.video = video;
    }

    public ProjectAndVideoVo(Long id, String desc, Double itemCost, Double itemProfit, String video) {
        this.id = id;
        this.desc = desc;
        this.itemCost = itemCost;
        this.itemProfit = itemProfit;
        this.video = video;
    }

    public ProjectAndVideoVo(Long id, String desc, Double itemCost, Double itemProfit, String video,LocalDateTime lastUpdateTime) {
        this.id = id;
        this.desc = desc;
        this.itemCost = itemCost;
        this.itemProfit = itemProfit;
        this.video = video;
        this.lastUpdateTime=lastUpdateTime;
    }

    public ProjectAndVideoVo() {
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
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "ProjectAndVideoVo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", type='" + type + '\'' +
                ", groupName='" + groupName + '\'' +
                ", itemCost=" + itemCost +
                ", itemProfit=" + itemProfit +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", status='" + status + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
