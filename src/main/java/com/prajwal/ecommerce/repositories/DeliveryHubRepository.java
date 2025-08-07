package com.prajwal.ecommerce.repositories;

import com.prajwal.ecommerce.models.DeliveryHub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DeliveryHubRepository extends JpaRepository<DeliveryHub,Integer>{
    Optional<DeliveryHub> findByAddress_ZipCode(String zipCode);
}
