package com.rental.demo.Service;

import com.rental.demo.Repository.dao.RentDao;
import com.rental.demo.Repository.dao.UserDao;
import com.rental.demo.Repository.entity.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.*;

@Service("RentService")
public class RentService {
    @Autowired
    private RentDao rentDao;

    //租房筛选房源
    public Set<Rent> selectRent(Map<String,String> condition){
        Set<Rent> ans = new HashSet<Rent>();
        //关键字筛选范围
        if(condition.containsKey("key")){
           String keywords = condition.get("key");
           ans.addAll(rentDao.queryByKWords("address",keywords));
           ans.addAll(rentDao.queryByKWords("title",keywords));
           ans.addAll(rentDao.queryByKWords("furniture",keywords));
           ans.addAll(rentDao.queryByKWords("type",keywords));
           ans.addAll(rentDao.queryByKWords("orientation",keywords));
           condition.remove("key");
         }
        //条件筛选范围
        for(Map.Entry<String,String>entry:condition.entrySet()){
            ans.addAll(rentDao.queryByCondt(entry.getKey(),entry.getValue()));
        }
       return ans;
    }


}
