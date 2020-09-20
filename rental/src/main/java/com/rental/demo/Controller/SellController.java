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

    /*
     *条件筛选出售房屋
     */
    @PostMapping("/sell/select")
    Set<SellBo> selectSell(@RequestBody Map<String,String> condition){
        return sellService.selectSell(condition);
    }

    /*
     *软连接的方式删除售房房源
     */
    @PostMapping("/sell/delete")
    boolean deleteSellInfo(@RequestBody Map<String,Integer> id) {
        return sellService.deleteSellInfo(id.get("id"));
    }

    /*
     *发布出售房源
     */
    @PostMapping("/insertSellHouse")
    public int insertSellHouse(@RequestBody Map<String,Object> sell){
        for(Map.Entry<String,Object>entry:sell.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
        return sellService.insertSellHouse(sell);
    }

    /*
     *根据出售房Id获取售房信息
     */
    @GetMapping("/sellinfo/{id}")
    SellBo getSellById(@PathVariable int id){return sellService.getSellById(id);}

    /*
     *按照Idh获取出售房信息
     */
    @GetMapping ("/sellAllInfo/{id}")
    Map<String,Object>getSellAllById(@PathVariable int id){
        return sellService.getSellAllById(id);
    }

    /*
     *修改出售房状态
     */
    @PostMapping("/changeSellState/{id}")
    int changeSellState(@PathVariable int id){return sellService.changeState(id);}

    /*
     *获取所有的售房信息
     */
    @GetMapping("/sell/all")
    public Set<SellBo> getAllSell(){
        return  sellService.getSellALL();
    }

}
