package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Report;
import com.rental.demo.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReportController {
    @Autowired
    ReportService reportService;

    @PostMapping("/addReport")
    public boolean addReport (@RequestBody Report report) {
       return  reportService.addReport(report);
    }
    @PostMapping("/checkReport")
    public boolean checkReport(@RequestBody Report report){
        return reportService.checkReport(report);
    }
    @GetMapping("/unDealreport")
    public List<Report> showUndealReport(){
        return reportService.showUnDealReport();
    }
    @PostMapping ("/showReport")
    public Report showReport(@RequestBody Map<String,String > id){
        return reportService.getAReport(id.get("id"));
    }

}
