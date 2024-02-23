package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Dto.ComplaintDTO;
import com.example.realestatemanagment.Models.Complaint;
import com.example.realestatemanagment.Repository.ComplaintRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ComplaintServiceTest {
    //Stap 1
    @Mock
    ComplaintRepository repos;
    //Stap 2
    @InjectMocks
    ComplaintService service;
    //Stap 3

    @Test
    public void shouldReturnAnComplaint(){
        // Arrange
            Complaint complaint = new Complaint(10L,new Date(2024-12-12),"Broken heater");

            when(repos.findById(anyLong())).thenReturn(Optional.of(complaint));
        // Act
            ComplaintDTO complaintDTO = service.getComplaintsById(10L);
        // Assert
            assertEquals("Broken heater",complaintDTO.getComplaintMessage());
            assertEquals(new Date(2024-12-12),complaintDTO.getDateOfComplaint());

    }

}