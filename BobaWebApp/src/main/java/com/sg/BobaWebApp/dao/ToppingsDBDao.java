package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Toppings;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author kevintl
 */
@Repository
public class ToppingsDBDao implements ToppingsDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Toppings> getAllToppings() {
        return jdbc.query("select * from Toppings", new ToppingsMapper());
    }

    @Override
    public Toppings getToppingByType(String toppingType) {
         return jdbc.queryForObject("select * from Toppings where toppingType = ?", new ToppingsMapper(), toppingType);
    }
    
    public static final class ToppingsMapper implements RowMapper<Toppings> {

        @Override
        public Toppings mapRow(ResultSet rs, int index) throws SQLException {
            Toppings topping = new Toppings();
            
            topping.setToppingType(rs.getString("toppingType"));
            topping.setIcon(rs.getString("icon"));
            
            return topping;
        }
    }
}
