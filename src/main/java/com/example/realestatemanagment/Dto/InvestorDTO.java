package com.example.realestatemanagment.Dto;

import com.example.realestatemanagment.Models.AuthorityRoles;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

public class InvestorDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastname;
    @NotNull
    private Date dob;
    @NotNull
    private String address;
    @NotNull
    private PropertyDTO propertyDTO;
    @JsonSerialize
    private Set<AuthorityRoles> authorities;

    public InvestorDTO(){

    }

    public InvestorDTO(String username, String password, String firstName, String lastname, Date dob, String address, Set<AuthorityRoles> authorities){
        this.username = username;
        this.password = password;
        this.address = address;
        this.dob = dob;
        this.lastname = lastname;
        this.firstName = firstName;

        this.authorities = authorities;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPropertyDTO(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }

    public Date getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public PropertyDTO getPropertyDTO() {
        return propertyDTO;
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
