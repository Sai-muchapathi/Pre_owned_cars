package com.car.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.car.model.Car;
import com.car.model.Seller;

@Repository
public interface SellerRepository extends CrudRepository<Seller, String>{

	Optional<Seller> findTopByOrderByIdDesc();

	Optional<Seller> findByEmailIdAndPassword(String emailId, String password);

	Optional<Seller> findBySellerId(String string);

	Optional<Seller> findByEmailId(String emailId);


}
