package com.example.realestatemanagment.Dto;

import com.example.realestatemanagment.Models.AuthorityRoles;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public class TenantDTO {
    private String username;
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private LocalDate Dob;

    private String rentalPeriod;

    private Double rentPrice;

    @JsonSerialize
    public Set<AuthorityRoles> authorities;

    private ComplaintDTO complaintDTO;
    private PropertyDTO propertyDTO;


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

    public LocalDate getDob() {
        return Dob;
    }

    public void setDob(LocalDate dob) {
        Dob = dob;
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

    public Set<AuthorityRoles> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityRoles> authorities) {
        authorities = authorities;
    }

    public ComplaintDTO getComplaintDTO() {
        return complaintDTO;
    }

    public void setComplaintDTO(ComplaintDTO complaintDTO) {
        this.complaintDTO = complaintDTO;
    }

    public PropertyDTO getPropertyDTO() {
        return propertyDTO;
    }

    public void setPropertyDTO(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }
}
