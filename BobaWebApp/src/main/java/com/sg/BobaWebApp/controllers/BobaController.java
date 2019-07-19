package com.sg.BobaWebApp.controllers;

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
import com.sg.BobaWebApp.dto.US;
import com.sg.BobaWebApp.service.BobaService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author kevintl
 */

@Controller
public class BobaController {
    
    @Autowired
    BobaDao bobaDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    PopularDrinksDao popularDrinksDao;
    
    @Autowired
    RatingDao ratingDao;
    
    @Autowired
    SpecialtyDao specialtyDao;
    
    @Autowired
    ToppingsDao toppingsDao;
    
    @Autowired
    BobaService service;
    
    String metropolitanName = "Dallas";
    
    Set<ConstraintViolation<Location>> violations = new HashSet<>();
    
    @GetMapping("index")
    public String homePage(Model model) {
        List<Metropolitan> metropolitans = service.allMetropolitan();
        model.addAttribute("metropolitans", metropolitans);
        
        return "index";
    }
    
    @GetMapping("boba")
    public String displayAllBoba(Model model) { //String metropolitan
        //Metropolitan metro = metropolitanDao.getMetropolitanName(metropolitan);
        //model.addAttribute("metropolitan", metro);
        
        List<Metropolitan> metropolitans = service.allMetropolitan();
        model.addAttribute("metropolitans", metropolitans);
        
        List<Boba> bobaCompanies = service.allBobaByMetropolitan(metropolitanName);
        model.addAttribute("bobaCompanies", bobaCompanies);
        
        List<Boba> sortByPrice = bobaCompanies.stream().sorted(Comparator.comparing(Boba::getLargeMilkTeaPrice)).collect(Collectors.toList());
        model.addAttribute("sortByPrice", sortByPrice);

        return "boba";
    }
    
    @GetMapping("addBoba")
    public String addBobaView(Model model, String metropolitan) {
        //Metropolitan metro = metropolitanDao.getMetropolitanName(metropolitan);
        //model.addAttribute("metropolitan", metro);
        
        List<Rating> ratings = service.allRatings();
        model.addAttribute("ratings", ratings);
        
        List<Specialty> specialties = service.allSpecialty();
        model.addAttribute("specialties", specialties);
        
        List<Toppings> toppings = service.allToppings();
        model.addAttribute("toppings", toppings);
        
        model.addAttribute("boba", new Boba()); 
        
        return "addBoba";
    }
    
    @PostMapping("addBoba")
    public String addBoba(@ModelAttribute @Valid Boba boba, BindingResult result, HttpServletRequest request, Model model) {
       
        //Defaults to ratingId = 2 if the user does not
        String ratingId = request.getParameter("ratingId");
        if (ratingId == null) {
            boba.setRatingId(2);
        }
        
        String[] topping = request.getParameterValues("toppings");
        boba.setToppingsAtThisLocation(service.setToppings(topping));
  
        String[] specialty = request.getParameterValues("specialty");
        boba.setSpecialtiesAtThisLocation(service.setSpecialty(specialty));
        
        if (result.hasErrors()) {
            model.addAttribute("ratings", service.allRatings());

            model.addAttribute("specialties", service.allSpecialty());
            
            model.addAttribute("toppings", service.allToppings());
            
            return "addBoba";
        }
        
        //String metro = request.getParameter("metropolitanName");
        
        service.addBoba(boba, metropolitanName);
        
        return "redirect:/boba";
    }
    
    @GetMapping("displayBoba")
    public String displayBobaView(Model model, String storeName) { //String metropolitan
        //model.addAttribute("metropolitanName", metropolitan);
        
        Boba boba = service.getBoba(metropolitanName, storeName);
        model.addAttribute("boba", boba);
        
        model.addAttribute("ratings", service.allRatings());
        
        List<Toppings> toppings = boba.getToppingsAtThisLocation();
        model.addAttribute("toppings", toppings);
        
        List<Specialty> specialties = boba.getSpecialtiesAtThisLocation();
        model.addAttribute("specialties", specialties);
        
        List<Location> locations = service.allLocationsInMetropolitanByStoreName(metropolitanName, storeName);
        model.addAttribute("locations", locations);
        
        return "displayBoba";
    }
    
    @GetMapping("EditBoba")
    public String editBobaView(Model model, String storeName) {
        Boba boba = service.getBoba(metropolitanName, storeName);
        model.addAttribute("boba", boba);
        
        model.addAttribute("specialties", service.allSpecialty());
        model.addAttribute("ratings", service.allRatings());
        model.addAttribute("toppings", service.allToppings());
       
        Set<String> selectedToppingsSet = boba.getToppingsAtThisLocation().stream().map(Toppings::getToppingType).collect(Collectors.toSet());
        model.addAttribute("selectedToppingsSet", selectedToppingsSet);
        
        Set<String> selectedSpecialtySet = boba.getSpecialtiesAtThisLocation().stream().map(Specialty::getSpecialtyType).collect(Collectors.toSet());
        model.addAttribute("selectedSpecialtySet", selectedSpecialtySet);
        
        return "EditBoba";
    }
    
    @PostMapping("EditBoba")
    public String editBoba(@ModelAttribute @Valid Boba boba, BindingResult result, Model model, HttpServletRequest request, String storeName) {
        
        String[] topping = request.getParameterValues("toppings");
        boba.setToppingsAtThisLocation(service.setToppings(topping));
  
        String[] specialty = request.getParameterValues("specialty");
        boba.setSpecialtiesAtThisLocation(service.setSpecialty(specialty));
       
        if(result.hasErrors()) {
           
            model.addAttribute("boba", boba);
            
            model.addAttribute("ratings", service.allRatings());
            model.addAttribute("specialties", service.allSpecialty());
            model.addAttribute("toppings", service.allToppings());
            
            Set<String> selectedToppingsSet = boba.getToppingsAtThisLocation().stream().map(Toppings::getToppingType).collect(Collectors.toSet());
            model.addAttribute("selectedToppingsSet", selectedToppingsSet);

            Set<String> selectedSpecialtySet = boba.getSpecialtiesAtThisLocation().stream().map(Specialty::getSpecialtyType).collect(Collectors.toSet());
            model.addAttribute("selectedSpecialtySet", selectedSpecialtySet);
            
            return "EditBoba";
        }
        
        service.addBoba(boba, metropolitanName);
        
        return "redirect:/displayBoba?storeName=" + boba.getStoreName();
    }
    
    @GetMapping("DeleteBoba")
    public String deleteBobaView(Model model, String storeName) { //String metropolitan
        Boba boba = service.getBoba(metropolitanName, storeName);
        model.addAttribute("boba", boba);
        
        model.addAttribute("metropolitanName", metropolitanName);
        
        return "DeleteBoba";
    }
    
    @PostMapping("DeleteBoba")
    public String deleteBoba(String storeName) { //String metropolitan, HttpServletRequest request
        //String metro = request.getParameter("metropolitanName");
        
        bobaDao.deleteBobaByStoreName(storeName, metropolitanName);
        
        return "redirect:/boba";
    }
    
    @GetMapping("addLocation")
    public String addLocationView(Model model, String storeName) {
        Boba boba = bobaDao.getBobaByStoreName(metropolitanName, storeName);
        model.addAttribute("boba", boba);
        
        model.addAttribute("metropolitanName", metropolitanName);
        
        List<String> states = Stream.of(US.values()).map(US::getUnabbreviated).collect(Collectors.toList());
        model.addAttribute("states", states);
        
        model.addAttribute("errors", violations);
                
        return "addLocation";
    }
    
    @PostMapping("addLocation")
    public String addLocation(Location location, Model model, String storeName) {

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);

        if(violations.isEmpty()) {
            locationDao.addLocation(location);
            return "redirect:/displayBoba?storeName=" + storeName;
        }
        
        return "redirect:/addLocation?storeName=" + storeName;
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(String address, String storeName) {    

        locationDao.deleteLocation(address);

       return "redirect:/displayBoba?storeName=" + storeName;
    }
   
}
