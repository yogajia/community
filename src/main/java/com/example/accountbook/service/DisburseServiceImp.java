package com.example.accountbook.service;

import com.example.accountbook.dao.DisburseMapper;
import com.example.accountbook.pojo.Disburse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisburseServiceImp implements DisburseService{

    @Autowired
    DisburseMapper disburseMapper;

    @Override
    public int addDisburse(Disburse disburse) {
        return disburseMapper.addDisburse(disburse);
    }

    @Override
    public List<Disburse> queryDisburseById(int id) {
        return disburseMapper.queryDisburseById(id);
    }

    @Override
    public List<Disburse> queryDisburseByType(int id, String type) {
        return disburseMapper.queryDisburseByType(id, type);
    }

    @Override
    public double getDaysAmountsByType(int id, String type) {
        return disburseMapper.getDaysAmountsByType(id,type);
    }

    public DisburseServiceImp() {
        super();
    }

    @Override
    public double getWeeksAmountByType(int id, String type) {
        return disburseMapper.getWeeksAmountByType(id, type);
    }

    @Override
    public double getMonthsAmountByType(int id, String type) {
        return disburseMapper.getMonthsAmountByType(id, type);
    }
}
