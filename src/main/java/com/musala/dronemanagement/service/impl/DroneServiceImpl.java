package com.musala.dronemanagement.service.impl;

import com.musala.dronemanagement.dto.DroneDTO;
import com.musala.dronemanagement.entity.Drone;
import com.musala.dronemanagement.repository.DroneRepository;
import com.musala.dronemanagement.service.DroneService;
import com.musala.dronemanagement.util.ResponseCode;
import com.musala.dronemanagement.util.ResponseGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 2:29:11 PM
 */

@Service
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DroneServiceImpl implements DroneService{

    private DroneRepository droneRepository;

    private ResponseGenerator responseGenerator;

    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ResponseEntity<Object> saveDrone(DroneDTO droneDTO) throws Exception{
        try {
            Date sysDate = new Date();
            Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(droneDTO.getSerialNumber()))
                    .orElse(new Drone());

            if (Objects.isNull(drone.getSerialNumber())) {
                drone = modelMapper.map(droneDTO, Drone.class);
                drone.setCreatedDate(sysDate);
                drone.setModifiedDate(sysDate);

                droneRepository.save(drone);

                log.info("Drone saved successfully");
                return responseGenerator.generateSuccessResponse(HttpStatus.CREATED,
                        ResponseCode.DRONE_SAVE_SUCCESS, "Drone Added Successfully" ,
                        drone);

            } else {
                return responseGenerator.generateErrorResponse(HttpStatus.CONFLICT,
                        ResponseCode.ALREADY_EXISTS, "Drone Serial Number Already Exists");
            }
        } catch (Exception ex) {
            log.error(ex);
            throw ex;
        }
    }


}
