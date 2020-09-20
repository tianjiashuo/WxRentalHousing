package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Collection;
import com.rental.demo.Repository.entity.Image;
import com.rental.demo.Repository.mappers.CollectionRowMapper;
import com.rental.demo.Repository.mappers.ImageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ImageDao")
public class ImageDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *查询某个房源的第一张图片
     */
    public String getFirstImageById(int houseId, int houseType){
            String sql = "SELECT * FROM image WHERE house_id = ? AND house_type=? LIMIT 1";
            String image= jdbcTemplate.queryForObject(sql, new ImageRowMapper(),houseId,houseType).getImageUrl();
            return image;
    }

    /***
     *查询所有图片
     */
    public List<Image> getAllImageById(int houseId, int houseType){
        String sql = "SELECT * FROM image WHERE house_id = ? AND house_type=?";
        List<Image> images= jdbcTemplate.query(sql, new ImageRowMapper(),houseId,houseType);
        return images;
    }

    /***
     *增加房源图片
     */
    public int insertImg(int house_id,String imgUrl,int house_type){
        String sql="INSERT INTO image (house_id,image_url,house_type) VALUES (?,?,?)";
        int flag = jdbcTemplate.update(sql,house_id,imgUrl,house_type);
        return flag;
    }
}
