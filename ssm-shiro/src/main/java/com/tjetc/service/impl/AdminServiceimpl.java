package com.tjetc.service.impl;

import com.tjetc.dao.AdminMapper;
import com.tjetc.entity.Admin;
import com.tjetc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author solang
 * @date 2021-12-27 9:52
 */
@Service
public class AdminServiceimpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public List<String> findPermissionNamesByUsername(String username) {
        return null;
    }

    @Override
    public List<Admin> findAll() {
        return adminMapper.selectAll();
    }
}
