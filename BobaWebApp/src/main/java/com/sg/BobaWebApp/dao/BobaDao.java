package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Boba;
import java.util.List;

/**
 * @author kevintl
 */

public interface BobaDao {
    
    Boba getBobaByStoreName(String metropolitanName, String storeName);
    List<Boba> getAllBobaByMetropolitan(String metropolitanName);
    Boba addBoba(Boba boba, String metropolitanName);
    void updateBoba(Boba boba, String storeName, String metropolitanName);
    void deleteBobaByStoreName(String storeName, String metropolitanName);
 
}
