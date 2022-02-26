package ru.shop.com.DAO;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.shop.com.Mappers.GoodMapper;
import ru.shop.com.Models.Good;

public class GoodDAO {
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public GoodDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public static void new_good(@NotNull Good good, int id){
        jdbcTemplate.update("INSERT INTO Goods VALUES(?,?,?,?)",good.getPrice(),
                good.getName(),
                good.getImg(),good.getEmail());
    }
    public static void update_good(@NotNull Good good){
        jdbcTemplate.update("UPDATE Goods SET name=?,img=?,price=? WHERE email = ?",good.getName(),good.getImg(),good.getPrice(),good.getEmail());

    }
    public static Good good_search(String email){
        return jdbcTemplate.query("SELECT * FROM Goods WHERE email=?", new Object[]{email},new GoodMapper()).stream().findAny().orElse(null);
    }
    }


