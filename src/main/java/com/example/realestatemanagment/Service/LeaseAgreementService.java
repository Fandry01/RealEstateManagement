package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Repository.LeaseAgreementRepository;
import com.example.realestatemanagment.Repository.PropertyRepository;
import com.example.realestatemanagment.Repository.TenantRepository;
import org.springframework.stereotype.Service;

@Service
public class LeaseAgreementService {
    private final LeaseAgreementRepository leaseAgreementRepo ;
    private final PropertyRepository propertyRepo;
    private final PropertyService propertyservice;
    private final TenantRepository tenantRepo;
    private final TenantService tenantService;
    private final InvestorService investorService;

    public LeaseAgreementService(LeaseAgreementRepository leaseAgreementRepo, PropertyRepository propertyRepo, PropertyService propertyservice, TenantRepository tenantRepo, TenantService tenantService, InvestorService investorService) {
        this.leaseAgreementRepo = leaseAgreementRepo;
        this.propertyRepo = propertyRepo;
        this.propertyservice = propertyservice;
        this.tenantRepo = tenantRepo;
        this.tenantService = tenantService;
        this.investorService = investorService;
    }







}
