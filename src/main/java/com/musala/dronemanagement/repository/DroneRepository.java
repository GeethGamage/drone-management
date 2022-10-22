package com.musala.dronemanagement.repository;


import com.musala.dronemanagement.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 1:55:38 PM
 */
public interface DroneRepository extends JpaRepository<Drone, Long> {

    Drone findBySerialNumber(String serialNumber);
}