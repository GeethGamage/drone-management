package com.musala.dronemanagement.dto.response;

import lombok.Data;
/**
 * @Author Geeth_G
 * @CreatedTime on 22-10-2022 4:55:38 PM
 */
@Data
public class ErrorResponse
{
    private String errorCode;

    private String errorDescription;
}
