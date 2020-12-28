package com.electric.appliances.oven.services.Impl;

import com.electric.appliances.oven.exceptions.exception.OvenNotFoundException;
import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.repository.OvenRepository;
import com.electric.appliances.oven.services.OvenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OvenServiceImpl implements OvenService {



    private final OvenRepository ovenRepository;
    @Autowired
    public OvenServiceImpl(OvenRepository ovenRepository){
        this.ovenRepository =ovenRepository;
    }

    @Override
    public Oven getOven(){

       return ovenRepository.findAll().get(0);
    }

    @Override
    public Oven updateOvenStatus(Oven oven){
      return ovenRepository.save(oven);
    }

    @Override
    public Oven getOvenById(long id){
       Optional<Oven> oven = ovenRepository.findOvenById(id);
       if(oven.isPresent()){
           return oven.get();
       }
       throw new OvenNotFoundException("Oven with provided OvenId is not Found.");
    }
}
