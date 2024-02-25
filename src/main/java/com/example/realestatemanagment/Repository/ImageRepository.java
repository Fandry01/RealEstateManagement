package com.example.realestatemanagment.Repository;

import com.example.realestatemanagment.Models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageData,Long> {
}
