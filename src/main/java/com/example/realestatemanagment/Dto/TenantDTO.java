package com.example.realestatemanagment.Dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class TenantDTO {
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String Dob;

}
