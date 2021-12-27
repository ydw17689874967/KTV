package com.tjetc.dao;

import com.tjetc.entity.vo.ProjectTimeCount;

import java.util.ArrayList;

public interface ProjectCountMapper {

    /**
     * 查找额charts显示的用户数据
     * @return
     */
    ArrayList<ProjectTimeCount> selectUserCounts();

    /**
     * 查找charts显示的project数据
     */
    ArrayList<ProjectTimeCount> selectProjectCounts();


}
