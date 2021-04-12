package com.example.travelfeemanagews.mapper;

import com.example.travelfeemanagews.entity.Spend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpendMapper {

    void addSpendItem(Spend spend);

    List<Spend> getSpendByBudgetid(Integer budgetid);
}
