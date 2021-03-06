package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Report;
import com.rental.demo.Repository.entity.User;
import com.rental.demo.Repository.mappers.RentRowMapper;
import com.rental.demo.Repository.mappers.ReportRowMapper;
import com.rental.demo.Repository.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("reportDao")
public class ReportDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    /***
     * 添加举报信息
     * @param fields
     * @param values
     * @return
     */
    public boolean addReport(List<String> fields,List<String> values){
        String sql = "INSERT INTO report (" +String.join(",",fields) + ") VALUES ('"+ String.join("','",values)+"')";
        return (jdbcTemplate.update(sql) == 1);
    }

    /***
     * 更新举报信息状态
     * @param reportState
     * @param reportId
     * @return
     */
    public boolean updateReportState (String reportState,String reportId){
        String sql = "UPDATE report SET result = ? WHERE id = ?";
        return (jdbcTemplate.update(sql,reportState,reportId) == 1);
    }

    /***
     * 根据状态查询举报信息
     * @param result
     * @return
     */
    public List<Report> getReportByResult(String result){
        String sql = "SELECT * FROM report WHERE result = ?";
        List<Report> ans = jdbcTemplate.query(sql, new ReportRowMapper(),result);
        return ans;
    }

    /***
     * 根据Id查找某个举报信息
     * @param reportId
     * @return
     */
    public Report getReportById(int reportId){
        String sql = "SELECT * FROM report WHERE id=? ";
        Report report= jdbcTemplate.queryForObject(sql, new ReportRowMapper(), reportId);
        return report;
    }
}
