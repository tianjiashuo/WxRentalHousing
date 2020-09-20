package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Roommates;
import com.rental.demo.Service.RentBo;
import com.rental.demo.Service.RentService;
import com.rental.demo.Service.RoommatesBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.core.InteractionCallback;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class RentController {
    @Autowired
    private RentService rentService;

    /**
     *筛选租房
     */
    @PostMapping ("/rent/select")
    public List<Set<RentBo>> selectRent(@RequestBody Map<String,String> condition){
        for(Map.Entry<String,String>entry:condition.entrySet()){
           System.out.println(entry.getKey()+"22222222"+entry.getValue());
        }

        return rentService.conditionSelectRentAll(condition);
    }

    /**
     *软连接的方式删除租房房源
     */
    @PostMapping("rent/delete")
    public boolean deleteRentinfo(@RequestBody Map<String,Integer> id) {
        return rentService.deleteRentInfo(id.get("id"));
    }

    /**
     * 增加租房
     * @param rent
     * @return
     */
    @PostMapping("/insertRentHouse")
    public int insertRentHouse(@RequestBody Map<String,Object> rent){
        for(Map.Entry<String,Object>entry:rent.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
        return rentService.insertRentHouse(rent);
    }

    /**
     * 按照Id查找首页房源信息
     * @param id
     * @return
     */
    @GetMapping ("/rentInfo/{id}")
    RentBo getRentById (@PathVariable int id){
        return  rentService.getRentById(id);
    }

    /**
     * 按照Id查找租房的详细信息
     * @param id
     * @return
     */
    @GetMapping ("/rentAllInfo/{id}")
    Map<String,Object> getRentAllById(@PathVariable int id){
        return rentService.getRentAllInfo(id);
    }

    /**
     * 按照房屋Id查找合租信息
     * @param id
     * @return
     */
    @GetMapping ("/roommatesinfo/{id}")
    List<Roommates> getRoommatesById(@PathVariable int id){return  rentService.getRoommatesById(id);}

    /**
     * 增加合租申请
     * @param roommatesBo
     * @return
     */
    @PostMapping ("/addApplocation")
    int addApplocation(@RequestBody RoommatesBo roommatesBo){
        System.out.printf("id--------"+roommatesBo.getUserId());
        System.out.println("house_id--------"+ roommatesBo.getHouseId());
        return rentService.addApplication(roommatesBo);}

    /**
     * 按照租房申请id通过申请
     */
    @PostMapping("/admitApplication/{id}")
    int admitApplication(@PathVariable int id){return rentService.admitApplication(id);}

    /**
     * 按照租房申请Id拒绝申请
     */

    @PostMapping("/refuseApplication/{id}")
    int refuseApplication(@PathVariable int id){
        return rentService.refuseApplication(id);
    }

    /**
     * 修改出租房源状态为下架
     * @param id
     * @return
     */
    @PostMapping("/changeRentState/{id}")
    int changeRentState(@PathVariable int id){return rentService.changeState(id);}

    /**
     * 按照全部/整租/合租类型获取首页房源信息
     * @return
     */
    @GetMapping("/rent/all")
    public List<Set<RentBo>> getAllRent(){
        return  rentService.getRentALL();
    }


}
