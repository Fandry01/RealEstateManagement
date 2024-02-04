package com.example.realestatemanagment.Dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class InvestorDTO {
    private Long id;
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

    public InvestorDTO(){

    }

    public InvestorDTO( Long id, String firstName, String lastname, Date dob, String address){
      this.address = address;
      this.dob = dob;
      this.lastname = lastname;
      this.firstName = firstName;
      this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getId() {
        return id;
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
}
