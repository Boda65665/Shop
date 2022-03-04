package ru.shop.com.Conrollers;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shop.com.DAO.UserDAO;
import ru.shop.com.Models.User;
import ru.shop.com.email.Mail;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.channels.FileChannel;

@RequestMapping(value = "/user")
@Controller
public class UserController {
    private final UserDAO userDAO;
    private final Mail email;

    @Autowired
    public UserController(UserDAO userDAO, Mail mail) {
        this.userDAO = userDAO;
        this.email = mail;
    }

    @GetMapping("/profile{id}")
    public String prof(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("id") int id) throws ServletException, IOException {
                    String email = GetDataJWT.getEmail(request,response);
                    User user = UserDAO.user_search(email);
                    System.out.println(email);
                    User user_search = UserDAO.user_search_id(id);
                    System.out.println(user.getSales());
                    System.out.println(user_search.getSales());
                    model.addAttribute("href_goods","user/"+id+"/goods");
                    model.addAttribute("href_reviews","user/"+id+"/reviews");
                    if (user_search.equals(user)){

                        model.addAttribute("user",user);
                        return "user/prof";

                    }
                    model.addAttribute("user",user_search);

                    return "user/prof";


                }


    public String goods(){
        return "user/goods";
    }
}
