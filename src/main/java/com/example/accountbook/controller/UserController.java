package com.example.accountbook.controller;

import com.example.accountbook.pojo.SysUser;
import com.example.accountbook.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @ResponseBody
    @SneakyThrows
    @RequestMapping("/getUsername")
    public String getUsername(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        SysUser sysUser=(SysUser) authentication.getPrincipal();
        return new ObjectMapper().writeValueAsString(sysUser.getUsername());
    }
}
