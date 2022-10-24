package com.musala.dronemanagement.service.impl;

import com.musala.dronemanagement.dto.DroneDTO;
import com.musala.dronemanagement.dto.DroneMedicationDTO;
import com.musala.dronemanagement.dto.MedicationDTO;
import com.musala.dronemanagement.dto.response.DroneBatteryResponse;
import com.musala.dronemanagement.dto.response.DroneMedicationsResponse;
import com.musala.dronemanagement.entity.Drone;
import com.musala.dronemanagement.entity.DroneMedicationDetails;
import com.musala.dronemanagement.entity.Medication;
import com.musala.dronemanagement.exception.InvalidArgumentException;
import com.musala.dronemanagement.exception.NotAvailableException;
import com.musala.dronemanagement.repository.DroneMedicationRepository;
import com.musala.dronemanagement.repository.DroneRepository;
import com.musala.dronemanagement.repository.MedicationRepository;
import com.musala.dronemanagement.service.DroneService;
import com.musala.dronemanagement.util.CommonConstants;
import com.musala.dronemanagement.util.ResponseCode;
import com.musala.dronemanagement.util.ResponseGenerator;
import com.musala.dronemanagement.util.enums.StateType;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 2:29:11 PM
 */

@Service
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DroneServiceImpl implements DroneService {

    private DroneRepository droneRepository;

    private MedicationRepository medicationRepository;

    private DroneMedicationRepository droneMedicationRepository;

    private ResponseGenerator responseGenerator;

    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ResponseEntity<Object> saveDrone(DroneDTO droneDTO) {
        try {
            Date sysDate = new Date();

            // Check already exist drone for given serial number
            Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(droneDTO.getSerialNumber()))
                    .orElse(new Drone());

            if (Objects.isNull(drone.getSerialNumber())) {
                drone = modelMapper.map(droneDTO, Drone.class);
                drone.setCreatedDate(sysDate);
                drone.setModifiedDate(sysDate);
                droneRepository.save(drone);

                log.info("Drone saved successfully");
                return responseGenerator.generateSuccessResponse(HttpStatus.CREATED,
                        ResponseCode.DRONE_SAVE_SUCCESS, "Drone Added Successfully",
                        modelMapper.map(drone, DroneDTO.class));
            } else {
                return responseGenerator.generateErrorResponse(HttpStatus.CONFLICT,
                        ResponseCode.ALREADY_EXISTS, "Drone Serial Number Already Exists");
            }
        } catch (Exception ex) {
            log.error(ex);
            throw ex;
        }
    }

    @Override
    public ResponseEntity<Object> getDronesByState(StateType state) {
        try {
            // Get all drones by state
            List<Drone> drones = droneRepository.findAllByState(state);

            //check drone list empty or not
            if (drones.isEmpty()) {
                return responseGenerator.generateNoContentResponse();
            }


            List<DroneDTO> droneDTOList = drones.stream().map(d -> modelMapper.map(d, DroneDTO.class))
                    .collect(Collectors.toList());

            return responseGenerator.generateSuccessResponse(HttpStatus.OK, ResponseCode.DRONE_LIST_GET_SUCCESS,
                    "Drone List Get Successfully", droneDTOList);
        } catch (Exception ex) {
            log.error(ex);
            throw ex;
        }
    }

    @Override
    public ResponseEntity<Object> getBatteryLevelByDrone(String serialNumber) {
        try {

            //Fetch drone by serial number
            Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(serialNumber))
                    .orElseThrow(() -> new EntityNotFoundException("Drone Not Found"));

            return responseGenerator.generateSuccessResponse(HttpStatus.OK, ResponseCode.DRONE_BATTERY_LEVEL_GET_SUCCESS,
                    "Drone Battery Level Get Successfully", new DroneBatteryResponse(drone.getBatteryCapacity()));
        } catch (EntityNotFoundException ex) {
            log.info(ex);
            throw ex;
        } catch (Exception ex) {
            log.error(ex);
            throw ex;
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> loadMedicationItems(DroneMedicationDTO droneMedicationDTO) {
        try {
            Date sysDate = new Date();
            Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(droneMedicationDTO.getSerialNumber()))
                    .orElseThrow(() -> new EntityNotFoundException("Drone Not Found"));

            // drone battery check
            if (drone.getBatteryCapacity() < CommonConstants.MIN_BATTERY_LEVEL_FOR_LOADING) {
                throw new InvalidArgumentException("Battery level is below 25%");
            }

            // check drone is idle
            if (drone.getState() != StateType.IDLE) {
                throw new NotAvailableException("Drone not available at the moment");
            }

            // Get medication objects by id
            List<Medication> medications = medicationRepository.findAllById(droneMedicationDTO.getMedications());


            // check medications weight limit exceed or not
            double totalWeight = medications.stream().mapToDouble(value -> value.getWeight().doubleValue()).sum();
            if (totalWeight > drone.getWeightLimit())
                throw new InvalidArgumentException("Provided medications exceeds maximum loadable weight limit");


            medications.forEach(med -> {
                DroneMedicationDetails droneMedicationDetails = new DroneMedicationDetails();
                droneMedicationDetails.setDrone(drone);
                droneMedicationDetails.setMedication(med);
                droneMedicationDetails.setCreatedDate(sysDate);
                droneMedicationDetails.setModifiedDate(sysDate);
                droneMedicationRepository.save(droneMedicationDetails);
            });

            // update drone status
            drone.setState(StateType.LOADED);
            droneRepository.save(drone);

            log.info("Drone Medication Items Loaded Successfully");
            return responseGenerator.generateSuccessResponse(HttpStatus.CREATED,
                    ResponseCode.LOAD_DRONE_MEDICATIONS_SUCCESS, "Drone Medication Items Loaded Successfully",
                    modelMapper.map(drone, DroneDTO.class));
        } catch (EntityNotFoundException | InvalidArgumentException | NotAvailableException ex) {
            log.info(ex);
            throw ex;
        } catch (Exception ex) {
            log.error(ex);
            throw ex;
        }
    }


    @Override
    public ResponseEntity<Object> getLoadedMedicationsByDrone(String serialNumber) {
        try {
            //Fetch drone by serial number
            Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(serialNumber))
                    .orElseThrow(() -> new EntityNotFoundException("Drone Not Found"));

            //Get Drone medications details by drone
            List<DroneMedicationDetails> droneMedications = droneMedicationRepository.findAllByDrone(drone);

            if (droneMedications.isEmpty()){
                return responseGenerator.generateNoContentResponse();
            }

            List<MedicationDTO> medicationList = droneMedications.stream().map(m -> modelMapper.map(m.getMedication(), MedicationDTO.class))
                    .collect(Collectors.toList());

            DroneMedicationsResponse droneMedicationsResponse = new DroneMedicationsResponse();
            droneMedicationsResponse.setDroneId(drone.getId());
            droneMedicationsResponse.setSerialNumber(serialNumber);
            droneMedicationsResponse.setMedications(medicationList);

            return responseGenerator.generateSuccessResponse(HttpStatus.OK, ResponseCode.DRONE_MEDICATIONS_GET_SUCCESS,
                    "Drone Medications Get Successfully", droneMedicationsResponse);
        } catch (Exception ex) {
            log.error(ex);
            throw ex;
        }
    }




}
