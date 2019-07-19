package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Metropolitan;
import java.util.List;

/**
 * @author kevintl
 */

public interface MetropolitanDao {
    
    List<Metropolitan> getAllMetropolitan();
    Metropolitan getMetropolitanName(String name);
    
}
