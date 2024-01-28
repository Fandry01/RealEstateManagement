package com.example.realestatemanagment.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Properties")
public class Property {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private Double boughtPrice;
    private Double currentPrice;
    private Date buildYear;
    private String squareFeet;
    private Boolean rented;
    private String address;

@OneToOne(mappedBy = "investor")
   Investor investor;

@OneToOne(mappedBy = "tenant")
    Tenant tenant;


public Property(){

}

public Property(Long id,String type, Double boughtPrice, Double currentPrice, Date buildYear, String squareFeet, Boolean rented, String address ){
 this.id = id;
 this.address = address;
 this.squareFeet = squareFeet;
 this.buildYear = buildYear;
 this.boughtPrice = boughtPrice;
 this.rented = rented;
 this.currentPrice = currentPrice;
 this.type = type;
}

    public String getSquareFeet() {
        return squareFeet;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public Double getBoughtPrice() {
        return boughtPrice;
    }

    public Boolean getRented() {
        return rented;
    }

    public Date getBuildYear() {
        return buildYear;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setSquareFeet(String squareFeet) {
        this.squareFeet = squareFeet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setBuildYear(Date buildYear) {
        this.buildYear = buildYear;
    }

    public void setBoughtPrice(Double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Investor getInvestor() {
        return investor;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setType(String type) {
        this.type = type;
    }
}


