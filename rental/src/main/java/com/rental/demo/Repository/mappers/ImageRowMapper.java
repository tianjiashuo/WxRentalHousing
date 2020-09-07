package com.rental.demo.Repository.mappers;

import com.rental.demo.Repository.entity.Image;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageRowMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet rs, int i) throws SQLException {
        Image image = new Image();
        image.setId(rs.getInt("id"));
        image.setHomeType(rs.getInt("house_type"));
        image.setHouseId(rs.getInt("house_id"));
        image.setImageUrl(rs.getString("image_url"));
        return image;
    }
}
