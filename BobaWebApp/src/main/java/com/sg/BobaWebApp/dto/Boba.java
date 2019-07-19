package com.sg.BobaWebApp.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Kevin Le
 */

public class Boba {
    
    @NotBlank(message = "Store must not be blank.")
    @Size(max = 30, message = "Store name must be less than 30 chracters.")
    private String storeName;
    
    @Size(max = 50, message = "Logo URL must be less than 50 chracters.")
    private String logoUrl;
    
    private int rank;
    
    @NotNull(message = "Please fill out a rating.")
    private int ratingId;
    
    @NotNull(message = "Price cannot be null.")
    @Min(0)
    @Max(10)
    private BigDecimal largeMilkTeaPrice;
    
    private String notes;
    private boolean hasBeen;
    
    private List<Toppings> toppingsAtThisLocation;
    private List<Specialty> specialtiesAtThisLocation;
    private List<PopularDrinks> popularDrinksAtThisLocation;
    private List<Metropolitan> metropolitanOfBoba;
    

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public BigDecimal getLargeMilkTeaPrice() {
        return largeMilkTeaPrice;
    }

    public void setLargeMilkTeaPrice(BigDecimal largeMilkTeaPrice) {
        this.largeMilkTeaPrice = largeMilkTeaPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isHasBeen() {
        return hasBeen;
    }

    public void setHasBeen(boolean hasBeen) {
        this.hasBeen = hasBeen;
    }

    public List<Toppings> getToppingsAtThisLocation() {
        return toppingsAtThisLocation;
    }

    public void setToppingsAtThisLocation(List<Toppings> toppingsAtThisLocation) {
        this.toppingsAtThisLocation = toppingsAtThisLocation;
    }

    public List<Specialty> getSpecialtiesAtThisLocation() {
        return specialtiesAtThisLocation;
    }

    public void setSpecialtiesAtThisLocation(List<Specialty> specialtiesAtThisLocation) {
        this.specialtiesAtThisLocation = specialtiesAtThisLocation;
    }

    public List<PopularDrinks> getPopularDrinksAtThisLocation() {
        return popularDrinksAtThisLocation;
    }

    public void setPopularDrinksAtThisLocation(List<PopularDrinks> popularDrinksAtThisLocation) {
        this.popularDrinksAtThisLocation = popularDrinksAtThisLocation;
    }

    public List<Metropolitan> getMetropolitanOfBoba() {
        return metropolitanOfBoba;
    }

    public void setMetropolitanOfBoba(List<Metropolitan> metropolitanOfBoba) {
        this.metropolitanOfBoba = metropolitanOfBoba;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.storeName);
        hash = 97 * hash + Objects.hashCode(this.logoUrl);
        hash = 97 * hash + this.rank;
        hash = 97 * hash + this.ratingId;
        hash = 97 * hash + Objects.hashCode(this.largeMilkTeaPrice);
        hash = 97 * hash + Objects.hashCode(this.notes);
        hash = 97 * hash + (this.hasBeen ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.toppingsAtThisLocation);
        hash = 97 * hash + Objects.hashCode(this.specialtiesAtThisLocation);
        hash = 97 * hash + Objects.hashCode(this.popularDrinksAtThisLocation);
        hash = 97 * hash + Objects.hashCode(this.metropolitanOfBoba);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Boba other = (Boba) obj;
        if (this.rank != other.rank) {
            return false;
        }
        if (this.ratingId != other.ratingId) {
            return false;
        }
        if (this.hasBeen != other.hasBeen) {
            return false;
        }
        if (!Objects.equals(this.storeName, other.storeName)) {
            return false;
        }
        if (!Objects.equals(this.logoUrl, other.logoUrl)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        if (!Objects.equals(this.largeMilkTeaPrice, other.largeMilkTeaPrice)) {
            return false;
        }
        if (!Objects.equals(this.toppingsAtThisLocation, other.toppingsAtThisLocation)) {
            return false;
        }
        if (!Objects.equals(this.specialtiesAtThisLocation, other.specialtiesAtThisLocation)) {
            return false;
        }
        if (!Objects.equals(this.popularDrinksAtThisLocation, other.popularDrinksAtThisLocation)) {
            return false;
        }
        if (!Objects.equals(this.metropolitanOfBoba, other.metropolitanOfBoba)) {
            return false;
        }
        return true;
    }
    
    
    
}
