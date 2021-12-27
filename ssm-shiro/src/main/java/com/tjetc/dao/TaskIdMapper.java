package com.tjetc.dao;

import com.tjetc.entity.TaskId;
import java.util.List;

public interface TaskIdMapper {
    int insert(TaskId record);

    List<TaskId> selectAll();
}