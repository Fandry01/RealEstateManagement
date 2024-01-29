package com.example.realestatemanagment.Models;


import com.sun.tools.javac.Main;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Maintenance {
    @Id
    @GeneratedValue
    private Long Id;
    public String typeOfMaintenance;
    public Date MaintenanceDate;


    @ManyToOne
    private Property property;


    public Maintenance(){

    }

    public Maintenance(Long id, String typeOfMaintenance, Date maintenanceDate){
        this.Id = id;
        this.MaintenanceDate = maintenanceDate;
        this.typeOfMaintenance = typeOfMaintenance;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Property getProperty() {
        return property;
    }

    public Long getId() {
        return Id;
    }

    public Date getMaintenanceDate() {
        return MaintenanceDate;
    }

    public String getTypeOfMaintenance() {
        return typeOfMaintenance;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        MaintenanceDate = maintenanceDate;
    }

    public void setTypeOfMaintenance(String typeOfMaintenance) {
        this.typeOfMaintenance = typeOfMaintenance;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
