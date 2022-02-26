package ru.shop.com.DAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.shop.com.Mappers.GoodMapper;
import ru.shop.com.Mappers.UserMapper;
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
        jdbcTemplate.update("INSERT INTO users VALUES(?, ?, ?, ?, ?, ?,?,?,?)", user.getLogin(), user.getPassword(),
                user.getEmail(),1234567890,id,"hello",0,0,0);
    }
    public void update(int id, User updatedPerson) {
        jdbcTemplate.update("UPDATE users SET login=?, password=?, email=?, email_confimation=?,kod_email_confimation=?, balance=?, sales=?, rating=?WHERE id=?", updatedPerson.getLogin(),
                updatedPerson.getPassword(), updatedPerson.getEmail(),updatedPerson.getEmailConfimation(),updatedPerson.getKod_email_confimation(),updatedPerson.getBalance(),updatedPerson.getSales(),updatedPerson.getSales(), id);
    }
    public static User user_search(String email){
            return jdbcTemplate.query("SELECT * FROM users WHERE email=?", new Object[]{email},new UserMapper()).stream().findAny().orElse(null);
        }
    public static User user_search_id(int id){
        return jdbcTemplate.query("SELECT * FROM users WHERE id=?", new Object[]{id},new UserMapper()).stream().findAny().orElse(null);
    }




    public int id_user() {
        List<User> ids = jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
//      ids.size() это число пользователей в бд = id последнего пользователя.
        return ids.size()+1;
    }

    }

