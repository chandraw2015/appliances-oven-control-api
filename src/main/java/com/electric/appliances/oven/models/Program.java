package com.electric.appliances.oven.models;

import com.electric.appliances.oven.utils.OvenState;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@ApiModel(description = "Details of an oven program")
@Embeddable
public class Program {


    @NotNull
    private OvenState ovenState = OvenState.STOPPED;

    @Min(value=0, message = "Temperature must be greater than 0.")
    @Max(value=800, message = "Temperature must be less than 800.")
    @NotNull
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
