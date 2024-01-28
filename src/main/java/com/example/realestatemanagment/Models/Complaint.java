package com.example.realestatemanagment.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Investors")
public class Complaint {
    @Id
    @GeneratedValue
    private long id;
    private Date dateOfComplaint;
    private String complaintMessage;

    @OneToOne(mappedBy = "tenant")
    Tenant tenant;

}
