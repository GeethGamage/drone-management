package com.musala.dronemanagement.repository;

import com.musala.dronemanagement.entity.Drone;
import com.musala.dronemanagement.entity.DroneMedicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 1:54:35 PM
 */
public interface DroneMedicationRepository extends JpaRepository<DroneMedicationDetails, Integer> {

    List<DroneMedicationDetails> findAllByDrone(Drone drone);
}