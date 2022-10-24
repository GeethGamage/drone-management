package com.musala.dronemanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class DroneMedicationDTO implements Serializable {


    private static final long serialVersionUID = 7691723783515752319L;

    private String serialNumber;

    private List<Integer> medications;
}
