package com.example.travelfeemanagews.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@CrossOrigin //解决跨域问题
@RestController
@RequestMapping(value = "/api/phone")
public class PhoneController {

    @RequestMapping(value = "/getPlace")
    public ModelMap getByPlace(@RequestParam String number) throws IOException {
        String url = "https://cx.shouji.360.cn/phonearea.php?number="+number;
        return getRequest(url);
    }

    private ModelMap getRequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();
        ModelMap result=new ModelMap();
        result.put("response",response);
        System.out.println(result);
        return result;
    }
}
