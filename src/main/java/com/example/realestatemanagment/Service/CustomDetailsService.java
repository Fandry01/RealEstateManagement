package com.example.realestatemanagment.Service;


import com.example.realestatemanagment.Dto.InvestorDTO;
import com.example.realestatemanagment.Dto.UserDTO;
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
    private final UserService userService;

    public CustomDetailsService(InvestorService investorService, UserService userService) {

        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        UserDTO userDTO = userService.getUser(username);

        String password = userDTO.getPassword();

        Set<AuthorityRoles> authorities = userDTO.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(AuthorityRoles authorityRoles: authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authorityRoles.getAuthorityRoles()));
        }
        return new org.springframework.security.core.userdetails.User(username,password,grantedAuthorities);
    }
}
