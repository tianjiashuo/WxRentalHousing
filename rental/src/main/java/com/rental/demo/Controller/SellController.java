package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Sell;
import com.rental.demo.Service.RentBo;
import com.rental.demo.Service.SellBo;
import com.rental.demo.Service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin
@RestController
public class SellController {

    @Autowired
    private SellService sellService;

    @PostMapping("/sell/select")
    Set<SellBo> selectSell(@RequestBody Map<String,String> condition){
        return sellService.selectSell(condition);
    }
    @PostMapping("/sell/delete")
    boolean deleteSellInfo(@RequestBody Map<String,Integer> id) {
        return sellService.deleteSellInfo(id.get("id"));
    }

    @PostMapping("/insertSellHouse")
    public int insertSellHouse(@RequestBody Map<String,Object> sell){
        for(Map.Entry<String,Object>entry:sell.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }

        return sellService.insertSellHouse(sell);
    }

    @GetMapping("/sellinfo/{id}")
    SellBo getSellById(@PathVariable int id){return sellService.getSellById(id);}

    @GetMapping ("/sellAllInfo/{id}")
    Map<String,Object>getRentAllById(@PathVariable int id){
        return sellService.getSellAllById(id);
    }

    @PostMapping("/changeSellState/{id}")
    int changeSellState(@PathVariable int id){return sellService.changeState(id);}

    @GetMapping("/sell/all")
    public Set<SellBo> getAllSell(){
        return  sellService.getSellALL();
    }

}
