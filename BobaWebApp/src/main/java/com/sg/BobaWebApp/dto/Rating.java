package com.sg.BobaWebApp.dto;

import java.util.Objects;

/**
 * @author kevintl
 */

public class Rating {
    
    private int ratingId;
    private String ratingName;

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public String getRatingName() {
        return ratingName;
    }

    public void setRatingName(String ratingName) {
        this.ratingName = ratingName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.ratingId;
        hash = 61 * hash + Objects.hashCode(this.ratingName);
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
        final Rating other = (Rating) obj;
        if (this.ratingId != other.ratingId) {
            return false;
        }
        if (!Objects.equals(this.ratingName, other.ratingName)) {
            return false;
        }
        return true;
    }
    
    
}
