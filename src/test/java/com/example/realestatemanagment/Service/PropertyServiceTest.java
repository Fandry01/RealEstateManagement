package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Dto.PropertyDTO;
import com.example.realestatemanagment.Enums.HouseTypes;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Models.Complaint;
import com.example.realestatemanagment.Models.Investor;
import com.example.realestatemanagment.Models.Maintenance;
import com.example.realestatemanagment.Models.Property;
import com.example.realestatemanagment.Repository.ComplaintRepository;
import com.example.realestatemanagment.Repository.InvestorRepository;
import com.example.realestatemanagment.Repository.MaintenanceRepository;
import com.example.realestatemanagment.Repository.PropertyRepository;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {
    //stap1
    @Mock
    PropertyRepository propertyRepo;
    @Mock
    InvestorRepository investorRepo;
    @Mock
    ComplaintRepository complaintRepo;
    @Mock
    MaintenanceRepository maintenanceRepo;
    @Mock
    MaintenanceService maintenanceService;

    @Mock
    InvestorService investorService;
    @Mock
    ComplaintService complaintService;
    @Captor
    ArgumentCaptor<Property> argumentCaptor;
    //@InjectMocks
    PropertyService propertyService;

    Property property1;
    Property  property2;
    Complaint complaint1;
    Maintenance maintenance1;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        property1 = new Property(10L,HouseTypes.APPARTEMENT,15000.00,17000.00,1999,"150M2",true,"Holendrecht12");
        property2 = new Property(12L,HouseTypes.VRIJSTAANDEHUIS,17500.00,19500.00,2015,"240m2",false,"Diemen15");
        complaint1 = new Complaint(10L, LocalDate.of(2024,10,15),"Its To cold here");
        maintenance1 = new Maintenance(20L,"Bathroom",LocalDate.of(2024,5,5));
        propertyService = new PropertyService(propertyRepo, complaintRepo, complaintService, maintenanceRepo,
                maintenanceService, investorService, investorRepo);
    }


    @Test
    void getAllProperties(){
        when(propertyRepo.findAll()).thenReturn((List.of(property1, property2)));

        List<Property> properties = propertyRepo.findAll();
        List<PropertyDTO> dtos = propertyService.getAllProperties();

        assertEquals(properties.get(0).getCurrentPrice(),dtos.get(0).getCurrentPrice());
        assertEquals(properties.get(0).getAddress(),dtos.get(0).getAddress());
        assertEquals(properties.get(0).getBuildYear(),dtos.get(0).getBuildYear());
        assertEquals(properties.get(0).getSquareFeet(),dtos.get(0).getSquareFeet());
        assertEquals(properties.get(0).getRented(),dtos.get(0).getRented());
        assertEquals(properties.get(0).getId(),dtos.get(0).getId());
    }

    @Test
    public void shouldReturnAProperty(){
        Property property = new Property(10L,HouseTypes.APPARTEMENT,15000.00,17000.00,1999,"150M2",true,"Holendrecht12");

        when(propertyRepo.findById(anyLong())).thenReturn((Optional.of(property)));
        when(propertyRepo.findById(150L)).thenReturn(Optional.empty());

        PropertyDTO propertyDTO = propertyService.getPropertyById(10L);

        assertEquals("Appartement",propertyDTO.getType());
        assertEquals(15000.00,propertyDTO.getBoughtPrice());
        assertEquals(17000.00,propertyDTO.getCurrentPrice());
        assertEquals(1999,propertyDTO.getBuildYear());
        assertEquals("150M2",propertyDTO.getSquareFeet());
        assertEquals(true,propertyDTO.getRented());
        assertEquals("Holendrecht12",propertyDTO.getAddress());

        assertThrows(RecordNotFoundException.class, ()->{
           propertyService.getPropertyById(150L);
        },"property not found");

        verify(propertyRepo, times(1)).findById(150L);

    }




    @Test
    public void shouldSaveAProperty(){
        //when(propertyRepo.save(property1)).thenReturn((property1));
        propertyService.addProperty(propertyService.transferToDTO(property1));
        verify(propertyRepo,times(1)).save(argumentCaptor.capture());

        Property property = argumentCaptor.getValue();

        assertEquals(property1.getAddress(), property.getAddress());
        assertEquals(property1.getType(), property.getType());
        assertEquals(property1.getBoughtPrice(), property.getBoughtPrice());
        assertEquals(property1.getCurrentPrice(), property.getCurrentPrice());
        assertEquals(property1.getBuildYear(), property.getBuildYear());
        assertEquals(property1.getSquareFeet(), property.getSquareFeet());
        assertEquals(property1.getRented(), property.getRented());
    }

    @Test
    public void deleteAProperty(){
        propertyService.deletePropertyById(10L);

        verify(propertyRepo).deleteById(10L);
    }

    @Test
    public void shouldUpdateAProperty(){
        when(propertyRepo.findById(10L)).thenReturn(Optional.of(property1));
        PropertyDTO propertyDTO = new PropertyDTO(10L, HouseTypes.APPARTEMENT,15000.00,18000.00,1999,"150M2",false,"Holendrecht12");
        //when(propertyRepo.save(propertyService.transferToProperty(propertyDTO))).thenReturn(property1);

        PropertyDTO resultDTO =  propertyService.updateProperty(10L,propertyDTO);



        assertNotNull(resultDTO);
        assertEquals(propertyDTO.getType(), resultDTO.getType());
        assertEquals(propertyDTO.getAddress(), resultDTO.getAddress());
        assertEquals(propertyDTO.getBoughtPrice(), resultDTO.getBoughtPrice());
        assertEquals(propertyDTO.getCurrentPrice(), resultDTO.getCurrentPrice());
        assertEquals(propertyDTO.getBuildYear(), resultDTO.getBuildYear());
        assertEquals(propertyDTO.getSquareFeet(), resultDTO.getSquareFeet());
        assertEquals(propertyDTO.getRented(), resultDTO.getRented());

        assertThrows(RecordNotFoundException.class,() -> {

            propertyService.updateProperty(11L,resultDTO);
        }, "Complaint Not Found");

        verify(propertyRepo, times(2)).findById(10L);
        verify(propertyRepo,times(1)).save(argumentCaptor.capture());

    }


    @Test
    public void shouldAssignPropertyToInvestor() {
        Long propertyId = 10L;
        String investorId = "investor1";
        Property property = new Property();
        Investor investor = new Investor();

        // Configure mocks
        when(propertyRepo.findById(propertyId)).thenReturn(Optional.of(property));
        when(investorRepo.findById(investorId)).thenReturn(Optional.of(investor));


        propertyService.assignPropertyToInvestor(propertyId, investorId);


        verify(propertyRepo, times(1)).findById(propertyId);
        verify(investorRepo, times(1)).findById(investorId);
        verify(propertyRepo, times(1)).save(property);


        assertEquals(investor, property.getInvestor());
    }

    @Test
    public void shouldAssignComplaintToProperty() {



        when(propertyRepo.findById(property1.getId())).thenReturn(Optional.of(property1));
        when(complaintRepo.findById(complaint1.getId())).thenReturn(Optional.of(complaint1));


        propertyService.assignComplaintToProperty(property1.getId(), complaint1.getId());


        verify(propertyRepo, times(1)).findById(ArgumentMatchers.anyLong());
        verify(complaintRepo, times(1)).findById(complaint1.getId());
        verify(propertyRepo, times(1)).save(argumentCaptor.capture());

        Property capturedProperty = argumentCaptor.getValue();

        assertEquals(complaint1.getId(), capturedProperty.getComplaint().getId());
        assertEquals(complaint1.getComplaintMessage(), capturedProperty.getComplaint().getComplaintMessage());

    }

    @Test
    public void shouldThrowRecordNotFoundExceptionWhenPropertyOrComplaintNotFound() {

        when(propertyRepo.findById(anyLong())).thenReturn(Optional.empty());
        when(complaintRepo.findById(anyLong())).thenReturn(Optional.empty());


        assertThrows(RecordNotFoundException.class, () -> {
            propertyService.assignComplaintToProperty(1L, 1L);
        });


        verify(propertyRepo, never()).save(any());
    }

    @Test
    public void shouldThrowRecordNotFoundExceptionWhenPropertyOrInvestorNotFound() {

        when(propertyRepo.findById(anyLong())).thenReturn(Optional.empty());
        when(investorRepo.findById(anyString())).thenReturn(Optional.empty());


        assertThrows(RecordNotFoundException.class, () -> {
            propertyService.assignPropertyToInvestor(1L, "123");
        });


        verify(propertyRepo, never()).save(any());
    }
    @Test
    public void shouldThrowRecordNotFoundExceptionWhenPropertyOrMaintenanceNotFound() {

        when(propertyRepo.findById(anyLong())).thenReturn(Optional.empty());
        when(maintenanceRepo.findById(anyLong())).thenReturn(Optional.empty());


        assertThrows(RecordNotFoundException.class, () -> {
            propertyService.assignMaintenanceToProperty(1L, 1L);
        });


        verify(propertyRepo, never()).save(any());
    }



    @Test
    public void shouldAssignMaintenanceToProperty(){
        List<Maintenance> maintenanceList = new ArrayList<>();
        maintenanceList.add(maintenance1);
        property1.setMaintenances(maintenanceList);
        when(propertyRepo.findById(property1.getId())).thenReturn(Optional.of(property1));
        when(maintenanceRepo.findById(maintenance1.getId())).thenReturn(Optional.of(maintenance1));

        propertyService.assignMaintenanceToProperty(property1.getId(),maintenance1.getId());

        verify(propertyRepo,times(1)).findById(property1.getId());
        verify(propertyRepo,times(1)).save(argumentCaptor.capture());

        Property capturedProperty = argumentCaptor.getValue();
        assertEquals(maintenance1.getId(), capturedProperty.getMaintenances().get(0).getId());

    }



}