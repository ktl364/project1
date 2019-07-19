/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.BobaWebApp.service;

import com.sg.BobaWebApp.dao.BobaDao;
import com.sg.BobaWebApp.dao.LocationDao;
import com.sg.BobaWebApp.dao.MetropolitanDao;
import com.sg.BobaWebApp.dao.PopularDrinksDao;
import com.sg.BobaWebApp.dao.RatingDao;
import com.sg.BobaWebApp.dao.SpecialtyDao;
import com.sg.BobaWebApp.dao.ToppingsDao;
import com.sg.BobaWebApp.dto.Boba;
import com.sg.BobaWebApp.dto.Location;
import com.sg.BobaWebApp.dto.Metropolitan;
import com.sg.BobaWebApp.dto.Rating;
import com.sg.BobaWebApp.dto.Specialty;
import com.sg.BobaWebApp.dto.Toppings;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kevintl
 */
@Service
public class BobaServiceLayerImpl implements BobaService{
    
    @Autowired
    BobaDao bobaDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    MetropolitanDao metropolitanDao;
    
    @Autowired
    PopularDrinksDao popularDrinksDao;
    
    @Autowired
    RatingDao ratingDao;
    
    @Autowired
    SpecialtyDao specialtyDao;
    
    @Autowired
    ToppingsDao toppingsDao;

    @Override
    public List<Metropolitan> allMetropolitan() {
        return metropolitanDao.getAllMetropolitan();
    }

    @Override
    public List<Boba> allBobaByMetropolitan(String metropolitan) {
        return bobaDao.getAllBobaByMetropolitan(metropolitan);
    }

    @Override
    public List<Rating> allRatings() {
        return ratingDao.getAllRating();
    }

    @Override
    public List<Specialty> allSpecialty() {
        return specialtyDao.getAllSpecialty();
    }

    @Override
    public List<Toppings> allToppings() {
       return toppingsDao.getAllToppings();
    }

    @Override
    public List<Toppings> setToppings(String[] topping) {
        List<Toppings> toppings = new ArrayList<>();
        
        if (topping != null) {
            for (String t : topping) {
                toppings.add(toppingsDao.getToppingByType(t));
            }
        } else {
            //do nothing
        }
        
        return toppings;
    }

    @Override
    public List<Specialty> setSpecialty(String[] specialty) {
        List<Specialty> specialties = new ArrayList<>();
        
        if (specialty != null) {
            for (String s : specialty) {
                specialties.add(specialtyDao.getSpecialtyByType(s));
            }
        } else {
            //do nothing
        }
        
        return specialties;
    }

    @Override
    public void addBoba(Boba boba, String metropolitanName) {
        bobaDao.addBoba(boba, metropolitanName);
    }

    @Override
    public Boba getBoba(String metropolitanName, String storeName) {
        return bobaDao.getBobaByStoreName(metropolitanName, storeName);
    }

    @Override
    public List<Location> allLocationsInMetropolitanByStoreName(String metropolitanName, String storeName) {
        return locationDao.getAllLocationsInMetropolitanByStoreName(metropolitanName, storeName);
    }

    @Override
    public void editBoba(Boba boba, String storeName, String metropolitanName) {
        List<Location> locations = locationDao.getAllLocationsInMetropolitanByStoreName(metropolitanName, storeName);
        
        for (Location location : locations) {
            location.setStoreName(boba.getStoreName());
            deleteLocation(location.getAddress());
            addLocation(location);
        }
        
        bobaDao.updateBoba(boba, storeName, metropolitanName);
    }

    @Override
    public void deleteBoba(String storeName, String metropolitanName) {
        bobaDao.deleteBobaByStoreName(storeName, metropolitanName);
    }

    @Override
    public void addLocation(Location location) {
        locationDao.addLocation(location);
    }

    @Override
    public void deleteLocation(String address) {
        locationDao.deleteLocation(address);
    }
 
    
    
}
