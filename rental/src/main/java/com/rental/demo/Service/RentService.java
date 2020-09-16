package com.rental.demo.Service;

import com.rental.demo.Repository.dao.ImageDao;
import com.rental.demo.Repository.dao.RentDao;
import com.rental.demo.Repository.dao.RoommatesDao;
import com.rental.demo.Repository.dao.UserDao;
import com.rental.demo.Repository.entity.Image;
import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Roommates;
import org.omg.CORBA.INTERNAL;
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
    private static final String HOUSE_TYPE_RENT ="0";
    private static final boolean IS_SHARE_RENT_FORM =true;
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
            //System.out.println(keywords);
           ans.addAll(rentDao.queryByKWords("address",keywords));
           ans.addAll(rentDao.queryByKWords("title",keywords));
           ans.addAll(rentDao.queryByKWords("furniture",keywords));
           ans.addAll(rentDao.queryByKWords("type",keywords));
           ans.addAll(rentDao.queryByKWords("orientation",keywords));
           condition.remove("key");
         }
        //条件筛选范围
        for(Map.Entry<String,String>entry:condition.entrySet()){
            if(entry.getValue()=="true"){
                ans.addAll(rentDao.queryByCondt(entry.getKey(),entry.getValue()));
                System.out.println(ans.size());
            }

        }
       return ans;
    }

    //获取某个房源的首页展示信息
    public RentBo getRentById(int id){
        try{
            Rent rent = rentDao.queryById(id);
            String image = imageDao.getFirstImageById(id,0);
            RentBo rentBo = new RentBo(rent.getId(),rent.getArea(),rent.getPrice(),rent.getAddress(),
                    rent.getTitle(),rent.getType(),rent.getFurniture(),image);
            return rentBo;
        }catch(Exception e ){
            System.out.println(e.toString());
            return  null;
        }

    }

    public RentBo getRentByIdHost(int id){
        try{
            Rent rent = rentDao.queryById(id);
            String image = imageDao.getFirstImageById(id,0);
            RentBo rentBo = new RentBo(rent.getId(),rent.getArea(),rent.getPrice(),rent.getAddress(),
                    rent.getTitle(),rent.getType(),rent.getFurniture(),image,rent.getState());
            return rentBo;
        }catch(Exception e ){
            System.out.println(e.toString());
            return  null;
        }

    }
    /***
     * 查看详细租房信息
     * @param rentId
     * @return
     */
    public Map<String,Object> getRentAllInfo(int rentId){
        Map<String,Object>map = new HashMap<String, Object>();
        Rent rent = rentDao.queryById(rentId);
        map.put("rentInfo",rent);
        List<Image>images = imageDao.getAllImageById(rent.getId(), Integer.parseInt(HOUSE_TYPE_RENT));
        map.put("imageList",images);
        return map;
    }


    /***
     * 删除租房信息（更改房源状态为非法）
     * @param houseId
     * @return
     */
    public boolean deleteRentInfo(int houseId) {
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

    //获取合租信息
    public RoommatesBo getRoomatesById(int id){
        Roommates roommates = roommatesDao.queryById(id);
        RoommatesBo roommatesBo = new RoommatesBo(roommates.getId(),roommates.getUserId(),
                roommates.getHouseId(),roommates.getState());
        return roommatesBo;

    }

    /***
     * 获得所有分类的rent信息
     * mao 2020-09-14
     * @return
     */
    public  List<Set<RentBo>>getRentALL() {
        try{
            List list =  rentDao.queryAll();
            return collectionInfo(list.iterator());
        }catch(Exception e){
            System.out.println(e.toString());
            return null;
        }

    }

    /**
     * 获得所有分类的筛选的rent信息
     * @param condition
     * @return
     */
    public List<Set<RentBo>>conditionSelectRentAll(Map<String,String> condition){
        Set tmpSet = selectRent(condition);

        return collectionInfo(tmpSet.iterator());
    }

    /**
     * 分类筛选信息为全部、整租、合租
     * @param it
     * @return
     */
    private List<Set<RentBo>> collectionInfo(Iterator<Rent> it){
        Set allSet = new HashSet();
        Set aSet = new HashSet();
        Set shareSet = new HashSet();
        while(it.hasNext()){
            Rent rent = it.next();
            //设置默认图片防止查不出来
            String image= "https://z1.muscache.cn/im/pictures/83177158/9e5c500b_original.jpg?aki_policy=large";
            try{
                image = imageDao.getFirstImageById(rent.getId(),0);
            }catch (Exception e){
                System.out.println(e.toString());
            }
            RentBo bo = new RentBo(rent,image);
            allSet.add(bo);
            if(rent.getIsForm()){//是合租
                shareSet.add(bo);
            }else{
                aSet.add(bo);//整租
            }
        }
        ArrayList ans = new ArrayList();
        ans.add(0,allSet);
        ans.add(1,aSet);
        ans.add(2,shareSet);
        return ans;
    }


}
