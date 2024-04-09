package com.example.realestatemanagment.Controller;


import com.example.realestatemanagment.Dto.LeaseAgreementDTO;
import com.example.realestatemanagment.Exceptions.BadRequestException;
import com.example.realestatemanagment.Service.LeaseAgreementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/leaseagreement")
@RestController
public class LeaseAgreementController {
    private final LeaseAgreementService leaseAgreementService;

    public LeaseAgreementController(LeaseAgreementService leaseAgreementService) {
        this.leaseAgreementService = leaseAgreementService;
    }

    @GetMapping
    public ResponseEntity<List<LeaseAgreementDTO>> getALlAgreements(){
        List<LeaseAgreementDTO> leaseDTOS;
        leaseDTOS = leaseAgreementService.getAllLeaseAgreements();
        return ResponseEntity.ok().body(leaseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaseAgreementDTO> getLeaseAgreement(@PathVariable("id")Long id){
        LeaseAgreementDTO leaseAgreementDTO = leaseAgreementService.getLeaseAgreementById(id);
        return ResponseEntity.ok().body(leaseAgreementDTO);
    }

    @PostMapping
    public ResponseEntity<Object> addLeaseAgreement(@RequestBody LeaseAgreementDTO leaseAgreementDTO){
        try{
            LeaseAgreementDTO leaseAgreementDTO1 = leaseAgreementService.addAgreement(leaseAgreementDTO);
            return ResponseEntity.created(null).body(leaseAgreementDTO1);
        }catch (Exception ex){
            throw new BadRequestException();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
        leaseAgreementService.deleteLeaseAgreementById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLeaseAgreement(@PathVariable("id") Long id,LeaseAgreementDTO leaseAgreementDTO ){
        LeaseAgreementDTO agreementDTO = leaseAgreementService.updateLeaseAgreement(id, leaseAgreementDTO);
        return  ResponseEntity.ok().body(agreementDTO);
    }

    @PutMapping("/{id}/properties/{propertyId}")
    public ResponseEntity<Object> assignPropertyToLeaseAgreement(@PathVariable("id")Long id, @PathVariable("propertyId") Long propertyId){
        leaseAgreementService.assignLeaseAgreementToProperty(id,propertyId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/tenants/{tenantId}")
    public ResponseEntity<Object> assignTenantToLeaseAgreement(@PathVariable("id") Long id,@PathVariable("tenantId")String tenantId){
        leaseAgreementService.assignLeaseAgreementToTenant(id,tenantId);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}/investor/{tenantId}")
    public ResponseEntity<Object> assignInvestorToLeaseAgreement(@PathVariable("id") long id,@PathVariable("investorId") String investorId){
        leaseAgreementService.assignLeaseAgreementToInvestor(id,investorId);
        return ResponseEntity.ok().build();
    }
}
