package ru.shop.com.Conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shop.com.DAO.UserDAO;

import java.util.Base64;
import java.util.HashMap;
import ru.shop.com.Models.User;
import ru.shop.com.email.Mail;
import java.lang.Math;
import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class MainController {

    private final UserDAO userDAO;
    private final Mail email;

    @Autowired
    public MainController(UserDAO userDAO, Mail mail) {
        this.userDAO = userDAO;
        this.email = mail;
    }
    @GetMapping("logout")
    public String logout(HttpServletRequest request, HttpServletResponse response,
                         String cookieName,String cookieDomain){
        Cookie name = new Cookie("JWT", "");
        name.setMaxAge(0);
        name.setPath("");
        response.addCookie(name);

        return "redirect:/";
    }
    @PostMapping("/ConfimationEmail/{email}")
    public String confirmation_post(HttpServletRequest request, HttpServletResponse response,Model model,@RequestParam("kod") int kod,@PathVariable("email") String email) {
            User user_search;
            user_search = userDAO.user_search(email+".com");
            System.out.println(kod);
            if ( kod == user_search.getKod_email_confimation()){
                Cookie cookie = new Cookie("JWT", JWT.jwt_create(user_search.getEmail()));
                cookie.setMaxAge(3600);
                response.addCookie(cookie);

                user_search.setEmailConfimation("True");
                userDAO.update(user_search.getId(),user_search);
                return "redirect:/shop";
            }
            else {
                model.addAttribute("error","Incorrect kod!");
                return "main/email_confirmation";
            }


    }

    @GetMapping("/ConfimationEmail")
    public String confirmation(Model model,@RequestParam("email") String email, @ModelAttribute("user") User user) {
        User user_search;

        user_search = userDAO.user_search(email);
        System.out.println(email);
        if (email != null) {
            if (user_search.getEmailConfimation().equals("None")) {
                model.addAttribute("email",email);
                model.addAttribute("user",user_search);
                return "main/email_confirmation";

            }

            return "redirect:/shop";
        }
        return "redirect:/error";

    }
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("user") User user) {
        Cookie[] cookies = request.getCookies();
        String cookieName = "JWT";
        Cookie cookie = null;
        System.out.println(1234);

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    System.out.println(123);
                    cookie = c;
                    return "redirect:/shop";

                }

            }
            return "main/login";

        }
        return "main/login";

    }

    @PostMapping("/login")
    public String sing_in(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user, Model model) throws MessagingException, IOException {
        User usere;
        System.out.println(user);
        usere = UserDAO.user_search(user.getEmail());
        if (usere == null) {
            System.out.println("1");

            model.addAttribute("error", "incorrect email address or password!");
            return "main/login";
        }
        if (user.getEmail().equals(usere.getEmail()) && user.getPassword().equals(usere.getPassword())) {

            if (usere.getEmailConfimation().equals("None")) {
                int min = 111111;
                int max = 999999;
                final int rnd = rnd(min, max);
                String text = String.valueOf(rnd);
                usere.setKodEmailConfimation(rnd);


                System.out.println(usere.getKod_email_confimation());
                Mail.sendEmail("Ващ код: " + text, usere.getEmail());
                userDAO.update(usere.getId(),usere);
                return "redirect:/ConfimationEmail?email="+usere.getEmail();
            } else {
                System.out.println("CookieControllerExample writeCookieExample() is called");
                Cookie cookie = new Cookie("JWT", JWT.jwt_create(user.getEmail()));
                cookie.setMaxAge(3600);
                response.addCookie(cookie);
                return "redirect:/shop";
            }

        }
        model.addAttribute("error", "incorrect email address or password!");
        System.out.println("2");
        System.out.println(usere.getEmail() + " " + user.getEmail() + " " + user.getPassword() + " " + usere.getPassword());
        return "main/login";

    }


    @GetMapping("")
    public String newPerson(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("user") User user) throws MessagingException, IOException {
        Cookie[] cookies = request.getCookies();
        String cookieName = "JWT";
        Cookie cookie = null;
        System.out.println(1234);

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    System.out.println(123);
                    cookie = c;
                    return "redirect:/shop";

                }
                return "main/sing_up";
            }
            return "main/sing_up";
        }
        return "main/sing_up";
    }
    @PostMapping()
    public String user_new(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "main/sing_up";

        } else {


            if (UserDAO.user_search(user.getEmail()) == null) {
                int id;
                id = userDAO.id_user();
                UserDAO.new_user(user, id);
                return "redirect:/login";
            } else {
                model.addAttribute("error", "This user already exists");
                return "main/sing_up";
            }
        }
    }
    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    }