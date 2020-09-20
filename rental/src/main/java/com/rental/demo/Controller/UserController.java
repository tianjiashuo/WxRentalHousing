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


    /***
     * 根据用户编号获取用户信息
     * @param id
     * @return
     */
    @GetMapping("/userInfo/{id}")
    UserBo getUserById(@PathVariable String id){
        return  userService.getUserById(id);
    }

    /**
     * 用户第一次使用微信登陆（注册）
     * @param userBo
     * @return
     */
    @PostMapping("/initUserInfo")
    int initUserInfo(@RequestBody UserBo userBo){
        return userService.initUserInfo(userBo);
    }

    /**
     * 编辑用户信息
     * @param userBo
     * @return
     */
    @PostMapping("/editUserInfo")
    int editUserInfo(@RequestBody UserBo userBo){
        return userService.editUserInfo(userBo.getId(),userBo);
    }

    /**
     * 检查用户实名信息接口
     * @param id
     * @return
     */
    @PostMapping("/checkUserRealName/{id}")
    boolean checkUserRealName(@PathVariable String id){
        return userService.checkUserRealName(id);
    }

    /**
     * 编辑用户实名信息接口
     * @param id
     * @param userBo
     * @return
     */
    @PostMapping("/insertUserRealName/{id}")
    int insertUserRealName(@PathVariable String id,@RequestBody UserBo userBo){
        if (CheckIdNumber.isIDNumber(userBo.getIdNumber())){
            return userService.insertUserRealName(id,userBo);
        }else{
            return -1;
        }
    }

    /**
     * 用户登陆接口
     * @param json
     * @return
     */
    @PostMapping("/user/login")
    public Map userLogin(@RequestBody Map<String,String> json){
        return userService.userLogin(json);
    }

    /**
     * 获取用户手机号接口
     * @param json
     * @return
     */
    @PostMapping("/user/getPhone")
    public Map getPhone(@RequestBody Map<String,Object> json){
        return userService.getPhone(json);
    }

    /**
     * 获取某人发布的所有房源
     */
    @GetMapping("/getUserHouse/{id}")
    public Map<String,Set> getUserHouse(@PathVariable String id){
        return userService.getUserHouse(id);
    }

    /**
     * 获得某个租房的租客信息
     * @param id
     * @return
     */
    @GetMapping("/getHouseRoommates/{id}")
    Map<String,Set> getRoommates(@PathVariable int id){return userService.getUserInfomation(id);}

    /**
     * 房东查看自己房源的申请
     */
    @PostMapping("/getAuditByHostId/{hostId}")
    public List<Map<String,Object>> queryAuditByHostId(@PathVariable String hostId){
        return rentService.queryByHostId(hostId);
    }
}
