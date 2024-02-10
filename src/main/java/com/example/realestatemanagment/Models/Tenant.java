package com.example.realestatemanagment.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private String Dob;
    @Column(name = "rental_periode")
    private Date rentalPeriod;
    @Column(name = "rent_price")
    private Integer rentPrice;

    @OneToOne
    Property property;

    @OneToOne
    Complaint complaint;
}
