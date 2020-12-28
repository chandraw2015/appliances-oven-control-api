package com.electric.appliances.oven.repository;

import com.electric.appliances.oven.OvenControlApplication;
import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.utils.OvenState;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.*;
import  org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {OvenControlApplication.class
})
public class OvenRepositoryTest {

    @Autowired
    private OvenRepository ovenRepository;


    @Test
    public void getOven_whenFindAll(){

        List<Oven> oven =ovenRepository.findAll();

        Assertions.assertNotNull(oven);


    }

    @Test
    public void test_saveOven(){
        Oven oven = new Oven();
        oven.setOvenState(OvenState.STARTED);
        oven.setId(1);
        oven.setName("LG");
        oven.setModel("LG-2020");
        oven.setVersion("1.0.2");
        Oven savedOven= ovenRepository.save(oven);

        Assertions.assertNotNull(savedOven);
        Assertions.assertEquals("LG" , savedOven.getName());
        Assertions.assertEquals("STARTED" , savedOven.getOvenState().toString());
        Assertions.assertEquals("1.0.2", savedOven.getVersion());
        Assertions.assertNotNull(savedOven.getId());
    }
     @Test
     public void test_findOvenByID(){

        long id = 1;
        Optional<Oven> oven = ovenRepository.findOvenById(id);
        Assertions.assertNotNull(oven.get());
     }


}
