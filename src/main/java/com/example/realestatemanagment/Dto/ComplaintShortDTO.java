package com.example.realestatemanagment.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ComplaintShortDTO {
    private Long id;
    @NotNull
    @PastOrPresent()
    private LocalDate dateOfComplaint;
    @NotBlank()
    @Size(max=200, message = "Explain the situation within 200 characters")
    private String complaintMessage;
    public ComplaintShortDTO(){

    }

    public ComplaintShortDTO(Long id, LocalDate dateOfComplaint, String complaintMessage){
        this.complaintMessage = complaintMessage;
        this.dateOfComplaint = dateOfComplaint;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfComplaint() {
        return dateOfComplaint;
    }

    public void setDateOfComplaint(LocalDate dateOfComplaint) {
        this.dateOfComplaint = dateOfComplaint;
    }

    public String getComplaintMessage() {
        return complaintMessage;
    }

    public void setComplaintMessage(String complaintMessage) {
        this.complaintMessage = complaintMessage;
    }
}
