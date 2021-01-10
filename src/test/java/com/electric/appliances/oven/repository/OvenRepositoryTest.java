package com.electric.appliances.oven.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.models.Program;
import com.electric.appliances.oven.utils.OvenState;
import  org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OvenRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private OvenRepository ovenRepository;

    @BeforeEach
    public void setUp(){


    }


    @Test
    public void test_save_oven(){

        Assertions.assertNotNull(ovenRepository.save(buildOven("TN", "TN-101")));
    }

    @Test
    public void test_findById(){
        Oven expectedOven  = buildOven("TN","TN-101");
        expectedOven = testEntityManager.persist(expectedOven);
        testEntityManager.flush();

        Assertions.assertEquals(expectedOven ,ovenRepository.findById(expectedOven.getId()).get());


    }

    @Test
    public void test_findOvenByWrongId(){

      Assertions.assertFalse(ovenRepository.findById(1L).isPresent());

    }

    @Test
    public void find_all_oven(){
      testEntityManager.persist(buildOven("TN", "TN-101"));
      testEntityManager.persist(buildOven("TN", "TN-201"));
      testEntityManager.flush();

      assertThat(ovenRepository.findAll()).isNotEmpty();

    }

    @Test
    public void test_getOven_byVersionAndModel(){

        testEntityManager.persist(buildOven("TN", "TN-101"));
        testEntityManager.persist(buildOven("TN", "TN-201"));
        testEntityManager.flush();
        Optional<Oven> oven = ovenRepository.findOvenByVersionAndAndModel("TN-201", "TN");
        assertThat(oven.get().getVersion()).isEqualTo("TN-201");
        assertThat(oven.get().getModel()).isEqualTo("TN");
    }
    @Test
    public void test_findByWrongVersionAndModel(){

        testEntityManager.persist(buildOven("TN", "TN-101"));
        testEntityManager.persist(buildOven("TN", "TN-201"));
        testEntityManager.flush();
        Optional<Oven> oven = ovenRepository.findOvenByVersionAndAndModel("TN-301", "TN");
        assertThat(oven.isPresent()).isEqualTo(false);
    }

    public static  Oven buildOven(String model, String version){
        Oven oven  = new Oven();
        oven.setName("test-oven");
        oven.setModel(model);
        oven.setVersion(version);
        Program program = new Program();
        program.setOvenState(OvenState.STOPPED);
        program.setTemperature(0L);
        oven.setProgram(program);

        return oven;
    }

}
