package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Dto.InvestorDTO;
import com.example.realestatemanagment.Exceptions.BadRequestException;
import com.example.realestatemanagment.Service.InvestorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/investors")
public class InvestorController {
    private final InvestorService investorService;

    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }
    @GetMapping
    public ResponseEntity<List<InvestorDTO>> getInvestors(){
        List<InvestorDTO> investorDTOS = investorService.getALlInvestors();

        return ResponseEntity.ok().body(investorDTOS);
    }

    @GetMapping(value ="/{username}")
    public ResponseEntity<InvestorDTO> getInvestor(@PathVariable("username") String username){

        InvestorDTO optionalInvestor = investorService.getInvestorById(username);

        return ResponseEntity.ok().body(optionalInvestor);
    }

    @PostMapping("/investors")
    public ResponseEntity<InvestorDTO> createInvestors(@RequestBody InvestorDTO dto){

        String newUsername = investorService.createInvestor(dto);
        investorService.addAuthority(newUsername,"ROLE_INVESTOR");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(newUsername).toUri();

        return  ResponseEntity.created(location).build();
    }


    @PutMapping(value="/{username}")
    public ResponseEntity<InvestorDTO> updateInvestor(@PathVariable("username") String username, @RequestBody InvestorDTO dto){
        investorService.updateInvestor(username,dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/{username}")
    public ResponseEntity<Object> deleteInvestor(@PathVariable("username") String username){
        investorService.deleteInvestor(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{username}/roles")
    public ResponseEntity<Object> addInvestorAuthorityRoles(@PathVariable("username") String username,@RequestBody Map<String, Object> fields){
        try{
            String authorityName = (String) fields.get("authority");
            investorService.addAuthority(username,authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (Exception ex){
            throw new BadRequestException();
        }

    }
    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getInvestorAuthorityRoles(@PathVariable("username") String username){
        return ResponseEntity.ok().body(investorService.getAuthorities(username));
    }

    @DeleteMapping(value = "/{username}/roles/{authority}")
    public ResponseEntity<Object> deleteInvestorAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority)
    {
        investorService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }
}
