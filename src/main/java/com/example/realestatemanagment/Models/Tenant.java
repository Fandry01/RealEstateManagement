package com.example.realestatemanagment.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false, length = 255)
    private String password;
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

    @OneToMany(targetEntity = AuthorityRoles.class,
    mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<AuthorityRoles> authoritySet = new ArrayList<>();

    public List<AuthorityRoles> getRoles() { return authoritySet; }
    public void addAuthorityRoles(AuthorityRoles authorityRoles) {
      this.authoritySet.add(authorityRoles);
    }
    public void deleteAuthorityRoles(AuthorityRoles authorityRoles){
        this.authoritySet.remove(authorityRoles);
    }

    @OneToOne
    Property property;

    @OneToOne
    Complaint complaint;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
