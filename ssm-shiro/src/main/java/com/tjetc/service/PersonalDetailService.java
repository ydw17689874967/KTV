package com.tjetc.service;

import com.tjetc.entity.PersonalDetail;

/**
 * @author solang
 * @date 2021-12-27 10:07
 */
public interface PersonalDetailService {
    PersonalDetail findUserDetail(String username1);

    int updatePassw(PersonalDetail personalDetail);

}
