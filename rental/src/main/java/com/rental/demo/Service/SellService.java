package com.rental.demo.Service;
import com.rental.demo.Repository.dao.ImageDao;
import com.rental.demo.Repository.dao.SellDao;
import com.rental.demo.Repository.entity.Image;
import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("SellService")
public class SellService {

    @Autowired
    private SellDao sellDao;
    @Autowired
    private ImageDao imageDao;

    private static final String ILLEGAL_STATE  ="-1";
    private static final String USELL_STATE  ="1";
    private static final String SELL_STATE  ="0";
    private static final String HOUSE_TYPE_SELL = "1";

    /**
     * 买房筛选房源
     * @param condition
     * @return
     */
    public Set<Sell> selectSell(Map<String,String> condition){
        Set<Sell> ans = new HashSet<Sell>();
        //关键字筛选范围
        if(condition.containsKey("key")){
            String keywords = condition.get("key");
            ans.addAll(sellDao.queryByKWords("address",keywords));
            ans.addAll(sellDao.queryByKWords("title",keywords));
            ans.addAll(sellDao.queryByKWords("type",keywords));
            ans.addAll(sellDao.queryByKWords("orientation",keywords));
            condition.remove("key");
        }
        //条件筛选范围
        for(Map.Entry<String,String>entry:condition.entrySet()){
            ans.addAll(sellDao.queryByCondt(entry.getKey(),entry.getValue()));
        }
        return ans;
    }

    /**
     * 查询首页信息
     */
    public SellBo getSellById(int id) {
        Sell sell = sellDao.queryById(id);
        String image = imageDao.getFirstImageById(id, 1);
        SellBo sellBo = new SellBo(sell.getId(), sell.getArea(), sell.getPrice(), sell.getAddress(),
                sell.getTitle(), sell.getType(), sell.getIsRenovation(), image);
        return sellBo;
    }

    /**
     * 查询商品的详细信息
     * @param id
     * @return
     */
    public Map<String,Object>getSellAllById(int id){
        Map<String,Object>map = new HashMap<String, Object>();
        Sell sell = sellDao.queryById(id);
        map.put("sellInfo",sell);
        List<Image> images = imageDao.getAllImageById(sell.getId(), Integer.parseInt(HOUSE_TYPE_SELL));
        map.put("imageList",images);
        return map;
    }

    //卖出去了
    public int changeState(int id) {
        return sellDao.changeState(id);
    }

    /***
     * 删除房源（更改房源状态）
     * @param sellId
     * @return
     */
    public boolean deleteSellInfo(String sellId) {
        return sellDao.updateSellState(sellId, ILLEGAL_STATE);
    }

    public int insertSellHouse(Sell sell){
        return sellDao.insertSellHouse(sell);

    }
}
