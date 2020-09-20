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


    private String appID ="wx1a8b5c07794aa546";
    private String appSecret = "831163400081c584967d45afc443eae3";


    /**
     * 解密用户信息
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
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
        System.out.println("aes--result"+result);
        Gson json = new Gson();
        Map<String,Object> temp = json.fromJson(result,HashMap.class);
        //获取会话密钥（session_key）
        String session_key = (String)temp.get("session_key");
        map.put("session_key",session_key);
        System.out.println("aes--result-session_key"+session_key);
        //用户的唯一标识（openid）
        String openid =(String)temp.get("openid");
        System.out.println("aes--result-openid"+openid);
        //2、对encryptedData加密数据进行AES解密
        try {
            String userInfoString = AesUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            System.out.println("aes--4"+userInfoString);
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");
                Map userInfoJSON = json.fromJson(userInfoString,HashMap.class);
                Map<String,String> userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId").toString());
                userInfo.put("nickName", userInfoJSON.get("nickName").toString());
                userInfo.put("gender", userInfoJSON.get("gender").toString().substring(0,1));
                userInfo.put("city", userInfoJSON.get("city").toString());
                userInfo.put("province", userInfoJSON.get("province").toString());
                userInfo.put("country", userInfoJSON.get("country").toString());
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl").toString());
                System.out.println("aes--5"+userInfo.toString());
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

    /***
     * 解密获得微信用户手机号
     * @param encryptedData
     * @param iv
     * @param key
     * @return
     */
    public Map decodePhoneNumber(String encryptedData, String iv,String key){
        try{
            String phoneInfo = AesUtil.decrypt(encryptedData,key,iv,"UTF-8");
            Gson json = new Gson();
            Map<String,Object> temp = json.fromJson(phoneInfo,HashMap.class);
            System.out.println("getphone2 "+temp.toString());
            return temp;
        }catch(Exception e ){
            e.printStackTrace();
        }
        return null;
    }
}
