package com.example.accountbook.service;

import com.example.accountbook.pojo.Disburse;

import java.util.List;

public interface DisburseService {
    int addDisburse(Disburse disburse);
    List<Disburse> queryDisburseById(int id);
    List<Disburse> queryDisburseByType(int id,String type);
    double getDaysAmountsByType(int id,String type);
    double getWeeksAmountByType(int id,String type);
    double getMonthsAmountByType(int id,String type);
}
