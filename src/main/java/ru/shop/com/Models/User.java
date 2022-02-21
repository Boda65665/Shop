package ru.shop.com.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class User {
    public static int idUser;
    public User(){

    }
    @Size(min = 5, max = 10,message = "Input symbol 5 and 10")
    private String login;
    @Size(min = 5, max = 10,message = "Input symbol 5 and 10")

    private String password;
    @Email(message = "No valid email")
    private String email;
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
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
