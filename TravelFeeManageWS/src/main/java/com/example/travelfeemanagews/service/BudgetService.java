package com.example.travelfeemanagews.service;


import com.example.travelfeemanagews.entity.Budget;

import java.util.List;

public interface BudgetService {

    boolean isExistById(Integer id);

    void addBudget(Budget budget);

    List<Budget> getBudgetByUserid(Integer userid);

}
