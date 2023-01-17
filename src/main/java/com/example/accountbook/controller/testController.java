package com.example.accountbook.controller;

import com.example.accountbook.pojo.SysUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class testController {

    @SneakyThrows
    @RequestMapping("/test")
    public String test(){
        /*Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        SysUser user=(SysUser) authentication.getPrincipal();*/
        Map<String,Object>map=new HashMap<>();
        map.put("msg","hello");
        //map.put("user",user);
        String str=new ObjectMapper().writeValueAsString(map);
        return str;
    }
}
