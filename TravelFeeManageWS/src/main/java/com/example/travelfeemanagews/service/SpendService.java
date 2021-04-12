package com.example.travelfeemanagews.service;

import com.example.travelfeemanagews.entity.Spend;

import java.util.List;

public interface SpendService {

    void addSpendItem(Spend spend);

    List<Spend> getSpendByBudgetid(Integer budgetid);

}
