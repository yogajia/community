package com.example.accountbook.config;


import com.example.accountbook.filter.JWTAuthenticationFilter;
import com.example.accountbook.filter.MyLoginFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public MyLoginFilter myLoginFilter()throws Exception{
        MyLoginFilter myLoginFilter=new MyLoginFilter();
        myLoginFilter.setFilterProcessesUrl("/doLogin");//指定认证的url
        myLoginFilter.setAuthenticationManager(authenticationManager());
        myLoginFilter.setAuthenticationSuccessHandler(new MyLoginSuccessHandler());
        myLoginFilter.setAuthenticationFailureHandler(new MyLoginFailedHandler());
        return myLoginFilter;
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter=new JWTAuthenticationFilter(authenticationManager());
        return jwtAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/addCommanUser","/getVCpng","/test","/login.html").permitAll()
                .antMatchers("/getUserList").hasRole("admin")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                /*.and()
                .headers().frameOptions().disable()*/
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(((request, response, authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    String s="未认证，请登录认证后再查看内容";
                    String js=new ObjectMapper().writeValueAsString(s);
                    response.getWriter().println(js);
                }))
                .accessDeniedHandler(((request, response, accessDeniedException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    String s="您没有权限访问此内容";
                    String js=new ObjectMapper().writeValueAsString(s);
                    response.getWriter().println(js);
                }))
                .and()
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler(((request, response, authentication) -> {
                    SecurityContextHolder.clearContext();
                    System.out.println("清除contextholder");
                }))
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                //.configurationSource(corsConfigurationSource())
                .and()
                .csrf()
                .disable()
                .cors();
        http.addFilterAt(myLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(jwtAuthenticationFilter(),BasicAuthenticationFilter.class);
    }

    /*@Bean
    CorsConfigurationSource corsConfigurationSource(){

        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:63342"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setMaxAge(600L);
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }*/
}
