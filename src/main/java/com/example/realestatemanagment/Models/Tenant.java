package com.example.realestatemanagment.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Tenants")
public class Tenant {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String Dob;
    private Date rentalPeriod;
    private Integer rentPrice;

    @OneToOne(mappedBy = "properties")
    Property property;

    @OneToOne(mappedBy = "complaint")
    Complaint complaint;
}
