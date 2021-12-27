package com.tjetc.service;

import com.tjetc.entity.vo.MyTaskVo;

import java.util.List;

public interface MyTaskService {
    List<MyTaskVo> selectMyTasks(Long id);

    Long selectId(String username);

    List<MyTaskVo> selectMyUnTasks();

    Boolean updateState(Long id);
}
