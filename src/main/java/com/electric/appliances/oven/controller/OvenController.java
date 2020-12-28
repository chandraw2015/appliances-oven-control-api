package com.electric.appliances.oven.controller;

import com.electric.appliances.oven.exceptions.exception.OvenNotStartedException;
import com.electric.appliances.oven.exceptions.exception.ProgramNotFoundException;
import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.models.OvenProcess;
import com.electric.appliances.oven.models.Program;
import com.electric.appliances.oven.models.wrapper.ProgramsWrapper;
import com.electric.appliances.oven.services.Impl.OvenProcessServiceImpl;
import com.electric.appliances.oven.services.Impl.OvenServiceImpl;
import com.electric.appliances.oven.services.Impl.ProgramServiceImpl;
import com.electric.appliances.oven.services.OvenProcessService;
import com.electric.appliances.oven.services.OvenService;
import com.electric.appliances.oven.services.ProgramService;
import com.electric.appliances.oven.utils.OvenState;
import com.electric.appliances.oven.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("api")
public class OvenController {


    private static final String OVEN_START_API ="start/oven";
    private static final String OVEN_STOP_API ="stop/oven/{ovenId}";
    private static final String OVEN_STATE_API ="state/oven/{ovenId}";
    private static final String OVEN_PROGRAMS_GET_API = "/oven/{ovenId}/programs";
    private static final String OVEN_PROGRAM_SET_API ="/oven/{ovenId}/program/{programId}";

    private final OvenService ovenService;
    private final ProgramService programService;
    private final OvenProcessService ovenProcessService;


    @Autowired
    public OvenController(OvenServiceImpl ovenService , ProgramServiceImpl programService , OvenProcessServiceImpl ovenProcessService){
     this.ovenService =ovenService;
     this.programService = programService;
     this.ovenProcessService = ovenProcessService;

    }

    @GetMapping(OVEN_START_API)
    public Oven start(){


     Oven oven =  ovenService.getOven();

     if(!Utils.isOvenStarted(oven))
        {
            oven.setOvenState(OvenState.STARTED);
            return ovenService.updateOvenStatus(oven);
        }

        return oven;

    }

    @GetMapping(OVEN_STOP_API)
    public Oven stop(@PathVariable long ovenId){

       Oven oven = ovenService.getOvenById(ovenId);
       if(Utils.isOvenStarted(oven) || Utils.getOvenState(oven).equals(OvenState.PROCESS.toString())){
           oven.setOvenState(OvenState.STOPPED);
           return ovenService.updateOvenStatus(oven);
       }
      return oven;
    }

    @GetMapping(OVEN_STATE_API)
    public OvenState getState(@PathVariable long ovenId){

     Oven oven = ovenService.getOvenById(ovenId);
     return oven.getOvenState();
    }

    @GetMapping(OVEN_PROGRAMS_GET_API)
    public ProgramsWrapper getPrograms(@PathVariable long ovenId){

    ProgramsWrapper programsWrapper = new ProgramsWrapper();
    programsWrapper.setPrograms(programService.getAllPrograms(ovenId));
    return programsWrapper;

    }

     @GetMapping(OVEN_PROGRAM_SET_API)
     public OvenProcess setOvenProgram(@PathVariable long ovenId , @PathVariable long programId){

        Oven oven = ovenService.getOvenById(ovenId);
        if(Utils.getOvenState(oven).equals(OvenState.STOPPED.toString() )){

            throw  new OvenNotStartedException("Oven is stopped. Please start the oven");
        }
         Optional<Program> program = programService.getProgram(programId , ovenId);
       if(program.isPresent()){
         return  ovenProcessService.startOvenProcess(oven ,program.get());
       }
        throw new ProgramNotFoundException("The program is not configured for ovenId provided");

     }
}
