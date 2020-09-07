package com.rental.demo.Service;

import com.rental.demo.Repository.dao.ImageDao;
import com.rental.demo.Repository.dao.SellDao;
import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SellService")
public class SellService {
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private SellDao sellDao;

    public SellBo getSellById(int id){
        Sell sell = sellDao.queryById(id);
        String image = imageDao.getFirstImageById(id,1);
        SellBo sellBo = new SellBo(sell.getId(),sell.getArea(),sell.getPrice(),sell.getAddress(),
                sell.getTitle(),sell.getType(),sell.getIsRenovation(),image);
        return sellBo;
    }
}
