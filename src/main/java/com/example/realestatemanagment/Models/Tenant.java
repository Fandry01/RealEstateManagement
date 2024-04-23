package com.example.realestatemanagment.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "tenants")
public class Tenant extends User {


    @OneToOne
    Property property;
    @OneToOne
    LeaseAgreement leaseAgreement;

    @OneToMany(mappedBy = "tenant")
    private List<Complaint> complaints;

    public Tenant() {
        super();
    }

    public Tenant(Property property, LeaseAgreement leaseAgreement, List<Complaint> complaints) {
        this.property = property;
        this.leaseAgreement = leaseAgreement;
        this.complaints = complaints;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LeaseAgreement getLeaseAgreement() {
        return leaseAgreement;
    }

    public void setLeaseAgreement(LeaseAgreement leaseAgreement) {
        this.leaseAgreement = leaseAgreement;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }
}

