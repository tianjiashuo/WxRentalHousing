package com.rental.demo.Repository.entity;


public class Sell{
    private int id;
    private int hostId;
    private int area;
    private int price;
    private  int floor;
    private String address;
    private String title;
    private String type;
    private String orientation;
    private boolean property;
    private boolean isElevator;
    private boolean isRenovation;
    private boolean state;


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

    public boolean getIsProperty() {
        return property;
    }

    public void setProperty(boolean property) {
        this.property = property;
    }

    public boolean getIsElevator() {
        return isElevator;
    }

    public void setIsElevator(boolean elevator) {
        isElevator = elevator;
    }

    public boolean getIsRenovation() {
        return isRenovation;
    }

    public void setIsRenovation(boolean renovation) {
        isRenovation = renovation;
    }

    public boolean getIsState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
