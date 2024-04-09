package com.example.realestatemanagment.Dto;

import com.example.realestatemanagment.Models.AuthorityRoles;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Set;

public class InvestorDTO {
    @NotBlank(message = "username can't be empty")
    private String username;
    @NotBlank()
    @Size(min=5,max=15, message = "required password length 4-15 characters")
    private String password;
    @NotBlank(message = "first name can't be empty")
    private String firstName;
    @NotBlank(message = "last name can't be empty")
    private String lastName;
    @NotBlank(message = "Date of Birth can't be empty")
    private LocalDate dob;
    @NotBlank(message = "address can't be empty")
    private String address;
    private PropertyDTO propertyDTO;
    @JsonSerialize
    private Set<AuthorityRoles> authorities;

    public InvestorDTO(){

    }

    public InvestorDTO(String username, String password, String firstName, String lastName, LocalDate dob, String address, Set<AuthorityRoles> authorities){
        this.username = username;
        this.password = password;
        this.address = address;
        this.dob = dob;
        this.lastName = lastName;
        this.firstName = firstName;

        this.authorities = authorities;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return firstName;
    }

    public String getLastname() {
        return lastName;
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
