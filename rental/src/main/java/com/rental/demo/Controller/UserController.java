package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Roommates;
import com.rental.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RentService rentService;


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
    public Map userLogin(@RequestBody Map<String,String> json){
        return userService.userLogin(json);
    }
    @PostMapping("/user/getPhone")
    public Map getPhone(@RequestBody Map<String,Object> json){
        return userService.getPhone(json);
    }

    //获取某人发布的所有房源
    @GetMapping("/getUserHouse/{id}")
    public Map<String,Set> getUserHouse(@PathVariable String id){
        return userService.getUserHouse(id);
    }

    //房东查看自己房源的申请
    @PostMapping("/getAuditByHostId/{hostId}")
    public List<Map<String,Object>> queryAuditByHostId(@PathVariable String hostId){
        return rentService.queryByHostId(hostId);
    }
}
