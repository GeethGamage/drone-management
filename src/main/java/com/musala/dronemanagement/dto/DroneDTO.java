package com.musala.dronemanagement.dto;

import com.musala.dronemanagement.util.enums.ModelType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class DroneDTO implements Serializable {

    private static final long serialVersionUID = -2760723783515752319L;

    private String serialNumber;

    private ModelType model;

    private Integer weightLimit;

    private Integer batteryCapacity;
}
