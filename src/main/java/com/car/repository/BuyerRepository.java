package com.car.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.car.model.Buyer;
import com.car.model.Car;

@Repository
public interface BuyerRepository extends CrudRepository<Buyer, String>{

	Optional<Buyer> findTopByOrderByIdDesc();

	Optional<Buyer> findByEmailIdAndPassword(String emailId, String password);

	Optional<Buyer> findByBuyerId(String string);

	Optional<Buyer> findByEmailId(String emailId);


}
