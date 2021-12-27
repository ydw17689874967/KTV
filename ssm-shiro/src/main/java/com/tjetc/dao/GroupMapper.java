package com.tjetc.dao;

import com.tjetc.entity.Group;

import java.util.List;

public interface GroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Group record);

    Group selectByPrimaryKey(Long id);

    List<Group> selectAll();

    int updateByPrimaryKey(Group record);

}