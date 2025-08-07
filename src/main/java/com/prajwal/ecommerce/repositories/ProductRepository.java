package com.prajwal.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecom.models.Product;

public interface ProductRepository  extends JpaRepository<Product,Integer>{

}

