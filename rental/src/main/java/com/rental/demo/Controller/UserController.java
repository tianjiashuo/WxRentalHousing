package com.rental.demo.Controller;

import com.rental.demo.Service.UserBo;
import com.rental.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo/{id}")
    UserBo getUserById(@PathVariable int id){
        return  userService.getUserById(id);
    }
}
