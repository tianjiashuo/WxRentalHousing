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

    public int insertRentHouse(Rent rent){
        String sql = "INSERT INTO rent (host_id,address,title,type,orientation,floor,is_elevator," +
                "is_pet,shortest_lease,area,furniture,price,state,form) " +
                "VALUES(?, ? ,?, ?, ?, ? , ? , ? , ? , ? , ? , ? , ? ,?)";

        System.out.println(rent.getAddress()+"----------");
        System.out.println(rent.getHostId()+"----------");
        return jdbcTemplate.update(sql,rent.getHostId(),rent.getAddress(),rent.getTitle(),rent.getType()
                ,rent.getOrientation(),rent.getFloor(),rent.getIsElevator(),rent.getIsPet(),
                rent.getShortestLease(),rent.getArea(),rent.getFurniture(),rent.getPrice()
        ,1,rent.getIsForm());
    }

    //房源状态修改
    public int changeState(int id){
        String sql = "UPDATE rent set state=0 WHERE id=? ";
        return jdbcTemplate.update(sql,id);
    }


}
