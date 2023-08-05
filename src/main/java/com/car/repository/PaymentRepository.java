package com.car.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.car.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, String>{


	Optional<Payment> findTopByOrderByIdDesc();

	Optional<List<Payment>> findAllByBuyerId(String string);
	Optional<List<Payment>> findAllBySellerId(String string);

	Optional<List<Payment>> findAllByCarIdIn(List<String> soldCarIds);

	Optional<Payment> findByCarId(String carId);

	Optional<List<Payment>> findAllBySellerIdAndStatus(String uid, String string);

	Optional<List<Payment>> findAllByStatus(String string);

	Optional<List<Payment>> findAllByBuyerIdAndStatusIs(String uid, String string);
	Optional<List<Payment>> findAllBySellerIdAndStatusIs(String uid, String string);

	Optional<List<Payment>> findAllByBuyerIdAndStatusEquals(String uid, String string);
	Optional<List<Payment>> findAllBySellerIdAndStatusEquals(String uid, String string);


	

}
