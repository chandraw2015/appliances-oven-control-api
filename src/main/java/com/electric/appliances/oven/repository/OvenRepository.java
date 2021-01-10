package com.electric.appliances.oven.repository;


import com.electric.appliances.oven.models.Oven;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface OvenRepository extends CrudRepository<Oven, Long> {



    Optional<Oven> findOvenByVersionAndAndModel(String version , String Model);



}
