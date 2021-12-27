package com.tjetc.service;


import com.tjetc.common.ProjectUserCountVo;

public interface ProjectCountsService {
    /**
     * 查询echarts数据显示
     */
    ProjectUserCountVo selectChartData();
}
