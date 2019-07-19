
package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Specialty;
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
public class SpecialtyDBDao implements SpecialtyDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Specialty> getAllSpecialty() {
        return jdbc.query("select * from Specialty", new SpecialtyMapper());
    }

    @Override
    public Specialty getSpecialtyByType(String specialtyType) {
        return jdbc.queryForObject("select * from Specialty where specialtyType = ?", new SpecialtyMapper(), specialtyType);
    }
    
    public static final class SpecialtyMapper implements RowMapper<Specialty> {

        @Override
        public Specialty mapRow(ResultSet rs, int index) throws SQLException {
            Specialty specialty = new Specialty();
            
            specialty.setSpecialtyType(rs.getString("specialtyType"));
            specialty.setIcon(rs.getString("icon"));
            
            return specialty;
        }
    }
}
