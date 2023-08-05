package com.car.repository;

import com.car.model.Car;
import com.car.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String>{

	Optional<Category> findTopByOrderByIdDesc();

	Optional<Category> findByCatId(String catId);

}
