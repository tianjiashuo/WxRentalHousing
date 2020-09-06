package com.rental.demo.Repository.mappers;

import com.rental.demo.Repository.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>{
    //mao增加了user新增属性的封装

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setHead(rs.getString("head"));
            user.setNickname(rs.getString("nickname"));
            user.setIntroduction(rs.getString("introduction"));
            user.setPassword(rs.getString("password"));
            user.setPhone(rs.getString("phone"));
            user.setGender(rs.getBoolean("gender"));
            user.setIDnumber(rs.getString("IDnumber"));
            return user;
        }

}
