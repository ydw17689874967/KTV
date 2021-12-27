package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.vo.GroupManageVo;
import com.tjetc.entity.vo.UserUserInfoVo;
import com.tjetc.service.ManageGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/lookManage")
public class ManageGroupController {
    @Autowired
    private ManageGroupService manageGroupService;

    @RequestMapping("/manageGroup")
    @ResponseBody
    public JsonResult manageGroup(){
        List<GroupManageVo> allGroup = manageGroupService.findAllGroup();
        return new JsonResult(0,"操作成功",allGroup);
    }

    @RequestMapping("/memberInfo")
    @ResponseBody
    public JsonResult selectMemberInfo(@RequestParam("id")Long id){
        List<UserUserInfoVo> userUserInfoVos = manageGroupService.selectMemberInfoByGId(id);
        return new JsonResult(0,"",userUserInfoVos);
    }

}
