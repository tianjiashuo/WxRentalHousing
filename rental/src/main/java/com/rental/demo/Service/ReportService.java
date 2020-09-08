package com.rental.demo.Service;

import com.rental.demo.Repository.dao.ReportDao;
import com.rental.demo.Repository.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service("ReportService")
public class ReportService {
    private static final String INIT_RESULT = "0" ;
    private static final String  RESOLVE_RESULT = "1";
    @Autowired
    private ReportDao reportDao;

    /**
     * 增加举报
     * @param report
     * @return
     * @throws IllegalAccessException
     */
    public boolean addReport(Report report)  {

       List keys = new ArrayList<String>();
       List values = new ArrayList<String>();
        Field[] field = report.getClass().getDeclaredFields();
        for(int i=0;i<field.length;i++){
            field[i].setAccessible(true);
            try{
                if(field[i].getName()!="id"){
                    keys.add(field[i].getName());
                    if(field[i].getName()=="result") {
                        values.add(INIT_RESULT);
                    }else{
                        values.add(field[i].get(report).toString());
                    }
                }
            }catch (Exception e ){
                e.getStackTrace();
            }
        }
      //  System.out.println("INSERT INTO report (" +String.join(",",keys) + ") VALUES ('"+ String.join("','",values)+"')");
        return reportDao.addReport(keys,values);
    }

    /***
     * 审批举报
     * @param report
     * @return
     */
    public boolean  checkReport(Report report) {
      return  reportDao.updateReportState(RESOLVE_RESULT,String.valueOf(report.getId()));
    }

    /**
     * 查询没有解决的举报
     * @return
     */
    public List<Report> showUnDealReport(){
        return reportDao.getReportByResult(INIT_RESULT);
    }

    /**
     * 更具id 查询举报详细信息
     * @param reportId
     * @return
     */
    public Report getAReport(String reportId){
        return  reportDao.getReportById(reportId);
    }
}
