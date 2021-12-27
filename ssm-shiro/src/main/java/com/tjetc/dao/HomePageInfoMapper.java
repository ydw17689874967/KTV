package com.tjetc.dao;

public interface HomePageInfoMapper {

    /**
     * 查询用户角色ID
     * @param username
     * @return
     */
    Long selectUserRoleId(String username);

    /**
     * 根据状态查询项目数量
     * @param state
     * @return
     */
    Long selectCountByState(int state);

    /**
     * 老板查看员工数量
     * @return
     */
    Long selectStaffCount();

    /**
     * 会计查看拨款总金额
     * @return
     */
    Double selectPayForMoney();

    /**
     * 会计查看
     * @return
     */
    Long selectPayCount();

    /**
     * 项目经理查看管理的小组数量
     * @return
     */
    Long selectManageGroupCount();

    /**
     * 组长查看申请的数量
     * @return
     */
    Long selectApplyCount(String username);

    /**
     * 组长或组员查看已完成项目总数
     * @param username
     * @return
     */
    Long selectOkProjectCount(String username);

    /**
     * 组长查看小组成员数量
     * @param username
     * @return
     */
    Long selectGroupStaffCount(String username);

    /**
     * 组员查看未完成任务总数
     * @param username
     * @return
     */
    Long selectNoOkTaskCount(String username);

    /**
     * 组员查看所有任务
     * @param username
     * @return
     */
    Long selectTaskCount(String username);
}
