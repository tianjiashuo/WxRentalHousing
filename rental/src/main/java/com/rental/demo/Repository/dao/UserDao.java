package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.User;
import com.rental.demo.Repository.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    /***
     * 根据id得到房屋信息
     * @author tian
     * @return
     */
    public User queryUserById(int id) {
        String sql = "SELECT * FROM user WHERE id=? ";
        User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
        return user;
    }

}

