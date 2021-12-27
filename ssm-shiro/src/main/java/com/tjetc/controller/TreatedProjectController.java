package com.tjetc.controller;

import com.github.pagehelper.PageInfo;
import com.tjetc.common.JsonResult;
import com.tjetc.common.RequestRoleIdAndList;
import com.tjetc.service.TreatedProjectService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/treated")
public class TreatedProjectController {
    @Autowired
    private TreatedProjectService treatedProjectService;
    @RequestMapping("/project")
    @ResponseBody
    public JsonResult findByStatus(@RequestParam("key") String key,
                                   @RequestParam("pageNo") Integer pageNo,
                                   @RequestParam("pageSize") Integer pageSize){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Integer intKey = Integer.valueOf(key);
        Long roleId = treatedProjectService.findRoleId(username);

        PageInfo treatedProject = treatedProjectService.findTreatedProject(username, intKey,pageNo,pageSize);
        RequestRoleIdAndList requestData = new RequestRoleIdAndList(roleId, treatedProject);

        JsonResult projectJson = new JsonResult(0, "根据状态查询未处理的项目成功", requestData);
        return projectJson;
    }
}
