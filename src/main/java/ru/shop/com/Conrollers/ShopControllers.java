package ru.shop.com.Conrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shop.com.DAO.UserDAO;
import ru.shop.com.email.Mail;

@Controller
@RequestMapping("/shop")
public class ShopControllers {
    private final UserDAO userDAO;

    @Autowired
    public ShopControllers(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
