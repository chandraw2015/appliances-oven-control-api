package com.electric.appliances.oven.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.electric.appliances.oven.exceptions.OvenAlreadyExistException;
import com.electric.appliances.oven.exceptions.OvenNotFoundException;
import com.electric.appliances.oven.exceptions.OvenNotStartedException;
import com.electric.appliances.oven.models.Oven;
import com.electric.appliances.oven.models.OvenDto;
import com.electric.appliances.oven.models.Program;
import com.electric.appliances.oven.services.Impl.OvenServiceImpl;
import com.electric.appliances.oven.utils.OvenState;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class OvenControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private OvenServiceImpl ovenService;

    @Test
    public void test_getOven_API() throws Exception {

        List<Oven> ovens = new ArrayList<>();
        ovens.add(buildOven(1L, "TN", "TN-101"));

        Mockito.when(ovenService.getOvens()).thenReturn(ovens);

        this.mockMvc.perform(get("/api/ovens")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("test-oven"))
                .andExpect(jsonPath("$.[0].model").value("TN"))
                .andExpect(jsonPath("$.[0].version").value("TN-101"));


    }

    @Test
    public void test_getOven_byId() throws Exception {

        Oven oven = buildOven(1L, "TN", "TN-101");
        Mockito.when(ovenService.getOvenById(ArgumentMatchers.anyLong())).thenReturn(oven);
        this.mockMvc.perform(get("/api/oven/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test-oven"))
                .andExpect(jsonPath("$.model").value("TN"))
                .andExpect(jsonPath("$.version").value("TN-101"));


    }

    @Test
    public void test_getOvenByWrongId_Not_Found() throws Exception {

        Mockito.when(ovenService.getOvenById(ArgumentMatchers.anyLong())).thenThrow(new OvenNotFoundException("Oven with provided id is not found"));
        this.mockMvc.perform(get("/api/oven/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.message").value("Oven with provided id is not found"));
    }


    @Test
    public void test_onBoardOven_success() throws Exception {

        Oven oven = buildOven(1L, "TN", "TN-101");
        OvenDto ovenDto = new OvenDto();
        ovenDto.setName("test-oven");
        ovenDto.setModel("TN");
        ovenDto.setVersion("TN-101");
        Mockito.when(this.ovenService.onBoard(ArgumentMatchers.any())).thenReturn(oven);
        this.mockMvc.perform(post("/api/oven/onboard")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertObjectToJsonString(ovenDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").isNotEmpty());

    }

    @Test
    public void test_onBoard_existingOven() throws Exception {
        Mockito.when(this.ovenService.onBoard(ArgumentMatchers.any())).thenThrow(new OvenAlreadyExistException("Oven with provided Model and Version already exists"));
        OvenDto ovenDto = new OvenDto();
        ovenDto.setName("test-oven");
        ovenDto.setModel("TN");
        ovenDto.setVersion("TN-101");
        this.mockMvc.perform(post("/api/oven/onboard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonString(ovenDto)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.message").value("Oven with provided Model and Version already exists"));
    }

    @Test
    public void test_programOven_Success() throws Exception {
        Oven oven = buildOven(1L, "TN", "TN-101");
        oven.getProgram().setOvenState(OvenState.STARTED);
        Program program = new Program();
        program.setOvenState(OvenState.STARTED);
        program.setTemperature(0L);
        Mockito.when(this.ovenService.setOvenProgram(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenReturn(oven);
        this.mockMvc.perform(put("/api/oven/1/program")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonString(program)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test-oven"))
                .andExpect(jsonPath("$.program.ovenState").value("STARTED"));
    }
    
    @Test
    public void test_wrongProgrammedOven() throws  Exception{
        Program program = new Program();
        program.setOvenState(OvenState.STARTED);
        program.setTemperature(0L);
        Mockito.when(ovenService.setOvenProgram(ArgumentMatchers.anyLong(), ArgumentMatchers.any())).thenThrow(new OvenNotStartedException("Oven is not started. Please start the oven"));
        this.mockMvc.perform(put("/api/oven/1/program")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonString(program)))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.message").value("Oven is not started. Please start the oven"));

    }


    private static Oven buildOven(Long id, String model, String version) {
        Oven oven = new Oven();
        oven.setId(id);
        oven.setName("test-oven");
        oven.setModel(model);
        oven.setVersion(version);
        Program program = new Program();
        program.setOvenState(OvenState.STOPPED);
        program.setTemperature(0L);
        oven.setProgram(program);

        return oven;
    }

    public static String convertObjectToJsonString(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(object);
    }

}
