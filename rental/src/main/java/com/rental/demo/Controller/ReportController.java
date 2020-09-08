package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Report;
import com.rental.demo.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @Autowired
    ReportService reportService;

    @PostMapping("/addReport")
    public boolean addReport (@RequestBody Report report) {
       return  reportService.addReport(report);
    }
}
