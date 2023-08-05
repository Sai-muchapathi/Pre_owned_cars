package com.car.repository;


import com.car.model.Inquiry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InquiryRepository extends CrudRepository<Inquiry, String> {
    Optional<List<Inquiry>> findAllBySellerIdOrderByIdDesc(String string);

    Optional<List<Inquiry>> findAllByBuyerIdOrderByIdDesc(String string);
}
