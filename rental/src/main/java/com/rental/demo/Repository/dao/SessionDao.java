package com.rental.demo.Repository.dao;


import com.rental.demo.Repository.entity.Session;
import com.rental.demo.Repository.mappers.SessionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("sessionDao")
public class SessionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Session getSessionById(String id){
        String sql = "SELECT *FROM session WHERE id =?";
        Session session = jdbcTemplate.queryForObject(sql, new SessionRowMapper(),id);
        return session;
    }

    public Boolean updateSession(String id,String session){
        String sql ="UPDATE session SET session_key =? WHERE id =?";
        int flag = jdbcTemplate.update(sql,session,id);
        return(flag==1);
    }
    public Boolean insertSession(String id,String session){
        String sql ="INSERT session (id,session_key) VALUES (?,?)";
        int flag = jdbcTemplate.update(sql,id,session);
        return (flag==1);
    }
}
