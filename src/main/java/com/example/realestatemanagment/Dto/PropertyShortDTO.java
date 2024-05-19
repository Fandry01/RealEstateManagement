package com.example.realestatemanagment.Dto;

import com.example.realestatemanagment.Enums.HouseTypes;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PropertyShortDTO {
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

    public PropertyShortDTO(){

    }

    public PropertyShortDTO(Long id, HouseTypes type, Double boughtPrice, Double currentPrice, Integer buildYear, String squareFeet, Boolean rented, String address) {
        this.id = id;
        this.type = type;
        this.boughtPrice = boughtPrice;
        this.currentPrice = currentPrice;
        this.buildYear = buildYear;
        this.squareFeet = squareFeet;
        this.rented = rented;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HouseTypes getType() {
        return type;
    }

    public void setType(HouseTypes type) {
        this.type = type;
    }

    public Double getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(Double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(Integer buildYear) {
        this.buildYear = buildYear;
    }

    public String getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(String squareFeet) {
        this.squareFeet = squareFeet;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
