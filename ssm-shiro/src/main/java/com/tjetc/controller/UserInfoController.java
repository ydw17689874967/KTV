package com.tjetc.controller;

import com.github.pagehelper.PageInfo;
import com.tjetc.common.JsonResult;
import com.tjetc.entity.User;
import com.tjetc.entity.UserInfo;
import com.tjetc.entity.vo.UserUserInfoVo;
import com.tjetc.service.UserInfoService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/view")
@RequiresPermissions(value = {"op:memberManage"},logical = Logical.OR)
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/roleUserInfo")
    @ResponseBody
    public JsonResult findUserInfoByRole(@RequestParam("role") String role,
                                         @RequestParam("page") int page,
                                         @RequestParam("limit") int limit){
        PageInfo<UserUserInfoVo> userInfoByRole = userInfoService.findUserInfoByRole(role, page, limit);
        return new JsonResult(0,"查询成功",userInfoByRole);
    }

    @RequestMapping("/userInfo")
    @ResponseBody
    public JsonResult findUserInfoById(@RequestParam("id") Long id){
        UserUserInfoVo userUserInfoVo = userInfoService.findUserInfoById(id);
        return new JsonResult(0,"查询成功",userUserInfoVo);
    }

    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public JsonResult updateUserInfoById(@RequestParam("id") Long id,
                                         @RequestParam("avatar") String avatar,
                                         @RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         @RequestParam("realName") String realName,
                                         @RequestParam("age") int age,
                                         @RequestParam("sex") int sex,
                                         @RequestParam("email") String email,
                                         @RequestParam("phoneNumber") String phoneNumber){
        User user = new User(id,username,password,LocalDateTime.now());
        UserInfo userInfo = new UserInfo(age,sex,email,avatar,realName,phoneNumber);
        boolean b = userInfoService.updateUserInfo(user, userInfo);
        if (b){
            return new JsonResult(0,"更新成功",null);
        }else{
            return new JsonResult(1,"更改的用户名已注册",null);
        }


    }

    @RequestMapping("/addUserInfo")
    @ResponseBody
    public JsonResult addUserInfo(@RequestParam("avatar") String avatar,
                                         @RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         @RequestParam("realName") String realName,
                                         @RequestParam("age") int age,
                                         @RequestParam("sex") int sex,
                                         @RequestParam("email") String email,
                                         @RequestParam("phoneNumber") String phoneNumber){
        User user = new User(username,password,LocalDateTime.now(),LocalDateTime.now());
        UserInfo userInfo = new UserInfo(age,sex,email,avatar,realName,phoneNumber);
        boolean b = userInfoService.addUserInfo(user, userInfo);
        if (b){
            return new JsonResult(0,"添加成功",null);
        }else{
            return new JsonResult(1,"用户名已被注册",null);
        }
    }


    @RequestMapping("/updateUserState")
    @ResponseBody
    public JsonResult updateUserState(@RequestParam("id") Long id){
        boolean b = userInfoService.updateUserState(id);
        if (b){
            return new JsonResult(0,"删除成功",null);
        }else{
            return new JsonResult(1,"删除失败",null);
        }
    }
}
