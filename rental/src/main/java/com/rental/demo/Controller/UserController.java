package com.rental.demo.Controller;

import com.rental.demo.Service.AesService;
import com.rental.demo.Service.CheckIdNumber;
import com.rental.demo.Service.UserBo;
import com.rental.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin
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

    @PostMapping("/editUserInfo")
    int editUserInfo(@RequestBody UserBo userBo){
        return userService.editUserInfo(userBo.getId(),userBo);
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

    @PostMapping("/user/login")
    public Map userLogin(@RequestBody Map<String,String> json)  {
        return userService.userLogin(json);
    }
    @PostMapping("/user/getPhone")
    public Map getPhone(@RequestBody Map<String,Object> json){
        return userService.getPhone(json);
    }

}
