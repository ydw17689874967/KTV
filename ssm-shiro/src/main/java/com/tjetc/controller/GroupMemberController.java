package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.vo.MemberInfoVo;
import com.tjetc.service.GroupMemberService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/lookGroup")
public class GroupMemberController {
    @Autowired
    private GroupMemberService groupMemberService;

    @RequestMapping("/groupMember")
    @ResponseBody
    public JsonResult selectAllMember(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Long id = groupMemberService.selectGroupId(username);
        System.out.println("id = " + id);
        List<MemberInfoVo> memberInfoVos = groupMemberService.selectAllMembers(id);
        return new JsonResult(0,"操作成功",memberInfoVos);
    }

    @RequestMapping("/task")
    @ResponseBody
    public JsonResult selectTaskState(@RequestParam("id") Long id){
        System.out.println("id = " + id);
        /*String username = (String) SecurityUtils.getSubject().getPrincipal();
        Long sid = groupMemberService.selectGroupId(username);*/
        List<MemberInfoVo> memberInfoVos = groupMemberService.selectTaskState(id);
        return new JsonResult(0,"操作成功",memberInfoVos);
    }
}
