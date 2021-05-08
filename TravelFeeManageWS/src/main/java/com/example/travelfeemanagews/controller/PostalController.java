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
@RequestMapping(value = "/api/postal")

public class PostalController {

    @RequestMapping(value = "/getByPlace")
    public ModelMap getByPlace(@RequestParam String place) throws IOException {
        String url = "https://sapi.k780.com/?app=life.postcode&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json&areaname="+place;
        return getRequest(url);
    }
    @RequestMapping(value = "/getByCode")
    public ModelMap getByCode(@RequestParam String code) throws IOException {
        String url = "https://sapi.k780.com/?app=life.postcode&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json&postcode="+code;
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
        ModelMap result=new ModelMap();
        result.put("response",response);
        return result;
    }
}
