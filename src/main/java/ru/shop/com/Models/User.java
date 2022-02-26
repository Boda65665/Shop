package ru.shop.com.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {
    public User(){

    }



    double balance = 0.0;
    double rating = 0.0;
    int sales = 0;
    @Size(min = 5, max = 10,message = "Input symbol 5 and 10")
    private String login;
    @Size(min = 5, max = 10,message = "Input symbol 5 and 10")

    private String password;
    @Email(message = "No valid email")
    private String email;

    private int kod_email_confimation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private String email_confimation;

    public int getKod_email_confimation() {
        return kod_email_confimation;
    }

    public void setKodEmailConfimation(int kod_email_confimation) {
        this.kod_email_confimation = kod_email_confimation;
    }

    public String getEmailConfimation() {
        return email_confimation;
    }

    public void setEmailConfimation(String email_confimation) {
        this.email_confimation = email_confimation;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }



    public User(double balance, double rating, int sales,String login, String password, String email, String email_confimation, int id, int kod_email_confimation) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.email_confimation = email_confimation;
        this.id = id;
        this.kod_email_confimation = kod_email_confimation;
        this.balance = balance;
        this.rating = rating;
        this.sales = sales;

    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
