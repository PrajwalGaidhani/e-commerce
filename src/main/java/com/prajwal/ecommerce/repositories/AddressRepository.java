package com.prajwal.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecom.models.Address;

public interface AddressRepository extends JpaRepository<Address,Integer>{

}