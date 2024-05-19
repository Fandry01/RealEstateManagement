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
    private TenantShortDTO tenantShortDTO;
    private PropertyDTO propertyDTO;
    private InvestorShortDTO investorShortDTO;


    public LeaseAgreementDTO(){

    }

    public LeaseAgreementDTO(Long id, Double rentalPrice, String rentalPeriod, LocalDate startDate, LocalDate endDate, PaymentTerms paymentTerms, TenantShortDTO tenantShortDTO, PropertyDTO propertyDTO, InvestorShortDTO investorShortDTO) {
        this.id = id;
        this.rentalPrice = rentalPrice;
        this.rentalPeriod = rentalPeriod;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentTerms = paymentTerms;
        this.propertyDTO = propertyDTO;
        this.tenantShortDTO = tenantShortDTO;
        this.investorShortDTO = investorShortDTO;
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


    public PropertyDTO getPropertyDTO() {
        return propertyDTO;
    }

    public void setPropertyDTO(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }


    public TenantShortDTO getTenantShortDTO() {
        return tenantShortDTO;
    }

    public void setTenantShortDTO(TenantShortDTO tenantShortDTO) {
        this.tenantShortDTO = tenantShortDTO;
    }

    public InvestorShortDTO getInvestorShortDTO() {
        return investorShortDTO;
    }

    public void setInvestorShortDTO(InvestorShortDTO investorShortDTO) {
        this.investorShortDTO = investorShortDTO;
    }
}
