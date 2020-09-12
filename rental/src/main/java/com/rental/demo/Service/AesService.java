package com.rental.demo.Service;


import com.google.gson.Gson;
import com.rental.demo.Repository.entity.User;
import com.rental.demo.utils.AesUtil;
import com.rental.demo.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service("aesService")
public class AesService {

    @Autowired
    UserService userService;
    private String appID ="wx1a8b5c07794aa546";
    private String appSecret = "831163400081c584967d45afc443eae3";

    public Map decodeUserInfo(String encryptedData, String iv, String code) {
        Map map = new HashMap();
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }
        String result ="";
        // 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        //发送请求
        try{
            result = HttpUtil.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            + this.appID + "&secret="
                            + this.appSecret + "&js_code="
                            + code
                            + "&grant_type=authorization_code", null);
        }catch (Exception e){
            e.printStackTrace();
        }
        Gson json = new Gson();
        Map<String,Object> temp = json.fromJson(result,HashMap.class);
        //获取会话密钥（session_key）
        String session_key = (String)temp.get("session_key");
        //用户的唯一标识（openid）
        String openid =(String)temp.get("openid");
        //2、对encryptedData加密数据进行AES解密
        try {
            String userInfoString = AesUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");
                Map userInfoJSON = json.fromJson(userInfoString,HashMap.class);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
////                this.id = id;
////                this.head = head;
////                this.nickname = nickname;
////                this.introduction = introduction;
////                this.phone = phone;
////                this.gender = gender;
////                this.idNumber = idNumber;
//                UserBo userbo = new UserBo(userInfoJSON.get("openid"),userInfoJSON.get("avatarUrl")
//                                          ,userInfoJSON.get("nickName"),"",""
//                                          ,userInfoJSON.get("gender"),"");
//                userService.initUserInfo()
                map.put("userInfo", userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }

    public Map decodePhoneNumber(String encryptedData, String iv,String openid){
        String key ="";
        try{
            String phoneInfo = AesUtil.decrypt(encryptedData,key,iv,"UTF-8");
            Gson json = new Gson();
            Map<String,Object> temp = json.fromJson(phoneInfo,HashMap.class);
            return temp;
        }catch(Exception e ){
            e.printStackTrace();
        }
        return null;
    }
}
