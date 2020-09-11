package com.rental.demo.Service;

import com.rental.demo.Controller.UserVo;
import com.rental.demo.Repository.dao.UserDao;
import com.rental.demo.Repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class LeaveService {

    @Autowired
    private UserDao userDao;

    //登录
    public boolean Login (User user){
        //判断密码是否正确
        if(user.getPassword().equals(user.getPassword())){
            return true;
        }else{
            return false;
        }
    }
}
