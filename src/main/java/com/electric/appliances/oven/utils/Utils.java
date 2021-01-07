package com.electric.appliances.oven.utils;

import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.models.OvenDto;
import com.electric.appliances.oven.models.Program;

public class Utils {


    public static Oven mapOvenDtoToOven(OvenDto ovenDto){
        final Oven oven = new Oven();

        final Program program = new Program();

        oven.setName(ovenDto.getName());
        oven.setVersion(ovenDto.getVersion());
        oven.setModel(ovenDto.getModel());
        program.setTemperature(0L);
        program.setOvenState(OvenState.STOPPED);
        oven.setProgram(program);
        return oven;
    }
}
