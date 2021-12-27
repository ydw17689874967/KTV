package com.tjetc.controller;

import com.github.pagehelper.PageInfo;
import com.tjetc.common.JsonResult;
import com.tjetc.common.RequestRoleIdAndList;
import com.tjetc.entity.Video;
import com.tjetc.service.UntreatedProjectService;
import com.tjetc.service.VideoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/untreated")
public class UntreatedProjectController {
    @Autowired
    private UntreatedProjectService untreatedProjectService;
    @Autowired
    private VideoService videoService;
    @RequestMapping("/project")
    @ResponseBody
    public JsonResult findByStatus(@RequestParam("key") String key,
                                   @RequestParam("pageNo") Integer pageNo,
                                   @RequestParam("pageSize") Integer pageSize){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Integer intKey = Integer.valueOf(key);
        Long roleId = untreatedProjectService.findRoleId(username);
        PageInfo untreatedProject = untreatedProjectService.findUntreatedProject(username, intKey,pageNo,pageSize);
        RequestRoleIdAndList requestData = new RequestRoleIdAndList(roleId, untreatedProject);
        return new JsonResult(0,"根据状态查询未处理项目成功",requestData);
    }


    @ResponseBody
    @RequestMapping("/pass/state")
    public JsonResult passProjectStatus(@RequestParam("key") String key,
                                        @RequestParam("pid") Long pid){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Integer intKey = Integer.valueOf(key);
        Boolean success = untreatedProjectService.updatePassProjectState(username, intKey, pid);
        untreatedProjectService.updateProjectLastUpdateTime(LocalDateTime.now(),pid);
        JsonResult jsonResult = null;
        if (success){
            jsonResult = new JsonResult(0, "通过该项目改变状态成功", success);
        }else {
            jsonResult = new JsonResult(0, "通过该项目改变状态失败", success);
        }
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("/refusal/state")
    public JsonResult refusalProjectStatus(@RequestParam("key") String key,
                                        @RequestParam("pid") Long pid){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Integer intKey = Integer.valueOf(key);
        Boolean success = untreatedProjectService.updateRefusalProjectState(username, intKey, pid);
        untreatedProjectService.updateProjectLastUpdateTime(LocalDateTime.now(),pid);
        JsonResult jsonResult = null;
        if (success){
            jsonResult = new JsonResult(0, "不通过该项目改变状态成功", success);
        }else {
            jsonResult = new JsonResult(0, "不通过该项目改变状态失败", success);
        }
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("/video")
    public JsonResult selectVideo(@RequestParam("pid") Long pid){
        Video video = videoService.findByPid(pid);
        return new JsonResult(0,"查询成功",video);
    }
}
