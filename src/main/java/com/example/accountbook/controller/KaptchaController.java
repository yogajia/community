package com.example.accountbook.controller;

import com.example.accountbook.Utils.RedisUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class KaptchaController {
    private final Producer producer;

    @Autowired
    public KaptchaController(Producer producer){
        this.producer=producer;
    }

    @RequestMapping("/getVCpng")
    @ResponseBody
    public String getVerifyCode(HttpSession session)throws IOException {
        String code=producer.createText();
        System.out.println("验证码 "+code);
        String key= UUID.randomUUID().toString();
        key="KAPTCHA"+key;
        RedisUtils.Stringput(key,code);
        RedisUtils.setExpire(key,300);
        BufferedImage bufferedImage=producer.createImage(code);
        FastByteArrayOutputStream fastByteArrayOutputStream=new FastByteArrayOutputStream();
        ImageIO.write(bufferedImage,"png",fastByteArrayOutputStream);
        Map<String,Object>resultmap=new HashMap<>();
        resultmap.put("key",key);
        resultmap.put("code",Base64Utils.encodeToString(fastByteArrayOutputStream.toByteArray()));
        return new ObjectMapper().writeValueAsString(resultmap);
    }
}
