package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Sell;
import com.rental.demo.Repository.mappers.RentRowMapper;
import com.rental.demo.Repository.mappers.SellRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("SellDao")
public class SellDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /***
     * 按照id获取信息
     * @author tian
     * @return
     */
    public Sell queryById(int id){
        String sql = "SELECT * FROM sell WHERE id = ?";
        Sell sell = jdbcTemplate.queryForObject(sql,new SellRowMapper(),id);
        return sell;
    }
}
