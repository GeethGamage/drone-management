package com.musala.dronemanagement.util;

import com.musala.dronemanagement.dto.response.ErrorResponse;
import com.musala.dronemanagement.dto.response.SuccessResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 5:15:18 PM
 */
@Component
@Log4j2
public class ResponseGenerator {

    public ResponseEntity<Object> generateSuccessResponse(HttpStatus httpStatus, String responseCode, String responseDescription, Object dataObject) {
        SuccessResponse responseDTO = new SuccessResponse();
        responseDTO.setResponseCode(responseCode);
        responseDTO.setResponseDescription(responseDescription);
        responseDTO.setData(dataObject);
        return ResponseEntity.status(httpStatus).body(responseDTO);
    }

    public ResponseEntity<Object> generateSuccessResponse(Object requestBean, HttpStatus httpStatus, String responseCode, String responseDescription) throws Exception {
        SuccessResponse responseDTO = new SuccessResponse();
        responseDTO.setResponseCode(responseCode);
        responseDTO.setResponseDescription(responseDescription);
        return ResponseEntity.status(httpStatus).body(responseDTO);
    }


    public ResponseEntity<Object> generateErrorResponse(HttpStatus httpStatus, String errorCode, String errorMessage) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(errorCode);
        errorResponse.setErrorDescription(errorMessage);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }


}
