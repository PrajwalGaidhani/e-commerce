package com.prajwal.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecom.models.User;

public interface UserRepository extends JpaRepository<User,Integer>{

}
