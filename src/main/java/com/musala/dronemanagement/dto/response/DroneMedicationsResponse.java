package com.musala.dronemanagement.dto.response;

import com.musala.dronemanagement.dto.MedicationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneMedicationsResponse {
    private Integer droneId;
    private String serialNumber;
    List<MedicationDTO> medications;
}
