package com.example.realestatemanagment.Dto;

import com.example.realestatemanagment.Models.AuthorityRoles;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TenantDTO {
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
    private LocalDate Dob;


    @JsonSerialize
    public Set<AuthorityRoles> authorities;

    private List<ComplaintShortDTO> complaintDTO = new ArrayList<>();
    private PropertyShortDTO propertyDTO;


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


    public Set<AuthorityRoles> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityRoles> authorities) {
        authorities = authorities;
    }

    public List<ComplaintShortDTO> getComplaintDTO() {
        return complaintDTO;
    }

    public void setComplaintDTO(List<ComplaintShortDTO> complaintDTO) {
       this.complaintDTO = complaintDTO;
    }

    public PropertyShortDTO getPropertyDTO() {
        return propertyDTO;
    }

    public void setPropertyDTO(PropertyShortDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }
}
