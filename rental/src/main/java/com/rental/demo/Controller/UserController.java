package com.rental.demo.Controller;

import com.rental.demo.Service.UserBo;
import com.rental.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo/{id}")
    UserBo getUserById(@PathVariable int id){
        return  userService.getUserById(id);
    }

    @PostMapping("/initUserInfo")
    int initUserInfo(@RequestBody UserBo userBo){
        return userService.initUserInfo(userBo);
    }

    @PostMapping("/editUserInfo/{id}")
    int editUserInfo(@PathVariable int id,@RequestBody UserBo userBo){
        return userService.editUserInfo(id,userBo);
    }


}
