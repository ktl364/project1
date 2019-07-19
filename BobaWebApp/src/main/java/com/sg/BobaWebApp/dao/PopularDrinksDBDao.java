package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.PopularDrinks;
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
public class PopularDrinksDBDao implements PopularDrinksDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<PopularDrinks> getAllPopularDrinksByStoreName(String storeName) {
        return jdbc.query("select * from PopularDrink", new PopularDrinksMapper());
    }
    
    public static final class PopularDrinksMapper implements RowMapper<PopularDrinks> {

        @Override
        public PopularDrinks mapRow(ResultSet rs, int index) throws SQLException {
            PopularDrinks popularDrink = new PopularDrinks();
            
            popularDrink.setDrinkName(rs.getString("drinkName"));
            
            return popularDrink;
        }
    }
    
}
