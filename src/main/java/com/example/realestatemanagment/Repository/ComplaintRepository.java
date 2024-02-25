package com.example.realestatemanagment.Repository;

import com.example.realestatemanagment.Models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
