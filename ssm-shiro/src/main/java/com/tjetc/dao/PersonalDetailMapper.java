package com.tjetc.dao;

import com.tjetc.entity.PersonalDetail;

public interface PersonalDetailMapper {
    //通过id查询用户信息
    PersonalDetail selectUserInfo(String username);

    //通过用户名修改密码
    int updatePassword(PersonalDetail personalDetail);
}
