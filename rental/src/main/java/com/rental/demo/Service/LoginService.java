package com.rental.demo.Service;

import com.rental.demo.Repository.dao.UserDao;
import com.rental.demo.Repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LoginService")
public class LoginService {
    @Autowired
    private UserDao userDao;

    //登录
    public boolean Login (User user){
        //判断密码是否正确
        System.out.println(user.getId());
        System.out.println(user.getPassword());
        System.out.println(userDao.getPassword((user.getId())));
        if(user.getPassword().equals(userDao.getPassword(user.getId()))){
            return true;
        }else{
            return false;
        }
    }
}
