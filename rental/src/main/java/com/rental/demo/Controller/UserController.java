package com.rental.demo.Controller;

import com.rental.demo.Service.CheckIdNumber;
import com.rental.demo.Service.UserBo;
import com.rental.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo/{id}")
    UserBo getUserById(@PathVariable String id){
        return  userService.getUserById(id);
    }

    @PostMapping("/initUserInfo")
    int initUserInfo(@RequestBody UserBo userBo){
        return userService.initUserInfo(userBo);
    }

    @PostMapping("/editUserInfo/{id}")
    int editUserInfo(@PathVariable String id,@RequestBody UserBo userBo){
        return userService.editUserInfo(id,userBo);
    }

    @PostMapping("/checkUserRealName/{id}")
    boolean checkUserRealName(@PathVariable String id){
        return userService.checkUserRealName(id);
    }
    @PostMapping("/insertUserRealName/{id}")
    int insertUserRealName(@PathVariable String id,@RequestBody UserBo userBo){
        if (CheckIdNumber.isIDNumber(userBo.getIdNumber())){
            return userService.insertUserRealName(id,userBo);
        }else{
            return -1;
        }
    }

}
