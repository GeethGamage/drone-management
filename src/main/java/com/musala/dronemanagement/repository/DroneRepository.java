package com.musala.dronemanagement.repository;


import com.musala.dronemanagement.entity.Drone;
import com.musala.dronemanagement.util.enums.StateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 1:55:38 PM
 */
public interface DroneRepository extends JpaRepository<Drone, Integer> {

    Drone findBySerialNumber(String serialNumber);

    List<Drone> findAllByState(StateType state);
}