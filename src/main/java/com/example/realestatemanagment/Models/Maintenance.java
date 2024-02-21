package com.example.realestatemanagment.Models;


import com.sun.tools.javac.Main;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Maintenances" )
public class Maintenance {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "type_of_maintenance")
    public String typeOfMaintenance;
    @Column(name = "maintenance_date")
    public Date MaintenanceDate;


    @ManyToOne(fetch = FetchType.EAGER)
    private Property property;


    public Maintenance(){

    }

    public Maintenance(Long id, String typeOfMaintenance, Date maintenanceDate){
        this.id = id;
        this.MaintenanceDate = maintenanceDate;
        this.typeOfMaintenance = typeOfMaintenance;
    }

    public void setId(Long id) {
        id = id;
    }

    public Property getProperty() {
        return property;
    }

    public Long getId() {
        return id;
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
