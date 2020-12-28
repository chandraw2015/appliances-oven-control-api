package com.electric.appliances.oven.repository;

import com.electric.appliances.oven.OvenControlApplication;
import com.electric.appliances.oven.models.Program;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {OvenControlApplication.class
})
public class ProgramRepositoryTest {


    @Autowired
    ProgramRepository programRepository;

    @Test
    public void test_find_programsByOvenID(){

        Optional<List<Program>> programList = programRepository.findProgramsByOvenId(1);

        Assertions.assertNotNull(programList);
    }

    @Test
    public void  test_find_programByProgramIdAndOvenId(){
         Optional<Program> program = programRepository.findProgramByIdAndOvenId(2, 1);

         Assertions.assertNotNull(program.get());

    }
}
