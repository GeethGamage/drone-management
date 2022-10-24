package com.musala.dronemanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MedicationDTO implements Serializable {

    private static final long serialVersionUID = 6860723783515752319L;

    private String id;

    private String name;

    private BigDecimal weight;

    private String code;

    private String imageRef;
}
