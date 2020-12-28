package com.electric.appliances.oven.models;

import com.electric.appliances.oven.utils.OvenState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.List;

@Entity
public class Oven {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;



    private String name;
    private String model;
    private String version;
    @Enumerated(EnumType.STRING)
    @Column(name= "OVENSTATE")
    private OvenState ovenState;
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

    public OvenState getOvenState() {
        return ovenState;
    }

    public void setOvenState(OvenState ovenState) {
        this.ovenState = ovenState;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "oven", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Program> program;



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


}
