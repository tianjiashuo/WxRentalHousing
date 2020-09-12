package com.rental.demo.Controller;


public class UserVo {
    private String id;
//    private String head;
    private String nickname;
    private String introduction;
    private String phone;
    private boolean gender;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
//    public String getHead(){
//        return head;
//    }
//    public void setHead(String head){
//        this.head = head;
//    }
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

    UserVo( String id,String head,String nickname,String introduction,String phone,boolean gender){
        this.id = id;
//        this.head = head;
        this.nickname = nickname;
        this.introduction = introduction;
        this.phone = phone;
        this.gender = gender;
    }
}
