package com.rental.demo.Service;

import com.rental.demo.Repository.dao.CollectionDao;
import com.rental.demo.Repository.dao.RentDao;
import com.rental.demo.Repository.dao.SellDao;
import com.rental.demo.Repository.entity.Collection;
import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Sell;
import com.rental.demo.Repository.mappers.RentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("collectService")
public class CollectService {
    @Autowired
    private CollectionDao collectionDao;
    @Autowired
    private RentService rentService;
    @Autowired
    private SellService sellService;

    public void addCollection(String userId,int houseId,int houseType){
        collectionDao.addCollection(userId,houseId,houseType);
    }

    public void cancelCollection(int id){
        collectionDao.cancelCollection(id);
    }

    /***
     * 拉取某用户的收藏信息
     * @author tian
     * @return
     */
    public HashSet getConnectionByUserId(String id){
        List<Collection> lc = collectionDao.getCollectionById(id);
        HashSet result = new HashSet();
        Iterator<Collection> iter = lc.iterator();
        while (iter.hasNext()) {
            Collection collection = iter.next();
            //出租房屋
            if(collection.getHouseType()==0){
                result.add(rentService.getRentById(collection.getHouseId()));
            }
            //出售房屋
            else if(collection.getHouseType()==1){
                result.add(sellService.getSellById(collection.getHouseId()));
            }
        }
        return result;
    }

    /***
     * 根据房屋id获得收藏了该房屋的所有用户id
     * @author tian
     * @return
     */
    public ArrayList<String> getAllUsersId(int houseId ,int houseType){
        ArrayList<String> ids = new ArrayList<>();
        List<Collection> collections = collectionDao.getAllUsersId(houseId,houseType);
        Iterator<Collection> iter = collections.iterator();
        while (iter.hasNext()) {
            Collection collection = iter.next();
            ids.add(collection.getUserId());
        }
        return ids;
    }

//    public ArrayList<String> getCollectionId(String userId,int houseId ,int houseType){
//        ArrayList<String> ids = new ArrayList<>();
//        List<Collection> collections = collectionDao.getCollectionId(userId,houseId,houseType);
//        Iterator<Collection> iter = collections.iterator();
//        while (iter.hasNext()) {
//            Collection collection = iter.next();
//            ids.add(collection.getUserId());
//        }
//        return ids;
//    }
}
