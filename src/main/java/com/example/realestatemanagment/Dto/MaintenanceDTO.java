package com.example.realestatemanagment.Dto;

import com.example.realestatemanagment.Enums.MaintenanceTypes;
import com.example.realestatemanagment.Enums.Priority;
import com.example.realestatemanagment.Models.Maintenance;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MaintenanceDTO {
    private Long id;
    @NotBlank(message = "Please choose what type of maintenance ")
    private MaintenanceTypes typeOfMaintenance;
    @NotNull
    @Future(message = "Please date the maintenance ahead")
    private LocalDate maintenanceDate;
    private Priority priority;

    private PropertyDTO propertyDTO;

    public MaintenanceDTO()
    {

    }

    public void setTypeOfMaintenance(MaintenanceTypes typeOfMaintenance) {
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

    public MaintenanceTypes getTypeOfMaintenance() {
        return typeOfMaintenance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public PropertyDTO getPropertyDTO() {
        return propertyDTO;
    }

    public void setPropertyDTO(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }
}
