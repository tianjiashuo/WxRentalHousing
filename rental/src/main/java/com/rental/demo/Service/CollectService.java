package com.rental.demo.Service;

import com.rental.demo.Repository.dao.CollectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("collectService")
public class CollectService {
    @Autowired
    private CollectionDao collectionDao;

    public void addCollection(int userId,int houseId,int houseType){
        collectionDao.addCollection(userId,houseId,houseType);
    }

    public void cancelCollection(int id){
        collectionDao.cancelCollection(id);
    }
}
