package ru.shop.com.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.shop.com.Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setEmailConfimation(resultSet.getString("email_confimation"));
        user.setId(resultSet.getInt("id"));
        user.setKodEmailConfimation(resultSet.getInt("kod_email_confimation"));
        user.setEmail(resultSet.getString("email"));
        user.setLogin("login");
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
