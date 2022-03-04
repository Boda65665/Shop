package ru.shop.com.Conrollers;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class GetDataJWT {
    public static String getEmail(HttpServletRequest request, HttpServletResponse response) {
        String email = "None";
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
                    email = (String) data.get("email");

                }
            }
        }
        return email;
    }
}