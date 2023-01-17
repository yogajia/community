package com.example.accountbook.service;

import com.example.accountbook.dao.IncomeMapper;
import com.example.accountbook.pojo.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomServiceImp implements IncomeService{

    @Autowired
    IncomeMapper incomeMapper;

    @Override
    public List<Income> queryIncomeById(int id) {
        return incomeMapper.queryAllIncomById(id);
    }

    @Override
    public int addIncome(Income income) {
        return incomeMapper.addIncome(income);
    }

    @Override
    public List<Income> queryIncomeByType(int id, String type) {
        return incomeMapper.queryAllIncomeByType(id, type);
    }

    public IncomServiceImp() {
        super();
    }

    @Override
    public double getDaysAmountsByType(int id, String type) {
        return incomeMapper.getDaysAmountsByType(id, type);
    }

    @Override
    public double getWeeksAmountByType(int id, String type) {
        return incomeMapper.getWeeksAmountByType(id, type);
    }

    @Override
    public double getMonthsAmountByType(int id, String type) {
        return incomeMapper.getMonthsAmountByType(id, type);
    }
}
