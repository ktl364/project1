package com.sg.BobaWebApp.dto;

import java.util.Objects;

/**
 * @author kevintl
 */

public class PopularDrinks {
    
    private String drinkName;

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.drinkName);
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
        final PopularDrinks other = (PopularDrinks) obj;
        if (!Objects.equals(this.drinkName, other.drinkName)) {
            return false;
        }
        return true;
    }
    
}
