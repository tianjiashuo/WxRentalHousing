package com.rental.demo.Service;

import com.rental.demo.Repository.dao.RentDao;
import com.rental.demo.Repository.dao.SellDao;
import com.rental.demo.Repository.dao.UserDao;
import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Sell;
import com.rental.demo.Repository.entity.Session;
import com.rental.demo.Repository.entity.User;
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
    private SellDao sellDao;
    @Autowired
    private SellService sellService;

    /**
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

    public int initUserInfo(UserBo user) {
        return userDao.insertUserInfo(user);
    }

    public int editUserInfo(String id,UserBo userBo){
        return userDao.editUserInfo(id,userBo);
    }

    public boolean checkUserRealName(String userId){
        return userDao.checkUserRealName(userId);
    }

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

    //获取某人发布的所有房源
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

}
