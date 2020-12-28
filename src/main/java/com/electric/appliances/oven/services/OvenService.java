package com.electric.appliances.oven.services;

import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.utils.OvenState;

import java.util.Optional;

public interface OvenService {

    public Oven getOven();

    public Oven updateOvenStatus(Oven oven);

    public Oven getOvenById(long id);
}
