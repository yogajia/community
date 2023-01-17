package com.example.accountbook.controller;

import com.example.accountbook.pojo.Role;
import com.example.accountbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/getUserList")
    public List<Map<String,Object>> getUserandRoleList(){
        List<Map<String,Integer>> userlist=userService.getAllUserNameAndId();
        System.out.println("userlist========>"+userlist);
        List<Map<String,Object>> resultList=new ArrayList<>();
        for (Map<String,Integer> map:userlist){
            System.out.println("map=======>"+map);
            Map<String,Object> usermap=new HashMap<>();
            usermap.put("username",map.get("username"));
            int id=map.get("id");
            List<Role>roles=userService.getRolesById(id);
            usermap.put("roles",roles);
            resultList.add(usermap);
        }
        return resultList;
    }
}
