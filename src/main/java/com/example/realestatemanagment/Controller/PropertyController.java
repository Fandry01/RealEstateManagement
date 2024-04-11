package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Dto.PropertyDTO;
import com.example.realestatemanagment.Exceptions.BadRequestException;
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
    public ResponseEntity <List<PropertyDTO>> getAllProperties(){
            List<PropertyDTO> propDTOS = propertyService.getAllProperties();
            return ResponseEntity.ok().body(propDTOS);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable("id")Long id){
        try{
            PropertyDTO propertyDTO = propertyService.getPropertyById(id);

            return ResponseEntity.ok().body(propertyDTO);
        }catch(Exception e){
            throw new RecordNotFoundException("Property with ID " + id + "not found");
        }
    }

    @PostMapping
    public ResponseEntity<Object> addProperty(@Valid @RequestBody PropertyDTO propertyDTO, BindingResult bindingResult) throws MethodArgumentNotValidException{
            if(bindingResult.hasErrors() ){
                throw new MethodArgumentNotValidException(null,bindingResult);
            }else{
                PropertyDTO propDto = propertyService.addProperty(propertyDTO);

                URI uri = URI.create(
                ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + propDto).toUriString());

                return ResponseEntity.created(uri).body(propDto);
            }

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        try{
            propertyService.deletePropertyById(id);
            return ResponseEntity.noContent().build();
        }catch (Exception ex){
            throw new RecordNotFoundException(id +"Not Found");

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProperty(@PathVariable Long id, PropertyDTO propertyDTO, BindingResult bindingResult) throws MethodArgumentNotValidException{
        if(bindingResult.hasErrors()){
            throw new MethodArgumentNotValidException(null,bindingResult);
        }else{
            PropertyDTO dto = propertyService.updateProperty(id,propertyDTO);
            return ResponseEntity.ok().body(dto);
        }
    }

    @PutMapping("/{id}/complaints/{complaintId}")
    public ResponseEntity<Object> assignComplaintToProperties(@PathVariable("id") Long id, @PathVariable("complaintId") Long complaintId){
     propertyService.assignComplaintToProperty(id,complaintId);
     return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/investors/{username}")
        public ResponseEntity<Object> assignPropertyToInvestor(@PathVariable("id") Long id,@PathVariable("username") String username){
        propertyService.assignPropertyToInvestor(id,username);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/maintenance")
    public ResponseEntity<Object> assignMaintenanceProperties(@PathVariable ("id") Long id, @PathVariable("maintenanceId") Long maintenanceId)
    {
       propertyService.assignMaintenanceToProperty(id,maintenanceId);
       return ResponseEntity.noContent().build();
    }




}
