package com.example.accountbook.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.util.Date;


public class JWTUtils {
    private static long expire=604800;
    private static String header="authorization";
    private static String secret="zhangcy";

    public static String getHeader(){
        return header;
    }
    public static String getSecret(){
        return secret;
    }
    public static String generateToken(String username){
        Date nowdate=new Date();
        Date expiredate=new Date(nowdate.getTime()+1000*expire);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(username)
                .setIssuedAt(nowdate)
                .setExpiration(expiredate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public static Claims getClaimByToken(String jwt){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static boolean isJWTExpired(Claims claims){
        return claims.getExpiration().before(new Date());
    }
}
