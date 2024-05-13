package com.example.realestatemanagment.Models;

import com.example.realestatemanagment.Enums.HouseTypes;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "properties")
public class Property {
    @ManyToOne(fetch = FetchType.LAZY)
    Investor investor;
    @OneToOne
    Tenant tenant;
    @OneToMany(mappedBy = "property")
    List<Complaint> complaint;
    @OneToMany(mappedBy = "property")
    List<Maintenance> maintenances;
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private HouseTypes type;
    private Double boughtPrice;
    private Double currentPrice;
    private Integer buildYear;
    private String squareFeet;
    private Boolean rented;
    private String address;
    @OneToOne(mappedBy = "property")
    private ImageData imageData;

    public Property() {

    }

    public Property(Long id, HouseTypes type, Double boughtPrice, Double currentPrice, Integer buildYear, String squareFeet, Boolean rented, String address) {
        this.id = id;
        this.address = address;
        this.squareFeet = squareFeet;
        this.buildYear = buildYear;
        this.boughtPrice = boughtPrice;
        this.rented = rented;
        this.currentPrice = currentPrice;
        this.type = type;
    }

    public String getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(String squareFeet) {
        this.squareFeet = squareFeet;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(Double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public Integer getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(Integer buildYear) {
        this.buildYear = buildYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HouseTypes getType() {
        return type;
    }

    public void setType(HouseTypes type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public void setMaintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
    }

    public ImageData getImageData() {
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }

    public List<Complaint> getComplaint() {
        return complaint;
    }

    public void setComplaint(List<Complaint> complaint) {
        this.complaint = complaint;
    }
}


