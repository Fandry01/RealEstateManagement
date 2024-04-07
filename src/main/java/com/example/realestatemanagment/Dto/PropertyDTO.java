package com.example.realestatemanagment.Dto;

import com.example.realestatemanagment.Enums.HouseTypes;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class PropertyDTO {
    private Long id;
    @NotNull
    private HouseTypes type;
    @NotNull
    private Double boughtPrice;
    @NotNull
    private Double currentPrice;
    @NotNull
    private Integer buildYear;
    @NotNull
    private String squareFeet;
    @NotNull
    private Boolean rented;
    @NotNull
    private String address;

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

}
