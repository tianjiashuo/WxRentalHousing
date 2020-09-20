package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Report;
import com.rental.demo.Service.RentService;
import com.rental.demo.Service.ReportService;
import com.rental.demo.Service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ReportController {
    @Autowired
    ReportService reportService;
    @Autowired
    SellService sellService;
    @Autowired
    RentService rentService;

    /*
     *增加举报
     */
    @PostMapping("/addReport")
    public boolean addReport (@RequestBody Report report) {
       return  reportService.addReport(report);
    }
    /*
     *删除举报的房源信息
     */
    @PostMapping("/checkReportDelete")
    public boolean checkReportDelete(@RequestBody Report report){
        return reportService.checkReportDelete(report);
    }
    /*
     * 举报的房源信息不做处理
     */
    @PostMapping("/checkReportIgnore")
    public boolean checkReportIgnore(@RequestBody Report report){
        return reportService.checkReportIgnore(report);
    }
    /*
     *回复举报的信息
     */
  @PostMapping("/addNews/{id}")
    public boolean addNews(@PathVariable int id, @RequestBody String content){
        return reportService.addNews(id,content);
    }
    /*
     *获取没有处理的举报
     */
    @GetMapping("/unDealReport")
    public Map<String,Object> showUndealReport(){
        HashMap hm = new HashMap();
        hm.put("code",0);
        hm.put("data",reportService.showUnDealReport());
        return hm;
    }
    /*
     *根据id展示某个举报
     */
    @PostMapping ("/showReport")
    public Report showReport(@RequestBody Map<String,Integer> id){
        return reportService.getAReport(id.get("id"));
    }

}
