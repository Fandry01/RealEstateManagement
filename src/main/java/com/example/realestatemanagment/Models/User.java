package com.example.realestatemanagment.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@MappedSuperclass
public class User {
    @Id
    private String username;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private LocalDate Dob;
    @OneToMany(
            targetEntity = AuthorityRoles.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<AuthorityRoles> authorities = new HashSet<>();

    public Set<AuthorityRoles> getRoles() {
        return authorities;
    }

    public void addAuthorityRoles(AuthorityRoles authorityRoles) {
        this.authorities.add(authorityRoles);
    }

    public void deleteAuthorityRoles(AuthorityRoles authorityRoles) {
        this.authorities.remove(authorityRoles);
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return Dob;
    }

    public void setDob(LocalDate dob) {
        Dob = dob;
    }

    public Set<AuthorityRoles> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityRoles> authorities) {
        this.authorities = authorities;
    }
}
