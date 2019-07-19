package com.sg.BobaWebApp.dto;

import java.util.Objects;

/**
 * @author kevintl
 */

public class Metropolitan {
    
    private String metropolitanName;

    public String getMetropolitanName() {
        return metropolitanName;
    }

    public void setMetropolitanName(String metropolitanName) {
        this.metropolitanName = metropolitanName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.metropolitanName);
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
        final Metropolitan other = (Metropolitan) obj;
        if (!Objects.equals(this.metropolitanName, other.metropolitanName)) {
            return false;
        }
        return true;
    }
    
    
}
