package com.rental.demo.Repository.entity;


public class Rent {
    private int id;
    private int host_id;
    private int shortest_lease;
    private int area;
    private int price;
    private  int floor;
    private String address;
    private String title;
    private String type;
    private String orientation;
    private String furniture;
    private boolean is_elevator;
    private boolean is_pet;
    private boolean state;
    private boolean form ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHost_id() {
        return host_id;
    }

    public void setHost_id(int host_id) {
        this.host_id = host_id;
    }

    public int getShortest_lease() {
        return shortest_lease;
    }

    public void setShortest_lease(int shortest_lease) {
        this.shortest_lease = shortest_lease;
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

    public boolean isIs_elevator() {
        return is_elevator;
    }

    public void setIs_elevator(boolean is_elevator) {
        this.is_elevator = is_elevator;
    }

    public boolean isIs_pet() {
        return is_pet;
    }

    public void setIs_pet(boolean is_pet) {
        this.is_pet = is_pet;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }




}
