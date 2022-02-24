package ru.shop.com.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {
    public User(){

    }
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


    public User(String login, String password, String email,String email_confimation,int id,int kod_email_confimation) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.email_confimation = email_confimation;
        this.id = id;
        this.kod_email_confimation = kod_email_confimation;


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
