package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Sell;
import com.rental.demo.Repository.mappers.RentRowMapper;
import com.rental.demo.Repository.mappers.SellRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("SellDao")
public class SellDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*
    关键字查询
     */
    public List<Sell> queryByKWords(String field, String keywords){
        String sql = "SELECT * FROM sell WHERE state = 1 AND  "+ field + " LIKE  \"%" + keywords+ "%\"  " ;
        List<Sell> ans = jdbcTemplate.query(sql , new SellRowMapper());
        return ans;
    };

    /*
    条件查询
     */
    public List<Sell> queryByCondt(String key,String value){
        String sql = "SELECT * FROM sell WHERE state = 1  AND "+ key + "= ?";
        List<Sell> ans = jdbcTemplate.query(sql , new SellRowMapper(),value);
        return ans;
    }

    /*
     * 更改state
     */
    public boolean updateSellState(int sellId, String stateCode){
        String sql = "UPDATE sell set state = "+ stateCode + " WHERE id = ?";
        int flag = jdbcTemplate.update(sql,sellId);
        return ( flag==1);
    }

    /***
     * 按照id获取信息
     * @author tian
     * @return
     */
    public Sell queryById(int id){
        String sql = "SELECT * FROM sell WHERE id = ?";
        Sell sell = jdbcTemplate.queryForObject(sql,new SellRowMapper(),id);
        return sell;
    }

    public int insertSellHouse(List keys,List values){
        String sql = "INSERT INTO sell (" +String.join(",",keys) + ") VALUES ('"+ String.join("','",values)+"')";
        System.out.println(sql+"--sell");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return  keyHolder.getKey().intValue();
    }
    //房源状态修改
    public int changeState(int id) {
        String sql = "UPDATE sell set state=0 WHERE id=? ";
        return jdbcTemplate.update(sql, id);
    }

    //查询所有房源信息
    public List<Sell> queryAll(){
        String sql ="SELECT * FROM sell WHERE state =1";
        List<Sell>ans = jdbcTemplate.query(sql,new SellRowMapper());
        return ans;
    }
    /*
    查询出某个host_id对应的房源信息
  */
    public List<Sell> queryByHostId(String hostId){
        String sql = "SELECT * FROM sell WHERE host_id=?";
        List<Sell> ans = jdbcTemplate.query(sql , new SellRowMapper(),hostId);
        return ans;
    }
}
