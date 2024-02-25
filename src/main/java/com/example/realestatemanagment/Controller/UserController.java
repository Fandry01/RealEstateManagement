package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Dto.InvestorDTO;
import com.example.realestatemanagment.Dto.TenantDTO;
import com.example.realestatemanagment.Dto.UserDTO;
import com.example.realestatemanagment.Exceptions.BadRequestException;
import com.example.realestatemanagment.Service.InvestorService;
import com.example.realestatemanagment.Service.TenantService;
import com.example.realestatemanagment.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value="users")
public class UserController {
    private final UserService userService;

    private final TenantService tenantService;
    private final InvestorService investorService;

    public UserController(UserService userService, TenantService tenantService, InvestorService investorService) {
        this.userService = userService;
        this.tenantService = tenantService;
        this.investorService = investorService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> userDTOList = userService.getUsers();
        return ResponseEntity.ok().body(userDTOList);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDTO> creatUser(@RequestBody UserDTO userDTO){
        String newUsername = userService.createUser(userDTO);

        userService.addAuthority(newUsername,"ROLE_USER");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }
    @PostMapping("/tenants")
    public ResponseEntity<TenantDTO> createTenant(@RequestBody TenantDTO tenantDTO){
        String  newUsername = tenantService.createTenant(tenantDTO);
        tenantService.addAuthority(newUsername,"ROLE_USER");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username").buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/investors")
    public ResponseEntity<InvestorDTO> createInvestors(@RequestBody InvestorDTO dto){

        String newUsername = investorService.createInvestor(dto);
        investorService.addAuthority(newUsername,"ROLE_ADMIN");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(newUsername).toUri();

        return  ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("username") String username, @RequestBody UserDTO dto) {

        userService.updateUser(username, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }


    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }
}
