package com.musala.dronemanagement.dto;

import com.musala.dronemanagement.util.enums.ModelType;
import com.musala.dronemanagement.util.enums.StateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneDTO implements Serializable {

    private static final long serialVersionUID = -2760723783515752319L;

    private Integer id;

    private String serialNumber;

    private ModelType model;

    private StateType state;

    private Integer weightLimit;

    private Integer batteryCapacity;
}
