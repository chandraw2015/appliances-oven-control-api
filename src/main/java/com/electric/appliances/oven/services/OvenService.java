package com.electric.appliances.oven.services;

import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.models.OvenDto;
import com.electric.appliances.oven.models.Program;

import java.util.List;

/**
 * A contract which needs to be followed for controlling a oven functions
 *
 * @author Chandra Rawat
 */

public interface OvenService {
    /**
     * To fetch the List of all Onboarded Ovens
     * @return List<Oven>
     */
    List<Oven> getOvens();

    /**
     * To onboard a new oven to system
     * @param ovenDto basic details of oven name, model and version
     * @return Oven
     */
    Oven onBoard(OvenDto ovenDto);

    /**
     * To fetch a oven by oven Id.
     * @param id  is identifier for an onboarded oven.
     * @return Oven
     */
    Oven getOvenById(long id);

    /**
     *
     * @param id   is identifier for an onboarded oven.
     * @param program has configuration for programming an oven
     * @return Oven
     */
    Oven setOvenProgram(long id, Program program);
}
