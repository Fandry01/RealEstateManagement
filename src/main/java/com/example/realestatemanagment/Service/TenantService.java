package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Dto.TenantDTO;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Exceptions.UsernameNotFoundException;
import com.example.realestatemanagment.Models.AuthorityRoles;
import com.example.realestatemanagment.Models.Tenant;
import com.example.realestatemanagment.Repository.ComplaintRepository;
import com.example.realestatemanagment.Repository.PropertyRepository;
import com.example.realestatemanagment.Repository.TenantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TenantService {
    private final TenantRepository tenantRepo;
    private final PropertyRepository propertyRepo;
    private final PropertyService propertyService;
    private final ComplaintRepository complaintRepo;
    private final ComplaintService complaintService;
    private final PasswordEncoder passwordEncoder;


    public TenantService(TenantRepository tenantRepo, PropertyRepository propertyRepo, PropertyService propertyService, ComplaintRepository complaintRepo, ComplaintService complaintService, PasswordEncoder passwordEncoder) {
        this.tenantRepo = tenantRepo;
        this.propertyRepo = propertyRepo;
        this.propertyService = propertyService;
        this.complaintRepo = complaintRepo;
        this.complaintService = complaintService;
        this.passwordEncoder = passwordEncoder;
    }


    public List<TenantDTO> getAllTenats(){
        List<TenantDTO> tenantList = new ArrayList<>();
        List<Tenant> tenList = tenantRepo.findAll();
        for(Tenant tenant: tenList){
            tenantList.add(transferToDTO(tenant));
        }
        return tenantList;
    }
    public TenantDTO getTenantByUsername(String username){
        Optional<Tenant> optionalTenant = tenantRepo.findById(username);
        if(optionalTenant.isPresent()){
            Tenant tenant = optionalTenant.get();

            return transferToDTO(tenant);
        }else {
            throw new RecordNotFoundException("Tenant not found");
        }
    }

    public String createTenant(TenantDTO tenant){
        Tenant newTenant = tenantRepo.save(transferToTenant(tenant));
        return newTenant.getUsername();
    }

    public void updateTenant(String username, TenantDTO tenantDTO){
        if(!tenantRepo.existsById(username)) throw new RecordNotFoundException("Tenant not found");
        Tenant tenant = tenantRepo.findById(username).get();
        tenant.setPassword(tenantDTO.getPassword());
        tenantRepo.save(tenant);
    }
    public void deleteTenant(String username){
        tenantRepo.deleteById(username);
    }

    public void assignPropertyToTenant(String username,Long propertyId){
        var optionalProperty = propertyRepo.findById(propertyId);
        var optionalTenant = tenantRepo.findById(username);

        if(optionalTenant.isPresent() && optionalProperty.isPresent()){
            var property = optionalProperty.get();
            var tenant = optionalTenant.get();

            tenant.setProperty(property);
            tenantRepo.save(tenant);
        }else{
            throw new RecordNotFoundException("Tenant or Property not found");
        }
    }

    public void assignComplaintToTenant(String username,Long complaintId){
        var optionalComplaint = complaintRepo.findById(complaintId);
        var optionalTenant = tenantRepo.findById(username);

        if(optionalTenant.isPresent() && optionalComplaint.isPresent()){
            var complaint = optionalComplaint.get();
            var tenant = optionalTenant.get();

            tenant.setComplaint(complaint);
            tenantRepo.save(tenant);
        }else{
            throw new RecordNotFoundException("Tenant or complaint not found");
        }
    }

    public void addAuthority(String username, String authority){
        if(!tenantRepo.existsById(username)) throw new RecordNotFoundException("Tenant not found");
        Tenant tenant = tenantRepo.findById(username).get();
        tenant.addAuthorityRoles(new AuthorityRoles(username,authority));
        tenantRepo.save(tenant);
    }

    public Set<AuthorityRoles> getAuthorities(String username){
        if(!tenantRepo.existsById(username)) throw new UsernameNotFoundException(username);
        Tenant tenant = tenantRepo.findById(username).get();
        TenantDTO tenantDTO = transferToDTO(tenant);
        return tenantDTO.getAuthorities();
    }

    public void removeAuthority(String username, String authority){
        if(!tenantRepo.existsById(username)) throw new UsernameNotFoundException(username);
        Tenant tenant = tenantRepo.findById(username).get();
        AuthorityRoles authorityRemove = tenant.getRoles().stream().filter(a -> a.getAuthorityRoles().equalsIgnoreCase(authority)).findAny().get();

        tenant.deleteAuthorityRoles(authorityRemove);
    }




    public TenantDTO transferToDTO(Tenant tenant){
        var dto = new TenantDTO();

        if(tenant.getComplaint() != null){
            dto.setComplaintDTO(complaintService.getComplaintsById(tenant.getComplaint().getId()));
        }
        if(tenant.getProperty() != null){
            dto.setPropertyDTO(propertyService.getPropertyById(tenant.getProperty().getId()));
        }

        dto.setUsername(tenant.getUsername());
        dto.setPassword(tenant.getPassword());
        dto.setFirstName(tenant.getFirstName());
        dto.setDob(tenant.getDob());
        dto.setLastName(tenant.getLastName());
        dto.setRentalPeriod(tenant.getRentalPeriod());
        dto.setRentPrice(tenant.getRentPrice());

        return dto;
    }
    public Tenant transferToTenant(TenantDTO dto){
        var tenant = new Tenant();

        tenant.setUsername(dto.getUsername());
        tenant.setPassword(passwordEncoder.encode(dto.getPassword()));
        tenant.setFirstName(dto.getFirstName());
        tenant.setLastName(dto.getLastName());
        tenant.setDob(dto.getDob());
        tenant.setRentalPeriod(dto.getRentalPeriod());
        tenant.setRentPrice(dto.getRentPrice());

        return tenant;
    }


}
