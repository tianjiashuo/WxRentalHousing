package com.rental.demo.Service;

import com.rental.demo.Repository.dao.RentDao;
import com.rental.demo.Repository.dao.RoommatesDao;
import com.rental.demo.Repository.dao.SellDao;
import com.rental.demo.Repository.dao.UserDao;
import com.rental.demo.Repository.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AesService aesService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private RentDao rentDao;
    @Autowired
    private RentService rentService;
    @Autowired
    private UserService userService;
    @Autowired
    private SellDao sellDao;
    @Autowired
    private SellService sellService;
    @Autowired
    private  RoommatesDao roommatesDao;

    /**
     * 获取用户展示信息
     * mao changed 2020-09-12
     * @param id
     * @return
     */
    public UserBo getUserById(String id){
        try{
            User user = userDao.queryUserById(id);
            UserBo userBo = new UserBo(id,user.getHead(),user.getNickname(),
                    user.getIntroduction(),user.getPhone(),user.getGender(),user.getIdNumber());
            return userBo;
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    /***
     * 第一次使用微信登陆的用户（注册）
     * @param user
     * @return
     */
    public int initUserInfo(UserBo user) {
        return userDao.insertUserInfo(user);
    }

    /**
     * 编辑用户信息
     * @param id
     * @param userBo
     * @return
     */
    public int editUserInfo(String id,UserBo userBo){
        return userDao.editUserInfo(id,userBo);
    }

    /***
     * 检查用户实名信息
     * @param userId
     * @return
     */
    public boolean checkUserRealName(String userId){
        return userDao.checkUserRealName(userId);
    }

    /***
     * 增加用户实名信息
     * @param id
     * @param userBo
     * @return
     */
    public int insertUserRealName(String id,UserBo userBo){
        return userDao.insertUserRealName(id,userBo);
    }


    /**
     * mao 2020-9-12
     * @param reqinfo
     * @return
     */
    public Map userLogin(Map<String,String> reqinfo){

        Map info = aesService.decodeUserInfo(reqinfo.get("encryptedData"),reqinfo.get("iv"),reqinfo.get("code"));
        String session = info.get("session_key").toString();
        System.out.println("aes--0 "+ session);
        info.remove("session_key");
        //第三方平台返回失败status=0
        if(info.get("status").toString().equals("0")){
            return info;
        }else{
              Map<String,String> userInfo = (Map) info.get("userInfo");

            //更新session
              boolean flag0 = sessionService.updateSession(userInfo.get("openId"),session);
              if(!flag0){
                  //如果不存在id
                  sessionService.insertSession(userInfo.get("openId"),session);
              }

              //用户已经存在
              if(getUserById(userInfo.get("openId"))!=null){
                  info.put("loginType","login");
                  return info;
              }else{
                  //用户第一次登陆系统
                  UserBo newuser = new UserBo();
                  newuser.setId(userInfo.get("openId"));
                  newuser.setHead(userInfo.get("avatarUrl"));
                  newuser.setGender(userInfo.get("gender").equals("1"));
                  newuser.setNickname(userInfo.get("nickName"));
                  newuser.setPhone("999");
                  //加入用户
                  int flag = initUserInfo(newuser);
                  if(flag==1){//加入成功
                      info.put("loginType","logUp");
                  }else{//加入失败
                      info.put("loginType","logUpFail");
                  }

                  return info;
              }
        }
    }

    /**
     * 获取用户手机号
     * mao 2020-9-12
     * @param reqinfo
     * @return
     */
    public Map getPhone(Map<String,Object> reqinfo){
        System.out.println("getphone0 "+reqinfo.toString());
        //拿取Session
        Session session =  sessionService.getSessionById(reqinfo.get("openId").toString());
        if(session==null){
            return null;
        }
        System.out.println("getphone1 "+session.getSession());
       //获得解码后的map
        // {phoneNumber=15378965067, watermark={timestamp=1.599921154E9, appid=wx1a8b5c07794aa546}, purePhoneNumber=15378965067, countryCode=86}
        Map<String,String> map = aesService.decodePhoneNumber(reqinfo.get("encryptedData").toString(),reqinfo.get("iv").toString(),session.getSession());
        //调用userdao加入电话
        userDao.editUserPhoneInfo(reqinfo.get("openId").toString(),map.get("phoneNumber"));
       return map;
    }

    /***
     * 获取某人发布的所有房源
     */
    public HashMap<String,Set> getUserHouse(String hostId){
        HashMap<String,Set> result = new HashMap<>();
        List<Rent> lr = rentDao.queryByHostId(hostId);
        Iterator<Rent> ir = lr.iterator();
        HashSet rentResult  = new HashSet();
        while(ir.hasNext()) {
            rentResult.add(rentService.getRentByIdHost(ir.next().getId()));
        }
        List<Sell> ls = sellDao.queryByHostId(hostId);
        Iterator<Sell> is = ls.iterator();
        HashSet sellResult  = new HashSet();
        while(is.hasNext()) {
            sellResult.add(sellService.getSellByIdHost(is.next().getId()));
        }
        result.put("rent",rentResult);
        result.put("sell",sellResult);
        return result;
    }


    /***
     * 获取租客信息
     * @param houseId
     * @return
     */
    public HashMap<String,Set> getUserInfomation(int houseId){
        HashMap<String,Set> result = new HashMap<>();
        List<Roommates> lr = roommatesDao.queryById(houseId);
        Iterator<Roommates> ir = lr.iterator();
        HashSet roomResult  = new HashSet();
        while(ir.hasNext()) {
            roomResult.add(userService.getUserById(ir.next().getUserId()));
        }

        result.put("roommates",roomResult);
        return result;
    }

}
