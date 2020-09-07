package com.rental.demo.Repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("collectionDao")
public class CollectionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /***
     * 添加收藏
     * @author tian
     * @return
     */
    public void addCollection(int userId,int houseId,int houseType){
        String sql = "INSERT INTO collection (user_id,house_id,house_type) VALUES(?, ? ,?)";
        jdbcTemplate.update(sql,userId,houseId,houseType);
    }
    /***
     * 取消收藏
     * @author tian
     * @return
     */
    public void cancelCollection(int id){
        String sql = "DELETE FROM collection WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
}
