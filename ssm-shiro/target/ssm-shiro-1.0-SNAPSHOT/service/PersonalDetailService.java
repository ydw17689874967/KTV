package com.tjetc.service;

import com.tjetc.entity.PersonalDetail;

public interface PersonalDetailService {
    //根据用户名查找个人信息
    PersonalDetail findUserDetail(String username);

    //根据用户名修改密码
    int updatePassw(PersonalDetail personalDetail);
}
