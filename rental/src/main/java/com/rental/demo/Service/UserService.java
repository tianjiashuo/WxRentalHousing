package com.rental.demo.Service;

import com.rental.demo.Repository.dao.UserDao;
import com.rental.demo.Repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserBo getUserById(int id){
        User user = userDao.queryUserById(id);
        UserBo userBo = new UserBo(id,user.getHead(),user.getNickname(),
                user.getIntroduction(),user.getPhone(),user.getGender(),user.getIDnumber());
        return userBo;
    }

    public int initUserInfo(UserBo user) {
        return userDao.insertUserInfo(user);
    }
    public int editUserInfo(int id,UserBo userBo){
        return userDao.editUserInfo(id,userBo);
    }
}
