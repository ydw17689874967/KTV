package com.tjetc.service;

import com.tjetc.entity.Video;

public interface VideoService {
    Boolean addVideo(Video video);
    Video findByPid(Long pid);
}
