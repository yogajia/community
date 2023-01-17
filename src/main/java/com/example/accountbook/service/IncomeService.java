package com.example.accountbook.service;

import com.example.accountbook.pojo.Income;

import java.util.List;

public interface IncomeService {
     int addIncome(Income income);
     List<Income> queryIncomeById(int id);
     List<Income> queryIncomeByType(int id,String type);
     double getDaysAmountsByType(int id,String type);
     double getWeeksAmountByType(int id,String type);
     double getMonthsAmountByType(int id,String type);
}
