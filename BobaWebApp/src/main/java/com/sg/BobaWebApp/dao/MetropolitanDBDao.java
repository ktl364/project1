package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Metropolitan;
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
public class MetropolitanDBDao implements MetropolitanDao{

    @Autowired
    JdbcTemplate jdbc;
            
    @Override
    public List<Metropolitan> getAllMetropolitan() {
        return jdbc.query("select * from MetroPolitan", new MetropolitanMapper());
    }

    @Override
    public Metropolitan getMetropolitanName(String name) {
        return jdbc.queryForObject("select * from Metropolitan where metropolitanName = ?", new MetropolitanMapper(), name);
    }
    
    public static final class MetropolitanMapper implements RowMapper<Metropolitan> {

        @Override
        public Metropolitan mapRow(ResultSet rs, int index) throws SQLException {
            Metropolitan metropolitan = new Metropolitan();
            
            metropolitan.setMetropolitanName(rs.getString("metropolitanName"));
            
            return metropolitan;
        }
    }
    
}
