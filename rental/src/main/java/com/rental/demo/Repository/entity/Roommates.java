package com.rental.demo.Repository.entity;

public class Roommates {
    private int id;
    private String userId;
    private int houseId;
    private int state;

    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getUserId(){return userId;}

    public void setUserId(String userId){this.userId = userId;}

    public int getHouseId(){return houseId;}

    public void  setHouseId(int houseId){this.houseId = houseId;}

    public int getState(){return state;}

    public void setState(int state){this.state = state;}
}
