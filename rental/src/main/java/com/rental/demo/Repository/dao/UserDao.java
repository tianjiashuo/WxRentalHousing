package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.User;
import com.rental.demo.Repository.mappers.UserRowMapper;
import com.rental.demo.Service.UserBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    /***
     * 根据id得到房屋信息
     * @author tian
     * @return
     */
    public User queryUserById(String id) {
        String sql = "SELECT * FROM user WHERE id=? ";
        User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        return user;
    }

    /***
     * 加入新用户
     * @param userBo
     * @return
     */
    public int insertUserInfo(UserBo userBo){
        String sql = "INSERT INTO user (id,head,nickname,phone,gender) VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(sql,userBo.getId(),userBo.getHead(),
                userBo.getNickname(),userBo.getPhone(),userBo.getGender());
    }

    /***
     * 编辑用户信息
     * @param id
     * @param userBo
     * @return
     */
    public int editUserInfo(String id,UserBo userBo){
        String sql = "UPDATE user set introduction=?,IDnumber=? WHERE id=? ";
        System.out.println(userBo.getIdNumber());
        return jdbcTemplate.update(sql,userBo.getIntroduction(),userBo.getIdNumber(),id);
    }

    /***
     * 编辑用户电话信息
     * @param id
     * @param phone
     * @return
     */
    public int editUserPhoneInfo(String id,String phone){
        String sql = "UPDATE user set phone=? WHERE id=? ";
        return jdbcTemplate.update(sql,phone,id);
    }

    /***
     * 编辑用户自我介绍信息
     * @param id
     * @param intro
     * @return
     */
    public int editUserIntroductionInfo(String id,String intro){
        String sql = "UPDATE user set introduction=? WHERE id=? ";
        return jdbcTemplate.update(sql,intro,id);
    }

    /***
     * 编辑用户身份证号
     * @param id
     * @param IDnumber
     * @return
     */
    public int editUserIDInfo(String id,String IDnumber){
        String sql = "UPDATE user set IDnumber=? WHERE id=? ";
        return jdbcTemplate.update(sql,IDnumber,id);
    }


    /**
     * 检查用户实名情况
     * @param userId
     * @return
     */
    public boolean checkUserRealName(String userId){
        String sql = "SELECT * FROM user WHERE id = ?";
        User user = jdbcTemplate.queryForObject(sql,new UserRowMapper(),userId);
        if(user.getIdNumber().equals("")){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 新增用户实名信息
     * @param id
     * @param userBo
     * @return
     */
    public int insertUserRealName(String id,UserBo userBo){
        String sql = "UPDATE user set IDnumber=? WHERE id=? ";
        return jdbcTemplate.update(sql,userBo.getIdNumber(),id);

    }

    /**
     * 获取用户密码
     * @param id
     * @return
     */
    public String getPassword(String id){
        String sql = "SELECT * FROM user WHERE id = ?";
        System.out.println(jdbcTemplate.queryForObject(sql,new UserRowMapper(),id));
        return jdbcTemplate.queryForObject(sql,new UserRowMapper(),id).getPassword();
    }


}

