package com.example.travelfeemanagews.mapper;

import com.example.travelfeemanagews.entity.Budget;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BudgetMapper {

    boolean isExistById(Integer id);

    void addBudget(Budget budget);

    List<Budget> getBudgetByUserid(Integer userid);

}
