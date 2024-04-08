package com.example.realestatemanagment.Models;

import com.example.realestatemanagment.Service.LeaseAgreementService;
import jakarta.persistence.*;


import java.util.*;

@Entity
@Table(name = "investors")
public class Investor extends User {
    @OneToMany(mappedBy = "investor")
    List<Property> properties;
    @OneToMany(mappedBy = "investor")
    List<LeaseAgreement> agreements;
    private String Address;

    public Investor() {
    super();
    }

    public Investor(String address) {

        this.Address = address;

    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<LeaseAgreement> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<LeaseAgreement> agreements) {
        this.agreements = agreements;
    }
}
