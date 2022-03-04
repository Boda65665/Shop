package ru.shop.com.Conrollers;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.shop.com.DAO.UserDAO;
import ru.shop.com.Models.Good;
import ru.shop.com.email.Mail;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
                    String email = GetDataJWT.getEmail(request,response);
                    if (email.equals("None")) {
                        return "redirect:/login";

                    }
                    Good good = new Good();
                    model.addAttribute("good",good);
                    return "shop/good_ad";

                }
                @PostMapping("good_ad")
                public String add_good_post(@RequestParam("img") MultipartFile file,HttpServletRequest request, HttpServletResponse response, Model model) throws IllegalStateException, IOException {
                    String parentPath = "C:\\Users\\User\\IdeaProjects\\Test\\src\\main\\webapp\\WEB-INF\\upload";
                    System.out.println(parentPath);
                    File parentFile = new File(parentPath);
                    String fileName = file.getOriginalFilename();
                    File dest = new File(parentFile, fileName);
                    file.transferTo(dest);
                    return null;


                }
            }



