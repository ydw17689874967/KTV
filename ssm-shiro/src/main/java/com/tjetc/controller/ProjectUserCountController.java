package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.common.ProjectUserCountVo;
import com.tjetc.service.ProjectCountsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/echarts")
public class ProjectUserCountController {
    @Autowired
    private ProjectCountsService projectCountsService;

    @RequestMapping("/userAndProject")
    @ResponseBody
    @RequiresPermissions(value = {"op:checkProjectCount"})
    public JsonResult selectEchartsData() {
        ProjectUserCountVo projectUserCountVo = projectCountsService.selectChartData();
        return new JsonResult(0, "查询成功", projectUserCountVo);
    }
}
