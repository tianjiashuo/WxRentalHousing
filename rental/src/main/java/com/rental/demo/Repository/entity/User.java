package com.rental.demo.Repository.entity;


public class User {
    private int id;
    private String head;
    private String nickname;
    private String introduction;
    private String password;
    private String phone;
    private boolean gender;
    private String IDnumber;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getHead(){
        return head;
    }
    public void setHead(String head){
        this.head = head;
    }
    public String getNickname(){
        return nickname;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getIntroduction(){
        return  introduction;
    }
    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = this.password;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone= this.phone;
    }
    public boolean getGender(){
        return gender;
    }
    public void setGender(boolean gender){
        this.gender = gender;
    }
    public String getIDnumber(){
        return IDnumber;
    }
    public void setIDnumber(String IDnumber){
        this.IDnumber = IDnumber;
    }
}
