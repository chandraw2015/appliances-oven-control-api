package com.electric.appliances.oven.repository;

import com.electric.appliances.oven.models.OvenProcess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OvenProcessRepository extends CrudRepository<OvenProcess, Long> {

    OvenProcess save(OvenProcess ovenProcess);
}
