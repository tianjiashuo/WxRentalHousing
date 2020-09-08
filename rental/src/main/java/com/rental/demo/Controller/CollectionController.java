package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Collection;
import com.rental.demo.Service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
public class CollectionController {
    @Autowired
    private CollectService collectService;

    @PostMapping("/addCollection")
    public void addCollection(@RequestBody Collection collection){
        collectService.addCollection(collection.getUserId(),collection.getHouseId(),collection.getHouseType());
    }

    @DeleteMapping("/cancelCollection/{id}")
    public void cancelCollection(@PathVariable int id){
        collectService.cancelCollection(id);
    }

    @PostMapping("/getCollection/{id}")
    public HashSet getCollection(@PathVariable int id){
        return collectService.getConnectionByUserId(id);
    }

    @PostMapping("/getUsersId")
    public List<Integer> getAllUsersId(@RequestBody CollectionVo collectionVo){
        return collectService.getAllUsersId(collectionVo.getHouseId(),collectionVo.getHouseType());
    }

}
