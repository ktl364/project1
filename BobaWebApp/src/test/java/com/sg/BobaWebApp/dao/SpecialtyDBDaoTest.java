package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Specialty;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kevintl
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecialtyDBDaoTest {
    
    @Autowired
    SpecialtyDao specialtyDao;
    
    public SpecialtyDBDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllSpecialty method, of class SpecialtyDBDao.
     */
    @Test
    public void testGetAllSpecialty() {
        List<Specialty> specialties = specialtyDao.getAllSpecialty();
        assertEquals(10, specialties.size());
    }
    
}
