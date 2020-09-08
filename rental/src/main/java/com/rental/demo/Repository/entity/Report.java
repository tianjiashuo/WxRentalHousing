package com.rental.demo.Repository.entity;

public class Report {
    private int id;
    private int user_id;
    private int house_id;
    private int house_type;
    private String content;
    private boolean result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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



}
