package com.musala.dronemanagement.service;

import com.musala.dronemanagement.dto.DroneDTO;
import org.springframework.http.ResponseEntity;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 2:21:35 PM
 */
public interface DroneService {

    ResponseEntity<Object> saveDrone(DroneDTO droneDTO) throws Exception;
}
