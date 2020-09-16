package com.rental.demo.Service;

import com.rental.demo.Repository.entity.Rent;

public class RentBo {

    private int id;
   // private int host_id;
    private int area;
    private int price;
    private String address;
    private String title;
    private String type;
    private String furniture;
    private String imageUrl;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
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

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public RentBo(int id, int area, int price, String address, String title, String type, String furniture, String imageUrl) {
        this.id = id;
        this.area = area;
        this.price = price;
        this.address = address;
        this.title = title;
        this.type = type;
        this.furniture = furniture;
        this.imageUrl = imageUrl;
    }

    public RentBo(int id, int area, int price, String address, String title, String type, String furniture, String imageUrl,int state) {
        this.id = id;
        this.area = area;
        this.price = price;
        this.address = address;
        this.title = title;
        this.type = type;
        this.furniture = furniture;
        this.imageUrl = imageUrl;
        this.state = state;
    }

    public  RentBo(Rent rent,String image){
        this.id = rent.getId();
        this.area = rent.getArea();
        this.price = rent.getPrice();
        this.address = rent.getAddress();
        this.title =rent.getTitle();
        this.imageUrl =image;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result += id*6 + address.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RentBo)){
            return false;
        }else{
            RentBo robj = (RentBo)obj;
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
