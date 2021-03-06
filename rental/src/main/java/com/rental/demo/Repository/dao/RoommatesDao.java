package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Roommates;
import com.rental.demo.Repository.mappers.RoommatesRowMapper;
import com.rental.demo.Service.RoommatesBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("RoommatesDao")
public class RoommatesDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据房屋id查询租客信息
     */
    public List<Roommates> queryById(int id) {
        String sql = "SELECT * FROM roommates_info WHERE house_id = ?";
        List roommates = jdbcTemplate.query(sql, new RoommatesRowMapper(), id);
        return roommates;
    }

    /***
     * 用户申请加入合租
     * @param roommatesBo
     * @return
     */
    public int addApplication(RoommatesBo roommatesBo){
        String sql = "INSERT INTO roommates_info (house_id,user_id,state) VALUES(? ,?,'0')";
        return jdbcTemplate.update(sql,roommatesBo.getHouseId(),
                roommatesBo.getUserId());
    }

    /***
     * 房东同意加入合租
     */
    public int admitApplication(int id){
        String sql = "UPDATE roommates_info set state=1 WHERE id=? ";
        return jdbcTemplate.update(sql,id);
    }
    /***
     * 房东拒绝加入合租
     */
    public int refuseApplication(int id){
        String sql = "UPDATE roommates_info set state=-1 WHERE id=? ";
        return jdbcTemplate.update(sql,id);
    }

    /***
     *根据房源id查询所有待审核申请
     */
    public List<Roommates> queryByHouseId(int houseId) {
        String sql = "SELECT * FROM roommates_info WHERE house_id = ? AND state=0";
        List<Roommates> roommates = jdbcTemplate.query(sql, new RoommatesRowMapper(), houseId);
        return roommates;
    }

}