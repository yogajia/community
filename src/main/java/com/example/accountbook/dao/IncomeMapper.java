package com.example.accountbook.dao;

import com.example.accountbook.pojo.Income;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IncomeMapper {
    int addIncome(Income income);
    List<Income> queryAllIncomById(int id);
    List<Income> queryAllIncomeByType(int id,String type);
    double getDaysAmountsByType(int id,String type);
    double getWeeksAmountByType(int id,String type);
    double getMonthsAmountByType(int id,String type);
}
