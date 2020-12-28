package com.electric.appliances.oven.services.Impl;

import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.models.OvenProcess;
import com.electric.appliances.oven.models.Program;
import com.electric.appliances.oven.repository.OvenProcessRepository;
import com.electric.appliances.oven.repository.OvenRepository;
import com.electric.appliances.oven.services.OvenProcessService;
import com.electric.appliances.oven.utils.OvenState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OvenProcessServiceImpl implements OvenProcessService {


    private  final OvenProcessRepository ovenProcessRepository;
    private  final OvenRepository ovenRepository ;

    @Autowired
    public OvenProcessServiceImpl(OvenProcessRepository ovenProcessRepository , OvenRepository ovenRepository){
        this.ovenProcessRepository = ovenProcessRepository;
        this.ovenRepository = ovenRepository;
    }

    @Override
    public OvenProcess startOvenProcess(Oven oven , Program program){
        OvenProcess ovenProcess = new OvenProcess();
        ovenProcess.setOven(oven);
        ovenProcess.setProgram(program);
        ovenProcess.setProcessStatus("ACTIVE");
        oven.setOvenState(OvenState.PROCESS);
        ovenRepository.save(oven);
        return  ovenProcessRepository.save(ovenProcess);
    }
}
