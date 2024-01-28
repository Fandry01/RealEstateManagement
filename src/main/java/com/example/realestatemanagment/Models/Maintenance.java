package com.example.realestatemanagment.Models;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

public class Maintenance {
    @Id
    @GeneratedValue
    private Long Id;
    public String typeOfMaintenance;
    public Date MaintenanceDate;

}
