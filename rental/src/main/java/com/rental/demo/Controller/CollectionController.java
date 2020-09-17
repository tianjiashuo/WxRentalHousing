package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Collection;
import com.rental.demo.Service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class CollectionController {
    @Autowired
    private CollectService collectService;

    @PostMapping("/addCollection")
//    public void addCollection(@RequestBody Collection collection){
//        collectService.addCollection(collection.getUserId(),collection.getHouseId(),collection.getHouseType());
//    }
    public int addCollection(@RequestBody Map<String,String> collection){
        for(Map.Entry<String,String>entry:collection.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
        return collectService.insertSellHouse(collection);
    }

    @DeleteMapping("/cancelCollection/{id}")
    public void cancelCollection(@PathVariable int id){
        collectService.cancelCollection(id);
    }

    @PostMapping("/getCollection/{id}")
    public HashSet getCollection(@PathVariable String id){
        return collectService.getConnectionByUserId(id);
    }

    //根据房屋id获得收藏了该房屋的所有用户id
    @PostMapping("/getUsersId")
    public List<String> getAllUsersId(@RequestBody CollectionVo collectionVo){
        return collectService.getAllUsersId(collectionVo.getHouseId(),collectionVo.getHouseType());
    }

}
