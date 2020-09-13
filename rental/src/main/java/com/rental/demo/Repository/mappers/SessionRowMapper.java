package com.rental.demo.Repository.mappers;


import com.rental.demo.Repository.entity.Session;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionRowMapper implements RowMapper<Session> {
    @Override
    public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
        Session session = new Session();
        session.setId(rs.getString("id"));
        session.setSession(rs.getString("session_key"));
        return session;
    }
}
