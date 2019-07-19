package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Location;
import java.util.List;

/**
 * @author kevintl
 */

public interface LocationDao {
    
    List<Location> getAllLocationsInMetropolitanByStoreName(String metropolitanName, String storeName);
    Location addLocation(Location location);
    void deleteLocation(String address);
    
}
