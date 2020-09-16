package com.rental.demo.Service;

import com.rental.demo.Repository.entity.Rent;
import com.rental.demo.Repository.entity.Sell;

import java.text.DecimalFormat;

public class SellBo {
    private int id;
    private int area;
    private String price;
    private String address;
    private String title;
    private String type;
    private boolean isRenovation;
    private String imageUrl;

    private DecimalFormat df   = new DecimalFormat("######0.00");
    public SellBo(int id, int area, int price, String address, String title, String type, boolean isRenovation, String imageUrl) {
        this.id = id;
        this.area = area;
        this.price = df.format(price/10000);
        this.address = address;
        this.title = title;
        this.type = type;
        this.isRenovation = isRenovation;
        this.imageUrl = imageUrl;
    }

    public  SellBo(Sell s,String img){
        this.id = s.getId();
        this.area = s.getArea();
        this.price = df.format(s.getPrice()/10000);
        this.address = s.getAddress();
        this.title = s.getTitle();
        this.imageUrl = img;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
    @Override
    public int hashCode() {
        int result = title.hashCode();
        result += id*6 +address.hashCode() ;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SellBo)){
            return false;
        }else{
            SellBo sobj = (SellBo)obj;
            if(this==sobj){
                return true;
            }
            if(sobj.id==(this.id)){
                return true;
            }else{
                return false;
            }
        }
    }
}
