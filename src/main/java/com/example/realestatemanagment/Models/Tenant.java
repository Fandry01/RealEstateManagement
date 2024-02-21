package com.example.realestatemanagment.Models;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "tenants")
public class Tenant extends User {

    @Column(name = "rental_periode")
    private Date rentalPeriod;
    @Column(name = "rent_price")
    private Integer rentPrice;

    @OneToMany(targetEntity = AuthorityRoles.class,
    mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<AuthorityRoles> authoritySet = new HashSet<>();

    public Set<AuthorityRoles> getRoles() { return authoritySet; }
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

}
