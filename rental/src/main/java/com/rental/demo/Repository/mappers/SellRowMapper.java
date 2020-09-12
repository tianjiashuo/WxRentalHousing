package com.rental.demo.Repository.mappers;

import com.rental.demo.Repository.entity.Sell;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellRowMapper implements RowMapper<Sell> {

        @Override
        public Sell mapRow(ResultSet rs, int i) throws SQLException {
            Sell sell = new Sell();
            sell.setId(rs.getInt("id"));
            sell.setHostId(rs.getString("host_id"));
            sell.setTitle(rs.getString("title"));
            sell.setAddress(rs.getString("address"));
            sell.setProperty(rs.getBoolean("property"));
            sell.setType(rs.getString("type"));
            sell.setOrientation(rs.getString("orientation"));
            sell.setFloor(rs.getInt("floor"));
            sell.setIsElevator(rs.getBoolean("is_elevator"));
            sell.setIsRenovation(rs.getBoolean("is_renovation"));
            sell.setArea(rs.getInt("area"));
            sell.setPrice(rs.getInt("price"));
            sell.setState(rs.getBoolean("state"));
          return sell;
        }

}
