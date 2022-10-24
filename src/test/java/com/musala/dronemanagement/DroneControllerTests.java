package com.musala.dronemanagement;


import com.musala.dronemanagement.controller.DroneController;
import com.musala.dronemanagement.dto.DroneDTO;
import com.musala.dronemanagement.dto.request.DroneRequest;
import com.musala.dronemanagement.dto.response.SuccessResponse;
import com.musala.dronemanagement.service.impl.DroneServiceImpl;
import com.musala.dronemanagement.util.ResponseCode;
import com.musala.dronemanagement.util.enums.ModelType;
import com.musala.dronemanagement.util.enums.StateType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class DroneControllerTests {

    @Autowired
    DroneController droneController;

    @Mock
    DroneServiceImpl droneService;

    @Test
    public void testRegisterDrone() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        SuccessResponse responseDTO = new SuccessResponse();
        responseDTO.setResponseCode(ResponseCode.DRONE_SAVE_SUCCESS);
        responseDTO.setResponseDescription("Drone Added Successfully");
        responseDTO.setData(new DroneDTO(1, "0001234", ModelType.Lightweight, StateType.IDLE, 400, 50));

        when(droneService.saveDrone(any(DroneDTO.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(responseDTO));

        DroneRequest droneRequest = new DroneRequest("0001234", "Lightweight", "IDLE", 400, 50);
        ResponseEntity<Object> responseEntity = droneController.saveDrones(droneRequest);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody()).isEqualTo(responseDTO);
    }


}
