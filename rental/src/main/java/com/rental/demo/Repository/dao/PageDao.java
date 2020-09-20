package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Swiper;
import com.rental.demo.Repository.mappers.SwiperRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("pageDao")
public class PageDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取轮播图数据
     * @return
     */
    public List<Swiper>getSwiper(){
        String sql = "SELECT * FROM index_page_swiper";
        List<Swiper> resultList= jdbcTemplate.query(sql,new SwiperRowMapper());
        return resultList;
    }
}
