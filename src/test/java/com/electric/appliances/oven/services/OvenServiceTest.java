package com.electric.appliances.oven.services;

import com.electric.appliances.oven.exceptions.OvenAlreadyExistException;
import com.electric.appliances.oven.exceptions.OvenNotFoundException;
import com.electric.appliances.oven.exceptions.OvenNotStartedException;
import com.electric.appliances.oven.models.Oven;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.electric.appliances.oven.models.OvenDto;
import com.electric.appliances.oven.models.Program;
import com.electric.appliances.oven.repository.OvenRepository;
import com.electric.appliances.oven.services.Impl.OvenServiceImpl;
import com.electric.appliances.oven.utils.OvenState;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OvenServiceTest {

    @Mock
    OvenRepository ovenRepository;

    @InjectMocks
    OvenServiceImpl ovenService;

    @BeforeAll
    public static void setUp() {
        MockitoAnnotations.initMocks(OvenServiceTest.class);
    }

    @Test
    public void test_get_ovens() {

        List<Oven> ovenList = new ArrayList<>();

        ovenList.add(buildOven("TN", "TN-101"));
        ovenList.add(buildOven("TN", "TN-102"));

        Mockito.when(ovenRepository.findAll()).thenReturn(ovenList);
        assertThat(ovenService.getOvens()).isNotEmpty();
    }

    @Test
    public void test_get_oven() {

        Mockito.when(ovenRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(buildOven("TN", "TN-101")));

        assertThat(ovenService.getOvenById(1L)).isNotNull();
    }

    @Test
    public void test_getOven_NotFound() {

        Mockito.when(ovenRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
        assertThatExceptionOfType(OvenNotFoundException.class).isThrownBy(() ->
                ovenService.getOvenById(1L)
        );

    }

    @Test
    public void test_onboard_oven() {
        Oven ovenOriginal = buildOven("TN", "TN-101");
        ovenOriginal.setId(1L);
        Mockito.when(ovenRepository.save(ArgumentMatchers.any())).thenReturn(ovenOriginal);
        OvenDto ovenDto = new OvenDto();
        ovenDto.setName("test-oven");
        ovenDto.setModel("TN");
        ovenDto.setVersion("TN-101");

        assertThat(ovenService.onBoard(ovenDto).getId()).isEqualTo(ovenOriginal.getId());
    }

    @Test
    public void test_oven_alreadyExist() {

        Oven oven = buildOven("TN", "TN-101");
        Mockito.when(ovenRepository.findOvenByVersionAndModel(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Optional.of(oven));
        OvenDto ovenDto = new OvenDto();
        ovenDto.setName("test-oven");
        ovenDto.setModel("TN");
        ovenDto.setVersion("TN-101");
        assertThatExceptionOfType(OvenAlreadyExistException.class).isThrownBy(() -> ovenService.onBoard(ovenDto));
    }

    @Test
    public void test_changeState_FROM_STOPPED_TO_STARTED() {
        Oven oven = buildOven("TN", "TN-101");
        oven.setId(1L);
        Mockito.when(ovenRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(oven));

        Program program = new Program();
        program.setTemperature(0L);
        program.setOvenState(OvenState.STARTED);

        Oven modifiedOven = ovenService.setOvenProgram(1L, program);
        assertThat(modifiedOven.getProgram().getTemperature()).isEqualTo(0);
        assertThat(modifiedOven.getProgram().getOvenState()).isEqualTo(OvenState.STARTED);
    }

    @Test
    public void test_changeStateFrom_STARTED_TO_COOKING() {

        Oven oven = buildOven("TN", "TN-101");
        oven.setId(1L);
        oven.getProgram().setOvenState(OvenState.STARTED);
        Mockito.when(ovenRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(oven));

        Program program = new Program();
        program.setTemperature(450L);
        program.setOvenState(OvenState.COOKING);
        Oven modifiedOven = ovenService.setOvenProgram(1L, program);
        assertThat(modifiedOven.getProgram().getTemperature()).isEqualTo(450);
        assertThat(modifiedOven.getProgram().getOvenState()).isEqualTo(OvenState.COOKING);

    }

    @Test
    public void test_changeStateFrom_COOKING_TO_IDLE() {

        Oven oven = buildOven("TN", "TN-101");
        oven.setId(1L);
        oven.getProgram().setOvenState(OvenState.COOKING);
        Mockito.when(ovenRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(oven));

        Program program = new Program();
        program.setTemperature(0L);
        program.setOvenState(OvenState.IDLE);
        Oven modifiedOven = ovenService.setOvenProgram(1L, program);
        assertThat(modifiedOven.getProgram().getTemperature()).isEqualTo(0);
        assertThat(modifiedOven.getProgram().getOvenState()).isEqualTo(OvenState.IDLE);

    }

    @Test
    public void test_changeStateFrom_STOPPED_TO_COOKING() {

        Oven oven = buildOven("TN", "TN-101");
        oven.setId(1L);
        oven.getProgram().setOvenState(OvenState.STOPPED);
        Mockito.when(ovenRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(oven));

        Program program = new Program();
        program.setTemperature(450L);
        program.setOvenState(OvenState.COOKING);
        assertThatExceptionOfType(OvenNotStartedException.class).isThrownBy(() ->
                ovenService.setOvenProgram(1L, program)
        );

    }


    public static Oven buildOven(String model, String version) {
        Oven oven = new Oven();
        oven.setName("test-oven");
        oven.setModel(model);
        oven.setVersion(version);
        Program program = new Program();
        program.setOvenState(OvenState.STOPPED);
        program.setTemperature(0L);
        oven.setProgram(program);

        return oven;
    }

}
