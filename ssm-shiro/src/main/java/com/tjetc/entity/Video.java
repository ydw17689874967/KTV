package com.tjetc.entity;

public class Video {
    private Long id;

    private String video;

    private Long pid;

    public Video(String video, Long pid) {
        this.video = video;
        this.pid = pid;
    }
    public Video() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

}

