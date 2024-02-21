package com.example.realestatemanagment.Models;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "tenants")
public class Tenant extends User {

    @Column(name = "rental_periode")
    private Date rentalPeriod;
    @Column(name = "rent_price")
    private Integer rentPrice;

    @OneToOne
    Property property;

    @OneToOne
    Complaint complaint;

    public Tenant(){
        super();
    }


    public Date getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(Date rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }
}
