package com.tjetc.dao;

import com.tjetc.entity.Task;
import com.tjetc.entity.vo.UserGroupInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserGroupInfoMapper {

    /**
     * 根据小组组长的username获取小组成员信息
     *
     * @param username
     * @return
     */
    List<UserGroupInfoVo> selectGroupUserInfoByXzzz(String username);



    /**
     * 增加任务
     */
    Integer addTask(Task task);

    /**
     * 增加task_id表
     */
    Integer addTaskAndId(@Param("taskId") Long taskId, @Param("userId") Long userId);

}
