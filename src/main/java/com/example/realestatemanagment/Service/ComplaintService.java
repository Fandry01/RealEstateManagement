package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Dto.ComplaintDTO;
import com.example.realestatemanagment.Dto.ComplaintShortDTO;
import com.example.realestatemanagment.Exceptions.RecordNotFoundException;
import com.example.realestatemanagment.Models.Complaint;
import com.example.realestatemanagment.Repository.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {
private final ComplaintRepository complaintRepo;

    public ComplaintService(ComplaintRepository complaintRepo) {
        this.complaintRepo = complaintRepo;
    }

    public List<ComplaintDTO> getAllComplaints(){
        List<ComplaintDTO> complaintList = new ArrayList<>();
        List<Complaint> complaints =  complaintRepo.findAll();

        for(Complaint complaint: complaints){
            complaintList.add(transferToDTO(complaint));
        }

        return complaintList;
    }

    public ComplaintDTO getComplaintsById(Long id){
       Optional<Complaint> optionalComplaint = complaintRepo.findById(id);
       if(optionalComplaint.isPresent()){
           Complaint complaint = optionalComplaint.get();

           return transferToDTO(complaint);
       }else{
           throw new RecordNotFoundException("Complaint not found");
       }
    }

    public ComplaintDTO addComplaint(ComplaintDTO complaintDTO){
        Complaint complaint = transferToComplaint(complaintDTO);
        complaintRepo.save(complaint);

        return transferToDTO(complaint);
    }

    public void deleteComplaint(Long id){
        complaintRepo.deleteById(id);

    }

    public ComplaintDTO updateComplaint (Long id,ComplaintDTO complaintDTO){
        if(complaintRepo.findById(id).isPresent()){
            Complaint complaint = complaintRepo.findById(id).get();
            Complaint complaint1 = transferToComplaint(complaintDTO);
            complaint1.setId(complaint.getId());
            complaintRepo.save(complaint1);

            return  transferToDTO(complaint1);
        }else {
            throw new RecordNotFoundException("Complaint Not Found");
        }
    }


    public static ComplaintDTO transferToDTO(Complaint complaint){
        var dto = new ComplaintDTO();

        dto.setId(complaint.getId());

        dto.setComplaintMessage(complaint.getComplaintMessage());
        dto.setDateOfComplaint(complaint.getDateOfComplaint());

        return dto;
    }

    public ComplaintShortDTO transferToShortDTO(Complaint complaint){
        var dto = new ComplaintShortDTO();

        dto.setId(complaint.getId());
        dto.setComplaintMessage(complaint.getComplaintMessage());
        dto.setDateOfComplaint(complaint.getDateOfComplaint());

        return dto;
    }

    public static Complaint transferToComplaint(ComplaintDTO complaintDTO){
        var complaint = new Complaint();

        complaint.setComplaintMessage(complaintDTO.getComplaintMessage());
        complaint.setDateOfComplaint(complaintDTO.getDateOfComplaint());

        return complaint;
    }
}
