package com.rental.demo.Repository.dao;

import com.rental.demo.Repository.entity.Sell;
import com.rental.demo.Repository.mappers.SellRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public boolean updateSellState(String sellId, String stateCode){
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
    public int insertSellHouse(Sell sell){
        String sql = "INSERT INTO sell (host_id,title,address,property,type,orientation" +
                ",floor,is_renovation,is_elevator,area,price,state) " +
                "VALUES(?, ? ,?, ?, ?, ? , ? , ? , ? , ? , ? , ? )";
        return jdbcTemplate.update(sql,sell.getHostId(),sell.getTitle(),sell.getAddress(),sell.getIsProperty(),sell.getType(),sell.getOrientation(),
                sell.getFloor(),sell.getIsRenovation(),sell.getIsElevator(),sell.getArea(),sell.getPrice(),1);
    }
    //房源状态修改
    public int changeState(int id) {
        String sql = "UPDATE sell set state=0 WHERE id=? ";
        return jdbcTemplate.update(sql, id);
    }
}
