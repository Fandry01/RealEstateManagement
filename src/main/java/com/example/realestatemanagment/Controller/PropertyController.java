package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Dto.PropertyDTO;
import com.example.realestatemanagment.Enums.HouseTypes;
import com.example.realestatemanagment.Exceptions.BadRequestException;
import com.example.realestatemanagment.Exceptions.HttpMessageNotReadableException;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/properties")
@RestController
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> propDTOS = propertyService.getAllProperties();
        return ResponseEntity.ok().body(propDTOS);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable("id") Long id) {
        PropertyDTO propertyDTO = propertyService.getPropertyById(id);

        return ResponseEntity.ok().body(propertyDTO);

    }

    @PostMapping
    public ResponseEntity<Object> addProperty(@Valid @RequestBody PropertyDTO propertyDTO) {

            PropertyDTO propDto = propertyService.addProperty(propertyDTO);

            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + propDto).toUriString());

            return ResponseEntity.created(uri).body(propDto);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        propertyService.deletePropertyById(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProperty(@PathVariable Long id, PropertyDTO propertyDTO) {
        PropertyDTO dto = propertyService.updateProperty(id, propertyDTO);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}/complaints/{complaintId}")
    public ResponseEntity<Object> assignComplaintToProperties(@PathVariable("id") Long id, @PathVariable("complaintId") Long complaintId) {
        propertyService.assignComplaintToProperty(id, complaintId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/investors/{username}")
    public ResponseEntity<Object> assignPropertyToInvestor(@PathVariable("id") Long id, @PathVariable("username") String username) {
        propertyService.assignPropertyToInvestor(id, username);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/maintenance/{maintenanceId}")
    public ResponseEntity<Object> assignMaintenanceProperties(@PathVariable("id") Long id, @PathVariable("maintenanceId") Long maintenanceId) {
        propertyService.assignMaintenanceToProperty(id, maintenanceId);
        return ResponseEntity.noContent().build();
    }


}
