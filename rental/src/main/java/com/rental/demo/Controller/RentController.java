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

    @PostMapping ("/rent/select")
    public List<Set<RentBo>> selectRent(@RequestBody Map<String,String> condition){
        for(Map.Entry<String,String>entry:condition.entrySet()){
           System.out.println(entry.getKey()+"22222222"+entry.getValue());
        }

        return rentService.conditionSelectRentAll(condition);
    }
    @PostMapping("rent/delete")
    public boolean deleteRentinfo(@RequestBody Map<String,Integer> id) {
        return rentService.deleteRentInfo(id.get("id"));
    }

    @PostMapping("/insertRentHouse")
    public int insertRentHouse(@RequestBody Map<String,Object> rent){
        for(Map.Entry<String,Object>entry:rent.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
        return rentService.insertRentHouse(rent);
    }

    @GetMapping ("/rentInfo/{id}")
    RentBo getRentById (@PathVariable int id){
        return  rentService.getRentById(id);
    }

    @GetMapping ("/rentAllInfo/{id}")
    Map<String,Object> getRentAllById(@PathVariable int id){
        return rentService.getRentAllInfo(id);
    }

    @GetMapping ("/roommatesinfo/{id}")
    List<Roommates> getRoommatesById(@PathVariable int id){return  rentService.getRoommatesById(id);}

    @PostMapping ("/addApplocation")
    int addApplocation(@RequestBody RoommatesBo roommatesBo){
        System.out.printf("id--------"+roommatesBo.getUserId());
        System.out.println("house_id--------"+ roommatesBo.getHouseId());
        return rentService.addApplication(roommatesBo);}

    //通过申请
    @PostMapping("/admitApplication/{id}")
    int admitApplication(@PathVariable int id){return rentService.admitApplication(id);}

    //拒绝申请
    @PostMapping("/refuseApplication/{id}")
    int refuseApplication(@PathVariable int id){
        return rentService.refuseApplication(id);
    }





    @PostMapping("/changeRentState/{id}")
    int changeRentState(@PathVariable int id){return rentService.changeState(id);}

    @GetMapping("/rent/all")
    public List<Set<RentBo>> getAllRent(){
        return  rentService.getRentALL();
    }


}
