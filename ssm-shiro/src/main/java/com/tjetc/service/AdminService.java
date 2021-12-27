package com.tjetc.service;

import com.tjetc.entity.Admin;

import java.util.List;

/**
 * @author solang
 * @date 2021-12-27 9:50
 */
public interface AdminService {
    List<String> findPermissionNamesByUsername(String username);

    List<Admin> findAll();
}
