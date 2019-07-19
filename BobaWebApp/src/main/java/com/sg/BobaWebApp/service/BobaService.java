package com.sg.BobaWebApp.service;

import com.sg.BobaWebApp.dto.Boba;
import com.sg.BobaWebApp.dto.Location;
import com.sg.BobaWebApp.dto.Metropolitan;
import com.sg.BobaWebApp.dto.Rating;
import com.sg.BobaWebApp.dto.Specialty;
import com.sg.BobaWebApp.dto.Toppings;
import java.util.List;

public interface BobaService {  
    List<Metropolitan> allMetropolitan();
    List<Boba> allBobaByMetropolitan(String metropolitan);
    List<Rating> allRatings();
    List<Specialty> allSpecialty();
    List<Toppings> allToppings();
    List<Toppings> setToppings(String[] topping);
    List<Specialty> setSpecialty(String[] specialty);
    void addBoba(Boba boba, String metropolitanName);
    Boba getBoba(String metropolitanName, String storeName);
    List<Location> allLocationsInMetropolitanByStoreName(String metropolitanName, String storeName);
    void editBoba(Boba boba, String storeName, String metropolitanName);
    void deleteBoba(String storeName, String metropolitanName);
    void addLocation(Location location);
    void deleteLocation(String address);
}