package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Dto.MaintenanceDTO;
import com.example.realestatemanagment.Dto.PropertyDTO;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Models.Maintenance;
import com.example.realestatemanagment.Repository.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceService {

    private final MaintenanceRepository repo;

    public MaintenanceService(MaintenanceRepository repo) {
        this.repo = repo;
    }

    public List<MaintenanceDTO> getAllMaintenance(){
        List<MaintenanceDTO> maintenanceList = new ArrayList<>();
        List<Maintenance> list = repo.findAll();

        for(Maintenance maintenance : list){
            maintenanceList.add(transferToDTO(maintenance));
        }
        return maintenanceList;
    }
    public MaintenanceDTO getMaintenanceById(Long id){
        Optional<Maintenance> optionalMaintenance = repo.findById(id);
        if(optionalMaintenance.isPresent()){
            Maintenance maintenance = optionalMaintenance.get();

            return transferToDTO(maintenance);
        }else{
            throw new RecordNotFoundException("maintenance with "+id+ "not Found");
        }
    }

    public MaintenanceDTO addMaintenance(MaintenanceDTO maintenanceDTO){
        Maintenance maintenance = transferToMaintenance(maintenanceDTO);
        repo.save(maintenance);

        return transferToDTO(maintenance);
    }

    public void deleteMaintenance(Long id){
        if(repo.findById(id).isPresent()){
            repo.deleteById(id);
        }else {
            throw new RecordNotFoundException("maintenance with "+id+ "not Found");
        }

    }

    public MaintenanceDTO UpdateMaintenance(Long id, MaintenanceDTO maintenanceDTO){
        if(repo.findById(id).isPresent()){
            Maintenance maintenance = repo.findById(id).get();
            Maintenance maintenance1 = transferToMaintenance(maintenanceDTO);
            maintenance1.setId(maintenance.getId());
            repo.save(maintenance1);

            return transferToDTO(maintenance1);
        }else{
            throw new RecordNotFoundException("Maintenance");
        }
    }



    public static MaintenanceDTO transferToDTO (Maintenance maintenance){
        var dto = new MaintenanceDTO();
        dto.setId(maintenance.getId());
        dto.setMaintenanceDate(maintenance.getMaintenanceDate());
        dto.setTypeOfMaintenance(maintenance.getTypeOfMaintenance());

        return dto;

    }

    public Maintenance transferToMaintenance(MaintenanceDTO dto){
         var maintenance = new Maintenance();

         maintenance.setMaintenanceDate(dto.getMaintenanceDate());
         maintenance.setTypeOfMaintenance(dto.getTypeOfMaintenance());

         return maintenance;
    }
}
