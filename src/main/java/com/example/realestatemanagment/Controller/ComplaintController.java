package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Dto.ComplaintDTO;
import com.example.realestatemanagment.Dto.MaintenanceDTO;
import com.example.realestatemanagment.Service.ComplaintService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("complaints")
@RestController
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public ResponseEntity<List<ComplaintDTO>> getAllComplaints() {
        List<ComplaintDTO> complaintDTOS = complaintService.getAllComplaints();

        return ResponseEntity.ok().body(complaintDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComplaintDTO> getComplaintsById(@PathVariable("id") Long id) {
        ComplaintDTO complaintDTO = complaintService.getComplaintsById(id);

        return ResponseEntity.ok().body(complaintDTO);
    }

    @PostMapping
    public ResponseEntity<Object> addComplaint(@RequestBody ComplaintDTO complaintDTO) {
        ComplaintDTO complaintD = complaintService.addComplaint(complaintDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/" + complaintD.getId()).buildAndExpand(complaintD.getId()).toUri();

        return ResponseEntity.created(location).body(complaintD);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateComplaint(@PathVariable Long id, ComplaintDTO complaintDTO) {
        ComplaintDTO complaintD = complaintService.updateComplaint(id, complaintDTO);

        return ResponseEntity.ok().body(complaintD);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);

        return ResponseEntity.noContent().build();
    }


}
