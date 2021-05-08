package com.example.travelfeemanagews.controller;


import com.example.travelfeemanagews.entity.Budget;
import com.example.travelfeemanagews.entity.Spend;
import com.example.travelfeemanagews.service.BudgetService;
import com.example.travelfeemanagews.service.SpendService;
import com.example.travelfeemanagews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin //解决跨域问题
@RestController
@RequestMapping(value = "/api/trip")
public class SpendController {

    @Autowired
    private SpendService spendService;

    @Autowired
    private UserService userService;

    @Autowired
    private BudgetService budgetService;

    @RequestMapping(value = "/add/budget")
    public ModelMap addBudget(@RequestBody Map<String,Object> param){
        ModelMap result=new ModelMap();
        ModelMap meta=new ModelMap();
        if(param.get("traffic")!=null && param.get("food")!=null && param.get("accommodation")!=null && param.get("play")!=null && param.get("shopping")!=null && param.get("other")!=null && param.get("userid")!=null) {
            Budget budget =new Budget();
            float traffic = Float.valueOf(param.get("traffic").toString());
            float food = Float.valueOf(param.get("food").toString());
            float accommodation = Float.valueOf(param.get("accommodation").toString());
            float play = Float.valueOf(param.get("play").toString());
            float shopping = Float.valueOf(param.get("shopping").toString());
            float other = Float.valueOf(param.get("other").toString());
            float total= traffic + food + accommodation + play + shopping + other;
            Integer userid=Integer.valueOf(param.get("userid").toString());

            if(!userService.isExistById(userid)){
                meta.addAttribute("msg","This user does not exist");
                meta.addAttribute("status",404);
                result.addAttribute("meta",meta);
                return result;
            }
            else {
                budget.setTraffic(traffic);
                budget.setFood(food);
                budget.setAccommodation(accommodation);
                budget.setPlay(play);
                budget.setShopping(shopping);
                budget.setOther(other);
                budget.setTotal(total);

                budget.setUserid(userid);

                try {
                    budgetService.addBudget(budget);
                } catch (Exception e) {
                    e.printStackTrace();
                    meta.addAttribute("msg", "Data insertion exception");
                    meta.addAttribute("status",500);
                    result.addAttribute("meta",meta);
                    return result;
                }
                meta.addAttribute("msg", "success");
                meta.addAttribute("status",200);
            }
        }
        else {
            meta.addAttribute("msg","Some parameters are empty");
            meta.addAttribute("status",400);
        }
        result.addAttribute("meta",meta);
        return result;
    }

    @RequestMapping(value = "/add/spend")
    public ModelMap addItemSpend(@RequestBody Map<String,Object> param){
        ModelMap result=new ModelMap();
        ModelMap meta=new ModelMap();
        if(param.get("matter")!=null && param.get("spend")!=null && param.get("budgetid")!=null){
            Spend spendItem =new Spend();
            String matter = param.get("matter").toString();
            Float spend = Float.valueOf(param.get("spend").toString());
            Integer budgetid = Integer.valueOf(param.get("budgetid").toString());

            if(!budgetService.isExistById(budgetid)){
                meta.addAttribute("msg","This budget does not exist");
                meta.addAttribute("status",404);
                result.addAttribute("meta",meta);
                return result;
            }
            else {
                spendItem.setMatter(matter);
                spendItem.setSpend(spend);
                spendItem.setBudgetid(budgetid);
                try {
                    spendService.addSpendItem(spendItem);
                } catch (Exception e) {
                    e.printStackTrace();
                    meta.addAttribute("msg", "Data insertion exception");
                    meta.addAttribute("status",500);
                    result.addAttribute("meta",meta);
                    return result;
                }
                meta.addAttribute("msg", "success");
                meta.addAttribute("status",200);
            }
        }
        else {
            meta.addAttribute("msg","Some parameters are empty");
            meta.addAttribute("status",400);
        }
        result.addAttribute("meta",meta);
        return result;
    }

    @RequestMapping(value = "/get/budget")
    public ModelMap getBudgetByUserid(@RequestParam Integer userid){
        ModelMap result=new ModelMap();
        ModelMap meta=new ModelMap();
        List<Budget> budgetList;//存放该用户所有预算
        try {
            budgetList = budgetService.getBudgetByUserid(userid);
        } catch (Exception e) {
            e.printStackTrace();
            meta.addAttribute("msg", "Data selection exception");
            meta.addAttribute("status",500);
            result.addAttribute("meta",meta);
            return result;
        }
        meta.addAttribute("msg","success");
        meta.addAttribute("status",200);
        ModelMap data=new ModelMap();
        data.addAttribute("budgetList", budgetList);
        result.addAttribute("meta",meta);
        result.addAttribute("meta",data);
        return result;
    }

    @RequestMapping(value = "/get/spend")
    public ModelMap getSpendByBudgetid(@RequestParam Integer budgetid){
        ModelMap result=new ModelMap();
        ModelMap meta=new ModelMap();
        List<Spend> spendList;//存放该预算对应所有开支
        try {
            spendList = spendService.getSpendByBudgetid(budgetid);
        } catch (Exception e) {
            e.printStackTrace();
            meta.addAttribute("msg", "Data selection exception");
            meta.addAttribute("status",500);
            result.addAttribute("meta",meta);
            return result;
        }
        meta.addAttribute("msg","success");
        meta.addAttribute("status",200);
        ModelMap data=new ModelMap();
        data.addAttribute("spendList", spendList);
        result.addAttribute("meta",meta);
        result.addAttribute("meta",data);

        return result;
    }
}
