package com.musala.dronemanagement.controller;

import com.musala.dronemanagement.dto.DroneDTO;
import com.musala.dronemanagement.dto.DroneMedicationDTO;
import com.musala.dronemanagement.dto.request.DroneMedicationRequest;
import com.musala.dronemanagement.dto.request.DroneRequest;
import com.musala.dronemanagement.service.DroneService;
import com.musala.dronemanagement.util.enums.StateType;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 3:11:21 PM
 */
@RestController
@RequestMapping("/api/v1")
@Log4j2
@CrossOrigin
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DroneController {

    private final DroneService droneService;

    private final ModelMapper modelMapper;

    @PostMapping(value = "/drone")
    public ResponseEntity<Object> saveDrones(@Valid @RequestBody DroneRequest droneRequest) {
        log.debug("Received registering a drone request {} -", droneRequest);
        return droneService.saveDrone(modelMapper.map(droneRequest, DroneDTO.class));
    }

    @GetMapping(value = "/drone/state/{state}")
    public ResponseEntity<Object> getDronesByState(@PathVariable StateType state) {
        log.debug("Received checking available drones for loading request {} -", state);
        return droneService.getDronesByState(state);
    }

    @GetMapping(value = "drone/battery/{serialNumber}")
    public ResponseEntity<Object> getBatteryLevelBySerialNumber(@PathVariable String serialNumber) {
        log.debug("Received check drone battery level for a given drone request {} -", serialNumber);
        return droneService.getBatteryLevelByDrone(serialNumber);
    }

    @PostMapping(value = "drone/medications")
    public ResponseEntity<Object> loadMedicationItems(@Valid @RequestBody DroneMedicationRequest droneMedicationRequest) {
        log.debug("Received loading a drone with medication items request {} -", droneMedicationRequest);
        return droneService.loadMedicationItems(modelMapper.map(droneMedicationRequest, DroneMedicationDTO.class));
    }

    @GetMapping(value = "drone/medications/{serialNumber}")
    public ResponseEntity<Object> getLoadedMedicationsByDrone(@PathVariable String serialNumber) {
        log.debug("Received checking loaded medication items for a given drone {} -", serialNumber);
        return droneService.getLoadedMedicationsByDrone(serialNumber);
    }





}
