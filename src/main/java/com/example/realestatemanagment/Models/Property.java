package com.example.realestatemanagment.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue
    private Long id;
    private String type;
    private Double boughtPrice;
    private Double currentPrice;
    private Integer buildYear;
    private String squareFeet;
    private Boolean rented;
    private String address;

@ManyToOne
   Investor investor;

@OneToOne
    Tenant tenant;

@OneToOne
Complaint complaint;

@OneToMany(mappedBy = "property")
@JsonIgnore
Collection<Maintenance> maintenances;

public Property(){

}

public Property(Long id,String type, Double boughtPrice, Double currentPrice, Integer buildYear, String squareFeet, Boolean rented, String address ){
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

    public Integer getBuildYear() {
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

    public void setMaintenances(Collection<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
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

    public void setBuildYear(Integer buildYear) {
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

    public Collection<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

}


