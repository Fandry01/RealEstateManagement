package com.example.realestatemanagment.Service;


import com.example.realestatemanagment.Dto.InvestorDTO;
import com.example.realestatemanagment.Models.AuthorityRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomDetailsService  implements UserDetailsService {
    private final InvestorService investorService;

    public CustomDetailsService(InvestorService investorService) {
        this.investorService = investorService;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        InvestorDTO investorDTO = investorService.getInvestorById(username);

        String password = investorDTO.getPassword();

        Set<AuthorityRoles> authorities = investorDTO.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(AuthorityRoles authorityRoles: authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authorityRoles.getAuthorityRoles()));
        }
        return new org.springframework.security.core.userdetails.User(username,password,grantedAuthorities);
    }
}
