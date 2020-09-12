package com.rental.demo.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rental.demo.Repository.entity.OpenIdJson;
import com.rental.demo.Service.AesService;
import com.rental.demo.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class LoginHandler {

    @Autowired
    private AesService aesService;
    @PostMapping("/user/login")
    public Map userLogin(@RequestBody Map<String,String> json) throws IOException {
        return aesService.decodeUserInfo(json.get("encryptedData"),json.get("iv"),json.get("code"));
    }

}
