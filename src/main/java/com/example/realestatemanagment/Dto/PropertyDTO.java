package com.example.realestatemanagment.Dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class PropertyDTO {
    private Long id;
    @NotNull
    private String type;
    @NotNull
    private Double boughtPrice;
    @NotNull
    private Double currentPrice;
    @NotNull
    private Date buildYear;
    @NotNull
    private String squareFeet;
    @NotNull
    private Boolean rented;
    @NotNull
    private String address;

    private MaintenanceDTO maintenanceDTO;
    public PropertyDTO(){

    }
    public PropertyDTO(Long id, String type, Double boughtPrice, Double currentPrice,Date buildYear, String squareFeet, Boolean rented,String address){
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

    public void setType(String type) {
        this.type = type;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBoughtPrice(Double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public void setBuildYear(Date buildYear) {
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

    public String getType() {
        return type;
    }

    public Boolean getRented() {
        return rented;
    }

    public Date getBuildYear() {
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
}
