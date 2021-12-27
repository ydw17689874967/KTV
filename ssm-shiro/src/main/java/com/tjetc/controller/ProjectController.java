package com.tjetc.controller;

import com.github.pagehelper.PageInfo;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.Project;
import com.tjetc.entity.Video;
import com.tjetc.entity.vo.ProjectInfoVo;
import com.tjetc.service.ProjectService;
import com.tjetc.service.VideoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private VideoService videoService;

    @RequestMapping("/apply")
    @ResponseBody
    @RequiresPermissions(value = {"op:submit"})
    public JsonResult applyProject(@RequestParam("title") String title, @RequestParam("desc") String desc,
                                   @RequestParam("type") String type, @RequestParam("itemCost") String itemCost,
                                   @RequestParam("itemProfit") String itemProfit) {

        Boolean insertApplyProject = projectService.addApplyProject(new Project(title, desc, type, Double.valueOf(itemCost), Double.valueOf(itemProfit)));
        if (insertApplyProject) {
            return new JsonResult(0, "申请成功", null);
        } else {
            return new JsonResult(1, "插入失败", null);
        }

    }

    @RequestMapping("/getList")
    @ResponseBody
    @RequiresPermissions(value = {"op:lookProject"})
    public JsonResult getAllProject(@RequestParam(name = "pageSize") Integer pageSize, @RequestParam(name = "pageNum") Integer pageNum) {
        PageInfo pageInfo = projectService.selectProjectAll(Integer.valueOf(pageSize), Integer.valueOf(pageNum));
        return new JsonResult(0, "查询成功", pageInfo);
    }

    @RequestMapping("getDetail")
    @ResponseBody
    @RequiresPermissions(value = {"op:lookProject"})
    public JsonResult getDetailById(Long id) {
        ProjectInfoVo projectInfoVo = projectService.selectProjectById(id);
        return new JsonResult(0, "查询成功", projectInfoVo);
    }


    @RequestMapping("updateProject")
    @ResponseBody
    @RequiresPermissions(value = {"op:lookProject"})
    public JsonResult updateProject(Long id) {
        Integer integer = projectService.updateProjectStateById(id);
        if (integer > 0) {
            return new JsonResult(0, "修改成功", null);
        }
        return new JsonResult(0, "修改失败", null);
    }
    @RequestMapping("/video")
    @ResponseBody
    public JsonResult uploadVideo(@RequestParam("videoPath") String videoPath,
                                  @RequestParam("pid") Long pid){
        System.out.println(videoPath);
        System.out.println(pid);
        Video video = new Video(videoPath, pid);
        Boolean b = videoService.addVideo(video);
        if (b){
            return new JsonResult(0,"上传视频成功",b);
        }else {
            return new JsonResult(1,"上传视频失败",b);
        }

    }

}
