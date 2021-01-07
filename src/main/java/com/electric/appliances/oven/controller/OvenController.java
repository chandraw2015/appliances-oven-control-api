package com.electric.appliances.oven.controller;

import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.models.OvenDto;
import com.electric.appliances.oven.models.Program;
import com.electric.appliances.oven.services.Impl.OvenServiceImpl;
import com.electric.appliances.oven.services.OvenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api")
public class OvenController {


    private static final String OVENS_GET_API ="/ovens";
    private static final String OVEN_GET_API ="/oven/{ovenId}";
    private static final String OVEN_ONBOARD_API ="/oven/onboard";
    private static final String OVEN_PROGRAM_SET_API = "/oven/{ovenId}/program";

    private final OvenService ovenService;


    @Autowired
    public OvenController(OvenServiceImpl ovenService ){
     this.ovenService =ovenService;

    }

    @GetMapping(OVENS_GET_API)
    public List<Oven> getOvens(){


    return  this.ovenService.getOvens();
    }

    @GetMapping(OVEN_GET_API)
    public Oven getOven(@PathVariable long ovenId){

     return this.ovenService.getOvenById(ovenId);
    }

    @PostMapping(OVEN_ONBOARD_API)
    public Oven onBoardOven(@RequestBody OvenDto ovenDto){

       return this.ovenService.onBoard(ovenDto);

    }


     @PutMapping(OVEN_PROGRAM_SET_API)
     public Oven setOvenProgram(@PathVariable long ovenId , @RequestBody Program program){

      return this.ovenService.setOvenProgram(ovenId , program);
     }


}
