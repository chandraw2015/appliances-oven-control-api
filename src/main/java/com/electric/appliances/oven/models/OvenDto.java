package com.electric.appliances.oven.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OvenDto implements Serializable {
    private static final long serialVersionUID = 11568845755587889L;

    @NotNull
    @Length(min = 3 , max=100 , message="Name must be between 3 and 100 characters")
    private String name;

    @NotNull
    @Length(min = 2 , max=100 , message="Model must be between 2 and 100 characters")
    private String model;

    @NotNull
    @Length(min = 2 , max=100, message="Version must be between 2 and 100 characters")
    private String version;


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


}
