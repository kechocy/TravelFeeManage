package com.example.travelfeemanagews.controller;


import com.example.travelfeemanagews.entity.User;
import com.example.travelfeemanagews.service.UserService;
import com.example.travelfeemanagews.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@CrossOrigin //解决跨域问题
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login")
    public ModelMap addBudget(@RequestBody Map<String,Object> param){
        ModelMap result=new ModelMap();
        ModelMap meta=new ModelMap();
        if(param.get("username")!=null && param.get("password")!=null) {
            User user=new User();
            String username = param.get("username").toString();
            String password = param.get("password").toString();
            user.setUsername(username);
            user.setPassword(password);
            try {
                if(userService.login(user)!=null){
                    Integer userid=userService.login(user);
                    String token=TokenUtils.createToken(username,password);
                    ModelMap data=new ModelMap();
                    meta.addAttribute("msg","Login Success");
                    meta.addAttribute("status",200);
                    data.addAttribute("userid",userid);
                    data.addAttribute("username",username);
                    data.addAttribute("token",token);
                    result.addAttribute("data",data);
                }
                else {
                    meta.addAttribute("msg","Login Failure");
                    meta.addAttribute("status",404);
                }
            } catch (Exception e) {
                e.printStackTrace();
                meta.addAttribute("msg", "DataBase Error");
                meta.addAttribute("status",500);
                result.addAttribute("meta",meta);
                return result;
            }
        }
        else {
            meta.addAttribute("msg","Some parameters are empty");
            meta.addAttribute("status",400);
        }
        result.addAttribute("meta",meta);
        return result;
    }


}
