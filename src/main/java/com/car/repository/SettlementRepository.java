package com.car.repository;

import com.car.model.Settlement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SettlementRepository extends CrudRepository<Settlement, String> {


    Optional<List<Settlement>> findAllBySellerId(String uid);
}
