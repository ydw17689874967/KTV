package com.tjetc.service;

import com.tjetc.entity.Video;

/**
 * @author solang
 * @date 2021-12-27 10:08
 */
public interface VideoService {
    Boolean addVideo(Video video);

    Video findByPid(Long pid);


}
