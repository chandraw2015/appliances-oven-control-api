package com.electric.appliances.oven.services;

import com.electric.appliances.oven.models.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramService {

    List<Program> getAllPrograms(long id);
    Optional<Program> getProgram(long id , long ovenId);
}
