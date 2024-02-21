package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Dto.PropertyDTO;
import com.example.realestatemanagment.Service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/properties")
@RestController
public class PropertyController {
    private final PropertyService propertyService;
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties(){
        List<PropertyDTO> propDtos;
        propDtos = propertyService.getAllProperties();

        return ResponseEntity.ok().body(propDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable("id")Long id){
        PropertyDTO propertyDTO = propertyService.getPropertyById(id);

        return ResponseEntity.ok().body(propertyDTO);
    }

    @PostMapping("properties")
    public ResponseEntity<Object> addProperty(@RequestBody PropertyDTO propertyDTO){
        PropertyDTO propDto = propertyService.addProperty(propertyDTO);
        return ResponseEntity.created(null).body(propDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        propertyService.deletePropertyById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProperty(@PathVariable Long id, PropertyDTO propertyDTO){
        PropertyDTO dto = propertyService.updateProperty(id,propertyDTO);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}/complaints")
    public ResponseEntity<Object> assignComplaintToProperties(@PathVariable("id") Long id, @PathVariable("complaintId") Long complaintId){
     propertyService.assignComplaintToProperty(id,complaintId);
     return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/maintenance")
    public ResponseEntity<Object> assignMaintenanceProperties(@PathVariable ("id") Long id, @PathVariable("maintenanceId") Long maintenanceId)
    {
       propertyService.assignMaintenanceToProperty(id,maintenanceId);
       return ResponseEntity.noContent().build();
    }




}
