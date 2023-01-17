package com.example.accountbook.service;

import com.example.accountbook.dao.UserDao;
import com.example.accountbook.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserDetailService implements UserDetailsService, UserDetailsPasswordService {

    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user= userDao.getUserByName(username);
        if(ObjectUtils.isEmpty(user)) throw new RuntimeException("用户不存在");
        user.setRoles(userDao.getRolesById(user.getId()));
        return user;
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        int i= userDao.updatePassword(user.getUsername(), newPassword);
        if(i==1) ((SysUser)user).setPassword(newPassword);
        return user;
    }
}
