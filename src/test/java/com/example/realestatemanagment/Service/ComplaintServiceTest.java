package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Dto.ComplaintDTO;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Models.Complaint;
import com.example.realestatemanagment.Models.Property;
import com.example.realestatemanagment.Repository.ComplaintRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComplaintServiceTest {
    //Stap 1
    @Mock
    ComplaintRepository complaintrepo;
    @Mock
    ComplaintService complaintService;
    //Stap 2
    @InjectMocks
    ComplaintService service;
    //Stap 3

    @Captor
    ArgumentCaptor<Complaint> argumentCaptor;

    Complaint complaint1;
    Complaint complaint2;

    @BeforeEach
    void setUp(){
        complaint1 = new Complaint(10L, LocalDate.of(2024,10,15),"Its To cold here");
        complaint2 = new Complaint(15L, LocalDate.of(2024,8,15),"no water in the bathroom");
        complaintService = new ComplaintService(complaintrepo);
    }

    @Test
    public void shouldReturnAnComplaint(){
        // Arrange
            Complaint complaint = new Complaint(10L,LocalDate.of(2024,12,12),"Broken heater");

            when(complaintrepo.findById(anyLong())).thenReturn(Optional.of(complaint));
            when(complaintrepo.findById(20L)).thenReturn(Optional.empty());
        // Act
            ComplaintDTO complaintDTO = service.getComplaintsById(10L);
        // Assert
            assertEquals("Broken heater",complaintDTO.getComplaintMessage());
            assertEquals( LocalDate.of(2024,12,12),complaintDTO.getDateOfComplaint());
            assertThrows(RecordNotFoundException.class, () -> {
            complaintService.getComplaintsById(20L);
            }, "Complaint not found");

            verify(complaintrepo, times(1)).findById(20L);
    }
    @Test
    public  void shouldReturnAllComplaints(){
        when(complaintrepo.findAll()).thenReturn(List.of(complaint1,complaint2));

        List<Complaint> complaints = complaintrepo.findAll();
        List<ComplaintDTO> dtos = complaintService.getAllComplaints();

        assertEquals(complaints.get(0).getComplaintMessage(),dtos.get(0).getComplaintMessage());
        assertEquals(complaints.get(0).getDateOfComplaint(),dtos.get(0).getDateOfComplaint());
        assertEquals(complaints.get(0).getId(),dtos.get(0).getId());

    }

    @Test
    public void shouldSaveAComplaint(){
        complaintService.addComplaint(ComplaintService.transferToDTO(complaint1));
        verify(complaintrepo,times(1)).save(argumentCaptor.capture());
        Complaint complaint = argumentCaptor.getValue();

        assertEquals(complaint1.getDateOfComplaint(),complaint.getDateOfComplaint());
        assertEquals(complaint1.getComplaintMessage(),complaint.getComplaintMessage());
    }
    @Test
    public void shouldUpdateAnComplaint(){
        when(complaintrepo.findById(10L)).thenReturn(Optional.of(complaint1));
        ComplaintDTO complaintDTO = new ComplaintDTO(10L,LocalDate.of(2025,5,5),"bathroom no hot water");

        ComplaintDTO resultDTO = complaintService.updateComplaint(10L,complaintDTO);
        assertNotNull(resultDTO);
        assertEquals(complaintDTO.getComplaintMessage(),resultDTO.getComplaintMessage());
        assertEquals(complaintDTO.getDateOfComplaint(), resultDTO.getDateOfComplaint());
        assertThrows(RecordNotFoundException.class,() -> {

            complaintService.updateComplaint(11L,resultDTO);
        }, "Complaint Not Found");

        verify(complaintrepo,times(2)).findById(10L);
        verify(complaintrepo,times(1)).save(argumentCaptor.capture());

    }

    @Test
    public void testTransferToDTO() {

        ComplaintDTO dto = ComplaintService.transferToDTO(complaint1);

        assertEquals(complaint1.getId(), dto.getId());
        assertEquals(complaint1.getComplaintMessage(), dto.getComplaintMessage());
        assertEquals(complaint1.getDateOfComplaint(), dto.getDateOfComplaint());
    }

    @Test
    public void shouldDeleteAComplaint(){
        complaintService.deleteComplaint(10L);
        verify(complaintrepo).deleteById(10L);
    }


    @Test
    public void testTransferToComplaint() {

        ComplaintDTO complaintDTO = new ComplaintDTO();
        complaintDTO.setComplaintMessage("Test complaint message");
        complaintDTO.setDateOfComplaint(LocalDate.now());

        Complaint complaint = ComplaintService.transferToComplaint(complaintDTO);

        assertEquals(complaintDTO.getComplaintMessage(), complaint.getComplaintMessage());
        assertEquals(complaintDTO.getDateOfComplaint(), complaint.getDateOfComplaint());
    }




}