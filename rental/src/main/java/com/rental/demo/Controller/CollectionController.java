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


    /***
     * 增加收藏信息
     * @param collection
     * @return
     */
    @PostMapping("/addCollection")
    public int addCollection(@RequestBody Map<String,String> collection){
        for(Map.Entry<String,String>entry:collection.entrySet()){
            System.out.println(entry.getKey()+","+entry.getValue());
        }
        return collectService.insertSCollectHouse(collection);
    }

    /***
     * 取消收藏信息
     * @param id
     */
    @DeleteMapping("/cancelCollection/{id}")
    public void cancelCollection(@PathVariable int id){
        collectService.cancelCollection(id);
    }

    /***
     *
     * 获取某个用户的收藏信息
     * @param id
     * @return
     */
    @PostMapping("/getCollection/{id}")
    public HashSet getCollection(@PathVariable String id){
        return collectService.getConnectionByUserId(id);
    }

    /***
     * 根据房屋id获得收藏了该房屋的所有用户id
     */
    @PostMapping("/collectionChanged")
    public String collectionChanged(@RequestBody Map<String,Integer> res){

        return collectService.collectInfoChanged(res.get("houseId"),res.get("houseType"));
    }

}
