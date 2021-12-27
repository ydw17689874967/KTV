package com.tjetc.dao;

import com.tjetc.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Video record);

    Video selectByPrimaryKey(Long id);

    List<Video> selectAll();

    int updateByPrimaryKey(Video record);

    int insertVideo(Video video);
    Video selectByPid(Long pid);
    Long selectPid(Long pid);
    int updateVideo(@Param("video") String video,@Param("pid") Long pid);
}



