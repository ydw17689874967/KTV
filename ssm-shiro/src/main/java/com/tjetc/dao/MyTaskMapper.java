package com.tjetc.dao;

import com.tjetc.entity.vo.MyTaskVo;

import java.util.List;

public interface MyTaskMapper {
    List<MyTaskVo> selectMyTasks(Long id);

    Long selectIdByName(String username);

    List<MyTaskVo> selectMyUnTasks();

    //改变值 传的是任务值
    Long  updateState(Long id);
}
