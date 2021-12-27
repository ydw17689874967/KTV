package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.dao.HomePageInfoMapper;
import com.tjetc.dao.ProjectMapper;
import com.tjetc.entity.vo.ProjectGroupInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomePageInfoController {
    @Autowired
    HomePageInfoMapper homePageInfoMapper;

    @Autowired
    ProjectMapper projectMapper;

    @RequestMapping("/role")
    @ResponseBody
    public JsonResult findRoleIdByUsername(@RequestParam("username") String username){
        Long roleId = homePageInfoMapper.selectUserRoleId(username);
        return new JsonResult(0,null,roleId);
    }

    @RequestMapping("/info")
    @ResponseBody
    public JsonResult viewBossNeedInfo(@RequestParam("username") String username){
        //根据用户名查询RoleId
        int roleId = homePageInfoMapper.selectUserRoleId(username).intValue();
        switch (roleId){
            case 1:{//首页展示老板所需相关信息
                //老板查看未审批项目
                Long noExCount = homePageInfoMapper.selectCountByState(3);
                //老板查看未审阅项目
                Long noChCount = homePageInfoMapper.selectCountByState(10);
                //老板查看已完成项目
                Long okProjectCount = homePageInfoMapper.selectCountByState(12);
                //老板查看工作员工数量
                Long staffCount = homePageInfoMapper.selectStaffCount();
                Map<String,Long> result = new HashMap<>();
                result.put("noExCount",noExCount);
                result.put("noChCount",noChCount);
                result.put("okProjectCount",okProjectCount);
                result.put("staffCount",staffCount);
                return new JsonResult(0,"老板首页显示",result);
            }
            case 2:{
                //项目经理查看未审批项目
                Long noExCount = homePageInfoMapper.selectCountByState(1);
                //项目经理查看未审阅项目
                Long noChCount = homePageInfoMapper.selectCountByState(8);
                //项目经理查看已完成项目
                Long okProjectCount = homePageInfoMapper.selectCountByState(12);
                //项目经理查看管理小组数量
                Long groupCount = homePageInfoMapper.selectManageGroupCount();
                Map<String,Object> result = new HashMap<>();
                result.put("noExCount",noExCount);
                result.put("noChCount",noChCount);
                result.put("okProjectCount",okProjectCount);
                result.put("groupCount",groupCount);
                return new JsonResult(0,"项目经理首页显示",result);
            }
            case 3:{
                //会计已拨款项目
                Long payFor = homePageInfoMapper.selectPayCount();
                //会计未拨款项目
                Long noPayFor = homePageInfoMapper.selectCountByState(6);
                //会计查看拨款总数额
                Double money = homePageInfoMapper.selectPayForMoney();
                Map<String,Object> result = new HashMap<>();
                result.put("payFor",payFor);
                result.put("noPayFor",noPayFor);
                result.put("money",money);
                return new JsonResult(0,"会计首页显示",result);
            }
            case 4:{
                //申请项目总数
                Long applyCount = homePageInfoMapper.selectApplyCount(username);
                //已完成项目总数
                Long okProjectCount = homePageInfoMapper.selectOkProjectCount(username);
                //组长查看组成员数量
                Long groupStaffCount = homePageInfoMapper.selectGroupStaffCount(username);
                Map<String,Object> result = new HashMap<>();
                result.put("applyCount",applyCount);
                result.put("okProjectCount",okProjectCount);
                result.put("groupStaffCount",groupStaffCount);
                return new JsonResult(0,"组长首页显示",result);
            }
            case 5:{
                //已完成项目总数
                Long okProjectCount = homePageInfoMapper.selectOkProjectCount(username);
                //未完成任务总数
                Long noOkTask = homePageInfoMapper.selectNoOkTaskCount(username);
                //任务总数
                Long taskCount = homePageInfoMapper.selectTaskCount(username);
                Map<String,Object> result = new HashMap<>();
                result.put("okProjectCount",okProjectCount);
                result.put("noOkTask",noOkTask);
                result.put("taskCount",taskCount);
                return new JsonResult(0,"组员首页显示",result);
            }
        }
        return null;
    }

    @RequestMapping("/ok")
    @ResponseBody
    public JsonResult findOkProject(){
        List<ProjectGroupInfo> projectGroupInfos = projectMapper.selectProjectAndGroupByState(12);
        return new JsonResult(0,null,projectGroupInfos);
    }
}
