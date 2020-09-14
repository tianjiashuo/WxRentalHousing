package com.rental.demo.Service;

import com.rental.demo.Controller.ReportVo;
import com.rental.demo.Repository.dao.ReportDao;
import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Report;
import com.rental.demo.Repository.entity.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service("ReportService")
public class ReportService {
    private static final String INIT_RESULT = "0" ;
    private static final String  RESOLVE_RESULT = "1";
    private static final String REPORT_VESOLVED_MSG = "您的举报信息管理员已处理";
    @Autowired
    private ReportDao reportDao;
    @Autowired
    private NewsService newsService;
    @Autowired
    private RentService rentService;
    @Autowired
    private SellService sellService;

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
     * 审批举报并加入举报处理信息
     * @param report
     * @return
     */
    //删除房源
    public boolean  checkReportDelete(Report report) {
        boolean flag1 = reportDao.updateReportState(RESOLVE_RESULT,String.valueOf(report.getId()));
        boolean flag2=false;
        if(getAReport(report.getId()).getHouse_type()==0){
            flag2 = rentService.deleteRentInfo(getAReport(report.getId()).getHouse_id());
        }
        else{
            flag2 = sellService.deleteSellInfo(getAReport(report.getId()).getHouse_id());
        }
        return flag1&&flag2;
    }
    //不做处理
    public boolean  checkReportIgnore(Report report) {
        boolean flag1 = reportDao.updateReportState(RESOLVE_RESULT,String.valueOf(report.getId()));
        return flag1;
    }

    //发送信息
    public boolean addNews(int report_id,String content){
        boolean flag2 = newsService.addNews(getAReport(report_id).getUser_id(),content);
        return flag2;
    }

    /**
     * 查询没有解决的举报
     * @return
     */
    public List<ReportVo> showUnDealReport(){
        List<Report> lr = reportDao.getReportByResult(INIT_RESULT);
        ArrayList<ReportVo> lrv = new ArrayList<>();
        Iterator<Report> iter = lr.iterator();
        while(iter.hasNext()){
            Report report = iter.next();
            //租房信息被举报
            if(report.getHouse_type()==0){
                Rent rent = (Rent)rentService.getRentAllInfo(report.getHouse_id()).get("rentInfo");
                if(rent.getState()!=-1){
                    lrv.add(new ReportVo(report,rent.getTitle()));
                }
            }
            //卖房信息被举报
            else if(report.getHouse_type()==1){
                Sell sell  = (Sell)sellService.getSellAllById(report.getHouse_id()).get("sellInfo");
                if(sell.getIsState()!=-1){
                    lrv.add(new ReportVo(report,sell.getTitle()));
                }
            }
        }
        return lrv;
    }

    /**
     * 更具id 查询举报详细信息
     * @param reportId
     * @return
     */
    public Report getAReport(int reportId){
        return  reportDao.getReportById(reportId);
    }
}
