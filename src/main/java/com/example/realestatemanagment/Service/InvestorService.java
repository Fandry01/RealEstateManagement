package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Dto.InvestorDTO;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Exceptions.UsernameNotFoundException;
import com.example.realestatemanagment.Models.AuthorityRoles;
import com.example.realestatemanagment.Models.Investor;
import com.example.realestatemanagment.Repository.InvestorRepository;
import com.example.realestatemanagment.Repository.PropertyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class InvestorService {
    private final InvestorRepository investorRepo;
    private final PropertyRepository propertyRepo;
    private final PasswordEncoder passwordEncoder;

    public InvestorService(InvestorRepository investorRepo, PropertyRepository propertyRepo, PasswordEncoder passwordEncoder) {
        this.investorRepo = investorRepo;
        this.propertyRepo = propertyRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<InvestorDTO> getALlInvestors(){
        List<InvestorDTO> investorList = new ArrayList<>();
        List<Investor> invList = investorRepo.findAll();
        for(Investor investor: invList){
            investorList.add(transferToDTO(investor));
        }
        return investorList;
    }

    public InvestorDTO getInvestorById(String username){
        Optional<Investor> optionalInvestor = investorRepo.findById(username);
        if(optionalInvestor.isPresent()){
            Investor investor = optionalInvestor.get();

            return transferToDTO(investor);
        }else{
           throw new RecordNotFoundException("Investor not found");
        }

    }

    public String createInvestor(InvestorDTO investorDTO){
        Investor newInvestor = investorRepo.save(transferToInvestor(investorDTO));
        return newInvestor.getUsername();
    }

    public void updateInvestor(String username, InvestorDTO investorDTO){
        if(!investorRepo.existsById(username)) throw new RecordNotFoundException("investor not found");
        Investor investor = investorRepo.findById(username).get();
        investor.setPassword(investorDTO.getPassword());
        investorRepo.save(investor);
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
        investor.addAuthorityRoles(new AuthorityRoles(username, authority));

        investorRepo.save(investor);
    }

    public void removeAuthority(String username, String authority){
        if(!investorRepo.existsById(username)) throw new UsernameNotFoundException(username);
        Investor investor = investorRepo.findById(username).get();
        AuthorityRoles authorityRemove = investor.getAuthorities().stream().filter(a -> a.getAuthorityRoles().equalsIgnoreCase(authority)).findAny().get();

        investor.deleteAuthorityRoles(authorityRemove);
    }




    public static InvestorDTO transferToDTO(Investor investor){
        var dto = new InvestorDTO();

        dto.setUsername(investor.getUsername());
        dto.setPassword(investor.getPassword());
        dto.setAddress(investor.getAddress());
        dto.setDob(investor.getDob());
        dto.setFirstName(investor.getFirstName());
        dto.setLastname(investor.getLastName());
        dto.setAuthorities(investor.getAuthorities());

        return dto;
    }

    public Investor transferToInvestor(InvestorDTO dto){
        var investor = new Investor();

        investor.setUsername(dto.getUsername());
        investor.setPassword(passwordEncoder.encode(dto.getPassword()));
        investor.setFirstName(dto.getFirstName());
        investor.setLastName(dto.getLastname());
        investor.setAddress(dto.getAddress());
        investor.setDob(dto.getDob());

        return investor;
    }



}
