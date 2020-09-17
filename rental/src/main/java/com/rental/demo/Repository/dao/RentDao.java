package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.mappers.RentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository("RentDao")
public class RentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    关键字查询
     */
    public List<Rent> queryByKWords(String field, String keywords){
        String sql = "SELECT * FROM rent WHERE state = 1 AND "+ field + " LIKE  \"%" + keywords+ "%\"  " ;
        List<Rent> ans = jdbcTemplate.query(sql, new RentRowMapper());
        return ans;
    };

    /*
     查询所有的租房信息
     */
    public List<Rent> queryAll(){
        String sql ="SELECT * FROM rent WHERE state =1";
        List<Rent>ans = jdbcTemplate.query(sql,new RentRowMapper());
        return ans;
    }

    /*
    条件查询
     */
    public List<Rent> queryByCondt(String key,String value){
        String sql = "SELECT * FROM rent WHERE state = 1 AND "+ key + " = "+value;
        System.out.println(sql+"-------------"+value);
        List<Rent> ans = jdbcTemplate.query(sql, new RentRowMapper());
        return ans;
    }

    /*
     * 更改state
     */
    public boolean updateRentState(int rentId, String stateCode){
        String sql = "UPDATE rent set state = "+ stateCode + " WHERE id = ?";
        int flag = jdbcTemplate.update(sql,rentId);
        return ( flag==1);
    }


    /***
     * 按照id获取信息
     * @author tian
     * @return
     */
    public Rent queryById(int id){
        String sql = "SELECT * FROM rent WHERE id = ?";
        Rent rent = jdbcTemplate.queryForObject(sql,new RentRowMapper(),id);
        return rent;
    }

    public int insertRentHouse(List keys,List values){
        String sql = "INSERT INTO rent (" +String.join(",",keys) + ") VALUES ('"+ String.join("','",values)+"')";
        System.out.println(sql+"--rent");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return  keyHolder.getKey().intValue();

    }

    //房源状态修改
    public int changeState(int id){
        String sql = "UPDATE rent set state=0 WHERE id=? ";
        return jdbcTemplate.update(sql,id);
    }


    public List<Rent> queryByHostId(String hostId){
        String sql = "SELECT * FROM rent WHERE host_id=?";
        List<Rent> ans = jdbcTemplate.query(sql , new RentRowMapper(),hostId);
        return ans;
    }

}
