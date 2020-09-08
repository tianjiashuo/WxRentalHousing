package com.rental.demo.Repository.entity;


public class Rent {
    private int id;
    private int hostId;
    private int shortestLease;
    private int area;
    private int price;
    private  int floor;
    private String address;
    private String title;
    private String type;
    private String orientation;
    private String furniture;
    private boolean isElevator;
    private boolean isPet;
    private boolean state;
    private boolean form ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getShortestLease() {
        return shortestLease;
    }

    public void setShortestLease(int shortestLease) {
        this.shortestLease = shortestLease;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public boolean getIsElevator() {
        return isElevator;
    }

    public void setIsElevator(boolean elevator) {
        isElevator = elevator;
    }

    public boolean getIsPet() {
        return isPet;
    }

    public void setIsPet(boolean pet) {
        isPet = pet;
    }

    public boolean getIsState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getIsForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }
    @Override
    public int hashCode() {
        int result = title.hashCode();
        result += id*6 + hostId*9;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rent)){
            return false;
        }else{
            Rent robj = (Rent)obj;
            if(this==robj){
                return true;
            }
            if(robj.id==(this.id)){
                return true;
            }else{
                return false;
            }
        }
    }
}
