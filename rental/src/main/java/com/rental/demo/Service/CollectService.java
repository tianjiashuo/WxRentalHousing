package com.rental.demo.Service;

import com.rental.demo.Repository.dao.CollectionDao;
import com.rental.demo.Repository.dao.RentDao;
import com.rental.demo.Repository.dao.SellDao;
import com.rental.demo.Repository.entity.Collection;
import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Sell;
import com.rental.demo.Repository.mappers.RentRowMapper;
import com.rental.demo.utils.xiaomageUtil;
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
    @Autowired
    private UserService userService;



//    public void addCollection(String userId,int houseId,int houseType){
//        collectionDao.addCollection(userId,houseId,houseType);
//    }


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
                RentBo rentBo = rentService.getRentByIdHost(collection.getHouseId());
                if(rentBo.getState()==1){
                    result.add(rentBo);
                }
            }
            //出售房屋
            else if(collection.getHouseType()==1){
                SellBo sellBo = sellService.getSellById(collection.getHouseId());
                if(sellBo.getState()==1){
                    result.add(rentService.getRentByIdHost(collection.getHouseId()));
                }
                result.add(sellBo);
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

    /***
     * 收藏信息更改后变换状态
     */

    public String collectInfoChanged(int houseId,int houseType){
        //拿到提示的所有用户的id;
        List idList = getAllUsersId(houseId,houseType);
        Iterator<String> it = idList.iterator();
        List phones = new ArrayList();
        while(it.hasNext()){
            String s = it.next();
            UserBo u = userService.getUserById(s);
            phones.add(u.getPhone());
        }
       Map<String,Object> sms = new HashMap<>();
        sms.put("smsType","news");
        sms.put("phone",phones);
        System.out.println(phones.toString());
        if(houseType==0){
            String title = rentService.getRentById(houseId).getTitle();
            sms.put("param",title);
        }else {
            String title = sellService.getSellById(houseId).getTitle();
            sms.put("param",title);
        }
        String response = xiaomageUtil.sendSMS(sms);
        return response;
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

    public int insertSellHouse(Map<String,String> sell) {

        List keys = new ArrayList<String>();
        List values = new ArrayList<String>();
        //处理其他数据values 均为String
        for (Map.Entry<String, String> Entry : sell.entrySet()) {
            keys.add(Entry.getKey());
            String flag = Entry.getValue();
            values.add(flag);
        }
        //获得house_id主键
        int collectionId = collectionDao.addCollection(keys,values);
        return collectionId;
}
}
