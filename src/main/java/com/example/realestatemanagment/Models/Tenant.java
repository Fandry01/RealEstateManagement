package com.example.realestatemanagment.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "tenants")
public class Tenant extends User {

    @Column(name = "rental_periode")
    private String rentalPeriod;
    @Column(name = "rent_price")
    private Double rentPrice;

    @OneToOne
    Property property;
    @OneToOne
    LeaseAgreement leaseAgreement;

    @OneToOne
    Complaint complaint;

    public Tenant(){
        super();
    }


    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
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
