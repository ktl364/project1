package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Location;
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
public class LocationDBDao implements LocationDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<Location> getAllLocationsInMetropolitanByStoreName(String metropolitanName, String storeName) {
        return jdbc.query("select l.* from Location l "
                + "inner join MetropolitanBoba mb on l.metropolitanName = mb.metropolitanName and l.storeName = mb.storeName "
                + "where l.metropolitanName = ? and mb.storeName = ? ", new LocationMapper(), metropolitanName, storeName);
    }

    @Override
    public Location addLocation(Location location) {
        jdbc.update("insert into Location(address, city, state, zip, phone, metropolitanName, storeName) values(?,?,?,?,?,?,?)",
                location.getAddress(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getPhone(),
                location.getMetropolitanName(),
                location.getStoreName());
        
        return location;  
    }

    @Override
    public void deleteLocation(String address) {
        jdbc.update("delete from Location where address = ?", address);
    }
    
    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            
            location.setId(rs.getInt("id"));
            location.setAddress(rs.getString("address"));
            location.setCity(rs.getString("city"));
            location.setState(rs.getString("state"));
            location.setZip(rs.getString("zip"));
            location.setPhone(rs.getString("phone"));
            location.setMetropolitanName(rs.getString("metropolitanName"));
            location.setStoreName(rs.getString("storeName"));
            
            return location;
        }
    }
}
