package com.example.accountbook.dao;

import com.example.accountbook.pojo.Disburse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DisburseMapper {
    int addDisburse(Disburse disburse);
    List<Disburse> queryDisburseById(int id);
    List<Disburse> queryDisburseByType(int id,String type);
    double getDaysAmountsByType(int id,String type);
    double getWeeksAmountByType(int id,String type);
    double getMonthsAmountByType(int id,String type);
}
