package com.car.repository;

import com.car.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, String> {

    Optional<Car> findTopByOrderByIdDesc();

    Optional<Car> findByCarId(String carid);

    Optional<List<Car>> findBySellerId(String string);

    Optional<List<Car>> findAllBySellerId(String string);

    Optional<List<Car>> findAllByCategoryIdAndIsApprovedAndIsSold(String id, boolean approved, boolean sold);

    Optional<List<Car>> findAllByIsApprovedAndIsSold(boolean approved, boolean sold);

    Optional<List<Car>> findAllBySellerIdAndIsSold(String uid, Boolean value);


    Optional<List<Car>> findAllByFuelTypeAndIsApprovedAndIsSold(String fuelType, boolean approved, boolean sold);

    Optional<List<Car>> findAllByBasePriceGreaterThanEqualAndIsApprovedAndIsSold(double budgetGte, boolean approved, boolean sold);

    Optional<List<Car>> findAllByBasePriceBetweenAndIsApprovedAndIsSold(double budgetGte, double budgetLte, boolean approved, boolean sold);

    Optional<List<Car>> findAllByCategoryIdAndFuelTypeAndIsApprovedAndIsSold(String catId, String fuelType, boolean approved, boolean sold);

    Optional<List<Car>> findAllByCategoryIdAndBasePriceGreaterThanEqualAndIsApprovedAndIsSold(String catId, double budgetGte, boolean approved, boolean sold);

    Optional<List<Car>> findAllByCategoryIdAndBasePriceBetweenAndIsApprovedAndIsSold(String catId, double budgetGte, double budgetLte, boolean approved, boolean sold);

    Optional<List<Car>> findAllByCategoryIdAndFuelTypeAndBasePriceGreaterThanEqualAndIsApprovedAndIsSold(String catId, String fuelType, double budgetGte, boolean approved, boolean sold);

    Optional<List<Car>> findAllByCategoryIdAndFuelTypeAndAndBasePriceBetweenAndIsApprovedAndIsSold(String catId, String fuelType, double budgetGte, double budgetLte, boolean approved, boolean sold);

    Optional<List<Car>> findAllByFuelTypeAndBasePriceGreaterThanEqualAndIsApprovedAndIsSold(String fuelType, double budgetGte, boolean approved, boolean sold);

    Optional<List<Car>> findAllByFuelTypeAndBasePriceBetweenAndIsApprovedAndIsSold(String fuelType, double budgetGte, double budgetLte, boolean approved, boolean sold);
}
