package com.example.realestatemanagment.Models;

import jakarta.persistence.*;


import java.util.*;

@Entity
@Table(name = "investors")
public class Investor extends User {
    @OneToMany(mappedBy = "investor")
    List<Property> properties;
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

    public void addProperty(Property property) {
        if (properties == null) {
            properties = new ArrayList<>();
        }
        properties.add(property);
        property.setInvestor(this);
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
