package com.rental.demo.utils;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import java.util.HashMap;
import java.util.Map;
public class xiaomageUtil {
    //    // 短信应用SDK AppID
//    private int appid = 1400412374; // 1400开头
//
//    // 短信应用SDK AppKey
//    private String appkey = "012e78d9660928d9955332f3736e2375";
//
//    // 短信模板ID，需要在短信应用中申请
//    private int templateId = 692467; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
//    // 签名
    private static final String Sign = "阿毛的实验室"; //

    private static final String secretId="AKIDtc5xeBpoSyTheMQ2H3kETBk2bvyl2jyr";

    private static final String secretKey="nfGZdS9xzlqztW9fY9VLyLSnsnUuJRIz";

    private static final String SmsSdkAppid="1400412374";

    private static final Map<String,String> templateIds = new HashMap<String,String>(){{
        templateIds.put("report","724123");
        templateIds.put("news","723977");
    }};

    public static String sendSMS(Map<String,Object> sms){

        try{

            Credential cred = new Credential(secretId, secretKey);

            HttpProfile httpProfile = new HttpProfile();

            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();

            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);

            String phone = sms.get("phone").toString();

            String param = sms.get("params").toString();

            String templateID=templateIds.get(sms.get("smsType"));

            // "{\"PhoneNumberSet\":[\"+8615378965067\"],\"TemplateID\":\"692467\",\"Sign\":\"阿毛的实验室\",\"SmsSdkAppid\":\"1400412374\"}";

            //String params = "{\"PhoneNumberSet\":[\"+8615378965067\"],\"TemplateID\":\"723977\",\"Sign\":\"阿毛的实验室\",\"TemplateParamSet\":[\"天津大学北洋园\"],\"SmsSdkAppid\":\"1400412374\"}";
            String jsonString = "{\"PhoneNumberSet\":"+phone+"," + "\"TemplateID\":\" "+templateID+ " \",\"Sign\":\""+Sign+ "\","+"\"TemplateParamSet\":"+param+",\"SmsSdkAppid\":\""+SmsSdkAppid+"\"}";

            SendSmsRequest req = SendSmsRequest.fromJsonString(jsonString, SendSmsRequest.class);

            SendSmsResponse resp = client.SendSms(req);

            return SendSmsResponse.toJsonString(resp);

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            return null;
        }
    }

}
