package com.rental.demo.Repository.mappers;

import com.rental.demo.Repository.entity.Roommates;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoommatesRowMapper implements RowMapper<Roommates> {
    @Override
    public Roommates mapRow(ResultSet rs, int i) throws SQLException {
        Roommates roommates = new Roommates();
        roommates.setId(rs.getInt("id"));
        roommates.setHouseId(rs.getInt("house_id"));
        roommates.setUserId(rs.getString("user_id"));
        roommates.setState(rs.getInt("state"));
        return roommates;
    }
}
