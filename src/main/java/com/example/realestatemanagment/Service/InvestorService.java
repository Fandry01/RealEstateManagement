package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Dto.InvestorDTO;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Exceptions.UsernameNotFoundException;
import com.example.realestatemanagment.Models.AuthorityRoles;
import com.example.realestatemanagment.Models.Investor;
import com.example.realestatemanagment.Repository.InvestorRepository;
import com.example.realestatemanagment.Repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InvestorService {
    private final InvestorRepository investorRepo;
    private final PropertyRepository propertyRepo;

    public InvestorService(InvestorRepository investorRepo, PropertyRepository propertyRepo) {
        this.investorRepo = investorRepo;
        this.propertyRepo = propertyRepo;
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

    public void deleteInvestor(String username){
        investorRepo.deleteById(username);
    }


    public Set<AuthorityRoles> getAuthorities(String username){
        if(!investorRepo.existsById(username)) throw new UsernameNotFoundException(username);
        Investor investor = investorRepo.findById(username).get();
        InvestorDTO investorDTO = transferToDTO(investor);
        return investorDTO.getAuthorities();
    }

    public void addAuthority(String username, String authority){
        if(!investorRepo.existsById(username)) throw new UsernameNotFoundException(username);
        Investor investor = investorRepo.findById(username).get();
        investor.addAuthority(new AuthorityRoles(username, authority));

        investorRepo.save(investor);
    }

    public void removeAuthority(String username, String authority){
        if(!investorRepo.existsById(username)) throw new UsernameNotFoundException(username);
        Investor investor = investorRepo.findById(username).get();
        AuthorityRoles authorityRemove = investor.getAuthorities().stream().filter(a -> a.getAuthorityRoles().equalsIgnoreCase(authority)).findAny().get();

        investor.removeAuthority(authorityRemove);
    }




    public static InvestorDTO transferToDTO(Investor investor){
        var dto = new InvestorDTO();

        dto.setUsername(investor.getUsername());
        dto.setPassword(investor.getPassword());
        dto.setAddress(investor.getAddress());
        dto.setDob(investor.getDob());
        dto.setFirstName(investor.getFirstname());
        dto.setLastname(investor.getLastname());

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

    public void assignPropertyToInvestor(String username, Long propertyId){
        var optionalProperty = propertyRepo.findById(propertyId);
        var optionalInvestor = investorRepo.findById(username);

        if(optionalProperty.isPresent() && optionalInvestor.isPresent()){
            var property = optionalProperty.get();
            var investor = optionalInvestor.get();

            investor.getProperties().add(property);
            property.setInvestor(investor);
            investorRepo.save(investor);
        } else{
            throw new RecordNotFoundException("Investor or Property not found");
        }
    }

}
