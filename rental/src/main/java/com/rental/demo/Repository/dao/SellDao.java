package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Sell;
import com.rental.demo.Repository.mappers.RentRowMapper;
import com.rental.demo.Repository.mappers.SellRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SellDao")
public class SellDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    关键字查询
     */
    public List<Sell> queryByKWords(String field, String keywords){
        String sql = "SELECT * FROM sell WHERE state =1 AND  "+ field + " LIKE  \"%" + keywords+ "%\"  " ;
        List<Sell> ans = jdbcTemplate.query(sql , new SellRowMapper());
        return ans;
    };

    /*
    条件查询
     */
    public List<Sell> queryByCondt(String key,String value){
        String sql = "SELECT * FROM sell WHERE state =1 AND "+ key + "= ?";
        List<Sell> ans = jdbcTemplate.query(sql , new SellRowMapper(),value);
        return ans;
    }

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
