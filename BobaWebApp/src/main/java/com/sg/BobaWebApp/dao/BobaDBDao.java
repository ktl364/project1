package com.sg.BobaWebApp.dao;

import com.sg.BobaWebApp.dao.MetropolitanDBDao.MetropolitanMapper;
import com.sg.BobaWebApp.dao.PopularDrinksDBDao.PopularDrinksMapper;
import com.sg.BobaWebApp.dao.SpecialtyDBDao.SpecialtyMapper;
import com.sg.BobaWebApp.dao.ToppingsDBDao.ToppingsMapper;
import com.sg.BobaWebApp.dto.Boba;
import com.sg.BobaWebApp.dto.Metropolitan;
import com.sg.BobaWebApp.dto.PopularDrinks;
import com.sg.BobaWebApp.dto.Specialty;
import com.sg.BobaWebApp.dto.Toppings;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kevintl
 */
@Repository
public class BobaDBDao implements BobaDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Boba getBobaByStoreName(String metropolitanName, String storeName) {
        Boba boba = jdbc.queryForObject("select * from Boba where storeName = ?", new BobaMapper(), storeName);
        boba.setToppingsAtThisLocation(getToppingsForBoba(storeName));
        boba.setPopularDrinksAtThisLocation(getPopularDrinks(storeName));
        boba.setSpecialtiesAtThisLocation(getSpecialties(storeName));
        boba.setMetropolitanOfBoba(getMetropolitansForBoba(storeName));
        
        return boba;
    }
    
            private List<Toppings> getToppingsForBoba(String storeName){
                List<Toppings> availableToppings = jdbc.query("select t.* from Toppings t inner join BobaToppings bt on t.toppingType = bt.toppingType where bt.storeName = ?", new ToppingsMapper(), storeName);
                return availableToppings;
            }
            
            private List<PopularDrinks> getPopularDrinks(String storeName) {
                List<PopularDrinks> popularDrinksForStore = jdbc.query("select p.* from PopularDrink p inner join BobaPopularDrink bp on p.drinkName = bp.drinkName where bp.storeName = ?", new PopularDrinksMapper(), storeName);
                return popularDrinksForStore;
            }
            
            private List<Specialty> getSpecialties(String storeName) {
                List<Specialty> specialtiesForStore = jdbc.query("select s.* from Specialty s inner join BobaSpecialty bs on s.specialtyType = bs.specialtyType where bs.storeName = ?", new SpecialtyMapper(), storeName);
                return specialtiesForStore;
            }
            
            private List<Metropolitan> getMetropolitansForBoba(String storeName) {
                List<Metropolitan> metropolitansForStore = jdbc.query("select m.* from Metropolitan m inner join MetropolitanBoba mb on m.metropolitanName = mb.metropolitanName where mb.storeName = ?", new MetropolitanMapper(), storeName);
                return metropolitansForStore;
            }

    @Override
    public List<Boba> getAllBobaByMetropolitan(String metropolitanName) {
        List<Boba> bobaByMetropolitan = jdbc.query("select Boba.* from Boba inner join MetropolitanBoba mb on mb.StoreName = Boba.storeName where mb.metropolitanName = ?", new BobaMapper(), metropolitanName);
        associateBobaProperties(bobaByMetropolitan);
        return bobaByMetropolitan; 
    }

        private void associateBobaProperties(List<Boba> bobaCompanies) {
            for (Boba bobaCompany : bobaCompanies) {
                bobaCompany.setToppingsAtThisLocation(getToppingsForBoba(bobaCompany.getStoreName()));
                bobaCompany.setPopularDrinksAtThisLocation(getPopularDrinks(bobaCompany.getStoreName()));
                bobaCompany.setSpecialtiesAtThisLocation(getSpecialties(bobaCompany.getStoreName()));
                bobaCompany.setMetropolitanOfBoba(getMetropolitansForBoba(bobaCompany.getStoreName()));
            }
        }
        
    @Override
    @Transactional
    public Boba addBoba(Boba boba, String metropolitanName) {
                jdbc.update("insert into Boba(storeName, logoUrl, ratingId, largeMilkTeaPrice, hasBeen, notes) values(?,?,?,?,?,?)",
                        boba.getStoreName(),
                        boba.getLogoUrl(),
                        boba.getRatingId(),
                        boba.getLargeMilkTeaPrice(),
                        boba.isHasBeen(),
                        boba.getNotes());

                insertBobaToppings(boba);
                insertBobaSpecialty(boba);
                insertMetropolitanBoba(boba, metropolitanName);
            
        
        return boba;
    }

            
            //loops thru the list of toppings for a particular Boba company and add database entries to BobaToppings for each
            private void insertBobaToppings(Boba boba) {
                 for (Toppings topping : boba.getToppingsAtThisLocation()) {
                    jdbc.update("insert into BobaToppings(storeName, toppingType) values (?,?)",
                        boba.getStoreName(),
                        topping.getToppingType());
                }
            }
            
            //loops thru the list of specialties for a particular Boba company and add databse entries to BobaSpecialty for each
            private void insertBobaSpecialty(Boba boba) {
                for (Specialty specialty : boba.getSpecialtiesAtThisLocation()) {
                    jdbc.update("insert into BobaSpecialty(storeName, specialtyType) values (?,?)",
                            boba.getStoreName(),
                            specialty.getSpecialtyType());
                }
            }
            
            private void insertMetropolitanBoba(Boba boba, String metropolitanName) {
                jdbc.update("insert into MetropolitanBoba(metropolitanName, storeName) values (?,?)",
                        metropolitanName,
                        boba.getStoreName());
            }
            
    @Override
    @Transactional
    public void updateBoba(Boba boba, String storeName, String metropolitanName) {
       
        jdbc.update("delete from BobaToppings where storeName = ?", storeName);
        
        jdbc.update("delete from BobaSpecialty where storeName = ?", storeName);
        
        //jdbc.update("delete from MetropolitanBoba where storeName = ?", storeName);
        
        jdbc.update("update Boba set storeName = ?, logoUrl = ?, ratingId = ?, largeMilkTeaPrice = ?, notes = ?, hasBeen = ? where storeName = ?",
                boba.getStoreName(),
                boba.getLogoUrl(),
                boba.getRatingId(),
                boba.getLargeMilkTeaPrice(),
                boba.getNotes(),
                boba.isHasBeen(),
                storeName);
        
        insertBobaToppings(boba);
        insertBobaSpecialty(boba);
        //insertMetropolitanBoba(boba, metropolitanName);
    }

    @Override
    @Transactional
    public void deleteBobaByStoreName(String storeName, String metropolitanName) {
        jdbc.update("delete from BobaToppings where storeName = ?", storeName);
        
        jdbc.update("delete from BobaSpecialty where storeName = ?", storeName);
        
        jdbc.update("delete from BobaPopularDrink where storeName = ?", storeName);
        
        jdbc.update("delete from Location where storeName = ? and metropolitanName = ?", storeName, metropolitanName);
        
        jdbc.update("delete from MetropolitanBoba where storeName = ?", storeName);
        
        jdbc.update("delete from Boba where storeName = ?", storeName);
    }
    
    public static final class BobaMapper implements RowMapper<Boba> {

        @Override
        public Boba mapRow(ResultSet rs, int index) throws SQLException {
            Boba boba = new Boba();
            boba.setStoreName(rs.getString("storeName"));
            boba.setLogoUrl(rs.getString("logoUrl"));
            boba.setRank(rs.getInt("rank"));
            boba.setRatingId(rs.getInt("ratingId"));
            boba.setLargeMilkTeaPrice(rs.getBigDecimal("largeMilkTeaPrice"));
            boba.setNotes(rs.getString("notes"));
            boba.setHasBeen(rs.getBoolean("hasBeen"));
            
            return boba;
        }
    }
    
}
