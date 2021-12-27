package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.vo.ProjectAndVideoVo;
import com.tjetc.service.ProjectAndVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/project-video")
public class ProjectAndVideoController {
    @Autowired
    private ProjectAndVideoService projectAndVideoService;

    @RequestMapping("/getProDetail")
    @ResponseBody
    public JsonResult selectOneProject(@RequestParam("id")Long id ){
        ProjectAndVideoVo projectAndVideoVo = projectAndVideoService.selectOneProject(id);

        return new JsonResult(0,"操作成功",projectAndVideoVo);
    }

    @RequestMapping("/updateSome")
    @ResponseBody
    public JsonResult updateSomeInfo( @RequestParam("id")Long id,@RequestParam("desc") String desc,
                                      @RequestParam("itemCost") String itemCost, @RequestParam("itemProfit") String itemProfit,
                                      @RequestParam("video")String video){
        ProjectAndVideoVo pV = new ProjectAndVideoVo(id,desc,Double.valueOf(itemCost),Double.valueOf(itemProfit),video, LocalDateTime.now());
        Long a = projectAndVideoService.updateProject(pV);
        Long b = projectAndVideoService.updateVideo(pV);
        Long c = projectAndVideoService.updateState(id);

        return new JsonResult(0,"操作成功",true);


    }
}
