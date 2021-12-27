package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.vo.MyTaskVo;
import com.tjetc.service.MyTaskService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/my")
public class MyTaskController {
    @Autowired
    private MyTaskService myTaskService;

    @RequestMapping("/task")
    @ResponseBody
    public JsonResult selectTask(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Long id = myTaskService.selectId(username);
        System.out.println("id = " + id);
        List<MyTaskVo> myTaskVos = myTaskService.selectMyTasks(id);
        return new JsonResult(0,"操作成功",myTaskVos);
    }

    @RequestMapping("/complete")
    @ResponseBody
    public JsonResult updateState(@RequestParam("id")Long id){
        Boolean bl = myTaskService.updateState(id);
        if(bl){
            return new JsonResult(0,"提交成功",bl);
        }
        return new JsonResult(1,"操作失败",bl);
    }

}
