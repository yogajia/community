package com.example.accountbook.service;

import com.example.accountbook.pojo.Role;
import com.example.accountbook.pojo.SysUser;

import java.util.List;
import java.util.Map;

public interface UserService {
    int addCommanUser(String username,String password);
    SysUser getUserByName(String username);
    List<Map<String,Integer>> getAllUserNameAndId();
    List<Role> getRolesById(int id);
}
