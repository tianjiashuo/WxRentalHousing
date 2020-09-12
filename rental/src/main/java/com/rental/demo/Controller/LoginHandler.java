package com.rental.demo.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rental.demo.Repository.entity.OpenIdJson;
import com.rental.demo.utils.HttpUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
public class LoginHandler {
    private String appID ="wx1a8b5c07794aa546";
    private String appSecret = "831163400081c584967d45afc443eae3";

    @PostMapping("/user/login")
    public String userLogin(@RequestParam("code") String code) throws IOException {
        String result = "";
        try{//请求微信服务器，用code换取openid。HttpUtil是工具类，后面会给出实现，Configure类是小程序配置信息，后面会给出代码
            result = HttpUtil.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            + this.appID + "&secret="
                            + this.appSecret + "&js_code="
                            + code
                            + "&grant_type=authorization_code", null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        OpenIdJson openIdJson = mapper.readValue(result, OpenIdJson.class);
        System.out.println(result.toString());
        System.out.println(openIdJson.getOpenid());
        return result;
    }

}
