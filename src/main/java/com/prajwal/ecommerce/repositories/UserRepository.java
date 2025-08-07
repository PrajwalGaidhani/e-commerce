package com.prajwal.ecommerce.repositories;

import com.prajwal.ecommerce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer>{

}
