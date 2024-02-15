package com.example.realestatemanagment.Controller;


import com.example.realestatemanagment.Dto.MaintenanceDTO;
import com.example.realestatemanagment.Service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }


    @GetMapping("/maintenances")
    public ResponseEntity <List<MaintenanceDTO>> getAllMaintenances(){
     List<MaintenanceDTO> maintenanceDTOS = maintenanceService.getAllMaintenance();

     return ResponseEntity.ok().body(maintenanceDTOS);
    }

    @GetMapping("/maintenances/{id}")
    public ResponseEntity<MaintenanceDTO> getMaintenanceById(@PathVariable("id") Long id){
        MaintenanceDTO maintenanceDTO = maintenanceService.getMaintenanceById(id);

        return ResponseEntity.ok().body(maintenanceDTO);
    }

    @PostMapping("maintenances")
    public ResponseEntity<Object> addMaintenance(@RequestBody MaintenanceDTO maintenanceDTO){
        MaintenanceDTO maintenanceDto = maintenanceService.addMaintenance(maintenanceDTO);

        return ResponseEntity.created(null).body(maintenanceDTO);
    }

    @DeleteMapping("/maintenances/{id}")
    public ResponseEntity<Object> deleteMaintenance(@PathVariable("id") Long id){
        maintenanceService.deleteMaintenance(id);

        return ResponseEntity.noContent().build();
    }

}
