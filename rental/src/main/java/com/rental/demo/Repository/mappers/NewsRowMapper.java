package com.rental.demo.Repository.mappers;

import com.rental.demo.Repository.entity.Image;
import com.rental.demo.Repository.entity.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsRowMapper  implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        News news = new News();
        news.setId(rs.getInt("id"));
        news.setUser_id(rs.getString("user_id"));
        news.setContent(rs.getString("content"));
        return news;
    }
}
