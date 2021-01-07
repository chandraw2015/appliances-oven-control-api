package com.electric.appliances.oven.models;

import com.electric.appliances.oven.utils.OvenState;

import javax.persistence.*;


@Embeddable
public class Program {

   private OvenState ovenState = OvenState.STOPPED;

    private Long temperature;

    public OvenState getOvenState() {
        return ovenState;
    }

    public void setOvenState(OvenState ovenState) {
        this.ovenState = ovenState;
    }

    public Long getTemperature() {
        return temperature;
    }

    public void setTemperature(Long temperature) {
        this.temperature = temperature;
    }




}
