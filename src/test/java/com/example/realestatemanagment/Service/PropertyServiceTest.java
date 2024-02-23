package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Dto.PropertyDTO;
import com.example.realestatemanagment.Models.Property;
import com.example.realestatemanagment.Repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {
    //stap1
    @Mock
    PropertyRepository repos;
    @InjectMocks
    PropertyService service;

    @Test
    public void ShouldReturnAProperty(){
        Property property = new Property(10L,"Appartement",15000.00,17000.00,1999,"150M2",true,"Holendrecht12");
        //Arrange
        when(repos.findById(anyLong())).thenReturn(Optional.of(property));
        //Act
        PropertyDTO propertyDTO = service.getPropertyById(10L);
        assertEquals("Appartement", propertyDTO.getType());
        assertEquals(15000.00, propertyDTO.getBoughtPrice());
        assertEquals(17000.00, propertyDTO.getCurrentPrice());
        assertEquals(1999, propertyDTO.getBuildYear());
        assertEquals("150M2", propertyDTO.getSquareFeet());
        assertEquals(true, propertyDTO.getRented());
        assertEquals("Holendrecht12", propertyDTO.getAddress());


    }





}