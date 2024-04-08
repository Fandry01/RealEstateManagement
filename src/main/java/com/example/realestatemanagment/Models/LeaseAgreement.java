package com.example.realestatemanagment.Models;

import com.example.realestatemanagment.Enums.PaymentTerms;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lease_agreement")
public class LeaseAgreement {
    @Id
    @GeneratedValue
    private Long id;
    private Double rentalPrice;

    private String rentalPeriod;
    private LocalDate startDate;
    private LocalDate endDate;
    private PaymentTerms paymentTerms;
    @ManyToOne(fetch = FetchType.EAGER)
    private Investor investor;
    @OneToOne
    private Tenant tenant;
    @OneToOne
    private Property property;
    @OneToOne(mappedBy = "")
    private ImageData contract;
    public LeaseAgreement(){

    }

    public LeaseAgreement(Long id, Double rentalPrice,String rentalPeriod, LocalDate startDate, LocalDate endDate, PaymentTerms paymentTerms){
        this.id = id;
        this.endDate = endDate;
        this.rentalPeriod = rentalPeriod;
        this.paymentTerms = paymentTerms;
        this.startDate = startDate;
        this.rentalPrice = rentalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public PaymentTerms getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(PaymentTerms paymentTerms) {
        this.paymentTerms = paymentTerms;
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

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
