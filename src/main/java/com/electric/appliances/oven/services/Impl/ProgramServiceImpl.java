package com.electric.appliances.oven.services.Impl;

import com.electric.appliances.oven.exceptions.exception.ProgramNotFoundException;
import com.electric.appliances.oven.models.Program;
import com.electric.appliances.oven.repository.ProgramRepository;
import com.electric.appliances.oven.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;
    @Autowired
    public ProgramServiceImpl(ProgramRepository programRepository){
        this.programRepository =programRepository;

    }

    @Override
    public List<Program> getAllPrograms(long id){
      Optional<List<Program>> programs=  programRepository.findProgramsByOvenId(id) ;
      if(programs.isPresent())
          return programs.get();
      throw new ProgramNotFoundException("Programs not found with provided ovenId");

    }

    @Override
    public Optional<Program> getProgram(long id , long ovenId){
        return programRepository.findProgramByIdAndOvenId( id , ovenId);
    }

}
