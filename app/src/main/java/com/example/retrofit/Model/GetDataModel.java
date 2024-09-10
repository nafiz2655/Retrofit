package com.example.retrofit.Model;

public class GetDataModel {
    private String id;
    private String name;
    private String roll;
    private String reg;
    private String sub;
    private String phone;
    private String image;
    private String address;


    public GetDataModel() {
    }

    public GetDataModel(String id, String name, String roll, String rej, String image, String phone, String sub, String address) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.reg = rej;
        this.image = image;
        this.phone = phone;
        this.sub = sub;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getRej() {
        return reg;
    }

    public void setRej(String rej) {
        this.reg = rej;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
