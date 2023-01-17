package com.example.accountbook.config;

import com.example.accountbook.Utils.JWTUtils;
import com.example.accountbook.pojo.SysUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String,Object>map=new HashMap<>();
        map.put("msg","登录成功");
        map.put("code",200);
        response.setContentType("application/json;charset=utf-8");
        SysUser user=(SysUser) authentication.getPrincipal();
        String jwt= JWTUtils.generateToken(user.getUsername());
        response.setHeader(JWTUtils.getHeader(),jwt);
        String s=new ObjectMapper().writeValueAsString(map);
        response.getWriter().println(s);
    }
}
