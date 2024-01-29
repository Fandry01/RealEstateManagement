package com.example.realestatemanagment.Dto;

import java.util.Date;

public class MaintenanceDTO {
    private Long id;
    private String typeOfMaintenance;
    private Date maintenanceDate;

    public MaintenanceDTO()
    {

    }

    public void setTypeOfMaintenance(String typeOfMaintenance) {
        this.typeOfMaintenance = typeOfMaintenance;
    }

    public void setMaintenanceDate(Date maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public Date getMaintenanceDate() {
        return maintenanceDate;
    }

    public Long getId() {
        return id;
    }

    public String getTypeOfMaintenance() {
        return typeOfMaintenance;
    }
}
