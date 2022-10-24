package com.musala.dronemanagement.scheduler;


import com.musala.dronemanagement.entity.Drone;
import com.musala.dronemanagement.repository.DroneRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Log4j2
@EnableScheduling
@EnableAsync
public class DroneBatteryChecker {


    private DroneRepository droneRepository;

    public DroneBatteryChecker(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }


    @Scheduled(fixedRate = 5000)
    public void droneBatteryLogger() throws InterruptedException {

        List<Drone> dronesBatteryLevels = droneRepository.findAll();

        for (Drone drone : dronesBatteryLevels) {
            log.info("SerialNumber:  " + drone.getSerialNumber() + "  Battery Level: " + drone.getBatteryCapacity().toString());
        }
        Thread.sleep(5000);
    }

}
