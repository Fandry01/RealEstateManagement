package com.example.realestatemanagment.Controller;


import com.example.realestatemanagment.Dto.TenantDTO;
import com.example.realestatemanagment.Exceptions.BadRequestException;
import com.example.realestatemanagment.Service.TenantService;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/tenants")
public class TenantController {
    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping
    public ResponseEntity<List<TenantDTO>> getAllTenants(){
        List<TenantDTO> tenantDTOList = tenantService.getAllTenants();

        return ResponseEntity.ok().body(tenantDTOList);
    }

    @GetMapping(value ="/{username}")
    public ResponseEntity<TenantDTO> getTenant(@PathVariable("username") String username){
        TenantDTO optionalTenant = tenantService.getTenantByUsername(username);

        return ResponseEntity.ok().body(optionalTenant);
    }
    @PostMapping
    public ResponseEntity<TenantDTO> createTenant(@RequestBody TenantDTO tenantDTO){
        String  newUsername = tenantService.createTenant(tenantDTO);
        tenantService.addAuthority(newUsername,"ROLE_USER");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username").buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping(value = "/{username}")
    public ResponseEntity<TenantDTO> updateTenant(@PathVariable("username") String username,@RequestBody TenantDTO dto){
        tenantService.updateTenant(username,dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteTenant(@PathVariable("username") String username){
        tenantService.deleteTenant(username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{username}/roles")
    public ResponseEntity<Object> addInvestorAuthorityRoles(@PathVariable("username") String username,@RequestBody Map<String, Object> fields){
        try{
            String authorityName = (String) fields.get("authority");
            tenantService.addAuthority(username,authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex){
            throw new BadRequestException();
        }

    }
    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getTenantAuthorityRoles(@PathVariable("username") String username){
       return ResponseEntity.ok().body(tenantService.getAuthorities(username));
    }

    @DeleteMapping(value = "/{username}/roles/{authority}")
    public ResponseEntity<Object> deleteInvestorAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority)
    {
        tenantService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/{username}/complaints/{complaintId}")
    public ResponseEntity<Object> assignComplaintsToTenants(@PathVariable("username") String username, @PathVariable("complaintId") Long complaintId){
        tenantService.assignComplaintToTenant(username,complaintId);
        return ResponseEntity.noContent().build();
    }
}
