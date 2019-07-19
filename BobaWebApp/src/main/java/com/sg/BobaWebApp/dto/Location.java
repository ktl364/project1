package com.sg.BobaWebApp.dto;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author kevintl
 */

public class Location {
    
    private int id;
    
    @NotBlank(message = "Address must be blank!")
    @Size(max=30, message ="Address must be less than 50 characters.")
    private String address;
    
    @NotBlank(message = "City must be blank!")
    @Size(max=30, message ="City must be less than 30 characters.")
    private String city;
    
    private String state;
    
    @NotBlank(message = "Zip code must not be blank!")
    @Size(min=5, max=5, message ="Zip code must contain 5 characters.")
    @Pattern(regexp = "^[0-9]+", message = "Zip code should contain only numbers.")
    private String zip;
    
    private String phone;
    private String metropolitanName;
    private String storeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMetropolitanName() {
        return metropolitanName;
    }

    public void setMetropolitanName(String metropolitanName) {
        this.metropolitanName = metropolitanName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.address);
        hash = 13 * hash + Objects.hashCode(this.city);
        hash = 13 * hash + Objects.hashCode(this.state);
        hash = 13 * hash + Objects.hashCode(this.zip);
        hash = 13 * hash + Objects.hashCode(this.phone);
        hash = 13 * hash + Objects.hashCode(this.metropolitanName);
        hash = 13 * hash + Objects.hashCode(this.storeName);
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
        final Location other = (Location) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.metropolitanName, other.metropolitanName)) {
            return false;
        }
        if (!Objects.equals(this.storeName, other.storeName)) {
            return false;
        }
        return true;
    }
    
}
