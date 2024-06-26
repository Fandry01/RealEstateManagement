package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Dto.UserDTO;
import com.example.realestatemanagment.Exceptions.UsernameNotFoundException;
import com.example.realestatemanagment.Models.AuthorityRoles;
import com.example.realestatemanagment.Models.Investor;
import com.example.realestatemanagment.Models.Tenant;
import com.example.realestatemanagment.Models.User;
import com.example.realestatemanagment.Repository.InvestorRepository;
import com.example.realestatemanagment.Repository.TenantRepository;
import com.example.realestatemanagment.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final InvestorRepository investorRepo;
    private final TenantRepository tenantRepo;

    public UserService(UserRepository userRepo, InvestorRepository investorRepo, TenantRepository tenantRepo) {
        this.userRepo = userRepo;
        this.investorRepo = investorRepo;
        this.tenantRepo = tenantRepo;
    }

    public static UserDTO fromUser(User user) {
        var dto = new UserDTO();

        dto.setUsername(user.getUsername());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPassword(user.getPassword());
        dto.setDob(user.getDob());
        dto.setAuthorities(user.getAuthorities());

        return dto;
    }

    public List<UserDTO> getUsers() {
        List<UserDTO> userList = new ArrayList<>();
        List<User> list = userRepo.findAll();

        for (User user : list) {
            userList.add(fromUser(user));
        }

        return userList;
    }

    public UserDTO getUser(String username) {
        UserDTO dto = new UserDTO();
        Optional<Tenant> tenant = tenantRepo.findById(username);
        Optional<Investor> investor = investorRepo.findById(username);
        if (tenant.isPresent()) {
            dto = fromUser(tenant.get());
        } else if (investor.isPresent()) {
            dto = fromUser(investor.get());
        } else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public String createUser(UserDTO userDto) {
        User newUser = userRepo.save(toUser(userDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        userRepo.deleteById(username);
    }

    public void updateUser(String username, UserDTO newUser) {
        if (!userRepo.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepo.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepo.save(user);
    }

    public Set<AuthorityRoles> getAuthorities(String username) {
        if (userRepo.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepo.findById(username).get();
        UserDTO userDTO = fromUser(user);
        return userDTO.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (userRepo.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepo.findById(username).get();
        user.addAuthorityRoles(new AuthorityRoles(username, authority));
        userRepo.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (userRepo.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepo.findById(username).get();
        AuthorityRoles authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthorityRoles().equalsIgnoreCase(authority)).findAny().get();
        user.deleteAuthorityRoles(authorityToRemove);
        userRepo.save(user);
    }

    public User toUser(UserDTO userDTO) {
        var user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDob(userDTO.getDob());

        return user;
    }
}
