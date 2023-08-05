package com.car.repository;


import com.car.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {


    Optional<Admin> findTopBy();

    Optional<Admin> findByUserNameAndPassword(String email, String password);
}
