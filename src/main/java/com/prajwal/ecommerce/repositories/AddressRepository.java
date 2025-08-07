package com.prajwal.ecommerce.repositories;

import com.prajwal.ecommerce.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address,Integer> {

}