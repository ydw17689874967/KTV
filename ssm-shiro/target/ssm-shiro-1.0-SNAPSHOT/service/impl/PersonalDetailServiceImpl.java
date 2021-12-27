package com.tjetc.service.impl;

import com.tjetc.dao.PersonalDetailMapper;
import com.tjetc.entity.PersonalDetail;
import com.tjetc.service.PersonalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalDetailServiceImpl implements PersonalDetailService {
    @Autowired
    private PersonalDetailMapper personalDetailMapper;

    @Override
    public PersonalDetail findUserDetail(String username) {
        return personalDetailMapper.selectUserInfo(username);
    }

    @Override
    public int updatePassw(PersonalDetail personalDetail) {
        return personalDetailMapper.updatePassword(personalDetail);
    }
}
