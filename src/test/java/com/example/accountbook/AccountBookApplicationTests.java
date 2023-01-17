package com.example.accountbook;

import com.example.accountbook.Utils.AppContextUtils;
import com.example.accountbook.dao.UserDao;
import com.example.accountbook.pojo.*;
import com.example.accountbook.service.DisburseService;
import com.example.accountbook.service.IncomeService;
import com.example.accountbook.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@SpringBootTest
class AccountBookApplicationTests {

    @Autowired
    IncomeService incomeService;

    @Autowired
    DisburseService disburseService;

    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        System.out.println(userDao.getUserAccountInfo(1));
    }

}
