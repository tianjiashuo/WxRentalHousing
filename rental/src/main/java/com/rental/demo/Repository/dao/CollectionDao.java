package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Collection;
import com.rental.demo.Repository.mappers.CollectionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Repository("collectionDao")
public class CollectionDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /***
     * 添加收藏
     * @author tian
     * @return
     */
    public void addCollection(String userId,int houseId,int houseType){
        String sql = "INSERT INTO collection (user_id,house_id,house_type) VALUES(?, ? ,?)";
        jdbcTemplate.update(sql,userId,houseId,houseType);
    }

//    public int getCollectionId(String userId,int houseId,int houseType){
//        String sql = "SELECT id FROM collection WHERE user_id=? and house_id = ? and house_type=?";
//        Integer collectionId = jdbcTemplate.queryForObject(sql,new Integer[],userId,houseId,houseType);
//    }

    /***
     * 取消收藏
     * @author tian
     * @return
     */
    public void cancelCollection(int id){
        String sql = "DELETE FROM collection WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
    /***
     * 拉取某个人的收藏信息
     * @author tian
     * @return
     */
    public List<Collection> getCollectionById(String userId){
        String sql = "SELECT * FROM collection WHERE user_id = ?";
        List<Collection> lc= jdbcTemplate.query(sql, new CollectionRowMapper(),userId);
        return lc;
    }
    public List<Collection> getAllUsersId(int houseId,int houseType){
        String sql = "SELECT * FROM collection WHERE house_id = ? AND house_type = ?";
        List<Collection> ids = jdbcTemplate.query(sql,new CollectionRowMapper(),houseId,houseType);
        return ids;
    }
}
