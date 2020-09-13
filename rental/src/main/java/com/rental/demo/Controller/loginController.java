package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.User;
import com.rental.demo.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class loginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    @CrossOrigin
    public String userLogin(@RequestBody User user){
        System.out.println("User : " + user);
        String str = "error";
        boolean bool =loginService.Login(user);
        if (bool) {
            str = "ok";
        }
        return str;
    }
}
