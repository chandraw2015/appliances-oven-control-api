package com.electric.appliances.oven.services.Impl;

import com.electric.appliances.oven.exceptions.InvalidOvenStateException;
import com.electric.appliances.oven.exceptions.OvenAlreadyExistException;
import com.electric.appliances.oven.exceptions.OvenNotFoundException;
import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.models.OvenDto;
import com.electric.appliances.oven.models.Program;
import com.electric.appliances.oven.repository.OvenRepository;
import com.electric.appliances.oven.services.OvenService;
import com.electric.appliances.oven.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of functions to control an connected oven. All business logic is handled here.
 * @author Chandra Rawat
 */
@Service
public class OvenServiceImpl implements OvenService {



    private final OvenRepository ovenRepository;
    @Autowired
    public OvenServiceImpl(OvenRepository ovenRepository){
        this.ovenRepository =ovenRepository;
    }

    @Override
    public List<Oven> getOvens(){

       return (List<Oven>)this.ovenRepository.findAll();
    }

    @Override
    public Oven onBoard(OvenDto ovenDto){

        Optional<Oven> oven = this.ovenRepository.findOvenByVersionAndModel(ovenDto.getVersion() , ovenDto.getModel());
         oven.ifPresent((o)->{
             throw new OvenAlreadyExistException("Oven with provided Model and Version Already Exists.");
         });

         return this.ovenRepository.save(Utils.mapOvenDtoToOven(ovenDto));

    }

    @Override
    public Oven getOvenById(long id){
       return this.ovenRepository.findById(id).orElseThrow(()-> new OvenNotFoundException("The oven with provided id does not exist"));

    }



    @Override
    public Oven setOvenProgram(long id , Program program){
       Oven oven = this.getOvenById(id);

       switch(program.getOvenState()) {
           case STOPPED:
               oven.stop();
               break;
           case IDLE:
               oven.idle(program.getTemperature());
               break;
           case COOKING:
               oven.cook(program.getTemperature());
               break;
           case STARTED:
               oven.start(program.getTemperature());
               break;
           default:
               throw new InvalidOvenStateException("Invalid Oven state ,Please provide a valid state.");
       }
        this.ovenRepository.save(oven);
       return oven;
    }
}
