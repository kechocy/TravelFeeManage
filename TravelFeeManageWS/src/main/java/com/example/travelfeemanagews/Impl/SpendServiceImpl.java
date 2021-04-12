package com.example.travelfeemanagews.Impl;

import com.example.travelfeemanagews.mapper.SpendMapper;
import com.example.travelfeemanagews.entity.Spend;
import com.example.travelfeemanagews.service.SpendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpendServiceImpl implements SpendService {

    @Autowired
    SpendMapper spendMapper;

    @Override
    public void addSpendItem(Spend spend) {
        spendMapper.addSpendItem(spend);
    }

    @Override
    public List<Spend> getSpendByBudgetid(Integer budgetid){
        return spendMapper.getSpendByBudgetid(budgetid);
    }
}
