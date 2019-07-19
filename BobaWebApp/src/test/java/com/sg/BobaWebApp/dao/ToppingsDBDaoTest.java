package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dto.Toppings;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kevintl
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ToppingsDBDaoTest {
    
    @Autowired
    ToppingsDao toppingsDao;
    
    public ToppingsDBDaoTest() {
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
     * Test of getAllToppings method, of class ToppingsDBDao.
     */
    @Test
    public void testGetAllToppings() {
        List<Toppings> toppings = toppingsDao.getAllToppings();
        assertEquals(14,toppings.size());
    }
    
}
