package com.example.realestatemanagment.Dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MaintenanceDTO {
    private Long id;
    @NotNull
    private String typeOfMaintenance;
    @NotNull
    @Future
    private LocalDate maintenanceDate;

    public MaintenanceDTO()
    {

    }

    public void setTypeOfMaintenance(String typeOfMaintenance) {
        this.typeOfMaintenance = typeOfMaintenance;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public Long getId() {
        return id;
    }

    public String getTypeOfMaintenance() {
        return typeOfMaintenance;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
