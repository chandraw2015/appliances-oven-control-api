package com.electric.appliances.oven.repository;


import com.electric.appliances.oven.models.Oven;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OvenRepository extends CrudRepository<Oven, Long> {

    List<Oven> findAll();
    Oven save(Oven oven);
    Optional<Oven> findOvenById(long id);

}
