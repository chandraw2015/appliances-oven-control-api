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



}
