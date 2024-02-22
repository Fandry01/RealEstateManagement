package com.example.realestatemanagment.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "complaints")
public class Complaint {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "date_of_complaint")
    private Date dateOfComplaint;
    @Column(name = "complaint_message")
    private String complaintMessage;

    @OneToOne
    Tenant tenant;

    @OneToOne
    Property property;
    public Complaint(){

    }
    public Complaint(Long id, Date dateOfComplaint, String complaintMessage){
        this.id = id;
        this.complaintMessage = complaintMessage;
        this.dateOfComplaint = dateOfComplaint;
    }

    public Long getId() {
        return id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Date getDateOfComplaint() {
        return dateOfComplaint;
    }

    public String getComplaintMessage() {
        return complaintMessage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public void setComplaintMessage(String complaintMessage) {
        this.complaintMessage = complaintMessage;
    }

    public void setDateOfComplaint(Date dateOfComplaint) {
        this.dateOfComplaint = dateOfComplaint;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
