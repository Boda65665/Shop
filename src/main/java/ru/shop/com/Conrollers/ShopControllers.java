package ru.shop.com.Conrollers;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shop.com.DAO.UserDAO;
import ru.shop.com.Models.Good;
import ru.shop.com.email.Mail;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/shop")
public class ShopControllers {
    private final UserDAO userDAO;

    @Autowired
    public ShopControllers(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("good_ad")
    public String good_add(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie[] cookies = request.getCookies();
        String cookieName = "JWT";
        Cookie cookie = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=Utf-8");
        System.out.println(1234);

        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    System.out.println(123);

                    cookie = c;
                    String token = cookie.getValue();
                    Claims data = JWT.jwt_open(token).getBody();
                    String email = (String) data.get("email");
                    Good good = new Good();
                    model.addAttribute("good",good);
                    return "shop/good_ad";

                }
            }
            return "redirect:/login";
        }
        return "redirect:/login";

    }
}
