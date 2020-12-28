package com.electric.appliances.oven.repository;

import com.electric.appliances.oven.models.Program;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramRepository extends CrudRepository<Program, Long> {

    Optional<List<Program>> findProgramsByOvenId(long id);


    Optional<Program> findProgramByIdAndOvenId(long id , long ovenId);

}
