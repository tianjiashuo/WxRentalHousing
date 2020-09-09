package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
public class RentController {
    @Autowired
    private RentService rentService;

    @PostMapping ("/rent/select")
     Set<Rent> selectRent(@RequestBody Map<String,String> condition){
        return rentService.selectRent(condition);
    }
    @PostMapping("rent/delete")
     boolean deleteRentinfo(@RequestBody Map<String,String> id){
        return rentService.deleteRentInfo(id.get("id"));
    }
}
