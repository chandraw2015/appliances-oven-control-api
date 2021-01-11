package com.electric.appliances.oven.repository;


import com.electric.appliances.oven.models.Oven;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

/**
 * Oven Repository to communicate with database
 * @author Chandra Rawat
 */

@Repository
public interface OvenRepository extends CrudRepository<Oven, Long> {


    /**
     * Return an oven using combination of version and model name.
     * @param version version of a particular model of oven
     * @param Model is model name of an oven.
     * @return Oven
     */
    Optional<Oven> findOvenByVersionAndModel(String version , String Model);



}
