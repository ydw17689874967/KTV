package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.PersonalDetail;
import com.tjetc.entity.User;
import com.tjetc.service.PersonalDetailService;
import com.tjetc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/personal")
public class PersonalDetailController {
    @Autowired
    private PersonalDetailService personalDetailService;
    @Autowired
    private UserService userService;

    @RequestMapping("/detail")
    @ResponseBody
    public JsonResult detail(){
        //登录的时候存取的信息，通过SecurityUtils.getSubject().getPrincipal()方法可以获得登录的时候存取的数据
        String username1 = (String) SecurityUtils.getSubject().getPrincipal();
        PersonalDetail personalDetail = personalDetailService.findUserDetail(username1);
        //下面注释的是如果数据为数组就要把数据转换成数组，jsonResult数据默认是对象
//        List<PersonalDetail> personalDetails = new ArrayList<>();
//        personalDetails.add(personalDetail);
        return new JsonResult(0,"",personalDetail);
    }

    @RequestMapping("/modify")
    @ResponseBody
    public JsonResult modifyPassword(@RequestParam("newPassword") String newPassword,
                                     @RequestParam("password") String password){
        String username = (String) SecurityUtils.getSubject().getPrincipal(); // 只能获得用户名
        User user = userService.selectByName(username);
        String passwordYuan = user.getPassword();
        if (passwordYuan.equals(password)){
            if (newPassword!=null && newPassword.length()>5){
                PersonalDetail personalDetail = new PersonalDetail(username,newPassword);
                int row = personalDetailService.updatePassw(personalDetail);
                return new JsonResult(0,"修改成功",true);
            }
        }
        return new JsonResult(1,"修改失败，原密码不正确",false);
    }
}
