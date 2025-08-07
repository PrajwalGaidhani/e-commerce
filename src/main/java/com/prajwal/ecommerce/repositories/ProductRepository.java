package com.prajwal.ecommerce.repositories;

import com.prajwal.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository  extends JpaRepository<Product,Integer>{

}

