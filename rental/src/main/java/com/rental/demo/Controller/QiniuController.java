package com.rental.demo.Controller;

import com.rental.demo.Service.LoginService;
import com.rental.demo.Service.QiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class QiniuController {

    @Autowired
    QiniuService qiniuService;

    @GetMapping("/qiniuimg/token")
    public Map<String,String> getUpToken(){
        String uptoken = qiniuService.getUpToken();
        Map<String,String> map = new HashMap<>();
        map.put("uptoken",uptoken);
        return map;
    }
}

