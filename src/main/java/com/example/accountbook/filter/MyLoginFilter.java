package com.example.accountbook.filter;

import com.example.accountbook.Exception.kaptchaNotMatchException;
import com.example.accountbook.Utils.RedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Map;

public class MyLoginFilter extends UsernamePasswordAuthenticationFilter {
    public static final String KAPTCHA_KEY="kaptcha";
    private String kaptchaParameter=KAPTCHA_KEY;

    public String getKaptchaParameter(){
        return kaptchaParameter;
    }

    public void setKaptchaParameter(String kaptchaParameter) {
        this.kaptchaParameter = kaptchaParameter;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //判断是否为post请求
        Cookie[] cookies= request.getCookies();
        if(cookies==null) System.out.println("没有cookie");
        if (!request.getMethod().equals("POST")) {
            System.out.println("不是post请求");
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //2.判断是否是 json 格式请求类型
        if(request.getContentType().equalsIgnoreCase("application/json;charset=utf-8")||request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)){
            //System.out.println("是json请求");
            //3.从 json 数据中获取用户输入用户名和密码进行认证
            Map<String,String>userInfo=new ObjectMapper().readValue(request.getInputStream(),Map.class);
            String username=userInfo.get(getUsernameParameter());
            String password=userInfo.get(getPasswordParameter());
            String kaptcha=userInfo.get(getKaptchaParameter());
            String key=userInfo.get("key");
            System.out.println("请求："+kaptcha);
            String session_kaptcha=(String)RedisUtils.getStringvalue(key);
            System.out.println("会话 "+session_kaptcha);
            if(!ObjectUtils.isEmpty(kaptcha)&&!ObjectUtils.isEmpty(session_kaptcha)&&kaptcha.equals(session_kaptcha)){
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
                setDetails(request,authenticationToken);
                return this.getAuthenticationManager().authenticate(authenticationToken);
            }
            else{
                throw new kaptchaNotMatchException("验证码不匹配");
            }
        }
        return super.attemptAuthentication(request,response);
    }
}
