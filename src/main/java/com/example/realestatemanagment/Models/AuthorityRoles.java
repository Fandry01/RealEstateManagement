package com.example.realestatemanagment.Models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@IdClass(AuthorityRolesKey.class)
@Table(name ="roles")
public class AuthorityRoles implements Serializable {

    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false,name = "authority_roles")
    private String authorityRoles;


    public AuthorityRoles(){}

    public AuthorityRoles(String username,String authorityRoles){
        this.authorityRoles =  authorityRoles;
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthorityRoles() {
        return authorityRoles;
    }

    public void setAuthorityRoles(String authorityRoles) {
        this.authorityRoles = authorityRoles;
    }

}
