package com.rental.demo.Repository.entity;


public class User {
    private String id;
    private String head;
    private String nickname;
    private String introduction;
    private String password;
    private String phone;
    private boolean gender;
    private String idNumber;

    public String getId() {
        return id;
    }
    public void setId(String id) {
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
        this.password = password;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone= phone;
    }
    public boolean getGender(){
        return gender;
    }
    public void setGender(boolean gender){
        this.gender = gender;
    }
    public String getIdNumber(){
        return idNumber;
    }
    public void setIdNumber(String idNumber){
        this.idNumber = idNumber;
    }
}
