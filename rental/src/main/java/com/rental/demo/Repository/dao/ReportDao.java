package com.rental.demo.Repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("reportDao")
public class ReportDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public boolean addReport(List<String> fields,List<String> values){
        String sql = "INSERT INTO report (" +String.join(",",fields) + ") VALUES ('"+ String.join("','",values)+"')";
        return (jdbcTemplate.update(sql) == 1);
    }
}
