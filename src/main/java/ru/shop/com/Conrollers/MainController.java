package ru.shop.com.Conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.shop.com.DAO.UserDAO;
import ru.shop.com.Models.User;
import ru.shop.com.email.Mail;
import java.lang.Math;
import javax.mail.MessagingException;
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

    @GetMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        return "main/login";
    }
    @PostMapping("/login")
    public String sing_in(@ModelAttribute("user") User user,Model model) throws MessagingException, IOException {
        User usere;
        System.out.println(user);
        usere = UserDAO.user_search(user.getEmail());
        if (usere==null){
            System.out.println("1");

            model.addAttribute("error","incorrect email address or password!");
            return "main/login";
        }
        if (user.getEmail().equals(usere.getEmail()) && user.getPassword().equals(usere.getPassword())){
            int min = 111111;
            int max = 999999;
            final int rnd = rnd(min, max);
            String text = String.valueOf(rnd);
            Mail.sendEmail("Ващ код: " + text,user.getEmail());
            return "redirect:/email";
        }
        model.addAttribute("error","incorrect email address or password!");
        System.out.println("2");
        System.out.println(usere.getEmail()+ " "+ user.getEmail()+ " "+user.getPassword()+ " " +usere.getPassword());
        return "main/login";

    }


    @GetMapping("")
    public String newPerson(@ModelAttribute("user") User user) throws MessagingException, IOException {
        System.out.println("1");
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