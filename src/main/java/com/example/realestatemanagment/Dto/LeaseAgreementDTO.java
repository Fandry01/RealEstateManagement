package com.example.realestatemanagment.Dto;

import com.example.realestatemanagment.Enums.PaymentTerms;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LeaseAgreementDTO {
    private Long id;
    @NotBlank(message ="Rental Price can't be empty")
    private Double rentalPrice;
    @NotBlank(message ="Please note the rental period")
    private String rentalPeriod;
    @NotNull(message ="Please note when the rental starts")
    private LocalDate startDate;
    @NotNull(message ="Please note when the rental ends" )
    private LocalDate endDate;
    @NotBlank(message ="payment terms can't be empty" )
    private PaymentTerms paymentTerms;
    private TenantDTO tenantDTO;
    private PropertyDTO propertyDTO;
    private InvestorDTO investorDTO;

    public LeaseAgreementDTO(){

    }

    public LeaseAgreementDTO(Long id, Double rentalPrice, String rentalPeriod, LocalDate startDate, LocalDate endDate, PaymentTerms paymentTerms, TenantDTO tenantDTO, PropertyDTO propertyDTO, InvestorDTO investorDTO) {
        this.id = id;
        this.rentalPrice = rentalPrice;
        this.rentalPeriod = rentalPeriod;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentTerms = paymentTerms;
        this.tenantDTO = tenantDTO;
        this.propertyDTO = propertyDTO;
        this.investorDTO = investorDTO;
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

    public TenantDTO getTenantDTO() {
        return tenantDTO;
    }

    public void setTenantDTO(TenantDTO tenantDTO) {
        this.tenantDTO = tenantDTO;
    }

    public PropertyDTO getPropertyDTO() {
        return propertyDTO;
    }

    public void setPropertyDTO(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }

    public InvestorDTO getInvestorDTO() {
        return investorDTO;
    }

    public void setInvestorDTO(InvestorDTO investorDTO) {
        this.investorDTO = investorDTO;
    }
}
