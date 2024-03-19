package com.example.realestatemanagment.Dto;

import com.example.realestatemanagment.Models.AuthorityRoles;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public class InvestorDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private LocalDate dob;
    @NotNull
    private String address;
    private PropertyDTO propertyDTO;
    @JsonSerialize
    private Set<AuthorityRoles> authorities;

    public InvestorDTO(){

    }

    public InvestorDTO(String username, String password, String firstname, String lastname, LocalDate dob, String address, Set<AuthorityRoles> authorities){
        this.username = username;
        this.password = password;
        this.address = address;
        this.dob = dob;
        this.lastname = lastname;
        this.firstname = firstname;

        this.authorities = authorities;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPropertyDTO(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstname;
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
