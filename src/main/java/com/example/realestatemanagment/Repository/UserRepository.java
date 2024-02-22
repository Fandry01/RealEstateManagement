package com.example.realestatemanagment.Repository;


import com.example.realestatemanagment.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
