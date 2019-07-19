package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Specialty;
import java.util.List;

/**
 * @author kevintl
 */

public interface SpecialtyDao {
    
    List<Specialty> getAllSpecialty();
    Specialty getSpecialtyByType(String specialtyType);
}
