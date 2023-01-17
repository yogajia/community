package com.example.accountbook.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao {
    int getIdByRoleName(String name);
}
