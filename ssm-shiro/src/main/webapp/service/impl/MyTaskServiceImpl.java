package com.tjetc.service.impl;

import com.tjetc.dao.MyTaskMapper;
import com.tjetc.entity.vo.MyTaskVo;
import com.tjetc.service.MyTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyTaskServiceImpl implements MyTaskService {
    @Autowired
    private MyTaskMapper myTaskMapper;
    @Override
    public List<MyTaskVo> selectMyTasks(Long id) {
        return myTaskMapper.selectMyTasks(id);
    }

    @Override
    public Long selectId(String username) {
        return myTaskMapper.selectIdByName(username);
    }

    @Override
    public List<MyTaskVo> selectMyUnTasks() {
        return myTaskMapper.selectMyUnTasks();
    }

    @Override
    public Boolean updateState(Long id) {
        Long xx = myTaskMapper.updateState(id);
        return xx>0;
    }
}
