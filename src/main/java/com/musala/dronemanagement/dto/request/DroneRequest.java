package com.musala.dronemanagement.dto.request;

import com.musala.dronemanagement.annotation.ValueOfEnum;
import com.musala.dronemanagement.util.enums.ModelType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class DroneRequest{
    @NotNull(message = "Serial number required")
    @Length(max = 100, message = "maximum allowed 100 characters for serial number")
    private String serialNumber;

    @NotNull(message = "Model Required")
    @ValueOfEnum( enumClass = ModelType.class, message = "Invalid model provided")
    private String model;

    @Range(min = 1,max = 500)
    private Integer weightLimit;

    @NotNull(message = "Battery Capacity Required")
    private Integer batteryCapacity;
}
