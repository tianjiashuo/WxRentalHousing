package com.rental.demo.Service;

import com.rental.demo.Repository.dao.SessionDao;
import com.rental.demo.Repository.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("sessionService")
public class SessionService {

    @Autowired
   private SessionDao sessiondao;

    /**
     * 更具用户Id获取Session
     * mao 2020-9-12
     * @param id
     * @return
     */
    public Session getSessionById(String id){
        try{
            Session session = sessiondao.getSessionById(id);
            return session;
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    /**
     * 增加session信息
     * mao 2020-9-12
     * @param
     * @return
     */
    public boolean insertSession(String id,String session){
        System.out.println("insert session");
       return sessiondao.insertSession(id,session);
    }

    /**
     * 更新session信息
     * mao 2020-9-12
     * @param
     * @return
     */
    public boolean updateSession(String id,String session){
        System.out.println("update session");
        return sessiondao.updateSession(id,session);
    }

}
