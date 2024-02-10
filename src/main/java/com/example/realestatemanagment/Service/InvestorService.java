package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Controller.InvestorController;
import com.example.realestatemanagment.Dto.InvestorDTO;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Models.Investor;
import com.example.realestatemanagment.Repository.InvestorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvestorService {
    private final InvestorRepository investorRepo;

    public InvestorService(InvestorRepository investorRepo) {
        this.investorRepo = investorRepo;
    }

    public List<InvestorDTO> getALlInvestors(){
        List<InvestorDTO> investorList = new ArrayList<>();
        List<Investor> invList = investorRepo.findAll();
        for(Investor investor: invList){
            investorList.add(transferToDTO(investor));
        }
        return investorList;
    }

    public InvestorDTO getInvestorById(Long id){
        Optional<Investor> optionalInvestor = investorRepo.findById(id);
        if(optionalInvestor.isPresent()){
            Investor investor = optionalInvestor.get();

            return transferToDTO(investor);
        }else{
           throw new RecordNotFoundException("geen Gebruiker gevonden");
        }

    }

    public void deleteInvestor(Long id){
        investorRepo.deleteById(id);
    }



    public static InvestorDTO transferToDTO(Investor investor){
        var dto = new InvestorDTO();

        dto.getAddress();
        dto.getPropertyDTO();
        dto.getDob();
        dto.getFirstName();
        dto.getLastname();

        return dto;
    }

    public Investor transferToInvestor(InvestorDTO dto){
        var investor = new Investor();

        investor.setFirstname(dto.getFirstName());
        investor.setLastname(dto.getLastname());
        investor.setAddress(dto.getAddress());
        investor.setDob(dto.getDob());

        return investor;
    }

    public void assignPropertyToInvestor(){

    }

}
