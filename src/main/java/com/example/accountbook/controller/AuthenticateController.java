package com.example.accountbook.controller;

import com.example.accountbook.pojo.SysUser;
import com.example.accountbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthenticateController {
    @Autowired
    UserService userService;

    @RequestMapping("/addCommanUser")
    @ResponseBody
    public Map<String, Object> addCommanUser(@RequestBody Map<String,String>nameAndPwd){
        String username=nameAndPwd.get("username");
        String password=nameAndPwd.get("password");
        SysUser user=userService.getUserByName(username);
        Map<String,Object>result=new HashMap<>();
        if(!ObjectUtils.isEmpty(user)){
            result.put("code",500);
            result.put("msg","该用户名已存在，请选用其他用户名");
            return result;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String encode=bCryptPasswordEncoder.encode(password);
        encode="{bcrypt}"+encode;
        userService.addCommanUser(username,encode);
        result.put("code",200);
        result.put("msg","注册成功");
        return result;
    }
}
