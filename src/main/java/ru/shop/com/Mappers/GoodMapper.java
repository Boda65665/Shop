package ru.shop.com.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.shop.com.Models.Good;
import ru.shop.com.Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodMapper implements RowMapper<Good> {
    @Override
    public Good mapRow(ResultSet resultSet, int i) throws SQLException {
        Good good = new Good();
        good.setImg(resultSet.getString("img"));
        good.setEmail(resultSet.getString("email"));
        good.setName(resultSet.getString("name"));
        good.setPrice(resultSet.getDouble("price"));
        return good;
    }
}
