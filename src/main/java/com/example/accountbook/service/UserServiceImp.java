package com.example.accountbook.service;

import com.example.accountbook.dao.RoleDao;
import com.example.accountbook.dao.UserDao;
import com.example.accountbook.pojo.Role;
import com.example.accountbook.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Override
    public int addCommanUser(String username, String password) {
        int result=userDao.addCommanUser(username,password);
        int uid=userDao.getUserByName(username).getId();
        int rid= roleDao.getIdByRoleName("ROLE_user");
        userDao.addUser_Role(uid,rid);
        return result;
    }

    @Override
    public SysUser getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public List<Map<String,Integer>> getAllUserNameAndId() {
        return userDao.getAllUserNameAndId();
    }

    @Override
    public List<Role> getRolesById(int id) {
        return userDao.getRolesById(id);
    }
}
