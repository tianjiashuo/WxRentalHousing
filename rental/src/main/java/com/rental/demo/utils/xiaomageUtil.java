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

    private static final String Sign = "阿毛的实验室"; //

    private static final String secretId="AKIDtc5xeBpoSyTheMQ2H3kETBk2bvyl2*****";

    private static final String secretKey="nfGZdS9xzlqztW9fY9VLyLSnsnUu*******";

    private static final String SmsSdkAppid="1400412374";

    private static final Map<String,String> templateIds = new HashMap<String,String>();

    static {
        templateIds.put("report","724123");
        templateIds.put("news","723977");
    }

    public static String sendSMS(Map<String,Object> sms){

        try{

            Credential cred = new Credential(secretId, secretKey);

            HttpProfile httpProfile = new HttpProfile();

            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();

            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);

            String phone = sms.get("phone").toString();

            String param = sms.get("param").toString();

            String templateID=templateIds.get(sms.get("smsType"));
            System.out.println("templateIds"+ templateID);

            // "{\"PhoneNumberSet\":[\"+8615378965067\"],\"TemplateID\":\"692467\",\"Sign\":\"阿毛的实验室\",\"SmsSdkAppid\":\"1400412374\"}";

            //String params = "{\"PhoneNumberSet\":[\"+8615378965067\"],\"TemplateID\":\"723977\",\"Sign\":\"阿毛的实验室\",\"TemplateParamSet\":[\"天津大学北洋园\"],\"SmsSdkAppid\":\"1400412374\"}";
            String jsonString = "{\"PhoneNumberSet\":"+phone+"," + "\"TemplateID\":\""+templateID+ "\",\"Sign\":\""+Sign+ "\","+"\"TemplateParamSet\":[\""+param+"\"],\"SmsSdkAppid\":\""+SmsSdkAppid+"\"}";
            System.out.println("jsonString"+jsonString);
            SendSmsRequest req = SendSmsRequest.fromJsonString(jsonString, SendSmsRequest.class);

            SendSmsResponse resp = client.SendSms(req);

            return SendSmsResponse.toJsonString(resp);

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            return null;
        }
    }

}
