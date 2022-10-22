package com.musala.dronemanagement.controller;


import com.musala.dronemanagement.dto.DroneDTO;
import com.musala.dronemanagement.dto.request.DroneRequest;
import com.musala.dronemanagement.service.DroneService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DroneController {

    private final DroneService droneService;

    private final ModelMapper modelMapper;

    @PostMapping(value = "/drone", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> saveDrones(@Valid @RequestBody DroneRequest droneRequest) throws Exception {
        log.debug("Received Drone add Request {} -", droneRequest);
        return droneService.saveDrone(modelMapper.map(droneRequest, DroneDTO.class));
    }

}
