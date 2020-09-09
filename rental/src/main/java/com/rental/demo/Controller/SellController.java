package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Sell;
import com.rental.demo.Service.RentService;
import com.rental.demo.Service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class SellController {

    @Autowired
    private SellService sellService;

    @PostMapping("/sell/select")
    Set<Sell> selectSell(@RequestBody Map<String,String> condition){
        return sellService.selectSell(condition);
    }
    @PostMapping("/sell/delete")
    boolean deleteSellInfo(@RequestBody Map<String,String> id){
        return sellService.deleteSellInfo(id.get("id"));
    }
}
