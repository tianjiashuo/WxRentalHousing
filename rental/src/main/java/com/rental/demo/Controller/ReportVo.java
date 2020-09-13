package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Report;

public class ReportVo {
    private int id;
    private String user_id;
    private int house_id;
    private int house_type;
    private String content;
    private boolean result;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public int getHouse_type() {
        return house_type;
    }

    public void setHouse_type(int house_type) {
        this.house_type = house_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public ReportVo(Report report, String title){
        this.id=report.getId();
        this.user_id = report.getUser_id();
        this.house_id = report.getHouse_id();
        this.house_type = report.getHouse_type();
        this.content = report.getContent();
        this.result = report.getResult();
        this.title = title;

    }
}
