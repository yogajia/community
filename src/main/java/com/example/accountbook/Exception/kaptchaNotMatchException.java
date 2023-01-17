package com.example.accountbook.Exception;

import org.springframework.security.core.AuthenticationException;

public class kaptchaNotMatchException extends AuthenticationException {
    public kaptchaNotMatchException(String msg){
        super(msg);
    }
    public kaptchaNotMatchException(String msg,Throwable cause){
        super(msg, cause);
    }
}
