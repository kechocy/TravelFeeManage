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
@RequestMapping(value = "/api/train")
public class TrainController {
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;

    private ModelMap getRequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(DEF_CONN_TIMEOUT);
        con.setReadTimeout(DEF_READ_TIMEOUT);
        con.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        ModelMap result=new ModelMap();
        result.put("response",response);
        return result;
    }

    @RequestMapping(value = "/getByCityName")
    public ModelMap getByCityName(@RequestParam String startCity, @RequestParam String endCity, @RequestParam String year, @RequestParam String mon, @RequestParam String day) throws IOException {
        String url="https://api.kinlon.work/12306/?startcity="+java.net.URLEncoder.encode(startCity,"utf-8")+"&endcity="+java.net.URLEncoder.encode(endCity,"utf-8")+"&year="+year+"&mon="+mon+"&day="+day;
        return getRequest(url);
    }


}

        
