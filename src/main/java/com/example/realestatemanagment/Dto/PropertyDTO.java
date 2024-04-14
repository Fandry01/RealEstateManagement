package com.example.realestatemanagment.Dto;

import com.example.realestatemanagment.Enums.HouseTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.apache.tomcat.Jar;

import java.util.Date;

public class PropertyDTO {
    private Long id;
    @NotNull(message = "Please choose House type")
    private HouseTypes type;
    @NotNull
    @Digits(integer = 8,fraction = 2,message = "maximum of 8 whole numbers and it must have 2 decimals")
    private Double boughtPrice;
    @NotNull
    @Digits(integer = 8,fraction = 2,message = "maximum of 8 whole numbers and it must have 2 decimals")
    private Double currentPrice;
    @NotNull(message = "Please enter the build year")
    private Integer buildYear;
    @NotBlank(message = "Square Feet can't be empty")
    private String squareFeet;
    @NotNull(message = "Please state if it's rented or not")
    private Boolean rented;
    @NotNull(message = "Please note the addres of the property")
    private String address;

    private InvestorDTO investorDTO;
    private MaintenanceDTO maintenanceDTO;

    private ComplaintDTO complaintDTO;
    public PropertyDTO(){

    }
    public PropertyDTO(Long id, HouseTypes type, Double boughtPrice, Double currentPrice, Integer buildYear, String squareFeet, Boolean rented, String address){
        this.id = id;
        this.type = type;
        this.boughtPrice = boughtPrice;
        this.currentPrice = currentPrice;
        this.rented = rented;
        this.squareFeet = squareFeet;
        this.buildYear = buildYear;
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(HouseTypes type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBoughtPrice(Double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public void setBuildYear(Integer buildYear) {
        this.buildYear = buildYear;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public void setSquareFeet(String squareFeet) {
        this.squareFeet = squareFeet;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public HouseTypes getType() {
        return type;
    }

    public Boolean getRented() {
        return rented;
    }

    public Integer getBuildYear() {
        return buildYear;
    }

    public Double getBoughtPrice() {
        return boughtPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public String getSquareFeet() {
        return squareFeet;
    }

    public MaintenanceDTO getMaintainanceDTO() {
        return maintenanceDTO;
    }

    public void setMaintainanceDTO(MaintenanceDTO maintenanceDTO) {
        this.maintenanceDTO = maintenanceDTO;
    }

    public ComplaintDTO getComplaintDTO() {
        return complaintDTO;
    }

    public void setComplaintDTO(ComplaintDTO complaintDTO) {
        this.complaintDTO = complaintDTO;
    }

    public MaintenanceDTO getMaintenanceDTO() {
        return maintenanceDTO;
    }

    public void setMaintenanceDTO(MaintenanceDTO maintenanceDTO) {
        this.maintenanceDTO = maintenanceDTO;
    }

    public InvestorDTO getInvestorDTO() {
        return investorDTO;
    }

    public void setInvestorDTO(InvestorDTO investorDTO) {
        this.investorDTO = investorDTO;
    }
}
