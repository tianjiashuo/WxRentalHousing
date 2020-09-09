package com.rental.demo.Service;

import com.rental.demo.Repository.dao.ImageDao;
import com.rental.demo.Repository.dao.RentDao;
import com.rental.demo.Repository.dao.RoommatesDao;
import com.rental.demo.Repository.dao.UserDao;
import com.rental.demo.Repository.entity.Image;
import com.rental.demo.Repository.entity.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service("RentService")
public class RentService {
    @Autowired
    private RentDao rentDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private RoommatesDao roommatesDao;

    private static final String ILLEGAL_STATE  ="-1";
    private static final String URENT_STATE  ="1";
    private static final String RENT_STATE  ="0";
    /***
     * 租房筛选房源
     * @param condition
     * @return
     */
    public Set<Rent> selectRent(Map<String,String> condition){
        Set<Rent> ans = new HashSet<Rent>();
        //关键字筛选范围
        if(condition.containsKey("key")){
           String keywords = condition.get("key");
           ans.addAll(rentDao.queryByKWords("address",keywords));
           ans.addAll(rentDao.queryByKWords("title",keywords));
           ans.addAll(rentDao.queryByKWords("furniture",keywords));
           ans.addAll(rentDao.queryByKWords("type",keywords));
           ans.addAll(rentDao.queryByKWords("orientation",keywords));
           condition.remove("key");
         }
        //条件筛选范围
        for(Map.Entry<String,String>entry:condition.entrySet()){
            ans.addAll(rentDao.queryByCondt(entry.getKey(),entry.getValue()));
        }
       return ans;
    }

    //获取某个房源的首页展示信息
    public RentBo getRentById(int id){
        Rent rent = rentDao.queryById(id);
        String image = imageDao.getFirstImageById(id,0);
        RentBo rentBo = new RentBo(rent.getId(),rent.getArea(),rent.getPrice(),rent.getAddress(),
                rent.getTitle(),rent.getType(),rent.getFurniture(),image);
        return rentBo;
    }


    /***
     * 删除租房信息（更改房源状态为非法）
     * @param houseId
     * @return
     */
    public boolean deleteRentInfo(String houseId) {
        return rentDao.updateRentState(houseId, ILLEGAL_STATE);
    }
    //发布房源
    public int insertRentHouse(Rent rent){
        return rentDao.insertRentHouse(rent);
    }

    //提交租房申请
    public int addApplication(RoommatesBo roommatesBo){
        return roommatesDao.addApplication(roommatesBo);
    }


    //房东通过租房申请
    public int admitApplication(int id){
        return roommatesDao.admitApplication(id);
    }

    //租满了
    public int changeState(int id){
        return rentDao.changeState(id);
    }


}
