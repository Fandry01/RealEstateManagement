package com.example.realestatemanagment.Service;


import com.example.realestatemanagment.Dto.PropertyDTO;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Models.Property;
import com.example.realestatemanagment.Repository.ComplaintRepository;
import com.example.realestatemanagment.Repository.InvestorRepository;
import com.example.realestatemanagment.Repository.MaintenanceRepository;
import com.example.realestatemanagment.Repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PropertyService {
    private final PropertyRepository propertyRepo;
    private final ComplaintRepository complaintRepo;
    private final ComplaintService complaintService;
    private final MaintenanceRepository maintenanceRepo;
    private final MaintenanceService maintenanceService;

    private final InvestorService investorService;
    private final InvestorRepository investorRepo;

    public PropertyService(PropertyRepository propertyRepo, ComplaintRepository complaintRepo, ComplaintService complaintService, MaintenanceRepository maintenanceRepo, MaintenanceService maintenanceService, InvestorService investorService, InvestorRepository investorRepo){
        this.propertyRepo = propertyRepo;
        this.complaintRepo = complaintRepo;
        this.complaintService = complaintService;
        this.maintenanceRepo = maintenanceRepo;
        this.maintenanceService = maintenanceService;
        this.investorService = investorService;
        this.investorRepo = investorRepo;
    }

    public List<PropertyDTO> getAllProperties(){
        List<PropertyDTO> propertyList = new ArrayList<>();
        List<Property> propList = propertyRepo.findAll();

        for(Property property : propList){
            propertyList.add(transferToDTO(property));
        }
        return propertyList;

    }

    public PropertyDTO getPropertyById(Long id){
        Optional<Property> optionalProperty = propertyRepo.findById(id);
        if(optionalProperty.isPresent()){
            Property property = optionalProperty.get();

            return  transferToDTO(property);
        }else {
            throw new RecordNotFoundException("Property with ID " + id + "not found");
        }
    }

    public PropertyDTO addProperty( PropertyDTO dto){
        Property property = transferToProperty(dto);
        propertyRepo.save(property);

        return transferToDTO(property);
    }
    public void deletePropertyById(Long id)
    {
        if(propertyRepo.findById(id).isPresent()){
            propertyRepo.deleteById(id);
        }else {
            throw new RecordNotFoundException("property with ID " + id + "not found");
        }

    }

    public PropertyDTO updateProperty(Long id, PropertyDTO dto){
        if(propertyRepo.findById(id).isPresent()){
            Property property = propertyRepo.findById(id).get();
            Property property1 = transferToProperty(dto);
            property1.setId(property.getId());
            propertyRepo.save(property1);
            return transferToDTO(property1);
        }else {
            throw new RecordNotFoundException("Property Not Found");
        }

    }

    public PropertyDTO transferToDTO(Property property){
        var dto = new PropertyDTO();

        if(property.getComplaint() != null){
            dto.setComplaintDTO(complaintService.getComplaintsById(property.getComplaint().getId()));
        }
        if(property.getInvestor() != null){
            dto.setInvestorDTO(investorService.getInvestorById(property.getInvestor().getUsername()));
        }

        dto.setId(property.getId());
        dto.setAddress(property.getAddress());
        dto.setBoughtPrice(property.getBoughtPrice());
        dto.setBuildYear(property.getBuildYear());
        dto.setRented(property.getRented());
        dto.setSquareFeet(property.getSquareFeet());
        dto.setCurrentPrice(property.getCurrentPrice());
        dto.setType(property.getType());

        return dto;
    }


    public Property transferToProperty(PropertyDTO dto){
        var property = new Property();

        property.setAddress(dto.getAddress());
        property.setBoughtPrice(dto.getBoughtPrice());
        property.setBuildYear(dto.getBuildYear());
        property.setType(dto.getType());
        property.setSquareFeet(dto.getSquareFeet());
        property.setRented(dto.getRented());
        property.setCurrentPrice(dto.getCurrentPrice());

        return property;
    }

    public void assignPropertyToInvestor(Long id, String investorId){
        var optionalProperty = propertyRepo.findById(id);
        var optionalInvestor = investorRepo.findById(investorId);

        if(optionalProperty.isPresent() && optionalInvestor.isPresent()){
            var property = optionalProperty.get();
            var investor = optionalInvestor.get();

            property.setInvestor(investor);
            propertyRepo.save(property);

        } else{
            throw new RecordNotFoundException("Investor or Property not found");
        }
    }

    public void assignComplaintToProperty(Long id, Long complaintId){
     var optionalProperty = propertyRepo.findById(id);
     var optionalComplaint = complaintRepo.findById(complaintId);

     if(optionalProperty.isPresent() && optionalComplaint.isPresent()){
         var property = optionalProperty.get();
         var complaint = optionalComplaint.get();

         property.setComplaint(complaint);
         propertyRepo.save(property);
     } else{
        throw new RecordNotFoundException("property or complaint not found");
     }

     }

    public void assignMaintenanceToProperty(Long id, Long maintenanceId){
        var optionalProperty = propertyRepo.findById(id);
        var optionalMaintenance = maintenanceRepo.findById(maintenanceId);

        if (optionalProperty.isPresent() && optionalMaintenance.isPresent()){
            var property = optionalProperty.get();
            var maintenance = optionalMaintenance.get();

            property.getMaintenances().add(maintenance);
            maintenance.setProperty(property);

            maintenanceRepo.save(maintenance);
            propertyRepo.save(property);

        } else{
            throw new RecordNotFoundException("property or maintenance not found");
        }

    }



}
