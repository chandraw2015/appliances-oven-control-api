package com.electric.appliances.oven.services;

import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.models.OvenProcess;
import com.electric.appliances.oven.models.Program;

import java.util.Optional;

public interface OvenProcessService {

    public OvenProcess startOvenProcess(Oven oven , Program program);

}
