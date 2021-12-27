package com.tjetc.service.impl;

import com.tjetc.dao.VideoMapper;
import com.tjetc.entity.Video;
import com.tjetc.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Override
    public Boolean addVideo(Video video) {
        int i = 0;
        Long pid = videoMapper.selectPid(video.getPid());
        if (pid!=null){
            i=videoMapper.updateVideo(video.getVideo(),video.getPid());
        }else {
            i=videoMapper.insertVideo(video);
        }
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Video findByPid(Long pid) {
        return videoMapper.selectByPid(pid);
    }
}
