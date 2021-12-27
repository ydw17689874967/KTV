package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Admin;
import com.tjetc.model.view.AdminVoView;

import com.tjetc.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/info")
    @ResponseBody
    public JsonResult info() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        List<String> permissionNames = adminService.findPermissionNamesByUsername(username);
        AdminVoView adminVoView = new AdminVoView(username, permissionNames);
        return new JsonResult(0, "", adminVoView);
    }

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam(value = "rememberMe", required = false, defaultValue = "false") Boolean rememberMe) {
        //实例化UsernamepasswordToken对象，两个参数 ：username  password
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //token对象设置rememberMe
        token.setRememberMe(rememberMe);
        //校验用户名和密码 SecurityUtils.getSubject().login(),通过异常来显示用户名错误或者密码错误
        try {
            //检验登录
            SecurityUtils.getSubject().login(token);
            //返回登录成功
            return new JsonResult(0, "登录成功", null);
        } catch (UnknownAccountException e) {
            return new JsonResult(1, "用户名错误", null);
        } catch (IncorrectCredentialsException e) {
            return new JsonResult(1, "密码错误", null);
        }
    }

    @RequestMapping("/logout")
    @ResponseBody
    public JsonResult logout() {
        //登出
        SecurityUtils.getSubject().logout();
        return new JsonResult(0, "登出成功", null);
    }

    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list() {
        List<Admin> admins = adminService.findAll();
        return new JsonResult(0, "", admins);
    }
}
