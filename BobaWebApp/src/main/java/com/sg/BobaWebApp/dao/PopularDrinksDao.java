package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.PopularDrinks;
import java.util.List;

/**
 * @author kevintl
 */

public interface PopularDrinksDao {
    
    List<PopularDrinks> getAllPopularDrinksByStoreName(String storeName);
    
}
