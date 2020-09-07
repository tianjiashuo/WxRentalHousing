package com.rental.demo.Service;

public class SellBo {
    private int id;
    //private int hostId;
    private int area;
    private int price;
    private String address;
    private String title;
    private String type;
    private boolean isRenovation;
    private String imageUrl;

    public SellBo(int id, int area, int price, String address, String title, String type, boolean isRenovation, String imageUrl) {
        this.id = id;
        this.area = area;
        this.price = price;
        this.address = address;
        this.title = title;
        this.type = type;
        this.isRenovation = isRenovation;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean getisRenovation() {
        return isRenovation;
    }

    public void setRenovation(boolean renovation) {
        isRenovation = renovation;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
