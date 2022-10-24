package com.musala.dronemanagement.entity;

import com.musala.dronemanagement.util.enums.ModelType;
import com.musala.dronemanagement.util.enums.StateType;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 12:11:38 PM
 */

@Data
@Entity
@Table(name = "drone")
public class Drone implements Serializable {

    private static final long serialVersionUID = 884286408176406224L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "serial_number", length = 100, unique = true, nullable = false)
    private String serialNumber;

    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private ModelType model;

    @Column(name = "weight_limit")
    private Integer weightLimit;

    @Column(name = "battery_capacity")
    private Integer batteryCapacity;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private StateType state;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
}
