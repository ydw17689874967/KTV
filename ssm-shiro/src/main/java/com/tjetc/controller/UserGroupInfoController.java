package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Task;
import com.tjetc.entity.vo.UserGroupInfoVo;
import com.tjetc.service.UserGroupInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/userGroup")
public class UserGroupInfoController {
    @Autowired
    private UserGroupInfoService userGroupInfoService;


    @RequiresPermissions("op:submit")
    @ResponseBody
    @RequestMapping("/userInfo")
    public JsonResult getUserInfoOfGroup() {
        List<UserGroupInfoVo> userinfos = userGroupInfoService.getUserInfoOfGroup();
        return new JsonResult(0, "查询成功", userinfos);
    }


    @RequestMapping("/addTask")
    @RequiresPermissions("op:submit")
    @ResponseBody
    public JsonResult addTaskToUser(@RequestParam(name = "userId") String userId,
                                    @RequestParam(name = "context") String context,
                                    @RequestParam(name = "projectId") String projectId) {

        Task task = new Task(context, Long.valueOf(projectId), 0);
        Boolean aBoolean = userGroupInfoService.addTask(task, Long.valueOf(userId));
        return new JsonResult(0, "派发成功", null);
    }
}
