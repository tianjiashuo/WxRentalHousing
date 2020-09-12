package com.rental.demo.Repository.mappers;

import com.rental.demo.Repository.entity.Collection;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionRowMapper implements RowMapper<Collection> {

    @Override
    public Collection mapRow(ResultSet rs, int i) throws SQLException {
        Collection collection = new Collection();
        collection.setId(rs.getInt("id"));
        collection.setUserId(rs.getString("user_id"));
        collection.setHouseId(rs.getInt("house_id"));
        collection.setHouseType(rs.getInt("house_type"));
        return collection;
    }
}
