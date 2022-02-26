package ru.shop.com.Models;

import javax.validation.constraints.NotNull;

public class Good{
    public Good(){};
    @NotNull
    String name;
    Double price;
    String img;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;
    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



}
