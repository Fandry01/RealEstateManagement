package com.example.realestatemanagment.Models;

import com.example.realestatemanagment.Enums.MaintenanceTypes;
import com.example.realestatemanagment.Enums.Priority;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Maintenances")
public class Maintenance {
    @Column(name = "type_of_maintenance")
    @Enumerated(EnumType.STRING)
    public MaintenanceTypes typeOfMaintenance;
    @Column(name = "maintenance_date")
    public LocalDate MaintenanceDate;
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    public Priority priority;
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Property property;


    public Maintenance() {

    }

    public Maintenance(Long id, MaintenanceTypes typeOfMaintenance, LocalDate maintenanceDate, Priority priority) {
        this.id = id;
        this.MaintenanceDate = maintenanceDate;
        this.typeOfMaintenance = typeOfMaintenance;
        this.priority = priority;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public LocalDate getMaintenanceDate() {
        return MaintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        MaintenanceDate = maintenanceDate;
    }

    public MaintenanceTypes getTypeOfMaintenance() {
        return typeOfMaintenance;
    }

    public void setTypeOfMaintenance(MaintenanceTypes typeOfMaintenance) {
        this.typeOfMaintenance = typeOfMaintenance;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
