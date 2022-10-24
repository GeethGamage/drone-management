package com.musala.dronemanagement.service;

import com.musala.dronemanagement.dto.DroneDTO;
import com.musala.dronemanagement.dto.DroneMedicationDTO;
import com.musala.dronemanagement.util.enums.StateType;
import org.springframework.http.ResponseEntity;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 2:21:35 PM
 */
public interface DroneService {

    ResponseEntity<Object> saveDrone(DroneDTO droneDTO) ;

    ResponseEntity<Object> getDronesByState(StateType state);

    ResponseEntity<Object> getBatteryLevelByDrone(String serialNumber);

    ResponseEntity<Object> loadMedicationItems(DroneMedicationDTO droneMedicationDTO);

    ResponseEntity<Object> getLoadedMedicationsByDrone(String serialNumber);
}
