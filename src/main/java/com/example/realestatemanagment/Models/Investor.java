package com.example.realestatemanagment.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.*;

@Entity
@Table(name = "investors")
public class Investor {
    @Id
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(name = "first_name")
    private String Firstname;
    @Column(name = "last_name")
    private String Lastname;
    @Column(name = "date_of_birth")
    private Date Dob;
    private String Address;
    @OneToMany(
            targetEntity = AuthorityRoles.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<AuthorityRoles> authorities = new HashSet<>();
    @OneToMany(mappedBy = "investor")
    List<Property> properties;




public Investor(){

}
public Investor(String firstname,String lastname,Date dob,String address){
    this.Firstname = firstname;
    this.Address = address;
    this.Lastname = lastname;
    this.Dob = dob;
}

    public void addProperty(Property property) {
        if (properties == null) {
            properties = new ArrayList<>();
        }
        properties.add(property);
        property.setInvestor(this);
    }
    public void addAuthority(AuthorityRoles authority) {
        this.authorities.add(authority);
    }
    public void removeAuthority(AuthorityRoles authorityRoles){
        this.authorities.remove(authorityRoles);
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

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AuthorityRoles> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityRoles> authorities) {
        this.authorities = authorities;
    }
}
