package com.example.realestatemanagment.Dto;

import com.example.realestatemanagment.Models.AuthorityRoles;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;

import java.util.Date;
import java.util.Set;

public class UserDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date Dob;
    @JsonSerialize
    public Set<AuthorityRoles> authorities;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date dob) {
        Dob = dob;
    }

    public Set<AuthorityRoles> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityRoles> authorities) {
        this.authorities = authorities;
    }
}
