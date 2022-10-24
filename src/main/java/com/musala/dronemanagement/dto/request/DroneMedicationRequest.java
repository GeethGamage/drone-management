package com.musala.dronemanagement.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
public class DroneMedicationRequest {

    @NotEmpty(message = "Drone serial number required")
    private String serialNumber;

    @NotEmpty(message = "One or more medications required")
    private List<Integer> medications;
}
