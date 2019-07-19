package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Toppings;
import java.util.List;

/**
 * @author kevintl
 */

public interface ToppingsDao {
    
    List<Toppings> getAllToppings();
    Toppings getToppingByType(String toppingType);
    
}
