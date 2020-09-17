package com.rental.demo.Service;

import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

@Service("qiniuService")
public class QiniuService {

    private String accessKey = "CWuxSfC_2OvpHnFThVhWD28pDZ3hp5rQ6QpyNoMO";
    private String secretKey = "kPKO5jMqd8VlOfJ-Tm3nv9Ob3HMJGDyYZixhEXSr";
    private String bucket = "rentalhousing";

    public String getUpToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);
        return upToken;
    }


}
