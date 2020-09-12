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

    public int insertUserInfo(UserBo userBo){
        String sql = "INSERT INTO user (head,nickname,phone,gender) VALUES(?, ? ,?,?)";
        return jdbcTemplate.update(sql,userBo.getHead(),
                userBo.getNickname(),userBo.getPhone(),true);
    }

    public int editUserInfo(String id,UserBo userBo){
        String sql = "UPDATE user set introduction=?,phone=?,gender=?,IDnumber=? WHERE id=? ";
        return jdbcTemplate.update(sql,userBo.getIntroduction(),userBo.getPhone(),
                userBo.getGender(),userBo.getIdNumber(),id);
    }

    public boolean checkUserRealName(String userId){
        String sql = "SELECT * FROM user WHERE id = ?";
        User user = jdbcTemplate.queryForObject(sql,new UserRowMapper(),userId);
        if(user.getIDnumber().equals("")){
            return false;
        }
        else{
            return true;
        }
    }

    public int insertUserRealName(String id,UserBo userBo){
        String sql = "UPDATE user set IDnumber=? WHERE id=? ";
        return jdbcTemplate.update(sql,userBo.getIdNumber(),id);

    }

    public String getPassword(String id){
        String sql = "SELECT * FROM user WHERE id = ?";
        System.out.println(jdbcTemplate.queryForObject(sql,new UserRowMapper(),id));
        return jdbcTemplate.queryForObject(sql,new UserRowMapper(),id).getPassword();
    }
}

