package com.example.realestatemanagment.Repository;

import com.example.realestatemanagment.Models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestorRepository extends JpaRepository<Investor,Long> {
}
