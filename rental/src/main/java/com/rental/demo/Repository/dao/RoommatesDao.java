package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Roommates;
import com.rental.demo.Repository.mappers.RentRowMapper;
import com.rental.demo.Repository.mappers.RoommatesRowMapper;
import com.rental.demo.Service.RoommatesBo;
import com.rental.demo.Service.UserBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("RoommatesDao")
public class RoommatesDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //根据id查询

    public Roommates queryById(int id) {
        String sql = "SELECT * FROM roommates_info WHERE id = ?";
        Roommates roommates = jdbcTemplate.queryForObject(sql, new RoommatesRowMapper(), id);
        return roommates;
    }

    //用户申请

    public int  addApplication(RoommatesBo roommatesBo){
        String sql = "INSERT INTO roommates_info (house_id,user_id,state) VALUES(? ,?,'0')";
        return jdbcTemplate.update(sql,roommatesBo.getHouseId(),
                roommatesBo.getUserId());
    }

    //房东同意
    public int admitApplication(int id){
        String sql = "UPDATE roommates_info set state=1 WHERE id=? ";
        return jdbcTemplate.update(sql,id);
    }
}