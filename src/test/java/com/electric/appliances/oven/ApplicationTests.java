package com.electric.appliances.oven;

import com.electric.appliances.oven.controller.OvenController;
import com.electric.appliances.oven.repository.OvenRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {OvenControlApplication.class})
class ApplicationTests {


    @Autowired
    OvenController ovenController;



}
