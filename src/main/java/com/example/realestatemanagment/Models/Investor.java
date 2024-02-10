package com.example.realestatemanagment.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "investors")
public class Investor {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "first_name")
    private String Firstname;
    @Column(name = "last_name")
    private String Lastname;
    @Column(name = "date_of_birth")
    private Date Dob;
    private String Address;
    @OneToMany(mappedBy = "investor")
    Collection<Property> properties;


public Investor(){

}
public Investor(Long id,String firstname,String lastname,Date dob,String address){
    this.id = id;
    this.Firstname = firstname;
    this.Address = address;
    this.Lastname = lastname;
    this.Dob = dob;
}

    public long getId() {
        return id;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getAddress() {
        return Address;
    }

    public Date getDob() {
        return Dob;
    }

    public Collection<Property> getProperties() {
        return properties;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProperties(Collection<Property> properties) {
        this.properties = properties;
    }
}
