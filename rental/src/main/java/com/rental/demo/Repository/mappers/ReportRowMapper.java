package com.rental.demo.Repository.mappers;

import com.rental.demo.Repository.entity.Report;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportRowMapper implements RowMapper<Report> {
    @Override
    public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
        Report report = new Report();
        report.setId(rs.getInt("id"));
        report.setUser_id(rs.getInt("user_id"));
        report.setHouse_id(rs.getInt("house_id"));
        report.setContent(rs.getString("content"));
        report.setResult(rs.getBoolean("result"));
        report.setHouse_type(rs.getInt("house_type"));
        return report;
    }
}
