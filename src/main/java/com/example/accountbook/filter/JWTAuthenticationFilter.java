package com.example.accountbook.filter;

import com.example.accountbook.Utils.AppContextUtils;
import com.example.accountbook.Utils.JWTUtils;
import com.example.accountbook.pojo.SysUser;
import com.example.accountbook.service.UserDetailService;
import com.example.accountbook.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTAuthenticationFilter extends BasicAuthenticationFilter {



    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt=request.getHeader(JWTUtils.getHeader());
        System.out.println("==============》调用JWTAuthenticationFilter jwt="+jwt);
        if(ObjectUtils.isEmpty(jwt)){
            chain.doFilter(request,response);
            return;
        }
        UserService userService= (UserService) AppContextUtils.getBean("userServiceImp");
        Claims claims=JWTUtils.getClaimByToken(jwt);
        if(claims==null) throw new JwtException("token 异常");
        if(JWTUtils.isJWTExpired(claims)) throw new JwtException("token 过期了");
        String username=claims.getSubject();
        SysUser user=userService.getUserByName(username);
        if(!ObjectUtils.isEmpty(user)){
            user.setRoles(userService.getRolesById(user.getId()));
            UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            System.out.println("=========================>执行JWTAuthenticationFilter得到用户"+user.toString());
            chain.doFilter(request,response);
        }
    }
}
