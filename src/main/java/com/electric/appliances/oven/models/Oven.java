package com.electric.appliances.oven.models;

import com.electric.appliances.oven.exceptions.OvenNotStartedException;
import com.electric.appliances.oven.utils.OvenState;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Oven {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;



    private String name;
    private String model;
    private String version;
    @Embedded
    private Program program;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Oven{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", version='" + version + '\'' +
                ", program=" + program +
                '}';
    }

  public void stop(){

        this.program.setOvenState(OvenState.STOPPED);
        this.program.setTemperature(0L);
  }
  public void idle(long temperature){
        if(this.program.getOvenState().equals(OvenState.STOPPED))
        {
            throw new OvenNotStartedException("Oven is not started, please start the oven");
        }
        this.program.setTemperature(temperature);
        this.program.setOvenState(OvenState.IDLE);
  }

  public void cook(long temperature){
      if(this.program.getOvenState().equals(OvenState.STOPPED))
      {
          throw new OvenNotStartedException("Oven is not started, please start the oven");
      }
      this.program.setTemperature(temperature);
      this.program.setOvenState(OvenState.COOKING);
    }

   public void start(long temperature){
        this.program.setTemperature(temperature);
        this.program.setOvenState(OvenState.STARTED);
   }

}
