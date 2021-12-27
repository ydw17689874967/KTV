package com.tjetc.service;

import com.tjetc.entity.vo.MyTaskVo;

import java.util.List;

/**
 * @author solang
 * @date 2021-12-27 10:06
 */
public interface MyTaskService {
    Long selectId(String username);

    List<MyTaskVo> selectMyTasks(Long id);

    Boolean updateState(Long id);

}
