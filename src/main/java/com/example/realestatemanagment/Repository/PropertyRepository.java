package com.example.realestatemanagment.Repository;

import com.example.realestatemanagment.Models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Long> {
}
