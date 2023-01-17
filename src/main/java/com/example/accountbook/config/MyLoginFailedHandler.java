package com.example.accountbook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyLoginFailedHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String,Object>map=new HashMap<>();
        map.put("msg","登录失败"+exception.getMessage());
        map.put("code",500);
        response.setContentType("application/json;charset=utf-8");
        String s=new ObjectMapper().writeValueAsString(map);
        response.getWriter().println(s);
    }
}
