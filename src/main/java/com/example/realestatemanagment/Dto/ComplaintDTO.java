package com.example.realestatemanagment.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ComplaintDTO {
    private Long id;
    @NotNull
    @PastOrPresent()
    private LocalDate dateOfComplaint;
    @NotBlank()
    @Size(max=200, message = "Explain the situation within 200 characters")
    private String complaintMessage;

    private TenantDTO tenant;

    public ComplaintDTO(){

    }

    public ComplaintDTO(Long id, LocalDate dateOfComplaint, String complaintMessage){
        this.complaintMessage = complaintMessage;
        this.dateOfComplaint = dateOfComplaint;
        this.id = id;
    }

    public void setDateOfComplaint(LocalDate dateOfComplaint) {
        this.dateOfComplaint = dateOfComplaint;
    }

    public void setComplaintMessage(String complaintMessage) {
        this.complaintMessage = complaintMessage;
    }

    public void setTenant(TenantDTO tenant) {
        this.tenant = tenant;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfComplaint() {
        return dateOfComplaint;
    }

    public String getComplaintMessage() {
        return complaintMessage;
    }

    public Long getId() {
        return id;
    }

    public TenantDTO getTenant() {
        return tenant;
    }
}
