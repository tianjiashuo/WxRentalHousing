package com.rental.demo.Repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("NewsDao")
public class NewsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 增加消息
     * @param userId
     * @param content
     * @return
     */
    public boolean addNews(String userId,String content){
        String sql = "INSERT INTO news (user_id,content) VALUES(?,?)";
        int f = jdbcTemplate.update(sql,userId,content);
        return (f==1);
    }
}
