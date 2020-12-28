package com.electric.appliances.oven.utils;

import com.electric.appliances.oven.models.Oven;

public class Utils {



    public static boolean isOvenStarted(Oven oven){

        return oven.getOvenState().equals(OvenState.STARTED);

    }

    public static String getOvenState(Oven oven){

        return oven.getOvenState().toString();
    }
}
