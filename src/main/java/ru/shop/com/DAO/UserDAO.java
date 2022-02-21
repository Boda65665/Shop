package ru.shop.com.DAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shop.com.Models.User;

import java.util.List;

@Component
public class UserDAO{
    public static int idUser;

    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public static void new_user(@NotNull User user,int id) {
        jdbcTemplate.update("INSERT INTO users VALUES(?, ?, ?, ?)", user.getLogin(), user.getPassword(),
                user.getEmail(),id);
    }
    public static User user_search(String email){
            return jdbcTemplate.query("SELECT * FROM users WHERE email=?", new Object[]{email}, new BeanPropertyRowMapper<>(User.class))
                    .stream().findAny().orElse(null);
        }




    public int id_user() {
        List<User> ids = jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
//      ids.size() это число пользователей в бд = id последнего пользователя.
        return ids.size()+1;
    }

    }

