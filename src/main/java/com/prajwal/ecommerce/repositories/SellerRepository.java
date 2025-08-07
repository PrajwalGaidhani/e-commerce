package com.prajwal.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecom.models.Seller;

public interface SellerRepository extends JpaRepository<Seller,Integer>{
}
