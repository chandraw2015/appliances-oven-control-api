package com.electric.appliances.oven.repository;

import com.electric.appliances.oven.OvenControlApplication;
import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.models.OvenProcess;
import com.electric.appliances.oven.models.Program;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {OvenControlApplication.class
})
public class OvenProcessRepositoryTest {

    @Autowired
    OvenProcessRepository ovenProcessRepository;

    @Autowired
    OvenRepository ovenRepository;

    @Autowired
    ProgramRepository programRepository;

    @Test
    public void test_SaveOvenProcess(){

        Optional<Oven> oven = ovenRepository.findOvenById(1);
        Optional<Program> program = programRepository.findProgramByIdAndOvenId(1 , 1);

        OvenProcess ovenProcess = new OvenProcess();
        ovenProcess.setProcessStatus("ACTIVE");
        ovenProcess.setOven(oven.get());
        ovenProcess.setProgram(program.get());

        Assertions.assertNotNull(ovenProcessRepository.save(ovenProcess));

    }
}
