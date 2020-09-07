package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.User;
import com.rental.demo.Repository.mappers.RentRowMapper;
import com.rental.demo.Repository.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository("RentDao")
public class RentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    关键字查询
     */
    public List<Rent> queryByKWords(String field, String keywords){
        String sql = "SELECT * FROM rent WHERE "+ field + "like % ? %";
        List<Rent> ans = jdbcTemplate.query(sql, new RentRowMapper(),keywords);
        return ans;
    };

    /*
    条件查询
     */
    public List<Rent> queryByCondt(String key,String value){
        String sql = "SELECT * FROM rent WHERE "+ key + "= ?";
        List<Rent> ans = jdbcTemplate.query(sql, new RentRowMapper(),value);
        return ans;
    }



}
