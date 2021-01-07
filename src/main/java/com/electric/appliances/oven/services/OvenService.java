package com.electric.appliances.oven.services;

import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.models.OvenDto;
import com.electric.appliances.oven.models.Program;
import com.electric.appliances.oven.utils.OvenState;

import java.util.List;
import java.util.Optional;

public interface OvenService {

    public List<Oven> getOvens();

    public Oven onBoard(OvenDto ovenDto);

    public Oven getOvenById(long id);

    public Oven setOvenProgram(long id , Program program);
}
