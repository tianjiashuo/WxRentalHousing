package com.rental.demo.Repository.mappers;

import com.rental.demo.Repository.entity.Rent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RentRowMapper implements RowMapper<Rent>{
    //mao增加了user新增属性的封装

        @Override
        public Rent mapRow(ResultSet rs, int i) throws SQLException {
            Rent rent = new Rent();
            rent.setId(rs.getInt("id"));
            rent.setHostId(rs.getString("host_id"));
            rent.setTitle(rs.getString("title"));
            rent.setAddress(rs.getString("address"));
            rent.setType(rs.getString("type"));
            rent.setOrientation(rs.getString("orientation"));
            rent.setFloor(rs.getInt("floor"));
            rent.setIsElevator(rs.getBoolean("is_elevator"));
            rent.setIsPet(rs.getBoolean("is_pet"));
            rent.setShortestLease(rs.getInt("shortest_lease"));
            rent.setArea(rs.getInt("area"));
            rent.setFurniture(rs.getString("furniture"));
            rent.setPrice(rs.getInt("price"));
            rent.setState(rs.getInt("state"));
            rent.setForm(rs.getBoolean("form"));
            return rent;
        }

}
