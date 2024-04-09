package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Dto.LeaseAgreementDTO;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Models.LeaseAgreement;
import com.example.realestatemanagment.Repository.InvestorRepository;
import com.example.realestatemanagment.Repository.LeaseAgreementRepository;
import com.example.realestatemanagment.Repository.PropertyRepository;
import com.example.realestatemanagment.Repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaseAgreementService {
    private final LeaseAgreementRepository leaseAgreementRepo ;
    private final PropertyRepository propertyRepo;
    private final PropertyService propertyservice;
    private final TenantRepository tenantRepo;
    private final TenantService tenantService;
    private final InvestorRepository investorRepo;
    private final InvestorService investorService;

    public LeaseAgreementService(LeaseAgreementRepository leaseAgreementRepo, PropertyRepository propertyRepo, PropertyService propertyservice, TenantRepository tenantRepo, TenantService tenantService, InvestorRepository investorRepo, InvestorService investorService) {
        this.leaseAgreementRepo = leaseAgreementRepo;
        this.propertyRepo = propertyRepo;
        this.propertyservice = propertyservice;
        this.tenantRepo = tenantRepo;
        this.tenantService = tenantService;
        this.investorRepo = investorRepo;
        this.investorService = investorService;
    }

    public List<LeaseAgreementDTO> getAllLeaseAgreements(){
        List<LeaseAgreementDTO> leaseAgreementList = new ArrayList<>();
        List<LeaseAgreement> leaseList = leaseAgreementRepo.findAll();

        for(LeaseAgreement leaseAgreement :leaseList){
            leaseAgreementList.add(transferToDTO(leaseAgreement));
        }
        return leaseAgreementList;
    }

    public LeaseAgreementDTO getLeaseAgreementById(Long id){
        Optional<LeaseAgreement> optionalLeaseAgreement = leaseAgreementRepo.findById(id);
        if(optionalLeaseAgreement.isPresent()){
            LeaseAgreement leaseAgreement = optionalLeaseAgreement.get();

            return transferToDTO(leaseAgreement);
        } else {
            throw new RecordNotFoundException("No Lease Agreement Found !");
        }
    }

    public LeaseAgreementDTO addAgreement(LeaseAgreementDTO dto){
        LeaseAgreement leaseAgreement = transferToLeaseAgreement(dto);
        leaseAgreementRepo.save(leaseAgreement);
        return transferToDTO(leaseAgreement);
    }

    public void deleteLeaseAgreementById(long id)
    {
        propertyRepo.deleteById(id);
    }

    public LeaseAgreementDTO updateLeaseAgreement(Long id, LeaseAgreementDTO dto){
        if(leaseAgreementRepo.findById(id).isPresent()){
            LeaseAgreement leaseAgreement  = leaseAgreementRepo.findById(id).get();
            LeaseAgreement leaseAgreement1 = transferToLeaseAgreement(dto);
            leaseAgreement1.setId(leaseAgreement.getId());
            leaseAgreementRepo.save(leaseAgreement1);
            return transferToDTO(leaseAgreement1);
        }else{
            throw new RecordNotFoundException("LeaseAgreement not found");
        }
    }



    public LeaseAgreementDTO transferToDTO(LeaseAgreement leaseAgreement){
        var dto = new LeaseAgreementDTO();

        dto.setId(leaseAgreement.getId());
        dto.setStartDate(leaseAgreement.getStartDate());
        dto.setEndDate(leaseAgreement.getEndDate());
        dto.setPaymentTerms(leaseAgreement.getPaymentTerms());
        dto.setRentalPeriod(leaseAgreement.getRentalPeriod());
        dto.setRentalPrice(leaseAgreement.getRentalPrice());

        return dto;
    }

    public LeaseAgreement transferToLeaseAgreement(LeaseAgreementDTO dto){

        var leaseAgreement = new LeaseAgreement();
        leaseAgreement.setStartDate(dto.getStartDate());
        leaseAgreement.setEndDate(dto.getEndDate());
        leaseAgreement.setPaymentTerms(dto.getPaymentTerms());
        leaseAgreement.setRentalPeriod(dto.getRentalPeriod());
        leaseAgreement.setRentalPrice(dto.getRentalPrice());

        return leaseAgreement;
    }

    public void assignLeaseAgreementToTenant(Long id, String tenantId)
    {
        var optionalLeaseAgreement = leaseAgreementRepo.findById(id);
        var optionalTenant = tenantRepo.findById(tenantId);

        if(optionalLeaseAgreement.isPresent() && optionalTenant.isPresent()){
            var leaseAgreement = optionalLeaseAgreement.get();
            var tenant = optionalTenant.get();

            leaseAgreement.setTenant(tenant);
            leaseAgreementRepo.save(leaseAgreement);

        }else {
            throw new RecordNotFoundException("leaseAgreement or tenant");
        }
    }

    public void assignLeaseAgreementToInvestor(Long id,String investorId)
    {
        var optionalLeaseAgreement = leaseAgreementRepo.findById(id);
        var optionalInvestor = investorRepo.findById(investorId);

        if(optionalLeaseAgreement.isPresent() && optionalInvestor.isPresent()){
            var leaseAgreement = optionalLeaseAgreement.get();
            var investor = optionalInvestor.get();

            leaseAgreement.setInvestor(investor);
            leaseAgreementRepo.save(leaseAgreement);

        }else{
            throw new RecordNotFoundException("LeaseAgreement or investor not found");
        }
    }

    public void assignLeaseAgreementToProperty(Long id,Long propertyId){
        var optionalLeaseAgreement = leaseAgreementRepo.findById(id);
        var optionalProperty = propertyRepo.findById(propertyId);

        if(optionalLeaseAgreement.isPresent() && optionalProperty.isPresent()){
            var leaseAgreement = optionalLeaseAgreement.get();
            var property = optionalProperty.get();

            leaseAgreement.setProperty(property);
            leaseAgreementRepo.save(leaseAgreement);
        }

    }

}
