package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Rating;
import com.sg.BobaWebApp.dto.Specialty;
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
public class RatingDBDao implements RatingDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Rating> getAllRating() {
        return jdbc.query("select * from Rating", new RatingMapper());
    }
    
    public static final class RatingMapper implements RowMapper<Rating> {

        @Override
        public Rating mapRow(ResultSet rs, int index) throws SQLException {
            Rating rating = new Rating();
            
            rating.setRatingId(rs.getInt("ratingId"));
            rating.setRatingName(rs.getString("ratingName"));
            
            return rating;
        }
    }
}
