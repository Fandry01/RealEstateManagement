package com.example.realestatemanagment.Repository;

import com.example.realestatemanagment.Models.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant,String> {
}
