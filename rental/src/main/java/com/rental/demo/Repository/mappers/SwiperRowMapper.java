package com.rental.demo.Repository.mappers;

import com.rental.demo.Repository.entity.Session;
import com.rental.demo.Repository.entity.Swiper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SwiperRowMapper  implements RowMapper<Swiper> {


    @Override
    public Swiper mapRow(ResultSet rs, int rowNum) throws SQLException {

        Swiper swiper = new Swiper();
        swiper.setId(rs.getString("id"));
        swiper.setUrl(rs.getString("url"));
        swiper.setTitle(rs.getString("title"));
        swiper.setSmallTitle(rs.getString("smalltitle"));
        return swiper;
    }
}
