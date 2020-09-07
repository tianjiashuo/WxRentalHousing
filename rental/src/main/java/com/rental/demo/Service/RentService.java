package com.rental.demo.Service;

import com.rental.demo.Repository.dao.RentDao;
import com.rental.demo.Repository.dao.UserDao;
import com.rental.demo.Repository.entity.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("RentService")
public class RentService {
    @Autowired
    private RentDao rentdao;

    //租房筛选房源
    public Set<Rent> selectRent(Map<String,String> condition){
        Set<Rent> ans = new HashSet<Rent>();
         if(condition.containsKey("key")){
           String keywords = condition.get("key");

         }
          for(Map.Entry<String,String>entry:condition.entrySet()){
              ans.addAll(rentdao.queryByCondt(entry.getKey(),entry.getValue()));
          }
        return ans;
    }



}