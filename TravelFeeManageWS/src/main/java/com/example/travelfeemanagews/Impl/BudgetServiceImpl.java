package com.example.travelfeemanagews.Impl;

import com.example.travelfeemanagews.entity.Budget;
import com.example.travelfeemanagews.mapper.BudgetMapper;
import com.example.travelfeemanagews.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    BudgetMapper budgetMapper;

    @Override
    public boolean isExistById(Integer id) {
        return budgetMapper.isExistById(id);
    }

    @Override
    public void addBudget(Budget budget) {
        budgetMapper.addBudget(budget);
    }

    @Override
    public List<Budget> getBudgetByUserid(Integer userid) {
        return budgetMapper.getBudgetByUserid(userid);
    }

}
